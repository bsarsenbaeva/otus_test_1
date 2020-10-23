package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

public class MainPage extends AbstractPage {
    private static final String URL = "https://otus.ru/";
    By buttonOpenLoginPage = By.cssSelector("button.header2__auth");
    By buttonMenuDropdown = By.cssSelector("p.header2-menu__item-text__username");
    By buttonOpenPersonalData = By.cssSelector(".header2-menu__dropdown-text_name");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть сайт")
    public MainPage openSite() {
        driver.get(URL);
        driver.manage().window().maximize();
        Allure.addAttachment("Главная страница", new ByteArrayInputStream
                (((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        return this;
    }

    @Step("Открыть профиль")
    public LoginPage openLoginPage() {
        driver.findElement(buttonOpenLoginPage).click();
        Allure.addAttachment("Страница профиля", new ByteArrayInputStream
                (((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        return new LoginPage(driver);
    }

    @Step("Добавить личную информацию")
    public PersonalDataPage openPersonalDataPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(buttonMenuDropdown).click();
        driver.findElement(buttonOpenPersonalData).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_fname")));
        return new PersonalDataPage(driver);
    }

    @Step("Очистить куки")
    public MainPage deleteCookies() {
        driver.manage().deleteAllCookies();
        return this;
    }
}