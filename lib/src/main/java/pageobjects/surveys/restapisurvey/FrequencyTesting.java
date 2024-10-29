package pageobjects.surveys.restapisurvey;

public enum FrequencyTesting{
    Daily("daily"),
    Weekly("weekly"),
    Monthly("monthly"),
    Never("never");

    final String testingFrequency;

    public String getStringName(){
        return this.testingFrequency;
    }

    FrequencyTesting(String frequency) {
        this.testingFrequency = frequency;
    }
}