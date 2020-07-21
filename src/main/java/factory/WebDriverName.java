package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public interface WebDriverName {
    WebDriver createNewDriver(String webDriverName, FirefoxOptions options);

    WebDriver createNewDriver(String webDriverName, ChromeOptions options);

    WebDriver createNewDriver(String webDriverName);
}
