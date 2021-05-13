package pages.search.refineElements.brandsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrandFilter {

    private WebElement mWebElement;

    public BrandFilter(WebElement webElement){
        mWebElement = webElement;
    }

    By optionsCSSSelector = By.cssSelector("div[data-comp*='RefinementItem']");

    public List<BrandOption> getOptions(){
        List<WebElement> optionElements = mWebElement.findElements(optionsCSSSelector);
        List<BrandOption> options= new ArrayList<>();
        System.out.println(optionElements.size());
        for (var optionElement : optionElements){
            options.add(new BrandOption(optionElement));
        }

        return options;
    }


}
