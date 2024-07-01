package com.saucelab.factories;

import com.saucelab.utility.fileoperation.PropertiesFileReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;


import java.util.concurrent.TimeUnit;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class BaseTest {
    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    public Logger log;
    private String baseUrl = "";

    public static final String RUN_ON_LOCALHOST = "LOCALHOST";
    public static final String RUN_ON_GRID = "DOCKER_SELENIUM_GRID";
    public static final String BROWSER_CHROME = "CHROME";

    @BeforeSuite(alwaysRun = true)
    public void suiteStartSetup() {
        log = Logger.getLogger(BaseTest.class);
        PropertyConfigurator.configure("src/test/resources/config/logger/log4j.properties");
        setLocalEnvironment();
        log.info("Test Run on :" + System.getProperty("runOn"));
        log.info("Environment Loaded :" + System.getProperty("env"));
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        PropertiesFileReader reader = new PropertiesFileReader();
        baseUrl = reader.getAppUrl();
        driver.set(DriverFactory.getDriver());
        driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get().manage().window().maximize();
        driver.get().get(baseUrl);
        log.info("Opening Url:" + baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
    public void setLocalEnvironment() {
        System.setProperty("env", "QA");
        System.setProperty("runOn", RUN_ON_LOCALHOST);
        System.setProperty("browser", BROWSER_CHROME);
    }
}
