package com.yc.education.controller.purchase;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.PurchaseDataController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.*;
import com.yc.education.model.purchase.*;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.*;
import com.yc.education.service.purchase.*;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleOfferProductService;
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

import java.math.BigDecimal;
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
 * @ClassName PurchaseOrdersController
 * @Description TODO 采购订单
 * @Author QuZhangJing
 * @Date 2018/10/9 17:47
 * @Version 1.0
 */
@Controller
public class PurchaseOrdersController extends BaseController implements Initializable {

    @Autowired
    private PurchaseOrdersService purchaseOrdersService;  //采购订单 Service
    @Autowired
    private SupplierContactService supplierContactService;//供应商联系人
    @Autowired
    private SupplierBasicService supplierBasicService; //供应商 Service
    @Autowired
    private DepotBasicService depotBasicService;//仓库库位
    @Autowired
    private PurchaseProductService purchaseProductService; //采购产品Service
    @Autowired
    private InquiryOrderService inquiryOrderService;//询价单Service
    @Autowired
    private InquiryProductService inquiryProductService;//询价产品 Service
    @Autowired
    private EmployeeBasicService employeeBasicService;//员工Service
    @Autowired
    private DataSettingService dataSettingService;
    @Autowired
    private PurchaseInvoiceService purchaseInvoiceService; //进项发票
    @Autowired
    private PurchaseDescriptionService purchaseDescriptionService;
    @Autowired
     private ProductBasicService productBasicService;
    @Autowired
    private ISaleOfferProductService iSaleOfferProductService;
    @Autowired
    private ProductStockService productStockService; //库存
    //销货单
    @Autowired
    private ISaleGoodsProductService iSaleGoodsProductService;
    @Autowired
    private  SupplierPurchaserService supplierPurchaserService;

    @Autowired
    private RelationService relationService;
    //每个单据用来锁定关联数据的
    boolean relationLock = false;

    long changeId =0;

    @FXML private Label fxmlStatus; //状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox clearvbox; //清除

    @FXML private VBox submitvbox; //提交

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox printingvbox; //打印

    @FXML private VBox shyes; //审核

    @FXML private VBox shno; //取消审核

    @FXML private VBox importData;//导入

    @FXML private VBox importOut; //导出



    @FXML private DatePicker createdate; //制单日期

    @FXML private TextField orders; //询价订单

    @FXML private TextField suppliernum; //供应商编号

    @FXML private TextField suppliername; //供应商简称

    @FXML private ComboBox warehouseid; //到货入库

    @FXML private TextField warehousename; //到货入库

    @FXML private TextField seeorders; //参考单号

    @FXML private ComboBox tax; //税别

    @FXML private ComboBox currency; //币别

   @FXML private DatePicker replydate; //回复日期

    /*  @FXML private DatePicker validdate; //有效期至*/

    @FXML private ComboBox purpeopletype; //采购负责类型

    @FXML private TextField purpeople; //采购负责人

    @FXML private TextField createpeople; //制单人

    @FXML private TextField shpeople; //审核人

    @FXML private TextField shdate; //审核日期

    @FXML private TextField updatepeople; //最后更新人

    @FXML private TextField updatedate; //最后更新日期


    //供应商基本资料


    @FXML private TextField supname;//供应商名称

    @FXML private ComboBox supcontacts; //供应商联系人

    @FXML private ComboBox supohone; //供应商 联系电话

    @FXML private ComboBox suptax; //供应商 传真

    @FXML private ComboBox supaddress; //供应商 地址


    //查询更多供应商
    @FXML private TableView tableView3; //供应商查询
    @FXML private TableColumn supid; //id
    @FXML private TableColumn findsupplierid; //供应商编号
    @FXML private TableColumn findsuppliername; //供应商简称


    //查询更多采购订单
    @FXML private TableView tableView4; //采购订单
    @FXML private TableColumn purchaseid; //id
    @FXML private TableColumn findpurchaseorder; //采购订单号
    @FXML private TableColumn findpurchasedata; //制单日期
    @FXML private TableColumn findpurchasenum;//供应商编号
    @FXML private TableColumn findpurchasedes;//供应商简称
    @FXML private TableColumn findpurchasepeo;//业务负责
    @FXML private TableColumn findpurchasecontact;//联系人
    @FXML private TableColumn findphone; //电话
    @FXML private TableColumn findfax;//传真


    //查询采购产品
    @FXML private TableView tableView1; //采购产品
    @FXML private TableColumn proid; //来源单据
    @FXML private TableColumn sourseorder; //来源单据
    @FXML private TableColumn inquiryorders; //单据编号
    @FXML private TableColumn sort; //序号
    @FXML private TableColumn proorders; //产品编号
    @FXML private TableColumn proname; //产品名称
    @FXML private TableColumn prosupname; //供应商名称
    @FXML private TableColumn pronum; //数量
    @FXML private TableColumn prounit; //单位
    @FXML private TableColumn proprice; //单价
    @FXML private TableColumn totalprice; //金额
    @FXML private TableColumn pro_depotnum; //库位编号
    @FXML private TableColumn depotbutton; //按钮
    @FXML private TableColumn pro_depotname; //库位名称
    @FXML private TableColumn pro_floor; //楼层
    @FXML private TableColumn remarks; //备注
    @FXML private TableColumn estimateDeliver;

    //导入--询价单据列表
    @FXML private TableView inquiryView;
    @FXML private TableColumn inquiryid; //单据ID
    @FXML private TableColumn findinquiryorder; //单据编号
    @FXML private TableColumn findinquirydata; //制单日期
    @FXML private TableColumn findsuppliernum; //供应商编号
    @FXML private TableColumn findstatus; //单据状态
    @FXML private TableColumn findcomestock; //到货仓库

    private long inquiryOrderId =0;

    //导入--询价单据产品
    @FXML private TableView inquiryProductView;
    @FXML private TableColumn inquiryproid; //询价单产品ID
    @FXML private TableColumn findproid; //产品编号
    @FXML private TableColumn finprosort; //产品序号
    @FXML private TableColumn findproname; //产品名称
    @FXML private TableColumn finpronum; //数量
    @FXML private TableColumn findproprice; //单价




    @FXML  private TextField totalnum;  //数量合计
    @FXML  private TextField totalmoney; //金额合计
    @FXML  private TextField totalloan; //货款合计
    @FXML  private TextField totaltax; //税款合计


    @FXML private Button supplierBtn;

    @FXML
    private TableView descriptionView;
    @FXML
    private TableColumn purcahsedescrtion;
    @FXML
    private TableView remarksView;
    @FXML
    private TableColumn purchaseremarks;



    @FXML private TableView houseView; //仓库库位
    @FXML private TableColumn findhousenum; //  库位编号
    @FXML private TableColumn findhousename; //  库位名称
    @FXML private TableColumn findparent; //  所属仓库
    @FXML private TableColumn findhousefloor; //  楼层
    @FXML private TableColumn depotNum;//库存数量

    @FXML private CheckBox viewAll;

    boolean selectLock = false;
    //采购产品——之数据绑定TabelView
    private ObservableList<PurchaseProductProperty> purchaseProductProperties = FXCollections.observableArrayList();
    //导入--询价单  询价产品导入数据TabelView
    private ObservableList<InquiryImportProperty> inquiryImportProperties = FXCollections.observableArrayList();

    private ObservableList<PurchaseDescriptionProperty> purchaseDescriptionProperties = FXCollections.observableArrayList();

    private ObservableList<PurchaseRemarkProperty> purchaseRemarkProperties = FXCollections.observableArrayList();

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    private String productNum = "";

    private int selectdIndex = 0;

    private int pageSize = 10;

    @FXML private VBox purchase_find_fast;
    @FXML private VBox purchase_find_prev;
    @FXML private VBox purchase_find_next;
    @FXML private VBox purchase_find_last;

    @FXML private TextField purchase_textField_pageSize;

    @FXML private TextField purchase_order_textField;

    private int tableViewIndex = 0;


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

    @FXML private TextField depot_order_textField;



    @FXML private VBox inquiry_find_fast;
    @FXML private VBox inquiry_find_prev;
    @FXML private VBox inquiry_find_next;
    @FXML private VBox inquiry_find_last;

    @FXML private TextField inquiry_textField_pageSize;

    @FXML private TextField inquiry_order_textField;

    @FXML private CheckBox inquiryCheckBox;



    @FXML private VBox product_find_fast;
    @FXML private VBox product_find_prev;
    @FXML private VBox product_find_next;
    @FXML private VBox product_find_last;

    @FXML private TextField product_textField_pageSize;

    @FXML private TextField product_order_textField;


    //询价产品查询
    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注

    /**
     * 单据关联容器
     */
    Relation relation = new Relation();


    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
    public String createIsnum(String currentDate){
        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
        String maxIsnum = purchaseOrdersService.selectMaxIdnum(currentDate);
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


    public void findDeoptSearch(){

        String pageSizes =  depot_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreHouse(1);
        }

    }


    public void findDepotPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreHouse(pageNum);
    }


    //现有库位查询
    public void morePurchaseStockHouseClick(){

        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/purchase_order_depot_house_find.fxml"));
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

                loadMoreHouse(1);


            }
        });
//        selectLock = false;
        pageSize = 10;
        loadMoreHouse(1);
    }


    public void loadMoreHouse(int pageNum){




//        List<DepotBasic> depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);

        List<DepotBasic> depotBasicList = new ArrayList<>();

//        String depotId = warehouseid.getSelectionModel().getSelectedItem().toString();

        if(!"".equals(productNum) && !selectLock){

            ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productNum);

            if(productBasic != null && productBasic.getInventoryplace() != null){
                String[] storehouse = productBasic.getInventoryplace().split(",");

                for (int i=0,len=storehouse.length;i<len;i++){

                    DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(storehouse[i],"".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);

                    depotBasicList.add(depotBasic);
                }
            }else{
                depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
            }

        }else{
//            depotBasicList = depotBasicService.selectDepotBasicByParentId(Long.valueOf(depotId),"".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
            depotBasicList = depotBasicService.selectDepotBasic("".equals(depot_order_textField.getText()) || depot_order_textField.getText() == null ? "" : depot_order_textField.getText().toString(),pageNum,pageSize);
        }

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
        depotNum.setCellValueFactory(new PropertyValueFactory("stockNum"));


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

        for(int i=0,len=purchaseProductProperties.size();i<len;i++){

            if(selectdIndex == i){

                purchaseProductProperties.get(i).setFloor(depotBasic.getDepfloor());

                purchaseProductProperties.get(i).setDepotnum(depotBasic.getIsnum());

                purchaseProductProperties.get(i).setDepotname(depotBasic.getDepname());

            }
        }

        coseWin();
    }

    public void findPurchaseSearch(){

        String pageSizes =  purchase_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreInquiry(1);
        }

    }


    public void findPurchasePages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreInquiry(pageNum);
    }



    //点击弹出 现有询价单查询
    public void moreInquiryClick(){

        stage.setTitle("现有采购订单查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/purchase_find.fxml"));
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

        List<PurchaseOrders> purchaseOrders = purchaseOrdersService.findPurchaseOrders("".equals(purchase_order_textField.getText()) || purchase_order_textField.getText() == null ? "" : purchase_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<PurchaseOrders> pageInfo = new PageInfo<>(purchaseOrders);

        purchase_find_fast.setUserData(1); //首页

        purchase_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        purchase_find_next.setUserData(pageInfo.getNextPage());//下一页

        purchase_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<PurchaseOrders> list = FXCollections.observableArrayList();


        /*tableView4.setEditable(true);*/


//        purchaseid.setCellValueFactory(new PropertyValueFactory("id"));
        findpurchaseorder.setCellValueFactory(new PropertyValueFactory("orders"));
        findpurchasedata.setCellValueFactory(new PropertyValueFactory("paremdate"));
        findpurchasenum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findpurchasedes.setCellValueFactory(new PropertyValueFactory("suppliername"));
        findpurchasepeo.setCellValueFactory(new PropertyValueFactory("purpeople"));
        findpurchasecontact.setCellValueFactory(new PropertyValueFactory("supplierconcat"));
        findphone.setCellValueFactory(new PropertyValueFactory("supplierphone"));
        findfax.setCellValueFactory(new PropertyValueFactory("supplierfax"));


        for (PurchaseOrders purchaseOrders1:purchaseOrders) {

            purchaseOrders1.setParemdate(new SimpleDateFormat("yyyy-MM-dd").format(purchaseOrders1.getCreatedate()));

            list.add(purchaseOrders1);

        }

        tableView4.setItems(list); //tableview添加list

        tableView4.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseOrders>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseOrders> observableValue, PurchaseOrders oldItem, PurchaseOrders newItem) {
                if(newItem.getOrders() != null && !("".equals(newItem.getOrders()))){
                    findpurchaseorder.setUserData(newItem.getId());
                }
            }
        });


        tableView4.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeInquiryWin();
            }
        });


    }

    public void closeInquiryWin(){
        long id =(long)findpurchaseorder.getUserData();
        PurchaseOrders purchaseOrders =  purchaseOrdersService.selectByKey(id);
        orders.setText(purchaseOrders.getOrders());
        loadDate(purchaseOrders);
        loadSupplier(purchaseOrders.getSuppliernum(),purchaseOrders);
        /*loadDate(supplierBasic);*/
        coseInquiryWin();
    }

    public void coseInquiryWin(){
        stage.close();
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
        pane.getChildren().setAll(loader.load("/fxml/purchase/purchase_supplier_find.fxml"));
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

        List<SupplierBasic> supplierBasics = supplierBasicService.selectSupplierBasic("".equals(supplier_order_textField.getText()) || supplier_order_textField.getText() == null ? "" : supplier_order_textField.getText().toString(),pageNum,pageSize);

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
//        long orderId =(long)orders.getUserData();
        SupplierBasic supplierBasic =  supplierBasicService.selectByKey(id);
//        PurchaseOrders purchaseOrders =  purchaseOrdersService.selectByKey(orderId);
        suppliernum.setText(supplierBasic.getIdnum());
        suppliername.setText(supplierBasic.getSupdes());


        List<SupplierPurchaser> supplierPurchasers = supplierPurchaserService.selectSupplierPurchaseBySupplierid(supplierBasic.getId());

        if(supplierPurchasers.size() > 0 && supplierPurchasers != null){

            for (SupplierPurchaser supplierPurchaser:supplierPurchasers) {
                if(supplierPurchaser.getKeycontent() == 1){

                    ObservableList<String > purchanseEmp =  purpeopletype.getItems();
                    for (int i=0,len = purchanseEmp.size();i<len;i++) {
                        if(purchanseEmp.get(i).equals(supplierPurchaser.getStaffcode())){
                            purpeopletype.getSelectionModel().select(i);
                        }
                    }
                    purpeople.setText(supplierPurchaser.getStaffname());
                }
            }
        }else{
            purpeopletype.getSelectionModel().select(-1);
            purpeople.setText("");
        }


        List<SupplierContact> supplierContacts = supplierContactService.selectSupplierContactBySupplierid(supplierBasic.getId());

        if(supplierContacts.size() > 0 && supplierContacts != null){

            supcontacts.getItems().clear();
            supohone.getItems().clear();
            suptax.getItems().clear();
            supaddress.getItems().clear();

            for(int i=0,len=supplierContacts.size();i<len;i++){

                supcontacts.getItems().add(supplierContacts.get(i).getUname());
                supohone.getItems().add(supplierContacts.get(i).getUphone());
                suptax.getItems().add(supplierContacts.get(i).getUfax());
            }


            for(int i=0,len=supplierContacts.size();i<len;i++){

                if(supplierContacts.get(i).getKeycontact() == 1){
                    supcontacts.getSelectionModel().select(i);
                    supohone.getSelectionModel().select(i);
                    suptax.getSelectionModel().select(i);
                    break;
                }else{
                    supcontacts.getSelectionModel().select(0);
                    supohone.getSelectionModel().select(0);
                    suptax.getSelectionModel().select(0);
                }

            }
            supname.setText(supplierBasic.getSupname());
            supaddress.getItems().add(supplierBasic.getRegadd());
            supaddress.getSelectionModel().select(0);

        }


        /*loadDate(supplierBasic);*/
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }






    /**
     * 上下首尾跳页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findInquiry(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);//
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findInquiry(int pageNum){

        List<PurchaseOrders> purchaseOrders = purchaseOrdersService.findPurchaseOrders(pageNum,1);

        PageInfo<PurchaseOrders> pageInfo = new PageInfo<>(purchaseOrders);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        purchaseOrders.forEach(inquiryOrder ->loadDate(inquiryOrder));





        if(purchaseOrders.size()<=0){

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(true);

            deletevbox.setDisable(true);


            shno.setDisable(true);

            shyes.setDisable(true);

            importOut.setDisable(true);

            orders.setUserData(0L);
            inquiryProduct();

            description(0);
            remarks(0);

            //权限管理
            matchingPermissions("采购订单",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox);
        }


    }


    /**
     * 向控件加载数据
     * @param purchaseOrders
     */
    public void loadDate(PurchaseOrders purchaseOrders){

        loadSupplier(purchaseOrders.getSuppliernum(),purchaseOrders); //加载供应商信息

        createdate.setValue(LocalDateTime.ofInstant(purchaseOrders.getCreatedate().toInstant(), ZoneId.systemDefault()).toLocalDate()); //制单日期

        orders.setUserData(purchaseOrders.getId());

        orders.setText(purchaseOrders.getOrders());//询价订单

        suppliernum.setText(purchaseOrders.getSuppliernum()); //供应商编号

        suppliername.setText(purchaseOrders.getSuppliername());//供应商名称


        List<DataSetting> comboBoxType = dataSettingService.findDataSetting(33);

        for(int i=0,len=comboBoxType.size();i<len;i++){
          if(comboBoxType.get(i).getId().toString().equals(purchaseOrders.getWarehouseid()) ){
              warehouseid.getSelectionModel().select(i);
              warehousename.setText(purchaseOrders.getWarehousename());
          }
        }


        tax.getItems().clear();
        tax.getItems().addAll("外加","内含","零税","免税");
        int taxs = purchaseOrders.getPtax();
        tax.getSelectionModel().select(--taxs); //税别

        seeorders.setText(purchaseOrders.getSeeorders());//来源单据

        int currentcyInt = purchaseOrders.getPcurrency();
        currency.getItems().clear();
        setComboBox(7L,currency,--currentcyInt); //币别

        replydate.setValue(LocalDateTime.ofInstant(purchaseOrders.getComedate().toInstant(), ZoneId.systemDefault()).toLocalDate()); //回复日期*/

        /*validdate.setValue(LocalDateTime.ofInstant(purchaseOrders.getValiddate().toInstant(), ZoneId.systemDefault()).toLocalDate()); //有效期至*/

        int purpeopletypes = purchaseOrders.getPurpeopletype();

        purpeopletype.getSelectionModel().select(--purpeopletypes); //采购负责类型


        purpeople.setText(purchaseOrders.getPurpeople()); //采购负责人

        createpeople.setText(purchaseOrders.getCreatepeople());//制单人

        shpeople.setText(purchaseOrders.getShpeople());//审核人


        shdate.setText(purchaseOrders.getShdate()); //审核日期


        updatepeople.setText(purchaseOrders.getUpdatepeople()); //最后更新人

        updatedate.setText(purchaseOrders.getUpdatedate()); //最后更新日期




        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

//        if(purchaseOrders.getShstatus() == 1){
//            //已审核不得修改和删除
//            updatevbox.setDisable(true);
//            deletevbox.setDisable(true);
//
//            shyes.setDisable(true);
//            shno.setDisable(false);
//        }else{
//
//            shyes.setDisable(false);
//            shno.setDisable(true);
//
//        }


        inquiryProduct();

        description(purchaseOrders.getId());
        remarks(purchaseOrders.getId());


        //权限管理
        matchingPermissions("采购订单",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,purchaseOrders.getShstatus());

    }

    /**
     * 根据供应商编号加载供应商
     * @param isNum
     */
    public void loadSupplier(String isNum,PurchaseOrders purchaseOrders){

        //供应商
        SupplierBasic supplierBasic =  supplierBasicService.selectSupplierBasicByIsnum(isNum);

        if(supplierBasic !=  null){

            supname.setText(supplierBasic.getSupname());

            //查询供应商联系人
            List<SupplierContact> supplierContacts = supplierContactService.selectSupplierContactBySupplierid(supplierBasic.getId());

            supcontacts.getItems().clear();
            supohone.getItems().clear();
            suptax.getItems().clear();
            supaddress.getItems().clear();

            for(int i=0,len=supplierContacts.size();i<len;i++){
                supcontacts.getItems().add(supplierContacts.get(i).getUname());
                supohone.getItems().add(supplierContacts.get(i).getUphone());
                suptax.getItems().add(supplierContacts.get(i).getUfax());
                if(purchaseOrders != null){
                    if(supplierContacts.get(i).getUname().equals(purchaseOrders.getSupplierconcat())){
                        supcontacts.getSelectionModel().select(i);
                    }
                if(supplierContacts.get(i).getUphone().equals(purchaseOrders.getSupplierphone())){
                    supohone.getSelectionModel().select(i);
                }
                if(supplierContacts.get(i).getUfax().equals(purchaseOrders.getSupplierfax())){
                    suptax.getSelectionModel().select(i);
                }
                }else{
                    supcontacts.getSelectionModel().select(0);
                    supohone.getSelectionModel().select(0);
                    suptax.getSelectionModel().select(0);
                }
            }
            supaddress.getItems().add(supplierBasic.getRegadd());
            supaddress.getSelectionModel().select(0);
        }

    }




    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        orders.setEditable(false);//询价订单

        suppliernum.setDisable(!status); //供应商编号

        suppliername.setDisable(!status); //供应商名称

        warehouseid.setDisable(!status);

        warehousename.setDisable(!status);

        seeorders.setDisable(!status);

        tax.setDisable(!status); //税别

        currency.setDisable(!status); //币别

        purpeopletype.setDisable(!status); //采购负责类型

        purpeople.setDisable(!status); //采购负责人

        createpeople.setDisable(!status); //制单人

        shpeople.setDisable(!status); //审核人

        shdate.setDisable(!status); //审核日期

        updatepeople.setDisable(!status); //最后更新人

        updatedate.setDisable(!status); //最后更新日期

        supname.setDisable(!status);

        supcontacts.setDisable(!status);
        supohone.setDisable(!status);
        suptax.setDisable(!status);
        supaddress.setDisable(!status);



        tableView1.setEditable(status);

        tableView1.setDisable(!status);

      /*  tableViewDes.setEditable(status);
        tableViewRem.setEditable(status);*/
        descriptionView.setEditable(status);
        remarksView.setEditable(status);

        createdate.setDisable(!status);
        replydate.setDisable(!status);
        /*validdate.setDisable(!status);*/


        supplierBtn.setDisable(!status); //查询更多供应商窗体

        importData.setDisable(!status);

    }



    /**
     * 清空
     */
    public void clearValue(){

        orders.setText(NumberUtil.NULLSTRING);//询价订单

        suppliernum.setText(NumberUtil.NULLSTRING); //供应商编号

        suppliername.setText(NumberUtil.NULLSTRING); //供应商名称

        tax.getSelectionModel().select(0); //税别

        currency.getSelectionModel().select(0); //币别

        purpeopletype.getSelectionModel().select(0); //采购负责类型

        purpeople.setText(NumberUtil.NULLSTRING); //采购负责人

//        createpeople.setText(NumberUtil.NULLSTRING); //制单人

        shpeople.setText(NumberUtil.NULLSTRING); //审核人

        shdate.setText(NumberUtil.NULLSTRING); //审核日期

        updatepeople.setText(NumberUtil.NULLSTRING); //最后更新人

        updatedate.setText(NumberUtil.NULLSTRING); //最后更新日期

        seeorders.setText(NumberUtil.NULLSTRING);  //参考单号

        warehousename.setText(NumberUtil.NULLSTRING); //仓库名称

        warehouseid.getSelectionModel().select(0); //仓库编号


        supname.setText(NumberUtil.NULLSTRING); //供应商名称

        supcontacts.getItems().clear();

        supohone.getItems().clear();

        suptax.getItems().clear();

        supaddress.getItems().clear();

        totalnum.setText("0");

        totalmoney.setText("0.00");

        totaltax.setText("0.00");

        totalloan.setText("0.00");


        purchaseDescriptionProperties.clear();

        purchaseRemarkProperties.clear();

    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) orders.getUserData();

            int rows = purchaseOrdersService.delete(id);

            List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(id);

            for (PurchaseProduct purchaseProduct:purchaseProducts) {
                purchaseProductService.delete(purchaseProduct.getId());
            }

            relationService.deleteRelation("采购订单",id);

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

//        blankInquiryProduct();
        blankPurchaseDescription();
        blankPurchaseRemarks();


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
        createPeople(createpeople);//制单人
        purchaseProductProperties.clear();
        setDatePicker(createdate);
        setArrivalDate(createdate,replydate,10);
//        blankInquiryProduct();
        blankPurchaseDescription();
        blankPurchaseRemarks();
    }


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

        PurchaseOrders purchaseOrders = new PurchaseOrders(new Date(java.sql.Date.valueOf(orderDate).getTime()),
                orders.getText(),
                suppliernum.getText(),
                suppliername.getText(),
                warehouseid.getItems().size()==0?"":warehouseid.getSelectionModel().getSelectedItem().toString(),
                warehousename.getText(),
                seeorders.getText(),
                new Date(java.sql.Date.valueOf(replydate.getValue()).getTime()),
                tax.getSelectionModel().getSelectedIndex()+1 ,
                currency.getSelectionModel().getSelectedIndex()+1,
                purpeopletype.getSelectionModel().getSelectedIndex()+1,
                purpeople.getText(),
                createpeople.getText(),
                shpeople.getText(),
                shdate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
                supname.getText(),
                supcontacts.getItems().size()==0?"":supcontacts.getSelectionModel().getSelectedItem().toString(),
                supohone.getItems().size()==0?"":supohone.getSelectionModel().getSelectedItem().toString(),
                suptax.getItems().size()==0?"":suptax.getSelectionModel().getSelectedItem().toString(),
                supaddress.getItems().size()==0?"":supaddress.getSelectionModel().getSelectedItem().toString(),
                0,
                0);


        int rows =0;
        if(submitStuts==1){
            //产生订单编号
            String orderNum = createIsnum(orderDate.toString());
            orders.setText(orderNum);
            purchaseOrders.setOrders(orderNum);
            rows = purchaseOrdersService.save(purchaseOrders);

            if(relationLock){
                //添加关联关系
                relation.setRelationName("采购订单");
                relation.setRelationId(purchaseOrders.getId());
                relationService.save(relation);
            }

        }else if(submitStuts ==2){
            purchaseOrders.setId((long)orders.getUserData());
            rows = purchaseOrdersService.updateNotNull(purchaseOrders);
        }

                            /*saveContact(supplierBasic.getId());//联系人
                            savePurchaser(supplierBasic.getId()); //采购负责人*/
        saveInquiryProduct(purchaseOrders.getId());
        savePurchaseDescription(purchaseOrders.getId());
        savePurchaseRemarks(purchaseOrders.getId());
//        saveInquiryDescription(purchaseOrders.getId());
//        saveInquiryRemarks(purchaseOrders.getId());
        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);

        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


        this.loadDate(purchaseOrders); //重新加载数据




    }


    //采购产品
    public void inquiryProduct(){

        List<PurchaseProduct> purchaseProducts =  purchaseProductService.findPurchaseProduct((long)orders.getUserData());




        Callback<TableColumn<PurchaseProduct, String>, TableCell<PurchaseProduct, String>> buttonFactory
                = new Callback<TableColumn<PurchaseProduct, String>, TableCell<PurchaseProduct, String>>() {
            @Override
            public TableCell call(final TableColumn<PurchaseProduct, String> param) {
                final TableCell<PurchaseProduct, String> cell = new TableCell<PurchaseProduct, String>() {


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
                                 selectdIndex = getTableRow().getIndex();

                                for(int i=0,len=purchaseProductProperties.size();i<len;i++){
                                    if(selectdIndex == i){
                                        productNum = purchaseProductProperties.get(i).getProorders();
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


        sourseorder.setCellFactory(TextFieldTableCell.forTableColumn());
        inquiryorders.setCellFactory(TextFieldTableCell.forTableColumn());
        sort.setCellFactory(TextFieldTableCell.forTableColumn());
        proorders.setCellFactory(TextFieldTableCell.forTableColumn());
        proname.setCellFactory(TextFieldTableCell.forTableColumn());
        prosupname.setCellFactory(TextFieldTableCell.forTableColumn());
        pronum.setCellFactory(TextFieldTableCell.forTableColumn());
        prounit.setCellFactory(TextFieldTableCell.forTableColumn());
        proprice.setCellFactory(TextFieldTableCell.forTableColumn());
        totalprice.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_depotnum.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_depotname.setCellFactory(TextFieldTableCell.forTableColumn());
        pro_floor.setCellFactory(TextFieldTableCell.forTableColumn());
        remarks.setCellFactory(TextFieldTableCell.forTableColumn());
        estimateDeliver.setCellFactory(TextFieldTableCell.forTableColumn());

        depotbutton.setCellFactory(buttonFactory);

        /*proid.setCellValueFactory(new PropertyValueFactory("id"));*/
        sourseorder.setCellValueFactory(new PropertyValueFactory("sourseorder"));
        inquiryorders.setCellValueFactory(new PropertyValueFactory("inquiryorders"));
        sort.setCellValueFactory(new PropertyValueFactory("sort"));
        proorders.setCellValueFactory(new PropertyValueFactory("proorders"));
        proname.setCellValueFactory(new PropertyValueFactory("proname"));
        prosupname.setCellValueFactory(new PropertyValueFactory("prosupname"));
        pronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        prounit.setCellValueFactory(new PropertyValueFactory("prounit"));
        proprice.setCellValueFactory(new PropertyValueFactory("proprice"));
        totalprice.setCellValueFactory(new PropertyValueFactory("totalprice"));
        pro_depotnum.setCellValueFactory(new PropertyValueFactory("depotnum"));
        pro_depotname.setCellValueFactory(new PropertyValueFactory("depotname"));
        pro_floor.setCellValueFactory(new PropertyValueFactory("floor"));
        remarks.setCellValueFactory(new PropertyValueFactory("remarks"));
        estimateDeliver.setCellValueFactory(new PropertyValueFactory("estimateDeliver"));

        purchaseProductProperties.clear();

        totalnum.setText("0");
        totalmoney.setText("0.00");
        totaltax.setText("0.00");
        totalloan.setText("0.00");
        if(purchaseProducts.size()>0){
            for (PurchaseProduct purchaseProduct:purchaseProducts) {

                PurchaseOrders purchaseOrders = purchaseOrdersService.selectByKey(purchaseProduct.getPurchaseid());

                totalCost(purchaseProduct.getNum(),purchaseProduct.getTotalprice(),purchaseOrders.getPtax(),getTaxReruenDouble(createdate.getValue(),1));

                PurchaseProductProperty purchaseProductProperty = new PurchaseProductProperty(purchaseProduct.getId(),purchaseProduct.getSourseorder(),purchaseProduct.getOrders(),purchaseProduct.getSort().toString(),purchaseProduct.getProorders(),purchaseProduct.getProname(),purchaseProduct.getSupname(),purchaseProduct.getNum().toString(),purchaseProduct.getUnit(),purchaseProduct.getPrice().toString(),purchaseProduct.getTotalprice().toString(),purchaseProduct.getDepotnum(),purchaseProduct.getDepotname(),purchaseProduct.getFloor(),purchaseProduct.getRemarks(),purchaseProduct.getExpecteddate());

                purchaseProductProperties.add(purchaseProductProperty);
            }
        }
        tableView1.setItems(purchaseProductProperties); //tableview添加list w



        tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseProductProperty> observableValue, PurchaseProductProperty oldItem, PurchaseProductProperty newItem) {
                tableViewIndex = tableView1.getSelectionModel().getSelectedIndex();
                changeId=newItem.getId();
            }
        });


        //提交单价计算金额  询价订单--询价产品 金额总计
        proprice.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();


                Double  newPrice = newValue != null && !"".equals(newValue)?newPrice=Double.parseDouble(newValue):0.00;

                totalnum.setText("0");
                totalmoney.setText("0.00");
                totaltax.setText("0.00");
                totalloan.setText("0.00");
                    for(int i = 0,len = purchaseProductProperties.size() ;i<len;i++ ){
                    if(i == tableViewIndex){

                        purchaseProductProperties.get(i).setProprice(String.valueOf(newPrice));
                        //计算金额
                        purchaseProductProperties.get(i).setTotalprice(String.valueOf(Integer.parseInt(purchaseProductProperties.get(i).getPronum())*newPrice));
                    }

                    if(purchaseProductProperties.get(i).getPronum() != null && purchaseProductProperties.get(i).getTotalprice() != null){
//                           totalCost(Integer.parseInt(inquiryProductProperty.getPronum()),Double.parseDouble(inquiryProductProperty.getTotalprice()));

                        totalCost(Integer.parseInt(purchaseProductProperties.get(i).getPronum()),Double.parseDouble(purchaseProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
                    }

                }
            }

        });


        pronum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();

                int  newNum = newValue != null && !"".equals(newValue)?newNum=Integer.parseInt(newValue):0;

                totalnum.setText("0");
                totalmoney.setText("0.00");
                totaltax.setText("0.00");
                totalloan.setText("0.00");

                for(int i = 0,len = purchaseProductProperties.size() ;i<len;i++ ){
                    if(i == tableViewIndex){

                        purchaseProductProperties.get(i).setPronum(String.valueOf(newNum));
                        //计算金额
                        purchaseProductProperties.get(i).setTotalprice(String.valueOf(newNum * Double.parseDouble(purchaseProductProperties.get(i).getProprice())));
                    }

                    if(purchaseProductProperties.get(i).getPronum() != null && purchaseProductProperties.get(i).getTotalprice() != null){
//                           totalCost(Integer.parseInt(inquiryProductProperty.getPronum()),Double.parseDouble(inquiryProductProperty.getTotalprice()));

                        totalCost(Integer.parseInt(purchaseProductProperties.get(i).getPronum()),Double.parseDouble(purchaseProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
                    }

                }
            }

        });


    }
    //
    public void saveInquiryProduct(long purchase){
        for (PurchaseProductProperty purchaseProductProperty :purchaseProductProperties) {
                if(purchaseProductProperty.getId()>0){
                    //修改采购负责人
                    PurchaseProduct purchaseProduct = new PurchaseProduct(purchaseProductProperty.getId(),purchaseProductProperty.getSourseorder(),purchaseProductProperty.getInquiryorders(),Integer.parseInt(purchaseProductProperty.getSort()),purchaseProductProperty.getProorders(),purchaseProductProperty.getProname(),purchaseProductProperty.getProsupname(),Integer.parseInt(purchaseProductProperty.getPronum()),purchaseProductProperty.getProunit(),Double.valueOf(purchaseProductProperty.getProprice()),Double.valueOf(purchaseProductProperty.getTotalprice()),purchaseProductProperty.getRemarks(),purchase,purchaseProductProperty.getDepotnum(),purchaseProductProperty.getDepotname(),purchaseProductProperty.getFloor(),purchaseProductProperty.getEstimateDeliver());
                    purchaseProductService.updateNotNull(purchaseProduct);
                }else{
                    //新增采购负责人
                    PurchaseProduct purchaseProduct = new PurchaseProduct(purchaseProductProperty.getSourseorder() == null ? "" : purchaseProductProperty.getSourseorder(),purchaseProductProperty.getInquiryorders() == null ? "" : purchaseProductProperty.getInquiryorders(),Integer.parseInt(purchaseProductProperty.getSort()),purchaseProductProperty.getProorders(),purchaseProductProperty.getProname(),purchaseProductProperty.getProsupname(),Integer.parseInt(purchaseProductProperty.getPronum()),purchaseProductProperty.getProunit(),Double.valueOf(purchaseProductProperty.getProprice()),Double.valueOf(purchaseProductProperty.getTotalprice()),purchaseProductProperty.getRemarks(),purchase,purchaseProductProperty.getDepotnum(),purchaseProductProperty.getDepotname(),purchaseProductProperty.getFloor(),purchaseProductProperty.getEstimateDeliver());
                    purchaseProduct.setOntheway(0);
                    purchaseProduct.setForcenum(0);
                    purchaseProduct.setStockover(0);
                    purchaseProductService.save(purchaseProduct);
                }
        }
    }


    //询价单产品空白
    public void blankInquiryProduct(){
        PurchaseProductProperty purchaseProductProperty = new PurchaseProductProperty();
        purchaseProductProperties.add(purchaseProductProperty);
    }


//    /**
//     * 核算
//     * @param pronum 数量
//     * @param price 金额
//     * @param order 单据号
//     */
//    public void totalCost(int pronum,Double price,long order){
//
//
//        Double taxFlag = 0.16;  //进项税率
//
//        int countNum = Integer.parseInt(totalnum.getText());
//
//        Double taxNum =  Double.parseDouble(totaltax.getText());
//
//        Double totaloanNum = Double.parseDouble(totalloan.getText());
//
//        totalnum.setText(String.valueOf((countNum+pronum)));
//
//         PurchaseOrders purchaseOrders =  purchaseOrdersService.selectByKey(order);
//
//         if(purchaseOrders != null){
//
//             int tax = purchaseOrders.getPtax(); //税别类型  "外加","内含","零税","免税"
//
//             switch (tax){
//                 case 1:
//
//                     totaltax.setText(String.valueOf(new BigDecimal(price * taxFlag + taxNum ).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + price).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     break;
//                 case 2:
//
//                     Double capital =  price / (taxFlag + 1); //税前价格
//
//                     totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + capital).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     totaltax.setText(String.valueOf(new BigDecimal(price - capital + taxNum).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//
//                     totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     break;
//                 default:
//                     totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + price ).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));
//
//                     break;
//             }
//
//         }
//
//    }


    /**
     * 核算
     * @param pronum
     * @param price
     * @param tax 税别类型  "外加","内含","零税","免税"
     */
    public void totalCost(int pronum,Double price,int tax,double taxRate){

        int countNum =  "".equals(totalnum.getText()) || totalnum.getText() == null ? 0 :  Integer.parseInt(totalnum.getText());

        Double taxNum =   "".equals(totaltax.getText()) || totaltax.getText() == null ? 0.00 : Double.parseDouble(totaltax.getText());

        Double totaloanNum =  "".equals(totalloan.getText()) || totalloan.getText() == null ? 0.00 : Double.parseDouble(totalloan.getText());

        totalnum.setText(String.valueOf((countNum+pronum)));

        switch (tax){
            case 1:

                totaltax.setText(String.valueOf(new BigDecimal(price * taxRate + taxNum ).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + price).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                break;
            case 2:

                Double capital =  price / (taxRate + 1); //税前价格

                totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + capital).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totaltax.setText(String.valueOf(new BigDecimal(price - capital + taxNum).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));


                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                break;
            default:
                totalloan.setText(String.valueOf(new BigDecimal(totaloanNum + price ).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                break;
        }


    }




    //采购产品键盘按下操作
    public void tableView1Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {

            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            purchaseProductService.delete(changeId);
            ObservableList<PurchaseProductProperty> purchaseProductPropertiesNew = FXCollections.observableArrayList();

            totalnum.setText("0");
            totalmoney.setText("0.00");
            totaltax.setText("0.00");
            totalloan.setText("0.00");

            for (int i = 0, len = purchaseProductProperties.size(); i < len; i++) {
                if (i != tableViewIndex) {
                    purchaseProductPropertiesNew.add(purchaseProductProperties.get(i));
                    totalCost(Integer.parseInt(purchaseProductProperties.get(i).getPronum()), Double.parseDouble(purchaseProductProperties.get(i).getTotalprice()), tax.getSelectionModel().getSelectedIndex() + 1, getTaxReruenDouble(createdate.getValue(), 1));
                }

            }

            purchaseProductProperties.setAll(purchaseProductPropertiesNew);

        }
        }


        if (event.getCode() == KeyCode.INSERT) {
            blankInquiryProduct();
        }

    }




    /**
     * 审核通过
     *
     * 修改审核人、审核日期
     *
     */
    public void shButtonSuccess(){
        shno.setDisable(false);
        shyes.setDisable(true);
        lastUpdatePeopel(shpeople,shdate);
        updateOrderStatus(1);
        updateProductStock(true);
    }

    /**
     * 取消审核
     *
     * 需查看 单据是否被其他地方调用
     *
     */
    public void shButtonCancel(){

        long id =  (long)orders.getUserData();

        if(!relationService.isRelation("采购订单",id)){
            shno.setDisable(true);
            shyes.setDisable(false);
            cancelSh(shpeople,shdate);
            updateOrderStatus(0);
            updateProductStock(false);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }

    }


    /**
     * 采购订单审核后 修改产品库存表中 采购未入数量
     * 审核 为 true
     * 取消审核为 false
     */
    public void updateProductStock(boolean flag){
        long id =  (long)orders.getUserData();

        List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(id);

        if(purchaseProducts != null && purchaseProducts.size()>0){

            for (PurchaseProduct purchaseProduct :purchaseProducts) {

                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(purchaseProduct.getDepotnum(),purchaseProduct.getProorders());

                if(productStock != null){
                    if(flag){
                        productStock.setPurchasenum(productStock.getPurchasenum() + purchaseProduct.getNum());
                    }else{
                        productStock.setPurchasenum(productStock.getPurchasenum() - purchaseProduct.getNum());
                    }
                    productStockService.updateNotNull(productStock);
                }

            }


        }



    }

    /**
     * 修改审核状态
     * @param status  0、未审核    1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)orders.getUserData();
        PurchaseOrders purchaseOrders = purchaseOrdersService.selectByKey(id);
        purchaseOrders.setShstatus(status);

        if(status == 1){

            purchaseOrders.setShpeople(getAdminName());
            purchaseOrders.setShdate(getCurrentDate());

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            purchaseOrders.setShpeople(NumberUtil.NULLSTRING);
            purchaseOrders.setShdate(NumberUtil.NULLSTRING);

            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
        }

        purchaseOrdersService.updateNotNull(purchaseOrders);
    }


    public void findInquirySearch(){

        String pageSizes =  inquiry_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreInquiryImport(1);
        }

    }


    public void findInquiryPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreInquiryImport(pageNum);
    }



    //导入---询价单
    public void importButtonInquiry(){

        stage.setTitle("导入-询价单");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/inquiry_import.fxml"));
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

        List<InquiryOrder> inquiryOrder = inquiryOrderService.findInquiryOrder(inquiryCheckBox.isSelected()? 1 : 0,pageNum,pageSize);

        PageInfo<InquiryOrder> pageInfo = new PageInfo<>(inquiryOrder);

        inquiry_find_fast.setUserData(1); //首页

        inquiry_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        inquiry_find_next.setUserData(pageInfo.getNextPage());//下一页

        inquiry_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<InquiryOrder> list = FXCollections.observableArrayList();


//        inquiryid.setCellValueFactory(new PropertyValueFactory("id"));
        findinquiryorder.setCellValueFactory(new PropertyValueFactory("orders"));
        findinquirydata.setCellValueFactory(new PropertyValueFactory("paremdate"));
        findsuppliernum.setCellValueFactory(new PropertyValueFactory("suppliernum"));
        findstatus.setCellValueFactory(new PropertyValueFactory("strstatus"));
        /*findcomestock.setCellValueFactory(new PropertyValueFactory("purpeople"));*/


        for (InquiryOrder inquiryOrder1:inquiryOrder) {

            inquiryOrder1.setParemdate(new SimpleDateFormat("yyyy-MM-dd").format(inquiryOrder1.getCreatedate()));

            if(inquiryOrder1.getShstatus()==0){
                inquiryOrder1.setStrstatus("未审核");
            }else{

                inquiryOrder1.setStrstatus("已审核");
            }

            list.add(inquiryOrder1);

        }

        inquiryView.setItems(list); //tableview添加list

        inquiryView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InquiryOrder>() {
            @Override
            public void changed(ObservableValue<? extends InquiryOrder> observableValue, InquiryOrder oldItem, InquiryOrder newItem) {
                if(newItem != null && newItem.getId()>0){
                    loadMoreInquiryProductImport(newItem.getId());
                }
            }
        });
    }


public void loadMoreInquiryProductImport(long id){
        inquiryOrderId=id;
         List<InquiryProduct> inquiryProducts = inquiryProductService.findInquiryProductByInquiryid(id);
        inquiryProductView.setEditable(true);
         inquiryproid.setCellFactory(CheckBoxTableCell.forTableColumn(inquiryproid));

        inquiryproid.setCellValueFactory(new PropertyValueFactory("checked"));
        findproid.setCellValueFactory(new PropertyValueFactory("proisnum"));
        finprosort.setCellValueFactory(new PropertyValueFactory("sort"));
        findproname.setCellValueFactory(new PropertyValueFactory("proname"));
        finpronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        findproprice.setCellValueFactory(new PropertyValueFactory("proprice"));

        inquiryImportProperties.clear();

        for (InquiryProduct inquiryProduct:inquiryProducts) {

            InquiryImportProperty inquiryImportProperty = new InquiryImportProperty(false,inquiryProduct.getId(),inquiryProduct.getProisnum(),inquiryProduct.getSort(),inquiryProduct.getProname(),inquiryProduct.getPronum(),inquiryProduct.getProprice());

            inquiryImportProperties.add(inquiryImportProperty);

        }

    inquiryProductView.setItems(inquiryImportProperties); //tableview添加list

}




    //导入----根据询价单据编号查询询价产品

    //确定导入产品
    public  void importInquiryProductData(){

            InquiryOrder inquiryOrder = inquiryOrderService.selectByKey(inquiryOrderId);

                //存储被关联单据
                relation.setBeRelationName("询价单");
                relation.setBeRelationId(inquiryOrder.getId());
                relationLock = true;

            for(InquiryImportProperty inquiryImportProperty : inquiryImportProperties){
//                boolean aoutationLock = true;
                if(inquiryOrder.getShstatus()==0){
                    alert_informationDialog(AppConst.ALERT_EXAMINE);
                }else{
                    //选中导入的产品
                    if(inquiryImportProperty.isChecked()){

                        InquiryProduct inquiryProduct = inquiryProductService.selectByKey(inquiryImportProperty.getId());


//                        for (PurchaseProductProperty purchaseProductProperty:purchaseProductProperties) {
//                            if(purchaseProductProperty.getProorders().equals(inquiryImportProperty.getProisnum())){
//                                aoutationLock = false;
//                                alert_informationDialog("产品"+inquiryImportProperty.getProisnum()+"重复!");
//                            }
//                        }

//                        if(aoutationLock){
                        suppliernum.setText(inquiryOrder.getSuppliernum()); //供应商编号
                        suppliername.setText(inquiryOrder.getSuppliername());//供应商名称

                        PurchaseOrders purchaseOrders = new PurchaseOrders();
                        purchaseOrders.setSupplieraddress(inquiryOrder.getSupplieraddress());
                        purchaseOrders.setSupplierfax(inquiryOrder.getSupplierfax());
                        purchaseOrders.setSupplierphone(inquiryOrder.getSupplierphone());
                        purchaseOrders.setSupplierconcat(inquiryOrder.getSupplierconcat());
                        loadSupplier(inquiryOrder.getSuppliernum(),purchaseOrders);

                        int taxFinal = inquiryOrder.getTaxs();//税别
                        int currencysFinal = inquiryOrder.getCurrencys();//币别

                        purpeopletype.getSelectionModel().select(inquiryOrder.getPurpeopletype()-1);
                        purpeople.setText(inquiryOrder.getPurpeople());
                        tax.getItems().addAll("外加","内含","零税","免税");
                        tax.getSelectionModel().select(--taxFinal); //税别
                        setComboBox(7L,currency,--currencysFinal); //币别

//                        seeorders.setText(inquiryOrder.getOrders()); //参考单号

                        orders.setUserData(0L);


                        ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(inquiryProduct.getProisnum());

                        DepotProperty depotProperty = new DepotProperty();

                        if(productBasic != null){
                            depotProperty = getDepot(productBasic.getInventoryplace());
                        }


                            PurchaseProductProperty purchaseProductProperty = new PurchaseProductProperty(0,"询价单",inquiryOrder.getOrders(),"0",inquiryProduct.getProisnum(),inquiryProduct.getProname(),inquiryOrder.getSuppliername(),inquiryProduct.getPronum().toString(),inquiryProduct.getProunit(),inquiryProduct.getProprice().toString(),inquiryProduct.getTotalprice().toString(),depotProperty.getDepotOrder(),depotProperty.getDepotName(),depotProperty.getDepotFloor(),inquiryProduct.getProremark(),inquiryProduct.getExpecteddate());
                            purchaseProductProperties.add(purchaseProductProperty);
                            totalCost(Integer.parseInt(purchaseProductProperty.getPronum()),Double.parseDouble(purchaseProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
//                        }

                    }
                }
            }

            inquiryImportProperties.clear();

        coseInquiryWin();
    }





    //导出采购入库单
    public void importPurchaseStock(){

        PurchaseOrders purchaseOrders =  purchaseOrdersService.selectByKey((long)orders.getUserData());

        if(purchaseOrders != null){
            if(purchaseOrders.getShstatus() == 1){

                changeHomeBtn(2,3,1);

                StageManager.purchaseOrders =purchaseOrders;

                StageManager.purchaseProducts = purchaseProductService.findPurchaseProduct((long)orders.getUserData());

                Pane pane1 = StageManager.ORDERCONTENT.get("stockPane");

                pane1.getChildren().setAll(loader.load("/fxml/stock/purchase_stock.fxml"));
            }else{
                alert_informationDialog(AppConst.ALERT_EXAMINE);
            }
        }else{
            alert_informationDialog("暂未单据导出!");
        }


    }





    //备注及说明
    public void description(long purchaseid){

        List<PurchaseDescription> purchaseDescriptions = purchaseDescriptionService.findPurchaseDescription(purchaseid,1);

        purcahsedescrtion.setCellFactory(TextFieldTableCell.forTableColumn());


        purcahsedescrtion.setCellValueFactory(new PropertyValueFactory("content"));

        purchaseDescriptionProperties.clear();

        if(purchaseDescriptions.size() > 0){

            for (PurchaseDescription purchaseDescription:purchaseDescriptions) {

                PurchaseDescriptionProperty purchaseDescriptionProperty = new PurchaseDescriptionProperty(purchaseDescription.getId(),purchaseDescription.getDes());

                purchaseDescriptionProperties.add(purchaseDescriptionProperty);
            }

        }

        descriptionView.setItems(purchaseDescriptionProperties);

    }



    //备注及说明
    public void savePurchaseDescription(long inquiryid){
        for (PurchaseDescriptionProperty purchaseDescriptionProperty :purchaseDescriptionProperties) {
            if(purchaseDescriptionProperty.getContent()!=null){
                if(purchaseDescriptionProperty.getId() > 0){
                    //修改
                    PurchaseDescription purchaseDescription = new PurchaseDescription(purchaseDescriptionProperty.getId(),purchaseDescriptionProperty.getContent(),inquiryid,1);

                    purchaseDescriptionService.updateNotNull(purchaseDescription);
                }else{
                    //新增
                    PurchaseDescription purchaseDescription = new PurchaseDescription(purchaseDescriptionProperty.getContent(),inquiryid,1);

                    purchaseDescriptionService.save(purchaseDescription);
                }
            }
        }
    }


    // 备注及说明---空白
    public void blankPurchaseDescription(){

        PurchaseDescriptionProperty purchaseDescriptionProperty = new PurchaseDescriptionProperty();

        purchaseDescriptionProperties.add(purchaseDescriptionProperty);

    }


    //报表备注
    public void remarks(long purchaseid){

        List<PurchaseDescription> purchaseDescriptions = purchaseDescriptionService.findPurchaseDescription(purchaseid,2);

        purchaseremarks.setCellFactory(TextFieldTableCell.forTableColumn());


//        remid.setCellValueFactory(new PropertyValueFactory("id"));
        purchaseremarks.setCellValueFactory(new PropertyValueFactory("content"));

        purchaseRemarkProperties.clear();

        if(purchaseDescriptions.size() > 0){

            for (PurchaseDescription purchaseDescriptions1:purchaseDescriptions) {

                PurchaseRemarkProperty purchaseRemarkProperty = new PurchaseRemarkProperty(purchaseDescriptions1.getId(),purchaseDescriptions1.getDes());

                purchaseRemarkProperties.add(purchaseRemarkProperty);
            }
        }

        remarksView.setItems(purchaseRemarkProperties);

    }


    //保存询价单报表备注
    public void savePurchaseRemarks(long purchaseid){
        for (PurchaseRemarkProperty purchaseRemarkProperty :purchaseRemarkProperties) {
            if(purchaseRemarkProperty.getContent()!=null){
                if(purchaseRemarkProperty.getId() > 0){

                    //修改
                    PurchaseDescription purchaseDescription = new PurchaseDescription(purchaseRemarkProperty.getId(),purchaseRemarkProperty.getContent(),purchaseid,2);

                    purchaseDescriptionService.updateNotNull(purchaseDescription);
                }else{
                    //新增
                    PurchaseDescription purchaseDescription = new PurchaseDescription(purchaseRemarkProperty.getContent(),purchaseid,2);

                    purchaseDescriptionService.save(purchaseDescription);
                }
            }
        }
    }


    //空白
    public void blankPurchaseRemarks(){
        PurchaseRemarkProperty purchaseRemarkProperty = new PurchaseRemarkProperty();

        purchaseRemarkProperties.add(purchaseRemarkProperty);
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //选中入库仓库
        warehouseid.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                DataSetting dataSetting = dataSettingService.selectByKey(Long.valueOf(newValue.toString()));
                warehousename.setText(dataSetting.getTitle());
            }
        });



        /*setComboBox(7L,tax,0);*/

        String newStr = location.toString();

        int index = newStr.indexOf("purchase_order.fxml");

        if(index != -1){
            relation = new Relation();
            relationLock = false;

            //询价单
            InquiryOrder inquiryOrder =  StageManager.inquiryOrderInfo;
            //报价单
            SaleQuotation saleQuotation = StageManager.saleQuotation ;

            SaleGoods saleGoods = StageManager.saleGoods;

            List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

            for(int i=0,len=employeeBasics.size();i<len;i++){
                purpeopletype.getItems().add(employeeBasics.get(i).getIdnum());
            }
            purpeopletype.getSelectionModel().select(0);



            purpeopletype.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    if(!"".equals(newValue) && newValue != null ){

                        EmployeeBasic employeeBasic =  employeeBasicService.selectEmployeeBasicByIsnum(newValue.toString());

                        purpeople.setText(employeeBasic.getEmpname());


                    }

                }
            });


            setComboBoxNo(33,warehouseid,0);
//            tax.getItems().addAll("外加","内含","零税","免税");
            if(saleQuotation != null){

                //存储被关联单据
                relation.setBeRelationName("报价单");
                relation.setBeRelationId(saleQuotation.getId());
                relationLock = true;


                insert();

                String taxFinal = saleQuotation.getTax();//税别
                String currencysFinal = saleQuotation.getCurrency();//币别

                setTaxCombox(tax,taxFinal);

                setComboBox(7L,currency,currencysFinal); //币别

//                seeorders.setText(saleQuotation.getOfferNo()); //参考单号

                orders.setUserData(0L);

                inquiryProduct();

                List<SaleOfferProduct> saleOfferProducts = iSaleOfferProductService.listSaleOfferProduct(saleQuotation.getId());


                for(int i=0,len = saleOfferProducts.size();i<len;i++){


                    ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(saleOfferProducts.get(i).getProductNo());

                    DepotProperty depotProperty = new DepotProperty();

                    if(productBasic != null){
                        depotProperty = getDepot(productBasic.getInventoryplace());
                    }

                    PurchaseProductProperty purchaseProductProperty = new PurchaseProductProperty(0,"报价单",saleQuotation.getOfferNo(),i+1+"",saleOfferProducts.get(i).getProductNo(),saleOfferProducts.get(i).getProductName(),"",saleOfferProducts.get(i).getNum().toString(),saleOfferProducts.get(i).getUnit(),saleOfferProducts.get(i).getPrice().toString(),saleOfferProducts.get(i).getMoney().toString(),depotProperty.getDepotOrder(),depotProperty.getDepotName(),depotProperty.getDepotFloor(),saleOfferProducts.get(i).getRemark(),setPushBackDate(createdate,10));

                    purchaseProductProperties.add(purchaseProductProperty);

                    totalCost(Integer.parseInt(purchaseProductProperty.getPronum()),Double.parseDouble(purchaseProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

                }


            }else if(inquiryOrder != null){
                //存储被关联单据
                relation.setBeRelationName("询价单");
                relation.setBeRelationId(inquiryOrder.getId());
                relationLock = true;
                changeHomeBtn(2,0,2);

                insert();

            suppliernum.setText(inquiryOrder.getSuppliernum()); //供应商编号
            suppliername.setText(inquiryOrder.getSuppliername());//供应商名称

            PurchaseOrders purchaseOrders = new PurchaseOrders();
                purchaseOrders.setSupplieraddress(inquiryOrder.getSupplieraddress());
                purchaseOrders.setSupplierfax(inquiryOrder.getSupplierfax());
                purchaseOrders.setSupplierphone(inquiryOrder.getSupplierphone());
                purchaseOrders.setSupplierconcat(inquiryOrder.getSupplierconcat());
                loadSupplier(inquiryOrder.getSuppliernum(),purchaseOrders);

                int taxFinal = inquiryOrder.getTaxs();//税别
                int currencysFinal = inquiryOrder.getCurrencys();//币别

                purpeopletype.getSelectionModel().select(inquiryOrder.getPurpeopletype()-1);
                purpeople.setText(inquiryOrder.getPurpeople());
                tax.getItems().addAll("外加","内含","零税","免税");
                tax.getSelectionModel().select(--taxFinal); //税别
                setComboBox(7L,currency,--currencysFinal); //币别

//                seeorders.setText(inquiryOrder.getOrders()); //参考单号

                orders.setUserData(0L);

                inquiryProduct();



                List<InquiryProduct> inquiryProducts = StageManager.inquiryProductsInfo;

                 for(InquiryProduct inquiryProduct : inquiryProducts){


                    ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(inquiryProduct.getProisnum());

                     DepotProperty depotProperty = new DepotProperty();

                     if(productBasic != null){
                         depotProperty = getDepot(productBasic.getInventoryplace());
                     }


                    PurchaseProductProperty purchaseProductProperty = new PurchaseProductProperty(0,"询价单",inquiryOrder.getOrders(),"0",inquiryProduct.getProisnum(),inquiryProduct.getProname(),inquiryOrder.getSuppliername(),inquiryProduct.getPronum().toString(),inquiryProduct.getProunit(),inquiryProduct.getProprice().toString(),inquiryProduct.getTotalprice().toString(),depotProperty.getDepotOrder(),depotProperty.getDepotName(),depotProperty.getDepotFloor(),inquiryProduct.getProremark(),inquiryProduct.getExpecteddate());
                    purchaseProductProperties.add(purchaseProductProperty);

                     totalCost(Integer.parseInt(purchaseProductProperty.getPronum()),Double.parseDouble(purchaseProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));
                }
                tableView1.setEditable(true);
            }else if(saleGoods != null){


                //存储被关联单据
                relation.setBeRelationName("销货单");
                relation.setBeRelationId(saleGoods.getId());
                relationLock = true;
                insert();

                String taxFinal = saleGoods.getTax();//税别
                String currencysFinal = saleGoods.getCurrency();//币别

                setTaxCombox(tax,taxFinal);

                setComboBox(7L,currency,currencysFinal); //币别

//                seeorders.setText(saleGoods.getSaleNo()); //参考单号

                orders.setUserData(0L);

                inquiryProduct();

                List<SaleGoodsProduct> saleGoodsProducts = iSaleGoodsProductService.listSaleGoodsProduct(saleGoods.getId()+"");

                for(int i=0,len = saleGoodsProducts.size();i<len;i++){


                    ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(saleGoodsProducts.get(i).getProductNo());

                    DepotProperty depotProperty = new DepotProperty();

                    if(productBasic != null){
                        depotProperty = getDepot(productBasic.getInventoryplace());
                    }

                    PurchaseProductProperty purchaseProductProperty = new PurchaseProductProperty(0,"销货单",saleGoods.getSaleNo(),i+1+"",saleGoodsProducts.get(i).getProductNo(),saleGoodsProducts.get(i).getProductName(),"",saleGoodsProducts.get(i).getNum().toString(),saleGoodsProducts.get(i).getUnit(),saleGoodsProducts.get(i).getPrice().toString(),saleGoodsProducts.get(i).getMoney().toString(),depotProperty.getDepotOrder(),depotProperty.getDepotName(),depotProperty.getDepotFloor(),saleGoodsProducts.get(i).getRemark(),setPushBackDate(createdate,10));

                    purchaseProductProperties.add(purchaseProductProperty);

                    totalCost(Integer.parseInt(purchaseProductProperty.getPronum()),Double.parseDouble(purchaseProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

                }

            }else{

                findInquiry(1);

                setComboBox(7L,currency,0); //币别

                tax.getSelectionModel().select(0); //税别

            }

            tax.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    totalnum.setText("0");
                    totalmoney.setText("0.00");
                    totaltax.setText("0.00");
                    totalloan.setText("0.00");

                    for(int i=0,len = purchaseProductProperties.size();i<len;i++  ){

                        totalCost(Integer.parseInt(purchaseProductProperties.get(i).getPronum()),Double.parseDouble(purchaseProductProperties.get(i).getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

                    }

                }
            });

            BaseController.clickEvent(tableView1, data ->{
                tableViewIndex = tableView1.getSelectionModel().getSelectedIndex();
                moreInquiryProductClick();
            }, 2);


        }

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


    public void moreInquiryProductClick(){

        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/purchase/purchase_product_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10 ;
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

//        depid.setCellValueFactory(new PropertyValueFactory("id"));
        findproductid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findproductname.setCellValueFactory(new PropertyValueFactory("proname"));
        findprotype.setCellValueFactory(new PropertyValueFactory("protypeStr"));
        findnormprice.setCellValueFactory(new PropertyValueFactory("normprice"));
        findlowprice.setCellValueFactory(new PropertyValueFactory("lowprice"));
        findsafetystock.setCellValueFactory(new PropertyValueFactory("safetystock"));
        findremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (ProductBasic productBasic:productBasics) {

            DataSetting dataSetting = dataSettingService.findDataSettingBySortAndParentId(productBasic.getProtype().intValue(),6);

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
                closeInquiryProductWin();
            }
        });


    }

    public void closeInquiryProductWin(){
        long id =(long)tableViewProduct.getUserData();

        ProductBasic productBasic =  productBasicService.selectByKey(id);

        DepotProperty depotProperty = new DepotProperty();

        if(productBasic != null){
            depotProperty = getDepot(productBasic.getInventoryplace());
        }


        totalnum.setText("0");
        totalmoney.setText("0.00");
        totaltax.setText("0.00");
        totalloan.setText("0.00");

//        boolean aoutationLock = true;

        for(int i=0,len=purchaseProductProperties.size();i<len;i++){
            PurchaseProductProperty purchaseProductProperty = purchaseProductProperties.get(i);

//            if( purchaseProductProperty.getProorders() != null && purchaseProductProperties.get(i).getProorders().equals(productBasic.getIsnum())){
//                aoutationLock = false;
//                alert_informationDialog("产品"+purchaseProductProperty.getProorders()+"重复!");
//
//            }

            if(i == tableViewIndex){
//                if(aoutationLock){

                    purchaseProductProperty.setProorders(productBasic.getIsnum());//产品编号
                    purchaseProductProperty.setProname(productBasic.getProname());//产品名称

                    //品类
                    {
//                    DataSetting dataSettings = dataSettingService.findDataSettingBySortAndParentId(productBasic.getProtype().intValue(),6L);
//                    purchaseProductProperty(dataSettings.getTitle());   //品类

                    }

//                purchaseProductProperty.setPronum(productBasic.getMinnum().toString());//数量

                    purchaseProductProperty.setPronum("0");

                    //基本单位
                    {
                        DataSetting dataSettings = dataSettingService.findDataSettingBySortAndParentId(productBasic.getBasicunit().intValue(),5L);
                        purchaseProductProperty.setProunit(dataSettings.getTitle());   //基本单位

                    }

                    purchaseProductProperty.setSort((i+1)+"");

//                    purchaseProductProperty.setProprice(productBasic.getNormprice().toString());//单价

                    purchaseProductProperty.setProprice("0.00");//单价

//                purchaseProductProperty.setTotalprice(String.valueOf(productBasic.getMinnum()*productBasic.getNormprice()));//金额

                    purchaseProductProperty.setTotalprice("0.00");//金额

                    purchaseProductProperty.setRemarks(productBasic.getRemarks()); //备注

                    purchaseProductProperty.setEstimateDeliver(setPushBackDate(createdate,10));

                    purchaseProductProperty.setDepotnum(depotProperty.getDepotOrder());

                    purchaseProductProperty.setDepotname(depotProperty.getDepotName());

                    purchaseProductProperty.setFloor(depotProperty.getDepotFloor());

                    totalCost(Integer.parseInt(purchaseProductProperty.getPronum()),Double.parseDouble(purchaseProductProperty.getTotalprice()),tax.getSelectionModel().getSelectedIndex()+1,getTaxReruenDouble(createdate.getValue(),1));

//                }
            }
        }

//        totalCost();


        coseWin();
    }






}
