package pages.search.refineElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;
import pages.search.refineElements.brandsElement.BrandFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RefinementBar extends POMBase {
    public RefinementBar(WebDriver driver) {
        super(driver);
    }

    By mRefinementFiltersSelector = By.cssSelector("div[data-comp*='RefinementBoxFS']");
    By mResetAllFilter = By.cssSelector("button[data-at*='reset_all']");

    public void resetAll(){
        print("Resetting filters");
        WebElement resetButton = $(mResetAllFilter);
        resetButton.click();
    }

    public List<RefinementFilters> getRefinementFilters(){
        List<RefinementFilters> refinementFilters = new ArrayList<>();

        List<WebElement> elements = mDriver.findElements(mRefinementFiltersSelector);
        print("Number of filters " + elements.size());
        for(var element : elements){
            var filter = new RefinementFilters(element);
            refinementFilters.add(filter);
        }
        return  refinementFilters;
    }

    public BrandFilter getBrandFilter(){
        for(var filter : getRefinementFilters()){
            if(filter.getTitle().toLowerCase(Locale.ROOT).equals("brand")){
                return new BrandFilter(filter.getWebElement(), mDriver);
            }
        }
        return null;
    }
}
