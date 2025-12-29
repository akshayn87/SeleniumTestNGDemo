package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basic12_Frames {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://omayo.blogspot.com/");
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        driver.switchTo().frame(driver.findElement(By.id("iframe1")));
        //driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();
        driver.findElement(By.name("q")).sendKeys("Halil Arslan");

    }
}
