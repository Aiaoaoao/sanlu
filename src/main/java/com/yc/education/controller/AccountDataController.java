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
 *@Description TODO 账款
 *@Author BlueSky
 *@Date 16:04  2018-08-13
 *@Version 1.0
 */
@Controller
public class AccountDataController extends BaseController implements Initializable {


    @FXML
    private  TreeView treeView;

    @FXML
    private Pane contentPane;

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        contentPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        accountTreeValue();

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
                    if("成本核算".equals(name)){
                        if(getPermissions("87_488_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_cost_accounting.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应收账款冲账".equals(name)){
                        if(getPermissions("90_501_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_accounts_receivable.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应付账款冲账".equals(name)){
                        if(getPermissions("91_509_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_accounts_payable.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("销项发票".equals(name)){
                        if(getPermissions("92_517_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_sales_invoice.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("进项发票".equals(name)){
                        if(getPermissions("93_525_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_input_invoice.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("收款单".equals(name)){
                        if(getPermissions("95_536_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_receipt.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("预付账款".equals(name)){
                        if(getPermissions("96_544_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/order_prepayments.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("销项成本明细".equals(name)){
                        pane.getChildren().setAll(loader.load("/fxml/account/query_sale_cost.fxml"));
                    }else if("库存成本查询".equals(name)){
                        if(getPermissions("89_495_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/query_inventory_cost.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应付账款".equals(name)){
                        if(getPermissions("3_105_4")){
                            pane.getChildren().setAll(loader.load("/fxml/purchase/pay.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("销货未开票".equals(name)){
                        if(getPermissions("94_530_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/query_sale_not_ticket.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应收账款账龄分析".equals(name)){
                        if(getPermissions("30_216_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/report_aging_analysis.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应收款明细".equals(name)){
                        if(getPermissions("83_475_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/report_receivable_details.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("应收款统计".equals(name)){
                        if(getPermissions("84_478_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/report_receivable_count.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("未冲发票明细".equals(name)){
                        if(getPermissions("85_481_4")){
                            pane.getChildren().setAll(loader.load("/fxml/account/report_unfilled_invoice.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }else if("资料设定".equals(name)){
                        showDataSetting();
                    }else if("功能参数设定".equals(name)){
                        if(getPermissions("6_129_4")){
                            pane.getChildren().setAll(loader.load("/fxml/basic/region_basic.fxml"));
                        }else {
                            alertPermissions();
                        }
                    }
                    contentPane.getChildren().setAll(pane);
                    StageManager.ORDERCONTENT.put("SalePane",contentPane);
                }
            }
        });
    }

    /**
     * @Author BlueSky
     * @Description //TODO 账款模块功能列表
     * @Date 10:31 2018/8/13
     * @Param []
     * @return void
     **/
    public  void accountTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 业务单据
//        TreeItem<String> item1 = new TreeItem<String> ("业务单据");
//        item1.setExpanded(true);
//        String[] dataArray1 = {"成本核算","应收账款冲账","应付账款冲账","销项发票","进项发票","收款单","预付账款"};
//        for (int i = 0; i < dataArray1.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray1[i]);
//            item1.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item1);

        // 业务查询
        TreeItem<String> itemBus = new TreeItem<String> ("业务查询");
        itemBus.setExpanded(true);
        String[] dataArrayBus = {"应收账款冲账","销项发票","收款单"};
        for (int i = 0; i < dataArrayBus.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArrayBus[i]);
            itemBus.getChildren().add(item);
        }
        rootItem.getChildren().add(itemBus);

        // 供应商查询
        TreeItem<String> itemCus = new TreeItem<String> ("供应商查询");
        itemCus.setExpanded(true);
        String[] dataArrayCus = {"成本核算","应付账款冲账","进项发票","预付账款"};
        for (int i = 0; i < dataArrayCus.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArrayCus[i]);
            itemCus.getChildren().add(item);
        }
        rootItem.getChildren().add(itemCus);

        // 业务查询
        TreeItem<String> item2 = new TreeItem<>("查询");
        item2.setExpanded(true);
        String[] dataArray2 = {"销项成本明细","库存成本查询","应付账款","销货未开票"};
        for (int i = 0; i < dataArray2.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
            item2.getChildren().add(item);
        }
        rootItem.getChildren().add(item2);

        // 相关报表
        TreeItem<String> item3 = new TreeItem<>("相关报表");
        item3.setExpanded(true);
        String[] dataArray3 = {"应收账款账龄分析","应收款明细","应收款统计","未冲发票明细"};
        for (int i = 0; i < dataArray3.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
            item3.getChildren().add(item);
        }
        rootItem.getChildren().add(item3);


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
