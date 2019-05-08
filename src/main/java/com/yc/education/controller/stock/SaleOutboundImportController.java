package com.yc.education.controller.stock;

import com.yc.education.controller.BaseController;
import com.yc.education.controller.sale.SaleReturnController;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.StockOutSale;
import com.yc.education.model.stock.StockOutSaleProduct;
import com.yc.education.model.stock.StockOutSaleProductProperty;
import com.yc.education.service.stock.IStockOutSaleProductService;
import com.yc.education.service.stock.IStockOutSaleService;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 销货出库导入 -- 查询
 */
@Controller
public class SaleOutboundImportController extends BaseController implements Initializable {


    @Autowired IStockOutSaleService iStockOutSaleService;
    @Autowired IStockOutSaleProductService iStockOutSaleProductService;

    @FXML
    Button client_sure;

    @FXML TableView tab_order;

    @FXML TableColumn col_order_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_order_no;
    @FXML TableColumn col_order_date;
    @FXML TableColumn col_order_customer_no;
    @FXML TableColumn col_order_customer_name;
    @FXML TableColumn col_order_status;

    @FXML TableView tab_product;

    @FXML TableColumn tab_product_che;
    @FXML TableColumn tab_no;
    @FXML TableColumn tab_product_id;
    @FXML TableColumn tab_product_no;
    @FXML TableColumn tab_product_name;
    @FXML TableColumn tab_product_num;
    @FXML TableColumn tab_product_unit;
    @FXML TableColumn tab_product_price;
    @FXML TableColumn tab_product_remark;


    // 订单编号
    private static String  orderid = "";
    // 查询订单中产品
    final ObservableList<StockOutSaleProductProperty> importData = FXCollections.observableArrayList();
    // 导入选中的产品 -- 销售退货单
    final ObservableList<SaleReturnGoodsProductProperty> importSaleReturnData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        importData.clear();
    }


    /**
     * 关闭导入窗口
     */
    @FXML
    public void closeImprotWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("SaleReturnControllerImportOutbound");
        importData.clear();
        stage.close();
    }

    /**
     * 确认按钮-关闭窗口
     */
    @FXML
    public void sureCloseImportWin(){
        if(orderid != null && !"".equals(orderid)){
            // 销售 - 销售退货单
            SaleReturnController saleReturnController = (SaleReturnController) StageManager.CONTROLLER.get("SaleReturnControllerImportOutbound");
            if(saleReturnController != null){
                StockOutSale stockOutSale = iStockOutSaleService.selectByKey(Long.valueOf(orderid));
                if(!stockOutSale.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到销售退货单上
                saleReturnController.setBasicImportOutboundVal(stockOutSale);
                // 把销货出库单中的选中的产品加载到销售退货单的销退产品中
                for (StockOutSaleProductProperty k : importData) {
                    if(k.isChecked() && k.getId() != null && k.getId()>0){
                        StockOutSaleProduct p = iStockOutSaleProductService.selectByKey(k.getId());
                        totalCost(k.getNum()==null?0:p.getNum(),p.getPrice().multiply(new BigDecimal(p.getNum().toString())),"", saleReturnController.total_num, saleReturnController.total_tax, saleReturnController.total_loan, saleReturnController.total_money);
                        importSaleReturnData.add(new SaleReturnGoodsProductProperty( p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPrice(), p.getPrice(), p.getPrice().multiply(new BigDecimal(p.getNum()+"")).setScale(2,BigDecimal.ROUND_HALF_UP), "销货出库单", stockOutSale.getOutboundNo(), p.getWarehouseName(), p.getFloor(), p.getRemark()));
                    }
                }
                if(importSaleReturnData != null){
                    saleReturnController.generalProductTab(importSaleReturnData);
                }
                saleReturnController.setControllerUse();
            }
        }
        closeImprotWin();
    }

    /**
     * 初始化网上订单信息
     */
    private void initData(){
        List<StockOutSale> list = iStockOutSaleService.listStockOutSaleAll();
        try {
            int rows = 1;
            for (StockOutSale p : list) {
                p.setNo(rows++);
                p.setOrderDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getOrderAudit() != null && p.getOrderAudit()){
                    p.setAuditStatus("已审核");
                }else{
                    p.setAuditStatus("未审核");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 查询客户集合
        final ObservableList<StockOutSale> data = FXCollections.observableArrayList(list);
        col_order_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("outboundNo"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("orderDateStr"));
        col_order_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_order_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_order_status.setCellValueFactory(new PropertyValueFactory("auditStatus"));

        tab_order.setItems(data);

        // 选择行 查询数据
        tab_order.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StockOutSale>() {
            @Override
            public void changed(ObservableValue<? extends StockOutSale> observableValue, StockOutSale oldItem, StockOutSale newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    SaleOutboundImportController.orderid = newItem.getId().toString();
                    List<StockOutSaleProduct> stockProductList = iStockOutSaleProductService.listStockOutSaleProduct(newItem.getId().toString());

                    importData.clear();
                    tab_product.setEditable(true);
                    tab_product_che.setCellFactory(CheckBoxTableCell.forTableColumn(tab_product_che));
                    tab_product_che.setCellValueFactory(new PropertyValueFactory("checked"));
                    tab_product_id.setCellValueFactory(new PropertyValueFactory("id"));
                    tab_no.setCellValueFactory(new PropertyValueFactory("no"));
                    tab_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
                    tab_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
                    tab_product_num.setCellValueFactory(new PropertyValueFactory("num"));
                    tab_product_unit.setCellValueFactory(new PropertyValueFactory("unit"));
                    tab_product_price.setCellValueFactory(new PropertyValueFactory("price"));
                    tab_product_remark.setCellValueFactory(new PropertyValueFactory("remark"));

                    int rows = 1;
                    for (StockOutSaleProduct p : stockProductList) {
                        importData.add(new StockOutSaleProductProperty(p.getId(),rows++,null,"销货出库单",p.getOrderNo(),p.getProductNo(),p.getProductName(),p.getCategory(),p.getNum(),p.getUnit(),p.getPrice(),p.getWarehouseName(),p.getFloor(),p.getRemark(),false));
                    }
                    tab_product.setItems(importData);

                }
            }
        });
        // 设置选择多行
        tab_product.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
