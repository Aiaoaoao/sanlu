package com.yc.education.controller.basic;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.ProductStock;
import com.yc.education.model.basic.*;
import com.yc.education.model.purchase.InquiryProduct;
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SaleOfferProduct;
import com.yc.education.model.sale.SalePurchaseOrderProduct;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.model.stock.StockOutSaleProduct;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.basic.*;
import com.yc.education.service.purchase.InquiryProductService;
import com.yc.education.service.purchase.PurchaseProductService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleOfferProductService;
import com.yc.education.service.sale.ISalePurchaseOrderProductService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import com.yc.education.service.stock.IStockOutSaleProductService;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName ProductBasicController
 * @Description TODO 产品基本资料
 * @Author QuZhangJing
 * @Date 2018-08-14 13:47
 * @Version 1.0
 */
@Controller
public class ProductBasicController extends BaseController implements  Initializable {


    @Autowired
    private ProductBasicService productBasicService; //产品 Service

    @Autowired
    private DepotBasicService depotBasicService; //库位 SErvice

    @Autowired
    private SupplierBasicService supplierBasicService;//供应商基本资料 Service

    @Autowired
    private ProductSupplierService productSupplierService;//产品-供应商

    @Autowired
    private ProductDetailsService productDetailsService;//产品-规格明细

    @Autowired
    private ProductStockService productStockService;//产品 --- 库存

    @Autowired
    private EmployeeBasicService employeeBasicService;//员工 Service





    @FXML
    Label fxmlStatus; //窗体状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox submitvbox; //提交

    @FXML private VBox clearvbox; //清除

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox printingvbox; //打印


    @FXML private HBox insertHBox; //库位

    @FXML private HBox textFieldHBox; //库位

    @FXML private Label scope;//折扣


    private long statusid = 0;


    /*************************************************表单控件 Start *************************************************************/

    /**
     *
     *  isnum
     * proname
     * pronum
     *  invoicenum
     *  basicunit
     * protype
     * normpricetype;
     * normprice;
     * lowpricetype;
     * lowprice;
     * pronature;
     * prosource;
     * packnum;
     * minnum;
     * businesstype;
     * business;
     * purchasetype;
     * purchase;
     * maxstock;
     * safetystock;
     * inventoryplace;
     * remarks;
     * addpeople;
     * adddate;
     * updatepeople;
     * updatedate;
     * costtype;
     * Double cost;
     * Integer isstop;
     * String stopdes;
     *

     *  控件件名与字段名对应 见 com.yc.education.model.ProductBasic
     */

    @FXML TextField isnum;

    @FXML TextField proname;

    @FXML TextField pronum;

    @FXML TextField invoicenum;

    @FXML ComboBox basicunit;

    @FXML ComboBox protype;

    @FXML ComboBox normpricetype;

    @FXML TextField normprice;

    @FXML ComboBox lowpricetype;

    @FXML TextField lowprice;

    @FXML ComboBox pronature;

    @FXML ComboBox prosource;

    @FXML TextField packnum;

    @FXML TextField minnum;

    @FXML ComboBox businesstype;

    @FXML TextField business;

    @FXML ComboBox purchasetype;

    @FXML TextField purchase;

    @FXML TextField maxstock;

    @FXML TextField safetystock;

    @FXML TextField weights;

    @FXML TextField inventoryplace;

    @FXML TextField remarks;

    @FXML TextField addpeople;

    @FXML TextField adddate;

    @FXML TextField updatepeople;

    @FXML TextField updatedate;

    @FXML ComboBox costtype;

    @FXML TextField cost;

    @FXML  TextField costusd; //美金

    @FXML CheckBox isstop;

    @FXML TextField stopdes;


    /**
     * 产品管理之供应商
     */
    private ObservableList<ProductSupplierProperty> productSupplierProperties = FXCollections.observableArrayList();

    /**
     * 产品管理之规格明细
     */
    private ObservableList<ProductDetailsProperty> productDetailsProperties = FXCollections.observableArrayList();



    /*************************************************表单控件 End *************************************************************/


    /**
     *     ******************************** 供应商
     */

    @FXML TableView product_supplier;


    @FXML TableColumn supplier_supid; //编号

    @FXML TableColumn supplier_supplierid; //供应商编号

    @FXML TableColumn supplier_supdes; //供应商简称

    @FXML TableColumn supplier_supply;//主要供应

    @FXML TableColumn supplier_remarks;//备注

    private long supplierNum=0,remarkNum=0;

    /**
     ********************************************* 规格明细
     */
    @FXML TableView product_detail;

    @FXML TableColumn detail_id; //id
    @FXML TableColumn detail_declare; //说明


    @FXML private TableView tableViewProduct; //产品基本查询
    @FXML private TableColumn depid; //id
    @FXML private TableColumn findproductid; //产品基本编号
    @FXML private TableColumn findproductname; //产品基本名称
    @FXML private TableColumn findprotype; //产品类型
    @FXML private TableColumn findnormprice; //产品标准售价
    @FXML private TableColumn findlowprice; //产品最低售价
    @FXML private TableColumn findsafetystock; //安全存量
    @FXML private TableColumn findremarks; //备注

    @FXML TableView tableViewDepot;
    @FXML TableColumn finddepid;
    @FXML TableColumn finddepotid; //id
    @FXML TableColumn finddepotname; //说明



    @FXML private TableView tableView3; //供应商查询
    @FXML private TableColumn supid; //id
    @FXML private TableColumn findsupplierid; //供应商编号
    @FXML private TableColumn findsuppliername; //供应商简称


    @FXML private CheckBox offerOrder; //报价

    @FXML private CheckBox OrderGoods; //订货

    @FXML private CheckBox saleOrder; //销货

    @FXML private CheckBox sellingBack; //销退

    @FXML private CheckBox inquiryOrder; //询价

    @FXML private CheckBox purchaseOrder; //采购

    @FXML private CheckBox saleOut; //销货出库

    @FXML private CheckBox purchaseIn; //采购入库

    @FXML private Button searchBtn; //查找

    //历史交易开始日期
    @FXML DatePicker historyDateStart;
    //历史交易结束日期
    @FXML DatePicker historyDateEnd;



    //报价单 报价产品
    @Autowired
    private ISaleOfferProductService iSaleOfferProductService;

    //订货单 订货产品
    @Autowired
    private ISalePurchaseOrderProductService iSalePurchaseOrderProductService;

    //销货单 销货产品
    @Autowired
    private ISaleGoodsProductService iSaleGoodsProductService;

    //销退单 销退产品
    @Autowired
    private ISaleReturnGoodsProductService iSaleReturnGoodsProductService;

    //询价单 询价产品
    @Autowired
    private InquiryProductService inquiryProductService;

    //采购订单  订单产品
    @Autowired
    private PurchaseProductService purchaseProductService;

    //销货出库 销货出库产品
    @Autowired
    private IStockOutSaleProductService iStockOutSaleProductService;

    //采购入库单 采购入库产品
    @Autowired
    private PurchaseStockProductService purchaseStockProductService;


    @FXML private TableView productHistoryTableView;
    //制单日期
    @FXML private TableColumn createDate;
    //单据类型
    @FXML private TableColumn orderType;
    //单号
    @FXML private TableColumn orderNo;
    //客户/供应
    @FXML private TableColumn supplierName;
    //客户/供应
    @FXML private TableColumn productName;
    //数量
    @FXML private TableColumn productNum;
    //单价
    @FXML private TableColumn productPrice;
    //税别
    @FXML private TableColumn taxType;
    //备注
    @FXML private TableColumn productRemarks;

    //产品基本资料历史查询记录
    ObservableList<ProductHistoryProperty> productHistoryProperties = FXCollections.observableArrayList();


    private Stage stage = new Stage();


    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @FXML private  VBox  productBasic_find_fast;
    @FXML private  VBox  productBasic_find_prev;
    @FXML private  VBox  productBasic_find_next;
    @FXML private  VBox  productBasic_find_last;

    private int pageSize;

    @FXML private TextField product_basic_textField;



    @FXML private  VBox  depot_find_fast;
    @FXML private  VBox  depot_find_prev;
    @FXML private  VBox  depot_find_next;
    @FXML private  VBox  depot_find_last;

    @FXML private TextField depot_textField;

    //交易历史 分页条数
    @FXML private TextField historySize;


    private int tableViewIndex = 0;



    @FXML private  VBox  supplier_find_fast;
    @FXML private  VBox  supplier_find_prev;
    @FXML private  VBox  supplier_find_next;
    @FXML private  VBox  supplier_find_last;

    @FXML private TextField supplier_textField;

    @FXML private CheckBox stopCheckBox;

    @FXML private TextField order_textField;

    @FXML private TextField order_supplier_textField;

    @FXML
    private Label  costlable;
    @FXML
    private Label  dllable;

    /**
     * 生成编号
     * @return
     */
//    public String createIsnum(){
//        String maxIsnum = companyBasicService.selectMaxIdnum();
//        if(maxIsnum != null){
//            String maxAlphabet = maxIsnum.substring(0,1);
//            int currenNumber = Integer.parseInt(maxIsnum.substring(1,maxIsnum.length()));
//            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){
//                if(currenNumber == NumberUtil.MAXNUMBER){
//                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
//                        return NumberUtil.ALPHABET[i+1]+"001";
//                    }
//                }
//            }
//            if(currenNumber>0 && currenNumber < 9){
//                return maxAlphabet +"00"+(currenNumber+1);
//            }else if(currenNumber >= 9 && currenNumber< 99){
//                return maxAlphabet +"0"+(currenNumber+1);
//            }else{
//                return maxAlphabet +(currenNumber+1);
//            }
//        }
//        return "A001";
//    }





    public void findProductBasicSearch(){

        String pageSizes =product_basic_textField.getText();


        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreProduct(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }


    }


    public void findProductBasicPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreProduct(pageNum);

    }




    public void moreProductButtonClick(){

        stage.setTitle("现有产品基本查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/product_find.fxml"));
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

        List<ProductBasic> productBasics = productBasicService.selectProductBasic(stopCheckBox.isSelected() ? 1 :0,pageNum,pageSize);

        PageInfo<ProductBasic> pageInfo = new PageInfo<>(productBasics);

        productBasic_find_fast.setUserData(1); //首页

        productBasic_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        productBasic_find_next.setUserData(pageInfo.getNextPage());//下一页

        productBasic_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<ProductBasic> list = FXCollections.observableArrayList();

        tableViewProduct.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        depid.setCellValueFactory(new PropertyValueFactory("id"));
        findproductid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findproductname.setCellValueFactory(new PropertyValueFactory("proname"));
        findprotype.setCellValueFactory(new PropertyValueFactory("protype"));
        findnormprice.setCellValueFactory(new PropertyValueFactory("normprice"));
        findlowprice.setCellValueFactory(new PropertyValueFactory("lowprice"));
        findsafetystock.setCellValueFactory(new PropertyValueFactory("safetystock"));
        findremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (ProductBasic productBasic:productBasics) {

            if(productBasic.getIsstop() ==1){
                productBasic.setProname(productBasic.getProname()+"(停用)");
            }

            list.add(productBasic);
        }

        tableViewProduct.setItems(list); //tableview添加list

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductBasic>() {
            @Override
            public void changed(ObservableValue<? extends ProductBasic> observableValue, ProductBasic oldItem, ProductBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    isnum.setUserData(newItem.getId());
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
        long id =(long)isnum.getUserData();
        ProductBasic productBasic =  productBasicService.selectByKey(id);
        loadData(productBasic);
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }





    public void findDepotSearch(){

        String pageSizes =depot_textField.getText();


        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreProductDepot(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }


    }


    public void findDepotPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreProductDepot(pageNum);

    }


    /**
     * 现有库位查询
     */
    public void moreProductDepotButtonClick(){

        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/product_depot_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreProductDepot(1);
    }


    /**
     * 现有库位查询
     */
    public void loadMoreProductDepot(int pageNum){

        List<DepotBasic> depotBasicList = depotBasicService.selectDepotBasic("".equals(order_textField.getText()) || order_textField.getText() == null ? "" : order_textField.getText(),pageNum,pageSize);

        PageInfo<DepotBasic> pageInfo = new PageInfo<>(depotBasicList);

        depot_find_fast.setUserData(1); //首页

        depot_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        depot_find_next.setUserData(pageInfo.getNextPage());//下一页

        depot_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<DepotBasic> list = FXCollections.observableArrayList();


        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        finddepid.setCellValueFactory(new PropertyValueFactory("id"));
        finddepotid.setCellValueFactory(new PropertyValueFactory("isnum"));
        finddepotname.setCellValueFactory(new PropertyValueFactory("depname"));

        for (DepotBasic depotBasic:depotBasicList) {
            list.add(depotBasic);
        }

        tableViewDepot.setItems(list); //tableview添加list

        tableViewDepot.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DepotBasic>() {
            @Override
            public void changed(ObservableValue<? extends DepotBasic> observableValue, DepotBasic oldItem, DepotBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    statusid = newItem.getId();
                }
            }
        });


        tableViewDepot.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeDepotWin();
            }
        });


    }

    public void closeDepotWin(){

        DepotBasic depotBasic = depotBasicService.selectByKey(statusid);

     if(depotBasic != null){

         ObservableList<Node> textFields = textFieldHBox.getChildren();

         textFieldHBox.setUserData("");
         for(int i=0,len=textFields.size();i<len;i++){
             TextField textField =  (TextField)textFields.get(i);
             if(i==len-1){
                 textField.setText(depotBasic.getIsnum());
             }
             if(textFieldHBox.getUserData().equals("")){
                 textFieldHBox.setUserData(textField.getText());
             }else{
                 textFieldHBox.setUserData(textFieldHBox.getUserData()+","+textField.getText());
             }

         }


     }
        coseWin(); //关闭窗体
    }



    /**
     * 加载数据
     * @param productBasic
     */
    public void  loadData(ProductBasic productBasic){


           isnum.setUserData(productBasic.getId());

           isnum.setText(productBasic.getIsnum());

           if(productBasic.getIsstop() == 1){
               proname.setText(productBasic.getProname()+"(停用)");
           }else{
               proname.setText(productBasic.getProname());
           }

           pronum.setText(productBasic.getPronum());

          invoicenum.setText(productBasic.getInvoicenum());

          /*  basicunit.getItems().setAll(
                    "斤","公斤","吨"
            );*/


          int bct = productBasic.getBasicunit().intValue();

         /* basicunit.getSelectionModel().select(--bct);*/


        setComboBox(5L,basicunit,--bct);

       /* protype.getItems().setAll(
                "零件","螺纹刀片"
        );*/

          int pte = productBasic.getProtype().intValue();

        /*  protype.getSelectionModel().select(--pte);*/

        setComboBox(6L,protype,--pte);

        setComboBox(7L,normpricetype,0);
        /*normpricetype.getItems().setAll(
                "RMB"
        );*/

        /*  int nte = productBasic.getNormpricetype().intValue();*/

        /* normpricetype.getSelectionModel().select(0);*/

         normprice.setText(productBasic.getNormprice().toString());

       /*  int lte = productBasic.getLowpricetype().intValue();*/

       /* lowpricetype.getItems().setAll(
                "RMB"
        );*/
        setComboBox(7L,lowpricetype,0);
        /* lowpricetype.getSelectionModel().select(0);*/

         lowprice.setText(productBasic.getLowprice().toString());


        {
            String nomPriceStr = normprice.getText();
            String lowPriceStr = lowprice.getText();

            if(!"".equals(nomPriceStr)&&!"".equals(lowPriceStr)){
                Double nomprice =  Double.parseDouble(nomPriceStr);//标准售价
                Double lowprice = Double.parseDouble(lowPriceStr);//最低售价
                Double scopePoint = (lowprice/nomprice)*100;
                if(scopePoint.toString().equals("NaN"))scopePoint = 0.00;
                if(scopePoint.toString().equals("-Infinity"))scopePoint = 0.00;
                BigDecimal bigDecimal = new BigDecimal(scopePoint);
                scope.setText(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"%");
            }
        }


        int pae = productBasic.getPronature().intValue();

        setComboBox(8L,pronature,--pae);




        int pse = productBasic.getProsource().intValue();
        setComboBox(9L,prosource,--pse);

        packnum.setText(productBasic.getPacknum().toString());

        minnum.setText(productBasic.getMinnum().toString());



        int bet = productBasic.getBusinesstype().intValue();

        businesstype.getSelectionModel().select(--bet);

         business.setText(productBasic.getBusiness());




        int phe = productBasic.getPurchasetype().intValue();

        purchasetype.getSelectionModel().select(--phe);

         purchase.setText(productBasic.getPurchase());

         maxstock.setText(productBasic.getMaxstock().toString());

         safetystock.setText(productBasic.getSafetystock().toString());

        weights.setText(productBasic.getWeights().toString());

         String place = productBasic.getInventoryplace();



             ObservableList<TextField> placeList = FXCollections.observableArrayList();
             String[] places= place.split(",");
                textFieldHBox.setPrefWidth(0);
                textFieldHBox.setUserData("");
                insertHBox.setPrefWidth(227);
             for (int i=0;i<places.length;i++){
                 textFieldHBox.setPrefWidth(textFieldHBox.getPrefWidth()+70);
                 insertHBox.setPrefWidth(insertHBox.getPrefWidth()+70);
                 TextField textField = new TextField();
                 textField.setPrefWidth(70.0);
                 textField.setPrefHeight(23.0);
                 textField.setText(places[i]);
                 textField.setOnKeyPressed(new EventHandler<KeyEvent>(){
                     @Override
                     public void handle(KeyEvent event) {
                         if(event.getCode()==KeyCode.ENTER){
                             findIsNum(event);
                         }
                         if(event.getCode()==KeyCode.DELETE){
                             if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                                 DepotKeyPress(event);
                             }
                         }
                     }
                 });
                 if(i==0){
                     textFieldHBox.setUserData(places[i]);
                 }else{
                     textFieldHBox.setUserData(textFieldHBox.getUserData()+","+places[i]);
                 }

                 placeList.add(textField);
             }
             textFieldHBox.getChildren().setAll(placeList);

         remarks.setText(productBasic.getRemarks());

        addpeople.setText(productBasic.getAddpeople());

        adddate.setText(productBasic.getAdddate());

        updatepeople.setText(productBasic.getUpdatepeople());

        updatedate.setText(productBasic.getUpdatedate());

   /*     costtype.getItems().setAll(
                "RMB"
        );*/

        setComboBox(7L,costtype,0);
        /*int cte = productBasic.getCosttype().intValue();*/

        /*costtype.getSelectionModel().select(0);*/

        cost.setText(productBasic.getCost().toString());
        costusd.setText(productBasic.getUsdcost().toString());
        if(productBasic.getIsstop()==1){
            isstop.setSelected(true);
        }else{
            isstop.setSelected(false);
        }
        stopdes.setText(productBasic.getStopdes());


        loadSupplier(productBasic.getId());

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(false);

            deletevbox.setDisable(false);


        //权限管理
        matchingPermissions("产品基本资料",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);

    }




    public  void  loadSupplier(long productId){

        /**
         * ******************************************供应商&&TableView
         */


        List<ProductSupplier > productSuppliers = productSupplierService.selectProducctSupplierByProid(productId);


        supplier_supplierid.setCellFactory(TextFieldTableCell.forTableColumn());
        supplier_supdes.setCellFactory(TextFieldTableCell.forTableColumn());
        supplier_supply.setCellFactory(TextFieldTableCell.forTableColumn());
        supplier_remarks.setCellFactory(TextFieldTableCell.forTableColumn());

        supplier_supid.setCellValueFactory(new PropertyValueFactory("id"));
        supplier_supplierid.setCellValueFactory(new PropertyValueFactory("supplierid"));
        supplier_supdes.setCellValueFactory(new PropertyValueFactory("supdes"));
        supplier_supply.setCellValueFactory(new PropertyValueFactory("supply"));
        supplier_remarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        productSupplierProperties.clear();

        if(productSuppliers.size()>0) {

            for (ProductSupplier productSupplier : productSuppliers) {


                ProductSupplierProperty productSupplierProperty = new ProductSupplierProperty(productSupplier.getId(),productSupplier.getSupplierid(),productSupplier.getSupdes(),productSupplier.getSupply(),productSupplier.getRemarks());

                productSupplierProperties.add(productSupplierProperty);

            }

        }
        product_supplier.setItems(productSupplierProperties);


        product_supplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductSupplierProperty>() {
            @Override
            public void changed(ObservableValue<? extends ProductSupplierProperty> observableValue, ProductSupplierProperty oldItem, ProductSupplierProperty newItem) {
                if(newItem.getId() >0){
                    supplierNum=newItem.getId();
                }else{
                    supplierNum=0;
                }
            }
        });








        /**
         * ******************************************规格明细&&TableView
         */


        List<ProductDetails> productDetails = productDetailsService.selectProductDetailsByProid(productId);



        detail_declare.setCellFactory(TextFieldTableCell.forTableColumn());

        detail_id.setCellValueFactory(new PropertyValueFactory("id"));
        detail_declare.setCellValueFactory(new PropertyValueFactory("declare"));

        productDetailsProperties.clear();

        if(productSuppliers.size()>0) {
            for (ProductDetails productDetails1 : productDetails) {

                ProductDetailsProperty productDetailsProperty = new ProductDetailsProperty(productDetails1.getId(),productDetails1.getDeclare());

                productDetailsProperties.add(productDetailsProperty);

            }
        }
        product_detail.setItems(productDetailsProperties);

        product_detail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductDetailsProperty>() {
            @Override
            public void changed(ObservableValue<? extends ProductDetailsProperty> observableValue, ProductDetailsProperty oldItem, ProductDetailsProperty newItem) {
                if(newItem.getId() >0){
                    remarkNum=newItem.getId();
                }
            }
        });

    }



    /**
     * 产品管理供应商 之TabelView 增改
     * @param proid
     */
    public void saveProductSupplier(long proid){
        for (ProductSupplierProperty productSupplierProperty :productSupplierProperties) {
            if(productSupplierProperty.getSupplierid()!=null){
                if(productSupplierProperty.getId()>0){
                    //修改供应商
                    ProductSupplier productSupplier = new ProductSupplier(productSupplierProperty.getId(),productSupplierProperty.getSupplierid(),productSupplierProperty.getSupdes(),productSupplierProperty.getSupply(),productSupplierProperty.getRemarks());
                    productSupplierService.updateNotNull(productSupplier);
                }else{
                    //新增供应商
                    ProductSupplier productSupplier = new ProductSupplier(productSupplierProperty.getId(),productSupplierProperty.getSupplierid(),productSupplierProperty.getSupdes(),productSupplierProperty.getSupply(),productSupplierProperty.getRemarks(),proid);
                    productSupplierService.save(productSupplier);
                }
            }
        }
    }

    /**
     * 产品管理规格明细 之TabelView 增改
     * @param proid
     */
    public void saveProductDetails(long proid){
        for (ProductDetailsProperty productDetailsProperty :productDetailsProperties) {
            if(productDetailsProperty.getDeclare()!=null){
                if(productDetailsProperty.getId()>0){
                    //修改规格明细
                    ProductDetails productDetails = new ProductDetails(productDetailsProperty.getId(),productDetailsProperty.getDeclare());
                    productDetailsService.updateNotNull(productDetails);
                }else{
                    //新增规格明细
                    ProductDetails productDetails = new ProductDetails(productDetailsProperty.getId(),productDetailsProperty.getDeclare(),proid);
                    productDetailsService.save(productDetails);
                }
            }
        }
    }



    //产品供应商空白行
    public void blankProductSupplier(){
        ProductSupplierProperty productSupplierProperty  = new ProductSupplierProperty();
        productSupplierProperties.add(productSupplierProperty);
    }

    //产品规格明细空白行
    public void blankDetails(){
        ProductDetailsProperty productDetailsProperty = new ProductDetailsProperty();
        productDetailsProperties.add(productDetailsProperty);
    }


    public void findSupplierSearch(){

        String pageSizes =supplier_textField.getText();


        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreProductSupplier(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }


    }


    public void findSupplierPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreProductSupplier(pageNum);

    }



    public void moreProductSupplierButtonClick(){

        stage.setTitle("现有供应商查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/product_supplier_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreProductSupplier(1);
    }


    /**
     * 现有供应商查询
     */
    public void loadMoreProductSupplier(int pageNum){

        List<SupplierBasic> supplierBasics = supplierBasicService.selectSupplierBasic("".equals(order_supplier_textField.getText()) || order_supplier_textField.getText() ==  null ? "" :order_supplier_textField.getText(),pageNum,pageSize);

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
                    tableView3.setUserData(newItem.getId());
                }
            }
        });


        tableView3.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeProductSupplierWin();
            }
        });


    }

    public void closeProductSupplierWin(){
        long id =(long)tableView3.getUserData();
        SupplierBasic supplierBasic =  supplierBasicService.selectByKey(id);



        for(int i=0,len=productSupplierProperties.size();i<len;i++){
            ProductSupplierProperty supplierPurchaserProperty =productSupplierProperties.get(i);
            if(i == tableViewIndex){
                supplierPurchaserProperty.setSupplierid(supplierBasic.getIdnum());
                supplierPurchaserProperty.setSupdes(supplierBasic.getSupdes());
                supplierPurchaserProperty.setRemarks(supplierBasic.getRemarks());

            }

        }

        coseWin();
    }


    /**
     * product_supplier 键盘按下触发
     * @param event
     */
    public void product_supplierKey(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {
            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                productSupplierService.delete(supplierNum);

                ObservableList<ProductSupplierProperty> productSupplierPropertiesNew = FXCollections.observableArrayList();

                for (ProductSupplierProperty productSupplierProperty : productSupplierProperties) {
                    if (productSupplierProperty.getId() != supplierNum) {
                        productSupplierPropertiesNew.add(productSupplierProperty);
                    }
                }
                productSupplierProperties.clear();
                productSupplierProperties.setAll(productSupplierPropertiesNew);
            }
        }
        if (event.getCode() == KeyCode.INSERT) {
            //联系人空白行
            blankProductSupplier();
        }

        if(event.getCode() == KeyCode.F4){
            moreProductSupplierButtonClick();
        }



    }




    /**
     * product_detail 键盘按下触发
     * @param event
     */
    public void product_detailKey(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {
            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                productDetailsService.delete(remarkNum);

                ObservableList<ProductDetailsProperty> productDetailsPropertiesNew = FXCollections.observableArrayList();

                for (ProductDetailsProperty productDetailsProperty : productDetailsProperties) {
                    if (productDetailsProperty.getId() != remarkNum) {
                        productDetailsPropertiesNew.add(productDetailsProperty);
                    }
                }
                productDetailsProperties.clear();
                productDetailsProperties.setAll(productDetailsPropertiesNew);
            }
        }
        if (event.getCode() == KeyCode.INSERT) {
            //空白行
            blankDetails();
        }
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findSupplier(int pageNum){

       List<ProductBasic> productBasics =  productBasicService.selectProductBasic(pageNum,1);

        PageInfo<ProductBasic> pageInfo = new PageInfo<>(productBasics);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        productBasics.forEach(companyBasic1 ->loadData(companyBasic1));

        if(productBasics == null  || productBasics.size() == 0){

            loadSupplier(0);

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(false);

            deletevbox.setDisable(false);


            //权限管理
            matchingPermissions("产品基本资料",insertvbox,deletevbox,updatevbox,printingvbox,clearvbox);

        }

    }



    /**
     * 上下首尾跳页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findSupplier(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);
    }




    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

                 proname.setDisable(!status);
                 pronum.setDisable(!status);
                 invoicenum.setDisable(!status);
                 basicunit.setDisable(!status);
                 protype.setDisable(!status);
                 normpricetype.setDisable(!status);
                 normprice.setDisable(!status);
                 lowpricetype.setDisable(!status);
                 lowprice.setDisable(!status);
                  pronature.setDisable(!status);
                  prosource.setDisable(!status);
                  packnum.setDisable(!status);
                  minnum.setDisable(!status);
                  businesstype.setDisable(!status);
                  business.setDisable(!status);
                  purchasetype.setDisable(!status);
                  purchase.setDisable(!status);
                  maxstock.setDisable(!status);
                  safetystock.setDisable(!status);
                  weights.setDisable(!status);
                  /*inventoryplace.setEditable(status);*/
                  remarks.setDisable(!status);
                  addpeople.setDisable(!status);
                  adddate.setDisable(!status);
                  updatepeople.setDisable(!status);
                  updatedate.setDisable(!status);
                  costtype.setDisable(!status);
                  cost.setDisable(!status);
                  costusd.setDisable(!status);
                  isstop.setDisable(!status);
                  stopdes.setDisable(!status);
                  insertHBox.setDisable(!status);
                product_supplier.setEditable(status);
                product_detail.setEditable(status);

    }




    /**
     * 清空
     */
    public void clearValue(){
        isnum.setText(NumberUtil.NULLSTRING);
        proname.setText(NumberUtil.NULLSTRING);
        pronum.setText(NumberUtil.NULLSTRING);
        invoicenum.setText(NumberUtil.NULLSTRING);
        basicunit.getSelectionModel().select(0);
        protype.getSelectionModel().select(0);
        normpricetype.getSelectionModel().select(0);
        normprice.setText("0.00");
        lowpricetype.getSelectionModel().select(0);
        lowprice.setText("0.00");
        pronature.getSelectionModel().select(0);
        prosource.getSelectionModel().select(0);
        packnum.setText("0");
        minnum.setText("0");
        businesstype.getSelectionModel().select(0);
        business.setText(NumberUtil.NULLSTRING);
        purchasetype.getSelectionModel().select(0);
        purchase.setText(NumberUtil.NULLSTRING);
        maxstock.setText("0");
        safetystock.setText("0");
        weights.setText("0.00");
        /*inventoryplace.setText(NumberUtil.NULLSTRING);*/
        remarks.setText(NumberUtil.NULLSTRING);
       addpeople.setText(NumberUtil.NULLSTRING);
        LocalDateTime localDateTime = LocalDateTime.now();
        adddate.setText(NumberUtil.NULLSTRING);
        updatepeople.setText(NumberUtil.NULLSTRING);
        updatedate.setText(NumberUtil.NULLSTRING);
        isstop.setSelected(false);
        stopdes.setText(NumberUtil.NULLSTRING);
        cost.setText("0.00");
        costusd.setText("0.00");
        scope.setText("0"+"%");
        productDetailsProperties.clear();
        productSupplierProperties.clear();

    }




    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

       /*String idnums ="";

        if(submitStuts==2){
            idnums=isnum.getText();
        }*/


        String isnumText = isnum.getText();

        if("".equals(isnumText) || isnum.getText() == null ){
            alert_informationDialog("请填写产品编号!");
            return;
        }

        String newsupdes = proname.getText();

        if(newsupdes.indexOf("(停用)") != -1){
            newsupdes = newsupdes.substring(0,newsupdes.indexOf("("));
        }

        ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(isnumText);

        if(productBasic != null){
            alert_informationDialog("产品编号重复!");
            return;
        }else{

            productBasic =  new ProductBasic(0L,
                    isnum.getText(),
                    newsupdes,
                    pronum.getText(),
                    invoicenum.getText(),
                    (long)basicunit.getSelectionModel().getSelectedIndex()+1,
                    (long)protype.getSelectionModel().getSelectedIndex()+1,
                    (long)normpricetype.getSelectionModel().getSelectedIndex()+1,
                    !normprice.getText().equals("")?Double.parseDouble(normprice.getText()):0.00,
                    (long)lowpricetype.getSelectionModel().getSelectedIndex()+1,
                    !lowprice.getText().equals("")?Double.parseDouble(lowprice.getText()):0.00,
                    (long)pronature.getSelectionModel().getSelectedIndex()+1,
                    (long)prosource.getSelectionModel().getSelectedIndex()+1,
                    !packnum.getText().equals("")?Integer.parseInt(packnum.getText()):0,
                    !minnum.getText().equals("")?Integer.parseInt(minnum.getText()):0,
                    (long)businesstype.getSelectionModel().getSelectedIndex()+1,
                    business.getText(),
                    (long)purchasetype.getSelectionModel().getSelectedIndex()+1,
                    purchase.getText(),
                    !maxstock.getText().equals("")?Integer.parseInt(maxstock.getText()):0,
                    !safetystock.getText().equals("")?Integer.parseInt(safetystock.getText()):0,
                    !weights.getText().equals("")?Double.parseDouble(weights.getText()):0.00,
                    textFieldHBox.getUserData().toString(),
                    remarks.getText(),
                    addpeople.getText(),
                    adddate.getText(),
                    updatepeople.getText(),
                    updatedate.getText(),
                    (long)costtype.getSelectionModel().getSelectedIndex()+1,
                    !cost.getText().equals("")?Double.parseDouble(cost.getText()):0.00,
                    !costusd.getText().equals("")?Double.parseDouble(costusd.getText()):0.00,
                    isstop.isSelected()?1:0,
                    stopdes.getText());


            if(submitStuts==1){
                productBasicService.save(productBasic);
                updateDepotAndProduct(productBasic,1);
            }else if(submitStuts ==2){
                productBasic.setId((long)isnum.getUserData());
                updateDepotAndProduct(productBasic,2);
                productBasicService.updateNotNull(productBasic);

            }

            saveProductSupplier(productBasic.getId());
            saveProductDetails(productBasic.getId());


            this.loadData(productBasic); //重新加载数据

            NumberUtil.changeStatus(fxmlStatus,0);
            submitvbox.setDisable(true);

        }

    }




    /**
     * 根据产品信息添加产品库存
     * 新建产品库存信息
     * @param productBasic
     * @param type  1、新增  2、修改
     */
    public void updateDepotAndProduct(ProductBasic productBasic,int type){

        String place = productBasic.getInventoryplace();

        if(type == 1){

            if(!"".equals(place)){

                String places[] = place.split(",");

                for (int i=0,len=places.length;i<len;i++){

                    ProductStock productStock = new ProductStock(productBasic.getIsnum(),productBasic.getProname(),0,0,0,0,0,0,productBasic.getNormprice(),productBasic.getNormprice(),places[i]);
                    productStockService.save(productStock);

                }

            }else{
                ProductStock productStock = new ProductStock(productBasic.getIsnum(),productBasic.getProname(),0,0,0,0,0,0,productBasic.getNormprice(),productBasic.getNormprice(),"");
                productStockService.save(productStock);

            }

        }else if(type == 2){


            if(!"".equals(place)){

                String places[] = place.split(",");

                for (int i=0,len=places.length;i<len;i++){

                    ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(places[i],productBasic.getIsnum());

                    if(productStock != null){
                        productStock.setParprice(productBasic.getNormprice());
                        productStock.setProductname(productBasic.getProname());
                        productStockService.updateNotNull(productStock);
                    }

                }

            }else{
                ProductStock productStock = productStockService.findProductStockByDepotAndIsnum("",productBasic.getIsnum());
                if(productStock != null){
                    productStock.setParprice(productBasic.getNormprice());
                    productStock.setProductname(productBasic.getProname());
                    productStockService.updateNotNull(productStock);
                }
            }


        }

    }






    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) isnum.getUserData();
            int rows = productBasicService.delete(id);
            if (rows > 0) {
                findSupplier((int) prev.getUserData()); //初始化第一条数据
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


        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期

        this.changeEditable(true);
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

        blankProductSupplier();
        blankDetails();


    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态


        ObservableList<TextField> placeList = FXCollections.observableArrayList();
        textFieldHBox.setPrefWidth(0);
        textFieldHBox.setUserData("");
        insertHBox.setPrefWidth(227);
        textFieldHBox.setPrefWidth(textFieldHBox.getPrefWidth()+70);
        insertHBox.setPrefWidth(insertHBox.getPrefWidth()+70);
        TextField textField = new TextField();
        textField.setPrefWidth(70.0);
        textField.setPrefHeight(23.0);
        textField.setText("");
        textField.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()==KeyCode.ENTER){
                    findIsNum(event);
                }
            }
        });

        placeList.add(textField);
        textFieldHBox.getChildren().setAll(placeList);

        this.changeEditable(true);



        clearValue();//清空控件

        createPeople(addpeople,adddate);//建档人、建档日期

        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

        blankProductSupplier();
        blankDetails();

    }


    /**
     * 回车查询
     * @param event
     */
    public void isNumKey(KeyEvent event){


        if(event.getCode()== KeyCode.ENTER){

            String value = isnum.getText();

            if(!"".equals(value)){

                ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(value);

                if(productBasic != null){
                    this.loadData(productBasic);
                }

            }

        }

    }

    /**
     * 新增库位
     */
    public void insertWarehouse(){
       if(insertHBox.getPrefWidth()+70<900){
           textFieldHBox.setPrefWidth(textFieldHBox.getPrefWidth()+70);
           insertHBox.setPrefWidth(insertHBox.getPrefWidth()+70);
           TextField textField = new TextField();
           textField.setPrefWidth(70.0);
           textField.setPrefHeight(23.0);
           textField.setOnKeyPressed(new EventHandler<KeyEvent>(){
               @Override
               public void handle(KeyEvent event) {
                    if(event.getCode()==KeyCode.ENTER){
                        findIsNum(event);
                    }

                   if(event.getCode()==KeyCode.DELETE){
                       if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                           DepotKeyPress(event);
                       }
                   }

               }
           });
           textField.setPadding(new Insets(0,0,0,10));
           textFieldHBox.getChildren().add(textField);
       }
    }


    /**
     * 删除库位
     * 根据TextField的layouX位置和盒子总位置来判断删除盒子的下标
     * @param event
     */
    public void DepotKeyPress(KeyEvent event){
        TextField textField1 = (TextField)event.getSource();
        Double layouX = textField1.getLayoutX();

        int deleteIndex = (int)(layouX+70)/70;

         ObservableList<Node> nodes = textFieldHBox.getChildren();

         ObservableList<Node> nodes1 =FXCollections.observableArrayList();

        //删除库位控件
         for(int i=0,len=nodes.size();i<len;i++){
             if((i+1) != deleteIndex){
                 nodes1.add(nodes.get(i));
             }
         }

            //库位信息
        String oldDepot =  textFieldHBox.getUserData().toString();

         String[] depot = oldDepot.split(",");

        textFieldHBox.setUserData("");
        //删除库位编号
        for(int i=0,len=depot.length;i<len;i++){

            if((i+1) != deleteIndex){
                if(textFieldHBox.getUserData().equals("")){
                    textFieldHBox.setUserData(depot[i]);
                }else{
                    textFieldHBox.setUserData(textFieldHBox.getUserData()+","+depot[i]);
                }
            }

        }
        textFieldHBox.setPrefWidth(textFieldHBox.getPrefWidth()-70);
        textFieldHBox.getChildren().setAll(nodes1);




    }

    public void findIsNum(KeyEvent event){

        TextField textField1 = (TextField)event.getSource();

        DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(textField1.getText());

            if(depotBasic == null){
                textField1.setText(NumberUtil.NULLSTRING);
            }else{
                if(textFieldHBox.getUserData().equals("")){
                    textFieldHBox.setUserData(textField1.getText());
                }else{
                    textFieldHBox.setUserData(textFieldHBox.getUserData()+","+textField1.getText());
                }
            }
    }


    /**
     * 计算折扣
     */
    public void scopePrice(){

                    String nomPriceStr = normprice.getText();
                    String lowPriceStr = lowprice.getText();

                    if(!"".equals(nomPriceStr)&&!"".equals(lowPriceStr)){
                        //标准售价
                        Double nomprice =  Double.parseDouble(nomPriceStr);
                        //最低售价
                        Double lowprice = Double.parseDouble(lowPriceStr);
                        Double scopePoint = (lowprice/nomprice)*100;
                        if(scopePoint.toString().equals("NaN"))scopePoint = 0.00;
                        if(scopePoint.toString().equals("-Infinity"))scopePoint = 0.00;
                        BigDecimal b = new BigDecimal(scopePoint);
                        scopePoint = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        scope.setText(scopePoint+"%");
                    }else{
                        scope.setText("0"+"%");
                    }

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {


        isstop.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                boolean isbool =(boolean)newValue;

                if(isbool){
                    stopdes.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
                }else{
                    stopdes.setText("");
                }


            }
        });





        String newStr = location.toString();

        int index = newStr.indexOf("product_basic");

        if(index != -1){





            BaseController.clickEvent(product_supplier, data ->{
                tableViewIndex = product_supplier.getSelectionModel().getSelectedIndex();
                moreProductSupplierButtonClick();
            }, 2);

            setComboBox(5L,basicunit,0);


            setComboBox(6L,protype,0);

            setComboBox(7L,normpricetype,0);


            setComboBox(7L,lowpricetype,0);


            setComboBox(8L,pronature,0);

            setComboBox(9L,prosource,0);

            setComboBox(7L,costtype,0);



            bindEmployee(businesstype,business);

            bindEmployee(purchasetype,purchase);

            findSupplier(1);

            //产品基本资料-成本   查看
            if(!getPermissions("8_138_4")){
//                cost.setText();
                costtype.setVisible(false);
                cost.setVisible(false);
                costusd.setVisible(false);
                costlable.setVisible(false);
                dllable.setVisible(false);
            }

        }



    }


    /**
     * 输入产品名称  自动填充 产品条码和发票品名
     * @param keyEvent
     */
    public  void keyTextFiled(KeyEvent keyEvent){

        String keyProName = proname.getText();

        pronum.setText(keyProName);

        invoicenum.setText(keyProName);
    }




    public void printing(){

        String print =  pronum.getText();

        if(!"".equals(print) && print != null){
            JbarcodeUtil.printCode(print);
            alert_informationDialog("打印成功!");
        }

//        alert_informationDialog("请连接打印设备.....");
    }


    /**
     * 产品基本资料交易历史
     */
    public void searchCheck(){

        String hisSize =  historySize.getText();
        int hisPageSize = 15;
        if(!"".equals(hisSize) && hisSize != null){
            hisPageSize = Integer.parseInt(hisSize);
        }

        String historyStart = "",historyEnd = "";

        ProductBasic productBasic = productBasicService.selectByKey((long)isnum.getUserData());

         if(historyDateStart.getValue() != null){
             historyStart =  historyDateStart.getValue().toString();
         }

         if(historyDateEnd.getValue() != null) {
             historyEnd = historyDateEnd.getValue().toString();
         }

        createDate.setCellValueFactory(new PropertyValueFactory("createDate"));
        orderType.setCellValueFactory(new PropertyValueFactory("orderType"));
        orderNo.setCellValueFactory(new PropertyValueFactory("orderNo"));
        supplierName.setCellValueFactory(new PropertyValueFactory("supplierName"));
        productName.setCellValueFactory(new PropertyValueFactory("productName"));
        productNum.setCellValueFactory(new PropertyValueFactory("productNum"));
        productPrice.setCellValueFactory(new PropertyValueFactory("productPrice"));
        taxType.setCellValueFactory(new PropertyValueFactory("taxType"));
        productRemarks.setCellValueFactory(new PropertyValueFactory("productRemarks"));

        productHistoryProperties.clear();

        if (offerOrder.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询报价单
            List<SaleOfferProduct> saleOfferProducts = iSaleOfferProductService.selectSaleOfferProductsByProductNumAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (SaleOfferProduct saleOfferProduct:saleOfferProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(saleOfferProduct.getSaleQuotations().getAddtime()),"报价单",saleOfferProduct.getSaleQuotations().getOfferNo(),saleOfferProduct.getSaleQuotations().getCustomerName(),"",saleOfferProduct.getNum().toString(),saleOfferProduct.getPrice().doubleValue(),saleOfferProduct.getSaleQuotations().getTax(),saleOfferProduct.getRemark());

                productHistoryProperties.add(productHistoryProperty);
            }


        }
        if (OrderGoods.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询订货单


            List<SalePurchaseOrderProduct> salePurchaseOrderProducts = iSalePurchaseOrderProductService.selectSalePurchaseOrderProductByProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (SalePurchaseOrderProduct salePurchaseOrderProduct:salePurchaseOrderProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(salePurchaseOrderProduct.getSalePurchaseOrders().getAddtime()),"订货单",salePurchaseOrderProduct.getSalePurchaseOrders().getOrderNo(),salePurchaseOrderProduct.getSalePurchaseOrders().getCustomerName(),"",salePurchaseOrderProduct.getNum().toString(),salePurchaseOrderProduct.getPrice().doubleValue(),salePurchaseOrderProduct.getSalePurchaseOrders().getTax(),salePurchaseOrderProduct.getRemark());

                productHistoryProperties.add(productHistoryProperty);
            }
        }
        if (saleOrder.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询销货单
            List<SaleGoodsProduct> saleGoodsProducts = iSaleGoodsProductService.selectSaleGoodsProdutByProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);


            for (SaleGoodsProduct saleGoodsProduct:saleGoodsProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(saleGoodsProduct.getSaleGoods().getAddtime()),"销货单",saleGoodsProduct.getSaleGoods().getSaleNo(),saleGoodsProduct.getSaleGoods().getCustomerName(),"",saleGoodsProduct.getNum().toString(),saleGoodsProduct.getPrice().doubleValue(),saleGoodsProduct.getSaleGoods().getTax(),saleGoodsProduct.getRemark());

                productHistoryProperties.add(productHistoryProperty);

            }

        }
        if (sellingBack.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询销退单
            List<SaleReturnGoodsProduct> saleReturnGoodsProducts = iSaleReturnGoodsProductService.selectSaleReturnGoodsProductdByProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (SaleReturnGoodsProduct saleReturnGoodsProduct:saleReturnGoodsProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(saleReturnGoodsProduct.getSaleReturnGoods().getAddtime()),"销退单",saleReturnGoodsProduct.getSaleReturnGoods().getPinbackNo(),saleReturnGoodsProduct.getSaleReturnGoods().getCustomerNoStr(),"",saleReturnGoodsProduct.getNum().toString(),saleReturnGoodsProduct.getPricing(),saleReturnGoodsProduct.getSaleReturnGoods().getTax(),saleReturnGoodsProduct.getRemark());

                productHistoryProperties.add(productHistoryProperty);

            }

        }
        if (inquiryOrder.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询询价单
            List<InquiryProduct> inquiryProducts = inquiryProductService.selectInquiryProdutByProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (InquiryProduct inquiryProduct:inquiryProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(inquiryProduct.getInquiryOrders().getCreatedate()),"询价单",inquiryProduct.getInquiryOrders().getOrders(),inquiryProduct.getInquiryOrders().getSuppliername(),"",inquiryProduct.getPronum().toString(),inquiryProduct.getProprice(),returnTax(inquiryProduct.getInquiryOrders().getTaxs()),inquiryProduct.getProremark());

                productHistoryProperties.add(productHistoryProperty);

            }
        }
        if (purchaseOrder.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询采购订单
            List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProductAndProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (PurchaseProduct purchaseProduct:purchaseProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(purchaseProduct.getPurchaseOrders().getCreatedate()),"采购订单",purchaseProduct.getPurchaseOrders().getOrders(),purchaseProduct.getPurchaseOrders().getSuppliername(),"",purchaseProduct.getNum().toString(),purchaseProduct.getPrice(),returnTax(purchaseProduct.getPurchaseOrders().getPtax()),purchaseProduct.getRemarks());

                productHistoryProperties.add(productHistoryProperty);
            }

        }
        if (saleOut.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询销货出库单
            List<StockOutSaleProduct> stockOutSaleProducts =  iStockOutSaleProductService.selectStockOutSaleProductByProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (StockOutSaleProduct stockOutSaleProduct:stockOutSaleProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }
                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(stockOutSaleProduct.getStockOutSales().getCreateDate()),"销货出库单",stockOutSaleProduct.getStockOutSales().getOutboundNo(),stockOutSaleProduct.getStockOutSales().getCustomerNoStr(),"",stockOutSaleProduct.getNum().toString(),stockOutSaleProduct.getPrice(),"",stockOutSaleProduct.getRemark());

                productHistoryProperties.add(productHistoryProperty);
            }
        }
        if (purchaseIn.isSelected() ||  !offerOrder.isSelected() &&  !OrderGoods.isSelected() && !saleOrder.isSelected() &&  !sellingBack.isSelected() && !inquiryOrder.isSelected() && !purchaseOrder.isSelected() && !saleOut.isSelected() && !purchaseIn.isSelected()) {
            //根据产品编号查询采购入库单
            List<PurchaseStockProduct> purchaseStockProducts =  purchaseStockProductService.findPurchaseStockProductByProductNameAndStartTimeAndEndTime(productBasic.getIsnum(),historyStart,historyEnd);

            for (PurchaseStockProduct purchaseStockProduct:purchaseStockProducts) {
                if(productHistoryProperties.size()==hisPageSize){
                    continue;
                }

                /**
                 * 产品基本资料--入库单价权限
                 */
                Double stockPrice = 0.00;

                if(getPermissions("1022_1023_4")){

                    stockPrice = purchaseStockProduct.getPrice();

                }


                ProductHistoryProperty productHistoryProperty = new ProductHistoryProperty(new SimpleDateFormat("yyyy-MM-dd").format(purchaseStockProduct.getPurchaseStocks().getCreatedate()),"采购入库单",purchaseStockProduct.getPurchaseStocks().getStockorder(),purchaseStockProduct.getPurchaseStocks().getSuppliername(),"",purchaseStockProduct.getStocknum().toString(),stockPrice,"",purchaseStockProduct.getRemarks());

                productHistoryProperties.add(productHistoryProperty);

            }
        }


        productHistoryTableView.setItems(productHistoryProperties);

    }




    @FXML
    private ImageView rightImage;

    /**
     * 右侧查询Panne 公司基本资料
     */
    public void shoRightPane(){

//        C:\Users\jzdsh\Desktop\login

//        String path = PathUtil.projectPath.replaceAll("\\\\","\\\\\\\\");
//        System.out.println("PATH:"+PathUtil.projectPath);
//        System.out.println("PATH:"+path);
//        Image image = new Image("file:"+path+"\\images\\rightgo.png");
//
//        rightImage.setImage(image);

        Stage stageStock = (Stage) StageManager.CONTROLLER.get("rightWinStage");

        StageManager.rightWin = !StageManager.rightWin;

        if(StageManager.rightWin){
            attrImages(rightImage, PathUtil.imageLeft);
            stageStock.setTitle("产品基本资料");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/basic/loadMoreProduct.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            Stage stageHome = (Stage)StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stageHome.getScene().getWindow().getWidth()+stageHome.getScene().getWindow().getX()-15);
            stageStock.setY(stageHome.getScene().getWindow().getY());

            loadCompanyRight(false);
            stageStock.setResizable(false); //禁止窗体缩放
            stageStock.show();
        }else{
            attrImages(rightImage,PathUtil.imageRight);
            stageStock.close();
        }

    }



    @FXML
    private TableView rightSupplierTableView;
    @FXML
    private TableColumn rightSupplierSort;
    @FXML
    private TableColumn rightSupplierOrder;
    @FXML
    private TableColumn rightSupplierDes;

    @FXML
    private CheckBox rightCheckBox;


    public void loadCompanyRight(boolean isStop){

        rightCheckBox.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                boolean isbool =(boolean)newValue;

                loadCompanyRight(isbool);

            }
        });

        List<ProductBasic> productBasics =  isStop ? productBasicService.selectProductBasic(0) : productBasicService.selectProductBasic("");

        ObservableList<ProductBasic> productBasicsList = FXCollections.observableArrayList();


        rightSupplierSort.setCellValueFactory(new PropertyValueFactory("sort"));
        rightSupplierOrder.setCellValueFactory(new PropertyValueFactory("isnum"));
        rightSupplierDes.setCellValueFactory(new PropertyValueFactory("proname"));

        if(productBasics != null && productBasics.size() > 0){

            for (int i=0,len = productBasics.size();i < len;i++) {
                productBasics.get(i).setSort(i+1);
                if(productBasics.get(i).getIsstop() == 1){
                    productBasics.get(i).setProname(productBasics.get(i).getProname()+"(停用)");
                }
                productBasicsList.add(productBasics.get(i));
            }
        }

        rightSupplierTableView.setItems(productBasicsList);


        rightSupplierTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductBasic>() {
            @Override
            public void changed(ObservableValue<? extends ProductBasic> observableValue, ProductBasic oldItem, ProductBasic newItem) {
//               alert_informationDialog("=="+newItem.getId());

                ProductBasic productBasic = productBasicService.selectByKey(newItem.getId());

                loadData(productBasic);

            }
        });

    }



}
