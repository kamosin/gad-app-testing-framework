package apitests.flashposts;

import api.FlashpostsService;
import api.models.flashpost.FlashpostRequest;
import api.models.flashpost.FlashpostSettings;
import apitests.BaseApiTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testutils.TestDataGenerator;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class NotLoggedInFlashpostTests extends BaseApiTest {

    private FlashpostsService flashpostsService;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        flashpostsService = new FlashpostsService(requestManager);
    }

    @Test(groups = {"api"})
    public void createFlashpostAsNotLoggedInUser(){
        //Given
        var flashpostRequest = new FlashpostRequest(TestDataGenerator.generateText(50), new FlashpostSettings("#dddfff"), false);
        //When
        var response = flashpostsService.createFlashpost(flashpostRequest);
        //Then
        response.then().log().all().statusCode(401).body(not(emptyOrNullString()));
    }
}
