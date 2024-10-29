package pageobjects.surveys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;
import pageobjects.surveys.automationsurvey.TestAutomationSurveyPage;
import pageobjects.surveys.restapisurvey.RestApiTestingSurveyPage;

import java.util.List;

public class SurveysPage {

    WebDriver driver;
    CommonComponent commonComponent;

    public SurveysPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[translateid='btnTakeSurvey']")
    List<WebElement> takeSurveyButtons;

    public RestApiTestingSurveyPage clickRestApiTakeSurveyButton(){
        takeSurveyButtons.getFirst().click();
        return new RestApiTestingSurveyPage(driver);
    }

    public TestAutomationSurveyPage clickTestAutomationSurveyButton(){
        takeSurveyButtons.getLast().click();
        return new TestAutomationSurveyPage(driver);
    }


}
