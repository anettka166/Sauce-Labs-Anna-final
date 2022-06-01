package com.academy.techcenture.saucelabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OrderConfirmationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(className = "title")
    private WebElement checkOutOverviewHeader;

    @FindBy(xpath = "//div[contains(text(),'SauceCard')]")
    private  WebElement  paymentInfo;

    @FindBy(xpath = "//div[contains(text(),'PONY')]")
    private WebElement shippingInfo;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemSubTotalPrice;

    @FindBy(className = "summary_tax_label")
    private WebElement itemTax;

    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(id = "finish")
    private WebElement finishBtn;

    public void verifyCardConfirmation() {

        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title is NOT correct");

        Assert.assertTrue(checkOutOverviewHeader.isDisplayed(), "Checkout Overview header is NOT displayed");

        Assert.assertTrue(paymentInfo.isDisplayed(), "Payment information is NOT displayed");
        String [] cardNumber = paymentInfo.getText().split("#");
        System.out.println(cardNumber[1]);

        Assert.assertTrue(shippingInfo.isDisplayed(), "Shipping information is NOT displayed");
        Assert.assertEquals(shippingInfo.getText(), "FREE PONY EXPRESS DELIVERY!","Shipping information is NOT correct" );

        Assert.assertTrue(itemSubTotalPrice.isDisplayed(), "Item's total price is NOT displayed");
        String [] itemSubTotalPriceArray = itemSubTotalPrice.getText().split("\\$");
        Assert.assertEquals(itemSubTotalPriceArray[1], "7.99", "Subtotal Price is NOT correct");
        double itemSubtotalPriceDouble =  Double.parseDouble(itemSubTotalPriceArray[1]);

        Assert.assertTrue(itemTax.isDisplayed(), "Item's tax is NOT displayed");
        String [] itemTaxArray = itemTax.getText().split("\\$");
        Assert.assertEquals(itemTaxArray[1], "0.64", "Item's tax is NOT correct");
        double itemTaxDouble =  Double.parseDouble(itemTaxArray[1]);

        Assert.assertTrue(totalPrice.isDisplayed(), "Item's total price is NOT displayed");
        String [] totalPriceArray = totalPrice.getText().split("\\$");
        Assert.assertEquals(totalPriceArray[1], "8.63", "Item's total price is NOT correct");
        double totalPriceDouble =  Double.parseDouble(totalPriceArray[1]);

        double manualTotalPrice = itemSubtotalPriceDouble + itemTaxDouble;
        Assert.assertEquals(totalPriceDouble, manualTotalPrice, "Total price from the website and manual counting do NOT match");

        Assert.assertTrue(finishBtn.isEnabled(), "Finish Btn is NOT enabled");
        finishBtn.click();































    }

}
