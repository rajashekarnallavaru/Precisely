package com.precisely.automation.PageInteraction;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class SetupLocatorPath {
    Properties prop = new Properties();
    InputStream input = null;

    public SetupLocatorPath() {
    }

    public void loadPropertyFile(String propertyFileName) {
        try {
            input = new FileInputStream(propertyFileName);
            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getPropertyLocator(String elementName) {
        return getPropertyLocator("", elementName);
    }

    public String getPropertyLocator(String fileName, String elementName) {
        String property = null;
        try {
            if (fileName.equals("")) {
                return elementName;
            } else {
                loadPropertyFile(fileName);
                property = prop.getProperty(elementName);
                return property;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return property;
    }
}
