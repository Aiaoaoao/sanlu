package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.model.stock.StockChangeProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import com.yc.education.service.sale.ISaleReturnGoodsService;
import com.yc.education.service.stock.StockChangeProductService;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName ChangeQueryController
 * @Description TODO 异动查询
 * @Author QuZhangJing
 * @Date 2019/3/20 10:09
 * @Version 1.0
 */
@Controller
public class ChangeQueryController extends BaseController implements Initializable {

    @Autowired
    private ProductBasicService productBasicService;// 产品基本资料
    @Autowired
    private StockChangeProductService stockChangeProductService;
    @Autowired
    private DataSettingService dataSettingService;
    @Autowired
    private ISaleReturnGoodsService iSaleReturnGoodsService;     //销售退货单
    @Autowired
    private ISaleReturnGoodsProductService iSaleReturnGoodsProductService;  //销售退货单产品详情

    @FXML
    Button proBtnStart; // 查询产品编号 开始按钮
    @FXML
    Button proBtnEnd; //查询产品编号 结束按钮
    @FXML
    TextField proTextStart; // 产品编号 开始
    @FXML
    TextField proTextEnd;   // 产品编号  结束
    @FXML
    ComboBox changeTypeStart; //异动类型开始
    @FXML
    ComboBox changeTypeEnd; //异动类型结束
    @FXML
    DatePicker changeDateStart; //异动日期开始
    @FXML
    DatePicker changeDateEnd; //异动日期结束


    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注


    @FXML private TableView changeQueryTableView;

    @FXML private TableColumn changeType; //异动类型
    @FXML private TableColumn changeRemarks; //备注
    @FXML private TableColumn productOrder; //产品编号
    @FXML private TableColumn productName; //产品名称
    @FXML private TableColumn productNum; //数量
    @FXML private TableColumn repaidNum; //已还数量
    @FXML private TableColumn norepaidNum; //未还数量
    @FXML private TableColumn changeDate; //异动日期
    @FXML private TableColumn changeOrder; //异动单号


    @FXML private VBox product_find_fast;
    @FXML private VBox product_find_prev;
    @FXML private VBox product_find_next;
    @FXML private VBox product_find_last;

    @FXML private TextField product_textField_pageSize;

    @FXML private TextField product_order_textField;

    private int pageSize = 10;

    @FXML private VBox change_find_fast;

    @FXML private VBox change_find_prev;

    @FXML private VBox change_find_next;

    @FXML private VBox change_find_last;


    private Boolean startOrEnd = false;


    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    public void clickProBtnStart(){
        startOrEnd = true;
        moreProductButtonClick();
    }

    public void clickProBtnEnd(){
        startOrEnd = false;
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

        stage.setTitle("现有产品查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/product_find.fxml"));
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
                proTextStart.setText(productBasic.getIsnum());
            }else{
                proTextEnd.setText(productBasic.getIsnum());
            }
        coseWin();
    }




    public void coseWin(){
        stage.close();
    }


    private int currentPage =  1;

    public void findChangePages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
         currentPage =Integer.parseInt(String.valueOf(node.getUserData()));

        searchChangeProduct();
    }

    //查找
    public void searchChangeProduct(){
//        @FXML
//        TextField proTextStart; // 产品编号 开始
//        @FXML
//        TextField proTextEnd;   // 产品编号  结束
//        @FXML
//        ComboBox changeTypeStart; //异动类型开始
//        @FXML
//        ComboBox changeTypeEnd; //异动类型结束
//        @FXML
//        DatePicker changeDateStart; //异动日期开始
//        @FXML
//        DatePicker changeDateEnd; //异动日期结束

        String proTextStartStr = proTextStart.getText();

        String proTextEndStr = proTextEnd.getText();

        int changeTypeStartInt =  changeTypeStart.getSelectionModel().getSelectedIndex()+1;

        int changeTypeEndInt = changeTypeEnd.getSelectionModel().getSelectedIndex()+1;

        String changeDateStartStr =  changeDateStart.getValue()==null?"":changeDateStart.getValue().toString();

        String changeDateEndStr = changeDateEnd.getValue()==null?"":changeDateEnd.getValue().toString();

        List<StockChangeProduct> stockChangeProducts = stockChangeProductService.findStockChangeProductByProductNumAndChangeTypeAndChangeDate(
                proTextStartStr,
                proTextEndStr,
                changeTypeStartInt,
                changeTypeEndInt,
                changeDateStartStr,
                changeDateEndStr,
                currentPage,
                24
                );

        PageInfo<StockChangeProduct> pageInfo = new PageInfo<>(stockChangeProducts);

        change_find_fast.setUserData(1); //首页

        change_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        change_find_next.setUserData(pageInfo.getNextPage());//下一页

        change_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<StockChangeProduct> stockChangeProducts1 = FXCollections.observableArrayList();

//        @FXML private TableView changeQueryTableView;
//

        changeType.setCellValueFactory(new PropertyValueFactory("changeType"));
        changeRemarks.setCellValueFactory(new PropertyValueFactory("remarks"));
        productOrder.setCellValueFactory(new PropertyValueFactory("productorder"));
        productName.setCellValueFactory(new PropertyValueFactory("productname"));
        productNum.setCellValueFactory(new PropertyValueFactory("productnum"));
        repaidNum.setCellValueFactory(new PropertyValueFactory("repaidNum"));
        norepaidNum.setCellValueFactory(new PropertyValueFactory("norepaidNum"));
        changeDate.setCellValueFactory(new PropertyValueFactory("changeDate"));
        changeOrder.setCellValueFactory(new PropertyValueFactory("changeOrder"));


        for (StockChangeProduct stockChangeProduct :stockChangeProducts) {



            switch (stockChangeProduct.getSoursebill()){
                case "销售退货单":
                     SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.getSaleReturnGoods(stockChangeProduct.getSourseorder());

                      List<SaleReturnGoodsProduct> saleReturnGoodsProducts = iSaleReturnGoodsProductService.listReturnGoodsProduct(saleReturnGoods.getId().toString());

                    for (SaleReturnGoodsProduct saleReturnGoodsProduct :saleReturnGoodsProducts) {
                        if(saleReturnGoodsProduct.getProductNo().equals(stockChangeProduct.getProductorder())){
                            //未还数量
                            stockChangeProduct.setNorepaidNum(saleReturnGoodsProduct.getNum() - stockChangeProduct.getProductnum());
                        }
                    }


                    break;

            }





            stockChangeProduct.setChangeType(returnChangeType(stockChangeProduct.getStockChange().getChangetype()));
            //已还数量
            stockChangeProduct.setRepaidNum(stockChangeProduct.getProductnum());

            stockChangeProduct.setChangeDate(new SimpleDateFormat("yyyy-MM-dd").format(stockChangeProduct.getStockChange().getChangedate()));
            stockChangeProduct.setChangeOrder(stockChangeProduct.getStockChange().getChangeorder());
            stockChangeProducts1.add(stockChangeProduct);
        }

        changeQueryTableView.setItems(stockChangeProducts1);
    }

    //清空
    public void clearSearch(){
        proTextStart.setText(NumberUtil.NULLSTRING);

        proTextEnd.setText(NumberUtil.NULLSTRING);

        changeTypeStart.getSelectionModel().select(-1);

        changeTypeEnd.getSelectionModel().select(-1);

        changeDateEnd.setValue(null);

        changeDateEnd.setValue(null);


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setChangeType(changeTypeStart); //异动类型开始
        setChangeType(changeTypeEnd);  //异动类型结束

        searchChangeProduct();

    }
}
