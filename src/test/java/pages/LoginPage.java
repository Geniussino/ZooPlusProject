package pages;

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
    }

    public void openLoginForm() {
        try {
            driver.findElement(loginIcon).click();
        } catch (TimeoutException e) {
            System.out.println("Login icon not visible. Retrying...");
            driver.navigate().refresh();
            elements.clickElement(loginIcon);
        }
    }

    public void login(String email, String password) {
        elements.waitForElement(emailInput);
        elements.fillElement(emailInput, email);
        elements.fillElement(passwordInput, password);
        elements.clickElement(submitBtn);
    }

    public boolean isLoginErrorDisplayed() {
        return elements.getElements(errorMessage).size() > 0;
    }

    public boolean isLoggedInSuccessfully() {
        By greetingText = By.xpath("//*[contains(text(), 'Salut Eugen')]");
        try {
            elements.waitForElement(greetingText);
            return true;
        } catch (Exception e) {
            System.out.println("Textul 'Salut Eugen' nu a fost găsit după login.");
            return false;
        }
    }

    public void logout() {
        elements.clickElement(logoutBtn);
    }

    @Override
    public boolean isAt() {
        return driver.getCurrentUrl().contains("feedback_logout");
    }
}