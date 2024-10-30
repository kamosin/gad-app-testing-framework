package pageobjects.surveys;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;
import pageobjects.surveys.restapisurvey.TestTypes;

import java.util.List;
import java.util.Objects;

public abstract class TestSurveyBasePage {

    WebDriver driver;
    CommonComponent commonComponent;

    public TestSurveyBasePage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "buttonStart")
    protected WebElement startSurveyButton;
    @FindBy(id = "buttonNext")
    protected List<WebElement> nextButton;
    @FindBy(xpath = "//input[@type='checkbox']")
    protected List<WebElement> checkbox;
    @FindBy(css = ".body")
    protected WebElement testingPlansTextArea;
    @FindBy(id = "buttonFinish")
    protected WebElement sendAnswersButton;
    @FindBy(id = "question-100")
    protected WebElement testTypesQuestion;
    @FindBy(css = ".button-primary.button-mobile")
    protected WebElement returnToSurveysButton;
    @FindBy(id = "cookies-infobar-reject")
    protected List<WebElement> cookiesRejectButton;

    public void startSurvey() {
        startSurveyButton.click();
    }

    public void clickNextQuestionButton() {
        nextButton.getLast().click();
    }

    public SurveysPage clickReturnToSurveysButton(){
        returnToSurveysButton.click();
        return new SurveysPage(driver);
    }

    public void selectTestTypesInProject(List<TestTypes> types) {
        scrollModal();
        if(isRejectButtonVisible()){
            clickRejectCookiesButton();
        }
        commonComponent.waitForElementToAppear(testTypesQuestion);
        for (TestTypes type : types) {
            for (WebElement checkbox : checkbox) {
                if (Objects.equals(checkbox.getAttribute("value"), type.getStringName())) {
                    checkbox.click();
                }
            }
        }
        scrollModal();
        clickNextQuestionButton();
    }

    public boolean isRejectButtonVisible() {
        return !cookiesRejectButton.isEmpty();
    }

    public void enterTestingPlans(String text) {
        scrollModal();
        commonComponent.waitForElementToAppear(testingPlansTextArea);
        testingPlansTextArea.sendKeys(text);
        clickNextQuestionButton();
    }

    public void clickSendAnswersButton() {
        scrollModal();
        commonComponent.waitForElementToAppear(sendAnswersButton);
        sendAnswersButton.click();
    }

    public void scrollModal() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void clickRejectCookiesButton() {
        commonComponent.waitForElementsToAppear(cookiesRejectButton);
        cookiesRejectButton.getFirst().click();
    }


}
