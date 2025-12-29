package SeleniumTutorials_Akshay;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Basic15_FrameConcept1 {
    public static void main(String[] args) throws IOException {

        //representing EmplooyeeDetails.properties

        File propertiesFile=new File("config/EmployeeDetails.properties");
        Properties properties = new Properties();

        FileInputStream fileInputStream=new FileInputStream(propertiesFile);
        properties.load(fileInputStream); // loading the EmplooyeeDetails.properties

        System.out.println(properties.getProperty("Name"));
        System.out.println(properties.getProperty("Place"));
        System.out.println(properties.getProperty("Designation"));
        System.out.println(properties.getProperty("Experience"));

    }
}
