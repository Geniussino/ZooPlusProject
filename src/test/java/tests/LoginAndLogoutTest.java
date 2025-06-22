package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class LoginAndLogoutTest {
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
        driver.manage().window().maximize();driver.get("https://www.zooplus.ro/");
    }

    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.acceptCookies();
        loginPage.openLoginForm();
        loginPage.login(props.getProperty("zoo.email.valid"), props.getProperty("zoo.password.valid"));
        assert loginPage.isLoggedInSuccessfully();

        loginPage.logout();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("feedback_logout"));//asteapta pana in 5 sec, sa apara pagina de logout
        assert loginPage.isAt();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
