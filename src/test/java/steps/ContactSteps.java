package steps;

import net.thucydides.core.annotations.Step;
import pages.ContactsPage;
import pages.LoginPage;
import utils.ContactExcelReader;

import java.util.List;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ContactSteps {

    LoginPage loginPage;
    ContactsPage contactsPage;

    String firstName;
    String lastName;

    int beforeCount;

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

    @Step("Open contacts page")
    public void openContactsPage() {

        contactsPage.clickContacts();
    }

    // ===============================
    // Create Contact using Excel
    // ===============================

    @Step("Create contact using excel data")
    public void createContactFromExcel(String sheetName) throws Exception {

        contactsPage.clickCreateContact();

        List<String[]> data = ContactExcelReader.getExcelData(sheetName);

        for (String[] contact : data) {

            // Generate unique value
            String unique = String.valueOf(System.currentTimeMillis());

            firstName = contact[0] + unique;
            lastName = contact[1] + unique;

            contactsPage.createContact(

                    firstName,          // First Name
                    lastName,           // Last Name
                    contact[2],         // Company
                    contact[3],         // Status
                    contact[4],         // Tag
                    contact[5],         // Category
                    contact[6],         // Position
                    contact[7],         // Supervisor
                    contact[8],         // Source
                    Boolean.parseBoolean(contact[9]),   // Do Not Call
                    Boolean.parseBoolean(contact[10]),  // Do Not Text
                    contact[11],        // Day
                    contact[12],        // Month
                    contact[13]         // Year
            );

            break; // create only one contact
        }

        contactsPage.clickSave();
    }

    @Step("Verify created contact")
    public boolean verifyCreatedContact() {

        return contactsPage.isContactPresent(firstName, lastName);
    }

    // ===============================
    // Delete Contact
    // ===============================

    @Step("Get record count before deletion")
    public void getBeforeCount() {

        beforeCount = contactsPage.getContactPageRecordCount();
    }

    @Step("Delete first contact")
    public void deleteFirstContact() {

        contactsPage.deleteFirstContact();
    }

    @Step("Verify contact deleted using count")
    public boolean verifyContactDeleted() {

        getDriver().navigate().refresh();

        contactsPage.waitForCondition().until(
                driver -> contactsPage.getContactPageRecordCount() != beforeCount
        );

        int afterCount = contactsPage.getContactPageRecordCount();

        return beforeCount - 1 == afterCount;
    }

    // ===============================
    // Edit Contact from Excel
    // ===============================

    @Step("Edit contact using excel data")
    public void editContactFromExcel(String sheetName) throws Exception {

        List<String[]> data = ContactExcelReader.getExcelData(sheetName);

        for (String[] contact : data) {

            // generate unique value
            String unique = String.valueOf(System.currentTimeMillis());

            String newFirstName = contact[0] + unique;
            String newLastName = contact[1] + unique;

            contactsPage.clickEditIcon();

            contactsPage.editExistingContact(

                    newFirstName,
                    newLastName,
                    contact[2],   // description
                    contact[3],   // address
                    contact[4],   // city
                    contact[5],   // state
                    contact[6]    // zip
            );

            // update variables for verification
            firstName = newFirstName;
            lastName = newLastName;

            break;
        }
    }

    @Step("Verify edited contact")
    public boolean verifyEditedContact() {

        return contactsPage.isContactPresent(firstName, lastName);
    }

    // ===============================
    // Search Contact
    // ===============================

    @Step("Search contact")
    public void searchContact(String name) {

        contactsPage.enterContactName(name);
        contactsPage.clickSearch();
    }

    @Step("Verify searched contact")
    public boolean verifySearchContact() {

        return contactsPage.verifySearchedContact();
    }
}