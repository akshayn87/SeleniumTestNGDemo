package SeleniumTutorials_Akshay;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.math3.exception.MathIllegalNumberException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Basic19_Log4jAPI {
    public static void main(String[] args) {

        ExtentReports report;
        //this class is used to create HTML report file
        ExtentHtmlReporter htmlReporter;
        //this will define a test enables adding logs, authors, test steps
        ExtentTest extendLogger;


        report=new ExtentReports();

        //create a report path
        String projectPath=System.getProperty("user.dir");
        String path=projectPath+"\\test-output\\report.html";

        //initialize the html reporter with the report path
        htmlReporter=new ExtentHtmlReporter(path);

        //attach the html report to report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Vytrack Smoke Test");

        //set environment information
        report.setSystemInfo("Environment","QA");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        extendLogger=report.createTest("TC123 Login as Driver Test");

        WebDriver driver = Driver.get();
        extendLogger.info("Open Chrome Browse");
        extendLogger.info("Go to URL");
        driver.get(ConfigurationReader.get("url"));
        extendLogger.info("Chrome browser maximized");
        driver.manage().window().maximize();
        extendLogger.info("Compendium opened");
        driver.findElement(By.linkText("compendiumdev")).click();
        extendLogger.info("Navigated back to Omaya");
        driver.navigate().back();
        extendLogger.info("Navigated forward to Compendium");
        driver.navigate().forward();
        extendLogger.info("Browser refreshed");
        driver.navigate().refresh();

        extendLogger.pass("TC123 has passed");


        driver.close();
        extendLogger.info("Browsr closed");

        report.flush();
    }

}
