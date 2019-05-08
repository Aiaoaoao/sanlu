package com.yc.education.controller.sale;


import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;

import com.yc.education.model.Relation;
import com.yc.education.model.customer.Remark;
import com.yc.education.model.sale.ReportRemark;
import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.model.sale.SaleOfferProductProperty;
import com.yc.education.model.customer.RemarkProperty;
import com.yc.education.model.customer.ReportRemarkProperty;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.customer.*;
import com.yc.education.service.sale.IReportRemarkService;
import com.yc.education.service.sale.ISaleOfferProductService;
import com.yc.education.service.sale.ISaleQuotationService;
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
import javafx.util.converter.LongStringConverter;
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
 * 销售-报价单
 */
@Controller
public class QuotationController extends BaseController implements Initializable {

    @Autowired ISaleQuotationService iSaleQuotationService;                 // 报价单
    @Autowired ISaleOfferProductService iSaleOfferProductService;           // 报价单产品
    @Autowired IRemarkService iRemarkService;                               //备注及说明
    @Autowired IReportRemarkService iReportRemarkService;                   //报表备注
    @Autowired ICustomerContactsService iCustomerContactsService;           //客户联系人
    @Autowired ICustomerService iCustomerService;                           //客户基本信息
    @Autowired ICustomerShippingAddressService iCustomerShippingAddressService; // 客户送货地址
    @Autowired RelationService iRelationService;                            // 关联单据

    /**
     * @Description 单据关联容器
     * @Author BlueSky
     * @Date 15:49 2019/4/26
     **/
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;
    // 菜单栏工具
    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页
    @FXML VBox menu_delete;
    @FXML VBox menu_insert;
    @FXML VBox menu_commit;
    @FXML VBox menu_clearAll;
    @FXML VBox menu_update; //修改
    @FXML VBox menu_printing; //打印
    @FXML VBox shiro_success;
    @FXML VBox shiro_cancel;
    @FXML VBox import_out;
    @FXML Label writestate;// 待输入

    @FXML
    DatePicker create_date; // 制单日期
    @FXML
    public TextField customer_no; //客户编号
    @FXML
    public TextField offer_no; // 报价单号
    @FXML
    public TextField customer_no_str; // 客户描述
    @FXML
    DatePicker enquiry_date; // 询价日期
    @FXML
    ComboBox business; // 负责业务
    @FXML
    TextField business_str; // 负责业务描述
    @FXML
    ComboBox tax; // 税别
    @FXML
    TextField discount; // 折扣
    @FXML
    CheckBox special_offer;  // 特价单
    //======================= 基本资料 =========================
    @FXML Button more_customer; // 按钮 - 更多客户
    @FXML
    public TextField amount_receivable; // 应收余额
    @FXML
    TextField audit; // 审核人
    @FXML
    TextField last_modifier; // 最后修改人
    @FXML
    public TextField customer_name; // 客户姓名
    @FXML
    ComboBox currency; // 币别
    @FXML
    TextField audit_str; // 审核人描述
    @FXML
    TextField last_modifier_str; // 最后修改人描述
    @FXML
    TextField single_people; // 制单人
    @FXML
    DatePicker valid_until; // 有效期至
    @FXML
    public ComboBox contact; // 联系人
    @FXML
    public ComboBox telephone; // 联系电话
    @FXML
    public ComboBox fax; // 传真
    @FXML
    public ComboBox address; // 地址
    @FXML
    ComboBox customer_category; // 客户类别
    // ================= 合计下的四个框 ======================
    @FXML
    TextField calc_number;
    @FXML
    TextField calc_money;
    @FXML
    TextField calc_loan;
    @FXML
    TextField calc_tax;
    // ====================== tableview列 ==============================
    @FXML
    public TableView offer_table;
    @FXML
    TableColumn offer_col_id;
    @FXML
    TableColumn offer_col_no;   // 产品序号
    @FXML
    TableColumn offer_col_product_no;
    @FXML
    TableColumn offer_col_product_name;
    @FXML
    TableColumn offer_col_category;
    @FXML
    TableColumn offer_col_num;
    @FXML
    TableColumn offer_col_unit;
    @FXML
    TableColumn offer_col_pricing;
    @FXML
    TableColumn offer_col_discount;
    @FXML
    TableColumn offer_col_price;
    @FXML
    TableColumn offer_col_money;
    @FXML
    TableColumn offer_col_remark;

    @FXML
    TableView remark_table;
    @FXML
    TableColumn remark_id;
    @FXML
    TableColumn remark_content;
    @FXML
    TableView report_table;
    @FXML
    TableColumn report_id;
    @FXML
    TableColumn report_content;

    static int changeId = 0;      // 记录当前编辑计算金额cell 的id值

    /***************************************** 弹出窗口-产品 ********************************************/

    int tablePosition = 0; // 弹出窗口选中的行数

    /***************************************** 弹出窗口-产品-结束 ********************************************/


    private static SpringFxmlLoader loader = new SpringFxmlLoader();
    Stage stage = new Stage();


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

        setComboBox(10L, customer_category, 0);
        tax.getItems().addAll("外加","内含","零税","免税");
        tax.getSelectionModel().select(0);
        setComboBox(7L,currency,0); //币别
        // 设置控件禁用
        setControlDisable();
        business.getItems().clear();
        loadEmployee(business, 0);
        // 初始化进来加载第一条数据
        setMenuValue(1);

        business.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = business.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    business_str.setText(bus);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // 报价产品行双击 调出现有产品窗口
        BaseController.clickEvent(offer_table, data ->{
            tablePosition = offer_table.getSelectionModel().getSelectedIndex();
            moreProductButtonClick();
        }, 2);

        // 折扣修改监听
        discount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String discountText = discount.getText();
                if(discountText != null && discountText.length() > 0 && isNumeric(discountText)){
                    if(Integer.valueOf(discountText) >= 0 && Integer.valueOf(discountText) <= 100){

                        List<SaleOfferProductProperty> list = offer_table.getItems();
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
    }


    /**
     * 更多产品查询
     */
    @FXML
    public void moreProductButtonClick(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("QuotationControllerProduct", this);
        StageManager.CONTROLLER.put("tablePosition", tablePosition);
        pane.getChildren().setAll(new SpringFxmlLoader().load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }



    /**
     * 提交、保存数据
     */
    @FXML
    public synchronized void save(){
        // 检查非空
        if(checkPrimaryNull(offer_no,customer_no,customer_no_str,business_str)){
            return;
        }

        // 特价单
        if(!special_offer.isSelected()){
            List<SaleOfferProductProperty> propertyList =  offer_table.getItems();
            if(propertyList != null){
                Double miniDis = getCustomerMinimumDiscountByCode(customer_no.getText());
                for (SaleOfferProductProperty p : propertyList) {
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


        // 修改人
        createPeople(last_modifier,last_modifier_str);
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();

        SaleQuotation quotation = new SaleQuotation();
        if ( create_date.getValue() != null) {
            try {
                Date date = df.parse(create_date.getValue().toString());
                quotation.setCreateDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (customer_no.getText() != null && !"".equals(customer_no.getText())) {
            quotation.setCustomerNo(customer_no.getText());
        }
        if (offer_no.getText() != null && !"".equals(offer_no.getText()) ) {
            quotation.setOfferNo(offer_no.getText());
        }
        if (customer_no_str.getText() != null && !"".equals(customer_no_str.getText())) {
            quotation.setCustomerNoStr(customer_no_str.getText());
        }
        if (enquiry_date.getValue() != null && !"".equals(enquiry_date.getValue())) {
            try {
                Date date = df.parse(enquiry_date.getValue().toString());
                quotation.setEnquiryDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (business.getSelectionModel().getSelectedItem() != null && !("".equals(business.getSelectionModel().getSelectedItem()))) {
            quotation.setBusiness(business.getSelectionModel().getSelectedItem().toString());
        }
        if (business_str.getText() != null && !"".equals(business_str.getText())) {
            quotation.setBusinessStr(business_str.getText());
        }
        if (tax.getSelectionModel().getSelectedItem() != null ) {
            quotation.setTax(tax.getSelectionModel().getSelectedItem().toString());
        }
        if (discount.getText() != null && !"".equals(discount.getText())) {
            quotation.setDiscount(discount.getText());
        }
        quotation.setSpecialOffer(special_offer.selectedProperty().getValue());

        //======================= 基本资料 =========================
//        if (amount_receivable.getText() != null && !"".equals(amount_receivable.getText())) {
//            quotation.setAmountReceivable(amount_receivable.getText());
//        }
        if (audit.getText() != null && !"".equals(audit.getText())) {
            quotation.setAudit(audit.getText());
        }
        if (last_modifier.getText() != null && !"".equals(last_modifier.getText())) {
            quotation.setLastModifier(last_modifier.getText());
        }
        if (customer_name.getText() != null && !"".equals(customer_name.getText())) {
            quotation.setCustomerName(customer_name.getText());
        }
        if (currency.getSelectionModel().getSelectedItem() != null && !"".equals(currency.getSelectionModel().getSelectedItem())) {
            quotation.setCurrency(currency.getSelectionModel().getSelectedItem().toString());
        }
        if (audit_str.getText() != null && !"".equals(audit_str.getText())) {
            quotation.setAuditStr(audit_str.getText());
        }
        if (last_modifier_str.getText() != null && !"".equals(last_modifier_str.getText())) {
            quotation.setLastModifierStr(last_modifier_str.getText());
        }
        if (single_people.getText() != null && !"".equals(single_people.getText())) {
            quotation.setSinglePeople(single_people.getText());
        }
        if (valid_until.getValue() != null && !"".equals(valid_until.getValue())) {
            try {
                Date date = df.parse(valid_until.getValue().toString());
                quotation.setValidUntil(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (contact.getSelectionModel().getSelectedItem() != null && !"".equals(contact.getSelectionModel().getSelectedItem())) {
            quotation.setContact(contact.getSelectionModel().getSelectedItem().toString());
        }
        if (telephone.getSelectionModel().getSelectedItem() != null && !"".equals(telephone.getSelectionModel().getSelectedItem())) {
            quotation.setTelephone(telephone.getSelectionModel().getSelectedItem().toString());
        }
        if (fax.getSelectionModel().getSelectedItem() != null && !"".equals(fax.getSelectionModel().getSelectedItem())) {
            quotation.setFax(fax.getSelectionModel().getSelectedItem().toString());
        }
        if (address.getSelectionModel().getSelectedItem() != null && !"".equals(address.getSelectionModel().getSelectedItem())) {
            quotation.setAddress(address.getSelectionModel().getSelectedItem().toString());
        }
        if (customer_category.getSelectionModel().getSelectedItem() != null ) {
            quotation.setCustomerCategory(customer_category.getSelectionModel().getSelectedItem().toString());
        }

        if(id == null || "".equals(id)){
            //新增
            quotation.setOrderAudit(false);
            quotation.setAddtime(new Date());
            int rows = iSaleQuotationService.save(quotation);
            // 加载新增数据id
            offer_no.setUserData(quotation.getId().toString());
            returnMsg(rows);
            if(relationLock){
                //添加关联关系
                relation.setRelationName("报价单");
                relation.setRelationId(quotation.getId());
                iRelationService.save(relation);
            }

        }else{
            // 修改
            quotation.setId(Long.valueOf(id));
            int rows = iSaleQuotationService.updateNotNull(quotation);
            returnMsg(rows);
        }
        if(quotation.getId() != null && quotation.getId() != 0L){
            saveOfferProduct(quotation.getId().toString());
            saveRemark(quotation.getId().toString());
            saveReportRemark(quotation.getId().toString());

        }
        showProductView();
        showRemarkView();
        showReportView();
        setControlDisable();
        setBasicValue(quotation);
    }

    /**
     * 保存报价产品view数据
     */
    private void saveOfferProduct(String id){
        if(id != null && !"".equals(id) && offer_table.getItems() != null){
            int tableSize = offer_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                SaleOfferProductProperty property = null;
                if(offer_table.getItems().get(i) != null){
                    property = (SaleOfferProductProperty)offer_table.getItems().get(i);
                }
                SaleOfferProduct offerProduct = new SaleOfferProduct();
                if(property.getProductNo() != null){
                    offerProduct.setProductNo(property.getProductNo());
                }
                if(property.getProductName() != null){
                    offerProduct.setProductName(property.getProductName());
                }
                if(property.getCategory() != null){
                    offerProduct.setCategory(property.getCategory());
                }
                if(property.getNum() != null){
                    offerProduct.setNum(Integer.valueOf(property.getNum()));
                }
                if(property.getUnit() != null){
                    offerProduct.setUnit(property.getUnit());
                }
                if(property.getPricing() != null){
                    offerProduct.setPricing(new BigDecimal(property.getPricing()));
                }
                if(property.getDiscount() != null){
                    offerProduct.setDiscount(property.getDiscount());
                }
                if(property.getPrice() != null){
                    offerProduct.setPrice(new BigDecimal(property.getPrice()));
                }
                if(property.getMoney() != null){
                    offerProduct.setMoney(new BigDecimal(property.getMoney()));
                }
                if(property.getRemark() != null){
                    offerProduct.setRemark(property.getRemark());
                }
                // 添加当前用户ID
                offerProduct.setUserid(1L);
                offerProduct.setQuotationId(Long.valueOf(id));
                if(property.getId() == null || property.getId() == 0){
                    offerProduct.setAddtime(new Date());
                    try {
                        iSaleOfferProductService.save(offerProduct);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    offerProduct.setId(property.getId());
                    try {
                        iSaleOfferProductService.updateNotNull(offerProduct);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存备注及说明view数据
     */
    private void saveRemark(String id){
        if(id != null && !"".equals(id) && remark_table.getItems() != null){
            int tableSize = remark_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                RemarkProperty property = null;
                if(remark_table.getItems().get(i) != null){
                    property = (RemarkProperty)remark_table.getItems().get(i);
                }
                Remark remark = new Remark();
                remark.setTypeid(2);
                remark.setOtherid(Long.valueOf(id));
                remark.setRemark(property.getRemark());
                if(property.getId() == null){
                    remark.setAddtime(new Date());
                    try {
                        iRemarkService.save(remark);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    remark.setId(property.getId());
                    try {
                        iRemarkService.updateNotNull(remark);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存报表说明view数据
     */
    private void saveReportRemark(String id){
        if(id != null && !"".equals(id) && report_table.getItems() != null){
            int tableSize = report_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                ReportRemarkProperty property = null;
                if(report_table.getItems().get(i) != null){
                    property = (ReportRemarkProperty)report_table.getItems().get(i);
                }
                ReportRemark remark = new ReportRemark();
                remark.setTypeid(1);
                remark.setOtherid(Long.valueOf(id));
                remark.setContent(property.getContent());
                if(property.getId() == null){
                    remark.setAddtime(new Date());
                    try {
                        iReportRemarkService.save(remark);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        remark.setId(property.getId());
                        iReportRemarkService.updateNotNull(remark);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 删除报价单
     */
    @FXML
    public synchronized  void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
            if(id != null && !"".equals(id)){
                int rows = iSaleQuotationService.delete(Long.valueOf(id));
                if(rows > 0){
                    // 删除相关信息
                    iSaleOfferProductService.deleteSaleOfferProduct(Long.valueOf(id));
                    // 删除关联单据
                    iRelationService.deleteRelation("报价单",Long.valueOf(id));
                    // todo ...
                    setMenuValue(1);
                    alert_informationDialog("操作成功！");
                }else{
                    alert_informationDialog("操作失败！");
                }
            }
        }

    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        //权限管理
        matchingPermissions("报价单",menu_insert,menu_delete,menu_update,shiro_success,shiro_cancel,menu_printing,menu_clearAll);
        List<SaleQuotation> saleQuotationList = iSaleQuotationService.listSaleQuotationAll("","",page, 1);
        if(saleQuotationList != null && saleQuotationList.size() >0){
            PageInfo<SaleQuotation> pageInfo = new PageInfo<>(saleQuotationList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            setBasicValue(saleQuotationList.get(0));
        }else{
            clearControllerVal();
            setControlDisable();
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
     * 清空合计框中的值
     */
    protected void clearTotalCalcVal(){
        calc_number.setText("0");
        calc_loan.setText("0.00");
        calc_money.setText("0.00");
        calc_tax.setText("0.00");
    }

    /**
     * 加载产品列表
     */
    public void showProductView(){
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();

        if(id != null && !"".equals(id)){

            List<SaleOfferProduct> list = iSaleOfferProductService.listSaleOfferProduct(Long.valueOf(id));
            List<SaleOfferProductProperty> newlist = new ArrayList<>();
            clearTotalCalcVal();
            int rows = 1; // 序号
            for (SaleOfferProduct p : list) {
                totalCost(p.getNum()==null?0:p.getNum(), p.getMoney()==null?new BigDecimal("0.00"):p.getMoney(), tax.getItems()==null?0:tax.getSelectionModel().getSelectedIndex()+1, calc_number, calc_tax, calc_loan, calc_money);
                SaleOfferProductProperty product = new SaleOfferProductProperty(p.getId(),rows++, p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getDiscount(), p.getPrice(), p.getMoney(), p.getRemark(), 0L);
                newlist.add(product);
            }
            generalProduct(newlist);
        }


    }

    /**
     * @Description 刷新表格数据
     * @Author BlueSky
     * @Date 20:34 2019/4/24
     **/
    public void generalProduct(List<SaleOfferProductProperty> newlist){

        // 报价产品
        offer_table.setEditable(true);

        offer_col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_category.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_num.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_unit.setCellFactory(column -> EditCell.createStringEditCell());
//        offer_col_pricing.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_discount.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_price.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_money.setCellFactory(column -> EditCell.createStringEditCell());
        offer_col_remark.setCellFactory(column -> EditCell.createStringEditCell());


        final ObservableList<SaleOfferProductProperty> data = FXCollections.observableArrayList(newlist);
        offer_col_id.setCellValueFactory(new PropertyValueFactory<SaleOfferProduct,LongStringConverter>("id"));
        offer_col_no.setCellValueFactory(new PropertyValueFactory("no"));
        offer_col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        offer_col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        offer_col_category.setCellValueFactory(new PropertyValueFactory("category"));
        offer_col_num.setCellValueFactory(new PropertyValueFactory("num"));
        offer_col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        offer_col_pricing.setCellValueFactory(new PropertyValueFactory("pricing"));
        offer_col_discount.setCellValueFactory(new PropertyValueFactory("discount"));
        offer_col_price.setCellValueFactory(new PropertyValueFactory("price"));
        offer_col_money.setCellValueFactory(new PropertyValueFactory("money"));
        offer_col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        offer_table.setItems(data);

        offer_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleOfferProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends SaleOfferProductProperty> observableValue, SaleOfferProductProperty oldItem, SaleOfferProductProperty newItem) {
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


        //提交单价计算金额  询价订单--询价产品 折扣
        offer_col_discount.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                Double  dis = 1.0;
                if(newValue != null && !"".equals(newValue)){
                    dis=Integer.parseInt(newValue)/100.0;
                }
                List<SaleOfferProductProperty> data = offer_table.getItems();
                for (SaleOfferProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        //计算金额
                        BigDecimal price = new BigDecimal(property.getPricing()).multiply(new BigDecimal(dis.toString())).setScale(2,BigDecimal.ROUND_UP);
                        BigDecimal money = new BigDecimal(property.getNum()).multiply(price).setScale(2, BigDecimal.ROUND_UP);
                        property.setPrice(price.toString());
                        property.setMoney(money.toString());
                        reloadTotalVal(); // 重新计算总金额、数量等信息
                    }
                }
            }
        });

        //提交数量计算金额  询价订单--询价产品 金额总计
        offer_col_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;
                List<SaleOfferProductProperty> data = offer_table.getItems();
                for (SaleOfferProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(newNum*Double.valueOf(property.getPrice())).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        reloadTotalVal(); // 重新计算总金额、数量等信息
                    }
                }
            }
        });
        //提交单价计算金额  询价订单--询价产品 金额总计
        offer_col_price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                Double  newPrice = newValue != null && !"".equals(newValue)?newPrice=Double.parseDouble(newValue):0.00;
                List<SaleOfferProductProperty> data = offer_table.getItems();
                for (SaleOfferProductProperty property :data) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setPrice(String.valueOf(newPrice));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(Integer.parseInt(property.getNum())*newPrice).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        reloadTotalVal(); // 重新计算总金额、数量等信息
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
        List<SaleOfferProductProperty> propertyList = offer_table.getItems();
        for (SaleOfferProductProperty p : propertyList) {
            totalCost(p.getNum()==null||"".equals(p.getNum())?0:Integer.valueOf(p.getNum()),p.getMoney()==null||"".equals(p.getMoney())?new BigDecimal("0.00"):new BigDecimal(p.getMoney()),"", calc_number, calc_tax, calc_loan, calc_money);
        }
    }
    /**
     * 加载备注列表
     */
    public void showRemarkView(){
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            // 备注及说明
            remark_table.setEditable(true);

            remark_content.setCellFactory(column -> EditCell.createStringEditCell());

            List<Remark> remarkList = iRemarkService.listRemark(Long.valueOf(id),"2");
            List<RemarkProperty> newRemarkList = new ArrayList<>();
            remarkList.forEach(p->{
                newRemarkList.add(new RemarkProperty(p.getId(),p.getRemark()));
            });
            final ObservableList<RemarkProperty> dataRemark = FXCollections.observableArrayList(newRemarkList);
            remark_id.setCellValueFactory(new PropertyValueFactory<Remark,Long>("id"));
            remark_content.setCellValueFactory(new PropertyValueFactory("remark"));//映射

            remark_table.setItems(dataRemark);
        }

    }

    /**
     * 加载报表列表
     */
    public void showReportView(){
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id != null && !"".equals(id)) {
            // 报表备注
            report_table.setEditable(true);

            report_content.setCellFactory(column -> EditCell.createStringEditCell());

            List<ReportRemark> reportRemarkList = iReportRemarkService.listReportRemark(Long.valueOf(id), "1");
            List<ReportRemarkProperty> newReportRemarkList = new ArrayList<>();
            reportRemarkList.forEach(p -> {
                newReportRemarkList.add(new ReportRemarkProperty(p.getId(), p.getContent()));
            });
            final ObservableList<ReportRemarkProperty> dataReportRemark = FXCollections.observableArrayList(newReportRemarkList);
            report_id.setCellValueFactory(new PropertyValueFactory("id"));
            report_content.setCellValueFactory(new PropertyValueFactory("content"));//映射
            report_table.setItems(dataReportRemark);
        }
    }


    /**
     * 报价产品view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfOfferPorductTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addOfferProduct();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfOfferProduct();
        }
    }

    /**
     * 备注及说明view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfRemarkTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addRemark();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfRemark();
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
            addReportRemark();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfReportRemark();
        }
    }


    /**
     * 添加报价产品行
     */
    public void addOfferProduct() {

        ObservableList<SaleOfferProductProperty> list = offer_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new SaleOfferProductProperty((list.size()+1)+"","", "", "", 0, "", new BigDecimal("0.00"), "100", new BigDecimal("0.00"), new BigDecimal("0.00"), ""));
        // 报价产品
        generalProduct(list);
    }

    /**
     * 添加备注及说明行
     */
    public void addRemark() {

        ObservableList<RemarkProperty> list = remark_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new RemarkProperty(""));
        remark_table.setItems(list);
    }

    /**
     * 添加报表备注行
     */
    public void addReportRemark() {

        ObservableList<ReportRemarkProperty> list = report_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new ReportRemarkProperty(""));
        report_table.setItems(list);
    }

    /**
     * 删除报价产品
     */
    private void deleteRowOfOfferProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                TablePosition pos = (TablePosition) offer_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                SaleOfferProductProperty product = (SaleOfferProductProperty)offer_table.getItems().get(index);
                if(product.getId() != null && product.getId() != 0L){
                    int rows = iSaleOfferProductService.delete(product.getId());
                    returnMsg(rows);
                }
                final ObservableList<SaleOfferProductProperty> dataProperty = offer_table.getItems();
                final ObservableList<SaleOfferProductProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                offer_table.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除备注及说明行
     */
    private void deleteRowOfRemark(){
        // 取得当前行的数据
        try {
            if(remark_table.getSelectionModel().getSelectedCells().size() == 0){return;}
            TablePosition pos = (TablePosition) remark_table.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            RemarkProperty remark = (RemarkProperty)remark_table.getItems().get(index);
            if(remark.getId() != null && remark.getId() != 0L){
                int rows = iRemarkService.delete(remark.getId());
                returnMsg(rows);
            }
            final ObservableList<RemarkProperty> dataProperty = remark_table.getItems();
            final ObservableList<RemarkProperty> newDataProperty = FXCollections.observableArrayList();
            for (int i = 0; i < dataProperty.size(); i++) {
                if(i != index){
                    newDataProperty.add(dataProperty.get(i));
                }
            }
            remark_table.setItems(newDataProperty);
        }catch (Exception e){
            alert_informationDialog("请选择需要删除的行！");
            e.printStackTrace();
        }
    }

    /**
     * 删除报表备注
     */
    private void deleteRowOfReportRemark(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(report_table.getSelectionModel().getSelectedCells().size() == 0){return;}
                TablePosition pos = (TablePosition) report_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                ReportRemarkProperty remark = (ReportRemarkProperty)report_table.getItems().get(index);
                if(remark.getId() != null && remark.getId() != 0L){
                    int rows = iReportRemarkService.delete(remark.getId());
                    returnMsg(rows);
                }
                final ObservableList<ReportRemarkProperty> dataProperty = report_table.getItems();
                final ObservableList<ReportRemarkProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                report_table.setItems(newDataProperty);
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }



    /**
     * 新增
     */
    @FXML
    public void add(){
        clearControllerVal();
        NumberUtil.changeStatus(writestate,1);
        offer_no.setText(createOrderNo(iSaleQuotationService.getMaxOrderNo()));
        enquiry_date.setValue(localDate.toLocalDate());
        createPeople(single_people);
        setControlUnDisable();
    }


    /**
     * 设置值
     */
    public void setBasicValue(SaleQuotation sale){

        clearControllerVal();
        if(sale==null){
            return;
        }

        if(sale.getId() != null && !"".equals(sale.getId())){
            offer_no.setUserData(sale.getId());
        }
        if(sale.getCreateDate() != null){
            create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(sale.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(sale.getCustomerNo() != null && !"".equals(sale.getCustomerNo())){
            customer_no.setText(sale.getCustomerNo());
        }
        if(sale.getOfferNo() != null && !"".equals(sale.getOfferNo())){
            offer_no.setText(sale.getOfferNo());
        }
        if(sale.getCustomerNoStr() != null && !"".equals(sale.getCustomerNoStr())){
            customer_no_str.setText(sale.getCustomerNoStr());
        }
        if(sale.getEnquiryDate() != null){
            enquiry_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(sale.getEnquiryDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(sale.getBusiness() != null && !"".equals(sale.getBusiness())){
            business.getSelectionModel().select(sale.getBusiness());
        }
        if(sale.getBusinessStr() != null && !"".equals(sale.getBusinessStr())){
            business_str.setText(sale.getBusinessStr());
        }
        if(sale.getTax() != null && !"".equals(sale.getTax())){
            tax.getSelectionModel().select(sale.getTax());
        }
        if(sale.getDiscount() != null && !"".equals(sale.getDiscount())){
            discount.setText(sale.getDiscount());
        }
        if(sale.getSpecialOffer() != null){
            special_offer.setSelected(sale.getSpecialOffer());
        }
//        if(sale.getAmountReceivable() != null && !"".equals(sale.getAmountReceivable())){
//            amount_receivable.setText(sale.getAmountReceivable());
//        }
        if(sale.getAudit() != null && !"".equals(sale.getAudit())){
            audit.setText(sale.getAudit());
        }
        if(sale.getLastModifier() != null && !"".equals(sale.getLastModifier())){
            last_modifier.setText(sale.getLastModifier());
        }
        if(sale.getCustomerName() != null && !"".equals(sale.getCustomerName())){
            customer_name.setText(sale.getCustomerName());
        }
        if(sale.getCurrency() != null && !"".equals(sale.getCurrency())){
            currency.getSelectionModel().select(sale.getCurrency());
        }
        if(sale.getAuditStr() != null && !"".equals(sale.getAuditStr())){
            audit_str.setText(sale.getAuditStr());
        }
        if(sale.getLastModifierStr() != null && !"".equals(sale.getLastModifierStr())){
            last_modifier_str.setText(sale.getLastModifierStr());
        }
        if(sale.getSinglePeople() != null && !"".equals(sale.getSinglePeople())){
            single_people.setText(sale.getSinglePeople());
        }
        if(sale.getValidUntil() != null && !"".equals(sale.getValidUntil())){
            valid_until.setValue(LocalDate.parse(DateUtils.getSpecifyDate(sale.getValidUntil(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(sale.getContact() != null && !"".equals(sale.getContact())){
            contact.getSelectionModel().select(sale.getContact());
        }
        if(sale.getTelephone() != null && !"".equals(sale.getTelephone())){
            telephone.getSelectionModel().select(sale.getTelephone());
        }
        if(sale.getFax() != null && !"".equals(sale.getFax())){
            fax.getSelectionModel().select(sale.getFax());
        }
        if(sale.getAddress() != null && !"".equals(sale.getAddress())){
            address.getSelectionModel().select(sale.getAddress());
        }
        if(sale.getCustomerCategory() != null && !"".equals(sale.getCustomerCategory())){
            customer_category.getSelectionModel().select(sale.getCustomerCategory());
        }

        shiro_success.setDisable(false);
        shiro_cancel.setDisable(false);
        menu_update.setDisable(false);
        if(sale.getOrderAudit() != null){
            if(sale.getOrderAudit()){
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



        showProductView();
        showRemarkView();
        showReportView();
    }

    /**
     * 清除值
     */
    @FXML
    public void clearControllerVal(){
        LocalDateTime localDate = LocalDateTime.now();
        offer_no.setUserData(null);
        offer_no.clear();
        create_date.setValue(localDate.toLocalDate());
        customer_no.clear();
        customer_no_str.clear();
        enquiry_date.setValue(localDate.toLocalDate());
        business_str.clear();
        business.getSelectionModel().select(0);
        try {
            String bus = business.getSelectionModel().getSelectedItem().toString();
            bus = bus.substring(bus.indexOf(')')+1, bus.length());
            business_str.setText(bus);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
//        business.getSelectionModel().selectFirst();
        tax.getSelectionModel().selectFirst();
        discount.setText("100");
        special_offer.setSelected(false);

        amount_receivable.clear();
        audit.clear();
        last_modifier.clear();
        customer_name.clear();
        currency.getSelectionModel().selectFirst();
        audit_str.clear();
        last_modifier_str.clear();
        single_people.clear();
        valid_until.setValue(LocalDate.parse(DateUtils.getSpecifyDate(DateUtils.getPreviousOrNextMonthsOfDate(new Date(),1),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        contact.getItems().clear();
        contact.getSelectionModel().select("");
        telephone.getItems().clear();
        telephone.getSelectionModel().select("");
        fax.getItems().clear();
        fax.getSelectionModel().select("");
        address.getItems().clear();
        address.getSelectionModel().select("");
        customer_category.getSelectionModel().selectFirst();




        clearTotalCalcVal();

        offer_table.setItems(null);
        remark_table.setItems(null);
        report_table.setItems(null);
    }

    /**
     * 设置控件禁用
     */
    public void setControlDisable(){
        setControlState(true);
        setMenuControlState(true);
    }

    /**
     * 设置控件为可用
     */
    @FXML
    public void setControlUnDisable(){
        setControlState(false);
        setMenuControlState(false);
    }


    /**
     * 设置控件状态
     * @param bool
     */
    private void setControlState(Boolean bool){
        if(bool){
            NumberUtil.changeStatus(writestate,0);
        }else{
            NumberUtil.changeStatus(writestate,2);
        }
        more_customer.setDisable(bool);
        create_date.setDisable(bool);
        customer_no.setDisable(true);
        offer_no.setDisable(true);
        customer_no_str.setDisable(bool);
        enquiry_date.setDisable(bool);
        business.setDisable(bool);
        business_str.setDisable(bool);
        tax.setDisable(bool);
        discount.setDisable(bool);
        special_offer.setDisable(bool);

        amount_receivable.setDisable(true);
        audit.setDisable(true);
        last_modifier.setDisable(true);
        customer_name.setDisable(bool);
        currency.setDisable(bool);
        audit_str.setDisable(true);
        last_modifier_str.setDisable(true);
        single_people.setDisable(true);
        valid_until.setDisable(bool);
        contact.setDisable(bool);
        telephone.setDisable(bool);
        fax.setDisable(bool);
        address.setDisable(bool);
        customer_category.setDisable(bool);
        calc_number.setDisable(bool);
        calc_money.setDisable(bool);
        calc_loan.setDisable(bool);
        calc_tax.setDisable(bool);

        offer_table.setDisable(bool);
        remark_table.setDisable(bool);
        report_table.setDisable(bool);
    }

    /**
     * 审核通过
     */
    private void setShiroControlYES(){
        shiro_cancel.setDisable(false);
        shiro_success.setDisable(true);
        import_out.setDisable(false);
        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        import_out.setDisable(true);
        menu_update.setDisable(false);
    }

    /**
     *  设置菜单控件状态
     * @param bool 
     */
    private void setMenuControlState(Boolean bool){
        menu_clearAll.setDisable(bool);
        menu_commit.setDisable(bool);
        menu_delete.setDisable(bool);
//        menu_insert.setDisable(bool);
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id == null && "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }
        shiro_cancel.setDisable(false);
        shiro_success.setDisable(true);
        import_out.setDisable(false);
        lastUpdatePeopel(audit, audit_str);
        shiroUpdateData(true);

        menu_update.setDisable(true);
    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool){
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(Long.valueOf(id));
            saleQuotation.setOrderAudit(bool);
            saleQuotation.setLastModifier(last_modifier.getText());
            saleQuotation.setLastModifierStr(last_modifier_str.getText());
            saleQuotation.setAudit(audit.getText());
            saleQuotation.setAuditStr(audit_str.getText());
            iSaleQuotationService.updateNotNull(saleQuotation);

        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel(){
        // 有效单据验证
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id == null || "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }

        if(!iRelationService.isRelation("报价单",Long.valueOf(id))){
            shiro_cancel.setDisable(true);
            shiro_success.setDisable(false);
            import_out.setDisable(true);
            lastUpdatePeopel(audit, audit_str);
            shiroUpdateData(false);

            menu_update.setDisable(false);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }

    }


    /**
     * 导出 到订货单
     */
    @FXML
    public void importOut(){
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(Long.valueOf(id));
            try {
                if(saleQuotation != null && saleQuotation.getOrderAudit()){
                    if(getPermissions("28_209_4")){
                        changeHomeBtn(1,0,3);
                        StageManager.saleQuotation = saleQuotation;
                        List<SaleOfferProduct> saleOfferProductList = iSaleOfferProductService.listSaleOfferProduct(saleQuotation.getId());
                        if(saleOfferProductList != null){
                            StageManager.PRODUCT_LIST = saleOfferProductList;
                        }
                        Pane pane1 = StageManager.ORDERCONTENT.get("SalePane");

                        //
                        pane1.getChildren().setAll(loader.load("/fxml/sale/purchase_order.fxml"));
                    }else{
                        alertPermissions();
                    }
                }else{
                    alert_informationDialog("该单据未审核无法进行导出!");
                }
            }catch (Exception e){
                alert_informationDialog("该单据未审核无法进行导出!");
            }
        }
    }

    /**
     * 导出 到销货单
     */
    @FXML
    public void importOutSaleGoods(){
        String id = offer_no.getUserData()==null?"":offer_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(Long.valueOf(id));
            try {

                if(saleQuotation != null && saleQuotation.getOrderAudit()){
                    if(getPermissions("32_1016_4")){
                        changeHomeBtn(1,0,4);
                        StageManager.saleQuotation = saleQuotation;
                        Pane pane1 = StageManager.ORDERCONTENT.get("SalePane");
                        pane1.getChildren().setAll(loader.load("/fxml/sale/sale_slip.fxml"));
                    }else{
                        alertPermissions();
                    }
                }else{
                    alert_informationDialog("该单据未审核无法进行导出!");
                }
            }catch (Exception e){
                alert_informationDialog("该单据未审核无法进行导出!");
            }
        }
    }

    /**
     * 导入
     */
    @FXML
    public void importIn(){

    }



    /**
     * 打开订单查询窗口
     */
    @FXML
    public void OpenQuotationMiniQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        stage.setTitle("报价单查询");
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("QuotationControllerNo", this);

        pane.getChildren().setAll(loader.load("/fxml/sale/quotation_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }



    //导出询价单
    public void importOutInquiry(){

        String orderids =  offer_no.getUserData()==null?"":offer_no.getUserData().toString();

        long id = 0;

        if(!"".equals(orderids))id = Long.parseLong(orderids);

        SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(id);

        if(saleQuotation != null){

            if(saleQuotation.getOrderAudit()){
                if(getPermissions("2_109_4")){
                    changeHomeBtn(1,2,1);

                    StageManager.saleQuotation = saleQuotation;

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


    //导出采购订单
    public void importPurchaseOrder(){




        String orderids =  offer_no.getUserData()==null?"":offer_no.getUserData().toString();

        long id = 0;

        if(!"".equals(orderids))id = Long.parseLong(orderids);

        SaleQuotation saleQuotation = iSaleQuotationService.selectByKey(id);

        if(saleQuotation != null){

            if(saleQuotation.getOrderAudit()){
                if(getPermissions("9_142_4")){
                    changeHomeBtn(1,2,2);

                    StageManager.saleQuotation = saleQuotation;

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
        stage.setTitle("现有客户查询");
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("QuotationController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

}
