package e2etests;

import guitests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.RegistrationPage;
import testutils.ReusableData;
import testutils.TestDataGenerator;

import static testutils.ReusableData.flashpostCreatedMessage;

public class E2EGad1Test extends BaseTest {

    @Test(groups = "e2e")
    public void test1() throws InterruptedException {
        //Given
        var registrationPage = new RegistrationPage(driver);
        var user = TestDataGenerator.generateUser();
        //When
        String registrationInfo = registrationPage.registerWithAllFields(user.firstname(), user.lastname(), user.email(), user.birthDate(),
                user.password(), user.avatar());
        //Then
        Assert.assertEquals(registrationInfo, ReusableData.userCreatedExpectedMessage);

        //When
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterAllLoginData(user.email(), user.password());
        loginPage.clickLoginButton();
        MyAccountPage myAccountPage = new MyAccountPage(driver);

        //Then
        Assert.assertEquals(myAccountPage.getWelcomeText(), "Hi " + user.email() + "!");

        //Given
        var articleTitle = TestDataGenerator.generateArticleTitle();
        var articleBody = TestDataGenerator.generateText(100);
        var articleImage = ReusableData.articlePictureName;
        navigationBar.clickArticlesPageButton();
        //When
        var newArticleModal = navigationBar.clickAddArticleButton();
        newArticleModal.enterAllData(articleTitle, articleBody, articleImage);
        newArticleModal.clickSaveButton();
        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedArticleCreatedMessage);

        //When
        var articlesPage = navigationBar.clickArticlesPageButton();
        articlesPage.searchArticle(articleTitle);
        //Then
        Thread.sleep(500);
        Assert.assertEquals(articlesPage.returnNumberOfArticlesVisible(), 1);
        Assert.assertTrue(articlesPage.returnNamesOfArticles().contains(articleTitle));

        //Given
        var singleArticlePage = articlesPage.clickSeeMore(articleTitle);
        var commentText=  TestDataGenerator.generateText(50);

        //When
        singleArticlePage.addComment(commentText);

        //Then
        Assert.assertEquals(commonComponent.getPopupText(), ReusableData.expectedCommentCreatedMessage);

        var commentsPage = navigationBar.clickCommentsPageButton();
        var singleComment = commentsPage.clickSeeMore(0);
        Assert.assertEquals(singleComment.getCommentText(), commentText);

        //Given
        var flashpostText = TestDataGenerator.generateText(40);
        //When
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        flashpostsPage.createNewFlashpost(flashpostText, "#000000", true);

        //Then
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostCreatedMessage));
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname() + " " + user.lastname());

        //When
        navigationBar.clickLogoutButton();
        articlesPage = navigationBar.clickArticlesPageButton();

        //Then
        Assert.assertEquals(articlesPage.returnUserOfArticle(0), user.firstname());
        Assert.assertEquals(articlesPage.returnTitleOfArticle(0), articleTitle);

        //When
        flashpostsPage = navigationBar.clickFlashpostsPageButton();

        //Then
        Assert.assertEquals(flashpostsPage.getFlashpostText(0), flashpostText);
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname());

    }
}
