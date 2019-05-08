package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.*;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.account.IAccountReceiptInfoService;
import com.yc.education.service.account.IAccountReceiptService;
import com.yc.education.service.account.IAccountSaleInvoiceService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.sale.ISaleGoodsService;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.SneakyThrows;
import org.omg.CORBA.ORB;
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
 * @Description 收款单
 * @Author BlueSky
 * @Date 2019-01-07 10:18
 */
@Controller
public class AccountReceiptController extends BaseController implements Initializable {

    @Autowired ICustomerService iCustomerService;                           //客户
    @Autowired IAccountReceiptService iAccountReceiptService;               // 收款单
    @Autowired IAccountReceiptInfoService iAccountReceiptInfoService;       // 收款单详情
    @Autowired ISaleGoodsService iSaleGoodsService;                         //销货单
    @Autowired IAccountSaleInvoiceService iAccountSaleInvoiceService;       // 销项发票
    // 日期格式
    LocalDateTime localDate = LocalDateTime.now();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
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
    @FXML Label writestate;// 待输入

    @FXML TextField order_no;           // 单号
    @FXML DatePicker receive_date;        // 收款日期
    @FXML public TextField customer_no;            // 客户编号
    @FXML public TextField customer_no_str;        // 客户姓名
    @FXML public TextField customer_no_ben;        // 客户编号
    @FXML public TextField customer_no_end;        // 客户编号end
    @FXML DatePicker date_ben;        // 期间开始
    @FXML DatePicker date_end;        // 期间结束
    @FXML ComboBox  currency_one;        // 收款方式
    @FXML ComboBox  currency_two;        // 收退类型（收款、退款）
    @FXML ComboBox  receive_method;        // 收款方式
    @FXML Button btn_customer;          // 更多客户

    @FXML ComboBox receive_type;        // 收款方式
    @FXML ComboBox receive_people;         // 收款人
    @FXML TextField receive_people_str;           // 收款人描述
    @FXML TextField made_people;             // 制单人
    @FXML TextField remark;             // 备注
    @FXML TextField audit;                // 审核人
    @FXML TextField audit_str;            // 审核人描述
    @FXML TextField last_update;         // 最后修改人
    @FXML TextField last_update_str;        // 最后修改人描述
    @FXML TextField exchange_rate;        // 汇率

    @FXML TextField money;               // 金额
    @FXML TextField certificate_no;        // 凭证编号
    @FXML DatePicker certificate_date;     // 凭证日期
    @FXML CheckBox che_not_receive_money;      // 未收款冲账




    @FXML TableView  tableview_fee;          //收款明细
    @FXML TableColumn col_id;               //ID
    @FXML TableColumn col_no;               //no
    @FXML TableColumn col_customer;           //客户
    @FXML TableColumn col_not_receipt;         //未收款冲款
    @FXML TableColumn col_date;               //日期
    @FXML TableColumn col_money;             //金额
    @FXML TableColumn col_dollar;            //美金
    @FXML TableColumn col_order_no;          //订单编号
    @FXML TableColumn col_verify;              // 审核状态
    @FXML TableColumn col_operation;           //操作


    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        setControllerDisable();
        loadEmployee(receive_people,0); // 收款人
        setComboBox(7L, currency_one, 0);    //币别
        setComboBox(7L, currency_two, 0);    //币别
        receive_method.setItems(FXCollections.observableArrayList(new String[]{"转账","现金","支票","抵账","银行汇款","银行承兑汇票","月末转结","退款（银行转账）","分角调整","预售银行抵贷款","对方开票","预售未到账款","特批单","期初额度调整","同一客户账款互转"}));
        receive_type.setItems(FXCollections.observableArrayList(new String[]{"收款","退款"}));
        setMenuValue(1);    // 第一页数据
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
    }



    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = order_no.getUserData()==null?null:order_no.getUserData().toString();

        if(id != null && !"".equals(id)){
//            if(che_not_receive_money.isSelected()){
//                alert_informationDialog("未收款冲账状态不能通过审核！");
//                return;
//            }
            setShiroControlYES();
            lastUpdatePeopel(last_update, last_update_str);
            lastUpdatePeopel(audit, audit_str);
            shiroUpdateData(true);
            Customer customer = iCustomerService.getCustomer(customer_no.getText());
            if(customer != null){
                customer.setUseableMoney(customer.getUseableMoney().subtract(new BigDecimal(money.getText())));
                iCustomerService.updateNotNull(customer);
                // 销项发票冲账
                rushSaleInvoice(Long.valueOf(id),customer.getCustomerCode(),new BigDecimal(money.getText()));
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
            AccountReceipt goods = iAccountReceiptService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAudit(audit.getText());
            goods.setAuditStr(audit_str.getText());
            iAccountReceiptService.updateNotNull(goods);
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
        shiro_success.setDisable(true);
        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_success.setDisable(false);
        menu_update.setDisable(false);
    }

    /**
     * 保存收款明细数据
     */
    @SneakyThrows
    private void saveTableReceiptInfo(){
        String orderid = order_no.getUserData() == null?null:order_no.getUserData().toString();
        if(orderid != null && !"".equals(orderid) && tableview_fee.getItems() != null){
            for (int i = 0; i < tableview_fee.getItems().size(); i++) {
                AccountReceiptInfoProperty property = null;
                if(tableview_fee.getItems().get(i) != null){
                    property = (AccountReceiptInfoProperty)tableview_fee.getItems().get(i);
                }
                AccountReceiptInfo product = new AccountReceiptInfo();
                if(property.getNo() != null && !"".equals(property.getNo())){
                    product.setNo(property.getNo());
                }
                if(property.getCustomer() != null && !"".equals(property.getCustomer())){
                    product.setCustomer(property.getCustomer());
                }
                if(property.getReceiptNot() != null && !"".equals(property.getReceiptNot())){
                    product.setReceiptNot(property.getReceiptNot());
                }
                if(property.getAddtime() != null && !"".equals(property.getAddtime())){
                    product.setAddtime(df.parse(property.getAddtime()));
                }
                if(property.getPrice() != null && !"".equals(property.getPrice())){
                    product.setPrice(property.getPrice() == null ? null: new BigDecimal(property.getPrice()));
                }
                if(property.getDollar() != null && !"".equals(property.getDollar())){
                    product.setDollar(property.getDollar());
                }
                if(property.getOrderNo() != null && !"".equals(property.getOrderNo())){
                    product.setOrderNo(property.getOrderNo());
                }
                product.setOtherid(Long.valueOf(orderid));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iAccountReceiptInfoService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountReceiptInfoService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            showTableReceiptInfo();
        }
    }

    /**
     * 保存数据
     */
    @FXML
    @SneakyThrows
    public synchronized  void save(){
         setMenuControlState(true);
         lastUpdatePeopel(last_update, last_update_str);
         AccountReceipt order = new AccountReceipt();
         order.setId(order_no.getUserData()==null?null:Long.valueOf(order_no.getUserData().toString()));
         order.setOrderNo(order_no.getText());
         order.setCustomerNo(customer_no.getText());
         order.setCustomerNoStr(customer_no_str.getText());
         order.setCreateDate(receive_date.getValue() == null?null:df.parse(receive_date.getValue().toString()));
         order.setMadePeople(made_people.getText());
        order.setAudit(audit.getText());
        order.setAuditStr(audit_str.getText());
        order.setLastUpdate(last_update.getText());
        order.setLastUpdateStr(last_update_str.getText());
        order.setCreateDate(receive_date.getValue() == null ? null:df.parse(receive_date.getValue().toString()));
        order.setCurrency(currency_one.getSelectionModel().getSelectedItem() == null?null:currency_one.getSelectionModel().getSelectedItem().toString());
        order.setCurrencyType(currency_two.getSelectionModel().getSelectedItem() == null?null:currency_two.getSelectionModel().getSelectedItem().toString());
        order.setReceiptMethod(receive_method.getSelectionModel().getSelectedItem() == null?null:receive_method.getSelectionModel().getSelectedItem().toString());
        order.setReceiptType(receive_type.getSelectionModel().getSelectedItem() == null?null:receive_type.getSelectionModel().getSelectedItem().toString());
        order.setReceiptPeople(receive_people.getSelectionModel().getSelectedItem() == null?null:receive_people.getSelectionModel().getSelectedItem().toString());
        order.setReceiptPeopleStr(receive_people_str.getText());
        order.setMadePeople(made_people.getText());
        order.setRemark(remark.getText());
        order.setRate(exchange_rate.getText());
        order.setMoney(money.getText()==null?null:new BigDecimal(money.getText()));
        order.setTokenNo(certificate_no.getText());
        order.setTokenDate(certificate_date.getValue() == null ? null:df.parse(certificate_date.getValue().toString()));
        order.setNotReceiptMoney(che_not_receive_money.isSelected());

        if(order.getId() != null){
            // 修改
            int rows = iAccountReceiptService.updateNotNull(order);
            returnMsg(rows);
            setControllerDisable();
        }else{
            order.setBalance(order.getMoney());
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iAccountReceiptService.save(order);
            order_no.setUserData(order.getId());
            returnMsg(rows);
            setControllerDisable();
            shiro_success.setDisable(false);
        }

         // 保存表格数据
         saveTableReceiptInfo();
    }

    /**
     * 添加
     */
    @FXML
    @SneakyThrows
    public void add(){
        clearValue();
        receive_date.setValue(localDate.toLocalDate());
        certificate_date.setValue(localDate.toLocalDate());
        String no = createOrderNo(iAccountReceiptService.getMaxOrderNo());
        order_no.setText(no);
        createPeople(made_people);
        setControllerUse();
    }


    /**
     * 加载表格数据
     * @param receiptList
     */
    @SneakyThrows(Exception.class)
    public void generalProductTab(List<AccountReceiptInfoProperty> receiptList){

        col_customer.setCellFactory(column -> EditCell.createStringEditCell());
        col_not_receipt.setCellFactory(column -> EditCell.createStringEditCell());
        col_date.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_dollar.setCellFactory(column -> EditCell.createStringEditCell());
        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_verify.setCellFactory(column -> EditCell.createStringEditCell());
        col_operation.setCellFactory(column -> EditCell.createStringEditCell());

        ObservableList<AccountReceiptInfoProperty> data = FXCollections.observableArrayList(receiptList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        col_not_receipt.setCellValueFactory(new PropertyValueFactory("receiptNot"));
        col_date.setCellValueFactory(new PropertyValueFactory("addtime"));
        col_money.setCellValueFactory(new PropertyValueFactory("price"));
        col_dollar.setCellValueFactory(new PropertyValueFactory("dollar"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_verify.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_operation.setCellValueFactory(new PropertyValueFactory(""));

        tableview_fee.setEditable(true);
        tableview_fee.setItems(data);

    }

    /**
     * 加载收款单明细明细
     */
    public void showTableReceiptInfo(){

        Object object = order_no.getUserData();
        if(object != null){
            List<AccountReceiptInfo> accountSaleInvoiceInfoList = iAccountReceiptInfoService.listAccountReceiptInfo(object.toString());
            List<AccountReceiptInfoProperty> propertyList = new ArrayList<>();
            if(accountSaleInvoiceInfoList!= null){
                accountSaleInvoiceInfoList.forEach(p-> propertyList.add(new AccountReceiptInfoProperty(p.getId(), p.getNo(), p.getCustomer(), p.getReceiptNot(), p.getAddtime()==null?null:new SimpleDateFormat("yyyy-MM-dd").format(p.getAddtime()), p.getPrice(), p.getDollar(), p.getOrderNo())) );
            }
            generalProductTab(propertyList);
        }

    }


    /**
     * 删除数据
     */
    @FXML
    public synchronized  void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
                int rows = iAccountReceiptService.delete(Long.valueOf(order_no.getUserData().toString()));
                iAccountReceiptInfoService.delete(order_no.getUserData().toString());
                returnMsg(rows);
                setMenuValue(1);    // 第一页数据
            }
        }
    }

    /**
     * 赋值
     */
    @FXML
    public void setBasicValue(AccountReceipt order){
        clearValue();
        if(order != null){
            order_no.setUserData(order.getId());
            order_no.setText(order.getOrderNo());
            if(order.getCreateDate() != null){
                receive_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            customer_no.setText(order.getCustomerNo());
            customer_no_str.setText(order.getCustomerNoStr());
            made_people.setText(order.getMadePeople());
            audit.setText(order.getAudit());
            audit_str.setText(order.getAuditStr());
            last_update.setText(order.getLastUpdate());
            last_update_str.setText(order.getLastUpdateStr());

            currency_one.getSelectionModel().select(order.getCurrency());
            currency_two.getSelectionModel().select(order.getCurrencyType());
            receive_method.getSelectionModel().select(order.getReceiptMethod());
            receive_type.getSelectionModel().select(order.getReceiptType());
            receive_people.getSelectionModel().select(order.getReceiptPeople());
            receive_people_str.setText(order.getReceiptPeople());
            remark.setText(order.getRemark());
            exchange_rate.setText(order.getRate());
            money.setText(order.getMoney() == null ? null:order.getMoney().toString());
            certificate_no.setText(order.getTokenNo());
            if(order.getTokenDate() != null){
                certificate_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getTokenDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            che_not_receive_money.setSelected(order.getNotReceiptMoney());

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
            showTableReceiptInfo();
        }
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<AccountReceipt> receiptList = iAccountReceiptService.listAccountReceipt("",page, 1);
        if(receiptList != null && receiptList.size() >0){
            receiptList.forEach(p->setBasicValue(p));
            PageInfo<AccountReceipt> pageInfo = new PageInfo<>(receiptList);
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
            showTableReceiptInfo();
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
                AccountReceiptInfoProperty property = (AccountReceiptInfoProperty)tableview_fee.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iAccountReceiptInfoService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<AccountReceiptInfoProperty> dataProperty = tableview_fee.getItems();
                final ObservableList<AccountReceiptInfoProperty> newDataProperty = FXCollections.observableArrayList();
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

        ObservableList<AccountReceiptInfoProperty> list = tableview_fee.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountReceiptInfoProperty( "", "", "", "", "", "", ""));
        tableview_fee.setItems(list);
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
        order_no.setDisable(true);
        order_no.setDisable(bool);
        customer_no.setDisable(bool);
        customer_no_str.setDisable(bool);
         receive_date.setDisable(bool);
        currency_one.setDisable(bool);
        currency_two.setDisable(bool);
        receive_method.setDisable(bool);
        receive_type.setDisable(bool);
        receive_people.setDisable(bool);
         receive_people_str.setDisable(bool);
         made_people.setDisable(true);
         remark.setDisable(bool);
         audit.setDisable(true);
         audit_str.setDisable(true);
         last_update.setDisable(true);
         last_update_str.setDisable(true);
         exchange_rate.setDisable(bool);
         money.setDisable(bool);
         certificate_no.setDisable(bool);
         certificate_date.setDisable(bool);
        che_not_receive_money.setDisable(bool);
        btn_customer.setDisable(bool);

//        tableview_fee.setDisable(bool);
    }

    /**
     * 清除控件所有的值
     */
    @FXML
    public void clearValue(){
        order_no.clear();
        order_no.setUserData(null);
        receive_date.setValue(null);
        customer_no.clear();
        customer_no_str.clear();
        customer_no_ben.clear();
        customer_no_end.clear();
        date_ben.setValue(null);
        date_end.setValue(null);
        currency_one.getSelectionModel().selectFirst();
        currency_two.getSelectionModel().selectFirst();
        receive_method.getSelectionModel().selectFirst();
        receive_type.getSelectionModel().selectFirst();
        receive_people.getSelectionModel().selectFirst();
        receive_people_str.clear();
        made_people.clear();
        remark.clear();
        audit.clear();
        audit_str.clear();
        last_update.clear();
        last_update_str.clear();
        exchange_rate.clear();
        money.clear();
        certificate_no.clear();
        certificate_date.setValue(null);
        che_not_receive_money.setSelected(false);

        tableview_fee.setItems(null);

    }

    /**
     * 根据选定的客户编号、时间查询
     **/
    @FXML
    @SneakyThrows
    public void filterQuery(){
        String customerNo = customer_no_ben.getText();
        String customerNoEnd = customer_no_end.getText();
        LocalDate dateBen =  date_ben.getValue();
        LocalDate dateEnd = date_end.getValue();
        List<AccountReceipt> accountReceiptList = iAccountReceiptService.listAccountReceiptByWhere(customerNo,customerNoEnd,dateBen==null?"":dateBen.toString(),dateEnd==null?"":dateEnd.toString());
        int rows = 1;
        for (AccountReceipt p : accountReceiptList) {
            p.setNo(rows++);
            p.setCreateDateStr(df.format(p.getCreateDate()));
            if(p.getNotReceiptMoney()){
                p.setNotReceiptMoneyStr("是");
            }else{
                p.setNotReceiptMoneyStr("否");
            }
            if(p.getOrderAudit()){
                p.setOrderAuditStr("已审核");
            }else {
                p.setOrderAuditStr("未审核");
            }
            p.setMoneyStr(p.getMoney()==null?"0.00":p.getMoney().toString());
        }
        // Button
        Callback<TableColumn<AccountReceipt, String>, TableCell<AccountReceipt, String>> buttonFactory
                = new Callback<TableColumn<AccountReceipt, String>, TableCell<AccountReceipt, String>>() {
            @Override
            public TableCell call(final TableColumn<AccountReceipt, String> param) {
                final TableCell<AccountReceipt, String> cell = new TableCell<AccountReceipt, String>() {

                    final Button btn1 = new Button("冲账");

                    @Override
                    public void updateItem(String ite, boolean empty) {
                        super.updateItem(ite, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction((ActionEvent t)
                                    -> {
                                int selectdIndex = getTableRow().getIndex();
                                AccountReceipt receipt = (AccountReceipt)tableview_fee.getItems().get(selectdIndex);
                                if(receipt.getOrderAudit()){
                                    alert_informationDialog("此单据已经被冲账过了无需再次冲账！");
                                }else{
                                    receipt.setAudit(getAdminName());
                                    receipt.setAuditStr(df.format(new Date()));
                                    receipt.setOrderAudit(true);
                                    iAccountReceiptService.updateNotNull(receipt);
                                    Customer customer = iCustomerService.getCustomer(receipt.getCustomerNo());
                                    if(customer != null){
                                        customer.setUseableMoney(customer.getUseableMoney().subtract(receipt.getMoney()));
                                        int rows = iCustomerService.updateNotNull(customer);
                                        // 销项发票冲账
                                        rushSaleInvoice(receipt.getId(),customer.getCustomerCode(),receipt.getMoney());
                                        alertProcessResult(rows);
                                        filterQuery();
                                    }
                                }
                            });
                            btn1.setMaxWidth(50);
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
        col_operation.setCellFactory(buttonFactory);

        ObservableList<AccountReceipt> data = FXCollections.observableArrayList(accountReceiptList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_customer.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_not_receipt.setCellValueFactory(new PropertyValueFactory("notReceiptMoneyStr"));
        col_money.setCellValueFactory(new PropertyValueFactory("moneyStr"));
        col_dollar.setCellValueFactory(new PropertyValueFactory(""));
        col_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_verify.setCellValueFactory(new PropertyValueFactory("orderAuditStr"));

        tableview_fee.setEditable(true);
        tableview_fee.setItems(data);
    }




    /**
     * 查询更多 收款单
     */
    @FXML
    public void moreOrder(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountReceiptControllerMore", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_receipt_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO  打开窗口--现有客户查询
     * @Date 20:22 2018/8/15f
     * @Param []
     **/
    @FXML
    public void OpenCurrentClientQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountReceiptControllerCustomer", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO  打开窗口--现有客户查询
     * @Date 20:22 2018/8/15f
     * @Param []
     **/
    @FXML
    public void OpenCurrentClientQueryBen() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountReceiptControllerCustomerBen", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO  打开窗口--现有客户查询
     * @Date 20:22 2018/8/15f
     * @Param []
     **/
    @FXML
    public void OpenCurrentClientQueryEnd() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountReceiptControllerCustomerEnd", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

}
