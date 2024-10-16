package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.decorators.RetryDecorator;
import utils.decorators.WaitDecorator;
import utils.decorators.WebElementActions;
import utils.decorators.WebElementDecorator;

public class RegistrationPage {

    WebDriver driver;
    CommonComponent commonComponent;
    NavigationBar navigationBar;
    private WebElementDecorator webElementActions;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        this.navigationBar = new NavigationBar(driver);
        PageFactory.initElements(driver, this);
        webElementActions = new WebElementActions();
        webElementActions = new RetryDecorator(new WaitDecorator(new WebElementActions(), driver), 3);

    }

    @FindBy(id="firstname")
    WebElement firstNameInput;
    @FindBy(id="lastname")
    WebElement lastNameInput;
    @FindBy(id="email")
    WebElement emailInput;
    @FindBy(id="datepicker")
    WebElement datepickerInput;
    @FindBy(id = "ui-datepicker-div")
    WebElement datePicker;
    @FindBy(css = ".ui-datepicker-close")
    WebElement datePickerDoneButton;
    @FindBy(id="password")
    WebElement passwordInput;
    @FindBy(xpath="//select[@id='avatar']")
    WebElement imageSelector;
    @FindBy(id="registerButton")
    WebElement registerButton;

    @FindBy(id="octavalidate_firstname")
    WebElement firstNameValidation;
    @FindBy(id="octavalidate_lastname")
    WebElement lastNameValidation;
    @FindBy(id="octavalidate_email")
    WebElement emailValidation;
    @FindBy(id="octavalidate_datepicker")
    WebElement dateValidation;
    @FindBy(id="octavalidate_password")
    WebElement passwordValidation;

    public boolean isFirstNameValidationTextVisible(String text){
        return firstNameValidation.isDisplayed() && firstNameValidation.getText().equals(text);
    }

    public boolean isLastNameValidationTextVisible(String text){
        return lastNameValidation.isDisplayed() && lastNameValidation.getText().equals(text);
    }

    public boolean isEmailValidationTextVisible(String text){
        return emailValidation.isDisplayed() && emailValidation.getText().equals(text);
    }

    public boolean isDateValidationTextVisible(String text){
        return dateValidation.isDisplayed() && dateValidation.getText().equals(text);
    }

    public boolean isPasswordValidationTextVisible(String text){
        return passwordValidation.isDisplayed() && passwordValidation.getText().equals(text);
    }

    public void enterFirstName(String firstName){
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }

    public void enterDate(String date){
        datepickerInput.sendKeys(date);
        webElementActions.click(datePickerDoneButton);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void selectImage(String value){
        commonComponent.waitForElementToAppear(imageSelector);
        Select imageSelect = new Select(imageSelector);
        imageSelect.selectByValue(value);
    }

    public void enterAllData(String firstName, String lastName,String email, String date, String password, String imageName){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterDate(date);
        enterPassword(password);
        selectImage(imageName);
    }

    public void clickRegisterButton(){
        commonComponent.waitForElementToDisappear(datePicker);
        webElementActions.click(registerButton);
    }

    public String registerWithAllFields(String firstName, String lastName, String email, String date,
                                         String password, String imageName) {
        navigationBar = new NavigationBar(driver);
        RegistrationPage registrationPage = navigationBar.clickRegisterButton();
        registrationPage.enterAllData(firstName, lastName, email, date,
                password, imageName);
        registrationPage.clickRegisterButton();
        return commonComponent.getPopupText();
    }

}
