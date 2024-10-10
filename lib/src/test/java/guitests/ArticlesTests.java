package guitests;

import api.ArticlesService;
import api.RequestManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import testutils.ReusableData;
import testutils.TestDataGenerator;

public class ArticlesTests extends BaseTest{

    @Test(groups = "gui")
    public void AddArticleWithProperData(){
        //Given
        var newArticleTitle = TestDataGenerator.generateText(20);
        var newArticleBody = TestDataGenerator.generateText(50);
        var picture = ReusableData.articlePictureName;
        var requestManager = new RequestManager();
        var articlesAPI = new ArticlesService(requestManager);

        //When
        registerAndLogin();

        int numberOfArticlesBeforeAdding = articlesAPI.getNumberOfArticles(appUrl);
        navigationBar.clickArticlesPageButton();
        NewArticleModal newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(newArticleTitle, newArticleBody, picture);
        newArticleModal.clickSaveButton();

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding +1);
    }

    @Test(groups = "gui")
    public void AddArticleWithMissingData(){
        //Given
        var requestManager = new RequestManager();
        var articlesAPI = new ArticlesService(requestManager);
        var numberOfArticlesBeforeAdding = articlesAPI.getNumberOfArticles(appUrl);
        var randomTitle = TestDataGenerator.generateText(15);
        var randomBody = TestDataGenerator.generateText(50);
        //When
        registerAndLogin();
        navigationBar.clickArticlesPageButton();
        NewArticleModal newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterTitle(randomTitle);
        newArticleModal.clickSaveButton();
        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleNotCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding);

        //When
        newArticleModal.clearTitle();
        newArticleModal.enterBody(randomBody);
        newArticleModal.clickSaveButton();

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleNotCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding);
    }

    @Test(groups = "gui")
    public void TryAddArticleByNotLoggedUser(){
        //When
        LandingPage landingPage= new LandingPage(driver);
        landingPage.clickStartButton();

        //Then
        Assert.assertFalse(navigationBar.isAddArticleButtonVisible());
    }

    @Test(groups = "gui")
    public void AddArticleWithPolishAndSpecialCharacters(){
        //Given
        var polishAndSpecialCharactersTitle = "Żółw wśród@ raf koralowych – #wyjątkowa podróż pełna emocji i % niespodzianek!";
        var polishAndSpecialCharactersBody = "Podróżując # wśród@ malowniczych! @ra$f ()&%koralowych, żółw majestatycznie unosi się w krystalicznie czystej wodzie. Odkryj piękno oceanu, którego tajemnice skrywają niezwykłe stworzenia. W tej podróży zobaczysz bogactwo barw, odcieni oraz różnorodność fauny, jakiej nie znajdziesz nigdzie indziej. Zbliż się do przyrody i zanurz w świat pełen emocji – od fascynacji po zachwyt. Niech ta przygoda pozostawi w Tobie niezapomniane wspomnienia.";
        var articlesAPI = new ArticlesService(requestManager);
        var numberOfArticlesBeforeAdding = articlesAPI.getNumberOfArticles(appUrl);

        //When
        registerAndLogin();
        navigationBar.clickArticlesPageButton();
        NewArticleModal newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(polishAndSpecialCharactersTitle, polishAndSpecialCharactersBody, ReusableData.articlePictureName);
        newArticleModal.clickSaveButton();

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding +1);
    }
}
