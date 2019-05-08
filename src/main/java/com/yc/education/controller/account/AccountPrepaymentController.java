package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountPrepayment;
import com.yc.education.model.account.AccountPrepaymentInfo;
import com.yc.education.model.account.AccountPrepaymentInfoProperty;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.service.account.IAccountPrepaymentInfoService;
import com.yc.education.service.account.IAccountPrepaymentService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.SneakyThrows;
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
 * @Description 预付账款
 * @Author BlueSky
 * @Date 2019-01-25 15:40
 */
@Controller
public class AccountPrepaymentController extends BaseController implements Initializable {

    @Autowired IAccountPrepaymentService iAccountPrepaymentService;         //预付账款
    @Autowired IAccountPrepaymentInfoService iAccountPrepaymentInfoService;         //预付账款详情
    @Autowired PurchaseStockService iPurchaseStockService;                 //采购入库单

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页
    @FXML VBox menu_clearAll;        // 清除
    @FXML VBox menu_commit;          // 提交
    @FXML VBox menu_update;          // 修改
    @FXML VBox import_in;            // 导入
    @FXML VBox menu_insert;          // 新增
    @FXML VBox menu_delete;          // 删除
    @FXML VBox shiro_success;        // 审核
    @FXML VBox shiro_cancel;         // 取消审核
    @FXML Label writestate;         // 待输入

    @FXML TextField  order_no;              //付款编号
    @FXML DatePicker create_date;           //付款日期
    @FXML public TextField customer_no;           //客户编号
    @FXML public TextField  customer_no_str;       //客户编号描述
    @FXML ComboBox   payment_currency_one;  //付款币别
    @FXML ComboBox   payment_currency_two;  //被转换的付款币别
    @FXML ComboBox   payment_method;        //付款方式
    @FXML ComboBox   process_people;        //经办人
    @FXML TextField  process_people_str;    //经办人描述
    @FXML TextField  made_people;           //制单人
    @FXML TextField  remark;                //备注
    @FXML TextField  audit;                 //审核人
    @FXML TextField  audit_str;             //审核人描述
    @FXML TextField  last_update;           //最后修改人
    @FXML TextField  last_update_str;       //最后修改人描述
    @FXML TextField  rate;                  //汇率
    @FXML TextField  pay_money;             //付款金额
    @FXML TextField  token_no;              //凭证编号
    @FXML TextField  prepayment_money;      //预付余额
    @FXML TextField  invoice_no;            //发票号码

    @FXML TableView tableview_purchase;
    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_disabled;         //审核
    @FXML TableColumn  col_propayment_no;   //预付账款号
    @FXML TableColumn  col_payment_method;   //付款方式
    @FXML TableColumn  col_invoice_no;       //发票号码
    @FXML TableColumn  col_supplier_short;  //供应商简称
    @FXML TableColumn  col_create_date;     //制单日期


    // 日期格式
    LocalDateTime localDate = LocalDateTime.now();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
    Stage stage = new Stage();

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        setControllerDisable();
        loadEmployee(process_people,0); // 收款人
        setComboBox(7L, payment_currency_one, 0);    //币别
        setComboBox(7L, payment_currency_two, 0);    //币别
        setComboBox(20L, payment_method, 0);         //付款方式
        setMenuValue(1);                                         //加载第一页数据
        process_people.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = process_people.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    process_people_str.setText(bus);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    // checkbox
    Callback<TableColumn<AccountPrepayment, Boolean>, TableCell<AccountPrepayment, Boolean>> generalCheckboxFactory
            = new Callback<TableColumn<AccountPrepayment, Boolean>, TableCell<AccountPrepayment, Boolean>>() {
        @Override
        public TableCell call(final TableColumn<AccountPrepayment, Boolean> param) {
            final TableCell<Customer, Boolean> cell = new TableCell<Customer, Boolean>() {

                final CheckBox checkBox = new CheckBox("审核");

                @Override
                public void updateItem(Boolean ite, boolean empty) {
                    if (ite != null) {
                        checkBox.setSelected(ite);

                    }
                    super.updateItem(ite, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        checkBox.setOnAction((ActionEvent t)
                                -> {

//                            if(ite){
//                                if(f_alert_confirmDialog("温馨提示","确认要取消审核吗？")){
//                                    checkBox.setSelected(false);
//                                }else{
//                                    checkBox.setSelected(true);
//                                }
//                            }else{
//                                if(f_alert_confirmDialog("温馨提示","确认要审核吗？")){
//                                    checkBox.setSelected(true);
//                                }else{
//                                    checkBox.setSelected(false);
//                                }
//                            }

                            int selectdIndex = getTableRow().getIndex();
                            List<AccountPrepayment> propertyList = tableview_purchase.getItems();
                            AccountPrepayment AccountPrepayment = (AccountPrepayment) tableview_purchase.getItems().get(selectdIndex);
                            if (AccountPrepayment != null && propertyList != null) {
                                // 如果回传 是没有任何值的时候 默认不勾选
                                if(AccountPrepayment.getOrderAudit() == null){
                                    AccountPrepayment.setOrderAudit(false);
                                }
                                propertyList.get(selectdIndex).setOrderAudit(!AccountPrepayment.getOrderAudit());
                                if(!AccountPrepayment.getOrderAudit()){
                                    AccountPrepayment.setOrderAudit(false);
                                }else{
                                    AccountPrepayment.setOrderAudit(true);
                                }
                                iAccountPrepaymentService.updateNotNull(AccountPrepayment);
                                generalProductTab(FXCollections.observableArrayList(propertyList));

                                setBasicValue(AccountPrepayment);
                            }
                        });
                        checkBox.setMaxWidth(Double.MAX_VALUE);
                        setGraphic(checkBox);
                        setText(null);
                    }
                }
            };
            cell.setStyle("-fx-alignment: CENTER;");
            return cell;
        }
    };

    /**
     * 加载表格数据
     * @param receiptList
     */
    @SneakyThrows(Exception.class)
    public void generalProductTab(List<AccountPrepayment> receiptList){
        int rows = 1;
        for (AccountPrepayment p : receiptList) {
            p.setCreateDateStr(df.format(p.getCreateDate()));
            p.setNo(rows++);
        }

        col_disabled.setCellFactory(generalCheckboxFactory);

        ObservableList<AccountPrepayment> data = FXCollections.observableArrayList(receiptList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_propayment_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_supplier_short.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_payment_method.setCellValueFactory(new PropertyValueFactory("paymentType"));
        col_invoice_no.setCellValueFactory(new PropertyValueFactory("invoiceNo"));
        col_create_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_disabled.setCellValueFactory(new PropertyValueFactory("orderAudit"));


        tableview_purchase.setEditable(true);
        tableview_purchase.setItems(data);

    }



    /**
     * 加载预付账款明细明细
     */
    public void showTableSelfInfo(){
        List<AccountPrepayment> prepaymentList = iAccountPrepaymentService.listAccountPrepaymentByRecently(30);
        if(prepaymentList != null){
            generalProductTab(prepaymentList);
        }

    }

    /**
     * 预付账款明细 view
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
            showTableSelfInfo();
        }
    }

    /**
     * 删除产 预付账款明细
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tableview_purchase.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tableview_purchase.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                AccountPrepaymentInfoProperty property = (AccountPrepaymentInfoProperty)tableview_purchase.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iAccountPrepaymentInfoService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<AccountPrepaymentInfoProperty> dataProperty = tableview_purchase.getItems();
                final ObservableList<AccountPrepaymentInfoProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                tableview_purchase.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加 预付账款明细 行
     */
    public void addProductRow() {

        ObservableList<AccountPrepaymentInfoProperty> list = tableview_purchase.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountPrepaymentInfoProperty( "", "", "", "",""));
        tableview_purchase.setItems(list);
    }

    /**
     * 保存收款明细数据
     */
    @SneakyThrows
    private void saveTableReceiptInfo(){
        String orderid = order_no.getUserData() == null?null:order_no.getUserData().toString();
        if(orderid != null && !"".equals(orderid) && tableview_purchase.getItems() != null){
            for (int i = 0; i < tableview_purchase.getItems().size(); i++) {
                AccountPrepaymentInfoProperty property = null;
                if(tableview_purchase.getItems().get(i) != null){
                    property = (AccountPrepaymentInfoProperty)tableview_purchase.getItems().get(i);
                }
                AccountPrepaymentInfo product = new AccountPrepaymentInfo();
                if(property.getCreateDate() != null && !"".equals(property.getCreateDate())){
                    product.setCreateDate(df.parse(property.getCreateDate()));
                }
                if(property.getPrepaymentAccount() != null && !"".equals(property.getPrepaymentAccount())){
                    product.setPrepaymentAccount(property.getPrepaymentAccount());
                }
                if(property.getEnterBox() != null && !"".equals(property.getEnterBox())){
                    product.setEnterBox(property.getEnterBox());
                }
                if(property.getOrderAudit() != null && !"".equals(property.getOrderAudit())){
                    product.setOrderAudit(property.getOrderAudit()==null?null:("true".equals(property.getOrderAudit())?true:false));
                }
                if(property.getSupplierShort() != null && !"".equals(property.getSupplierShort())){
                    product.setSupplierShort(property.getSupplierShort());
                }
                product.setOtherid(Long.valueOf(orderid));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iAccountPrepaymentInfoService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountPrepaymentInfoService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            showTableSelfInfo();
        }
    }


    /**
     * 保存数据
     */
    @FXML
    @SneakyThrows
    public  synchronized void save(){
        lastUpdatePeopel(last_update, last_update_str);
        AccountPrepayment order = new AccountPrepayment();
        order.setId(order_no.getUserData()==null?null:Long.valueOf(order_no.getUserData().toString()));
        order.setOrderNo(order_no.getText());
        order.setCreateDate(create_date.getValue() == null?null:df.parse(create_date.getValue().toString()));
        order.setMadePeople(made_people.getText());
        order.setCustomerNo(customer_no.getText());
        order.setCustomerNoStr(customer_no_str.getText());
        order.setAudit(audit.getText());
        order.setAuditStr(audit_str.getText());
        order.setLastUpdate(last_update.getText());
        order.setLastUpdateStr(last_update_str.getText());
        order.setMadePeople(made_people.getText());
        order.setRemark(remark.getText());
        order.setRate(rate.getText());
        order.setPaymentMoney(pay_money.getText()==null?null:new BigDecimal(pay_money.getText()));
        order.setPrepaymentMoney(prepayment_money.getText()==null?null:new BigDecimal(prepayment_money.getText()));
        order.setCurrency(payment_currency_one.getSelectionModel().getSelectedItem() == null?null:payment_currency_one.getSelectionModel().getSelectedItem().toString());
        order.setCurrencyTwo(payment_currency_two.getSelectionModel().getSelectedItem() == null?null:payment_currency_two.getSelectionModel().getSelectedItem().toString());
        order.setPaymentType(payment_method.getSelectionModel().getSelectedItem() == null?null:payment_method.getSelectionModel().getSelectedItem().toString());
        order.setProcessPeople(process_people.getSelectionModel().getSelectedItem() == null?null:process_people.getSelectionModel().getSelectedItem().toString());
        order.setProcessPeopleStr(process_people_str.getText());
        order.setTokenNo(token_no.getText());
        order.setInvoiceNo(invoice_no.getText());

        if(order.getId() != null){
            // 修改
            int rows = iAccountPrepaymentService.updateNotNull(order);
            returnMsg(rows);
        }else{
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iAccountPrepaymentService.save(order);
            order_no.setUserData(order.getId());
            returnMsg(rows);
        }
        setControllerDisable();
        showTableSelfInfo();
        // 保存表格数据
//        saveTableReceiptInfo();
    }

    /**
     * 添加
     */
    @FXML
    @SneakyThrows
    public void add(){
        clearValue();
        create_date.setValue(localDate.toLocalDate());
        String no = createOrderNo(iAccountPrepaymentService.getMaxOrderNo());
        order_no.setText(no);
        createPeople(made_people);
        setControllerUse();
    }

    /**
     * 赋值
     */
    @FXML
    public void setBasicValue(AccountPrepayment order){
        clearValue();
        if(order != null){
            order_no.setUserData(order.getId());
            order_no.setText(order.getOrderNo());
            if(order.getCreateDate() != null){
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            made_people.setText(order.getMadePeople());
            customer_no.setText(order.getCustomerNo());
            customer_no_str.setText(order.getCustomerNoStr());
            audit.setText(order.getAudit());
            audit_str.setText(order.getAuditStr());
            last_update.setText(order.getLastUpdate());
            last_update_str.setText(order.getLastUpdateStr());

            payment_currency_one.getSelectionModel().select(order.getCurrency());
            payment_currency_two.getSelectionModel().select(order.getCurrencyTwo());
            payment_method.getSelectionModel().select(order.getPaymentType());
            process_people.getSelectionModel().select(order.getProcessPeople());
            process_people_str.setText(order.getProcessPeopleStr());
            remark.setText(order.getRemark());
            rate.setText(order.getRate());
            pay_money.setText(order.getPaymentMoney() == null?null:order.getPaymentMoney().toString());
            token_no.setText(order.getTokenNo());
            prepayment_money.setText(order.getPrepaymentMoney() == null?null:order.getPrepaymentMoney().toString());
            invoice_no.setText(order.getInvoiceNo());

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
            }
            showTableSelfInfo();
        }
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<AccountPrepayment> receiptList = iAccountPrepaymentService.listAccountPrepayment("",page, 1);
        if(receiptList != null && receiptList.size() >0){
            receiptList.forEach(p->setBasicValue(p));
            PageInfo<AccountPrepayment> pageInfo = new PageInfo<>(receiptList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
        }else{
            setBasicValue(null);
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
            setControllerDisable();
        }
    }

    /**
     * 删除数据
     */
    @FXML
    public  synchronized void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
                int rows = iAccountPrepaymentService.delete(Long.valueOf(order_no.getUserData().toString()));
                iAccountPrepaymentInfoService.deleteAccountPrepaymentInfoByParentId(order_no.getUserData().toString());
                returnMsg(rows);
                setMenuValue(1);    // 第一页数据
            }
        }
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = order_no.getUserData()==null?null:order_no.getUserData().toString();

        if(id != null && !"".equals(id)){
            AccountPrepayment order = iAccountPrepaymentService.selectByKey(Long.valueOf(id));
            if(order != null){
                setShiroControlYES();
                lastUpdatePeopel(last_update, last_update_str);
                lastUpdatePeopel(audit, audit_str);
                shiroUpdateData(true);
            }
        }else{
            alert_informationDialog("单据还暂未保存，无法审核！");
        }
    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool){
        String id = order_no.getUserData()==null?null:order_no.getUserData().toString();

        if(id != null && !"".equals(id)){
            AccountPrepayment goods = iAccountPrepaymentService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAudit(audit.getText());
            goods.setAuditStr(audit_str.getText());
            iAccountPrepaymentService.updateNotNull(goods);
            showTableSelfInfo();
        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel(){
        // 有效单据验证
        String id = order_no.getUserData()==null?null:order_no.getUserData().toString();

        if(id == null && "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }
        setShiroControlNO();
        lastUpdatePeopel(audit, audit_str);
        shiroUpdateData(false);
    }

    /**
     * 审核通过
     */
    private void setShiroControlYES(){
        shiro_cancel.setDisable(false);
        shiro_success.setDisable(true);
        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        menu_update.setDisable(false);
    }


    /**
     * 设置控件可用
     */
    public void setControllerUse(){
        setController(false);
        setMenuControlState(false);
    }

    /**
     * 设置控件禁用
     */
    public void setControllerDisable(){
        setController(true);
        setMenuControlState(true);
    }

    /**
     *  设置菜单控件状态
     * @param bool
     */
    private void setMenuControlState(Boolean bool){
        menu_clearAll.setDisable(bool);
        menu_commit.setDisable(bool);
        menu_delete.setDisable(bool);
    }


    /**
     * 设置控件状态
     */
    public void setController(Boolean bool){
        if(bool){
            NumberUtil.changeStatus(writestate,0);
        }else{
            NumberUtil.changeStatus(writestate,2);
        }
        order_no.setDisable(bool);
        create_date.setDisable(bool);
        customer_no.setDisable(bool);
        customer_no_str.setDisable(bool);
        payment_currency_one.setDisable(bool);
        payment_currency_two.setDisable(bool);
        payment_method.setDisable(bool);
        process_people.setDisable(bool);
        process_people_str.setDisable(bool);
        made_people.setDisable(true);
        remark.setDisable(bool);
        audit.setDisable(true);
        audit_str.setDisable(true);
        last_update.setDisable(true);
        last_update_str.setDisable(true);
        rate.setDisable(bool);
        pay_money.setDisable(bool);
        token_no.setDisable(bool);
        prepayment_money.setDisable(bool);
        invoice_no.setDisable(bool);

    }

    /**
     * 清除控件上的值
     */
    @FXML
    public void clearValue(){
        order_no.setUserData(null);
        order_no.clear();
        create_date.setValue(null);
        customer_no.clear();
        customer_no_str.clear();
        payment_currency_one.getSelectionModel().selectFirst();
        payment_currency_two.getSelectionModel().selectFirst();
        payment_method.getSelectionModel().selectFirst();
        process_people.getSelectionModel().selectFirst();
        process_people_str.clear();
        made_people.clear();
        remark.clear();
        audit.clear();
        audit_str.clear();
        last_update.clear();
        last_update_str.clear();
        rate.clear();
        pay_money.clear();
        token_no.clear();
        prepayment_money.clear();
        invoice_no.clear();



    }


    /**
     * @Description 打开窗口--供应商
     * @Author BlueSky
     * @Date 15:16 2019/4/12
     **/
    @FXML
    public void OpenSupplierQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountPrepaymentController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/supplier_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * @Description 更多预付款单据
     * @Author BlueSky
     * @Date 20:35 2019/4/10
     **/
    @FXML
    public void OpenMoreOrder() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountPrepaymentControllerMore", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_prepayment_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


}
