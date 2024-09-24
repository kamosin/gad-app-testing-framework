package API;

import API.pojo.ArticleRequest;
import API.testutils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ArticlesAPI {

    private static final String endpoint = "/api/articles";

    public Response getArticles(String baseURI){
        return RestAssured.given().baseUri(baseURI).when().get(endpoint);
    }

    public Response postArticle(ArticleRequest articleRequest, String token){
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri(TestUtils.getGlobalValue("baseUrl"))
                .setContentType(ContentType.JSON).build();
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .spec(req)
                .body(articleRequest)
                .when()
                .log().all()
                .post(endpoint);
    }

    public int getNumberOfArticles(String baseURI){
        return getArticles(baseURI).jsonPath().getList("").size();
    }
}
