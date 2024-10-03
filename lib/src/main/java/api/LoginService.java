package api;

import api.models.LoginRequest;
import io.restassured.response.Response;

public class LoginService {

    private final RequestManager requestManager;
    public static final String loginEndpoint = "/api/login";
    public static final String logoutEndpoint = "/logout";

    public LoginService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public Response login(LoginRequest loginRequest){
        return requestManager.post(loginEndpoint, loginRequest);
    }

    public Response logout(){
        requestManager.logout();
        return requestManager.get(logoutEndpoint);
    }
}
