package apitests;

import api.RequestManager;
import api.UserService;
import api.models.LoginRequest;
import org.testng.annotations.BeforeMethod;
import testutils.TestDataGenerator;

public class BaseApiTest {

    protected RequestManager requestManager;

    @BeforeMethod(groups = "api")
    public void baseSetup() {
        requestManager = new RequestManager();
    }

    protected void authUser() {
        var userService = new UserService(requestManager);
        var user = TestDataGenerator.generateUser();
        var response = userService.createUser(user);
        response.then().statusCode(201);
        requestManager.setToken(new LoginRequest(user.email(), user.password()));
    }
}
