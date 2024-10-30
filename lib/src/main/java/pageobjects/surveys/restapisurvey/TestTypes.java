package pageobjects.surveys.restapisurvey;

public enum TestTypes{
    UnitTests("Unit tests"),
    ModuleTests("Module tests"),
    ContractTests("Contract tests"),
    IntegrationTests("Integration tests"),
    E2eTests("E2e tests"),
    UatTests(" UAT tests"),
    PerformanceTests("Performance tests"),
    SecurityTests("Security tests"),
    VisualTests("Visual tests"),
    AccessibilityTests("Accessibility tests"),
    UsabilityTests("Usability tests"),
    SmokeTests("Smoke tests"),
    SanityTests("Sanity tests"),
    RegressionTests("Regression tests"),
    AdHocTests("Ad-hoc tests"),
    ExploratoryTests("Exploratory tests"),
    MutationTests("Mutation tests"),
    FuzzTests("Fuzz tests"),
    ChaosTests("Chaos tests"),
    other("other ");

    final String testType;


    TestTypes(String testType) {
        this.testType = testType;
    }

    public String getStringName(){
        return this.testType;
    }
}