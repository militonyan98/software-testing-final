package pages.Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.POMBase;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends POMBase {
    public CartPage(WebDriver driver){
        super(driver);
    }

    By basketItemSelector = By.cssSelector("div[data-comp*='BasketListItem']");

    public List<CartItem> getCartItems(){
        var items = mDriver.findElements(basketItemSelector);
        List<CartItem> cartItems = new ArrayList<>();
        for(var item : items){
            cartItems.add(new CartItem(item));
        }
        return cartItems;
    }
}
