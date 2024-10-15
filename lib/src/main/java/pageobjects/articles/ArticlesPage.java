package pageobjects.articles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.CommonComponent;
import pageobjects.comments.SingleCommentModal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArticlesPage {

    WebDriver driver;
    CommonComponent commonComponent;

    public ArticlesPage(WebDriver driver) {
        this.driver = driver;
        this.commonComponent = new CommonComponent(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="search-input")
    WebElement articlesSearchInput;
    @FindBy(css = "a[data-testid='search-button']")
    WebElement searchArticleButton;
    @FindBy(css = ".card-wrapper")
    List<WebElement> singleArticleWrapper;
    By seeMoreClickableText = By.xpath("//a[text()='See More...']");
    By singleArticleName = By.xpath(".//strong//a");
    By singleArticleUser = By.xpath("(.//span//a)[1]");

    public SingleArticlePage clickSeeMore(String articleTitle){
        var wrapper = getArticleWrapperByArticleName(articleTitle);
        wrapper.findElement(seeMoreClickableText).click();
        return new SingleArticlePage(driver);
    }

    private WebElement getArticleWrapperByArticleName(String articleTitle) {
        return singleArticleWrapper.stream()
                .filter(wrapper -> articleTitle.equals(wrapper.findElement(singleArticleName).getText()))
                .findFirst()
                .orElse(null);
    }

    public void searchArticle(String articleData){
        commonComponent.waitForElementToAppear(articlesSearchInput);
        articlesSearchInput.sendKeys(articleData);
        searchArticleButton.click();

    }

    public int returnNumberOfArticlesVisible(){
        return singleArticleWrapper.size();
    }

    public List<String> returnNamesOfArticles(){
        return singleArticleWrapper.stream()
                .map(wrapper -> wrapper.findElement(singleArticleName).getText())
                .collect(Collectors.toList());
    }

    public String returnTitleOfArticle(int number){
        commonComponent.waitForElementsToAppear(singleArticleWrapper);
        return singleArticleWrapper.get(number).findElement(singleArticleName).getText();
    }

    public String returnUserOfArticle(int number){
        commonComponent.waitForElementsToAppear(singleArticleWrapper);
        return singleArticleWrapper.get(number).findElement(singleArticleUser).getText();
    }
}
