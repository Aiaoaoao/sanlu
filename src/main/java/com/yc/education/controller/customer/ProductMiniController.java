package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.account.QueryAccountSaleCostController;
import com.yc.education.controller.account.QueryInventoryCostController;
import com.yc.education.controller.sale.*;
import com.yc.education.model.DataSetting;
import com.yc.education.model.ProductStock;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.customer.CustomerDemandGoodsInfoProperty;
import com.yc.education.model.sale.*;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 现有产品查询
 * @Author BlueSky
 * @Date 2018-12-11 14:20
 */
@Controller
public class ProductMiniController extends BaseController implements Initializable {
    @Autowired
    private ProductBasicService iProductBasicService; //产品 Service
    @Autowired
    private DataSettingService iDataSettingService; // 基本资料
    @Autowired
    private ProductStockService iProductStockService; //产品库存 Service
    @Autowired
    PurchaseStockProductService iPurchaseStockProductService; //采购入库单
    @Autowired DepotBasicService iDepotBasicService;



    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;    //显示最近
    @FXML CheckBox che_stop_compay; //显示暂停来往公司
    @FXML TextField num;       //最近多少笔

    /***************************************** 弹出窗口-产品 ********************************************/
    @FXML
    private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注
    int tablePosition = 0; // 弹出窗口选中的行数

    /***************************************** 弹出窗口-产品-结束 ********************************************/

    /**
     * @Description 查找
     * @Author BlueSky
     * @Date 16:04 2019/4/15
     **/
    @FXML
    public void textQuery(){
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        int rows = pageRows(che_recently,num);

        List<ProductBasic> list = iProductBasicService.selectProductBasic("",page, rows);
        if(list != null && list.size() >0){
            PageInfo<ProductBasic> pageInfo = new PageInfo<>(list);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadMoreProduct(list);
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
     * @Description 加载产品
     * @Author BlueSky
     * @Date 16:00 2019/4/22
     **/
    public void loadMoreProduct(List<ProductBasic> productBasics){
        List<DataSetting> dataSettingList = iDataSettingService.findDataSetting(6L);
        if(productBasics != null && dataSettingList != null){
            productBasics.forEach(p->{
                if(p.getProtype()!=null){
                    for (int i = 0; i < dataSettingList.size(); i++) {
                        if(p.getProtype().toString().equals(dataSettingList.get(i).getSort()==null?"":dataSettingList.get(i).getSort().toString())){
                            p.setProtypeStr(dataSettingList.get(i).getTitle());
                            break;
                        }
                    }
                }
            });
        }

        ObservableList<ProductBasic> list = FXCollections.observableArrayList();

        tableViewProduct.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

        depid.setCellValueFactory(new PropertyValueFactory("id"));
        findproductid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findproductname.setCellValueFactory(new PropertyValueFactory("proname"));
        findprotype.setCellValueFactory(new PropertyValueFactory("protypeStr"));
        findnormprice.setCellValueFactory(new PropertyValueFactory("normprice"));
        findlowprice.setCellValueFactory(new PropertyValueFactory("lowprice"));
        findsafetystock.setCellValueFactory(new PropertyValueFactory("safetystock"));
        findremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (ProductBasic productBasic:productBasics) {
            list.add(productBasic);
        }

        tableViewProduct.setItems(list); //tableview添加list

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductBasic>() {
            @Override
            public void changed(ObservableValue<? extends ProductBasic> observableValue, ProductBasic oldItem, ProductBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    tableViewProduct.setUserData(newItem.getId());
                    depid.setUserData(newItem.getIsnum());
                }
            }
        });
        tableViewProduct.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }

    /**
     * 确认关闭
     */
    @FXML
    @SneakyThrows
    public void closeSureWin(){
        try{
            tablePosition =  StageManager.CONTROLLER.get("tablePosition")==null?0:(int)StageManager.CONTROLLER.get("tablePosition");
        }catch (Exception e){
            e.printStackTrace();
        }
        long productid =(long)tableViewProduct.getUserData();
        ProductBasic    productBasic =  iProductBasicService.selectByKey(productid);
        boolean place = true;
        if(productBasic.getInventoryplace() != null && !"".equals(productBasic.getInventoryplace()) && productBasic.getInventoryplace().split(",").length > 1){
            place = false;
        }
        if(productBasic != null){
            // 客户--客户需求商品
            CustomerDemandGoodsController customerDemandGoodsController = (CustomerDemandGoodsController) StageManager.CONTROLLER.get("CustomerDemandGoodsControllerProduct");
            if(customerDemandGoodsController != null){
                // 把数据绑到当前选中的行中
                ObservableList<CustomerDemandGoodsInfoProperty> list = customerDemandGoodsController.customer_demand_goods_table.getItems();
                if (list == null) {
                    list = FXCollections.observableArrayList();
                }
                List<DataSetting> dataSettingList = iDataSettingService.findDataSetting(8L); // 产品性质
                List<DataSetting> dataSettingListBig = iDataSettingService.findDataSetting(6L); // 产品大类
                List<DataSetting> dataSettingListUnit = iDataSettingService.findDataSetting(5L); // 基本单位
                for (int i = 0; i < list.size(); i++) {
                    if(tablePosition == i){
                        list.get(i).setProductName(productBasic.getProname());
                        list.get(i).setProductNo(productBasic.getIsnum());
                        list.get(i).setPrice(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setQuantityDemand(productBasic.getMinnum()==null?"1":productBasic.getMinnum().toString());
                        if(dataSettingList != null && productBasic.getPronature() != null){
                            for (int i1 = 1; i1 < dataSettingList.size(); i1++) {
                                if((i1+"").equals(productBasic.getPronature().toString())){
                                    list.get(i).setProductNature(dataSettingList.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                        if(dataSettingListBig != null && productBasic.getProtype() != null){
                            for (int i1 = 1; i1 < dataSettingListBig.size(); i1++) {
                                if((i1+"").equals(productBasic.getProtype().toString())){
                                    list.get(i).setProductCategories(dataSettingListBig.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                        if(dataSettingListUnit != null && productBasic.getBasicunit() != null){
                            for (int i1 = 1; i1 < dataSettingListUnit.size(); i1++) {
                                if((i1+"").equals(productBasic.getBasicunit().toString())){
                                    list.get(i).setUnit(dataSettingListUnit.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                    }
                }
                customerDemandGoodsController.customer_demand_goods_table.setItems(FXCollections.observableArrayList(list));
            }

            // 销售 -- 报价单
            QuotationController quotationController = (QuotationController) StageManager.CONTROLLER.get("QuotationControllerProduct");
            if(quotationController != null){
                // 把数据绑到当前选中的行中
                // todo...
                ObservableList<SaleOfferProductProperty> list = quotationController.offer_table.getItems();
                if (list == null) {
                    list = FXCollections.observableArrayList();
                }
                List<DataSetting> dataSettingListBig = iDataSettingService.findDataSetting(6L); // 产品大类
                List<DataSetting> dataSettingListUnit = iDataSettingService.findDataSetting(5L); // 基本单位
                for (int i = 0; i < list.size(); i++) {
                    if(tablePosition == i){
                        list.get(i).setProductName(productBasic.getProname());
                        list.get(i).setProductNo(productBasic.getIsnum());
                        list.get(i).setPricing(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setPrice(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setNum("0");
                        list.get(i).setMoney("0");
                        // 产品类型
                        if(dataSettingListBig != null && productBasic.getProtype() != null){
                            for (int i1 = 1; i1 < dataSettingListBig.size(); i1++) {
                                if((i1+"").equals(productBasic.getProtype().toString())){
                                    list.get(i).setCategory(dataSettingListBig.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                        // 产品单位
                        if(dataSettingListUnit != null && productBasic.getBasicunit() != null){
                            for (int i1 = 1; i1 < dataSettingListUnit.size(); i1++) {
                                if((i1+"").equals(productBasic.getBasicunit().toString())){
                                    list.get(i).setUnit(dataSettingListUnit.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                    }
                }
                quotationController.offer_table.setItems(FXCollections.observableArrayList(list));
            }

            // 销售 -- 网上订单
            OnlineOrderController onlineOrderController = (OnlineOrderController) StageManager.CONTROLLER.get("OnlineOrderControllerProduct");
            if(onlineOrderController != null){
                // 把数据绑到当前选中的行中
                ObservableList<SaleOnlineOrderProductProperty> list = onlineOrderController.tableview.getItems();
                if (list == null) {
                    list = FXCollections.observableArrayList();
                }
                List<DataSetting> dataSettingListBig = iDataSettingService.findDataSetting(6L); // 产品大类
                List<DataSetting> dataSettingListUnit = iDataSettingService.findDataSetting(5L); // 基本单位
                for (int i = 0; i < list.size(); i++) {
                    if(tablePosition == i){
                        list.get(i).setProductName(productBasic.getProname());
                        list.get(i).setProductNo(productBasic.getIsnum());
                        list.get(i).setPrice(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setNum("0");
                        list.get(i).setMoney("0");
                        ProductStock productStock = iProductStockService.findProductStockReturnNum(productBasic.getIsnum());
                        if(productStock != null){
                            list.get(i).setUsableNum(productStock.getUsablenum()==null?"0":productStock.getUsablenum().toString());
                            list.get(i).setIfstock(productStock.getUsablenum()==null?"否":(productStock.getUsablenum()>0?"是":"否"));
                        }else {
                            list.get(i).setUsableNum("0");
                            list.get(i).setIfstock("否");
                        }
                        if(dataSettingListBig != null && productBasic.getProtype() != null){
                            for (int i1 = 1; i1 < dataSettingListBig.size(); i1++) {
                                if((i1+"").equals(productBasic.getProtype().toString())){
                                    list.get(i).setCategory(dataSettingListBig.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                        if(dataSettingListUnit != null && productBasic.getBasicunit() != null){
                            for (int i1 = 1; i1 < dataSettingListUnit.size(); i1++) {
                                if((i1+"").equals(productBasic.getBasicunit().toString())){
                                    list.get(i).setUnit(dataSettingListUnit.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                    }
                }
                onlineOrderController.tableview.setItems(FXCollections.observableArrayList(list));
            }

            // 销售 -- 采购单
            PurchaseOrderController purchaseOrderController = (PurchaseOrderController) StageManager.CONTROLLER.get("PurchaseOrderControllerProduct");
            if(purchaseOrderController != null){
                // 把数据绑到当前选中的行中
                ObservableList<SalePurchaseOrderProductProperty> list = purchaseOrderController.product_table.getItems();
                if (list == null) {
                    list = FXCollections.observableArrayList();
                }
                List<DataSetting> dataSettingListBig = iDataSettingService.findDataSetting(6L); // 产品大类
                List<DataSetting> dataSettingListUnit = iDataSettingService.findDataSetting(5L); // 基本单位
                for (int i = 0; i < list.size(); i++) {
                    if(tablePosition == i){
                        // 只有一个库位的话就带过去，多个库位需要手动选择
                        if(place){
                            DepotBasic depotBasic = iDepotBasicService.selectDepotBasicByIsnum(productBasic.getInventoryplace());
                            if(depotBasic != null){
                                list.get(i).setWarehousePosition(productBasic.getInventoryplace());
                                list.get(i).setFloor(depotBasic.getDepfloor());
                            }
                        }else{
                            list.get(i).setWarehousePosition("");
                            list.get(i).setFloor("");
                        }
                        list.get(i).setProductName(productBasic.getProname());
                        list.get(i).setProductNo(productBasic.getIsnum());
                        list.get(i).setPricing(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setPrice(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setNum("0");
                        list.get(i).setMoney("0");
                        if(dataSettingListBig != null && productBasic.getProtype() != null){
                            for (int i1 = 1; i1 < dataSettingListBig.size(); i1++) {
                                if((i1+"").equals(productBasic.getProtype().toString())){
                                    list.get(i).setCategory(dataSettingListBig.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                        if(dataSettingListUnit != null && productBasic.getBasicunit() != null){
                            for (int i1 = 1; i1 < dataSettingListUnit.size(); i1++) {
                                if((i1+"").equals(productBasic.getBasicunit().toString())){
                                    list.get(i).setUnit(dataSettingListUnit.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                    }
                }
                purchaseOrderController.product_table.setItems(FXCollections.observableArrayList(list));
            }

            // 销售 -- 销货单
            SaleGoodsController saleGoodsController = (SaleGoodsController) StageManager.CONTROLLER.get("SaleGoodsControllerProduct");
            if(saleGoodsController != null){
                // 把数据绑到当前选中的行中
                ObservableList<SaleGoodsProductProperty> list = saleGoodsController.product_table.getItems();
                if (list == null) {
                    list = FXCollections.observableArrayList();
                }
                List<DataSetting> dataSettingListBig = iDataSettingService.findDataSetting(6L); // 产品大类
                List<DataSetting> dataSettingListUnit = iDataSettingService.findDataSetting(5L); // 基本单位
                for (int i = 0; i < list.size(); i++) {
                    if(tablePosition == i){
//                        List<String> stringList = iPurchaseStockProductService.listPurchaseStockProductMoreStockByproductNo(list.get(i).getProductNo());
//                        if(stringList != null && stringList.size() >1){
//                            list.get(i).setWarehousePosition("");
//                            list.get(i).setFloor("");
//                        }else{
//                            PurchaseStockProduct purchaseStockProduct = iPurchaseStockProductService.getProductAddressByProductNo(list.get(i).getProductNo());
//                            if(purchaseStockProduct != null){
//                                list.get(i).setWarehousePosition(purchaseStockProduct.getDepotnum());
//                                list.get(i).setFloor(purchaseStockProduct.getFloor());
//                            }
//                        }
                        // 只有一个库位的话就带过去，多个库位需要手动选择
                        if(place){
                            DepotBasic depotBasic = iDepotBasicService.selectDepotBasicByIsnum(productBasic.getInventoryplace());
                            if(depotBasic != null){
                                list.get(i).setWarehousePosition(productBasic.getInventoryplace());
                                list.get(i).setFloor(depotBasic.getDepfloor());
                            }
                        }else{
                            list.get(i).setWarehousePosition("");
                            list.get(i).setFloor("");
                        }
                        // 产品重量不为空
                        if(productBasic.getWeights() != null && !"".equals(productBasic.getWeights())){
                            list.get(i).setWeight(productBasic.getWeights()+"");
                        }
                        list.get(i).setProductName(productBasic.getProname());
                        list.get(i).setProductNo(productBasic.getIsnum());
                        list.get(i).setPricing(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setPrice(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setNum("0");
                        list.get(i).setMoney("0");
                        if(dataSettingListBig != null && productBasic.getProtype() != null){
                            for (int i1 = 1; i1 < dataSettingListBig.size(); i1++) {
                                if((i1+"").equals(productBasic.getProtype().toString())){
                                    list.get(i).setCategory(dataSettingListBig.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                        if(dataSettingListUnit != null && productBasic.getBasicunit() != null){
                            for (int i1 = 1; i1 < dataSettingListUnit.size(); i1++) {
                                if((i1+"").equals(productBasic.getBasicunit().toString())){
                                    list.get(i).setUnit(dataSettingListUnit.get(i1-1).getTitle());
                                    break;
                                }
                            }
                        }
                    }
                }
                saleGoodsController.product_table.setItems(FXCollections.observableArrayList(list));
            }

            // 销售 -- 销售退货单
            SaleReturnController saleReturnController = (SaleReturnController) StageManager.CONTROLLER.get("SaleReturnControllerProduct");
            if(saleReturnController != null){
                // 把数据绑到当前选中的行中
                ObservableList<SaleReturnGoodsProductProperty> list = saleReturnController.tab_product.getItems();
                if (list == null) {
                    list = FXCollections.observableArrayList();
                }
                for (int i = 0; i < list.size(); i++) {
                    if(tablePosition == i){
                        list.get(i).setProductName(productBasic.getProname());
                        list.get(i).setProductNo(productBasic.getIsnum());
                        list.get(i).setPricing(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                        list.get(i).setPrice(productBasic.getNormprice()==null?"0.00":productBasic.getNormprice().toString());
                    }
                }
                saleReturnController.tab_product.setItems(FXCollections.observableArrayList(list));
            }
        }
        // 账款 -- 销项成本明细
        QueryAccountSaleCostController queryAccountSaleCostControllerProduct = (QueryAccountSaleCostController) StageManager.CONTROLLER.get("AccountSaleCostControllerProduct");
        if(queryAccountSaleCostControllerProduct != null){
            String productNum = depid.getUserData()==null?null:depid.getUserData().toString();
            queryAccountSaleCostControllerProduct.product_no.setText(productNum);
        }
        // 账款 -- 销项成本明细
        QueryAccountSaleCostController queryAccountSaleCostControllerProductEnd = (QueryAccountSaleCostController) StageManager.CONTROLLER.get("AccountSaleCostControllerProductEnd");
        if(queryAccountSaleCostControllerProductEnd != null){
            String productNum = depid.getUserData()==null?null:depid.getUserData().toString();
            queryAccountSaleCostControllerProductEnd.product_no_end.setText(productNum);
        }

        // 账款 -- 库存成本查询
        QueryInventoryCostController queryInventoryCostControllerProduct = (QueryInventoryCostController) StageManager.CONTROLLER.get("QueryInventoryCostControllerProduct");
        if(queryInventoryCostControllerProduct != null){
            String productNum = depid.getUserData()==null?null:depid.getUserData().toString();
            queryInventoryCostControllerProduct.product_no.setText(productNum);
        }
        // 账款 -- 库存成本查询
        QueryInventoryCostController queryInventoryCostControllerProductEnd = (QueryInventoryCostController) StageManager.CONTROLLER.get("QueryInventoryCostControllerProductEnd");
        if(queryInventoryCostControllerProductEnd != null){
            String productNum = depid.getUserData()==null?null:depid.getUserData().toString();
            queryInventoryCostControllerProductEnd.product_no_end.setText(productNum);
        }

        colseWin(); // 最后关闭此窗口
    }

    @FXML
    public void colseWin(){
        StageManager.CONTROLLER.remove("tablePosition");
        StageManager.CONTROLLER.remove("CustomerDemandGoodsControllerProduct");
        StageManager.CONTROLLER.remove("QuotationControllerProduct");
        StageManager.CONTROLLER.remove("OnlineOrderControllerProduct");
        StageManager.CONTROLLER.remove("PurchaseOrderControllerProduct");
        StageManager.CONTROLLER.remove("SaleGoodsControllerProduct");
        StageManager.CONTROLLER.remove("SaleReturnControllerProduct");
        StageManager.CONTROLLER.remove("AccountSaleCostControllerProduct");
        StageManager.CONTROLLER.remove("AccountSaleCostControllerProductEnd");
        StageManager.CONTROLLER.remove("QueryInventoryCostControllerProduct");
        StageManager.CONTROLLER.remove("QueryInventoryCostControllerProductEnd");
        Stage stage=(Stage)tableViewProduct.getScene().getWindow();
        stage.close();
        tablePosition = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }
}
