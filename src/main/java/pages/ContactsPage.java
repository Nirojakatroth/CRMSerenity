package pages;

import java.time.Duration;
import java.util.List;

import io.cucumber.java.hu.De;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ContactsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //create contact
    @FindBy(xpath = "//span[text()='Contacts']")
    WebElement Contactbutton;

    @FindBy(xpath = "//button[text()='Create']")
    WebElement CreateContact;

    @FindBy(name = "first_name")
    WebElement firstName;

    @FindBy(name = "last_name")
    WebElement lastName;

    @FindBy(xpath = "//label[text()='Company']/following-sibling::div//input[@type='text']")
    WebElement company;

    @FindBy(xpath = "(//div[@role='listbox'])[2]")
    WebElement companySuggestions;

    @FindBy(xpath = "//label[text()='Status']/following-sibling::div")
    WebElement status;

    @FindBy(xpath = "//label[text()='Tags']/following-sibling::div//input")
    WebElement tagsInput;

    @FindBy(xpath = "//label[text()='Tags']/following-sibling::div//div[@role='option']")
    WebElement tagSuggestions;

    @FindBy(xpath = "//label[text()='Category']/following-sibling::div")
    WebElement category;

    @FindBy(name = "position")
    WebElement position;

    @FindBy(xpath = "//label[text()='Supervisor']/following-sibling::div//input[@type='text']")
    WebElement supervisorInput;

    @FindBy(name = "source")
    WebElement source;

    @FindBy(xpath = "//input[@name='do_not_call']/following-sibling::label")
    WebElement doNotCallLabel;

    @FindBy(xpath = "//input[@name='do_not_text']/following-sibling::label")
    WebElement doNotTextLabel;

    @FindBy(name = "day")
    WebElement day;

    @FindBy(xpath = "//div[@name='month']")
    WebElement monthDropdown;

    @FindBy(name = "year")
    WebElement year;

    @FindBy(xpath = "//button[text()=\"Save\"]")
    WebElement Save;

    //delete contact

    @FindBy(xpath = "//div[contains(text(),'Showing') and contains(text(),'records')]")
    WebElement recordCountText;

    @FindBy(xpath = "//table//tbody//tr[1]//div[contains(@class,'checkbox')]")
    WebElement firstCheckboxLabel;

    @FindBy(xpath = "(//i[contains(@class,'trash')])[2]")
    WebElement deleteButton;

    @FindBy(xpath = "//div[contains(@class,'modal')]//button[contains(@class,'red')]")
    WebElement confirmDeleteButton;


    // edit contact

    @FindBy(xpath = "(//i[@class='edit icon'])[2]")
    WebElement Editicon;

    @FindBy(name = "description")
    WebElement Description;

    @FindBy(name = "address")
    WebElement Address;

    @FindBy(name = "city")
    WebElement City;

    @FindBy(name = "state")
    WebElement State;

    @FindBy(name = "zip")
    WebElement Zip;

    // Search contact

    @FindBy(xpath = "//input[contains(@placeholder,'Search')]")
    WebElement searchField;

    @FindBy(xpath = "//table//td")
    List<WebElement> SearchedContact;

    public void ClickOnContactButton() {
        wait.until(ExpectedConditions.visibilityOf(Contactbutton));
        Contactbutton.click();
    }

    public void ClickonCreateContact() {
        CreateContact.click();
    }

    public void createContact(String fName, String lName, String comp, String stat, String tagName, String cat,
                              String pos, String sup, String src, boolean call, boolean text, String d, String m, String y) {

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);

        wait.until(ExpectedConditions.elementToBeClickable(company));
        company.click();
        company.sendKeys(comp);

        wait.until(ExpectedConditions.visibilityOf(companySuggestions));
        companySuggestions.click();

        tagsInput.sendKeys(tagName);
        wait.until(ExpectedConditions.visibilityOf(tagSuggestions));
        tagSuggestions.click();

        position.sendKeys(pos);

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

        day.sendKeys(d);
        selectMonth(m);
        year.sendKeys(y);
    }

    public void selectFromDropdown(WebElement dropdown, String value) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);

        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        dropdown.click();

        By optionsLocator = By.xpath("//div[contains(@class,'visible menu')]//div[contains(@class,'item')]");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

        List<WebElement> options = driver.findElements(optionsLocator);

        for (int i = 0; i < options.size(); i++) {

            String text = options.get(i).getText();

            if (text.equalsIgnoreCase(value)) {
                options.get(i).click();
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

    public void selectMonth(String m) {
        selectFromDropdown(monthDropdown, m);
    }

    public void selectSupervisor(String sup) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", supervisorInput);

        wait.until(ExpectedConditions.elementToBeClickable(supervisorInput));

        supervisorInput.click();
        supervisorInput.clear();
        supervisorInput.sendKeys(sup);

        By suggestions = By.xpath("//div[contains(@class,'visible menu')]//div[contains(@class,'item')]");

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(suggestions));

        List<WebElement> options = driver.findElements(suggestions);

        for (int i = 0; i < options.size(); i++) {

            if (options.get(i).getText().trim().equalsIgnoreCase(sup)) {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(i));

                break;
            }
        }
    }

    public void selectSource(String src) {
        selectFromDropdown(source, src);
    }

    public void ClickOnSavebutton() {
        Save.click();
      //  wait.until(ExpectedConditions.visibilityOf(updatedContactName));
    }

    public boolean verifyCreatedContact(String firstName, String lastName) {

        String fullName = firstName + " " + lastName;

        String dynamicXpath = "//span[text()='" + fullName + "']";

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXpath)));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public int getContactPageRecordCount() {

        wait.until(ExpectedConditions.visibilityOf(recordCountText));
        String text = recordCountText.getText().trim();
        String number = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(number);
    }

    public void deleteFirstContact() {

        wait.until(ExpectedConditions.elementToBeClickable(firstCheckboxLabel));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstCheckboxLabel);

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDeleteButton.click();
        wait.until(ExpectedConditions.visibilityOf(recordCountText));
    }

    public void ClickEditIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(Editicon));
        Editicon.click();
    }

    public void clearAndType(WebElement element, String value) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();

        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);

        element.sendKeys(value);
    }

    public void EditExistingContact(String FirstName, String LastName, String description, String address, String city,
                                    String state, String zip) {


        clearAndType(firstName, FirstName);
        clearAndType(lastName, LastName);
        clearAndType(Description, description);
        clearAndType(Address, address);
        clearAndType(City, city);
        clearAndType(State, state);
        clearAndType(Zip, zip);
    }

    public void ClickOnSearchbutton() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", searchField);

        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(Keys.ENTER);
        // searchField.click();
    }

    public void EnterContactName(String contactName) {

        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.clear();
        searchField.sendKeys(contactName);

    }

    public boolean VerifySearchedContact() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(SearchedContact));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}