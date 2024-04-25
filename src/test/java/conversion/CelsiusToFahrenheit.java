package conversion;

import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reusable.ReusableMethod;

import static io.restassured.RestAssured.given;

public class CelsiusToFahrenheit {

    ReusableMethod rm;

    @BeforeClass
    public void setUp(){
        rm = new ReusableMethod();
    }

    @Parameters({"url","celsius"})
    @Test
    public void celsiusToFahrenheitConver(String url,String celsius){
        Response response = given()
                .header("Content-Type","text/xml; charset=utf-8")
                .body(rm.createXmlBody(celsius))
                .when()
                .post(url);

        int statuscode = response.getStatusCode();
        String responsBody = response.getBody().asString();
        System.out.println(statuscode);
        System.out.println(responsBody);


    }

    @AfterClass
    public void tearDown(){

    }
}
