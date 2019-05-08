package com.yc.education.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.education.model.purchase.*;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.CheckStock;
import com.yc.education.model.stock.CheckStockProduct;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author BlueSky
 * @Date 11:47 2018-08-21
 */
public class StageManager {
    /**
     *
     * @auther: BlueSky
     */
    public static Map<String, Stage> STAGE = new HashMap<String, Stage>();

    public static Map<String, Object> CONTROLLER = new HashMap<String, Object>();

    public static Map<String,Pane> ORDERCONTENT = new HashMap<String, Pane>();
    //询价单导出存入
    public static InquiryOrder inquiryOrderInfo = null;
    //询价单导出导出询价产品
    public static List<InquiryProduct> inquiryProductsInfo = new ArrayList<>();
    //采购订单
    public static PurchaseOrders purchaseOrders = null;
    //采购订单产品导出
    public static List<PurchaseProduct> purchaseProducts = new ArrayList<>();
    //在途库存导入采购入库
    public static List<TransportationProduct> transportationProducts  = new ArrayList<>();
    //盘库作业产品导入库存异动作业产品
    public static List<CheckStockProduct> checkStockProducts  = new ArrayList<>();
    //销售-报价单
    public static SaleQuotation saleQuotation = null;
    //销售-网上订单
    public static SaleOnlineOrder saleOnlineOrder = null;
    //销售-订货单
    public static SalePurchaseOrder salePurchaseOrder = null;
    //销售-销货单
    public static SaleGoods saleGoods = null;
    //销售-销货退货单
    public static SaleReturnGoods saleReturnGoods = null;
    //在途库存-采购入库
    public static TransportationInventory transportationInventory = null;
    //盘库作业-库存异动作业
    public static CheckStock checkStock = null;
    // 集合
    public static List<?> PRODUCT_LIST = new ArrayList<>();
    //限制右侧船体打开关闭
    public static boolean rightWin = false;

    /**
     * 清空所有集合和对象
     */
    public  static void clear(){

        StageManager.inquiryOrderInfo =  null;

        StageManager.inquiryProductsInfo = new ArrayList<>();

        StageManager.purchaseOrders =  null;

        StageManager.purchaseProducts = new ArrayList<>();

        StageManager.transportationProducts = new ArrayList<>();

        StageManager.saleOnlineOrder = null;

        StageManager.salePurchaseOrder = null;

        StageManager.checkStockProducts = new ArrayList<>();

        StageManager.saleQuotation  = null;

        StageManager.saleGoods =  null;

        StageManager.saleReturnGoods = null;

        StageManager .transportationInventory = null;

        StageManager .checkStock = null;

        StageManager.PRODUCT_LIST = new ArrayList<>();
    }


}