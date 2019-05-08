package com.yc.education.controller.account;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.ProductStock;
import com.yc.education.model.account.AccountCoastAccounting;
import com.yc.education.model.account.AccountCoastPurchase;
import com.yc.education.model.account.AccountCoastPurchaseProperty;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.sale.SaleGoodsProductProperty;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.account.IAccountCoastAccountingService;
import com.yc.education.service.account.IAccountCoastPurchaseService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.val;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Description 成本核算
 * @Author BlueSky
 * @Date 2018-12-04 17:06
 */
@Controller
public class AccountCostAccountingController extends BaseController implements Initializable {

    @Autowired IAccountCoastAccountingService iAccountCoastAccountingService; // 成本核算
    @Autowired IAccountCoastPurchaseService iAccountCoastPurchaseService; // 成本核算明细
    @Autowired PurchaseStockService iPurchaseStockService;   // 采购库存
    @Autowired PurchaseStockProductService iPurchaseStockProductService;   // 采购库存明细
    @Autowired ProductBasicService iProductBasicService;        //产品基本资料
    @Autowired ProductStockService iProductStockService;        //产品库存

    @FXML VBox menu_clearAll;
    @FXML VBox menu_commit;
    @FXML VBox menu_update;
    @FXML VBox import_in;
    @FXML Label writestate;          // 待输入

    @FXML TextField storage_in_no;          //入库单号
    @FXML TextField install_no;          //装箱单号
    @FXML TextField audit;               //审核人
    @FXML TextField audit_date;         //审核日期
    @FXML TextField storage_in_remark;  //入库备注
    @FXML TextField verify_people;      //核算人
    @FXML TextField verify_people_str;  //核算人描述

    @FXML TextField invoice_no; // 发票号码
    @FXML ComboBox currency; // 币别
    @FXML TextField customs_fee; // 报关费
    @FXML TextField proxy_fee; // 代理费
    @FXML TextField carriage_fee; // 运费
    @FXML TextField poundage_fee; // 手续费
    @FXML TextField other_fee; // 其它费用
    @FXML TextField loan_total; // 贷款合计
    @FXML TextField tax_total; // 税款合计
    @FXML TextField price_tax_total; // 价税合计
    @FXML TextField total; // 总计
    @FXML TextField exchange_rate; // 当天汇率
    @FXML TextField exchange_currency; // 当天汇率描述
    @FXML Button btn_cost; //成本核算按钮

    @FXML TableView tableview_cost;
    @FXML TableColumn col_ids;
    @FXML TableColumn col_no;
    @FXML TableColumn col_product_no;
    @FXML TableColumn col_product_name;
    @FXML TableColumn col_warehouse_position;
    @FXML TableColumn col_num;
    @FXML TableColumn col_price;
    @FXML TableColumn col_dollar;
    @FXML TableColumn col_total_dollar;
    @FXML TableColumn col_rmb_money; //人民币金额
    @FXML TableColumn col_order_no;

    // 日期格式
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
    Stage stage = new Stage();
    static long changeId = 0L;      // 记录当前编辑计算金额cell 的id值

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 设置控件禁用
        setControllerDisable();
        // 加载币别
        setComboBox(7L, currency, 0);
//        firstData();
        generalProductTab(null);
        addlistenerOther();  //报关费、代理费 等监听
        addlistenerTotal(); //贷款合计、税款合计 监听
    }

    /**
     * @Author BlueSky
     * @Description //TODO 成本核算按钮
     * @Date 17:52 2019/3/29
     * @Param []
     **/
    @FXML
    public void btnCostCalc(){
        if(tableview_cost.getItems() != null){
            List<AccountCoastPurchaseProperty> propertyList = tableview_cost.getItems();

            // 1.单项产品货款    = 数量 * 入库单价  并得出货款总价
            // 2.计算货物总价    = 美金总价 * 汇率 + 关税 + 其他费用（运费.报关费等）
            // 3.计算占货款比例  = 单项产品货款 ÷ 美金总金额
            // 4.某商品成本      = 占货比例 * 货物总价
            // 5.最后单位成本    = 某商品成本 ÷ 某商品数量

            BigDecimal productTotal = new BigDecimal("0.00"); // 货款总价
            BigDecimal goodsTotal = new BigDecimal("0.00");   // 货物总价
            Boolean warring = false;    // 表格数据是否有问题
            for (AccountCoastPurchaseProperty p : propertyList) {
                // 1.单项产品货款    = 数量 * 入库单价  并得出货款总价
                try {
                    p.setTempProductPrice(Double.valueOf(p.getRmbMoney()));
                    productTotal = productTotal.add(new BigDecimal(p.getTempProductPrice().toString()));  // 货款总价
                }catch (Exception e){warring = true;}
                // 2.计算货物总价    += 美金总价
                //
//                try {
//                    if(loan_total.getText() != null && !"".equals(p.getRmbMoney()) && !"0.00".equals(loan_total.getText())){
//                        goodsTotal = goodsTotal.add(new BigDecimal(loan_total.getText()));
//                    }
//                }catch (Exception e){warring = true;}
            }
            // 2.计算货物总价 加上关税 + 其他费用（运费.报关费等）
            if(total.getText() != null && !"".equals(total.getText())){
                goodsTotal = goodsTotal.add(new BigDecimal(total.getText()));
            }

            // 3.计算占货款比例  = 单项产品货款 ÷ 美金总金额
            // 4.某商品成本      = 占货比例 * 货物总价
            // 5.最后单位成本    = 某商品成本 ÷ 某商品数量
            for (AccountCoastPurchaseProperty p : propertyList) {
                try {
                    //计算占货款比例
                    p.setTempGoodsRate(new BigDecimal(p.getTempProductPrice().toString()).divide(productTotal,5,BigDecimal.ROUND_UP).doubleValue());
                    //某商品成本
                    p.setTempGoodsCost(new BigDecimal(p.getTempGoodsRate().toString()).multiply(goodsTotal).setScale(2,BigDecimal.ROUND_UP).doubleValue());
                    //最后单位成本
                    p.setTempUnitCost(new BigDecimal(p.getTempGoodsCost().toString()).divide(new BigDecimal(p.getWarehouseNum()),2,BigDecimal.ROUND_UP).doubleValue());
                    p.setPrice(p.getTempUnitCost().toString());
                }catch (Exception e){warring = true;}
            }
            if(warring){
                alert_informationDialog("数据格式错误！");
                return;
            }
        }
    }

    /**
     * @Author BlueSky
     * @Description //TODO 计算 报关费、代理费 等 合计费用
     * @Date 17:00 2019/3/29
     * @Param []
     **/
    private void calcOtherFee(){
        String customsFee = customs_fee.getText();
        String proxyFee = proxy_fee.getText();
        String carriageFee = carriage_fee.getText();
        String poundageFee = poundage_fee.getText();
        String otherFee = other_fee.getText();
        String taxTotal = tax_total.getText();
        String loanTotal = loan_total.getText();
        Double calc = 0.00D;
        if(customsFee != null && !"".equals(customsFee)){
            calc += Double.valueOf(customsFee);
        }
        if(proxyFee != null && !"".equals(proxyFee)){
            calc += Double.valueOf(proxyFee);
        }
        if(carriageFee != null && !"".equals(carriageFee)){
            calc += Double.valueOf(carriageFee);
        }
        if(poundageFee != null && !"".equals(poundageFee)){
            calc += Double.valueOf(poundageFee);
        }
        if(otherFee != null && !"".equals(otherFee)){
            calc += Double.valueOf(otherFee);
        }
        if(taxTotal != null && !"".equals(taxTotal)){
            calc += Double.valueOf(taxTotal);
        }
        if(loanTotal != null && !"".equals(loanTotal)){
            calc += Double.valueOf(loanTotal);
        }
        total.setText(calc.toString());
    }

    /**
     * @Author BlueSky
     * @Description //TODO 计算 贷款合计、税款合计 合计费用
     * @Date 17:00 2019/3/29
     * @Param []
     **/
    private void calcTotalFee(){
        String loanTotal = loan_total.getText();
        String taxTotal = tax_total.getText();
        Double calc = 0.00D;
        if(loanTotal != null && !"".equals(loanTotal)){
            calc += Double.valueOf(loanTotal);
        }
        if(taxTotal != null && !"".equals(taxTotal)){
            calc += Double.valueOf(taxTotal);
        }
        price_tax_total.setText(calc.toString());
    }

    /**
     * @Author BlueSky
     * @Description //TODO 报关费、代理费 等监听
     * @Date 17:00 2019/3/29
     * @Param []
     **/
    private void addlistenerOther(){
        customs_fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
        proxy_fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
        carriage_fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
        poundage_fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
        other_fee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
        tax_total.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
        loan_total.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcOtherFee();
            }
        });
    }

    /**
     * @Author BlueSky
     * @Description //TODO 贷款合计、税款合计 监听
     * @Date 17:05 2019/3/29
     * @Param []
     **/
    private void addlistenerTotal(){
        loan_total.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcTotalFee();
            }
        });
        tax_total.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                calcTotalFee();
            }
        });
    }

    /**
     * 采购成本 view
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
            showTableviewCost();
        }
    }

    /**
     * 删除 采购成本
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tableview_cost.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tableview_cost.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                AccountCoastPurchaseProperty property = (AccountCoastPurchaseProperty)tableview_cost.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iAccountCoastPurchaseService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<AccountCoastPurchaseProperty> dataProperty = tableview_cost.getItems();
                final ObservableList<AccountCoastPurchaseProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                tableview_cost.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加 采购成本 行
     */
    public void addProductRow() {

        ObservableList<AccountCoastPurchaseProperty> list = tableview_cost.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new AccountCoastPurchaseProperty( list.size()+1,"", "", "", "0", "0.00", "0.00", "","0.00","0.00"));
        tableview_cost.setItems(list);
    }


    /**
     * 加载表格数据
     */
    private void showTableviewCost(){
        String orderid = storage_in_no.getUserData() == null?null:storage_in_no.getUserData().toString();
        if(orderid != null && !"".equals(orderid)){
            List<AccountCoastPurchase> purchaseList = iAccountCoastPurchaseService.listAccountCoastPurchase(orderid);
            List<AccountCoastPurchaseProperty> propertyList = new ArrayList<>();
            if(purchaseList != null && purchaseList.size() > 0){
                int rows = 1;
                for (AccountCoastPurchase p : purchaseList) {
                    propertyList.add(new AccountCoastPurchaseProperty(p.getId(),rows++, p.getProductNo(), p.getProductName(), p.getWarehousePosition(), p.getWarehouseNum(), p.getPrice(), p.getDollar(), p.getOrderNo(),p.getRmbMoney(),p.getUsdMoney()));
                }
                generalProductTab(propertyList);
            }
        }
    }

    /**
     * 加载表格数据
     * @param receiptList
     */
    @SneakyThrows(Exception.class)
    public void generalProductTab(List<AccountCoastPurchaseProperty> receiptList){

//        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
//        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
//        col_warehouse_position.setCellFactory(column -> EditCell.createStringEditCell());
//        col_num.setCellFactory(column -> EditCell.createStringEditCell());
//        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_dollar.setCellFactory(column -> EditCell.createStringEditCell());
        col_rmb_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_total_dollar.setCellFactory(column -> EditCell.createStringEditCell());
//        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());

        ObservableList<AccountCoastPurchaseProperty> data = receiptList==null?FXCollections.observableArrayList():FXCollections.observableArrayList(receiptList);
        col_ids.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_warehouse_position.setCellValueFactory(new PropertyValueFactory("warehousePosition"));
        col_num.setCellValueFactory(new PropertyValueFactory("warehouseNum"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_dollar.setCellValueFactory(new PropertyValueFactory("dollar"));
        col_total_dollar.setCellValueFactory(new PropertyValueFactory("totalDollar"));
        col_rmb_money.setCellValueFactory(new PropertyValueFactory("rmbMoney"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));

        tableview_cost.setEditable(true);
        tableview_cost.setItems(data);

        tableview_cost.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountCoastPurchaseProperty>() {
            @Override
            public void changed(ObservableValue<? extends AccountCoastPurchaseProperty> observableValue, AccountCoastPurchaseProperty oldItem, AccountCoastPurchaseProperty newItem) {
                try {
                    if(newItem.getNo() != 0 && !"".equals(newItem.getNo())){
                        changeId = Integer.valueOf(newItem.getNo());
                    }else{
                        changeId = 0;
                    }
                }catch (Exception e){
                    changeId = 0;
                }
            }
        });

        //提交单价计算金额  询价订单--询价产品 单价
        col_rmb_money.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {

                List<AccountCoastPurchaseProperty> propertyList = tableview_cost.getItems();
                if(propertyList != null){
                    double money = 0.00;
                    for (AccountCoastPurchaseProperty p : propertyList) {
                        if(p.getNo() == changeId){
                            p.setRmbMoney(event.getNewValue().toString());
                        }
                        if(p.getRmbMoney() != null && !"".equals(p.getRmbMoney())){
                            money += Double.valueOf(p.getRmbMoney());
                        }
                    }
                    loan_total.setText(money+"");
                    changeId = 0;
                }
            }
        });



    }


    /**
     * 保存收款明细数据
     */
    @SneakyThrows
    private void saveTableReceiptInfo(String[] arr){
        String orderid = storage_in_no.getUserData() == null?null:storage_in_no.getUserData().toString();
        if(orderid != null && !"".equals(orderid) && tableview_cost.getItems() != null){
            for (int i = 0; i < tableview_cost.getItems().size(); i++) {
                AccountCoastPurchaseProperty property = null;
                if(tableview_cost.getItems().get(i) != null){
                    property = (AccountCoastPurchaseProperty)tableview_cost.getItems().get(i);
                }
                AccountCoastPurchase product = new AccountCoastPurchase();
                if(property.getProductNo() != null && !"".equals(property.getProductNo())){
                    product.setProductNo(property.getProductNo());
                }
                if(property.getProductName() != null && !"".equals(property.getProductName())){
                    product.setProductName(property.getProductName());
                }
                if(property.getWarehousePosition() != null && !"".equals(property.getWarehousePosition())){
                    product.setWarehousePosition(property.getWarehousePosition());
                }
                if(property.getWarehouseNum() != null && !"".equals(property.getWarehouseNum())){
                    product.setWarehouseNum(property.getWarehouseNum()==null?null:Integer.valueOf(property.getWarehouseNum()));
                }
                if(property.getPrice() != null && !"".equals(property.getPrice())){
                    product.setPrice(property.getPrice() == null ? new BigDecimal("0.00"): new BigDecimal(property.getPrice()));
                }
                if(property.getDollar() != null && !"".equals(property.getDollar())){
                    product.setDollar(property.getDollar() == null ? new BigDecimal("0.00"): new BigDecimal(property.getDollar()));
                }
                if(property.getOrderNo() != null && !"".equals(property.getOrderNo())){
                    product.setOrderNo(property.getOrderNo());
                }
                if(property.getRmbMoney() != null && !"".equals(property.getRmbMoney())){
                    product.setRmbMoney(property.getRmbMoney() == null ? new BigDecimal("0.00"): new BigDecimal(property.getRmbMoney()));
                }
                product.setOtherid(Long.valueOf(orderid));
                // 加权平均值
                BigDecimal nowPrice = new BigDecimal("0.00");
                ProductStock productStock = iProductStockService.findProductStockByDepotAndIsnum(property.getWarehousePosition(),property.getProductNo());
                if(productStock != null){
                    // 查询未成本核算单据 （ps：未核算的单据库存数量已经加入进去了，所以现在加权平均取库存数量时得先把未成本核算单据数量减出）
                    List<PurchaseStock> stockList = iPurchaseStockService.listPurchaseStock("",1, Integer.MAX_VALUE);
                    for (PurchaseStock p : stockList) {
                        List<PurchaseStockProduct> productList = iPurchaseStockProductService.findPurchaseStockProductByPurchaseOrder(p.getStockorder());
                        if(productList != null){
                            for (PurchaseStockProduct k : productList) {
                                if(property.getProductNo().equals(k.getPronum()) && property.getWarehousePosition().equals(k.getDepotnum())){
                                    productStock.setStocknum(productStock.getStocknum() - k.getStocknum());
                                }
                            }
                        }
                    }
                    Double oldMoney = productStock.getStocknum() * productStock.getParprice();
                    Double nowMoney = Double.valueOf(property.getPrice()) * Integer.valueOf(property.getWarehouseNum());
                    int num = productStock.getStocknum() + Integer.valueOf(property.getWarehouseNum());
                    nowPrice = new BigDecimal((oldMoney+nowMoney)+"").divide(new BigDecimal(num+""),2,BigDecimal.ROUND_UP);
                }

                if(property.getId() == null || property.getId() == 0L){
                    try {
                        product.setAddtime(new Date());
                        iAccountCoastPurchaseService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iAccountCoastPurchaseService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                // 更新 产品基本资料、采购入库 数据
                saveCostPrice(nowPrice.toString(),property.getProductNo(),property.getWarehousePosition());
            }
            if(arr != null){
                for (int i=0 ; i<arr.length; i++){
                    PurchaseStock purchaseStock = iPurchaseStockService.findPurchaseStockByNo(arr[i]);
                    if(purchaseStock != null){
                        purchaseStock.setCost(true);
                        iPurchaseStockService.updateNotNull(purchaseStock);
                    }
                }
            }
            showTableviewCost();
        }
    }

    /**
     * @Description 保存核算成到 产品基本资料、采购入库
     * @Author BlueSky
     * @Date 16:03 2019/4/19
     **/
    private void saveCostPrice(String price,String productno,String depot){
        if(price != null && !"".equals(price) && productno != null && !"".equals(productno)){
            ProductBasic basic = iProductBasicService.selectProductBasicByIsnum(productno);
            if(basic != null){
                basic.setCost(Double.valueOf(price));
                basic.setUsdcost(Double.valueOf(price));
                iProductBasicService.updateNotNull(basic);
            }
            ProductStock stock = iProductStockService.findProductStockByDepotAndIsnum(depot,productno);
            if(stock != null){
                stock.setParprice(Double.valueOf(price));
                iProductStockService.updateNotNull(stock);
            }
        }
    }

    /**
     * 保存数据
     */
    @FXML
    @SneakyThrows
    public void save(){
        setControllerDisable();
        AccountCoastAccounting order = new AccountCoastAccounting();
        order.setStorageInNo(storage_in_no.getText());
        order.setInstallNo(install_no.getText());
        order.setAudit(audit.getText());
        if(audit_date.getText() != null && !"".equals(audit_date.getText())){
            order.setAuditDate(df.parse(audit_date.getText()));
        }
        order.setStorageInRemark(storage_in_remark.getText());
        order.setVerifyPeople(verify_people.getText());
        order.setVerifyPeopleStr(verify_people_str.getText());

        order.setInvoiceNo(invoice_no.getText());
        order.setCurrency(currency.getSelectionModel().getSelectedIndex());
        order.setCustomsFee(customs_fee.getText());
        order.setProxyFee(proxy_fee.getText());
        order.setCarriageFee(carriage_fee.getText());
        order.setPoundageFee(poundage_fee.getText());
        order.setOtherFee(other_fee.getText());
        order.setLoanTotal(loan_total.getText());
        order.setTaxTotal(tax_total.getText());
        order.setPriceTaxTotal(price_tax_total.getText());
        order.setTotal(total.getText());
        order.setExchangeRate(exchange_rate.getText());
        order.setExchangeCurrency(exchange_currency.getText());

        String[] arr =null;
        val id = storage_in_no.getUserData();
        if(id!=null && !"".equals(id)){
            order.setId(Long.valueOf(id.toString()));
            val rows = iAccountCoastAccountingService.updateNotNull(order);
            alertProcessResult(rows);
        }else{
            order.setAddtime(new Date());
            val rows = iAccountCoastAccountingService.save(order);
            alertProcessResult(rows);
            storage_in_no.setUserData(order.getId());

            arr = order.getStorageInNo().split(",");

        }
        // 把发票号码带到采购入库单中去
        PurchaseStock purchaseStock = iPurchaseStockService.findPurchaseStockByNo(storage_in_no.getText());
        if(purchaseStock != null){
            purchaseStock.setInvoiceno(invoice_no.getText());
            purchaseStock.setInvoicedate(audit_date.getText()+"");
            iPurchaseStockService.updateNotNull(purchaseStock);
        }

        // 保存表格数据
        saveTableReceiptInfo(arr);
    }


    /**
     * 加载采购入库单数据
     * @param order
     */
    @SneakyThrows
    public void setBasicPurchaseValue(PurchaseStock order){
        if(order == null) {
            return;
        }
        if(storage_in_no.getUserData() != null){
            clearValue();
        }

        setControllerUse();
        if(storage_in_no.getText() != null && !"".equals(storage_in_no.getText())){
            if(storage_in_no.getText().indexOf(order.getStockorder()) > -1){
                return;
            }
            storage_in_no.setText(storage_in_no.getText()+","+order.getStockorder());
        }else{
            storage_in_no.setText(order.getStockorder());
        }
        if(install_no.getText() != null && !"".equals(install_no.getText())){
            install_no.setText(install_no.getText()+","+order.getBoxnum());
        }else {
            install_no.setText(order.getBoxnum());
        }
        storage_in_remark.setText(order.getRemarks());
        audit.setText(order.getShpeople());
        audit_date.setText(order.getShdate());
        createPeople(verify_people,verify_people_str);
        List<PurchaseStockProduct> productList = iPurchaseStockProductService.listPurchaseStockProductAndPurchaseStockByProdutNo(order.getStockorder());
        List<AccountCoastPurchaseProperty> propertyList = new ArrayList<>();
        if(productList != null && productList.size() > 0){
            if(tableview_cost.getItems() != null && tableview_cost.getItems().size() > 0){
                propertyList = tableview_cost.getItems();
            }
            int rows = 1;
//            Double totalMoney = 0.00;
            for (PurchaseStockProduct p : productList) {
//                totalMoney += p.getPrice()*p.getStocknum();
                AccountCoastPurchaseProperty purchaseProperty = new AccountCoastPurchaseProperty( rows++,p.getPronum(), p.getProname(), p.getDepotnum(), p.getStocknum()==null?"0":p.getStocknum().toString(), "0.00", "0.00", p.getPurchaseStocks().getStockorder(),"0.00","0.00");
                propertyList.add(purchaseProperty);
            }
//            loan_total.setText(totalMoney.toString());
            generalProductTab(propertyList);
        }
    }

    /**
     * 赋值--给控件加载数据
     * @param order
     */
    @SneakyThrows
    public void setBasicValue(AccountCoastAccounting order){
        if(order == null) {
            return;
        }
        storage_in_no.setUserData(order.getId());
        storage_in_no.setText(order.getStorageInNo());
        install_no.setText(order.getInstallNo());
        audit.setText(order.getAudit());
        if(order.getAuditDate()!=null){
            audit_date.setText(DateUtils.getSpecifyDate(order.getAuditDate(),DateUtils.FORMAT_YYYY_MM_DD));
        }
        storage_in_remark.setText(order.getStorageInRemark());
        verify_people.setText(order.getVerifyPeople());
        verify_people_str.setText(order.getVerifyPeopleStr());

        invoice_no.setText(order.getInvoiceNo());
        currency.getSelectionModel().select(order.getCurrency()+0);
        customs_fee.setText(order.getCustomsFee());
        proxy_fee.setText(order.getProxyFee());
        carriage_fee.setText(order.getCarriageFee());
        poundage_fee.setText(order.getPoundageFee());
        other_fee.setText(order.getOtherFee());
        loan_total.setText(order.getLoanTotal());
        tax_total.setText(order.getTaxTotal());
        price_tax_total.setText(order.getPriceTaxTotal());
        total.setText(order.getTotal());
        exchange_rate.setText(order.getExchangeRate());
        exchange_currency.setText(order.getExchangeCurrency());

        // 加载表格数据
        showTableviewCost();
    }

    /**
     * 恢复控件为可用
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
     * 设置控件状态
     * @param bool
     */
    private void setController(Boolean bool){
        if(bool){
            NumberUtil.changeStatus(writestate,0);
        }else{
            NumberUtil.changeStatus(writestate,2);
        }
        storage_in_remark.setDisable(bool);
        storage_in_no.setDisable(bool);
        install_no.setDisable(true);

        invoice_no.setDisable(bool);
        currency.setDisable(bool);
        customs_fee.setDisable(bool);
        proxy_fee.setDisable(bool);
        carriage_fee.setDisable(bool);
        poundage_fee.setDisable(bool);
        other_fee.setDisable(bool);
        loan_total.setDisable(bool);
        tax_total.setDisable(bool);
        price_tax_total.setDisable(bool);
        total.setDisable(bool);
        exchange_rate.setDisable(bool);
        exchange_currency.setDisable(bool);
        btn_cost.setDisable(bool);

        tableview_cost.setDisable(bool);
    }


    /**
     *  设置菜单控件状态
     * @param bool
     */
    private void setMenuControlState(Boolean bool){
        menu_clearAll.setDisable(false);
        menu_commit.setDisable(bool);
    }

    /**
     * 清除控件值
     */
    @FXML
    public void clearValue(){
        storage_in_no.setUserData(null);
        storage_in_no.clear();
        install_no.clear();
        audit.clear();
        audit_date.clear();
        storage_in_remark.clear();
        verify_people.clear();
        verify_people_str.clear();

        invoice_no.clear();
        currency.getSelectionModel().selectFirst();
        customs_fee.clear();
        proxy_fee.clear();
        carriage_fee.clear();
        poundage_fee.clear();
        other_fee.clear();
        loan_total.setText("0.00");
        tax_total.clear();
        price_tax_total.clear();
        total.clear();
        exchange_rate.clear();
        exchange_currency.clear();

        tableview_cost.setItems(null);

//        install_no.setText(createOrderNo(iAccountCoastAccountingService.getMaxOrderNo()));
    }

    /**
     * 打开订单查询窗口
     */
    @FXML
    public void OpenMiniQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountCostAccountingController", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_cost_accounting_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开采购入库单查询窗口
     */
    @FXML
    public void OpenPurchaseMiniQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("AccountCoastAccountingPurchaseController", this);

        pane.getChildren().setAll(loader.load("/fxml/account/order_cost_accounting_purchase_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
