import driver.Driver;
import factory.WebDriverName;
import factory.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import static factory.DriverType.CHROME;

public class SecondTest extends WebDriverFactory {
    Driver driver;
    WebDriver webDriver;

    @Before
    public void setUp() {
        String browserName = getParameter("browser");
        driver = new Driver(browserName);
        WebDriverFactory factory = new WebDriverFactory();
        ChromeOptions options = new ChromeOptions();
        WebDriverName chrome = factory.createDriver(CHROME);
        webDriver = chrome.createNewDriver(browserName, options);
    }

    @Test
    public void openContactsPage() {
        webDriver.get("https://otus.ru/");
        webDriver.manage().window().maximize();
        WebElement contactsButton = driver.findElement(By.cssSelector(".header2_subheader-container__right a[href='/contacts/']"));
        contactsButton.click();
        String title = driver.findElement(By.cssSelector(".container .title__text")).getText();

        Assert.assertTrue(title.contains("Контакты"));
    }

    @After
    public void quitDriver() {
        if (webDriver != null) {
            webDriver.close();
        }
    }

    private String getParameter(String name) {
        String value = System.getProperty(name);
        if (value == null)
            throw new RuntimeException(name + " is not a parameter!");

        if (value.isEmpty())
            throw new RuntimeException(name + " is empty!");

        return value;
    }
}
