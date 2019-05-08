package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.sale.*;
import com.yc.education.model.sale.SaleGoodsProductProperty;
import com.yc.education.model.sale.SalePurchaseOrderProductProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.sale.*;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * 订货单导入--查询
 */
@Controller
public class PurchaseOrderImportController extends BaseController implements Initializable {


    @Autowired ICustomerService iCustomerService;
    @Autowired ISalePurchaseOrderService iSalePurchaseOrderService;
    @Autowired ISalePurchaseOrderProductService iSalePurchaseOrderProductService;
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
    @FXML TableColumn col_order_category; //客户类型
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
    @FXML TableColumn tab_product_remark;


    // 订单编号
    private static String  orderid = "";
    // 查询订单中产品
    ObservableList<SalePurchaseOrderProductProperty> importData = FXCollections.observableArrayList();
    // 导入选中的产品--销货单
    ObservableList<SaleGoodsProductProperty> importSaleGoodsData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
        importData  = FXCollections.observableArrayList();
        importSaleGoodsData  = FXCollections.observableArrayList();
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
        List<SalePurchaseOrder> purchaseOrderList = iSalePurchaseOrderService.listSalePurchaseOrderByPage("",audit?"1":"",page, rows);
        if(purchaseOrderList != null && purchaseOrderList.size() >0){
            PageInfo<SalePurchaseOrder> pageInfo = new PageInfo<>(purchaseOrderList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            initData(purchaseOrderList);
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
        StageManager.CONTROLLER.remove("SaleGoodsControllerImport");
        importData.clear();
        importSaleGoodsData.clear();
        stage.close();
    }

    /**
     * 确认按钮-关闭窗口
     */
    @FXML
    public void sureCloseImportWin(){
        if(orderid != null && !"".equals(orderid)){
            // 销货单
            SaleGoodsController orderController = (SaleGoodsController) StageManager.CONTROLLER.get("SaleGoodsControllerImport");
            if(orderController != null){
                SalePurchaseOrder order = iSalePurchaseOrderService.selectByKey(Long.valueOf(orderid));
                if(!order.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到订货单上
                orderController.setBasicImportPurchaseVal(order);
                orderController.relation.setBeRelationId(order.getId());
                orderController.relation.setBeRelationName("订货单");
                if(orderController.product_table.getItems() != null){
                    importSaleGoodsData = orderController.product_table.getItems();
                }
                // 把订货单中的选中产品加载到销货单的采购产品中
                int rows = 1;
                for (SalePurchaseOrderProductProperty p : importData) {
                    if(p.isChecked() && p.getId() != null && p.getId()>0){
                        totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),order.getTax(), orderController.total_num, orderController.total_tax, orderController.total_loan, orderController.total_money);
                        SalePurchaseOrderProduct product = iSalePurchaseOrderProductService.selectByKey(p.getId());
                        importSaleGoodsData.add(new SaleGoodsProductProperty(rows++,"订货单",order.getOrderNo(),  product.getProductNo(), product.getProductName(), product.getCategory(), product.getNum(), product.getUnit(), product.getPricing(), product.getDiscount(), product.getPrice(), product.getMoney(), product.getWarehousePosition(), product.getFloor(), product.getRemark()));
                    }
                }
                Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
                if(customer != null){
                    orderController.receivable.setText(customer.getUseableMoney()==null?"0.00":customer.getUseableMoney().toString());
                }
                if(importSaleGoodsData != null){
                    orderController.generalProductTab(importSaleGoodsData);
                }
                orderController.setControllerUse();
            }
        }
        closeImprotWin();
    }

    /**
     * 初始化订货单信息
     */
    private void initData(List<SalePurchaseOrder> list ){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getOrderAudit() != null && p.getOrderAudit()){
                    p.setAuditStatus("已审核");
                }else{
                    p.setAuditStatus("未审核");
                }
            });
        }

        // 查询客户集合
        final ObservableList<SalePurchaseOrder> data = FXCollections.observableArrayList(list);
        col_order_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_order_category.setCellValueFactory(new PropertyValueFactory("customerCategory"));
        col_order_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_order_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_order_status.setCellValueFactory(new PropertyValueFactory("auditStatus"));

        tab_order.setItems(data);

        // 选择行 查询数据
        tab_order.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalePurchaseOrder>() {
            @Override
            public void changed(ObservableValue<? extends SalePurchaseOrder> observableValue, SalePurchaseOrder oldItem, SalePurchaseOrder newItem) {
                if(newItem.getId() != null && !"".equals(newItem.getId()) && newItem.getOrderNo() != null && !"".equals(newItem.getOrderNo())){
                    PurchaseOrderImportController.orderid = newItem.getId().toString();
                    List<SalePurchaseOrderProduct> productList = iSalePurchaseOrderProductService.listPurchaseOrderProduct(newItem.getId().toString());
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
                    tab_product_remark.setCellValueFactory(new PropertyValueFactory("remark"));


                    for (SalePurchaseOrderProduct p : productList) {
                        importData.add(new SalePurchaseOrderProductProperty(p.getId(), 0L, p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(),p.getDiscount() ,p.getPrice() ,p.getMoney() , newItem.getOrderNo() , "采购单",p.getWarehousePosition(),p.getFloor(),p.getRemark(),false));
                    }
                    tab_product.setItems(importData);

                }
            }
        });
        // 设置选择多行
        tab_product.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
