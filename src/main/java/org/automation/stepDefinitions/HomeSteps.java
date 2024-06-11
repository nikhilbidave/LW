package org.automation.stepDefinitions;

import org.automation.pages.AmazonHomePage;
import org.automation.common.AutomationManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class HomeSteps {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    @Before
    public void setUp() {
        driver = AutomationManager.getDriver();
        amazonHomePage = new AmazonHomePage(driver);
    }
    @After
    public void tearDown() {
        AutomationManager.quitDriver();
    }
    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        driver.get("http://www.google.com");
    }
    @When("I do something")
    public void i_do_something() {
        // amazonHomePage.productSearch();
    }
}
