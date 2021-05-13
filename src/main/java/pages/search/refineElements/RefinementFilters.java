package pages.search.refineElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;

public class RefinementFilters {


    private WebElement mWebElement;

    By mTitleSelector = By.cssSelector("h2");

    public RefinementFilters(WebElement we) {
        mWebElement = we;
        mTitle = mWebElement.findElement(mTitleSelector).getText();
        System.out.println(mTitle);
    }

    private final String mTitle;

    public String getTitle() {
        return mTitle;
    }
}
