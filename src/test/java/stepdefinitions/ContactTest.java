/*
package stepdefinitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ContactExcelReader;

public class ContactTest {

	WebDriver driver;
	LoginPage lp;
	ContactsPage cp;

	String firstName;
	String lastName;
	int beforeCount;

	@Given("User launches the CRM application URL")
	public void user_launches_the_crm_application_url() {
		driver = Hooks.driver;
		driver.get(ConfigReader.getProperty("app.url"));
		lp = new LoginPage(driver);
		cp = new ContactsPage(driver);
	}

	@When("User logs into the CRM application")
	public void user_logs_into_the_crm_application() {
		lp.EnterUserNameandPassword(ConfigReader.getProperty("app.email"), ConfigReader.getProperty("app.password"));
		lp.ClickLoginbutton();

	}

	@And("User clicks on Contacts")
	public void user_clicks_on_contacts() {
		cp.ClickOnContactButton();

	}

	@When("User creates a contact using excel {string}")
	public void user_enters_contact_details_from_excel(String sheetName) throws Exception {

		cp.ClickonCreateContact();

		List<String[]> data = ContactExcelReader.getExcelData(sheetName);
		for (String[] contact : data) {
			
			   String unique = String.valueOf(System.currentTimeMillis());
			   

			    firstName = contact[0] + unique;
		        lastName = contact[1] + unique;
		        
			cp.createContact(firstName,
	                lastName,
					contact[2], // Company
					contact[3], // Status
					contact[4], // Tag
					contact[5], // Category
					contact[6], // Position
					contact[7], // Supervisor
					contact[8], // Source
					Boolean.parseBoolean(contact[9]), // Call
					Boolean.parseBoolean(contact[10]), // Text
					contact[11], // Day
					contact[12], // Month
					contact[13] // Year
			);

			break;
		}
		cp.ClickOnSavebutton();
	}

	@Then("User verifies the created contact name")
	public void user_verifies_the_created_contact_name() {
		Assert.assertTrue(cp.verifyCreatedContact(firstName, lastName));
	}

	@When("User verifies contact before deletion")
	public void user_verifies_contact_before_deletion() {
		beforeCount = cp.getContactPageRecordCount();
		System.out.println("Before Delete: " + beforeCount);

		Assert.assertTrue(beforeCount > 0);
	}

	@And("User delete the first contact")
	public void user_delete_the_first_contact() {
		cp.deleteFirstContact();
	}

	@Then("User verifies the contact after deletion")
	public void user_verifies_the_contact_after_deletion() {

		driver.navigate().refresh();
		int afterCount = cp.getContactPageRecordCount();
		System.out.println("After Delete: " + afterCount);
		Assert.assertEquals(beforeCount - 1, afterCount);
	}


	@And("User updates contact details using {string}")
	public void User_updates_contact_details_using_(String sheetName) throws Exception{
		cp.ClickEditIcon();
		List<String[]> data = ContactExcelReader.getExcelData(sheetName);

		for (String[] contact : data) {

			   String unique = String.valueOf(System.currentTimeMillis());
			   

			    firstName = contact[0] + unique;
		        lastName = contact[1] + unique;
		        
		        cp.EditExistingContact(
		                firstName,
		                lastName,
		                contact[2], // Description
		                contact[3], // Address
		                contact[5], // City
		                contact[4], // State
		                contact[6]  // Zip
		        );
			break;
		}
		cp.ClickOnSavebutton();
	    
	}
	
	@Then("User should see the contact updated")
	public void user_should_see_contact_updated() {
		Assert.assertTrue(cp.verifyCreatedContact(firstName, lastName));
	    
	}
	
	@When("User searches for contact {string}")
	public void User_searches_for_contact(String contactName) {
	    cp.EnterContactName(contactName);
		cp.ClickOnSearchbutton();
	}

	@Then("User should see the searched contact")
	public void user_should_see_the_searched_contact() {
	    Assert.assertTrue(cp.VerifySearchedContact());

	}
	
}
*/



package stepdefinitions;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ContactsPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ContactExcelReader;

public class ContactTest {

	WebDriver driver;
	LoginPage lp;
	ContactsPage cp;

	String firstName;
	String lastName;
	int beforeCount;

	@Given("User launches the CRM application URL")
	public void user_launches_the_crm_application_url() {

		driver = Hooks.driver;

		driver.get(ConfigReader.getProperty("app.url"));

		Hooks.logStep("User launched CRM application");

		lp = new LoginPage(driver);
		cp = new ContactsPage(driver);
	}

	@When("User logs into the CRM application")
	public void user_logs_into_the_crm_application() {

		lp.EnterUserNameandPassword(
				ConfigReader.getProperty("app.email"),
				ConfigReader.getProperty("app.password"));

		lp.ClickLoginbutton();

		Hooks.logStep("User logged into CRM application");
	}

	@And("User clicks on Contacts")
	public void user_clicks_on_contacts() {

		cp.ClickOnContactButton();

		Hooks.logStep("User clicked on Contacts page");
	}

	@When("User creates a contact using excel {string}")
	public void user_enters_contact_details_from_excel(String sheetName) throws Exception {

		cp.ClickonCreateContact();

		Hooks.logStep("User clicked Create Contact");

		List<String[]> data = ContactExcelReader.getExcelData(sheetName);

		for (String[] contact : data) {

			String unique = String.valueOf(System.currentTimeMillis());

			firstName = contact[0] + unique;
			lastName = contact[1] + unique;

			cp.createContact(
					firstName,
					lastName,
					contact[2], // Company
					contact[3], // Status
					contact[4], // Tag
					contact[5], // Category
					contact[6], // Position
					contact[7], // Supervisor
					contact[8], // Source
					Boolean.parseBoolean(contact[9]), // Call
					Boolean.parseBoolean(contact[10]), // Text
					contact[11], // Day
					contact[12], // Month
					contact[13] // Year
			);

			break;
		}

		Hooks.logStep("User entered contact details");

		cp.ClickOnSavebutton();

		Hooks.logStep("User clicked Save button");
	}

	@Then("User verifies the created contact name")
	public void user_verifies_the_created_contact_name() {

		Assert.assertTrue(cp.verifyCreatedContact(firstName, lastName));

		Hooks.logStep("Contact created successfully");
	}

	@When("User verifies contact before deletion")
	public void user_verifies_contact_before_deletion() {

		beforeCount = cp.getContactPageRecordCount();

		Hooks.logStep("Record count before deletion: " + beforeCount);

		Assert.assertTrue(beforeCount > 0);
	}

	@And("User delete the first contact")
	public void user_delete_the_first_contact() {

		cp.deleteFirstContact();

		Hooks.logStep("User deleted first contact");
	}

	@Then("User verifies the contact after deletion")
	public void user_verifies_the_contact_after_deletion() {

		driver.navigate().refresh();

		int afterCount = cp.getContactPageRecordCount();

		Hooks.logStep("Record count after deletion: " + afterCount);

		Assert.assertEquals(beforeCount - 1, afterCount);
	}

	@And("User updates contact details using {string}")
	public void User_updates_contact_details_using_(String sheetName) throws Exception {

		cp.ClickEditIcon();

		Hooks.logStep("User clicked Edit icon");

		List<String[]> data = ContactExcelReader.getExcelData(sheetName);

		for (String[] contact : data) {

			String unique = String.valueOf(System.currentTimeMillis());

			firstName = contact[0] + unique;
			lastName = contact[1] + unique;

			cp.EditExistingContact(
					firstName,
					lastName,
					contact[2], // Description
					contact[3], // Address
					contact[5], // City
					contact[4], // State
					contact[6]  // Zip
			);

			break;
		}

		Hooks.logStep("User edited contact details");

		cp.ClickOnSavebutton();

		Hooks.logStep("User clicked Save button");
	}

	@Then("User should see the contact updated")
	public void user_should_see_contact_updated() {
		boolean result = cp.verifyCreatedContact(firstName, lastName);

		System.out.println("Verification result: " + result);

		Assert.assertTrue(result);

		//Assert.assertTrue(cp.verifyCreatedContact(firstName, lastName));

		Hooks.logStep("Contact updated successfully");
	}

	@When("User searches for contact {string}")
	public void User_searches_for_contact(String contactName) {

		cp.EnterContactName(contactName);

		Hooks.logStep("User entered contact name in search");

		cp.ClickOnSearchbutton();

		Hooks.logStep("User clicked Search button");
	}

	@Then("User should see the searched contact")
	public void user_should_see_the_searched_contact() {

		Assert.assertTrue(cp.VerifySearchedContact());

		Hooks.logStep("Search result verified successfully");
	}
}
