package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox implements WebDriverName {

    @Override
    public WebDriver createNewDriver(String webDriverName, FirefoxOptions options) {
        System.setProperty("webdriver.gecko.driver", "/Users/bakytgul/Drivers/geckodriver");
        return new FirefoxDriver(options);
    }

    @Override
    public WebDriver createNewDriver(String webDriverName, ChromeOptions options) {
        System.setProperty("webdriver.gecko.driver", "/Users/bakytgul/Drivers/geckodriver");
        return new FirefoxDriver(options);
    }

    @Override
    public WebDriver createNewDriver(String webDriverName) {
        System.setProperty("webdriver.gecko.driver", "/Users/bakytgul/Drivers/geckodriver");
        return new FirefoxDriver();
    }
}
