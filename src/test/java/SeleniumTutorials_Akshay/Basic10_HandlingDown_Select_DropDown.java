package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Basic10_HandlingDown_Select_DropDown {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        WebElement dropDownField = driver.findElement(By.id("drop1"));
        Select select=new Select(dropDownField);
        select.selectByVisibleText("doc 3");














    }
}
