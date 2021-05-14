package SearchTests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.search.SearchPage;
import utils.Helpers;

import java.util.List;

import static org.testng.Assert.*;

public class TestResetFilters extends SearchTestBase {
    @Test
    public void TestReset(){
        //checking for login popup
        var loginPage = homePage.login();
        if(loginPage.isDisplayed()){
            loginPage.closePage();
        }

        String query = "gloss";

        SearchPage sp = homePage.searchBar().Search(query);

        var itemsBeforeFilter = sp.getItems();

        try
        {
            TestBrandSelection(sp);

            var elementsAfterReset = TestResetInner(sp);

            var same = Helpers.Same(elementsAfterReset, itemsBeforeFilter);

            assertTrue(same,"Different results before and after reset");

        }
        catch(Exception inner){}
    }

}
