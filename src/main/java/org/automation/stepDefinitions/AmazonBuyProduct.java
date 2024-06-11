package org.automation.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.common.GlobalParams;
import org.automation.pages.ProductDetailPage;
import org.automation.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.automation.common.AutomationManager;
import org.automation.pages.AmazonHomePage;

import java.util.Arrays;
import java.util.List;

public class AmazonBuyProduct {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private ProductDetailPage productDetailPage;
    private ShoppingCartPage shoppingCartPage;

    @Before
    public void setUp() {
        driver = AutomationManager.getDriver();
        amazonHomePage = new AmazonHomePage(driver);
        productDetailPage = new ProductDetailPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }
    @After
    public void tearDown() {
        AutomationManager.quitDriver();
    }

    @Given("Amazon URL and Product List")
    public void openAmazon() {
        amazonHomePage.openAmazon();
    }

    @When("Searched for {string} and added {string} product to cart")
    public void userOpenProductPageAndAddProductToCart(String productName, String productIndex) {
        GlobalParams.reqFields.put("TOTAL_CART_VALUE", "0");
        List<String> productList;
        List<String> searchResultIndex;
        if(productName.contains(",")){
            productList = Arrays.asList(productName.split(","));
            searchResultIndex = Arrays.asList(productIndex.split(","));
            for(int i=0;i<productList.size();i++){
                amazonHomePage.productSearch(productList.get(i));
                amazonHomePage.openProductDetailsPage(Integer.parseInt(searchResultIndex.get(i).trim()));
                productDetailPage.switchToWindow();
                productDetailPage.addProductToCart();
                productDetailPage.closeCurrentWindow();
            }
        }else{
            amazonHomePage.productSearch(productName);
            amazonHomePage.openProductDetailsPage(Integer.parseInt(productIndex));
            productDetailPage.switchToWindow();
            productDetailPage.addProductToCart();
        }
    }

    @Then("Verify product is added to cart")
    public void verifyProductIsAddedToCart() throws InterruptedException {
        amazonHomePage.closeCurrentWindow();
        amazonHomePage.openCart();
    }

    @And("Verify product price is identical")
    public void verifyProductPriceIsIdentical() {
        shoppingCartPage.verifyProductPrice();
        shoppingCartPage.verifyCartValue();

    }

    @And("Verify subtotal is identical to product page")
    public void verifySubtotalIsIdenticalToProductPage() {

    }
}
