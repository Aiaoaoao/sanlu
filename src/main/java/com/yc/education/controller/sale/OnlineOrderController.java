package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.Relation;
import com.yc.education.model.customer.Remark;
import com.yc.education.model.sale.SaleOnlineOrder;
import com.yc.education.model.sale.SaleOnlineOrderProduct;
import com.yc.education.model.sale.SaleOnlineOrderProductProperty;
import com.yc.education.service.RelationService;
import com.yc.education.service.sale.ISaleOnlineOrderProductService;
import com.yc.education.service.sale.ISaleOnlineOrderService;
import com.yc.education.service.sale.ISalePurchaseOrderService;
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
 * 销售-网上订单
 */
@Controller
public class OnlineOrderController extends BaseController implements Initializable {

    @Autowired ISaleOnlineOrderService iSaleOnlineOrderService;
    @Autowired ISaleOnlineOrderProductService iSaleOnlineOrderProductService;
    @Autowired ISalePurchaseOrderService iSalePurchaseOrderService;
    @Autowired RelationService iRelationService;    // 关联单据

    /**
     * @Description 单据关联容器
     * @Author BlueSky
     * @Date 17:03 2019/4/26
     **/
    Relation relation = new Relation();

    //每个单据用来锁定关联数据的
    boolean relationLock = false;


    // 菜单控件
    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页
    @FXML VBox menu_clearAll;
    @FXML VBox menu_commit;
    @FXML VBox menu_insert;
    @FXML VBox menu_update;
    @FXML VBox menu_delete;
    @FXML VBox menu_printing;
    @FXML VBox shiro_success;
    @FXML VBox import_out;
    @FXML Label writestate;// 待输入

    // 订单id order_no.getUserData()

    @FXML DatePicker order_date;
    @FXML public TextField customer_no;
    @FXML TextField order_remark;
    @FXML TextField order_no;
    @FXML public TextField customer_no_str;
    // 作废按钮
    @FXML Button btn_invalid;
    @FXML Button more_customer;   // 按钮 - 更多客户
    @FXML public TextField order_people;
    @FXML public ComboBox contact;  // 联系人
    @FXML public TextField zip;
    @FXML public TextField invoice_title;
    @FXML public TextField invoice_address;
    @FXML TextField audit;
    @FXML TextField update_last;
    @FXML TextField invalid_people;
    @FXML TextField base_remark;
    @FXML public ComboBox phone; // 联系电话
    @FXML public ComboBox fax;   // 传真
    @FXML public ComboBox delivery_address;  // 送货地址
    @FXML TextField audit_str;
    @FXML TextField update_last_str;
    @FXML TextField invalid_people_str;

    @FXML public TableView tableview;
    @FXML TableColumn col_id;
    @FXML TableColumn col_no;   //序号
    @FXML TableColumn col_product_no;
    @FXML TableColumn col_product_name;
    @FXML TableColumn col_category;
    @FXML TableColumn col_number;
    @FXML TableColumn col_unit;
    @FXML TableColumn col_price;
    @FXML TableColumn col_money;
    @FXML TableColumn col_usable_num;
    @FXML TableColumn col_stock;
    @FXML TableColumn col_remark;

    // 合计
    @FXML TextField total_num; // 合计数量
    @FXML TextField total_money; //合计金额
    @FXML TextField total_loan; //合计贷款
    @FXML TextField tax_total; //税款合计

    static int changeId = 0;      // 记录当前编辑计算金额cell 的id值

    /***************************************** 弹出窗口-产品 ********************************************/
    int tablePosition = 0; // 弹出窗口选中的行数
    /***************************************** 弹出窗口-产品-结束 ********************************************/

    SpringFxmlLoader loader = new SpringFxmlLoader();
    private Stage stage = new Stage();
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

        setMenuValue(1);
        setControllerDisable();

        // 报价产品行双击 调出现有产品窗口
        BaseController.clickEvent(tableview, data ->{
            tablePosition = tableview.getSelectionModel().getSelectedIndex();
            moreProductButtonClick();
        }, 2);
    }

    /**
     * 审核通过
     */
    private void setShiroControlYES(){
        shiro_success.setDisable(true);
        import_out.setDisable(false);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_success.setDisable(false);
        import_out.setDisable(true);
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleOnlineOrder order = iSaleOnlineOrderService.selectByKey(Long.valueOf(id));
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
        String id = order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleOnlineOrder onlineOrder = iSaleOnlineOrderService.selectByKey(Long.valueOf(id));
            onlineOrder.setOrderAudit(bool);
            onlineOrder.setUpdateLast(update_last.getText());
            onlineOrder.setUpdateLastStr(update_last_str.getText());
            onlineOrder.setAudit(audit.getText());
            onlineOrder.setAuditStr(audit_str.getText());
            iSaleOnlineOrderService.updateNotNull(onlineOrder);
        }
    }

    /**
     * 订单作废
     */
    @FXML
    public void orderInvalid(){
        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_ABOLISH)){
                btn_invalid.setDisable(true);
                SaleOnlineOrder onlineOrder = new SaleOnlineOrder();
                onlineOrder.setId(Long.valueOf(order_no.getUserData().toString()));
                onlineOrder.setInvalid(true);
                int rows = iSaleOnlineOrderService.updateNotNull(onlineOrder);
                setControllerDisable();
                returnMsg(rows);
            }
        }
    }

    /**
     * 现有产品查询
     */
    @FXML
    public  void moreProductButtonClick(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("OnlineOrderControllerProduct", this);
        StageManager.CONTROLLER.put("tablePosition", tablePosition);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * 报表备注view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfReportRemarkTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addProductRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfProduct();
            generalProductTab();
        }
    }

    /**
     * 删除产品行
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tableview.getSelectionModel().getSelectedCells().size() == 0){return;}
                TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                SaleOnlineOrderProductProperty property = (SaleOnlineOrderProductProperty)tableview.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iSaleOnlineOrderProductService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<SaleOnlineOrderProductProperty> dataProperty = tableview.getItems();
                final ObservableList<SaleOnlineOrderProductProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                tableview.setItems(newDataProperty);
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }

    /**
     * 添加产品行
     */
    public void addProductRow() {

        ObservableList<SaleOnlineOrderProductProperty> list = tableview.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new SaleOnlineOrderProductProperty( list.size()+1,"", "", "", "", 0, "", new BigDecimal("0.00"), new BigDecimal("0.00"), 0, ""));
        tableview.setEditable(true);
        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_category.setCellFactory(column -> EditCell.createStringEditCell());
        col_number.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_usable_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_stock.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());

        col_id.setCellValueFactory(new PropertyValueFactory<Remark,Long>("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_category.setCellValueFactory(new PropertyValueFactory("category"));
        col_number.setCellValueFactory(new PropertyValueFactory("num"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_usable_num.setCellValueFactory(new PropertyValueFactory("usableNum"));
        col_stock.setCellValueFactory(new PropertyValueFactory("ifstock"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableview.setItems(list);
    }

    /**
     * 新增订单
     */
    @FXML
    public void add(){
        clearControllerVal();
        contact.getItems().clear();
        phone.getItems().clear();
        fax.getItems().clear();
        delivery_address.getItems().clear();
        order_date.setValue(localDate.toLocalDate());
        order_no.setText(createOrderNo(iSaleOnlineOrderService.getMaxOrderNo()));
        setControllerUse();
    }

    /**
     * 保存数据
     */
    @FXML
    public synchronized void save(){
        // 检查非空
        if(checkPrimaryNull(order_no,customer_no,customer_no_str,null)){
            return;
        }
        lastUpdatePeopel(update_last, update_last_str);

        SaleOnlineOrder onlineOrder = new SaleOnlineOrder();
        if(order_date.getValue() != null){
            try {
                Date date = df.parse(order_date.getValue().toString());
                onlineOrder.setOrderDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            onlineOrder.setCustomerNo(customer_no.getText());
        }
        if(order_remark.getText() != null && !"".equals(order_remark.getText())){
            onlineOrder.setOrderRemark(order_remark.getText());
        }
        if(order_no.getText() != null && !"".equals(order_no.getText())){
            onlineOrder.setOrderNo(order_no.getText());
        }
        if(customer_no_str.getText() != null && !"".equals(customer_no_str.getText())){
            onlineOrder.setCustomerNoStr(customer_no_str.getText());
        }
        if(order_people.getText() != null && !"".equals(order_people.getText())){
            onlineOrder.setOrderPeople(order_people.getText());
        }
        if(contact.getSelectionModel().getSelectedItem() != null){
            onlineOrder.setContact(contact.getSelectionModel().getSelectedItem().toString());
        }
        if(zip.getText() != null && !"".equals(zip.getText())){
            onlineOrder.setZip(zip.getText());
        }
        if(invoice_title.getText() != null && !"".equals(invoice_title.getText())){
            onlineOrder.setInvoiceTitle(invoice_title.getText());
        }
        if(invoice_address.getText() != null && !"".equals(invoice_address.getText())){
            onlineOrder.setInvoiceAddress(invoice_address.getText());
        }
        if(audit.getText() != null && !"".equals(audit.getText())){
            onlineOrder.setAudit(audit.getText());
        }
        if(update_last.getText() != null && !"".equals(update_last.getText())){
            onlineOrder.setUpdateLast(update_last.getText());
        }
        if(invalid_people.getText() != null && !"".equals(invalid_people.getText())){
            onlineOrder.setInvalidPeople(invalid_people.getText());
        }
        if(base_remark.getText() != null && !"".equals(base_remark.getText())){
            onlineOrder.setBaseRemark(base_remark.getText());
        }
        if(phone.getSelectionModel().getSelectedItem() != null ){
            onlineOrder.setPhone(phone.getSelectionModel().getSelectedItem().toString());
        }
        if(fax.getSelectionModel().getSelectedItem() != null){
            onlineOrder.setFax(fax.getSelectionModel().getSelectedItem().toString());
        }
        if(delivery_address.getSelectionModel().getSelectedItem() != null && !"".equals(delivery_address.getSelectionModel().getSelectedItem())){
            onlineOrder.setDeliveryAddress(delivery_address.getValue().toString());
        }
        if(audit_str.getText() != null && !"".equals(audit_str.getText())){
            onlineOrder.setAuditStr(audit_str.getText());
        }
        if(update_last_str.getText() != null && !"".equals(update_last_str.getText())){
            onlineOrder.setUpdateLastStr(update_last_str.getText());
        }
        if(invalid_people_str.getText() != null && !"".equals(invalid_people_str.getText())){
            onlineOrder.setInvalidPeopleStr(invalid_people_str.getText());
        }
        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            onlineOrder.setId(Long.valueOf(order_no.getUserData().toString()));
        }

        if(onlineOrder.getId() != null){
            // 修改
            int rows = iSaleOnlineOrderService.updateNotNull(onlineOrder);
            returnMsg(rows);
        }else{
            // 保存
            onlineOrder.setOrderAudit(false);
            onlineOrder.setInvalid(false);
            onlineOrder.setAddtime(new Date());
            int rows = iSaleOnlineOrderService.save(onlineOrder);
            order_no.setUserData(onlineOrder.getId());
            returnMsg(rows);
            setControllerDisable();

            if(relationLock){
                //添加关联关系
                relation.setRelationName("网上订单");
                relation.setRelationId(onlineOrder.getId());
                iRelationService.save(relation);
            }


        }
        // 操作tableview中的数据
        saveTableviewProduct(order_no.getUserData().toString());
        // 刷新表格数据
        generalProductTab();
        // 重新加载表格数据
        setControllerDisable();
        setBasicVal(onlineOrder);
    }

    /**
     * 保存订货产品tableview数据
     */
    private void saveTableviewProduct(String id){
        if(id != null && !"".equals(id) && tableview.getItems() != null){
            int tableSize = tableview.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                SaleOnlineOrderProductProperty property = null;
                if(tableview.getItems().get(i) != null){
                    property = (SaleOnlineOrderProductProperty)tableview.getItems().get(i);
                }
                SaleOnlineOrderProduct product = new SaleOnlineOrderProduct();

                if(property.getProductNo() != null){
                    product.setProductNo(property.getProductNo());
                }
                if(property.getProductName() != null){
                    product.setProductName(property.getProductName());
                }
                if(property.getCategory() != null){
                    product.setCategory(property.getCategory());
                }
                if(property.getNum() != null){
                    product.setNum(Integer.valueOf(property.getNum()));
                }
                if(property.getUnit() != null){
                    product.setUnit(property.getUnit());
                }
                if(property.getPrice() != null){
                    product.setPrice(new BigDecimal(property.getPrice()));
                }
                if(property.getMoney() != null){
                    product.setMoney(new BigDecimal(property.getMoney()));
                }
                if(property.getUsableNum() != null){
                    product.setUsableNum(Integer.valueOf(property.getUsableNum()));
                }
                if(property.getIfstock() != null && !"".equals(property.getIfstock())){
                    if("是".equals(property.getIfstock())){
                        product.setIfstock(true);
                    }else{
                        product.setIfstock(false);
                    }
                }
                if(property.getRemark() != null){
                    product.setRemark(property.getRemark());
                }
                product.setOnlineOrderId(Long.valueOf(id));
                product.setAddtime(new Date());
                if(property.getId() == null || property.getId() == 0){
                    try {
                        iSaleOnlineOrderProductService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    product.setId(property.getId());
                    try {
                        iSaleOnlineOrderProductService.updateNotNull(product);
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
                int rows = iSaleOnlineOrderService.delete(Long.valueOf(order_no.getUserData().toString()));
                iSaleOnlineOrderProductService.deleteSaleOnlineOrderProductByParentId(Long.valueOf(order_no.getUserData().toString()));
                // 删除关联单据
                iRelationService.deleteRelation("网上订单",Long.valueOf(order_no.getUserData().toString()));
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
        List<SaleOnlineOrder> purchaseOrdersList = iSaleOnlineOrderService.listSaleOnlineOrderByPage("","",page, 1);
        if(purchaseOrdersList != null && purchaseOrdersList.size() >0){
            PageInfo<SaleOnlineOrder> pageInfo = new PageInfo<>(purchaseOrdersList);
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
     * @param onlineOrder
     */
    public void setBasicVal(SaleOnlineOrder onlineOrder){
        clearControllerVal();
        if(onlineOrder == null){
            return;
        }

        if(onlineOrder.getId() != null){
            order_no.setUserData(onlineOrder.getId().toString());
        }
        if(onlineOrder.getOrderDate() != null){
            order_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(onlineOrder.getOrderDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(onlineOrder.getCustomerNo() != null){
            customer_no.setText(onlineOrder.getCustomerNo());
        }
        if(onlineOrder.getOrderRemark() != null){
            order_remark.setText(onlineOrder.getOrderRemark());
        }
        if(onlineOrder.getOrderNo() != null){
            order_no.setText(onlineOrder.getOrderNo());
        }
        if(onlineOrder.getCustomerNoStr() != null){
            customer_no_str.setText(onlineOrder.getCustomerNoStr());
        }
        if(onlineOrder.getOrderPeople() != null){
            order_people.setText(onlineOrder.getOrderPeople());
        }
        if(onlineOrder.getContact() != null){
            contact.getSelectionModel().select(onlineOrder.getContact());
        }
        if(onlineOrder.getZip() != null){
            zip.setText(onlineOrder.getZip());
        }
        if(onlineOrder.getInvoiceTitle() != null){
            invoice_title.setText(onlineOrder.getInvoiceTitle());
        }
        if(onlineOrder.getInvoiceAddress() != null){
            invoice_address.setText(onlineOrder.getInvoiceAddress());
        }
        if(onlineOrder.getAudit() != null){
            audit.setText(onlineOrder.getAudit());
        }
        if(onlineOrder.getUpdateLast() != null){
            update_last.setText(onlineOrder.getUpdateLast());
        }
        if(onlineOrder.getInvalidPeople() != null){
            invalid_people.setText(onlineOrder.getInvalidPeople());
        }
        if(onlineOrder.getBaseRemark() != null){
            base_remark.setText(onlineOrder.getBaseRemark());
        }
        if(onlineOrder.getPhone() != null){
            phone.getSelectionModel().select(onlineOrder.getPhone());
        }
        if(onlineOrder.getFax() != null){
            fax.getSelectionModel().select(onlineOrder.getFax());
        }
        if(onlineOrder.getDeliveryAddress() != null){
            delivery_address.getSelectionModel().select(onlineOrder.getDeliveryAddress());
        }
        if(onlineOrder.getAuditStr() != null){
            audit_str.setText(onlineOrder.getAuditStr());
        }
        if(onlineOrder.getUpdateLastStr() != null){
            update_last_str.setText(onlineOrder.getUpdateLastStr());
        }
        if(onlineOrder.getInvalidPeopleStr() != null){
            invalid_people_str.setText(onlineOrder.getInvalidPeopleStr());
        }
        // 审核
        shiro_success.setDisable(false);
        menu_update.setDisable(false);
        if(onlineOrder.getOrderAudit() != null){
            if(onlineOrder.getOrderAudit()){
                shiro_success.setDisable(true);
                menu_update.setDisable(true);
            }else{
                menu_update.setDisable(false);
            }
        }else{
            setShiroControlNO();
            menu_update.setDisable(false);
        }
        // 作废
        if(onlineOrder.getInvalid() != null){
            if(onlineOrder.getInvalid()){
                btn_invalid.setDisable(true);
            }else{
                btn_invalid.setDisable(false);
            }
        }else{
            btn_invalid.setDisable(false);
        }
        generalProductTab();
    }

    /**
     * 加载tableview数据
     */
    private void generalProductTab(){
        tableview.setEditable(true);

        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_category.setCellFactory(column -> EditCell.createStringEditCell());
        col_number.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_usable_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_stock.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());


        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            List<SaleOnlineOrderProduct> productList = iSaleOnlineOrderProductService.listByOnlineOrderAll(order_no.getUserData().toString());
            List<SaleOnlineOrderProductProperty> productPropertyList = new ArrayList<>();
            clearTotalCalcVal();
            int rows = 1; //序号
            for (SaleOnlineOrderProduct p : productList) {
                totalCost(p.getNum()==null?0:p.getNum(), p.getMoney()==null?new BigDecimal("0.00"):p.getMoney(), 0, total_num, tax_total, total_loan, total_money);
                productPropertyList.add(new SaleOnlineOrderProductProperty(p.getId(), rows++,p.getOnlineOrderId(), p.getCustomerNo(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPrice(), p.getMoney(), p.getUsableNum() ,p.getRemark()));
            }
            final ObservableList<SaleOnlineOrderProductProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_id.setCellValueFactory(new PropertyValueFactory<Remark,Long>("id"));
            col_no.setCellValueFactory(new PropertyValueFactory("no"));
            col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
            col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
            col_category.setCellValueFactory(new PropertyValueFactory("category"));
            col_number.setCellValueFactory(new PropertyValueFactory("num"));
            col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
            col_price.setCellValueFactory(new PropertyValueFactory("price"));
            col_money.setCellValueFactory(new PropertyValueFactory("money"));
            col_usable_num.setCellValueFactory(new PropertyValueFactory("usableNum"));
            col_stock.setCellValueFactory(new PropertyValueFactory("ifstock"));
            col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

            tableview.setItems(dataProperty);


            tableview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleOnlineOrderProductProperty>() {
                @Override
                public void changed(ObservableValue<? extends SaleOnlineOrderProductProperty> observableValue, SaleOnlineOrderProductProperty oldItem, SaleOnlineOrderProductProperty newItem) {
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
        }



        //提交数量计算金额  询价订单--询价产品 金额总计
        col_number.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;
                List<SaleOnlineOrderProductProperty> data = tableview.getItems();
                for (SaleOnlineOrderProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(newNum*Double.valueOf(property.getPrice())).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        reloadTotalVal();
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
                List<SaleOnlineOrderProductProperty> data = tableview.getItems();
                for (SaleOnlineOrderProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setPrice(String.valueOf(newPrice));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(Integer.parseInt(property.getNum())*newPrice).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
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
        List<SaleOnlineOrderProductProperty> propertyList = tableview.getItems();
        for (SaleOnlineOrderProductProperty p : propertyList) {
            totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),"", total_num, tax_total, total_loan, total_money);
        }
    }

    /**
     * 清除空间上的值
     */
    @FXML
    private void clearControllerVal(){
        LocalDateTime localDate = LocalDateTime.now();
        order_date.setValue(localDate.toLocalDate());
        customer_no.clear();
        order_remark.clear();
        order_no.clear();
        order_no.setUserData(null);
        customer_no_str.clear();
        order_people.clear();
        contact.getItems().clear();
        zip.clear();
        invoice_title.clear();
        invoice_address.clear();
        audit.clear();
        update_last.clear();
        invalid_people.clear();
        base_remark.clear();
        phone.getItems().clear();
        fax.getItems().clear();
        delivery_address.getItems().clear();
        delivery_address.getSelectionModel().select("");
        audit_str.clear();
        update_last_str.clear();
        invalid_people_str.clear();

        tableview.setItems(null);

        // 合计
        clearTotalCalcVal();
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
    public void setControllerUse(){
        setControllerState(false);
        if(order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())){
            SaleOnlineOrder order = iSaleOnlineOrderService.selectByKey(Long.valueOf(order_no.getUserData().toString()));
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
        order_date.setDisable(bool);
        customer_no.setDisable(true);
        order_remark.setDisable(bool);
        order_no.setDisable(true);
        customer_no_str.setDisable(bool);
        order_people.setDisable(bool);
        contact.setDisable(bool);
        zip.setDisable(bool);
        invoice_title.setDisable(bool);
        invoice_address.setDisable(bool);


        base_remark.setDisable(bool);
        phone.setDisable(bool);
        fax.setDisable(bool);
        delivery_address.setDisable(bool);
        more_customer.setDisable(bool);
        audit.setDisable(true);
        audit_str.setDisable(true);
        update_last.setDisable(true);
        update_last_str.setDisable(true);
        invalid_people.setDisable(true);
        invalid_people_str.setDisable(true);

        tableview.setDisable(bool);
        menu_clearAll.setDisable(bool);
        menu_commit.setDisable(bool);
//        menu_insert.setDisable(bool);
        menu_delete.setDisable(bool);

        tableview.setDisable(bool);

        btn_invalid.setDisable(bool);

        total_loan.setDisable(true);
        total_money.setDisable(true);
        total_num.setDisable(true);
        tax_total.setDisable(true);
    }


    /**
     * 导出 到订货单（采购单）
     */
    @FXML
    public void importOutPurchase(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleOnlineOrder order = iSaleOnlineOrderService.selectByKey(Long.valueOf(id));
            try {

                if(order != null && order.getOrderAudit() && !order.getInvalid()){
                    changeHomeBtn(1,0,3);
                    StageManager.saleOnlineOrder = order;
                    Pane pane1 = StageManager.ORDERCONTENT.get("SalePane");
                    pane1.getChildren().setAll(loader.load("/fxml/sale/purchase_order.fxml"));
                }else{
                    alert_informationDialog("该单据未审核或已作废暂无法进行导出!");
                }
            }catch (Exception e){
                alert_informationDialog("导出失败,单据数据异常!");
            }
        }
    }


    /**
     * 导出 到销货单
     */
    @FXML
    public void importOutSaleGoods(){
        String id = order_no.getUserData()==null?"":order_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleOnlineOrder order = iSaleOnlineOrderService.selectByKey(Long.valueOf(id));
            try {

                if(order != null && order.getOrderAudit() && !order.getInvalid()){
                    changeHomeBtn(1,0,4);
                    StageManager.saleOnlineOrder = order;
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
     * 打开订单查询窗口
     */
    @FXML
    public void OpenMiniQuery() {

        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("OnlineOrderControllerNo", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/online_order_query_mini.fxml"));
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
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("OnlineOrderController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

}
