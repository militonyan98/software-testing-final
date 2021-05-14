package CartTests;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.Cart.CartItem;
import pages.search.SearchPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import utils.Helpers;

import static org.testng.Assert.*;

public class AddToCartTest extends BaseTests {



    @Test
    public void AddToCartTest(){
        closeAfterFinishing = false;
        //checking for login popup
        var loginPage = homePage.login();
        if(loginPage.isDisplayed()){
            loginPage.closePage();
        }

        String query = "gloss";

        SearchPage sp = homePage.searchBar().Search(query);

        List<SearchPage.SearchItem> addedElements = new ArrayList<>();

        //adding 3 elements to the cart
        for(int i = 0; i<3; i++){
            var items = sp.getItems();
            var size = items.size();
            var index = Helpers.randomNumber(0, size-1);
            var targetItem = items.get(index);
            var productPage = targetItem.clickOnProduct();
            if(productPage==null)
            {
                i--;
                continue;
            }
            if(productPage.addToCart()){
                System.out.println("Added to cart: "+targetItem.toString());
                addedElements.add(targetItem);
            }
            windowManager.goBack();
        }

        var cartPage = homePage.goToCart();
        var cartItems = cartPage.getCartItems();
        for(var item : cartItems){
            System.out.println(item.toString());
        }

        assertTrue(CompareAddedListToCart(addedElements, cartItems));
    }

    public boolean CompareAddedListToCart(List<SearchPage.SearchItem> addedList, List<CartItem> cartItems){
        for (var addedItem : addedList){
            boolean found = false;
            for(var cartItem : cartItems){
                if(CompareCartItemToSearchItem(addedItem,cartItem))
                    found = true;
            }
            if(!found)
            {
                System.out.println("Cannot find "+addedItem.toString());
                return false;
            }

        }

        return true;
    }

    private boolean CompareCartItemToSearchItem(SearchPage.SearchItem searchItem, CartItem cartItem){
        return searchItem.getItemName().trim().equals(cartItem.getProductName().trim()) &&
                searchItem.getBrandName().trim().equals(cartItem.getBrandName().trim());
    }
}
