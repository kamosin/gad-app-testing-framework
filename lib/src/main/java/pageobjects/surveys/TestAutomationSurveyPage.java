package pageobjects.surveys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;

public class TestAutomationSurveyPage {

    WebDriver driver;
    CommonComponent commonComponent;

    public TestAutomationSurveyPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }


}
