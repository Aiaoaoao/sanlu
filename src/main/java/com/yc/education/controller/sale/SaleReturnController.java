package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.StockOutSale;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.customer.IRemarkService;
import com.yc.education.service.sale.*;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
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
 * 销售退货单
 */
@Controller
public class SaleReturnController extends BaseController implements Initializable {

    @Autowired IRemarkService iRemarkService;
    @Autowired IReportRemarkService iReportRemarkService;
    @Autowired ICustomerService iCustomerService;
    @Autowired ISaleGoodsService iSaleGoodsService;                 //销货单
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;   // 销货单产品详情
    @Autowired ISaleReturnGoodsService iSaleReturnGoodsService;     //销售退货单
    @Autowired ISaleReturnGoodsProductService iSaleReturnGoodsProductService;  //销售退货单产品详情
    @Autowired ProductStockService iProductStockService;            //产品库存
    @Autowired RelationService iRelationService;            //单据关联

    /**
     * @Description 单据关联容器
     * @Author BlueSky
     * @Date 17:07 2019/4/26
     **/
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;
    // 菜单控件
    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页
    @FXML VBox menu_clearAll; //清除
    @FXML VBox menu_commit;  //提交
    @FXML VBox menu_insert; //新增
    @FXML VBox menu_update; //修改
    @FXML VBox menu_delete; //删除
    @FXML VBox menu_printing; //删除
    @FXML VBox shiro_success;
    @FXML VBox shiro_cancel;
    @FXML VBox import_out;
    @FXML VBox import_in;
    @FXML Label writestate;// 待输入


    @FXML Button more_customer;   // 按钮 - 更多客户
    @FXML CheckBox che_early; // 前期单据
    @FXML Button btn_invalid; // 作废按钮
    @FXML DatePicker create_date;  // 制单日期
    @FXML public TextField customer_no; // 客户编号
    @FXML public TextField customer_no_str; // 客户编号描述
    @FXML ComboBox tax; // 税别
    @FXML ComboBox currency; // 币别
    @FXML TextField return_no; // 销售单号
    @FXML TextField made_people; //制单人
    @FXML TextField last_update; //最后修改人
    @FXML TextField last_update_str; //最后修改人描述
    @FXML ComboBox business_leader; //负责业务
    @FXML TextField business_leader_str; //负责业务描述
    @FXML public TextField warehouse_in;       // 入库仓库
    @FXML public TextField warehouse_in_str;   // 入库仓库描述
    @FXML ComboBox return_reason; // 退货原因
    @FXML TextField apply_people; // 退货申请人
    @FXML TextField audit_people; // 审核人
    @FXML TextField audit_people_str; // 审核人描述
    @FXML TextField remark; // 备注


    @FXML public TableView tab_product; // 订货产品 表格
    @FXML TableColumn col_id; // 列id
    @FXML TableColumn col_no; //列 编号
    @FXML TableColumn col_product_no; //列 产品编号
    @FXML TableColumn col_product_name; //列 产品名称
    @FXML TableColumn col_category; //列 品类
    @FXML TableColumn col_num; //列 数量
    @FXML TableColumn col_unit; // 列 单位
    @FXML TableColumn col_price; // 列 单价
    @FXML TableColumn col_pricing; // 列 定价
    @FXML TableColumn col_money; //列 金额
    @FXML TableColumn col_source; //列 来源数据
    @FXML TableColumn col_order_no; //列 单号
    @FXML public TableColumn col_warehouse_position; //列 库位
    @FXML public TableColumn col_floor; //列 楼层
    @FXML TableColumn col_remark; //列 备注
    @FXML TableColumn depotbutton; //列 库位选择按钮


    // 合计
    @FXML public TextField total_num; // 合计数量
    @FXML public TextField total_money; //合计金额
    @FXML public TextField total_loan; //合计贷款
    @FXML public TextField total_tax; //税款合计

    /***************************************** 弹出窗口-产品 ********************************************/
    int tablePosition = 0; // 弹出窗口选中的行数

    /***************************************** 弹出窗口-产品-结束 ********************************************/

    static long changeId = 0L;      // 记录当前编辑计算金额cell 的id值
    SpringFxmlLoader loader = new SpringFxmlLoader();
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

        tax.getItems().addAll("外加","内含","零税","免税");
        tax.getSelectionModel().select(0);
        setComboBox(7L,currency,0); //币别
        setComboBox(19L,return_reason,0); //退货原因
        loadEmployee(business_leader, 0); // 业务负责人
        // 截取姓名
        business_leader.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = business_leader.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    business_leader_str.setText(bus);
                }catch (Exception e){

                }
            }
        });

        // 报价产品行双击 调出现有产品窗口
        BaseController.clickEvent(tab_product, data ->{
            tablePosition = tab_product.getSelectionModel().getSelectedIndex();
            moreProductButtonClick();
        }, 2);

        SaleGoods order = StageManager.saleGoods;
        StageManager.saleGoods = null;
        if(order == null){
            setMenuValue(1);

        }else{
            //存储被关联单据
            relation.setBeRelationName("销货单");
            relation.setBeRelationId(order.getId());
            relationLock = true;
            clearControllerVal();

            if(order.getCreateDate() != null){
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
            }
            if(order.getSaleNo() != null){
                return_no.setText(order.getSaleNo());
            }
            if(order.getCustomerNo() != null){
                customer_no.setText(order.getCustomerNo());
            }
            if(order.getCustomerNoStr() != null){
                customer_no_str.setText(order.getCustomerNoStr());
            }
            if(order.getBusinessLeader() != null){
                business_leader.getSelectionModel().select(order.getBusinessLeader());
            }
            if(order.getBusinessLeaderStr() != null){
                business_leader_str.setText(order.getBusinessLeaderStr());
            }
            if(order.getTax() != null){
                tax.getSelectionModel().select(order.getTax());
            }
            if(order.getCurrency() != null){
                currency.getSelectionModel().select(order.getCurrency());
            }
            if(order.getWarehouseOut() != null){
                warehouse_in.setText(order.getWarehouseOut());
            }
            if(order.getWarehouseOutStr() != null){
                warehouse_in_str.setText(order.getWarehouseOutStr());
            }
            if(order.getCustomerName() != null){
                apply_people.setText(order.getCustomerName());
            }
            if(order.getMadePeople() != null){
                made_people.setText(order.getMadePeople());
            }
//            if(order.getAudit() != null){
//                audit_people.setText(order.getAudit());
//            }
//            if(order.getAuditStr() != null){
//                audit_people_str.setText(order.getAuditStr());
//            }
//            if(order.getLastUpdate() != null){
//                last_update.setText(order.getLastUpdate());
//            }
//            if(order.getLastUpdateStr() != null){
//                last_update_str.setText(order.getLastUpdateStr());
//            }
            lastUpdatePeopel(last_update, last_update_str);

            List<SaleReturnGoodsProductProperty> propertyList = new ArrayList<>();
            List<SaleGoodsProduct> productList = iSaleGoodsProductService.listSaleGoodsProduct(order.getId().toString());
            int rows = 1;
            for (SaleGoodsProduct p : productList) {
                propertyList.add(new SaleReturnGoodsProductProperty( rows++,p.getProductNo(),p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getPrice(), p.getMoney(), "销货单导入", p.getOrderNo(), p.getWarehousePosition(), p.getFloor(), p.getRemark()));
            }
            generalProductTab(FXCollections.observableArrayList(propertyList));
            setControllerUse();
        }
    }

    /**
     * 订单作废
     */
    @FXML
    public void orderInvalid(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_ABOLISH)) {
            if (return_no.getUserData() != null && !"".equals(return_no.getUserData().toString())) {
                SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.selectByKey(Long.valueOf(return_no.getUserData().toString()));
                if (saleReturnGoods != null && saleReturnGoods.getOrderAudit() == null || !saleReturnGoods.getOrderAudit()) {
                    btn_invalid.setDisable(true);
                    SaleReturnGoods order = new SaleReturnGoods();
                    order.setId(Long.valueOf(return_no.getUserData().toString()));
                    order.setInvalid(true);
                    int rows = iSaleReturnGoodsService.updateNotNull(order);
                    if(rows > 0){
                        import_out.setDisable(true);
                    }
                    returnMsg(rows);
                } else {
                    alert_informationDialog("此单据已经被审核，无法作废！");
                }
            }
        }
    }



    /**
     * 更多产品查询
     */
    public  void moreProductButtonClick(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleReturnControllerProduct", this);
        StageManager.CONTROLLER.put("tablePosition", tablePosition);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 产品view
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
            setTableviewVal();
        }
    }

    /**
     * 删除产品行
     */
    private void deleteRowOfProduct(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            // 取得当前行的数据
            try {
                if(tab_product.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) tab_product.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                SaleReturnGoodsProductProperty property = (SaleReturnGoodsProductProperty)tab_product.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iSaleReturnGoodsProductService.delete(property.getId());
                    returnMsg(rows);
                }
                final ObservableList<SaleReturnGoodsProductProperty> dataProperty = tab_product.getItems();
                final ObservableList<SaleReturnGoodsProductProperty> newDataProperty = FXCollections.observableArrayList();
                for (int i = 0; i < dataProperty.size(); i++) {
                    if(i != index){
                        newDataProperty.add(dataProperty.get(i));
                    }
                }
                tab_product.setItems(newDataProperty);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加产品行
     */
    public void addProductRow() {

        ObservableList<SaleReturnGoodsProductProperty> list = tab_product.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new SaleReturnGoodsProductProperty( list.size()+1,"", "", "", 0, "", new BigDecimal("0.00"), new BigDecimal("0.00"),new BigDecimal("0.00"), "", "", "", "", ""));
        generalProductTab(list);
    }

    /**
     * 新增订单
     */
    @FXML
    public void add(){
        clearControllerVal();
        create_date.setValue(localDate.toLocalDate());
        createPeople(made_people);
        return_no.setText(createOrderNo(iSaleReturnGoodsService.getMaxOrderNo()));
        setControllerUse();

        business_leader.getItems().clear();
        loadEmployee(business_leader, 0); // 业务负责人
        // 截取姓名
        business_leader.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try {
                    String bus = business_leader.getSelectionModel().getSelectedItem().toString();
                    bus = bus.substring(bus.indexOf(')')+1, bus.length());
                    business_leader_str.setText(bus);
                }catch (Exception e){

                }
            }
        });
    }

    /**
     * 保存数据
     */
    @FXML
    public synchronized  void save(){
        // 检查非空
        if(checkPrimaryNull(return_no,customer_no,customer_no_str,business_leader_str)){
            return;
        }
        if(checkWarehouseNull(warehouse_in,warehouse_in_str)){
            return;
        }
        List<SaleReturnGoodsProductProperty> list = tab_product.getItems();
        // 库位 楼层不为空
        for (int i = 0; i < list.size(); i++) {
            try {
                if(list.get(i).getFloor() == null || "".equals( list.get(i).getFloor()) || list.get(i).getWarehousePosition() == null || "".equals(list.get(i).getWarehousePosition())){
                    alert_informationDialog(list.get(i).getProductName()+"库位、楼层不能为空,单据提交失败！");
                    return;
                }
            }catch (NullPointerException e){}
        }

        lastUpdatePeopel(last_update, last_update_str);
        SaleReturnGoods order = new SaleReturnGoods();
        if(create_date.getValue() != null){
            try {
                Date date = df.parse(create_date.getValue().toString());
                order.setCreateDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(return_no.getText() != null && !"".equals(return_no.getText())){
            order.setPinbackNo(return_no.getText());
        }
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            order.setCustomerNo(customer_no.getText());
        }
        if(customer_no_str.getText() != null && !"".equals(customer_no_str.getText())){
            order.setCustomerNoStr(customer_no_str.getText());
        }
        if(tax.getSelectionModel().getSelectedItem() != null && !"".equals(tax.getSelectionModel().getSelectedItem())){
            order.setTax(tax.getSelectionModel().getSelectedItem().toString());
        }
        if(currency.getSelectionModel().getSelectedItem() != null && !"".equals(currency.getSelectionModel().getSelectedItem())){
            order.setCurrency(currency.getSelectionModel().getSelectedItem().toString());
        }
        if(warehouse_in.getText() != null && !"".equals(warehouse_in.getText())){
            order.setWarehouseIn(warehouse_in.getText());
        }
        if(warehouse_in_str.getText() != null && !"".equals(warehouse_in_str.getText())){
            order.setWarehouseInStr(warehouse_in_str.getText());
        }
        if(return_reason.getSelectionModel().getSelectedItem() != null && !"".equals(return_reason.getSelectionModel().getSelectedItem())){
            order.setReturnReason(return_reason.getSelectionModel().getSelectedItem().toString());
        }
        if(remark.getText() != null && !"".equals(remark.getText())){
            order.setRemark(remark.getText());
        }
        if(apply_people.getText() != null && !"".equals(apply_people.getText())){
            order.setReturnReasonPeople(apply_people.getText());
        }
        if(made_people.getText() != null && !"".equals(made_people.getText())){
            order.setMadePeople(made_people.getText());
        }
        if(audit_people.getText() != null && !"".equals(audit_people.getText())){
            order.setAuditPeople(audit_people.getText());
        }
        if(audit_people_str.getText() != null && !"".equals(audit_people_str.getText())){
            order.setAuditPeopleStr(audit_people_str.getText());
        }
        if(last_update.getText() != null && !"".equals(last_update.getText())){
            order.setLastUpdate(last_update.getText());
        }
        if(last_update_str.getText() != null && !"".equals(last_update_str.getText())){
            order.setLastUpdateStr(last_update_str.getText());
        }
        if(business_leader.getSelectionModel().getSelectedItem() != null && !"".equals(business_leader.getSelectionModel().getSelectedItem())){
            order.setBusinessLeader(business_leader.getSelectionModel().getSelectedItem().toString());
        }
        if(business_leader_str.getText() != null && !"".equals(business_leader_str.getText())){
            order.setBusinessLeaderStr(business_leader_str.getText());
        }
        if (return_no.getUserData() != null && !"".equals(return_no.getUserData().toString())) {
            order.setId(Long.valueOf(return_no.getUserData().toString()));
        }
        order.setEarlyDocument(che_early.selectedProperty().getValue());


        if(order.getId() != null && order.getId() != 0){
            // 修改
            int rows = iSaleReturnGoodsService.updateNotNull(order);
            returnMsg(rows);
            setControllerDisable();
        }else{
            order.setTaxRate(Float.valueOf(getTax(new Date(),2).toString()));
            order.setOrderAudit(false);
            order.setInvalid(false);
            order.setTicket(false);
            order.setAddtime(new Date());
            // 保存
            int rows = iSaleReturnGoodsService.save(order);
            return_no.setUserData(order.getId().toString());
            returnMsg(rows);
            setControllerDisable();

            if(relationLock){
                //添加关联关系
                relation.setRelationName("销售退货单");
                relation.setRelationId(order.getId());
                iRelationService.save(relation);
            }

        }
        // 操作tableview中的数据
        saveTableviewProduct();
        // 重新加载表格数据
        setTableviewVal();
        setBasicVal(order);
    }

    /**
     * 保存 销退产品tableview数据
     */
    private void saveTableviewProduct(){
        String id = return_no.getUserData().toString();
        if(id != null && !"".equals(id) && tab_product.getItems() != null){
            int tableSize = tab_product.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                SaleReturnGoodsProductProperty property = null;
                if(tab_product.getItems().get(i) != null){
                    property = (SaleReturnGoodsProductProperty)tab_product.getItems().get(i);
                }
                SaleReturnGoodsProduct order = new SaleReturnGoodsProduct();

                if(property.getProductNo() != null && !"".equals(property.getProductNo())){
                    order.setProductNo(property.getProductNo());
                }
                if(property.getProductName() != null && !"".equals(property.getProductName())){
                    order.setProductName(property.getProductName());
                }
                if(property.getCategory() != null && !"".equals(property.getCategory())){
                    order.setCategory(property.getCategory());
                }
                if(property.getNum() != null && !"".equals(property.getNum())){
                    order.setNum(Integer.valueOf(property.getNum()));
                }
                if(property.getUnit() != null && !"".equals(property.getUnit())){
                    order.setUnit(property.getUnit());
                }
                if(property.getPricing() != null && !"".equals(property.getPricing())){
                    order.setPricing(new BigDecimal(property.getPricing()));
                }
                if(property.getPrice() != null && !"".equals(property.getPrice())){
                    order.setPrice(new BigDecimal(property.getPrice()));
                }
                if(property.getSource() != null && !"".equals(property.getSource())){
                    order.setSource(property.getSource());
                }
                if(property.getOrderNo() != null && !"".equals(property.getOrderNo())){
                    order.setOrderNo(property.getOrderNo());
                }
                if(property.getMoney() != null && !"".equals(property.getMoney())){
                    order.setMoney(new BigDecimal(property.getMoney()));
                }
                if(property.getWarehousePosition() != null && !"".equals(property.getWarehousePosition())){
                    order.setWarehousePosition(property.getWarehousePosition());
                }
                if(property.getFloor() != null && !"".equals(property.getFloor())){
                    order.setFloor(property.getFloor());
                }
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    order.setRemark(property.getRemark());
                }
                order.setReturnGoodsId(Long.valueOf(id));

                if(property.getId() == null || property.getId()==0L){
                    try {
                        order.setAddtime(new Date());
                        order.setInboundNum(0); // 入库数量
                        iSaleReturnGoodsProductService.save(order);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        order.setId(property.getId());
                        iSaleReturnGoodsProductService.updateNotNull(order);
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
    public  synchronized void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if (return_no.getUserData() != null && !"".equals(return_no.getUserData().toString())) {
                int rows = iSaleReturnGoodsService.delete(Long.valueOf(return_no.getUserData().toString()));
                iSaleReturnGoodsProductService.deleteSaleReturnGoodsProductByParentId(return_no.getUserData().toString());
                // 删除关联单据
                iRelationService.deleteRelation("销售退货单",Long.valueOf(return_no.getUserData().toString()));
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
        //权限管理
        matchingPermissions("销售退货单",menu_insert,menu_delete,menu_update,shiro_success,shiro_cancel,menu_printing,menu_clearAll);
        List<SaleReturnGoods> SaleReturnGoodsList = iSaleReturnGoodsService.listSaleReturnGoodsByPage("","",page, 1);
        if(SaleReturnGoodsList != null && SaleReturnGoodsList.size() >0){
            PageInfo<SaleReturnGoods> pageInfo = new PageInfo<>(SaleReturnGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            setBasicVal(SaleReturnGoodsList.get(0));
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
     * @param order
     */
    public void setBasicVal(SaleReturnGoods order){
        clearControllerVal();
        if(order == null){
            return;
        }

        if(order.getId() != null){
            return_no.setUserData(order.getId().toString());
        }
        if(order.getCreateDate() != null){
            create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(order.getPinbackNo() != null){
            return_no.setText(order.getPinbackNo());
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getBusinessLeader() != null){
            business_leader.getSelectionModel().select(order.getBusinessLeader());
        }
        if(order.getBusinessLeaderStr() != null){
            business_leader_str.setText(order.getBusinessLeaderStr());
        }
        if(order.getTax() != null){
            tax.getSelectionModel().select(order.getTax());
        }
        if(order.getCurrency() != null){
            currency.getSelectionModel().select(order.getCurrency());
        }
        if(order.getWarehouseIn() != null){
            warehouse_in.setText(order.getWarehouseIn());
        }
        if(order.getWarehouseInStr() != null){
            warehouse_in_str.setText(order.getWarehouseInStr());
        }
        if(order.getReturnReason() != null){
            return_reason.getSelectionModel().select(order.getReturnReason());
        }
        if(order.getRemark() != null){
            remark.setText(order.getRemark());
        }
        if(order.getReturnReasonPeople() != null){
            apply_people.setText(order.getReturnReasonPeople());
        }
        if(order.getMadePeople() != null){
            made_people.setText(order.getMadePeople());
        }
        if(order.getAuditPeople() != null){
            audit_people.setText(order.getAuditPeople());
        }
        if(order.getAuditPeopleStr() != null){
            audit_people_str.setText(order.getAuditPeopleStr());
        }
        if(order.getLastUpdate() != null){
            last_update.setText(order.getLastUpdate());
        }
        if(order.getLastUpdateStr() != null){
            last_update_str.setText(order.getLastUpdateStr());
        }
        che_early.setSelected(order.getEarlyDocument());
        // 审核
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
        // 单据作废
        if(order.getInvalid() != null){
            if(order.getInvalid()){
                btn_invalid.setDisable(true);
                import_out.setDisable(true);
            }else{
                btn_invalid.setDisable(false);
                import_out.setDisable(false);
            }
        }else{
            btn_invalid.setDisable(false);
            import_out.setDisable(false);
        }
        setTableviewVal();
    }

    /**
     * 加载销售单的数据
     * @param order
     */
    public void setBasicImportVal(SaleGoods order){
        if(order == null){
            return;
        }
        clearControllerVal();
        if(order.getCreateDate() != null){
            create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        return_no.setText(createOrderNo(iSaleReturnGoodsService.getMaxOrderNo()));
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getBusinessLeader() != null){
            business_leader.getSelectionModel().select(order.getBusinessLeader());
        }
        if(order.getBusinessLeaderStr() != null){
            business_leader_str.setText(order.getBusinessLeaderStr());
        }
        if(order.getTax() != null){
            tax.getSelectionModel().select(order.getTax());
        }
        if(order.getCurrency() != null){
            currency.getSelectionModel().select(order.getCurrency());
        }
        if(order.getWarehouseOut() != null){
            warehouse_in.setText(order.getWarehouseOut());
        }
        if(order.getWarehouseOutStr() != null){
            warehouse_in_str.setText(order.getWarehouseOutStr());
        }
        if(order.getMadePeople() != null){
            made_people.setText(order.getMadePeople());
        }
        lastUpdatePeopel(last_update, last_update_str);
        che_early.setSelected(true);
    }

    /**
     * 加载销货出库单中的数据
     * @param order
     */
    public void setBasicImportOutboundVal(StockOutSale order){
        if(order == null){
            return;
        }
        clearControllerVal();
        add();
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getWarehouseIn() != null){
            warehouse_in.setText(order.getWarehouseIn());
        }
        if(order.getWarehouseInStr() != null){
            warehouse_in_str.setText(order.getWarehouseInStr());
        }
    }

    /**
     * 加载tableview数据
     */
    private void setTableviewVal(){
        if (return_no.getUserData() != null && !"".equals(return_no.getUserData().toString())) {
            List<SaleReturnGoodsProduct> productList = iSaleReturnGoodsProductService.listReturnGoodsProduct(return_no.getUserData().toString());
            List<SaleReturnGoodsProductProperty> productPropertyList = new ArrayList<>();
            if(productList == null) {return;}
            clearTotalCalcVal();
            int rows = 1;
            for (SaleReturnGoodsProduct p : productList) {
                totalCost(p.getNum()==null?0:p.getNum(), p.getMoney()==null?new BigDecimal("0.00"):p.getMoney(), tax.getItems()==null?0:tax.getSelectionModel().getSelectedIndex()+1, total_num, total_tax, total_loan, total_money);
                productPropertyList.add(new SaleReturnGoodsProductProperty(p.getId(),rows++,p.getReturnGoodsId(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit(), p.getPricing(), p.getPrice(), p.getMoney(),p.getSource(), p.getOrderNo(), p.getWarehousePosition() , p.getFloor() ,p.getRemark()));
            }
            generalProductTab(FXCollections.observableArrayList(productPropertyList));
        }

    }

    /**
     * 加载表格数据
     * @param productPropertyList
     */
    public void generalProductTab(ObservableList<SaleReturnGoodsProductProperty> productPropertyList){
        tab_product.setEditable(true);

        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_category.setCellFactory(column -> EditCell.createStringEditCell());
        col_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
        col_pricing.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_money.setCellFactory(column -> EditCell.createStringEditCell());
        col_source.setCellFactory(column -> EditCell.createStringEditCell());
        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_warehouse_position.setCellFactory(column -> EditCell.createStringEditCell());
        col_floor.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());
        Callback<TableColumn<SaleReturnGoodsProductProperty, String>, TableCell<SaleReturnGoodsProductProperty, String>> buttonFactory
                = new Callback<TableColumn<SaleReturnGoodsProductProperty, String>, TableCell<SaleReturnGoodsProductProperty, String>>() {
            @Override
            public TableCell call(final TableColumn<SaleReturnGoodsProductProperty, String> param) {
                final TableCell<SaleReturnGoodsProductProperty, String> cell = new TableCell<SaleReturnGoodsProductProperty, String>() {

                    final Button btn1 = new Button("...");

                    @Override
                    public void updateItem(String ite, boolean empty) {
                        super.updateItem(ite, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction((ActionEvent t)
                                    -> {
                                int selectIndex = getTableRow().getIndex();
                                //现有库位查询
                                openMoreWarehouseQuery(selectIndex);
                            });
                            btn1.setMaxWidth(20);
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
        depotbutton.setCellFactory(buttonFactory);

        final ObservableList<SaleReturnGoodsProductProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_category.setCellValueFactory(new PropertyValueFactory("category"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_pricing.setCellValueFactory(new PropertyValueFactory("pricing"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_source.setCellValueFactory(new PropertyValueFactory("source"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_money.setCellValueFactory(new PropertyValueFactory("money"));
        col_warehouse_position.setCellValueFactory(new PropertyValueFactory("warehousePosition"));
        col_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tab_product.setItems(dataProperty);

        tab_product.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleReturnGoodsProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends SaleReturnGoodsProductProperty> observableValue, SaleReturnGoodsProductProperty oldItem, SaleReturnGoodsProductProperty newItem) {
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

        //提交单价计算金额  询价订单--询价产品 金额总计
        col_num.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;
                for (SaleReturnGoodsProductProperty property :dataProperty) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setNum(String.valueOf(newNum));
                        //计算金额
                        property.setMoney(String.valueOf(newNum*Double.valueOf(property.getPrice())));
                    }
                }
            }
        });
        //提交单价计算金额  询价订单--询价产品 金额总计
        col_pricing.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                String newValue =event.getNewValue().toString();
                Double  newPrice = newValue != null && !"".equals(newValue)?newPrice=Double.parseDouble(newValue):0.00;
                for (SaleReturnGoodsProductProperty property :dataProperty) {
                    if(Integer.valueOf(property.getNo()) == changeId){
                        property.setPricing(String.valueOf(newPrice));
                        //计算金额
                        property.setMoney(String.valueOf(Integer.parseInt(property.getNum())*newPrice));
                    }
                }
            }
        });
    }

    /**
     * 清除控件上的值
     */
    @FXML
    private void clearControllerVal(){

        LocalDateTime localDate = LocalDateTime.now();

         che_early.setSelected(false);
         create_date.setValue(localDate.toLocalDate());
         customer_no.clear();
         customer_no_str.clear();
         tax.getSelectionModel().selectFirst();
         currency.getSelectionModel().selectFirst();
         return_no.clear();
         return_no.setUserData(null);
         made_people.clear();
         last_update.clear();
         last_update_str.clear();
         business_leader.getSelectionModel().selectFirst();
         business_leader_str.clear();
         warehouse_in.clear();
         warehouse_in_str.clear();
         return_reason.getSelectionModel().selectFirst();
         apply_people.clear();
         audit_people.clear();
         audit_people_str.clear();
         remark.clear();


//         col_id; // 列id
//         col_product_no; //列 产品编号
//         col_product_name; //列 产品名称
//         col_category; //列 品类
//         col_num; //列 数量
//         col_unit; // 列 单位
//         col_pricing; // 列 定价
//         col_money; //列 金额
//         col_source; //列 来源数据
//         col_order_no; //列 单号
//         col_warehouse_position; //列 库位
//         col_floor; //列 楼层
//         col_remark; //列 备注

        clearTotalCalcVal();

        tab_product.setItems(null);
    }


    /**
     * 清空合计框中的值
     */
    protected void clearTotalCalcVal(){
        total_num.setText("0");
        total_loan.setText("0.00");
        total_money.setText("0.00");
        total_tax.setText("0.00");
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
    @FXML
    public void setControllerUse(){
        setControllerState(false);
        if(return_no.getUserData() != null && !"".equals(return_no.getUserData().toString())){
            SaleReturnGoods order = iSaleReturnGoodsService.selectByKey(Long.valueOf(return_no.getUserData().toString()));
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
         create_date.setDisable(bool);
         menu_clearAll.setDisable(bool);
         menu_commit.setDisable(bool);
//         menu_insert.setDisable(bool);
         menu_delete.setDisable(bool);


        more_customer.setDisable(bool);
        che_early.setDisable(bool);
        create_date.setDisable(bool);
        customer_no.setDisable(true);
        customer_no_str.setDisable(bool);
        tax.setDisable(bool);
        currency.setDisable(bool);
        return_no.setDisable(true);
        made_people.setDisable(true);
        last_update.setDisable(true);
        last_update_str.setDisable(true);
        business_leader.setDisable(bool);
        business_leader_str.setDisable(bool);
        warehouse_in.setDisable(true);
        warehouse_in_str.setDisable(bool);
        return_reason.setDisable(bool);
        apply_people.setDisable(bool);
        audit_people.setDisable(true);
        audit_people_str.setDisable(true);
        remark.setDisable(bool);
        btn_invalid.setDisable(bool);
        

         tab_product.setDisable(bool);

        // 合计
         total_num.setDisable(bool);
         total_money.setDisable(bool);
         total_loan.setDisable(bool);
         total_tax.setDisable(bool);
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
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = return_no.getUserData().toString();

        if(id != null && !"".equals(id)){
            SaleReturnGoods order = iSaleReturnGoodsService.selectByKey(Long.valueOf(id));
            if(order != null && order.getInvalid() != null && !order.getInvalid() || order.getInvalid() == null){
                setShiroControlYES();
                lastUpdatePeopel(last_update, last_update_str);
                lastUpdatePeopel(audit_people, audit_people_str);
                shiroUpdateData(true);
            }else{
                alert_informationDialog("此单据已经被作废，无法审核！");
            }
        }else{
            alert_informationDialog("单据还暂未保存，无法审核！");
        }
    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool){
        String id = return_no.getUserData().toString();
        if(id != null && !"".equals(id)){
            SaleReturnGoods goods = iSaleReturnGoodsService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAuditPeople(audit_people.getText());
            goods.setAuditPeopleStr(audit_people_str.getText());
            iSaleReturnGoodsService.updateNotNull(goods);


            List<SaleReturnGoodsProductProperty> propertyList = tab_product.getItems();
            if(propertyList != null && propertyList.size() >0){
                propertyList.forEach(p->{
                    // 往库存表里面添加销退未入库数量
                    ProductStock stock = iProductStockService.findProductStockByDepotAndIsnum(p.getWarehousePosition(),p.getProductNo());
                    if(stock != null && p.getNum() != null && !"".equals(p.getNum())){
                        if(bool){
                            stock.setBacknum(stock.getBacknum()+Integer.valueOf(p.getNum()));
                        }else{
                            stock.setBacknum(stock.getBacknum()-Integer.valueOf(p.getNum()));
                        }
                        iProductStockService.updateNotNull(stock);
                    }
                    // 给客户退款
                    Customer customer = iCustomerService.getCustomer(goods.getCustomerNo());
                    if(p.getMoney() != null && customer != null){
                        if(bool){
                            customer.setUseableMoney(customer.getUseableMoney().add(new BigDecimal(p.getMoney())));
                        }else{
                            customer.setUseableMoney(customer.getUseableMoney().subtract(new BigDecimal(p.getMoney())));
                        }
                        iCustomerService.updateNotNull(customer);
                    }
                });
            }



        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel(){
        // 有效单据验证
        String id = return_no.getUserData().toString();
        if(id == null && "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }

        if(!iRelationService.isRelation("销售退货单",Long.valueOf(id))){
            setShiroControlNO();
            lastUpdatePeopel(audit_people, audit_people_str);
            shiroUpdateData(false);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }
    }

    /**
     * 导入 销货单
     */
    @FXML
    public void importInSaleGoods(){
        stage.setTitle("导入-销货单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleReturnControllerImport", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 导入 销货出库单
     */
    @FXML
    public void importInOutbound(){
        stage.setTitle("导入-销货出库单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleReturnControllerImportOutbound", this);
        pane.getChildren().setAll(loader.load("/fxml/stock/sale_outbound_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 打开订单查询窗口
     */
    @FXML
    public void OpenMiniQuery() {
        Pane pane = new Pane();
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleReturnControllerNo", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_return_query_mini.fxml"));
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
        StageManager.CONTROLLER.put("SaleReturnController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * 销货退货单  导出  --库存异动作业
     */
    public void exportStockChangeOrder(){

        SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.selectByKey(Long.valueOf(return_no.getUserData().toString()));

        if(saleReturnGoods != null){


            if(saleReturnGoods.getOrderAudit()){

                changeHomeBtn(1,3,3);

                StageManager.saleReturnGoods = saleReturnGoods;

                Pane pane1 = StageManager.ORDERCONTENT.get("stockPane");

                pane1.getChildren().setAll(loader.load("/fxml/stock/stock_change.fxml"));

            }else{
                alert_informationDialog(AppConst.ALERT_EXAMINE);
            }

        }else{
            alert_informationDialog("暂未单据导出!");
        }


    }


    /**
     * 打开窗口 -- 现有库位查询
     * @param index 第几行
     */
    @FXML
    public void openMoreWarehouseQuery(int index){
        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("index", index);
        String productNum = "";
        if(tab_product.getItems() != null){
            productNum = ((SaleReturnGoodsProductProperty)tab_product.getItems().get(index)).getProductNo();
        }
        StageManager.CONTROLLER.put("productNum", productNum);
        StageManager.CONTROLLER.put("SaleReturnControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/storehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @Description 现有仓库查询
     * @Author BlueSky
     * @Date 10:41 2019/4/24
     **/
    @FXML
    public void openWarehouseQuery(){
        stage.setTitle("现有仓库查询");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleReturnControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/warehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
