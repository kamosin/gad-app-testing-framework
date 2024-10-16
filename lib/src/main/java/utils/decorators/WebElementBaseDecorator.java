package utils.decorators;

import org.openqa.selenium.WebElement;

public abstract class WebElementBaseDecorator implements WebElementDecorator{

    protected WebElementDecorator webElementDecorator;

    public WebElementBaseDecorator(WebElementDecorator webElementDecorator) {
        this.webElementDecorator = webElementDecorator;
    }

    @Override
    public void click(WebElement element) {
        webElementDecorator.click(element);
    }
}
