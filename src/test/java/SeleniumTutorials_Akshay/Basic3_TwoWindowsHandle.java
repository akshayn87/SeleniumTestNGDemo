package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class Basic3_TwoWindowsHandle {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://omayo.blogspot.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("cookieChoiceDismiss")).click();

        driver.findElement(By.linkText("Open a popup window")).click();

        //String currentTab= driver.getWindowHandle();

        Set<String> windowHandles=driver.getWindowHandles();
        Iterator<String> itr=windowHandles.iterator();

        String mainWindow=itr.next();
        String childWindow=itr.next();
        driver.switchTo().window(childWindow);

     /*   while(itr.hasNext()){
            System.out.println(itr.next());
        }*/

        /*       for(String tab:windowHandles){
            if(!tab.equals(currentTab)){
                driver.switchTo().window(tab);
            }
        }*/

      System.out.println(driver.findElement(By.cssSelector("#para1")).getText());

      driver.close();

      driver.switchTo().window(mainWindow);
      driver.close();



    }
}
