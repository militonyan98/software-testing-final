package pages.Products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;

public class ProductPage extends POMBase {

    public ProductPage(WebDriver driver) {
        super(driver);
    }


    By addToCartButton = By.cssSelector("button[data-comp*='AddToBasketButton']");
    public boolean addToCart(){
        try{
            WebElement button = mDriver.findElement(addToCartButton);
            button.click();
            return true;
        }
        catch (Exception ignored){
            return false;
        }
    }
}
