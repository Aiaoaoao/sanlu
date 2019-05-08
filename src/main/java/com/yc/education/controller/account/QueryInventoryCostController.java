package com.yc.education.controller.account;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.EditCell;
import com.yc.education.util.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 账款 -- 业务查询 -- 库存成本查询
 * @Author BlueSky
 * @Date 2019-02-18 16:30
 */
@Controller
public class QueryInventoryCostController extends BaseController implements Initializable {

    @Autowired private DataSettingService iDataSettingService;  //基础数据
    @Autowired  PurchaseStockService iPurchaseStockService;     //采购入库单
    @Autowired ProductBasicService iProductBasicService;     //产品基本资料

    @FXML public TextField product_no;         //产品编号
    @FXML public TextField product_no_end;     //产品编号END
    @FXML TextField product_name;        //产品名称
    @FXML TextField product_name_end;    //产品名称END
    @FXML ComboBox calc_unit;           //计量单位
    @FXML ComboBox calc_unit_end;       //计量单位END

    @FXML TableView order_table;
    @FXML TableColumn col_id;
    @FXML TableColumn col_product_no;
    @FXML TableColumn col_product_name;
    @FXML TableColumn col_invoice_name;
    @FXML TableColumn col_unit;
    @FXML TableColumn col_cost;
    @FXML TableColumn col_norm_price;
    @FXML TableColumn col_inventory_quantity;
    @FXML TableColumn col_total_cost;

    private Stage stage = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setComboBox(5L, calc_unit, 0);
        setComboBox(5L, calc_unit_end, 0);
    }

    @FXML
    public void queryByCondition(){
        String productno = product_no.getText();
        String productnoEnd = product_no_end.getText();
        String productname = product_name.getText();
        String productnameEnd = product_name_end.getText();
        String calcunit = calc_unit.getSelectionModel().getSelectedItem()==null?"":calc_unit.getSelectionModel().getSelectedItem().toString();
        String calcunitEnd = calc_unit_end.getSelectionModel().getSelectedItem()==null?"":calc_unit_end.getSelectionModel().getSelectedItem().toString();
        List<DataSetting> comboBoxType = iDataSettingService.findDataSetting(5L);
        if(calcunit!=null&&!"".equals(calcunit) || calcunitEnd!=null&&!"".equals(calcunitEnd)){
            for (int i = 0; i < comboBoxType.size(); i++) {
                if(calcunit.equals(comboBoxType.get(i).getTitle())){
                    calcunit = (i+1)+"";
                }
                if(calcunitEnd.equals(comboBoxType.get(i).getTitle())){
                    calcunitEnd = (i+1)+"";
                }
            }
        }
        List<ProductBasic> productBasicList = iProductBasicService.listQueryInventoryCost(productno, productnoEnd, productname, productnameEnd, calcunit, calcunitEnd);
        if(productBasicList != null){
            generalProductTab(productBasicList);
        }
    }



    /**
     * 加载表格数据
     * @param productBasicList
     */
    public void generalProductTab(List<ProductBasic> productBasicList){
        List<DataSetting> comboBoxType = iDataSettingService.findDataSetting(5L);
        productBasicList.forEach(p->{
            if(p.getStocknum()!=null && p.getPurchaseprice()!=null){
                p.setTotalCost(p.getStocknum() * p.getPurchaseprice());
            }
            for (int i = 0; i < comboBoxType.size(); i++) {
                if(p.getBasicunit() != null && Integer.valueOf(p.getBasicunit()+"")==i+1){
                    p.setProtypeStr(comboBoxType.get(i).getTitle());
                }
            }
        });
        ObservableList<ProductBasic> data = FXCollections.observableArrayList(productBasicList);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("isnum"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("proname"));
        col_invoice_name.setCellValueFactory(new PropertyValueFactory(""));
        col_unit.setCellValueFactory(new PropertyValueFactory("protypeStr"));
        col_cost.setCellValueFactory(new PropertyValueFactory<ProductBasic,String>("cost"));
        col_norm_price.setCellValueFactory(new PropertyValueFactory<ProductBasic,String>("normprice"));
        col_inventory_quantity.setCellValueFactory(new PropertyValueFactory<ProductBasic,String>("stocknum"));
        col_total_cost.setCellValueFactory(new PropertyValueFactory<ProductBasic,String>("totalCost"));

        order_table.setItems(data);

    }

    @FXML
    public void clearValue(){
        product_no.clear();
        product_no_end.clear();
        product_name.clear();
        product_name_end.clear();
        calc_unit.getSelectionModel().selectFirst();
        calc_unit_end.getSelectionModel().selectFirst();
        order_table.setItems(null);
    }

    /**
     * 现有产品查询
     */
    @FXML
    public  void moreProductButton(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("QueryInventoryCostControllerProduct", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 现有产品查询
     */
    @FXML
    public  void moreProductButtonEnd(){
        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("QueryInventoryCostControllerProductEnd", this);
        pane.getChildren().setAll(loader.load("/fxml/customer/product_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }


}
