package utils.decorators;

import org.openqa.selenium.*;

public class RetryDecorator extends WebElementBaseDecorator{

    private final int retryCount;

    public RetryDecorator(WebElementDecorator webElementDecorator, int retryCount) {
        super(webElementDecorator);
        this.retryCount = retryCount;
    }

    @Override
    public void click(WebElement element) {
        int attempts = 0;
        while (attempts < retryCount) {
            try {
                super.click(element);
                return;
            } catch (NoSuchElementException | StaleElementReferenceException |
                     ElementClickInterceptedException | TimeoutException e) {
                attempts++;
                System.out.println("Exception occurred: " + e.getClass().getSimpleName() +
                        ", retrying... Attempt: " + attempts);
            }
        }
        throw new RuntimeException("Element could not be clicked after " + retryCount + " attempts");
    }
}
