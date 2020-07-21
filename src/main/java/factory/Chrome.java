package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Chrome implements WebDriverName {

    @Override
    public WebDriver createNewDriver(String webDriverName, FirefoxOptions options) {
        System.setProperty("webdriver.chrome.driver", "/Users/bakytgul/Drivers/chromedriver");
        return new ChromeDriver(options);
    }

    @Override
    public WebDriver createNewDriver(String webDriverName, ChromeOptions options) {
        System.setProperty("webdriver.chrome.driver", "/Users/bakytgul/Drivers/chromedriver");
        return new ChromeDriver(options);
    }

    @Override
    public WebDriver createNewDriver(String webDriverName) {
        System.setProperty("webdriver.chrome.driver", "/Users/bakytgul/Drivers/chromedriver");
        return new ChromeDriver();
    }
}
