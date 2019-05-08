package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.*;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.customer.CustomerBasic;
import com.yc.education.model.customer.CustomerDetailInfo;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.service.account.IAccountSaleInvoiceInfoService;
import com.yc.education.service.account.IAccountSaleInvoiceService;
import com.yc.education.service.customer.ICustomerBasicService;
import com.yc.education.service.customer.ICustomerDetailInfoService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import com.yc.education.service.sale.ISaleReturnGoodsService;
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
 * @Description 账款 -- 业务查询 -- 销项发票
 * @Author BlueSky
 * @Date 2019-01-07 10:18
 */
@Controller
public class AccountSaleInvoiceController extends BaseController implements Initializable {

    @Autowired ICustomerService iCustomerService;
    @Autowired ICustomerBasicService iCustomerBasicService;
    @Autowired ICustomerDetailInfoService iCustomerDetailInfoService;
    @Autowired ISaleGoodsService iSaleGoodsService;                                 // 销货单
    @Autowired IAccountSaleInvoiceService iAccountSaleInvoiceService;               // 销项发票
    @Autowired IAccountSaleInvoiceInfoService iAccountSaleInvoiceInfoService;       // 销项发票详情
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;                   // 销货单详情
    @Autowired ISaleReturnGoodsService iSaleReturnGoodsService;                     // 销退单
    @Autowired ISaleReturnGoodsProductService iSaleReturnGoodsProductService;       // 销退单详情
    // 日期格式
    LocalDateTime localDate = LocalDateTime.now();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    static int changeId = 0;      // 记录当前编辑计算金额cell 的id值s
    Stage stage = new Stage();
    @FXML
    VBox menu_first;           // 第一页
    @FXML
    VBox menu_prev;            // 上一页
    @FXML
    VBox menu_next;            // 下一页
    @FXML
    VBox menu_last;            // 最后一页
    @FXML
    VBox menu_clearAll;        // 清除
    @FXML
    VBox menu_commit;          // 提交
    @FXML
    VBox menu_update;          // 修改
    @FXML
    VBox import_in;            // 导入
    @FXML
    VBox menu_insert;          // 新增
    @FXML
    VBox menu_delete;          // 删除
    @FXML
    VBox shiro_success;        // 审核
    @FXML
    Label writestate;          // 待输入

    @FXML
    TextField order_no;        // 单号
    @FXML
    DatePicker create_date;        // 制单日期
    @FXML
    Button update_btn;        // 上边修改按钮
    @FXML
    TextField invoice_no;        // 发票号码
    @FXML
    TextField made_people;        // 制单人
    @FXML
    ComboBox invoice_type;        // 发票类型
    @FXML
    DatePicker invoice_date;        // 发票日期

    // 发票信息 tab
    @FXML
    ComboBox payment_method;        // 结算方式
    @FXML
    TextField taxpayer_no;         // 纳税人登记号
    @FXML
    ComboBox customer_no;           // 客户编号
    @FXML
    TextField bank_no;             // 账户账号
    @FXML
    TextField audit;                // 审核人
    @FXML
    TextField audit_str;            // 审核人描述
    @FXML
    TextField last_update;         // 最后修改人
    @FXML
    TextField last_update_str;        // 最后修改人描述
    @FXML
    TextField invoice_address;        // 发票地址
    @FXML
    TextField invoice_title;        // 发票抬头
    @FXML
    TextField company;             // 所属公司
    @FXML
    ComboBox currency;             // 币别
    @FXML
    TextField bank;                // 开户银行
    @FXML
    DatePicker payment_date;        // 结算日期
    @FXML
    TextField customer_no_str;        // 客户编号描述
    @FXML
    Button btn_cancel_lation;     //作废按钮

    // 发票明细tab
    @FXML
    TextField adjust;             //分角调整
    @FXML
    Button btn_sure;           //分角调整确认按钮
    @FXML
    TextField redmark;             //分角调整
    @FXML
    Button btn_redmark;           //红字发票确认按钮
    @FXML
    TextField total_money;        // 金额总计
    @FXML
    TextField total_loan;         // 贷款合计
    @FXML
    TextField total_tax;          // 税款合计
    @FXML
    DatePicker create_date_ben;        // 发票日期
    @FXML
    DatePicker create_date_end;        // 发票日期

    @FXML
    TableView invoice_table;          //发票明细
    @FXML
    TableColumn col_id;               //ID
    @FXML
    TableColumn col_no;               //序号
    @FXML
    TableColumn col_source;           //来源单据
    @FXML
    TableColumn col_order_no;         //单号
    @FXML
    TableColumn col_customer_no;      //客户编号
    @FXML
    TableColumn col_customer_short;      //客户简称
    @FXML
    TableColumn col_product_no;       //产品编号
    @FXML
    TableColumn col_product_name;      //产品名称
    @FXML
    TableColumn col_invoce;           //发票品名
    @FXML
    TableColumn col_unit;             // 单位
    @FXML
    TableColumn col_num;              // 数量
    @FXML
    TableColumn col_price;            // 单价
    @FXML
    TableColumn col_money;            //金额
    @FXML
    TableColumn col_rate;             // 税率
    @FXML
    TableColumn col_rate_money;       // 税额
    @FXML
    TableColumn col_tax;              // 税别
    @FXML
    TableColumn col_rate_not;         // 未税小计
    @FXML
    TableColumn col_remark;           // 备注


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setControllerDisable();
        setComboBox(35L, invoice_type, 0);      //发票种类
        setComboBox(20L, payment_method, 0);    //结算方式
        setComboBox(7L, currency, 0);           //币别
        loadCustomer();
        generalProductTab(new ArrayList<>());
        setMenuValue(1);    // 第一页数据
        invoiceDateQuery();
    }

    /**
     * @Author BlueSky
     * @Description //TODO 单据作废
     * @Date 11:49 2019/4/2
     * @Param []
     **/
    public void btnOrderCancel() {
        if (order_no.getUserData() != null) {
            String orderid = order_no.getUserData().toString();
            AccountSaleInvoice accountSaleInvoice = iAccountSaleInvoiceService.selectByKey(Long.valueOf(orderid));
            if (accountSaleInvoice != null) {
                accountSaleInvoice.setOrderCancel(true);
                int rows = iAccountSaleInvoiceService.updateNotNull(accountSaleInvoice);
                btn_cancel_lation.setDisable(true);
                alertProcessResult(rows);
            }
        }
    }

    /**
     * @Author BlueSky
     * @Description //TODO 分角调整
     * @Date 21:01 2019/4/1
     **/
    public void addAdjust() {
        String money = adjust.getText();
        adjust.clear();
        if (money != null && !"".equals(money)) {
            List<AccountSaleInvoiceInfoProperty> propertyList = invoice_table.getItems() == null ? new ArrayList<>() : invoice_table.getItems();
            propertyList.add(new AccountSaleInvoiceInfoProperty("分角调整", "", "", "", "", "", "", "", "", "", "", money, "", "", "", "", ""));
            generalProductTab(propertyList);
            if (total_loan.getText() != null && !"0.00".equals(total_loan.getText())) {
                total_loan.setText(new BigDecimal(total_loan.getText()).add(new BigDecimal(money)).toString());
                total_money.setText(new BigDecimal(total_money.getText()).add(new BigDecimal(money)).toString());
            } else {
                total_loan.setText(money);
                total_money.setText(money);
            }
        }
    }

    /**
     * @Description 红字发票
     * @Author BlueSky
     * @Date 15:52 2019/4/30
     **/
    public void addRedmark() {
        String money = redmark.getText();
        redmark.clear();
        if (money != null && !"".equals(money)) {
            List<AccountSaleInvoiceInfoProperty> propertyList = invoice_table.getItems() == null ? new ArrayList<>() : invoice_table.getItems();
            propertyList.add(new AccountSaleInvoiceInfoProperty("", "", "", "", "", "", "", "", "", "", "", money, "", "", "", "", ""));
            generalProductTab(propertyList);
            if (total_loan.getText() != null && !"0.00".equals(total_loan.getText())) {
                total_loan.setText(new BigDecimal(total_loan.getText()).add(new BigDecimal(money)).toString());
                total_money.setText(new BigDecimal(total_money.getText()).add(new BigDecimal(money)).toString());
            } else {
                total_loan.setText(money);
                total_money.setText(money);
            }
        }
    }

    /**
     * 根据发票日期查询
     **/
    void queryByInvoiceDate() {
        LocalDate dateBen = create_date_ben.getValue();
        LocalDate dateEnd = create_date_end.getValue();
        List<SaleGoodsProduct> saleGoodsProductList = iSaleGoodsProductService.listTimeWhereNoTicket("0", dateBen == null ? "" : dateBen.toString(), dateEnd == null ? "" : dateEnd.toString(), customer_no_str.getText());
        List<SaleReturnGoodsProduct> saleReturnGoodsProductList = iSaleReturnGoodsProductService.listTimeWhere("0", dateBen == null ? "" : dateBen.toString(), dateEnd == null ? "" : dateEnd.toString(), customer_no_str.getText());
        List<AccountSaleInvoiceInfoProperty> propertyList = new ArrayList<>();
        BigDecimal totalMoney = new BigDecimal("0");
        BigDecimal totalLoan = new BigDecimal("0");
        BigDecimal totalTax = new BigDecimal("0");
        if (saleGoodsProductList != null) {
            int rows = 1;
            for (SaleGoodsProduct p : saleGoodsProductList) {
                BigDecimal money = "外加".equals(p.getSaleGoods().getTax()) ? p.getMoney().multiply(new BigDecimal(p.getSaleGoods().getTaxRate() + "")).setScale(2, BigDecimal.ROUND_UP) : p.getMoney();
                propertyList.add(new AccountSaleInvoiceInfoProperty("销货单", p.getSaleGoods().getSaleNo(), (rows++) + "", p.getSaleGoods().getCustomerNo(), p.getSaleGoods().getCustomerNoStr(), p.getProductNo(), p.getProductName(), "", p.getUnit(), p.getNum() == null ? "0" : p.getNum().toString(), p.getPrice() == null ? "0.00" : p.getPrice().toString(), p.getMoney() == null ? "0.00" : p.getMoney().toString(), p.getSaleGoods().getTaxRate() == null ? "0" : p.getSaleGoods().getTaxRate().toString(), money.toString(), p.getSaleGoods().getTax(), p.getMoney() == null ? "0.00" : p.getMoney().toString(), p.getRemark()));
                totalLoan = totalLoan.add(new BigDecimal(p.getMoney().toString()));
                totalTax = totalTax.add(money);
            }
        }
        if (saleReturnGoodsProductList != null) {
            int rows = 1;
            for (SaleReturnGoodsProduct p : saleReturnGoodsProductList) {
                BigDecimal money = "外加".equals(p.getSaleReturnGoods().getTax()) ? p.getMoney().multiply(new BigDecimal(p.getSaleReturnGoods().getTaxRate() + "")).setScale(2, BigDecimal.ROUND_UP) : p.getMoney();
                propertyList.add(new AccountSaleInvoiceInfoProperty("销售退货单", p.getSaleReturnGoods().getPinbackNo(), (rows++) + "", p.getSaleReturnGoods().getCustomerNo(), p.getSaleReturnGoods().getCustomerNoStr(), p.getProductNo(), p.getProductName(), "", p.getUnit(), p.getNum() == null ? "0" : p.getNum().toString(), p.getPrice() == null ? "0.00" : p.getPrice().toString(), p.getMoney() == null ? "0.00" : p.getMoney().toString(), p.getSaleReturnGoods().getTaxRate() == null ? "0" : p.getSaleReturnGoods().getTaxRate().toString(), money.toString(), p.getSaleReturnGoods().getTax() == null ? "" : p.getSaleReturnGoods().getTax(), p.getMoney() == null ? "0.00" : p.getMoney().toString(), p.getRemark()));
                totalLoan = totalLoan.add(new BigDecimal(p.getMoney().toString()));
                totalTax = totalTax.add(money);
            }
        }
        totalMoney = totalLoan.add(totalTax);
        total_money.setText(totalMoney.toString());
        total_loan.setText(totalLoan.toString());
        total_tax.setText(totalTax.toString());
        generalProductTab(propertyList);
        if (propertyList == null || propertyList.size() == 0) {
            alert_informationDialog("暂无数据！");
        }
    }

    /**
     * 发票日期选定
     **/
    public void invoiceDateQuery() {
        create_date_ben.setOnAction((ActionEvent e) -> {
            if (customer_no_str.getText() != null) {
                queryByInvoiceDate();
            }
        });
        create_date_end.setOnAction((ActionEvent e) -> {
            if (customer_no_str.getText() != null) {
                queryByInvoiceDate();
            }
        });
    }

    /**
     * 加载客户信息
     */
    private void loadCustomer() {
        List<Customer> customerList = iCustomerService.listGeneralCustomer();

        for (int i = 0, len = customerList == null ? 0 : customerList.size(); i < len; i++) {
            customer_no.getItems().add(customerList.get(i).getName());
        }
        if (customer_no.getItems() != null) {
            customer_no.getSelectionModel().select(0);
        }

        customer_no.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (!"".equals(newValue) && newValue != null) {
                    try {
                        Customer customer = customerList.get(customer_no.getSelectionModel().getSelectedIndex());
                        String customerNo = customer.getCustomerCode();
                        if (customerNo != null && !"".equals(customerNo)) {
                            customer_no_str.setText(customerNo);
                            CustomerBasic customerBasic = iCustomerBasicService.getCustomerBasicByCustomerId(customer.getId());
                            CustomerDetailInfo customerDetailInfo = iCustomerDetailInfoService.getCustomerDetailInfoByCustomerId(customer.getId());
                            if (customerBasic != null) {
                                invoice_title.setText(customer.getName());
                                invoice_address.setText(customerBasic.getShippingAddress());
                                currency.getSelectionModel().select(customerBasic.getInitialQuotaMoneyType());
                                taxpayer_no.setText(customerDetailInfo.getTaxRegister());
                                bank.setText(customerDetailInfo.getBank());
                                bank_no.setText(customerDetailInfo.getBankAccount());
                                payment_method.getSelectionModel().select(customerBasic.getPaymentMethod());
                                company.setText(customerDetailInfo.getCompanyAffiliation());
                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    /**
     * 加载销售单的数据
     *
     * @param order
     */
    public void setSaleGoodsImportVal(SaleGoods order) {
        if (order != null) {
            order_no.setText(createOrderNo(iAccountSaleInvoiceService.getMaxOrderNo()));
            if (order.getCreateDate() != null) {
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
            }
            made_people.setText(order.getMadePeople());
            payment_method.getSelectionModel().select(order.getCarryMethod());
            customer_no.getSelectionModel().select(order.getCustomerNo());
            invoice_address.setText(order.getInvoiceAddress());
            invoice_title.setText(order.getInvoiceTitle());
            currency.getSelectionModel().select(order.getCurrency());
            customer_no_str.setText(order.getCustomerNoStr());

            showTableInvoiceInfo();
        }
    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess() {
        // 有效单据验证
        String id = order_no.getUserData() == null ? null : order_no.getUserData().toString();

        if (id != null && !"".equals(id)) {
            AccountSaleInvoice order = iAccountSaleInvoiceService.selectByKey(Long.valueOf(id));
            setShiroControlYES();
            lastUpdatePeopel(last_update, last_update_str);
            lastUpdatePeopel(audit, audit_str);
            shiroUpdateData(true);
        } else {
            alert_informationDialog("单据还暂未保存，无法审核！");
        }
    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool) {
        String id = order_no.getUserData() == null ? null : order_no.getUserData().toString();

        if (id != null && !"".equals(id)) {
            AccountSaleInvoice goods = iAccountSaleInvoiceService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAudit(audit.getText());
            goods.setAuditStr(audit_str.getText());
            iAccountSaleInvoiceService.updateNotNull(goods);


        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel() {
        // 有效单据验证
        String id = order_no.getUserData() == null ? null : order_no.getUserData().toString();

        if (id == null && "".equals(id)) {
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
    private void setShiroControlYES() {
        shiro_success.setDisable(true);
        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO() {
        shiro_success.setDisable(false);
        menu_update.setDisable(false);
    }

    /**
     * 保存发票明细数据
     */
    private void saveTableInvoiceInfo() {
        String orderid = order_no.getUserData() == null ? null : order_no.getUserData().toString();
        System.out.println("=====================================");
        System.out.println("ID:"+orderid);
        System.out.println("=====================================");
//         删除之前保存的发票记录 然后重新写入保存
//        iAccountSaleInvoiceInfoService.deleteAccountSaleInvoiceInfoByParentId(orderid);
        if (orderid != null && !"".equals(orderid) && invoice_table.getItems() != null) {
            for (int i = 0; i < invoice_table.getItems().size(); i++) {
                AccountSaleInvoiceInfoProperty property = null;
                if (invoice_table.getItems().get(i) != null) {
                    property = (AccountSaleInvoiceInfoProperty) invoice_table.getItems().get(i);
                }
                AccountSaleInvoiceInfo product = new AccountSaleInvoiceInfo();
                if (property.getOrderSoruce() != null && !"".equals(property.getOrderSoruce())) {
                    product.setOrderSoruce(property.getOrderSoruce());
                }
                if (property.getOrderNo() != null && !"".equals(property.getOrderNo())) {
                    product.setOrderNo(property.getOrderNo());
                }
                if (property.getNo() != null && !"".equals(property.getNo())) {
                    product.setNo(property.getNo());
                }
                if (property.getCustomerNo() != null && !"".equals(property.getCustomerNo())) {
                    product.setCustomerNo(property.getCustomerNo());
                }
                if (property.getCustomerStr() != null && !"".equals(property.getCustomerStr())) {
                    product.setCustomerStr(property.getCustomerStr());
                }
                if (property.getProductNo() != null && !"".equals(property.getProductNo())) {
                    product.setProductNo(property.getProductNo());
                }
                if (property.getProductName() != null && !"".equals(property.getProductName())) {
                    product.setProductName(property.getProductName());
                }
                if (property.getInvoceName() != null && !"".equals(property.getInvoceName())) {
                    product.setInvoceName(property.getInvoceName());
                }
                if (property.getUnit() != null && !"".equals(property.getUnit())) {
                    product.setUnit(property.getUnit());
                }
                if (property.getNum() != null && !"".equals(property.getNum())) {
                    try {
                        product.setNum(Integer.valueOf(property.getNum()));
                    } catch (Exception e) {
                        product.setNum(0);
                    }
                }
                if (property.getPrice() != null && !"".equals(property.getPrice())) {
                    try {
                        product.setPrice(new BigDecimal(property.getPrice()));
                    } catch (Exception e) {
                        product.setPrice(new BigDecimal("0.00"));
                    }
                }
                if (property.getMoney() != null && !"".equals(property.getMoney())) {
                    try {
                        product.setMoney(new BigDecimal(property.getMoney()));
                    } catch (Exception e) {
                        product.setMoney(new BigDecimal("0.00"));
                    }
                }
                if (property.getRate() != null && !"".equals(property.getRate())) {
                    product.setRate(property.getRate());
                }
                if (property.getRateMoney() != null && !"".equals(property.getRateMoney())) {
                    try {
                        product.setRateMoney(new BigDecimal(property.getRateMoney()));
                    } catch (Exception e) {
                        product.setRateMoney(new BigDecimal("0.00"));
                    }
                }
                if (property.getTax() != null && !"".equals(property.getTax())) {
                    product.setTax(property.getTax());
                }
                if (property.getRateNot() != null && !"".equals(property.getRateNot())) {
                    try {
                        product.setRateNot(new BigDecimal(property.getRateNot()));
                    } catch (Exception e) {
                        product.setRateNot(new BigDecimal("0.00"));
                    }
                }
                if (property.getRemark() != null && !"".equals(property.getRemark())) {
                    product.setRemark(property.getRemark());
                }
                product.setOtherid(Long.valueOf(orderid));
                if (property.getId() == null || property.getId() == 0) {
                    try {
                        product.setAddtime(new Date());
                        iAccountSaleInvoiceInfoService.save(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        product.setId(property.getId());
                        iAccountSaleInvoiceInfoService.updateNotNull(product);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            showTableInvoiceInfo();
        }
        List<AccountSaleInvoiceInfo> invoiceInfoList = iAccountSaleInvoiceInfoService.listAccountSaleInvoiceInfo(orderid);
        if (invoiceInfoList != null && invoiceInfoList.size() > 0) {
            ArrayList<String> returnList = new ArrayList<String>();
            ArrayList<String> saleList = new ArrayList<String>();
            ArrayList<String> nowReturnList = new ArrayList<String>();
            ArrayList<String> nowSaleList = new ArrayList<String>();
            // orderNo 去重
            for (AccountSaleInvoiceInfo p : invoiceInfoList) {
                if("销售退货单".equals(p.getOrderSoruce())){
                    if (Collections.frequency(invoiceInfoList, p.getOrderNo()) < 1) {
                        returnList.add(p.getOrderNo());
                    }
                }else if("销货单".equals(p.getOrderSoruce())){
                    if (Collections.frequency(invoiceInfoList, p.getOrderNo()) < 1) {
                        saleList.add(p.getOrderNo());
                    }
                }
            }
            for(int i=0;i<returnList.size();i++){
                if(!nowReturnList.contains(returnList.get(i))){
                    nowReturnList.add(returnList.get(i));
                }
            }
            for(int i=0;i<saleList.size();i++){
                if(!nowSaleList.contains(saleList.get(i))){
                    nowSaleList.add(saleList.get(i));
                }
            }
            System.err.println("nowReturnList :"+nowReturnList.size());
            System.err.println("nowSaleList :"+nowSaleList.size());
            // 销售退货单标注已经开票
            if (nowReturnList != null && nowReturnList.size() > 0) {
                nowReturnList.forEach(p -> {
                    SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.getSaleReturnGoods(p);
                    if (saleReturnGoods != null && saleReturnGoods != null) {
                        saleReturnGoods.setTicket(true);
                        iSaleReturnGoodsService.updateNotNull(saleReturnGoods);
                        System.err.println("return process !");
                    }
                });
            }
            // 销货单标注已经开票
            if (nowSaleList != null && nowSaleList.size() > 0) {
                nowSaleList.forEach(p -> {
                    SaleGoods saleGoods = iSaleGoodsService.getSaleGoods(p);
                    if (saleGoods != null && saleGoods != null) {
                        saleGoods.setFinancial("是");
                        saleGoods.setTicket(true);
                        iSaleGoodsService.updateNotNull(saleGoods);
                        System.err.println("saleGoods process !");
                    }
                });
            }
        }
    }

    /**
     * 保存数据
     */
    @FXML
    @SneakyThrows
    public  void save() {
        setMenuControlState(true);
        lastUpdatePeopel(last_update, last_update_str);
        AccountSaleInvoice order = new AccountSaleInvoice();
        order.setId(order_no.getUserData() == null ? null : Long.valueOf(order_no.getUserData().toString()));
        order.setOrderNo(order_no.getText());
        order.setCreateDate(create_date.getValue() == null ? null : df.parse(create_date.getValue().toString()));
        order.setInvoiceNo(invoice_no.getText());
        order.setMadePeople(made_people.getText());
        order.setInvoiceType(invoice_type.getSelectionModel().getSelectedItem() == null ? null : invoice_type.getSelectionModel().getSelectedItem().toString());
        order.setInvoiceDate(invoice_date.getValue() == null ? null : df.parse(invoice_date.getValue().toString()));
        order.setPaymentMethod(payment_method.getSelectionModel().getSelectedItem() == null ? null : payment_method.getSelectionModel().getSelectedItem().toString());
        order.setTaxpayerNo(taxpayer_no.getText());
        order.setCustomerNo(customer_no.getSelectionModel().getSelectedItem() == null ? null : customer_no.getSelectionModel().getSelectedItem().toString());
        order.setBankNo(bank_no.getText());
        order.setAudit(audit.getText());
        order.setAuditStr(audit_str.getText());
        order.setLastUpdate(last_update.getText());
        order.setLastUpdateStr(last_update_str.getText());
        order.setInvoiceAddress(invoice_address.getText());
        order.setInvoiceTitle(invoice_title.getText());
        order.setCompany(company.getText());
        order.setCurrency(currency.getSelectionModel().getSelectedItem() == null ? null : currency.getSelectionModel().getSelectedItem().toString());
        order.setBank(bank.getText());
        order.setPaymentDate(payment_date.getValue() == null ? null : df.parse(payment_date.getValue().toString()));
        order.setCustomerNoStr(customer_no_str.getText());
        order.setMoney(new BigDecimal(total_money.getText()));
        // 作废按钮 默认正常使用
        order.setOrderCancel(false);
        // 将客户编号和描述翻转
        String temp = order.getCustomerNoStr();
        order.setCustomerNoStr(order.getCustomerNo());
        order.setCustomerNo(temp);

        if (order.getId() != null) {
            // 修改
            int rows = iAccountSaleInvoiceService.updateNotNull(order);
            returnMsg(rows);
            setControllerDisable();
        } else {
            order.setReceiveMoney(new BigDecimal("0.00"));
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iAccountSaleInvoiceService.save(order);
            order_no.setUserData(order.getId());
            shiro_success.setDisable(false);
            returnMsg(rows);
            setControllerDisable();
        }

        // 保存表格数据
        saveTableInvoiceInfo();
    }

    /**
     * 添加
     */
    @FXML
    @SneakyThrows
    public void add() {
        clearValue();
        loadCustomer();
        create_date.setValue(localDate.toLocalDate());
        String no = createOrderNo(iAccountSaleInvoiceService.getMaxOrderNo());
        order_no.setText(no);
        payment_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(DateUtils.getPreviousOrNextDaysOfDate(new Date(), 10), DateUtils.FORMAT_YYYY_MM_DD), formatter));
        createPeople(made_people);
        setControllerUse();
    }


    /**
     * 加载表格数据
     *
     * @param invoiceList
     */
    public void generalProductTab(List<AccountSaleInvoiceInfoProperty> invoiceList) {

        // textfile
        Callback<TableColumn<AccountSaleInvoiceInfoProperty, String>, TableCell<AccountSaleInvoiceInfoProperty, String>> generalTextfileFactory
                = new Callback<TableColumn<AccountSaleInvoiceInfoProperty, String>, TableCell<AccountSaleInvoiceInfoProperty, String>>() {
            @Override
            public TableCell call(final TableColumn<AccountSaleInvoiceInfoProperty, String> param) {
                final TableCell<Customer, String> cell = new TableCell<Customer, String>() {
                    final TextField textField = new TextField();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (item != null) {
                            textField.setText(item);
                        }
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            this.textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                                if (event.getCode() == KeyCode.ESCAPE) {
                                    this.textField.setText(getItem());
                                    cancelEdit();
                                    event.consume();
                                } else if (event.getCode() == KeyCode.TAB || event.getCode() == KeyCode.ENTER) {
                                    textField.setText(textField.getText());
                                    System.err.println("结果：" + textField.getText());
                                }
                            });
                            textField.setMaxWidth(Double.MAX_VALUE);
                            setGraphic(textField);
                            setText(null);
                        }
                        setContentDisplay(ContentDisplay.TEXT_ONLY);
                    }
                };

                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };

        col_source.setCellFactory(column -> EditCell.createStringEditCell());
        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_customer_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_customer_short.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_invoce.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
        col_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_rate.setCellFactory(column -> EditCell.createStringEditCell());
        col_rate_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_tax.setCellFactory(column -> EditCell.createStringEditCell());
        col_rate_not.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());

        ObservableList<AccountSaleInvoiceInfoProperty> data = FXCollections.observableArrayList(invoiceList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_source.setCellValueFactory(new PropertyValueFactory("orderSoruce"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_customer_short.setCellValueFactory(new PropertyValueFactory("customerStr"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_invoce.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_rate.setCellValueFactory(new PropertyValueFactory("rate"));
        col_rate_money.setCellValueFactory(new PropertyValueFactory("rateMoney"));
        col_tax.setCellValueFactory(new PropertyValueFactory("tax"));
        col_rate_not.setCellValueFactory(new PropertyValueFactory("rateNot"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        invoice_table.setEditable(true);
        invoice_table.setItems(data);




        invoice_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountSaleInvoiceInfoProperty>() {
            @Override
            public void changed(ObservableValue<? extends AccountSaleInvoiceInfoProperty> observableValue, AccountSaleInvoiceInfoProperty oldItem, AccountSaleInvoiceInfoProperty newItem) {
                try {
                    if (newItem.getNo() != null && !"".equals(newItem.getNo())) {
                        changeId = Integer.valueOf(newItem.getNo());
                    } else {
                        changeId = 0;
                    }
                } catch (Exception e) {
                    changeId = 0;
                }
            }
        });

        //提交数量计算金额  询价订单--询价产品 金额总计
        col_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue = event.getNewValue().toString();
                int newNum = newValue != null && !"".equals(newValue) ? newNum = Integer.parseInt(newValue) : 0;
                List<AccountSaleInvoiceInfoProperty> data = invoice_table.getItems();
                for (AccountSaleInvoiceInfoProperty property : data) {
                    if (Integer.valueOf(property.getNo()) == changeId) {
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(newNum * Double.valueOf(property.getPrice())).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        BigDecimal rateMoney = calcMoney.multiply(new BigDecimal(property.getRate())).setScale(2, BigDecimal.ROUND_UP);
                        property.setRateMoney(rateMoney.toString());
                    }
                }
            }
        });
        //提交单价计算金额  询价订单--询价产品 金额总计
        col_price.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue = event.getNewValue().toString();
                Double newPrice = newValue != null && !"".equals(newValue) ? newPrice = Double.parseDouble(newValue) : 0.00;
                List<AccountSaleInvoiceInfoProperty> data = invoice_table.getItems();
                for (AccountSaleInvoiceInfoProperty property : data) {
                    if (Integer.valueOf(property.getNo()) == changeId) {
                        property.setPrice(String.valueOf(newPrice));
                        //计算金额
                        BigDecimal calcMoney = new BigDecimal(Integer.parseInt(property.getNum()) * newPrice).setScale(2, BigDecimal.ROUND_UP);
                        property.setMoney(calcMoney.toString());
                        BigDecimal rateMoney = calcMoney.multiply(new BigDecimal(property.getRate())).setScale(2, BigDecimal.ROUND_UP);
                        property.setRateMoney(rateMoney.toString());
                    }
                }
            }
        });


    }

    /**
     * 加载发票明细
     */
    public void showTableInvoiceInfo() {

        Object object = order_no.getUserData();
        if (object != null) {
            List<AccountSaleInvoiceInfo> accountSaleInvoiceInfoList = iAccountSaleInvoiceInfoService.listAccountSaleInvoiceInfo(object.toString());
            List<AccountSaleInvoiceInfoProperty> propertyList = new ArrayList<>();
            if (accountSaleInvoiceInfoList != null) {
                int rows = 1;
                BigDecimal totalMoney = new BigDecimal("0");
                BigDecimal totalLoan = new BigDecimal("0");
                BigDecimal totalTax = new BigDecimal("0");
                for (AccountSaleInvoiceInfo p : accountSaleInvoiceInfoList) {
                    propertyList.add(new AccountSaleInvoiceInfoProperty(p.getId(), p.getOrderSoruce(), p.getOrderNo(), (rows++) + "", p.getCustomerNo(), p.getCustomerStr(), p.getProductNo(), p.getProductName(), p.getInvoceName(), p.getUnit(), p.getNum(), p.getPrice(), p.getMoney(), p.getRate(), p.getRateMoney(), p.getTax(), p.getRateNot(), p.getRemark()));
                    totalLoan = totalLoan.add(new BigDecimal(p.getMoney().toString()));
                    if(p.getRateMoney() != null){
                        totalTax = totalTax.add(p.getRateMoney());
                    }
                }
                totalMoney = totalLoan.add(totalTax);
                total_money.setText(totalMoney.toString());
                total_loan.setText(totalLoan.toString());
                total_tax.setText(totalTax.toString());
            }
            generalProductTab(propertyList);
        }

    }


    /**
     * 删除数据
     */
    @FXML
    public  void delete() {
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            if (order_no.getUserData() != null && !"".equals(order_no.getUserData().toString())) {
                int rows = iAccountSaleInvoiceService.delete(Long.valueOf(order_no.getUserData().toString()));
                iAccountSaleInvoiceInfoService.deleteAccountSaleInvoiceInfoByParentId(order_no.getUserData().toString());
                returnMsg(rows);
                setMenuValue(1);    // 第一页数据
            }
        }

    }

    /**
     * 赋值
     */
    @FXML
    public void setBasicValue(AccountSaleInvoice order) {
        clearValue();
        if (order != null) {
            order_no.setUserData(order.getId());
            order_no.setText(order.getOrderNo());
            if (order.getCreateDate() != null) {
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
            }
            invoice_no.setText(order.getInvoiceNo());
            made_people.setText(order.getMadePeople());
            invoice_type.getSelectionModel().select(order.getInvoiceType() + "");
            if (order.getInvoiceDate() != null) {
                invoice_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getInvoiceDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
            }
            payment_method.getSelectionModel().select(order.getPaymentMethod() + "");
            taxpayer_no.setText(order.getTaxpayerNo());
            customer_no.getSelectionModel().select(order.getCustomerNoStr() + "");
            customer_no_str.setText(order.getCustomerNo());
            bank_no.setText(order.getBankNo());
            audit.setText(order.getAudit());
            audit_str.setText(order.getAuditStr());
            last_update.setText(order.getLastUpdate());
            last_update_str.setText(order.getLastUpdateStr());
            invoice_address.setText(order.getInvoiceAddress());
            invoice_title.setText(order.getInvoiceTitle());
            company.setText(order.getCompany());
            currency.getSelectionModel().select(order.getCurrency());
            bank.setText(order.getBank());
            if (order.getPaymentDate() != null) {
                payment_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getPaymentDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
            }
            btn_cancel_lation.setDisable(false);
            if (order.getOrderCancel() != null) {
                if (order.getOrderCancel()) {
                    btn_cancel_lation.setDisable(true);
                } else {
                    btn_cancel_lation.setDisable(false);
                }
            }

            shiro_success.setDisable(false);
            menu_update.setDisable(false);
            if (order.getOrderAudit() != null) {
                if (order.getOrderAudit()) {
                    setShiroControlYES();
                    menu_update.setDisable(true);
                } else {
                    setShiroControlNO();
                    menu_update.setDisable(false);
                }
            } else {
                setShiroControlNO();
                menu_update.setDisable(false);
            }

            showTableInvoiceInfo();
        }
    }

    /**
     * 给翻页菜单赋值
     *
     * @param page
     */
    @SneakyThrows
    private void setMenuValue(int page) {
        List<AccountSaleInvoice> invoiceList = iAccountSaleInvoiceService.listAccountSaleInvoice("", page, 1);
        if (invoiceList != null && invoiceList.size() > 0) {
            invoiceList.forEach(p -> setBasicValue(p));
            PageInfo<AccountSaleInvoice> pageInfo = new PageInfo<>(invoiceList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
        } else {
            setBasicValue(null);
        }

    }


    /**
     * 分页
     *
     * @param event
     */
    public void pages(MouseEvent event) {
        Node node = (Node) event.getSource();
        if (node.getUserData() != null) {
            int page = Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
            setControllerDisable();
        }
    }

    /**
     * 发票明细 view
     * enter 键盘按下触发
     *
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
    private void deleteRowOfProduct() {
            // 取得当前行的数据
            try {
                if (invoice_table.getSelectionModel().getSelectedCells().size() == 0) {
                    return;
                }
                TablePosition pos = (TablePosition) invoice_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                final ObservableList<AccountSaleInvoiceInfoProperty> dataProperty = invoice_table.getItems();
                final ObservableList<AccountSaleInvoiceInfoProperty> newDataProperty = FXCollections.observableArrayList();
                AccountSaleInvoiceInfoProperty property = (AccountSaleInvoiceInfoProperty) invoice_table.getItems().get(index);
                // 已保存数据确认是否删除
                if (property.getId() != null && property.getId() != 0L && f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                    int rows = iAccountSaleInvoiceInfoService.delete(Long.valueOf(property.getId()));
                    returnMsg(rows);
                }else if (f_alert_confirmDialog(AppConst.ALERT_HEADER, "您确认要删除吗？相同来源单据也将一同删除！")) {
                    for (int i = 0; i < dataProperty.size(); i++) {
                        // 如果单号 和 来源都相同 则一同剔除
                        if(!property.getOrderSoruce().equals(dataProperty.get(i).getOrderSoruce()) && !property.getOrderNo().equals(dataProperty.get(i).getOrderNo())){
                            newDataProperty.add(dataProperty.get(i));
                        }
                    }
                    invoice_table.setItems(newDataProperty);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * 添加 发票明细 行
     */
    public void addProductRow() {

        ObservableList<AccountSaleInvoiceInfoProperty> list = invoice_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountSaleInvoiceInfoProperty("", "", (list.size() + 1) + "", "", "", "", "", "", "", "0", "0.00", "0.00", getTax(new Date(), 2) + "", "", "外加", "", ""));
        generalProductTab(list);
    }


    /**
     * 设置控件可用
     */
    public void setControllerUse() {
        if (order_no.getUserData() != null) {
            String orderid = order_no.getUserData().toString();
            AccountSaleInvoice accountSaleInvoice = iAccountSaleInvoiceService.selectByKey(Long.valueOf(orderid));
            if (accountSaleInvoice != null) {
                if (accountSaleInvoice.getOrderCancel()) {
                    alert_informationDialog("单据已作废，不能再次修改。");
                } else {
                    setController(false);
                }
            }
        } else {
            setController(false);
        }
        setMenuControlState(false);
    }

    /**
     * 设置控件禁用
     */
    public void setControllerDisable() {
        setController(true);
        setMenuControlState(true);
    }

    /**
     * 设置菜单控件状态
     *
     * @param bool
     */
    private void setMenuControlState(Boolean bool) {
        menu_clearAll.setDisable(bool);
        menu_commit.setDisable(bool);
        menu_delete.setDisable(bool);
    }

    /**
     * 设置控件状态
     */
    public void setController(Boolean bool) {
        if (bool) {
            NumberUtil.changeStatus(writestate, 0);
        } else {
            NumberUtil.changeStatus(writestate, 2);
        }
        order_no.setDisable(true);
        create_date.setDisable(bool);
        update_btn.setDisable(bool);
        invoice_no.setDisable(bool);
        made_people.setDisable(true);
        invoice_type.setDisable(bool);
        invoice_date.setDisable(bool);
        adjust.setDisable(bool);
        redmark.setDisable(bool);
        btn_sure.setDisable(bool);
        btn_redmark.setDisable(bool);

        payment_method.setDisable(true);
        taxpayer_no.setDisable(true);
        customer_no.setDisable(bool);
        customer_no_str.setDisable(true);
        bank_no.setDisable(true);
        audit.setDisable(true);
        audit_str.setDisable(true);
        last_update.setDisable(true);
        last_update_str.setDisable(true);
        invoice_address.setDisable(true);
        invoice_title.setDisable(true);
        company.setDisable(true);
        currency.setDisable(true);
        bank.setDisable(true);
        payment_date.setDisable(bool);


        total_money.setDisable(bool);
        total_loan.setDisable(bool);
        total_tax.setDisable(bool);
        create_date_ben.setDisable(bool);
        create_date_end.setDisable(bool);

        invoice_table.setDisable(bool);
    }

    /**
     * 清除控件所有的值
     */
    @FXML
    public void clearValue() {
        order_no.setUserData(null);
        order_no.clear();
        create_date.setValue(null);
        invoice_no.clear();
        made_people.clear();
        invoice_type.getSelectionModel().selectFirst();
        invoice_date.setValue(localDate.toLocalDate());

        payment_method.getSelectionModel().selectFirst();
        taxpayer_no.clear();
        customer_no.getItems().clear();
        bank_no.clear();
        audit.clear();
        audit_str.clear();
        last_update.clear();
        last_update_str.clear();
        invoice_address.clear();
        invoice_title.clear();
        company.clear();
        currency.getSelectionModel().selectFirst();
        bank.clear();
        payment_date.setValue(null);
        customer_no_str.clear();
        redmark.clear();
        adjust.clear();

        total_money.setText("0.00");
        total_loan.setText("0.00");
        total_tax.setText("0.00");
        create_date_ben.setValue(null);
        create_date_end.setValue(null);

        invoice_table.setItems(null);
    }

    /**
     * 查询更多未开票的 销货单
     */
    @FXML
    public void moreOrder() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("saleInvoiceControllerMore", this);

        pane.getChildren().setAll(loader.load("/fxml/account/sale_goods_not_ticket_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * 查询更多已开票的 销项发票
     */
    @FXML
    public void moreInvoiceOrder() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("saleInvoiceControllerInvoiceMore", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_sale_invoice_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
