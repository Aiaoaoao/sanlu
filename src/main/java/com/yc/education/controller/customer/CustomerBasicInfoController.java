package com.yc.education.controller.customer;


import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.account.AccountReceipt;
import com.yc.education.model.customer.*;
import com.yc.education.model.sale.*;
import com.yc.education.model.customer.RemarkProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.UsersService;
import com.yc.education.service.customer.*;
import com.yc.education.service.sale.*;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import lombok.val;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.math.BigDecimal;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.collections.*;

/**
 * 客户基本资料
 *
 * @Author: BlueSky
 * @Date: 2018/8/15 15:08
 */
@Controller
public class CustomerBasicInfoController extends BaseController implements Initializable {

//    @Autowired ICustomerShippingAddressService addressService;// 送货地址
    @Autowired UsersService usersService; // 用户
    @Autowired ICustomerService iCustomerService;// 客户基本信息
    @Autowired ICustomerBasicService iCustomerBasicService; // 客户基本资料
    @Autowired ICustomerDetailInfoService iCustomerDetailInfoService;// 客户详情
    @Autowired ICustomerBusinessLeaderService iCustomerBusinessLeaderService; // 三禄负责人
    @Autowired ICustomerContactsService iCustomerContactsService; // 客户联系人
    @Autowired ICustomerDataMaintenanceService iCustomerDataMaintenanceService; // 资料维护
    @Autowired IRemarkService iRemarkService;// 备注及留言
    @Autowired ICustomerShippingAddressService iCustomerShippingAddressService;// 送货地址
    @Autowired DataSettingService iDataSettingService;// 基本数据
    @Autowired IInvoiceService iInvoiceService;  // 发票详情
    @Autowired ISaleQuotationService iSaleQuotationService;  // 报价单
    @Autowired ISaleGoodsService iSaleGoodsService;  // 销售单
    @Autowired ISaleReturnGoodsService iSaleReturnGoodsService;  // 销退单
    @Autowired ISaleOfferProductService iSaleOfferProductService;  // 报价产品
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;  // 销售单产品
    @Autowired ISaleReturnGoodsProductService iSaleReturnGoodsProductService;  // 销退单产品

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页


    //======================================== 送货地址=============================================
    @FXML
    TableView shipping_address_table;
    @FXML
    private TableColumn address_col_id;
    @FXML
    private TableColumn address_col_contact;
    @FXML
    private TableColumn address_col_address;
    @FXML
    private TableColumn address_col_phone;
    @FXML
    private TableColumn address_col_setting;
    @FXML
    private TableColumn address_col_type;
    @FXML
    private TableColumn address_col_title;
    @FXML
    private ComboBox address_combox_type;
    @FXML
    private TextField address_txt_contact;
    @FXML
    private TextField address_txt_info;
    @FXML
    private TextField address_txt_phone;
    @FXML
    private TextField address_txt_name; // 名称
    @FXML
    Button address_btn_sure;
    /*================================================== 上边模块 =====================================================================*/
    // 客户编号
    @FXML
    public TextField code;
    // 客户姓名
    @FXML
    public TextField customer_name;
    // 注册地址
    @FXML
    public TextField register_address;
    // 客户简称
    @FXML
    public TextField customer_initials;
    // 客户隐藏id
    @FXML
    public TextField customer_id;

    @FXML
    Button more;

    // 当前页
    private static int page = 0;
    // 页大小
    private final static int rows = 1;
    //=========================================== 基本信息 =====================================================
    // 国家id下拉框
    @FXML
    private ComboBox base_combox_country_id;
    //国家文本框
    @FXML
    private TextField base_country;
    @FXML
    private TextField base_phone;
    @FXML
    private ComboBox base_payment_method; // 结算方式
    @FXML
    private TextField base_initial_quota;
    @FXML
    private ComboBox base_initial_quota_money_type; // 期初额度调整
    @FXML
    private DatePicker base_rush_money_date;
    @FXML
    private TextField base_shipping_address;
    @FXML
    private TextField base_shipping_address_remark;
    @FXML
    private TextField base_primary_contact;
    @FXML
    private TextField base_archivist;
    @FXML
    private TextField base_last_modifier;
    @FXML
    private DatePicker base_establish_date;
    @FXML
    CheckBox che_dis_use; // 暂停使用
    @FXML
    private TextField base_zip_code;
    @FXML
    private TextField base_fax;
    @FXML
    private TextField base_credit_line;
    @FXML
    private ComboBox base_credit_line_money_type;
    @FXML
    private TextField base_credit_line_remark;
    @FXML
    private TextField base_minimum_discount;
    @FXML
    private TextField base_contact_number;
    @FXML
    private DatePicker base_document_date;
    @FXML
    private DatePicker base_last_modified_date;
    @FXML
    private TextField base_stop_use;
    // 国家名称
    List<DataSetting> countryList = new ArrayList<>();
    //========================================== 详细资料 =============================================
    @FXML TextField customer_property;
    //客户类别
    @FXML
    ComboBox info_customer_category;
    // 行业
    @FXML
    ComboBox info_industry;
    // 客户来源
    @FXML
    ComboBox info_customer_source;
    // 开户银行
    @FXML
    TextField info_bank;
    // 银行账户
    @FXML
    TextField info_bank_acount;
    // 税务登记号
    @FXML
    TextField info_tax_no;
    // 所属区域
    @FXML
    ComboBox info_area_com;
    // 所属区域
    @FXML
    TextField info_area_str;
    // 账款备注
    @FXML
    TextField info_acount_remark;
    // 地区
    @FXML
    ComboBox info_region;
    // 客户等级
    @FXML
    ComboBox info_customer_level;
    // 所属公司
    @FXML
    ComboBox info_company;
    // 销售单需回传
    @FXML
    CheckBox info_comes_back;
    // 发票详情tableview
    @FXML
    TableView customer_invoice_tab;
    @FXML
    TableColumn invoice_id_col;
    @FXML
    TableColumn invoice_usual_col;
    @FXML
    TableColumn invoice_title_col;
    @FXML
    TableColumn invoice_address_col;
    @FXML
    TableColumn invoice_phone_col;
    @FXML
    TableColumn invoice_contact_col;
    @FXML
    TableColumn invoice_remark_col; // 备注
    @FXML
    TableColumn invoice_disable_col; // 停用
    @FXML
    TableColumn invoice_disable_date_col; //停用日期


    //================================================== 业务负责人 =====================================================
    // id
    @FXML
    TableColumn leader_id;
    // 员工编号
    @FXML
    TableColumn leader_no;
    // 姓名
    @FXML
    TableColumn leader_name;
    // 主要负责人
    @FXML
    TableColumn leader_primary;
    @FXML
    TableColumn leader_assistant;//助理
    // 备注
    @FXML
    TableColumn leader_remark;
    // 业务负责人tableview
    @FXML
    public TableView customer_business_leader_tab;

    //================================================== 联系人 =====================================================
    // id
    @FXML
    TableColumn contact_id;
    // 主要负责人
    @FXML
    TableColumn contact_primary;
    // 姓名
    @FXML
    TableColumn contact_name;
    // 部门
    @FXML
    TableColumn contact_department;
    // 职务
    @FXML
    TableColumn contact_position;
    // 电话
    @FXML
    TableColumn contact_telephone;
    // 传真
    @FXML
    TableColumn contact_fax;
    // 手机
    @FXML
    TableColumn contact_phone;
    // 邮箱
    @FXML
    TableColumn contact_mail;
    // 备注
    @FXML
    TableColumn contact_remark;
    // 联系人tableview
    @FXML
    TableView customer_contact_tab;
    //===================================== 资料维护 ============================================
    // ID号
    @FXML
    TableColumn data_id;
    // 建档编号
    @FXML
    TableColumn data_no;
    // 建档日期
    @FXML
    TableColumn data_create_date;
    // 负责人
    @FXML
    TableColumn data_leader_people;
    // 采购人
    @FXML
    TableColumn data_purcharse_people;
    // 联络人
    @FXML
    TableColumn data_contact;
    // 电话
    @FXML
    TableColumn data_telephone;

    @FXML
    TableColumn data_fax; // 传真
    @FXML
    TableColumn data_startup_year;   // 创业年度
    @FXML
    TableColumn data_employee_number; // 员工数量
    @FXML
    TableColumn data_last_year_business;// 去年营业
    @FXML
    TableColumn date_year_plan;// 今年预计
    @FXML
    TableColumn date_industry;// 工业形态
    @FXML
    TableView customer_data_maintenance_tab; // 资料维护tableview
    //===================================== 备注及说明 ============================================
    // ID号
    @FXML
    TableColumn remark_id;
    // 备注及说明
    @FXML
    TableColumn remark_remark;
    // 备注及说明tableview
    @FXML
    TableView customer_remark_tab;

    //============================================== 菜单 ===============================================
    @FXML
    VBox menu_clearAll;
    @FXML
    VBox menu_commit;
    @FXML
    VBox menu_insert;
    @FXML
    VBox menu_update;
    @FXML
    VBox menu_delete;
    @FXML
    VBox menu_printing;

    //=============================================== 交易查询 ================================================
    @FXML
    DatePicker order_date_ben;
    @FXML
    DatePicker order_date_end;
    @FXML
    CheckBox che_order_quo;   // 报价
    @FXML
    CheckBox che_order_sale;   // 销货
    @FXML
    CheckBox che_order_return;   // 销退
    @FXML
    CheckBox che_order_move;   // 移动
    @FXML
    CheckBox che_order_out;   // 销货出
    @FXML
    CheckBox che_order_order;   // 订货

    @FXML
    TableColumn col_order_id; // id
    @FXML
    TableColumn col_order_date;   // 制单日期
    @FXML
    TableColumn col_order_type; // 单据类型
    @FXML
    TableColumn col_order_no;// 单号
    @FXML
    TableColumn col_order_reference;// 参考单号
    @FXML
    TableColumn col_order_business;// 业务负责
    @FXML
    TableColumn col_order_total;// 金额总计
    @FXML
    TableView order_tab; // 交易单据 tableview

    @FXML
    DatePicker product_date_ben;
    @FXML
    DatePicker product_date_end;
    @FXML
    CheckBox che_product_quo;   // 报价
    @FXML
    CheckBox che_product_sale;   // 销货
    @FXML
    CheckBox che_product_return;   // 销退
    @FXML
    CheckBox che_product_move;   // 移动
    @FXML
    CheckBox che_product_out;   // 销货出
    @FXML
    CheckBox che_product_order;   // 订货

    @FXML
    TableColumn col_product_id; // id
    @FXML
    TableColumn col_product_no;   // 产品编号
    @FXML
    TableColumn col_product_name; // 产品名称
    @FXML
    TableColumn col_product_date;// 制单日期
    @FXML
    TableColumn col_product_type;// 单据类型
    @FXML
    TableColumn col_product_single;// 单号
    @FXML
    TableColumn col_product_num; // 数量
    @FXML
    TableColumn col_product_price;   // 单价
    @FXML
    TableColumn col_product_tax; // 税别
    @FXML
    TableColumn col_product_remark;// 备注
    @FXML
    TableView product_tab; // 交易产品详情 tableview

    //===================================== 全局 ============================================
    private static SpringFxmlLoader loader = new SpringFxmlLoader();
    Stage stage = new Stage();
    int tablePosition = 0; // 弹出窗口选中的行数
    // TAB 大面板
    @FXML
    TabPane tab;
    // 存储当前面板选项卡位置
    private String current_pane_str = "基本资料";

    // 日期格式
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    LocalDateTime localDate = LocalDateTime.now();




    /**
     * 初始化
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        long startTime = System.currentTimeMillis();
        // 国家-复选框值
        base_combox_countrySetVal();
        // 地址类型-复选框值
        setComboBox(34L, address_combox_type, 0);
        // 结算方式
        loadSettlementMethod(base_payment_method);
        // 详细信息 - 所属公司
        setComboBox(27L, info_company, 0);
        // 详细信息 - 所属区域
        setComboBox(31L, info_area_com, 0);
        // 详细信息 - 客户类别
        setComboBox(10L, info_customer_category, 0);
        // 详细信息 - 行业
        setComboBox(11L, info_industry, 0);
        // 详细信息 - 地区
        setComboBox(12L, info_region, 0);
        // 详细信息 - 客户来源
        setComboBox(13L, info_customer_source, 0);
        // 详细信息 - 客户等级
        setComboBox(14L, info_customer_level, 0);
        // 基本资料-复选框-货币类型
        setComboBox(7, base_initial_quota_money_type, 0);
        setComboBox(7, base_credit_line_money_type, 0);
        // 加载第一条客户数据
        setMenuValue(1);


        // 客户编号变更监听
        customerCodeChange();

        //查询面板中的所有内容
        selectAllPane();


        // tabpane 面板切换监听
//        tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
//            @Override
//            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
//                String customerid = customer_id.getText();
//                current_pane_str = tab.getSelectionModel().getSelectedItem().getText().toString();
//                switchCase(current_pane_str,customerid);
//            }
//        });



        // 下拉框值改变事件监听
        info_area_com.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    if(info_area_com.getSelectionModel().getSelectedItem() != null){
                        info_area_str.setText(info_area_com.getSelectionModel().getSelectedItem().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        long stopTime = System.currentTimeMillis();
        System.err.println("耗时："+(stopTime - startTime));

    }

    /**
     * 查询交易单据
     */
    @FXML
    public void findProductOrder() {
        Boolean cheQuo = che_order_quo.isSelected(), cheSale = che_order_sale.isSelected(), cheReturn = che_order_return.isSelected();
        LocalDate ben = order_date_ben.getValue();
        LocalDate end = order_date_end.getValue();
        String benStr = "", endStr = "";
        if (ben != null) {
            benStr = ben.toString();
        }
        if (end != null) {
            endStr = end.toString();
        }
        List<SaleQuotation> quotationList = null;
        if (cheQuo) {
            quotationList = iSaleQuotationService.listTimeWhere(benStr, endStr);
        }
        List<SaleGoods> goodsList = null;
        if (cheSale) {
            goodsList = iSaleGoodsService.listTimeWhere(benStr, endStr);
        }
        List<SaleReturnGoods> returnGoodsList = null;
        if (cheReturn) {
            returnGoodsList = iSaleReturnGoodsService.listTimeWhere(benStr, endStr);
        }
        List<ProductOrderProperty> propertyList = new ArrayList<>();
        if (quotationList != null) {
            quotationList.forEach(p -> propertyList.add(new ProductOrderProperty(p.getId()
                    , p.getAddtime(), "报价单", p.getOfferNo(), p.getOfferNo(), p.getBusiness(), new BigDecimal("0.00"))));
        }
        if (goodsList != null) {
            goodsList.forEach(p -> propertyList.add(new ProductOrderProperty(p.getId(), p.getAddtime(), "销货单", p.getSaleNo(), p.getSaleNo(), p.getBusinessLeader(), new BigDecimal("0.00"))));
        }
        if (returnGoodsList != null) {
            returnGoodsList.forEach(p -> propertyList.add(new ProductOrderProperty(p.getId(), p.getAddtime(), "销退单", p.getPinbackNo(), p.getPinbackNo(), p.getBusinessLeader(), new BigDecimal("0.00"))));
        }
        col_order_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_order_date.setCellValueFactory(new PropertyValueFactory("date"));
        col_order_type.setCellValueFactory(new PropertyValueFactory("type"));//映射
        col_order_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_order_reference.setCellValueFactory(new PropertyValueFactory("reference"));
        col_order_business.setCellValueFactory(new PropertyValueFactory("business"));
        col_order_total.setCellValueFactory(new PropertyValueFactory("total"));
        ObservableList<ProductOrderProperty> data = FXCollections.observableArrayList(propertyList);
        order_tab.setItems(data);
    }

    /**
     * 查询交易产品
     */
    @FXML
    public void findProductListOrder() {
        Boolean cheQuo = che_product_quo.isSelected(), cheSale = che_product_sale.isSelected(), cheReturn = che_product_return.isSelected();
        LocalDate ben = product_date_ben.getValue();
        LocalDate end = product_date_end.getValue();
        String benStr = "", endStr = "";
        if (ben != null) {
            benStr = ben.toString();
        }
        if (end != null) {
            endStr = end.toString();
        }
        List<SaleOfferProduct> quotationList = null;
        if (cheQuo) {
            quotationList = iSaleOfferProductService.listTimeWhere(benStr, endStr);
        }
        List<SaleGoodsProduct> goodsList = null;
        if (cheSale) {
            goodsList = iSaleGoodsProductService.listTimeWhere(benStr, endStr);
        }
        List<SaleReturnGoodsProduct> returnGoodsList = null;
        if (cheReturn) {
            returnGoodsList = iSaleReturnGoodsProductService.listTimeWhere("",benStr, endStr,"");
        }
        List<ProductListProperty> productListPropertyList = new ArrayList<>();
        if (quotationList != null) {
            quotationList.forEach(p -> productListPropertyList.add(new ProductListProperty(p.getId(), p.getProductNo(), p.getProductName(), p.getAddtime(), "报价单", "", p.getNum(), p.getPrice(), "", p.getRemark())));
        }
        if (goodsList != null) {
            goodsList.forEach(p -> productListPropertyList.add(new ProductListProperty(p.getId(), p.getProductNo(), p.getProductName(), p.getAddtime(), "销货单", p.getOrderNo(), p.getNum(), p.getPrice(), "", p.getRemark())));
        }
        if (returnGoodsList != null) {
            returnGoodsList.forEach(p -> productListPropertyList.add(new ProductListProperty(p.getId(), p.getProductNo(), p.getProductName(), p.getAddtime(), "销退单", p.getOrderNo(), p.getNum(), p.getPricing(), "", p.getRemark())));
        }
        col_product_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("name"));//映射
        col_product_date.setCellValueFactory(new PropertyValueFactory("date"));
        col_product_type.setCellValueFactory(new PropertyValueFactory("type"));
        col_product_single.setCellValueFactory(new PropertyValueFactory("single"));
        col_product_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_product_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_product_tax.setCellValueFactory(new PropertyValueFactory("tax"));
        col_product_remark.setCellValueFactory(new PropertyValueFactory("remark"));
        ObservableList<ProductListProperty> data = FXCollections.observableArrayList(productListPropertyList);
        product_tab.setItems(data);
    }

    /*********************************************** 控件禁用 ***************************************************/

    /**
     * 设置菜单按钮状态
     *
     * @param state
     */
    private void setMenu(boolean state) {
        menu_clearAll.setDisable(state);
        menu_commit.setDisable(state);
//        menu_insert.setDisable(state);
        menu_delete.setDisable(state);
    }

    /**
     * 设置控件可编辑状态
     */
    @FXML
    public void setControllerUse() {
        allController(false);
    }

    /**
     * 设置控件不可编辑
     */
    private void setControllerDisable() {
        allController(true);
    }

    /**
     * 所有控件状态
     *
     * @param state
     */
    private void allController(boolean state) {
        setMainController(state);
        setBasicController(state);
        setAddressController(state);
        setBusinessController(state);
        setContactsController(state);
        setDateMaintainController(state);
        setInfoController(state);
        setNoteController(state);
        setMenu(state);
    }

    /**
     * 设置备注及说明信息控件状态（可编辑、不可编辑）
     * @param state
     */
    private void setNoteController(boolean state) {
        customer_remark_tab.setDisable(state);
    }


    /**
     * 设置资料维护信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setDateMaintainController(boolean state) {
        customer_data_maintenance_tab.setDisable(state);
    }

    /**
     * 设置送货地址信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setAddressController(boolean state) {
        shipping_address_table.setDisable(state);
        address_combox_type.setDisable(state);
        address_txt_contact.setDisable(state);
        address_txt_info.setDisable(state);
        address_txt_phone.setDisable(state);
        address_btn_sure.setDisable(state);
        address_txt_name.setDisable(state);
    }

    /**
     * 设置业务负责人信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setBusinessController(boolean state) {
        customer_business_leader_tab.setDisable(state);
    }

    /**
     * 设置联系人信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setContactsController(boolean state) {
        customer_contact_tab.setDisable(state);
    }

    /**
     * 设置详细你信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setInfoController(boolean state) {
        info_customer_category.setDisable(state);
        info_industry.setDisable(state);
        info_customer_source.setDisable(state);
        customer_property.setDisable(state);
        info_bank.setDisable(state);
        info_bank_acount.setDisable(state);
        info_tax_no.setDisable(state);
        info_area_com.setDisable(state);
        info_area_str.setDisable(state);
        info_acount_remark.setDisable(state);
        info_region.setDisable(state);
        info_customer_level.setDisable(state);
        info_company.setDisable(state);
        info_comes_back.setDisable(state);
        customer_invoice_tab.setDisable(state);
    }

    /**
     * 设置基本信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setBasicController(boolean state) {
        base_combox_country_id.setDisable(state);
        base_country.setDisable(state);
        base_phone.setDisable(state);
        base_payment_method.setDisable(state);
        base_initial_quota.setDisable(state);
        base_initial_quota_money_type.setDisable(state);
        base_rush_money_date.setDisable(state);
        base_shipping_address.setDisable(state);
        base_shipping_address_remark.setDisable(state);
        base_primary_contact.setDisable(state);
//        base_archivist.setDisable(state);
//        base_last_modifier.setDisable(state);
//        base_establish_date.setDisable(state);
        base_zip_code.setDisable(state);
        base_fax.setDisable(state);
        base_credit_line.setDisable(state);
        base_credit_line_money_type.setDisable(state);
        base_credit_line_remark.setDisable(state);
        base_minimum_discount.setDisable(state);
        base_contact_number.setDisable(state);
//        base_document_date.setDisable(state);
//        base_last_modified_date.setDisable(state);
        base_stop_use.setDisable(state);
        che_dis_use.setDisable(state);
    }

    /**
     * 设置主要信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setMainController(boolean state) {
        code.setDisable(state);
        customer_name.setDisable(state);
        register_address.setDisable(state);
//        customer_initials.setDisable(state);

        more.setDisable(state);
    }

    /*************************************************** 备注及说明-开始 ************************************/

    /**
     * 刷新备注及说明数据
     */
    public void setCustomerRemarkVal() {
        String customerid = customer_id.getText();
        if (customerid != null && !"".equals(customerid)) {
            customer_remark_tab.setEditable(true);

            remark_remark.setCellFactory(column -> EditCell.createStringEditCell());

            // 查询地址集合
            List<Remark> list = iRemarkService.listRemark(Long.valueOf(customerid), "1");
            List<RemarkProperty> listProperty = new ArrayList<>();
            if (list != null) {
                list.forEach(p -> {
                    listProperty.add(new RemarkProperty(p.getId(), p.getRemark()));
                });
            }
            final ObservableList<RemarkProperty> data = FXCollections.observableArrayList(listProperty);
            remark_id.setCellValueFactory(new PropertyValueFactory("id"));
            remark_remark.setCellValueFactory(new PropertyValueFactory("remark"));//映射

            customer_remark_tab.setItems(data);
        }

    }

    /**
     * 清空备注及说明数据
     */
    public void clearCustomerRemarkVal() {
        customer_remark_tab.setItems(null);
        customer_remark_tab.refresh();
    }
    /*************************************************** 备注及说明-结束 ************************************/

    /*************************************************** 资料维护-开始 ************************************/

    /**
     * 刷新资料 维护数据
     */
    public  void setCustomerDataMaintenanceVal(String customerid) {
        customer_data_maintenance_tab.setEditable(true);

        data_no.setCellFactory(column -> EditCell.createStringEditCell());
        data_create_date.setCellFactory(column -> EditCell.createStringEditCell());
        data_leader_people.setCellFactory(column -> EditCell.createStringEditCell());
        data_purcharse_people.setCellFactory(column -> EditCell.createStringEditCell());
        data_contact.setCellFactory(column -> EditCell.createStringEditCell());
        data_telephone.setCellFactory(column -> EditCell.createStringEditCell());
        data_fax.setCellFactory(column -> EditCell.createStringEditCell());
        data_startup_year.setCellFactory(column -> EditCell.createStringEditCell());
        data_employee_number.setCellFactory(column -> EditCell.createStringEditCell());
        data_last_year_business.setCellFactory(column -> EditCell.createStringEditCell());
        date_year_plan.setCellFactory(column -> EditCell.createStringEditCell());
        date_industry.setCellFactory(column -> EditCell.createStringEditCell());

        // 查询地址集合
        List<CustomerDataMaintenance> list = iCustomerDataMaintenanceService.listCustomerDataMaintenanceByCustomerId(Long.valueOf(customerid));
        List<CustomerDataMaintenanceProperty> listProperty = new ArrayList<>();
        if (list != null) {
            list.forEach(p -> {
                CustomerDataMaintenanceProperty dt = new CustomerDataMaintenanceProperty(p.getId(), p.getCustomerId()==null?"":p.getCustomerId().toString(), p.getDocumentNo(), p.getCreateDate()==null?"":p.getCreateDate().toString(), p.getLeaderPeople(), p.getPurchasePeople(), p.getContact(), p.getTelephone(), p.getFax(), p.getStartupYear()==null?"":p.getStartupYear().toString(), p.getEmployeeNumber()==null?"":p.getEmployeeNumber().toString(), p.getLastYearBusiness(), p.getYearPlan(), p.getIndustry());
                if(p.getCreateDate() != null){
                    dt.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                }
                if(p.getStartupYear() != null){
                    dt.setStartupYear(new SimpleDateFormat("yyyy-MM-dd").format(p.getStartupYear()));
                }
                listProperty.add(dt);
            });
        }
        final ObservableList<CustomerDataMaintenanceProperty> data = FXCollections.observableArrayList(listProperty);
        data_id.setCellValueFactory(new PropertyValueFactory("id"));
        data_no.setCellValueFactory(new PropertyValueFactory("documentNo"));//映射
        data_create_date.setCellValueFactory(new PropertyValueFactory("createDate"));
        data_leader_people.setCellValueFactory(new PropertyValueFactory("leaderPeople"));
        data_purcharse_people.setCellValueFactory(new PropertyValueFactory("purchasePeople"));
        data_contact.setCellValueFactory(new PropertyValueFactory("contact"));
        data_telephone.setCellValueFactory(new PropertyValueFactory("telephone"));
        data_fax.setCellValueFactory(new PropertyValueFactory("fax"));
        data_startup_year.setCellValueFactory(new PropertyValueFactory("startupYear"));
        data_employee_number.setCellValueFactory(new PropertyValueFactory("employeeNumber"));
        data_last_year_business.setCellValueFactory(new PropertyValueFactory("lastYearBusiness"));
        date_year_plan.setCellValueFactory(new PropertyValueFactory("yearPlan"));
        date_industry.setCellValueFactory(new PropertyValueFactory("industry"));

        customer_data_maintenance_tab.setItems(data);
        customer_data_maintenance_tab.refresh();

    }

    /**
     * 清空资料维护数据
     */
    public void clearCustomerDataMaintenanceVal() {
        customer_data_maintenance_tab.setItems(null);
        customer_data_maintenance_tab.refresh();
    }
    /*************************************************** 资料维护-结束 ************************************/

    /*************************************************** 联系人-开始 ************************************/

    /**
     * 刷新客户联系人数据
     */
    public void setCustomerContactsVal() {
        String customerid = customer_id.getText();
        customer_contact_tab.setEditable(true);

        contact_primary.setCellFactory((param) -> new TableViewSample.RadioButtonCell<CustomerContactsProperty, ParticipationUsual>(EnumSet.allOf(ParticipationUsual.class)));
        contact_name.setCellFactory(column -> EditCell.createStringEditCell());
        contact_department.setCellFactory(column -> EditCell.createStringEditCell());
        contact_position.setCellFactory(column -> EditCell.createStringEditCell());
        contact_telephone.setCellFactory(column -> EditCell.createStringEditCell());
        contact_fax.setCellFactory(column -> EditCell.createStringEditCell());
        contact_phone.setCellFactory(column -> EditCell.createStringEditCell());
        contact_mail.setCellFactory(column -> EditCell.createStringEditCell());
        contact_remark.setCellFactory(column -> EditCell.createStringEditCell());

        // 查询地址集合
        List<CustomerContacts> list = iCustomerContactsService.listCustomerContactsByCustomerId(Long.valueOf(customerid));
        List<CustomerContactsProperty> listProperty = new ArrayList<>();
        if (list != null) {
            list.forEach(p -> {
                try {
                    ParticipationUsual part = null;
                    if (p.getPrimaryContacts() != null && p.getPrimaryContacts()) {
                        part = ParticipationUsual.常用;
                    }
                    listProperty.add(new CustomerContactsProperty(p.getId(), p.getCustomerId().toString(), p.getPrimaryContacts(), p.getName(), p.getDepartment(), p.getPosition(), p.getTelephone(), p.getFax(), p.getMobilePhone(), p.getEmail(), p.getRemark()));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        final ObservableList<CustomerContactsProperty> data = FXCollections.observableArrayList(listProperty);
        contact_id.setCellValueFactory(new PropertyValueFactory("id"));
        contact_primary.setCellValueFactory(new PropertyValueFactory<CustomerContactsProperty, Participation>("participation"));
        contact_name.setCellValueFactory(new PropertyValueFactory("name"));
        contact_department.setCellValueFactory(new PropertyValueFactory("department"));
        contact_position.setCellValueFactory(new PropertyValueFactory("position"));
        contact_telephone.setCellValueFactory(new PropertyValueFactory("telephone"));
        contact_fax.setCellValueFactory(new PropertyValueFactory("fax"));
        contact_phone.setCellValueFactory(new PropertyValueFactory("mobilePhone"));
        contact_mail.setCellValueFactory(new PropertyValueFactory("email"));
        contact_remark.setCellValueFactory(new PropertyValueFactory("remark"));


        contact_primary.setOnEditCommit(evt -> {
            boolean inputValid = false;
            if (!inputValid) {
                int row = customer_contact_tab.getEditingCell().getRow();

                CustomerContactsProperty selected = (CustomerContactsProperty) customer_contact_tab.getItems().get(row);
                // 清除所有默认
                iCustomerContactsService.clearCustomerDefaultInvoice(Long.valueOf(customerid));
                // 设置为默认
                int count = iCustomerContactsService.setCustomerDefaultInvoice(selected.getId());

                if (count > 0) {
                    setCustomerContactsVal();
                }
                customer_contact_tab.requestFocus(); // get back focus
                customer_contact_tab.edit(row, contact_primary);
            }
        });

        customer_contact_tab.setItems(data);
        customer_contact_tab.refresh();

    }

    /**
     * 清空联系人数据
     */
    public void clearCustomerContactsVal() {
        customer_contact_tab.setItems(null);
        customer_contact_tab.refresh();
    }
    /*************************************************** 联系人-结束 ************************************/

    /*************************************************** 业务负责人-开始 ************************************/

    /**
     * 刷新客户业务负责人数据
     */
    public void setCustomerBusinessLeaderVal(String customerid) {
        customer_business_leader_tab.setEditable(true);

        leader_no.setCellFactory(column -> EditCell.createStringEditCell());
        leader_name.setCellFactory(column -> EditCell.createStringEditCell());
        leader_primary.setCellFactory(column -> EditCell.createStringEditCell());
        leader_assistant.setCellFactory(column -> EditCell.createStringEditCell());
        leader_remark.setCellFactory(column -> EditCell.createStringEditCell());

        // 查询地址集合
        List<CustomerBusinessLeader> list = iCustomerBusinessLeaderService.listCustomerBusinessLeaderByCustomerId(Long.valueOf(customerid));
        List<CustomerBusinessLeaderProperty> listProperty = new ArrayList<>();
        if (list != null) {
            list.forEach(p -> {
                try {
                    listProperty.add(new CustomerBusinessLeaderProperty(p.getId(), p.getCustomerId().toString(), p.getEmployeeCode(), p.getName(), p.getPrimaryPeople(), p.getRemark(), p.getAssistant()));
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            });
        }
        final ObservableList<CustomerBusinessLeaderProperty> data = FXCollections.observableArrayList(listProperty);
        leader_id.setCellValueFactory(new PropertyValueFactory("id"));
        leader_no.setCellValueFactory(new PropertyValueFactory("employeeCode"));//映射
        leader_name.setCellValueFactory(new PropertyValueFactory("name"));
        leader_primary.setCellValueFactory(new PropertyValueFactory("primaryPeople"));
        leader_assistant.setCellValueFactory(new PropertyValueFactory("assistant"));
        leader_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        customer_business_leader_tab.setItems(data);

        CustomerBasicInfoController.clickEvent(customer_business_leader_tab, da ->{
            tablePosition = customer_business_leader_tab.getSelectionModel().getSelectedIndex();
            moreEmployeeButtonClick();
        }, 2);
    }


    /**
     * 员工选择
     */
    @FXML
    public  void moreEmployeeButtonClick(){
        Stage stage = new Stage();
        stage.setTitle("员工查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerBasicInfoControllerEmployee", this);
        StageManager.CONTROLLER.put("tablePosition", tablePosition);
        pane.getChildren().setAll(loader.load("/fxml/customer/employee_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 清空业务负责人数据
     */
    public void clearCustomerBusinessLeaderVal() {
        customer_business_leader_tab.setItems(null);
        customer_business_leader_tab.refresh();
    }
    /*************************************************** 业务负责人-结束 ************************************/


    /********************************************* 详细信息-开始 ********************************************/

    /**
     * 显示发票详情
     */
    @SneakyThrows(Exception.class)
    public void showInvoice() {
        String customerid = customer_id.getText();
        if (!"".equals(customerid) && customerid != null) {

            customer_invoice_tab.setEditable(true);

            // checkbox
            Callback<TableColumn<InvoiceProperty, Boolean>, TableCell<InvoiceProperty, Boolean>> generalCheckboxFactory
                    = new Callback<TableColumn<InvoiceProperty, Boolean>, TableCell<InvoiceProperty, Boolean>>() {
                @Override
                public TableCell call(final TableColumn<InvoiceProperty, Boolean> param) {
                    final TableCell<Customer, Boolean> cell = new TableCell<Customer, Boolean>() {

                        final CheckBox checkBox = new CheckBox("停用");

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
                                    int selectdIndex = getTableRow().getIndex();
                                    List<InvoiceProperty> propertyList = customer_invoice_tab.getItems();
                                    InvoiceProperty selectedRecord = (InvoiceProperty) customer_invoice_tab.getItems().get(selectdIndex);
                                    if (selectedRecord != null && propertyList != null) {
                                        propertyList.get(selectdIndex).setParticipationStop(!selectedRecord.isParticipationStop());
                                        if(selectedRecord.isParticipationStop()){
                                            propertyList.get(selectdIndex).setDisdate(DateUtils.getSpecifyDate(new Date(),DateUtils.FORMAT_YYYY_MM_DD));
                                        }
                                        customer_invoice_tab.setItems(FXCollections.observableArrayList(propertyList));
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


            invoice_usual_col.setCellFactory((param) -> new TableViewSample.RadioButtonCell<InvoiceProperty, ParticipationUsual>(EnumSet.allOf(ParticipationUsual.class)));
            invoice_title_col.setCellFactory(column -> EditCell.createStringEditCell());
            invoice_address_col.setCellFactory(column -> EditCell.createStringEditCell());
            invoice_phone_col.setCellFactory(column -> EditCell.createStringEditCell());
            invoice_contact_col.setCellFactory(column -> EditCell.createStringEditCell());
            invoice_disable_col.setCellFactory(generalCheckboxFactory);
            invoice_remark_col.setCellFactory(column -> EditCell.createStringEditCell());

            List<InvoiceProperty> propertyList = new ArrayList<>();
            List<Invoice> invoiceList = iInvoiceService.listInvoiceByCustomerId(Long.valueOf(customerid));
            if (invoiceList != null) {
                invoiceList.forEach(p -> {
                    ParticipationUsual part = null;
                    if (p.getUsual() != null && p.getUsual()) {
                        part = ParticipationUsual.常用;
                    }
                    InvoiceProperty invoiceProperty = new InvoiceProperty(p.getId(), p.getUsual(), p.getTitle(), p.getAddress(), p.getPhone(), p.getContact(), p.getRemark(), p.getDisabled(), p.getDisdate(), part, p.getDisabled());
                    if (p.getDisdate() != null) {
                        invoiceProperty.setDisdate(new SimpleDateFormat("yyyy-MM-dd").format(p.getDisdate()));
                    }
                    propertyList.add(invoiceProperty);
                });
            }
            ObservableList<InvoiceProperty> data = FXCollections.observableArrayList(propertyList);

            invoice_id_col.setCellValueFactory(new PropertyValueFactory("id"));
            invoice_usual_col.setCellValueFactory(new PropertyValueFactory<InvoiceProperty, Participation>("participation"));
            invoice_title_col.setCellValueFactory(new PropertyValueFactory("title"));//映射
            invoice_address_col.setCellValueFactory(new PropertyValueFactory("address"));
            invoice_phone_col.setCellValueFactory(new PropertyValueFactory("phone"));
            invoice_contact_col.setCellValueFactory(new PropertyValueFactory("contact"));
            invoice_disable_col.setCellValueFactory(new PropertyValueFactory("participationStop"));
            invoice_disable_date_col.setCellValueFactory(new PropertyValueFactory("disdate"));
            invoice_remark_col.setCellValueFactory(new PropertyValueFactory<Customer, Date>("remark"));

            customer_invoice_tab.setItems(data);

            invoice_usual_col.setOnEditCommit(evt -> {
                boolean inputValid = false;
                if (!inputValid) {
                    int row = customer_invoice_tab.getEditingCell().getRow();

                    InvoiceProperty selected = (InvoiceProperty) customer_invoice_tab.getItems().get(row);
                    // 清除所有默认
                    iInvoiceService.updateClearInvoiceDefault(Long.valueOf(customerid), "1");
                    // 设置为默认
                    int count = iInvoiceService.updateInvoiceDefault(selected.getId(), "1", "");

                    if (count > 0) {
                        showInvoice();
                    }
                    customer_invoice_tab.requestFocus(); // get back focus
                    customer_invoice_tab.edit(row, invoice_usual_col);
                }
            });
            invoice_disable_col.setOnEditCommit(evt -> {
                boolean inputValid = false;
                if (!inputValid) {
                    int row = customer_invoice_tab.getEditingCell().getRow();

                    InvoiceProperty selected = (InvoiceProperty) customer_invoice_tab.getItems().get(row);

                    // 清除所有默认
                    iInvoiceService.updateClearInvoiceDefault(Long.valueOf(customerid), "2");
                    // 设置为默认
                    int count = iInvoiceService.updateInvoiceDefault(selected.getId(), "2", "date");

                    if (count > 0) {
                        showInvoice();
                    }
                    customer_invoice_tab.requestFocus(); // get back focus
                    customer_invoice_tab.edit(row, invoice_usual_col);
                }
            });
        }

    }

    /**
     * 客户类型下拉框
     */
    public void customerCategoryCombox() {
        info_customer_category.getSelectionModel().selectFirst();
        setComboBox(10, info_customer_category, 0);
        info_customer_category.setTooltip(new Tooltip("请选择客户类型"));
    }

    /**
     * 行业下拉框
     */
    public void industryCombox() {
        setComboBox(11L, info_industry, 0);
        info_industry.setTooltip(new Tooltip("请选择行业类型"));
    }

    /**
     * 客户来源下拉框
     */
    public void customerSourceCombox() {
        setComboBox(13L, info_customer_source, 0);
        info_customer_source.setTooltip(new Tooltip("请选择客户来源"));
    }


    /**
     * 客户等级下拉框
     */
    public void customerLevelCombox() {
        setComboBox(14, info_customer_level, 0);
        info_customer_level.setTooltip(new Tooltip("请选择客户等级"));
    }


    /**
     * 清空客户详细信息值
     */
    public void clearCustomerDetailInfoVal() {
        info_customer_category.getSelectionModel().selectFirst();
        info_industry.getSelectionModel().selectFirst();
        info_customer_source.getSelectionModel().selectFirst();
        customer_property.clear();
        info_bank.clear();
        info_bank_acount.clear();
        info_tax_no.clear();
        info_area_com.getSelectionModel().selectFirst();
        info_area_str.clear();
        info_acount_remark.clear();
        info_region.getSelectionModel().selectFirst();
        info_customer_level.getSelectionModel().selectFirst();
        // 所属公司
        info_company.getSelectionModel().selectFirst();
        info_comes_back.setSelected(false);
    }

    /**
     * 给客户详情赋值
     *
     * @param info
     */
    public void setCustomerDetailInfoVal(CustomerDetailInfo info) {
        if (info != null) {
            if (info.getCustomerCategory() != null) {
                try {
                    setComboBox(10, info_customer_category, info.getCustomerCategory());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (info.getIndustry() != null) {
                setComboBox(11, info_industry, info.getIndustry());
            }
            if (info.getCustomerSource() != null) {
                setComboBox(13, info_customer_source, info.getCustomerSource());
            }
            if (info.getCustomerProperty() != null) {
                customer_property.setText(info.getCustomerProperty());
            }
            if (info.getBank() != null) {
                info_bank.setText(info.getBank());
            }
            if (!("".equals(info.getBankAccount())) && info.getBankAccount() != null) {
                info_bank_acount.setText(info.getBankAccount());
            }
            if (!("".equals(info.getTaxRegister())) && info.getTaxRegister() != null) {
                info_tax_no.setText(info.getTaxRegister());
            }
            if (info.getArea() != null && !"".equals(info.getArea())) {
                info_area_com.getSelectionModel().select(info.getArea());
            }
            if (!("".equals(info.getAreaInfo())) && info.getAreaInfo() != null) {
                info_area_str.setText(info.getAreaInfo());
            }
            if (!("".equals(info.getAccountRemark())) && info.getAccountRemark() != null) {
                info_acount_remark.setText(info.getAccountRemark());
            }
            if (info.getRegion() != null) {
                info_region.getSelectionModel().select(info.getRegion());
            }
            if (info.getCustomerLevel() != null) {
                try {
                    setComboBox(14L, info_customer_level, info.getCustomerLevel());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 所属公司
            if (info.getCompanyAffiliation() != null) {
                try {
                    info_company.getSelectionModel().select(info.getCompanyAffiliation());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            info_comes_back.setSelected(info.getSentBack());
        }
    }

    /********************************************* 详细信息-结束 ********************************************/

    /********************************************* 基本信息-开始 ********************************************/


    /**
     * @Author BlueSky
     * @Description //TODO 基本信息-国家下拉框
     * @Date 11:40 2018/8/17
     **/
    public void base_combox_countrySetVal() {
        List<String> ids = new ArrayList<>();
        countryList = iDataSettingService.findDataSetting(28L);
        countryList.forEach(p -> {
            ids.add(p.getTitle());
        });
        base_combox_country_id.setItems(FXCollections.observableArrayList(ids.toArray()));
        base_combox_country_id.setTooltip(new Tooltip("Select the country"));
        if (countryList.size() > 0) {
            base_country.setText(countryList.get(0).getTitle());
        }
        // 下拉框值改变事件监听
        base_combox_country_id.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    if(base_combox_country_id.getSelectionModel().getSelectedItem() != null){
                        base_country.setText(base_combox_country_id.getSelectionModel().getSelectedItem().toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 清除基本信息
     */
    private void clearBasicVal() {
        base_combox_country_id.getSelectionModel().selectFirst();
        base_country.setText(null);
        base_phone.setText(null);
        base_payment_method.getSelectionModel().selectFirst();
        base_initial_quota.setText(null);
        base_initial_quota_money_type.getSelectionModel().selectFirst();

        /*
        Date 类型转换 LocalDate
        Instant instant = new Date().toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
        */
        LocalDateTime localDate = LocalDateTime.now();

        base_rush_money_date.setValue(localDate.toLocalDate());
        base_shipping_address.setText(null);
        base_shipping_address_remark.setText(null);
        base_primary_contact.setText(null);
        base_archivist.setText(null);
        base_last_modifier.setText(null);
        base_establish_date.setValue(localDate.toLocalDate());
        base_zip_code.setText(null);
        base_fax.setText(null);
        base_credit_line.setText(null);
        base_credit_line_money_type.getSelectionModel().selectFirst();
        base_credit_line_remark.setText(null);
        base_minimum_discount.setText(null);
        base_contact_number.setText(null);
        base_document_date.setValue(localDate.toLocalDate());
        base_last_modified_date.setValue(localDate.toLocalDate());
        base_stop_use.setText(null);
        che_dis_use.setSelected(false);
    }

    /**
     * 查询基本资料信息
     *
     * @param basic
     */
    public void setBasicValue(CustomerBasic basic) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
        // 先清空所有值
        clearBasicVal();
        if(basic==null){
            return;
        }
        // 赋值
        if (basic != null) {
            try {
                if (!("".equals(basic.getCountryId())) && basic.getCountryId() != null) {
                    base_combox_country_id.getSelectionModel().select(Integer.valueOf(basic.getCountryId().toString())-1);
                    base_country.setText(countryList.get(Integer.valueOf(basic.getCountryId().toString())-1).getTitle());
                }
                if (!("".equals(basic.getPhone())) && basic.getPhone() != null) {
                    base_phone.setText(basic.getPhone());
                }
                if (!("".equals(basic.getPaymentMethod())) && basic.getPaymentMethod() != null) {
                    base_payment_method.getSelectionModel().select(basic.getPaymentMethod());
                }
                if (!("".equals(basic.getInitialQuota())) && basic.getInitialQuota() != null) {
                    base_initial_quota.setText(basic.getInitialQuota().toString());
                }
                if (!("".equals(basic.getInitialQuotaMoneyType())) && basic.getInitialQuotaMoneyType() != null) {
                    base_initial_quota_money_type.getSelectionModel().select(basic.getInitialQuotaMoneyType());
                }
                if (!("".equals(basic.getRushMoneyDate())) && basic.getRushMoneyDate() != null) {
                    base_rush_money_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(basic.getRushMoneyDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
                }
                if (!("".equals(basic.getShippingAddress())) && basic.getShippingAddress() != null) {
                    base_shipping_address.setText(basic.getShippingAddress());
                }
                if (!("".equals(basic.getShippingAddressRemark())) && basic.getShippingAddressRemark() != null) {
                    base_shipping_address_remark.setText(basic.getShippingAddressRemark());
                }
                if (!("".equals(basic.getPrimaryContact())) && basic.getPrimaryContact() != null) {
                    base_primary_contact.setText(basic.getPrimaryContact());
                }
                if (!("".equals(basic.getArchivist())) && basic.getArchivist() != null) {
                    base_archivist.setText(basic.getArchivist());
                }
                if (!("".equals(basic.getLastModifier())) && basic.getLastModifier() != null) {
                    base_last_modifier.setText(basic.getLastModifier());
                }
                if (!("".equals(basic.getEstablishDate())) && basic.getEstablishDate() != null) {
                    base_establish_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(basic.getEstablishDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
                }
                if (!("".equals(basic.getZipCode())) && basic.getZipCode() != null) {
                    base_zip_code.setText(basic.getZipCode());
                }
                if (!("".equals(basic.getFax())) && basic.getFax() != null) {
                    base_fax.setText(basic.getFax());
                }
                if (!("".equals(basic.getCreditLine())) && basic.getCreditLine() != null) {
                    base_credit_line.setText(basic.getCreditLine().toString());
                }
                if (basic.getCreditLineMoneyType() != null) {
                    setComboBox(7, base_credit_line_money_type, basic.getCreditLineMoneyType()-1);
                }
                if (!("".equals(basic.getCreditLineRemark())) && basic.getCreditLineRemark() != null) {
                    base_credit_line_remark.setText(basic.getCreditLineRemark());
                }
                if (!("".equals(basic.getMinimumDiscount())) && basic.getMinimumDiscount() != null) {
                    base_minimum_discount.setText(basic.getMinimumDiscount().toString());
                }
                if (!("".equals(basic.getContactNumber())) && basic.getContactNumber() != null) {
                    base_contact_number.setText(basic.getContactNumber());
                }
                if (!("".equals(basic.getDocumentDate())) && basic.getDocumentDate() != null) {
                    base_document_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(basic.getDocumentDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
                }
                if (!("".equals(basic.getLastModifiedDate())) && basic.getLastModifiedDate() != null) {
                    base_last_modified_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(basic.getLastModifiedDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
                }
                if (!("".equals(basic.getStopUseStr())) && basic.getStopUseStr() != null) {
                    base_stop_use.setText(basic.getStopUseStr());
                }
                if (basic.getStopUse() != null) {
                    che_dis_use.setSelected(basic.getStopUse());
                }
                showInvoice();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 客户编号变更监听
     */
    public void customerCodeChange() {
        code.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String customerid = customer_id.getText();
                if (customerid != null && !("".equals(customerid))) {
                    selectAllPane();
                }
            }
        });
    }


    /********************************************* 基本信息-结束 ********************************************/


    /********************************************* 客户公共部分结束 ********************************************/

    /**
     * 查询所有面板中的数据
     */
    @SneakyThrows(Exception.class)
    private void selectAllPane() {
        val customerid = customer_id.getText();
        if(customerid!=null&&!"".equals(customerid)){
            // 基本资料
            val basic = iCustomerBasicService.getCustomerBasicByCustomerId(Long.valueOf(customerid));
            setBasicValue(basic);
            // 详细信息
            customerCategoryCombox();
            industryCombox();
            customerSourceCombox();
            customerLevelCombox();
            // 发票详情
            showInvoice();
            // 清除客户详细信息
            clearCustomerDetailInfoVal();
            val info = iCustomerDetailInfoService.getCustomerDetailInfoByCustomerId(Long.valueOf(customerid));
            setCustomerDetailInfoVal(info);
            // 三禄业务负责人
            clearCustomerBusinessLeaderVal();
            setCustomerBusinessLeaderVal(customerid);
            // 客户联系人
            clearCustomerContactsVal();
            setCustomerContactsVal();
            // 送货地址
            clearAddressVal();
            showAddressList();
            // 交易单据

            // 资料维护
            clearCustomerDataMaintenanceVal();
            setCustomerDataMaintenanceVal(customerid);
            // 备注及说明
            clearCustomerRemarkVal();
            setCustomerRemarkVal();

            // 设置所有控件不可编辑
            setControllerDisable();

            //权限管理
            matchingPermissions("客户基本资料",menu_insert,menu_delete,menu_update,menu_printing,menu_clearAll);

        }
    }


    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO 删除客户
     * @Date 18:44 2018/8/20
     * @Param []
     **/
    @FXML
    public void delete() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            String strid = customer_id.getText();
            Customer customer = new Customer();
            customer.setId(Long.valueOf(strid));
            customer.setDeleteIs(true);
            int rows = iCustomerService.updateNotNull(customer);
            if (rows > 0) {
                // 加载第一条客户数据
                setMenuValue(1);
                alert_informationDialog("删除成功！");
            } else {
                alert_informationDialog("删除失败！");
            }
        }


    }

    /**
     * 清空所有数据
     */
    @FXML
    public void clearAll() {
        clearBasicVal();
        clearCustomerData();
        clearAddressVal();
        clearCustomerBusinessLeaderVal();
        clearCustomerContactsVal();
        clearCustomerDataMaintenanceVal();
        clearCustomerDetailInfoVal();
        clearCustomerRemarkVal();

    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO 清空文本框
     * @Date 17:12 2018/8/20
     * @Param []
     **/
    public void clearCustomerData() {
        customer_id.clear();
        code.clear();
        customer_name.clear();
        register_address.clear();
        customer_initials.clear();
        customer_invoice_tab.setItems(null);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<Customer> customerList = iCustomerService.listCustomerAll("0",page, 1);
        if(customerList != null && customerList.size() >0){
            customerList.forEach(p->setCustomerTextFieldVal(p));
            PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
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
     * @return void
     * @Author BlueSky
     * @Description //TODO 给客户基本信息文本框赋值
     * @Date 15:13 2018/8/20
     * @Param [customer]
     **/
    private void setCustomerTextFieldVal(Customer customer) {
        if (customer != null) {
            if (customer.getId() != null) {
                customer_id.setText(customer.getId().toString());
            }
            if (customer.getName() != null) {
                customer_name.setText(customer.getName());
            }
            if (customer.getShortName() != null) {
                customer_initials.setText(customer.getShortName());
            }
            if (customer.getCustomerCode() != null) {
                code.setText(customer.getCustomerCode());
            }
            if (customer.getRegisterAddress() != null) {
                register_address.setText(customer.getRegisterAddress());
            }
        }
    }


    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO 新建客户
     * @Date 10:40 2018/8/20
     * @Param []
     **/
    @FXML
    public void add_customer() {
        // 清空所有
        clearAll();
        // 设置控件可编辑
        setControllerUse();
        String str_code = DateUtils.getDateFormat(DateUtils.FORMAT_YYYYMMDDHHMMSS).format(new Date());
        code.setText(str_code);
        // 建档人 建档日期
        createPeople(base_archivist, base_document_date);
        customer_initials.setText(null);
        customer_name.setText(null);
        register_address.setText(null);
        // 选中下拉框中的第一个值
        try {
            base_country.setText(base_combox_country_id.getValue().toString());
            info_area_str.setText(info_area_com.getSelectionModel().getSelectedItem().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO 客户数据提交
     * @Date 15:17 2018/8/20
     * @Param []
     **/
    @FXML
    public void save() {
        // 用户 --> 暂停使用
        if (che_dis_use.isSelected()) {
            customer_initials.setText(customer_name.getText()+"-禁用");
        } else {
            customer_initials.setText(customer_name.getText());
        }

        String old_code = code.getText();
        Customer tempCustomer = iCustomerService.getCustomer(old_code);
        String name = customer_name.getText();
        String address = register_address.getText();
        String initials = customer_initials.getText();
        // 最后修改人
        lastUpdatePeopel(base_last_modifier, base_last_modified_date);
        if (old_code == null || "".equals(old_code) || name == null || "".equals(name) || address == null || "".equals(address)) {
            alert_informationDialog("客户信息不能为空");
            return;
        } else {
            if (tempCustomer == null) {
                // 实例化
                tempCustomer = new Customer();
            }
            tempCustomer.setName(name);
            tempCustomer.setRegisterAddress(address);
            tempCustomer.setShortName(initials);
            tempCustomer.setCustomerCode(old_code);
            tempCustomer.setAddtime(new Date());
            String new_code = code.getText();
            tempCustomer.setCustomerCode(new_code);
            if (tempCustomer.getId() == null || tempCustomer.getId() == 0L) {
                tempCustomer.setUseableMoney(new BigDecimal(0.00));
                tempCustomer.setDeleteIs(false);
                int rows = iCustomerService.save(tempCustomer);
                if (rows > 0) {
                    // 把新增的id值赋予隐藏框
                    customer_id.setText(tempCustomer.getId().toString());
                    setControllerDisable();
                } else {
                    alert_informationDialog("客户基本信息新增失败");
                }
            } else {
                int rows = iCustomerService.updateNotNull(tempCustomer);
                if (rows > 0) {
                    // 把id值赋予隐藏框
                    customer_id.setText(tempCustomer.getId().toString());
                    setControllerDisable();
                } else {
                    alert_informationDialog("客户基本信息修改失败");
                }
            }
        }

        //送货地址
        saveAddress();
        // 基本资料
        saveCustomerBasic();
        // 客户详细信息
        saveCustomerInfo();

        // 发票明细
        saveTableviewInvice();
        // 三禄负责人
        saveTableviewBusiness();
        // 客户联系人
        saveTableviewContact();
        // 资料维护
        saveTableviewMaintenance();
        // 备注及说明
        saveTableviewRemark();


    }

    /**
     * 保存客户基本信息
     */
    private void saveCustomerBasic() {
        String customerid = customer_id.getText();
        if (!("".equals(customerid)) && customerid != null) {
            try {
                CustomerBasic basic = new CustomerBasic();
                basic.setCustomerId(Long.valueOf(customerid));
                if (base_combox_country_id.getSelectionModel().getSelectedItem() != null && !"".equals(base_combox_country_id.getSelectionModel().getSelectedItem())) {
                    basic.setCountryId(Long.valueOf(base_combox_country_id.getSelectionModel().getSelectedIndex())+1);
                }
                if (!("".equals(base_phone.getText())) && base_phone.getText() != null) {
                    basic.setPhone(base_phone.getText());
                }
                if (base_payment_method.getSelectionModel().getSelectedItem() != null) {
                    basic.setPaymentMethod(base_payment_method.getSelectionModel().getSelectedItem().toString());
                }
                if (!("".equals(base_initial_quota.getText())) && base_initial_quota.getText() != null) {
                    basic.setInitialQuota(new BigDecimal(base_initial_quota.getText()));
                }
                if (base_initial_quota_money_type.getSelectionModel().getSelectedItem() != null) {
                    basic.setInitialQuotaMoneyType(base_initial_quota_money_type.getSelectionModel().getSelectedItem().toString());
                }
                if (!("".equals(base_rush_money_date.getValue())) && base_rush_money_date.getValue() != null) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = df.parse(base_rush_money_date.getValue().toString());
                        basic.setRushMoneyDate(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!("".equals(base_shipping_address.getText())) && base_shipping_address.getText() != null) {
                    basic.setShippingAddress(base_shipping_address.getText());
                }
                if (!("".equals(base_shipping_address_remark.getText())) && base_shipping_address_remark.getText() != null) {
                    basic.setShippingAddressRemark(base_shipping_address_remark.getText());
                }
                if (!("".equals(base_primary_contact.getText())) && base_primary_contact.getText() != null) {
                    basic.setPrimaryContact(base_primary_contact.getText());
                }
                if (!("".equals(base_archivist.getText())) && base_archivist.getText() != null) {
                    basic.setArchivist(base_archivist.getText());
                }
                if (!("".equals(base_last_modifier.getText())) && base_last_modifier.getText() != null) {
                    basic.setLastModifier(base_last_modifier.getText());
                }
                if (!("".equals(base_establish_date.getValue())) && base_establish_date.getValue() != null) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = df.parse(base_establish_date.getValue().toString());
                        basic.setEstablishDate(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!("".equals(base_zip_code.getText())) && base_zip_code.getText() != null) {
                    basic.setZipCode(base_zip_code.getText());
                }
                if (!("".equals(base_fax.getText())) && base_fax.getText() != null) {
                    basic.setFax(base_fax.getText());
                }
                if (!("".equals(base_credit_line.getText())) && base_credit_line.getText() != null) {
                    basic.setCreditLine(new BigDecimal(base_credit_line.getText()));
                }
                if (base_credit_line_money_type.getSelectionModel().getSelectedItem() != null && !"".equals(base_credit_line_money_type.getSelectionModel().getSelectedItem())) {
                    basic.setCreditLineMoneyType(base_credit_line_money_type.getSelectionModel().getSelectedIndex()+1);
                }
                if (!("".equals(base_credit_line_remark.getText())) && base_credit_line_remark.getText() != null) {
                    basic.setCreditLineRemark(base_credit_line_remark.getText());
                }
                if (!("".equals(base_minimum_discount.getText())) && base_minimum_discount.getText() != null) {
                    basic.setMinimumDiscount(Double.valueOf(base_minimum_discount.getText()));
                }
                if (!("".equals(base_contact_number.getText())) && base_contact_number.getText() != null) {
                    basic.setContactNumber(base_contact_number.getText());
                }
                if (!("".equals(base_document_date.getValue())) && base_document_date.getValue() != null) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = df.parse(base_document_date.getValue().toString());
                        basic.setDocumentDate(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!("".equals(base_last_modified_date.getValue())) && base_last_modified_date.getValue() != null) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = df.parse(base_last_modified_date.getValue().toString());
                        basic.setLastModifiedDate(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 暂停使用
                basic.setStopUse(che_dis_use.isSelected());
                if (!"".equals(base_stop_use.getText()) && base_stop_use.getText() != null) {
                    basic.setStopUseStr(base_stop_use.getText());
                }
                if (basic.getCustomerId() != null && basic.getCustomerId() != 0L) {
                    CustomerBasic cu = iCustomerBasicService.getCustomerBasicByCustomerId(basic.getCustomerId());
                    if (cu == null) {
                        // 新增客户基本资料
                        basic.setAddtime(new Date());
                        int rows = iCustomerBasicService.save(basic);
                        if (rows > 0) {
                            setControllerDisable();
                        } else {
                            alert_informationDialog("客户基本资料保存失败！");
                        }
                    } else {
                        // 修改客户基本资料
                        basic.setId(cu.getId());
                        int rows = iCustomerBasicService.updateNotNull(basic);
                        if (rows > 0) {
                            setControllerDisable();
                        } else {
                            alert_informationDialog("客户基本资料修改失败！");
                        }
                    }
                } else {
                    alert_informationDialog("客户基本信息不完整");
                }
                // 如果地址信息不为空，则添加客户默认地址
                CustomerShippingAddress address = new CustomerShippingAddress();
                if(base_shipping_address.getText() != null && !"".equals(base_shipping_address.getText())){
                    List<CustomerShippingAddress> addressList = iCustomerShippingAddressService.listAll(Long.valueOf(customerid));
                    if(addressList == null || addressList.size() == 0){
                        address.setAddress(base_shipping_address.getText());
                        address.setAddtime(new Date());
                        address.setSetting(true);
                        address.setCustomerid(Long.valueOf(customerid));
                        iCustomerShippingAddressService.save(address);
                        // 刷新送货地址表格
                        showAddressList();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 客户详细信息保存
     */
    private void saveCustomerInfo() {
        CustomerDetailInfo info = new CustomerDetailInfo();
        String customerid = customer_id.getText().toString();
        info.setCustomerId(Long.valueOf(customerid));
        //客户类别
        if (info_customer_category.getSelectionModel().getSelectedItem() != null) {
            info.setCustomerCategory(info_customer_category.getSelectionModel().getSelectedIndex());
        }
        // 行业
        if (info_industry.getSelectionModel().getSelectedItem() != null) {
            info.setIndustry(info_industry.getSelectionModel().getSelectedIndex());
        }
        // 客户来源
        if (!("".equals(info_customer_source.getValue().toString())) && info_customer_source.getValue().toString() != null) {
            info.setCustomerSource(info_customer_source.getSelectionModel().getSelectedIndex());
        }
        // 客户属性
        if ( customer_property.getText() != null && !"".equals(customer_property.getText())) {
            info.setCustomerProperty(customer_property.getText());
        }
        // 开户银行
        if ( info_bank.getText() != null && !"".equals(info_bank.getText())) {
            info.setBank(info_bank.getText());
        }
        // 银行账户
        if (!("".equals(info_bank_acount.getText())) && info_bank_acount.getText() != null) {
            info.setBankAccount(info_bank_acount.getText());
        }
        // 税务登记号
        if (!("".equals(info_tax_no.getText())) && info_tax_no.getText() != null) {
            info.setTaxRegister(info_tax_no.getText());
        }
        // 所属区域
        if (info_area_com.getSelectionModel().getSelectedItem() != null) {
            info.setArea(info_area_com.getSelectionModel().getSelectedItem().toString());
        }
        // 所属区域详情
        if (!("".equals(info_area_str.getText())) && info_area_str.getText() != null) {
            info.setAreaInfo(info_area_str.getText());
        }
        // 账款备注
        if (!("".equals(info_acount_remark.getText())) && info_acount_remark.getText() != null) {
            info.setAccountRemark(info_acount_remark.getText());
        }
        // 所属地区
        if (info_region.getSelectionModel().getSelectedItem() != null) {
            info.setRegion(info_region.getSelectionModel().getSelectedItem().toString());
        }
        // 客户等级
        if (info_customer_level.getSelectionModel().getSelectedItem() != null) {
            try {
                info.setCustomerLevel(info_customer_level.getSelectionModel().getSelectedIndex());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 所属公司
        if (info_company.getSelectionModel().getSelectedItem() != null) {
            info.setCompanyAffiliation(info_company.getSelectionModel().getSelectedItem().toString());
        }
        // 销售单需回传
        info.setSentBack(info_comes_back.selectedProperty().getValue());
        CustomerDetailInfo detailInfo = iCustomerDetailInfoService.getCustomerDetailInfoByCustomerId(Long.valueOf(customerid));
        if (detailInfo == null) {
            info.setAddtime(new Date());
            int rows = iCustomerDetailInfoService.save(info);
            if (rows > 0) {
                setControllerDisable();
            } else {
                alert_informationDialog("客户详情新增失败！");
            }
        } else {
            info.setId(detailInfo.getId());
            int rows = iCustomerDetailInfoService.updateNotNull(info);
            if (rows > 0) {
                setControllerDisable();
            } else {
                alert_informationDialog("客户详情修改失败！");
            }
        }
    }

    /********************************************* 客户公共部分结束 ********************************************/

    /********************************************* 送货地址开始 ********************************************/

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO 添加送货地址
     * @Date 10:35 2018/8/20
     * @Param []
     **/
    @FXML
    public void address_add() {
        String type = "1";
        if(address_combox_type.getSelectionModel().getSelectedItem() != null){
            type = address_combox_type.getSelectionModel().getSelectedItem().toString();
        }
        String contact = address_txt_contact.getText();
        String info = address_txt_info.getText();
        String phone = address_txt_phone.getText();
        String title = address_txt_name.getText();
        ShippingAddressProperty address = new ShippingAddressProperty(type, title, info, phone, contact);

        List<ShippingAddressProperty> propertyList = shipping_address_table.getItems();
        propertyList.add(address);
        address_txt_contact.clear();
        address_txt_info.clear();
        address_txt_phone.clear();
        address_txt_name.clear();
        shipping_address_table.setItems(FXCollections.observableArrayList(propertyList));

        showAddressGeneral(propertyList);

//        if (!"".equals(customer_id.getText()) && customer_id.getText() != null) {
//            address.setCustomerid(Long.valueOf(customer_id.getText()));
//            int rows = addressService.save(address);
//            if (rows > 0) {
//                clearAddressVal();
//                // 刷新数据
//                showAddressList();
//            } else {
//                alert_informationDialog("保存失败，系统出错了！");
//            }
//        }
    }




    public static enum Participation {
        设为默认;
    }

    public static enum ParticipationUsual {
        常用;
    }


    /**
     * 表格中下拉框的值
     */
    static ObservableList<String> levelChoice = null;

    public static ObservableList<String> getLevelChoice() {
        return levelChoice;
    }

    public static void setLevelChoice(ObservableList<String> levelChoice) {
        CustomerBasicInfoController.levelChoice = levelChoice;
    }

    /**
     * @Author BlueSky
     * @Description //TODO  送货地址查询
     * @Date 11:20 2018/8/17
     **/
    public void showAddressList() {

        String customerid = customer_id.getText();
        if (customerid != null && !("".equals(customerid))) {
            List<CustomerShippingAddress> addressList = iCustomerShippingAddressService.listAll(Long.valueOf(customerid));
            List<ShippingAddressProperty> addressListNew = new ArrayList<>();
            if(addressList != null){
                addressList.forEach(p -> {
                    Participation part = null;
                    if (p.getSetting() != null && p.getSetting()) {
                        part = Participation.设为默认;
                    }
                    addressListNew.add(new ShippingAddressProperty(p.getId(), p.getCustomerid(), p.getType(), p.getTitle(), p.getAddress(), p.getPhone(), p.getContact(), part));
                });
            }
            showAddressGeneral(addressListNew);
        }else{
            showAddressGeneral(null);
        }
    }

    public void showAddressGeneral(List<ShippingAddressProperty> addressListNew){
        shipping_address_table.setEditable(true);

        address_col_type.setCellFactory(column -> EditCell.createStringEditCell());
        address_col_title.setCellFactory(column -> EditCell.createStringEditCell());
        address_col_contact.setCellFactory(column -> EditCell.createStringEditCell());
        address_col_address.setCellFactory(column -> EditCell.createStringEditCell());
        address_col_phone.setCellFactory(column -> EditCell.createStringEditCell());
        address_col_setting.setCellFactory((param) -> new TableViewSample.RadioButtonCell<CustomerShippingAddress, Participation>(EnumSet.allOf(Participation.class)));

        // 查询地址集合
        final ObservableList<ShippingAddressProperty> data = FXCollections.observableArrayList(addressListNew);
        address_col_id.setCellValueFactory(new PropertyValueFactory<ShippingAddressProperty, Long>("id"));
        address_col_type.setCellValueFactory(new PropertyValueFactory("type"));
        address_col_title.setCellValueFactory(new PropertyValueFactory("title"));
        address_col_address.setCellValueFactory(new PropertyValueFactory("address"));
        address_col_phone.setCellValueFactory(new PropertyValueFactory("phone"));
        address_col_contact.setCellValueFactory(new PropertyValueFactory("contact"));
        address_col_setting.setCellValueFactory(new PropertyValueFactory<ShippingAddressProperty, Participation>("participation"));

        shipping_address_table.setItems(data);

        shipping_address_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // 默认地址选择
        address_col_setting.setOnEditCommit(evt -> {
            boolean inputValid = false;
            if (!inputValid) {
                int row = shipping_address_table.getEditingCell().getRow();

                ShippingAddressProperty selected = (ShippingAddressProperty) shipping_address_table.getItems().get(row);
                // 清除所有默认
                iCustomerShippingAddressService.updateClearAddressDefault(selected.getCustomerId());
                // 设置为默认
                int count = iCustomerShippingAddressService.updateAddressDefault(selected.getId());
                base_shipping_address.setText(selected.getAddress());
                if (count > 0) {
                    showAddressList();
                }
                shipping_address_table.requestFocus(); // get back focus
                shipping_address_table.edit(row, address_col_setting);
            }
        });
    }

    /**
     * 保存tableview中送货地址的数据
     */
    @FXML
    public void saveAddress() {

            String customerid = customer_id.getText();
            if (customerid != null && !"".equals(customerid) && shipping_address_table.getItems() != null) {
                int rows = shipping_address_table.getItems().size();
                for (int i = 0; i < rows; i++) {
                    ShippingAddressProperty ad = null;
                    if (shipping_address_table.getItems().get(i) != null) {
                        ad = (ShippingAddressProperty) shipping_address_table.getItems().get(i);
                    }
                    CustomerShippingAddress address = new CustomerShippingAddress();
                    if (ad.getType() != null) {
                        address.setType(ad.getType());
                    }
                    if (ad.getTitle() != null) {
                        address.setTitle(ad.getTitle());
                    }
                    if (ad.getAddress() != null) {
                        address.setAddress(ad.getAddress());
                    }
                    if (ad.getContact() != null) {
                        address.setContact(ad.getContact());
                    }
                    if (ad.getPhone() != null) {
                        address.setPhone(ad.getPhone());
                    }
                    if (ad.getParticipation() != null) {
                        address.setSetting(true);
                        base_shipping_address.setText(ad.getAddress());
                    }
                    address.setCustomerid(Long.valueOf(customerid));
                    if (ad.getId() == null || ad.getId() == 0) {
                        address.setAddtime(new Date());
                        try {
                            if (!(ad.getAddress() == null && ad.getContact() == null && ad.getPhone() == null && ad.getType() == null)) {
                                iCustomerShippingAddressService.save(address);
//                                iCustomerShippingAddressService.updateClearAddressDefault(ad.getCustomerId());
//                                iCustomerShippingAddressService.updateAddressDefault(address.getId());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        address.setId(ad.getId());
                        try {
                            iCustomerShippingAddressService.updateNotNull(address);
//                            if (address.getSetting() != null && address.getSetting()) {
//                                iCustomerShippingAddressService.updateClearAddressDefault(ad.getCustomerId());
//                                iCustomerShippingAddressService.updateAddressDefault(address.getId());
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                alert_informationDialog("保存成功！");
            }
            // 刷新地址信息
//            showAddressList();

    }

    /**
     * 清空地址信息
     */
    public void clearAddressVal() {
        address_combox_type.getSelectionModel().selectFirst();
        address_txt_contact.clear();
        address_txt_info.clear();
        address_txt_phone.clear();
        address_txt_name.clear();
        shipping_address_table.getItems().clear();
        shipping_address_table.refresh();
    }


    /**
     * enter 键盘按下触发
     *
     * @param event
     */
    public void tableViewInsertKey(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addAddress();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteAddress();
        }
    }


    /**
     * 删除地址
     */
    private void deleteAddress() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            // 取得当前行的数据
            try {
                if (shipping_address_table.getSelectionModel().getSelectedCells().size() == 0) {
                    return;
                }
                TablePosition pos = (TablePosition) shipping_address_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                ShippingAddressProperty product = (ShippingAddressProperty)shipping_address_table.getItems().get(index);
                if(product.getId() != null && product.getId() != 0L){
                    int rows = iCustomerShippingAddressService.delete(product.getId());
                    returnMsg(rows);
                }
                final ObservableList<ShippingAddressProperty> dataProperty = shipping_address_table.getItems();
                final ObservableList<ShippingAddressProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                shipping_address_table.setItems(newDataProperty);
            } catch (Exception e) {
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }

    /**
     * 添加新地址
     */
    public void addAddress() {

        ObservableList<ShippingAddressProperty> list = shipping_address_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        if (customer_id.getText() != null && !"".equals(customer_id.getText())) {
            list.add(new ShippingAddressProperty(Long.valueOf(customer_id.getText()), "", "", "", "", ""));        //list添加值对象
        }

        shipping_address_table.setItems(list);

    }


    /********************************************* 送货地址结束 ********************************************/


    /**
     * 业务负责人
     * enter 键盘按下触发
     *
     * @param event
     */
    @FXML
    public void KeyOfBusinessTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addBusinessRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfBusiness();

        }
    }

    /**
     * 行删除 业务负责人
     */
    private void deleteRowOfBusiness() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
// 取得当前行的数据
            try {
                if (customer_business_leader_tab.getSelectionModel().getSelectedCells().size() == 0) {
                    return;
                }
                TablePosition pos = (TablePosition) customer_business_leader_tab.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                CustomerBusinessLeaderProperty property = (CustomerBusinessLeaderProperty) customer_business_leader_tab.getItems().get(index);
                if (property.getId() != null && property.getId() != 0L) {
                    int rows = iCustomerBusinessLeaderService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<CustomerBusinessLeader> dataProperty = customer_business_leader_tab.getItems();
                final ObservableList<CustomerBusinessLeader> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if (i != index) {
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                customer_business_leader_tab.setItems(newDataProperty);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 行添加 业务负责人
     */
    public void addBusinessRow() {

        ObservableList<CustomerBusinessLeaderProperty> list = customer_business_leader_tab.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new CustomerBusinessLeaderProperty("", "", "", "", "", ""));
        customer_business_leader_tab.setItems(list);
    }

    /**
     * 联系人
     * enter 键盘按下触发
     *
     * @param event
     */
    @FXML
    public void KeyOfContactTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addContactRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfContact();

        }
    }

    /**
     * 行删除 联系人
     */
    private void deleteRowOfContact() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
// 取得当前行的数据
            try {
                if(customer_contact_tab.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) customer_contact_tab.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                CustomerContactsProperty property = (CustomerContactsProperty) customer_contact_tab.getItems().get(index);
                if (property.getId() != null && property.getId() != 0L) {
                    int rows = iCustomerContactsService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<CustomerContactsProperty> dataProperty = customer_contact_tab.getItems();
                final ObservableList<CustomerContactsProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if (i != index) {
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                customer_contact_tab.setItems(newDataProperty);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 行添加 联系人
     */
    public void addContactRow() {

        ObservableList<CustomerContactsProperty> list = customer_contact_tab.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new CustomerContactsProperty("", "", "", "", "", "", "", "", "", ""));
        customer_contact_tab.setItems(list);
    }

    /**
     * 资料维护
     * enter 键盘按下触发
     *
     * @param event
     */
    @FXML
    public void KeyOfMaintenanceTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addMaintenanceRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfMaintenance();

        }
    }

    /**
     * 行删除 资料维护
     */
    private void deleteRowOfMaintenance() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
// 取得当前行的数据
            try {
                if(customer_data_maintenance_tab.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) customer_data_maintenance_tab.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                CustomerDataMaintenanceProperty property = (CustomerDataMaintenanceProperty) customer_data_maintenance_tab.getItems().get(index);
                if (property.getId() != null && property.getId() != 0L) {
                    int rows = iCustomerContactsService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<CustomerDataMaintenanceProperty> dataProperty = customer_data_maintenance_tab.getItems();
                final ObservableList<CustomerDataMaintenanceProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if (i != index) {
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                customer_data_maintenance_tab.setItems(newDataProperty);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 行添加 资料维护
     */
    public void addMaintenanceRow() {

        ObservableList<CustomerDataMaintenanceProperty> list = customer_data_maintenance_tab.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new CustomerDataMaintenanceProperty("", "", "", "", "", "", "", "", "", "", "", "", ""));
        customer_data_maintenance_tab.setItems(list);
    }

    /**
     * 备注view
     * enter 键盘按下触发
     *
     * @param event
     */
    @FXML
    public void KeyOfRemarkTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addRemarkRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfRemark();
        }
    }

    /**
     * 行添加 备注
     */
    public void addRemarkRow() {

        ObservableList<RemarkProperty> list = customer_remark_tab.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new RemarkProperty(""));
        customer_remark_tab.setItems(list);
    }


    /**
     * 行删除 备注
     */
    private void deleteRowOfRemark() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            // 取得当前行的数据
            try {
                if(customer_remark_tab.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) customer_remark_tab.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                RemarkProperty property = (RemarkProperty) customer_remark_tab.getItems().get(index);
                if (property.getId() != null && property.getId() != 0L) {
                    int rows = iRemarkService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<RemarkProperty> dataProperty = customer_remark_tab.getItems();
                final ObservableList<RemarkProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if (i != index) {
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                customer_remark_tab.setItems(newDataProperty);
            } catch (Exception e) {
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }

    /**
     * 保存 备注 tableview数据
     */
    private void saveTableviewRemark() {
        String id = customer_id.getText();
        if (id != null && !"".equals(id) && customer_remark_tab.getItems() != null) {
            int tableSize = customer_remark_tab.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                RemarkProperty property = null;
                if (customer_remark_tab.getItems().get(i) != null) {
                    property = (RemarkProperty) customer_remark_tab.getItems().get(i);
                }
                Remark product = new Remark();
                if (property.getRemark() != null && !"".equals(property.getRemark())) {
                    product.setRemark(property.getRemark());
                }
                product.setTypeid(1);
                product.setOtherid(Long.valueOf(id));
                if (property.getId() == null || property.getId() == 0) {
                    try {
                        product.setAddtime(new Date());
                        iRemarkService.save(product);
                        setCustomerRemarkVal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        product.setId(property.getId());
                        iRemarkService.updateNotNull(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 保存 业务负责人 tableview数据
     */
    private void saveTableviewBusiness() {
        String id = customer_id.getText();
        if (id != null && !"".equals(id) && customer_business_leader_tab.getItems() != null) {
            int tableSize = customer_business_leader_tab.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                CustomerBusinessLeaderProperty property = null;
                if (customer_business_leader_tab.getItems().get(i) != null) {
                    property = (CustomerBusinessLeaderProperty) customer_business_leader_tab.getItems().get(i);
                }
                CustomerBusinessLeader product = new CustomerBusinessLeader();

                if (property.getEmployeeCode() != null && !"".equals(property.getEmployeeCode())) {
                    product.setEmployeeCode(property.getEmployeeCode());
                }
                if (property.getName() != null && !"".equals(property.getName())) {
                    product.setName(property.getName());
                }
                if (property.getPrimaryPeople() != null && !"".equals(property.getPrimaryPeople())) {
                    product.setPrimaryPeople(property.getPrimaryPeople());
                }
                if (property.getAssistant() != null && !"".equals(property.getAssistant())) {
                    product.setAssistant(property.getAssistant());
                }
                if (property.getRemark() != null && !"".equals(property.getRemark())) {
                    product.setRemark(property.getRemark());
                }
                product.setCustomerId(Long.valueOf(id));

                if (property.getId() == null || property.getId() == 0) {
                    try {
                        product.setAddtime(new Date());
                        iCustomerBusinessLeaderService.save(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        product.setId(property.getId());
                        iCustomerBusinessLeaderService.updateNotNull(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存 资料维护 tableview数据
     */
    private  void saveTableviewMaintenance() {
        String id = customer_id.getText();
        if (id != null && !"".equals(id) && customer_data_maintenance_tab.getItems() != null) {
            int tableSize = customer_data_maintenance_tab.getItems().size();
            try {
                for (int i = 0; i < tableSize; i++) {
                    CustomerDataMaintenanceProperty property = null;
                    if (customer_data_maintenance_tab.getItems().get(i) != null) {
                        property = (CustomerDataMaintenanceProperty) customer_data_maintenance_tab.getItems().get(i);
                    }
                    CustomerDataMaintenance product = new CustomerDataMaintenance();

                    if (property.getIndustry() != null && !"".equals(property.getIndustry())) {
                        product.setIndustry(property.getIndustry());
                    }
                    if (property.getLastYearBusiness() != null && !"".equals(property.getLastYearBusiness())) {
                        product.setLastYearBusiness(property.getLastYearBusiness());
                    }
                    if (property.getLeaderPeople() != null && !"".equals(property.getLeaderPeople())) {
                        product.setLeaderPeople(property.getLeaderPeople());
                    }
                    if (property.getPurchasePeople() != null && !"".equals(property.getPurchasePeople())) {
                        product.setPurchasePeople(property.getPurchasePeople());
                    }
                    if (property.getStartupYear() != null && !"".equals(property.getStartupYear())) {
                        Date date = df.parse(property.getStartupYear());
                        product.setStartupYear(date);
                    }
                    if (property.getTelephone() != null && !"".equals(property.getTelephone())) {
                        product.setTelephone(property.getTelephone());
                    }
                    if (property.getYearPlan() != null && !"".equals(property.getYearPlan())) {
                        product.setYearPlan(property.getYearPlan());
                    }
                    if (property.getFax() != null && !"".equals(property.getFax())) {
                        product.setFax(property.getFax());
                    }
                    if (property.getEmployeeNumber() != null && !"".equals(property.getEmployeeNumber())) {
                        product.setEmployeeNumber(Integer.valueOf(property.getEmployeeNumber()));
                    }
                    if (property.getDocumentNo() != null && !"".equals(property.getDocumentNo())) {
                        product.setDocumentNo(property.getDocumentNo());
                    }
                    if (property.getCreateDate() != null && !"".equals(property.getCreateDate())) {
                        Date date = df.parse(property.getCreateDate());
                        product.setCreateDate(date);
                    }
                    if (property.getContact() != null && !"".equals(property.getContact())) {
                        product.setContact(property.getContact());
                    }
                    product.setCustomerId(Long.valueOf(id));
                    if (property.getId() == null || property.getId() == 0) {
                        product.setAddtime(new Date());
                        iCustomerDataMaintenanceService.save(product);
                    } else {
                        product.setId(property.getId());
                        iCustomerDataMaintenanceService.updateNotNull(product);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存 联系人 tableview数据
     */
    private void saveTableviewContact() {
        String id = customer_id.getText();
        if (id != null && !"".equals(id) && customer_contact_tab.getItems() != null) {
            int tableSize = customer_contact_tab.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                CustomerContactsProperty property = null;
                if (customer_contact_tab.getItems().get(i) != null) {
                    property = (CustomerContactsProperty) customer_contact_tab.getItems().get(i);
                }
                CustomerContacts product = new CustomerContacts();

                if (property.getDepartment() != null && !"".equals(property.getDepartment())) {
                    product.setDepartment(property.getDepartment());
                }
                if (property.getEmail() != null && !"".equals(property.getEmail())) {
                    product.setEmail(property.getEmail());
                }
                if (property.getFax() != null && !"".equals(property.getFax())) {
                    product.setFax(property.getFax());
                }
                if (property.getMobilePhone() != null && !"".equals(property.getMobilePhone())) {
                    product.setMobilePhone(property.getMobilePhone());
                }
                if (property.getName() != null && !"".equals(property.getName())) {
                    product.setName(property.getName());
                }
                if (property.getPosition() != null && !"".equals(property.getPosition())) {
                    product.setPosition(property.getPosition());
                }
                if (property.getRemark() != null && !"".equals(property.getRemark())) {
                    product.setRemark(property.getRemark());
                }
                if (property.getTelephone() != null && !"".equals(property.getTelephone())) {
                    product.setTelephone(property.getTelephone());
                }

                product.setCustomerId(Long.valueOf(id));

                if (property.getId() == null || property.getId() == 0) {
                    try {
                        product.setAddtime(new Date());
                        iCustomerContactsService.save(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        product.setId(property.getId());
                        iCustomerContactsService.updateNotNull(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 详细信息tab中的发票明细表格数据

    /**
     * 发票明细 view
     * enter 键盘按下触发
     *
     * @param event
     */
    @FXML
    public void KeyOfInvoiceTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addInviceRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfInvice();
        }
    }

    /**
     * 行添加 发票明细
     */
    public void addInviceRow() {

        ObservableList<InvoiceProperty> list = customer_invoice_tab.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new InvoiceProperty("", "", "", "", "", "", "", ""));
        customer_invoice_tab.setItems(list);
    }


    /**
     * 行删除 发票明细
     */
    private void deleteRowOfInvice() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            // 取得当前行的数据
            try {
                if(customer_invoice_tab.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) customer_invoice_tab.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                InvoiceProperty property = (InvoiceProperty) customer_invoice_tab.getItems().get(index);
                if (property.getId() != null && property.getId() != 0L) {
                    int rows = iInvoiceService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<InvoiceProperty> dataProperty = customer_invoice_tab.getItems();
                final ObservableList<InvoiceProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if (i != index) {
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                customer_invoice_tab.setItems(newDataProperty);
            } catch (Exception e) {
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }

    }

    /**
     * 保存 发票明细 tableview数据
     */
    @SneakyThrows(Exception.class)
    private void saveTableviewInvice() {
        String id = customer_id.getText();
        if (id != null && !"".equals(id) && customer_invoice_tab.getItems() != null) {
            int tableSize = customer_invoice_tab.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                InvoiceProperty property = null;
                if (customer_invoice_tab.getItems().get(i) != null) {
                    property = (InvoiceProperty) customer_invoice_tab.getItems().get(i);
                }
                Invoice product = new Invoice();
                if (property.isParticipationStop()) {
                    product.setDisabled(true);
                } else {
                    product.setDisabled(false);
                }
                if(property.getDisdate() != null && !"".equals(property.getDisdate())){
                    product.setDisdate(DateUtils.getDateByPattern(property.getDisdate(),DateUtils.FORMAT_YYYY_MM_DD));
                }
                if (property.getRemark() != null && !"".equals(property.getRemark())) {
                    product.setRemark(property.getRemark());
                }
                if (property.getAddress() != null && !"".equals(property.getAddress())) {
                    product.setAddress(property.getAddress());
                }
                if (property.getContact() != null && !"".equals(property.getContact())) {
                    product.setContact(property.getContact());
                }
                if (property.getPhone() != null && !"".equals(property.getPhone())) {
                    product.setPhone(property.getPhone());
                }
                if (property.getTitle() != null && !"".equals(property.getTitle())) {
                    product.setTitle(property.getTitle());
                }
                product.setCustomerId(Long.valueOf(id));
                if (property.getId() == null || property.getId() == 0) {
                    try {
                        product.setAddtime(new Date());
                        iInvoiceService.save(product);
                        setCustomerRemarkVal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        product.setId(property.getId());
                        iInvoiceService.updateNotNull(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            showInvoice();
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
        Stage stage = new Stage();
        Pane pane = new Pane();

        //将第二个窗口保存到map中
        StageManager.STAGE.put("clientQuery", stage);
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerBasicInfoController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private ImageView rightImage;

    /**
     * 右侧查询Panne
     */
    public void shoRightPane(){

        Stage stageStock = (Stage) StageManager.CONTROLLER.get("rightWinStage");

        StageManager.rightWin = !StageManager.rightWin;

        if(StageManager.rightWin){
            attrImages(rightImage, PathUtil.imageLeft);
            stageStock.setTitle("客户基本资料");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/customer/loadMoreCustomer.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            Stage stageHome = (Stage)StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stageHome.getScene().getWindow().getWidth()+stageHome.getScene().getWindow().getX()-15);
            stageStock.setY(stageHome.getScene().getWindow().getY());

            loadSupplierRight(false);
            stageStock.setResizable(false); //禁止窗体缩放
            stageStock.show();
        }else{
            attrImages(rightImage,PathUtil.imageRight);
            stageStock.close();
        }

    }

    @FXML
    private TableView rightCustomerTableView;
    @FXML
    private TableColumn rightCustomerNum;
    @FXML
    private TableColumn rightCustomerNo;
    @FXML
    private TableColumn rightCustomerDes;

    @FXML
    private CheckBox rightCheckBox;


    /**
     * @Description 加载右侧客户数据
     * @Author BlueSky
     * @Date 20:08 2019/4/25
     **/
    public  void loadSupplierRight(boolean isStop){

        rightCheckBox.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                boolean isbool =(boolean)newValue;

                loadSupplierRight(isbool);

            }
        });

        List<Customer> customerList = iCustomerService.listExistCustomer("",isStop?"":"0",1, Integer.MAX_VALUE);



        rightCustomerNum.setCellValueFactory(new PropertyValueFactory("no"));
        rightCustomerNo.setCellValueFactory(new PropertyValueFactory("customerCode"));
        rightCustomerDes.setCellValueFactory(new PropertyValueFactory("shortName"));

        if(customerList != null && customerList.size() > 0){

            for (int i=0,len = customerList.size();i < len;i++) {
                customerList.get(i).setNo(i+1);
            }
        }

        rightCustomerTableView.setItems(FXCollections.observableArrayList(customerList));


        rightCustomerTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observableValue, Customer oldItem, Customer newItem) {
                Customer customer = iCustomerService.selectByKey(Long.valueOf(newItem.getId().toString()));
                if(customer != null){
                    setCustomerTextFieldVal(customer);
                    customer_id.setText(customer.getId().toString());
                    selectAllPane();
                }
            }
        });

    }

}
