package Extent_Reports;


import Base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ExtentReport extends Base implements ITestListener {
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public synchronized void onStart(ITestContext context) {


        String reportPath = System.getProperty("user.dir") + "/target/surefire-reports/extent-report.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Vipin Test Execution Report P_Signal");


        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");

    }

    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    public synchronized void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));
    }

    public synchronized void onTestFailure(ITestResult result) {
        // Get the driver from the test class
        Object testClass = result.getInstance();
        WebDriver driver = ((Base) testClass).driver;

        // Take screenshot
        takeScreenshot(driver, result.getName());
    }

    public void takeScreenshot(WebDriver driver, String testName) {
        try {

            TakesScreenshot screenshot = (TakesScreenshot) driver;


            File source = screenshot.getScreenshotAs(OutputType.FILE);


            String destinationPath = "target/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            File destination = new File(destinationPath);


            FileUtils.copyFile(source, destination);

            System.out.println("Screenshot saved at: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }



    public synchronized void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED", ExtentColor.ORANGE));
        test.get().skip(result.getThrowable());
    }

    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }


    public String captureScreenshot(String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        return dest;
    }
}