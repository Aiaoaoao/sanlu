package com.yc.education.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;


/**
 *@Description TODO
 *@Author QuZhangJing
 *@Date 13:02  2018-08-07
 *@Version 1.0
 */
public class Main extends Application {

    private static Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws Exception {
        logger.info("Starting application");

        //读取配置文件，初始化shiroManger工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //工厂模式,获得securityManager实例对象
        SecurityManager securityManager = factory.getInstance();
        //将securityManger实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);



        Platform.setImplicitExit(true);
        ScreenManager screens = new ScreenManager();
        screens.setPrimaryStage(stage);
        screens.showStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
