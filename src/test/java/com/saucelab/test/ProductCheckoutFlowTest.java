package com.saucelab.test;

import com.saucelab.factories.BaseTest;
import com.saucelab.pages.LoginPage;
import org.testng.annotations.Test;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class ProductCheckoutFlowTest extends BaseTest {
    @Test(enabled = true, description = "Verify that a customer can successfully select 3 random items and complete the checkout process on the application.",
            groups = "Task1")
    public void verifyProductCheckoutCustomerJourney() {

        LoginPage loginPage = new LoginPage(driver.get());
        loginPage.enterCredentialsAndClickOnLogin()
                .onProductScreenVerifyTitle()
                .selectProductRandomly(3)
                .verifyNumberOfProductDisplayedOnCartButton()
                .clickOnCart()
                .verifyListOfProductInCart()
                .clickOnCheckout()
                .fillCustomerInformation()
                .clickOnContinue()
                .verifyCheckoutSummaryInformation()
                .clickOnHamburgerButton()
                .logout();
    }
}
