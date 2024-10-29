package e2etests;

import guitests.BaseTest;
import org.testng.annotations.Test;
import pageobjects.MyAccountPage;
import pageobjects.surveys.automationsurvey.NumberOfAutomatedTests;
import pageobjects.surveys.automationsurvey.ProgrammingLanguages;
import pageobjects.surveys.automationsurvey.TestAutomationTool;
import pageobjects.surveys.automationsurvey.YearsOfExperience;
import pageobjects.surveys.restapisurvey.*;
import testutils.TestDataGenerator;

import java.util.List;

public class SurveysTests extends BaseTest {

    @Test(groups = "e2e")
    public void surveysTest1() {
        registerAndLogin();
        var myAccountPage = new MyAccountPage(driver);
        var surveysPage = myAccountPage.clickSurveysButton();
        var restApiTestingPage = surveysPage.clickRestApiTakeSurveyButton();

        restApiTestingPage.answerAllQuestions(true, List.of(ManualTestingTool.Swagger, ManualTestingTool.Postman),
                true, true, List.of(CiCdTool.GithubActions), FrequencyTesting.Weekly,
                List.of(TestTypes.E2eTests, TestTypes.AccessibilityTests), TestDataGenerator.generateText(25));

        surveysPage = restApiTestingPage.clickReturnToSurveysButton();
        var automationTestingPage = surveysPage.clickTestAutomationSurveyButton();
        automationTestingPage.answerAllQuestions(true, List.of(YearsOfExperience.fiveOrMoreYears),
                List.of(TestAutomationTool.Playwright, TestAutomationTool.Selenium), List.of(ProgrammingLanguages.Java, ProgrammingLanguages.JavaScript),
                NumberOfAutomatedTests.FiftyToOneHundred, List.of(TestTypes.E2eTests, TestTypes.AccessibilityTests), TestDataGenerator.generateText(35));


    }
}
