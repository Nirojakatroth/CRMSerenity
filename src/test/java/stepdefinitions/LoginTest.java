package stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest {
	
	WebDriverWait wait;
	LoginPage lp;

	@Given("User launch the CRM URL")
	public void user_launch_the_crm_url() {
		lp=new LoginPage(Hooks.driver);
	   
	}
	@When("User enter the valid username and password")
	public void user_enter_the_valid_username_and_password() {
		lp.EnterUserNameandPassword(
		        ConfigReader.getProperty("app.email"),
		        ConfigReader.getProperty("app.password"));
	}
	@When("User click on login button")
	public void user_click_on_login_button() {
		lp.ClickLoginbutton();
	   
	}
	@Then("User verify the homepage")
	public void user_verify_the_homepage() {
		Assert.assertTrue(lp.VerifyHomePage());
	    
	}



	
}
