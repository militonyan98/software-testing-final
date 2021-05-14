package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.Account.LoginPage;
import pages.Cart.CartPage;
import pages.base.POMBase;
import pages.search.SearchBar;
import pages.search.SearchPage;

public class HomePage extends POMBase {



    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(){
        return new LoginPage(mDriver);
    }

    public SearchBar searchBar(){
        return new SearchBar(mDriver);
    }

    By cartSelector = By.id("inline_basket_trigger");

    public CartPage goToCart(){
        var cartIcon = $(cartSelector);

        Actions builder = new Actions(mDriver);
        builder.moveToElement(cartIcon);
        builder.click();
        builder.click();
        builder.build().perform();

        return new CartPage(mDriver);
    }
}
