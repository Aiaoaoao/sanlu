package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.customer.*;
import com.yc.education.model.sale.*;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.customer.ICustomerBasicService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.customer.IRemarkService;
import com.yc.education.service.sale.*;
import com.yc.education.service.stock.PurchaseStockProductService;
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
 * 销售-销货单
 */
@Controller
public class SaleGoodsController extends BaseController implements Initializable {

    private static SpringFxmlLoader loader = new SpringFxmlLoader();
    Stage stage = new Stage();

    @Autowired ICustomerService iCustomerService; //客户基本信息
    @Autowired ICustomerBasicService iCustomerBasicService; //客户基本信息
    @Autowired IRemarkService iRemarkService;
    @Autowired IReportRemarkService iReportRemarkService;
    @Autowired ISaleGoodsService iSaleGoodsService;                 //销货单
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;   //销货单产品详情
    @Autowired ProductBasicService productBasicService; //产品 Service
    @Autowired ProductStockService iProductStockService; //库存 Service
    @Autowired PurchaseStockProductService purchaseStockProductService; //采购入库单
    @Autowired ISalePurchaseOrderProductService iSalePurchaseOrderProductService;  // 采购单
    @Autowired ISaleOfferProductService iSaleOfferProductService;  // 报价单产品详情
    @Autowired ISaleOnlineOrderProductService iSaleOnlineOrderProductService;  // 网上订单产品详情
    @Autowired RelationService iRelationService;         // 关联单据

    /**
     * @Description 单据关联容器
     * @Author BlueSky
     * @Date 16:10 2019/4/26
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
    @FXML VBox menu_printing; //删除
    @FXML VBox shiro_success;
    @FXML VBox shiro_cancel;
    @FXML VBox import_out;
    @FXML VBox import_in;
    @FXML Label writestate;// 待输入


    @FXML Button more_customer;   // 按钮 - 更多客户
    @FXML DatePicker create_date;  // 制单日期
    @FXML public TextField customer_no; // 客户编号
    @FXML public TextField customer_no_str; // 客户编号描述
    @FXML TextField customer_order_no; // 客户订单号
    @FXML ComboBox tax; // 税别
    @FXML ComboBox currency; // 币别
    @FXML public TextField discount; // 折扣
    @FXML TextField sale_no; // 销售单号


    @FXML Button btn_invalid; // 作废按钮
    @FXML CheckBox che_special_price;// 特价单复选框
    @FXML CheckBox che_special; // 特批单


    @FXML ComboBox transport_method; //运输方式
    @FXML ComboBox delivery_status; //发货状态
    @FXML ComboBox category; //客户类别
    @FXML public TextField customer_name; //客户名称
    @FXML public TextField receivable; //销售应收
    @FXML TextField made_people; //制单人
    @FXML public ComboBox payment; //结算方式
    @FXML TextField transport_method_str; //快递公司
    @FXML TextField last_update; //最后修改人
    @FXML TextField last_update_str; //最后修改人描述
    @FXML TextField audit; // 审核人
    @FXML TextField audit_str; // 审核人描述
    @FXML public ComboBox contact; // 联系人
    @FXML public ComboBox phone; // 联系电话
    @FXML public TextField invoice_title; //发票抬头
    @FXML public TextField invoice_address; //发票地址
    @FXML public ComboBox shipping_address; //送货地址
    @FXML ComboBox business_leader; //负责业务
    @FXML public ComboBox fax; // 传真
    @FXML public TextField zip; //邮政编码
    @FXML TextField business_leader_str; //负责业务描述
    @FXML public TextField warehouse_out; // 出货仓库
    @FXML public TextField warehouse_out_str; // 出货仓库描述
    @FXML TextField weight; // 重量




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
    @FXML public TableColumn col_inventory; //列 仓库
    @FXML public TableColumn col_floor; //列 楼层
    @FXML TableColumn col_remark; //列 备注
    @FXML TableColumn depotbutton; //列 库位选择按钮
    @FXML TableColumn col_source_order; //列 单据来源
    @FXML TableColumn col_source_no; //列 来源单号



    @FXML TableView tab_remark; //备注及说明--备注表格
    @FXML TableColumn col_remark_id; //列id
    @FXML TableColumn col_remark_content; //列 内容

    @FXML TableView tab_report; //备注及说明--报表
    @FXML TableColumn col_report_id; // 列 id
    @FXML TableColumn col_report_content; //列 内容

    // 合计
    @FXML public TextField total_num; // 合计数量
    @FXML public TextField total_money; //合计金额
    @FXML public TextField total_loan; //合计贷款
    @FXML public TextField total_tax; //税款合计

    /***************************************** 弹出窗口-产品 ********************************************/
    int tablePosition = 0; // 弹出窗口选中的行数
    /***************************************** 弹出窗口-产品-结束 ********************************************/

    static long changeId = 0L;      // 记录当前编辑计算金额cell 的id值

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
        long startTime = System.currentTimeMillis();
        tax.getItems().addAll("外加","内含","零税","免税");
        tax.getSelectionModel().select(0);
        setComboBox(1L,delivery_status,0); //发货状态
        setComboBox(7L,currency,0); //币别
        loadSettlementMethod(payment); //收款方式
        setComboBox(30L,transport_method,0); //运输方式
        setComboBox(10L,category,0); // 客户类别
        loadEmployee(business_leader,0); // 业务负责人

        // 报价产品行双击 调出现有产品窗口
        BaseController.clickEvent(product_table, data ->{
            tablePosition = product_table.getSelectionModel().getSelectedIndex();
            moreProductButtonClick();
        }, 2);


        SalePurchaseOrder purchaseOrder = StageManager.salePurchaseOrder;   // 从订货单导出过来
        SaleQuotation quotationOrder = StageManager.saleQuotation;          // 从报价单导出过来
        SaleOnlineOrder onlineOrder = StageManager.saleOnlineOrder;         // 从网上单导出过来
        StageManager.clear(); // 清理 StageManager
        if(purchaseOrder != null && quotationOrder != null && onlineOrder != null){
            setMenuValue(1);
        }else if(quotationOrder != null){
            setBasicValImportFromQuotation(quotationOrder);     // 从报价单导出过来
            relationLock = true;
        }else if(onlineOrder != null){
            setBasicValImportFromOnline(onlineOrder);           // 从网上单导出过来
            relationLock = true;
        }else if(purchaseOrder != null){
            setBasicValImportFromPurchase(purchaseOrder);       // 从订货单导出过来
            relationLock = true;
        }else{
            setMenuValue(1);
        }
        long stopTime = System.currentTimeMillis();
        System.err.println("耗时："+(stopTime - startTime));

        // 折扣修改监听
        discount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String discountText = discount.getText();
                if(discountText != null && discountText.length() > 0 && isNumeric(discountText)){
                    if(Integer.valueOf(discountText) >= 0 && Integer.valueOf(discountText) <= 100){

                        List<SaleGoodsProductProperty> list = product_table.getItems();
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
     * 订单作废
     */
    @FXML
    public void orderInvalid(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_ABOLISH)) {
            if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
                SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(sale_no.getUserData().toString()));
                if (saleGoods != null && saleGoods.getOrderAudit() == null || !saleGoods.getOrderAudit()) {
                    btn_invalid.setDisable(true);
                    SaleGoods order = new SaleGoods();
                    order.setId(Long.valueOf(sale_no.getUserData().toString()));
                    order.setInvalid(true);
                    int rows = iSaleGoodsService.updateNotNull(order);
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
     * 更多产品查询
     */
    public  void moreProductButtonClick(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleGoodsControllerProduct", this);
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
                SaleGoodsProductProperty property = (SaleGoodsProductProperty)product_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iSaleGoodsProductService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<SaleGoodsProductProperty> dataProperty = product_table.getItems();
                final ObservableList<SaleGoodsProductProperty> newDataProperty = FXCollections.observableArrayList();
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
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){}

    }

    /**
     * 添加产品行
     */
    public void addProductRow() {

        ObservableList<SaleGoodsProductProperty> list = product_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new SaleGoodsProductProperty(list.size()+1,"", "", "", "", "", 0, "", new BigDecimal("0.00"), "100", new BigDecimal("0.00"), new BigDecimal("0.00"), "", "", ""));
        generalProductTab(list);
    }

    /**
     * 删除备注行
     */
    private void deleteRowOfRemark(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tab_remark.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tab_remark.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                RemarkProperty property = (RemarkProperty)tab_remark.getItems().get(index);
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

        ObservableList<RemarkProperty> list = tab_remark.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new RemarkProperty(""));
        tab_remark.setItems(list);
    }

    /**
     * 删除报表行
     */
    private void deleteRowOfReport(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tab_report.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tab_report.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                ReportRemarkProperty property = (ReportRemarkProperty)tab_report.getItems().get(index);
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

        ObservableList<ReportRemarkProperty> list = tab_report.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new ReportRemarkProperty(""));
        tab_report.setItems(list);
    }

    /**
     * 新增订单
     */
    @FXML
    public void add(){
        clearControllerVal();
        createPeople(made_people);
        create_date.setValue(localDate.toLocalDate());
        sale_no.setText(createOrderNo(iSaleGoodsService.getMaxOrderNo()));
        setControllerUse();
    }

    /**
     * 保存数据
     */
    @FXML
    public synchronized  void save(){
        // 检查非空
        if(checkPrimaryNull(sale_no,customer_no,customer_no_str,business_leader_str)){
            return;
        }
        List<SaleGoodsProductProperty> list = product_table.getItems();
        // 库位 楼层不为空
        for (int i = 0; i < list.size(); i++) {
            try {
                if(list.get(i).getFloor() == null || "".equals( list.get(i).getFloor()) || list.get(i).getWarehousePosition() == null || "".equals(list.get(i).getWarehousePosition())){
                    alert_informationDialog(list.get(i).getProductName()+"库位、楼层不能为空,单据提交失败！");
                    return;
                }
                // 库存校验
                List<ProductStock> productStockList = iProductStockService.findProductStockByProIsnum(list.get(0).getProductNo());
                if(productStockList != null && productStockList.size() > 0){
                    int num = 0;
                    for (ProductStock p : productStockList) {
                        if(p.getUsablenum() > 0){
                            num += p.getUsablenum();
                        }
                    }
                    if(num < Integer.valueOf(list.get(0).getNum())){
                        alert_informationDialog("提交失败！"+list.get(0).getProductName()+"库存不足，请注意此单据！");
                        return;
                    }
                }else{
                    alert_informationDialog("提交失败！"+list.get(0).getProductNo()+"库存不足，请注意此单据！");
                    return;
                }
            }catch (NullPointerException e){}
        }
        // 特价单
        if(!che_special_price.isSelected()){
            List<SaleGoodsProductProperty> propertyList =  product_table.getItems();
            if(propertyList != null){
                Double miniDis = getCustomerMinimumDiscountByCode(customer_no.getText());
                for (SaleGoodsProductProperty p : propertyList) {
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



        SaleGoods order = new SaleGoods();
        // 清算表格金额 和税款
        try {
            Double totalWeight = 0.00;
            BigDecimal totalMoney = new BigDecimal("0.00");
            for (SaleGoodsProductProperty p : list) {
                totalMoney = totalMoney.add(new BigDecimal(p.getMoney()));
                if(p.getWeight() != null && !"".equals(p.getWeight())){
                    totalWeight += Double.parseDouble(p.getWeight()) * Integer.valueOf(p.getNum());
                }
            }
            weight.setText(totalWeight.toString());
            order.setMoney(totalMoney);
            order.setMoneyTax(totalMoney.multiply(new BigDecimal(getTaxReruenDouble(create_date.getValue(),2)+"")).setScale(2,BigDecimal.ROUND_UP));
        }catch (Exception e){}

        lastUpdatePeopel(last_update, last_update_str);
        if(create_date.getValue() != null){
            try {
                Date date = df.parse(create_date.getValue().toString());
                order.setCreateDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            order.setCustomerNo(customer_no.getText());
        }
        if(sale_no.getText() != null && !"".equals(sale_no.getText())){
            order.setSaleNo(sale_no.getText());
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
        if(warehouse_out_str.getText() != null && !"".equals(warehouse_out_str.getText())){
            order.setWarehouseOutStr(warehouse_out_str.getText());
        }
        if(category.getSelectionModel().getSelectedItem() != null){
            order.setCustomerCategory(category.getSelectionModel().getSelectedItem().toString());
        }
        if(customer_name.getText() != null && !"".equals(customer_name.getText())){
            order.setCustomerName(customer_name.getText());
        }
        // 销售应收
//        if(receivable.getText() != null && !"".equals(receivable.getText())){
//            order.setSaleReceivable(new BigDecimal(receivable.getText()));
//        }
        if(tax.getSelectionModel().getSelectedItem() != null && !"".equals(tax.getSelectionModel().getSelectedItem())){
            order.setTax(tax.getSelectionModel().getSelectedItem().toString());
        }
        if(currency.getSelectionModel().getSelectedItem() != null && !"".equals(currency.getSelectionModel().getSelectedItem())){
            order.setCurrency(currency.getSelectionModel().getSelectedItem().toString());
        }
        if(warehouse_out.getText() != null && !"".equals(warehouse_out.getText())){
            order.setWarehouseOut(warehouse_out.getText());
        }

        if(made_people.getText() != null && !"".equals(made_people.getText())){
            order.setMadePeople(made_people.getText());
        }
        if(audit.getText() != null && !"".equals(audit.getText())){
            order.setAudit(audit.getText());
        }
        if(audit_str.getText() != null && !"".equals(audit_str.getText())){
            order.setAuditStr(audit_str.getText());
        }
        if(last_update.getText() != null && !"".equals(last_update.getText())){
            order.setLastUpdate(last_update.getText());
        }
        if(last_update_str.getText() != null && !"".equals(last_update_str.getText())){
            order.setLastUpdateStr(last_update_str.getText());
        }
        if(payment.getSelectionModel().getSelectedItem() != null && !"".equals(payment.getSelectionModel().getSelectedItem())){
            order.setPayment(payment.getSelectionModel().getSelectedItem().toString());
        }
        if(contact.getSelectionModel().getSelectedItem() != null && !"".equals(contact.getSelectionModel().getSelectedItem())){
            order.setContact(contact.getValue().toString());
        }
        if(phone.getSelectionModel().getSelectedItem() != null && !"".equals(phone.getSelectionModel().getSelectedItem())){
            order.setPhone(phone.getValue().toString());
        }
        if(shipping_address.getSelectionModel().getSelectedItem() != null && !"".equals(shipping_address.getSelectionModel().getSelectedItem())){
            order.setShippingAddress(shipping_address.getValue().toString());
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
        if(invoice_title.getText() != null && !"".equals(invoice_title.getText())){
            order.setInvoiceTitle(invoice_title.getText());
        }
        if(invoice_address.getText() != null && !"".equals(invoice_address.getText())){
            order.setInvoiceAddress(invoice_address.getText());
        }
        if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
            order.setId(Long.valueOf(sale_no.getUserData().toString()));
        }
        if(transport_method.getSelectionModel().getSelectedItem() != null && !"".equals(transport_method.getSelectionModel().getSelectedItem())){
            order.setCarryMethod(transport_method.getSelectionModel().getSelectedItem().toString());
        }
        if(transport_method_str.getText() != null && !"".equals(transport_method_str.getText())){
            order.setCarryMethodStr(transport_method_str.getText());
        }
        if(weight.getText() != null && !"".equals(weight.getText())){
            order.setWeight(Double.valueOf(weight.getText()));
        }
        if(delivery_status.getSelectionModel().getSelectedItem() != null && !"".equals(delivery_status.getSelectionModel().getSelectedItem())){
            order.setDeliveryStatus(delivery_status.getSelectionModel().getSelectedItem().toString());
        }
        order.setSpecialPriceOrder(che_special_price.selectedProperty().getValue());
        order.setSpecialOrder(che_special.selectedProperty().getValue());
        order.setMoney(new BigDecimal(total_loan.getText()));
        order.setMoneyTax(new BigDecimal(total_tax.getText()));

        if(order.getId() != null){

            // 修改
            int rows = iSaleGoodsService.updateNotNull(order);
            returnMsg(rows);

            setControllerDisable();
        }else{
            order.setReceiveMoney(new BigDecimal("0"));
            order.setImportOutboundAudit(false);
            order.setImportExpressAudit(false);
            order.setTicket(false);
            order.setFinancial("否");
            order.setInvalid(false);
            order.setTaxRate(Float.valueOf(getTax(new Date(),2).toString()));
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iSaleGoodsService.save(order);
            sale_no.setUserData(order.getId().toString());
            returnMsg(rows);
            setControllerDisable();

            if(relationLock){
                //添加关联关系
                relation.setRelationName("销货单");
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
     * @Description 提交验证账户余额是否足够本次交易
     * @Author BlueSky
     * @Date 19:43 2019/4/22
     **/
    private boolean commitAudit(String customerNo){
        SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(sale_no.getUserData().toString()));
        if(saleGoods != null && total_money.getText() != null && !"".equals(total_money.getText()) && customerNo != null && !"".equals(customerNo)){
            Customer customer = iCustomerService.getCustomer(customerNo);
            CustomerBasic customerBasic = iCustomerBasicService.getCustomerBasicByCustomerId(customer.getId());
            // 先款后货客户需要余额验证
            if(customer != null && customerBasic != null && "先款后货".equals(customerBasic.getPaymentMethod())){
                BigDecimal goodsMoney = new BigDecimal(total_money.getText());       // 销货产品总金额（附加税）
                // 提交规则： 余额+信用额度+期初额度 >= 货总金额
                // money 大于0才能提交订单
                BigDecimal money = new BigDecimal("0.00");
                // 1.当余额没钱,先使用信用额度 (ps:欠钱是正数)
                if(customer.getUseableMoney().compareTo(new BigDecimal("0.00"))>0){
                    // 余额 + 信用额度
                    money = customerBasic.getCreditLine().subtract(customer.getUseableMoney());
                    if(money.compareTo(goodsMoney) >= 0){
                        customer.setUseableMoney(customer.getUseableMoney().add(goodsMoney));
                        // 此处使用的信用额度，所以不用冲账
                    }else{
                        alert_informationDialog("可用余额不足！请先打款后再次提交订单!");
                        return false;
                    }
                }
                // 2.当余额有钱
                else{
                    // 2.1当期初还有余额时
                    if(new BigDecimal("0.00").compareTo(customerBasic.getInitialQuota())>0){
                        money = customer.getUseableMoney().add(customerBasic.getInitialQuota()).subtract(customerBasic.getCreditLine());
                        if((money.add(goodsMoney)).compareTo(new BigDecimal("0")) <= 0){
                            // 期初额度是否够本次货物金额
                            if((customerBasic.getInitialQuota().add(goodsMoney)).compareTo(new BigDecimal("0")) <= 0){
                                customerBasic.setInitialQuota(customerBasic.getInitialQuota().add(goodsMoney));
                                saleGoods.setReceiveMoney(goodsMoney);  //冲账金额
                                iSaleGoodsService.updateNotNull(saleGoods);
                            }else{
                                // 先扣除期初额度
                                goodsMoney = goodsMoney.add(customerBasic.getInitialQuota());
                                customerBasic.setInitialQuota(new BigDecimal("0.00"));  // 表示期初余额已经用完
                                customer.setUseableMoney(customer.getUseableMoney().add(goodsMoney));
                                saleGoods.setReceiveMoney(customerBasic.getInitialQuota().abs());  //冲账金额
                                iSaleGoodsService.updateNotNull(saleGoods);
                            }
                            iCustomerBasicService.updateNotNull(customerBasic);     // 更新期初值
                            return true;
                        }else{
                            alert_informationDialog("可用余额不足！请先打款后再次提交订单!");
                            return false;
                        }
                    }
                    // 2.2 当期初余额用完时
                    else{
                        money = customer.getUseableMoney().abs().add(customerBasic.getCreditLine());
                        if( money.compareTo(goodsMoney) >= 0){
                            customer.setUseableMoney(customer.getUseableMoney().add(goodsMoney));
                            saleGoods.setReceiveMoney(goodsMoney);//冲账金额
                            iSaleGoodsService.updateNotNull(saleGoods);
                        }else{
                            alert_informationDialog("可用余额不足！请先打款后再次提交订单!");
                            return false;
                        }
                    }
                }
                iCustomerService.updateNotNull(customer);   //更新客户余额
                return true;
            }else{
                customer.setUseableMoney(customer.getUseableMoney().add(new BigDecimal(total_money.getText())));
                iCustomerService.updateNotNull(customer);   //更新客户余额
                return true;
            }

        }else{
            alert_informationDialog("数据参数异常！");
            return false;
        }
    }


    /**
     * 保存订货产品tableview数据
     */
    private void saveTableviewProduct(){
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id) && product_table.getItems() != null){

            List<SaleGoodsProductProperty> list = product_table.getItems();

            // 重复产品提示。
            for (int i = 0; i < list.size(); i++)
            {
                for (int j = 0; j < list.size(); j++)
                {
                    if (i == j) continue;

                    if (list.get(i).getProductNo().equals(list.get(j).getProductNo()))
                    {
                        alert_informationDialog(list.get(i).getProductName()+"产品有重复！");
                    }
                }
            }
            // 保存数据
            int tableSize = product_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                SaleGoodsProductProperty property = null;
                if(product_table.getItems().get(i) != null){
                    property = (SaleGoodsProductProperty)product_table.getItems().get(i);
                }
                SaleGoodsProduct order = new SaleGoodsProduct();

                if(property.getProductNo() != null && !"".equals(property.getProductNo())){
                    order.setProductNo(property.getProductNo());
                }
                if(property.getSourceOrder() != null && !"".equals(property.getSourceOrder())){
                    order.setSourceOrder(property.getSourceOrder());
                }
                if(property.getSourceNo() != null && !"".equals(property.getSourceNo())){
                    order.setSourceNo(property.getSourceNo());
                }
                if(property.getProductName() != null && !"".equals(property.getProductName())){
                    order.setProductName(property.getProductName());
                }
                if(property.getCategory() != null && !"".equals(property.getCategory())){
                    order.setCategory(property.getCategory());
                }
                if(property.getNum() != null && !"".equals(property.getNum())){
                    order.setNum(Integer.valueOf(property.getNum()));
                }
                if(property.getUnit() != null && !"".equals(property.getUnit())){
                    order.setUnit(property.getUnit());
                }
                if(property.getPricing() != null && !"".equals(property.getPricing())){
                    order.setPricing(new BigDecimal(property.getPricing()));
                }
                if(property.getDiscount() != null && !"".equals(property.getDiscount())){
                    order.setDiscount(Double.valueOf(property.getDiscount()));
                }
                if(property.getPrice() != null && !"".equals(property.getPrice())){
                    order.setPrice(new BigDecimal(property.getPrice()));
                }
                if(property.getMoney() != null && !"".equals(property.getMoney())){
                    order.setMoney(new BigDecimal(property.getMoney()));
                }
                if(property.getWarehousePosition() != null && !"".equals(property.getWarehousePosition())){
                    order.setWarehousePosition(property.getWarehousePosition());
                }
                if(property.getFloor() != null && !"".equals(property.getFloor())){
                    order.setFloor(property.getFloor());
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    order.setRemark(property.getRemark());
                }
                order.setSaleGoodsId(Long.valueOf(id));
                order.setOutboundNum(0);

                if(property.getSourceOrder() != null && property.getSourceNo() != null  && property.getNum() != null && "订货单".equals(property.getSourceOrder())){
                    // 修改订货单 销货数量
                    SalePurchaseOrderProduct pro = iSalePurchaseOrderProductService.getSalePurchaseOrderProductBySaleNum(property.getSourceNo(),property.getProductNo());
                    if(pro != null){
                        int num = Integer.valueOf(property.getNum()) - pro.getSaleNum();
                        int rows = iSalePurchaseOrderProductService.updateSalePurchaseOrderProductSaleNum(property.getSourceNo(),property.getProductNo(),num+"");
                        if(!(rows>0)){
                            System.err.println("改订货单 销货数量失败");
                        }
                    }
                }

                if(property.getId() == null || property.getId() == 0){
                    try {
                        order.setAddtime(new Date());
                        iSaleGoodsProductService.save(order);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        order.setId(property.getId());
                        iSaleGoodsProductService.updateNotNull(order);
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
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id) && tab_remark.getItems() != null){
            int tableSize = tab_remark.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                RemarkProperty property = null;
                if(tab_remark.getItems().get(i) != null){
                    property = (RemarkProperty)tab_remark.getItems().get(i);
                }
                Remark product = new Remark();
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                // 4：销货单
                product.setTypeid(4);
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
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id) && tab_report.getItems() != null){
            int tableSize = tab_report.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                ReportRemarkProperty property = null;
                if(tab_report.getItems().get(i) != null){
                    property = (ReportRemarkProperty)tab_report.getItems().get(i);
                }
                ReportRemark product = new ReportRemark();
                if(property.getContent() != null && !"".equals(property.getContent())){
                    product.setContent(property.getContent());
                }
                // 3：销货单
                product.setTypeid(3);
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
    public synchronized  void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
                int rows = iSaleGoodsService.delete(Long.valueOf(sale_no.getUserData().toString()));
                iSaleGoodsProductService.deleteSaleGoodsProductByParentId(sale_no.getUserData().toString());
                // 删除关联单据
                iRelationService.deleteRelation("报价单",Long.valueOf(sale_no.getUserData().toString()));
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
        setControllerDisable();
        //权限管理
        matchingPermissions("销货单",menu_insert,menu_delete,menu_update,shiro_success,shiro_cancel,menu_printing,menu_clearAll);
        List<SaleGoods> SaleGoodsList = iSaleGoodsService.listSaleGoodsByPage("","",page, 1);
        if(SaleGoodsList != null && SaleGoodsList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(SaleGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            setBasicVal(SaleGoodsList.get(0));
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
     * @Description 加载客户的 结算方式、邮编、类别
     * @Author BlueSky
     * @Date 14:40 2019/4/17
     **/
    private void loadCustomerBasicInfo(String customerNo){
        Customer customer = iCustomerService.getCustomer(customerNo);
        if(customer != null){
            CustomerBasic customerBasic = iCustomerBasicService.getCustomerBasicByCustomerId(customer.getId());
            if(customerBasic != null){
                if(customerBasic.getPaymentMethod() != null){
                    payment.getSelectionModel().select(customerBasic.getPaymentMethod());
                }
                if(customerBasic.getZipCode() != null){
                    zip.setText(customerBasic.getZipCode());
                }
            }
            CustomerDetailInfo detailInfo = new CustomerDetailInfo();
            if(detailInfo != null){
                if(detailInfo.getCustomerCategory() != null){
                    category.getSelectionModel().select(detailInfo.getCustomerCategory());
                }
            }
        }
    }

    /**
     * 加载报价单中的数据
     * @param order
     */
    public void setBasicImportQuotationVal(SaleQuotation order){
        if(order == null){
            return;
        }
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        menu_update.setDisable(false);

        List<SaleGoodsProductProperty> propertyList = product_table.getItems();
        add();
        if(propertyList != null){
            product_table.setItems(FXCollections.observableArrayList(propertyList));
            propertyList = null;
        }

        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
            // 加载客户结算方式 和 邮政编码
            loadCustomerBasicInfo(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
            invoice_title.setText(order.getCustomerNoStr());
        }
        if(order.getOfferNo() != null){
            customer_order_no.setText(order.getOfferNo());
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
            che_special_price.setSelected(order.getSpecialOffer());
        }
        if(order.getCustomerCategory() != null){
            category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        // 销售应收
//        if(order.getReceivableBalance() != null){
//            receivable.setText(order.getReceivableBalance().toString());
//        }
        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getTelephone() != null){
            phone.getSelectionModel().select(order.getTelephone());
        }
        if(order.getAddress() != null){
            invoice_address.setText(order.getAddress());
            shipping_address.getSelectionModel().select(order.getAddress());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
    }

    /**
     * 加载网上订单中的数据
     * @param order
     */
    public void setBasicImportOnlineVal(SaleOnlineOrder order){
        if(order == null){
            return;
        }
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        menu_update.setDisable(false);

        List<SaleGoodsProductProperty> propertyList = product_table.getItems();
        add();
        if(propertyList != null){
            product_table.setItems(FXCollections.observableArrayList(propertyList));
            propertyList = null;
        }


        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
            // 加载客户结算方式 和 邮政编码
            loadCustomerBasicInfo(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getCustomerNoStr() != null){
            customer_name.setText(order.getCustomerNoStr());
        }
        // 销售应收
//        if(order.getReceivableBalance() != null){
//            receivable.setText(order.getReceivableBalance().toString());
//        }
        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getPhone() != null){
            phone.getSelectionModel().select(order.getPhone());
        }
        if(order.getInvoiceAddress() != null){
            invoice_address.setText(order.getInvoiceAddress());
        }
        if(order.getDeliveryAddress() != null){
            shipping_address.getSelectionModel().select(order.getDeliveryAddress());
        }
        if(order.getInvoiceTitle() != null){
            invoice_title.setText(order.getInvoiceTitle());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
    }


    /**
     * 加载订货单中的数据
     * @param order
     */
    public void setBasicImportPurchaseVal(SalePurchaseOrder order){
        if(order == null){
            return;
        }
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        menu_update.setDisable(false);

        List<SaleGoodsProductProperty> propertyList = product_table.getItems();
        add();
        if(propertyList != null){
            product_table.setItems(FXCollections.observableArrayList(propertyList));
            propertyList = null;
        }

        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
            // 加载客户结算方式 和 邮政编码
            loadCustomerBasicInfo(order.getCustomerNo());
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
            warehouse_out.setText(order.getWarehouseOut());
        }
        if(order.getSpecialOrder() != null){
            che_special.setSelected(order.getSpecialOrder());
        }
        if(order.getSpecialOrder() != null){
            che_special_price.setSelected(order.getSpecialOrder());
        }
        if(order.getWarehouseOutStr() != null){
            warehouse_out_str.setText(order.getWarehouseOutStr());
        }
        if(order.getCustomerCategory() != null){
            category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        // 销售应收
//        if(order.getReceivableBalance() != null){
//            receivable.setText(order.getReceivableBalance().toString());
//        }
        if(order.getPaymentMethod() != null){
            payment.getSelectionModel().select(order.getPaymentMethod());
        }
        if(order.getZip() != null){
            zip.setText(order.getZip());
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
        if(order.getBusinessLeaderStr() != null){
            business_leader_str.setText(order.getBusinessLeaderStr());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }


    }

    /**
     * @Description 设置值 来自网上订单导入数据
     * @Author BlueSky
     * @Date 11:11 2019/4/16
     **/
    public void setBasicValImportFromOnline(SaleOnlineOrder order){
        //存储被关联单据
        relation.setBeRelationName("网上订单");
        relation.setBeRelationId(order.getId());
        relationLock = true;
        clearControllerVal();
        setControllerUse();
        add();

        Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
        if(customer != null){
            receivable.setText(customer.getUseableMoney()==null?"0.00":customer.getUseableMoney().toString());
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getCustomerNoStr() != null){
            customer_name.setText(order.getCustomerNoStr());
        }
        lastUpdatePeopel(last_update, last_update_str);
        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getPhone() != null){
            phone.getSelectionModel().select(order.getPhone());
        }
        if(order.getDeliveryAddress() != null){
            invoice_address.setText(order.getDeliveryAddress());
        }
        if(order.getInvoiceAddress() != null){
            shipping_address.getSelectionModel().select(order.getInvoiceAddress());
        }
        if(order.getInvoiceTitle() != null){
            invoice_title.setText(order.getInvoiceTitle());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
        List<SaleGoodsProductProperty> productPropertyList = new ArrayList<>();
        List<SaleOnlineOrderProduct> onlineOrderList = iSaleOnlineOrderProductService.listByOnlineOrderAll(order.getId().toString());
        if(onlineOrderList != null){
            int rows = 1;
            for (SaleOnlineOrderProduct p : onlineOrderList) {
                DepotProperty depotProperty = new DepotProperty();
                ProductBasic productBasic = getProductBasic(p.getProductNo());
                if(productBasic != null){
                    depotProperty = getDepot(productBasic.getInventoryplace());
                }
                productPropertyList.add(new SaleGoodsProductProperty(rows++,"网上订单",order.getOrderNo(),p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPrice(), "0", p.getPrice(), p.getMoney(), depotProperty.getDepotOrder(), depotProperty.getDepotFloor(), p.getRemark()));
            }
        }
        generalProductTab(FXCollections.observableArrayList(productPropertyList));

    }

    /**
     * @Description 设置值 来自报价单导入数据
     * @Author BlueSky
     * @Date 11:11 2019/4/16
     **/
    public void setBasicValImportFromQuotation(SaleQuotation order){
        //存储被关联单据
        relation.setBeRelationName("报价单");
        relation.setBeRelationId(order.getId());
        relationLock = true;
        clearControllerVal();
        setControllerUse();
        add();

        Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
        if(customer != null){
            receivable.setText(customer.getUseableMoney()==null?"0.00":customer.getUseableMoney().toString());
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
            invoice_title.setText(order.getCustomerNoStr());
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
        if(order.getCustomerCategory() != null){
            category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        lastUpdatePeopel(last_update, last_update_str);
        if(order.getContact() != null){
            contact.getSelectionModel().select(order.getContact());
        }
        if(order.getTelephone() != null){
            phone.getSelectionModel().select(order.getTelephone());
        }
        if(order.getAddress() != null){
            invoice_address.setText(order.getAddress());
            shipping_address.getSelectionModel().select(order.getAddress());
        }
        if(order.getBusiness() != null){
            business_leader.getSelectionModel().select(order.getBusiness());
        }
        if(order.getBusinessStr() != null){
            business_leader_str.setText(order.getBusinessStr());
        }
        if(order.getFax() != null){
            fax.getSelectionModel().select(order.getFax());
        }
        List<SaleGoodsProductProperty> productPropertyList = new ArrayList<>();
        List<SaleOfferProduct> productList = iSaleOfferProductService.listSaleOfferProduct(order.getId());
        if(productList != null){
            int rows = 1;
            for (SaleOfferProduct p : productList) {
                DepotProperty depotProperty = new DepotProperty();
                ProductBasic productBasic = getProductBasic(p.getProductNo());
                if(productBasic != null){
                    depotProperty = getDepot(productBasic.getInventoryplace());
                }
                productPropertyList.add(new SaleGoodsProductProperty(rows++,"报价单",order.getOfferNo(),p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getDiscount(), p.getPrice(), p.getMoney(), depotProperty.getDepotOrder(), depotProperty.getDepotFloor(), ""));
            }
        }
        generalProductTab(FXCollections.observableArrayList(productPropertyList));

        loadRemark(order.getId(),"2","1");

    }

    /**
     * @Description 设置值 来自订货单导入数据
     * @Author BlueSky
     * @Date 11:20 2019/4/16
     **/
    public void setBasicValImportFromPurchase(SalePurchaseOrder order){
        //存储被关联单据
        relation.setBeRelationName("订货单");
        relation.setBeRelationId(order.getId());
        relationLock = true;
        clearControllerVal();
        setControllerUse();

        add();
        Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
        if(customer != null){
            receivable.setText(customer.getUseableMoney()==null?"0.00":customer.getUseableMoney().toString());
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
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
            warehouse_out.setText(order.getWarehouseOut());
        }
        if(order.getSpecialOrder() != null){
            che_special_price.setSelected(order.getSpecialOrder());
        }
        if(order.getWarehouseOutStr() != null){
            warehouse_out_str.setText(order.getWarehouseOutStr());
        }
        if(order.getCustomerCategory() != null){
            category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        if(order.getPaymentMethod() != null){
            payment.getSelectionModel().select(order.getPaymentMethod());
        }
        lastUpdatePeopel(last_update, last_update_str);
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
            try {
                business_leader.getSelectionModel().select(order.getBusinessLeader());
            }catch (Exception e){e.printStackTrace();}
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
        List<SaleGoodsProductProperty> productPropertyList = new ArrayList<>();

        List<SalePurchaseOrderProduct> listPurchaseOrderProduct = iSalePurchaseOrderProductService.listPurchaseOrderProduct(order.getId().toString());

        if(listPurchaseOrderProduct != null){
            int rows = 1;
            for (SalePurchaseOrderProduct p : listPurchaseOrderProduct) {
                DepotProperty depotProperty = new DepotProperty();
                ProductBasic productBasic = getProductBasic(p.getProductNo());
                if(productBasic != null){
                    depotProperty = getDepot(productBasic.getInventoryplace());
                }
                productPropertyList.add(new SaleGoodsProductProperty(rows++,"订货单",order.getOrderNo(),p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getDiscount(), p.getPrice(), p.getMoney(), depotProperty.getDepotOrder(), depotProperty.getDepotFloor(), p.getRemark()));
            }
        }
        generalProductTab(FXCollections.observableArrayList(productPropertyList));

        loadRemark(order.getId(),"3","2");
    }

    /**
     * @Description 加载备注及说明内容
     * @Author BlueSky
     * @Date 18:10 2019/4/16
     **/
    public void loadRemark(Long orderid ,String remarkid,String reportid){
        List<RemarkProperty> remarkPropertyList = new ArrayList<>();
        List<ReportRemarkProperty> reportPropertyList = new ArrayList<>();
        List<Remark> listRemark = iRemarkService.listRemark(orderid, remarkid);
        List<ReportRemark> listReport = iReportRemarkService.listReportRemark(orderid, reportid);
        if(listRemark != null){
            listRemark.forEach(p->{
                remarkPropertyList.add(new RemarkProperty(p.getRemark()));
            });
        }
        tab_remark.setEditable(true);
        col_remark_content.setCellFactory(column -> EditCell.createStringEditCell());
        final ObservableList<RemarkProperty> dataProperty = FXCollections.observableArrayList(remarkPropertyList);
        col_remark_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_remark_content.setCellValueFactory(new PropertyValueFactory("remark"));
        tab_remark.setItems(dataProperty);

        if(listReport != null){
            listReport.forEach(p->{
                reportPropertyList.add(new ReportRemarkProperty(p.getContent()));
            });
        }
        tab_report.setEditable(true);
        col_report_content.setCellFactory(column -> EditCell.createStringEditCell());
        final ObservableList<ReportRemarkProperty> reportProperty = FXCollections.observableArrayList(reportPropertyList);
        col_report_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_report_content.setCellValueFactory(new PropertyValueFactory("content"));
        tab_report.setItems(reportProperty);
        setControllerUse();
    }

    /**
     * 设置值
     * @param order
     */
    public void setBasicVal(SaleGoods order){
        clearControllerVal();
        if(order == null){
            return;
        }
        Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
        if(customer != null){
            receivable.setText(customer.getUseableMoney()==null?"0.00":customer.getUseableMoney().toString());
        }

        if(order.getId() != null){
            sale_no.setUserData(order.getId().toString());
        }
        if(order.getCreateDate() != null){
            create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getSaleNo() != null){
            sale_no.setText(order.getSaleNo());
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
            warehouse_out.setText(order.getWarehouseOut());
        }
        if(order.getSpecialOrder() != null){
            che_special.setSelected(order.getSpecialOrder());
        }
        if(order.getSpecialPriceOrder() != null){
            che_special_price.setSelected(order.getSpecialPriceOrder());
        }
        if(order.getWarehouseOutStr() != null){
            warehouse_out_str.setText(order.getWarehouseOutStr());
        }
        if(order.getCustomerCategory() != null){
            category.getSelectionModel().select(order.getCustomerCategory());
        }
        if(order.getCustomerName() != null){
            customer_name.setText(order.getCustomerName());
        }
        if(order.getMadePeople() != null){
            made_people.setText(order.getMadePeople());
        }
        if(order.getPayment() != null){
            payment.getSelectionModel().select(order.getPayment());
        }
        if(order.getAudit() != null){
            audit.setText(order.getAudit());
        }
        if(order.getAuditStr() != null){
            audit_str.setText(order.getAuditStr());
        }
        if(order.getLastUpdate() != null){
            last_update.setText(order.getLastUpdate());
        }
        if(order.getLastUpdateStr() != null){
            last_update_str.setText(order.getLastUpdateStr());
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
            try {
                business_leader.getSelectionModel().select(order.getBusinessLeader());
            }catch (Exception e){e.printStackTrace();}
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
        if(order.getWeight() != null){
            weight.setText(order.getWeight().toString());
        }
        if(order.getCarryMethod() != null){
            transport_method.getSelectionModel().select(order.getCarryMethod());
        }
        if(order.getCarryMethodStr() != null){
            transport_method_str.setText(order.getCarryMethodStr());
        }
        if(order.getDeliveryStatus() != null){
            delivery_status.getSelectionModel().select(order.getDeliveryStatus());
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
     * 加载tableview数据
     */
    private void setTableviewVal(){
        if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
            List<SaleGoodsProduct> productList = iSaleGoodsProductService.listSaleGoodsProduct(sale_no.getUserData().toString());
            List<SaleGoodsProductProperty> productPropertyList = new ArrayList<>();
            if(productList == null) {return;}
            clearTotalCalcVal();
            int rows = 1;
            for (SaleGoodsProduct p : productList) {
                totalCost(p.getNum()==null?0:p.getNum(), p.getMoney()==null?new BigDecimal("0.00"):p.getMoney(), tax.getItems()==null?0:tax.getSelectionModel().getSelectedIndex()+1, total_num, total_tax, total_loan, total_money);
                productPropertyList.add(new SaleGoodsProductProperty(p.getId(),rows++,p.getSourceOrder(),p.getSourceNo(),p.getSaleGoodsId(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(),p.getDiscount()+"", p.getPrice(), p.getMoney(), p.getWarehousePosition() , p.getFloor() ,p.getRemark()));

            }
            generalProductTab(FXCollections.observableArrayList(productPropertyList));
        }
    }

    /**
     * 给产品表格加载数据
     * @param productPropertyList
     */
    public void generalProductTab(ObservableList<SaleGoodsProductProperty> productPropertyList){

        product_table.setEditable(true);
        col_source_order.setCellFactory(column -> EditCell.createStringEditCell());
        col_source_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_category.setCellFactory(column -> EditCell.createStringEditCell());
        col_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
//        col_pricing.setCellFactory(column -> EditCell.createStringEditCell());
        col_discount.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_inventory.setCellFactory(column -> EditCell.createStringEditCell());
        col_floor.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());
        Callback<TableColumn<SaleGoodsProductProperty, String>, TableCell<SaleGoodsProductProperty, String>> buttonFactory
                = new Callback<TableColumn<SaleGoodsProductProperty, String>, TableCell<SaleGoodsProductProperty, String>>() {
            @Override
            public TableCell call(final TableColumn<SaleGoodsProductProperty, String> param) {
                final TableCell<SaleGoodsProductProperty, String> cell = new TableCell<SaleGoodsProductProperty, String>() {

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
        final ObservableList<SaleGoodsProductProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_source_order.setCellValueFactory(new PropertyValueFactory("sourceOrder"));
        col_source_no.setCellValueFactory(new PropertyValueFactory("sourceNo"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_category.setCellValueFactory(new PropertyValueFactory("category"));
        col_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_pricing.setCellValueFactory(new PropertyValueFactory("pricing"));
        col_discount.setCellValueFactory(new PropertyValueFactory("discount"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_inventory.setCellValueFactory(new PropertyValueFactory("warehousePosition"));
        col_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        product_table.setItems(dataProperty);


        product_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoodsProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends SaleGoodsProductProperty> observableValue, SaleGoodsProductProperty oldItem, SaleGoodsProductProperty newItem) {
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

        //提交数量计算金额  询价订单--询价产品 数量
        col_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;
                List<SaleGoodsProductProperty> data = product_table.getItems();
                for (SaleGoodsProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        Double money = newNum*Double.valueOf(property.getPrice());
                        money = money * (property.getDiscount()==null||"".equals(property.getDiscount())||"0".equals(property.getDiscount())?1:Double.valueOf(property.getDiscount())/100.0) ;
                        property.setMoney(String.valueOf(money));
                        reloadTotalVal();
                    }
                }
            }
        });
        //提交折扣计算金额  询价订单--询价产品 折扣
        col_discount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                List<SaleGoodsProductProperty> data = product_table.getItems();
                for (SaleGoodsProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        String newValue =event.getNewValue().toString();
                        Double newDis = newValue==null||"".equals(newValue)||"0".equals(newValue)?1:Integer.valueOf(newValue)/100.0;
                        //计算金额
                        BigDecimal price = new BigDecimal(property.getPricing()).multiply(new BigDecimal(newDis.toString())).setScale(2, BigDecimal.ROUND_UP);
                        property.setPrice(price.toString());
                        Double money = newDis*Double.valueOf(property.getPrice());
                        property.setMoney(String.valueOf(money));
                        reloadTotalVal();
                    }
                }
            }
        });
        //提交单价计算金额  询价订单--询价产品 单价
        col_price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                Double  newPrice = newValue != null && !"".equals(newValue)?newPrice=Double.parseDouble(newValue):0.00;
                List<SaleGoodsProductProperty> data = product_table.getItems();
                for (SaleGoodsProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setPrice(String.valueOf(newPrice));
                        //计算金额
                        Double money = Integer.parseInt(property.getNum())*newPrice;
                        money = money * (property.getDiscount()==null||"".equals(property.getDiscount())||"0".equals(property.getDiscount())?1:Double.valueOf(property.getDiscount())/100.0) ;
                        property.setMoney(String.valueOf(money));
                        property.setDiscount(new BigDecimal(newPrice).multiply(new BigDecimal("100")).divide(new BigDecimal(property.getPricing()),2,BigDecimal.ROUND_UP).toString());
                        reloadTotalVal();
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
        List<SaleGoodsProductProperty> propertyList = product_table.getItems();
        for (SaleGoodsProductProperty p : propertyList) {
            totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),tax.getSelectionModel().getSelectedItem()==null?"外加":tax.getSelectionModel().getSelectedItem().toString(), total_num, total_tax, total_loan, total_money);
        }
    }

    /**
     * 加载备注tableview数据
     */
    private void setTableviewRemarkVal(){


        if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
            List<Remark> productList = iRemarkService.listRemark(Long.valueOf(sale_no.getUserData().toString()),"4");
            List<RemarkProperty> productPropertyList = new ArrayList<>();
            productList.forEach(p->{
                productPropertyList.add(new RemarkProperty(p.getId(),p.getRemark()));
            });
            tab_remark.setEditable(true);
            col_remark_content.setCellFactory(column -> EditCell.createStringEditCell());
            final ObservableList<RemarkProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_remark_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_remark_content.setCellValueFactory(new PropertyValueFactory("remark"));

            tab_remark.setItems(dataProperty);
        }
    }

    /**
     * 加载报表tableview数据
     */
    private void setTableviewReportVal(){
        tab_report.setEditable(true);

        col_report_content.setCellFactory(column -> EditCell.createStringEditCell());

        if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
            List<ReportRemark> productList = iReportRemarkService.listReportRemark(Long.valueOf(sale_no.getUserData().toString()),"3");
            List<ReportRemarkProperty> productPropertyList = new ArrayList<>();
            productList.forEach(p->{
                productPropertyList.add(new ReportRemarkProperty(p.getId(),p.getContent()));
            });
            final ObservableList<ReportRemarkProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_report_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_report_content.setCellValueFactory(new PropertyValueFactory("content"));

            tab_report.setItems(dataProperty);
        }
    }
    /**
     * 清除空间上的值
     */
    @FXML
    private void clearControllerVal(){

        LocalDateTime localDate = LocalDateTime.now();

        create_date.setValue(localDate.toLocalDate());
        customer_no.clear();
        sale_no.clear();
        sale_no.setUserData(null);
        customer_no_str.clear();
        customer_order_no.clear();
        tax.getSelectionModel().selectFirst();
        currency.getSelectionModel().selectFirst();
        discount.setText("100");
        warehouse_out.clear();
        warehouse_out_str.clear();


        // 特批单复选框
        che_special.setSelected(false);
        // 特价单复选框
        che_special_price.setSelected(false);

        category.getSelectionModel().selectFirst();
        customer_name.clear();
        receivable.clear();
        made_people.clear();
        payment.getSelectionModel().selectFirst();
        audit.clear();
        audit_str.clear();
        last_update.clear();
        last_update_str.clear();
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
        transport_method.getSelectionModel().selectFirst();
        transport_method_str.clear();
        weight.clear();
        delivery_status.getSelectionModel().selectFirst();

        clearTotalCalcVal();

        product_table.setItems(null);
        tab_remark.setItems(null);
        tab_report.setItems(null);
    }

    /**
     * 清空合计框中的值
     */
    protected void clearTotalCalcVal(){
        total_num.setText("0");
        total_loan.setText("0.00");
        total_money.setText("0.00");
        total_tax.setText("0.00");
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
        if(sale_no.getUserData() != null && !"".equals(sale_no.getUserData().toString())){
            SaleGoods order = iSaleGoodsService.selectByKey(Long.valueOf(sale_no.getUserData().toString()));
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
         create_date.setDisable(bool);
         menu_clearAll.setDisable(bool);
         menu_commit.setDisable(bool);
//         menu_insert.setDisable(bool);
         menu_delete.setDisable(bool);
         shiro_success.setDisable(bool);

         customer_no.setDisable(true);
        sale_no.setDisable(true);
         customer_no_str.setDisable(bool);
         customer_order_no.setDisable(bool);
         tax.setDisable(bool);
         currency.setDisable(bool);
         discount.setDisable(bool);
         warehouse_out.setDisable(true);
        warehouse_out_str.setDisable(bool);
        // 作废按钮
         btn_invalid.setDisable(bool);
        // 特价单复选框
        che_special_price.setDisable(bool);
        che_special.setDisable(bool);

         delivery_status.setDisable(bool);
         weight.setDisable(true);
         transport_method.setDisable(bool);
         transport_method_str.setDisable(bool);
         category.setDisable(bool);
         customer_name.setDisable(bool);
         receivable.setDisable(true);
         made_people.setDisable(true);
         payment.setDisable(bool);
         audit.setDisable(true);
         audit_str.setDisable(true);
         last_update.setDisable(true);
         last_update_str.setDisable(true);
         contact.setDisable(bool);
         phone.setDisable(bool);
         invoice_address.setDisable(bool);
         shipping_address.setDisable(bool);
         invoice_title.setDisable(bool);
         business_leader.setDisable(bool);
         fax.setDisable(bool);
         zip.setDisable(bool);
         business_leader_str.setDisable(bool);
         btn_invalid.setDisable(bool);


         product_table.setDisable(bool);
         tab_remark.setDisable(bool);
         tab_report.setDisable(bool);

        // 合计
         total_num.setDisable(bool);
         total_money.setDisable(bool);
         total_loan.setDisable(bool);
         total_tax.setDisable(bool);
    }

    /**
     * 审核通过
     */
    private void setShiroControlYES(){
        shiro_cancel.setDisable(false);
        shiro_success.setDisable(true);
        import_out.setDisable(false);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        import_out.setDisable(true);
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            String customerNo = customer_no.getText();    // 客户编号
            // 提交验证账户余额是否足够本次交易
            if(!commitAudit(customerNo)){
                alert_informationDialog("操作失败！客户信用额度不足。");
                return;
            }
            SaleGoods order = iSaleGoodsService.selectByKey(Long.valueOf(id));
            if(order != null && order.getInvalid() != null && !order.getInvalid() || order.getInvalid() == null){
                if(id == null && "".equals(id)){
                    alert_informationDialog("单据还暂未保存，无法审核！");
                    return;
                }
                setShiroControlYES();
                lastUpdatePeopel(audit, audit_str);
                shiroUpdateData(true);
                menu_update.setDisable(true);
            }else{
                alert_informationDialog("此单据已经被作废，无法审核！");
            }
        }
    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool){
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(id));
            saleGoods.setOrderAudit(bool);
            saleGoods.setLastUpdate(last_update.getText());
            saleGoods.setLastUpdateStr(last_update_str.getText());
            saleGoods.setAudit(audit.getText());
            saleGoods.setAuditStr(audit_str.getText());
            iSaleGoodsService.updateNotNull(saleGoods);
            setBasicVal(saleGoods);
        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel(){
        // 有效单据验证
        String id = sale_no.getUserData().toString();
        if(id == null || "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }

        if(!iRelationService.isRelation("销货单",Long.valueOf(id))){
            setShiroControlNO();
            lastUpdatePeopel(audit, audit_str);
            shiroUpdateData(false);
            menu_update.setDisable(false);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }
    }

    /**
     * 导出 到销售退货单
     */
    @FXML
    public void importOutToReturn(){
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(id));
            try {
                if(saleGoods != null && saleGoods.getOrderAudit() && !saleGoods.getInvalid()){
                    if(getPermissions("37_259_4")){
                        changeHomeBtn(1,0,5);
                        StageManager.saleGoods = saleGoods;
                        List<SaleGoodsProduct> saleGoodsProductList = iSaleGoodsProductService.listSaleGoodsProduct(saleGoods.getId().toString());
                        if(saleGoodsProductList != null){
                            StageManager.PRODUCT_LIST = saleGoodsProductList;
                        }
                        Pane pane1 = StageManager.ORDERCONTENT.get("SalePane");

                        pane1.getChildren().setAll(loader.load("/fxml/sale/sale_return.fxml"));
                    }else{
                        alertPermissions();
                    }
                }else{
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出!");
                }
            }catch (Exception e){
                alert_informationDialog("导出失败,单据数据异常!");
            }

        }
    }

    /**
     * 导出 到 销货出库单
     */
    @FXML
    public void importOutToOutbound(){
        String id = sale_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleGoods saleGoods = iSaleGoodsService.selectByKey(Long.valueOf(id));
            try {
                if(saleGoods != null && saleGoods.getOrderAudit() && !saleGoods.getInvalid()){
                    changeHomeBtn(1,3,2);
                    StageManager.saleGoods = saleGoods;
                    Pane pane1 = StageManager.ORDERCONTENT.get("publicPane");
                    pane1.getChildren().setAll(loader.load("/fxml/stock/sale_outbound_order.fxml"));
                }else{
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出!");
                }
            }catch (Exception e){
                alert_informationDialog("导出失败,单据数据异常!");
            }
        }
    }

    /**
     * 导入 报价单
     */
    @FXML
    public void importInFromQuotation(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        stage.setTitle("导入-报价单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleGoodsControllerImportQuotation", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/quotation_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 导入 网上订单
     */
    @FXML
    public void importInFromOnline(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        stage.setTitle("导入-网上订单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleGoodsControllerImportOnline", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/online_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 导入订货单
     */
    @FXML
    public void importInFromPurchase(){
         SpringFxmlLoader loader = new SpringFxmlLoader();
        stage.setTitle("导入-订货单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleGoodsControllerImport", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/purchase_order_import.fxml"));
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
        StageManager.CONTROLLER.put("SaleGoodsControllerNo", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void importOutInquiry(){

        String orderids =  sale_no.getUserData().toString();

        long id = 0;

        if(!"".equals(orderids))id = Long.parseLong(orderids);


        SaleGoods saleGoods = iSaleGoodsService.selectByKey(id);


        if(saleGoods != null){

            if(saleGoods.getOrderAudit()){
                if(getPermissions("2_109_4")){

                    changeHomeBtn(1,2,1);

                    StageManager.saleGoods = saleGoods;

                    Pane pane1 = StageManager.ORDERCONTENT.get("PurchasePane");

                    pane1.getChildren().setAll(loader.load("/fxml/purchase/inquiry.fxml"));
                }else {
                    alertPermissions();
                }

            }else{
                alert_informationDialog("该单据未审核无法进行导出!");
            }



        }


    }


    /**
     * 导出采购订单
     */
    @FXML
    public void importOutPurchaseOrder(){

        String orderids =  sale_no.getUserData().toString();

        long id = 0;

        if(!"".equals(orderids))id = Long.parseLong(orderids);


        SaleGoods saleGoods = iSaleGoodsService.selectByKey(id);


        if(saleGoods != null){

            if(saleGoods.getOrderAudit()){
                if(getPermissions("9_142_4")){
                    changeHomeBtn(1,2,2);

                    StageManager.saleGoods = saleGoods;

                    Pane pane1 = StageManager.ORDERCONTENT.get("PurchasePane");

                    pane1.getChildren().setAll(loader.load("/fxml/purchase/purchase_order.fxml"));
                }else {
                    alertPermissions();
                }
            }else{
                alert_informationDialog("该单据未审核无法进行导出!");
            }



        }


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
        StageManager.CONTROLLER.put("SaleGoodsController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * 打开窗口 -- 现有库位查询
     * @param index 第几行
     */
    @FXML
    public void openMoreWarehouseQuery(int index){
        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("index", index);
        String productNum = "";
        if(product_table.getItems() != null){
            productNum = ((SaleGoodsProductProperty)product_table.getItems().get(index)).getProductNo();
        }
        StageManager.CONTROLLER.put("productNum", productNum);
        StageManager.CONTROLLER.put("SaleGoodsControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/storehouse_mini.fxml"));
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
        StageManager.CONTROLLER.put("SaleGoodsControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/warehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


}
