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
 *@Description TODO 客户关系
 *@Author BlueSky
 *@Date 16:04  2018-08-13
 *@Version 1.0
 */
@Controller
public class CustomerRelationDataController extends BaseController implements Initializable {


    @FXML
    private  TreeView treeView;

    @FXML
    private Pane contentPane;

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    private static boolean firstInit = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化listview列表
        customerRelationTreeValue();

        treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();
                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String name = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
                    System.out.println("Node click: " + name);
                    Pane pane = new Pane();
                    if("客户基本资料".equals(name)){
                        if(getPermissions("19_190_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_basic_info.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("客户需求商品".equals(name)){
                        if(getPermissions("27_203_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_demand_goods.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("客户资料维护".equals(name)){
                        if(getPermissions("82_469_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_data_maintenance.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("客户需求商品查询".equals(name)){
                        if(getPermissions("45_286_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_demand_goods_query.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("销售产品折扣查询".equals(name)){
                        if(getPermissions("46_289_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_discount_query.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应收账款账龄分析".equals(name)){
                        if(getPermissions("30_216_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/accounts_receivable_age_analysis.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }
                    /*else{
                        if(getPermissions("19_190_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_basic_info.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }*/
                    contentPane.getChildren().setAll(pane);
                }
            }
        });
    }

    /**
     * @Author BlueSky
     * @Description //TODO 客户关系模块功能列表
     * @Date 11:03 2018/8/13
     * @Param []
     * @return void
     **/
    public  void customerRelationTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 基本资料
        TreeItem<String> item4 = new TreeItem<>("基本资料");
        item4.setExpanded(true);
        String[] dataArray4 = {"客户基本资料","客户需求商品","客户资料维护"};
        for (int i = 0; i < dataArray4.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray4[i]);
            item4.getChildren().add(item);
        }
        rootItem.getChildren().add(item4);

        // 业务查询
        TreeItem<String> item2 = new TreeItem<>("业务查询");
        item2.setExpanded(true);
        String[] dataArray2 = {"客户需求商品查询","销售产品折扣查询"};
        for (int i = 0; i < dataArray2.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
            item2.getChildren().add(item);
        }
        rootItem.getChildren().add(item2);

        // 相关报表
        TreeItem<String> item3 = new TreeItem<>("相关报表");
        item3.setExpanded(true);
        String[] dataArray3 = {"应收账款账龄分析"};
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
