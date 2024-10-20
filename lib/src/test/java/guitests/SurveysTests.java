package guitests;

import org.testng.annotations.Test;
import pageobjects.MyAccountPage;

public class SurveysTests extends BaseTest{

    @Test
    public void surveysTest1(){
        registerAndLogin();
        var myAccountPage = new MyAccountPage(driver);
        var surveysPage = myAccountPage.clickSurveysButton();
        surveysPage.clickTestAutomationSurveyButton();
        System.out.println("xxxx");
    }
}
