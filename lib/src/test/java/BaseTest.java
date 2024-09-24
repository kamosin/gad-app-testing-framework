import API.OtherApis;
import API.testutils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobjects.*;

public class BaseTest {

    WebDriver driver;
    NavigationBar navigationBar;
    CommonComponent commonComponent;
    OtherApis otherApis;
    String appUrl = TestUtils.getGlobalValue("baseUrl");

    @BeforeMethod
    public void launchApplication(){
        initializeDriver();
        var landingPage = new LandingPage(driver);
        landingPage.goToLandingPage(appUrl);
        restoreDatabase();
    }

    private void restoreDatabase() {
        otherApis = new OtherApis();
        otherApis.restoreDb(appUrl);
    }

    private void initializeDriver() {
        driver = new ChromeDriver(new ChromeOptions());
        commonComponent = new CommonComponent(driver);
        navigationBar = new NavigationBar(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
