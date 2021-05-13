package pages.search.refineElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;

import java.util.ArrayList;
import java.util.List;

public class RefinementBar extends POMBase {
    public RefinementBar(WebDriver driver) {
        super(driver);
    }

    By mRefinementFiltersSelector = By.cssSelector("div[data-comp*='RefinementBoxFS']");

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
}