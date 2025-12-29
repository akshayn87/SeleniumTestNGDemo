package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Basic3_WebElements {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://omayo.blogspot.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        List<WebElement> element = driver.findElements(By.xpath(("//div[@id='LinkList1']//a")));
        for (WebElement webElement : element) {
            System.out.println(webElement.getText());
        }

        for (int i = 0; i < element.size(); i++) {
            System.out.println(element.get(i).getAttribute("href"));
        }

        //Butun hyperlinlkleri bastirir

        List<WebElement> hyperLinkList = driver.findElements(By.tagName("a"));
        for (int i = 0; i < hyperLinkList.size(); i++) {
            System.out.println(hyperLinkList.get(i).getText());
        }

        for (int i = 0; i < hyperLinkList.size(); i++) {
            System.out.println(hyperLinkList.get(i).getAttribute("href"));

        }
        driver.close();
    }
}