package pageobjects.surveys.automationsurvey;

public enum YearsOfExperience {
    LessThanOneYear("less than 1 year"),
    one_two_Years("1-2 years"),
    two_three_years("2-3 years"),
    four_five_years("4-5 years"),
    fiveOrMoreYears("5 or more years");

    final String years;

    public String getStringName(){
        return this.years;
    }

    YearsOfExperience(String yearsOfExperience) {
        this.years = yearsOfExperience;
    }
}
