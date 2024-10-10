package testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;

public class ExtentReporter implements ITestListener, ISuiteListener {

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
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
