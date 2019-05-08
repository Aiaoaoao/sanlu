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
 *@Description TODO 销售
 *@Author QuZhangJing
 *@Date 16:04  2018-08-13
 *@Version 1.0
 */
@Controller
public class SaleDataController extends BaseController implements Initializable {


    @FXML
    private  TreeView treeView;

    @FXML
    private Pane contentPane;

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        contentPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        saleTreeValue();

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
                    if("报价单".equals(name)){
                        if(getPermissions("15_170_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/quotation.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("网上订单".equals(name)){

                        if(getPermissions("18_183_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/online_order.fxml"));
                        }else{
                            alertPermissions();
                        }

                    }else if("订货单".equals(name)){
                        if(getPermissions("28_209_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/purchase_order.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货单".equals(name)){
                        if(getPermissions("32_1016_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_slip.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销售退货单".equals(name)){
                        if(getPermissions("37_259_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_return.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货单状态更新".equals(name)){
                        if(getPermissions("41_274_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_status.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("订货未销".equals(name)){
                        if(getPermissions("31_2169_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/order_not_pins.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货未出库".equals(name)){
                        if(getPermissions("36_253_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_storage_not_out.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销退未入库".equals(name)){
                        if(getPermissions("38_264_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_return_not_enter_storage.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("产品库存查询".equals(name)){
                        if(getPermissions("12_155_4")){
                            // 引用采购模块
                            pane.getChildren().setAll(loader.load("/fxml/purchase/product_stock.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货发货跟踪".equals(name)){
                        if(getPermissions("43_280_4")){
                            // 引用采购模块
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_delivery_track.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("客户需求商品查询".equals(name)){
                        if(getPermissions("45_286_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_demand_goods_query.fxml"));
                        }else{
                            alertPermissions();
                        }

                    }else if("销售产品折扣查询".equals(name)){

                        if(getPermissions("46_289_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_discount_query.fxml"));
                        }else{
                            alertPermissions();
                        }

                    }else if("销售合同".equals(name)){
                        if(getPermissions("17_178_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_contract.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("应收账款账龄分析".equals(name)){
                        if(getPermissions("30_216_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/accounts_aging_receivable_analysis.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货明细表".equals(name)){
                        if(getPermissions("35_250_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/list_sale.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货日报表".equals(name)){
                        if(getPermissions("39_267_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/daily_sale_report.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("产品销售统计表".equals(name)){
                        if(getPermissions("40_270_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/product_sales_statistics.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货统计表".equals(name)){
                        if(getPermissions("42_277_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/sale_statistics.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销货月报表".equals(name)){
                        if(getPermissions("44_283_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/monthly_sale_report.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("销退统计表".equals(name)){
                        if(getPermissions("47_292_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/statistical_return.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("年度销售统计".equals(name)){
                        if(getPermissions("48_1013_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/annual_sale_statistics.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("月度销售统计".equals(name)){

                        if(getPermissions("49_295_4")){
                            pane.getChildren().setAll(loader.load("/fxml/sale/monthly_sale_statistics.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("客户基本资料".equals(name)){
                        if(getPermissions("19_187_1")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_basic_info.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("客户需求商品".equals(name)){//cheng826528058

                        if(getPermissions("27_203_4")){
                            pane.getChildren().setAll(loader.load("/fxml/customer/customer_demand_goods.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("产品基本资料".equals(name)){

                        if(getPermissions("7_135_4")){
                            pane.getChildren().setAll(loader.load("/fxml/basic/product_basic.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }else if("资料设定".equals(name)){
                        showDataSetting();
                    }else if("功能参数设定".equals(name)){
                        if(getPermissions("6_129_4")){
                            pane.getChildren().setAll(loader.load("/fxml/basic/region_basic.fxml"));
                        }else{
                            alertPermissions();
                        }
                    }
                    contentPane.getChildren().setAll(pane);

                    StageManager.clear();

                    StageManager.ORDERCONTENT.put("SalePane",contentPane);

                    StageManager.ORDERCONTENT.put("publicPane",contentPane);

                    //右侧菜单
                    StageManager.CONTROLLER.put("saleTreeView",treeView);

                }
            }
        });
    }


    /**
     * @Author BlueSky
     * @Description //TODO 销售模块功能列表
     * @Date 10:30 2018/8/13
     * @Param []
     * @return void
     **/
    public  void saleTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 业务单据
        TreeItem<String> item1 = new TreeItem<String> ("业务单据");
        item1.setExpanded(true);
        String[] dataArray1 = {"报价单","网上订单","订货单","销货单","销售退货单","销货单状态更新"};
        for (int i = 0; i < dataArray1.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray1[i]);
            item1.getChildren().add(item);
        }
        rootItem.getChildren().add(item1);

        // 业务查询
        TreeItem<String> item2 = new TreeItem<>("业务查询");
        item2.setExpanded(true);
        String[] dataArray2 = {"订货未销","销货未出库","销退未入库","产品库存查询","销货发货跟踪","客户需求商品查询","销售产品折扣查询"};
        for (int i = 0; i < dataArray2.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
            item2.getChildren().add(item);
        }
        rootItem.getChildren().add(item2);

        // 相关报表
        TreeItem<String> item3 = new TreeItem<>("相关报表");
        item3.setExpanded(true);
        String[] dataArray3 = {"销售合同","应收账款账龄分析","销货明细表","销货日报表","产品销售统计表","销货统计表","销货月报表","销退统计表","年度销售统计","月度销售统计"};
        for (int i = 0; i < dataArray3.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
            item3.getChildren().add(item);
        }
        rootItem.getChildren().add(item3);

        // 基本资料
        TreeItem<String> item4 = new TreeItem<>("基本资料");
        item4.setExpanded(true);
        String[] dataArray4 = {"客户基本资料","客户需求商品","产品基本资料"};
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
