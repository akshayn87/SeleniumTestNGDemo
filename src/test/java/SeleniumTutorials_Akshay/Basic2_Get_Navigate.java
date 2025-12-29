package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Basic2_Get_Navigate {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://omayo.blogspot.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("cookieChoiceDismiss")).click();
        Thread.sleep(2000);
        WebElement newPage=driver.findElement(By.xpath("//a[text()='compendiumdev']"));
        newPage.click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.close();
    }
}
