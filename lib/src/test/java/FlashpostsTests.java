import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import pageobjects.RegistrationPage;

public class FlashpostsTests extends BaseTest{

    private static final String firstName = "John";
    private static final String lastName = "Smith";
    private static final String email = "john.smith@mail.com";
    private static final String date = "1960-12-12";
    private static final String password = "pass";
    private static final String imageName = "0797eae7-5f95-4985-8ac8-10c58e17c769.jpg";

    private static final String flashpostCreatedMessage = "Flashpost created successfully";
    private static final String flashpostNotCreatedMessage = "You can't create this flashpost.";
    private static final String flashpostNotEmptyMessage = "Flashpost can't be empty";
    private static final String flashpostLongMessage = "Automatyzuj testy z Playwright i Git! Zwiększ efektywność, wdrażaj szybciej i poprawiaj jakość kodu. Przyszłość IT w twoich rękach!";


    public void registerAndLogin(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithAllFields(firstName, lastName, email, date, password, imageName);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
    }

    @Test
    public void AddFlashpostWithCorrectData() throws InterruptedException {
        registerAndLogin();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.enterFlashpostData("It is never too late to start IT!");
        newFlashpostModal.setBackgroundColor("#000000");
        newFlashpostModal.clickCreateButton();
        Assert.assertEquals(commonComponent.getSimpleAlertText(), flashpostCreatedMessage);
        Assert.assertEquals(flashpostsPage.getFlashpostAuthor(0), firstName + " " + lastName);
    }

    @Test
    public void AddFlashpostWithTooLongMessage(){
        registerAndLogin();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.enterFlashpostData(flashpostLongMessage);
        newFlashpostModal.setBackgroundColor("#000000");
        int length = newFlashpostModal.getFlashpostTextLength();
        newFlashpostModal.enterFlashpostData("Some more data");
        Assert.assertEquals(newFlashpostModal.getFlashpostTextLength(), length);
        //TODO add API assertions
    }

    @Test
    public void AddAFlashpostByNotLoggedInUser(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickStartButton();
        var flashpostsPage = navigationBar.clickFlashpostsPageButton();
        var newFlashpostModal = flashpostsPage.clickCreateFlashpostsButton();
        newFlashpostModal.clickCreateButton();
        Assert.assertEquals(commonComponent.getSimpleAlertText(), flashpostNotEmptyMessage);
        commonComponent.waitForSimpleAlertToDisappear();
        newFlashpostModal.enterFlashpostData("It is a trap!");
        newFlashpostModal.setBackgroundColor("#dddddd");
        newFlashpostModal.clickCreateButton();
        //problems with checking text of next simple alert
        Assert.assertEquals(commonComponent.getSimpleAlertText(), flashpostNotCreatedMessage);
        newFlashpostModal.clickCancelButton();
        Assert.assertNotEquals(flashpostsPage.getNumberOfFlashposts(), 0);
    }
}
