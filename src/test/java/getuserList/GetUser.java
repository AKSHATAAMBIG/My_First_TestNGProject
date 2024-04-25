package getuserList;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class GetUser {
    Response response;

    @BeforeClass
    public void setUp(){

        System.out.println("I am before class");
    }
    @Test
    public void doReqresGetUserList_Validation(){
        Response response1 = get("https://reqres.in/api/users?page=2");
        System.out.println(response1.getBody().asString());
        int statuscode = response1.getStatusCode();
        System.out.println(statuscode);
    }

    @Test
    public void doBookLibrarySoapCall_Validation(){
        System.out.println("This is book library test");

    }

    @AfterClass
    public void shutdown()
    {
        System.out.println("I am for closing the connection");
    }
}
