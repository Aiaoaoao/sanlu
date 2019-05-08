package com.yc.education.application;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.util.DragUtil;
import com.yc.education.util.DrawUtil;
import com.yc.education.util.StageManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.ibatis.io.ResolverUtil;

import javax.swing.*;

/**
 * @ClassName NetworkConfiguration
 * @Description TODO 网络配置
 * @Author QuZhangJing
 * @Date 2018-08-10 9:36
 * @Version 1.0
 */
public class NetworkConfiguration {

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    /**
     * 网络配置
     */
    public static   void  display(){
        Stage stage = new Stage();
        stage.setTitle("");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/network_config.fxml"));
        Scene  scene = new Scene(pane);
        stage.getIcons().add(new Image(
                ResolverUtil.Test.class.getResourceAsStream("/images/logo.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听


        stage.show();
    }


    /**
     * home
     */
    public static   void  homeShow(){
        Stage stage = new Stage();
        stage.setTitle("三禄进销存系统");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/home.fxml"));
        stage.getIcons().add(new Image(
                ResolverUtil.Test.class.getResourceAsStream("/images/logo.png")));
        Scene  scene = new Scene(pane);
        stage.setScene(scene);
        StageManager.CONTROLLER.put("homeStage",stage);
//        stage.setMaximized(true);  //默认窗口最大化
        stage.setResizable(false); //禁止窗体缩放
        stage.show();
    }



}
