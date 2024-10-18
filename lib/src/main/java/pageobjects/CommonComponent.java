package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CommonComponent {

    WebDriver driver;

    By alertPopup = By.id("alertPopup");
    By simpleAlert = By.id("simple-alert");

    public CommonComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForElementsToAppear(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public String getPopupText() {
        waitForElementToAppear(alertPopup);
        WebElement popup = driver.findElement(alertPopup);
        if (popup.isDisplayed()) {
            return popup.getText();
        } else return "Popup not displayed";
    }

    public List<String> getSimpleAlertsText() {
        waitForElementToAppear(simpleAlert);
        List<WebElement> popups = driver.findElements(simpleAlert);
        if (popups.getFirst().isDisplayed()) {
            return popups.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        } else return List.of();
    }
}
