package stepdefinitions;

import java.io.File;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentManager;

public class Hooks {

	/*
	 * public static WebDriver driver;
	 * 
	 * 
	 * @Before public void setup() {
	 * 
	 * String browser = ConfigReader.getProperty("browser");
	 * 
	 * if (browser.equalsIgnoreCase("chrome")) { driver = new ChromeDriver(); }
	 * 
	 * driver.manage().window().maximize();
	 * driver.get(ConfigReader.getProperty("app.url")); }
	 */

	public static WebDriver driver;
	public static ExtentReports extent = ExtentManager.getInstance();
	public static ExtentTest test;

	/*@Before
	public void setup(Scenario scenario) {

	//	WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		test = extent.createTest(scenario.getName());
		test.info("Starting Scenario: " + scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {
		try {

			if (scenario.isFailed()) {

				Thread.sleep(1000);

				byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

				String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

				test.fail("Scenario Failed", com.aventstack.extentreports.MediaEntityBuilder
						.createScreenCaptureFromBase64String(base64Screenshot).build());

			} else {
				test.pass("Scenario Passed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (driver != null) {
			driver.quit();
		}

		extent.flush();
	}
}*/


	// Runs before every scenario
	@Before
	public void setup(Scenario scenario) {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		test = extent.createTest(scenario.getName());

		test.info("Starting Scenario: " + scenario.getName());
	}

	// Screenshot after each step
	@AfterStep
	public void addScreenshot(Scenario scenario) {

		try {

			byte[] screenshot =
					((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", "Step Screenshot");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Runs after every scenario
	@After
	public void tearDown(Scenario scenario) {

		try {

			String base64Screenshot =
					((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

			if (scenario.isFailed()) {

				test.fail("Scenario Failed",
						com.aventstack.extentreports.MediaEntityBuilder
								.createScreenCaptureFromBase64String(base64Screenshot)
								.build());

			} else {

				test.pass("Scenario Passed",
						com.aventstack.extentreports.MediaEntityBuilder
								.createScreenCaptureFromBase64String(base64Screenshot)
								.build());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (driver != null) {
			driver.quit();
		}

		extent.flush();
	}

	// Helper method for logging steps in report
	public static void logStep(String message) {
		test.info(message);
	}
}