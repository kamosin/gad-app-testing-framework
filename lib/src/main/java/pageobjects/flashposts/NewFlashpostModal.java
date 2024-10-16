package pageobjects.flashposts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewFlashpostModal {

    WebDriver driver;

    public NewFlashpostModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "flashpost-text")
    WebElement flashpostTextArea;
    @FindBy(id = "background-color-picker")
    WebElement backgroundColorPicker;
    @FindBy(css = ".create")
    WebElement createButton;
    @FindBy(css = ".cancel")
    WebElement cancelButton;
    @FindBy(id = "public-checkbox")
    WebElement publicCheckbox;

    public void enterAllFlashpostData(String text, String hexColor, boolean isPublic){
        enterFlashpostData(text);
        setBackgroundColor(hexColor);
        if(isPublic){
            clickCheckbox();
        }
    }
    public void enterFlashpostData(String text){
        flashpostTextArea.sendKeys(text);
    }

    public void setBackgroundColor(String hexColor){
        backgroundColorPicker.sendKeys(hexColor);
    }

    public void clickCheckbox(){
        publicCheckbox.click();
    }

    public void clickCreateButton(){
        createButton.click();
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public int getFlashpostTextLength(){
        return flashpostTextArea.getText().length();
    }


}
