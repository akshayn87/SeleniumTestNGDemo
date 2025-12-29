package SeleniumTutorials_Akshay;

import org.testng.annotations.Test;

public class Basic20_TestNG1 {

    @Test(priority = 2)
    public void enterWithValidCredentials() {
        System.out.println("enterWithValidCredentials");
    }

    @Test(priority = 2)
    public void enterWithInvalidCredentials() {
        System.out.println("enterWithInvalidCredentials");
    }

    @Test(priority = 1)
    public void enterWithNoCredentials() {
        System.out.println("enterWithNoCredentials");
    }

}

