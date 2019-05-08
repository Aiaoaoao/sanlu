package com.yc.education.constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 *@Description TODO
 *@Author QuZhangJing
 *@Date 13:02  2018-08-07
 *@Version 1.0
 */
public class Config {

    private static Logger logger = LogManager.getLogger(Config.class);
    private static Properties properties;
    private static double WIDTH = 600.0;
    private static double HEIGHT = 400.0;

    private static Properties getProperties() {
        if (properties == null) {
            Properties prop = new Properties();
            try {
                prop.load(Config.class.getResourceAsStream("/config/config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!prop.isEmpty())
                properties = prop;
        }
        return properties;
    }

    public static double getWIDTH() {
        Properties properties = getProperties();
        if (properties != null)
            try {
                return Double.parseDouble(properties.getProperty("width"));
            } catch (NumberFormatException | NullPointerException e) {
                logger.error("get width properties fail", e);
            }
        return WIDTH;
    }

    public static double getHEIGHT() {
        Properties properties = getProperties();
        if (properties != null)
            try {
                return Double.parseDouble(properties.getProperty("height"));
            } catch (NumberFormatException | NullPointerException e) {
                logger.error("get height properties fail", e);
            }
        return HEIGHT;
    }

}
