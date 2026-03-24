package stepdefinitions;

import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import steps.ContactSteps;

public class ContactTest {

    @Steps
    ContactSteps contactSteps;

    @Given("User launches the CRM application URL")
    public void user_launches_application() {

        contactSteps.openApplication();
    }

    @When("User logs into the CRM application")
    public void user_logs_into_crm() {

        contactSteps.loginToCRM();
    }

    @And("User clicks on Contacts")
    public void user_clicks_contacts() {

        contactSteps.openContactsPage();
    }

    // ===============================
    // Create Contact from Excel
    // ===============================

    @When("User creates a contact using excel {string}")
    public void create_contact_from_excel(String sheetName) throws Exception {

        contactSteps.createContactFromExcel(sheetName);
    }

    @Then("User verifies the created contact name")
    public void verify_contact_created() {

        Assert.assertTrue(contactSteps.verifyCreatedContact());
    }

    // ===============================
    // Delete Contact
    // ===============================

    @When("User verifies contact before deletion")
    public void user_verifies_contact_before_deletion() {

        contactSteps.getBeforeCount();
    }

    @And("User delete the first contact")
    public void user_delete_the_first_contact() {

        contactSteps.deleteFirstContact();
    }

    @Then("User verifies the contact after deletion")
    public void user_verifies_the_contact_after_deletion() {

        Assert.assertTrue(contactSteps.verifyContactDeleted());
    }
    // ===============================
    // Edit Contact
    // ===============================

    @When("User updates contact details using {string}")
    public void edit_contact_from_excel(String sheetName) throws Exception {

        contactSteps.editContactFromExcel(sheetName);
    }

    @Then("User should see the contact updated")
    public void verify_contact_updated() {

        Assert.assertTrue(contactSteps.verifyEditedContact());
    }

    // ===============================
    // Search Contact
    // ===============================

    @When("User searches for contact {string}")
    public void user_searches_contact(String name) {

        contactSteps.searchContact(name);
    }

    @Then("User should see the searched contact")
    public void verify_search_contact() {

        Assert.assertTrue(contactSteps.verifySearchContact());
    }
}