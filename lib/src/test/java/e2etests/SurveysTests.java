package e2etests;

import guitests.BaseTest;
import org.testng.annotations.Test;
import pageobjects.MyAccountPage;
import pageobjects.surveys.RestApiTestingSurveyPage;
import testutils.TestDataGenerator;

public class SurveysTests extends BaseTest {

    @Test
    public void surveysTest1() {
        registerAndLogin();
        var myAccountPage = new MyAccountPage(driver);
        var surveysPage = myAccountPage.clickSurveysButton();
        var restApiTestingPage = surveysPage.clickRestApiTakeSurveyButton();
        restApiTestingPage.startRestApiSurvey();
        restApiTestingPage.chooseExperienceInManualRestApiTesting(true);
        restApiTestingPage.selectManualTestingTool(RestApiTestingSurveyPage.ManualTestingTool.Swagger, RestApiTestingSurveyPage.ManualTestingTool.DevTools, RestApiTestingSurveyPage.ManualTestingTool.Postman);
        restApiTestingPage.chooseExperienceInNewman(true);
        restApiTestingPage.chooseExperienceInCiCd(true);
        restApiTestingPage.selectCiCdTool(RestApiTestingSurveyPage.CiCdTool.GithubActions, RestApiTestingSurveyPage.CiCdTool.TeamCity);
        restApiTestingPage.selectFrequencyOfManualTesting(RestApiTestingSurveyPage.FrequencyTesting.Daily);
        restApiTestingPage.selectTestTypesInProject(RestApiTestingSurveyPage.TestTypes.AccessibilityTests, RestApiTestingSurveyPage.TestTypes.AdHocTests,
                RestApiTestingSurveyPage.TestTypes.ExploratoryTests, RestApiTestingSurveyPage.TestTypes.E2eTests);
        restApiTestingPage.enterTestingPlans(TestDataGenerator.generateText(25));
        restApiTestingPage.clickSendAnswersButton();
    }
}
