package pageobjects.surveys.automationsurvey;

public enum ProgrammingLanguages {
    JavaScript("JavaScript"),
    TypeScript("TypeScript"),
    Java("Java"),
    CSharp("C#"),
    Python("Python"),
    Ruby("Ruby"),
    Other("Other");

    final String language;

    ProgrammingLanguages(String language) {
        this.language = language;
    }

    public String getStringName() {
        return this.language;
    }
}