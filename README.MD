# Lean Tech - WebApp Test Automation Framework

## Getting Started

Below is the project structure
```
|-- README.md
|-- leantechTestSuite.xml
|-- logs
|   `-- logfile.log
|-- pom.xml
|-- screenshot
`-- src
    |-- main
    |   |-- java
    |   |   `-- com
    |   |       `-- saucelab
    |   |           |-- factories
    |   |           |   |-- BasePage.java
    |   |           |   |-- BaseTest.java
    |   |           |   |-- CapabilitiesFactory.java
    |   |           |   `-- DriverFactory.java
    |   |           |-- listeners
    |   |           |   `-- TestListeners.java
    |   |           |-- pageobjects
    |   |           |   |-- CartPageObject.java
    |   |           |   |-- CheckoutPageObject.java
    |   |           |   |-- LoginPageObject.java
    |   |           |   |-- NavigationBarPageObject.java
    |   |           |   `-- ProductCatalaguePageObject.java
    |   |           |-- pages
    |   |           |   |-- CartPage.java
    |   |           |   |-- CheckoutPage.java
    |   |           |   |-- LoginPage.java
    |   |           |   |-- NavigationBar.java
    |   |           |   `-- ProductCataloguepage.java
    |   |           |-- testData
    |   |           `-- utility
    |   |               `-- fileoperation
    |   |                   `-- PropertiesFileReader.java
    |   `-- resources
    `-- test
        |-- java
        |   `-- com
        |       `-- saucelab
        |           `-- test
        |               `-- ProductCheckoutFlowTest.java
        `-- resources
            `-- config
                |-- env
                |   `-- QA.properties
                |-- logger
                |   `-- log4j.properties
                `-- testRunner
                    `-- SeleniumGrid.properties
```
Description
-   factories : This directory is the heart of framework and it has some major component which drives the whole framework
-   listeners : This consist of all the test listeners which needs to be executed on certain actions
-   pageobject: In this package all the web elements of application page are stored.
-   pages : In this package all the application pages and their respective actions are written
-   utils : In this package contains different utility which we can use while we are writing our automation test like wait function, Different file operation
-   test/resources : This consist of config.properties file where all the application url and test user are present and testng.xml file which we use to run our automation test
-   tests     : This directory where we write our test.
-   screenshot : This directory is responsible for storing failed test case screenshot after run
-   logs : This directory is responsible for storing failed test case screenshot after run
### Prerequisites

Please download below applications and jar before we run our framework

```
-   JDK 1.8 or higher
-   maven
```

## Writing Test

Once you have finished your configuration you can begin writing your test by extending BaseTest to your respective test. Below is the sample code.
```
    @Test(enabled = true, description = "Verify that a customer can successfully select 3 random items and complete the checkout process on the application.",
            groups = "Task1")
    public void veeifyProductCheckoutCustomerJourney() {

        LoginPage loginPage = new LoginPage(driver.get());
        loginPage.enterCredentialsAndClickOnLogin()
                .onProductScreenVerifyTitle()
                .selectProductRandomly(8)
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

```

## Running the tests

To Run from IDE
-   Simply navigate to "testng.xml".
    Right click on the file and select option Run to the test

To Run from command-line
-   In the project directory use below command to run the test
```
mvn clean test -Denv="QA" -DrunOn="LOCALHOST" -Dbrowser="CHROME"
```

## After run of test
Below directory are usefull after running the test
- target/surefire-reports/index.html : Under this directory we can find plain testng reports
- screenshot: All the screenshot for failed test can be found here

### Break down into end to end tests

The scenarios which I have tried to cover and present in below files
- veeifyProductCheckoutCustomerJourney
    - Verify successful Login
    - Verify Product selection
    - Compare Number of Product Selected vs Number of Product present in cart along with their name
    - Verify checkout summary screen - Name and Number of Product, Calculation of final amount
    - Logout

```
-   Verify that a customer can successfully select 3 random items and complete the checkout process on the application.
```

### And coding style tests

I have used testNG and its annotation to write the automation test

### Execution Logs:

Execution Video:


Logs:


Reports:

Note: Considering Time frame I have use plain testNG reports but we can also use Extent or Allure reports for deep analysis at one place
## Authors

* **Vinaykumar Gupta**