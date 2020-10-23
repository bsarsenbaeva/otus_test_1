package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    By emailLocator = By.cssSelector("div.new-input-line_slim:nth-child(3) input");
    By passwordLocator = By.cssSelector(".js-psw-input");
    By loginButtonLocator = By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести е-мейл")
    public LoginPage typeEmail(String email) {
        driver.findElement(emailLocator).sendKeys(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    @Step("Нажать авторизоваться")
    public MainPage submitLogin() {
        driver.findElement(loginButtonLocator).submit();
        return new MainPage(driver);
    }

}