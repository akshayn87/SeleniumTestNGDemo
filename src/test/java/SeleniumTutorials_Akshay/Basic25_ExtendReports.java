package SeleniumTutorials_Akshay;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.security.PublicKey;

public class Basic25_ExtendReports {
    ExtentReports eReport;
    @Test
    public void test() {

        eReport=ExtendManager.getInstance();
        ExtentTest eTest=eReport.startTest("Test Omayo");
        eTest.log(LogStatus.INFO, "Test Started");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = Driver.get();
        eTest.log(LogStatus.INFO, "Chrome Browser launced");
        driver.get(ConfigurationReader.get("url"));
        eTest.log(LogStatus.INFO, "Cookies clicked");
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        String expectedText = "PracticeAutomationHere";
        String actualText = driver.findElement(By.id("pah")).getText();
        eTest.log(LogStatus.INFO, "Text verified");
        Assert.assertEquals(actualText, expectedText);
        eTest.log(LogStatus.PASS, "PASSED");

        }
        @AfterMethod
        public void testClosure(){
        eReport.flush();
        eReport.close();
        }

}
