package reusable;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class ReusableMethod {
    Response response;

    public String CreateJsonBody(String userName,String ssnNo) {
        String body = "{\n" +
                "    \"name\": \""+userName+"\",\n" +
                "    \"job\": \"LEAD\",\n" +
                "    \"Address\": \"123 HONNAVAR\",\n" +
                "    \"SSN\": \""+ssnNo+"\"\n" +
                "}";
        return body;
    }

    public String createXmlBody(String celsius) {
        String soapBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <soap:Body>\n" +
                "    <CelsiusToFahrenheit xmlns=\"https://www.w3schools.com/xml/\">\n" +
                "      <Celsius>" + celsius + "</Celsius>\n" +
                "    </CelsiusToFahrenheit>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        return soapBody;
    }

    public String readingXLFileAsPerTestData(String testcaseName, String colName) {

        String data = null;
        try {
            // String xlFile = System.getProperty(user.dir)
            String xlFile = "C:/Users/akshatan/Desktop/Population.xlsx";
            FileInputStream myxlfile1 = new FileInputStream(xlFile);
            Workbook workbook = new XSSFWorkbook(myxlfile1);
            Sheet sheet = workbook.getSheet("Sheet1");
            int lastrow = sheet.getLastRowNum();
            //System.out.println("The last row number which has data is:" + lastrow);

            //loop for row iteration
            for (int i = 0; i <= lastrow; i++) {
                Row row = sheet.getRow(i);
                //get the  last column which has data
                int lastCell = row.getLastCellNum();
                Cell cell = row.getCell(0);
                String runTimeTestCaseName = cell.getStringCellValue();

                if (runTimeTestCaseName.equals(testcaseName)) {

                    //Loop for column iteration
                    Row rowNew = sheet.getRow(0);
                    for (int j = 1; j < lastCell; j++) {
                        Cell cell1 = rowNew.getCell(j);
                        String runTimeCellValue = cell1.getStringCellValue();
                        if (runTimeCellValue.equals(colName)) {
                            data = sheet.getRow(i).getCell(j).toString();
                            System.out.println("The XL value is :" + data);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return data;
    }

    public static String readPropertyFile(){

        String url = null;
        try {
            // Load the properties file
            FileReader reader = new FileReader("C:\\Users\\akshatan\\Desktop\\pyspark_loan_default\\TestNG_RestAssured\\src\\test\\java\\createuser\\user.properties");
            Properties properties = new Properties();
            properties.load(reader);

            // Get property values
            url = properties.getProperty("url");

            // Close the reader
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}

