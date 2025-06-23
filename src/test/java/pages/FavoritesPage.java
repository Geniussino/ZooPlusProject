package pages;

import extentUtility.ExtentHelper;
import extentUtility.ReportEventType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FavoritesPage extends BasePage {
    private final By productList = By.cssSelector(".c-wishlist-product__description");
    private final By removeFavoriteBtn = By.cssSelector("button[data-testid='remove-from-favourites']");

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToFavorites() {
        driver.get("https://www.zooplus.ro/account/wishlist");
        elements.waitForElement(productList);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Navigated to the Favorites page");
    }

    public void removeProductFromFavorites(String productName) {
        elements.clickElementByText(productList, productName);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Selected the product: " + productName + " from the Favorites page");
        elements.waitForElement(removeFavoriteBtn);
        elements.clickElement(removeFavoriteBtn);
        ExtentHelper.logInfo(ReportEventType.INFO_STEP, "Clicked on the remove button for the selected product");
    }

    public boolean isProductUnfavorited() {
        boolean unfavorited = elements.getElements(removeFavoriteBtn).size() == 0;
        ExtentHelper.logInfo(unfavorited ? ReportEventType.PASS_STEP : ReportEventType.INFO_STEP,
                unfavorited ? "Product was successfully removed from Favorites" : "Product is still present in Favorites");
        return unfavorited;
    }

    @Override
    public boolean isAt() {
        boolean isOnFavorites = elements.getElements(productList).size() > 0;
        ExtentHelper.logInfo(isOnFavorites ? ReportEventType.INFO_STEP : ReportEventType.INFO_STEP,
                isOnFavorites ? "Landed on the Favorites page" : "The product isn't in the Favorites page");
        return isOnFavorites;
    }
}