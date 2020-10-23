package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDataPage extends AbstractPage {
    By nameField = By.cssSelector("#id_fname");
    By nameLatinField = By.cssSelector("#id_fname_latin");
    By lastNameField = By.cssSelector("#id_lname");
    By lastNameLatinField = By.cssSelector("#id_lname_latin");
    By blogNameField = By.cssSelector("#id_blog_name");
    By birthDateField = By.cssSelector(".input-icon > input:nth-child(1)");
    By buttonSave = By.cssSelector("button.button_md-4:nth-child(1)");

    public PersonalDataPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить поля с личной информацией")
    public PersonalDataPage addPersonalInfo() {
        driver.findElement(nameField).sendKeys("Бакытгуль");
        driver.findElement(nameLatinField).sendKeys("Bakytgul");
        driver.findElement(lastNameField).sendKeys("Сарсенбаева");
        driver.findElement(lastNameLatinField).sendKeys("Sarsenbayeva");
        driver.findElement(blogNameField).sendKeys("Bakytgul");
        driver.findElement(birthDateField).sendKeys("14.04.1993");
        return this;
    }

    @Step("Добавить первого контакта")
    public PersonalDataPage addFirstContact() {
        driver.findElement(By.cssSelector("button.lk-cv-block__action:nth-child(6)")).click();
        driver.findElement(By.cssSelector("div.js-formset-row:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div.js-formset-row:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > button:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#id_contact-1-value")).sendKeys("test");
        return this;
    }

    @Step("Добавить второго контакта")
    public PersonalDataPage addSecondContact() {
        driver.findElement(By.cssSelector("button.lk-cv-block__action:nth-child(6)")).click();
        driver.findElement(By.cssSelector("div.js-formset-row:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div.js-formset-row:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > button:nth-child(4)")).click();
        driver.findElement(By.cssSelector("#id_contact-1-value")).sendKeys("test");
        return this;
    }

    @Step("Сохранить изменения")
    public PersonalDataPage saveChanges() {
        driver.findElement(buttonSave).click();
        return this;
    }

    @Step("Проверить личные данные")
    public PersonalDataPage assertData() {
        String name = driver.findElement(nameField).getAttribute("value");
        String nameLatin = driver.findElement(nameLatinField).getAttribute("value");
        String lastName = driver.findElement(lastNameField).getAttribute("value");
        String lastNameLatin = driver.findElement(lastNameLatinField).getAttribute("value");
        String blogName = driver.findElement(blogNameField).getAttribute("value");
        String birthDate = driver.findElement(birthDateField).getAttribute("value");

        Assert.assertEquals(name, "БакытгульБакытгуль");
        Assert.assertEquals(nameLatin, "Bakytgul");
        Assert.assertEquals(lastName, "СарсенбаеваСарсенбаева");
        Assert.assertEquals(lastNameLatin, "Sarsenbayeva");
        Assert.assertEquals(blogName, "Bakytgul");
        Assert.assertEquals(birthDate, "14.04.1993");
        return this;
    }
}
