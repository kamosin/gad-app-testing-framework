import API.UsersAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.RegistrationPage;

public class RegistrationTests extends BaseTest {

    private static final String firstName = "John";
    private static final String lastName = "Smith";
    private static final String email = "john.smith@mail.com";
    private static final String date = "1960-12-12";
    private static final String password = "pass";
    private static final String imageName = "0797eae7-5f95-4985-8ac8-10c58e17c769.jpg";
    private static final String userCreatedExpectedText = "User created";
    private static final String emailNotUniqueExpectedText = "User not created! Email not unique";
    private static final String requiredFieldMessage  = "This field is required";

    private static final String wrongFirstName = "John1";
    private static final String wrongLastName = "Smith2";
    private static final String wrongEmail = "john.smith@mail";
    private static final String wrongDate = "12-12-1969";

    private static final String wrongFirstNameLastNameMessage = "Please enter only Letters!";
    private static final String wrongEmailMessage = "Please provide a valid email address";
    private static final String wrongDateMessage = "Date must be in format YYYY-MM-DD";
    RegistrationPage registrationPage;

    @Test
    public void RegistrationAndLoggingTest() {
        registrationPage = new RegistrationPage(driver);
        String registrationInfo = registrationPage.registerWithAllFields(firstName, lastName, email, date,
                password, imageName);
        Assert.assertEquals(registrationInfo, userCreatedExpectedText);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterAllLoginData(email, password);
        loginPage.clickLoginButton();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        Assert.assertEquals(myAccountPage.getWelcomeText(), "Hi " + email + "!");
    }

    @Test
    public void RegistrationWithExistingUserEmail() {
        registrationPage = new RegistrationPage(driver);
        String registrationInfo = registrationPage.registerWithAllFields(firstName, lastName, email, date,
                password, imageName);
        Assert.assertEquals(registrationInfo, userCreatedExpectedText);

        registrationInfo = registrationPage.registerWithAllFields(firstName, lastName, email, date,
                password, imageName);
        Assert.assertEquals(registrationInfo, emailNotUniqueExpectedText);
    }

    @Test
    public void RegistrationWithOnlyEmail() {
        registrationPage = navigationBar.clickRegisterButton();
        registrationPage.enterEmail(email);
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isPasswordValidationTextVisible(requiredFieldMessage) &&
                registrationPage.isLastNameValidationTextVisible(requiredFieldMessage) &&
                registrationPage.isFirstNameValidationTextVisible(requiredFieldMessage));
    }

    @Test
    public void RegistrationWithWrongFormatData(){
        UsersAPI usersAPI = new UsersAPI();
        int numberOfUsersBeforeRegistration = usersAPI.getNumberOfUsers(appUrl);
        registrationPage = navigationBar.clickRegisterButton();
        registrationPage.enterAllData(wrongFirstName, wrongLastName, wrongEmail, wrongDate, password, imageName);
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isFirstNameValidationTextVisible(wrongFirstNameLastNameMessage) &&
                registrationPage.isLastNameValidationTextVisible(wrongFirstNameLastNameMessage) &&
                registrationPage.isEmailValidationTextVisible(wrongEmailMessage) &&
                registrationPage.isDateValidationTextVisible(wrongDateMessage));
        Assert.assertEquals(usersAPI.getNumberOfUsers(appUrl), numberOfUsersBeforeRegistration);
    }

}
