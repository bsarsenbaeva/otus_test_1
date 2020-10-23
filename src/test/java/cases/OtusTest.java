package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalDataPage;
import utils.BaseHooks;

public class OtusTest  extends BaseHooks {


    public static final PersonalDataPage PERSONAL_DATA_PAGE = new PersonalDataPage(driver);

    @Test
    @Epic("MainPage")
    @Feature("Открытие сайта")
    @Description("Тест проверяет открытие сайта")
    public void openOtus(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();
    }

    @Test
    @Epic("Profile")
    @Feature("Добавление информации в профиль")
    @Story("Добавление личной информации в профиле")
    @Description("Тест проверяет добавление личной информации в профиле")
    public void addUserDataTest() {
        String email = "bakytgul.sarsenbayeva@btsdigital.kz";
        String password = "VXuVrCLpT8su";
        MainPage mainPage = new MainPage(driver);
        mainPage.openSite();
        mainPage.openLoginPage();
        LoginPage loginPage = new LoginPage(driver);
        //autorize
        loginPage.typeEmail(email)
                .typePassword(password)
                .submitLogin();
        mainPage.openPersonalDataPage();
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        //add info
        personalDataPage.addPersonalInfo()
                .addFirstContact()
                .addSecondContact()
                .saveChanges();
        //delete cookies and login again
        mainPage.deleteCookies()
                .openSite();
        mainPage.openLoginPage();
        loginPage.typeEmail(email)
                .typePassword(password)
                .submitLogin();
        //assert that data is correct
        mainPage.openPersonalDataPage();
        personalDataPage.assertData();
    }
}
