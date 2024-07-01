package com.saucelab.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class CartPageObject {

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement lblCartTitle;
    @FindBy(xpath = "//*[@id=\"checkout\"]")
    public WebElement btnCheckout;
}
