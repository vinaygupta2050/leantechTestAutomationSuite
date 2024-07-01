package com.saucelab.pages;

import com.saucelab.factories.BasePage;
import com.saucelab.pageobjects.NavigationBarPageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author vinaykumargupta
 * @Date 01/07/24
 */
public class NavigationBar extends BasePage {
    private NavigationBarPageObject navigationBarPageObject;
    private Logger log = Logger.getLogger(String.valueOf(NavigationBar.class));

    public NavigationBar(WebDriver driver) {
        super(driver);
        this.navigationBarPageObject = new NavigationBarPageObject();
        PageFactory.initElements(driver, navigationBarPageObject);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void logout() {
        log.info("Clicked on Logout Button");
        navigationBarPageObject.btnLogout.click();
        log.info("Logout Successfull!!");
    }
}
