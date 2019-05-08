package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.account.*;
import com.yc.education.controller.sale.*;
import com.yc.education.controller.stock.ExpressMailController;
import com.yc.education.controller.stock.SaleOutboundOrderController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.customer.*;
import com.yc.education.model.customer.CustomerProperty;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.customer.*;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 现有客户查询
 * @Author: BlueSky
 * @Date: 2018/8/15 15:06
 */
@Controller
public class CurrentClientQueryMiniController extends BaseController implements Initializable {

    @Autowired ICustomerService iCustomerService;
    @Autowired ICustomerDetailInfoService iCustomerDetailInfoService;
    @Autowired ICustomerContactsService iCustomerContactsService;
    @Autowired ICustomerBasicService iCustomerBasicService;
    @Autowired ICustomerShippingAddressService iCustomerShippingAddressService;
    @Autowired DataSettingService iDataSettingService;
    @Autowired ISaleGoodsService iSaleGoodsService;
    @Autowired IInvoiceService iInvoiceService;  // 发票详情

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;    //显示最近
    @FXML CheckBox che_stop_compay; //显示暂停来往公司
    @FXML TextField recently;       //最近多少笔
    @FXML TextField text;           //查找内容

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn customer_id;
    @FXML TableColumn customer_type;
    @FXML TableColumn customer_call;
    @FXML TableColumn customer_general;
    @FXML TableColumn customer_level;
    @FXML TableColumn customer_note;
    @FXML Button client_sure;
    //======================= 客户基本资料 =============================
    // 客户编号
    private static String  code = "";
    // 客户姓名
    private static String  customer_name = "";
    // 注册地址
    private static String  register_address = "";
    // 客户简称
    private static String  customer_initials = "";
    // 客户简称
    private static String  customer_remark = "";
    // 客户id
    private static String  customerid = "";
    //======================= 客户需求商品 =============================

    //======================= 客户需求商品查询 =============================
    private static String  customerNo = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }

    /**
     * @Description 查找
     * @Author BlueSky
     * @Date 16:04 2019/4/15
     **/
    @FXML
    public void btnFind(){
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        CustomerBasicInfoController customer = (CustomerBasicInfoController) StageManager.CONTROLLER.get("CustomerBasicInfoController");
        List<Customer> CustomerList;
        int rows = AppConst.ROWS;
        boolean newly = che_recently.isSelected();
        if(newly && recently.getText() !=null && !"".equals(recently.getText())){
            rows = Integer.valueOf(recently.getText());
        }
        boolean stop = che_stop_compay.isSelected();
        if(customer != null){
            // 查全部
            CustomerList = iCustomerService.listExistCustomer(text.getText(),stop?"":"0",page, rows);
        }else{
            // 查未被禁用
            CustomerList = iCustomerService.listExistCustomer(text.getText(),stop?"":"0",page, rows);
        }
        if(CustomerList != null && CustomerList.size() >0){
            PageInfo<Customer> pageInfo = new PageInfo<>(CustomerList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(CustomerList);
        }else{
            loadData(null);
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

    private void loadData(List<Customer> list){
        // 基本资料
        if(list==null){
            tableView.setItems(null);
            return;
        }
        List<CustomerProperty> customerPropertyList = new ArrayList<>();
        List<DataSetting> dataSettingList = iDataSettingService.findDataSetting(10L);
        // 客户等级
        List<DataSetting> levelList = iDataSettingService.findDataSetting(14L);
        try {
            list.forEach(p->{
                if( p.getId() != null && p.getId() != 0L){
                    try {
                        if(p.getCustomerLevel() != null && !"".equals(p.getCustomerLevel())){
                            p.setCustomerLevel(levelList.get(Integer.valueOf(p.getCustomerLevel())).getTitle());
                        }
                        if(p.getCustomerType() != null && !"".equals(p.getCustomerType())){
                            if(dataSettingList != null){
                                p.setCustomerType(dataSettingList.get(Integer.valueOf(p.getCustomerType())).getTitle());
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    CustomerProperty cust = new CustomerProperty(p.getId(), p.getCustomerType(), p.getCustomerLevel(), p.getCustomerCode(), p.getShortName(), p.getName(),p.getAddtime(),p.getGeneralCustomer(),p.getRemark());
                    customerPropertyList.add(cust);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        // checkbox
        Callback<TableColumn<CustomerProperty, Boolean>, TableCell<CustomerProperty, Boolean>> generalCheckboxFactory
                = new Callback<TableColumn<CustomerProperty, Boolean>, TableCell<CustomerProperty, Boolean>>() {
            @Override
            public TableCell call(final TableColumn<CustomerProperty, Boolean> param) {
                final TableCell<Customer, Boolean> cell = new TableCell<Customer, Boolean>() {

                    final CheckBox checkBox = new CheckBox("一般客户");

                    @Override
                    public void updateItem(Boolean ite, boolean empty) {
                        if(ite != null){
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
                                CustomerProperty selectedRecord = (CustomerProperty) tableView.getItems().get(selectdIndex);
                                if(selectedRecord!=null){
                                    selectedRecord.setGeneralCustomer(!selectedRecord.isGeneralCustomer());
                                    iCustomerService.updateNotNull(new Customer(selectedRecord.getId(),selectedRecord.getCustomerType(), selectedRecord.getCustomerLevel(), selectedRecord.getRemark(), selectedRecord.getCustomerCode(), selectedRecord.getShortName(),  selectedRecord.isGeneralCustomer()));
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

        // 查询客户集合
        final ObservableList<CustomerProperty> data = FXCollections.observableArrayList(customerPropertyList);
        customer_general.setCellFactory(generalCheckboxFactory);

        id.setCellValueFactory(new PropertyValueFactory("id"));
        customer_id.setCellValueFactory(new PropertyValueFactory("customerCode"));
        customer_type.setCellValueFactory(new PropertyValueFactory("customerType"));//映射
        customer_call.setCellValueFactory(new PropertyValueFactory("shortName"));
        customer_general.setCellValueFactory(new PropertyValueFactory("generalCustomer"));
        customer_level.setCellValueFactory(new PropertyValueFactory("customerLevel"));
        customer_note.setCellValueFactory(new PropertyValueFactory("remark"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerProperty>() {
            @Override
            public void changed(ObservableValue<? extends CustomerProperty> observableValue, CustomerProperty oldItem, CustomerProperty newItem) {
                if(newItem.getCustomerCode() != null && !("".equals(newItem.getCustomerCode()))){
                    CurrentClientQueryMiniController.customerid = newItem.getId()+"";
                    CurrentClientQueryMiniController.customer_name = newItem.getShortName();
                    CurrentClientQueryMiniController.code = newItem.getCustomerCode();
                    CurrentClientQueryMiniController.register_address = "";
                    CurrentClientQueryMiniController.customer_initials = newItem.getName();

                    // 客户需求商品查询
                    CurrentClientQueryMiniController.customerNo = newItem.getCustomerCode();
                }
                if (newItem.getRemark() != null && !"".equals(newItem.getRemark())){
                    CurrentClientQueryMiniController.customer_remark = newItem.getRemark();
                }
            }
        });

        tableView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }


    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("CustomerBasicInfoController");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsController");
        StageManager.CONTROLLER.remove("CustomerDataMaintenanceController");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsQueryController");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsQueryControllerCustomerNo");
        StageManager.CONTROLLER.remove("OnlineOrderController");
        StageManager.CONTROLLER.remove("PurchaseOrderController");
        StageManager.CONTROLLER.remove("SaleGoodsController");
        StageManager.CONTROLLER.remove("SaleReturnController");
        StageManager.CONTROLLER.remove("SaleReturnStatusController");
        StageManager.CONTROLLER.remove("SaleDiscountQueryWhereController");
        StageManager.CONTROLLER.remove("SaleDiscountQueryWhereControllerEnd");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsWhereControllerEnd");
        StageManager.CONTROLLER.remove("SaleOutboundOrderController");
        StageManager.CONTROLLER.remove("SaleDeliveryTrackControllerBen");
        StageManager.CONTROLLER.remove("SaleDeliveryTrackControllerEnd");
        StageManager.CONTROLLER.remove("SaleOutboundOrderController");
        StageManager.CONTROLLER.remove("OrderAccountsReceivableController");
        StageManager.CONTROLLER.remove("AccountReceiptControllerCustomer");
        StageManager.CONTROLLER.remove("AccountReceiptControllerCustomerBen");
        StageManager.CONTROLLER.remove("AccountReceiptControllerCustomerEnd");
        StageManager.CONTROLLER.remove("AccountPrepaymentController");
        StageManager.CONTROLLER.remove("AccountSaleCostControllerCustomer");
        StageManager.CONTROLLER.remove("AccountSaleCostControllerCustomerEnd");
        StageManager.CONTROLLER.remove("ExpressMailController");

        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){

        // 基本资料
        CustomerBasicInfoController customer = (CustomerBasicInfoController) StageManager.CONTROLLER.get("CustomerBasicInfoController");
        if(customer != null){
            customer.customer_id.setText(customerid);
            customer.code.setText(code);
            customer.customer_initials.setText(customer_name);
            customer.customer_name.setText(customer_name);
            customer.register_address.setText(register_address);
            Customer cu = iCustomerService.selectByKey(Long.valueOf(customerid));
            if(cu != null){
                customer.customer_initials.setText(cu.getShortName());
                customer.customer_name.setText(cu.getName());
                customer.register_address.setText(cu.getRegisterAddress());
            }

        }
        // 客户需求商品
        CustomerDemandGoodsController demand = (CustomerDemandGoodsController) StageManager.CONTROLLER.get("CustomerDemandGoodsController");
        if(demand != null){
            demand.clearCustomerDemandGoodsVal();
            if(!"".equals(code)){
                demand.customer_no.setText(code);
            }
            if(!"".equals(customerid)){
                demand.customer_id.setText(customerid);
                CustomerBasic basic = iCustomerBasicService.getCustomerBasicByCustomerId(Long.valueOf(customerid));
                if(basic!=null){
                    demand.address.setText(basic.getShippingAddress());
                }
            }
            if(!"".equals(customer_name)){
                demand.customer_name.setText(customer_name);
                createPeople(demand.create_people);
            }
            demand.build_number.setText(new Date().getTime()+getRandomone());
            if(!"".equals(customer_remark)){
                demand.remark.setText(customer_remark);
            }


        }
        // 客户资料维护
        CustomerDataMaintenanceController maintain = (CustomerDataMaintenanceController) StageManager.CONTROLLER.get("CustomerDataMaintenanceController");
        if(maintain != null && customerid != null && !"".equals(customerid)){
            maintain.customer_id.setText(customerid);
            Customer cu = iCustomerService.selectByKey(Long.valueOf(customerid));
            maintain.customer_name.setText(cu.getName());
            maintain.customer_no.setText(cu.getCustomerCode());
            maintain.customer_no_str.setText(cu.getRegisterAddress());
            maintain.create_no.setText(new Date().getTime()+"");
            maintain.create_date.setValue(LocalDateTime.now().toLocalDate());
            maintain.getMaintainMainInfoById();
        }
        // 客户需求商品查询
        CustomerDemandGoodsQueryController demandQuery = (CustomerDemandGoodsQueryController) StageManager.CONTROLLER.get("CustomerDemandGoodsQueryController");
        String demandQueryCustomer = (String) StageManager.CONTROLLER.get("CustomerDemandGoodsQueryControllerCustomerNo");
        if(demandQuery != null){
            if(demandQueryCustomer != null){
                if("ben".equals(String.valueOf(demandQueryCustomer))){
                    demandQuery.customer_no.setText(customerNo);
                    demandQuery.customer_no_end.setText(customerNo);
                }else if("end".equals(String.valueOf(demandQueryCustomer))){
                    demandQuery.customer_no_end.setText(customerNo);
                }
            }
        }
        // 销售--报价单
        QuotationController quotationController = (QuotationController) StageManager.CONTROLLER.get("QuotationController");
        if(quotationController != null){
            if(!"".equals(code)){
                quotationController.customer_no.setText(code);
            }
            if(!"".equals(customer_name)){
                quotationController.customer_no_str.setText(customer_name);
                quotationController.customer_name.setText(customer_name);
            }
            Customer cust = iCustomerService.getCustomer(code);
            if(cust != null) {
                // 加载 联系人、手机号、传真、地址
                loadCustomerBasicContactWay(cust.getId(), quotationController.telephone, quotationController.fax, quotationController.contact, quotationController.address,new TextField(),new TextField());
                quotationController.amount_receivable.setText(cust.getUseableMoney()==null?"0":cust.getUseableMoney().toString());
            }
        }
        // 网上订单
        OnlineOrderController onlineOrderController = (OnlineOrderController)StageManager.CONTROLLER.get("OnlineOrderController");
        if(onlineOrderController != null){
            onlineOrderController.customer_no.setText(code);
            onlineOrderController.customer_no_str.setText(customer_name);
            onlineOrderController.order_people.setText(customer_name);
            Customer cust = iCustomerService.getCustomer(code);
            if(cust != null){
                CustomerBasic basic = iCustomerBasicService.getCustomerBasicByCustomerId(cust.getId());
                if(basic != null){
                    onlineOrderController.zip.setText(basic.getZipCode());
                }
                // 加载 联系人、手机号、传真、地址
                loadCustomerBasicContactWay(cust.getId(),onlineOrderController.phone,onlineOrderController.fax,onlineOrderController.contact,onlineOrderController.delivery_address,onlineOrderController.invoice_title,onlineOrderController.invoice_address);
            }
        }
        // 订货单
        PurchaseOrderController purchaseOrderController = (PurchaseOrderController)StageManager.CONTROLLER.get("PurchaseOrderController");
        if(purchaseOrderController != null){
            purchaseOrderController.customer_no.setText(code);
            purchaseOrderController.customer_no_str.setText(customer_name);
            Customer cust = iCustomerService.getCustomer(code);
            if(cust != null){
                CustomerBasic basic = iCustomerBasicService.getCustomerBasicByCustomerId(cust.getId());
                if(basic != null){
                    purchaseOrderController.payment.getSelectionModel().select(basic.getPaymentMethod());
                    purchaseOrderController.zip.setText(basic.getZipCode());
                    purchaseOrderController.customer_name.setText(customer_name);
                }
                // 加载 联系人、手机号、传真、地址
                loadCustomerBasicContactWay(cust.getId(),purchaseOrderController.phone,purchaseOrderController.fax,purchaseOrderController.contact,purchaseOrderController.shipping_address,purchaseOrderController.invoice_title,purchaseOrderController.invoice_address);
                purchaseOrderController.receivable_balance.setText(cust.getUseableMoney()==null?"0":cust.getUseableMoney().toString());
            }
        }
        // 销货单
        SaleGoodsController saleGoodsController = (SaleGoodsController)StageManager.CONTROLLER.get("SaleGoodsController");
        if(saleGoodsController != null){
            saleGoodsController.customer_no.setText(code);
            saleGoodsController.customer_no_str.setText(customer_name);
            Customer cust = iCustomerService.getCustomer(code);
            if(cust != null){
                CustomerBasic basic = iCustomerBasicService.getCustomerBasicByCustomerId(cust.getId());
                if(basic != null){
                    saleGoodsController.payment.getSelectionModel().select(basic.getPaymentMethod());
                    saleGoodsController.zip.setText(basic.getZipCode());
                    saleGoodsController.customer_name.setText(customer_name);
                }
                // 加载 联系人、手机号、传真、地址
                loadCustomerBasicContactWay(cust.getId(),saleGoodsController.phone,saleGoodsController.fax,saleGoodsController.contact,saleGoodsController.shipping_address,saleGoodsController.invoice_title,saleGoodsController.invoice_address);
                saleGoodsController.receivable.setText(cust.getUseableMoney()==null?"0":cust.getUseableMoney().toString());
            }
        }
        // 销售退货单
        SaleReturnController saleReturnController = (SaleReturnController)StageManager.CONTROLLER.get("SaleReturnController");
        if(saleReturnController != null){
            saleReturnController.customer_no.setText(code);
            saleReturnController.customer_no_str.setText(customer_name);
        }
        // 销货单状态更新
        SaleReturnStatusController saleReturnStatusController = (SaleReturnStatusController)StageManager.CONTROLLER.get("SaleReturnStatusController");
        if(saleReturnStatusController != null){
            saleReturnStatusController.customer_no.setText(code);
            saleReturnStatusController.customer_short.setText(customer_name);
        }
        // 销售产品折扣查询 -客户编号开始
        SaleDiscountQueryWhereController saleDiscountQueryWhereController = (SaleDiscountQueryWhereController)StageManager.CONTROLLER.get("SaleDiscountQueryWhereController");
        if(saleDiscountQueryWhereController != null){
            saleDiscountQueryWhereController.customer_no.setText(code);
        }
        // 销售产品折扣查询 -客户编号结束
        SaleDiscountQueryWhereController saleDiscountQueryWhereControllerEnd = (SaleDiscountQueryWhereController)StageManager.CONTROLLER.get("SaleDiscountQueryWhereControllerEnd");
        if(saleDiscountQueryWhereControllerEnd != null){
            saleDiscountQueryWhereControllerEnd.customer_no_end.setText(code);
        }
        // 销货发货跟踪 -客户编号开始
        SaleDeliveryTrackController saleDeliveryTrackControllerBen = (SaleDeliveryTrackController)StageManager.CONTROLLER.get("SaleDeliveryTrackControllerBen");
        if(saleDeliveryTrackControllerBen != null){
            saleDeliveryTrackControllerBen.customer_no.setText(code);
        }
        // 销货发货跟踪 -客户编号结束
        SaleDeliveryTrackController saleDeliveryTrackControllerEnd = (SaleDeliveryTrackController)StageManager.CONTROLLER.get("SaleDeliveryTrackControllerEnd");
        if(saleDeliveryTrackControllerEnd != null){
            saleDeliveryTrackControllerEnd.customer_no_end.setText(code);
        }

        // 库存 - 销货出库单
        SaleOutboundOrderController SaleOutboundOrderController = (SaleOutboundOrderController)StageManager.CONTROLLER.get("SaleOutboundOrderController");
        if(SaleOutboundOrderController != null){
            SaleOutboundOrderController.customer_no.setText(code);
            SaleOutboundOrderController.customer_no_str.setText(customer_name);
        }

        // 账款 --应收账款冲款
        AccountAccountsReceivableController accountAccountsReceivableController = (AccountAccountsReceivableController)StageManager.CONTROLLER.get("OrderAccountsReceivableController");
        if(accountAccountsReceivableController != null){
            accountAccountsReceivableController.customer_no.setText(code);
            accountAccountsReceivableController.customer_str.setText(customer_name);
        }

        // 账款 --收款单
        AccountReceiptController accountReceiptController = (AccountReceiptController)StageManager.CONTROLLER.get("AccountReceiptControllerCustomer");
        if(accountReceiptController != null){
            accountReceiptController.customer_no.setText(code);
            accountReceiptController.customer_no_str.setText(customer_name);
        }

        // 账款 --收款单 ben
        AccountReceiptController accountReceiptControllerCustomerBen = (AccountReceiptController)StageManager.CONTROLLER.get("AccountReceiptControllerCustomerBen");
        if(accountReceiptControllerCustomerBen != null){
            accountReceiptControllerCustomerBen.customer_no_ben.setText(code);
        }

        // 账款 --收款单 end
        AccountReceiptController accountReceiptControllerCustomerEnd = (AccountReceiptController)StageManager.CONTROLLER.get("AccountReceiptControllerCustomerEnd");
        if(accountReceiptControllerCustomerEnd != null){
            accountReceiptControllerCustomerEnd.customer_no_end.setText(code);
        }

        // 账款 -- 预付账款
        AccountPrepaymentController accountPrepaymentController = (AccountPrepaymentController)StageManager.CONTROLLER.get("AccountPrepaymentController");
        if(accountPrepaymentController != null){
            accountPrepaymentController.customer_no.setText(code);
            accountPrepaymentController.customer_no_str.setText(customer_name);
        }
        // 账款 - 销项成本明细
        QueryAccountSaleCostController queryAccountSaleCostControllerCustomer = (QueryAccountSaleCostController)StageManager.CONTROLLER.get("AccountSaleCostControllerCustomer");
        if(queryAccountSaleCostControllerCustomer != null){
            queryAccountSaleCostControllerCustomer.customer_no.setText(code);
        }
        // 账款 - 销项成本明细结束
        QueryAccountSaleCostController queryAccountSaleCostControllerCustomerEnd = (QueryAccountSaleCostController)StageManager.CONTROLLER.get("AccountSaleCostControllerCustomerEnd");
        if(queryAccountSaleCostControllerCustomerEnd != null){
            queryAccountSaleCostControllerCustomerEnd.customer_no_end.setText(code);
        }

        ExpressMailController expressMailController = (ExpressMailController)StageManager.CONTROLLER.get("ExpressMailController");
        if(expressMailController != null){
            expressMailController.collectid.setText(code);
            expressMailController.collectdes.setText(customer_name);
            expressMailController.collectcompany.setText(customer_initials);
             Customer customer1 = iCustomerService.getCustomer(code);

//            @FXML public TextField collectid;//收件公司编号
//            @FXML public TextField collectdes;//收件公司名称
//            @FXML public TextField collectcompany;//收件公司
//            @FXML public ComboBox collectprovince;//省
//            @FXML public ComboBox collectcity;//市
//            @FXML public ComboBox collectarea;//区
//            @FXML public TextField collectaddress;//详细地址
//            @FXML public ComboBox collectpeople;//收件人
//            @FXML public ComboBox collectphone;//联络方式

            loadCustomerBasicContactWay(customer1.getId(),expressMailController.collectphone,new ComboBox(),expressMailController.collectpeople,expressMailController.collectaddr,new TextField(),new TextField());
        }

        //关闭窗口
        closeWin();
    }


    /**
     * 加载客户信息中的 手机、传真、姓名、地址信息
     * @param customerId 客户id
     * @param phone 联系电话 ComboBox
     * @param fax 传真 ComboBox
     * @param contact 联系人 ComboBox
     * @param address 地址 ComboBox
     */
    private void loadCustomerBasicContactWay(Long customerId,ComboBox phone,ComboBox fax,ComboBox contact,ComboBox address,TextField invoiceTitle,TextField invoiceAddress){
        List<CustomerContacts> list = iCustomerContactsService.listCustomerContactsByCustomerId(customerId);
        // 联系人
        if(list != null && list.size() > 0 ){
            phone.getItems().clear();
            fax.getItems().clear();
            contact.getItems().clear();
            list.forEach(p->{
                phone.getItems().add(p.getMobilePhone());
                fax.getItems().add(p.getFax());
                contact.getItems().add(p.getName());
            });
            phone.getSelectionModel().select(0);
            fax.getSelectionModel().select(0);
            contact.getSelectionModel().select(0);
        }
        address.getItems().clear();
        List<CustomerShippingAddress> addressList = iCustomerShippingAddressService.listAll(customerId);
        if(addressList != null && addressList.size() > 0){
            addressList.forEach(p->address.getItems().add(p.getAddress()));
            address.getSelectionModel().select(0);
        }
        // 发票
        List<Invoice> invoiceList = iInvoiceService.listInvoiceByCustomerId(customerId);
        if(invoiceList != null && invoiceList.size() >0){
            invoiceTitle.setText(invoiceList.get(0).getTitle());
            invoiceAddress.setText(invoiceList.get(0).getAddress());
        }
    }

}
