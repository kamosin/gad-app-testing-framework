package api;

import api.models.UserRequest;
import io.restassured.response.Response;

public class UserService{

    private final RequestManager requestManager;
    private static final String usersEndpoint = "/api/users";

    public UserService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public Response getUsers(){
        return requestManager.get(usersEndpoint);
    }

    public Response createUser(UserRequest userRequest){
        return requestManager.post(usersEndpoint, userRequest);
    }

    public Response getUserById(int userId){
        return requestManager.get(usersEndpoint+"/"+userId);
    }

    public int getNumberOfUsers(String baseURI){
        return getUsers().jsonPath().getList("").size();
    }

}
