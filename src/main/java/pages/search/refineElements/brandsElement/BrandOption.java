package pages.search.refineElements.brandsElement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.POMBase;

public class BrandOption extends POMBase {

    WebElement mWebElement;

    By mBrandNameSelector = By.cssSelector(".Checkbox-label");
    By mCheckboxSelector = By.cssSelector("input[type='checkbox']");

    public BrandOption(WebElement webElement, WebDriver driver){
        super(driver);
        mWebElement = webElement;
        mBrandName = mWebElement.findElement(mBrandNameSelector).getText();
        mCheckboxElement = mWebElement.findElement(mCheckboxSelector);
    }

    private WebElement mCheckboxElement;

    private String mBrandName;

    public String getBrandName(){
        return mBrandName;
    }

    public void select(){
        mWebElement.click();

        WebDriverWait wait = new WebDriverWait(mDriver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                mCheckboxElement = mWebElement.findElement(mCheckboxSelector);
                if(mCheckboxElement!=null){
                    return mCheckboxElement.isSelected();
                }
                return false;
            }
        });
    }

}
