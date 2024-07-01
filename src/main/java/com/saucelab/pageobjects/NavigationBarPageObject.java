package com.saucelab.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author vinaykumargupta
 * @Date 01/07/24
 */
public class NavigationBarPageObject {


    @FindBy(xpath = "//*[@id=\"menu_button_container\"]/div/div[2]/div[1]")
    public WebElement NavigationBar;

    @FindBy(xpath = "//*[@id=\"inventory_sidebar_link\"]")
    public WebElement btnAllItems;

    @FindBy(xpath = "//*[@id=\"about_sidebar_link\"]")
    public WebElement btnAbout;
    @FindBy(xpath = "//*[@id=\"logout_sidebar_link\"]")
    public WebElement btnLogout;


}
