package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Basic11_HandlingDown_Select_MultipleSelectionBox {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        WebElement multiSelectionBox = driver.findElement(By.id("multiselect1"));

        Select select=new Select(multiSelectionBox);
        select.selectByVisibleText("Swift");
        select.selectByVisibleText("Audi");
        select.deselectByVisibleText("Swift");

    }
}
