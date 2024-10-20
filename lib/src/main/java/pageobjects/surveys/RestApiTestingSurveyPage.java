package pageobjects.surveys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.CommonComponent;

public class RestApiTestingSurveyPage {

    WebDriver driver;
    CommonComponent commonComponent;

    public RestApiTestingSurveyPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
    }

    @FindBy(id="buttonStart")
    WebElement startSurveyButton;

    
}
