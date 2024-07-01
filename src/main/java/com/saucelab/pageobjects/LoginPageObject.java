package com.saucelab.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class LoginPageObject {
    @FindBy(xpath = "//*[@id='user-name']")
    public WebElement txtUserName;
    @FindBy(xpath = "//*[@id='password']")
    public WebElement txtPassword;
    @FindBy(xpath = "//*[@id='login-button']")
    public WebElement btnLogin;
}
