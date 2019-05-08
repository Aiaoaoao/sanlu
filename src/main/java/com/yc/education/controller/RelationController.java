package com.yc.education.controller;

import com.yc.education.model.Relation;
import com.yc.education.model.RelationProperty;
import com.yc.education.model.account.AccountPayable;
import com.yc.education.model.account.AccountReceivable;
import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.*;
import com.yc.education.service.RelationService;
import com.yc.education.service.account.*;
import com.yc.education.service.purchase.InquiryOrderService;
import com.yc.education.service.purchase.PurchaseOrdersService;
import com.yc.education.service.sale.*;
import com.yc.education.service.stock.*;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName RelationController
 * @Description TODO 关联单据
 * @Author QuZhangJing
 * @Date 2019/4/26 15:47
 * @Version 1.0
 */
@Controller
public class RelationController extends BaseController implements Initializable {


    @Autowired
    private RelationService relationService;



    //采购入库单
    @Autowired
    private PurchaseStockService purchaseStockService;
    //销货出库单
    @Autowired
    private IStockOutSaleService iStockOutSaleService;
    //库存异动作业
    @Autowired
    private StockChangeService stockChangeService;
    //盘库作业
    @Autowired
    private CheckStockService checkStockService;
    //采购订单
    @Autowired
    private PurchaseOrdersService purchaseOrdersService;
    //询价单
    @Autowired
    private InquiryOrderService inquiryOrderService;
    //销货单
    @Autowired
    private ISaleGoodsService iSaleGoodsService;
    //销货退货单
    @Autowired
    private ISaleReturnGoodsService iSaleReturnGoodsService;
    //报价单
    @Autowired
    private ISaleQuotationService iSaleQuotationService;
    //订货单
    @Autowired
    private ISalePurchaseOrderService iSalePurchaseOrderService;
    //网上订单
    @Autowired
    private ISaleOnlineOrderService iSaleOnlineOrderService;
    //应收账款冲账
    @Autowired
    private IAccountReceivableService iAccountReceivableService;
    //应付账款冲账
    @Autowired
    private IAccountPayableService iAccountPayableService;
    //快递寄件
    @Autowired
    private ExpressMailService expressMailService;
    //快递收件
    @Autowired
    private ExpressCollectService expressCollectService;



    @FXML
    private ComboBox orderType;
    @FXML
    private TextField orders;

    @FXML
    private TableView stayOrderTableView;
    @FXML
    private TableColumn tabSort;
    @FXML
    private TableColumn tabOrderType;
    @FXML
    private TableColumn tabOrders;
    @FXML
    private TableColumn tabTime;
    @FXML
    private TableColumn createPeople;

    List<RelationProperty> relationProperties = new ArrayList<>();

    /**
     * 查找
     */
    public void searchOrders(){

        String orderTypeStr = orderType.getSelectionModel().getSelectedItem()+"";

        String orderStr =  orders.getText();

        relationProperties.clear();

        if(!"".equals(orderStr) && !"".equals(orderTypeStr)){

            long orderId = 0L;

            switch (orderTypeStr){

                case "采购入库单":
                    PurchaseStock purchaseStock = purchaseStockService.findPurchaseStockByNo(orderStr);
                    if(purchaseStock != null){
                        orderId = purchaseStock.getId();
                    }
                    break;
                case "销货出库单":
                      StockOutSale stockOutSale = iStockOutSaleService.getStockOutSaleByOutboundNo(orderStr);
                      if(stockOutSale != null){
                          orderId =  stockOutSale.getId();
                      }
                    break;
                case "库存异动作业":
                    StockChange stockChange = stockChangeService.findStockChangeOrders(orderStr);
                    if(stockChange != null){
                        orderId =  stockChange.getId();
                    }
                    break;
                case "盘库作业":
                    CheckStock checkStock = checkStockService.findCheckStockOrders(orderStr);
                    if(checkStock != null){
                        orderId =  checkStock.getId();
                    }
                    break;
                case "快递收件":
                    ExpressCollect expressCollect = expressCollectService.findExpressCollectOrders(orderStr);
                    if(expressCollect != null ){
                        orderId = expressCollect.getId();
                    }
                    break;
                case "快递寄件":
                    ExpressMail expressMail = expressMailService.findExpressMailOrder(orderStr);
                    if(expressMail != null ){
                        orderId = expressMail.getId();
                    }
                    break;
                case "销货单":
                     SaleGoods saleGoods =  iSaleGoodsService.findBySaleNo(orderStr);
                    if(saleGoods != null ){
                        orderId = saleGoods.getId();
                    }
                    break;
                case "销售退货单":
                     SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.getSaleReturnGoods(orderStr);
                    if(saleReturnGoods != null ){
                        orderId = saleReturnGoods.getId();
                    }
                    break;
                case "报价单":
                     SaleQuotation saleQuotation = iSaleQuotationService.getSaleQuotation(orderStr);
                    if(saleQuotation != null ){
                        orderId = saleQuotation.getId();
                    }
                    break;
                case "订货单":
                     SalePurchaseOrder salePurchaseOrder = iSalePurchaseOrderService.getSalePurchaseOrder(orderStr);
                    if(salePurchaseOrder != null ){
                        orderId = salePurchaseOrder.getId();
                    }
                    break;

                case "网上订单":
                    SaleOnlineOrder saleOnlineOrder = iSaleOnlineOrderService.getSaleOnlineOrder(orderStr);
                    if(saleOnlineOrder != null ){
                        orderId = saleOnlineOrder.getId();
                    }
                    break;
                case "采购订单":
                    PurchaseOrders purchaseOrders = purchaseOrdersService.findPurchaseByOrders(orderStr);
                    if(purchaseOrders != null){
                        orderId = purchaseOrders.getId();
                    }
                    break;
                case "询价单":
                    InquiryOrder inquiryOrder = inquiryOrderService.findInquiryOrderByOrders(orderStr);
                    if(inquiryOrder != null){
                        orderId = inquiryOrder.getId();
                    }
                    break;
                case "应收账款冲账":
                    AccountReceivable accountReceivable = iAccountReceivableService.getByOrderNo(orderStr);
                    if(accountReceivable != null){
                        orderId = accountReceivable.getId();
                    }
                    break;
                case "应付账款冲账":
                    AccountPayable accountPayable = iAccountPayableService.getByOrderNo(orderStr);
                    if(accountPayable != null){
                        orderId = accountPayable.getId();
                    }
                    break;
                default:
                    break;
            }


            ObservableList<RelationProperty> relationPropertieList = FXCollections.observableArrayList();

            tabSort.setCellValueFactory(new PropertyValueFactory<>("sort"));
            tabOrderType.setCellValueFactory(new PropertyValueFactory<>("orderType"));
            tabOrders.setCellValueFactory(new PropertyValueFactory<>("orders"));
            tabTime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
            createPeople.setCellValueFactory(new PropertyValueFactory<>("createPeople"));




            itemRelation(orderTypeStr,orderId);


            if(relationProperties != null && relationProperties.size() > 0 ){

                for (int i=0,len = relationProperties.size();i<len;i++) {
                    switch (relationProperties.get(i).getOrderType()){

                        case "采购入库单":
                            PurchaseStock purchaseStock = purchaseStockService.selectByKey(relationProperties.get(i).getId());
                            if(purchaseStock != null){
                                relationProperties.get(i).setOrders(purchaseStock.getStockorder());
                                relationProperties.get(i).setOrderTime(DateToString(purchaseStock.getCreatedate()));
                                relationProperties.get(i).setCreatePeople(purchaseStock.getCreatename());
                            }
                            break;
                        case "销货出库单":
                            StockOutSale stockOutSale = iStockOutSaleService.selectByKey(relationProperties.get(i).getId());
                            if(stockOutSale != null){
                                relationProperties.get(i).setOrders(stockOutSale.getOutboundNo());
                                relationProperties.get(i).setOrderTime(DateToString(stockOutSale.getCreateDate()));
                                relationProperties.get(i).setCreatePeople(stockOutSale.getMadePeople());
                            }
                            break;
                        case "库存异动作业":
                            StockChange stockChange = stockChangeService.selectByKey(relationProperties.get(i).getId());
                            if(stockChange != null){
                                relationProperties.get(i).setOrders(stockChange.getChangeorder());
                                relationProperties.get(i).setOrderTime(DateToString(stockChange.getChangedate()));
                                relationProperties.get(i).setCreatePeople(stockChange.getCreatepeople());
                            }
                            break;
                        case "盘库作业":
                            CheckStock checkStock = checkStockService.selectByKey(relationProperties.get(i).getId());
                            if(checkStock != null){
                                relationProperties.get(i).setOrders(checkStock.getCheckorder());
                                relationProperties.get(i).setOrderTime(DateToString(checkStock.getCheckdate()));
                                relationProperties.get(i).setCreatePeople(checkStock.getCreatepeople());
                            }
                            break;
                        case "快递收件":
                            ExpressCollect expressCollect = expressCollectService.selectByKey(relationProperties.get(i).getId());
                            if(expressCollect != null ){
                                relationProperties.get(i).setOrders(expressCollect.getCollectorder());
                                relationProperties.get(i).setOrderTime(DateToString(expressCollect.getCollectdate()));
                                relationProperties.get(i).setCreatePeople(expressCollect.getCreatepeople());
                            }
                            break;
                        case "快递寄件":
                            ExpressMail expressMail = expressMailService.selectByKey(relationProperties.get(i).getId());
                            if(expressMail != null ){
                                relationProperties.get(i).setOrders(expressMail.getMailorder());
                                relationProperties.get(i).setOrderTime(DateToString(expressMail.getMaildate()));
                                relationProperties.get(i).setCreatePeople(expressMail.getCreatepeople());
                            }
                            break;
                        case "销货单":
                            SaleGoods saleGoods =  iSaleGoodsService.selectByKey(relationProperties.get(i).getId());
                            if(saleGoods != null ){
                                relationProperties.get(i).setOrders(saleGoods.getSaleNo());
                                relationProperties.get(i).setOrderTime(DateToString(saleGoods.getCreateDate()));
                                relationProperties.get(i).setCreatePeople(saleGoods.getMadePeople());
                            }
                            break;
                        case "销售退货单":
                            SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.selectByKey(relationProperties.get(i).getId());
                            if(saleReturnGoods != null ){
                                relationProperties.get(i).setOrders(saleReturnGoods.getPinbackNo());
                                relationProperties.get(i).setOrderTime(DateToString(saleReturnGoods.getCreateDate()));
                                relationProperties.get(i).setCreatePeople(saleReturnGoods.getMadePeople());
                            }
                            break;
                        case "报价单":
                            SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(relationProperties.get(i).getId());
                            if(saleQuotation != null ){
                                relationProperties.get(i).setOrders(saleQuotation.getOfferNo());
                                relationProperties.get(i).setOrderTime(DateToString(saleQuotation.getCreateDate()));
                                relationProperties.get(i).setCreatePeople(saleQuotation.getSinglePeople());
                            }
                            break;
                        case "订货单":
                            SalePurchaseOrder salePurchaseOrder = iSalePurchaseOrderService.selectByKey(relationProperties.get(i).getId());
                            if(salePurchaseOrder != null ){
                                relationProperties.get(i).setOrders(salePurchaseOrder.getOrderNo());
                                relationProperties.get(i).setOrderTime(DateToString(salePurchaseOrder.getCreateDate()));
                                relationProperties.get(i).setCreatePeople(salePurchaseOrder.getMadePeople());
                            }
                            break;

                        case "网上订单":
                            SaleOnlineOrder saleOnlineOrder = iSaleOnlineOrderService.selectByKey(relationProperties.get(i).getId());
                            if(saleOnlineOrder != null ){
                                relationProperties.get(i).setOrders(saleOnlineOrder.getOrderNo());
                                relationProperties.get(i).setOrderTime(DateToString(saleOnlineOrder.getOrderDate()));
                                relationProperties.get(i).setCreatePeople("");
                            }
                            break;
                        case "采购订单":
                            PurchaseOrders purchaseOrders = purchaseOrdersService.selectByKey(relationProperties.get(i).getId());
                            if(purchaseOrders != null){
                                relationProperties.get(i).setOrders(purchaseOrders.getOrders());
                                relationProperties.get(i).setOrderTime(DateToString(purchaseOrders.getCreatedate()));
                                relationProperties.get(i).setCreatePeople(purchaseOrders.getCreatepeople());
                            }
                            break;
                        case "询价单":
                            InquiryOrder inquiryOrder = inquiryOrderService.selectByKey(relationProperties.get(i).getId());
                            if(inquiryOrder != null){
                                relationProperties.get(i).setOrders(inquiryOrder.getOrders());
                                relationProperties.get(i).setOrderTime(DateToString(inquiryOrder.getCreatedate()));
                                relationProperties.get(i).setCreatePeople(inquiryOrder.getCreatepeople());
                            }
                            break;
                        case "应收账款冲账":
                            AccountReceivable accountReceivable = iAccountReceivableService.selectByKey(relationProperties.get(i).getId());
                            if(accountReceivable != null){
                                relationProperties.get(i).setOrders(accountReceivable.getOrderNo());
                                relationProperties.get(i).setOrderTime(DateToString(accountReceivable.getRushDate()));
                                relationProperties.get(i).setCreatePeople(accountReceivable.getMadePeople());
                            }
                            break;
                        case "应付账款冲账":
                            AccountPayable accountPayable = iAccountPayableService.selectByKey(relationProperties.get(i).getId());
                            if(accountPayable != null){
                                relationProperties.get(i).setOrders(accountPayable.getOrderNo());
                                relationProperties.get(i).setOrderTime(DateToString(accountPayable.getRushDate()));
                                relationProperties.get(i).setCreatePeople(accountPayable.getMadePoeple());
                            }
                            break;
                        default:
                            break;

                    }

                    relationProperties.get(i).setSort(i+1);

                    relationPropertieList.add(relationProperties.get(i));
                }

            }

            stayOrderTableView.setItems(relationPropertieList);
        }

    }



    public void itemRelation(String orderTypeStr,long orderId){

        List<Relation> relations =  relationService.selectRelations(orderTypeStr,orderId);

        if(relations != null &&  relations.size() > 0){
            for (Relation relation:relations) {
                RelationProperty relationProperty = new RelationProperty();
                if(relation.getBeRelationName().equals(orderTypeStr) && relation.getBeRelationId() == orderId){
                    relationProperty.setOrderType(relation.getRelationName());
                    relationProperty.setId(relation.getRelationId());
//                    itemRelation(relation.getRelationName(),relation.getRelationId());
                }else if(relation.getRelationName().equals(orderTypeStr) && relation.getRelationId() == orderId){
                    relationProperty.setOrderType(relation.getBeRelationName());
                    relationProperty.setId(relation.getBeRelationId());
//                    itemRelation(relation.getBeRelationName(),relation.getBeRelationId());
                }
                relationProperties.add(relationProperty);
            }
        }
    }




    /**
     * 清除
     */
    public void clearAllInput(){
        orderType.getSelectionModel().select(-1);
        orders.setText(NumberUtil.NULLSTRING);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderType.getItems().setAll(AppConst.RELATION);
    }

}
