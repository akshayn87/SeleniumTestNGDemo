package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class Basic5_MoreWindows {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://omayo.blogspot.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        driver.findElement(By.linkText("Open a popup window")).click();
        driver.findElement(By.linkText("Blogger")).click();

        //first way
        String parentWindowID = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();

        Iterator<String> itr = set.iterator();

        while (itr.hasNext()) {
            String childWindowId = itr.next();
            if (!parentWindowID.equals(childWindowId)) {
                driver.switchTo().window(childWindowId);
                System.out.println(driver.getTitle());
            }
        }
        //to print all titles
/*        while (itr.hasNext()) {
            String windowId = itr.next();
            driver.switchTo().window(windowId);
            System.out.println(driver.getTitle());
            }
        }*/

        //second way to change to next window
        //*******************************************************************
/*        String mainWindowID = driver.getWindowHandle();
        Set<String> set = driver.getWindowHandles();
        System.out.println("driver.getTitle() = " + driver.getTitle());
        for (String childWindowID : set) {
            if (!childWindowID.equals(mainWindowID)) {
                driver.switchTo().window(childWindowID);
                //break;
                System.out.println("driver.getTitle() = " + driver.getTitle());
                // break;
            } //System.out.println("driver.getTitle() = " + driver.getTitle());
        } //System.out.println("driver.getTitle() = " + driver.getTitle());*/

        //3th way to change to requested window
        //******************************************************************************
           /* driver.getWindowHandle();
            Set<String> set = driver.getWindowHandles();

            for (String childWindowID : set) {
                driver.switchTo().window(childWindowID);
                if(driver.getTitle().equals("Blogger.com - Create a unique and beautiful blog easily.")){
                    break;
                }
            }
            System.out.println("driver.getTitle() = " + driver.getTitle());
*/

    }
}