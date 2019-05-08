package com.yc.education.controller;

import com.yc.education.constants.SpringFxmlLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName ContainerController
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/11/26 14:54
 * @Version 1.0
 */
@Controller
public class ContainerController implements Initializable {

    /**
     * container pane
     */
    @FXML
    private StackPane containerPane;

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        containerPane.getChildren().addAll(loader.load("/fxml/home.fxml"));


    }
}
