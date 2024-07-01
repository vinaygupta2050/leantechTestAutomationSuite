package com.saucelab.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class ProductCataloguePageObject {

    @FindBy(xpath = "//span[@class='title']")
    public WebElement lblProductPageTitle;

    //div[text()='Sauce Labs Backpack']/../../../div[2]/button[text()='Add to cart']
}
