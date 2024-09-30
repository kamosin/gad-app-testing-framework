package api;

import io.restassured.RestAssured;

public class OtherFeaturesService {

    private final RequestManager requestManager;
    private static final String restoreDbEndpoint = "/restoreDB";

    public OtherFeaturesService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public void restoreDb(String baseURI){
        requestManager.get(restoreDbEndpoint)
                .then()
                .statusCode(201)
                .log()
                .status();
    }
}
