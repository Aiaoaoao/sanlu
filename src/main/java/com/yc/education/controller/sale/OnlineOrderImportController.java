package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.sale.*;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.sale.ISaleOnlineOrderProductService;
import com.yc.education.service.sale.ISaleOnlineOrderService;
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
public class OnlineOrderImportController extends BaseController implements Initializable {


    @Autowired ISaleOnlineOrderService iSaleOnlineOrderService;
    @Autowired ISaleOnlineOrderProductService iSaleOnlineOrderProductService;

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
    ObservableList<SaleOnlineOrderProductProperty> importData = FXCollections.observableArrayList();
    // 导入选中的产品 -- 销货单
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
        List<SaleOnlineOrder> onlineOrderList = iSaleOnlineOrderService.listSaleOnlineOrderByPage("",audit?"1":"",page, rows);
        if(onlineOrderList != null && onlineOrderList.size() >0){
            PageInfo<SaleOnlineOrder> pageInfo = new PageInfo<>(onlineOrderList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            initData(onlineOrderList);
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
        StageManager.CONTROLLER.remove("SaleGoodsControllerImportOnline");
        importData.clear();
        stage.close();
    }
    /**
     * 确认按钮-关闭窗口
     */
    @FXML
    public void sureCloseImportWin(){
        if(orderid != null && !"".equals(orderid)){
            // 销售 - 销货单
            SaleGoodsController saleGoodsController = (SaleGoodsController) StageManager.CONTROLLER.get("SaleGoodsControllerImportOnline");
            if(saleGoodsController != null){
                SaleOnlineOrder saleOnlineOrder = iSaleOnlineOrderService.selectByKey(Long.valueOf(orderid));
                if(!saleOnlineOrder.getOrderAudit()){
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出");
                    return;
                }
                // 把选中订单数据加载到销货单上
                saleGoodsController.setBasicImportOnlineVal(saleOnlineOrder);
                saleGoodsController.relation.setBeRelationId(saleOnlineOrder.getId());
                saleGoodsController.relation.setBeRelationName("网上订单");
                if(saleGoodsController.product_table.getItems() != null){
                    importSaleGoodsData = saleGoodsController.product_table.getItems();
                }
                // 把网上订单中的选中的产品加载到销货单的销货产品中
                int rows = 1;
                for (SaleOnlineOrderProductProperty p : importData) {
                    if(p.isChecked() && p.getId() != null && p.getId()>0){
                        SaleOnlineOrderProduct product = iSaleOnlineOrderProductService.selectByKey(p.getId());
                        DepotProperty depotProperty = new DepotProperty();
                        ProductBasic productBasic = getProductBasic(p.getProductNo());
                        if(productBasic != null){
                            depotProperty = getDepot(productBasic.getInventoryplace());
                        }
                        totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),"外加", saleGoodsController.total_num, saleGoodsController.total_tax, saleGoodsController.total_loan, saleGoodsController.total_money);
                        importSaleGoodsData.add(new SaleGoodsProductProperty(rows++,"网上订单",saleOnlineOrder.getOrderNo(), product.getProductNo(), product.getProductName(), product.getCategory(), product.getNum(), product.getUnit(), product.getPrice(), "", product.getPrice(), product.getMoney(), depotProperty.getDepotOrder(), depotProperty.getDepotFloor(), product.getRemark()));
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
     * 初始化网上订单信息
     */
    private void initData(List<SaleOnlineOrder> list){

        try {
            int rows = 1;
            for (SaleOnlineOrder p : list) {
                p.setNo(rows++);
                p.setOrderDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getOrderDate()));
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
        final ObservableList<SaleOnlineOrder> data = FXCollections.observableArrayList(list);
        col_order_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("orderDateStr"));
        col_order_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_order_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_order_status.setCellValueFactory(new PropertyValueFactory("auditStatus"));

        tab_order.setItems(data);

        // 选择行 查询数据
        tab_order.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleOnlineOrder>() {
            @Override
            public void changed(ObservableValue<? extends SaleOnlineOrder> observableValue, SaleOnlineOrder oldItem, SaleOnlineOrder newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    OnlineOrderImportController.orderid = newItem.getId().toString();
                    List<SaleOnlineOrderProduct> onlineOrderProductList = iSaleOnlineOrderProductService.listByOnlineOrderAll(newItem.getId().toString());

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
                    for (SaleOnlineOrderProduct p : onlineOrderProductList) {
                        importData.add(new SaleOnlineOrderProductProperty(p.getId(),rows++,p.getProductNo(),p.getProductName(),p.getCategory(),p.getNum(),p.getUnit(),p.getPrice(),p.getMoney(),p.getUsableNum(),p.getRemark(),false));
                    }
                    tab_product.setItems(importData);

                }
            }
        });
        // 设置选择多行
        tab_product.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
