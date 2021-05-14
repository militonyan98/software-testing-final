package pages.Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItem {

    By brandNameSelector = By.cssSelector("strong[data-at*='bsk_sku_brand'");
    By productNameSelector = By.cssSelector("span[data-at*='bsk_sku_name'");

    public CartItem(WebElement element){
        mBrandName = element.findElement(brandNameSelector).getText();
        mProductName = element.findElement(productNameSelector).getText();
    }

    private String mBrandName;
    private String mProductName;

    public String getBrandName() {
        return mBrandName;
    }

    public String getProductName() {
        return mProductName;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "mBrandName='" + mBrandName + '\'' +
                ", mProductName='" + mProductName + '\'' +
                '}';
    }
}
