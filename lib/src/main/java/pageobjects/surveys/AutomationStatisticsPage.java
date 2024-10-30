package pageobjects.surveys;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;

import java.util.List;

public class AutomationStatisticsPage extends StatisticsBasePage{

    WebDriver driver;
    CommonComponent commonComponent;

    public AutomationStatisticsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@x='0']")
    List<WebElement> separateCharts;

    By numberInChart = By.xpath("..//*[@text-anchor='end']");

    public int getAutomationExperienceNumber(){
        return separateCharts.getFirst().findElements(numberInChart).size()-1;
    }


}
