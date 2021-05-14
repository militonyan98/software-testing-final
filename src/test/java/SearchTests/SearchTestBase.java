package SearchTests;

import base.BaseTests;
import pages.search.SearchPage;
import utils.Helpers;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class SearchTestBase extends BaseTests {
    protected List<SearchPage.SearchItem> TestBrandSelection(SearchPage searchPage) throws InterruptedException {
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

    protected List<SearchPage.SearchItem> TestResetInner(SearchPage page) throws InterruptedException {
        var elementsBeforeReset = page.getItems();

        page.refinementBar().resetAll();

        Thread.sleep(1500);

        var elementsAfterReset = page.getItems();

        var same = Helpers.Same(elementsBeforeReset, elementsAfterReset);

        assertFalse(same, "Reset filter took no effect");

        return elementsAfterReset;
    }
}
