package createuser;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;

import reusable.ReusableMethod;

import java.io.FileInputStream;

public class CreateUser {

    ReusableMethod rm;

    @BeforeClass
    public void setUp() {

        rm = new ReusableMethod();
    }

    @Parameters({"usercreationurl", "userName", "ssnNo"})
    @Test(groups = {"cel", "book"}, priority = 0)
    public void createNewUser(String usercreationurl, String userName, String ssnNo) {
        setUp();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(rm.CreateJsonBody(userName, ssnNo))
                .when()
                .post(usercreationurl);

        int statuscode = response.getStatusCode();
        String responsBody = response.getBody().asString();
        System.out.println(statuscode);
        System.out.println(responsBody);
        System.out.println(response.getBody().jsonPath().getString("id"));

    }


    @Parameters({"url", "celsius"})
    @Test(groups = {"cel"}, priority = 2)
    public void celsiusToFahrenheitConver(String url, String celsius) {
        setUp();
        Response response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .body(rm.createXmlBody(celsius))
                .when()
                .post(url);

        int statuscode = response.getStatusCode();
        String responsBody = response.getBody().asString();
        System.out.println(statuscode);
        System.out.println(responsBody);
    }

    @Parameters({"bookurl"})
    @Test(priority = 1)
    public void bookxmlValidation(String bookurl) {
        setUp();
        Response response = RestAssured.get(bookurl);

        int statuscode = response.getStatusCode();
        String responsBody = response.getBody().asString();
        XmlPath xml_path_obj = new XmlPath(response.getBody().asString()).using(xmlPathConfig().namespaceAware(false));
        String data = xml_path_obj.getString("bookstore.book[0].@category");
        System.out.println(data);
        System.out.println(statuscode);
        System.out.println(responsBody);

    }

    @AfterClass
    public void tearDown(){

    }

}
