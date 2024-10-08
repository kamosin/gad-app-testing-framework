package apitests.registration;

import api.LoginService;
import api.UserService;
import api.models.LoginRequest;
import api.models.UserRequest;
import api.testutils.TestUtils;
import apitests.BaseApiTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testutlis.ReusableData;
import testutlis.TestDataGenerator;

public class ApiRegistrationTests extends BaseApiTest {

    UserService userService;
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        userService = new UserService(requestManager);
    }

    @Test(groups = {"api", "registration"})
    public void userRegistrationAndLoginWithProperData(){
        //Given

        var loginService = new LoginService(requestManager);
        var user = TestDataGenerator.generateUser();

        //When
        var userResponse = userService.createUser(user);
        userResponse.then().statusCode(201);
        var loginResponse = loginService.login(new LoginRequest(user.email(), user.password()));

        //Then
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
    }

    @Test(groups = {"api"})
    public void registerWithExistingEmail(){
        //Given
        var userService = new UserService(requestManager);
        var user = TestDataGenerator.generateUser();

        //When
        var userResponse = userService.createUser(user);
        userResponse.then().statusCode(201);
        userResponse = userService.createUser(new UserRequest(TestDataGenerator.generateFirstName(), TestDataGenerator.generateLastName(),
                user.email(), TestDataGenerator.generateBirthdate(), TestDataGenerator.generatePassword(), "0797eae7-5f95-4985-8ac8-10c58e17c769.jpg"));

        //Then
        Assert.assertEquals(userResponse.getStatusCode(), 409);
        Assert.assertEquals(TestUtils.getJsonPath(userResponse, "error.message"), ReusableData.emailNotUniqueApiMessage);
    }

    @Test(groups = {"api"})
    public void registerOnlyWithEmail(){
        //Given
        var userService = new UserService(requestManager);
        var user = new UserRequest(null, null, TestDataGenerator.generateEmail(), null, null, null);

        //When
        var userResponse = userService.createUser(user);

        //Then
        Assert.assertEquals(userResponse.getStatusCode(), 422);
        Assert.assertEquals(TestUtils.getJsonPath(userResponse, "error.message"), ReusableData.mandatoryFieldsMissingApiMessage);
    }

    @Test(groups = {"api"})
    public void registerWithWrongDateFormat(){
        //Given
        var userService = new UserService(requestManager);
        var user = new UserRequest(TestDataGenerator.generateFirstName(), TestDataGenerator.generateLastName(),
                "12-12-1996", TestDataGenerator.generateBirthdate(), TestDataGenerator.generatePassword(), "0797eae7-5f95-4985-8ac8-10c58e17c769.jpg");

        //When
        var userResponse =userService.createUser(user);

        //Then
        Assert.assertEquals(userResponse.getStatusCode(), 422);
        Assert.assertEquals(TestUtils.getJsonPath(userResponse, "error.message"), ReusableData.invalidEmailFormatApiMessage);
    }


}
