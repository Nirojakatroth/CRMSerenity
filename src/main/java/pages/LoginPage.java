
package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "email")
	WebElement Email;
	@FindBy(name = "password")
	WebElement Password;
	@FindBy(xpath = "//div[text()=\"Login\"]")
	WebElement Login;
	@FindBy(xpath = "//span[text()=\"Home\"]")
	WebElement Homepage;

	public void EnterUserNameandPassword(String email, String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
	}

	public void ClickLoginbutton() {
		Login.click();
	}

	public boolean VerifyHomePage() {
		try {
			wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfAllElements(Homepage));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
