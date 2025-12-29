package SeleniumTutorials_Akshay;

import utilities.ExcelUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Basic18_POI_Project2_ExcelUtil {
    public static void main(String[] args) {
        ExcelUtil testData=new ExcelUtil("src/test/resources/ExcelXLS.xls","EmployeeData");
        System.out.println(testData.rowCount());
        System.out.println(testData.columnCount());
        System.out.println(testData.getColumnsNames());

        List<Map<String, String>> dataList = testData.getDataList();
        for (Map<String,String> onerow:dataList){
            System.out.println(onerow);
        }

        String[][] dataArray=testData.getDataArray();
        System.out.println(Arrays.deepToString(dataArray));

    }
}
