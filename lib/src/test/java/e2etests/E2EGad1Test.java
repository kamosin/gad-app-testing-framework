package e2etests;

import api.models.UserRequest;
import guitests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.RegistrationPage;
import pageobjects.articles.ArticlesPage;
import pageobjects.flashposts.FlashpostsPage;
import testutils.ReusableData;
import testutils.TestDataGenerator;

import static testutils.ReusableData.flashpostCreatedMessage;

public class E2EGad1Test extends BaseTest {

    @Test(groups = "e2e")
    public void test1() throws InterruptedException {
        //Given
        var user = TestDataGenerator.generateUser();
        var articleTitle = TestDataGenerator.generateArticleTitle();
        var articleBody = TestDataGenerator.generateText(100);
        var commentText=  TestDataGenerator.generateText(50);
        var flashpostText = TestDataGenerator.generateText(40);

        testUserRegistration(user);
        testUserLogin(user);
        testArticleCreation(articleTitle, articleBody);
        var articlesPage = checkArticleSearch(articleTitle);
        testCommentCreation(articlesPage, articleTitle, commentText);
        testFlashpostsCreation(user, flashpostText);
        verifyArticleVisibilityForNotLoggedInUser(user, articleTitle);
        verifyFlashpostVisibilityForNotLoggedInUser(user, flashpostText);
    }

    @Test(groups = "e2e")
    public UserRequest testUserRegistration(UserRequest user){
        //Given
        var registrationPage = new RegistrationPage(driver);

        //When
        String registrationInfo = registrationPage.registerWithAllFields(user.firstname(), user.lastname(), user.email(), user.birthDate(),
                user.password(), user.avatar());
        //Then
        Assert.assertEquals(registrationInfo, ReusableData.userCreatedExpectedMessage);
        return user;
    }

    @Test
    public void testUserLogin(UserRequest user){
        //Given
        LoginPage loginPage = new LoginPage(driver);

        //When
        loginPage.enterAllLoginData(user.email(), user.password());
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        //Then
        Assert.assertEquals(myAccountPage.getWelcomeText(), "Hi " + user.email() + "!");
    }

    @Test
    public String testArticleCreation(String articleTitle, String articleBody){
        //Given
        var articleImage = ReusableData.articlePictureName;
        navigationBar.clickArticlesPageButton();

        //When
        var newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(articleTitle, articleBody, articleImage);
        newArticleModal.clickSaveButton();

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleCreatedMessage);
        return articleTitle;
    }

    @Test
    public ArticlesPage checkArticleSearch(String articleTitle) throws InterruptedException {
        //Given
        var articlesPage = navigationBar.clickArticlesPageButton();

        //When
        articlesPage.searchArticle(articleTitle);

        //Then
        Thread.sleep(500);
        Assert.assertEquals(articlesPage.returnNumberOfArticlesVisible(), 1);
        Assert.assertTrue(articlesPage.returnNamesOfArticles().contains(articleTitle));

        return articlesPage;
    }

    @Test
    public void testCommentCreation(ArticlesPage articlesPage, String articleTitle, String commentText){
        //Given
        var singleArticlePage = articlesPage.clickSeeMore(articleTitle);

        //When
        singleArticlePage.addComment(commentText);

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedCommentCreatedMessage);
        var commentsPage = navigationBar.clickCommentsPageButton();
        var singleComment = commentsPage.clickSeeMore(0);
        Assert.assertEquals(singleComment.getCommentText(), commentText);
    }

    @Test
    public FlashpostsPage testFlashpostsCreation(UserRequest user, String flashpostText){
        //Given
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();

        //When
        flashpostsPage.createNewFlashpost(flashpostText, "#000000", true);

        //Then
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostCreatedMessage));
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname() + " " + user.lastname());

        return flashpostsPage;
    }

    @Test
    public void verifyArticleVisibilityForNotLoggedInUser(UserRequest user, String articleTitle){
        //When
        navigationBar.clickLogoutButton();
        var articlesPage = navigationBar.clickArticlesPageButton();

        //Then
        Assert.assertEquals(articlesPage.returnUserOfArticle(0), user.firstname());
        Assert.assertEquals(articlesPage.returnTitleOfArticle(0), articleTitle);
    }

    @Test
    public void verifyFlashpostVisibilityForNotLoggedInUser(UserRequest user, String flashpostText){
        //When
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();

        //Then
        Assert.assertEquals(flashpostsPage.getFlashpostText(0), flashpostText);
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname());
    }

}


