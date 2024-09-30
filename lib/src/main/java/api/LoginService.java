package api;

import api.models.LoginRequest;
import io.restassured.response.Response;

public class LoginService {

    private final RequestManager requestManager;
    public static final String usersEndpoint = "/api/login";

    public LoginService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public Response login(LoginRequest loginRequest){
        return requestManager.post(usersEndpoint, loginRequest);
    }
}
