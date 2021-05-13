package SearchTests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.search.SearchPage;

import java.util.Locale;

import static org.testng.Assert.assertTrue;


public class SearchTest extends BaseTests {
    @Test
    public void TestSearching(){

        //checking for login popup
        var loginPage = homePage.login();
        if(loginPage.isDisplayed()){
            loginPage.closePage();
        }

        //searching
        String[] queries = new String[]{"hello", "shampoo", "gloss"};
        for (int i = 0, queriesLength = queries.length; i < queriesLength; i++) {
            String query = queries[i];
            var searchPage = homePage.searchBar().Search(query);

            //scrolling down to load some of the lazy loaded elements
            windowManager.scrollDown();
            windowManager.scrollDown();

            //getting elements
            var items = searchPage.getItems();


            //what percent should match the query exactly
            double threshold = 0.8;


            int numberOfMatch = 0;
            for (var item : items) {
                if (item.getItemName().toLowerCase(Locale.ROOT).contains(query)
                        || item.getBrandName().toLowerCase(Locale.ROOT).contains(query))
                    numberOfMatch++;
            }

            System.out.println("Number of match : " + numberOfMatch);
            System.out.println("Target to match: " + (int) (threshold * items.size()));

            assertMoreOrEquals(numberOfMatch, (int) (threshold * items.size()), "Less than 80% of items match query: " + query);
        }


    }

    @Test
    public void TestBrandSelectionFunctionality(){
        //checking for login popup
        var loginPage = homePage.login();
        if(loginPage.isDisplayed()){
            loginPage.closePage();
        }

        String query = "gloss";

        SearchPage sp = homePage.searchBar().Search(query);

        TestBrandSelection(sp);
    }

    private void TestBrandSelection(SearchPage searchPage){
        var elements = searchPage.refinementBar().getRefinementFilters();
    }
}
