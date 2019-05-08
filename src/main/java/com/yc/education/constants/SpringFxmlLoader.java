package com.yc.education.constants;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;

/**
 *@Description TODO
 *@Author QuZhangJing
 *@Date 13:01  2018-08-07
 *@Version 1.0
 */
public class SpringFxmlLoader {

    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    public Parent load(String url) {
        try {
            URL sourseUrl = this.getClass().getResource(url);
            FXMLLoader loader = new FXMLLoader(sourseUrl);
            loader.setControllerFactory(applicationContext::getBean);
            return loader.load();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}