package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.articles.ArticlesPage;

public class LandingPage {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="btnGui")
    WebElement startButton;

    public void goToLandingPage(String url){
        driver.get(url);
    }

    public ArticlesPage clickStartButton(){
        startButton.click();
        return new ArticlesPage(driver);
    }
}
