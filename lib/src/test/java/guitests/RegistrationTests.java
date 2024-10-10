package guitests;

import api.RequestManager;
import api.UserService;

import api.models.UserRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.RegistrationPage;
import testutils.ReusableData;
import testutils.TestDataGenerator;

public class RegistrationTests extends BaseTest {

    UserRequest user;

    @Test(groups = "gui")
    public void RegistrationAndLoggingTest() {
        //Given
        var registrationPage = new RegistrationPage(driver);
        user = TestDataGenerator.generateUser();
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
    }

    @Test(groups = "gui")
    public void RegistrationWithExistingUserEmail() {
        //Given
        user = TestDataGenerator.generateUser();
        var registrationPage = new RegistrationPage(driver);

        //When
        String registrationInfo = registrationPage.registerWithAllFields(user.firstname(), user.lastname(), user.email(), user.birthDate(),
                user.password(), user.avatar());
        Assert.assertEquals(registrationInfo, ReusableData.userCreatedExpectedMessage);
        registrationInfo = registrationPage.registerWithAllFields(user.firstname(), user.lastname(), user.email(), user.birthDate(),
                user.password(), user.avatar());

        //Then
        Assert.assertEquals(registrationInfo, ReusableData.emailNotUniqueExpectedMessage);
    }

    @Test(groups = "gui")
    public void RegistrationWithOnlyEmail() {
        //Given
        user = TestDataGenerator.generateUser();
        var registrationPage = navigationBar.clickRegisterButton();

        //When
        registrationPage.enterEmail(user.email());
        registrationPage.clickRegisterButton();

        //Then
        Assert.assertTrue(registrationPage.isPasswordValidationTextVisible(ReusableData.requiredFieldMessage) &&
                registrationPage.isLastNameValidationTextVisible(ReusableData.requiredFieldMessage) &&
                registrationPage.isFirstNameValidationTextVisible(ReusableData.requiredFieldMessage));
    }

    @Test(groups = "gui")
    public void RegistrationWithWrongFormatData(){
        //Given
        var requestManager = new RequestManager();
        var wrongFirstName = "John1";
        var wrongLastName = "Smith2";
        var wrongEmail = "john.smith@mail";
        var wrongDate = "12-12-1969";
        var userService = new UserService(requestManager);
        var numberOfUsersBeforeRegistration = userService.getNumberOfUsers(appUrl);

        //When
        var registrationPage = navigationBar.clickRegisterButton();
        registrationPage.enterAllData(wrongFirstName, wrongLastName, wrongEmail, wrongDate, TestDataGenerator.generatePassword(), ReusableData.userAvatar);
        registrationPage.clickRegisterButton();

        //Then
        Assert.assertTrue(registrationPage.isFirstNameValidationTextVisible(ReusableData.wrongFirstNameMessage) &&
                registrationPage.isLastNameValidationTextVisible(ReusableData.wrongLastNameMessage) &&
                registrationPage.isEmailValidationTextVisible(ReusableData.wrongEmailMessage) &&
                registrationPage.isDateValidationTextVisible(ReusableData.wrongDateMessage));
        Assert.assertEquals(userService.getNumberOfUsers(appUrl), numberOfUsersBeforeRegistration);
    }

}
