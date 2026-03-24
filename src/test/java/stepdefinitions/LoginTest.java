package stepdefinitions;

import io.cucumber.java.en.*;
import models.LoginData;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import steps.LoginSteps;
import utils.JsonReader;

import java.util.List;

public class LoginTest {

    @Steps
    LoginSteps loginSteps;

    @Given("User launch the CRM URL")
    public void user_launch_the_crm_url() {

        loginSteps.openApplication();
    }

    @When("User login to CRM application")
    public void user_login_to_crm_application() {

        loginSteps.loginToCRM();
    }

    @Then("User verify the homepage")
    public void user_verify_the_homepage() {

        Assert.assertTrue(loginSteps.verifyHomePage());
    }

    @When("user logs in with multiple credentials from JSON")
    public void login_with_multiple_users_from_json() {

        List<LoginData> users = JsonReader.getLoginDataList();

        for (LoginData user : users) {

            // Always start from login page
            loginSteps.openLoginPage();

            loginSteps.enterCredentials(user.getUsername(), user.getPassword());
            loginSteps.clickLogin();

            if (user.getResult().equalsIgnoreCase("success")) {
                Assert.assertTrue(loginSteps.verifyHomePage());

                // logout only if login success
                loginSteps.clickSettingIcon();
                loginSteps.clickLogout();

            } else {
                Assert.assertTrue(loginSteps.verifyLoginFailure());
            }
        }
    }
}