package com.saucelab.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class CheckoutPageObject {

    @FindBy(xpath = "//*[@id=\"first-name\"]")
    public WebElement txtFirstName;
    @FindBy(xpath = "//*[@id=\"last-name\"]")
    public WebElement txtLastName;

    @FindBy(xpath = "//*[@id=\"postal-code\"]")
    public WebElement txtZipCode;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    public WebElement btncCheckout;

}
