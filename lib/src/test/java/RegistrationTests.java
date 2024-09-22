import API.UsersAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;
import pageobjects.NavigationBar;
import pageobjects.RegistrationPage;

public class RegistrationTests extends BaseTest {

    String firstName = "John";
    String lastName = "Smith";
    String email = "john.smith@mail.com";
    String date = "1960-12-12";
    String password = "pass";
    String imageName = "0797eae7-5f95-4985-8ac8-10c58e17c769.jpg";
    String userCreatedExpectedText = "User created";
    String emailNotUniqueExpectedText = "User not created! Email not unique";
    String requiredFieldMessage  = "This field is required";

    String wrongFirstName = "John1";
    String wrongLastName = "Smith2";
    String wrongEmail = "john.smith@mail";
    String wrongDate = "12-12-1969";

    String wrongFirstNameLastNameMessage = "Please enter only Letters!";
    String wrongEmailMessage = "Please provide a valid email address";
    String wrongDateMessage = "Date must be in format YYYY-MM-DD";


    NavigationBar navigationBar;

    private String registerWithAllFields(String firstName, String lastName, String email, String date,
                                         String password, String imageName) {
        navigationBar = new NavigationBar(driver);
        RegistrationPage registrationPage = navigationBar.clickRegisterButton();
        registrationPage.enterAllData(firstName, lastName, email, date,
                password, imageName);
        registrationPage.clickRegisterButton();
        return registrationPage.getPopupText();
    }

    @Test
    public void RegistrationAndLoggingTest() {
        String registrationInfo = registerWithAllFields(firstName, lastName, email, date,
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
        String registrationInfo = registerWithAllFields(firstName, lastName, email, date,
                password, imageName);
        Assert.assertEquals(registrationInfo, userCreatedExpectedText);

        registrationInfo = registerWithAllFields(firstName, lastName, email, date,
                password, imageName);
        Assert.assertEquals(registrationInfo, emailNotUniqueExpectedText);
    }

    @Test
    public void RegistrationWithOnlyEmail() {
        navigationBar = new NavigationBar(driver);
        RegistrationPage registrationPage = navigationBar.clickRegisterButton();
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
        navigationBar = new NavigationBar(driver);
        RegistrationPage registrationPage = navigationBar.clickRegisterButton();
        registrationPage.enterAllData(wrongFirstName, wrongLastName, wrongEmail, wrongDate, password, imageName);
        registrationPage.clickRegisterButton();
        Assert.assertTrue(registrationPage.isFirstNameValidationTextVisible(wrongFirstNameLastNameMessage) &&
                registrationPage.isLastNameValidationTextVisible(wrongFirstNameLastNameMessage) &&
                registrationPage.isEmailValidationTextVisible(wrongEmailMessage) &&
                registrationPage.isDateValidationTextVisible(wrongDateMessage));
        Assert.assertEquals(usersAPI.getNumberOfUsers(appUrl), numberOfUsersBeforeRegistration);
    }

}
