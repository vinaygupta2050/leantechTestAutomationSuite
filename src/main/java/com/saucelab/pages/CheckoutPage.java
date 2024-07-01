package com.saucelab.pages;

import com.github.javafaker.Faker;
import com.saucelab.factories.BasePage;
import com.saucelab.pageobjects.CheckoutPageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class CheckoutPage extends BasePage {

    private Logger log = Logger.getLogger(String.valueOf(CheckoutPage.class));
    private CheckoutPageObject checkoutPageObject;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        checkoutPageObject = new CheckoutPageObject();
        PageFactory.initElements(driver, checkoutPageObject);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public CheckoutPage fillCustomerInformation() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String zipCode = faker.address().zipCode();
        checkoutPageObject.txtFirstName.sendKeys(firstName);
        log.info("Entering firstname as : " + firstName);
        checkoutPageObject.txtLastName.sendKeys(lastName);
        log.info("Entering lastname as : " + lastName);
        checkoutPageObject.txtZipCode.sendKeys(zipCode);
        log.info("Entering zipcode as :" + zipCode);
        return this;
    }

    public CheckoutPage clickOnContinue() {
        checkoutPageObject.btncCheckout.click();
        log.info("Clicking on Continue button");
        return this;
    }

    public CheckoutPage verifyCheckoutSummaryInformation() {
        log.info("Verifying checkout summary screen");
        float total = 0;
        List<WebElement> checkoutDetails = driver.findElements(By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[@class='cart_item']"));
        Assert.assertEquals(CartPage.cartDetailMap, getCheckoutSummaryCartDetails(checkoutDetails));
        float expectedTotalSum = 0.0f;
        for (Float value : getCheckoutSummaryCartDetails(checkoutDetails).values()) {
            expectedTotalSum += value;
        }
        log.info("Verify total amount calculated");
        Float actualTotalSum  = Float.parseFloat(driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText().toString().split(":")[1].trim().substring(1));
        Float taxApplicable  = Float.parseFloat(driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[@class='summary_tax_label']")).getText().toString().split(":")[1].trim().substring(1));
        Float actualFinalTotalSum = actualTotalSum+taxApplicable;
        Float expectedFinalTotalSum = expectedTotalSum+taxApplicable;
        log.info("Expected Total Sum :$"+expectedFinalTotalSum);
        log.info("Actual Total Sum :$"+actualFinalTotalSum);
        Assert.assertEquals(actualFinalTotalSum, expectedFinalTotalSum);
        log.info("Verification Successfull!!");
        return this;
    }

    public static HashMap<String, Float> getCheckoutSummaryCartDetails(List<WebElement> cartDetails) {
        HashMap<String, Float> checkoutCartDetails = new HashMap<>();
        for (WebElement ele : cartDetails) {
            String[] productList = ele.getText().toString().split("\n");
            String productName = productList[1];
            Float price = Float.parseFloat(productList[3].substring(1));
            checkoutCartDetails.put(productName, price);
        }
        return checkoutCartDetails;
    }

    public NavigationBar clickOnHamburgerButton()
    {
        log.info("Clicked on Hambuger Button");
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        return new NavigationBar(driver);

    }
}
