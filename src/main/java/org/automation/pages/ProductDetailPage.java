package org.automation.pages;

import org.automation.common.GlobalParams;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage extends BasePage {
    double allProductsPrice = 0.0;
    @FindBy(xpath = "//div[@id='desktop_qualifiedBuyBox']//input[@id='add-to-cart-button']")
    private WebElement btnAddToCart;
    @FindBy(id = "attach-close_sideSheet-link")
    private  WebElement btnClose;
    @FindBy(xpath = "//div[contains(@id,'corePrice')]//span[contains(@class, 'a-price')]//span[@class='a-price-whole']")
    private WebElement productPrice;

    public ProductDetailPage(WebDriver driver) {

        super(driver);
    }

    public void addProductToCart() {
        String product_name = GlobalParams.reqFields.get("PRODUCT_NAME");
        int initialCartValue = Integer.parseInt(GlobalParams.reqFields.get("TOTAL_CART_VALUE"));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        String price = productPrice.getText();
        int price2 = Integer.parseInt(price.replace(",",""));
        String tempCartValue = Integer.toString(initialCartValue + price2);
        GlobalParams.reqFields.put("TOTAL_CART_VALUE", tempCartValue);
        GlobalParams.reqFields.put(product_name.toUpperCase()+"_PRICE", Integer.toString(price2));
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(btnAddToCart));
        btnAddToCart.click();
        wait.until(ExpectedConditions.visibilityOf(btnClose));
        // btnClose.click();
        // wait.until(ExpectedConditions.invisibilityOf(btnClose));
    }
}
