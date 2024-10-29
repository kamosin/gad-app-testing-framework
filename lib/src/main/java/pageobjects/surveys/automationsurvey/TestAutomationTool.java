package pageobjects.surveys.automationsurvey;

public enum TestAutomationTool {
    Playwright("Playwright"),
    Cypress("Cypress"),
    Selenium("Selenium"),
    WebdriverIO("Webdriver.io"),
    Puppeteer("Puppeteer"),
    Nightwatch("Nightwatch"),
    Other("Other");

    final String tool;

    public String getStringName(){
        return this.tool;
    }

    TestAutomationTool(String tool) {
        this.tool = tool;
    }
}
