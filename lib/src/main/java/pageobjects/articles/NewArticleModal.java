package pageobjects.articles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageobjects.CommonComponent;

public class NewArticleModal {

    WebDriver driver;
    CommonComponent commonComponent;

    public NewArticleModal(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(tagName = "h2")
    WebElement header;
    @FindBy(css = "input[data-testid='title-input']")
    WebElement titleInput;
    @FindBy(id = "body")
    WebElement bodyTextArea;
    @FindBy(id = "image")
    WebElement imageSelector;
    @FindBy(css = "button[data-testid='save']")
    WebElement saveButton;
    @FindBy(css = ".cancel")
    WebElement cancelButton;
    @FindBy(id = "close")
    WebElement closeModalButton;


    public void enterTitle(String title){
        titleInput.sendKeys(title);
    }

    public void enterBody(String body){
        bodyTextArea.sendKeys(body);
    }

    public void selectPicture(String pictureName){
        Select pictureSelect = new Select(imageSelector);
        pictureSelect.selectByValue(pictureName);
    }

    public void enterAllData(String title, String body, String pictureName){
        enterTitle(title);
        enterBody(body);
        selectPicture(pictureName);
    }

    public void clickSaveButton(){
        saveButton.click();
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public void clearTitle(){
        titleInput.clear();
    }

    public  void clearBody(){
        bodyTextArea.clear();
    }

}
