package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
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
 * 销售产品折扣查询
 */
@Controller
public class SaleDiscountQueryWhereController extends BaseController implements Initializable {


    @Autowired
    ISaleGoodsService iSaleGoodsService;
    @Autowired
    ISaleGoodsProductService iSaleGoodsProductService;
    @Autowired
    private ProductBasicService productBasicService; //产品 Service


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML public TextField customer_no;
    @FXML public TextField customer_no_end;
    @FXML TextField product_no;
    @FXML TextField product_name_end;
    @FXML DatePicker sale_date;
    @FXML DatePicker sale_date_end;
    @FXML TextField customer_name;
    @FXML TextField product_no_end;
    @FXML TextField product_name;
    @FXML TextField customer_name_end;
    @FXML TextField discount;

    @FXML TableView tab_product;

    @FXML TableColumn col_id;
    @FXML TableColumn col_product_no;
    @FXML TableColumn col_product_name;
    @FXML TableColumn col_demand_num;
    @FXML TableColumn col_price;
    @FXML TableColumn col_customer_no;
    @FXML TableColumn col_discount;
    @FXML TableColumn col_customer_name;
    @FXML TableColumn col_customer_discount;
    @FXML TableColumn col_sale_no;
    @FXML TableColumn col_sale_date;
    @FXML TableColumn col_business_leader;

    //=============================== 产品 ================================
    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注
    private Stage stage = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();
    int winStatus =0;

    @FXML private TextField rightorder;
    @FXML private TextField leftorder;


    // 订单编号
    private static String  orderid = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        String customerNo = "";
        String customerNoEnd = "";
        String productNo = "";
        String productNoEnd = "";
        String productName = "";
        String productNameEnd = "";
        String saleDateStr = "";
        String saleDateEndStr = "";
        String customerName = "";
        String customerNameEnd = "";
        String discountStr = "";
        if(customer_no.getText() != null){
            customerNo = customer_no.getText();
        }
        if(customer_no_end.getText() != null){
            customerNoEnd = customer_no_end.getText();
        }
        if(product_no.getText() != null){
            productNo = product_no.getText();
        }
        if(product_no_end.getText() != null){
            productNoEnd = product_no_end.getText();
        }
        if(product_name.getText() != null){
            productName = product_name.getText();
        }
        if(product_name_end.getText() != null){
            productNameEnd = product_name_end.getText();
        }
        if(sale_date.getValue() != null){
            saleDateStr = sale_date.getValue().toString();
        }
        if(sale_date_end.getValue() != null){
            saleDateEndStr = sale_date_end.getValue().toString();
        }
        if(customer_name.getText() != null){
            customerName = customer_name.getText();
        }
        if(customer_name_end.getText() != null){
            customerNameEnd = customer_name_end.getText();
        }
        if(discount.getText() != null){
            discountStr = discount.getText();
        }
        List<SaleGoodsProduct> list = iSaleGoodsProductService.listSaleGoodsProductDiscount(page,AppConst.ROWS,customerNo, customerNoEnd, productNo, productNoEnd, productName, productNameEnd, saleDateStr, saleDateEndStr, customerName, customerNameEnd, discountStr);

        if(list != null && list.size() >0){
            PageInfo<SaleGoodsProduct> pageInfo = new PageInfo<>(list);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(list);
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
     * 清除
     */
     @FXML
     public void clear(){
        customer_no.clear();
        customer_no_end.clear();
        product_no.clear();
        product_name_end.clear();
        sale_date.setValue(null);
        sale_date_end.setValue(null);
        customer_name.clear();
        product_no_end.clear();
        product_name.clear();
        customer_name_end.clear();
        discount.clear();

        tab_product.setItems(null);
     }

    /**
     * 查找
     */
    @FXML
    public void find(){
       setMenuValue(1);
    }

    /**
     * 初始化加载数据
     */
    private void loadData(List<SaleGoodsProduct> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                SaleGoods saleGoods = iSaleGoodsService.getSaleGoods(p.getSaleNo());
                if(saleGoods != null){
                    p.setBusinessLeader(saleGoods.getBusinessLeaderStr());
                    p.setCustomerDiscount(saleGoods.getDiscount());
                }
            });
        }

        // 查询客户集合
        final ObservableList<SaleGoodsProduct> data = FXCollections.observableArrayList(list);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_demand_num.setCellValueFactory(new PropertyValueFactory("num"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_discount.setCellValueFactory(new PropertyValueFactory("discount"));
        col_customer_name.setCellValueFactory(new PropertyValueFactory("customerName"));
        col_customer_discount.setCellValueFactory(new PropertyValueFactory("customerDiscount"));
        col_sale_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        col_sale_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_business_leader.setCellValueFactory(new PropertyValueFactory("businessLeader"));

        tab_product.setItems(data);

    }

    /**
     * 打开现有客户窗口 开始
     */
    @FXML
    public void OpenCurrentClientQuery() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Stage stage = new Stage();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDiscountQueryWhereController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }


    /**
     * 打开现有客户窗口 结束
     */
    @FXML
    public void OpenCurrentClientQueryEnd() {
        SpringFxmlLoader loader = new SpringFxmlLoader();
        Stage stage = new Stage();
        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("SaleDiscountQueryWhereControllerEnd", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 右边按钮
     */
    public  void rightButton(){
        winStatus =1;
        moreProductButtonClick();
    }

    /**
     * 左边按钮
     */
    public  void leftButton(){
        winStatus =2;
        moreProductButtonClick();
    }


    /**
     * 打开现有产品窗口
     */
    @FXML
    public void moreProductButtonClick(){

        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/sale/product_sale_discount_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        loadMoreProduct();
    }

    /**
     * 现有产品查询
     */
    public void loadMoreProduct(){

        List<ProductBasic> productBasics = productBasicService.selectProductBasic();

        ObservableList<ProductBasic> list = FXCollections.observableArrayList();

        tableViewProduct.setEditable(true);

        depid.setCellValueFactory(new PropertyValueFactory("id"));
        findproductid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findproductname.setCellValueFactory(new PropertyValueFactory("proname"));
        findprotype.setCellValueFactory(new PropertyValueFactory("protype"));
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
            product_no_end.setText(productBasic.getIsnum());
        }else if(winStatus == 2){
            product_no.setText(productBasic.getIsnum());
        }

        coseWin();
    }

    public void coseWin(){
        stage.close();
    }



}
