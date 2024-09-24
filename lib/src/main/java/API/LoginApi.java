package API;

import API.pojo.LoginRequest;
import API.testutils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginApi {

    private static final String endpoint = "/api/login";
    private static final String baseUrl = TestUtils.getGlobalValue("baseUrl");

    public Response postLogin(LoginRequest loginRequest){
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON).build();
        return RestAssured.given().spec(req).baseUri(baseUrl).body(loginRequest).when().post(endpoint);
    }

    public String getToken (LoginRequest loginRequest){
        var response = postLogin(loginRequest);
        return TestUtils.getJsonPath(response, "access_token");
    }
}
