package guitests;

import api.OtherFeaturesService;
import api.RequestManager;
import api.models.UserRequest;
import api.testutils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pageobjects.*;
import testutlis.TestDataGenerator;

public class BaseTest {

    WebDriver driver;
    NavigationBar navigationBar;
    CommonComponent commonComponent;
    OtherFeaturesService otherFeaturesService;
    String appUrl = TestUtils.getGlobalValue("baseUrl");
    RequestManager requestManager;

    @BeforeMethod(alwaysRun = true)
    public void launchApplication(){
        initializeDriver();
        var landingPage = new LandingPage(driver);
        landingPage.goToLandingPage(appUrl);
        requestManager = new RequestManager();
//        restoreDatabase();
    }

    private void restoreDatabase() {
        otherFeaturesService = new OtherFeaturesService(requestManager);
        otherFeaturesService.restoreDb(appUrl);
    }

    private void initializeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        commonComponent = new CommonComponent(driver);
        navigationBar = new NavigationBar(driver);
        driver.manage().window().maximize();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }

    public UserRequest registerAndLogin(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        UserRequest user = TestDataGenerator.generateUser();
        registrationPage.registerWithAllFields(user.firstname(), user.lastname(), user.email(), user.birthDate(), user.password(), user.avatar());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.email(), user.password());
        return user;
    }
}
