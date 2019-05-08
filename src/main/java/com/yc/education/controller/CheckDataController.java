package com.yc.education.controller;

import com.yc.education.constants.SpringFxmlLoader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *@Description TODO 考勤
 *@Author QuZhangJing
 *@Date 16:04  2018-08-13
 *@Version 1.0
 */
@Controller
public class CheckDataController extends BaseController implements Initializable {


    @FXML
    private  TreeView treeView;

    @FXML
    private Pane contentPane;


    private String url="";

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();

                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String name = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
                    changeClick(name);
                }
            }
        });

        checkTreeValue();
    }



    /**
     * 单击树
     * @param value
     */
    public  void  changeClick(String value){

        if("请假/加班/出差".equals(value)){
            if(getPermissions("50_301_4")){
                url= "/fxml/check/check_order.fxml";
            }else {
                alertPermissions();
            }
        }

        if("原始考勤资料".equals(value)){
            if(getPermissions("52_317_4")){
                url= "/fxml/check/original_check.fxml";
            }else {
                alertPermissions();
            }
        }

        if("考勤资料结转".equals(value)){
            if(getPermissions("56_346_4")){
                url= "/fxml/check/data_check.fxml";
            }else {
                alertPermissions();
            }
        }



        if(!"".equals(url)){
            Pane pane = new Pane();

            pane.getChildren().setAll(loader.load(url));

            contentPane.getChildren().setAll(pane);
        }


    }




    /**
     * @Author BlueSky
     * @Description //TODO 考勤管理模块功能列表
     * @Date 11:05 2018/8/13
     * @Param []
     * @return void
     **/
    public  void checkTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 考勤管理
        TreeItem<String> item4 = new TreeItem<>("考勤管理");
        item4.setExpanded(true);
        String[] dataArray4 = {"请假/加班/出差","原始考勤资料","考勤报告","考勤资料结转","薪资计算"};
        for (int i = 0; i < dataArray4.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray4[i]);
            item4.getChildren().add(item);
        }
        rootItem.getChildren().add(item4);



        // 参数设定
        TreeItem<String> item3 = new TreeItem<>("参数设定");
        item3.setExpanded(true);
        String[] dataArray3 = {"法定节假日设定","考勤班次设定","薪资设定"};
        for (int i = 0; i < dataArray3.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
            item3.getChildren().add(item);
        }
        rootItem.getChildren().add(item3);

        // 更节点隐藏
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }


}
