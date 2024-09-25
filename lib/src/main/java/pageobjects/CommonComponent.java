package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonComponent {

    WebDriver driver;

    By alertPopup = By.id("alertPopup");
    By simpleAlert = By.id("simple-alert");

    public CommonComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(By element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForSimpleAlertToDisappear(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(simpleAlert));
    }

    public String getPopupText(){
        waitForElementToAppear(alertPopup);
        WebElement popup = driver.findElement(alertPopup);
        if(popup.isDisplayed()){
            return popup.getText();
        }
        else return "Popup not displayed";
    }

    public String getSimpleAlertText(){
        waitForElementToAppear(simpleAlert);
        WebElement popup = driver.findElement(simpleAlert);
        if(popup.isDisplayed()){
            return popup.getText();
        }
        else return "Popup not displayed";
    }
}
