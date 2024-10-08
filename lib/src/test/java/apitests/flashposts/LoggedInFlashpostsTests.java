package apitests.flashposts;

import api.FlashpostsService;
import api.LoginService;
import api.models.flashpost.FlashpostRequest;
import api.models.flashpost.FlashpostSettings;
import api.testutils.TestUtils;
import apitests.BaseApiTest;
import org.testng.Assert;
import testutlis.ReusableData;
import testutlis.TestDataGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class LoggedInFlashpostsTests extends BaseApiTest {

    private FlashpostsService flashpostsService;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        flashpostsService = new FlashpostsService(requestManager);
        authUser();
    }

    @Test(groups = {"api"})
    public void getAllFlashposts(){
        //When
        var response = flashpostsService.getFlashposts();
        //Then
        Assert.assertEquals(response.getStatusCode() ,200);
    }

    @Test(groups = {"api"})
    public void createFlashpostWithValidData(){
        //Given
        var flashpostRequest = new FlashpostRequest(TestDataGenerator.generateText(50), new FlashpostSettings("#dddfff"), false);
        var initialNrOfFlashposts = flashpostsService.getNumberOfFlashposts();
        //When
        var response = flashpostsService.createFlashpost(flashpostRequest);
        //Then
        var nrOfFlaspostsAfterCreation = initialNrOfFlashposts + 1;
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(flashpostsService.getNumberOfFlashposts(), nrOfFlaspostsAfterCreation);
    }

    @Test(groups = {"api"})
    public void createFlashpostWithTooLongMessage(){
        //Given
        var flashpostRequest = new FlashpostRequest("Automatyzuj testy z Playwright i Git! Zwiększ efektywność, wdrażaj szybciej i poprawiaj jakość kodu. Przyszłość IT w twoich rękach!", new FlashpostSettings(null), true);
        //When
        var response = flashpostsService.createFlashpost(flashpostRequest);
        //Then
        Assert.assertEquals(response.statusCode(), 422);
        response.then().body("error.message", containsString(ReusableData.flashpostsFieldValidationMessage));

    }

    @Test(groups = {"api"})
    public void createPublicFlashpostThenLogoutAndCheckVisibility(){
        //Given
        var flashpostText = TestDataGenerator.generateText(50);
        var flashpostRequest = new FlashpostRequest(flashpostText, new FlashpostSettings("#dddfff"), true);
        var loginService = new LoginService(requestManager);
        //When
        var response = flashpostsService.createFlashpost(flashpostRequest);
        loginService.logout();

        //Then
        response = flashpostsService.getFlashpostsById(Integer.parseInt(TestUtils.getJsonPath(response, "id")));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(TestUtils.getJsonPath(response, "body"), flashpostText);
    }

    @Test(groups = {"api", "brokenTests"})
    public void createNonPublicFlashpostThenLogoutAndCheckVisibility(){
        //Given
        var flashpostText = TestDataGenerator.generateText(50);
        var flashpostRequest = new FlashpostRequest(flashpostText, new FlashpostSettings("#dddfff"), false);
        var loginService = new LoginService(requestManager);
        //When
        var response = flashpostsService.createFlashpost(flashpostRequest);
        loginService.logout();
        //Then
        response = flashpostsService.getFlashpostsById(Integer.parseInt(TestUtils.getJsonPath(response, "id")));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 401);
        Assert.assertNotEquals(TestUtils.getJsonPath(response, "body"), flashpostText);

    }

}
