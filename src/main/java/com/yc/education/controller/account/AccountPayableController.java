package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.*;
import com.yc.education.service.account.IAccountInputInvoiceInfoService;
import com.yc.education.service.account.IAccountInputInvoiceService;
import com.yc.education.service.account.IAccountPayableInfoService;
import com.yc.education.service.account.IAccountPayableService;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @Description  应付账款冲账
 * @Author BlueSky
 * @Date 2019-01-22 10:54
 */
@Controller
public class AccountPayableController extends BaseController implements Initializable {

    @Autowired IAccountPayableService iAccountPayableService;      // 应付账款冲账
    @Autowired IAccountPayableInfoService iAccountPayableInfoService;  // 应付账款冲账单据详情
    @Autowired IAccountInputInvoiceService iAccountInputInvoiceService;      // 进项发票
    @Autowired IAccountInputInvoiceInfoService iAccountInputInvoiceInfoService;  // 进项发票详情

    // 日期格式
    LocalDateTime localDate = LocalDateTime.now();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
    static SpringFxmlLoader loader = new SpringFxmlLoader();
    Stage stage = new Stage();
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
//    @FXML VBox import_out;           // 导出
    @FXML Label writestate;          // 待输入

    @FXML CheckBox  che_early;            //前期
    @FXML DatePicker  rush_date;          //冲账日期
    @FXML public TextField supplier_no;         //客户编号
    @FXML public TextField supplier_no_str;     //客户编号描述
    @FXML TextField  order_no;            //冲账编号

    @FXML ComboBox  currency;             //币别
    @FXML ComboBox  currency_two;         //被冲单据币别
    @FXML TextField  rate;                  //汇率
    @FXML ComboBox  receipt;              //经办人
    @FXML TextField  receipt_str;         //经办人描述
    @FXML TextField  made_people;         //制单人
    @FXML TextField  last_update;         //最后修改人
    @FXML TextField  last_update_str;     //最后修改人描述
    @FXML TextField  audit;                //审核人
    @FXML TextField  audit_str;           //审核人描述
    @FXML TextField  remark;              //备注

    @FXML TableView  tableview_fee;      // 收款方式及收费金额
    @FXML TableColumn  cols_id;
    @FXML TableColumn  cols_no;                  //序号
    @FXML TableColumn  cols_payment_type;        //付款方式
    @FXML TableColumn  cols_payment_money;       //付款金额
    @FXML TableColumn  cols_invoice_no;          //发票号码
    @FXML TableColumn  cols_supplier;            //供应商
    @FXML TableColumn  cols_addtime;             //时间
    @FXML TableColumn  cols_remark;              //备注

    @FXML TableView  tableview_order;      // 冲账单据
    @FXML TableColumn  col_id;
    @FXML TableColumn  col_no;
    @FXML TableColumn  col_invoice_enter;        //进项发票
    @FXML TableColumn  col_invoice_no;           //发票号码
    @FXML TableColumn  col_order_date;           //账款日期
    @FXML TableColumn  col_customer;             //客户
    @FXML TableColumn  col_carete_date;          //开票日期
    @FXML TableColumn  col_money;          //开票金额
    @FXML TableColumn  col_remark;               //备注

    @FXML TextField  account_total;             //费用表格中的账款累计

    @FXML Button btn_invoice;
    @FXML Button btn_auto;
    @FXML Button btn_verify;
    @FXML DatePicker date_receipt_ben;
    @FXML DatePicker date_receipt_end;

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        setControllerDisable();
        setComboBox(7L, currency, 0); // 币别
        setComboBox(7L, currency_two, 0); // 被冲单据币别
        loadEmployee(receipt,0); // 收款人
        setMenuValue(1);    // 第一页数据
        receipt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = receipt.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    receipt_str.setText(bus);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * @Description 按发票载入单据
     * @Author BlueSky
     * @Date 11:55 2019/4/12
     **/
    @FXML
    public void btnByInvoice(){
        String dateBen = date_receipt_ben.getValue()==null?"":date_receipt_ben.getValue().toString();
        String dateEnd = date_receipt_end.getValue()==null?"":date_receipt_end.getValue().toString();
        showOrderTable(dateBen,dateEnd);
    }

    /**
     * @Description 自动冲账
     * @Author BlueSky
     * @Date 11:56 2019/4/12
     **/
    @FXML
    public void btnAutoRush(){
        List<AccountPayableInfoProperty> invoiceList = tableview_order.getItems() == null?null:tableview_order.getItems();
        if(invoiceList != null && invoiceList.size() > 0){
            invoiceList.forEach(p->{
                if(p.getId() != null && p.getId() != 0L){
                    AccountInputInvoice invoice = iAccountInputInvoiceService.selectByKey(p.getId());
                    if(invoice != null && !invoice.getOrderAudit()){
                        invoice.setOrderAudit(true);
                        iAccountInputInvoiceService.updateNotNull(invoice); // 单据通过审核
                    }
                }
            });
            alert_informationDialog("冲账成功！");
        }
        save(); //保存数据
    }

    /**
     * @Description 冲账校验
     * @Author BlueSky
     * @Date 11:56 2019/4/12
     **/
    @FXML
    public void btnRushVerify(){
        showOrderTable("","");
    }

    /**
     * @Description 加载未冲账单数据
     * @Author BlueSky
     * @Date 17:24 2019/4/12
     **/
    private void showOrderTable(String dateBen,String dateEnd){
        List<AccountInputInvoice> invoiceList = iAccountInputInvoiceService.listAccountInputInvoiceByDate(dateBen,dateEnd);
        List<AccountPayableInfoProperty> propertyList = new ArrayList<>();
        int rows = 1;
        BigDecimal totalMoney = new BigDecimal("0.00");
        for (AccountInputInvoice p : invoiceList) {
            if(p.getMoney() != null){
                totalMoney = totalMoney.add(p.getMoney());
            }
            p.setCreateDateStr(df.format(p.getCreateDate()));
            p.setInvoiceDateStr(df.format(p.getInvoiceDate()));
            propertyList.add(new AccountPayableInfoProperty(p.getId(),rows++,p.getInvoiceType(),p.getInvoceNo(),p.getCreateDateStr(),p.getSupplierNoStr(),p.getInvoiceDateStr(),p.getRemark(),p.getMoney()));
        }
        account_total.setText(totalMoney.toString());

        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_invoice_enter.setCellValueFactory(new PropertyValueFactory("interInvoice"));
        col_invoice_no.setCellValueFactory(new PropertyValueFactory("invoiceNo"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("accountDate"));
        col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        col_carete_date.setCellValueFactory(new PropertyValueFactory("invoiceDate"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableview_order.setItems(FXCollections.observableArrayList(propertyList));
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = order_no.getUserData()==null?null:order_no.getUserData().toString();

        if(id != null && !"".equals(id)){
            AccountPayable order = iAccountPayableService.selectByKey(Long.valueOf(id));
            setShiroControlYES();
            lastUpdatePeopel(last_update, last_update_str);
            lastUpdatePeopel(audit, audit_str);
            shiroUpdateData(true);
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
            AccountPayable goods = iAccountPayableService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAudit(audit.getText());
            goods.setAuditStr(audit_str.getText());
            iAccountPayableService.updateNotNull(goods);
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
     * 收款单明细 view
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
            showTableFeeInfo();
        }
    }

    /**
     * 删除产 收款单明细
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tableview_fee.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tableview_fee.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                AccountPayableInfoProperty property = (AccountPayableInfoProperty)tableview_fee.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iAccountPayableInfoService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<AccountPayableInfoProperty> dataProperty = tableview_fee.getItems();
                final ObservableList<AccountPayableInfoProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                tableview_fee.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加 收款单明细 行
     */
    public void addProductRow() {

        ObservableList<AccountPayableInfoProperty> list = tableview_fee.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountPayableInfoProperty(  "", "", "", "", "", ""));
        tableview_fee.setItems(list);
    }


    /**
     * 删除数据
     */
    @FXML
    public synchronized  void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
                int rows = iAccountPayableService.delete(Long.valueOf(order_no.getUserData().toString()));
                iAccountPayableInfoService.deleteAccountPayableInfoByParentId(order_no.getUserData().toString());
                returnMsg(rows);
                setMenuValue(1);    // 第一页数据
            }
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
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<AccountPayable> receiptList = iAccountPayableService.listAccountsPayable("",page, 1);
        if(receiptList != null && receiptList.size() >0){
            receiptList.forEach(p->setBasicValue(p));
            PageInfo<AccountPayable> pageInfo = new PageInfo<>(receiptList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
        }else{
            setBasicValue(null);
        }
    }

    /**
     * 赋值
     */
    @FXML
    @SneakyThrows
    public void setBasicValue(AccountPayable order){
        clearValue();
        if(order != null){
            order_no.setUserData(order.getId());
            order_no.setText(order.getOrderNo());
            if(order.getRushDate() != null){
                rush_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getRushDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            made_people.setText(order.getMadePoeple());
            supplier_no.setText(order.getSupplierNo());
            supplier_no_str.setText(order.getSupplierNoStr());
            audit.setText(order.getAudit());
            audit_str.setText(order.getAuditStr());
            last_update.setText(order.getLastUpdate());
            last_update_str.setText(order.getLastUpdateStr());

            currency.getSelectionModel().select(order.getCurrency());
            currency_two.getSelectionModel().select(order.getRushCurrency());
            rate.setText(order.getExchangeRate());
            remark.setText(order.getRemark());
            receipt.getSelectionModel().select(order.getProcessPeople());
            receipt_str.setText(order.getProcessPeopleStr());

            che_early.setSelected(order.getEarlyOrder());

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
            showTableFeeInfo();
        }
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
        che_early.setDisable(bool);
        rush_date.setDisable(bool);
        supplier_no.setDisable(bool);
        supplier_no_str.setDisable(bool);
        order_no.setDisable(bool);

        currency.setDisable(bool);
        currency_two.setDisable(bool);
        rate.setDisable(bool);
        receipt.setDisable(bool);
        receipt_str.setDisable(bool);
        made_people.setDisable(true);
        last_update.setDisable(true);
        last_update_str.setDisable(true);
        audit.setDisable(true);
        audit_str.setDisable(true);
        remark.setDisable(bool);

        btn_invoice.setDisable(bool);
        btn_verify.setDisable(bool);
        btn_auto.setDisable(bool);

        account_total.setDisable(bool);

    }

    /**
     * 保存 提交数据
     */
    @FXML
    @SneakyThrows
    public synchronized  void save(){
        lastUpdatePeopel(last_update, last_update_str);
        AccountPayable order = new AccountPayable();
        order.setId(order_no.getUserData()==null?null:Long.valueOf(order_no.getUserData().toString()));
        order.setEarlyOrder(che_early.isSelected());
        order.setRushDate(rush_date.getValue() == null?null:df.parse(rush_date.getValue().toString()));
        order.setSupplierNo(supplier_no.getText());
        order.setSupplierNoStr(supplier_no_str.getText());
        order.setOrderNo(order_no.getText());
        order.setCurrency(currency.getSelectionModel().getSelectedItem() == null ? null:currency.getSelectionModel().getSelectedItem().toString());
        order.setRushCurrency(currency_two.getSelectionModel().getSelectedItem() == null ? null:currency_two.getSelectionModel().getSelectedItem().toString());
        order.setExchangeRate(rate.getText());
        order.setProcessPeople(receipt.getSelectionModel().getSelectedItem() == null ? null:receipt.getSelectionModel().getSelectedItem().toString());
        order.setProcessPeopleStr(receipt_str.getText());
        order.setMadePoeple(made_people.getText());
        order.setLastUpdate(last_update.getText());
        order.setLastUpdateStr(last_update_str.getText());
        order.setAudit(audit.getText());
        order.setAuditStr(audit_str.getText());
        order.setRemark(remark.getText());
        order.setMoney("".equals(account_total.getText())?new BigDecimal("0.00"):new BigDecimal(account_total.getText()));

        if(order.getId() != null){
            // 修改
            int rows = iAccountPayableService.updateNotNull(order);
            returnMsg(rows);
            setControllerDisable();
        }else{
            order.setAddtime(new Date());
            // 保存
            int rows = iAccountPayableService.save(order);
            order_no.setUserData(order.getId());
            returnMsg(rows);
            setControllerDisable();
        }

        // 保存表格数据
        saveTableviewOrder();
    }

    /**
     * 保存表格冲账单据
     */
    @SneakyThrows
    private void saveTableviewOrder(){
        String orderid = order_no.getUserData() == null?null:order_no.getUserData().toString();
        if(orderid != null && !"".equals(orderid) && tableview_order.getItems() != null){
            for (int i = 0; i < tableview_order.getItems().size(); i++) {
                AccountPayableInfoProperty property = null;
                if(tableview_order.getItems().get(i) != null){
                    property = (AccountPayableInfoProperty)tableview_order.getItems().get(i);
                }
                AccountPayableInfo product = new AccountPayableInfo();
                if(property.getInvoiceNo() != null && !"".equals(property.getInvoiceNo())){
                    product.setInvoiceNo(property.getInvoiceNo());
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                // 账款日期
                if(property.getAccountDate() != null && !"".equals(property.getAccountDate())){
                    product.setCreatetime(df.parse(property.getAccountDate()));
                }
                if(property.getCustomer() != null && !"".equals(property.getCustomer())){
                    product.setSupplier(property.getCustomer());
                }
                if(property.getInterInvoice() != null && !"".equals(property.getInterInvoice())){
                    product.setPaymentMethod(property.getInterInvoice());
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }

                product.setOtherid(Long.valueOf(orderid));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iAccountPayableInfoService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountPayableInfoService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            showTableFeeInfo();
        }
    }

    /**
     * 加载收款单明细明细
     */
    public void showTableFeeInfo(){

        List<AccountPayable> payableList = iAccountPayableService.listAccountsPayableByNum(30);
        int rows = 1;
        for (AccountPayable p : payableList) {
            p.setCreateDateStr(df.format(p.getRushDate()));
            p.setNo(rows++);
        }

        cols_id.setCellValueFactory(new PropertyValueFactory("id"));
        cols_no.setCellValueFactory(new PropertyValueFactory("no"));
        cols_payment_money.setCellValueFactory(new PropertyValueFactory("money"));
        cols_supplier.setCellValueFactory(new PropertyValueFactory("supplierNoStr"));
        cols_addtime.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        cols_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableview_fee.setItems(FXCollections.observableArrayList(payableList));
    }



    /**
     * 新增
     */
    public void add(){
        clearValue();
        rush_date.setValue(localDate.toLocalDate());
        order_no.setText(createOrderNo(iAccountPayableService.getMaxOrderNo()));
        createPeople(made_people);
        setControllerUse();
    }

    /**
     * 清除控件数据
     */
    @FXML
    public void clearValue(){
        che_early.setSelected(false);
        rush_date.setValue(null);
        supplier_no.clear();
        supplier_no_str.clear();
        order_no.clear();
        order_no.setUserData(null);

        currency.getSelectionModel().selectFirst();
        currency_two.getSelectionModel().selectFirst();
        rate.clear();
        receipt.getSelectionModel().selectFirst();
        receipt_str.clear();
        made_people.clear();
        last_update.clear();
        last_update_str.clear();
        audit.clear();
        audit_str.clear();
        remark.clear();
        account_total.setText("0.00");
        tableview_order.setItems(null);

    }


    /**
     * @Description 打开窗口 -- 供应商
     * @Author BlueSky
     * @Date 15:16 2019/4/12
     **/
    @FXML
    public void OpenSupplierQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountPayableControllerSupplier", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/supplier_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 查询更多 应付账款冲账
     */
    @FXML
    public void moreOrder(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountPayableControllerMore", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_accounts_payable_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }





}
