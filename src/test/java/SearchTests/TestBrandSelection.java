package SearchTests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.search.SearchPage;
import utils.Helpers;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class TestBrandSelection extends SearchTestBase {
    @Test
    public void TestBrandSelectionFunctionality(){
        //checking for login popup
        var loginPage = homePage.login();
        if(loginPage.isDisplayed()){
            loginPage.closePage();
        }

        String query = "gloss";

        SearchPage sp = homePage.searchBar().Search(query);

        try
        {
            TestBrandSelection(sp);
        }
        catch (Exception ignored)
        {

        }
    }


}
