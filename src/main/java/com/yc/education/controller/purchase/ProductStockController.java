package com.yc.education.controller.purchase;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.ProductStock;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.util.DragUtil;
import com.yc.education.util.NumberUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName ProductStockController
 * @Descripion TODO  产品库存
 * @Author QuZhangJing
 * @Date 2018/10/22 18:12
 * @Version 1.0
 */
@Controller
public class ProductStockController extends BaseController implements Initializable {


    @Autowired
    private ProductStockService productStockService; //产品库存 Service

    @Autowired
    private ProductBasicService productBasicService; //产品 Service


    @FXML private TextField rightorder;

    @FXML private TextField leftorder;

    @FXML private TextField righttext;

    @FXML private TextField lefttext;

    @FXML private ComboBox rightcom;

    @FXML private ComboBox leftcom;






    @FXML private TableView productStockView;
    @FXML private TableColumn productorder; //产品编号
    @FXML private TableColumn productname; //产品编号
    @FXML private TableColumn usablenum; //产品编号
    @FXML private TableColumn stocknum; //产品编号
    @FXML private TableColumn onthewaynum; //产品编号
    @FXML private TableColumn salenum; //产品编号
    @FXML private TableColumn backnum; //产品编号
    @FXML  private TableColumn purchasenum; //产品编号
    @FXML  private TableColumn parprice; //产品编号


    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注


    @FXML private Label warehouseProductName;

    @FXML private TableView warehouseTableView;

    @FXML private TableColumn warehouseName; //仓库名称

    @FXML private TableColumn productNum; //数量



    @FXML private VBox stock_find_fast;

    @FXML private VBox stock_find_prev;

    @FXML private VBox stock_find_next;

    @FXML private VBox stock_find_last;


    @FXML private VBox product_find_fast;
    @FXML private VBox product_find_prev;
    @FXML private VBox product_find_next;
    @FXML private VBox product_find_last;

    @FXML private TextField product_textField_pageSize;

    @FXML private TextField product_order_textField;

    private int pageSize = 10;


    private Stage stage = new Stage();

    int winStatus =0;

    private static SpringFxmlLoader loader = new SpringFxmlLoader();



    public  void rightButton(){
        winStatus =1;
        moreProductButtonClick();
    }

    public  void leftButton(){
        winStatus =2;
        moreProductButtonClick();
    }


    public void findProductSearch(){

        String pageSizes =  product_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreProduct(1);
        }

    }


    public void findProductPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreProduct(pageNum);
    }


    public void moreProductButtonClick(){

        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/product_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreProduct(1);
    }


    /**
     * 现有产品查询
     */
    public void loadMoreProduct(int pageNum){

        List<ProductBasic> productBasics = productBasicService.selectProductBasic("".equals(product_order_textField.getText()) || product_order_textField.getText() == null ? "" : product_order_textField.getText() ,pageNum,pageSize);

        PageInfo<ProductBasic> pageInfo = new PageInfo<>(productBasics);

        product_find_fast.setUserData(1); //首页

        product_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        product_find_next.setUserData(pageInfo.getNextPage());//下一页

        product_find_last.setUserData(pageInfo.getPages());//尾页


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
            DataSetting dataSetting = dataSettingService.findDataSettingBySortAndParentId(productBasic.getProtype().intValue(),6L);
            productBasic.setProtypeStr(dataSetting.getTitle());
            list.add(productBasic);
        }

        tableViewProduct.setItems(list); //tableview添加list

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductBasic>() {
            @Override
            public void changed(ObservableValue<? extends ProductBasic> observableValue, ProductBasic oldItem, ProductBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    tableViewProduct.setUserData(newItem.getId());
                }
            }
        });


        tableViewProduct.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }

    public void closeSureWin(){
        long id =(long)tableViewProduct.getUserData();
        ProductBasic productBasic =  productBasicService.selectByKey(id);
        if(winStatus == 1 ){
            leftorder.setText(productBasic.getIsnum());
        }else if(winStatus == 2){
            rightorder.setText(productBasic.getIsnum());
        }

        coseWin();
    }

    public void coseWin(){
        stage.close();
    }


    public void selectButton(){
        loadData(1);
    }



    public void findStockPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadData(pageNum);
    }



    public void loadData(int pageNum){

         List<ProductStock> productStocks = productStockService.findProductStock(rightorder.getText(),leftorder.getText(),
                 righttext.getText(),lefttext.getText(),
                 "","",pageNum,18);

        PageInfo<ProductStock> pageInfo = new PageInfo<>(productStocks);

        stock_find_fast.setUserData(1); //首页

        stock_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        stock_find_next.setUserData(pageInfo.getNextPage());//下一页

        stock_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<ProductStock> list = FXCollections.observableArrayList();

        productorder.setCellValueFactory(new PropertyValueFactory("productorder"));
        productname.setCellValueFactory(new PropertyValueFactory("productname"));
        usablenum.setCellValueFactory(new PropertyValueFactory("usablenum"));
        stocknum.setCellValueFactory(new PropertyValueFactory("stocknum"));
        onthewaynum.setCellValueFactory(new PropertyValueFactory("onthewaynum"));
        salenum.setCellValueFactory(new PropertyValueFactory("salenum"));
        backnum.setCellValueFactory(new PropertyValueFactory("backnum"));
        purchasenum.setCellValueFactory(new PropertyValueFactory("purchasenum"));
        parprice.setCellValueFactory(new PropertyValueFactory("parprice"));


        if(productStocks.size()>0){
                for(ProductStock productStock : productStocks){
                    list.add(productStock);
                }
        }

        productStockView.setItems(list);

        productStockView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductStock>() {
            @Override
            public void changed(ObservableValue<? extends ProductStock> observableValue, ProductStock oldItem, ProductStock newItem) {
                productStockView.setUserData(newItem.getId());
            }
        });

        productStockView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                openWarehouse();
            }
        });


    }


    public void openWarehouse(){

        stage.setTitle("产品库存数量");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/select_warehouse.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
//        stage.initStyle(StageStyle.UNDECORATED);
//        DragUtil.addDragListener(stage, pane); //拖拽监听
        stage.show();
        loadWarehouseAndProductNum();
    }
//    @FXML private Label warehouseProductName;
//
//    @FXML private TableView warehouseTableView;
//
//    @FXML private TableColumn warehouseName; //仓库名称
//
//    @FXML private TableColumn productNum; //数量


    @Autowired
    private DataSettingService dataSettingService;

    public void loadWarehouseAndProductNum(){

        long id = (long)productStockView.getUserData();

        ProductStock productStock =productStockService.selectByKey(id);

        warehouseProductName.setText(productStock.getProductname());

        List<DataSetting> dataSettings = dataSettingService.findDataSettingByPrevId(0,33);

        ObservableList<DataSetting> dataSettings1 = FXCollections.observableArrayList();

        warehouseName.setCellValueFactory(new PropertyValueFactory("title"));
        productNum.setCellValueFactory(new PropertyValueFactory("productNum"));

        for (DataSetting dataSetting:dataSettings) {
            dataSetting.setProductNum(productStockService.findProductStockByWarehouseId(dataSetting.getId(),productStock.getProductorder()) == null?0.00:productStockService.findProductStockByWarehouseId(dataSetting.getId(),productStock.getProductorder()));
            dataSettings1.add(dataSetting);
        }

        warehouseTableView.setItems(dataSettings1);

    }


    //清除
    public void clearButton(){
        rightorder.setText(NumberUtil.NULLSTRING);

        leftorder.setText(NumberUtil.NULLSTRING);

        righttext.setText(NumberUtil.NULLSTRING);

        lefttext.setText(NumberUtil.NULLSTRING);

        rightcom.getSelectionModel().select(-1);

        leftcom.getSelectionModel().select(-1);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData(1);
        setComboBox(6,rightcom,-1);
        setComboBox(6,leftcom,-1);
    }
}
