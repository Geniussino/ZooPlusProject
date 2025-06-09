package pages;

import helpers.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected ElementMethods elements;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.elements = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isAt();
}

