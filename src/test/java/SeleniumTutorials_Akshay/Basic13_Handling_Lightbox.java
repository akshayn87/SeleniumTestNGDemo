package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basic13_Handling_Lightbox {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://omayo.blogspot.com/p/lightbox.html");
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        driver.findElement(By.id("lightbox1")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@class='close cursor']")).click();
    }
}
