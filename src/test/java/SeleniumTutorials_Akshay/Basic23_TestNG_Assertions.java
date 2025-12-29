package SeleniumTutorials_Akshay;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class Basic23_TestNG_Assertions {
    @Test
    public void test1() {
        Assert.assertEquals("Halil", "Halil");
    }

    @Test
    public void test2() {

        Assert.assertNotEquals("Halil", "Arslan");
    }

    @Test
    public void test3() {
        int age = 17;

        if (age < 16) {
            throw new ArithmeticException("Age is under 16");
        }
        Assert.assertTrue(5 < 7);
        System.out.println("Age is over 16");
    }

    @Test
    public void test4() {
        Assert.assertFalse(5 > 7);
    }

    @Test
    public void test5() {
        int age = 17;
        if (age < 16) {
            Assert.fail();
        }
    }

    @Test
    public void test6() {
        int age = 15;
        if (age < 16) {
            throw new SkipException("This is skipped");
        }
    }

}

