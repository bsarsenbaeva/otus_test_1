import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.log4j.Logger;

public class FirstTest {
    private static final Logger log = Logger.getLogger(FirstTest.class);
    protected static WebDriver driver;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://otus.ru/");
        log.info("Сайт открыт");

        String title_text = driver.findElement(By.cssSelector("div .title-new__text")).getText();

        Assert.assertTrue(title_text.contains("Авторские онлайн‑курсы для профессионалов"));
        log.error("Это сообщение ошибки");

        driver.quit();
    }
}
