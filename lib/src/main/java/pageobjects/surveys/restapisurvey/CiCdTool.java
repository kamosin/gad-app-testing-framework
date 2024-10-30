package pageobjects.surveys.restapisurvey;

public enum CiCdTool{
    GithubActions("GitHub Actions"),
    Jenkins("Jenkins"),
    AzureDevops("Azure DevOps "),
    GitlabCI("GitLab CI"),
    TeamCity("TeamCity"),
    CircleCI("Circle CI"),
    other("other");

    final String ciTool;

    public String getStringName(){
        return this.ciTool;
    }

    CiCdTool(String ciTool) {
        this.ciTool = ciTool;
    }
}