package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.surveys.SurveysPage;

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
    @FindBy(id = "btnSurveysLink")
    WebElement surveysButton;

    public String getWelcomeText(){
        commonComponent.waitForElementToAppear(welcomeElement);
        return welcomeElement.getText();
    }

    public SurveysPage clickSurveysButton(){
        commonComponent.waitForElementToAppear(surveysButton);
        surveysButton.click();
        return new SurveysPage(driver);
    }
}
