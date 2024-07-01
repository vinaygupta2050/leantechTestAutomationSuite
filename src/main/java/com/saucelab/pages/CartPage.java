package com.saucelab.pages;

import com.saucelab.factories.BasePage;
import com.saucelab.pageobjects.CartPageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.saucelab.pages.ProductCataloguePage.cartInformation;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class CartPage extends BasePage {
    private Logger log = Logger.getLogger(String.valueOf(CartPage.class));
    private CartPageObject cartPageObject;

    public static HashMap<String, Float> cartDetailMap = new HashMap<>();

    public CartPage(WebDriver driver) {
        super(driver);
        cartPageObject = new CartPageObject();
        PageFactory.initElements(driver, cartPageObject);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public CartPage NavigateToCartScreen() {
        waitForElementToBeVisible(cartPageObject.lblCartTitle, 50);
        log.info("Navigated To Cart screen");
        log.info("Verifying screen title");
        Assert.assertTrue(cartPageObject.lblCartTitle.isDisplayed());
        log.info("Verification Successfull!!!");
        return this;
    }

    public CartPage verifyListOfProductInCart() {
        log.info("Verifying all the selected product are present in the cart");
        List<WebElement> cartDetails = driver.findElements(By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[@class='cart_item']"));
        buildMapForCartDetails(cartDetails);
        List<WebElement> listOfAddedProduct = driver.findElements(By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[@class='cart_item']/div/a/div"));
        List<String> expectedListOfProduct = Collections.list(Collections.enumeration(cartInformation.keySet()));
        List<String> actualListOfProduct = new ArrayList<>();
        for (int i = 0; i < listOfAddedProduct.size(); i++) {
            actualListOfProduct.add(listOfAddedProduct.get(i).getText().toString());
        }
        Collections.sort(expectedListOfProduct);
        Collections.sort(actualListOfProduct);
        Assert.assertEquals(actualListOfProduct, expectedListOfProduct);
        log.info("Verification Successfull!!");
        return this;
    }

    public CheckoutPage clickOnCheckout() {
        log.info("Clicking on checkout button");
        cartPageObject.btnCheckout.click();
        return new CheckoutPage(driver);
    }

    public static void buildMapForCartDetails(List<WebElement> cartDetails) {
        for (WebElement ele : cartDetails) {

            String[] productList = ele.getText().toString().split("\n");
            String productName = productList[1];
            Float price = Float.parseFloat(productList[3].substring(1));
            cartDetailMap.put(productName, price);
        }
    }


}
