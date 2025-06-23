package tests;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import helpers.CredentialsUtil;
import helpers.ElementMethods;
import org.testng.annotations.*;
import pages.LoginPage;
import java.util.*;

public class LoginWithMultipleInvalidCredentialsTest extends BaseTest {
    private Properties props;
    private LoginPage loginPage;

    @BeforeClass
    public void loadCredentials() {
        props = CredentialsUtil.load("src/test/resources/invalidMultipleCredentials.properties");
    }

    @Test
    public void loginWithMultipleInvalidCredentials() {
        loginPage = new LoginPage(driver);
        ElementMethods elementMethods = new ElementMethods(driver);
        loginPage.acceptCookies();

        Map<String, String> invalidCredentials = getInvalidCredentialsMap();

        for (Map.Entry<String, String> entry : invalidCredentials.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();

            ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Attempting to log in with user: " + username);
            loginPage.openLoginForm();
            loginPage.login(username, password);

            String errorMessage = elementMethods.getErrorMessage();
            if (errorMessage != null && !errorMessage.isEmpty()) {
                ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Error captured correctly: " + errorMessage);
            } else {
                ExtentHelper.logFailScreenshot(driver, "No error message found for user: " + username);
            }

            driver.get("https://www.zooplus.ro/");
        }
    }

    private Map<String, String> getInvalidCredentialsMap() {
        Map<String, String> credentials = new HashMap<>();
        List<String> usernames = new ArrayList<>();
        List<String> passwords = new ArrayList<>();

        for (Object key : props.keySet()) {
            String strKey = key.toString();
            if (strKey.startsWith("invalidUsername")) {
                usernames.add(props.getProperty(strKey));
            } else if (strKey.startsWith("invalidPassword")) {
                passwords.add(props.getProperty(strKey));
            }
        }

        for (int i = 0; i < usernames.size(); i++) {
            credentials.put(usernames.get(i), passwords.get(i));
        }
        return credentials;
    }
}