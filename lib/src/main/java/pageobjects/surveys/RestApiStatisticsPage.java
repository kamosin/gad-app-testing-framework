package pageobjects.surveys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;


import java.util.Objects;

public class RestApiStatisticsPage extends StatisticsBasePage{

    WebDriver driver;
    CommonComponent commonComponent;

    public RestApiStatisticsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    public int getRestApiExperienceRectangleHeight(){
        return Integer.parseInt(Objects.requireNonNull(experienceRectangle.getFirst().getAttribute("height")));
    }

    public int getCiCdExperienceRectangleHeight(){
        return Integer.parseInt(Objects.requireNonNull(experienceRectangle.getLast().getAttribute("height")));
    }
}
