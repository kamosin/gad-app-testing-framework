package API;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsersAPI {

    private static final String endpoint = "/api/users";

    public Response getUsers(String baseURI){
        return RestAssured.given().baseUri(baseURI).when().get(endpoint);
    }

    public Response getUserById(String baseURI, int userId){
        return RestAssured.given().baseUri(baseURI).when().get(endpoint+"/"+userId);
    }

    public int getNumberOfUsers(String baseURI){
        return getUsers(baseURI).jsonPath().getList("").size();
    }

}
