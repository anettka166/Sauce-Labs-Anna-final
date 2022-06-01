package com.academy.techcenture.saucelabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OverViewPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OverViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(className = "complete-header")
    private WebElement thankYouForYourOrderLogo;

    @FindBy(className = "complete-text")
    private WebElement yourOrderDispatched;

    @FindBy(className = "pony_express")
    private WebElement image;

    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuBtn;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logOutBtn;






    public void verifyOverviewPage() throws InterruptedException {

        Assert.assertTrue(thankYouForYourOrderLogo.isDisplayed(), "Thank you for your order- Logo NOT displayed");
        Assert.assertTrue(yourOrderDispatched.isDisplayed(), "Your order was dispatched- NOT displayed");
        Assert.assertTrue(image.isDisplayed(), "Image- NOT displayed");

        Assert.assertTrue(backHomeBtn.isEnabled(), "Bach home Btn is NOT enabled");
        backHomeBtn.click();

        menuBtn.click();

        logOutBtn.click();

        Thread.sleep(3000);


    }


}
