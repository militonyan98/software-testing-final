package SearchTests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.search.SearchPage;

import java.util.List;
import java.util.Locale;

import utils.Helpers;

import static org.testng.Assert.*;


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

        try
        {
            TestBrandSelection(sp);
        }
        catch (Exception ignored)
        {

        }
    }

    private List<SearchPage.SearchItem> TestBrandSelection(SearchPage searchPage) throws InterruptedException {
        var brandFilter = searchPage.refinementBar().getBrandFilter();
        var elements = brandFilter.getOptions();

        System.out.println("Number of brands " + elements.size());

        var element = elements.get(0);
        String name = element.getBrandName();
        System.out.println("Checking for brand name: "+name);
        element.select();
        var items = searchPage.getItems();
        for(var result : searchPage.getItems()) {
            assertEquals(result.getBrandName(), name, "Failed for brand name: " + name);
        }
        return items;
    }

    private List<SearchPage.SearchItem> TestResetInner(SearchPage page) throws InterruptedException {
        var elementsBeforeReset = page.getItems();

        page.refinementBar().resetAll();

        Thread.sleep(1500);

        var elementsAfterReset = page.getItems();

        var same = Helpers.Same(elementsBeforeReset, elementsAfterReset);

        assertFalse(same, "Reset filter took no effect");

        return elementsAfterReset;
    }

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
