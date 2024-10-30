package e2etests;

import api.models.UserRequest;
import guitests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LandingPage;
import pageobjects.MyAccountPage;
import testutils.ReusableData;
import testutils.TestDataGenerator;

import static testutils.ReusableData.flashpostCreatedMessage;

public class E2EGad1Test extends BaseTest {

    UserRequest user;
    String articleTitle;
    String articleBody;
    String commentText;
    String flashpostText;

    @BeforeClass(groups = "e2e")
    public void initData(){
        user = TestDataGenerator.generateUser();
        articleTitle = TestDataGenerator.generateArticleTitle();
        articleBody = TestDataGenerator.generateText(50);
        commentText = TestDataGenerator.generateText(25);
        flashpostText = TestDataGenerator.generateText(60);
    }

    @Test(groups = "e2e")
    public void testUserRegistration(){
        //Given
        var registrationPage = navigationBar.clickRegisterButton();

        //When
        String registrationInfo = registrationPage.registerWithAllFields(user.firstname(), user.lastname(), user.email(), user.birthDate(),
                user.password(), user.avatar());
        //Then
        Assert.assertEquals(registrationInfo, ReusableData.userCreatedExpectedMessage);
    }

    @Test(groups = "e2e", dependsOnMethods = "testUserRegistration")
    public void testUserLogin(){
        //Given
        var loginPage = navigationBar.clickLoginButton();

        //When
        loginPage.enterAllLoginData(user.email(), user.password());
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        //Then
        Assert.assertEquals(myAccountPage.getWelcomeText(), "Hi " + user.email() + "!");
    }

    @Test(groups = "e2e", dependsOnMethods = "testUserLogin")
    public void testArticleCreation(){
        //Given
        testUserLogin();
        var articleImage = ReusableData.articlePictureName;
        navigationBar.clickArticlesPageButton();

        //When
        var newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(articleTitle, articleBody, articleImage);
        newArticleModal.clickSaveButton();

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleCreatedMessage);
    }

    @Test(groups = "e2e", dependsOnMethods = "testArticleCreation")
    public void checkArticleSearch() throws InterruptedException {
        //Given
        testUserLogin();
        var articlesPage = navigationBar.clickArticlesPageButton();

        //When
        articlesPage.searchArticle(articleTitle);

        //Then
        Thread.sleep(500);
        Assert.assertEquals(articlesPage.returnNumberOfArticlesVisible(), 1);
        Assert.assertTrue(articlesPage.returnNamesOfArticles().contains(articleTitle));
    }

    @Test(groups = "e2e", dependsOnMethods = "checkArticleSearch")
    public void testCommentCreation(){
        //Given
        testUserLogin();
        var articlesPage = navigationBar.clickArticlesPageButton();
        var singleArticlePage = articlesPage.clickSeeMore(articleTitle);

        //When
        singleArticlePage.addComment(commentText);

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedCommentCreatedMessage);
        var commentsPage = navigationBar.clickCommentsPageButton();
        var singleComment = commentsPage.clickSeeMore(0);
        Assert.assertEquals(singleComment.getCommentText(), commentText);
    }

    @Test(groups = "e2e", dependsOnMethods = "testCommentCreation")
    public void testFlashpostsCreation(){
        //Given
        testUserLogin();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();

        //When
        flashpostsPage.createNewFlashpost(flashpostText, "#000000", true);

        //Then
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostCreatedMessage));
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname() + " " + user.lastname());
    }

    @Test(groups = "e2e", dependsOnMethods = "testFlashpostsCreation")
    public void verifyArticleVisibilityForNotLoggedInUser(){
        //When
        var landingPage = new LandingPage(driver);
        landingPage.clickStartButton();
        var articlesPage = navigationBar.clickArticlesPageButton();

        //Then
        Assert.assertEquals(articlesPage.returnUserOfArticle(0), user.firstname());
        Assert.assertEquals(articlesPage.returnTitleOfArticle(0), articleTitle);
    }

    @Test(groups = "e2e", dependsOnMethods = "verifyArticleVisibilityForNotLoggedInUser")
    public void verifyFlashpostVisibilityForNotLoggedInUser(){
        //When
        var landingPage = new LandingPage(driver);
        landingPage.clickStartButton();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();

        //Then
        Assert.assertEquals(flashpostsPage.getFlashpostText(0), flashpostText);
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname());
    }

}


