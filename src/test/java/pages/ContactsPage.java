package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactsPage extends PageObject {

    // Create Contact Locators

    @FindBy(xpath = "//span[text()='Contacts']")
    WebElementFacade contactButton;

    @FindBy(xpath = "//button[text()='Create']")
    WebElementFacade createContact;

    @FindBy(name = "first_name")
    WebElementFacade firstName;

    @FindBy(name = "last_name")
    WebElementFacade lastName;

    @FindBy(xpath = "//label[text()='Company']/following-sibling::div//input")
    WebElementFacade company;

    @FindBy(xpath = "(//div[@role='listbox'])[2]")
    WebElementFacade companySuggestions;

    @FindBy(xpath = "//label[text()='Status']/following-sibling::div")
    WebElementFacade status;

    @FindBy(xpath = "//label[text()='Tags']/following-sibling::div//input")
    WebElementFacade tagsInput;

    @FindBy(xpath = "//label[text()='Tags']/following-sibling::div//div[@role='option']")
    WebElementFacade tagSuggestions;

    @FindBy(xpath = "//label[text()='Category']/following-sibling::div")
    WebElementFacade category;

    @FindBy(name = "position")
    WebElementFacade position;

    @FindBy(xpath = "//label[text()='Supervisor']/following-sibling::div//input[@type='text']")
    WebElementFacade supervisorInput;

    @FindBy(name = "source")
    WebElementFacade source;

    @FindBy(xpath = "//input[@name='do_not_call']/following-sibling::label")
    WebElementFacade doNotCallLabel;

    @FindBy(xpath = "//input[@name='do_not_text']/following-sibling::label")
    WebElementFacade doNotTextLabel;

    @FindBy(name = "day")
    WebElementFacade day;

    @FindBy(xpath = "//div[@name='month']")
    WebElementFacade monthDropdown;

    @FindBy(name = "year")
    WebElementFacade year;

    @FindBy(xpath = "//button[text()='Save']")
    WebElementFacade saveButton;

    // Delete Contact Locators

    @FindBy(xpath = "//div[contains(text(),'Showing') and contains(text(),'records')]")
    WebElementFacade recordCountText;

    @FindBy(xpath = "//table//tbody//tr[1]//div[contains(@class,'checkbox')]")
    WebElementFacade firstCheckbox;

    @FindBy(xpath = "(//i[contains(@class,'trash')])[2]")
    WebElementFacade deleteButton;

    @FindBy(xpath = "//div[contains(@class,'modal')]//button[contains(@class,'red')]")
    WebElementFacade confirmDeleteButton;

    // Edit Contact Locators

    @FindBy(xpath = "(//i[@class='edit icon'])[2]")
    WebElementFacade editIcon;

    @FindBy(name = "description")
    WebElementFacade description;

    @FindBy(name = "address")
    WebElementFacade address;

    @FindBy(name = "city")
    WebElementFacade city;

    @FindBy(name = "state")
    WebElementFacade state;

    @FindBy(name = "zip")
    WebElementFacade zip;

    // Search Contact

    @FindBy(xpath = "//input[contains(@placeholder,'Search')]")
    WebElementFacade searchField;

    @FindBy(xpath = "//table//td")
    List<WebElementFacade> searchedContacts;

    // Navigation

    public void clickContacts() {
        contactButton.click();
    }

    public void clickCreateContact() {
        createContact.click();
    }

    public void clickSave() {
        saveButton.click();
    }

    // Utility Method

    public void clearAndType(WebElementFacade element, String value) {

        element.waitUntilClickable();
        element.click();

        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);

        element.type(value);
    }

    // Dropdown Handler

    public void selectFromDropdown(WebElementFacade dropdown, String value) {

        waitABit(1000);

        // Scroll element into view
        evaluateJavascript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        waitABit(500);

        // Use JS click to avoid navbar intercept
        evaluateJavascript("arguments[0].click();", dropdown);

        waitABit(1000);

        List<WebElementFacade> options =
                findAll("//div[contains(@class,'visible menu')]//div[contains(@class,'item')]");

        for (WebElementFacade option : options) {

            if (option.getText().trim().equalsIgnoreCase(value)) {

                evaluateJavascript("arguments[0].click();", option);
                break;
            }
        }
    }

    public void selectStatus(String stat) {
        selectFromDropdown(status, stat);
    }

    public void selectCategory(String cat) {
        selectFromDropdown(category, cat);
    }

    public void selectMonth(String month) {
        selectFromDropdown(monthDropdown, month);
    }

    public void selectSource(String src) {
        selectFromDropdown(source, src);
    }

    // Supervisor Auto Suggestion

    public void selectSupervisor(String sup) {

        supervisorInput.waitUntilVisible();
        supervisorInput.type(sup);

        waitABit(1500);

        List<WebElementFacade> options =
                findAll("//div[contains(@class,'visible menu')]//div[contains(@class,'item')]");

        for (WebElementFacade option : options) {

            if (option.getText().trim().equalsIgnoreCase(sup)) {

                option.waitUntilClickable().click();
                break;
            }
        }
    }

    // Create Contact

    public void createContact(String fName, String lName, String comp, String stat,
                              String tagName, String cat, String pos,
                              String sup, String src, boolean call, boolean text,
                              String d, String m, String y) {

        firstName.type(fName);
        lastName.type(lName);

        company.type(comp);
        companySuggestions.waitUntilClickable().click();

        tagsInput.type(tagName);
        tagSuggestions.waitUntilClickable().click();

        position.type(pos);

        evaluateJavascript("window.scrollBy(0,300)");

        waitABit(1000);

        waitABit(1000);

        selectStatus(stat);
        selectCategory(cat);
        selectSupervisor(sup);
        selectSource(src);

        if (call) {
            doNotCallLabel.click();
        }

        if (text) {
            doNotTextLabel.click();
        }

        day.type(d);
        selectMonth(m);
        year.type(y);
    }

    // Verify Created Contact

    public boolean isContactPresent(String firstName, String lastName) {

        String fullName = firstName + " " + lastName;

        try {
            return find(By.xpath("//span[text()='" + fullName + "']")).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    // Delete Contact

    public int getContactPageRecordCount() {

        recordCountText.waitUntilVisible();

        String text = recordCountText.getText();

        String number = text.replaceAll("[^0-9]", "");

        return Integer.parseInt(number);
    }

    public void deleteFirstContact() {

        firstCheckbox.waitUntilClickable().click();
        deleteButton.waitUntilClickable().click();
        confirmDeleteButton.waitUntilClickable().click();
    }

    // Edit Contact

    public void clickEditIcon() {
        editIcon.click();
    }

    public void editExistingContact(String fName, String lName, String desc,
                                    String addr, String cityName,
                                    String st, String zipCode) {

        clearAndType(firstName, fName);
        clearAndType(lastName, lName);
        clearAndType(description, desc);
        clearAndType(address, addr);
        clearAndType(city, cityName);
        clearAndType(state, st);
        clearAndType(zip, zipCode);

        saveButton.click();
    }

    public void searchContact(String contactName) {

        searchField.clear();
        searchField.type(contactName);
        searchField.sendKeys(Keys.ENTER);
    }

    // Search Contact

    public void enterContactName(String contactName) {

        searchField.clear();
        searchField.type(contactName);
    }

    public void clickSearch() {

        searchField.sendKeys(Keys.ENTER);
    }

    public boolean verifySearchedContact() {

        return !searchedContacts.isEmpty();
    }


}