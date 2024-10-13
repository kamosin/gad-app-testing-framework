package testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import guitests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import java.io.File;
import java.io.IOException;

public class ExtentReporter extends BaseTest implements ITestListener, ISuiteListener {

    private ExtentReports extent;
    private ExtentTest test;
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extent-report.html");
        reporter.config().setReportName("Automation tests report");
        reporter.config().setDocumentTitle("Test results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        driver = getDriverInstance(result);
        var screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
        extentTest.get().addScreenCaptureFromPath(screenshotPath, "Failed Test Screenshot");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped");

        driver = getDriverInstance(result);
        String screenshotPath = takeScreenshot(result.getMethod().getMethodName(), driver);
        extentTest.get().addScreenCaptureFromPath(screenshotPath, "Skipped Test Screenshot");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String takeScreenshot(String testName, WebDriver driver){
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = "screenshots/" + testName + ".png";
        try {
            FileUtils.copyFile(source, new File("reports/" + destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    private WebDriver getDriverInstance(ITestResult result){
        try {
            return (WebDriver) result.getTestClass().getRealClass().getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
