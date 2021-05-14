package SearchTests;

import org.testng.annotations.Test;
import pages.search.SearchPage;

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
