package com.academy.techcenture.saucelabs;

import com.academy.techcenture.saucelabs.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceLabsE2ETest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //browser configuration
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.get("https://www.saucedemo.com/");
    }


    @Test
    public void saucelabsE2ETest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();

        ProductPage productPage = new ProductPage(driver);
        productPage.verifyAbout();

        productPage.verifyProducts();

        productPage.sortProducts("lowToHigh");

        productPage.verifyFirstAndLastItems();

        productPage.verifyFirstProduct();

        CartPage cartPage = new CartPage(driver);
        cartPage.VerifyYourCart();

        CartInfoPage cartInfoPage = new CartInfoPage(driver);
        cartInfoPage.verifyCartInfo();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.verifyCardConfirmation();

        OverViewPage overViewPage = new OverViewPage(driver);
        overViewPage.verifyOverviewPage();

    }


    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}

