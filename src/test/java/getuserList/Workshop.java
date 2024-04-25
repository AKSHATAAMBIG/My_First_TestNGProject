package getuserList;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reusable.ReusableMethod;

import java.io.FileReader;
import java.util.Properties;

import static io.restassured.RestAssured.get;
import static reusable.ReusableMethod.readPropertyFile;


public class Workshop {


    //Use TestNG framework
    //Read GET api URL from properties file
    //create one xl table where population vs year details are given
    //Hit the url and read Year vs population and match the data with xl sheet
    //If data matches then update the details in extent report and create a final Extent report



    String year = null;
    ReusableMethod rs;
    String population;

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private static ExtentTest logger;

    @BeforeClass
    public void setUp(){

        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/validation3.html");
        spark.config().setDocumentTitle("Workshop: API validation");
        spark.config().setReportName("Validate GET_API_DETAILS");
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
        extent.setSystemInfo("QA Name","Raji");
        extent.setSystemInfo("Build Name","BHg567");
        extent.setSystemInfo("Environment Name","QA");
        logger = extent.createTest("validate web api from the url");

        rs = new ReusableMethod();
    }

    @Test
    public void doAllWebAppTest(){

        year = "2021";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2020";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2019";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2018";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2017";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2016";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year= "2015";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2014";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

        year = "2013";
        population = rs.readingXLFileAsPerTestData(year,"Population");
        doWebApiValidationForPopulation(population,year);

    }

    public static void doWebApiValidationForPopulation (String population,String year) {

        logger.info("The get API URL is https://datausa.io/api/data?drilldowns=Nation&measures=Population");
        String url = null;
        url = readPropertyFile();

        Response response = get(url);
        String res = response.getBody().asPrettyString();
        logger.info(res);

        int jsonPathCount = response.getBody().jsonPath().getList("data.year").size();

        for (int a = 0; a <= jsonPathCount; a++) {

            String actualPopulation = response.getBody().jsonPath().getString("data.Population[" + a + "]");
            String actualYear = response.getBody().jsonPath().getString(("data.Year[" + a + "]"));

            if (population.equals(actualPopulation)) {
                Assert.assertEquals(year, actualYear);
                System.out.println("For the year " + actualYear + ", the population is: " + actualPopulation);
                logger.info("For the year " + actualYear + ", the population is: " + actualPopulation);
                break; // Assuming each year's population is unique, stop iteration once the correct year is found
            }
        }
    }

    @AfterClass
    public void tearDown(){
        extent.flush();
    }
}
