
package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name="email")
    WebElementFacade txtEmail;

    @FindBy(name="password")
    WebElementFacade txtPassword;

    @FindBy(xpath="//div[text()='Login']")
    WebElementFacade btnLogin;

    @FindBy(xpath="//span[text()='Home']")
    WebElementFacade homePage;

    @FindBy(xpath = "(//i[@class='settings icon'])[1]")
    WebElementFacade SettingIcon;

    @FindBy(xpath = "//span[text()=\"Log Out\"]")
    WebElementFacade Logoutbutton;

    @FindBy(xpath = "//p[text()='Invalid login']")
    WebElementFacade errorMessage;

    public void enterCredentials(String email, String password) {
        txtEmail.type(email);
        txtPassword.type(password);
    }

    public void clickLoginButton() {
        btnLogin.click();
    }

    public boolean verifyHomePage() {
        homePage.waitUntilVisible();
        return homePage.isDisplayed();
    }

    public boolean verifyErrorMessage() {
        errorMessage.waitUntilVisible();
        return errorMessage.isDisplayed();
    }

    public void clickSettingIcon() {
        SettingIcon.click();
    }

    public void clickLogout() {
        Logoutbutton.click();
    }

    public void refreshPage() {
        getDriver().navigate().refresh();
    }


}

