package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.customer.Remark;
import com.yc.education.model.sale.ReportRemark;

import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.model.stock.StockOutSale;
import com.yc.education.model.stock.StockOutSaleProduct;
import com.yc.education.model.stock.StockOutSaleProductProperty;
import com.yc.education.model.customer.RemarkProperty;
import com.yc.education.model.customer.ReportRemarkProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.customer.IRemarkService;
import com.yc.education.service.sale.IReportRemarkService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.service.stock.IStockOutSaleProductService;
import com.yc.education.service.stock.IStockOutSaleService;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.util.*;
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
 * 库存 - 销货出库单
 */
@Controller
public class SaleOutboundOrderController extends BaseController implements Initializable {

    @Autowired IRemarkService iRemarkService;       // 备注
    @Autowired IReportRemarkService iReportRemarkService;   // 报告
    @Autowired IStockOutSaleService iStockOutSaleService;   // 销货出库单
    @Autowired IStockOutSaleProductService iStockOutSaleProductService;     // 销货出库单详情
    @Autowired ISaleGoodsService iSaleGoodsService;   // 销货出库单
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;   // 销货出库单详情
    @Autowired DataSettingService iDataSettingService;
    @Autowired ProductStockService productStockService;
    @Autowired  ProductBasicService productBasicService;
    @Autowired DepotBasicService depotBasicService;
    @Autowired RelationService iRelationService;     // 关联单据
    @Autowired PurchaseStockProductService iPurchaseStockProductService;    //采购入库单

    /**
     * @Description 关联单据容器
     * @Author BlueSky
     * @Date 17:55 2019/4/27
     **/
    public Relation relation = new Relation();
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
    @FXML VBox menu_printing;        //打印
    @FXML VBox shiro_success;        // 审核
    @FXML VBox shiro_cancel;         // 取消审核
    @FXML VBox import_in;            // 导入
    @FXML Label writestate;          // 待输入

    // 订单id
    @FXML public TextField orderid;

    // 作废按钮
    @FXML Button btn_invalid;
    @FXML DatePicker order_date;
    @FXML public TextField customer_no;
    @FXML public TextField customer_no_str;
    @FXML public TextField sale_no;     //销货单号

    @FXML TextField made_people; // 制单人
    @FXML TextField order_no;
    @FXML TextField last_update_str;
    @FXML TextField last_update;
    @FXML public TextField warehouse_in;
    @FXML public TextField warehouse_in_str;
    @FXML TextField audit;
    @FXML TextField audit_str;

 // 产品tableview
    @FXML public TableView tableview;
    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_source_order;
    @FXML TableColumn col_order_no;
    @FXML TableColumn col_product_no;
    @FXML TableColumn col_product_name;
    @FXML TableColumn col_category;
    @FXML TableColumn col_unit;
    @FXML TableColumn col_price;
    @FXML TableColumn col_num;
    @FXML public TableColumn col_location_name;
    @FXML public TableColumn col_floor;
    @FXML TableColumn col_remark;
    @FXML TableColumn depotbutton;

    // 备注tableview
    @FXML TableView remark_table; //备注及说明--备注表格
    @FXML TableColumn col_remark_id; //列id
    @FXML TableColumn col_remark_content; //列 内容
    // 报表tableview
    @FXML TableView report_table; //备注及说明--报表
    @FXML TableColumn col_report_id; // 列 id
    @FXML TableColumn col_report_content; //列 内容


    /**
     * 仓库库位
     */
    @FXML
    private TableView depotView;

    /**
     * 编号
     */
    @FXML
    private TableColumn depid;
    /**
     * 仓库编号
     */
    @FXML
    private TableColumn finddepotid;

    /**
     * 仓库名称
     */
    @FXML
    private TableColumn finddepotname;

    private Stage stage = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();
    // 日期格式
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.CHINA);
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    LocalDateTime localDate = LocalDateTime.now();


    // 出库仓库 表格
    private int pageSize = 10;

    @FXML private VBox dataSetting_find_fast;
    @FXML private VBox dataSetting_find_prev;
    @FXML private VBox dataSetting_find_next;
    @FXML private VBox dataSetting_find_last;

    @FXML private TextField dataSetting_textField_pageSize;

    @FXML private TextField dataSetting_order_textField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        relation = new Relation();
        relationLock = false;

        String newStr = location.toString();

        int index = newStr.indexOf("sale_outbound_order.fxml");

        if(index != -1) {

            SaleGoods order = StageManager.saleGoods;      // 业务单据-销货单、业务查询-订货未销、业务查询-销货出库单 导入数据
            StageManager.clear();
            if(order == null){
                setMenuValue(1);
            }else{
                setControllerUse();
                add();
                order_no.setText(createOrderNo(iStockOutSaleService.getMaxOrderNo()));
                //存储被关联单据
                relation.setBeRelationName("销货单");
                relation.setBeRelationId(order.getId());
                relationLock =true;
                if(order.getSaleNo() != null){
                    sale_no.setText(order.getSaleNo());
                }
                if(order.getCustomerNo() != null){
                    customer_no.setText(order.getCustomerNo());
                }
                if(order.getCustomerNoStr() != null){
                    customer_no_str.setText(order.getCustomerNoStr());
                }
                if(order.getWarehouseOut() != null){
                    warehouse_in.setText(order.getWarehouseOut());
                }
                if(order.getWarehouseOutStr() != null){
                    warehouse_in_str.setText(order.getWarehouseOutStr());
                }

                List<SaleGoodsProduct> saleGoodsProductList = iSaleGoodsProductService.listSaleGoodsProduct(order.getId().toString());
                if(saleGoodsProductList != null){
                    List<StockOutSaleProductProperty> productPropertyList = new ArrayList<>();
                    int rows = 1;
                    for (SaleGoodsProduct p : saleGoodsProductList) {
                        productPropertyList.add(new StockOutSaleProductProperty(0L,rows++ ,0L,"销货单" ,order.getSaleNo(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit()==null?null:p.getUnit(), p.getPrice(), p.getWarehousePosition(), p.getFloor() ,p.getRemark()));
                    }
                    generalProductTab(productPropertyList);

                }
            }



        }


    }

    /**
     * 审核通过
     */
    @FXML
    public void setShiroControlSuccess(){
        // 有效单据验证
        String id = orderid.getText()==null?null:orderid.getText();

        if(id != null && !"".equals(id)){

            if(updateProductStock(true)){
                setShiroControlYES();
                lastUpdatePeopel(last_update, last_update_str);
                lastUpdatePeopel(audit, audit_str);
                shiroUpdateData(true);
            }else{
                alert_informationDialog("数据错误,审核失败！");
            }
        }else{
            alert_informationDialog("单据还暂未保存，无法审核！");
        }
    }

    /**
     * 审核过后的数据提交
     */
    private void shiroUpdateData(Boolean bool){
        String id = orderid.getText()==null?null:orderid.getText();

        if(id != null && !"".equals(id)){
            StockOutSale goods = iStockOutSaleService.selectByKey(Long.valueOf(id));
            goods.setOrderAudit(bool);
            goods.setLastUpdate(last_update.getText());
            goods.setLastUpdateStr(last_update_str.getText());
            goods.setAudit(audit.getText());
            goods.setAuditStr(audit_str.getText());
            iStockOutSaleService.updateNotNull(goods);
        }
    }

    /**
     * 取消审核
     */
    @FXML
    public void setShiroControlCancel(){
        // 有效单据验证
        String id = orderid.getText()==null?null:orderid.getText();

        if(id == null && "".equals(id)){
            alert_informationDialog("单据还暂未保存，无法审核！");
            return;
        }
        if(updateProductStock(false)){
            setShiroControlNO();
            lastUpdatePeopel(audit, audit_str);
            shiroUpdateData(false);
        }else{
            alert_informationDialog("数据错误,取消审核失败！");
        }
    }

    /**
     * 审核通过
     */
    private void setShiroControlYES(){
        shiro_cancel.setDisable(false);
        shiro_success.setDisable(true);
        import_in.setDisable(false);
        menu_update.setDisable(true);
    }

    /**
     * 取消审核
     */
    private void setShiroControlNO(){
        shiro_cancel.setDisable(true);
        shiro_success.setDisable(false);
        menu_update.setDisable(false);
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
        // 取得当前行的数据
        try {
            TablePosition pos = (TablePosition) tableview.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            StockOutSaleProductProperty property = (StockOutSaleProductProperty)tableview.getItems().get(index);
            if(property.getId() != null && property.getId() != 0L){
                int rows = iStockOutSaleProductService.delete(property.getId());
                returnMsg(rows);
            }
        }catch (Exception e){
            alert_informationDialog("请选择需要删除的行！");
            e.printStackTrace();
        }
    }

    /**
     * 添加产品行
     */
    public void addProductRow() {

        ObservableList<StockOutSaleProductProperty> list = tableview.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new StockOutSaleProductProperty(0L, list.size()+1,0L, "", "", "", "", "", 0, "", new BigDecimal("0.00"), "", "", ""));
        tableview.setItems(list);
    }



    /**
     * 新增订单
     */
    @FXML
    public void add(){
        clearControllerVal();
        order_date.setValue(localDate.toLocalDate());
        order_no.setText(createOrderNo(iStockOutSaleService.getMaxOrderNo()));
        createPeople(made_people);
        setControllerUse();
    }

    /**
     * 保存数据
     */
    @FXML
    public void save(){
        // 最后修改人
        lastUpdatePeopel(last_update, last_update_str);
        StockOutSale order = new StockOutSale();
        if(order_date.getValue() != null){
            try {
                Date date = df.parse(order_date.getValue().toString());
                order.setCreateDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(customer_no.getText() != null && !"".equals(customer_no.getText())){
            order.setCustomerNo(customer_no.getText());
        }
        if(order_no.getText() != null && !"".equals(order_no.getText())){
            order.setOutboundNo(order_no.getText());
        }
        if(sale_no.getText() != null && !"".equals(sale_no.getText())){
            order.setSaleNo(sale_no.getText());
        }
        if(customer_no_str.getText() != null && !"".equals(customer_no_str.getText())){
            order.setCustomerNoStr(customer_no_str.getText());
        }
        if(audit.getText() != null && !"".equals(audit.getText())){
            order.setAudit(audit.getText());
        }
        if(audit_str.getText() != null && !"".equals(audit_str.getText())){
            order.setAuditStr(audit_str.getText());
        }
        if(orderid.getText() != null && !"".equals(orderid.getText())){
            order.setId(Long.valueOf(orderid.getText()));
        }
        if(made_people.getText() != null && !"".equals(made_people.getText())){
            order.setMadePeople(made_people.getText());
        }
        if(last_update_str.getText() != null && !"".equals(last_update_str.getText())){
            order.setLastUpdateStr(last_update_str.getText());
        }
        if(last_update.getText() != null && !"".equals(last_update.getText())){
            order.setLastUpdate(last_update.getText());
        }
        if(warehouse_in.getText() != null && !"".equals(warehouse_in.getText())){
            order.setWarehouseIn(warehouse_in.getText());
        }
        if(warehouse_in_str.getText() != null && !"".equals(warehouse_in_str.getText())){
            order.setWarehouseInStr(warehouse_in_str.getText());
        }

        int rows = 0;
        if(order.getId() != null){
            // 修改
            rows = iStockOutSaleService.updateNotNull(order);
        }else{
            // 保存
            order.setOrderAudit(false);
            order.setAddtime(new Date());
            rows = iStockOutSaleService.save(order);
            orderid.setText(order.getId().toString());


            if(relationLock){
                //添加关联关系
                relation.setRelationName("销货出库单");
                relation.setRelationId(order.getId());
                iRelationService.save(relation);
            }



            // 修改销货单导入状态
            SaleGoods saleGoods = iSaleGoodsService.findBySaleNo(order.getOutboundNo());
            if(saleGoods != null){
                saleGoods.setImportOutboundAudit(true);
                int num = iSaleGoodsService.updateNotNull(saleGoods);
                if(num<=0){
                    alert_informationDialog("导入失败！");
                }
            }
        }
        // 操作tableview中的数据
        saveTableviewProduct();
        saveTableviewRemark();
        saveTableviewReport();
        setBasicVal(order);
        returnMsg(rows);
        setControllerDisable();
    }

    /**
     * 保存订货产品tableview数据
     */
    private void saveTableviewProduct(){

        String id = orderid.getText();
        if(id != null && !"".equals(id) && tableview.getItems() != null){
            int tableSize = tableview.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                StockOutSaleProductProperty property = null;
                if(tableview.getItems().get(i) != null){
                    property = (StockOutSaleProductProperty)tableview.getItems().get(i);
                }
                StockOutSaleProduct product = new StockOutSaleProduct();
                if(property.getOrderNo() != null){
                    product.setOrderNo(property.getOrderNo());
                }
                if(property.getOrderSource() != null){
                    product.setOrderSource(property.getOrderSource());
                }
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
                if(property.getWarehouseName() != null){
                    product.setWarehouseName(property.getWarehouseName());
                }
                if(property.getFloor() != null){
                    product.setFloor(property.getFloor());
                }
                if(property.getRemark() != null){
                    product.setRemark(property.getRemark());
                }
                product.setStockOutSaleId(Long.valueOf(id));
                product.setAddtime(new Date());

                if(property.getOrderSource() != null && "销货单".equals(property.getOrderSource()) &&  property.getOrderNo() != null  && property.getNum() != null ){
                    // 修改销货单 出库数量
                    SaleGoodsProduct pro = iSaleGoodsProductService.getSaleGoodsProductBySaleNum(property.getOrderNo(),property.getProductNo());
                    if(pro != null){
                        int num = Integer.valueOf(property.getNum()) - pro.getOutboundNum();
                        int rows = iSaleGoodsProductService.updateSaleGoodsProductOutboundNum(property.getOrderNo(),property.getProductNo(),num+"");
                        if(!(rows>0)){
                            System.err.println("修改销货单，出库数量失败！");
                        }
                    }
                }
                if(property.getId() == null || property.getId() == 0L){
                    try {
                        iStockOutSaleProductService.save(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    product.setId(property.getId());
                    try {
                        iStockOutSaleProductService.updateNotNull(product);
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
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(orderid.getText() != null && !"".equals(orderid.getText())){
                StockOutSale stockOutSale = iStockOutSaleService.selectByKey(Long.valueOf(orderid.getText()));
                if(stockOutSale !=  null){
                    int rows = iStockOutSaleService.delete(stockOutSale.getId());
                    iStockOutSaleProductService.deleteStockOutSaleProductByParentId(stockOutSale.getId().toString());
                    returnMsg(rows);
                    if(rows>0){
                        SaleGoods saleGoods = iSaleGoodsService.findBySaleNo(stockOutSale.getOutboundNo());
                        if(saleGoods != null){
                            saleGoods.setImportOutboundAudit(false);
                            iSaleGoodsService.updateNotNull(saleGoods);
                        }
                    }
                }
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
        matchingPermissions("销货出库单",menu_insert,menu_delete,menu_update,shiro_success,shiro_cancel,menu_printing,menu_clearAll);
        List<StockOutSale> stockOutSaleList = iStockOutSaleService.listStockOutSaleAll("",page, 1);
        if(stockOutSaleList != null && stockOutSaleList.size() >0){
            PageInfo<StockOutSale> pageInfo = new PageInfo<>(stockOutSaleList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            setBasicVal(stockOutSaleList.get(0));
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
    public void setBasicVal(StockOutSale order){
        if(order == null){
            clearControllerVal();
            return;
        }
        clearControllerVal();
        if(order.getId() != null){
            orderid.setText(order.getId().toString());
        }
        if(order.getCreateDate() != null){
            order_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(order.getCreateDate(),DateUtils.FORMAT_YYYY_MM_DD),formatter));
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
        if(order.getLastUpdate() != null){
            last_update.setText(order.getLastUpdate());
        }
        if(order.getLastUpdateStr() != null){
            last_update_str.setText(order.getLastUpdateStr());
        }
        if(order.getMadePeople() != null){
            made_people.setText(order.getMadePeople());
        }
        if(order.getAudit() != null){
            audit.setText(order.getAudit());
        }
        if(order.getAuditStr() != null){
            audit_str.setText(order.getAuditStr());
        }
        if(order.getOutboundNo() != null){
            order_no.setText(order.getOutboundNo());
        }
        if(order.getSaleNo() != null){
            sale_no.setText(order.getSaleNo());
        }
        if(order.getWarehouseIn() != null){
            warehouse_in.setText(order.getWarehouseIn());
        }
        if(order.getWarehouseInStr() != null){
            warehouse_in_str.setText(order.getWarehouseInStr());
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
        }else{
            setShiroControlNO();
            menu_update.setDisable(false);
        }

        setTableviewVal();
        setTableviewRemarkVal();
        setTableviewReportVal();
    }

    /**
     * 加载销货单数据
     * @param order
     */
    public void setSaleGoodsBasicVal(SaleGoods order){
        if(order == null){
            return;
        }
        clearControllerVal();
        add();
        if(order.getSaleNo() != null){
            sale_no.setText(order.getSaleNo());
        }
        if(order.getCustomerNo() != null){
            customer_no.setText(order.getCustomerNo());
        }
        if(order.getCustomerNoStr() != null){
            customer_no_str.setText(order.getCustomerNoStr());
        }
    }

    /**
     * 加载tableview数据
     */
    private void setTableviewVal(){

        if(orderid.getText() != null && !"".equals(orderid.getText())){
            List<StockOutSaleProduct> productList = iStockOutSaleProductService.listStockOutSaleProduct(orderid.getText());
            List<StockOutSaleProductProperty> productPropertyList = new ArrayList<>();
            if(productList != null){
                int rows = 1;
                for (StockOutSaleProduct p : productList) {
                    productPropertyList.add(new StockOutSaleProductProperty(p.getId(),rows++, p.getStockOutSaleId(),p.getOrderSource() ,p.getOrderNo(), p.getProductNo(), p.getProductName(), p.getCategory(), p.getNum(), p.getUnit()==null?null:p.getUnit().toString(), p.getPrice(), p.getWarehouseName(), p.getFloor() ,p.getRemark()));
                }
                generalProductTab(productPropertyList);
            }
        }
    }

    int selectIndex = 0;

    String productNum = "";

    /**
     * 加载销货单的产品信息
     * @param list
     */
    public void generalProductTab(List<StockOutSaleProductProperty> list){
        tableview.setItems(null);
        tableview.setEditable(true);
        col_source_order.setCellFactory(column -> EditCell.createStringEditCell());
        col_order_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_no.setCellFactory(column -> EditCell.createStringEditCell());
        col_product_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_category.setCellFactory(column -> EditCell.createStringEditCell());
        col_unit.setCellFactory(column -> EditCell.createStringEditCell());
        col_price.setCellFactory(column -> EditCell.createStringEditCell());
        col_num.setCellFactory(column -> EditCell.createStringEditCell());
        col_location_name.setCellFactory(column -> EditCell.createStringEditCell());
        col_floor.setCellFactory(column -> EditCell.createStringEditCell());
        col_remark.setCellFactory(column -> EditCell.createStringEditCell());
        Callback<TableColumn<StockOutSaleProductProperty, String>, TableCell<StockOutSaleProductProperty, String>> buttonFactory
                = new Callback<TableColumn<StockOutSaleProductProperty, String>, TableCell<StockOutSaleProductProperty, String>>() {
            @Override
            public TableCell call(final TableColumn<StockOutSaleProductProperty, String> param) {
                final TableCell<StockOutSaleProductProperty, String> cell = new TableCell<StockOutSaleProductProperty, String>() {

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
                                selectIndex = getTableRow().getIndex();

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

        final ObservableList<StockOutSaleProductProperty> dataProperty = FXCollections.observableArrayList(list);
        col_id.setCellValueFactory(new PropertyValueFactory<StockOutSaleProductProperty,Long>("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_source_order.setCellValueFactory(new PropertyValueFactory("orderSource"));
        col_order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_category.setCellValueFactory(new PropertyValueFactory("category"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_location_name.setCellValueFactory(new PropertyValueFactory("warehouseName"));
        col_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableview.setItems(dataProperty);
    }






    /**
     * 清除空间上的值
     */
    @FXML
    private void clearControllerVal(){

        orderid.clear();
        LocalDateTime localDate = LocalDateTime.now();
        order_date.setValue(localDate.toLocalDate());
        customer_no.clear();
        customer_no_str.clear();
        made_people.clear();
        order_no.clear();
        sale_no.clear();
        last_update_str.clear();
        last_update.clear();
        warehouse_in.clear();
        warehouse_in_str.clear();
        audit.clear();
        audit_str.clear();

        tableview.setItems(null);
        remark_table.setItems(null);
        report_table.setItems(null);
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

        // 作废按钮
        btn_invalid.setDisable(bool);

        order_date.setDisable(bool);
        customer_no.setDisable(bool);
        customer_no_str.setDisable(bool);
        sale_no.setDisable(bool);
        order_no.setDisable(true);
        made_people.setDisable(true);
        audit.setDisable(true);
        audit_str.setDisable(true);
        last_update.setDisable(true);
        last_update_str.setDisable(true);
        warehouse_in.setDisable(bool);
        warehouse_in_str.setDisable(bool);

        menu_clearAll.setDisable(bool);
        menu_commit.setDisable(bool);
        menu_delete.setDisable(bool);

        tableview.setDisable(bool);
        report_table.setDisable(bool);
        remark_table.setDisable(bool);
    }

    /**
     * 备注view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfRemarkTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addRemarkRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfRemark();
            setTableviewRemarkVal();
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
            addReportRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfReport();
            setTableviewReportVal();
        }
    }

    /**
     * 删除备注行
     */
    private void deleteRowOfRemark(){
        // 取得当前行的数据
        try {
            TablePosition pos = (TablePosition) remark_table.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            RemarkProperty property = (RemarkProperty)remark_table.getItems().get(index);
            if(property.getId() != null && property.getId() != 0L){
                int rows = iRemarkService.delete(property.getId());
                returnMsg(rows);
            }
        }catch (Exception e){
            alert_informationDialog("请选择需要删除的行！");
            e.printStackTrace();
        }
    }

    /**
     * 添加备注行
     */
    public void addRemarkRow() {

        ObservableList<RemarkProperty> list = remark_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new RemarkProperty(""));
        remark_table.setItems(list);
    }

    /**
     * 删除报表行
     */
    private void deleteRowOfReport(){
        // 取得当前行的数据
        try {
            TablePosition pos = (TablePosition) report_table.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            ReportRemarkProperty property = (ReportRemarkProperty)report_table.getItems().get(index);
            if(property.getId() != null && property.getId() != 0L){
                int rows = iReportRemarkService.delete(property.getId());
                returnMsg(rows);
            }
        }catch (Exception e){
            alert_informationDialog("请选择需要删除的行！");
            e.printStackTrace();
        }
    }

    /**
     * 添加报表行
     */
    public void addReportRow() {

        ObservableList<ReportRemarkProperty> list = report_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new ReportRemarkProperty(""));
        report_table.setItems(list);
    }

    /**
     * 加载备注tableview数据
     */
    private void setTableviewRemarkVal(){
        remark_table.setEditable(true);

        col_remark_content.setCellFactory(column -> EditCell.createStringEditCell());

        if(orderid.getText() != null && !"".equals(orderid.getText())){
            List<Remark> productList = iRemarkService.listRemark(Long.valueOf(orderid.getText()),"5");
            List<RemarkProperty> productPropertyList = new ArrayList<>();
            productList.forEach(p->{
                productPropertyList.add(new RemarkProperty(p.getId(),p.getRemark()));
            });
            final ObservableList<RemarkProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_remark_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_remark_content.setCellValueFactory(new PropertyValueFactory("remark"));

            remark_table.setItems(dataProperty);
        }
    }

    /**
     * 加载报表tableview数据
     */
    private void setTableviewReportVal(){
        report_table.setEditable(true);

        col_report_content.setCellFactory(column -> EditCell.createStringEditCell());

        if(orderid.getText() != null && !"".equals(orderid.getText())){
            List<ReportRemark> productList = iReportRemarkService.listReportRemark(Long.valueOf(orderid.getText()),"4");
            List<ReportRemarkProperty> productPropertyList = new ArrayList<>();
            productList.forEach(p->{
                productPropertyList.add(new ReportRemarkProperty(p.getId(),p.getContent()));
            });
            final ObservableList<ReportRemarkProperty> dataProperty = FXCollections.observableArrayList(productPropertyList);
            col_report_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_report_content.setCellValueFactory(new PropertyValueFactory("content"));

            report_table.setItems(dataProperty);
        }
    }

    /**
     * 保存 备注tableview数据
     */
    private void saveTableviewRemark(){
        String id = orderid.getText();
        if(id != null && !"".equals(id) && remark_table.getItems() != null){
            int tableSize = remark_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                RemarkProperty property = null;
                if(remark_table.getItems().get(i) != null){
                    property = (RemarkProperty)remark_table.getItems().get(i);
                }
                Remark product = new Remark();
                if(property.getRemark() != null && !"".equals(property.getRemark())){
                    product.setRemark(property.getRemark());
                }
                product.setTypeid(5);
                product.setOtherid(Long.valueOf(id));
                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iRemarkService.save(product);
                        setTableviewVal();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iRemarkService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 保存 报表tableview数据
     */
    private void saveTableviewReport(){
        String id = orderid.getText();
        if(id != null && !"".equals(id) && report_table.getItems() != null){
            int tableSize = report_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                ReportRemarkProperty property = null;
                if(report_table.getItems().get(i) != null){
                    property = (ReportRemarkProperty)report_table.getItems().get(i);
                }
                ReportRemark product = new ReportRemark();
                if(property.getContent() != null && !"".equals(property.getContent())){
                    product.setContent(property.getContent());
                }
                product.setTypeid(4);
                product.setOtherid(Long.valueOf(id));

                if(property.getId() == null){
                    try {
                        product.setAddtime(new Date());
                        iReportRemarkService.save(product);
                        setTableviewVal();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    try {
                        product.setId(property.getId());
                        iReportRemarkService.updateNotNull(product);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 打开订单查询窗口
     */
    @FXML
    public void OpenMiniQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Pane pane = new Pane();
        stage.setTitle("现有销售出库单");
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleOutboundOrderControllerMini", this);
        pane.getChildren().setAll(loader.load("/fxml/stock/sale_outbound_query_mini.fxml"));
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
        stage.setTitle("现有客户查询");
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleOutboundOrderController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

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
        if(tableview.getItems() != null){
            productNum = ((StockOutSaleProductProperty)tableview.getItems().get(index)).getProductNo();
        }
        StageManager.CONTROLLER.put("productNum", productNum);
        StageManager.CONTROLLER.put("SaleOutboundOrderControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/storehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * 导入 销货单
     */
    @FXML
    public void importIn(){
        stage.setTitle("导入-销货单");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleOutboundOrderControllerImport", this);
        pane.getChildren().setAll(loader.load("/fxml/sale/sale_goods_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }



    /**
     * 现有仓库查询
     */
    @FXML
    public void moreSaleOutboundHouseClick(){
        stage.setTitle("现有仓库查询");
        Pane pane = new Pane();
        StageManager.CONTROLLER.put("SaleOutboundOrderControllerWarehouse", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/warehouse_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }





    /**
     * 判断库存是否充足
     * @return
     */
    public boolean updateProductStock(boolean falg){

        if(orderid.getText() != null && !"".equals(orderid.getText())){
            List<StockOutSaleProduct> stockOutSaleProducts = iStockOutSaleProductService.listStockOutSaleProduct(orderid.getText());

            for (StockOutSaleProduct stockOutSaleProduct:stockOutSaleProducts) {

                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(stockOutSaleProduct.getWarehouseName(),stockOutSaleProduct.getProductNo());

                if(productStock != null){

                    int  productNum = productStock.getUsablenum();

                    int nowProductNum = stockOutSaleProduct.getNum();

                    if(nowProductNum > productNum && falg){
                        //库存不足
                        alert_informationDialog("产品"+productStock.getProductorder()+"库存不足审核失败");
                        return false;
                    }else{
                       if(falg){
                           //审核
                           productStock.setUsablenum(productStock.getUsablenum() - nowProductNum);
                           productStock.setStocknum(productStock.getStocknum() - nowProductNum);
//                           List<PurchaseStockProduct> productList = iPurchaseStockProductService.listNotOutboundPurchaseStockProduct(stockOutSaleProduct.getProductNo());
//                           if(productList != null && productList.size() >0){
//                               for (PurchaseStockProduct p : productList) {
//                                   // 未出库完
//                                   if(nowProductNum > 0){
//                                       // 入库数量 - 出库数量 > 出库数量
//                                       if((p.getStocknum() - p.getOutnum()) > nowProductNum){
//                                           p.setOutnum(p.getOutnum()+nowProductNum);
//                                           nowProductNum = 0;
//                                       }else{
//                                           nowProductNum -= p.getStocknum() - p.getOutnum();
//                                           p.setOutnum(p.getStocknum());
//                                       }
//                                       iPurchaseStockProductService.updateNotNull(p);
//                                   }
//                               }
//                           }
                       }else{
                           //取消审核
                           productStock.setUsablenum(productStock.getUsablenum() + nowProductNum);
                           productStock.setStocknum(productStock.getStocknum() + nowProductNum);
                       }
                        productStockService.updateNotNull(productStock);
                        return true;
                    }



                }


            }

        }
        return false;
    }


}
