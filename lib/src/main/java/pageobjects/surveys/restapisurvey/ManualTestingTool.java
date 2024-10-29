package pageobjects.surveys.restapisurvey;

public enum ManualTestingTool{
    Swagger("Swagger"),
    Postman("Postman"),
    Bruno("Bruno"),
    DevTools("Dev Tools"),
    Other("Other");

    final String tool;

    public String getStringName(){
        return this.tool;
    }

    ManualTestingTool(String tool) {
        this.tool = tool;
    }
}