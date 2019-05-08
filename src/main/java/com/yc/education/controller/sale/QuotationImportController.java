package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.sale.*;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.sale.ISaleOfferProductService;
import com.yc.education.service.sale.ISaleQuotationService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 报价单导入 -- 查询
 */
@Controller
public class QuotationImportController extends BaseController implements Initializable {


    @Autowired ISaleQuotationService iSaleQuotationService;
    @Autowired ISaleOfferProductService iSaleOfferProductService;
    @Autowired DataSettingService iDataSettingService;


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;    // 最近单据
    @FXML CheckBox che_audit;       // 已审核
    @FXML TextField num;            // 数量


    @FXML
    Button client_sure;

    @FXML TableView tab_order;

    @FXML TableColumn col_order_id;
    @FXML TableColumn col_order_no;
    @FXML TableColumn col_order_date;
    @FXML TableColumn col_order_valid_until; //有效期至
    @FXML TableColumn col_order_customer_no;
    @FXML TableColumn col_order_customer_name;
    @FXML TableColumn col_order_status;

    @FXML TableView tab_product;

    @FXML TableColumn tab_product_che;
    @FXML TableColumn tab_product_id;
    @FXML TableColumn tab_product_no;
    @FXML TableColumn tab_product_name;
    @FXML TableColumn tab_product_num;
    @FXML TableColumn tab_product_unit;
    @FXML TableColumn tab_product_price;
    @FXML TableColumn tab_product_money;
    @FXML TableColumn tab_product_remark;


    // 订单编号
    private static String  orderid = "";
    // 查询订单中产品
    ObservableList<SaleQuotationImportProductProperty> importData = FXCollections.observableArrayList();
    // 导入选中的产品 -- 订货单
    ObservableList<SalePurchaseOrderProductProperty> importPurchaseData = FXCollections.observableArrayList();
    // 导入选中的产品 -- 销货单
    ObservableList<SaleGoodsProductProperty> importSaleGoodsData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
        importData = FXCollections.observableArrayList();
        importPurchaseData = FXCollections.observableArrayList();
        importSaleGoodsData = FXCollections.observableArrayList();
    }


    /**
     * @Description 模糊查询
     * @Author BlueSky
     * @Date 12:00 2019/4/11
     **/
    @FXML
    public void textQuery(){
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        int rows = pageRows(che_recently,num);
        boolean audit = che_audit.isSelected();
        List<SaleQuotation> quotationList = iSaleQuotationService.listSaleQuotationAll("",audit?"1":"",page, rows);
        if(quotationList != null && quotationList.size() >0){
            PageInfo<SaleQuotation> pageInfo = new PageInfo<>(quotationList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            initData(quotationList);
        }else {
            tab_order.setItems(null);
        }
    }


    /**
     * 分页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        if(node.getUserData() != null){
            int page =Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
        }
    }


    /**
     * 关闭导入窗口
     */
    @FXML
    public void closeImprotWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("PurchaseOrderControllerImport");
        StageManager.CONTROLLER.remove("SaleGoodsControllerImportQuotation");
        importData.clear();
        importPurchaseData.clear();
        importSaleGoodsData.clear();
        stage.close();
    }
    /**
     * 确认按钮-关闭窗口
     */
    @FXML
    public  void  sureCloseImportWin(){
        if(orderid != null && !"".equals(orderid)){
            int rows = 1;
            // 销售 - 订货单
            PurchaseOrderController orderController = (PurchaseOrderController) StageManager.CONTROLLER.get("PurchaseOrderControllerImport");
            if(orderController != null){
                SaleQuotation quotation = iSaleQuotationService.selectByKey(Long.valueOf(orderid));
                if(!quotation.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到订货单上
                orderController.setBasicImportVal(quotation);
                orderController.relation.setBeRelationId(quotation.getId());
                orderController.relation.setBeRelationName("报价单");
                // 把报价单中的选中产品加载到订货单的订货产品中
                for (SaleQuotationImportProductProperty p : importData) {
                    if(p.isChecked() && p.getId() != null && p.getId()>0){
                        SaleOfferProduct product = iSaleOfferProductService.selectByKey(p.getId());
                        DepotProperty depotProperty = new DepotProperty();
                        ProductBasic productBasic = getProductBasic(p.getProductNo());
                        if(productBasic != null){
                            depotProperty = getDepot(productBasic.getInventoryplace());
                        }
                        totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),quotation.getTax(), orderController.total_num, orderController.tax_total, orderController.total_loan, orderController.total_money);
                        importPurchaseData.add(new SalePurchaseOrderProductProperty( rows++, product.getProductNo(), product.getProductName(), product.getCategory(), product.getNum(), product.getUnit(), product.getPricing(), product.getDiscount(), product.getPrice(), product.getMoney(), quotation.getOfferNo(), "报价单", depotProperty.getDepotOrder(),depotProperty.getDepotFloor(),product.getRemark()));
                    }
                }
                if(importPurchaseData != null){
                    orderController.generalProductTab(importPurchaseData);
                }
                orderController.setControllerUse();
            }

            // 销售 - 销货单
            SaleGoodsController saleGoodsController = (SaleGoodsController) StageManager.CONTROLLER.get("SaleGoodsControllerImportQuotation");
            if(saleGoodsController != null){
                SaleQuotation quotation = iSaleQuotationService.selectByKey(Long.valueOf(orderid));
                if(!quotation.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到销货单上
                saleGoodsController.setBasicImportQuotationVal(quotation);
                saleGoodsController.relation.setBeRelationId(quotation.getId());
                saleGoodsController.relation.setBeRelationName("报价单");
                // 把报价单中的选中产品加载到销货单的销货产品中
                if(saleGoodsController.product_table.getItems() != null){
                    importSaleGoodsData = saleGoodsController.product_table.getItems();
                }
                for (SaleQuotationImportProductProperty p : importData) {
                    if(p.isChecked() && p.getId() != null && p.getId()>0){
                        SaleOfferProduct product = iSaleOfferProductService.selectByKey(p.getId());
                        DepotProperty depotProperty = new DepotProperty();
                        ProductBasic productBasic = getProductBasic(p.getProductNo());
                        if(productBasic != null){
                            depotProperty = getDepot(productBasic.getInventoryplace());
                        }
                        totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),quotation.getTax(), saleGoodsController.total_num, saleGoodsController.total_tax, saleGoodsController.total_loan, saleGoodsController.total_money);
                        importSaleGoodsData.add(new SaleGoodsProductProperty(rows++,"报价单",quotation.getOfferNo(),  product.getProductNo(), product.getProductName(), product.getCategory(), product.getNum(), product.getUnit(), product.getPricing(), product.getDiscount(), product.getPrice(), product.getMoney(), depotProperty.getDepotOrder(), depotProperty.getDepotFloor(), product.getRemark()));
                    }
                }
                if(importSaleGoodsData != null){
                    saleGoodsController.generalProductTab(importSaleGoodsData);
                }
                saleGoodsController.setControllerUse();
            }
        }
        closeImprotWin();
    }

    /**
     * 初始化报价单信息
     */
    private void initData(List<SaleQuotation> list){

        try {
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                p.setValidUntilStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getValidUntil()));
                if(p.getOrderAudit() != null && p.getOrderAudit()){
                    p.setAuditStatus("已审核");
                }else{
                    p.setAuditStatus("未审核");
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }

        // 查询客户集合
        final ObservableList<SaleQuotation> data = FXCollections.observableArrayList(list);
        col_order_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("offerNo"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));//映射
        col_order_valid_until.setCellValueFactory(new PropertyValueFactory("validUntilStr"));
        col_order_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_order_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_order_status.setCellValueFactory(new PropertyValueFactory("auditStatus"));

        tab_order.setItems(data);

        // 选择行 查询数据
        tab_order.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleQuotation>() {
            @Override
            public void changed(ObservableValue<? extends SaleQuotation> observableValue, SaleQuotation oldItem, SaleQuotation newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    QuotationImportController.orderid = newItem.getId().toString();
                    List<SaleOfferProduct> offerProductList = iSaleOfferProductService.listSaleOfferProduct(newItem.getId());

                    importData.clear();
                    tab_product.setEditable(true);
                    tab_product_che.setCellFactory(CheckBoxTableCell.forTableColumn(tab_product_che));
                    tab_product_che.setCellValueFactory(new PropertyValueFactory("checked"));
                    tab_product_id.setCellValueFactory(new PropertyValueFactory("id"));
                    tab_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
                    tab_product_name.setCellValueFactory(new PropertyValueFactory("productName"));//映射
                    tab_product_num.setCellValueFactory(new PropertyValueFactory("num"));
                    tab_product_unit.setCellValueFactory(new PropertyValueFactory("unit"));
                    tab_product_price.setCellValueFactory(new PropertyValueFactory("price"));
                    tab_product_money.setCellValueFactory(new PropertyValueFactory("money"));
                    tab_product_remark.setCellValueFactory(new PropertyValueFactory("remark"));


                    for (SaleOfferProduct p : offerProductList) {
                        importData.add(new SaleQuotationImportProductProperty(p.getId(), p.getProductNo(), p.getProductName(), p.getNum(), p.getUnit(), p.getPrice(),p.getMoney(), p.getRemark(), false));
                    }
                    tab_product.setItems(importData);

                }
            }
        });
        // 设置选择多行
        tab_product.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
