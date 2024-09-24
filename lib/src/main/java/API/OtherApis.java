package API;

import io.restassured.RestAssured;

public class OtherApis {

    private static final String restoreDbEndpoint = "/restoreDB";

    public void restoreDb(String baseURI){
        RestAssured
                .given()
                .baseUri(baseURI)
                .when()
                .get(restoreDbEndpoint)
                .then()
                .statusCode(201)
                .log()
                .status();
    }
}
