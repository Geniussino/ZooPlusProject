package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementMethods {
    public WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    public void scrollPageDown() {
        js.executeScript("window.scrollBy(0,400)");
    }

    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(By locator) {
        waitForElement(locator);
        getElement(locator).click();
    }

    public void clickElement(WebElement element) {
        waitForElement(element);
        element.click();
    }

    public void fillElement(By locator, String textValue) {
        waitForElement(locator);
        getElement(locator).clear();
        getElement(locator).sendKeys(textValue);
    }

    public void fillElement(WebElement element, String textValue) {
        waitForElement(element);
        element.clear();
        element.sendKeys(textValue);
    }

    public void fillElement(By locator, Keys keyValue) {
        waitForElement(locator);
        getElement(locator).sendKeys(keyValue);
    }

    public void fillElement(WebElement element, Keys keyValue) {
        waitForElement(element);
        element.sendKeys(keyValue);
    }

    public void elementFromListByText(By locator, String textValue) {
        for (WebElement menuName : getElements(locator)) {
            if (menuName.getText().equals(textValue)) {
                menuName.click();
                break;
            }
        }
    }

    public void clickElementByText(By locator, String textValue) {
        for (WebElement element : getElements(locator)) {
            if (element.getText().contains(textValue)) {
                waitForElement(element);
                element.click();
                break;
            }
        }
    }

    public String getErrorMessage() {
        By errorLocator = By.cssSelector("[data-zta='alertText']");
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        return errorElement.getText();
    }
}