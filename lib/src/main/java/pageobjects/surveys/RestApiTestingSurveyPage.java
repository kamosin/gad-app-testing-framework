package pageobjects.surveys;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;

import java.util.List;
import java.util.Objects;

public class RestApiTestingSurveyPage {

    public enum ManualTestingTool{
        Swagger("Swagger"),
        Postman("Postman"),
        Bruno("Bruno"),
        DevTools("Dev Tools"),
        Other("Other");

        final String tool;

        public String getStringName(){
            return this.tool;
        }

        ManualTestingTool(String tool) {
            this.tool = tool;
        }
    }

    public enum CiCdTool{
        GithubActions("GitHub Actions"),
        Jenkins("Jenkins"),
        AzureDevops("Azure DevOps "),
        GitlabCI("GitLab CI"),
        TeamCity("TeamCity"),
        CircleCI("Circle CI"),
        other("other");

        final String ciTool;

        public String getStringName(){
            return this.ciTool;
        }

        CiCdTool(String ciTool) {
            this.ciTool = ciTool;
        }
    }

    public enum FrequencyTesting{
        Daily("daily"),
        Weekly("weekly"),
        Monthly("monthly"),
        Never("never");

        final String testingFrequency;

        public String getStringName(){
            return this.testingFrequency;
        }

        FrequencyTesting(String frequency) {
            this.testingFrequency = frequency;
        }
    }

    public enum TestTypes{
        UnitTests("Unit tests"),
        ModuleTests("Module tests"),
        ContractTests("Contract tests"),
        IntegrationTests("Integration tests"),
        E2eTests("E2e tests"),
        UatTests(" UAT tests"),
        PerformanceTests("Performance tests"),
        SecurityTests("Security tests"),
        VisualTests("Visual tests"),
        AccessibilityTests("Accessibility tests"),
        UsabilityTests("Usability tests"),
        SmokeTests("Smoke tests"),
        SanityTests("Sanity tests"),
        RegressionTests("Regression tests"),
        AdHocTests("Ad-hoc tests"),
        ExploratoryTests("Exploratory tests"),
        MutationTests("Mutation tests"),
        FuzzTests("Fuzz tests"),
        ChaosTests("Chaos tests"),
        other("other ");

        final String testType;


        TestTypes(String testType) {
            this.testType = testType;
        }

        public String getStringName(){
            return this.testType;
        }
    }

    WebDriver driver;
    CommonComponent commonComponent;

    public RestApiTestingSurveyPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="buttonStart")
    WebElement startSurveyButton;
    @FindBy(id="buttonNext")
    List<WebElement> nextButton;
    @FindBy(css = "input[name='experience']")
    List<WebElement> manualRestExperienceRadioButton;
    @FindBy(xpath="//input[@type='checkbox']")
    List<WebElement> toolCheckbox;
    @FindBy(id = "question-5")
    WebElement cicdQuestion;
    @FindBy(css = "input[name='cicd']")
    List<WebElement> cicdExperienceRadioButton;
    @FindBy(css = "input[name='newman']")
    List<WebElement> newmanExperienceRadioButton;
    @FindBy(css = "input[name='frequency']")
    List<WebElement> frequencyRadioButton;
    @FindBy(css = ".body")
    WebElement testingPlansTextArea;
    @FindBy(id = "buttonFinish")
    WebElement sendAnswersButton;
    @FindBy(id = "question-100")
    WebElement testTypesQuestion;
    @FindBy(css = ".button-primary.button-mobile")
    WebElement returnToSurveysButton;
    @FindBy(id="cookies-infobar-reject")
    WebElement cookiesRejectButton;

    public void startRestApiSurvey(){
        startSurveyButton.click();
    }

    public void clickNextQuestionButton(){
        nextButton.getLast().click();
    }

    public void chooseExperienceInManualRestApiTesting(boolean experienced){
        commonComponent.waitForElementsToAppear(manualRestExperienceRadioButton);
        if(experienced){
            manualRestExperienceRadioButton.getFirst().click();
        }
        else {
            manualRestExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void selectManualTestingTool(ManualTestingTool... tools){
        commonComponent.waitForElementsToAppear(toolCheckbox);
        for(ManualTestingTool tool : tools){
            for(WebElement checkbox: toolCheckbox){
                if(Objects.equals(checkbox.getAttribute("value"), tool.getStringName())){
                    checkbox.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void chooseExperienceInNewman(boolean experienced){
        commonComponent.waitForElementsToAppear(newmanExperienceRadioButton);
        if(experienced){
            newmanExperienceRadioButton.getFirst().click();
        }
        else {
            newmanExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void chooseExperienceInCiCd(boolean experienced){
        commonComponent.waitForElementsToAppear(cicdExperienceRadioButton);
        if(experienced){
            cicdExperienceRadioButton.getFirst().click();
        }
        else {
            cicdExperienceRadioButton.getLast().click();
        }
        clickNextQuestionButton();
    }

    public void selectCiCdTool(CiCdTool... tools){
        commonComponent.waitForElementToAppear(cicdQuestion);
        for(CiCdTool tool : tools){
            for(WebElement checkbox: toolCheckbox){
                if(Objects.equals(checkbox.getAttribute("value"), tool.getStringName())){
                    checkbox.click();
                }
            }
        }
        clickNextQuestionButton();
    }

    public void selectFrequencyOfManualTesting(FrequencyTesting frequencyTesting){
        commonComponent.waitForElementsToAppear(frequencyRadioButton);
        for(WebElement frequency: frequencyRadioButton){
            if(Objects.equals(frequency.getAttribute("value"), frequencyTesting.getStringName())){
                frequency.click();
            }
        }
        clickNextQuestionButton();
    }

    public void selectTestTypesInProject(TestTypes... types){
        scrollModal();
        commonComponent.waitForElementToAppear(testTypesQuestion);
        for(TestTypes type : types){
            for(WebElement checkbox: toolCheckbox){
                if(Objects.equals(checkbox.getAttribute("value"), type.getStringName())){
                    checkbox.click();
                }
            }
        }
        scrollModal();
        clickNextQuestionButton();
    }

    public void enterTestingPlans(String text){
        scrollModal();
        commonComponent.waitForElementToAppear(testingPlansTextArea);
        testingPlansTextArea.sendKeys(text);
        clickNextQuestionButton();
    }

    public void clickSendAnswersButton(){
        scrollModal();
        clickRejectCookiesButton();
        commonComponent.waitForElementToAppear(sendAnswersButton);
        sendAnswersButton.click();
    }

    private void scrollModal() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void clickRejectCookiesButton(){
        commonComponent.waitForElementToAppear(cookiesRejectButton);
        cookiesRejectButton.click();
    }
}
