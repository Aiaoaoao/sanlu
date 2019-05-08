package com.yc.education.controller.customer;


import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.customer.CustomerDemandGoods;
import com.yc.education.model.customer.CustomerDemandGoodsInfo;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.customer.ICustomerDemandGoodsInfoService;
import com.yc.education.service.customer.ICustomerDemandGoodsService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 客户需求商品查询
 * @Author: BlueSky
 * @Date: 2018/8/15 15:14
 */
@Controller
public class CustomerDemandGoodsQueryController extends BaseController implements Initializable {

    @Autowired
    ICustomerDemandGoodsService iCustomerDemandGoodsService;
    @Autowired
    ICustomerDemandGoodsInfoService iCustomerDemandGoodsInfoService;
    @Autowired
    private ProductBasicService productBasicService; //产品 Service


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML
    TextField create_no;
    @FXML
    DatePicker create_date;
    @FXML
    TextField product_no;
    @FXML
    TextField customer_no;
    @FXML
    ComboBox material;
    @FXML
    ComboBox production;
    @FXML
    TextField create_no_end;
    @FXML
    DatePicker create_date_end;
    @FXML
    TextField customer_no_end;
    @FXML
    TextField product_no_end;
    @FXML
    ComboBox manufacture;
    @FXML
    TableView table;
    @FXML
    TableColumn col_id;
    @FXML
    TableColumn col_product_no;
    @FXML
    TableColumn col_product_name;
    @FXML
    TableColumn col_need_num;
    @FXML
    TableColumn col_price;
    @FXML
    TableColumn col_unit;
    // 材质
    @FXML
    TableColumn col_material;
    @FXML
    TableColumn col_manufacture;
    @FXML
    TableColumn col_process_material;
    @FXML
    TableColumn col_short_call;
    @FXML
    TableColumn col_create_no;
    @FXML
    TableColumn col_create_date;
    @FXML
    TableColumn col_remark;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        String createNo = create_no.getText();
        String createNoEnd = create_no_end.getText();
        LocalDate createDate = create_date.getValue();
        LocalDate createDateEnd = create_date_end.getValue();
        String productNo = product_no.getText();
        String productNoEnd = product_no_end.getText();
        String customerNo = customer_no.getText();
        String customerNoEnd = customer_no_end.getText();
        String createDateStr = "",createDateEndStr ="";
        String materialStr,productionStr,manufactureStr;
        if(createNo==null){
            createNo = "";
        }
        if(createNo==null){
            createNo = "";
        }
        if(createDate != null){
            createDateStr = createDate.toString();
        }
        if(createDateEnd != null){
            createDateEndStr = createDateEnd.toString();
        }
        if(productNo==null){
            productNo = "";
        }
        if(productNoEnd==null){
            productNoEnd = "";
        }
        if(customerNo==null){
            customerNo = "";
        }
        if(customerNoEnd==null){
            customerNoEnd = "";
        }
        if(material.getSelectionModel().getSelectedItem() != null){
            materialStr = material.getSelectionModel().getSelectedItem().toString();
        }
        if(production.getSelectionModel().getSelectedItem() != null){
            productionStr = production.getSelectionModel().getSelectedItem().toString();
        }
        if(manufacture.getSelectionModel().getSelectedItem() != null){
            manufactureStr = manufacture.getSelectionModel().getSelectedItem().toString();
        }
        // 拼接条件
        List<CustomerDemandGoods> customerDemandGoodsList = iCustomerDemandGoodsService.listCustomerDemandGoodsByManyCondition(page,AppConst.ROWS,createNo,createNoEnd,createDateStr ,createDateEndStr ,customerNo ,customerNoEnd  );
        // 查询结果集合
        List<CustomerDemandGoodsInfo> customerDemandGoodsInfoList = new ArrayList<>();
        customerDemandGoodsList.forEach(p->{
            List<CustomerDemandGoodsInfo> info = iCustomerDemandGoodsInfoService.listCustomerDemandGoodsInfoByCustomerDemandId(p.getId());
            info.forEach(k->{
                k.setCreateNo(p.getCreateNo());
                k.setCreateDate(p.getCreateDate());
                k.setRemark(p.getRemark());
                k.setCustomerShortCall(p.getCustomerName());
                customerDemandGoodsInfoList.add(k);
            });
        });


        if(customerDemandGoodsInfoList != null && customerDemandGoodsInfoList.size() >0){
            PageInfo<CustomerDemandGoodsInfo> pageInfo = new PageInfo<>(customerDemandGoodsInfoList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            generalProductTab(customerDemandGoodsInfoList);
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
     * 查询需求商品
     */
    @FXML
    public void queryDemandGoods(){
        setMenuValue(1);
    }

    /**
     * 获取问题及意见
     */
    public void generalProductTab(List<CustomerDemandGoodsInfo> list){
        table.setItems(null);

        // 查询地址集合
        col_id.setCellValueFactory(new PropertyValueFactory<CustomerDemandGoodsInfo,Long>("id"));
        col_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
        col_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
        col_need_num.setCellValueFactory(new PropertyValueFactory("quantityDemand"));
        col_price.setCellValueFactory(new PropertyValueFactory("price"));
        col_unit.setCellValueFactory(new PropertyValueFactory("unit"));
        col_process_material.setCellValueFactory(new PropertyValueFactory("processMaterial"));
        col_manufacture.setCellValueFactory(new PropertyValueFactory("manufactureMethod"));
        col_material.setCellValueFactory(new PropertyValueFactory("material"));
        col_short_call.setCellValueFactory(new PropertyValueFactory("customerShortCall"));
        col_create_no.setCellValueFactory(new PropertyValueFactory("createNo"));
        col_create_date.setCellValueFactory(new PropertyValueFactory<CustomerDemandGoodsInfo,Date>("createDate"));
        col_create_date.setCellFactory(column -> {
            TableCell<CustomerDemandGoodsInfo, Date> cell = new TableCell<CustomerDemandGoodsInfo, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        if(item != null){
                            this.setText(format.format(item));
                        }
                    }
                }
            };

            return cell;
        });
        col_remark.setCellValueFactory(new PropertyValueFactory("remark"));

        table.setItems(FXCollections.observableArrayList(list));

    }

    /**
     * 打开现有产品窗口
     */
    @FXML
    public void moreProductButtonClick(){

        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/customer/product_customer_demand_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        loadMoreProduct();
    }

    /**
     * 右边按钮
     */
    @FXML
    public  void rightButton(){
        winStatus =1;
        moreProductButtonClick();
    }

    /**
     * 左边按钮
     */
    @FXML
    public  void leftButton(){
        winStatus =2;
        moreProductButtonClick();
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

    /**
     * 清空
     */
    @FXML
    private void clearVal(){
        create_no.clear();
        create_date.setValue(null);
        product_no.clear();
        customer_no.clear();
        material.getItems().clear();
        production.getItems().clear();
        create_no_end.clear();
        create_date_end.setValue(null);
        customer_no_end.clear();
        product_no_end.clear();
        manufacture.getItems().clear();
    }




    /**
     * @Author BlueSky
     * @Description //建档编号（客户需求商品查询）开始
     * @Date 20:22 2018/8/15
     **/
    @FXML
    public void CustomerDemandGoodsQueryBen(){

        Pane pane = new Pane();

        //将第二个窗口保存到map中
        StageManager.STAGE.put("CustomerDemandGoodsQuery", stage);
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryController", this);
        //设置标识 查询开始的客户编号
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryControllerState", "ben");
        pane.getChildren().setAll(loader.load("/fxml/customer/customer_demand_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @Author BlueSky
     * @Description //建档编号（客户需求商品查询）结束
     * @Date 20:22 2018/8/15
     * @Param []
     * @return void
     **/
    @FXML
    public void CustomerDemandGoodsQueryEnd(){

        Pane pane = new Pane();

        //将第二个窗口保存到map中
        StageManager.STAGE.put("CustomerDemandGoodsQuery", stage);
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryController", this);
        //设置标识 查询结束的客户编号
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryControllerState", "end");

        pane.getChildren().setAll(loader.load("/fxml/customer/customer_demand_goods_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @Author BlueSky
     * @Description //客户编号查询 开始
     * @Date 20:22 2018/8/15
     * @Param []
     * @return void
     **/
    @FXML
    public void CustomerQueryBen(){

        Pane pane = new Pane();

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryController", this);
        //设置标识 查询开始的客户编号
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryControllerCustomerNo", "ben");
        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @Author BlueSky
     * @Description //客户编号查询 结束
     * @Date 20:22 2018/8/15
     * @Param []
     * @return void
     **/
    @FXML
    public void CustomerQueryEnd(){

        Pane pane = new Pane();


        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryController", this);
        //设置标识 查询结束的客户编号
        StageManager.CONTROLLER.put("CustomerDemandGoodsQueryControllerCustomerNo", "end");

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

}
