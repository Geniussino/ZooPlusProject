package pages;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import helpers.ElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver driver;
    private ElementMethods elements;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.elements = new ElementMethods(driver);
    }

    private final By searchField = By.id("search_query_field_desktop");
    private final By productTitleOnPage = By.cssSelector("h1");
    private final By addToFavoritesBtn = By.cssSelector("button[aria-label='Adaugă produsul în lista de favorite']");
    private final By favoritedIcon = By.cssSelector("button[aria-label='Adaugă produsul în lista de favorite']");

    public void searchAndOpenProductByName(String productName) {
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Searching for the product: " + productName);
        elements.clickElement(searchField);
        elements.fillElement(searchField, productName);
        elements.fillElement(searchField, Keys.ENTER);
        elements.waitForElement(By.id("986105"));
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Opening the product page for: " + productName);

        WebElement productLink = driver.findElement(By.id("986105"));
        productLink.click();

        elements.waitForElement(productTitleOnPage);
        ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Product page for '" + productName + "' successfully opened.");
    }

    public void addToFavorites() {
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Adding the current product to favorites...");
        elements.waitForElement(addToFavoritesBtn);
        elements.clickElement(addToFavoritesBtn);
        ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Product added to favorites.");
    }

    public boolean isProductFavorited() {
        boolean isFavorited = !elements.getElements(favoritedIcon).isEmpty();
        if (isFavorited) {
            ExtentHelper.logInfo(ReportEventType.PASS_STEP, "Product is confirmed as favorited.");
        } else {
            ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Product is NOT marked as favorite.");
        }
        return isFavorited;
    }
}