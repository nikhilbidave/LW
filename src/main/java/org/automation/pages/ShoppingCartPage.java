package org.automation.pages;

import io.cucumber.java.sl.In;
import org.automation.common.GlobalParams;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.text.GapContent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCartPage extends BasePage {
    @FindBy(xpath = "//div[@class='sc-badge-price']//span[contains(@class, 'sc-product-price')]")
    private WebElement productPrice;
    @FindBy(xpath = "//span[@id='sc-subtotal-amount-buybox']//span[contains(@class, 'sc-price')]")
    private WebElement cartValue;

    public ShoppingCartPage(WebDriver driver) {

        super(driver);
    }

    public void verifyProductPrice() {
        String productName = GlobalParams.reqFields.get("PRODUCT_NAME");
        List<Integer> priceOnCartPage = new ArrayList<>();
        List<WebElement> productPriceElements = driver.findElements(By.xpath("//div[@class='sc-badge-price']//span[contains(@class, 'sc-product-price')]"));
        for(WebElement element: productPriceElements){
            priceOnCartPage.add((int)Double.parseDouble(element.getText().trim().replace(",","")));
        }
        int expectedProductValue = Integer.parseInt(GlobalParams.reqFields.get(productName.toUpperCase()+"_PRICE"));
        for (int i = 0; i < priceOnCartPage.size(); i++){
            assert priceOnCartPage.get(i).intValue() == expectedProductValue;
        }
    }

    public void verifyCartValue(){
        String totalCartValue = cartValue.getText();
        int expectedCartValue = Integer.parseInt(GlobalParams.reqFields.get("TOTAL_CART_VALUE"));
        assert expectedCartValue == (int)Double.parseDouble(totalCartValue.trim().replace(",",""));
        System.out.println(totalCartValue);
    }
}
