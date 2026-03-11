package utils;


import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/ExtentReport.html");

            spark.config().setReportName("CRM Automation Report");
            spark.config().setDocumentTitle("CRM Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Niroja");
            extent.setSystemInfo("OS", "Windows");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Project", "CRMAutomation");
        }

        return extent;
    }
}