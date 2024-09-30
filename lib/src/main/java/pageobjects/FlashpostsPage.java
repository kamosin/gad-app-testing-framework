package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FlashpostsPage {

    WebDriver driver;

    public FlashpostsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".create-flashpost-btn")
    WebElement createFlashpostsButton;
    @FindBy(css = ".flashpost-container")
    List<WebElement> flashpostContainers;

    By flashpostAuthor = By.cssSelector(".flashpost-author");
    By flashpostText = By.cssSelector(".flashpost-message");

    public NewFlashpostModal clickCreateFlashpostsButton(){
        createFlashpostsButton.click();
        return new NewFlashpostModal(driver);
    }

    public String getFlashpostAuthor(int containerNumber){
        return flashpostContainers.get(containerNumber).findElement(flashpostAuthor).getText();
    }

    public String getFlashpostText(int containerNumber){
        return flashpostContainers.get(containerNumber).findElement(flashpostText).getText();
    }

    public int getNumberOfFlashposts(){
        return flashpostContainers.size();
    }
}
