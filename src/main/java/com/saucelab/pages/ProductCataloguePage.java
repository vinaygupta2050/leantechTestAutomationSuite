package com.saucelab.pages;

import com.saucelab.factories.BasePage;
import com.saucelab.pageobjects.LoginPageObject;
import com.saucelab.pageobjects.ProductCataloguePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class ProductCataloguePage extends BasePage {

    private Logger log = Logger.getLogger(String.valueOf(ProductCataloguePage.class));
    private ProductCataloguePageObject productCataloguePageObject;
    public static int expectedCartSize=0;

    public static HashMap<String,Integer> cartInformation = new HashMap<String,Integer>();
    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        productCataloguePageObject = new ProductCataloguePageObject();
        PageFactory.initElements(driver,productCataloguePageObject);

    }

    public ProductCataloguePage onProductScreenVerifyTitle() {
        waitForElementToBeVisible(productCataloguePageObject.lblProductPageTitle, 50);
        log.info("Navigated To Product catalogue screen");
        log.info("Verifying screen title");
        Assert.assertTrue(productCataloguePageObject.lblProductPageTitle.isDisplayed());
        log.info("Verification Successfull!!!");
        return this;
    }

    /**
     * This method is used when we want to select number of product randomly
     * @param numberOfProductToSelected
     * @return
     */
    public ProductCataloguePage selectProductRandomly(int numberOfProductToSelected)
    {
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='inventory_list']/div"));
        log.info("Number of Product Listed on Page : "+productList.size());
        log.info("Number of product to be selected : "+numberOfProductToSelected);
        for(int i=1;i<=numberOfProductToSelected;i++)
        {
            WebElement product =driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]/div[2]/div[2]/button"));
            product.click();
            String productName = driver.findElement(By.xpath("//div[@class='inventory_list']/div["+i+"]/div[2]/div[1]/a/div")).getText().toString();
            log.info("Product Added to Cart :"+productName);
            cartInformation.put(productName,i);
        }
        return this;
    }

    /**
     * This method is used when we want to individual product by their names
     * @param productName
     * @return
     */
    public ProductCataloguePage selectProductByName(String productName)
    {
            driver.findElement(By.xpath("//div[text()='"+productName+"']/../../../div[2]/button[text()='Add to cart']")).click();
            log.info("Product Added To cart :"+productName);
            return this;
    }

    /**
     * This method is used when we want to select multiple product by their names
     * @param productList
     * @return
     */
    public ProductCataloguePage selectProductMultiple(List<String> productList)
    {
        for(String productName:productList){
            driver.findElement(By.xpath("//div[text()='"+productName+"']/../../../div[2]/button[text()='Add to cart']")).click();
            log.info("Product Added To cart :"+productName);
        }
        return this;
    }

    public ProductCataloguePage verifyNumberOfProductDisplayedOnCartButton()
    {
        log.info("Verifying Cart Size");
        int actualCartSize = Integer.parseInt(driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a/span")).getText().toString());
        log.info("Expected Cart Size :"+cartInformation.size());
        log.info("Actual Cart Size :"+actualCartSize);
        Assert.assertEquals(cartInformation.size(),actualCartSize);
        log.info("Verification Success");
        return this;
    }

    public CartPage clickOnCart()
    {
        log.info("Click on Cart Button");
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']/a/span")).click();
        return new CartPage(driver);
    }


}
