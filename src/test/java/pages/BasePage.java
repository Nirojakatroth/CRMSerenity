package pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.EnvironmentVariables;

import javax.inject.Inject;

public class BasePage extends PageObject {

    @Inject
    EnvironmentVariables environmentVariables;

    public String getEmail() {
        return environmentVariables.getProperty("app.email");
    }

    public String getPassword() {
        return environmentVariables.getProperty("app.password");
    }

    public String getApplicationUrl() {
        return environmentVariables.getProperty("webdriver.base.url");
    }

    public void openApplication() {

        open();   // Serenity automatically opens webdriver.base.url
        getDriver().manage().window().maximize();
    }
}