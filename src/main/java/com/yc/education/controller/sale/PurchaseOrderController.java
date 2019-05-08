package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;

import com.yc.education.model.DataSetting;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.customer.Remark;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.model.sale.*;


import com.yc.education.model.customer.RemarkProperty;
import com.yc.education.model.customer.ReportRemarkProperty;
import com.yc.education.model.sale.SalePurchaseOrderProductProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.RelationService;
import com.yc.education.service.customer.IRemarkService;
import com.yc.education.service.sale.*;

import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 销售--订货单
 */
@Controller
public class PurchaseOrderController extends BaseController implements Initializable {

    private Stage stage = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @Autowired IRemarkService iRemarkService;                           // 备注
    @Autowired IReportRemarkService iReportRemarkService;               // 报表备注
    @Autowired ISalePurchaseOrderService iSalePurchaseOrderService;     //订货单
    @Autowired ISalePurchaseOrderProductService iSalePurchaseOrderProductService;    //采购订单产品详情
    @Autowired ISaleOfferProductService iSaleOfferProductService;                    // 报价单产品详情
    @Autowired ISaleOnlineOrderProductService iSaleOnlineOrderProductService;        // 网上订单产品详情
    @Autowired DataSettingService iDataSettingService;                           // 基本资料
    @Autowired RelationService iRelationService;                           // 基本资料

    /**
     * @Description 单据关联容器
     * @Author BlueSky
     * @Date 16:16 2019/4/26
     **/
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;

    // 菜单控件
    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页
    @FXML VBox menu_clearAll; //清除
    @FXML VBox menu_commit;  //提交
    @FXML VBox menu_insert; //新增
    @FXML VBox menu_update; //修改
    @FXML VBox menu_delete; //删除
    @FXML VBox menu_printing; //打印
    @FXML VBox shiro_success;
    @FXML VBox shiro_cancel;
    @FXML VBox import_out;
    @FXML VBox import_in;
    @FXML Label writestate;// 待输入


    @FXML Button more_customer;   // 按钮 - 更多客户
    @FXML DatePicker order_date;  // 制单日期
    @FXML public TextField customer_no; // 客户编号
    @FXML TextField order_no; // 订货单号
    @FXML public TextField customer_no_str; // 客户编号描述
    @FXML TextField customer_order_no; // 客户订单号
    @FXML ComboBox tax; // 税别
    @FXML ComboBox currency; // 币别
    @FXML TextField discount; // 折扣
    @FXML public TextField shipping_warehouse; // 出货仓库
    // 作废按钮
    @FXML Button btn_invalid;
    // 特价单复选框
    @FXML CheckBox ch_special;
    @FXML public TextField shipping_warehouse_str; //出货仓库描述

    @FXML public ComboBox customer_category; //客户类别
    @FXML public TextField customer_name; //客户名称
    @FXML public TextField receivable_balance; //应收余额
    @FXML TextField made_people; //制单人
    @FXML public ComboBox payment; // 结算方式
    @FXML TextField reviewer; // 审核人
    @FXML TextField reviewer_str; // 审核人描述
    @FXML TextField last_udpate; //最后修改人
    @FXML TextField last_udpate_str; //最后修改人描述
    @FXML public ComboBox contact; // 联系人
    @FXML public ComboBox phone; // 联系电话
    @FXML public TextField invoice_title; //发票抬头
    @FXML public TextField invoice_address; //发票地址
    @FXML public ComboBox shipping_address; //送货地址
    @FXML ComboBox business_leader; //负责业务
    @FXML public ComboBox fax; // 传真
    @FXML public TextField zip; //邮政编码
    @FXML TextField business_leader_str; //负责业务描述
    @FXML Button btn_copy; // 复制 按钮



    @FXML public TableView product_table; // 订货产品 表格
    @FXML TableColumn col_id; // 列id
    @FXML TableColumn col_no; // 列 序号
    @FXML TableColumn col_product_no; //列 产品编号
    @FXML TableColumn col_product_name; //列 产品名称
    @FXML TableColumn col_category; //列 品类
    @FXML TableColumn col_num; //列 数量
    @FXML TableColumn col_unit; // 列 单位
    @FXML TableColumn col_pricing; // 列 定价
    @FXML TableColumn col_discount; //列 折扣
    @FXML TableColumn col_price; //列 单价
    @FXML TableColumn col_money; //列 金额
    @FXML TableColumn depotbutton; //列 库位按钮
    @FXML TableColumn col_warehouse_position; //列 库位
    @FXML TableColumn col_floor; //列 楼层
    @FXML TableColumn col_order_no; //列 订货单号
    @FXML TableColumn col_product_source; //列 产品来源
    @FXML TableColumn col_remark; //列 备注


    @FXML TableView remark_table; //备注及说明--备注表格
    @FXML TableColumn col_remark_id; //列id
    @FXML TableColumn col_remark_content; //列 内容

    @FXML TableView report_table; //备注及说明--报表
    @FXML TableColumn col_report_id; // 列 id
    @FXML TableColumn col_report_content; //列 内容


    // 合计
    @FXML public TextField total_num; // 合计数量
    @FXML public TextField total_money; //合计金额
    @FXML public TextField total_loan; //合计贷款
    @FXML public TextField tax_total; //税款合计


    static long changeId = 0L;      // 记录当前编辑计算金额cell 的id值

    /***************************************** 弹出窗口-产品 ********************************************/
    int tablePosition = 0; // 弹出窗口选中的行数

    /***************************************** 弹出窗口-产品-结束 ********************************************/

    // 日期格式
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    LocalDateTime localDate = LocalDateTime.now();
    // 当前页
    private static int page = 0;
    // 页大小
    private final static int rows = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        relation = new Relation();
        relationLock = false;

        tax.getItems().addAll("外加","内含","零税","免税");
        tax.getSelectionModel().select(0);
        setComboBox(7L,currency,0); //币别
        loadSettlementMethod(payment);//结算方式
        setComboBox(10L,customer_category,0); //客户类别

        loadEmployee(business_leader, 0);




        // 报价产品行双击 调出现有产品窗口
        BaseController.clickEvent(product_table, data ->{
            tablePosition = product_table.getSelectionModel().getSelectedIndex();
            moreProductButtonClick();
        }, 2);

        SaleQuotation saleQuotation = StageManager.saleQuotation;       // 报价单导入
        SaleOnlineOrder onlineOrder = StageManager.saleOnlineOrder;     // 网上订单导入
        StageManager.clear();

        if(saleQuotation != null && onlineOrder != null){
            setMenuValue(1);
        }else if(saleQuotation != null){
            setBasicValImportFromQuotation(saleQuotation);     // 从报价单导出过来
            relationLock = true;
        }else if(onlineOrder != null){
            setBasicValImportFromOnline(onlineOrder); // 从网上单导出过来
            relationLock = true;
        }else{
            setMenuValue(1);
        }

        // 折扣修改监听
        discount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String discountText = discount.getText();
                if(discountText != null && discountText.length() > 0 && isNumeric(discountText)){
                    if(Integer.valueOf(discountText) >= 0 && Integer.valueOf(discountText) <= 100){

                        List<SalePurchaseOrderProductProperty> list = product_table.getItems();
                        if(list != null){
                            list.forEach(p->{
                                if(p.getNum() != null && !"".equals(p.getNum())){
                                    p.setDiscount(discountText);
                                    p.setPrice(new BigDecimal(p.getPricing()).multiply(new BigDecimal(discountText)).divide(new BigDecimal(100.0),2,BigDecimal.ROUND_UP).toString());
                                    p.setMoney(new BigDecimal(p.getPricing()).multiply(new BigDecimal(discountText)).multiply(new BigDecimal(p.getNum())).divide(new BigDecimal(100.0),2,BigDecimal.ROUND_UP).toString());
                                }
                            });
                        }
                    }else{
//                        alert_informationDialog("请输入0 ~ 100 数字");
                    }
                }else{
                    alert_informationDialog("折扣只能输入数字");
                }
            }
        });

        business_leader.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = business_leader.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    business_leader_str.setText(bus);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * @Description 加载网上订单导入的数据
     * @Author BlueSky
     * @Date 19:48 2019/4/16
     **/
    public void setBasicValImportFromOnline(SaleOnlineOrder order){
        //存储被关联单据
        relation.setBeRelationName("网上订单");
        relation.setBeRelationId(order.getId());
        relationLock = true;
        add();
        //添加转单数据
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getCustomerNoStr() != null){
            customer_name.setText(order.getCustomerNoStr());
        }
        lastUpdatePeopel(last_udpate, last_udpate_str);
        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getPhone() != null){
            phone.getSelectionModel().select(order.getPhone());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
        if(order.getDeliveryAddress() != null){
            shipping_address.getSelectionModel().select(order.getDeliveryAddress());
        }
        if(order.getInvoiceAddress() != null){
            invoice_address.setText(order.getInvoiceAddress());
        }
        if(order.getInvoiceTitle() != null){
            invoice_title.setText(order.getInvoiceTitle());
        }
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        setControllerUse();

        // 加载导入单产品列表
        Long orderid = order.getId();
        List<SaleOnlineOrderProduct> list = iSaleOnlineOrderProductService.listByOnlineOrderAll(order.getId().toString());

        List<SalePurchaseOrderProductProperty> purchaseOrderProductPropertyList = new ArrayList<>();

        if(list != null){
            int rows = 1;
            for (SaleOnlineOrderProduct p : list) {
                DepotProperty depotProperty = new DepotProperty();
                ProductBasic productBasic = getProductBasic(p.getProductNo());
                if(productBasic != null){
                    depotProperty = getDepot(productBasic.getInventoryplace());
                }
                purchaseOrderProductPropertyList.add(new SalePurchaseOrderProductProperty(rows++,p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPrice(), "0", p.getPrice(), p.getMoney(), order.getOrderNo(), "网上单导入",depotProperty.getDepotOrder(),depotProperty.getDepotFloor(), p.getRemark()));
            }
        }
        generalProductTab(FXCollections.observableArrayList(purchaseOrderProductPropertyList));
        loadRemark(orderid,"2","1");
    }

    /**
     * @Description 加载报价单导入的数据
     * @Author BlueSky
     * @Date 19:48 2019/4/16
     **/
    public void setBasicValImportFromQuotation(SaleQuotation saleQuotation){
        //存储被关联单据
        relation.setBeRelationName("报价单");
        relation.setBeRelationId(saleQuotation.getId());
        relationLock = true;
        add();
        //添加转单数据
        if(saleQuotation.getCustomerNo() != null){
            customer_no.setText(saleQuotation.getCustomerNo());
        }
        if(saleQuotation.getOfferNo() != null){
            order_no.setText(saleQuotation.getOfferNo());
        }
        if(saleQuotation.getCustomerNoStr() != null){
            customer_no_str.setText(saleQuotation.getCustomerNoStr());
        }
        if(saleQuotation.getTax() != null){
            tax.getSelectionModel().select(saleQuotation.getTax());
        }
        if(saleQuotation.getCurrency() != null){
            currency.getSelectionModel().select(saleQuotation.getCurrency());
        }
        if(saleQuotation.getDiscount() != null){
            discount.setText(saleQuotation.getDiscount());
        }
        if(saleQuotation.getSpecialOffer() != null){
            ch_special.setSelected(saleQuotation.getSpecialOffer());
        }
        if(saleQuotation.getCustomerCategory() != null){
            customer_category.getSelectionModel().select(saleQuotation.getCustomerCategory());
        }
        if(saleQuotation.getCustomerName() != null){
            customer_name.setText(saleQuotation.getCustomerName());
        }
        if(saleQuotation.getAmountReceivable() != null){
            receivable_balance.setText(saleQuotation.getAmountReceivable());
        }
        lastUpdatePeopel(last_udpate, last_udpate_str);
        if(saleQuotation.getContact() != null){
            contact.getSelectionModel().select(saleQuotation.getContact());
        }
        if(saleQuotation.getTelephone() != null){
            phone.getSelectionModel().select(saleQuotation.getTelephone());
        }
        if(saleQuotation.getAddress() != null){
            shipping_address.getSelectionModel().select(saleQuotation.getAddress());
        }
        if(saleQuotation.getBusiness() != null){
            try {
                business_leader.getSelectionModel().select(saleQuotation.getBusiness());
            }catch (Exception e){e.printStackTrace();}
        }
        if(saleQuotation.getFax() != null){
            fax.getSelectionModel().select(saleQuotation.getFax());
        }
        if(saleQuotation.getBusinessStr() != null){
            business_leader_str.setText(saleQuotation.getBusinessStr());
        }
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        setControllerUse();

        // 加载导入单产品列表
        Long orderid = saleQuotation.getId();
        List<SaleOfferProduct> list = iSaleOfferProductService.listSaleOfferProduct(orderid);

        List<SalePurchaseOrderProductProperty> purchaseOrderProductPropertyList = new ArrayList<>();

        if(list != null){
            int rows = 1;
            for (SaleOfferProduct p : list) {
                DepotProperty depotProperty = new DepotProperty();
                ProductBasic productBasic = getProductBasic(p.getProductNo());
                if(productBasic != null){
                    depotProperty = getDepot(productBasic.getInventoryplace());
                }
                purchaseOrderProductPropertyList.add(new SalePurchaseOrderProductProperty(rows++,p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getDiscount(), p.getPrice(), p.getMoney(), saleQuotation.getOfferNo(), "报价单导入",depotProperty.getDepotOrder(),depotProperty.getDepotFloor(), p.getRemark()));
            }
        }
        generalProductTab(FXCollections.observableArrayList(purchaseOrderProductPropertyList));
        loadRemark(orderid,"2","1");
    }

    /**
     * @Description 加载备注及说明内容
     * @Author BlueSky
     * @Date 18:10 2019/4/16
     **/
    public void loadRemark(Long orderid ,String remarkid,String reportid){
        List<RemarkProperty> remarkPropertyList = new ArrayList<>();
        List<ReportRemarkProperty> reportRemarkPropertyList = new ArrayList<>();
        List<Remark> listRemark = iRemarkService.listRemark(orderid, remarkid);
        List<ReportRemark> reportRemarkList = iReportRemarkService.listReportRemark(orderid, reportid);

        if(listRemark != null){
            listRemark.forEach(p->{
                remarkPropertyList.add(new RemarkProperty(p.getRemark()));
            });
        }
        if(reportRemarkList != null){
            reportRemarkList.forEach(p->{
                reportRemarkPropertyList.add(new ReportRemarkProperty(p.getContent()));
            });
        }


        remark_table.setEditable(true);
        col_remark_content.setCellFactory(column -> EditCell.createStringEditCell());
        final ObservableList<RemarkProperty> dataProperty = FXCollections.observableArrayList(remarkPropertyList);
        col_remark_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_remark_content.setCellValueFactory(new PropertyValueFactory("remark"));
        remark_table.setItems(dataProperty);

        report_table.setEditable(true);
        col_report_content.setCellFactory(column -> EditCell.createStringEditCell());
        final ObservableList<ReportRemarkProperty> dataReportProperty = FXCollections.observableArrayList(reportRemarkPropertyList);
        col_report_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_report_content.setCellValueFactory(new PropertyValueFactory("content"));
        report_table.setItems(dataReportProperty);
    }


    /**
     * 订单作废
     */
    @FXML
    public void orderInvalid(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_ABOLISH)) {
            if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
                SalePurchaseOrder salePurchaseOrder = iSalePurchaseOrderService.selectByKey(Long.valueOf(order_no.getUserData().toString()));
                if (salePurchaseOrder != null && salePurchaseOrder.getOrderAudit() == null || !salePurchaseOrder.getOrderAudit()) {
                    btn_invalid.setDisable(true);
                    SalePurchaseOrder order = new SalePurchaseOrder();
                    order.setId(Long.valueOf(order_no.getUserData().toString()));
                    order.setInvalid(true);
                    int rows = iSalePurchaseOrderService.updateNotNull(order);
                    if(rows > 0){
                        import_out.setDisable(true);
                    }
                    returnMsg(rows);
                } else {
                    alert_informationDialog("此单据已经被审核，无法作废！");
                }
            }
        }
    }

    /**
     * 现有产品查询
     */
    public  void moreProductButtonClick(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseOrderControllerProduct", this);
        StageManager.CONTROLLER.put("tablePosition", tablePosition);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * 产品view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfProductTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addProductRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfProduct();
            setTableviewVal();
        }
    }

    /**
     * 备注view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfRemarkTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addRemarkRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfRemark();
            setTableviewRemarkVal();
        }
    }

    /**
     * 报表备注view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfReportRemarkTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addReportRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfReport();
            setTableviewReportVal();
        }
    }

    /**
     * 删除产品行
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(product_table.getSelectionModel().getSelectedCells().size() == 0){return;}
                TablePosition pos = (TablePosition) product_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                SalePurchaseOrderProductProperty property = (SalePurchaseOrderProductProperty)product_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iSalePurchaseOrderProductService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<SalePurchaseOrderProductProperty> dataProperty = product_table.getItems();
                final ObservableList<SalePurchaseOrderProductProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                product_table.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 添加产品行
     */
    public void addProductRow() {

        ObservableList<SalePurchaseOrderProductProperty> list = product_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new SalePurchaseOrderProductProperty(list.size()+1,"", "", "", 0, "", new BigDecimal("0.00"), "100", new BigDecimal("0.00"), new BigDecimal("0.00"), "", "", "","",""));
        generalProductTab(list);
    }

    /**
     * 删除备注行
     */
    private void deleteRowOfRemark(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(remark_table.getSelectionModel().getSelectedCells().size() == 0){return;}
                TablePosition pos = (TablePosition) remark_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                RemarkProperty property = (RemarkProperty)remark_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iRemarkService.delete(property.getId());
                    returnMsg(rows);
                }
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }

    /**
     * 添加备注行
     */
    public void addRemarkRow() {

        ObservableList<RemarkProperty> list = remark_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new RemarkProperty(""));
        remark_table.setItems(list);
    }

    /**
     * 删除报表行
     */
    private void deleteRowOfReport(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(report_table.getSelectionModel().getSelectedCells().size() == 0){return;}
                TablePosition pos = (TablePosition) report_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                ReportRemarkProperty property = (ReportRemarkProperty)report_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iReportRemarkService.delete(property.getId());
                    returnMsg(rows);
                }
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }

    }

    /**
     * 添加报表行
     */
    public void addReportRow() {

        ObservableList<ReportRemarkProperty> list = report_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new ReportRemarkProperty(""));
        report_table.setItems(list);
    }

    /**
     * 新增订单
     */
    @FXML
    public void add(){
        clearControllerVal();
        order_date.setValue(localDate.toLocalDate());
        createPeople(made_people);
        order_no.setText(createOrderNo(iSalePurchaseOrderService.getMaxOrderNo()));
        setControllerUse();
    }

    /**
     * 保存数据
     */
    @FXML
    public synchronized void save(){
        // 检查非空
        if(checkPrimaryNull(order_no,customer_no,customer_no_str,business_leader_str)){
            return;
        }
        // 特价单
        if(!ch_special.isSelected()){
            List<SalePurchaseOrderProductProperty> propertyList =  product_table.getItems();
            if(propertyList != null){
                Double miniDis = getCustomerMinimumDiscountByCode(customer_no.getText());
                for (SalePurchaseOrderProductProperty p : propertyList) {
                    ProductBasic basic = getProductBasic(p.getProductNo());
                    if(basic == null){
                        alert_informationDialog("提交失败！"+p.getProductNo()+" 不存在，请检查单据再次提交！");
                        return;
                    }
                    if(basic.getInventoryplace() == null || "".equals(basic.getInventoryplace())){
                        alert_informationDialog("提交失败！产品"+p.getProductNo()+" 暂无库位规格等信息，请完善后再次提交！");
                    }
                    String result = checkProductPrice(p.getProductNo(),p.getPrice());
                    if(result != null){
                        alert_informationDialog("提交失败！产品："+p.getProductNo()+",超出最低售价："+result);
                        return;
                    }
                    if(miniDis != null){
                        Double discount = Double.valueOf(p.getDiscount());
                        if(miniDis.compareTo(discount) > 0){
                            alert_informationDialog("提交失败！产品："+p.getProductNo()+",超出客户最低折扣，需按特价单处理");
                            return;
                        }
                    }
                }
            }
        }

        lastUpdatePeopel(last_udpate, last_udpate_str);
        SalePurchaseOrder order = new SalePurchaseOrder();
        if(order_date.getValue() != null){
            try {
                Date date = df.parse(order_date.getValue().toString());
                order.setCreateDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            order.setCustomerNo(customer_no.getText());
        }
        if(order_no.getText() != null && !"".equals(order_no.getText())){
            order.setOrderNo(order_no.getText());
        }
        if(customer_no_str.getText() != null && !"".equals(customer_no_str.getText())){
            order.setCustomerNoStr(customer_no_str.getText());
        }
        if(customer_order_no.getText() != null && !"".equals(customer_order_no.getText())){
            order.setCustomerOrderNo(customer_order_no.getText());
        }
        if(discount.getText() != null && !"".equals(discount.getText())){
            order.setDiscount(discount.getText());
        }
        if(shipping_warehouse_str.getText() != null && !"".equals(shipping_warehouse_str.getText())){
            order.setWarehouseOutStr(shipping_warehouse_str.getText());
        }
        if(customer_category.getSelectionModel().getSelectedItem() != null ){
            order.setCustomerCategory(customer_category.getSelectionModel().getSelectedItem().toString());
        }
        if(customer_name.getText() != null && !"".equals(customer_name.getText())){
            order.setCustomerName(customer_name.getText());
        }
        if(receivable_balance.getText() != null && !"".equals(receivable_balance.getText())){
            order.setReceivableBalance(new BigDecimal(receivable_balance.getText()));
        }
        if(tax.getSelectionModel().getSelectedItem() != null && !"".equals(tax.getSelectionModel().getSelectedItem())){
            order.setTax(tax.getSelectionModel().getSelectedItem().toString());
        }
        if(currency.getSelectionModel().getSelectedItem() != null && !"".equals(currency.getSelectionModel().getSelectedItem())){
            order.setCurrency(currency.getSelectionModel().getSelectedItem().toString());
        }
        if(shipping_warehouse.getText() != null && !"".equals(shipping_warehouse.getText()) ){
            order.setWarehouseOut(shipping_warehouse.getText());
        }

        if(made_people.getText() != null && !"".equals(made_people.getText())){
            order.setMadePeople(made_people.getText());
        }
        if(reviewer.getText() != null && !"".equals(reviewer.getText())){
            order.setAuditPeople(reviewer.getText());
        }
        if(reviewer_str.getText() != null && !"".equals(reviewer_str.getText())){
            order.setAuditPeopleStr(reviewer_str.getText());
        }
        if(last_udpate.getText() != null && !"".equals(last_udpate.getText())){
            order.setLastUpdate(last_udpate.getText());
        }
        if(last_udpate_str.getText() != null && !"".equals(last_udpate_str.getText())){
            order.setLastUpdateStr(last_udpate_str.getText());
        }
        if(payment.getSelectionModel().getSelectedItem() != null && !"".equals(payment.getSelectionModel().getSelectedItem())){
            order.setPaymentMethod(payment.getSelectionModel().getSelectedItem().toString());
        }
        if(contact.getSelectionModel().getSelectedItem() != null && !"".equals(contact.getSelectionModel().getSelectedItem())){
            order.setContact(contact.getSelectionModel().getSelectedItem().toString());
        }
        if(phone.getSelectionModel().getSelectedItem() != null && !"".equals(phone.getSelectionModel().getSelectedItem())){
            order.setPhone(phone.getSelectionModel().getSelectedItem().toString());
        }
        if(shipping_address.getSelectionModel().getSelectedItem() != null && !"".equals(shipping_address.getSelectionModel().getSelectedItem())){
            order.setShippingAddress(shipping_address.getSelectionModel().getSelectedItem().toString());
        }
        if(business_leader.getSelectionModel().getSelectedItem() != null){
            order.setBusinessLeader(business_leader.getSelectionModel().getSelectedItem().toString());
        }
        if(business_leader_str.getText() != null && !"".equals(business_leader_str.getText())){
            order.setBusinessLeaderStr(business_leader_str.getText());
        }
        if(fax.getSelectionModel().getSelectedItem() != null && !"".equals(fax.getSelectionModel().getSelectedItem())){
            order.setFax(fax.getValue().toString());
        }
        if(zip.getText() != null && !"".equals(zip.getText())){
            order.setZip(zip.getText());
        }
        if(invoice_title.getText() != null  && !"".equals(invoice_title.getText()) ){
            order.setInvoiceTitle(invoice_title.getText());
        }
        if(invoice_address.getText() != null && !"".equals(invoice_address.getText())){
            order.setInvoiceAddress(invoice_address.getText());
        }
        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            order.setId(Long.valueOf(order_no.getUserData().toString()));
        }

        order.setSpecialOrder(ch_special.selectedProperty().getValue());

        if(order.getId() != null){
            // 修改
            int rows = iSalePurchaseOrderService.updateNotNull(order);
            returnMsg(rows);
            setControllerDisable();
        }else{
            order.setInvalid(false);
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iSalePurchaseOrderService.save(order);
            order_no.setUserData(order.getId().toString());
            returnMsg(rows);
            setControllerDisable();

            if(relationLock){
                //添加关联关系
                relation.setRelationName("订货单");
                relation.setRelationId(order.getId());
                iRelationService.save(relation);
            }

        }
        // 操作tableview中的数据
        saveTableviewProduct();
        saveTableviewRemark();
        saveTableviewReport();
        // 重新加载表格数据
        setTableviewVal();
        setTableviewRemarkVal();
        setTableviewReportVal();
        setBasicVal(order);

    }

    /**
     * 保存订货产品tableview数据
     */
    private void saveTableviewProduct(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id) && product_table.getItems() != null){
            int tableSize = product_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                SalePurchaseOrderProductProperty property = null;
                if(product_table.getItems().get(i) != null){
                    property = (SalePurchaseOrderProductProperty)product_table.getItems().get(i);
                }
                SalePurchaseOrderProduct product = new SalePurchaseOrderProduct();

                if(property.getProductNo() != null && !"".equals(property.getProductNo())){
                    product.setProductNo(property.getProductNo());
                }
                if(property.getProductName() != null && !"".equals(property.getProductName())){
                    product.setProductName(property.getProductName());
                }
                if(property.getCategory() != null && !"".equals(property.getCategory())){
                    product.setCategory(property.getCategory());
                }
                if(property.getNum() != null && !"".equals(property.getNum())){
                    product.setNum(Integer.valueOf(property.getNum()));
                }
                if(property.getUnit() != null && !"".equals(property.getUnit())){
                    product.setUnit(property.getUnit());
                }
                if(property.getPricing() != null && !"".equals(property.getPricing())){
                    product.setPricing(new BigDecimal(property.getPricing()));
                }
                if(property.getDiscount() != null && !"".equals(property.getDiscount())){
                    product.setDiscount(property.getDiscount());
                }
                if(property.getPrice() != null && !"".equals(property.getPrice())){
                    product.setPrice(new BigDecimal(property.getPrice()));
                }
                if(property.getMoney() != null && !"".equals(property.getMoney())){
                    product.setMoney(new BigDecimal(property.getMoney()));
                }
                if(property.getWarehousePosition() != null && !"".equals(property.getWarehousePosition())){
                    product.setWarehousePosition(property.getWarehousePosition());
                }
                if(property.getFloor() != null && !"".equals(property.getFloor())){
                    product.setFloor(property.getFloor());
                }
                if(property.getProductSource() != null && !"".equals(property.getProductSource())){
                    product.setProductSource(property.getProductSource());
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                product.setPurchaseOrderId(Long.valueOf(id));

                if(property.getId() == null || property.getId() == 0){
                    try {
                        product.setSaleNum(0);
                        product.setAddtime(new Date());
                        iSalePurchaseOrderProductService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iSalePurchaseOrderProductService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存 备注tableview数据
     */
    private void saveTableviewRemark(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id) && remark_table.getItems() != null){
            int tableSize = remark_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                RemarkProperty property = null;
                if(remark_table.getItems().get(i) != null){
                    property = (RemarkProperty)remark_table.getItems().get(i);
                }
                Remark product = new Remark();
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                product.setTypeid(3);
                product.setOtherid(Long.valueOf(id));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iRemarkService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iRemarkService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存 报表tableview数据
     */
    private void saveTableviewReport(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id) && report_table.getItems() != null){
            int tableSize = report_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                ReportRemarkProperty property = null;
                if(report_table.getItems().get(i) != null){
                    property = (ReportRemarkProperty)report_table.getItems().get(i);
                }
                ReportRemark product = new ReportRemark();
                if(property.getContent() != null && !"".equals(property.getContent())){
                    product.setContent(property.getContent());
                }
                product.setTypeid(2);
                product.setOtherid(Long.valueOf(id));

                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iReportRemarkService.save(product);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iReportRemarkService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 删除数据
     */
    @FXML
    public synchronized void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
                int rows = iSalePurchaseOrderService.delete(Long.valueOf(order_no.getUserData().toString()));
                iSalePurchaseOrderProductService.deleteSalePurchaseOrderProductByParentId(order_no.getUserData().toString());
                // 删除关联单据
                iRelationService.deleteRelation("订货单",Long.valueOf(order_no.getUserData().toString()));
                returnMsg(rows);
                setMenuValue(1);
            }
        }

    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        //权限管理
        matchingPermissions("订货单",menu_insert,menu_delete,menu_update,shiro_success,shiro_cancel,menu_printing,menu_clearAll);
        List<SalePurchaseOrder> purchaseOrdersList = iSalePurchaseOrderService.listSalePurchaseOrderByPage("","",page, 1);
        if(purchaseOrdersList != null && purchaseOrdersList.size() >0){
            PageInfo<SalePurchaseOrder> pageInfo = new PageInfo<>(purchaseOrdersList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            setBasicVal(purchaseOrdersList.get(0));
            setControllerDisable();
        }else{
            clearControllerVal();
            setControllerDisable();
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
     * 设置值
     * @param order
     */
    public void setBasicVal(SalePurchaseOrder order){
        clearControllerVal();
        if(order == null){
            return;
        }
        if(order.getId() != null){
            order_no.setUserData(order.getId().toString());
        }
        if(order.getCreateDate() != null){
            order_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getOrderNo() != null){
            order_no.setText(order.getOrderNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getCustomerOrderNo() != null){
            customer_order_no.setText(order.getCustomerOrderNo());
        }
        if(order.getTax() != null){
            tax.getSelectionModel().select(order.getTax());
        }
        if(order.getCurrency() != null){
            currency.getSelectionModel().select(order.getCurrency());
        }
        if(order.getDiscount() != null){
            discount.setText(order.getDiscount());
        }
        if(order.getWarehouseOut() != null){
            shipping_warehouse.setText(order.getWarehouseOut());
        }
        if(order.getSpecialOrder() != null){
            ch_special.setSelected(order.getSpecialOrder());
        }
        if(order.getWarehouseOutStr() != null){
            shipping_warehouse_str.setText(order.getWarehouseOutStr());
        }
        if(order.getCustomerCategory() != null){
            customer_category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        if(order.getReceivableBalance() != null){
            receivable_balance.setText(order.getReceivableBalance().toString());
        }
        if(order.getMadePeople() != null){
            made_people.setText(order.getMadePeople());
        }
        if(order.getPaymentMethod() != null){
            payment.getSelectionModel().select(order.getPaymentMethod());
        }
        if(order.getAuditPeople() != null){
            reviewer.setText(order.getAuditPeople());
        }
        if(order.getAuditPeopleStr() != null){
            reviewer_str.setText(order.getAuditPeopleStr());
        }
        if(order.getLastUpdate() != null){
            last_udpate.setText(order.getLastUpdate());
        }
        if(order.getLastUpdateStr() != null){
            last_udpate_str.setText(order.getLastUpdateStr());
        }
        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getPhone() != null){
            phone.getSelectionModel().select(order.getPhone());
        }
        if(order.getInvoiceAddress() != null){
            invoice_address.setText(order.getInvoiceAddress());
        }
        if(order.getShippingAddress() != null){
            shipping_address.getSelectionModel().select(order.getShippingAddress());
        }
        if(order.getInvoiceTitle() != null){
            invoice_title.setText(order.getInvoiceTitle());
        }
        if(order.getBusinessLeader() != null){
            business_leader.getSelectionModel().select(order.getBusinessLeader());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
        if(order.getZip() != null){
            zip.setText(order.getZip());
        }
        if(order.getBusinessLeaderStr() != null){
            business_leader_str.setText(order.getBusinessLeaderStr());
        }
        // 审核
        shiro_success.setDisable(false);
        shiro_cancel.setDisable(false);
        menu_update.setDisable(false);
        if(order.getOrderAudit() != null){
            if(order.getOrderAudit()){
                shiro_success.setDisable(true);
                menu_update.setDisable(true);
            }else{
                shiro_cancel.setDisable(true);
                menu_update.setDisable(false);
            }
        }else{
            setShiroControlNO();
            menu_update.setDisable(false);
        }
        // 单据作废
        if(order.getInvalid() != null){
            if(order.getInvalid()){
                btn_invalid.setDisable(true);
                import_out.setDisable(true);
            }else{
                btn_invalid.setDisable(false);
                import_out.setDisable(false);
            }
        }else{
            btn_invalid.setDisable(false);
            import_out.setDisable(false);
        }
        setTableviewVal();
        setTableviewRemarkVal();
        setTableviewReportVal();
    }

    /**
     * 报价单转单赋值
     * @param order
     */
    public void setBasicImportVal(SaleQuotation order){
        if(order == null){
            return;
        }
        clearControllerVal();
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        if(order.getOfferNo() != null){
            customer_order_no.setText(order.getOfferNo());
        }
        if(order.getCreateDate() != null){
            order_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        order_no.setText(createOrderNo(iSalePurchaseOrderService.getMaxOrderNo()));
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getTax() != null){
            tax.getSelectionModel().select(order.getTax());
        }
        if(order.getCurrency() != null){
            currency.getSelectionModel().select(order.getCurrency());
        }
        if(order.getDiscount() != null){
            discount.setText(order.getDiscount());
        }
        if(order.getSpecialOffer() != null){
            ch_special.setSelected(order.getSpecialOffer());
        }
        if(order.getCustomerCategory() != null){
            customer_category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        if(order.getAmountReceivable() != null){
            receivable_balance.setText(order.getAmountReceivable());
        }
        if(order.getSinglePeople() != null){
            made_people.setText(order.getSinglePeople());
        }
        lastUpdatePeopel(last_udpate, last_udpate_str);

        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getTelephone() != null){
            phone.getSelectionModel().select(order.getTelephone());
        }
        if(order.getAddress() != null){
            shipping_address.getSelectionModel().select(order.getAddress());
        }

        if(order.getBusiness() != null){
            business_leader.getSelectionModel().select(order.getBusiness());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
        if(order.getBusinessStr() != null){
            business_leader_str.setText(order.getBusinessStr());
        }
    }

    /**
     * 加载tableview数据
     */
    private void setTableviewVal(){
        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            List<SalePurchaseOrderProduct> productList = iSalePurchaseOrderProductService.listPurchaseOrderProduct(order_no.getUserData().toString());
            List<SalePurchaseOrderProductProperty> productPropertyList = new ArrayList<>();
            clearTotalCalcVal();
            int rows = 1; // 序号
            for (SalePurchaseOrderProduct p : productList) {
                totalCost(p.getNum()==null?0:p.getNum(), p.getMoney()==null?new BigDecimal("0.00"):p.getMoney(), tax.getItems()==null?0:tax.getSelectionModel().getSelectedIndex()+1, total_num, tax_total, total_loan, total_money);
                productPropertyList.add(new SalePurchaseOrderProductProperty(p.getId(),rows++,p.getPurchaseOrderId(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(),p.getDiscount(), p.getPrice(), p.getMoney(), p.getOrderNo() , p.getProductSource() ,p.getWarehousePosition(),p.getFloor(),p.getRemark()));

            }
            generalProductTab(FXCollections.observableArrayList(productPropertyList));
        }
    }

    /**
     * 给产品tableview加载数据
     * @param productPropertyList
     */
    public void generalProductTab(ObservableList<SalePurchaseOrderProductProperty> productPropertyList){
        product_table.setEditable(true);

        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_category.setCellFactory(column -> EditCell.createStringEditCell());
        col_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
//        col_pricing.setCellFactory(column -> EditCell.createStringEditCell());
        col_discount.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_source.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());
        col_warehouse_position.setCellFactory(column -> EditCell.createStringEditCell());
        col_floor.setCellFactory(column -> EditCell.createStringEditCell());
        Callback<TableColumn<SalePurchaseOrderProductProperty, String>, TableCell<SalePurchaseOrderProductProperty, String>> buttonFactory
                = new Callback<TableColumn<SalePurchaseOrderProductProperty, String>, TableCell<SalePurchaseOrderProductProperty, String>>() {
            @Override
            public TableCell call(final TableColumn<SalePurchaseOrderProductProperty, String> param) {
                final TableCell<SalePurchaseOrderProductProperty, String> cell = new TableCell<SalePurchaseOrderProductProperty, String>() {

                    final Button btn1 = new Button("...");

                    @Override
                    public void updateItem(String ite, boolean empty) {
                        super.updateItem(ite, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction((ActionEvent t)
                                    -> {
                                int selectIndex = getTableRow().getIndex();
                                //现有库位查询
                                openMoreWarehouseQuery(selectIndex);
                            });
                            btn1.setMaxWidth(20);
                            btn1.setTextFill(Color.BLACK);
                            setGraphic(btn1);
                            setText(null);
                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };
        depotbutton.setCellFactory(buttonFactory);

        ObservableList<SalePurchaseOrderProductProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);

        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_category.setCellValueFactory(new PropertyValueFactory("category"));
        col_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_pricing.setCellValueFactory(new PropertyValueFactory("pricing"));
        col_discount.setCellValueFactory(new PropertyValueFactory("discount"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_product_source.setCellValueFactory(new PropertyValueFactory("productSource"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));
        col_warehouse_position.setCellValueFactory(new PropertyValueFactory("warehousePosition"));
        col_floor.setCellValueFactory(new PropertyValueFactory("floor"));

        product_table.setItems(dataProperty);

        product_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SalePurchaseOrderProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends SalePurchaseOrderProductProperty> observableValue, SalePurchaseOrderProductProperty oldItem, SalePurchaseOrderProductProperty newItem) {
                try {
                    if(newItem.getNo() != null && !"".equals(newItem.getNo())){
                        changeId = Integer.valueOf(newItem.getNo());
                    }else{
                        changeId = 0;
                    }
                }catch (Exception e){
                    changeId = 0;
                }
            }
        });


        //提交折扣计算金额  询价订单--询价产品 折扣
        col_discount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                Double  dis = 1.0;
                if(newValue != null && !"".equals(newValue)){
                    dis=Integer.parseInt(newValue)/100.0;
                }
                List<SalePurchaseOrderProductProperty> data = product_table.getItems();
                for (SalePurchaseOrderProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        //计算金额
                        BigDecimal price = new BigDecimal(property.getPricing()).multiply(new BigDecimal(dis.toString())).setScale(2,BigDecimal.ROUND_UP);
                        BigDecimal money = new BigDecimal(property.getNum()).multiply(price).setScale(2, BigDecimal.ROUND_UP);
                        property.setPrice(price.toString());
                        property.setMoney(money.toString());
                        reloadTotalVal(); //重新计算数量、货款金额等信息
                    }
                }
            }
        });
        //提交数量计算金额  询价订单--询价产品 金额总计
        col_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;
                List<SalePurchaseOrderProductProperty> data = product_table.getItems();
                for (SalePurchaseOrderProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        property.setMoney(String.valueOf(newNum*Double.valueOf(property.getPrice())));
                        reloadTotalVal(); //重新计算数量、货款金额等信息
                    }
                }
            }
        });
        //提交单价计算金额  询价订单--询价产品 金额总计
        col_price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                Double  newPrice = newValue != null && !"".equals(newValue)?newPrice=Double.parseDouble(newValue):0.00;
                List<SalePurchaseOrderProductProperty> data = product_table.getItems();
                for (SalePurchaseOrderProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setPrice(String.valueOf(newPrice));
                        //计算金额
                        property.setMoney(String.valueOf(Integer.parseInt(property.getNum())*newPrice));
                        property.setDiscount(new BigDecimal(newPrice).multiply(new BigDecimal("100")).divide(new BigDecimal(property.getPricing()),2,BigDecimal.ROUND_UP).toString());
                        reloadTotalVal(); //重新计算数量、货款金额等信息
                    }
                }
            }
        });
    }

    /**
     * @Description 重新计算数量、货款金额等信息
     * @Author BlueSky
     * @Date 9:54 2019/5/7
     **/
    private void reloadTotalVal(){
        clearTotalCalcVal();
        List<SalePurchaseOrderProductProperty> propertyList = product_table.getItems();
        for (SalePurchaseOrderProductProperty p : propertyList) {
            totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),tax.getSelectionModel().getSelectedItem()==null?"外加":tax.getSelectionModel().getSelectedItem().toString(), total_num, tax_total, total_loan, total_money);
        }
    }

    /**
     * 加载备注tableview数据
     */
    private void setTableviewRemarkVal(){
        remark_table.setEditable(true);

        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            List<Remark> productList = iRemarkService.listRemark(Long.valueOf(order_no.getUserData().toString()),"3");
            List<RemarkProperty> productPropertyList = new ArrayList<>();
            productList.forEach(p->{
                productPropertyList.add(new RemarkProperty(p.getId(),p.getRemark()));
            });
            col_remark_content.setCellFactory(column -> EditCell.createStringEditCell());
            final ObservableList<RemarkProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_remark_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_remark_content.setCellValueFactory(new PropertyValueFactory("remark"));

            remark_table.setItems(dataProperty);
        }
    }

    /**
     * 加载报表tableview数据
     */
    private void setTableviewReportVal(){
        report_table.setEditable(true);

        col_report_content.setCellFactory(column -> EditCell.createStringEditCell());

        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            List<ReportRemark> productList = iReportRemarkService.listReportRemark(Long.valueOf(order_no.getUserData().toString()),"2");
            List<ReportRemarkProperty> productPropertyList = new ArrayList<>();
            productList.forEach(p->{
                productPropertyList.add(new ReportRemarkProperty(p.getId(),p.getContent()));
            });
            final ObservableList<ReportRemarkProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_report_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_report_content.setCellValueFactory(new PropertyValueFactory("content"));

            report_table.setItems(dataProperty);
        }
    }
    /**
     * 清除空间上的值
     */
    @FXML
    private void clearControllerVal(){

        LocalDateTime localDate = LocalDateTime.now();

        order_date.setValue(localDate.toLocalDate());
        order_date.setValue(localDate.toLocalDate());
        customer_no.clear();
        order_no.clear();
        order_no.setUserData(null);
        customer_no_str.clear();
        customer_order_no.clear();
        tax.getSelectionModel().selectFirst();
        currency.getSelectionModel().selectFirst();
        discount.setText("100");
        shipping_warehouse.clear();

        // 特价单复选框
        ch_special.setSelected(false);
        shipping_warehouse_str.clear();

        customer_category.getSelectionModel().selectFirst();
        customer_name.clear();
        receivable_balance.clear();
        made_people.clear();
        payment.getSelectionModel().selectFirst();
        reviewer.clear();
        reviewer_str.clear();
        last_udpate.clear();
        last_udpate_str.clear();
        contact.getItems().clear();
        contact.getSelectionModel().select("");
        phone.getItems().clear();
        phone.getSelectionModel().select("");
        invoice_address.clear();
        shipping_address.getItems().clear();
        shipping_address.getSelectionModel().select("");
        invoice_title.clear();
        business_leader.getSelectionModel().selectFirst();
        fax.getItems().clear();
        fax.getSelectionModel().select("");
        zip.clear();
        business_leader_str.clear();

        clearTotalCalcVal();

        product_table.setItems(null);
        remark_table.setItems(null);
        report_table.setItems(null);
    }

    /**
     * 清空合计框中的值
     */
    protected void clearTotalCalcVal(){
        total_num.setText("0");
        total_loan.setText("0.00");
        total_money.setText("0.00");
        tax_total.setText("0.00");
    }

    /**
     * 设置所有控件禁用
     */
    public void setControllerDisable(){
        setControllerState(true);

    }

    /**
     * 设置所有控件可用
     */
    @FXML
    public void setControllerUse(){
        setControllerState(false);
        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            SalePurchaseOrder order = iSalePurchaseOrderService.selectByKey(Long.valueOf(order_no.getUserData().toString()));
            // 作废
            if(order.getInvalid() != null){
                if(order.getInvalid()){
                    btn_invalid.setDisable(true);
                }else{
                    btn_invalid.setDisable(false);
                }
            }else{
                btn_invalid.setDisable(false);
            }
        }
    }

    /**
     *  设置控件状态（可用、不可用）
     * @param bool
     */
    private void setControllerState(boolean bool){
        if(bool){
            NumberUtil.changeStatus(writestate,0);
        }else{
            NumberUtil.changeStatus(writestate,2);
        }

        more_customer.setDisable(bool);
        order_date.setDisable(bool);
         menu_clearAll.setDisable(bool);
         menu_commit.setDisable(bool);
//         menu_insert.setDisable(bool);
         menu_delete.setDisable(bool);


         order_date.setDisable(bool);
         customer_no.setDisable(true);
         order_no.setDisable(true);
         customer_no_str.setDisable(bool);
         customer_order_no.setDisable(bool);
         tax.setDisable(bool);
         currency.setDisable(bool);
         discount.setDisable(bool);
         shipping_warehouse.setDisable(true);
        // 作废按钮
         btn_invalid.setDisable(bool);
        // 特价单复选框
         ch_special.setDisable(bool);
         shipping_warehouse_str.setDisable(bool);

         customer_category.setDisable(bool);
         customer_name.setDisable(bool);
         receivable_balance.setDisable(true);
         made_people.setDisable(true);
         payment.setDisable(bool);
         reviewer.setDisable(true);
         reviewer_str.setDisable(true);
         last_udpate.setDisable(true);
         last_udpate_str.setDisable(true);
         contact.setDisable(bool);
         phone.setDisable(bool);
         invoice_address.setDisable(bool);
         shipping_address.setDisable(bool);
         invoice_title.setDisable(bool);
         business_leader.setDisable(bool);
         fax.setDisable(bool);
         zip.setDisable(bool);
         business_leader_str.setDisable(bool);
         btn_copy.setDisable(bool);
         btn_invalid.setDisable(true);

         product_table.setDisable(bool);
         remark_table.setDisable(bool);
         report_table.setDisable(bool);

        btn_invalid.setDisable(bool);

        // 合计
         total_num.setDisable(bool);
         total_money.setDisable(bool);
         total_loan.setDisable(bool);
         tax_total.setDisable(bool);
    }

    /**
     * 审核通过
     */
    private void setShiroControlYES(){
        // 有效单据验证
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id == null || "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }
        shiro_cancel.setDisable(false);
        shiro_success.setDisable(true);
        import_out.setDisable(false);

        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        // 有效单据验证
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id == null || "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        import_out.setDisable(true);
        menu_update.setDisable(false);
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SalePurchaseOrder order = iSalePurchaseOrderService.selectByKey(Long.valueOf(id));
            if(order != null && order.getInvalid() != null && !order.getInvalid() || order.getInvalid() == null){
                setShiroControlYES();
                lastUpdatePeopel(reviewer, reviewer_str);
                lastUpdatePeopel(last_udpate, last_udpate_str);
                shiroUpdateData(true);
            }else{
                alert_informationDialog("此单据已经被作废，无法审核！");
            }
        }

    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SalePurchaseOrder saleGoods = iSalePurchaseOrderService.selectByKey(Long.valueOf(id));
            saleGoods.setOrderAudit(bool);
            saleGoods.setLastUpdate(last_udpate.getText());
            saleGoods.setLastUpdateStr(last_udpate_str.getText());
            saleGoods.setAuditPeople(reviewer.getText());
            saleGoods.setAuditPeopleStr(reviewer_str.getText());
            iSalePurchaseOrderService.updateNotNull(saleGoods);
        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel(){

        if(!iRelationService.isRelation("订货单",Long.valueOf(order_no.getUserData().toString()))){
            setShiroControlNO();
            lastUpdatePeopel(reviewer, reviewer_str);
            lastUpdatePeopel(last_udpate, last_udpate_str);
            shiroUpdateData(false);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }
    }

    /**
     * 导出 到订货单
     */
    @FXML
    public void importOut(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SalePurchaseOrder salePurchaseOrder = iSalePurchaseOrderService.selectByKey(Long.valueOf(id));
            try {

                if(salePurchaseOrder != null && salePurchaseOrder.getOrderAudit() && !salePurchaseOrder.getInvalid()){
                    changeHomeBtn(1,0,4);
                    StageManager.salePurchaseOrder = salePurchaseOrder;
                    Pane pane1 = StageManager.ORDERCONTENT.get("SalePane");
                    pane1.getChildren().setAll(loader.load("/fxml/sale/sale_slip.fxml"));
                }else{
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出!");
                }
            }catch (Exception e){
                alert_informationDialog("导出失败,单据数据异常!");
            }
        }

    }

    /**
     * 导入
     */
    @FXML
    public void importIn(){
        stage.setTitle("导入-报价单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("PurchaseOrderControllerImport", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/quotation_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开订单查询窗口
     */
    @FXML
    public void OpenMiniQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();

        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseOrderControllerNo", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/purchase_order_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO  打开窗口--现有客户查询
     * @Date 20:22 2018/8/15
     * @Param []
     **/
    @FXML
    public void OpenCurrentClientQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();

        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("PurchaseOrderController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @Description 现有仓库查询
     * @Author BlueSky
     * @Date 10:41 2019/4/24
     **/
    @FXML
    public void openWarehouseQuery(){
        stage.setTitle("现有仓库查询");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("PurchaseOrderControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/warehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @Description 打开窗口 -- 现有库位查询
     * @Author BlueSky
     * @Date 19:58 2019/4/29
     **/
    @FXML
    public void openMoreWarehouseQuery(int index){
        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("index", index);
        String productNum = "";
        if(product_table.getItems() != null){
            productNum = ((SalePurchaseOrderProductProperty)product_table.getItems().get(index)).getProductNo();
        }
        StageManager.CONTROLLER.put("productNum", productNum);
        StageManager.CONTROLLER.put("PurchaseOrderControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/storehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
