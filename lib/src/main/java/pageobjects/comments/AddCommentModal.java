package pageobjects.comments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;

public class AddCommentModal {

    WebDriver driver;
    CommonComponent commonComponent;

    public AddCommentModal(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="body")
    WebElement commentBody;
    @FindBy(css = ".save")
    WebElement saveCommentButton;

    public void enterBodyText(String text){
        commentBody.sendKeys(text);
    }

    public void clickSaveCommentButton(){
        saveCommentButton.click();
    }
}
