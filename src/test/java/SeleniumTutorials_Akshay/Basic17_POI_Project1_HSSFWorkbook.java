package SeleniumTutorials_Akshay;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utilities.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Basic17_POI_Project1_HSSFWorkbook {
    public static void main(String[] args) throws IOException {

        File excelFile = new File("src/test/resources/ExcelXLS.xls");
        FileInputStream fis = new FileInputStream(excelFile);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(3);
        HSSFCell cell = row.getCell(2);
        System.out.println(cell.getStringCellValue());

        ExcelUtil newExcel=new ExcelUtil("src/test/resources/ExcelXLS.xlsx","EmployeeData");

        System.out.println(newExcel.getColumnsNames());
        System.out.println(newExcel.getCellData(2, 3));


    }
}
