package com.academy.techcenture.saucelabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(className = "title")
    private WebElement yourCartHeader;

    @FindBy(xpath = "//div[contains(text(),'1')]")
    private WebElement qty;

    @FindBy(id = "checkout")
    private WebElement checkOutBtn;



    public void VerifyYourCart() {

        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title is NOT correct");

        Assert.assertTrue(yourCartHeader.isDisplayed(), "Your Cart Header is NOT displayed");

        Assert.assertEquals(qty.getText(), "1", "QTY is NOT 1");


        Assert.assertTrue(checkOutBtn.isDisplayed(), "Check Out Btn is NOT displayed");
        Assert.assertTrue(checkOutBtn.isEnabled(), "Check Out Btn is enabled" );
        checkOutBtn.click();



    }





}
