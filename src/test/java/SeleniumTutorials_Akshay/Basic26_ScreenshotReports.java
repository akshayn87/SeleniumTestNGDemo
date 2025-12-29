package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.io.File;
import java.io.IOException;

public class Basic26_ScreenshotReports {
    WebDriver driver;
    @Test
    public void test() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver= Driver.get();
        driver.get(ConfigurationReader.get("url"));
        driver.findElement(By.id("cookieChoiceDismiss")).click();
        driver.manage().window().maximize();

        File screenshotFile1=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotBase64=((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        FileUtils.copyFile(screenshotFile1, new File("test-output\\screenshots\\screenshot1.jpg"));

        driver.findElement(By.linkText("compendiumdev")).click();

        File screenshotFile2=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile2, new File("test-output\\screenshots\\screenshot2.jpg"));


        driver.close();

    }
}
