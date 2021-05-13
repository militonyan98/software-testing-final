package pages.Account;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.POMBase;

import java.time.Duration;
import java.util.ArrayList;

public class LoginPage extends POMBase {

    private final By mLoginWindow = By.className("css-gxahov");
    private final By mCloseButton = By.className("css-1kna575");

    private final By signInButton = By.xpath("//button[text()=\"Sign In\"]");

    private WebElement mLoginWindowElement;

    public LoginPage(WebDriver driver) {
        super(driver);
        mLoginWindowElement = $(mLoginWindow);
    }

    public boolean isDisplayed() {
        return $(mLoginWindow).isDisplayed();
    }

    public void clickOnLoginButton(){

        FluentWait fluentWait = new FluentWait(mDriver)
                .withTimeout(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(1))
                .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                    {
                        add(StaleElementReferenceException.class);
                        add(NoSuchElementException.class);
                        add(TimeoutException.class);
                        add(InvalidElementStateException.class);
                        add(WebDriverException.class);
                    }}
                );
        fluentWait.until(ExpectedConditions.visibilityOf(mLoginWindowElement));

        WebElement element = mLoginWindowElement.findElement(signInButton);
        Actions actions = new Actions(mDriver);
        element.submit();
        actions.moveToElement(element).click().build().perform();
    }

    public void closePage(){
        print("Closing login page");
        FluentWait fluentWait = new FluentWait(mDriver)
                .withTimeout(Duration.ofSeconds(5))
                .withTimeout(Duration.ofSeconds(1))
                .ignoreAll(new ArrayList<Class<? extends Throwable>>() {
                    {
                        add(StaleElementReferenceException.class);
                        add(NoSuchElementException.class);
                        add(TimeoutException.class);
                        add(InvalidElementStateException.class);
                        add(WebDriverException.class);
                    }}
                );
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(mLoginWindow));
        WebElement closeButton = mDriver.findElement(mCloseButton);
        closeButton.click();
    }
}
