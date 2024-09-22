import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.*;

public class BaseTest {

    WebDriver driver;
    String appUrl = "http://localhost:3000";
    String restoreDatabaseEndpoint = "/restoreDB";

    @BeforeMethod
    public void launchApplication(){
        initializeDriver();
        var landingPage = new LandingPage(driver);
        landingPage.goToLandingPage(appUrl);
        restoreDatabase();
    }

    private void restoreDatabase() {
        RestAssured
                .given()
                .baseUri(appUrl)
                .when()
                .get(restoreDatabaseEndpoint)
                .then()
                .statusCode(201)
                .log()
                .status();
    }

    private void initializeDriver() {
        driver = new ChromeDriver(new ChromeOptions());
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
