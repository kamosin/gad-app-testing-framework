package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {
    WebDriver driver;
    CommonComponent commonComponent;

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="avatar")
    WebElement avatarIcon;
    @FindBy(id="registerBtn")
    WebElement registerButton;
    @FindBy(id="btnArticles")
    WebElement articlesButton;
    @FindBy(id = "add-new")
    WebElement addNewArticleButton;
    By addArticleBtn = By.id("add-new");

    public RegistrationPage clickRegisterButton(){
        Actions hoverOverElement = new Actions(driver);
        hoverOverElement.moveToElement(avatarIcon).perform();
        registerButton.click();
        return new RegistrationPage(driver);
    }

    public ArticlesPage clickArticlesPageButton(){
        articlesButton.click();
        return new ArticlesPage(driver);
    }

    public NewArticleModal clickAddArticleButton(){
        commonComponent.waitForElementToAppear(addNewArticleButton);
        addNewArticleButton.click();
        return new NewArticleModal(driver);
    }

    public boolean isAddArticleButtonVisible(){
        return !driver.findElements(addArticleBtn).isEmpty();
    }
}
