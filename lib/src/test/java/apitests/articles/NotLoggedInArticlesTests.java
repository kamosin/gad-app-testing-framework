package apitests.articles;

import api.ArticlesService;
import apitests.BaseApiTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testutils.TestDataGenerator;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class NotLoggedInArticlesTests extends BaseApiTest {

    ArticlesService articlesService;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        articlesService = new ArticlesService(requestManager);
    }

    @Test(groups = {"api"})
    public void createArticleAsNotLoggedInUser(){
        //Given
        var article = TestDataGenerator.generateArticle();
        //When
        var response = articlesService.createArticle(article);
        //Then
        response.then().log().all().statusCode(401).body(not(emptyOrNullString()));
    }
}
