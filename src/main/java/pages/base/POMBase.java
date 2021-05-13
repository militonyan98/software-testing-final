package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class POMBase {
    protected WebDriver mDriver;

    public POMBase(WebDriver driver){
        this.mDriver = driver;
    }

    public WebElement $(By by){
        return this.mDriver.findElement(by);
    }

    public String getURL(){
        return mDriver.getCurrentUrl();
    }
}
