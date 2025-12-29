package SeleniumTutorials_Akshay;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.util.Date;

public class ExtendManager {
    public static ExtentReports getInstance(){
        Date date=new Date();
        String fileNameType=date.toString().replace(" ","_").replace(":","_")+".html";
        String reportFilePath="test-output\\"+fileNameType;

        ExtentReports eReport=new ExtentReports(reportFilePath,true, DisplayOrder.NEWEST_FIRST);
        File reportConfig=new File("ReportsConfig.xml");
        eReport.loadConfig(reportConfig);
        eReport.addSystemInfo("TestNG Version","7.6.1")
                .addSystemInfo("Environment","Staging QA")
                .addSystemInfo("Executed By","Halil Arslan");

            return eReport;
    }

}
