package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.stock.SaleOutboundOrderController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.StockOutSaleProductProperty;
import com.yc.education.service.DataSettingService;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 销货单导入 -- 查询
 */
@Controller
public class SaleGoodsImportController extends BaseController implements Initializable {

    @Autowired ISaleGoodsService iSaleGoodsService;
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;
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
     ObservableList<SaleGoodsProductProperty> importData = FXCollections.observableArrayList();
    // 导入选中的产品--销售退货单
    ObservableList<SaleReturnGoodsProductProperty> importPurchaseData = FXCollections.observableArrayList();
    // 导入选中的产品--销货出库单
    ObservableList<StockOutSaleProductProperty> importOutboundData = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
        importData.clear();
        importPurchaseData.clear();
        importOutboundData.clear();
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
        List<SaleGoods> saleGoodsList = iSaleGoodsService.listSaleGoodsByPage("",audit?"1":"",page, rows);
        if(saleGoodsList != null && saleGoodsList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(saleGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            initData(saleGoodsList);
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
        StageManager.CONTROLLER.remove("SaleReturnControllerImport");
        StageManager.CONTROLLER.remove("SaleOutboundOrderControllerImport");
        importData.clear();
        importPurchaseData.clear();
        importOutboundData.clear();
        stage.close();
    }

    /**
     * 确认按钮-关闭窗口
     */
    @FXML
    public void sureCloseImportWin(){
        if(orderid != null && !"".equals(orderid)){
            int rows = 1;
            // 销售 - 销售退货单
            SaleReturnController orderController = (SaleReturnController) StageManager.CONTROLLER.get("SaleReturnControllerImport");
            if(orderController != null){
                SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(orderid));
                if(!saleGoods.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到退货订单上
                orderController.setBasicImportVal(saleGoods);
                orderController.relation.setBeRelationId(saleGoods.getId());
                orderController.relation.setBeRelationName("销货单");
                // 把销货订单中的选中产品加载到销售退货单中的退货产品中
                for (SaleGoodsProductProperty k : importData) {
                    if(k.isChecked() && k.getId() != null && k.getId()>0){
                        SaleGoodsProduct p = iSaleGoodsProductService.selectByKey(k.getId());
                        totalCost(p.getNum()==null?0:p.getNum(),p.getMoney()==null?new BigDecimal("0.00"):p.getMoney(),saleGoods.getTax(), orderController.total_num, orderController.total_tax, orderController.total_loan, orderController.total_money);
                        importPurchaseData.add(new SaleReturnGoodsProductProperty( rows++, p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getPrice(), p.getMoney(),"销货单" , saleGoods.getSaleNo(),p.getWarehousePosition() , p.getFloor(),p.getRemark() ));
                    }
                }
                if(importPurchaseData != null){
                    orderController.generalProductTab(importPurchaseData);
                }
                orderController.setControllerUse();
            }

            // 库存 - 销货出库单
            SaleOutboundOrderController saleOutboundOrderController = (SaleOutboundOrderController) StageManager.CONTROLLER.get("SaleOutboundOrderControllerImport");
            if(saleOutboundOrderController != null){
                SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(orderid));
                if(!saleGoods.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到销货出库订单上
                saleOutboundOrderController.setSaleGoodsBasicVal(saleGoods);
                saleOutboundOrderController.relation.setBeRelationId(saleGoods.getId());
                saleOutboundOrderController.relation.setBeRelationName("销货单");
                // 把销货单中的选中产品加载到销货出库单的出货产品中
                for (SaleGoodsProductProperty k : importData) {
                    if(k.isChecked() && k.getId() != null && k.getId()>0){
                        SaleGoodsProduct p = iSaleGoodsProductService.selectByKey(k.getId());
                        importOutboundData.add(new StockOutSaleProductProperty( 0L, rows++,0L,"销货单", saleGoods.getSaleNo(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPrice(), p.getWarehousePosition(), p.getFloor(), p.getRemark()));
                    }
                }
                if(importOutboundData != null){
                    saleOutboundOrderController.generalProductTab(importOutboundData);
                }
                saleOutboundOrderController.setControllerUse();
            }
        }
        closeImprotWin();
    }

    /**
     * 初始化销货单信息
     */
    private void initData(List<SaleGoods> list){
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
        final ObservableList<SaleGoods> data = FXCollections.observableArrayList(list);
        col_order_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_order_category.setCellValueFactory(new PropertyValueFactory("customerCategory"));
        col_order_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_order_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_order_status.setCellValueFactory(new PropertyValueFactory("auditStatus"));

        tab_order.setItems(data);

        // 选择行 查询数据
        tab_order.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleGoods> observableValue, SaleGoods oldItem, SaleGoods newItem) {
                if(newItem.getId() != null && !"".equals(newItem.getId()) && newItem.getSaleNo() != null && !"".equals(newItem.getSaleNo())){
                    SaleGoodsImportController.orderid = newItem.getId().toString();
                    List<SaleGoodsProduct> productList = iSaleGoodsProductService.listSaleGoodsProduct(newItem.getId().toString());
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

                    try {
                        for (SaleGoodsProduct p : productList) {
                            importData.add(new SaleGoodsProductProperty(p.getId(),  p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(),p.getDiscount()+"" ,p.getPrice() ,p.getMoney() , newItem.getSaleNo() , "销货单",p.getRemark(),false));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    tab_product.setItems(importData);

                }
            }
        });
        // 设置选择多行
        tab_product.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
