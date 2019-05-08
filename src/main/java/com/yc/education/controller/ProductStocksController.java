package com.yc.education.controller;

import com.yc.education.model.ProductStock;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.util.NumberUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName ProductStockController
 * @Description TODO  库存查询
 * @Author QuZhangJing
 * @Date 2019/4/24 15:14
 * @Version 1.0
 */
@Controller
public class ProductStocksController extends BaseController implements Initializable {



    @Autowired
    private ProductStockService productStockService;
    @Autowired
    private DataSettingService dataSettingService;

    @FXML
    private TextField productOrderSearch;
    @FXML
    private TextField productNameSearch;
    @FXML
    private ComboBox productTypeSearch;
    @FXML
    private TextField warehouseNameSearch;
    @FXML
    private TextField depotNameSearch;


    @FXML
    private TableView productStockTableView;
    @FXML
    private TableColumn productSort;
    //产品编号
    @FXML
    private TableColumn productOrder;
    //产品名称
    @FXML
    private TableColumn productName;
    //可用数量
    @FXML
    private TableColumn userNum;
    //库存数量
    @FXML
    private TableColumn stockNum;
    //在途数量
    @FXML
    private TableColumn onthewayNum;
    //价格
    @FXML
    private TableColumn price;
    //最低售价
    @FXML
    private TableColumn floorPrice;
    //单位
    @FXML
    private TableColumn productUnit;
    //所属仓库
    @FXML
    private TableColumn warehouse;
    //所属库位
    @FXML
    private TableColumn depot;


    @FXML
    private TableView depotTableView;
    @FXML
    private TableColumn depotSort;
    @FXML
    private TableColumn depotOrder;
    @FXML
    private TableColumn depotNum;


    public void searchProductStock(){

        List<ProductStock> productStocks = productStockService.findProductStockByProductOrderAndProductNameAndMore(
                productOrderSearch.getText(),
                productNameSearch.getText(),
                productTypeSearch.getSelectionModel().getSelectedIndex()+1,
                warehouseNameSearch.getText(),
                depotNameSearch.getText());

        ObservableList<ProductStock> productStocksList = FXCollections.observableArrayList();


        productSort.setCellValueFactory(new PropertyValueFactory("sort"));
        productOrder.setCellValueFactory(new PropertyValueFactory("productorder"));
        productName.setCellValueFactory(new PropertyValueFactory("productname"));
        userNum.setCellValueFactory(new PropertyValueFactory("usablenum"));
        stockNum.setCellValueFactory(new PropertyValueFactory("stocknum"));
        onthewayNum.setCellValueFactory(new PropertyValueFactory("onthewaynum"));
        price.setCellValueFactory(new PropertyValueFactory("normprice"));
        floorPrice.setCellValueFactory(new PropertyValueFactory("lowprice"));
        productUnit.setCellValueFactory(new PropertyValueFactory("basicunitStr"));
        warehouse.setCellValueFactory(new PropertyValueFactory("title"));
        depot.setCellValueFactory(new PropertyValueFactory("depname"));

        if(productStocks != null && productStocks.size()>0){

            for (int i=0,len=productStocks.size();i<len;i++) {
                productStocks.get(i).setSort(i+1);
                productStocks.get(i).setBasicunitStr(dataSettingService.findDataSettingBySortAndParentId(productStocks.get(i).getBasicunit().intValue(),5L).getTitle());
                productStocksList.add(productStocks.get(i));
            }

        }

        productStockTableView.setItems(productStocksList);


        productStockTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductStock>() {
            @Override
            public void changed(ObservableValue<? extends ProductStock> observableValue, ProductStock oldItem, ProductStock newItem) {
//                tableViewIndex = productStockTableView.getSelectionModel().getSelectedIndex();
                loadDepot(newItem.getProductorder());
            }
        });


    }


    public void loadDepot(String orders){

        List<ProductStock> productStocks = productStockService.findProductStockByProIsnum(orders);

        ObservableList<ProductStock> productStocksList = FXCollections.observableArrayList();

        depotSort.setCellValueFactory(new PropertyValueFactory("sort"));
        depotOrder.setCellValueFactory(new PropertyValueFactory("depot"));
        depotNum.setCellValueFactory(new PropertyValueFactory("stocknum"));

        if(productStocks != null && productStocks.size()>0){

            for (int i=0,len=productStocks.size();i<len;i++) {
                productStocks.get(i).setSort(i+1);
                productStocksList.add(productStocks.get(i));
            }

        }

        depotTableView.setItems(productStocksList);

    }







    public void clearSearch(){

        productOrderSearch.setText(NumberUtil.NULLSTRING);

        productNameSearch.setText(NumberUtil.NULLSTRING);

        productTypeSearch.getSelectionModel().select(-1);

        warehouseNameSearch.setText(NumberUtil.NULLSTRING);

        depotNameSearch.setText(NumberUtil.NULLSTRING);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setComboBox(6L,productTypeSearch,-1);

//        loadData();
    }




}
