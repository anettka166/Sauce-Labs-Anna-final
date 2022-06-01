package com.academy.techcenture.saucelabs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuLink;


    @FindBy(xpath = "//a[@class='bm-item menu-item']")
    private List<WebElement> menuItems;

    @FindBy(className = "product_sort_container")
    private WebElement sortComponent;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> inventoryItemPrices;

    @FindBy(xpath = "//div[contains(text(),'Sauce Labs Onesie')]")
    private WebElement firstProductTitle;

    @FindBy(xpath = "//div[contains(text(),'Rib snap')]")
    private WebElement firstProductDescription;

    @FindBy(xpath = "//div[@class='inventory_details_price']")
    private WebElement firstProductPrice;

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    private WebElement firstItemAddToCartBtn;

    @FindBy(id = "remove-sauce-labs-onesie")
    private WebElement firstItemRemoveFromCartBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;





    public void verifyAbout() throws InterruptedException {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Swag Labs", "Page title is NOT correct");
        menuLink.click();
        wait.until(ExpectedConditions.visibilityOf(menuItems.get(1)));
        Assert.assertTrue(menuItems.get(1).isDisplayed(), "ABOUT menu item is NOT displayed");
        menuItems.get(1).click();
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "Cross Browser Testing, Selenium Testing, Mobile Testing | Sauce Labs",
                "About page Title is NOT correct");
        driver.navigate().back();
    }

    public void verifyProducts(){

        Assert.assertTrue(inventoryItemPrices.size() == 6, "6 products are NOT displayed");
    }

    public void sortProducts(String sort){
        Assert.assertTrue(sortComponent.isDisplayed(), "Sort products tab is NOT displayed ");
        sortComponent.click();
        Select select = new Select(sortComponent);
        switch (sort){
            case "lowToHigh":
                select.selectByValue("lohi");
                break;
            case "highToLow":
                select.selectByValue("hilo");
                break;
            case "atoZ":
                select.selectByValue("az");
                break;
            case "ztoA":
                select.selectByValue("za");
                break;
        }
           }

    public void verifyFirstAndLastItems(){
        //TODO verify the first and last item prices

       String firstPrice = inventoryItemPrices.get(0).getText().trim();
       Assert.assertEquals(firstPrice, "$7.99", "The first of the 1st item is NOT $7.99");

       String lastPrice = inventoryItemPrices.get(inventoryItemPrices.size()-1).getText().trim();
       Assert.assertEquals(lastPrice, "$49.99", "The first of the Last item is NOT $49.99");

    }

    public void verifyFirstProduct(){
        //TODO click on first product

       firstProductTitle.click();

       Assert.assertTrue(firstProductTitle.isDisplayed(), "1st Item title is NOT displayed");
       Assert.assertEquals(firstProductTitle.getText(), "Sauce Labs Onesie", "Title of the 1st item is NOT correct");

       Assert.assertTrue(firstProductDescription.isDisplayed(), "1st item description is NOT displayed");
       Assert.assertTrue(firstProductDescription.getText().contains("Rib"), "The description of the 1st Item is NOT correct");

       Assert.assertTrue(firstProductPrice.isDisplayed(), "1st item price is NOT displayed");
       Assert.assertEquals(firstProductPrice.getText(), "$7.99", "Price of the 1st Item is NOT $7.99");

       Assert.assertTrue(firstItemAddToCartBtn.isDisplayed(), "1st Add To Cart Btn is NOT displayed");
       firstItemAddToCartBtn.click();

       Assert.assertTrue(firstItemRemoveFromCartBtn.isDisplayed(), "1st item Remove From Cart Btn is NOT displayed");

       Assert.assertTrue(cartIcon.isDisplayed(), "Shopping Cart Icon is NOT displayed");
       cartIcon.click();










    }


}

