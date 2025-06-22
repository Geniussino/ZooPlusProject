package pages;

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
        elements.clickElement(searchField);
        elements.fillElement(searchField, productName);
        elements.fillElement(searchField, Keys.ENTER);
        elements.waitForElement(By.id("986105")); // așteapta elementul target

        WebElement productLink = driver.findElement(By.id("986105"));
        productLink.click();

        elements.waitForElement(productTitleOnPage);
    }

    public void addToFavorites() {
        elements.waitForElement(addToFavoritesBtn);
        elements.clickElement(addToFavoritesBtn);
    }

    public boolean isProductFavorited() {
        return !elements.getElements(favoritedIcon).isEmpty();
    }
}