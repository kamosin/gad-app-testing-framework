package utils.decorators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDecorator extends WebElementBaseDecorator{
    private WebDriver driver;


    public WaitDecorator(WebElementDecorator webElementDecorator, WebDriver driver) {
        super(webElementDecorator);
        this.driver = driver;
    }

    @Override
    public void click(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Waiting for element to be visible before clicking: " + element);
        super.click(element);
    }
}
