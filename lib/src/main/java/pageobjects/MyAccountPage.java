package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    WebDriver driver;
    CommonComponent commonComponent;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2[data-testid='hello']")
    WebElement welcomeElement;

    public String getWelcomeText(){
        commonComponent.waitForElementToAppear(welcomeElement);
        return welcomeElement.getText();
    }
}
