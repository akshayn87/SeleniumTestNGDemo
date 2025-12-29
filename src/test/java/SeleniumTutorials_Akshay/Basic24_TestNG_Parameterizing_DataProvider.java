package SeleniumTutorials_Akshay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Basic24_TestNG_Parameterizing_DataProvider {
    @Test(dataProvider = "loginRelatedData")
    public void testLogin(String username,String password){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("http://omayo.blogspot.com/");

        System.out.println("Username "+username+" Password "+password);
    }

    @DataProvider
    public Object[][] loginRelatedData(){
        Object [][] obj={{"AdminUsername","AdminPassword"},{"TeacherUserName","TeacherPassword"},{"StudentUserName","StudentUsername"}};
        return obj;
    }
}
