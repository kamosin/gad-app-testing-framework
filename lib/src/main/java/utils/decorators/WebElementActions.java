package utils.decorators;

import org.openqa.selenium.WebElement;

public class WebElementActions implements WebElementDecorator{
    @Override
    public void click(WebElement element) {
        element.click();
    }
}
