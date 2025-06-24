package tests;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

import static extentUtility.ExtentHelper.finishTest;

public class BaseTest {
    public WebDriver driver;
    public String testName;

    @BeforeClass
    public void initiateReport() {
        ExtentHelper.initiateReport();
    }

    @BeforeMethod
    public void setUp(Method method) {
        ExtentHelper.createTest(method.getName());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.zooplus.ro/");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentHelper.logFailScreenshot(driver, "Test failed: " + result.getThrowable().getMessage());
        } else {
            ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Test passed!");
        }
        if (driver != null) {
            driver.quit();
        }
        finishTest(result.getMethod().getMethodName());
    }

    @AfterClass
    public void finalizeReport() {
        ExtentHelper.generateReport();
    }
}
