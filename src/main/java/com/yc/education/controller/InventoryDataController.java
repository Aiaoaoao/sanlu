package com.yc.education.controller;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.util.StageManager;
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
 *@Description TODO 库存
 *@Author QuZhangJing
 *@Date 16:04  2018-08-13
 *@Version 1.0
 */
@Controller
public class InventoryDataController extends BaseController implements Initializable {


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
        inventoryTreeValue();

        StageManager.ORDERCONTENT.put("stockPane",contentPane);

        StageManager.ORDERCONTENT.put("publicPane",contentPane);
        //右侧菜单
        StageManager.CONTROLLER.put("stockTreeView",treeView);


    }



    /**
     * 单击树
     * @param value
     */
    public  void  changeClick(String value){


        if(value.equals("采购入库单")){
            if(getPermissions("58_362_4")){
                url= "/fxml/stock/purchase_stock.fxml";
            }else {
                alertPermissions();
            }
        }

        if(value.equals("销货出库单")){
            if(getPermissions("61_376_4")){
                url="/fxml/stock/sale_outbound_order.fxml";
            }else {
                alertPermissions();
            }
        }

        if(value.equals("库存异动作业")){
            if(getPermissions("63_385_4")){
                url="/fxml/stock/stock_change.fxml";
            }else {
                alertPermissions();
            }
            }

        if(value.equals("盘库作业")){
            if(getPermissions("69_405_4")){
                url="/fxml/stock/inventory_operation.fxml";
            }else {
                alertPermissions();
            }
            }

        if(value.equals("快递寄件")){
            if(getPermissions("71_414_4")){
                url="/fxml/stock/express_mail.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("快递收件")){
            if(getPermissions("73_422_4")){
                url="/fxml/stock/express_collect.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("包装打印")){
            if(getPermissions("74_425_4")){
                url="/fxml/stock/printing.fxml";
            }else {
                alertPermissions();
            }
        }

        if(value.equals("异动查询")) {
            if (getPermissions("59_367_4")) {
                url = "/fxml/stock/change_query.fxml";
            } else {
                alertPermissions();
            }
        }

        if(value.equals("盘点查询")){
            if(getPermissions("60_370_4")){
                url="/fxml/stock/inventory_query.fxml";
        }else {
            alertPermissions();
        }
        }

        if(value.equals("销货未出库")){
            if(getPermissions("36_253_4")){
                url="/fxml/sale/sale_storage_not_out.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("销退未入库")){
            if(getPermissions("38_264_4")){
                url="/fxml/sale/sale_return_not_enter_storage.fxml";
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



        if(value.equals("库存异动汇总")){
            if(getPermissions("66_397_4")){
                url="/fxml/stock/stock_change_total.fxml";
            }else {
                alertPermissions();
            }

        }

        if(value.equals("库存异动查询")){
            if(getPermissions("68_401_4")){
                url="/fxml/stock/stock_change_query.fxml";
            }else {
                alertPermissions();
            }
        }

        if(value.equals("进销货统计")){
            if(getPermissions("72_417_4")){
                url="/fxml/stock/purchase_sale_total.fxml";
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

        if(value.equals("仓库库位设置")){
            if(getPermissions("65_394_4")){
                url="/fxml/basic/depot_basic.fxml";
            }else {
                alertPermissions();
            }
        }
        if(value.equals("资料设定")){showDataSetting();}

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
     * @Description //TODO 库存模块功能列表
     * @Date 10:31 2018/8/13
     * @Param []
     * @return void
     **/
    public  void inventoryTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 业务单据
        TreeItem<String> item1 = new TreeItem<String> ("业务单据");
        item1.setExpanded(true);
        String[] dataArray1 = {"采购入库单","销货出库单","库存异动作业","盘库作业","快递寄件","快递收件","包装打印"};
        for (int i = 0; i < dataArray1.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray1[i]);
            item1.getChildren().add(item);
        }
        rootItem.getChildren().add(item1);

        // 业务查询
        TreeItem<String> item2 = new TreeItem<>("业务查询");
        item2.setExpanded(true);
        String[] dataArray2 = {"异动查询","盘点查询","销货未出库","销退未入库","采购未入库","产品库存查询"};
        for (int i = 0; i < dataArray2.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
            item2.getChildren().add(item);
        }
        rootItem.getChildren().add(item2);

        // 相关报表
        TreeItem<String> item3 = new TreeItem<>("相关报表");
        item3.setExpanded(true);
        String[] dataArray3 = {"库存异动汇总","库存异动查询","进销货物统计"};
        for (int i = 0; i < dataArray3.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
            item3.getChildren().add(item);
        }
        rootItem.getChildren().add(item3);

        // 基本资料
        TreeItem<String> item4 = new TreeItem<>("基本资料");
        item4.setExpanded(true);
        String[] dataArray4 = {"产品基本资料","供应商基本资料","仓库库位设置"};
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
