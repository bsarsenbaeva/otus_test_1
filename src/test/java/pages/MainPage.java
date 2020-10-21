package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
    private static final String URL = "https://otus.ru/";
    By buttonOpenLoginPage = By.cssSelector("button.header2__auth");
    By buttonMenuDropdown = By.cssSelector("p.header2-menu__item-text__username");
    By buttonOpenPersonalData = By.cssSelector(".header2-menu__dropdown-text_name");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage openSite() {
        driver.get(URL);
        driver.manage().window().maximize();
        return this;
    }

    public LoginPage openLoginPage() {
        driver.findElement(buttonOpenLoginPage).click();
        return new LoginPage(driver);
    }

    public PersonalDataPage openPersonalDataPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(buttonMenuDropdown).click();
        driver.findElement(buttonOpenPersonalData).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_fname")));
        return new PersonalDataPage(driver);
    }

    public MainPage deleteCookies() {
        driver.manage().deleteAllCookies();
        return this;
    }
}