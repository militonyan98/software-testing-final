package pages.search.refineElements.brandsElement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;

import java.util.ArrayList;
import java.util.List;

public class BrandFilter extends POMBase {

    private WebElement mWebElement;

    public BrandFilter(WebElement webElement, WebDriver driver){
        super(driver);
        mWebElement = webElement;
    }

    By optionsCSSSelector = By.cssSelector("div[data-comp*='RefinementItem']");

    By resetSelection = By.cssSelector("button");

    public void reset(){
        WebElement webElement = mWebElement.findElement(resetSelection);
        webElement.click();
    }

    public List<BrandOption> getOptions(){
        List<WebElement> optionElements = mWebElement.findElements(optionsCSSSelector);
        List<BrandOption> options= new ArrayList<>();
        System.out.println(optionElements.size());
        for (var optionElement : optionElements){
            options.add(new BrandOption(optionElement, mDriver));
        }

        return options;
    }


}
