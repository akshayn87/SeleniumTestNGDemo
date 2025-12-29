package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basic1_OpenBrowser {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://omayo.blogspot.com/");

        driver.manage().window().maximize();

        Thread.sleep(1000);
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        WebElement element = driver.findElement(By.id("confirm"));
        element.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        driver.findElement(By.name("SiteMap")).click();
        driver.findElement(By.className("classone")).sendKeys("Halil");

        System.out.println(driver.findElement(By.linkText("compendiumdev")).getText());

        driver.findElement(By.id("selenium143")).click();
        driver.quit();


    }
}
