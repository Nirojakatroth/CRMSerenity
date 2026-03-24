package steps;

import com.google.gson.stream.JsonReader;
import models.LoginData;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    @Step("Open CRM application")
    public void openApplication() {
        loginPage.openApplication();
    }

    @Step("Login to CRM")
    public void loginToCRM() {
        loginPage.enterCredentials(
                loginPage.getEmail(),
                loginPage.getPassword());

        loginPage.clickLoginButton();
    }

    @Step("Verify home page")
    public boolean verifyHomePage() {
        return loginPage.verifyHomePage();
    }

    @Step("Enter credentials")
    public void enterCredentials(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @Step("Click login")
    public void clickLogin() {
        loginPage.clickLoginButton();
    }

    @Step("Click Setting icon")
    public void clickSettingIcon() {
        loginPage.clickSettingIcon();
    }
    @Step("Click Logout")
    public void clickLogout() {
        loginPage.clickLogout();
    }

    @Step("Verify login failure")
    public boolean verifyLoginFailure() {
        return loginPage.verifyErrorMessage();
    }

    @Step("Open login page")
    public void openLoginPage() {
        loginPage.open();   // or specific URL method
    }
}