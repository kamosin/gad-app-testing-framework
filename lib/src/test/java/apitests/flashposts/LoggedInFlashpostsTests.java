package apitests.flashposts;

import api.FlashpostsService;
import api.RequestManager;
import api.UserService;
import api.models.LoginRequest;
import api.models.UserRequest;
import api.models.flashpost.FlashpostRequest;
import api.models.flashpost.FlashpostSettings;
import testutlis.ReusableData;
import testutlis.TestDataGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class LoggedInFlashpostsTests {

    UserRequest user;
    private FlashpostsService flashpostsService;
    RequestManager requestManager = new RequestManager();

    public void authUser() {
        var userService = new UserService(requestManager);
        user = TestDataGenerator.generateUser();
        var response = userService.createUser(user);
        response.then().statusCode(201);
        requestManager.setToken(new LoginRequest(user.email(), user.password()));
    }

    @BeforeMethod
    public void setup(){
        authUser();
        flashpostsService = new FlashpostsService(requestManager);
    }

    @Test
    public void getAllFlashposts(){
        //Act
        var response = flashpostsService.getFlashposts();
        //Assert
        response.then().assertThat().statusCode(200).body(not(emptyOrNullString()));
    }

    @Test
    public void createFlashpostWithValidData(){
        //Given
        var flashpostRequest = new FlashpostRequest("Flashpost test12", new FlashpostSettings("#dddfff"), false);
        //When
        flashpostsService.createFlashpost(flashpostRequest).then().statusCode(201).body(not(emptyOrNullString()));
    }

    @Test
    public void createFlashpostWithInvalidData(){
        //Given
        var flashpostRequest = new FlashpostRequest("Automatyzuj testy z Playwright i Git! Zwiększ efektywność, wdrażaj szybciej i poprawiaj jakość kodu. Przyszłość IT w twoich rękach!", new FlashpostSettings(null), true);
        //When
        var response = flashpostsService.createFlashpost(flashpostRequest);
        response.then().statusCode(422);
        response.then().body("error.message", containsString(ReusableData.flashpostsFieldValidationMessage));

    }

}
