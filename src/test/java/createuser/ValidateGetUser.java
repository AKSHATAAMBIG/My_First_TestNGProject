package createuser;

import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ValidateGetUser {

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeClass
    public void createSetUp(){

        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/validation1.html");
        spark.config().setDocumentTitle("Reqres website get api details validation");
        spark.config().setReportName("Reqres GET_API_DETAILS");
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
        extent.setSystemInfo("QA Name","Raji");
        extent.setSystemInfo("Build Name","BHg567");
        extent.setSystemInfo("Environment Name","QA");
        logger = extent.createTest("validate get api from reqres application");

    }

    @Test
    public void valiadateWithContains () {
        logger.info("The get API URL is https://reqres.in/api/users?page=2");
        Response response = get("https://reqres.in/api/users?page=2");
        String res = response.getBody().asString();
        logger.info(res);
        int jsonPathCount = response.getBody().jsonPath().getList("data.first_name").size();
        System.out.println("Count is:" + jsonPathCount);

        for (int a = 0; a < jsonPathCount; a++) {
            String id = response.getBody().jsonPath().getString("data.id[" + a + "]");
            String email = response.getBody().jsonPath().getString("data.email[" + a + "]");

            System.out.println("If id value is:" + id + " Then email value is:" + email);
            logger.info("If id value is:" + id + " Then email value is:" + email);

        }
    }

    @AfterClass
    public void tearDown(){
        extent.flush();
    }

}
