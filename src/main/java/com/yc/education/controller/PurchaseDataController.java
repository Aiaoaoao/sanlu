package com.yc.education.controller;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.util.StageManager;
import javafx.application.Application;
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
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *@Description TODO 采购管理
 *@Author QuZhangJing
 *@Date 16:04  2018-08-13
 *@Version 1.0
 */
@Controller
public class PurchaseDataController extends BaseController implements Initializable {


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
        purchaseTreeValue();

        //右侧菜单
        StageManager.CONTROLLER.put("purchanseTreeView",treeView);

        StageManager.ORDERCONTENT.put("PurchasePane",contentPane);

        StageManager.ORDERCONTENT.put("publicPane",contentPane);

    }




    /**
     * 单击树
     * @param value
     */
    public   void  changeClick(String value){

        if(value.equals("询价单")){

            if(getPermissions("2_109_4")){
                url= "/fxml/purchase/inquiry.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("采购订单")){


            if(getPermissions("9_142_4")){
                url="/fxml/purchase/purchase_order.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("强制结案")){


            if(getPermissions("11_152_4")){
                url="/fxml/purchase/constraint.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("在途库存")){

            if(getPermissions("13_161_4")){
                url="/fxml/purchase/ontheway.fxml";
            }else {
                alertPermissions();
            }
        }

        if(value.equals("应付账款")){
            if(getPermissions("3_105_4")){
                url="/fxml/purchase/pay.fxml";
            }else {
                alertPermissions();
            }
        }

        if(value.equals("采购未入库")){
            if(getPermissions("10_148_4")){
                url="/fxml/purchase/nostorage.fxml";
            }else {
                alertPermissions();
            }
           }

        if(value.equals("产品库存查询")){
            if(getPermissions("12_155_4")){
                url="/fxml/purchase/product_stock.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("最新采购查询")){
            if(getPermissions("14_164_4")){
                url="/fxml/purchase/purchase_query.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("供应商基本资料")){
            if(getPermissions("4_121_4")){
                url="/fxml/basic/supplier_basic.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("产品基本资料")){
            if(getPermissions("7_135_4")){
                url="/fxml/basic/product_basic.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("资料设定")){
            showDataSetting();
        }

        if(value.equals("功能参数设定")){
            if(getPermissions("6_129_4")){
                url="/fxml/basic/region_basic.fxml";
            }else {
                alertPermissions();
            }

        }


        if(!"".equals(url)){
            Pane pane = new Pane();

            pane.getChildren().setAll(loader.load(url));

            contentPane.getChildren().setAll(pane);

            StageManager.clear();





        }


    }



    /**
     * @Author BlueSky
     * @Description //TODO 采购模块功能列表
     * @Date 10:31 2018/8/13
     * @Param []
     * @return void
     **/
    public  void purchaseTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 业务单据
        TreeItem<String> item1 = new TreeItem<String> ("业务单据");
        item1.setExpanded(true);
        String[] dataArray1 = {"询价单","采购订单","强制结案","在途库存"};
        for (int i = 0; i < dataArray1.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray1[i]);
            item1.getChildren().add(item);
        }
        rootItem.getChildren().add(item1);

        // 业务查询
        TreeItem<String> item2 = new TreeItem<>("业务查询");
        item2.setExpanded(true);
        String[] dataArray2 = {"应付账款","采购未入库","产品库存查询","最新采购查询"};
        for (int i = 0; i < dataArray2.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
            item2.getChildren().add(item);
        }
        rootItem.getChildren().add(item2);

        // 相关报表
//        TreeItem<String> item3 = new TreeItem<>("相关报表");
//        item3.setExpanded(true);
//        String[] dataArray3 = {"相关报表","相关报表"};
//        for (int i = 0; i < dataArray3.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
//            item3.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item3);

        // 基本资料
        TreeItem<String> item4 = new TreeItem<>("基本资料");
        item4.setExpanded(true);
        String[] dataArray4 = {"供应商基本资料","产品基本资料"};
        for (int i = 0; i < dataArray4.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray4[i]);
            item4.getChildren().add(item);
        }
        rootItem.getChildren().add(item4);

        // 参数设定
        TreeItem<String> item5 = new TreeItem<>("参数设定");
        item5.setExpanded(true);
        String[] dataArray5 = {"资料设定","功能参数设定"};
        for (int i = 0; i < dataArray5.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray5[i]);
            item5.getChildren().add(item);
        }
        rootItem.getChildren().add(item5);

        // 更节点隐藏
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }


}
