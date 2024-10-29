package pageobjects.surveys.automationsurvey;

public enum NumberOfAutomatedTests {
    LessThanTen("less than 10"),
    TenToFifty("10-50"),
    FiftyToOneHundred("50-100"),
    OneHundredToTwoHundredFifty("100-250"),
    TwoHundredFiftyToFiveHundred("250-500"),
    FiveHundredToOneThousand("500-1000"),
    OverOneThousand("1000+");

    final String range;

    NumberOfAutomatedTests(String range) {
        this.range = range;
    }

    public String getStringName() {
        return this.range;
    }
}
