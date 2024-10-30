package pageobjects.surveys.restapisurvey;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;
import pageobjects.surveys.SurveysPage;
import pageobjects.surveys.TestSurveyBasePage;

import java.util.List;
import java.util.Objects;

public class RestApiTestingSurveyPage extends TestSurveyBasePage {

    WebDriver driver;
    CommonComponent commonComponent;

    public RestApiTestingSurveyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='experience']")
    List<WebElement> manualRestExperienceRadioButton;
    @FindBy(id = "question-5")
    WebElement cicdQuestion;
    @FindBy(css = "input[name='cicd']")
    List<WebElement> cicdExperienceRadioButton;
    @FindBy(css = "input[name='newman']")
    List<WebElement> newmanExperienceRadioButton;
    @FindBy(css = "input[name='frequency']")
    List<WebElement> frequencyRadioButton;

    public void answerAllQuestions(boolean manualTestingExperience, List<ManualTestingTool> manualTestingTools,
                                   boolean newmanExperience, boolean ciCdExperience, List<CiCdTool> ciCdTools,
                                   FrequencyTesting manualTestingFrequency, List<TestTypes> testTypes, String testingPlans) {
        startSurvey();
        chooseExperienceInManualRestApiTesting(manualTestingExperience);
        selectManualTestingTool(manualTestingTools);
        chooseExperienceInNewman(newmanExperience);
        chooseExperienceInCiCd(ciCdExperience);
        selectCiCdTool(ciCdTools);
        selectFrequencyOfManualTesting(manualTestingFrequency);
        selectTestTypesInProject(testTypes);
        enterTestingPlans(testingPlans);
        clickSendAnswersButton();
    }

    public void chooseExperienceInManualRestApiTesting(boolean experienced) {
        commonComponent.waitForElementsToAppear(manualRestExperienceRadioButton);
        if (experienced) {
            manualRestExperienceRadioButton.getFirst().click();
        } else {
            manualRestExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void selectManualTestingTool(List<ManualTestingTool> tools) {
        commonComponent.waitForElementsToAppear(checkbox);
        for (ManualTestingTool tool : tools) {
            for (WebElement check : checkbox) {
                if (Objects.equals(check.getAttribute("value"), tool.getStringName())) {
                    check.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void chooseExperienceInNewman(boolean experienced) {
        commonComponent.waitForElementsToAppear(newmanExperienceRadioButton);
        if (experienced) {
            newmanExperienceRadioButton.getFirst().click();
        } else {
            newmanExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void chooseExperienceInCiCd(boolean experienced) {
        commonComponent.waitForElementsToAppear(cicdExperienceRadioButton);
        if (experienced) {
            cicdExperienceRadioButton.getFirst().click();
        } else {
            cicdExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void selectCiCdTool(List<CiCdTool> tools) {
        commonComponent.waitForElementToAppear(cicdQuestion);
        for (CiCdTool tool : tools) {
            for (WebElement check : checkbox) {
                if (Objects.equals(check.getAttribute("value"), tool.getStringName())) {
                    check.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void selectFrequencyOfManualTesting(FrequencyTesting frequencyTesting) {
        commonComponent.waitForElementsToAppear(frequencyRadioButton);
        for (WebElement frequency : frequencyRadioButton) {
            if (Objects.equals(frequency.getAttribute("value"), frequencyTesting.getStringName())) {
                frequency.click();
            }
        }
        clickNextQuestionButton();
    }
}
