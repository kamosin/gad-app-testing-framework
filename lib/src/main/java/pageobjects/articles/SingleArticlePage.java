package pageobjects.articles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.comments.AddCommentModal;
import pageobjects.CommonComponent;

public class SingleArticlePage {

    WebDriver driver;
    CommonComponent commonComponent;

    public SingleArticlePage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="add-new")
    WebElement addNewCommentButton;

    public AddCommentModal clickAddNewCommentButton(){
        addNewCommentButton.click();
        return new AddCommentModal(driver);
    }

    public void addComment(String commentText){
        var commentModal = clickAddNewCommentButton();
        commentModal.enterBodyText(commentText);
        commentModal.clickSaveCommentButton();
    }
}
