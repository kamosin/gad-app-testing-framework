package pageobjects.surveys.automationsurvey;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;
import pageobjects.surveys.TestSurveyBasePage;
import pageobjects.surveys.restapisurvey.TestTypes;

import java.util.List;
import java.util.Objects;

public class TestAutomationSurveyPage extends TestSurveyBasePage {

    WebDriver driver;
    CommonComponent commonComponent;

    public TestAutomationSurveyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='experience']")
    List<WebElement> automationExperienceRadioButton;
    @FindBy(css = "input[name='automatedTestsNumber']")
    List<WebElement> automationTestsNumber;
    @FindBy(id = "question-3")
    WebElement automationTestingToolsQuestion;
    @FindBy(id = "question-4")
    WebElement automationLanguagesQuestion;
    @FindBy(id = "cookies-infobar-reject")
    List<WebElement> cookiesRejectButton;

    public void answerAllQuestions(boolean automationTestingExperience, List<YearsOfExperience> automationExperience,
                                   List<TestAutomationTool> automationTools, List<ProgrammingLanguages> programmingLanguages,
                                   NumberOfAutomatedTests numberOfAutomatedTests,
                                   List<TestTypes> testTypes, String testingPlans) {
        startSurvey();
        chooseExperienceInAutomationTesting(automationTestingExperience);
        selectYearsOfExperienceInTestAutomation(automationExperience);
        selectAutomationTool(automationTools);
        selectProgrammingLanguages(programmingLanguages);
        selectNumberOfAutomatedTests(numberOfAutomatedTests);
        selectTestTypesInProject(testTypes);
        enterTestingPlans(testingPlans);
        clickSendAnswersButton();
    }

    public void chooseExperienceInAutomationTesting(boolean experienced) {
        commonComponent.waitForElementsToAppear(automationExperienceRadioButton);
        if (experienced) {
            automationExperienceRadioButton.getFirst().click();
        } else {
            automationExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void selectYearsOfExperienceInTestAutomation(List<YearsOfExperience> yearsOfExperience) {
        commonComponent.waitForElementsToAppear(checkbox);
        for (YearsOfExperience years : yearsOfExperience) {
            for (WebElement webCheckbox : checkbox) {
                if (Objects.equals(webCheckbox.getAttribute("value"), years.getStringName())) {
                    webCheckbox.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void selectAutomationTool(List<TestAutomationTool> tools) {
        commonComponent.waitForElementToAppear(automationTestingToolsQuestion);
        for (TestAutomationTool tool : tools) {
            for (WebElement check : checkbox) {
                if (Objects.equals(check.getAttribute("value"), tool.getStringName())) {
                    check.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void selectProgrammingLanguages(List<ProgrammingLanguages> languages) {
        commonComponent.waitForElementToAppear(automationLanguagesQuestion);
        for (ProgrammingLanguages language : languages) {
            for (WebElement check : checkbox) {
                if (Objects.equals(check.getAttribute("value"), language.getStringName())) {
                    check.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void selectNumberOfAutomatedTests(NumberOfAutomatedTests automatedTests) {
        commonComponent.waitForElementsToAppear(automationTestsNumber);
        for (WebElement frequency : automationTestsNumber) {
            if (Objects.equals(frequency.getAttribute("value"), automatedTests.getStringName())) {
                frequency.click();
            }
        }
        clickNextQuestionButton();
    }
}
