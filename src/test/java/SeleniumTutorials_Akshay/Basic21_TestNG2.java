package SeleniumTutorials_Akshay;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.Driver;

public class Basic21_TestNG2 {
    WebDriver driver=Driver.get();

    @BeforeMethod
    public void openBrowser(){
        Driver.get().get(ConfigurationReader.get("url"));

    }
    @Test
    public void test1() {
        WebElement dropDownField = driver.findElement(By.id("drop1"));
        Select select = new Select(dropDownField);
        select.selectByVisibleText("doc 3");
    }
        @Test
        public void test2(){
            WebElement dropDownField = driver.findElement(By.id("drop1"));
            Select select=new Select(dropDownField);
            select.selectByVisibleText("doc 3");
    }
}
