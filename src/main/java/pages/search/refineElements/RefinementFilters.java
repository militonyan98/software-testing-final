package pages.search.refineElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;

public class RefinementFilters {


    private final WebElement mWebElement;

    By mTitleSelector = By.cssSelector("h2");

    public RefinementFilters(WebElement we) {
        mWebElement = we;
        mTitle = mWebElement.findElement(mTitleSelector).getText();
    }

    private final String mTitle;

    public String getTitle() {
        return mTitle;
    }

    public WebElement getWebElement() {
        return mWebElement;
    }
}
