import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class YandexTest {
    protected static WebDriver driver;
    private final Logger logger = LogManager.getLogger(YandexTest.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
    }

    @After
    public void setDown() {
        if (driver != null) {
//            driver.quit();
        }
    }

    @Test
    public void addPhoneToCompareTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://market.yandex.kz/");
        logger.info("Открыта страница яндес маркет");
        // find mobile phones
        WebElement searchInput = driver.findElement(By.id("header-search"));
        searchInput.sendKeys("мобильные телефоны", Keys.DOWN, Keys.ENTER);
        //sort by manufacturer
        WebElement checkBoxSamsung = wait.until(presenceOfElementLocated(By.cssSelector(".\\_1-l0XiE_ze:nth-child(8) .LhMupC0dLR")));
        checkBoxSamsung.click();
        checkBoxSamsung.isSelected();
        WebElement checkBoxXiaomi = driver.findElement(By.cssSelector(".\\_1-l0XiE_ze:nth-child(12) .LhMupC0dLR"));
        checkBoxXiaomi.click();
        checkBoxXiaomi.isSelected();
        logger.info("Сортировка произведена");
        //wait until loader is invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("._3b6DT4JVj4")));
        //add phones to compare
        WebElement firstPhoneName = driver.findElement(By.cssSelector(".\\_1O1OnAPlSR:nth-child(1) > .d0N9PZYfeg span"));
        Actions actions = new Actions(driver);
        actions.moveToElement(firstPhoneName).build().perform();
        WebElement addFirstPhoneToCompare = driver.findElement(By.cssSelector(".\\_1O1OnAPlSR:nth-child(1) .\\_1CXljk9rtd"));
        addFirstPhoneToCompare.click();
        WebElement firstPhoneAddedMessage = driver.findElement(By.cssSelector("._1_ABPFjOJQ"));
        wait.until(ExpectedConditions.visibilityOf(firstPhoneAddedMessage));
        String firstPhoneAddedText = firstPhoneAddedMessage.getText();
        Assert.assertTrue(firstPhoneAddedText.contains("добавлен к сравнению"));
        logger.info("Первый телефон добавлен");
        //add second phone
        WebElement secondPhoneName = driver.findElement(By.cssSelector(".\\_1O1OnAPlSR:nth-child(2) > .d0N9PZYfeg span"));
        actions.moveToElement(secondPhoneName).build().perform();
        WebElement addSecondPhoneToCompare = driver.findElement(By.cssSelector(".\\_1O1OnAPlSR:nth-child(2) .\\_1CXljk9rtd"));
        addSecondPhoneToCompare.click();
        WebElement secondPhoneAddedMessage = driver.findElement(By.cssSelector("._1_ABPFjOJQ"));
        wait.until(ExpectedConditions.visibilityOf(secondPhoneAddedMessage));
        String secondPhoneAddedText = secondPhoneAddedMessage.getText();
        Assert.assertTrue(secondPhoneAddedText.contains("добавлен к сравнению"));
        logger.info("Второй телефон добавлен");
        // open Compare window
        driver.get("https://market.yandex.kz/compare");
        logger.info("Открыта страница сравнения");
        Assert.assertTrue(driver.findElements(By.cssSelector("._3B3AAKx4qr .LwwocgVx0q:nth-child(3) img")).isEmpty());
        WebElement allSpecificationsButton = driver.findElement(By.cssSelector(".MOYcCv2eIJ:nth-child(3)"));
        allSpecificationsButton.click();
        WebElement operatingSystemText = driver.findElement(By.xpath("//div[contains(text(), 'Операционная система')]"));
        operatingSystemText.isDisplayed();
        WebElement differentSpecificationsButton = driver.findElement(By.cssSelector(".\\_1_bHOFDacR:nth-child(2)"));
        differentSpecificationsButton.click();
        Assert.assertTrue(driver.findElements(By.xpath("//div[contains(text(), 'Операционная система')]")).isEmpty());
    }
}
