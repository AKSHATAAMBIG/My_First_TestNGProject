package createuser;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reusable.ReusableMethod;

import static io.restassured.RestAssured.given;

public class CreateUser1 {

    ReusableMethod rm;
    @BeforeClass
    public void setUp(){

        rm = new ReusableMethod();
    }

    @Parameters({"usercreationurl","userName","ssnNo"})
    @Test
    public void createNewUser(String usercreationurl,String userName,String ssnNo){
        Response response = given()
                .contentType(ContentType.JSON)
                .body(rm.CreateJsonBody(userName,ssnNo))
                .when()
                .post(usercreationurl);

        int statuscode = response.getStatusCode();
        String responsBody = response.getBody().asString();
        System.out.println(statuscode);
        System.out.println(responsBody);
        System.out.println(response.getBody().jsonPath().getString("id"));

    }

    @Parameters({"url","celsius"})
    @Test
    public void celsiusToFahrenheitConver(String url,String celsius) {
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

    @AfterClass
    public void tearDown(){

    }

}
