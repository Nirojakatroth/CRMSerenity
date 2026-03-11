package tests;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C://Users//Niroja//eclipse-workspace//Java//Javaprogramming//CRMApplication//src//test//resources//Features//Contacts.feature", glue = "stepdefinitions",
		//tags = "@Regression",
		monochrome = true, dryRun = false, plugin = { "pretty", "html:target/Cucumber-Reports.html" }

)


public class TestRunner {

}
