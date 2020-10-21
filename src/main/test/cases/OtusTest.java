package cases;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalDataPage;
import utils.BaseHooks;

public class OtusTest extends BaseHooks {

    @Test()
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
