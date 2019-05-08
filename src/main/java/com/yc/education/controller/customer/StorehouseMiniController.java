package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.sale.PurchaseOrderController;
import com.yc.education.controller.sale.SaleGoodsController;
import com.yc.education.controller.sale.SaleReturnController;
import com.yc.education.controller.stock.SaleOutboundOrderController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.sale.SaleGoodsProductProperty;
import com.yc.education.model.sale.SalePurchaseOrder;
import com.yc.education.model.sale.SalePurchaseOrderProductProperty;
import com.yc.education.model.sale.SaleReturnGoodsProductProperty;
import com.yc.education.model.stock.StockOutSaleProductProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.ProductBasicService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 仓库库位查询，表格中的库位选择
 * @Author BlueSky
 * @Date 14:19 2019/4/22
 **/
@Controller
public class StorehouseMiniController extends BaseController implements Initializable {
    @Autowired DataSettingService iDataSettingService;
    @Autowired ProductBasicService iProductBasicService;
    @Autowired DepotBasicService iDepotBasicService;

    // 表格现有库位查询
    @FXML private VBox depot_find_fast;
    @FXML private VBox depot_find_prev;
    @FXML private VBox depot_find_next;
    @FXML private VBox depot_find_last;

    @FXML CheckBox che_recently; //显示最近
    @FXML private TextField depot_textField_pageSize;

    @FXML private TextField depot_order_textField;

    @FXML private TableView houseView; //仓库库位
    @FXML private TableColumn findhousenum; //  库位编号
    @FXML private TableColumn findhousename; //  库位名称
    @FXML private TableColumn findparent; //  所属仓库
    @FXML private TableColumn findhousefloor; //  楼层

    int pageSize = 10;
    String depotId = ""; // 仓库id
    String productNum = "";  //产品编号

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productNum = (String)StageManager.CONTROLLER.get("productNum");
        loadMoreHouse(0,1);
    }



    /**
     * @Description 库位查询规则： 只查询当前产品下的库位
     * @Author BlueSky
     * @param status 0：查全部、1：根据仓库id查库位
     * @param pageNum 页数
     * @Date 10:20 2019/4/25
     **/
    public void loadMoreHouse(int status,int pageNum){

        List<DepotBasic> depotBasicList = new ArrayList<>();

        if(status == 0){
            if(!"".equals(productNum)){
                ProductBasic productBasic = iProductBasicService.selectProductBasicByIsnum(productNum);
                if(productBasic != null){
                    String[] storehouse = productBasic.getInventoryplace().split(",");
                    for (int i=0,len=storehouse.length;i<len;i++){
                        DepotBasic depotBasic = iDepotBasicService.selectDepotBasicByIsnum(storehouse[i],"".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
                        if(depotBasic != null){
                            depotBasicList.add(depotBasic);
                        }
                    }
                }else{
                    depotBasicList = iDepotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
                }
            }else{
                depotBasicList = iDepotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
            }
        }else{
            if(depotId != null && !"".equals(depotId)){
                depotBasicList = iDepotBasicService.selectDepotBasicByParentId(Long.valueOf(depotId),"",pageNum,pageSize);
            }else{
                depotBasicList = iDepotBasicService.selectDepotBasic("",pageNum,pageSize);
            }
        }
        loadData(depotBasicList);
    }

    /**
     * @Description 加载数据
     * @Author BlueSky
     * @Date 10:17 2019/4/25
     **/
    public void loadData(List<DepotBasic> depotBasicList){
        PageInfo<DepotBasic> pageInfo = new PageInfo<>(depotBasicList);

        depot_find_fast.setUserData(1); //首页

        depot_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        depot_find_next.setUserData(pageInfo.getNextPage());//下一页

        depot_find_last.setUserData(pageInfo.getPages());//尾页


        ObservableList<DepotBasic> list = FXCollections.observableArrayList();

        findhousenum.setCellValueFactory(new PropertyValueFactory("isnum"));
        findhousename.setCellValueFactory(new PropertyValueFactory("depname"));
        findparent.setCellValueFactory(new PropertyValueFactory("remarks"));
        findhousefloor.setCellValueFactory(new PropertyValueFactory("depfloor"));


        if(depotBasicList.size() > 0){
            for (DepotBasic depotBasic:depotBasicList) {

                DataSetting dataSetting =  iDataSettingService.selectByKey(depotBasic.getParentid());

                if(dataSetting != null){depotBasic.setRemarks(dataSetting.getTitle());}

                list.add(depotBasic);

            }
        }

        houseView.setItems(list); //tableview添加list

        houseView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DepotBasic>() {
            @Override
            public void changed(ObservableValue<? extends DepotBasic> observableValue, DepotBasic oldItem, DepotBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    houseView.setUserData(newItem.getId());
                }
            }
        });


        houseView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeHouseWin();
            }
        });
    }


    /**
     * @Description 分页
     * @Author BlueSky
     * @Date 14:27 2019/4/22
     **/
    public void findDepotPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreHouse(1,pageNum);
    }

    /**
     * @Description 查找
     * @Author BlueSky
     * @Date 14:27 2019/4/22
     **/
    @FXML
    public void findDeoptSearch(){

        if(che_recently.isSelected()){
            String pageSizes =  depot_textField_pageSize.getText();

            if("".equals(pageSizes) || pageSizes == null  ){
                alert_informationDialog("请输入页码数!");
            }else{
                pageSize = Integer.parseInt(pageSizes);
                loadMoreHouse(1,1);
            }
        }
    }


    /**
     * @Description 关闭窗口并把选中数据带回
     * @Author BlueSky
     * @Date 14:35 2019/4/22
     **/
    public void closeHouseWin(){

        long id =(long)houseView.getUserData();

        DepotBasic depotBasic =  iDepotBasicService.selectByKey(id);

        if(depotBasic != null){
            Integer index = (Integer)StageManager.CONTROLLER.get("index");

            // 库存 - 销货出库单
            SaleOutboundOrderController saleOutboundOrderController = (SaleOutboundOrderController)StageManager.CONTROLLER.get("SaleOutboundOrderControllerWarehouse");
            if(saleOutboundOrderController != null && index != null){
                ObservableList<StockOutSaleProductProperty> propertyList = saleOutboundOrderController.tableview.getItems()==null?FXCollections.observableArrayList():saleOutboundOrderController.tableview.getItems();
                if(propertyList != null && propertyList.get(index) != null){
                    propertyList.get(index).setFloor(depotBasic.getDepfloor());
                    propertyList.get(index).setWarehouseName(depotBasic.getIsnum());
                }
            }

            // 销售 - 订货单
            PurchaseOrderController salePurchaseOrderController = (PurchaseOrderController)StageManager.CONTROLLER.get("PurchaseOrderControllerWarehouse");
            if(salePurchaseOrderController != null && index != null){
                ObservableList<SalePurchaseOrderProductProperty> propertyList = salePurchaseOrderController.product_table.getItems()==null?FXCollections.observableArrayList():salePurchaseOrderController.product_table.getItems();
                if(propertyList != null && propertyList.get(index) != null){
                    propertyList.get(index).setFloor(depotBasic.getDepfloor());
                    propertyList.get(index).setWarehousePosition(depotBasic.getIsnum());
                }
            }

            // 销售 - 销货单
            SaleGoodsController saleGoodsController = (SaleGoodsController)StageManager.CONTROLLER.get("SaleGoodsControllerWarehouse");
            if(saleGoodsController != null && index != null){
                ObservableList<SaleGoodsProductProperty> propertyList = saleGoodsController.product_table.getItems()==null?FXCollections.observableArrayList():saleGoodsController.product_table.getItems();
                if(propertyList != null && propertyList.get(index) != null){
                    propertyList.get(index).setFloor(depotBasic.getDepfloor());
                    propertyList.get(index).setWarehousePosition(depotBasic.getIsnum());
                }
            }

            // 销售 - 销售退货单
            SaleReturnController saleReturnController = (SaleReturnController)StageManager.CONTROLLER.get("SaleReturnControllerWarehouse");
            if(saleReturnController != null && index != null){
                ObservableList<SaleReturnGoodsProductProperty> propertyList = saleReturnController.tab_product.getItems()==null?FXCollections.observableArrayList():saleReturnController.tab_product.getItems();
                if(propertyList != null && propertyList.get(index) != null){
                    propertyList.get(index).setFloor(depotBasic.getDepfloor());
                    propertyList.get(index).setWarehousePosition(depotBasic.getIsnum());
                }
            }
            // depotBasic.getDepname()
        }

        closeWin();
    }

    /**
     * @Description 关闭当前窗体
     * @Author BlueSky
     * @Date 14:30 2019/4/22
     **/
    @FXML
    public void closeWin(){
        Stage stage=(Stage)houseView.getScene().getWindow();
        depotId = "";
        StageManager.CONTROLLER.remove("index");
        StageManager.CONTROLLER.remove("depotId");
        StageManager.CONTROLLER.remove("SaleOutboundOrderControllerWarehouse");
        StageManager.CONTROLLER.remove("PurchaseOrderControllerWarehouse");
        StageManager.CONTROLLER.remove("SaleGoodsControllerWarehouse");
        StageManager.CONTROLLER.remove("SaleReturnControllerWarehouse");

        stage.close();
    }


}
