package com.yc.education.controller.stock;

import com.yc.education.controller.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName PrintingController
 * @Description TODO 包装打印
 * @Author QuZhangJing
 * @Date 2019/3/15 14:42
 * @Version 1.0
 */
@Controller
public class PrintingController extends BaseController implements Initializable {

    @FXML private VBox printingvbox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(!getPermissions("74_426_7")){
            printingvbox.setDisable(true);
        }

    }



}
