import API.ArticlesAPI;
import API.LoginApi;
import API.pojo.ArticleRequest;
import API.pojo.LoginRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;
import java.time.Instant;

public class ArticlesTests extends BaseTest{

    private static final String firstName = "John";
    private static final String lastName = "Smith";
    private static final String email = "john.smith@mail.com";
    private static final String date = "1960-12-12";
    private static final String password = "pass";
    private static final String imageName = "0797eae7-5f95-4985-8ac8-10c58e17c769.jpg";

    private static final String articleTitle = "Will you lose your job because of AI?";
    private static final String articleBody = "Nobody cares";
    private static final String articlePictureName = "it-tester-1c4c6592-61aa-471c-866b-a438a06de921.jpg";

    private static final String expectedArticleCreatedMessage = "Article was created";
    private static final String expectedArticleNotCreatedMessage = "Article was not created";

    private static final String tooLongTitle = "Przykładowy tekst, który ma dokładnie tyle znaków. Jest on starannie dobrany, aby spełnić moje wymagania dotyczące długości treści.";
    private static final String errorMessageText = "Field validation: \"title\" longer than \"128\"";

    private static final String polishAndSpecialCharactersTitle = "Żółw wśród@ raf koralowych – #wyjątkowa podróż pełna emocji i % niespodzianek!";
    private static final String polishAndSpecialCharactersBody = "Podróżując # wśród@ malowniczych! @ra$f ()&%koralowych, żółw majestatycznie unosi się w krystalicznie czystej wodzie. Odkryj piękno oceanu, którego tajemnice skrywają niezwykłe stworzenia. W tej podróży zobaczysz bogactwo barw, odcieni oraz różnorodność fauny, jakiej nie znajdziesz nigdzie indziej. Zbliż się do przyrody i zanurz w świat pełen emocji – od fascynacji po zachwyt. Niech ta przygoda pozostawi w Tobie niezapomniane wspomnienia.";

    public void registerAndLogin(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithAllFields(firstName, lastName, email, date, password, imageName);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
    }

    @Test
    public void AddArticleWithProperData(){
        registerAndLogin();
        ArticlesAPI articlesAPI = new ArticlesAPI();
        int numberOfArticlesBeforeAdding = articlesAPI.getNumberOfArticles(appUrl);
        navigationBar.clickArticlesPageButton();
        NewArticleModal newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(articleTitle, articleBody, articlePictureName);
        newArticleModal.clickSaveButton();

        Assert.assertEquals(commonComponent.getPopupText(), expectedArticleCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding +1);
    }

    @Test
    public void AddArticleWithMissingData(){
        registerAndLogin();
        ArticlesAPI articlesAPI = new ArticlesAPI();
        int numberOfArticlesBeforeAdding = articlesAPI.getNumberOfArticles(appUrl);

        navigationBar.clickArticlesPageButton();
        NewArticleModal newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterTitle("Another article");
        newArticleModal.clickSaveButton();
        Assert.assertEquals(commonComponent.getPopupText(), expectedArticleNotCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding);

        newArticleModal.clearTitle();
        newArticleModal.enterBody("Lorem ipsum");
        newArticleModal.clickSaveButton();
        Assert.assertEquals(commonComponent.getPopupText(), expectedArticleNotCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding);
    }

    @Test
    public void TryAddArticleByNotLoggedUser(){
        LandingPage landingPage= new LandingPage(driver);
        landingPage.clickStartButton();
        Assert.assertFalse(navigationBar.isAddArticleButtonVisible());
    }

    @Test
    public void AddArticleWithTitleLongerThan128Characters(){

        //connection of registering with GUi and adding an article using API
        registerAndLogin();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setAllData(email, password);
        LoginApi loginApi = new LoginApi();
        var token = loginApi.getToken(loginRequest);
        ArticlesAPI articlesAPI = new ArticlesAPI();
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setAllNewArticleData(tooLongTitle, articleBody, Instant.now().toString(), articlePictureName);
        var response = articlesAPI.postArticle(articleRequest, token);
        Assert.assertEquals(response.getStatusCode(), 422);
        Assert.assertTrue(response.jsonPath().getString("error.message").contains(errorMessageText));
    }

    @Test
    public void AddArticleWithPolishAndSpecialCharacters(){
        registerAndLogin();
        ArticlesAPI articlesAPI = new ArticlesAPI();
        int numberOfArticlesBeforeAdding = articlesAPI.getNumberOfArticles(appUrl);

        navigationBar.clickArticlesPageButton();
        NewArticleModal newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(polishAndSpecialCharactersTitle, polishAndSpecialCharactersBody, articlePictureName);
        newArticleModal.clickSaveButton();
        Assert.assertEquals(commonComponent.getPopupText(), expectedArticleCreatedMessage);
        Assert.assertEquals(articlesAPI.getNumberOfArticles(appUrl), numberOfArticlesBeforeAdding +1);
    }
}
