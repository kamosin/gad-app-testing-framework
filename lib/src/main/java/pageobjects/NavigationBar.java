package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.articles.ArticlesPage;
import pageobjects.articles.NewArticleModal;
import pageobjects.comments.CommentsPage;
import pageobjects.flashposts.FlashpostsPage;

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
    @FindBy(id="loginBtn")
    WebElement loginButton;
    @FindBy(id = "logoutBtn")
    WebElement logoutButton;
    @FindBy(id="btnArticles")
    WebElement articlesButton;
    @FindBy(id="btnComments")
    WebElement commentsButton;
    @FindBy(id = "add-new")
    WebElement addNewArticleButton;
    @FindBy(css = "button[data-testid='open-flashposts']")
    WebElement flashPostsButton;
    By addArticleBtn = By.id("add-new");

    public RegistrationPage clickRegisterButton(){
        Actions hoverOverElement = new Actions(driver);
        hoverOverElement.moveToElement(avatarIcon).perform();
        registerButton.click();
        return new RegistrationPage(driver);
    }

    public LoginPage clickLoginButton(){
        Actions hoverOverElement = new Actions(driver);
        hoverOverElement.moveToElement(avatarIcon).perform();
        loginButton.click();
        return new LoginPage(driver);
    }



    public LoginPage clickLogoutButton(){
        Actions hoverOverElement = new Actions(driver);
        hoverOverElement.moveToElement(avatarIcon).perform();
        logoutButton.click();
        return new LoginPage(driver);
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

    public CommentsPage clickCommentsPageButton(){
        commentsButton.click();
        return new CommentsPage(driver);
    }

    public FlashpostsPage clickFlashpostsPageButton(){
        commonComponent.waitForElementToAppear(flashPostsButton);
        flashPostsButton.click();
        return new FlashpostsPage(driver);
    }

    public boolean isAddArticleButtonVisible(){
        return !driver.findElements(addArticleBtn).isEmpty();
    }
}
