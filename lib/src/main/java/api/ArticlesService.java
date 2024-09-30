package api;

import api.models.ArticleRequest;
import api.testutils.TestUtils;
import io.restassured.response.Response;

public class ArticlesService extends TestUtils{

    private final RequestManager requestManager;
    private static final String articlesEndpoint = "/api/articles";

    public ArticlesService(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public Response getArticles(){
        return requestManager.get(articlesEndpoint);
    }

    public Response createArticle(ArticleRequest articleRequest){
        return requestManager.post(articlesEndpoint, articleRequest);
    }

    public int getNumberOfArticles(String baseURI){
        return getArticles().jsonPath().getList("").size();
    }
}
