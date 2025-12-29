package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Basic7_WaitingMechanism_ExplicitWait {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver,100);

        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.id("cookieChoiceDismiss")).click();
        driver.findElement(By.className("dropbtn")).click();
        //WebElement faceBookButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Facebook")));
        WebElement faceBookButton= wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Facebook")));
        faceBookButton.click();

    }
}
