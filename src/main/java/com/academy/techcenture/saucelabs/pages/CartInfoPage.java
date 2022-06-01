package com.academy.techcenture.saucelabs.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CartInfoPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(id = "first-name")
    private WebElement firstnameInputLink;

    @FindBy(id = "last-name")
    private WebElement lastnameInputLink;

    @FindBy(id = "postal-code")
    private WebElement zipInputLCodeink;

    @FindBy(id = "continue")
    private WebElement continueBtn;



    public void verifyCartInfo(){

        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title is NOT correct");

        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String zipCode = faker.address().zipCode().substring(0, 5);

        Assert.assertTrue(firstnameInputLink.isDisplayed(), "First Name Input Link is NOT displayed");
        firstnameInputLink.clear();
        firstnameInputLink.sendKeys(firstName);

        Assert.assertTrue(lastnameInputLink.isDisplayed(), "Last Name Input Link is NOT displayed");
        lastnameInputLink.clear();
        lastnameInputLink.sendKeys(lastName);

        Assert.assertTrue(zipInputLCodeink.isDisplayed(), "Zip code Input Link is NOT displayed");
        zipInputLCodeink.clear();
        zipInputLCodeink.sendKeys(zipCode);

        Assert.assertTrue(continueBtn.isEnabled(), "Continue Btn is NOT enabled");
        continueBtn.click();






    }











}