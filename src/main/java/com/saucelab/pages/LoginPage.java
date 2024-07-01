package com.saucelab.pages;

import com.saucelab.factories.BasePage;
import com.saucelab.pageobjects.LoginPageObject;
import com.saucelab.utility.fileoperation.PropertiesFileReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class LoginPage extends BasePage {

    private LoginPageObject loginPageObject;
    private PropertiesFileReader propertiesFileReader = new PropertiesFileReader();

    private Logger log = Logger.getLogger(String.valueOf(LoginPage.class));


    public LoginPage(WebDriver driver) {
        super(driver);
        loginPageObject = new LoginPageObject();
        PageFactory.initElements(driver,loginPageObject);
    }

    public ProductCataloguePage enterCredentialsAndClickOnLogin()
    {
        loginPageObject.txtUserName.sendKeys(propertiesFileReader.getUserName());
        log.info("Enter user name as :"+propertiesFileReader.getUserName());
        loginPageObject.txtPassword.sendKeys(propertiesFileReader.getPassword());
        log.info("Entering password..");
        loginPageObject.btnLogin.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return new ProductCataloguePage(driver);
    }
}
