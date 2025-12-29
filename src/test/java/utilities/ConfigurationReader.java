package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static{
        try{
            //what file to read
            //File propertiesFile=new File("config/ProjectDetails.properties");
            String propertiesFile="config/ProjectDetails.properties";
            //read the file into java. finds the file using the string path
            FileInputStream fileInput=new FileInputStream(propertiesFile);
            //properties---> class that score properties in key/ value format
            properties=new Properties();
            //the values from the file input is loaded/ fed in to the properties object
            properties.load(fileInput);
            //fileInput.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String get(String keyName){

        return  properties.getProperty(keyName);
    }




}
