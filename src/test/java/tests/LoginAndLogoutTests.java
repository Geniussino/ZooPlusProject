package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileInputStream;
import java.util.Properties;

public class LoginAndLogoutTests {
    private WebDriver driver;
    private Properties props;

    @BeforeClass
    public void loadCredentials() throws Exception {
        props = new Properties();
        props.load(new FileInputStream("src/test/resources/credentials.properties"));
    }

    @BeforeMethod
    public void openHomePageAndMaximize() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.zooplus.ro/");
    }

    @Test
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.openLoginForm();
        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
        assert loginPage.isLoggedInSuccessfully();
    }

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.openLoginForm();
        loginPage.login(props.getProperty("zoo.email.invalid"), props.getProperty("zoo.password.invalid"));
        assert loginPage.isLoginErrorDisplayed();
    }

    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.openLoginForm();
        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
        assert loginPage.isLoggedInSuccessfully();

        loginPage.logout();
        assert loginPage.isAt();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
