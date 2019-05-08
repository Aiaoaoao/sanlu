package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.stock.CheckStockProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.stock.CheckStockProductService;
import com.yc.education.service.stock.CheckStockService;
import com.yc.education.util.NumberUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName InventoryQueryController
 * @Description TODO 盘点查询
 * @Author QuZhangJing
 * @Date 2019/3/21 10:51
 * @Version 1.0
 */
@Controller
public class InventoryQueryController extends BaseController implements Initializable {

    @Autowired
    private DataSettingService dataSettingService;
    @Autowired
    private CheckStockProductService checkStockProductService;
    @Autowired
    private ProductBasicService productBasicService;// 产品基本资料

    @FXML
    private Pane warehousePane;

    @FXML
    private TableView checkStockTableView;
    @FXML
    private TableColumn productOrder;
    @FXML
    private TableColumn productName;
    @FXML
    private TableColumn checkStockOrder;
    @FXML
    private TableColumn checkStockDate;
    @FXML
    private TableColumn stockNum;
    @FXML
    private TableColumn checkStockNum;
    @FXML
    private TableColumn profitandloss;
    @FXML
    private TableColumn remarks;

    @FXML
    private TextField productOrderStart;
    @FXML
    private TextField productOrderEnd;
    @FXML
    private ComboBox productTypeStart;
    @FXML
    private ComboBox productTypeEnd;
    @FXML
    private DatePicker checkDateStart;
    @FXML
    private DatePicker checkDateEnd;


    @FXML
    private VBox check_find_fast;
    @FXML
    private VBox check_find_prev;
    @FXML
    private VBox check_find_next;
    @FXML
    private VBox check_find_last;


    @FXML private VBox product_find_fast;
    @FXML private VBox product_find_prev;
    @FXML private VBox product_find_next;
    @FXML private VBox product_find_last;

    @FXML private TextField product_textField_pageSize;

    @FXML private TextField product_order_textField;


    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注


    private Boolean startOrEnd = false;



    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    private int pageSize = 10;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setComboBox(6,productTypeStart,-1);
        setComboBox(6,productTypeEnd,-1);

       List<DataSetting> dataSettingList =  dataSettingService.findDataSettingByPrevId(0,33);

        ObservableList<CheckBox> checkBoxes = FXCollections.observableArrayList();

        double checkBoxHeight = 20.0;

        boolean flag = true;

        for (DataSetting dataSetting:dataSettingList) {
            CheckBox checkBox;
            if(flag){
                checkBox = new CheckBox("全部仓库");
                checkBox.setPrefHeight(checkBoxHeight);
                checkBoxes.add(0,checkBox);
                checkBoxHeight += 40.0;
                flag = !flag;
            }

             checkBox = new CheckBox(dataSetting.getTitle());
            checkBox.setPrefHeight(checkBoxHeight);

            checkBoxHeight += 40.0;
            checkBoxes.add(checkBox);

            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov,
                                    Boolean old_val, Boolean new_val) {
//                    alert_informationDialog(ov.getValue()+"===");
                }
            });

        }

        warehousePane.getChildren().setAll(checkBoxes);

        checkStockLoad();

    }

    private int currentPage =  1;

    public void findCheckPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        currentPage =Integer.parseInt(String.valueOf(node.getUserData()));

        checkStockLoad();
    }


    public void checkStockLoad(){


        String proTextStartStr = productOrderStart.getText();

        String proTextEndStr = productOrderEnd.getText();

        int changeTypeStartInt =  productTypeStart.getSelectionModel().getSelectedIndex()+1;

        int changeTypeEndInt = productTypeEnd.getSelectionModel().getSelectedIndex()+1;

        String changeDateStartStr =  checkDateStart.getValue()==null?"":checkDateStart.getValue().toString();

        String changeDateEndStr = checkDateEnd.getValue()==null?"":checkDateEnd.getValue().toString();

        List<CheckStockProduct> checkStockProducts = checkStockProductService.findCheckStockProductByProductOrderAndProductType(
                proTextStartStr,
                proTextEndStr,
                changeTypeStartInt,
                changeTypeEndInt,
                changeDateStartStr,
                changeDateEndStr,
                currentPage,20);


        PageInfo<CheckStockProduct> pageInfo = new PageInfo<>(checkStockProducts);

        check_find_fast.setUserData(1); //首页

        check_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        check_find_next.setUserData(pageInfo.getNextPage());//下一页

        check_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<CheckStockProduct> checkStockProducts1 = FXCollections.observableArrayList();

//        @FXML
//        private TableView checkStockTableView;


        productOrder.setCellValueFactory(new PropertyValueFactory("productorder"));
        productName.setCellValueFactory(new PropertyValueFactory("productname"));
        checkStockOrder.setCellValueFactory(new PropertyValueFactory("checkStockOrder"));
        checkStockDate.setCellValueFactory(new PropertyValueFactory("flagDate"));
        stockNum.setCellValueFactory(new PropertyValueFactory("depotnum"));
        checkStockNum.setCellValueFactory(new PropertyValueFactory("nownum"));
        profitandloss.setCellValueFactory(new PropertyValueFactory("profitandloss"));
        remarks.setCellValueFactory(new PropertyValueFactory("remarks"));


        if(checkStockProducts != null && checkStockProducts.size() > 0){
            for (CheckStockProduct checkStockProduct:checkStockProducts) {
                checkStockProduct.setCheckStockOrder(checkStockProduct.getCheckStock().getCheckorder());
                checkStockProduct.setFlagDate(DateToString(checkStockProduct.getCheckStock().getCheckdate()));
                checkStockProducts1.add(checkStockProduct);
            }
        }


        checkStockTableView.setItems(checkStockProducts1);

    }


    public void clearSearch(){
        productOrderStart.setText(NumberUtil.NULLSTRING);

        productOrderEnd.setText(NumberUtil.NULLSTRING);

        productTypeStart.getSelectionModel().select(-1);

        productTypeEnd.getSelectionModel().select(-1);

        checkDateStart.setValue(null);

        checkDateEnd.setValue(null);


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

        stage.setTitle("现有产品查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/check_stock_product_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreProduct(1);
    }

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
        if(startOrEnd){
            productOrderStart.setText(productBasic.getIsnum());
        }else{
            productOrderEnd.setText(productBasic.getIsnum());
        }
        coseWin();
    }




    public void coseWin(){
        stage.close();
    }


    public void clickProBtnStart(){
        startOrEnd = true;
        moreProductButtonClick();
    }

    public void clickProBtnEnd(){
        startOrEnd = false;
        moreProductButtonClick();
    }

}
