package com.saucelab.utility.fileoperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author vinaykumargupta
 * @Date 30/06/24
 */
public class PropertiesFileReader {
    Properties properties = new Properties();

    public PropertiesFileReader() {
        loadApplicationProperties();
        loadSeleniumGridProperty();
    }

    private void loadApplicationProperties() {
        String homePath = System.getProperty("user.home");
        String fileName = "test" + System.currentTimeMillis();
        String filePath = homePath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator  + "config" + File.separator  + "env" + File.separator;
        try {
            String env = System.getProperty("env");
            if (env == null) env = "QA";
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/env/" + env + ".properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void loadSeleniumGridProperty() {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/testRunner/SeleniumGrid.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String readProperty(String key) {
        return properties.getProperty(key);
    }

    public String getAppUrl() {
        return readProperty("appUrl");
    }

    public String getUserName() {
        return readProperty("username");
    }

    public String getPassword() {
        return readProperty("password");
    }

    public String getSeleniumGridUrl() {
        return readProperty("GRID_URL");
    }
}
