package com.yc.education.application;

import com.yc.education.constants.Config;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.util.DragUtil;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.ibatis.io.ResolverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.util.Observable;
import java.util.Observer;

/**
 *@Description TODO
 *@Author QuZhangJing
 *@Date 13:01  2018-08-07
 *@Version 1.0
 */
@Configuration
public class ScreenManager  implements Observer  {
    private static Logger logger = LogManager.getLogger(ScreenManager.class);
    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    private Stage stage;
    private Scene scene;

    public void setPrimaryStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void showStage() {
        stage.setTitle("");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/login.fxml"));
        scene = new Scene(pane);
        stage.getIcons().add(new Image(
                ResolverUtil.Test.class.getResourceAsStream("/images/logo.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
