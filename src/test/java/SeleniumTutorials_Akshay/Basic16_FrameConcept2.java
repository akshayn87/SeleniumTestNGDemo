package SeleniumTutorials_Akshay;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ConfigurationReader;
import utilities.Driver;

import java.io.*;
import java.util.Properties;

public class Basic16_FrameConcept2 {
    public static void main(String[] args) throws IOException {
        //File propertiesFile=new File("config/ProjectDetails.properties");
  /*      String propertiesFile="config/ProjectDetails.properties";
        Properties properties=new Properties();
        FileInputStream fileInput=new FileInputStream(propertiesFile);
        properties.load(fileInput);
        fileInput.close();*/

        WebDriver driver= Driver.get();
        driver.get(ConfigurationReader.get("url"));
        driver.manage().window().maximize();
        driver.findElement(By.id("cookieChoiceDismiss")).click();


    }
}
