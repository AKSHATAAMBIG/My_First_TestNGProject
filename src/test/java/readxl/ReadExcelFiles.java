package readxl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import javax.imageio.stream.FileImageInputStream;
import java.io.FileInputStream;

public class ReadExcelFiles {

    //workbook-Interface to instantiate different XL file(xls/xlsx)
    //Sheet-Interface to read the sheet inside the workbook
    //Row-Interface used to identify the row inside the sheet
    //Cell-Interface to identify the corresponding cell of a given row

    //classes inside apache poi

    //XSSFWorkbook-Class which will implement this workbook interface for the XL file
    //HSSFWorkbook- Class which will implement this workbook interface for the XL file
    // XSSFSheet - Class representing a Sheet interface.
    // HSSFSheet - Class representing a Sheet interface.
    // XSSFRow - Class representing a Row interface.
    // HSSFRow - Class representing a Row interface.
    // XSSFCell - Class representing a Cell interface.
    // HSSFCell - Class representing a Cell interface.

    @Test
    public void readAndPrintSimpleXL(){
        try
        {
            String xlFile = "/Users/akshatan/Desktop/TestCaseExcel.xlsx";
            FileInputStream myxlfile = new FileInputStream(xlFile);
            Workbook workbook = new XSSFWorkbook(myxlfile);
            Sheet sheet = workbook.getSheet("Sheet1");
            int lastrow = sheet.getLastRowNum();
            System.out.println("The last row number which has data is:"+lastrow);

            //loop for row iteration
            for(int i = 0; i<=lastrow; i++){
                Row row = sheet.getRow(i);
                //get the  last column which has data
                int lastCell = row.getLastCellNum();

                //Loop for column iteration
                for(int j=0 ; j<lastCell ; j++){
                    Cell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    System.out.println("The XL value is :"+value);
                }
            }

        }catch(Exception e){
            e.printStackTrace();

        }

    }

    public static void readingXLFileAsPerTestData(String testcaseName, String colName){
        try
        {
        String xlFile = "C:/Users/akshatan/Desktop/CheckURL.xlsx";
        FileInputStream myxlfile1 = new FileInputStream(xlFile);
        Workbook workbook = new XSSFWorkbook(myxlfile1);
        Sheet sheet = workbook.getSheet("Sheet1");
        int lastrow = sheet.getLastRowNum();
        System.out.println("The last row number which has data is:"+lastrow);

        //loop for row iteration
        for(int i = 0; i<=lastrow; i++){
            Row row = sheet.getRow(i);
            //get the  last column which has data
            int lastCell = row.getLastCellNum();
            Cell cell = row.getCell(0);
            String runTimeTestCaseName = cell.getStringCellValue();

            if(runTimeTestCaseName.equals(testcaseName)) {

                //Loop for column iteration
                Row rowNew = sheet.getRow(0);
                for (int j = 1; j < lastCell; j++) {
                    Cell cell1 = rowNew.getCell(j);
                    String runTimeCellValue = cell1.getStringCellValue();
                    if (runTimeCellValue.equals(colName)) {
                        String value = sheet.getRow(i).getCell(j).toString();
                        System.out.println("The title value is :" + value);
                    }
                }
            }
        }
    }
        catch(Exception e){
        e.printStackTrace();

    }
    }

    public static void main(String[] args) {

        readingXLFileAsPerTestData("TC001","Title");
    }
}
