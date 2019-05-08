package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.model.purchase.*;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.model.stock.PurchaseStockProductProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.basic.SupplierBasicService;
import com.yc.education.service.purchase.PurchaseOrdersService;
import com.yc.education.service.purchase.PurchaseProductService;
import com.yc.education.service.purchase.TransportationInventoryService;
import com.yc.education.service.purchase.TransportationProductService;
import com.yc.education.service.stock.AffairManageService;
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
import java.util.*;

/**
 * @ClassName PurchaseStockController
 * @Description TODO 采购入库单
 * @Author QuZhangJing
 * @Date 2018/10/24 15:54
 * @Version 1.0
 */
@Controller
public class PurchaseStockController extends BaseController implements Initializable {

    @Autowired
    private PurchaseStockService purchaseStockService;

    @Autowired
    private PurchaseStockProductService purchaseStockProductService;

    @Autowired
    private PurchaseOrdersService purchaseOrdersService;

    @Autowired
    private PurchaseProductService purchaseProductService;

    @Autowired
    private SupplierBasicService supplierBasicService;

    @Autowired
    private DepotBasicService depotBasicService;

    @Autowired
    private DataSettingService dataSettingService;

    @Autowired
    private ProductBasicService productBasicService;

    @Autowired
    private ProductStockService productStockService;

    @Autowired
    private TransportationInventoryService transportationInventoryService; //在途库存

    @Autowired
    private TransportationProductService transportationProductService;//在途库存产品

    @Autowired
    private AffairManageService affairManageService;
    @Autowired
    private RelationService relationService;




    @FXML
    private Label fxmlStatus; //状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox clearvbox; //清除

    @FXML private VBox submitvbox; //提交

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox printingvbox; //删除

    @FXML private VBox shyes; //审核

    @FXML private VBox shno; //取消审核

    @FXML private VBox importData;//导入


    @FXML private DatePicker createdate; //制单日期

    @FXML private TextField stockorder; //入库单号

    @FXML private TextField depotnum; //入库仓库编号

    @FXML private TextField depotname; //入库仓库名称

//  @FXML private TextField depotfloor;//楼层

    @FXML private TextField suppliernum; //供应商编号

    @FXML private TextField suppliername;//供应商名称

    @FXML private TextField boxnum; //装箱单号

    @FXML private ComboBox inspectnum; //质检人编号

    @FXML private TextField inspectname;  //质检人名称

    @FXML private TextField createname; //制单人

    @FXML private TextField shpeople; //审核人

    @FXML private TextField shdate; //审核时间

    @FXML private TextField updatepeople; //最后修改人

    @FXML private TextField updatedate; //最后修改日期

    @FXML private TextArea remarks; //备注

    @FXML private Label costShow; //未成本核算字样


    @FXML private TableView purchaseStockProductView;//采购入库单产品
    @FXML private TableColumn pro_sourseorder; //来源单据
    @FXML private TableColumn pro_orders;//单号
    @FXML private TableColumn pro_sort;//序号
    @FXML private TableColumn pro_seeorder;//参考单号
    @FXML private TableColumn pro_pronum;//产品编号
    @FXML private TableColumn pro_proname;//产品名称
    @FXML private TableColumn pro_stocknum;//入库数量
    @FXML private TableColumn pro_units;//单位
    @FXML private TableColumn pro_depotnum;//仓库编号
    @FXML private TableColumn depotbutton;//仓库编号
    @FXML private TableColumn pro_depotname;//仓库名称
    @FXML private TableColumn pro_floor;//楼层
    @FXML private TableColumn pro_boxnum;//备注
    @FXML private TableColumn pro_remarks;//备注



    private long changeId = 0;


    private long inquiryOrderId =0;

    private String productNum = "";

    private int selectIndex = 0;


    //导入--询价单据列表
    @FXML private TableView inquiryView;
    @FXML private TableColumn inquiryid; //单据ID
    @FXML private TableColumn findinquiryorder; //单据编号
    @FXML private TableColumn findinquirydata; //制单日期
    @FXML private TableColumn findsuppliernum; //供应商编号
    @FXML private TableColumn findstatus; //单据状态
    @FXML private TableColumn findcomestock; //到货仓库



    //导入--询价单据产品
    @FXML private TableView inquiryProductView;
    @FXML private TableColumn inquiryproid; //询价单产品ID
    @FXML private TableColumn findproid; //产品编号
    @FXML private TableColumn finprosort; //产品序号
    @FXML private TableColumn findproname; //产品名称
    @FXML private TableColumn finpronum; //数量
    @FXML private TableColumn findproprice; //单价



    //查询更多供应商
    @FXML private TableView tableView3; //供应商查询
    @FXML private TableColumn supid; //id
    @FXML private TableColumn findsupplierid; //供应商编号
    @FXML private TableColumn findsuppliername; //供应商简称

    //采购入库单
    @FXML private TableView purchaseStockView; //采购入库单
    @FXML private TableColumn findid; //  编号
    @FXML private TableColumn findstockorder; //采购入库单
    @FXML private TableColumn findcreatedate; //制单日期
    @FXML private TableColumn findstocksuppliernum; //供应商编号
    @FXML private TableColumn findstocksuppliername; //供应商简称
    @FXML private TableColumn findboxnum; //装箱单号
    @FXML private TableColumn findremarks; //备注

    //仓库库位
    @FXML private TableView depotView; //仓库库位
    @FXML private TableColumn depid; //  编号
    @FXML private TableColumn finddepotid; //  仓库编号
    @FXML private TableColumn finddepotname; //  仓库名称


    @FXML private TableView houseView; //仓库库位
    @FXML private TableColumn findhousenum; //  库位编号
    @FXML private TableColumn findhousename; //  库位名称
    @FXML private TableColumn findparent; //  所属仓库
    @FXML private TableColumn findhousefloor; //  楼层
    @FXML private TableColumn depotNums;//库存数量



    //采购入库单产品之TableView数据绑定
    private ObservableList<PurchaseStockProductProperty> purchaseStockProductProperties = FXCollections.observableArrayList();

    //采购订单产品导入
    private ObservableList<InquiryImportProperty> importProperties = FXCollections.observableArrayList();

    private Stage stage = new Stage();

    private Stage stage2 = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @FXML private VBox purchaseStock_find_fast;
    @FXML private VBox purchaseStock_find_prev;
    @FXML private VBox purchaseStock_find_next;
    @FXML private VBox purchaseStock_find_last;

    @FXML private TextField purchaseStock_textField_pageSize;

    @FXML private TextField purchaseStock_order_textField;

    private int pageSize = 10;


    @FXML private VBox dataSetting_find_fast;
    @FXML private VBox dataSetting_find_prev;
    @FXML private VBox dataSetting_find_next;
    @FXML private VBox dataSetting_find_last;

    @FXML private TextField dataSetting_textField_pageSize;

    @FXML private TextField dataSetting_order_textField;



    @FXML private VBox supplier_find_fast;
    @FXML private VBox supplier_find_prev;
    @FXML private VBox supplier_find_next;
    @FXML private VBox supplier_find_last;

    @FXML private TextField supplier_textField_pageSize;

    @FXML private TextField supplier_order_textField;



    @FXML private VBox depot_find_fast;
    @FXML private VBox depot_find_prev;
    @FXML private VBox depot_find_next;
    @FXML private VBox depot_find_last;

    @FXML private TextField depot_textField_pageSize;



    @FXML private VBox purchaseOrder_find_fast;
    @FXML private VBox purchaseOrder_find_prev;
    @FXML private VBox purchaseOrder_find_next;
    @FXML private VBox purchaseOrder_find_last;

    @FXML private TextField purchaseOrder_textField_pageSize;

    @FXML private TextField purchaseOrder_order_textField;

    @FXML private CheckBox purchaseCheckBox;



    /**
     * 单据关联容器
     */
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;

    @FXML private CheckBox viewAll;

    boolean selectLock = false;


    @FXML private TextField  totalNum;

    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
    public String createIsnum(String currentDate){
        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
        String maxIsnum = purchaseStockService.selectMaxIdnum(currentDate);
        if(maxIsnum != null){
            String maxAlphabet = maxIsnum.substring(0,1);
            //180928 0001
            int currenNumber = Integer.parseInt(maxIsnum.substring(maxIsnum.length()-4,maxIsnum.length()));
            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){
                if(currenNumber == 9999 ){
                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
                        return NumberUtil.ALPHABET[i+1]+"0001";
                    }
                }
            }
            if(currenNumber>0 && currenNumber < 9){
                return maxAlphabet +newDateStr+"000"+(currenNumber+1);
            }else if(currenNumber >= 9 && currenNumber< 99){
                return maxAlphabet+newDateStr +"00"+(currenNumber+1);
            }else if(currenNumber >= 99 && currenNumber< 999){
                return maxAlphabet+newDateStr +"0"+(currenNumber+1);
            }else{
                    return maxAlphabet+newDateStr+(currenNumber+1);
            }
        }
        return "A"+newDateStr+"0001";
    }


    public void findDataSettingSearch(){
        String pageSizes =  dataSetting_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreDepartment(1);
        }

    }


    public void findDataSettingPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreDepartment(pageNum);
    }

    public void morePurchaseStockDepotClick(){

        stage.setTitle("现有仓库查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/purchase_stock_depot_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreDepartment(1);
    }


    /**
     * 现有库位查询
     */
    public void loadMoreDepartment(int pageNum){

//        List<DepotBasic> departmentBasics = depotBasicService.selectDepotBasic();

        List<DataSetting> dataSettings = dataSettingService.findDataSetting(33L,"".equals(dataSetting_order_textField.getText()) || dataSetting_order_textField.getText() ==  null ? "" :dataSetting_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<DataSetting> pageInfo = new PageInfo<>(dataSettings);

        dataSetting_find_fast.setUserData(1); //首页

        dataSetting_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        dataSetting_find_next.setUserData(pageInfo.getNextPage());//下一页

        dataSetting_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<DataSetting> list = FXCollections.observableArrayList();


        /*depid.setCellValueFactory(new PropertyValueFactory("id"));*/
        finddepotid.setCellValueFactory(new PropertyValueFactory("id"));
        finddepotname.setCellValueFactory(new PropertyValueFactory("title"));

        for (DataSetting dataSetting:dataSettings) {

            list.add(dataSetting);

        }

        depotView.setItems(list); //tableview添加list

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
                closeDepotWin();
            }
        });


    }

    public void closeDepotWin(){
        long id =(long)depotView.getUserData();
//        DepotBasic depotBasic =  depotBasicService.selectByKey(id);
        DataSetting dataSetting = dataSettingService.selectByKey(id);

        depotnum.setText(dataSetting.getId()+""); //入库仓库编号

        depotnum.setUserData(dataSetting.getId());

        depotname.setText(dataSetting.getTitle()); //入库仓库名称

//        depotfloor.setText(depotBasic.getDepfloor()); //楼层



        coseWin();
    }


    public void loadDepot(){
        loadMoreHouse(1,1);
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
        pane.getChildren().setAll(loader.load("/fxml/stock/purchase_stock_depot_house_find.fxml"));
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

//        String depotId = depotnum.getText();

       if(status == 0){

           if(!"".equals(productNum) && !selectLock){

               ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productNum);

//               if(!"".equals(depotId)){
//
//                   depotBasicList = depotBasicService.selectDepotBasicByParentId(Long.valueOf(depotId),"",pageNum,pageSize);
//
//               }else

               if(productBasic != null){
                   String[] storehouse = productBasic.getInventoryplace().split(",");

                   for (int i=0,len=storehouse.length;i<len;i++){

                       DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(storehouse[i]);

                       depotBasicList.add(depotBasic);
                   }
               }else{
                   depotBasicList = depotBasicService.selectDepotBasic("",pageNum,pageSize);
               }

           }else{
               depotBasicList = depotBasicService.selectDepotBasic("",pageNum,pageSize);
           }

       }else{


//           if(!"".equals(depotId)){
//
//               depotBasicList = depotBasicService.selectDepotBasicByParentId(Long.valueOf(depotId),"",pageNum,pageSize);
//
//           }
               depotBasicList = depotBasicService.selectDepotBasic("",pageNum,pageSize);
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

        for (DepotBasic depotBasic:depotBasicList) {

             DataSetting dataSetting =  dataSettingService.selectByKey(depotBasic.getParentid());

            if(dataSetting != null)depotBasic.setRemarks(dataSetting.getTitle());

            ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(depotBasic.getIsnum(),productNum);

            if(productStock != null){
                depotBasic.setStockNum(productStock.getStocknum());
            }else{
                depotBasic.setStockNum(0);
            }

            list.add(depotBasic);

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

        for(int i=0,len=purchaseStockProductProperties.size();i<len;i++){

            if(selectIndex == i){

                purchaseStockProductProperties.get(i).setFloor(depotBasic.getDepfloor());

                purchaseStockProductProperties.get(i).setDepotnum(depotBasic.getIsnum());

                purchaseStockProductProperties.get(i).setDepotname(depotBasic.getDepname());

            }
        }

        coseWin();
    }


    public void findPurchaseStockSearch(){
        String pageSizes =  purchaseStock_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMorePurchaseStock(1);
        }
    }


    public void findPurchaseStockPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMorePurchaseStock(pageNum);
    }


        /**
         * 查询更多采购入库单
         */
        public void morePurchaseStockClick(){

            stage.setTitle("现有采购入库单");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/stock/purchase_stock_find.fxml"));
            Scene scene = new Scene(pane);
            stage.setScene(scene);
                /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
            stage.show();
            pageSize = 10;
            loadMorePurchaseStock(1);
        }


        public void loadMorePurchaseStock(int pageNum){

            List<PurchaseStock> purchaseStocks  =  purchaseStockService.findPurchaseStock("".equals(purchaseStock_order_textField.getText()) || purchaseStock_order_textField.getText() == null ? "" :purchaseStock_order_textField.getText().toString(),pageNum,pageSize);

            PageInfo<PurchaseStock> pageInfo = new PageInfo<>(purchaseStocks);

            purchaseStock_find_fast.setUserData(1); //首页

            purchaseStock_find_prev.setUserData(pageInfo.getPrePage()); //上一页

            purchaseStock_find_next.setUserData(pageInfo.getNextPage());//下一页

            purchaseStock_find_last.setUserData(pageInfo.getPages());//尾页

            ObservableList<PurchaseStock> list =FXCollections.observableArrayList();

//            findid.setCellValueFactory(new PropertyValueFactory("id"));
            findstockorder.setCellValueFactory(new PropertyValueFactory("stockorder"));
            findcreatedate.setCellValueFactory(new PropertyValueFactory("createDateStr"));
            findstocksuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
            findstocksuppliername.setCellValueFactory(new PropertyValueFactory("suppliername"));
            findboxnum.setCellValueFactory(new PropertyValueFactory("boxnum"));
            findremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

            if(purchaseStocks.size()>0){

                for (PurchaseStock purchaseStock:purchaseStocks) {
                    purchaseStock.setCreateDateStr(DateToString(purchaseStock.getCreatedate()));
                    list.add(purchaseStock);
                }

            }

            purchaseStockView.setItems(list);


            purchaseStockView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseStock>() {
                @Override
                public void changed(ObservableValue<? extends PurchaseStock> observableValue, PurchaseStock oldItem, PurchaseStock newItem) {
                    if(newItem.getStockorder() != null && !("".equals(newItem.getStockorder()))){
                        purchaseStockView.setUserData(newItem.getId());
                    }
                }
            });


            purchaseStockView.setOnMouseClicked((MouseEvent t) -> {
                if (t.getClickCount() == 2) {
                    closePurchaseStockWin();
                }
            });


        }


    public void closePurchaseStockWin(){
            long id =(long)purchaseStockView.getUserData();
            PurchaseStock purchaseStock =  purchaseStockService.selectByKey(id);
            loadDate(purchaseStock);
        coseWin();
    }




    public void findSupplierSearch(){
        String pageSizes =  supplier_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreSupplier(1);
        }




    }


    public void findSupplierPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreSupplier(pageNum);
    }


    /**
     * 点击弹出 现有供应商查询
     */
    public void moreSupplierClick(){

        stage.setTitle("现有供应商查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/purchase_stock_supplier_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreSupplier(1);
    }


    /**
     * 现有供应商查询
     */
    public void loadMoreSupplier(int pageNum){

//           List<DataSetting> dataSettings = dataSettingService.findDataSetting(33L);

        List<SupplierBasic> supplierBasics = supplierBasicService.selectSupplierBasic("".equals(supplier_order_textField.getText()) || supplier_order_textField.getText() ==  null ? "" : supplier_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<SupplierBasic> pageInfo = new PageInfo<>(supplierBasics);

        supplier_find_fast.setUserData(1); //首页

        supplier_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        supplier_find_next.setUserData(pageInfo.getNextPage());//下一页

        supplier_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<SupplierBasic> list =FXCollections.observableArrayList();


        tableView3.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        supid.setCellValueFactory(new PropertyValueFactory("id"));
        findsupplierid.setCellValueFactory(new PropertyValueFactory("idnum"));
        findsuppliername.setCellValueFactory(new PropertyValueFactory("supdes"));

        for (SupplierBasic supplierBasics1:supplierBasics) {

            list.add(supplierBasics1);

        }

        tableView3.setItems(list); //tableview添加list

        tableView3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierBasic>() {
            @Override
            public void changed(ObservableValue<? extends SupplierBasic> observableValue, SupplierBasic oldItem, SupplierBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    suppliernum.setUserData(newItem.getId());
                }
            }
        });


        tableView3.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)suppliernum.getUserData();
        SupplierBasic supplierBasic =  supplierBasicService.selectByKey(id);
        suppliernum.setText(supplierBasic.getIdnum());
        suppliername.setText(supplierBasic.getSupdes());
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }







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

        List<PurchaseStock> purchaseStock = purchaseStockService.findPurchaseStock(pageNum,1);

        PageInfo<PurchaseStock> pageInfo = new PageInfo<>(purchaseStock);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        purchaseStock.forEach(inquiryOrder ->loadDate(inquiryOrder));





        if(purchaseStock.size()<=0){

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(false);

            deletevbox.setDisable(false);

         loadPurchaseStockProduct(0);

            //权限管理
            matchingPermissions("采购入库单",insertvbox,deletevbox,updatevbox,shno,shyes,printingvbox,clearvbox,0);


        }



    }


    /**
     * 向控件加载数据
     * @param purchaseStock
     */
    public void loadDate(PurchaseStock purchaseStock){

        stockorder.setUserData(purchaseStock.getId());

        createdate.setValue(LocalDateTime.ofInstant(purchaseStock.getCreatedate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        stockorder.setText(purchaseStock.getStockorder());

        depotnum.setText(purchaseStock.getDepotnum());

        depotname.setText(purchaseStock.getDepotname());


//        depotfloor.setText(purchaseStock.getDepotfloor());

        suppliernum.setText(purchaseStock.getSuppliernum());

        suppliername.setText(purchaseStock.getSuppliername());

        boxnum.setText(purchaseStock.getBoxnum());

        inspectnum.getSelectionModel().select(0);

        inspectname.setText(purchaseStock.getInspectname());

        createname.setText(purchaseStock.getCreatename());

        shpeople.setText(purchaseStock.getShpeople());

        shdate.setText(purchaseStock.getShdate());

        updatepeople.setText(purchaseStock.getUpdatepeople());

        updatedate.setText(purchaseStock.getUpdatedate());

        remarks.setText(purchaseStock.getRemarks());

        if(purchaseStock.getCost()){
            costShow.setText(AppConst.COST_PASS);
        }else{
            costShow.setText(AppConst.COST_NO);
        }


        changeEditable(false);

//        submitvbox.setDisable(true);
//
//        insertvbox.setDisable(false);
//
//        updatevbox.setDisable(false);
//
//        deletevbox.setDisable(false);


        int isSh = purchaseStock.getShstatus();

//        if(isSh == 0){
//
//            shyes.setDisable(false);
//            shno.setDisable(true);
//
//        }else{
//
//            //已审核不得修改和删除
//            updatevbox.setDisable(true);
//            deletevbox.setDisable(true);
//
//            shyes.setDisable(true);
//            shno.setDisable(false);
//
//        }


        loadPurchaseStockProduct(purchaseStock.getId());


        //权限管理
        matchingPermissions("采购入库单",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,isSh);


    }






    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        createdate.setDisable(!status);

        stockorder.setDisable(!status);

        depotnum.setDisable(!status);

        depotname.setDisable(!status);

//        depotfloor.setDisable(!status);

        suppliernum.setDisable(!status);

        suppliername.setDisable(!status);

        boxnum.setDisable(!status);

        inspectnum.setDisable(!status);

        inspectname.setDisable(!status);

        createname.setDisable(!status);

        shpeople.setDisable(!status);

        shdate.setDisable(!status);

        updatepeople.setDisable(!status);

        updatedate.setDisable(!status);

        remarks.setDisable(!status);

        purchaseStockProductView.setEditable(status);

        importData.setDisable(!status);

    }



    /**
     * 清空
     */
    public void clearValue(){

        createdate.setValue(null);

        stockorder.setText(NumberUtil.NULLSTRING);

        depotnum.setText(NumberUtil.NULLSTRING);

        depotname.setText(NumberUtil.NULLSTRING);

//        depotfloor.setText(NumberUtil.NULLSTRING);

        suppliernum.setText(NumberUtil.NULLSTRING);

        suppliername.setText(NumberUtil.NULLSTRING);

        boxnum.setText(NumberUtil.NULLSTRING);

        inspectnum.getSelectionModel().select(0);

        inspectname.setText(NumberUtil.NULLSTRING);

        createname.setText(NumberUtil.NULLSTRING);

        shpeople.setText(NumberUtil.NULLSTRING);

        shdate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        totalNum.setText("0");

    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) stockorder.getUserData();
            List<PurchaseStockProduct> purchaseStockProduct = purchaseStockProductService.findStockProductBypurchaseStockId(id);
            purchaseStockProduct.forEach(purchaseStockProduct1 -> {
                purchaseStockProductService.delete(purchaseStockProduct1.getId());
            });
            relationService.deleteRelation("采购入库单",id);
            int rows = purchaseStockService.delete(id);
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
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.UPDATE);//修改为修改状态
        this.changeEditable(true);

        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期
        //联系人空白行
//        blankPurchaseStockProduct();


    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件
        submitvbox.setDisable(false);
        updatevbox.setDisable(true);
        deletevbox.setDisable(true);
        insertvbox.setDisable(true);
        createPeople(createname);//制单人
        setDatePicker(createdate);
        purchaseStockProductProperties.clear();
//        blankPurchaseStockProduct();

    }

//    /**
//     * 验证产品数量
//     */
//    public  void checkPurchseProductNum(){
//
//
//
//
//    }

    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

        LocalDate orderDate =createdate.getValue();

        String supperIsnum =suppliernum.getText();

        if(supperIsnum == "" || "".equals(supperIsnum)){
            alert_informationDialog("请填写供应商编号!");
            return;
        }
        if(orderDate == null){
            alert_informationDialog("请填写制单日期!");
            return;
        }

            PurchaseStock purchaseStock = new PurchaseStock(
                    new Date(java.sql.Date.valueOf(orderDate).getTime()),
                    stockorder.getText(),
                    depotnum.getText(),
                    depotname.getText(),
                    "",
                    suppliernum.getText(),
                    suppliername.getText(),
                    boxnum.getText(),
                    inspectnum.getItems().size()==0?"":inspectnum.getSelectionModel().getSelectedItem().toString(),
                    inspectname.getText(),
                    createname.getText(),
                    shpeople.getText(),
                    shdate.getText(),
                    updatepeople.getText(),
                    updatedate.getText(),
                    remarks.getText(),0
            );


        int rows =0;
        if(submitStuts==1){
            //产生订单编号
            String orderNum = createIsnum(orderDate.toString());
            stockorder.setText(orderNum);
            purchaseStock.setStockorder(orderNum);
            purchaseStock.setCost(false);
            purchaseStock.setAddtime(new Date());
            rows = purchaseStockService.save(purchaseStock);
            if(relationLock) {
                //添加关联关系
                relation.setRelationName("采购入库单");
                relation.setRelationId(purchaseStock.getId());
                relationService.save(relation);
            }
        }else if(submitStuts ==2){
            purchaseStock.setId((long)stockorder.getUserData());
            rows = purchaseStockService.updateNotNull(purchaseStock);
        }

        try {
            savePurchaseStockProduct(purchaseStock.getId(),submitStuts,relation.getId());
            NumberUtil.changeStatus(fxmlStatus,0);
            submitvbox.setDisable(true);

            this.loadDate(purchaseStock); //重新加载数据

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(false);

            deletevbox.setDisable(false);
        } catch (Exception e) {

        }

    }







    public void loadPurchaseStockProduct(long id){

        List<PurchaseStockProduct> purchaseStockProducts = purchaseStockProductService.findStockProductBypurchaseStockId(id);


        Callback<TableColumn<PurchaseStockProduct, String>, TableCell<PurchaseStockProduct, String>> buttonFactory
                = new Callback<TableColumn<PurchaseStockProduct, String>, TableCell<PurchaseStockProduct, String>>() {
            @Override
            public TableCell call(final TableColumn<PurchaseStockProduct, String> param) {
                final TableCell<PurchaseStockProduct, String> cell = new TableCell<PurchaseStockProduct, String>() {


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

                                for(int i=0,len=purchaseStockProductProperties.size();i<len;i++){
                                    if(selectIndex == i){
                                        productNum = purchaseStockProductProperties.get(i).getPronum();
                                    }
                                }

                                ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productNum);

                                if(productBasic != null){


                                    if("".equals(productBasic.getInventoryplace())){
                                        insertDepot();
                                    }else{
                                        selectLock = false;
                                        //现有库位查询
                                        morePurchaseStockHouseClick();
                                    }


                                }




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


        pro_sourseorder.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_orders.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_sort.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_seeorder.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_pronum.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_proname.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_stocknum.setCellFactory(TextFieldTableCell.forTableColumn());

        pro_units.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_depotnum.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_depotname.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_floor.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_boxnum.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_remarks.setCellFactory(TextFieldTableCell.forTableColumn());

        depotbutton.setCellFactory(buttonFactory);

        pro_sourseorder.setCellValueFactory(new PropertyValueFactory("sourseorder"));
        pro_orders.setCellValueFactory(new PropertyValueFactory("orders"));
        pro_sort.setCellValueFactory(new PropertyValueFactory("sort"));
        pro_seeorder.setCellValueFactory(new PropertyValueFactory("seeorder"));
        pro_pronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        pro_proname.setCellValueFactory(new PropertyValueFactory("proname"));
        pro_stocknum.setCellValueFactory(new PropertyValueFactory("stocknum"));
        pro_units.setCellValueFactory(new PropertyValueFactory("units"));
        pro_depotnum.setCellValueFactory(new PropertyValueFactory("depotnum"));
        pro_depotname.setCellValueFactory(new PropertyValueFactory("depotname"));
        pro_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        pro_boxnum.setCellValueFactory(new PropertyValueFactory("boxnum"));
        pro_remarks.setCellValueFactory(new PropertyValueFactory("remarks"));



        purchaseStockProductProperties.clear();

        int totalNums = 0;

        if(purchaseStockProducts.size()>0){

            for (PurchaseStockProduct purchaseStockProduct:purchaseStockProducts) {

                PurchaseStockProductProperty purchaseStockProductProperty = new PurchaseStockProductProperty(purchaseStockProduct.getId(),purchaseStockProduct.getSourseorder(),purchaseStockProduct.getOrders(),purchaseStockProduct.getSort(),purchaseStockProduct.getSeeorder(),purchaseStockProduct.getPronum(),purchaseStockProduct.getProname(),purchaseStockProduct.getStocknum(),purchaseStockProduct.getPrice(),purchaseStockProduct.getUnits(),purchaseStockProduct.getDepotnum(),purchaseStockProduct.getDepotname(),purchaseStockProduct.getFloor(),purchaseStockProduct.getProductBoxnum(),purchaseStockProduct.getRemarks());

                purchaseStockProductProperties.add(purchaseStockProductProperty);

                totalNums += purchaseStockProductProperty.getStocknum();
            }

        }
        purchaseStockProductView.setItems(purchaseStockProductProperties);

        totalNum.setText(totalNums +"");

        purchaseStockProductView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseStockProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseStockProductProperty> observableValue, PurchaseStockProductProperty oldItem, PurchaseStockProductProperty newItem) {
                selectIndex = purchaseStockProductView.getSelectionModel().getSelectedIndex();
                changeId=newItem.getId();
            }
        });


        pro_stocknum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();

                int totalNums = 0;

                for (int i=0,len=purchaseStockProductProperties.size();i<len;i++) {
                        if(selectIndex == i){
                            purchaseStockProductProperties.get(i).setStocknum(newValue);
                        }

                    totalNums += purchaseStockProductProperties.get(i).getStocknum();
                }

                totalNum.setText(totalNums+"");


            }

        });


    }


    //采购入库单新增修改
    public void savePurchaseStockProduct(long purchaseStockid,int orderStatus,long relationId){
        for (PurchaseStockProductProperty purchaseStockProductProperty :purchaseStockProductProperties) {
            if(purchaseStockProductProperty.getPronum()!=null){

                if(!"".equals(purchaseStockProductProperty.getSourseorder()) && purchaseStockProductProperty.getSourseorder() != null){

                   if("采购订单".equals(purchaseStockProductProperty.getSourseorder())){
                        PurchaseOrders purchaseOrders =  purchaseOrdersService.findPurchaseByOrders(purchaseStockProductProperty.getOrders());
                        if(purchaseOrders != null){
                            List<PurchaseProduct> purchaseProducts =  purchaseProductService.findPurchaseProduct(purchaseOrders.getId());
                            if(purchaseProducts != null && purchaseProducts.size() > 0){
                                for (PurchaseProduct purchaseProduct:purchaseProducts) {
                                       if(purchaseProduct.getProorders().equals(purchaseStockProductProperty.getPronum())){
                                           //订单数量 - （已入库数量+在途数量+结案数量）
                                           if(purchaseStockProductProperty.getStocknum() > (purchaseProduct.getNum() - (purchaseProduct.getStockover() + purchaseProduct.getOntheway() + purchaseProduct.getForcenum()))){
                                               alert_informationDialog("入库数量超出采购未入库数量!");
                                               purchaseStockService.delete(purchaseStockid);
                                               if(orderStatus == 1){
                                                    relationService.delete(relationId);
                                               }
                                               throw new RuntimeException();
                                           }
                                       }
                                }
                            }
                        }
                   } else if("在途库存".equals(purchaseStockProductProperty.getSourseorder())){


                       TransportationProduct transportationProducts = transportationProductService.findTransportationProductByTransportationInventoryOrder(purchaseStockProductProperty.getOrders(),purchaseStockProductProperty.getProname());

                      if(transportationProducts != null){
                            //订单数量 - （已入库数量+在途数量+结案数量）
                          if(purchaseStockProductProperty.getStocknum() > (transportationProducts.getThistimeontheway() - transportationProducts.getStockover())){
                              alert_informationDialog("入库数量超出采购未入库数量!");
                              purchaseStockService.delete(purchaseStockid);
                              if(orderStatus == 1){
                                  relationService.delete(relationId);
                              }
                              throw new RuntimeException();
                          }
                      }
                   }
                }
                PurchaseStockProduct purchaseStockProduct ;

                if(purchaseStockProductProperty.getId()>0){
                    //修改
                    purchaseStockProduct = new PurchaseStockProduct(
                            purchaseStockProductProperty.getId(),
                            purchaseStockProductProperty.getSourseorder(),
                            purchaseStockProductProperty.getOrders(),
                            purchaseStockProductProperty.getSort(),
                            purchaseStockProductProperty.getSeeorder(),
                            purchaseStockProductProperty.getPronum(),
                            purchaseStockProductProperty.getProname(),
                            purchaseStockProductProperty.getStocknum(),
                            purchaseStockProductProperty.getPrice(),
                            purchaseStockProductProperty.getUnits(),
                            purchaseStockProductProperty.getDepotnum(),
                            purchaseStockProductProperty.getDepotname(),
                            purchaseStockProductProperty.getFloor(),
                            purchaseStockProductProperty.getBoxnum(),
                            purchaseStockProductProperty.getRemarks(),purchaseStockid);

                    purchaseStockProductService.updateNotNull(purchaseStockProduct);
                }else{
                    //新增

                    purchaseStockProduct = new PurchaseStockProduct(
                            purchaseStockProductProperty.getSourseorder(),
                            purchaseStockProductProperty.getOrders(),
                            purchaseStockProductProperty.getSort(),
                            purchaseStockProductProperty.getSeeorder(),
                            purchaseStockProductProperty.getPronum(),
                            purchaseStockProductProperty.getProname(),
                            purchaseStockProductProperty.getStocknum(),
                            purchaseStockProductProperty.getPrice(),
                            purchaseStockProductProperty.getUnits(),
                            purchaseStockProductProperty.getDepotnum(),
                            purchaseStockProductProperty.getDepotname(),
                            purchaseStockProductProperty.getFloor(),
                            purchaseStockProductProperty.getBoxnum(),
                            purchaseStockProductProperty.getRemarks(),purchaseStockid);
                    purchaseStockProduct.setOutnum(0);
                    purchaseStockProductService.save(purchaseStockProduct);
                }
            }
        }
    }






    /**
     * 采购入库单产品空白
     */
    public void blankPurchaseStockProduct(){
        PurchaseStockProductProperty purchaseStockProductProperty = new PurchaseStockProductProperty();
        purchaseStockProductProperties.add(purchaseStockProductProperty);
    }

    public void findPurchaseOrderSearch(){

        String pageSizes =  purchaseOrder_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreInquiryImport(1);
        }

    }


    public void findPurchaseOrderPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreInquiryImport(pageNum);
    }


    /**
     * 导入--采购订单
     */
    public  void importPurchaseOrder(){

        stage.setTitle("导入-采购订单");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/purchase_import.fxml"));
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

        List<PurchaseOrders> purchaseOrders = purchaseOrdersService.findPurchaseOrders(purchaseCheckBox.isSelected() ? 1 : 0,pageNum,pageSize);

        PageInfo<PurchaseOrders> pageInfo = new PageInfo<>(purchaseOrders);

        purchaseOrder_find_fast.setUserData(1); //首页

        purchaseOrder_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        purchaseOrder_find_next.setUserData(pageInfo.getNextPage());//下一页

        purchaseOrder_find_last.setUserData(pageInfo.getPages());//尾页


        ObservableList<PurchaseOrders> list = FXCollections.observableArrayList();


//        inquiryid.setCellValueFactory(new PropertyValueFactory("id"));
        findinquiryorder.setCellValueFactory(new PropertyValueFactory("orders"));
        findinquirydata.setCellValueFactory(new PropertyValueFactory("paremdate"));
        findsuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findstatus.setCellValueFactory(new PropertyValueFactory("strstatus"));
        /*findcomestock.setCellValueFactory(new PropertyValueFactory("purpeople"));*/


        for (PurchaseOrders inquiryOrder1:purchaseOrders) {

            inquiryOrder1.setParemdate(new SimpleDateFormat("yyyy-MM-dd").format(inquiryOrder1.getCreatedate()));

            if(inquiryOrder1.getShstatus()==0){
                inquiryOrder1.setStrstatus("未审核");
            }else{
                inquiryOrder1.setStrstatus("已审核");
            }

            list.add(inquiryOrder1);

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

        importProperties.clear();

        for (PurchaseProduct purchaseProduct:purchaseProducts) {

            InquiryImportProperty inquiryImportProperty = new InquiryImportProperty(false,purchaseProduct.getId(),purchaseProduct.getProorders(),0,purchaseProduct.getProname(),purchaseProduct.getNum(),purchaseProduct.getPrice());

            importProperties.add(inquiryImportProperty);

        }

        inquiryProductView.setItems(importProperties); //tableview添加list

    }




    //导入----根据询价单据编号查询询价产品

    //确定导入产品
    public  void importInquiryProductData(){

        PurchaseOrders purchaseOrders = purchaseOrdersService.selectByKey(inquiryOrderId);

        //存储被关联单据
        relation.setBeRelationName("采购订单");
        relation.setBeRelationId(purchaseOrders.getId());
        relationLock = true;
       if(purchaseOrders.getShstatus() == 1){
           List<TransportationProduct> transportationProducts = transportationProductService.findTransportarionProductByPurchaseOrders(purchaseOrders.getOrders());

           if(transportationProducts.size()>0){
               TransportationInventory transportationInventory = transportationInventoryService.selectByKey(transportationProducts.get(0).getParentid());
               boxnum.setText(transportationInventory.getOrders());
           }

            int totalNums = totalNum.getText() == null || "".equals(totalNum.getText()) ? 0 : Integer.parseInt(totalNum.getText());

           for(InquiryImportProperty inquiryImportProperty : importProperties){

//               boolean aoutationLock = true;
               //选中导入的产品
               if(inquiryImportProperty.isChecked()){

                   PurchaseProduct purchaseProduct = purchaseProductService.selectByKey(inquiryImportProperty.getId());

//                   for (PurchaseStockProductProperty purchaseStockProductProperty:purchaseStockProductProperties) {
//                       if(purchaseProduct.getProorders().equals(purchaseStockProductProperty.getPronum())){
//                           alert_informationDialog("产品"+inquiryImportProperty.getProisnum()+"重复!");
//                           aoutationLock  =  false;
//                       }
//                   }


                   ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(purchaseProduct.getProorders());


                   String depotOrderStr = "";
                   String depotNameStr = "";
                   String depotFloorStr = "";



                       if (!"".equals(purchaseProduct.getDepotnum()) && purchaseProduct.getDepotnum() != null) {
                           depotOrderStr = purchaseProduct.getDepotnum();
                           depotNameStr = purchaseProduct.getDepotname();
                           depotFloorStr = purchaseProduct.getFloor();
                       } else {
                           DepotProperty depotProperty = new DepotProperty();

                           if (productBasic != null) {
                               depotProperty = getDepot(productBasic.getInventoryplace());
                           }

                           depotOrderStr = depotProperty.getDepotOrder();
                           depotNameStr = depotProperty.getDepotName();
                           depotFloorStr = depotProperty.getDepotFloor();
                       }


//                   if(aoutationLock){
                       suppliernum.setText(purchaseOrders.getSuppliernum());
                       suppliername.setText(purchaseOrders.getSuppliername());

                       depotnum.setText(purchaseOrders.getWarehouseid());
                       depotname.setText(purchaseOrders.getWarehousename());


                        totalNums += purchaseProduct.getNum();

                       PurchaseStockProductProperty purchaseStockProduct = new PurchaseStockProductProperty(0,"采购订单",purchaseOrders.getOrders(),0,purchaseOrders.getSeeorders(),purchaseProduct.getProorders(),purchaseProduct.getProname(),purchaseProduct.getNum(),purchaseProduct.getPrice(),purchaseProduct.getUnit(),depotOrderStr,depotNameStr,depotFloorStr,"",purchaseProduct.getRemarks());

                       purchaseStockProductProperties.add(purchaseStockProduct);
//                   }
               }
           }

           totalNum.setText(totalNums +"");

       }else{
           alert_informationDialog(AppConst.ALERT_EXAMINE);
       }


        coseInquiryWin();
    }



    public void coseInquiryWin(){
        stage.close();
    }


    /**
     * 审核通过
     *
     * 修改审核人、审核日期
     *
     */
    public void shButtonSuccess(){


//        updateStock();
        try {
            affairManageService.savePurchaseStock((long)stockorder.getUserData(),true);
        } catch (Exception e) {

        }

        if(  "".equals(NumberUtil.resultMap.get(true)) ||  NumberUtil.resultMap.get(true) != null){

            alert_informationDialog(NumberUtil.resultMap.get(false));

        }else{

            shno.setDisable(false);

            shyes.setDisable(true);

            lastUpdatePeopel(shpeople,shdate);

            updateOrderStatus(1);

        }


    }

    /**
     * 取消审核
     *
     * 需查看 单据是否被其他地方调用
     *
     */
    public void shButtonCancel(){

        try {
            affairManageService.savePurchaseStock((long)stockorder.getUserData(),false);
        } catch (Exception e) {

        }

        if(  "".equals(NumberUtil.resultMap.get(true)) ||  NumberUtil.resultMap.get(true) != null){

            alert_informationDialog(NumberUtil.resultMap.get(false));

        }else{

            shno.setDisable(true);
            shyes.setDisable(false);
            cancelSh(shpeople,shdate);
            updateOrderStatus(0);

        }



    }

    /**
     * 修改审核状态
     * @param status  0、未审核    1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)stockorder.getUserData();
        PurchaseStock purchaseStock = purchaseStockService.selectByKey(id);
        purchaseStock.setShstatus(status);

        if(status == 1){
            purchaseStock.setShpeople(getAdminName());
            purchaseStock.setShdate(getCurrentDate());

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            purchaseStock.setShpeople(NumberUtil.NULLSTRING);
            purchaseStock.setShdate(NumberUtil.NULLSTRING);

            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
        }

        purchaseStockService.updateNotNull(purchaseStock);
    }


    /**
     *
     * 根据入库产品修改库存数
     * des: 审核以后修改采购入库单产品库存数量
     * 步骤:
     * 1、修改入库数量
     * 2、修改已入库数量
     * 3、修改在途库存 在途数量 和 (订单数量)未入库数量
     */
    public boolean updateStock(){
        //采购入库单编号
        long purchaseStockId =  (long)stockorder.getUserData();
        //采购入库单信息
        PurchaseStock purchaseStock = purchaseStockService.selectByKey(purchaseStockId);

        if(purchaseStock == null) {return false;}
        //采购入库单产品信息
        List<PurchaseStockProduct   > purchaseStockProducts = purchaseStockProductService.findStockProductBypurchaseStockId(purchaseStock.getId());

        if(purchaseStockProducts == null) {return false;}

            for (PurchaseStockProduct purchaseStockProduct:purchaseStockProducts) {

                //根据库位和产品名称查询产品库存
                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(purchaseStockProduct.getDepotnum(),purchaseStockProduct.getPronum());

                if(productStock != null){
                //仓库有该产品库存
                    //库存数量
                    productStock.setStocknum(productStock.getStocknum()+purchaseStockProduct.getStocknum());
                    //可用库存
                    productStock.setUsablenum(productStock.getUsablenum()+purchaseStockProduct.getStocknum());
                    //在途库存
                    productStock.setOnthewaynum(productStock.getOnthewaynum() - purchaseStockProduct.getStocknum() );
                    //采购未入
                    productStock.setPurchasenum(productStock.getPurchasenum() - purchaseStockProduct.getStocknum());

                    productStockService.updateNotNull(productStock);

                }else{

                    //仓库无该产品库存
                    productStock = new ProductStock();
                    //产品编号
                    productStock.setProductorder(purchaseStockProduct.getPronum());
                    //产品名称
                    productStock.setProductname(purchaseStockProduct.getProname());
                    //可用数量
                    productStock.setUsablenum(purchaseStockProduct.getStocknum());
                    //库存数量
                    productStock.setStocknum(purchaseStockProduct.getStocknum());
                    //在途数量
//                    productStock.setOnthewaynum();
                    productStock.setOnthewaynum(0);
                    //销售未出
                    productStock.setSalenum(0);
                    //销售未入
                    productStock.setBacknum(0);
                    //采购未入
//                    productStock.setPurchasenum();
                    productStock.setPurchasenum(0);
                    //标准售价
                    productStock.setParprice(0.00);
                    //进价
                    productStock.setPurchaseprice(purchaseStockProduct.getPrice());
                    //库位
                    productStock.setDepot(purchaseStockProduct.getDepotnum());

                    productStockService.save(productStock);

                }



                //减少订单未入库量
                PurchaseOrders purchaseOrders = purchaseOrdersService.findPurchaseByOrders(purchaseStockProduct.getSeeorder());

                List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(purchaseOrders.getId());

                for (PurchaseProduct purchaseProduct:purchaseProducts) {

                    if(purchaseProduct.getProorders().equals(purchaseStockProduct.getPronum())){
                        //修改采购订单未入库数量
                        purchaseProduct.setStockover(purchaseProduct.getStockover()+purchaseStockProduct.getStocknum());

                        purchaseProductService.updateNotNull(purchaseProduct);

                    }


                }

                //修改在途库存  减少已在途数量 和 订货数量    增加已入库数量
                TransportationInventory transportationInventory = transportationInventoryService.findTransportationInventoryByBoxNum(purchaseStock.getBoxnum());

                List<TransportationProduct> transportationProducts =  transportationProductService.findTransportationProductByParentid(transportationInventory.getId());

                if(transportationProducts != null && transportationProducts.size() > 0){

                    for (TransportationProduct transportationProduct:transportationProducts) {

                        if(purchaseStockProduct.getPronum().equals(transportationProduct.getPronum())){

                            //增加已入库数量
                            transportationProduct.setStockover(transportationProduct.getStockover()+purchaseStockProduct.getStocknum());

//                            //减少订货数量
//                            transportationProduct.setTotalnum(transportationProduct.getTotalnum() - purchaseStockProduct.getStocknum());
//
//                            transportationProduct.setOntheway(transportationProduct.getOntheway() - purchaseStockProduct.getStocknum() );

                            transportationProductService.updateNotNull(transportationProduct);


                        }

                    }

                    List<TransportationProduct> transportationProductList =  transportationProductService.findTransportarionProductByPurchaseOrders(transportationProducts.get(0).getPurchaseorder());


                    for (TransportationProduct transportationProduct:transportationProductList) {
                        //减少订货数量
                        transportationProduct.setTotalnum(transportationProduct.getTotalnum() - purchaseStockProduct.getStocknum());

                        transportationProduct.setOntheway(transportationProduct.getOntheway() - purchaseStockProduct.getStocknum() );

                        transportationProductService.updateNotNull(transportationProduct);

                    }


                }





                //修改产品基本资料库位

                ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productStock.getProductorder());

                 String place = productBasic.getInventoryplace(); //产品所有库位

                 String idnum = purchaseStockProduct.getDepotnum(); //库位编号

                 int index =  place.indexOf(idnum);

                 if(index == -1 && !"".equals(place)){
                     productBasic.setInventoryplace(productBasic.getInventoryplace()+","+idnum);
                 }else if(index == -1 && "".equals(place)){
                     productBasic.setInventoryplace(idnum);
                 }

                productBasicService.updateNotNull(productBasic);

            }







        return true;

    }




    @Override
        public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("purchase_stock.fxml");

        if(index != -1) {
            relation = new Relation();
            relationLock = false;
            //采购订单
            PurchaseOrders purchaseOrders = StageManager.purchaseOrders;
            //在途库存
            TransportationInventory transportationInventory = StageManager.transportationInventory;


              bindEmployee(inspectnum,inspectname);

            String depotId = "",depotName = "",depotFloor = "";

              if(transportationInventory != null){

                  //存储被关联单据
                  relation.setBeRelationName("在途库存");
                  relation.setBeRelationId(transportationInventory.getId());
                  relationLock = true;
                  //新增状态
                  insert();

                  //初始化表格数据
                  loadPurchaseStockProduct(0);

                  List<TransportationProduct> transportationProducts = StageManager.transportationProducts;

                  purchaseOrders =  purchaseOrdersService.findPurchaseByOrders(transportationProducts.get(0).getPurchaseorder());


                  suppliernum.setText(purchaseOrders.getSuppliernum());
                  suppliername.setText(purchaseOrders.getSuppliername());

                  depotnum.setText(purchaseOrders.getWarehouseid());
                  depotname.setText(purchaseOrders.getWarehousename());

//                  boxnum.setText(transportationInventory.getOrders());

                  for (TransportationProduct transportationProduct :transportationProducts) {


//                      ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(transportationProduct.getPronum());

                      PurchaseProduct purchaseProduct = purchaseProductService.findPurchaseProductByPronum(purchaseOrders.getId(),transportationProduct.getPronum());

                      String depotOrderStr = "";
                      String depotNameStr = "";
                      String depotFloorStr = "";

                      if(!"".equals(transportationProduct.getDepotnum()) && transportationProduct.getDepotnum() != null){
                          depotOrderStr = transportationProduct.getDepotnum();
                          depotNameStr = transportationProduct.getDepotname();
                          depotFloorStr =  transportationProduct.getDepotfloor();
                      }else{
                          DepotProperty depotProperty = new DepotProperty();

                          ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(transportationProduct.getPronum());
                          if(productBasic != null){
                              depotProperty = getDepot(productBasic.getInventoryplace());
                          }

                          depotOrderStr = depotProperty.getDepotOrder();
                          depotNameStr = depotProperty.getDepotName();
                          depotFloorStr =  depotProperty.getDepotFloor();
                      }



//                      transportationProduct.getTotalnum() - transportationProduct.getStockover() +""
                      PurchaseStockProductProperty purchaseStockProductProperty = new PurchaseStockProductProperty(0,"在途库存",transportationInventory.getOrders(),transportationProduct.getSort(),transportationProduct.getSeeorder(),purchaseProduct.getProorders(),purchaseProduct.getProname(),transportationProduct.getOntheway(),purchaseProduct.getPrice(),purchaseProduct.getUnit(),depotOrderStr,depotNameStr,depotFloorStr,transportationProduct.getBoxnum(),purchaseProduct.getRemarks());


                      purchaseStockProductProperties.add(purchaseStockProductProperty);

                  }


              }else if(purchaseOrders != null){
                  //采购订单转入采购入库单加载

                  //新增状态
                  insert();


                  //存储被关联单据
                  relation.setBeRelationName("采购订单");
                  relation.setBeRelationId(purchaseOrders.getId());
                  relationLock = true;
                  suppliernum.setText(purchaseOrders.getSuppliernum());
                  suppliername.setText(purchaseOrders.getSuppliername());

                  depotnum.setText(purchaseOrders.getWarehouseid());
                  depotname.setText(purchaseOrders.getWarehousename());
                  //初始化表格数据
                  loadPurchaseStockProduct(0);

                  List<PurchaseProduct> purchaseProducts = StageManager.purchaseProducts;

                  if(purchaseProducts.size() > 0){

                      for (PurchaseProduct purchaseProduct:purchaseProducts) {

                          String depotOrderStr = "";
                          String depotNameStr = "";
                          String depotFloorStr = "";


                               ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(purchaseProduct.getProorders());


                               if (!"".equals(purchaseProduct.getDepotnum()) && purchaseProduct.getDepotnum() != null) {
                                   depotOrderStr = purchaseProduct.getDepotnum();
                                   depotNameStr = purchaseProduct.getDepotname();
                                   depotFloorStr = purchaseProduct.getFloor();
                               } else {
                                   DepotProperty depotProperty = new DepotProperty();

                                   if (productBasic != null) {
                                       depotProperty = getDepot(productBasic.getInventoryplace());
                                   }

                                   depotOrderStr = depotProperty.getDepotOrder();
                                   depotNameStr = depotProperty.getDepotName();
                                   depotFloorStr = depotProperty.getDepotFloor();
                               }

                          PurchaseStockProductProperty purchaseStockProductProperty = new PurchaseStockProductProperty(0,"采购订单",purchaseOrders.getOrders(),purchaseProduct.getSort(),purchaseOrders.getSeeorders(),purchaseProduct.getProorders(),purchaseProduct.getProname(),purchaseProduct.getNum() - (purchaseProduct.getStockover() + purchaseProduct.getForcenum() + purchaseProduct.getOntheway()),purchaseProduct.getPrice(),purchaseProduct.getUnit(),depotOrderStr,depotNameStr,depotFloorStr,"",purchaseProduct.getRemarks());

                          purchaseStockProductProperties.add(purchaseStockProductProperty);

                      }


                  }
              }else{
                  //正常单据加载
                  findInquiry(1);
              }
        }


    }

    @FXML
    TextField isNum; //备注

    @FXML TextField depname; //名称

    @FXML ComboBox depfloor; //楼层

    @FXML ComboBox parentid; //隶属仓库

    @FXML TextArea depotRemarks; //备注

    @FXML TextField addpeople; //建档人

    @FXML TextField adddate; //建档日期


    //新增库位
    public void insertDepot(){
        stage2.setTitle("库位管理");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/depot_basic_insert.fxml"));
        Scene scene = new Scene(pane);
        stage2.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/

        setComboBox(32,depfloor,0);
        setComboBox(33,parentid,0);
        createPeople(addpeople,adddate);//建档人、建档日期

        stage2.show();
    }

    public void insertDepotAndProduct(){

        String idnums = isNum.getText();

        String idName =  depname.getText();

         DepotBasic depotBasic =  depotBasicService.selectDepotBasicByIsnum(idnums);

        if("".equals(idnums) || idnums == null){
            alert_informationDialog("库位编号不能为空!");
            return;
        }
        if("".equals(idName) || idName == null){
            alert_informationDialog("库位名称不能为空!");
            return;
        }
        if(depotBasic != null){
             alert_informationDialog("库位编号重复!");
             isNum.setText("");
             return;
         }else{

            depotBasic = new DepotBasic( 0L,
                    idnums,
                    depname.getText(),
                    depfloor.getSelectionModel().getSelectedItem()==null?"":depfloor.getSelectionModel().getSelectedItem().toString(),
                    parentid.getSelectionModel().getSelectedItem()==null?0:dataSettingService.findDataSettingByName(parentid.getSelectionModel().getSelectedItem().toString()).getId(),
                    depotRemarks.getText(),
                    addpeople.getText(),
                    adddate.getText(),
                    "",""
            );

            depotBasicService.save(depotBasic);

            alert_informationDialog("添加成功!");


            for (int i=0,len = purchaseStockProductProperties.size();i<len;i++) {

                if(selectIndex == i){
                    purchaseStockProductProperties.get(i).setDepotnum(depotBasic.getIsnum());
                    purchaseStockProductProperties.get(i).setDepotname(depotBasic.getDepname());
                    purchaseStockProductProperties.get(i).setFloor(depotBasic.getDepfloor());
                }

            }


            closeInsertDepotWin();
        }


    }


    public void closeInsertDepotWin(){
        stage2.close();
    }


}
