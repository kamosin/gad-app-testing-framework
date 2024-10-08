package guitests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LandingPage;
import testutlis.TestDataGenerator;


import static testutlis.ReusableData.*;

public class FlashpostsTests extends BaseTest{

    @Test(groups = "gui")
    public void AddFlashpostWithCorrectData(){
        //Given
        var user = registerAndLogin();
        //When
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.enterFlashpostData("It is never too late to start IT!");
        newFlashpostModal.setBackgroundColor("#000000");
        newFlashpostModal.clickCreateButton();
        //Then
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostCreatedMessage));
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname() + " " + user.lastname());
    }

    @Test(groups = "gui")
    public void AddFlashpostWithTooLongMessage(){
        //When
        registerAndLogin();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.enterFlashpostData(TestDataGenerator.generateText(130));
        newFlashpostModal.setBackgroundColor("#000000");
        int length = newFlashpostModal.getFlashpostTextLength();
        newFlashpostModal.enterFlashpostData("Some more data");
        //Then
        Assert.assertEquals(newFlashpostModal.getFlashpostTextLength(), length);
    }

    @Test(groups = "gui")
    public void AddAFlashpostByNotLoggedInUser(){
        //When
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickStartButton();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.clickCreateButton();
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostNotEmptyMessage));
        newFlashpostModal.enterFlashpostData("It is a trap!");
        newFlashpostModal.setBackgroundColor("#dddddd");
        newFlashpostModal.clickCreateButton();

        //Then
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostNotCreatedMessage));
        newFlashpostModal.clickCancelButton();
        Assert.assertNotEquals(flashpostsPage.getNumberOfFlashposts(), 0);
    }

    @Test(groups = "gui")
    public void AddPublicFlashpostAndCheckByNonLoggedUser(){
        //Given
        var flashpostPublicMessage = TestDataGenerator.generateText(15);
        var user = registerAndLogin();
        //When
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.enterFlashpostData(flashpostPublicMessage);
        newFlashpostModal.clickCheckbox();
        newFlashpostModal.clickCreateButton();
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostCreatedMessage));
        navigationBar.clickLogoutButton();
        flashpostsPage = navigationBar.clickFlashpostsPageButton();
        //Then
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname());
        Assert.assertEquals(flashpostsPage.getFlashpostText(0), flashpostPublicMessage);
    }

    @Test(groups = "gui")
    public void AddNonPublicFlashpostAndCheckByNonLoggedUser(){
        //Given
        var flashpostPublicMessage = TestDataGenerator.generateText(15);
        var user = registerAndLogin();
        //When
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.enterFlashpostData(flashpostPublicMessage);
        newFlashpostModal.clickCreateButton();
        Assert.assertTrue(commonComponent.getSimpleAlertsText().contains(flashpostCreatedMessage));
        navigationBar.clickLogoutButton();
        flashpostsPage = navigationBar.clickFlashpostsPageButton();
        //Then
        Assert.assertNotEquals(flashpostsPage.getFlashpostAuthor(0), user.firstname());
        Assert.assertNotEquals(flashpostsPage.getFlashpostText(0), flashpostPublicMessage);
    }
}
