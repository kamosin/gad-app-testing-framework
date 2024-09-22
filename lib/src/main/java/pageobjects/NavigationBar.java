package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public class NavigationBar {
    WebDriver driver;

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="avatar")
    WebElement avatarIcon;
    @FindBy(id="registerBtn")
    WebElement registerButton;

    public RegistrationPage clickRegisterButton(){
        Actions hoverOverElement = new Actions(driver);
        hoverOverElement.moveToElement(avatarIcon).perform();
        registerButton.click();
        return new RegistrationPage(driver);
    }
}
