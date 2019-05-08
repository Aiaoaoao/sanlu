package com.yc.education.controller;

import com.yc.education.model.StayToExamine;
import com.yc.education.model.account.*;
import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.CheckStock;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.model.stock.StockChange;
import com.yc.education.model.stock.StockOutSale;
import com.yc.education.service.account.*;
import com.yc.education.service.purchase.InquiryOrderService;
import com.yc.education.service.purchase.PurchaseOrdersService;
import com.yc.education.service.sale.*;
import com.yc.education.service.stock.CheckStockService;
import com.yc.education.service.stock.IStockOutSaleService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.service.stock.StockChangeService;
import com.yc.education.util.AppConst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName StayToExamineController
 * @Description TODO 待审核单据
 * @Author QuZhangJing
 * @Date 2019/4/25 15:32
 * @Version 1.0
 */
@Controller
public class StayToExamineController extends  BaseController implements Initializable {


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
    //销项发票
    @Autowired
    private IAccountSaleInvoiceService iAccountSaleInvoiceService;
    //进项发票
    @Autowired
    private IAccountInputInvoiceService iAccountInputInvoiceService;
    //收款单
    @Autowired
    private IAccountReceiptService iAccountReceiptService;
    //预付账款
    @Autowired
    private IAccountPrepaymentService iAccountPrepaymentService;



    //单据类型
    @FXML
    private ComboBox orderType;

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



    /***
     * 查询
     */
    public  void  searchOroderInfo(){

        String orderTypes = orderType.getSelectionModel().getSelectedItem()+"";


        if(!"".equals(orderTypes) && orderTypes != null ){

            ObservableList<StayToExamine> stayToExamines = FXCollections.observableArrayList();

            tabSort.setCellValueFactory(new PropertyValueFactory<>("sort"));
            tabOrderType.setCellValueFactory(new PropertyValueFactory<>("orderType"));
            tabOrders.setCellValueFactory(new PropertyValueFactory<>("orders"));
            tabTime.setCellValueFactory(new PropertyValueFactory<>("orderTime"));
            createPeople.setCellValueFactory(new PropertyValueFactory<>("createPeople"));

            switch (orderTypes){

                case "采购入库单":
                    List<PurchaseStock> purchaseStocks =  purchaseStockService.findPurchanseStockNotSh();

                    if(purchaseStocks != null && purchaseStocks.size()>0){
                        for (int i=0,len = purchaseStocks.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(purchaseStocks.get(i).getId(),i+1,AppConst.STAY_ORDER[0],purchaseStocks.get(i).getStockorder(),DateToString(purchaseStocks.get(i).getCreatedate()),purchaseStocks.get(i).getCreatename());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "销货出库单":
                    List<StockOutSale> stockOutSales = iStockOutSaleService.listStockOutSaleNotSh();
                    if(stockOutSales != null &&  stockOutSales.size() > 0){
                        for (int i=0,len = stockOutSales.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(stockOutSales.get(i).getId(),i+1,AppConst.STAY_ORDER[1],stockOutSales.get(i).getOutboundNo(),DateToString(stockOutSales.get(i).getCreateDate()),stockOutSales.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "库存异动作业":
                    List<StockChange> stockChanges = stockChangeService.findStockChangeNotSh();
                        if(stockChanges != null && stockChanges.size() > 0){
                            for (int i=0,len = stockChanges.size();i<len;i++) {
                                StayToExamine stayToExamine = new StayToExamine(stockChanges.get(i).getId(),i+1,AppConst.STAY_ORDER[2],stockChanges.get(i).getChangeorder(),DateToString(stockChanges.get(i).getChangedate()),stockChanges.get(i).getCreatepeople());
                                stayToExamines.add(stayToExamine);
                            }
                        }
                    break;
                case "盘库作业":
                    List<CheckStock> checkStocks = checkStockService.findCheckStockNotSh();
                    if(checkStocks != null && checkStocks.size() > 0){
                        for (int i=0,len = checkStocks.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(checkStocks.get(i).getId(),i+1,AppConst.STAY_ORDER[3],checkStocks.get(i).getCheckorder(),DateToString(checkStocks.get(i).getCheckdate()),checkStocks.get(i).getCreatepeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "销货单":
                    List<SaleGoods> saleGoods = iSaleGoodsService.listSaleGoodsNotSh();
                    if(saleGoods != null && saleGoods.size() > 0){
                        for (int i=0,len = saleGoods.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(saleGoods.get(i).getId(),i+1,AppConst.STAY_ORDER[4],saleGoods.get(i).getSaleNo(),DateToString(saleGoods.get(i).getCreateDate()),saleGoods.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "销售退货单":
                    List<SaleReturnGoods> saleReturnGoods = iSaleReturnGoodsService.listSaleReturnGoodsNotSh();
                    if(saleReturnGoods != null && saleReturnGoods.size() > 0){
                        for (int i=0,len = saleReturnGoods.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(saleReturnGoods.get(i).getId(),i+1,AppConst.STAY_ORDER[5],saleReturnGoods.get(i).getPinbackNo(),DateToString(saleReturnGoods.get(i).getCreateDate()),saleReturnGoods.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "报价单":
                    List<SaleQuotation> saleQuotations = iSaleQuotationService.listSaleQuotationNotSh();
                    if(saleQuotations != null && saleQuotations.size() > 0){
                        for (int i=0,len = saleQuotations.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(saleQuotations.get(i).getId(),i+1,AppConst.STAY_ORDER[6],saleQuotations.get(i).getOfferNo(),DateToString(saleQuotations.get(i).getCreateDate()),saleQuotations.get(i).getSinglePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "订货单":
                    List<SalePurchaseOrder> salePurchaseOrders = iSalePurchaseOrderService.listSalePurchanseOrderNotSh();
                    if(salePurchaseOrders != null && salePurchaseOrders.size() > 0){
                        for (int i=0,len = salePurchaseOrders.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(salePurchaseOrders.get(i).getId(),i+1,AppConst.STAY_ORDER[7],salePurchaseOrders.get(i).getOrderNo(),DateToString(salePurchaseOrders.get(i).getCreateDate()),salePurchaseOrders.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;

                case "网上订单":
                    List<SaleOnlineOrder> saleOnlineOrders = iSaleOnlineOrderService.listSaleOnlineOrderNotSh();
                    if(saleOnlineOrders != null && saleOnlineOrders.size() > 0){
                        for (int i=0,len = saleOnlineOrders.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(saleOnlineOrders.get(i).getId(),i+1,AppConst.STAY_ORDER[8],saleOnlineOrders.get(i).getOrderNo(),DateToString(saleOnlineOrders.get(i).getOrderDate()),"");
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "采购订单":
                    List<PurchaseOrders> purchaseOrders = purchaseOrdersService.findPurchaseOrderNotSh();
                    if(purchaseOrders != null && purchaseOrders.size() > 0){
                        for (int i=0,len = purchaseOrders.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(purchaseOrders.get(i).getId(),i+1,AppConst.STAY_ORDER[9],purchaseOrders.get(i).getOrders(),DateToString(purchaseOrders.get(i).getCreatedate()),purchaseOrders.get(i).getCreatepeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "询价单":
                    List<InquiryOrder> inquiryOrders = inquiryOrderService.findInquiryOrderNotSh();
                    if(inquiryOrders != null && inquiryOrders.size() > 0){
                        for (int i=0,len = inquiryOrders.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(inquiryOrders.get(i).getId(),i+1,AppConst.STAY_ORDER[10],inquiryOrders.get(i).getOrders(),DateToString(inquiryOrders.get(i).getCreatedate()),inquiryOrders.get(i).getCreatepeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;

                case "应收账款冲账":
                    List<AccountReceivable> accountReceivables = iAccountReceivableService.listAccountReceivableNotSh();
                    if(accountReceivables != null && accountReceivables.size() > 0){
                        for (int i=0,len = accountReceivables.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(accountReceivables.get(i).getId(),i+1,AppConst.STAY_ORDER[11],accountReceivables.get(i).getOrderNo(),DateToString(accountReceivables.get(i).getRushDate()),accountReceivables.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "应付账款冲账":
                    List<AccountPayable> accountPayables = iAccountPayableService.listAccountPayableNotSh();
                    if(accountPayables != null && accountPayables.size() > 0){
                        for (int i=0,len = accountPayables.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(accountPayables.get(i).getId(),i+1,AppConst.STAY_ORDER[12],accountPayables.get(i).getOrderNo(),DateToString(accountPayables.get(i).getRushDate()),accountPayables.get(i).getMadePoeple());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "销项发票":
                    List<AccountSaleInvoice> saleInvoices =  iAccountSaleInvoiceService.listAccountSaleInvoiceNotSh();
                    if(saleInvoices != null && saleInvoices.size() > 0){
                        for (int i=0,len = saleInvoices.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(saleInvoices.get(i).getId(),i+1,AppConst.STAY_ORDER[13],saleInvoices.get(i).getOrderNo(),DateToString(saleInvoices.get(i).getCreateDate()),saleInvoices.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "进项发票":
                    List<AccountInputInvoice> accountInputInvoices = iAccountInputInvoiceService.listAccountInputInvoiceNotSh();
                    if(accountInputInvoices != null && accountInputInvoices.size() > 0){
                        for (int i=0,len = accountInputInvoices.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(accountInputInvoices.get(i).getId(),i+1,AppConst.STAY_ORDER[14],accountInputInvoices.get(i).getOrderNo(),DateToString(accountInputInvoices.get(i).getCreateDate()),accountInputInvoices.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "收款单":
                    List<AccountReceipt> accountReceipts = iAccountReceiptService.listAccountReceiptNotSh();
                    if(accountReceipts != null && accountReceipts.size() > 0){
                        for (int i=0,len = accountReceipts.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(accountReceipts.get(i).getId(),i+1,AppConst.STAY_ORDER[15],accountReceipts.get(i).getOrderNo(),DateToString(accountReceipts.get(i).getCreateDate()),accountReceipts.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                case "预付账款":
                    List<AccountPrepayment> accountPrepayments = iAccountPrepaymentService.listAccountPrepaymentNotSh();
                    if(accountPrepayments != null && accountPrepayments.size() > 0){
                        for (int i=0,len = accountPrepayments.size();i<len;i++) {
                            StayToExamine stayToExamine = new StayToExamine(accountPrepayments.get(i).getId(),i+1,AppConst.STAY_ORDER[16],accountPrepayments.get(i).getOrderNo(),DateToString(accountPrepayments.get(i).getCreateDate()),accountPrepayments.get(i).getMadePeople());
                            stayToExamines.add(stayToExamine);
                        }
                    }
                    break;
                    default:
                        break;
            }

            stayOrderTableView.setItems(stayToExamines);

        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        orderType.getItems().setAll(AppConst.STAY_ORDER);


    }

}
