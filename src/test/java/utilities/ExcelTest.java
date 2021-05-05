package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelTest {
    public static void main(String[] args) {
        String path= "src/test/resources/testdata/TestData.xlsx";
        try {

            FileInputStream file = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet1= workbook.getSheet("Page1");
            String firstName=sheet1.getRow(1).getCell(0).toString();
            System.out.println(firstName);
            System.out.println(sheet1.getLastRowNum());
            System.out.println(sheet1.getRow(1).getCell(2));




        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
