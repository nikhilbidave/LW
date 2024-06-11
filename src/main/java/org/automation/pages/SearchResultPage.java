package org.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {
    @FindBy(id = "q")
    private WebElement someElement;

    public SearchResultPage(WebDriver driver) {

        super(driver);
    }

    public void doSomething() {

        someElement.sendKeys("Python");
    }
}
