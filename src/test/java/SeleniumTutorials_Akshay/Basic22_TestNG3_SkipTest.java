package SeleniumTutorials_Akshay;

import org.testng.SkipException;
import org.testng.annotations.Test;

public class Basic22_TestNG3_SkipTest {

    @Test
    public void test1() {
        System.out.println("Inside testOne");
    }


    @Test
    public void test2() {
        int a = 10 / 0;
    }

    @Test
    public void test3() {
        int age = 17;

        if (age < 16) {
            throw new ArithmeticException("Age is under 16");
        }

        System.out.println("Age is over 16");
    }

    @Test
    public void test4() {
        int age = 15;

        if (age < 16) {
            throw new SkipException("This method is skipped");
        }
    }
}
