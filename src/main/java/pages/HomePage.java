package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Account.LoginPage;
import pages.base.POMBase;
import pages.search.SearchBar;
import pages.search.SearchPage;

public class HomePage extends POMBase {



    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(){
        return new LoginPage(mDriver);
    }

    public SearchBar searchBar(){
        return new SearchBar(mDriver);
    }
}
