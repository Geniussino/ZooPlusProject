package pages;

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
    }

    public void removeProductFromFavorites(String productName) {
        elements.clickElementByText(productList, productName);
        elements.waitForElement(removeFavoriteBtn);
        elements.clickElement(removeFavoriteBtn);
    }

    public boolean isProductUnfavorited() {
        return elements.getElements(removeFavoriteBtn).size() == 0;
    }

    @Override
    public boolean isAt() {
        return elements.getElements(productList).size() > 0;
    }
}