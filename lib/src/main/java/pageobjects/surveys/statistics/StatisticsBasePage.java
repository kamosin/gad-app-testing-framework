package pageobjects.surveys.statistics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;
import pageobjects.surveys.SurveysPage;

import java.util.List;

public abstract class StatisticsBasePage {

    WebDriver driver;
    CommonComponent commonComponent;

    public StatisticsBasePage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "rect[x*='205']")
    List<WebElement> experienceRectangle;
    @FindBy(css = "button[translateid='btnReturnToSurveys']")
    WebElement returnToSurveysButton;

    public SurveysPage clickReturnToSurveysButton(){
        returnToSurveysButton.click();
        return new SurveysPage(driver);
    }
}
