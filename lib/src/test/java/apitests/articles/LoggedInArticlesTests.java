package apitests.articles;

import api.ArticlesService;
import api.models.ArticleRequest;
import api.testutils.TestUtils;
import apitests.BaseApiTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testutils.ReusableData;
import testutils.TestDataGenerator;

public class LoggedInArticlesTests extends BaseApiTest {

    ArticlesService articlesService;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        articlesService = new ArticlesService(requestManager);
        authUser();
    }

    @Test(groups = {"api"})
    public void addArticleByRegisteredAndLoggedInUser(){
        //Given
        var article = TestDataGenerator.generateArticle();

        //When
        var response = articlesService.createArticle(article);

        //Then
        Assert.assertEquals(response.getStatusCode(), 201);

    }

    @Test(groups = {"api"})
    public void addArticleWithMissingData(){
        //Given
        var articleWithoutBody = new ArticleRequest(TestDataGenerator.generateArticleTitle(), "", TestDataGenerator.currentDate(), ReusableData.articlePictureName);
        var articleWithoutTitle = new ArticleRequest("", TestDataGenerator.generateText(50), TestDataGenerator.currentDate(), ReusableData.articlePictureName);
        //When
        var response = articlesService.createArticle(articleWithoutBody);
        //Then
        Assert.assertEquals(response.statusCode(), 422);
        Assert.assertEquals(TestUtils.getJsonPath(response, "error.message"), ReusableData.mandatoryFieldsMissingApiMessage);
        //When
        response = articlesService.createArticle(articleWithoutTitle);
        //Then
        Assert.assertEquals(response.statusCode(), 422);
        Assert.assertEquals(TestUtils.getJsonPath(response, "error.message"), ReusableData.mandatoryFieldsMissingApiMessage);

    }

    @Test(groups = {"api"})
    public void AddArticleWithTitleLongerThan128Characters(){
        //Given
        var article = new ArticleRequest(TestDataGenerator.generateText(130), TestDataGenerator.generateText(100),
                TestDataGenerator.currentDate(), ReusableData.articlePictureName);

        //When
        var response = articlesService.createArticle(article);

        //Then
        Assert.assertEquals(response.getStatusCode(), 422);
        Assert.assertTrue(response.jsonPath().getString("error.message").contains(ReusableData.articleTitleTooLongErrorMessageText));
    }
}
