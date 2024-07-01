package com.saucelab.factories;

import com.saucelab.utility.fileoperation.PropertiesFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class DriverFactory {
    private static PropertiesFileReader propertiesFileReader = new PropertiesFileReader();
    private static String RUN_ON = System.getProperty("runOn");
    private static String BROWSER = System.getProperty("browser");
    private static final Logger log = Logger.getLogger(DriverFactory.class);
    public static RemoteWebDriver getDriver() {
        if (RUN_ON == null) {
            BROWSER = "CHROME";
            RUN_ON = "LOCALHOST";
        }
        switch (RUN_ON) {
            case "LOCALHOST":
                return getLocalWebDriver();
            case "DOCKER_SELENIUM_GRID":
                return getRemoteWebDriver();
            default:
                throw new IllegalStateException(String.format("%s is not a valid HOST choice. Pick your HOST from %s." + RUN_ON));
        }
    }
    private static synchronized RemoteWebDriver getLocalWebDriver() {
        switch (BROWSER.toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().clearDriverCache().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                throw new IllegalStateException(String.format("%s is not a valid browser choice. Pick your browser from %s." + BROWSER));
        }
    }
    private static synchronized RemoteWebDriver getRemoteWebDriver() {
        String GRID_URL=propertiesFileReader.getSeleniumGridUrl();
        try {
            log.info("GRID Url :" + GRID_URL);
            return new RemoteWebDriver(new URL(GRID_URL),CapabilitiesFactory.getCapabilities(BROWSER));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
