package pages;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");
    private final By loginIcon = By.cssSelector("a[href='/account/overview']");
    private final By emailInput = By.cssSelector("input[name='username']");
    private final By passwordInput = By.cssSelector("input[name='password']");
    private final By submitBtn = By.name("login");
    private final By errorMessage = By.cssSelector("div.z-alert__text");
    private final By userMenuAfterLogin = By.cssSelector("a[data-testid='user-menu-button']");
    private final By logoutBtn = By.cssSelector("a[data-zta='account-overview-logout-link']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        elements.clickElement(acceptCookiesBtn);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Accepted cookies banner");
    }

    public void openLoginForm() {
        try {
            driver.findElement(loginIcon).click();
            ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Clicked on the login icon to open the login form");
        } catch (TimeoutException e) {
            System.out.println("Login icon not visible. Retrying...");
            driver.navigate().refresh();
            elements.clickElement(loginIcon);
            ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Login icon clicked after page refresh");
        }
    }

    public void login(String email, String password) {
        elements.waitForElement(emailInput);
        elements.fillElement(emailInput, email);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Entered email: " + email);
        elements.fillElement(passwordInput, password);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Entered password: [HIDDEN]");
        elements.clickElement(submitBtn);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Clicked on login button");
    }

    public boolean isLoginErrorDisplayed() {
        boolean errorVisible = elements.getElements(errorMessage).size() > 0;
        ExtentHelper.logInfo(ReportEventType.INFO_STEP,
                errorVisible ? "Login error message is displayed" : "No login error message detected");
        return errorVisible;
    }

    public boolean isLoggedInSuccessfully() {
        By greetingText = By.xpath("//*[contains(text(), 'Salut Eugen')]");
        try {
            elements.waitForElement(greetingText);
            ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Login was successful — greeting message found");
            return true;
        } catch (Exception e) {
            ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Login greeting not found. Login might have failed.");
            return false;
        }
    }

    public void logout() {
        elements.clickElement(logoutBtn);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Clicked on logout button");
    }

    @Override
    public boolean isAt() {
        boolean isAtPage = driver.getCurrentUrl().contains("feedback_logout");
        ExtentHelper.logInfo(ReportEventType.INFO_STEP,
                isAtPage ? "Successfully landed on logout feedback page" : "Not on the expected logout feedback page");
        return isAtPage;
    }
}