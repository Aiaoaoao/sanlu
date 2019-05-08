package com.yc.education.controller.purchase;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.purchase.*;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.purchase.*;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName TransportationInventoryController
 * @Description TODO 在途库存-采购管理
 * @Author QuZhangJing
 * @Date 2018/10/17 10:21
 * @Version 1.0
 */
@Controller
public class TransportationInventoryController extends BaseController implements Initializable {

    @Autowired
    private TransportationInventoryService transportationInventoryService; //在途库存
    @Autowired
    private TransportationProductService transportationProductService;//在途产品
    @Autowired
    private PurchaseOrdersService purchaseOrdersService;//采购订单
    @Autowired
    private PurchaseProductService purchaseProductService;//采购产品
    @Autowired
    private ForceOrderService forceOrderService;//强制结案
    @Autowired
    private ForceProductService forceProductService;//强制结案产品
    @Autowired
    private ProductStockService productStockService;//产品库存
    @Autowired
    private PurchaseStockProductService purchaseStockProductService;
    @Autowired
    private PurchaseStockService purchaseStockService;
    @Autowired
    private DataSettingService dataSettingService;
    @Autowired
    private ProductBasicService productBasicService;
    @Autowired
    private DepotBasicService depotBasicService;
    @Autowired
    private RelationService relationService;


    @FXML
    private Label fxmlStatus; //状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页

    private long changeId=0;

    private String purchaseOrderNum = "";
    //清除
    @FXML private VBox clearvbox;
    //提交
    @FXML private VBox submitvbox;
    //新增
    @FXML private VBox insertvbox;
    //修改
    @FXML private VBox updatevbox;
    //删除
    @FXML private VBox deletevbox;
    //打印
    @FXML private VBox printingvbox;
    //导入
    @FXML private VBox importData;


    @FXML private TextField orders;  //装箱单号

    @FXML private DatePicker senddate;  //发货日期

    @FXML private TextField invoicenum; //发票号码

    @FXML private DatePicker comedate;  //预计到货日期


    @FXML private TableView onthewayfind; //在途库存

    @FXML private TableColumn onthewayid; //编号

    @FXML private TableColumn findorders; //装箱单号

    @FXML private TableColumn findsenddate; //发货日期


    @FXML private TableView transportationProduct;
    @FXML private TableColumn productid; //
    @FXML private TableColumn purchaseorder; //
    @FXML private TableColumn sort; //
    @FXML private TableColumn seeorder; //参考单号
    @FXML private TableColumn pronum; //
    @FXML private TableColumn proName; //
    @FXML private TableColumn stocknum; //
    @FXML private TableColumn boxnum; //
    @FXML private TableColumn thistimeontheway; //
    @FXML private TableColumn totalnum; //
    @FXML private TableColumn stockover; //
    @FXML private TableColumn forcenum; //
    @FXML private TableColumn ontheway; //




    //导入--询价单据列表
    @FXML private TableView inquiryView;
    @FXML private TableColumn inquiryid; //单据ID
    @FXML private TableColumn findinquiryorder; //单据编号
    @FXML private TableColumn findinquirydata; //制单日期
    @FXML private TableColumn findsuppliernum; //供应商编号
    @FXML private TableColumn findstatus; //单据状态
    @FXML private TableColumn findcomestock; //到货仓库

    private long inquiryOrderId =0;

    //导入--采购订单产品
    @FXML private TableView inquiryProductView;
    @FXML private TableColumn inquiryproid; //询价单产品ID
    @FXML private TableColumn findproid; //产品编号
    @FXML private TableColumn finprosort; //产品序号
    @FXML private TableColumn findproname; //产品名称
    @FXML private TableColumn finpronum; //数量
    @FXML private TableColumn findproprice; //单价

    @FXML private TableColumn pro_depotnum;//仓库编号
    @FXML private TableColumn depotbutton;//仓库编号
    @FXML private TableColumn pro_depotname;//仓库名称
    @FXML private TableColumn pro_floor;//楼层
    @FXML private TableColumn pro_remarks;//备注


    @FXML private TableView houseView; //仓库库位
    @FXML private TableColumn findhousenum; //  库位编号
    @FXML private TableColumn findhousename; //  库位名称
    @FXML private TableColumn findparent; //  所属仓库
    @FXML private TableColumn findhousefloor; //  楼层
    @FXML private TableColumn depotNums;//库存数量



    @FXML private VBox transportation_find_fast;
    @FXML private VBox transportation_find_prev;
    @FXML private VBox transportation_find_next;
    @FXML private VBox transportation_find_last;

    @FXML private TextField transportation_textField_pageSize;

    @FXML private TextField transportation_order_textField;

    @FXML private VBox purchase_find_fast;
    @FXML private VBox purchase_find_prev;
    @FXML private VBox purchase_find_next;
    @FXML private VBox purchase_find_last;

    @FXML private TextField purchase_textField_pageSize;

    @FXML private CheckBox transportationCheckBox;

    private int pageSize = 10;


    @FXML private VBox depot_find_fast;
    @FXML private VBox depot_find_prev;
    @FXML private VBox depot_find_next;
    @FXML private VBox depot_find_last;

    @FXML private TextField depot_textField_pageSize;

    @FXML private TextField depot_order_textField;

    /**
     * 单据关联容器
     */
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;

    /**
     * 复制本单据砖专用锁
     */
    private boolean lockOrder = false;


    //在途产品之TabelView
    private ObservableList<TransportationProductProperty> transportationProductProperties = FXCollections.observableArrayList();
    //导入--询价单  采购订单产品导入数据TabelView
    private ObservableList<InquiryImportProperty> inquiryImportProperties = FXCollections.observableArrayList();


    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @FXML private CheckBox viewAll;

    boolean selectLock = false;

    @FXML private TextField totalNum;

    public void findTransportationSearch(){
        String pageSizes =  transportation_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreInquiry(1);
        }




    }


    public void findTransportationPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreInquiry(pageNum);
    }


    public void moreTransportationInventoryClick(){

        stage.setTitle("在途库存查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/ontheway_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreInquiry(1);
    }


    public void loadMoreInquiry(int pageNum){

        List<TransportationInventory> transportationInventory = transportationInventoryService.findTransportationInventory("".equals(transportation_order_textField.getText()) || transportation_order_textField.getText() == null ? "" : transportation_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<TransportationInventory> pageInfo = new PageInfo<>(transportationInventory);

        transportation_find_fast.setUserData(1); //首页

        transportation_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        transportation_find_next.setUserData(pageInfo.getNextPage());//下一页

        transportation_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<TransportationInventory> list = FXCollections.observableArrayList();


        /*tableView4.setEditable(true);*/


        onthewayid.setCellValueFactory(new PropertyValueFactory("id"));
        findorders.setCellValueFactory(new PropertyValueFactory("orders"));
        findsenddate.setCellValueFactory(new PropertyValueFactory("senddates"));


        for (TransportationInventory transportationInventory1:transportationInventory) {
            transportationInventory1.setSenddates(new SimpleDateFormat("yyyy-MM-dd").format(transportationInventory1.getSenddate()));
            list.add(transportationInventory1);

        }

        onthewayfind.setItems(list); //tableview添加list

        onthewayfind.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransportationInventory>() {
            @Override
            public void changed(ObservableValue<? extends TransportationInventory> observableValue, TransportationInventory oldItem, TransportationInventory newItem) {
                if(newItem.getOrders() != null && !("".equals(newItem.getOrders()))){
                    onthewayid.setUserData(newItem.getId());
                }
            }
        });


        onthewayfind.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeInquiryWin();
            }
        });
    }

    public void closeInquiryWin(){
        long id =(long)onthewayid.getUserData();
        TransportationInventory transportationInventory =  transportationInventoryService.selectByKey(id);
        orders.setText(transportationInventory.getOrders());
        invoicenum.setText(transportationInventory.getInvoicenum());
        senddate.setValue(LocalDateTime.ofInstant(transportationInventory.getSenddate().toInstant(), ZoneId.systemDefault()).toLocalDate());
        comedate.setValue(LocalDateTime.ofInstant(transportationInventory.getComedate().toInstant(), ZoneId.systemDefault()).toLocalDate());
        coseInquiryWin();
    }

    public void coseInquiryWin(){
        stage.close();
    }



    int selectIndex = 0;

    String productNum = "";

    public void inquiryProduct(){


       List<TransportationProduct> transportationProducts =  transportationProductService.findTransportationProductByParentid((long)orders.getUserData());


        Callback<TableColumn<TransportationProduct, String>, TableCell<TransportationProduct, String>> buttonFactory
                = new Callback<TableColumn<TransportationProduct, String>, TableCell<TransportationProduct, String>>() {
            @Override
            public TableCell call(final TableColumn<TransportationProduct, String> param) {
                final TableCell<TransportationProduct, String> cell = new TableCell<TransportationProduct, String>() {

                    final Button btn1 = new Button("...");

                    @Override
                    public void updateItem(String ite, boolean empty) {
                        super.updateItem(ite, empty);


                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction((ActionEvent t)
                                    -> {
                                selectIndex = getTableRow().getIndex();

                                for(int i=0,len=transportationProductProperties.size();i<len;i++){
                                    if(selectIndex == i){
                                        productNum = transportationProductProperties.get(i).getPronum();
                                    }
                                }
                                selectLock = false;
                                //现有库位查询
                                morePurchaseStockHouseClick();

                                // 参数
                            });
                            btn1.setMaxWidth(50);
                            btn1.setTextFill(Color.BLACK);
                            setGraphic(btn1);
                            setText(null);

                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };



        purchaseorder.setCellFactory(TextFieldTableCell.forTableColumn());
        sort.setCellFactory(TextFieldTableCell.forTableColumn());
        seeorder.setCellFactory(TextFieldTableCell.forTableColumn());
        pronum.setCellFactory(TextFieldTableCell.forTableColumn());
        proName.setCellFactory(TextFieldTableCell.forTableColumn());
        forcenum.setCellFactory(TextFieldTableCell.forTableColumn());
        stocknum.setCellFactory(TextFieldTableCell.forTableColumn());
        boxnum.setCellFactory(TextFieldTableCell.forTableColumn());
        thistimeontheway.setCellFactory(TextFieldTableCell.forTableColumn());
        totalnum.setCellFactory(TextFieldTableCell.forTableColumn());
        stockover.setCellFactory(TextFieldTableCell.forTableColumn());
        forcenum.setCellFactory(TextFieldTableCell.forTableColumn());
        ontheway.setCellFactory(TextFieldTableCell.forTableColumn());

        depotbutton.setCellFactory(buttonFactory);

        productid.setCellValueFactory(new PropertyValueFactory("serialNumber"));
        purchaseorder.setCellValueFactory(new PropertyValueFactory("purchaseorder"));
        sort.setCellValueFactory(new PropertyValueFactory("sort"));
        seeorder.setCellValueFactory(new PropertyValueFactory("seeorder"));
        pronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        proName.setCellValueFactory(new PropertyValueFactory("proName"));
        forcenum.setCellValueFactory(new PropertyValueFactory("forcenum"));
        stocknum.setCellValueFactory(new PropertyValueFactory("stocknum"));
        boxnum.setCellValueFactory(new PropertyValueFactory("boxnum"));
        thistimeontheway.setCellValueFactory(new PropertyValueFactory("thistimeontheway"));
        totalnum.setCellValueFactory(new PropertyValueFactory("totalnum"));
        stockover.setCellValueFactory(new PropertyValueFactory("stockover"));
        forcenum.setCellValueFactory(new PropertyValueFactory("forcenum"));
        ontheway.setCellValueFactory(new PropertyValueFactory("ontheway"));
        pro_depotnum.setCellValueFactory(new PropertyValueFactory("pro_depotnum"));
        pro_depotname.setCellValueFactory(new PropertyValueFactory("pro_depotname"));
        pro_floor.setCellValueFactory(new PropertyValueFactory("pro_floor"));
        pro_remarks.setCellValueFactory(new PropertyValueFactory("remarks"));



        transportationProductProperties.clear();

        int totalNums = 0;

        if(transportationProducts.size()>0){
            for (int i=0,len = transportationProducts.size();i<len;i++) {

                purchaseOrderNum = transportationProducts.get(i).getPurchaseorder();

                totalNums += transportationProducts.get(i).getThistimeontheway();

                TransportationProductProperty transportationProductProperty = new TransportationProductProperty(lockOrder ? 0 :transportationProducts.get(i).getId(),i+1,transportationProducts.get(i).getPurchaseorder(),transportationProducts.get(i).getSort(),transportationProducts.get(i).getSeeorder(),transportationProducts.get(i).getPronum(),transportationProducts.get(i).getProname(),transportationProducts.get(i).getStocknum(),transportationProducts.get(i).getBoxnum(),transportationProducts.get(i).getThistimeontheway(),transportationProducts.get(i).getTotalnum(),transportationProducts.get(i).getStockover(),transportationProducts.get(i).getForcenum(),transportationProducts.get(i).getOntheway(),transportationProducts.get(i).getDepotnum(),transportationProducts.get(i).getDepotname(),transportationProducts.get(i).getDepotfloor(),transportationProducts.get(i).getRemarks());

                transportationProductProperties.add(transportationProductProperty);
            }

        }

        transportationProduct.setItems(transportationProductProperties); //tableview添加list

        totalNum.setText(totalNums+"");

        transportationProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransportationProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends TransportationProductProperty> observableValue, TransportationProductProperty oldItem, TransportationProductProperty newItem) {
                selectIndex = transportationProduct.getSelectionModel().getSelectedIndex();
                changeId=newItem.getId();
            }
        });


        thistimeontheway.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();

                int totalNums = 0;

                for (int i=0,len=transportationProductProperties.size();i<len;i++) {
                    if(selectIndex == i){
                        transportationProductProperties.get(i).setThistimeontheway(newValue);
                    }

                    totalNums += transportationProductProperties.get(i).getThistimeontheway();
                }

                totalNum.setText(totalNums+"");


            }

        });

        lockOrder = false;
    }
    //保存在途产品
    public boolean saveTransportationProduct(long forceid){
        for (TransportationProductProperty transportationProductProperty :transportationProductProperties) {
            if(transportationProductProperty.getPronum()!=null){

                if(transportationProductProperty.getId()>0){

                    TransportationProduct transportationProduct1 =  transportationProductService.selectByKey(transportationProductProperty.getId());

                    if(transportationProductProperty.getThistimeontheway()  < 0){
                        alert_informationDialog("本次在途必须大于0");
//                        forceOrderService.delete(forceid);
//                        if(status == 1){
//                            relationService.delete(reationId);
//                        }
                        return false;
                    }

                    int onTheWay = 0;

                    boolean symbol = true;

                    if(transportationProduct1.getThistimeontheway() > transportationProductProperty.getThistimeontheway() ){
                        onTheWay =  transportationProduct1.getThistimeontheway() - transportationProductProperty.getThistimeontheway() ;
                        symbol = false;
                    }else if(transportationProduct1.getThistimeontheway() < transportationProductProperty.getThistimeontheway() ){
                        onTheWay =  transportationProductProperty.getThistimeontheway()  -  transportationProduct1.getThistimeontheway();
                        symbol = true;
                    }

                    TransportationProduct transportationProduct = new TransportationProduct(transportationProductProperty.getId(),forceid,transportationProductProperty.getPurchaseorder(),transportationProductProperty.getSort(),transportationProductProperty.getSeeorder(),transportationProductProperty.getPronum(),transportationProductProperty.getProName(),transportationProductProperty.getStocknum(),transportationProductProperty.getBoxnum(),onTheWay,transportationProductProperty.getTotalnum(),transportationProductProperty.getStockover(),transportationProductProperty.getForcenum(),transportationProductProperty.getOntheway(),transportationProductProperty.getPro_depotnum(),transportationProductProperty.getPro_depotname(),transportationProductProperty.getPro_floor(),transportationProductProperty.getRemarks());

                        if(validata(transportationProduct,symbol)){
                        transportationProduct.setOntheway(symbol ? transportationProduct.getOntheway()+transportationProduct.getThistimeontheway() : transportationProduct.getOntheway() - transportationProduct.getThistimeontheway());
                        transportationProduct.setThistimeontheway(transportationProductProperty.getThistimeontheway());
                        transportationProductService.updateNotNull(transportationProduct);
                        }
//                        else{
////                            transportationInventoryService.delete(forceid);
////                            if(status == 1){
////                                relationService.delete(reationId);
////                            }
//                            return false;
//                        }


                }else{
                    TransportationProduct transportationProduct = new TransportationProduct(forceid,transportationProductProperty.getPurchaseorder() == null ? "" : transportationProductProperty.getPurchaseorder(),transportationProductProperty.getSort(),transportationProductProperty.getSeeorder(),transportationProductProperty.getPronum(),transportationProductProperty.getProName(),transportationProductProperty.getStocknum(),transportationProductProperty.getBoxnum(),transportationProductProperty.getThistimeontheway(),transportationProductProperty.getTotalnum(),transportationProductProperty.getStockover(),transportationProductProperty.getForcenum(),transportationProductProperty.getOntheway(),transportationProductProperty.getPro_depotnum(),transportationProductProperty.getPro_depotname(),transportationProductProperty.getPro_floor(),transportationProductProperty.getRemarks());

                       if(validata(transportationProduct,true)){
                           transportationProduct.setOntheway(transportationProduct.getOntheway()+transportationProduct.getThistimeontheway());
                           transportationProductService.save(transportationProduct);
                       }
//                       else{
////                           transportationInventoryService.delete(forceid);
////                           if(status == 1){
//////                               relationService.delete(reationId);
//////                           }
//                           return false;
//                       }
                }
            }

        }
        return true;
    }

    //在途产品空白
    public void blankTransportationProduct(){
        TransportationProductProperty transportationProductProperty = new TransportationProductProperty();
        transportationProductProperties.add(transportationProductProperty);
    }



    public void findDeoptSearch(){

        String pageSizes =  depot_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreHouse(0,1);
        }

    }


    public void findDepotPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreHouse(0,pageNum);
    }

    //现有库位查询
    public void morePurchaseStockHouseClick(){

        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/ontheway_depot_house_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        viewAll.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                selectLock =(boolean)newValue;

                loadMoreHouse(0,1);


            }
        });
        pageSize = 10;
        loadMoreHouse(0,1);
    }


    /**
     * 库位查询规则： 只查询当前产品下的库位
     */
    public void loadMoreHouse(int status,int pageNum){

        List<DepotBasic> depotBasicList = new ArrayList<>();

        if(status == 0){

            if(!"".equals(productNum) && !selectLock){
                ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productNum);

             if(productBasic != null){
                 String[] storehouse = productBasic.getInventoryplace().split(",");

                 for (int i=0,len=storehouse.length;i<len;i++){

                     DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(storehouse[i],"".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);

                     if(depotBasic != null){
                         depotBasicList.add(depotBasic);
                     }else{
                         depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
                     }
                 }
             }else{
                 depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
             }

            }else{
                depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
            }

        }else{
            depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
        }



//        depotBasicList = depotBasicService.selectDepotBasic();

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
        depotNums.setCellValueFactory(new PropertyValueFactory("stockNum"));


        if(depotBasicList.size() > 0){
            for (DepotBasic depotBasic:depotBasicList) {

                DataSetting dataSetting =  dataSettingService.selectByKey(depotBasic.getParentid());

                if(dataSetting != null){depotBasic.setRemarks(dataSetting.getTitle());}

                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(depotBasic.getIsnum(),productNum);

                if(productStock != null){
                    depotBasic.setStockNum(productStock.getStocknum());
                }else{
                    depotBasic.setStockNum(0);
                }


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
    public void closeHouseWin(){

        long id =(long)houseView.getUserData();

        DepotBasic depotBasic =  depotBasicService.selectByKey(id);

        for(int i=0,len=transportationProductProperties.size();i<len;i++){

            if(selectIndex == i){

                transportationProductProperties.get(i).setPro_floor(depotBasic.getDepfloor());

                transportationProductProperties.get(i).setPro_depotnum(depotBasic.getIsnum());

                transportationProductProperties.get(i).setPro_depotname(depotBasic.getDepname());

            }
        }

        coseWin();
    }

    public void coseWin(){
        stage.close();
    }

    /**
     * 修改订单在途数量
     * 订货数量：未入库数量
     * @param transportationProduct
     * @return
     */
    public boolean validata(TransportationProduct transportationProduct,boolean symbol){



        //订货数量 - 已入库数量+ 已在途数量   - (transportationProduct.getStockover()+ transportationProduct.getOntheway() )

        int  flagNum = transportationProduct.getTotalnum()  - (transportationProduct.getStockover()+ transportationProduct.getOntheway() + transportationProduct.getForcenum());

        if(transportationProduct.getThistimeontheway() <= flagNum || !symbol && transportationProduct.getThistimeontheway() != 0 ){

            {
                //修改采购订单    根据订单来源的订单号 查询采购订单
                PurchaseOrders purchaseOrders =  purchaseOrdersService.findPurchaseByOrders(transportationProduct.getPurchaseorder());

                if(purchaseOrders != null){

                    List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(purchaseOrders.getId());

                    if( purchaseProducts != null && purchaseProducts.size()>0){

                        for (PurchaseProduct purchaseProduct:purchaseProducts) {


                            if(purchaseProduct.getProorders().equals(transportationProduct.getPronum())){


                                int productOnthway = symbol ? transportationProduct.getThistimeontheway()+purchaseProduct.getOntheway() :   purchaseProduct.getOntheway() - transportationProduct.getThistimeontheway() ;

                                //修改采购订单产品中产品在途数量
                                purchaseProduct.setOntheway(productOnthway);

                                purchaseProductService.updateNotNull(purchaseProduct);

                                //修改同订单 同产品的在途数量
                                List<TransportationProduct> transportationProducts = transportationProductService.findTransportarionProductByPurchaseOrders(transportationProduct.getPurchaseorder(),transportationProduct.getPronum());

                                for (TransportationProduct transportationProduct1:transportationProducts) {
                                    transportationProduct1.setOntheway(productOnthway);
                                    transportationProductService.updateNotNull(transportationProduct1);
                                }

                            }

                        }


                    }

                }


            }



            {

                //修改结案单


                List<ForceOrder>  forceOrder = forceOrderService.findForceOrderByPurchaseOrder(transportationProduct.getPurchaseorder());


                if(forceOrder.size() > 0){


                    for ( ForceOrder forceOrder1 :forceOrder) {

                        List<ForceProduct> forceProducts =  forceProductService.findForceProductByForceId(forceOrder1.getId());

                        if(forceProducts.size() > 0){

                            for (ForceProduct forceProduct:forceProducts) {


                                if(forceProduct.getPronum().equals(transportationProduct.getPronum())){
                                    forceProduct.setOntheway(symbol ? transportationProduct.getThistimeontheway()+forceProduct.getOntheway(): forceProduct.getOntheway() - transportationProduct.getThistimeontheway() );
                                    forceProductService.updateNotNull(forceProduct);
                                }


                            }

                        }


                    }


                }


            }


            {

                //修改产品库存在途库存
                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(transportationProduct.getDepotnum(),transportationProduct.getPronum());

                if(productStock != null){

                    //修改在途库存
                    productStock.setOnthewaynum( symbol ? productStock.getOnthewaynum()+transportationProduct.getThistimeontheway() : productStock.getOnthewaynum()-transportationProduct.getThistimeontheway());
                    //修改采购未入
                    productStock.setPurchasenum(symbol ? productStock.getPurchasenum()+transportationProduct.getThistimeontheway() : productStock.getPurchasenum()-transportationProduct.getThistimeontheway());

                    productStockService.updateNotNull(productStock);

                }

            }


            return true;

        }else{
            alert_informationDialog("本次在途数量不得大于未在途数量");
            return  false;
        }

    }









    /**
     * 上下首尾跳页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findInquiry(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);//修改为修改状态
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findInquiry(int pageNum){

        List<TransportationInventory> transportationInventories = transportationInventoryService.findTransportationInventory(pageNum,1);

        PageInfo<TransportationInventory> pageInfo = new PageInfo<>(transportationInventories);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        transportationInventories.forEach(forceOrder1 ->loadDate(forceOrder1));



        if(transportationInventories.size()<=0){
            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(true);

            deletevbox.setDisable(true);

            orders.setUserData(0L);

            inquiryProduct();
            //权限管理
            if(!lockOrder)matchingPermissions("在途库存",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);
        }



    }


    /**
     * 向控件加载数据
     * @param transportationInventory
     */
    public void loadDate(TransportationInventory transportationInventory){

        if(!lockOrder)orders.setUserData(transportationInventory.getId());
        //装箱单号
        if(!lockOrder)orders.setText(transportationInventory.getOrders());
        //发货日期
        senddate.setValue(LocalDateTime.ofInstant(transportationInventory.getSenddate().toInstant(), ZoneId.systemDefault()).toLocalDate());
        //发票号码
        invoicenum.setText(transportationInventory.getInvoicenum());
        //预计到货日期
        comedate.setValue(LocalDateTime.ofInstant(transportationInventory.getComedate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        if(!lockOrder){

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(false);

            deletevbox.setDisable(false);

        }

                inquiryProduct();


        //权限管理
        if(!lockOrder)matchingPermissions("在途库存",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);
    }


    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        orders.setDisable(!status);

        senddate.setDisable(!status);

        invoicenum.setDisable(!status);

        comedate.setDisable(!status);

        transportationProduct.setEditable(status);

//        importData.setDisable(!status);


    }



    /**
     * 清空
     */
    public void clearValue(){

        orders.setText(NumberUtil.NULLSTRING);

        invoicenum.setText(NumberUtil.NULLSTRING);

        totalNum.setText("0");

        transportationProduct.getItems().clear();

    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) orders.getUserData();
            int rows = transportationInventoryService.delete(id);

            List<TransportationProduct> transportationProducts =  transportationProductService.findTransportationProductByParentid(id);

            for (TransportationProduct transportationProduct:transportationProducts) {
                transportationProductService.delete(transportationProduct.getId());
            }
            relationService.deleteRelation("在途库存",id);
            if (rows > 0) {
                findInquiry(1); //初始化第一条数据
            }
            NumberUtil.changeStatus(fxmlStatus, 0); //修改为修改状态
            this.changeEditable(false);
        }
    }



    /**
     * 修改
     */
    public void update(){


//        TransportationInventory transportationInventory = transportationInventoryService.selectByKey((long)orders.getUserData());
//
//        PurchaseStock purchaseStock = purchaseStockService.findPurchanseStockBoxNum(transportationInventory.getOrders());

        boolean alertLuck = true;

        for (TransportationProductProperty transportationProductProperty:transportationProductProperties) {
                if(transportationProductProperty .getTotalnum() != transportationProductProperty.getStockover() + transportationProductProperty.getForcenum()){
                    alertLuck = false;
                }
        }


        if(alertLuck){
            alert_informationDialog("订单已完成不得修改。");
        }else {
            NumberUtil.changeStatus(fxmlStatus,NumberUtil.UPDATE);//修改为修改状态
            this.changeEditable(true);
//            blankTransportationProduct();
            submitvbox.setDisable(false);
            insertvbox.setDisable(true);
            deletevbox.setDisable(true);
            updatevbox.setDisable(true);
        }


//        /**
//         * 根据采购订单来判断
//         */
//        List<PurchaseStockProduct> purchaseStockProducts =  purchaseStockProductService.findPurchaseStockProductByPurchaseOrder(purchaseOrderNum);

//        if(purchaseStockProducts != null && purchaseStockProducts.size() > 0){
//
//
//            PurchaseStock purchaseStock = purchaseStockService.selectByKey(purchaseStockProducts.get(0).getPurchasestockid());
//
//
//
//
//        }else {
//            NumberUtil.changeStatus(fxmlStatus,NumberUtil.UPDATE);//修改为修改状态
//            this.changeEditable(true);
//            blankTransportationProduct();
//            submitvbox.setDisable(false);
//            insertvbox.setDisable(true);
//            deletevbox.setDisable(true);
//            updatevbox.setDisable(true);
//        }

    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件
//        blankTransportationProduct();
        setDatePicker(senddate);
        setArrivalDate(senddate,comedate,10);
        submitvbox.setDisable(false);
        updatevbox.setDisable(true);
        deletevbox.setDisable(true);
        insertvbox.setDisable(true);
    }


    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

        LocalDate orderDate =senddate.getValue();

        String supperIsnum =orders.getText();

        if(supperIsnum == "" || "".equals(supperIsnum)){
            alert_informationDialog("请填写装箱单号!");
            return;
        }
        if(orderDate == null){
            alert_informationDialog("请填写发货日期!");
            return;
        }

        TransportationInventory transportationInventory = new TransportationInventory(supperIsnum,
                new Date(java.sql.Date.valueOf(orderDate).getTime()),
                invoicenum.getText(),
                new Date(java.sql.Date.valueOf(comedate.getValue()).getTime())
                );

        int rows =0;
        if(submitStuts==1){
            rows = transportationInventoryService.save(transportationInventory);

           if(relationLock){
               //添加关联关系
               relation.setRelationName("在途库存");
               relation.setRelationId(transportationInventory.getId());
               relationService.save(relation);
           }

        }else if(submitStuts ==2){
            transportationInventory.setId((long)orders.getUserData());
            rows = transportationInventoryService.updateNotNull(transportationInventory);
        }




        if(saveTransportationProduct(transportationInventory.getId())){
            this.loadDate(transportationInventory); //重新加载数据
            NumberUtil.changeStatus(fxmlStatus,0);
            submitvbox.setDisable(true);
        }else {
            orders.setUserData(transportationInventory.getId());
            NumberUtil.changeStatus(fxmlStatus,NumberUtil.UPDATE);//修改为修改状态
        }

    }


    //在途产品表格处理
    public void transportationProductKey(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {

            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                transportationProductService.delete(changeId);


                ObservableList<TransportationProductProperty> transportationProductPropertiesNew = FXCollections.observableArrayList();

                for (TransportationProductProperty transportationProductProperty : transportationProductProperties) {
                    if (transportationProductProperty.getId() != changeId) {
                        transportationProductPropertiesNew.add(transportationProductProperty);
                    }
                }

                transportationProductProperties.clear();
                transportationProductProperties.setAll(transportationProductPropertiesNew);
            }
        }


        if (event.getCode() == KeyCode.INSERT) {
            //联系人空白行
            /* blankContact();*/
            blankTransportationProduct();
        }

    }

    public void findPurchaseSearch(){
        String pageSizes =  purchase_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreInquiryImport(1);
        }




    }


    public void findPurchasePages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreInquiryImport(pageNum);
    }


    public void importButtonInquiry(){

        stage.setTitle("导入-采购订单");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/purchase_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreInquiryImport(1);
    }

    public void loadMoreInquiryImport(int pageNum){

        List<PurchaseOrders> purchaseOrders = purchaseOrdersService.findPurchaseOrders(transportationCheckBox.isSelected() ? 1 : 0,pageNum,pageSize);

        PageInfo<PurchaseOrders> pageInfo = new PageInfo<>(purchaseOrders);

        purchase_find_fast.setUserData(1); //首页

        purchase_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        purchase_find_next.setUserData(pageInfo.getNextPage());//下一页

        purchase_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<PurchaseOrders> list = FXCollections.observableArrayList();


//        inquiryid.setCellValueFactory(new PropertyValueFactory("id"));
        findinquiryorder.setCellValueFactory(new PropertyValueFactory("orders"));
        findinquirydata.setCellValueFactory(new PropertyValueFactory("paremdate"));
        findsuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findstatus.setCellValueFactory(new PropertyValueFactory("strstatus"));
        findcomestock.setCellValueFactory(new PropertyValueFactory("warehousename"));


        for (PurchaseOrders purchaseOrders1:purchaseOrders) {

            purchaseOrders1.setParemdate(new SimpleDateFormat("yyyy-MM-dd").format(purchaseOrders1.getCreatedate()));

            if(purchaseOrders1.getShstatus()==0){
                purchaseOrders1.setStrstatus("未审核");
            }else{
                purchaseOrders1.setStrstatus("已审核");
            }

            list.add(purchaseOrders1);

        }

        inquiryView.setItems(list); //tableview添加list

        inquiryView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseOrders>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseOrders> observableValue, PurchaseOrders oldItem, PurchaseOrders newItem) {
                if(newItem != null && newItem.getId()>0){
                    loadMoreInquiryProductImport(newItem.getId());
                }
            }
        });
    }



    public void loadMoreInquiryProductImport(long id){
        inquiryOrderId=id;
        List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(id);
        inquiryProductView.setEditable(true);
        inquiryproid.setCellFactory(CheckBoxTableCell.forTableColumn(inquiryproid));

        inquiryproid.setCellValueFactory(new PropertyValueFactory("checked"));
        findproid.setCellValueFactory(new PropertyValueFactory("proisnum"));
        finprosort.setCellValueFactory(new PropertyValueFactory("sort"));
        findproname.setCellValueFactory(new PropertyValueFactory("proname"));
        finpronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        findproprice.setCellValueFactory(new PropertyValueFactory("proprice"));

        inquiryImportProperties.clear();


        for (PurchaseProduct inquiryProduct:purchaseProducts) {

            InquiryImportProperty inquiryImportProperty = new InquiryImportProperty(false,inquiryProduct.getId(),inquiryProduct.getProorders(),inquiryProduct.getSort(),inquiryProduct.getProname(),inquiryProduct.getNum(),inquiryProduct.getPrice());

            inquiryImportProperties.add(inquiryImportProperty);

        }

        inquiryProductView.setItems(inquiryImportProperties); //tableview添加list

    }



    //确定导入产品
    public  void importInquiryProductData(){

        PurchaseOrders purchaseOrders = purchaseOrdersService.selectByKey(inquiryOrderId);


        //存储被关联单据
        relation.setBeRelationName("采购订单");
        relation.setBeRelationId(purchaseOrders.getId());
        relationLock = true;
        if(purchaseOrders.getShstatus() == 1){

            int totalNums = totalNum.getText() == null ? 0 : Integer.parseInt(totalNum.getText());

            for(InquiryImportProperty inquiryImportProperty : inquiryImportProperties){

//                boolean aoutationLock = true;

                //选中导入的产品
                if(inquiryImportProperty.isChecked()){
                    PurchaseProduct purchaseProduct = purchaseProductService.selectByKey(inquiryImportProperty.getId());

//                    for (TransportationProductProperty transportationProductProperty :transportationProductProperties) {
//                        if( transportationProductProperty.getPronum() != null && transportationProductProperty.getPronum().equals(inquiryImportProperty.getProisnum())){
//                            alert_informationDialog("产品"+inquiryImportProperty.getProisnum()+"重复!");
//                            aoutationLock  =  false;
//                        }
//                    }
                    int ontheway = purchaseProduct.getNum()-(purchaseProduct.getStockover()+purchaseProduct.getForcenum()+purchaseProduct.getOntheway());

                    totalNums += ontheway;
//                    if(aoutationLock){
                        TransportationProductProperty purchaseProductProperty = new TransportationProductProperty(0,purchaseOrders.getOrders(),purchaseProduct.getSort(),purchaseOrders.getSeeorders(),purchaseProduct.getProorders(),purchaseProduct.getProname(),purchaseOrders.getWarehousename(),"",ontheway,purchaseProduct.getNum(),purchaseProduct.getStockover(),purchaseProduct.getForcenum(),purchaseProduct.getOntheway(),purchaseProduct.getDepotnum(),purchaseProduct.getDepotname(),purchaseProduct.getFloor(),purchaseProduct.getRemarks());
                        transportationProductProperties.add(purchaseProductProperty);
//                    }
                }
            }

            totalNum.setText(totalNums+"");
        }else {
            alert_informationDialog(AppConst.ALERT_EXAMINE);
        }

        coseInquiryWin();
    }



    //导出在途库存 --  到采购入库
    public void exportPurchaseStockOrder(){

        TransportationInventory transportationInventory = transportationInventoryService.selectByKey((long)orders.getUserData());

        if(transportationInventory != null){

                changeHomeBtn(2,3,1);

                StageManager.transportationInventory = transportationInventory;

                StageManager.transportationProducts = transportationProductService.findTransportationProductByParentid(transportationInventory.getId());

                StageManager.purchaseProducts = purchaseProductService.findPurchaseProduct((long)orders.getUserData());

                Pane pane1 = StageManager.ORDERCONTENT.get("stockPane");

                pane1.getChildren().setAll(loader.load("/fxml/stock/purchase_stock.fxml"));


        }else{
            alert_informationDialog("暂未单据导出!");
        }


    }



    public void copeCurrentOrder(){

        insert();

        long orderId =  (long)orders.getUserData();

        TransportationInventory transportationInventory = transportationInventoryService.selectByKey(orderId);

        lockOrder = true;

        loadDate(transportationInventory);



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //初始化

        String newStr = location.toString();

        int index = newStr.indexOf("ontheway.fxml");

        if(index != -1){
            relation = new Relation();
            relationLock = false;

            findInquiry(1);
        }

    }
}
