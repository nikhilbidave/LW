package org.automation.pages;

import org.automation.common.GlobalParams;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

public class AmazonHomePage extends BasePage {
    @FindBy(id = "twotabsearchtextbox")
    private WebElement textProductSearch;
    @FindBy(id = "nav-search-submit-button")
    private WebElement btnSearch;
    @FindBy(id = "nav-cart")
    private WebElement btnOpenCart;

    public AmazonHomePage(WebDriver driver) {

        super(driver);
    }

    public void openAmazon(){
        driver.get("http://www.amazon.in");
    }

    public void productSearch(String productName) {
        textProductSearch.clear();
        textProductSearch.sendKeys(productName);
        GlobalParams.reqFields.put("PRODUCT_NAME", productName);
        btnSearch.click();
    }

    public void openProductDetailsPage(int product_number){
        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@class, 's-result-item') and not(contains(@class, 'AdHolder')) and not(contains(@class, 'border'))]//div[contains(@class, 'title')]//a"));
        searchResults.get(product_number).click();
    }

    public void openCart(){
        wait.until(ExpectedConditions.visibilityOf(btnOpenCart));
        btnOpenCart.click();
        System.out.println("");
    }

}
