package pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.POMBase;

import java.security.Key;

public class SearchBar extends POMBase {

    By mSearchBoxBy = By.id("site_search_input");

    WebElement mSearchElement;

    public SearchBar(WebDriver driver) {
        super(driver);
        mSearchElement = $(mSearchBoxBy);
    }

    public SearchPage Search(String query){
        Clean();
        mSearchElement.sendKeys(query);
        mSearchElement.sendKeys(Keys.RETURN);
        return new SearchPage(mDriver);
    }

    public void Clean(){
        mSearchElement.sendKeys(Keys.CONTROL, "A");
        mSearchElement.sendKeys(Keys.DELETE);
    }
}
