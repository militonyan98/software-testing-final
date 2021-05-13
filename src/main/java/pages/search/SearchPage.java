package pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;
import pages.search.refineElements.RefinementBar;
import pages.search.refineElements.RefinementFilters;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends POMBase {

    By searchElement = By.className("css-12egk0t");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<SearchItem> getItems() {
        List<SearchItem> items = new ArrayList<>();
        List<WebElement> webElements = mDriver.findElements(searchElement);
        for(WebElement elem : webElements){
            var si = new SearchItem(elem);
            if(si.getIsValid())
                items.add(si);
            else break;
        }
        return items;
    }

    public RefinementBar refinementBar(){
        return new RefinementBar(mDriver);
    }

    public class SearchItem {

        WebElement mCurrentElement;

        By productDescription = By.className("css-1gughuu");

        private final String mSkuProductBrandAttribute = "sku_item_brand";
        private final String mSkuProductProductName = "sku_item_name";
        private final String mDataAt = "data-at";

        public SearchItem(WebElement element){
            mCurrentElement = element;
            try
            {
                WebElement productDesc = mCurrentElement.findElement(productDescription);
                List<WebElement> childElementsOfProductsDesc = productDesc.findElements(By.cssSelector("*"));
                for(var childElement : childElementsOfProductsDesc){

                    var dataInfo = childElement.getAttribute(mDataAt);

                    if(dataInfo==null)
                        continue;;

                    //getting product brand
                    if(dataInfo.equals(mSkuProductBrandAttribute)){
                        mBrandName = childElement.getText();
                    }
                    //getting product name
                    if(dataInfo.equals(mSkuProductProductName)){
                        mItemName = childElement.getText();
                    }
                }

                mIsValid = true;
            }
            catch(Exception ex){
                mIsValid = false;
            }

        }




        @Override
        public String toString() {
            return "SearchItem{" +
                    "BrandName='" + mBrandName + '\'' +
                    ", ItemName='" + mItemName + '\'' +
                    '}';
        }


        //because of loading some elems might not be valid
        private boolean mIsValid;

        public boolean getIsValid(){
            return mIsValid;
        }



        //item fields
        private String mBrandName = "";
        private String mItemName = "";

        public String getBrandName() {
            return mBrandName;
        }

        public String getItemName(){
            return mItemName;
        }
    }
}