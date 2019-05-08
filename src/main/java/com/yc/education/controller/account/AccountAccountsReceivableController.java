package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.*;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.account.*;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.sale.ISaleGoodsService;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.DoubleStringConverter;
import lombok.SneakyThrows;
import lombok.val;
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
 * @Description 应收账款冲账
 * @Author BlueSky
 * @Date 2018-12-04 17:06
 */
@Controller
public class AccountAccountsReceivableController extends BaseController implements Initializable {

    @Autowired ICustomerService iCustomerService;                   // 客户
    @Autowired IAccountReceivableService iAccountReceivableService; // 应收账款冲账
    @Autowired IAccountReceivableFeeService iAccountReceivableFeeService; // 应收账款冲账
    @Autowired IAccountReceivableRushService iAccountReceivableRushService; // 应收账款冲账
    @Autowired IAccountSaleInvoiceService iAccountSaleInvoiceService; // 销项发票
    @Autowired ISaleGoodsService iSaleGoodsService; // 销货单
    @Autowired IAccountReceiptService iAccountReceiptService; // 收款单

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
    @FXML VBox import_out;           // 导出
    @FXML Label writestate;          // 待输入

    @FXML DatePicker rush_date;     // 冲账日期
    @FXML CheckBox  che_early_order; // 前期单据
    @FXML TextField early_balance;  // 前期余额
    @FXML public TextField customer_no;     // 客户编号
    @FXML public TextField customer_str;    // 客户描述
    @FXML TextField rush_no;        // 冲款编号

    @FXML ComboBox  currency;        // 币别
    @FXML ComboBox  rush_currency;   // 被冲单据币别
    @FXML TextField exchange_rate;  // 汇率
    @FXML ComboBox  receive_people;  // 收款人
    @FXML TextField receive_people_str; // 收款人描述
    @FXML TextField made_people;    // 制单人
    @FXML ComboBox  receive_method;  // 收款方式
    @FXML TextField receive_money;   // 收款金额
    @FXML TextField last_update;     // 最后修改人
    @FXML TextField last_update_str; // 最后修改人描述
    @FXML TextField audit;           // 审核人
    @FXML TextField audit_str;       // 审核人描述
    @FXML TextField remark;          // 备注

    @FXML DatePicker date_receipt_ben;   // 收款日期开始
    @FXML DatePicker date_receipt_end;   // 收款日期结束
    @FXML TextField account_total;       // 账款累计

    @FXML TableView tableview_rush;      // 冲账单据
    @FXML TableColumn col_id;            // id
    @FXML TableColumn col_nos;            // id
    @FXML TableColumn col_sale_invoice;   // 销项发票编号
    @FXML TableColumn col_invoice_no;     // 发票号码
    @FXML TableColumn col_bill_date;      // 账款日期
    @FXML TableColumn col_order_money;    // 单据总金额
    @FXML TableColumn col_receive_now;    // 本次应收
    @FXML TableColumn col_discount_now;   // 本次折让
    @FXML TableColumn col_rush_money;     // 已冲减金

    @FXML TableView tableview_fee;       // 收款方式及收费金额
    @FXML TableColumn col_ids;           // 订单id
    @FXML TableColumn col_no;           // no
    @FXML TableColumn col_receive_no;    // 收款单号
    @FXML TableColumn col_rush_can;      // 可冲金额
    @FXML TableColumn col_write_down;    // 本次冲减
    @FXML TableColumn col_note;          // 备注

    @FXML TableView tableview_info;       // 销项发票冲款明细
    @FXML TableColumn col_info_id;           // 订单id
    @FXML TableColumn col_info_nos;           // no
    @FXML TableColumn col_info_sale_invoice;    // 销项发票编号
    @FXML TableColumn col_info_invoice_no;      // 发票号码
    @FXML TableColumn col_info_bill_date;    // 账款日期
    @FXML TableColumn col_info_order_money;          // 单据总金额
    @FXML TableColumn col_info_discount_now;          // 本次折让
    @FXML TableColumn col_info_rush_money;          // 已冲减金
    @FXML TableColumn col_info_not_receive;          // 未收款金额

    @FXML TableView tableview_sale;       // 销货单冲货明细
    @FXML TableColumn col_sale_id;           // 订单id
    @FXML TableColumn col_sale_nos;           // no
    @FXML TableColumn col_sale_order;           // 销货单号
    @FXML TableColumn col_sale_order_money;      // 单据金额
    @FXML TableColumn col_sale_not_rush;            // 未冲金额
    @FXML TableColumn col_sale_rush_money;          // 已冲金额


    // 日期格式
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
    LocalDateTime localDate = LocalDateTime.now();
    Stage stage = new Stage();

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {

        setComboBox(7L, currency, 0);       // 币别
        setComboBox(7L, rush_currency, 0);       // 被冲单据币别
        loadEmployee(receive_people, 0);          // 收款人
        setComboBox(20L, receive_method, 0);       // 收款方式

        setControllerDisable();     // 设置控件禁用
//        setMenuValue(1);         // 加载第一条数据
        receive_people.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = receive_people.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    receive_people_str.setText(bus);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        // 初始化查询
        generalTabInvoice();
        generalTabSaleGoods();
        // 客户编号变更监听
        customer_no.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(customer_no.getText() != null && !"".equals(customer_no.getText())){
                    generalTabInvoice();
                    generalTabSaleGoods();
                }
            }
        });
    }

    /**
     * @Description 根据客户编号查询销项发票冲款明细
     * @Author BlueSky
     * @Date 16:14 2019/5/6
     **/
    public void generalTabInvoice(){
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            List<AccountSaleInvoice> invoiceList = iAccountSaleInvoiceService.listAccountSaleInvoiceByCustomer(customer_no.getText());
            int rows = 1;
            for (AccountSaleInvoice p : invoiceList) {
                p.setMoneyStr(p.getMoney().toString());
                p.setReceiveMoneyStr(p.getReceiveMoney().toString());
                p.setReduceMoneyStr(p.getMoney().subtract(p.getReceiveMoney()).toString());
                p.setInvoiceDateStr(df.format(p.getInvoiceDate()));
                p.setNo(rows++);
                p.setOrderCancelStr("0");   // 暂替本次折让
            }

            col_info_discount_now.setCellFactory(column -> EditCell.createStringEditCell());
            ObservableList<AccountSaleInvoice> data = FXCollections.observableArrayList(invoiceList);
            col_info_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_info_nos.setCellValueFactory(new PropertyValueFactory("no"));
            col_info_sale_invoice.setCellValueFactory(new PropertyValueFactory("orderNo"));
            col_info_invoice_no.setCellValueFactory(new PropertyValueFactory("invoiceNo"));
            col_info_bill_date.setCellValueFactory(new PropertyValueFactory("invoiceDateStr"));
            col_info_order_money.setCellValueFactory(new PropertyValueFactory("moneyStr"));
            col_info_discount_now.setCellValueFactory(new PropertyValueFactory("orderCancelStr")); // 暂替本次折让
            col_info_rush_money.setCellValueFactory(new PropertyValueFactory("receiveMoneyStr"));
            col_info_not_receive.setCellValueFactory(new PropertyValueFactory("reduceMoneyStr"));

            tableview_info.setEditable(true);
            tableview_info.setItems(data);
        }
    }

    /**
     * @Description 根据客户编号查询销货单冲货明细
     * @Author BlueSky
     * @Date 16:14 2019/5/6
     **/
    public void generalTabSaleGoods(){
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            List<SaleGoods> goodsList = iSaleGoodsService.listSaleGoodsByCustomer("",customer_no.getText());
            int rows = 1;
            for (SaleGoods p : goodsList) {
                p.setMoneyStr(p.getMoney().add(p.getMoneyTax()).toString());
                p.setReceiveMoneyStr(p.getReceiveMoney().toString());
                p.setReceiveNotStr(p.getMoney().add(p.getMoneyTax()).subtract(p.getReceiveMoney()).toString());
                p.setNo(rows++);
            }

            ObservableList<SaleGoods> data = FXCollections.observableArrayList(goodsList);
            col_sale_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_sale_nos.setCellValueFactory(new PropertyValueFactory("no"));
            col_sale_order.setCellValueFactory(new PropertyValueFactory("saleNo"));
            col_sale_order_money.setCellValueFactory(new PropertyValueFactory("moneyStr"));
            col_sale_not_rush.setCellValueFactory(new PropertyValueFactory("receiveNotStr"));
            col_sale_rush_money.setCellValueFactory(new PropertyValueFactory("receiveMoneyStr"));

            tableview_sale.setEditable(true);
            tableview_sale.setItems(data);
        }
    }

    /**
     * @Description 自动冲账
     * @Author BlueSky
     * @Date 10:34 2019/5/7
     **/
    public synchronized void autoRushAccounts(){
        //1.销售应收重算
        //    a)把所有的销货单里面货款金额和收款金额核算一遍，如有未收款完的销货单，用剩下的收款单余额进项冲减。。实际上就是把未审核的收款单做审核，然后把收款金额加到客户余额里面去。
        //2.发票冲账
        //    a)把所有的收款单的收款金额-销项发票金额。。实际上就是把收款单的余额 - 未冲销项发票金额
        // todo..
        List<AccountReceipt> receiptList = iAccountReceiptService.listAccountReceiptNotShiroOrBalance(customer_no.getText(),date_receipt_ben.getValue()==null?"":date_receipt_ben.getValue().toString(),date_receipt_end.getValue()==null?"":date_receipt_end.getValue().toString());
        for (AccountReceipt p : receiptList) {
            Customer customer = iCustomerService.getCustomer(p.getCustomerNo());
            BigDecimal oldMoney = customer.getUseableMoney();
            // 已经审核的收款不需要再次叠加客户余额
            if(p.getOrderAudit()){
                // 已审核的收款--剩余余额：1.冲销项发票
                // 销项发票冲账
                rushSaleInvoice(p.getId(),customer.getCustomerCode(),p.getBalance());
            }else{
                // 未审核的收款单：1.叠加客户余额、2.冲销项发票
                if(customer != null){
                    customer.setUseableMoney(customer.getUseableMoney().subtract(p.getMoney()));
                    iCustomerService.updateNotNull(customer);
                    p.setOrderAudit(true);
                    iAccountReceiptService.updateNotNull(p);
                    // 销项发票冲账
                    rushSaleInvoice(p.getId(),customer.getCustomerCode(),p.getBalance());
                }
            }
            // 冲销货单
            List<SaleGoods> saleGoodsList = iSaleGoodsService.listSaleGoodsByCustomer("1",p.getCustomerNo());
            customer.setUseableMoney(customer.getUseableMoney().subtract(oldMoney));
            for (SaleGoods k : saleGoodsList) {
                if(customer.getUseableMoney().compareTo(new BigDecimal("0")) < 0){
                    // 客户余额 + （货款+税款-已收货款）  ps:当余额有钱时余额时负数
                    BigDecimal money = customer.getUseableMoney().add((k.getMoney().add(k.getMoneyTax()).subtract(k.getReceiveMoney())));
                    if(money.compareTo(new BigDecimal("0")) <= 0){
                        k.setReceiveMoney(k.getReceiveMoney().add(k.getMoney().add(k.getMoneyTax())));
                        iSaleGoodsService.updateNotNull(k); //更新冲款金额
                        customer.setUseableMoney(money);
                        iCustomerService.updateNotNull(customer);   //更新余额
                    }else{
                        k.setReceiveMoney(k.getReceiveMoney().add(customer.getUseableMoney().abs()));
                        iSaleGoodsService.updateNotNull(k); //更新冲款金额
                        customer.setUseableMoney(new BigDecimal("0"));
                        iCustomerService.updateNotNull(customer);   //更新余额
                    }
                }
            }
        }
        alert_informationDialog("冲账成功！");
        generalTabInvoice();
        generalTabSaleGoods();
    }

//    /**
//     * @Description 自动冲账
//     * @Author BlueSky
//     * @Date 10:16 2019/4/9
//     **/
////    @FXML
////    public void autoRushAccounts(){
////        List<AccountReceivable> receivableList = tableview_rush.getItems() == null?null:tableview_rush.getItems();
////        if(receivableList != null && receivableList.size() > 0){
////            receivableList.forEach(p->{
////                if(p.getId() != null && p.getId() != 0L){
////                    AccountReceivable receivable = iAccountReceivableService.selectByKey(p.getId());
////                    if(receivable != null && !receivable.getOrderAudit() && receivable.getReceiveMoney() != null){
////                        Customer customer = iCustomerService.getCustomer(receivable.getCustomerNo());
////                        if(customer != null){
////                            customer.setUseableMoney(customer.getUseableMoney().subtract(receivable.getReceiveMoney()));
////                            iCustomerService.updateNotNull(customer);//修改客户可用余额
////                            receivable.setOrderAudit(true);
////                            iAccountReceivableService.updateNotNull(receivable); // 单据通过审核
////                        }
////                    }
////                }
////            });
////            alert_informationDialog("冲账成功！");
////            tableview_rush.setItems(null); //刷新列表数据
////            showFee();
////        }
////    }

    /**
     * @Author BlueSky
     * @Description //TODO 查找 -- 冲账单据
     * @Date 20:14 2019/4/8
     **/
    @FXML
    public void findBtn(){
        String bentime = date_receipt_ben.getValue() ==null?"":date_receipt_ben.getValue().toString();
        String endtime = date_receipt_end.getValue() ==null?"":date_receipt_end.getValue().toString();
        List<AccountReceivable> accountReceiptList = iAccountReceivableService.listAccountReceivableByTime(bentime,endtime);
        if(accountReceiptList != null && accountReceiptList.size() >0){
            int rows = 1;
            for (AccountReceivable p : accountReceiptList) {
                p.setNo(rows++);
                p.setRushDateStr(df.format(p.getRushDate()));
                p.setCanRushStr(p.getCanRush()==null?"0.00":p.getCanRush().toString());
                p.setReceiveMoneyStr(p.getReceiveMoney()==null?"0.00":p.getReceiveMoney().toString());
            }
            ObservableList<AccountReceivable> data = FXCollections.observableArrayList(accountReceiptList);
            col_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_nos.setCellValueFactory(new PropertyValueFactory("no"));
            col_sale_invoice.setCellValueFactory(new PropertyValueFactory(""));
            col_invoice_no.setCellValueFactory(new PropertyValueFactory(""));
            col_bill_date.setCellValueFactory(new PropertyValueFactory("rushDateStr"));
            col_order_money.setCellValueFactory(new PropertyValueFactory(""));
            col_receive_now.setCellValueFactory(new PropertyValueFactory<AccountReceivable,BigDecimalStringConverter>("canRushStr"));
            col_discount_now.setCellValueFactory(new PropertyValueFactory(""));
            col_rush_money.setCellValueFactory(new PropertyValueFactory<AccountReceivable,BigDecimalStringConverter>("receiveMoneyStr"));

            tableview_rush.setItems(data);
        }else{
            alert_informationDialog("暂无可冲账数据");
        }
    }

    /**
     * 收费详情 view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfFeeTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addFeeRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfFee();
        }
    }

    /**
     * 行添加 收费详情
     */
    public void addFeeRow() {

        ObservableList<AccountReceivableFeeProperty> list = tableview_fee.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountReceivableFeeProperty("", "", "", ""));
        tableview_fee.setItems(list);
    }


    /**
     * 行删除 收费详情
     */
    private void deleteRowOfFee(){
        // 取得当前行的数据
        try {
            TablePosition pos = (TablePosition) tableview_fee.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            AccountReceivableFeeProperty property = (AccountReceivableFeeProperty)tableview_fee.getItems().get(index);
            if(property.getId() != null && property.getId() != 0L){
                int rows = iAccountReceivableFeeService.delete(property.getId());
                returnMsg(rows);
            }
        }catch (Exception e){
            alert_informationDialog("请选择需要删除的行！");
            e.printStackTrace();
        }
    }

    /**
     * 保存 收费详情 tableview数据
     */
    @SneakyThrows(Exception.class)
    private void saveTableviewFee(){
        Object object = rush_no.getUserData();
        if(object != null && !"".equals(object.toString()) && tableview_fee.getItems() != null){
            int tableSize = tableview_fee.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                AccountReceivableFeeProperty property = null;
                if(tableview_fee.getItems().get(i) != null){
                    property = (AccountReceivableFeeProperty)tableview_fee.getItems().get(i);
                }
                AccountReceivableFee product = new AccountReceivableFee();
                if(property.getReceiveNo() != null && !"".equals(property.getReceiveNo())){
                    product.setReceiveNo(property.getReceiveNo());
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                if(property.getRushMoneyCan() != null && !"".equals(property.getRushMoneyCan())){
                    product.setRushMoneyCan(new BigDecimal(property.getRushMoneyCan()));
                }
                if(property.getRushMoneyNow() != null && !"".equals(property.getRushMoneyNow())){
                    product.setRushMoneyNow(new BigDecimal(property.getRushMoneyNow()));
                }
                product.setOtherid(Long.valueOf(object.toString()));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iAccountReceivableFeeService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountReceivableFeeService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            showFee();
        }
    }

    /**
     * 冲账单据 view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfRushTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addRushRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfRush();
        }
    }

    /**
     * 行添加 冲账单据
     */
    public void addRushRow() {

        ObservableList<AccountReceivableRushProperty> list = tableview_rush.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountReceivableRushProperty("", "", "", "","", "", ""));
        tableview_rush.setItems(list);
    }


    /**
     * 行删除 冲账单据
     */
    private void deleteRowOfRush(){
        // 取得当前行的数据
        try {
            TablePosition pos = (TablePosition) tableview_rush.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            AccountReceivable property = (AccountReceivable)tableview_rush.getItems().get(index);
            tableview_rush.getItems().remove(property);
        }catch (Exception e){
            alert_informationDialog("请选择需要删除的行！");
            e.printStackTrace();
        }
    }

    /**
     * 保存 冲账单据 tableview数据
     */
    @SneakyThrows(Exception.class)
    private void saveTableviewRush(){
        Object object = rush_no.getUserData();
        if(object != null && !"".equals(object)){
            int tableSize = tableview_rush.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                AccountReceivableRushProperty property = null;
                if(tableview_rush.getItems().get(i) != null){
                    property = (AccountReceivableRushProperty)tableview_rush.getItems().get(i);
                }
                AccountReceivableRush product = new AccountReceivableRush();
                try {
                    if(property.getAccountDate() != null && !"".equals(property.getAccountDate())){
                        product.setAccountDate(df.parse(property.getAccountDate()));
                    }
                }catch (Exception e){e.printStackTrace();}
                if(property.getDiscount() != null && !"".equals(property.getDiscount())){
                    product.setDiscount(property.getDiscount());
                }
                if(property.getInvoiceNo() != null && !"".equals(property.getInvoiceNo())){
                    product.setInvoiceNo(property.getInvoiceNo());
                }
                if(property.getReceive() != null && !"".equals(property.getReceive())){
                    product.setReceive(new BigDecimal(property.getReceive()));
                }
                if(property.getRushMoney() != null && !"".equals(property.getRushMoney())){
                    product.setRushMoney(new BigDecimal(property.getRushMoney()));
                }
                if(property.getSaleNo() != null && !"".equals(property.getSaleNo())){
                    product.setSaleNo(property.getSaleNo());
                }
                if(property.getTotalMoney() != null && !"".equals(property.getTotalMoney())){
                    product.setTotalMoney(new BigDecimal(property.getTotalMoney()));
                }
                product.setOtherid(Long.valueOf(object.toString()));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iAccountReceivableRushService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountReceivableRushService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 收款方式及收费金额 tableview数据展示
     */
    @SneakyThrows
    public void showFee(){
        // 查询最近30 条冲账记录
        List<AccountReceivable> accountReceivableList = iAccountReceivableService.listAccountReceivableByNum(30);

        int rows = 1;
        for (AccountReceivable p : accountReceivableList) {
            p.setNo(rows++);
        }

//        col_receive_no.setCellFactory(column -> EditCell.createStringEditCell());
//        col_rush_can.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
//        col_write_down.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
//        col_note.setCellFactory(column -> EditCell.createStringEditCell());

        ObservableList<AccountReceivable> data = FXCollections.observableArrayList(accountReceivableList);
        col_ids.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_receive_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_rush_can.setCellValueFactory(new PropertyValueFactory<AccountReceivable, BigDecimal>("canRush"));
        col_write_down.setCellValueFactory(new PropertyValueFactory<AccountReceivable, BigDecimal>("receiveMoney"));
        col_note.setCellValueFactory(new PropertyValueFactory("remark"));

        tableview_fee.setEditable(true);
        tableview_fee.setItems(data);
    }

    /**
     * 审核失败
     */
    @FXML
    @SneakyThrows
    public void setShiroFaile(){
        if(rush_no.getUserData() != null){
            Object orderno = rush_no.getUserData();
            if(orderno != null){
                AccountReceivable accountReceivable = iAccountReceivableService.selectByKey(Long.valueOf(orderno.toString()));
                if(accountReceivable != null){
                    accountReceivable.setOrderAudit(false);
                    lastUpdatePeopel(audit, audit_str);
                    accountReceivable.setAudit(audit.getText());
                    accountReceivable.setAuditStr(audit_str.getText());
                    int rows = iAccountReceivableService.updateNotNull(accountReceivable);
                    if(rows>0){
                        shiro_success.setDisable(false);
                        setControllerDisable();
                        menu_update.setDisable(false);
                    }
                    returnMsg(rows);
                }
            }
        }else{
            alert_informationDialog("请先创建单据！");
        }
    }

    /**
     * 审核通过
     */
    @FXML
    @SneakyThrows
    public void setShiroSuccess(){
        if(rush_no.getUserData() != null){
            Object orderno = rush_no.getUserData();
            if(orderno != null){
                AccountReceivable accountReceivable = iAccountReceivableService.selectByKey(Long.valueOf(orderno.toString()));
                if(accountReceivable != null){
                    accountReceivable.setOrderAudit(true);
                    lastUpdatePeopel(audit, audit_str);
                    accountReceivable.setAudit(audit.getText());
                    accountReceivable.setAuditStr(audit_str.getText());
                    int rows = iAccountReceivableService.updateNotNull(accountReceivable);
                    if(rows>0){
                        shiro_success.setDisable(true);
                        setControllerDisable();
                        menu_update.setDisable(true);
                    }
                    // 审核通过 修改客户可用余额
                    Customer customer = iCustomerService.getCustomer(accountReceivable.getCustomerNo());
                    if(customer != null){
                        customer.setUseableMoney(customer.getUseableMoney().subtract(accountReceivable.getReceiveMoney()));
                        iCustomerService.updateNotNull(customer);
                    }
                    returnMsg(rows);
                }
            }
        }else{
            alert_informationDialog("请先创建单据！");
        }
    }

    /**
     * 删除单据
     */
    @FXML
    @SneakyThrows
    public synchronized  void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(rush_no.getUserData() != null){
                Object orderid = rush_no.getUserData();
                if(orderid != null){
                    int rows = iAccountReceivableService.delete(Long.valueOf(orderid.toString()));
                    iAccountReceivableFeeService.deleteAccountReceivableFeeByParentId(orderid.toString());
                    iAccountReceivableRushService.deleteAccountReceivableRushByParentId(orderid.toString());
                    returnMsg(rows);
                    setMenuValue(1);
                }
            }
        }
    }

    /**
     * 添加
     */
    @FXML
    @SneakyThrows
    public void add(){
        clearValue();
        rush_date.setValue(localDate.toLocalDate());
        String no = createOrderNo(iAccountReceivableService.getMaxOrderNo());
        rush_no.setText(no);
        createPeople(made_people);
        setControllerUse();
    }


    /**
     * 给翻页菜单赋值
     * @param page
     */
    @SneakyThrows
    private void setMenuValue(int page){
        List<AccountReceivable> receivableList = iAccountReceivableService.listAccountReceivable("",page, 1);
        if(receivableList != null && receivableList.size() >0){
            receivableList.forEach(p->setBasicValue(p));
            PageInfo<AccountReceivable> pageInfo = new PageInfo<>(receivableList);
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
     * 保存数据
     */
    @FXML
    @SneakyThrows
    public synchronized  void save(){
        setMenuControlState(true);
        // 最后修改人
        lastUpdatePeopel(last_update, last_update_str);
        AccountReceivable order = new AccountReceivable();
        if(rush_date.getValue() != null){
            order.setRushDate(df.parse(rush_date.getValue().toString()));
        }
        order.setEarlyOrder(che_early_order.isSelected());
        order.setEarlyBalance(early_balance.getText());
        order.setCustomerNo(customer_no.getText());
        order.setCustomerStr(customer_str.getText());
        order.setOrderNo(rush_no.getText());
        if(currency.getSelectionModel().getSelectedItem() != null){
            order.setCurrency(currency.getSelectionModel().getSelectedIndex());
        }
        if(rush_currency.getSelectionModel().getSelectedItem() != null){
            order.setRushCurrency(rush_currency.getSelectionModel().getSelectedIndex());
        }
        order.setExchangeRate(exchange_rate.getText());
        if(receive_people.getSelectionModel().getSelectedItem() != null){
            try {
                order.setReceivePeople(Long.valueOf(receive_people.getSelectionModel().getSelectedIndex()+""));
            }catch (Exception e){e.printStackTrace();}
        }
        order.setReceivePeopleStr(receive_people_str.getText());
        order.setMadePeople(made_people.getText());
        if(receive_method.getSelectionModel().getSelectedItem() != null){
            order.setReceiveMethod(receive_method.getSelectionModel().getSelectedIndex());
        }
        if(receive_money.getText()!=null && !"".equals(receive_money.getText())){
            order.setReceiveMoney(new BigDecimal(receive_money.getText()));
        }
        order.setLastUpdate(last_update.getText());
        order.setLastUpdateStr(last_update_str.getText());
        order.setAudit(audit.getText());
        order.setAuditStr(audit_str.getText());
        order.setRemark(remark.getText());


        shiro_success.setDisable(false);

        val id = rush_no.getUserData();
        if(id!=null && !"".equals(id)){
            order.setId(Long.valueOf(id.toString()));
            val rows = iAccountReceivableService.updateNotNull(order);
            alertProcessResult(rows);
        }else{
            Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
            if(customer != null){
                order.setCanRush(customer.getUseableMoney());
            }
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            val rows = iAccountReceivableService.save(order);
            alertProcessResult(rows);
            rush_no.setUserData(order.getId());
        }
        setControllerDisable();
        showFee();
//        saveTableviewFee();     // 保存列表费用详情
//        saveTableviewRush();    // 保存冲账单据
    }

    /**
     * 赋值
     * @param order
     */
    @SneakyThrows
    public void setBasicValue(AccountReceivable order){
        clearValue();
        if(order == null){
            return;
        }
        rush_no.setUserData(order.getId());
        if(order.getRushDate()!=null){
            rush_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getRushDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(order.getEarlyOrder() != null){
            che_early_order.setSelected(order.getEarlyOrder());
        }
        early_balance.setText(order.getEarlyBalance()==null?"0":order.getEarlyBalance());
        customer_no.setText(order.getCustomerNo());
        customer_str.setText(order.getCustomerStr());
        rush_no.setText(order.getOrderNo());

        currency.getSelectionModel().select(order.getCurrency()+0);
        rush_currency.getSelectionModel().select(order.getRushCurrency()+0);
        exchange_rate.setText(order.getExchangeRate());
        if (order.getReceivePeople() != null){
            receive_people.getSelectionModel().select(Integer.valueOf(order.getReceivePeople().toString())+0);
        }
        receive_people_str.setText(order.getReceivePeopleStr());
        made_people.setText(order.getMadePeople());
        receive_method.getSelectionModel().select(order.getReceiveMethod()+0);
        if(order.getReceiveMoney() != null){
            receive_money.setText(order.getReceiveMoney().toString());
        }
        last_update.setText(order.getLastUpdate());
        last_update_str.setText(order.getLastUpdateStr());
        audit.setText(order.getAudit());
        audit_str.setText(order.getAuditStr());
        remark.setText(order.getRemark());

        shiro_success.setDisable(false);
        menu_update.setDisable(false);
        if(order.getOrderAudit() != null){
            if(order.getOrderAudit()){
                shiro_success.setDisable(true);
                menu_update.setDisable(true);
            }else{
                menu_update.setDisable(false);
            }
        }

        showFee();  // 收费金额列表
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
        rush_date.setDisable(bool);
        che_early_order.setDisable(bool);
        early_balance.setDisable(bool);
        customer_no.setDisable(bool);
        customer_str.setDisable(bool);
        currency.setDisable(bool);
        rush_currency.setDisable(bool);
        exchange_rate.setDisable(bool);
        receive_method.setDisable(bool);
        receive_money.setDisable(bool);
        receive_people.setDisable(bool);
        receive_people_str.setDisable(bool);
        remark.setDisable(bool);

//        tableview_rush.setDisable(bool);
//        tableview_fee.setDisable(bool);
    }

    /**
     * 清除控件值
     */
    public void clearValue(){
        rush_no.setUserData("");
        rush_date.setValue(null);
        che_early_order.setSelected(false);
        early_balance.clear();
        customer_no.clear();
        customer_str.clear();
        rush_no.clear();
        currency.getSelectionModel().selectFirst();
        rush_currency.getSelectionModel().selectFirst();
        exchange_rate.clear();
        receive_people.getSelectionModel().selectFirst();
        receive_people_str.clear();
        made_people.clear();
        receive_people.getSelectionModel().selectFirst();
        receive_people_str.clear();
        receive_method.getSelectionModel().selectFirst();
        receive_money.clear();
        last_update.clear();
        last_update_str.clear();
        audit.clear();
        audit_str.clear();
        remark.clear();

//        tableview_fee.setItems(null);
        tableview_rush.setItems(null);
    }


    /**
     * 打开订单查询窗口
     */
    @FXML
    public void OpenMiniQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();

        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("OrderAccountsReceivableControllerNo", this);
        pane.getChildren().setAll(loader.load("/fxml/account/order_accounts_receivable_query_mini.fxml"));
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
        StageManager.CONTROLLER.put("OrderAccountsReceivableController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
    
}
