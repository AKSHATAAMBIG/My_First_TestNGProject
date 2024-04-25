package getuserList;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import reusable.ReusableMethod;

import static io.restassured.RestAssured.get;



public class WebAPI {


    String testName = null;
    ReusableMethod rs;
    String url,statuscode,title;

    @BeforeTest
    @BeforeClass
    public void setUp(){
        rs = new ReusableMethod();
    }

    @Test
    public void doAllWebAppTest(){
        testName = "TC001";
        url = rs.readingXLFileAsPerTestData(testName,"RequestURL");
        statuscode = rs.readingXLFileAsPerTestData(testName,"statuscode");
        title = rs.readingXLFileAsPerTestData(testName,"Title");
        doWebApiValidation(url,statuscode,title);

        testName = "TC002";
        url = rs.readingXLFileAsPerTestData(testName,"RequestURL");
        statuscode = rs.readingXLFileAsPerTestData(testName,"statuscode");
        title = rs.readingXLFileAsPerTestData(testName,"Title");
        doWebApiValidation(url,statuscode,title);

        testName = "TC003";
        url = rs.readingXLFileAsPerTestData(testName,"RequestURL");
        statuscode = rs.readingXLFileAsPerTestData(testName,"statuscode");
        title = rs.readingXLFileAsPerTestData(testName,"Title");
        doWebApiValidation(url,statuscode,title);
    }

    public static void doWebApiValidation (String url,String statuscode,String title) {

        Response response = get(url);
        String res = response.getBody().asString();

        Assert.assertEquals(String.valueOf(response.getStatusCode()),statuscode);
        Assert.assertTrue(res.contains(title));


    }
}
