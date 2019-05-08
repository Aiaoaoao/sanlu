package com.yc.education.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @Author: BlueSky
 * @Date: 2018/8/10 16:31
 */
@Controller
public class baselineData  implements Initializable {


    @FXML
    private  TreeView treeView;

    @FXML
    private Pane contentPane;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        contentPane.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        statisticsTreeValue();
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
        String[] dataArray2 = {"应收账款","采购未入库","产品库存查询","最新采购查询"};
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
        String[] dataArray2 = {"异动查询","","销货未出库","销退未入库","采购未入库","产品库存查询"};
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
        TreeItem<String> item1 = new TreeItem<String> ("业务单据");
        item1.setExpanded(true);
        String[] dataArray1 = {"成本核算","应收账款冲款","应付账款冲款","销项发票","进项发票","收款单","预付账款"};
        for (int i = 0; i < dataArray1.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray1[i]);
            item1.getChildren().add(item);
        }
        rootItem.getChildren().add(item1);

        // 业务查询
        TreeItem<String> item2 = new TreeItem<>("业务查询");
        item2.setExpanded(true);
        String[] dataArray2 = {"销项成本明细","库存成本查询","应付账款","销项未开票"};
        for (int i = 0; i < dataArray2.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
            item2.getChildren().add(item);
        }
        rootItem.getChildren().add(item2);

        // 相关报表
        TreeItem<String> item3 = new TreeItem<>("相关报表");
        item3.setExpanded(true);
        String[] dataArray3 = {"应收账款账龄分析","应收款明细","应收款统计","未充发票明细"};
        for (int i = 0; i < dataArray3.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
            item3.getChildren().add(item);
        }
        rootItem.getChildren().add(item3);

        // 基本资料
//        TreeItem<String> item4 = new TreeItem<>("基本资料");
//        item4.setExpanded(true);
//        String[] dataArray4 = {"产品基本资料","供应商基本资料","仓库库位设置"};
//        for (int i = 0; i < dataArray4.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray4[i]);
//            item4.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item4);

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

    /**
     * @Author BlueSky
     * @Description //TODO 基本资料模块功能列表
     * @Date 10:57 2018/8/13
     * @Param []
     * @return void
     **/
    public  void baselineTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 基本资料
        TreeItem<String> item4 = new TreeItem<>("基本资料");
        item4.setExpanded(true);
        String[] dataArray4 = {"客户基本资料","供应商基本资料","运输商基本资料","公司基本资料","员工档案","公司部门设置","仓库库位设置","产品基本资料","产品价格设定","资料编码维护","业务区域设定"};
        for (int i = 0; i < dataArray4.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray4[i]);
            item4.getChildren().add(item);
        }
        rootItem.getChildren().add(item4);

        // 业务查询
//        TreeItem<String> item2 = new TreeItem<>("业务查询");
//        item2.setExpanded(true);
//        String[] dataArray2 = {"销项成本明细","库存成本查询","应付账款","销项未开票"};
//        for (int i = 0; i < dataArray2.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
//            item2.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item2);

        // 相关报表
//        TreeItem<String> item3 = new TreeItem<>("相关报表");
//        item3.setExpanded(true);
//        String[] dataArray3 = {"应收账款账龄分析","应收款明细","应收款统计","未充发票明细"};
//        for (int i = 0; i < dataArray3.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
//            item3.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item3);



        // 更节点隐藏
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
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
        String[] dataArray4 = {"请假/加班/出差","原始考勤资料","考勤报告","考勤资料转结","薪资计算"};
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

    /**
     * @Author BlueSky
     * @Description //TODO 统计汇总模块功能列表
     * @Date 11:09 2018/8/13
     * @Param []
     * @return void
     **/
    public  void statisticsTreeValue(){
        //根节点
        TreeItem<String> rootItem = new TreeItem<String> ();
        rootItem.setExpanded(true);

        // 销售
        TreeItem<String> item1 = new TreeItem<String> ("销售");
        item1.setExpanded(true);
        String[] dataArray1 = {"退货日报表","退货月报表","退货统计表","产品销售统计表","业绩奖金统计表"};
        for (int i = 0; i < dataArray1.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray1[i]);
            item1.getChildren().add(item);
        }
        rootItem.getChildren().add(item1);

        // 采购
//        TreeItem<String> item2 = new TreeItem<>("采购");
//        item2.setExpanded(true);
//        String[] dataArray2 = {"应收账款","采购未入库","产品库存查询","最新采购查询"};
//        for (int i = 0; i < dataArray2.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray2[i]);
//            item2.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item2);

        // 库存
//        TreeItem<String> item3 = new TreeItem<>("库存");
//        item3.setExpanded(true);
//        String[] dataArray3 = {"相关报表","相关报表"};
//        for (int i = 0; i < dataArray3.length; i++) {
//            TreeItem<String> item = new TreeItem<> (dataArray3[i]);
//            item3.getChildren().add(item);
//        }
//        rootItem.getChildren().add(item3);

        // 账款
        TreeItem<String> item4 = new TreeItem<>("账款");
        item4.setExpanded(true);
        String[] dataArray4 = {"应收账款账龄分析","应收款明细","应收款统计","未充发票明细"};
        for (int i = 0; i < dataArray4.length; i++) {
            TreeItem<String> item = new TreeItem<> (dataArray4[i]);
            item4.getChildren().add(item);
        }
        rootItem.getChildren().add(item4);


        // 更节点隐藏
        treeView.setShowRoot(false);
        treeView.setRoot(rootItem);
    }

}
