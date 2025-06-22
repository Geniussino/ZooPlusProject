package tests;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import helpers.CredentialsUtil;
import helpers.ElementMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.Properties;


public class LoginWithInvalidCredentialsTest extends BaseTest {
    private Properties props;
    private LoginPage loginPage;

    @BeforeClass
    public void loadInvalidCreds() {
        props = CredentialsUtil.load("src/test/resources/credentials.properties");
    }

    @Test
    public void loginWithInvalidCredentials() {
        loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.openLoginForm();
        loginPage.login(props.getProperty("zoo.email.invalid"), props.getProperty("zoo.password.invalid"));
        String error = new ElementMethods(driver).getErrorMessage();
        Assert.assertNotNull(error, "Error message not found!");
        ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Error found as expected: " + error);
    }
}