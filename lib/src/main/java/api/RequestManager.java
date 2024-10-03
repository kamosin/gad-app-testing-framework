package api;

import api.models.LoginRequest;
import api.testutils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static api.testutils.TestUtils.*;

public class RequestManager {

    private static final String baseUrl = TestUtils.getGlobalValue("baseUrl");
    private static final String loginEndpoint = "/api/login";
    private String token;

    public void setToken(LoginRequest loginRequest) {
        var response = post(loginEndpoint, loginRequest);
        this.token = getJsonPath(response, "access_token");
    }

    public void logout(){
        this.token = "";
    }

    public RequestSpecification createRequestSpecification() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON).build();

        if (token != null && !token.isEmpty()) {
            requestSpecification.header("Authorization", "Bearer " + token);
        }

        return requestSpecification;
    }

    public <T> Response post(String endpoint, T body) {
        return RestAssured.given()
                .spec(createRequestSpecification())
                .body(body)
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response get(String endpoint) {
        return RestAssured.given()
                .spec(createRequestSpecification())
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response delete(String endpoint) {
        return RestAssured.given()
                .spec(createRequestSpecification())
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}
