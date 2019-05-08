package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.account.AccountInputInvoiceController;
import com.yc.education.controller.account.AccountPayableController;
import com.yc.education.controller.account.AccountPrepaymentController;
import com.yc.education.controller.sale.PurchaseOrderController;
import com.yc.education.controller.sale.SaleGoodsController;
import com.yc.education.controller.sale.SaleReturnController;
import com.yc.education.controller.stock.SaleOutboundOrderController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.model.sale.SalePurchaseOrder;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.basic.SupplierBasicService;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 仓库查询（出库仓库、入库仓库）
 * @Author BlueSky
 * @Date 2019-01-29 16:03
 */
@Controller
public class WarehouseMiniController extends BaseController implements Initializable {


    @Autowired DataSettingService iDataSettingService;

    // 出库仓库 表格
    private int pageSize = 10;
    @FXML private VBox dataSetting_find_fast;
    @FXML private VBox dataSetting_find_prev;
    @FXML private VBox dataSetting_find_next;
    @FXML private VBox dataSetting_find_last;

    @FXML private TextField dataSetting_textField_pageSize;

    @FXML private TextField dataSetting_order_textField;

    /**
     * 仓库库位
     */
    @FXML private TableView depotView;

    /**
     * 编号
     */
    @FXML private TableColumn depid;
    /**
     * 仓库编号
     */
    @FXML private TableColumn finddepotid;

    /**
     * 仓库名称
     */
    @FXML private TableColumn finddepotname;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMoreDepartment(1);
    }

    /** 查找
     * @Description
     * @Author BlueSky
     * @Date 9:58 2019/4/24
     **/
    public void findDataSettingSearch(){
        String pageSizes =  dataSetting_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreDepartment(1);
        }

    }


    /**
     * @Description 分页
     * @Author BlueSky
     * @Date 9:58 2019/4/24
     **/
    public void findDataSettingPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreDepartment(pageNum);
    }


    /**
     * @Description 加载现有库位查询
     * @Author BlueSky
     * @Date 9:57 2019/4/24
     **/
    public void loadMoreDepartment(int pageNum){


        List<DataSetting> dataSettings = iDataSettingService.findDataSetting(33L,"".equals(dataSetting_order_textField.getText()) || dataSetting_order_textField.getText() ==  null ? "" :dataSetting_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<DataSetting> pageInfo = new PageInfo<>(dataSettings);

        dataSetting_find_fast.setUserData(1); //首页

        dataSetting_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        dataSetting_find_next.setUserData(pageInfo.getNextPage());//下一页

        dataSetting_find_last.setUserData(pageInfo.getPages());//尾页


        ObservableList<DataSetting> list = FXCollections.observableArrayList();


        finddepotid.setCellValueFactory(new PropertyValueFactory("id"));
        finddepotname.setCellValueFactory(new PropertyValueFactory("title"));

        for (DataSetting dataSetting:dataSettings) {

            list.add(dataSetting);

        }
        //tableview添加list
        depotView.setItems(list);

        depotView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DataSetting>() {
            @Override
            public void changed(ObservableValue<? extends DataSetting> observableValue, DataSetting oldItem, DataSetting newItem) {
                if(newItem.getSort()!= null && !("".equals(newItem.getSort()))){
                    depotView.setUserData(newItem.getId());
                }
            }
        });


        depotView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }




    /**
     * @Description 关闭窗体并传值回去
     * @Author BlueSky
     * @Date 9:56 2019/4/24
     **/
    public void closeSureWin(){
        long id =(long)depotView.getUserData();
        DataSetting dataSetting =  iDataSettingService.selectByKey(id);

        if(dataSetting != null){

            // 库存 - 销货出库单
            SaleOutboundOrderController saleOutboundOrderController = (SaleOutboundOrderController)StageManager.CONTROLLER.get("SaleOutboundOrderControllerWarehouse");
            if(saleOutboundOrderController != null){
                saleOutboundOrderController.warehouse_in.setText(dataSetting.getId().toString());
                saleOutboundOrderController.warehouse_in_str.setText(dataSetting.getTitle());
            }

            // 销售 - 订货单
            PurchaseOrderController purchaseOrderController = (PurchaseOrderController)StageManager.CONTROLLER.get("PurchaseOrderControllerWarehouse");
            if(purchaseOrderController != null){
                purchaseOrderController.shipping_warehouse.setText(dataSetting.getId().toString());
                purchaseOrderController.shipping_warehouse_str.setText(dataSetting.getTitle());
            }

            // 销售 - 销货单
            SaleGoodsController saleGoodsController = (SaleGoodsController)StageManager.CONTROLLER.get("SaleGoodsControllerWarehouse");
            if(saleGoodsController != null){
                saleGoodsController.warehouse_out.setText(dataSetting.getId().toString());
                saleGoodsController.warehouse_out_str.setText(dataSetting.getTitle());
            }

            // 销售 - 销售退货单
            SaleReturnController saleReturnController = (SaleReturnController)StageManager.CONTROLLER.get("SaleReturnControllerWarehouse");
            if(saleReturnController != null){
                saleReturnController.warehouse_in.setText(dataSetting.getId().toString());
                saleReturnController.warehouse_in_str.setText(dataSetting.getTitle());
            }

        }

        closeWin();
    }

    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)depotView.getScene().getWindow();
        StageManager.CONTROLLER.remove("SaleOutboundOrderControllerWarehouse");
        StageManager.CONTROLLER.remove("PurchaseOrderControllerWarehouse");
        StageManager.CONTROLLER.remove("SaleGoodsControllerWarehouse");
        StageManager.CONTROLLER.remove("SaleReturnControllerWarehouse");

        stage.close();
    }


}
