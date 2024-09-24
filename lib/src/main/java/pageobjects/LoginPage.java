package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;
    CommonComponent commonComponent;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "username")
    WebElement emailInput;
    @FindBy(name = "password")
    WebElement passwordInput;
    @FindBy(id = "loginButton")
    WebElement loginButton;

    public void enterEmail(String email){
        commonComponent.waitForElementToAppear(emailInput);
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password){
        commonComponent.waitForElementToAppear(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void enterAllLoginData(String email, String password){
        enterEmail(email);
        enterPassword(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void login(String email, String password){
        enterAllLoginData(email, password);
        clickLoginButton();
    }


}
