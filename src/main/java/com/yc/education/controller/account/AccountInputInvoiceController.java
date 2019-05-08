package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.*;
import com.yc.education.service.account.IAccountInputInvoiceInfoService;
import com.yc.education.service.account.IAccountInputInvoiceService;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @Description 进项发票
 * @Author BlueSky
 * @Date 2019-01-24 9:39
 */
@Controller
public class AccountInputInvoiceController extends BaseController implements Initializable {

    @Autowired IAccountInputInvoiceService iAccountInputInvoiceService;     //进项发票
    @Autowired IAccountInputInvoiceInfoService iAccountInputInvoiceInfoService;        //进项发票详情

    // 日期格式
    LocalDateTime localDate = LocalDateTime.now();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Stage stage = new Stage();
    static int changeId = 0;      // 记录当前编辑计算金额cell 的id值s
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
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

    @FXML DatePicker create_date;           //制单日期
    @FXML TextField order_no;               //单号
    @FXML DatePicker invoice_date;           //发票日期
    @FXML TextField invoice_no;             //发票号码
    @FXML TextField made_people;            //制单人
    @FXML ComboBox  invoice_type;            //发票种类

    @FXML public TextField supplier_no;            //供应商编号
    @FXML public TextField supplier_str;           //供应商编号描述
    @FXML DatePicker payment_date;          //结算日期
    @FXML TextField remark;                 //备注
    @FXML TextField last_update;            //最后修改人
    @FXML TextField last_update_str;        //最后修改人描述
    @FXML TextField audit;                  //审核人
    @FXML TextField audit_str;              //审核人描述

    @FXML TableView tableview_invoice;
    @FXML TableColumn col_id;               //id
    @FXML TableColumn col_no;               //序号
    @FXML TableColumn col_order_source;     //来源单据
    @FXML TableColumn col_order_no;         //单号
    @FXML TableColumn col_order_num;        //序号
    @FXML TableColumn col_product_no;       //产品编号
    @FXML TableColumn col_product_name;     //产品名称
    @FXML TableColumn col_invoice;          //发票品名
    @FXML TableColumn col_unit;             //单位
    @FXML TableColumn col_price;            //单价
    @FXML TableColumn col_money;            // 金额
    @FXML TableColumn col_tax;              //税率
    @FXML TableColumn col_tax_money;        //税额
    @FXML TableColumn col_remark;           //备注
    @FXML TableColumn col_num;              //数量

    @FXML TextField total_num;              //数量总计
    @FXML TextField total_money;            //金额总计
    @FXML TextField total_loan;             //贷款合计
    @FXML TextField total_tax;              //税款合计

    @Override
    public synchronized void initialize(URL location, ResourceBundle resources) {
        setControllerDisable();
        setMenuValue(1);    // 第一页数据
        setComboBox(35L, invoice_type, 0);
    }

    /**
     * 发票明细 view
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
            showTableInvoiceInfo();
        }
    }

    /**
     * 删除产 发票明细
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tableview_invoice.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tableview_invoice.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                AccountInputInvoiceInfoProperty property = (AccountInputInvoiceInfoProperty)tableview_invoice.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iAccountInputInvoiceInfoService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<AccountInputInvoiceInfoProperty> dataProperty = tableview_invoice.getItems();
                final ObservableList<AccountInputInvoiceInfoProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                tableview_invoice.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加 发票明细 行
     */
    public void addProductRow() {

        ObservableList<AccountInputInvoiceInfoProperty> list = tableview_invoice.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        Double rate = getTaxReruenDouble(create_date.getValue(),1);
        list.add(new AccountInputInvoiceInfoProperty(list.size()+1,"", "", "", "", "", "", "","0","0.00", "0.00", rate.toString(), "0.00", ""));
        generalProductTab(list);
    }


    /**
     * 新增
     */
    @FXML
    public void add(){
        clearValue();
        create_date.setValue(localDate.toLocalDate());
        setArrivalDate(create_date,payment_date,10);
        String no = createOrderNo(iAccountInputInvoiceService.getMaxOrderNo());
        order_no.setText(no);
        createPeople(made_people);
        setControllerUse();
    }


    /**
     * 加载数据
     */
    @FXML
    public void setBasicValue(AccountInputInvoice order){
        clearValue();
        if(order != null){
            order_no.setUserData(order.getId());
            order_no.setText(order.getOrderNo());
            if(order.getCreateDate() != null){
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            made_people.setText(order.getMadePeople());
            if(order.getInvoiceDate() != null){
                invoice_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getInvoiceDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            invoice_no.setText(order.getInvoceNo());
            audit.setText(order.getAudit());
            audit_str.setText(order.getAuditStr());
            last_update.setText(order.getLastUpdate());
            last_update_str.setText(order.getLastUpdateStr());
            remark.setText(order.getRemark());
            invoice_type.getSelectionModel().select(order.getInvoiceType());
            supplier_no.setText(order.getSupplierNo());
            supplier_str.setText(order.getSupplierNoStr());
            if(order.getPaymentDate() != null){
                payment_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getPaymentDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }


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
            showTableInvoiceInfo();
        }
    }



    /**
     * 删除数据
     */
    @FXML
    public synchronized  void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
                int rows = iAccountInputInvoiceService.delete(Long.valueOf(order_no.getUserData().toString()));
                iAccountInputInvoiceInfoService.deleteAccountInputInvoiceInfoByParentId(order_no.getUserData().toString());
                returnMsg(rows);
                setMenuValue(1);    // 第一页数据
            }
        }
    }


    /**
     * 保存数据 == 提交
     */
    @FXML
    @SneakyThrows
    public synchronized  void save(){
        setMenuControlState(true);
        lastUpdatePeopel(last_update, last_update_str);
        AccountInputInvoice order = new AccountInputInvoice();
        order.setId(order_no.getUserData()==null?null:Long.valueOf(order_no.getUserData().toString()));
        order.setCreateDate(create_date.getValue() == null?null:df.parse(create_date.getValue().toString()));
        order.setInvoiceDate(invoice_date.getValue() == null?null:df.parse(invoice_date.getValue().toString()));
        order.setOrderNo(order_no.getText());
        order.setInvoceNo(invoice_no.getText());
        order.setMadePeople(made_people.getText());
        order.setInvoiceType(invoice_type.getSelectionModel().getSelectedItem() == null ? null:invoice_type.getSelectionModel().getSelectedItem().toString());
        order.setSupplierNo(supplier_no.getText());
        order.setSupplierNoStr(supplier_str.getText());
        order.setRemark(remark.getText());
        order.setLastUpdate(last_update.getText());
        order.setLastUpdateStr(last_update_str.getText());
        order.setAudit(audit.getText());
        order.setAuditStr(audit_str.getText());
        order.setPaymentDate(payment_date.getValue() == null?null:df.parse(payment_date.getValue().toString()));
        List<AccountInputInvoiceInfoProperty> propertyList = tableview_invoice.getItems();
        total_num.setText("0");
        total_loan.setText("0.00");
        total_tax.setText("0.00");
        total_money.setText("0.00");
        for (AccountInputInvoiceInfoProperty p : propertyList) {
            // 数量合计
            if(p.getNum() != null){
                if(total_num.getText() == null || "0".equals(total_num.getText())){
                    total_num.setText(p.getNum());
                }else{
                    total_num.setText((Integer.valueOf(total_num.getText())+p.getNum())+"");
                }
            }
            // 贷款合计
            if(p.getMoney() != null){
                if(total_loan.getText() == null || "0.00".equals(total_loan.getText())){
                    total_loan.setText(p.getMoney());
                }else{
                    total_loan.setText((new BigDecimal(total_loan.getText()).add(new BigDecimal(p.getMoney()))).toString());
                }
            }
            // 税款合计
            if(p.getTaxMoney() != null){
                if(total_tax.getText() == null || "0.00".equals(total_tax.getText())){
                    total_tax.setText(p.getTaxMoney());
                }else{
                    total_tax.setText((new BigDecimal(total_tax.getText()).add(new BigDecimal(p.getMoney()))).toString());
                }
            }
            // 金额总计
            if(total_money.getText() != null && !"0.00".equals(total_money.getText())){
                total_money.setText((new BigDecimal(total_loan.getText()).add(new BigDecimal(total_tax.getText())).add(new BigDecimal(total_money.getText()))).toString());
            }else{
                total_money.setText((new BigDecimal(total_loan.getText()).add(new BigDecimal(total_tax.getText()))).toString());
            }
        }
        order.setMoney(total_money.getText()==null?new BigDecimal("0.00"):new BigDecimal(total_money.getText()));
        if(order.getId() != null){
            // 修改
            int rows = iAccountInputInvoiceService.updateNotNull(order);
            returnMsg(rows);
            setControllerDisable();
        }else{
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iAccountInputInvoiceService.save(order);
            order_no.setUserData(order.getId());
            returnMsg(rows);
            setControllerDisable();
        }

        // 保存表格数据
        saveTableInvoice();
    }

    /**
     * 保存表格发票明细数据
     */
    private void saveTableInvoice(){
        String orderid = order_no.getUserData() == null?null:order_no.getUserData().toString();
        if(orderid != null && !"".equals(orderid) && tableview_invoice.getItems() != null){
            for (int i = 0; i < tableview_invoice.getItems().size(); i++) {
                AccountInputInvoiceInfoProperty property = null;
                if(tableview_invoice.getItems().get(i) != null){
                    property = (AccountInputInvoiceInfoProperty)tableview_invoice.getItems().get(i);
                }
                AccountInputInvoiceInfo product = new AccountInputInvoiceInfo();

                if(property.getOrderNo() != null && !"".equals(property.getOrderNo())){
                    product.setOrderNo(property.getOrderNo());
                }
                if(property.getOrderNum() != null && !"".equals(property.getOrderNum())){
                    product.setOrderNum(property.getOrderNum());
                }
                if(property.getOrderSource() != null && !"".equals(property.getOrderSource())){
                    product.setOrderSource(property.getOrderSource());
                }else{
                    product.setOrderSource("手动录入");
                }
                if(property.getInvoiceName() != null && !"".equals(property.getInvoiceName())){
                    product.setInvoiceName(property.getInvoiceName());
                }
                if(property.getUnit() != null && !"".equals(property.getUnit())){
                    product.setUnit(property.getUnit());
                }
                if(property.getNum() != null && !"".equals(property.getNum())){
                    product.setNum(property.getNum() == null ? 0: Integer.valueOf(property.getNum()));
                }
                if(property.getPrice() != null && !"".equals(property.getPrice())){
                    product.setPrice(property.getPrice() == null ? new BigDecimal("0.00"): new BigDecimal(property.getPrice()));
                }
                if(property.getProductName() != null && !"".equals(property.getProductName())){
                    product.setProductName(property.getProductName());
                }
                if(property.getProductNo() != null && !"".equals(property.getProductNo())){
                    product.setProductNo(property.getProductNo());
                }
                if(property.getMoney() != null && !"".equals(property.getMoney())){
                    product.setMoney(property.getMoney() == null ? new BigDecimal("0.00"): new BigDecimal(property.getMoney()));
                }
                if(property.getTax() != null && !"".equals(property.getTax())){
                    product.setTax(property.getTax());
                }
                if(property.getTaxMoney() != null && !"".equals(property.getTaxMoney())){
                    product.setTaxMoney(property.getTaxMoney() == null ? new BigDecimal("0.00"): new BigDecimal(property.getTaxMoney()));
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                product.setOtherid(Long.valueOf(orderid));
                if(property.getId() == null || property.getId() == 0){
                    try {
                        product.setAddtime(new Date());
                        iAccountInputInvoiceInfoService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountInputInvoiceInfoService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            showTableInvoiceInfo();
        }
    }

    public void showTableInvoiceInfo(){
        Object object = order_no.getUserData();
        if(object != null){
            List<AccountInputInvoiceInfo> accountInputInvoiceInfoList = iAccountInputInvoiceInfoService.listAccountInputInvoiceInfo(object.toString());
            List<AccountInputInvoiceInfoProperty> propertyList = new ArrayList<>();
            if(accountInputInvoiceInfoList!= null){
                int rows = 1;
                total_num.setText("0");
                total_loan.setText("0.00");
                total_tax.setText("0.00");
                total_money.setText("0.00");
                for (AccountInputInvoiceInfo p : accountInputInvoiceInfoList) {
                    // 数量合计
                    if(p.getNum() != null){
                        if(total_num.getText() == null || "".equals(total_num.getText())){
                            total_num.setText(p.getNum().toString());
                        }else{
                            total_num.setText((Integer.valueOf(total_num.getText())+p.getNum())+"");
                        }
                    }
                    // 贷款合计
                    if(p.getMoney() != null){
                        if(total_loan.getText() == null || "".equals(total_loan.getText())){
                            total_loan.setText(p.getMoney().toString());
                        }else{
                            total_loan.setText((new BigDecimal(total_loan.getText()).add(p.getMoney())).toString());
                        }
                    }
                    // 税款合计
                    if(p.getTaxMoney() != null){
                        if(total_tax.getText() == null || "".equals(total_tax.getText())){
                            total_tax.setText(p.getTaxMoney().toString());
                        }else{
                            total_tax.setText((new BigDecimal(total_tax.getText()).add(p.getTaxMoney())).toString());
                        }
                    }
                    // 金额总计
                    if(total_money.getText() != null && !"".equals(total_money.getText())){
                        total_money.setText((new BigDecimal(total_loan.getText()).add(new BigDecimal(total_tax.getText())).add(new BigDecimal(total_money.getText()))).toString());
                    }else{
                        total_money.setText((new BigDecimal(total_loan.getText()).add(new BigDecimal(total_tax.getText()))).toString());
                    }
                    propertyList.add(new AccountInputInvoiceInfoProperty(p.getId(), rows++,p.getOrderSource(), p.getOrderNo(), p.getOrderNum(), p.getProductNo(), p.getProductName(), p.getInvoiceName(), p.getUnit(),p.getNum(), p.getPrice(), p.getMoney(),p.getTax(), p.getTaxMoney(), p.getRemark()));
                }
            }
            generalProductTab(propertyList);
        }
    }

    /**
     * 加载表格数据
     * @param receiptList
     */
    @SneakyThrows(Exception.class)
    public void generalProductTab(List<AccountInputInvoiceInfoProperty> receiptList){

        col_order_source.setCellFactory(column -> EditCell.createStringEditCell());
        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());
//        col_order_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_invoice.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
        col_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_tax.setCellFactory(column -> EditCell.createStringEditCell());
        col_tax_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());

        ObservableList<AccountInputInvoiceInfoProperty> data = FXCollections.observableArrayList(receiptList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_source.setCellValueFactory(new PropertyValueFactory("orderSource"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
//        col_order_num.setCellValueFactory(new PropertyValueFactory("orderNum"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_invoice.setCellValueFactory(new PropertyValueFactory("invoiceName"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_tax.setCellValueFactory(new PropertyValueFactory("tax"));
        col_tax_money.setCellValueFactory(new PropertyValueFactory("taxMoney"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableview_invoice.setEditable(true);
        tableview_invoice.setItems(data);

        tableview_invoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountInputInvoiceInfoProperty>() {
            @Override
            public void changed(ObservableValue<? extends AccountInputInvoiceInfoProperty> observableValue, AccountInputInvoiceInfoProperty oldItem, AccountInputInvoiceInfoProperty newItem) {
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

        //提交数量计算金额  询价订单--询价产品 金额总计
        col_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;
                List<AccountInputInvoiceInfoProperty> data = tableview_invoice.getItems();
                for (AccountInputInvoiceInfoProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(newNum*Double.valueOf(property.getPrice())).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        BigDecimal rateMoney = calcMoney.multiply(new BigDecimal(property.getTax())).setScale(2,BigDecimal.ROUND_UP);
                        property.setTaxMoney(rateMoney.toString());
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
                List<AccountInputInvoiceInfoProperty> data = tableview_invoice.getItems();
                for (AccountInputInvoiceInfoProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setPrice(String.valueOf(newPrice));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(Integer.parseInt(property.getNum())*newPrice).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        BigDecimal rateMoney = calcMoney.multiply(new BigDecimal(property.getTax())).setScale(2,BigDecimal.ROUND_UP);
                        property.setTaxMoney(rateMoney.toString());
                    }
                }
            }
        });

    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<AccountInputInvoice> receiptList = iAccountInputInvoiceService.listAccountInputInvoice("",page, 1);
        if(receiptList != null && receiptList.size() >0){
            receiptList.forEach(p->setBasicValue(p));
            PageInfo<AccountInputInvoice> pageInfo = new PageInfo<>(receiptList);
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
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = order_no.getUserData()==null?null:order_no.getUserData().toString();

        if(id != null && !"".equals(id)){
            AccountInputInvoice order = iAccountInputInvoiceService.selectByKey(Long.valueOf(id));
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
            AccountInputInvoice goods = iAccountInputInvoiceService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAudit(audit.getText());
            goods.setAuditStr(audit_str.getText());
            iAccountInputInvoiceService.updateNotNull(goods);
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
//        import_out.setDisable(false);
        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
//        import_out.setDisable(true);
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
     * @param bool
     */
    public void setController(Boolean bool){
        if(bool){
            NumberUtil.changeStatus(writestate,0);
        }else{
            NumberUtil.changeStatus(writestate,2);
        }
        create_date.setDisable(bool);
        order_no.setDisable(bool);
        invoice_date.setDisable(bool);
        invoice_no.setDisable(bool);
        made_people.setDisable(true);
        invoice_type.setDisable(bool);


        supplier_no.setDisable(bool);
        supplier_str.setDisable(bool);
        remark.setDisable(bool);
        payment_date.setDisable(bool);
        last_update.setDisable(true);
        last_update_str.setDisable(true);
        audit.setDisable(true);
        audit_str.setDisable(true);

        tableview_invoice.setDisable(bool);
    }

    /**
     * 清除所有控件数据
     */
    @FXML
    public void clearValue(){
        order_no.clear();
        order_no.setUserData(null);
        create_date.setValue(null);
        invoice_date.setValue(null);
        payment_date.setValue(null);
        invoice_no.clear();
        made_people.clear();
        invoice_type.getSelectionModel().selectFirst();

        supplier_no.clear();
        supplier_str.clear();
        remark.clear();
        last_update.clear();
        last_update_str.clear();
        audit.clear();
        audit_str.clear();

        total_money.setText("0.00");
        total_tax.setText("0.00");
        total_loan.setText("0.00");
        total_num.setText("0");

        tableview_invoice.setItems(null);
    }

    /**
     * 查询更多已开票的 销项发票
     */
    @FXML
    public void moreInvoiceOrder(){
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountInputInvoiceControllerMore", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_input_invoice_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
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
        StageManager.CONTROLLER.put("AccountInputInvoiceControllerSupplier", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/supplier_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
