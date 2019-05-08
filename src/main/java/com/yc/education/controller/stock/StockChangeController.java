package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.ProductStock;
import com.yc.education.model.Relation;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.purchase.InquiryImportProperty;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.model.sale.SaleReturnGoodsProduct;
import com.yc.education.model.stock.*;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.service.sale.ISaleReturnGoodsProductService;
import com.yc.education.service.sale.ISaleReturnGoodsService;
import com.yc.education.service.stock.*;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName StockChangeController
 * @Description TODO  库存异动作业
 * @Author QuZhangJing
 * @Date 2018/11/2 18:16
 * @Version 1.0
 */
@Controller
public class StockChangeController extends BaseController implements Initializable {

    /**
     * 库存异动
     */
    @Autowired
    private StockChangeService stockChangeService;
    /**
     * 库存异动产品
     */
    @Autowired
    private StockChangeProductService stockChangeProductService;
    /**
     * 员工Service  ---异动申请人
     */
    @Autowired
    private EmployeeBasicService employeeBasicService;
    @Autowired
    private DepotBasicService depotBasicService;
    @Autowired
    private PurchaseStockService purchaseStockService;
    @Autowired
    private PurchaseStockProductService purchaseStockProductService;
    @Autowired
    private DataSettingService dataSettingService;

    @Autowired
    private CheckStockService checkStockService;

    @Autowired
    private CheckStockProductService checkStockProductService;
    @Autowired
    private ISaleReturnGoodsService iSaleReturnGoodsService;
    @Autowired
    private ISaleReturnGoodsProductService iSaleReturnGoodsProductService;
    //产品库存
    @Autowired
    private ProductStockService productStockService;
    @Autowired
    private RelationService relationService;

    /**
     * 单据关联容器
     */
    Relation relation = new Relation();
    //每个单据用来锁定关联数据的
    boolean relationLock = false;
    /**
     * 状态
     */
    @FXML private Label fxmlStatus;
    /**
     * 第一页
     */
    @FXML private VBox first;

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



    @FXML private DatePicker changedate; //异动日期

    @FXML private TextField changeorder; //异动单号

    @FXML private TextField depotorder; //异动仓库编号

    @FXML private TextField depotname; //异动仓库名称

    @FXML private ComboBox changetype; //异动类型

    @FXML private ComboBox peopleorder; //异动申请人编号

    @FXML private TextField peoplename; //异动申请人名称

    @FXML private ComboBox changecurrency; //币率

    @FXML private TextField changesourse; //异动来源

    @FXML private TextField createpeople; //制单人

    @FXML private TextField shpeople; //审核人

    @FXML private TextField shdate; //审核日期

    @FXML private TextField updatepeople; //最后修改人

    @FXML private TextField updatedate; //最后修改说明

    @FXML private TextArea remarks; //备注




    //库存异动产品
    @FXML private TableView stockChangeProductView; //库存异动产品TableView
    @FXML private TableColumn findchangesourse; //来源单据
    @FXML private TableColumn findchangeorder; //单号
    @FXML private TableColumn findchangesort; //序号
    @FXML private TableColumn findproductorder; //产品编号
    @FXML private TableColumn findproductname; //产品名称
    @FXML private TableColumn findproductnum; //数量
    @FXML private TableColumn findproductunit; //单位
    @FXML private TableColumn finddepotorder; //库位编号
    @FXML private TableColumn finddepotname; //库位名称
    @FXML private TableColumn findproductremarks; //备注


    //仓库库位
    @FXML private TableView depotView; //仓库库位
    @FXML private TableColumn depid; //  编号
    @FXML private TableColumn finddepotid; //  仓库编号
    @FXML private TableColumn finddepotnames; //  仓库名称

    //库存异动
    @FXML private TableView stockChangeView; //库存异动
    @FXML private TableColumn findstockchangeid; //  编号
    @FXML private TableColumn findstockchangeorder; //  异动单号
    @FXML private TableColumn findstockchangedate; //  制单日期
    @FXML private TableColumn findstockchangedepotorder; //  仓库编号
    @FXML private TableColumn findstockchangedepotname; //  仓库名称
    @FXML private TableColumn findstockchangeremarks; //  备注

    private long checkStockId = 0,checkStockIds = 0;

    //导入  盘库作业
    @FXML private TableView purchaseStockView;

    @FXML private TableColumn stockid;
    @FXML private TableColumn findstockorder; //单据编号
    @FXML private TableColumn findstockdata; //制单日期
    @FXML private TableColumn findsuppliernum; //供应商编号
    @FXML private TableColumn findstatus; //单据状态
    @FXML private TableColumn findcomestock; //到货仓库


    //导入销退订单
    @FXML private TableView purchaseSaleStockView;

    @FXML private TableColumn salestockid;
    @FXML private TableColumn findsalestockorder; //单据编号
    @FXML private TableColumn findsalestockdata; //制单日期
    @FXML private TableColumn findsalesuppliernum; //供应商编号
    @FXML private TableColumn findsalestatus; //单据状态


    @FXML private TableView stockProductView;

    @FXML private TableColumn stockproid;
    @FXML private TableColumn findproid; //产品编号
    @FXML private TableColumn finprosort; //产品序号
    @FXML private TableColumn findproname; //产品名称
    @FXML private TableColumn finpronum; //数量
    @FXML private TableColumn findproprice; //单价



    @FXML private TableView saleReturnstockProductView;

    @FXML private TableColumn saleReturnstockproid; //
    @FXML private TableColumn saleReturnfinprosort; //产品序号
    @FXML private TableColumn saleReturnfindproid; //产品序号
    @FXML private TableColumn saleReturnfindproname; //产品名称
    @FXML private TableColumn saleReturnfinpronum; //数量
    @FXML private TableColumn saleReturnfindproprice; //单价

    private int tableViewIndex = 0;

    private long changeId=0;

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @FXML private VBox stockChange_find_fast;
    @FXML private VBox stockChange_find_prev;
    @FXML private VBox stockChange_find_next;
    @FXML private VBox stockChange_find_last;

    @FXML private TextField stockChange_textField_pageSize;

    @FXML private TextField stockChange_order_textField;

    private int pageSize = 10;

    @FXML private VBox department_find_fast;
    @FXML private VBox department_find_prev;
    @FXML private VBox department_find_next;
    @FXML private VBox department_find_last;

    @FXML private TextField department_textField_pageSize;

    @FXML private TextField department_order_textField;


    /**
     * 复制本单据砖专用锁
     */
    private boolean lockOrder = false;


    /**
     * 库存异动产品之TableView
     */
    private ObservableList<StockChangeProductProperty> stockChangeProductProperties = FXCollections.observableArrayList();


    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
    public String createIsnum(String currentDate){
        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
        String maxIsnum = stockChangeService.selectMaxIdnum(currentDate);
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

    public void findDepartmentSearch(){
        String pageSizes =  department_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadMoreDepartment(1);
        }
    }


    public void findDepartmentPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreDepartment(pageNum);
    }


    public void morePurchaseStockDepotClick(){

        stage.setTitle("现有仓库查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/change_stock_depot_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
                /*stage.initStyle(StageStyle.UNDECORATED);
               DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10 ;
        loadMoreDepartment(1);
    }





    /**
     * 现有库位查询
     */
    public void loadMoreDepartment(int pageNum){

//        List<DepotBasic> departmentBasics = depotBasicService.selectDepotBasic();

        List<DataSetting> dataSettings = dataSettingService.findDataSetting(33L,"".equals(department_order_textField.getText()) || department_order_textField.getText() == null ? "" : department_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<DataSetting> pageInfo = new PageInfo<>(dataSettings);

        department_find_fast.setUserData(1); //首页

        department_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        department_find_next.setUserData(pageInfo.getNextPage());//下一页

        department_find_last.setUserData(pageInfo.getPages());//尾页

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

        DataSetting dataSetting = dataSettingService.selectByKey(id);
        //入库仓库编号
        depotorder.setText(dataSetting.getId().toString());

        //入库仓库名称
        depotname.setText(dataSetting.getTitle());

        coseWin();
    }

    public void coseWin(){
        stage.close();
    }


    public void findStockChangeSearch(){
        String pageSizes =  stockChange_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadStockChange(1);
        }
    }


    public void findStockChangePages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadStockChange(pageNum);
    }


    /**
     * 现有库存异动
     */
    public void moreStockChangeClick(){

        stage.setTitle("现有库存异动");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/stock_change_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadStockChange(1);
    }




    public void loadStockChange(int pageNum){

        List<StockChange> stockChanges = stockChangeService.findStockChange("".equals(stockChange_order_textField.getText()) || stockChange_order_textField.getText() == null ? "" : stockChange_order_textField.getText().toString(),pageNum,pageSize);


        PageInfo<StockChange> pageInfo = new PageInfo<>(stockChanges);

        stockChange_find_fast.setUserData(1); //首页

        stockChange_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        stockChange_find_next.setUserData(pageInfo.getNextPage());//下一页

        stockChange_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<StockChange> list = FXCollections.observableArrayList();



//        findstockchangeid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findstockchangeorder.setCellValueFactory(new PropertyValueFactory("changeorder"));
        findstockchangedate.setCellValueFactory(new PropertyValueFactory("flgTime"));
        findstockchangedepotorder.setCellValueFactory(new PropertyValueFactory("depotorder"));
        findstockchangedepotname.setCellValueFactory(new PropertyValueFactory("depotname"));
        findstockchangeremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (StockChange stockChange:stockChanges) {

            stockChange.setFlgTime(new SimpleDateFormat("yyyy-MM-dd").format(stockChange.getChangedate()));

            list.add(stockChange);

        }

        stockChangeView.setItems(list); //tableview添加list

        stockChangeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StockChange>() {
            @Override
            public void changed(ObservableValue<? extends StockChange> observableValue, StockChange oldItem, StockChange newItem) {
                if(newItem.getId()!= null && !("".equals(newItem.getId()))){
                    stockChangeView.setUserData(newItem.getId());
                }
            }
        });

        stockChangeView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeStockChangeWin();
            }
        });
    }


    public void closeStockChangeWin(){

        long id =  (long)stockChangeView.getUserData();

        StockChange stockChange  = stockChangeService.selectByKey(id);

        loadDate(stockChange);

        coseWin();

    }





    /**
     * 根据库存异动编号查询库存异动产品
     * @param stockChangeId 库存异动编号
     *
     */
    public void loadStockChangeProduct(long stockChangeId){

        List<StockChangeProduct> stockChangeProducts = stockChangeProductService.findStockChangeProduct(stockChangeId);

        findchangesourse.setCellFactory(TextFieldTableCell.forTableColumn());
        findchangeorder.setCellFactory(TextFieldTableCell.forTableColumn());
        findchangesort.setCellFactory(TextFieldTableCell.forTableColumn());
        findproductorder.setCellFactory(TextFieldTableCell.forTableColumn());
        findproductname.setCellFactory(TextFieldTableCell.forTableColumn());
        findproductnum.setCellFactory(TextFieldTableCell.forTableColumn());
        findproductunit.setCellFactory(TextFieldTableCell.forTableColumn());
        finddepotorder.setCellFactory(TextFieldTableCell.forTableColumn());
        finddepotname.setCellFactory(TextFieldTableCell.forTableColumn());
        findproductremarks.setCellFactory(TextFieldTableCell.forTableColumn());

        findchangesourse.setCellValueFactory(new PropertyValueFactory("soursebill"));
        findchangeorder.setCellValueFactory(new PropertyValueFactory("sourseorder"));
        findchangesort.setCellValueFactory(new PropertyValueFactory("sort"));
        findproductorder.setCellValueFactory(new PropertyValueFactory("productorder"));
        findproductname.setCellValueFactory(new PropertyValueFactory("productname"));
        findproductnum.setCellValueFactory(new PropertyValueFactory("productnum"));
        findproductunit.setCellValueFactory(new PropertyValueFactory("unit"));
        finddepotorder.setCellValueFactory(new PropertyValueFactory("depotorder"));
        finddepotname.setCellValueFactory(new PropertyValueFactory("depotname"));
        findproductremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        stockChangeProductProperties.clear();

        if(stockChangeProducts.size()>0){

            for (StockChangeProduct stockChangeProduct :stockChangeProducts) {

                StockChangeProductProperty stockChangeProductProperty = new StockChangeProductProperty(lockOrder ? 0 : stockChangeProduct.getId(),stockChangeProduct.getSoursebill(),stockChangeProduct.getSourseorder(),stockChangeProduct.getSort(),stockChangeProduct.getProductorder(),stockChangeProduct.getProductname(),stockChangeProduct.getProductnum(),stockChangeProduct.getUnit(),stockChangeProduct.getDepotorder(),stockChangeProduct.getDepotname(),stockChangeProduct.getRemarks());

                stockChangeProductProperties.add(stockChangeProductProperty);
            }
        }
        stockChangeProductView.setItems(stockChangeProductProperties);



        stockChangeProductView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StockChangeProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends StockChangeProductProperty> observableValue, StockChangeProductProperty oldItem, StockChangeProductProperty newItem) {
                tableViewIndex = stockChangeProductView.getSelectionModel().getSelectedIndex();
                changeId=newItem.getId();
            }
        });


        lockOrder = false;

    }


    public void saveStockChangeProduct(long stockChangeProductId){
        for (StockChangeProductProperty stockChangeProductProperty :stockChangeProductProperties) {
            if(stockChangeProductProperty.getSourseorder()!=null){
                if(stockChangeProductProperty.getSoursebill() != null && "销售退货单".equals(stockChangeProductProperty.getSoursebill()) &&  stockChangeProductProperty.getSourseorder() != null  ){
                    // 修改销售退货单 入库数量
                    SaleReturnGoodsProduct pro = iSaleReturnGoodsProductService.getSaleReturnGoodsProductBySaleNum(stockChangeProductProperty.getSourseorder(),stockChangeProductProperty.getProductorder());
                    if(pro != null){
                        int num = Integer.valueOf(stockChangeProductProperty.getProductnum()) - pro.getInboundNum();
                        int rows = iSaleReturnGoodsProductService.updateSaleReturnGoodsProductInboundNum(stockChangeProductProperty.getSourseorder(),stockChangeProductProperty.getProductorder(),num+"");
                        if(!(rows>0)){
                            System.err.println("修改销售退货单，入库数量失败！");
                        }
                    }
                }
                if(stockChangeProductProperty.getId()>0){
                    //修改
                    StockChangeProduct stockChangeProduct = new StockChangeProduct(stockChangeProductProperty.getId(),stockChangeProductProperty.getSoursebill(),stockChangeProductProperty.getSourseorder(),stockChangeProductProperty.getSort(),stockChangeProductProperty.getProductorder(),stockChangeProductProperty.getProductname(),stockChangeProductProperty.getProductnum(),stockChangeProductProperty.getUnit(),stockChangeProductProperty.getDepotorder(),stockChangeProductProperty.getDepotname(),stockChangeProductProperty.getRemarks(),stockChangeProductId);
                    stockChangeProductService.updateNotNull(stockChangeProduct);
                }else{
                    //新增
                    StockChangeProduct stockChangeProduct = new StockChangeProduct(stockChangeProductProperty.getSoursebill(),stockChangeProductProperty.getSourseorder(),stockChangeProductProperty.getSort(),stockChangeProductProperty.getProductorder(),stockChangeProductProperty.getProductname(),stockChangeProductProperty.getProductnum(),stockChangeProductProperty.getUnit(),stockChangeProductProperty.getDepotorder(),stockChangeProductProperty.getDepotname(),stockChangeProductProperty.getRemarks(),stockChangeProductId);
                    stockChangeProductService.save(stockChangeProduct);
                }
            }
        }
    }

    public void blankStockChangeProduct(){
        StockChangeProductProperty stockChangeProductProperty = new StockChangeProductProperty();
        stockChangeProductProperties.add(stockChangeProductProperty);
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

        List<StockChange> stockChange = stockChangeService.findStockChange(pageNum,1);

        PageInfo<StockChange> pageInfo = new PageInfo<>(stockChange);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页





        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


       if(stockChange != null){
           stockChange.forEach(inquiryOrder ->loadDate(inquiryOrder));
       }

    if(stockChange.size()<0){
        loadStockChangeProduct(0);
        //权限管理
        if(!lockOrder)matchingPermissions("库存异动作业",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,0);
    }



    }




    /**
     * 向控件加载数据
     * @param stockChange
     */
    public void loadDate(StockChange stockChange){

        if(!lockOrder)changeorder.setUserData(stockChange.getId());

        changedate.setValue(LocalDateTime.ofInstant(stockChange.getChangedate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        if(!lockOrder)changeorder.setText(stockChange.getChangeorder());

        depotorder.setText(stockChange.getDepotorder());

        depotname.setText(stockChange.getDepotname());

        peoplename.setText(stockChange.getPeoplename());

        int changecny =  stockChange.getChangecurrency();

        setComboBox(7,changecurrency,--changecny);

        changetype.getSelectionModel().select(stockChange.getChangetype()-1);

        changesourse.setText(stockChange.getChangesourse());

        createpeople.setText(stockChange.getCreatepeople());

        shpeople.setText(stockChange.getShpeople());

        shdate.setText(stockChange.getShdate());

        updatepeople.setText(stockChange.getUpdatepeople());

        updatedate.setText(stockChange.getUpdatedate());

        remarks.setText(stockChange.getRemarks());

        int isSh = stockChange.getShstatus();

//        if(isSh == 0){
//            shyes.setDisable(false);
//            shno.setDisable(true);
//        }else{
//            shyes.setDisable(true);
//            shno.setDisable(false);
//        }

        loadStockChangeProduct(stockChange.getId());

        //权限管理
        if(!lockOrder)matchingPermissions("库存异动作业",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,isSh);

    }






    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){
                 changedate.setDisable(!status);
                  changeorder.setDisable(!status);
                depotorder.setDisable(!status);
                   depotname.setDisable(!status);
                changetype.setDisable(!status);
                 peopleorder.setDisable(!status);
                peoplename.setDisable(!status);
                 changecurrency.setDisable(!status);
                changesourse.setDisable(!status);
                  createpeople.setDisable(!status);
                shpeople.setDisable(!status);
                shdate.setDisable(!status);
                updatepeople.setDisable(!status);
                updatedate.setDisable(!status);
                 remarks.setDisable(!status);

                 //库存异动表格
        stockChangeProductView.setEditable(status);

    }



    /**
     * 清空
     */
    public void clearValue(){



        changedate.setValue(null);
        changeorder.setText(NumberUtil.NULLSTRING);
        depotorder.setText(NumberUtil.NULLSTRING);
        depotname.setText(NumberUtil.NULLSTRING);
        changetype.getSelectionModel().select(0);
        peopleorder.getSelectionModel().select(0);
        peoplename.setText(NumberUtil.NULLSTRING);
        changecurrency.getSelectionModel().select(0);
        changesourse.setText(NumberUtil.NULLSTRING);
        createpeople.setText(NumberUtil.NULLSTRING);
        shpeople.setText(NumberUtil.NULLSTRING);
        updatepeople.setText(NumberUtil.NULLSTRING);
        updatedate.setText(NumberUtil.NULLSTRING);
        remarks.setText(NumberUtil.NULLSTRING);

        stockChangeProductProperties.clear();
        SaleReturnGoods.clear();
        importProperties.clear();


    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) changeorder.getUserData();

            List<StockChangeProduct> stockChangeProducts = stockChangeProductService.findStockChangeProduct(id);

            for (StockChangeProduct stockChangeProduct:stockChangeProducts) {
                stockChangeProductService.delete(stockChangeProduct.getId());
            }

            relationService.deleteRelation("库存异动作业",id);

            int rows = stockChangeService.delete(id);
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
//        blankStockChangeProduct();
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期
        //联系人空白行


    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件
//        blankStockChangeProduct();
        submitvbox.setDisable(false);
        updatevbox.setDisable(true);
        deletevbox.setDisable(true);
        insertvbox.setDisable(true);
        setDatePicker(changedate);
        createPeople(createpeople);//制单人



    }


    /**
     * 提交
     */
    public  void submit(){

    int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

    LocalDate orderDate =changedate.getValue();

        if(orderDate == null){
            alert_informationDialog("请填写制单日期!");
             return;
          }

            StockChange stockChange = new StockChange(
                    new Date(java.sql.Date.valueOf(orderDate).getTime()),
                    changeorder.getText(),
                    depotorder.getText(),
                    depotname.getText(),
                    changetype.getSelectionModel().getSelectedIndex()+1,
                    peopleorder.getItems().size()==0?"":peopleorder.getSelectionModel().getSelectedItem().toString(),
                    peoplename.getText(),
                    changecurrency.getSelectionModel().getSelectedIndex()+1,
                    changesourse.getText(),
                    createpeople.getText(),
                    shpeople.getText(),
                    shdate.getText(),
                    updatepeople.getText(),
                    updatedate.getText(),
                    remarks.getText(),
                    0
            );



    int rows =0;
        if(submitStuts==1){
        //产生订单编号
        String orderNum = createIsnum(orderDate.toString());
        changeorder.setText(orderNum);
            stockChange.setChangeorder(orderNum);
        rows = stockChangeService.save(stockChange);
            if(relationLock) {
                //添加关联关系
                relation.setRelationName("库存异动作业");
                relation.setRelationId(stockChange.getId());
                relationService.save(relation);
            }
    }else if(submitStuts ==2){
            stockChange.setId((long)changeorder.getUserData());
        rows = stockChangeService.updateNotNull(stockChange);
    }

        saveStockChangeProduct(stockChange.getId());

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);



        this.loadDate(stockChange); //重新加载数据


        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

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
        changePurchangeStockByChangeType();
    }

    /**
     * 取消审核
     *
     * 需查看 单据是否被其他地方调用
     *
     */
    public void shButtonCancel(){
        shno.setDisable(true);
        shyes.setDisable(false);
        updateOrderStatus(0);
    }

    /**
     * 修改审核状态
     * @param status  0、未审核    1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)changeorder.getUserData();
        StockChange stockChange = stockChangeService.selectByKey(id);
        stockChange.setShstatus(status);

        if(status == 1){
            stockChange.setShpeople(getAdminName());
            stockChange.setShdate(getCurrentDate());

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            stockChange.setShpeople(NumberUtil.NULLSTRING);
            stockChange.setShdate(NumberUtil.NULLSTRING);

            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
        }

        stockChangeService.updateNotNull(stockChange);
    }


    /**
     * 导入 盘库作业
     */
    public  void importPurchaseStockOrder(){

        stage.setTitle("导入-盘库作业");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/purchase_stock_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreCheckStockImport(1);
    }



    public void loadMoreCheckStockImport(int pageNum){

        List<CheckStock> checkStocks = checkStockService.findCheckStock(pageNum,pageSize);


        ObservableList<CheckStock> list = FXCollections.observableArrayList();


        stockid.setCellValueFactory(new PropertyValueFactory("id"));
        findstockorder.setCellValueFactory(new PropertyValueFactory("checkorder"));
        findstockdata.setCellValueFactory(new PropertyValueFactory("flgTime"));
        findsuppliernum.setCellValueFactory(new PropertyValueFactory("depotname"));
        findstatus.setCellValueFactory(new PropertyValueFactory("shstatusStr"));
        /*findcomestock.setCellValueFactory(new PropertyValueFactory("purpeople"));*/


        for (CheckStock checkStock:checkStocks) {

            checkStock.setFlgTime(new SimpleDateFormat("yyyy-MM-dd").format(checkStock.getCheckdate()));

            if(checkStock.getShstatus()==0){
                checkStock.setShstatusStr("未审核");
            }else{

                checkStock.setShstatusStr("已审核");
            }

            list.add(checkStock);

        }

        purchaseStockView.setItems(list); //tableview添加list

        purchaseStockView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CheckStock>() {
            @Override
            public void changed(ObservableValue<? extends CheckStock> observableValue, CheckStock oldItem, CheckStock newItem) {
                if(newItem != null && newItem.getId()>0){
                    loadMoreCheckStockProductImport(newItem.getId());
                }
            }
        });
    }


    /**
     * 采购订单产品导入
     */
    private ObservableList<InquiryImportProperty> importProperties = FXCollections.observableArrayList();

    public void loadMoreCheckStockProductImport(long cheid){

        checkStockId = cheid;

        List<CheckStockProduct>  checkStockProducts = checkStockProductService.findCheckStockProduct(checkStockId);

//        List<PurchaseStockProduct> purchaseStockProducts = purchaseStockProductService.findStockProductBypurchaseStockId(checkStockId);
        stockProductView.setEditable(true);
        stockproid.setCellFactory(CheckBoxTableCell.forTableColumn(stockproid));

        stockproid.setCellValueFactory(new PropertyValueFactory("checked"));
        findproid.setCellValueFactory(new PropertyValueFactory("proisnum"));
        finprosort.setCellValueFactory(new PropertyValueFactory("sort"));
        findproname.setCellValueFactory(new PropertyValueFactory("proname"));
        finpronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        findproprice.setCellValueFactory(new PropertyValueFactory("proprice"));

        importProperties.clear();

        for (int i=0,len = checkStockProducts.size();i<len;i++) {


            int  finalNum  = 0;

            int fastNum =  checkStockProducts.get(i).getDepotnum();

            int lastNum = checkStockProducts.get(i).getNownum();

            if(fastNum > lastNum){
                //盘亏
                finalNum = fastNum - lastNum;
            }else if(lastNum > fastNum){
                //盘盈
                finalNum = lastNum - fastNum;
            }

            InquiryImportProperty inquiryImportProperty = new InquiryImportProperty(false,checkStockProducts.get(i).getId(),checkStockProducts.get(i).getProductorder(),i,checkStockProducts.get(i).getProductname(),finalNum,checkStockProducts.get(i).getProductprice());

            importProperties.add(inquiryImportProperty);

        }

        stockProductView.setItems(importProperties); //tableview添加list

    }



    public  void importPurchaseStockProductData(){

        CheckStock checkStock = checkStockService.selectByKey(checkStockId);

        //存储被关联单据
        relation.setBeRelationName("盘库作业");
        relation.setBeRelationId(checkStock.getId());
        relationLock = true;
        if(checkStock.getShstatus() == 1){

            //仓库编号
            depotorder.setText(checkStock.getDepotorder());
            //仓库名称
            depotname.setText(checkStock.getDepotname());
            //异动来源
            changesourse.setText("盘库作业");


            for( int i=0,len = importProperties.size(); i<len; i++){
                //选中导入的产品
                if(importProperties.get(i).isChecked()){

//                PurchaseStockProduct purchaseStockProduct = purchaseStockProductService.selectByKey(inquiryImportProperty.getId());

                    CheckStockProduct checkStockProduct = checkStockProductService.selectByKey(importProperties.get(i).getId());

                    int  finalNum  = 0;

                    int fastNum =  checkStockProduct.getDepotnum();

                    int lastNum = checkStockProduct.getNownum();

                    if(fastNum > lastNum){
                        //盘亏
                        setChangeType(changetype,6);
                        finalNum = fastNum - lastNum;
                    }else if(lastNum > fastNum){
                        //盘盈
                        setChangeType(changetype,0);
                        finalNum = lastNum - fastNum;
                    }

                    StockChangeProductProperty stockChangeProductProperty = new StockChangeProductProperty(0,"盘库作业",checkStock.getCheckorder(),i+"",checkStockProduct.getProductorder(),checkStockProduct.getProductname(),finalNum,checkStockProduct.getProductunit(),checkStockProduct.getDepotorder(),checkStockProduct.getDepotname(),checkStockProduct.getRemarks());

                    stockChangeProductProperties.add(stockChangeProductProperty);


                }
            }

        }else{
            alert_informationDialog(AppConst.ALERT_EXAMINE);
        }


        coseInquiryWin();
    }



    public void coseInquiryWin(){
        stage.close();
    }






    /**
     * 导入 销售退货单
     */
    public  void importSalesReturnsOrder(){
        stage.setTitle("导入-销售退货单");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/sales_returns_import.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadSalesReturnsImport(1);
    }



    public void loadSalesReturnsImport(int pageNum){

        List<SaleReturnGoods> saleReturnGoods = iSaleReturnGoodsService.getSaleReturnGoodsByPages(pageNum,pageSize);


        ObservableList<SaleReturnGoods> list = FXCollections.observableArrayList();


        salestockid.setCellValueFactory(new PropertyValueFactory("id"));
        findsalestockorder.setCellValueFactory(new PropertyValueFactory("pinbackNo"));
        findsalestockdata.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        findsalesuppliernum.setCellValueFactory(new PropertyValueFactory("warehouseIn"));
        findsalestatus.setCellValueFactory(new PropertyValueFactory("reasonStr"));
        /*findcomestock.setCellValueFactory(new PropertyValueFactory("purpeople"));*/


        for (SaleReturnGoods saleReturnGoods1:saleReturnGoods) {

            saleReturnGoods1.setCreateDateStr(DateToString(saleReturnGoods1.getCreateDate()));

            if(saleReturnGoods1.getOrderAudit()){
                saleReturnGoods1.setReasonStr("已审核");
            }else{
                saleReturnGoods1.setReasonStr("未审核");
            }

            list.add(saleReturnGoods1);

        }

        purchaseSaleStockView.setItems(list); //tableview添加list

        purchaseSaleStockView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleReturnGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleReturnGoods> observableValue, SaleReturnGoods oldItem, SaleReturnGoods newItem) {
                if(newItem != null && newItem.getId()>0){
                    loadMoreSaleReturnGoodsImport(newItem.getId());
                }
            }
        });
    }





    /**
     * 采购订单产品导入
     */
    private ObservableList<InquiryImportProperty> SaleReturnGoods = FXCollections.observableArrayList();

    public void loadMoreSaleReturnGoodsImport(long cheid){

        checkStockIds = cheid;

        List<SaleReturnGoodsProduct>  saleReturnGoodsProducts = iSaleReturnGoodsProductService.listReturnGoodsProduct(String.valueOf(checkStockIds));

//        List<PurchaseStockProduct> purchaseStockProducts = purchaseStockProductService.findStockProductBypurchaseStockId(checkStockId);
        saleReturnstockProductView.setEditable(true);
        saleReturnstockproid.setCellFactory(CheckBoxTableCell.forTableColumn(stockproid));

        saleReturnstockproid.setCellValueFactory(new PropertyValueFactory("checked"));
        saleReturnfindproid.setCellValueFactory(new PropertyValueFactory("proisnum"));
        saleReturnfinprosort.setCellValueFactory(new PropertyValueFactory("sort"));
        saleReturnfindproname.setCellValueFactory(new PropertyValueFactory("proname"));
        saleReturnfinpronum.setCellValueFactory(new PropertyValueFactory("pronum"));
        saleReturnfindproprice.setCellValueFactory(new PropertyValueFactory("proprice"));

        SaleReturnGoods.clear();

        for (int i=0,len = saleReturnGoodsProducts.size();i<len;i++) {


//            int  finalNum  = 0;
//
//            int fastNum =  saleReturnGoodsProducts.get(i).getDepotnum();
//
//            int lastNum = saleReturnGoodsProducts.get(i).getNownum();
//
//            if(fastNum > lastNum){
//                //盘亏
//                finalNum = fastNum - lastNum;
//            }else if(lastNum > fastNum){
//                //盘盈
//                finalNum = lastNum - fastNum;
//            }

            InquiryImportProperty inquiryImportProperty = new InquiryImportProperty(false,saleReturnGoodsProducts.get(i).getId(),saleReturnGoodsProducts.get(i).getProductNo(),i,saleReturnGoodsProducts.get(i).getProductName(),saleReturnGoodsProducts.get(i).getNum(),saleReturnGoodsProducts.get(i).getPrice().doubleValue());

            SaleReturnGoods.add(inquiryImportProperty);

        }

        saleReturnstockProductView.setItems(SaleReturnGoods); //tableview添加list

    }



    public  void importSaleReturnProductData(){

        SaleReturnGoods saleReturnGoods = iSaleReturnGoodsService.selectByKey(checkStockIds);

//        CheckStock checkStock = checkStockService.selectByKey(checkStockId);

        //存储被关联单据
        relation.setBeRelationName("销售退货单");
        relation.setBeRelationId(saleReturnGoods.getId());
        relationLock = true;
        if(saleReturnGoods.getOrderAudit()){

            DataSetting dataSetting = dataSettingService.findDataSettingByName(saleReturnGoods.getWarehouseIn());

            if(dataSetting != null){
                //仓库编号
                depotorder.setText(dataSetting.getId()+"");
                //仓库名称
                depotname.setText(dataSetting.getTitle());
            }
            //异动来源
            changesourse.setText("销售退货单");

            setChangeType(changetype,1);


            for( int i=0,len = SaleReturnGoods.size(); i<len; i++){
                //选中导入的产品
                if(SaleReturnGoods.get(i).isChecked()){

//                PurchaseStockProduct purchaseStockProduct = purchaseStockProductService.selectByKey(inquiryImportProperty.getId());


                    SaleReturnGoodsProduct saleReturnGoodsProduct = iSaleReturnGoodsProductService.selectByKey(SaleReturnGoods.get(i).getId());


                    if(saleReturnGoodsProduct != null){

                        DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(saleReturnGoodsProduct.getWarehousePosition());

                        String depotNo = "",depotName = "";

                        if(depotBasic != null ){
                            depotNo = depotBasic.getIsnum();
                            depotName = depotBasic.getDepname();
                        }


                        StockChangeProductProperty stockChangeProductProperty = new StockChangeProductProperty(0,"销售退货单",saleReturnGoods.getPinbackNo(),i+"",saleReturnGoodsProduct.getProductNo(),saleReturnGoodsProduct.getProductName(),saleReturnGoodsProduct.getNum(),saleReturnGoodsProduct.getUnit(),depotNo,depotName,saleReturnGoodsProduct.getRemark());

                        stockChangeProductProperties.add(stockChangeProductProperty);

                    }




                }
            }

        }else{
            alert_informationDialog(AppConst.ALERT_EXAMINE);
        }


        coseInquiryWin();
    }


    /**
     * 导入订单中 的本单据
     * 复制本单据为一个新订单
     */
    public void copeCurrentOrder(){

        insert();

        long stockChangeOrder =  (long)changeorder.getUserData();

        StockChange stockChange = stockChangeService.selectByKey(stockChangeOrder);

        lockOrder = true;

        loadDate(stockChange);
    }







    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("stock_change.fxml");

        if(index != -1) {
            relation = new Relation();
            relationLock = false;

            peopleorder.getItems().clear();

            List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

            for(int i=0,len=employeeBasics.size();i<len;i++){
                peopleorder.getItems().add(employeeBasics.get(i).getIdnum());
            }
            peopleorder.getSelectionModel().select(0);



            CheckStock checkStock = StageManager.checkStock;

             SaleReturnGoods saleReturnGoods = StageManager.saleReturnGoods;

            if(checkStock != null){

                //存储被关联单据
                relation.setBeRelationName("盘库作业");
                relation.setBeRelationId(checkStock.getId());
                relationLock = true;
                insert();

                loadStockChangeProduct(0);

                setComboBox(7,changecurrency,0);
                //仓库编号
                depotorder.setText(checkStock.getDepotorder());
                //仓库名称
                depotname.setText(checkStock.getDepotname());
                //异动来源
                changesourse.setText("盘库作业");

                 List<CheckStockProduct> checkStockProducts = StageManager.checkStockProducts;

                for (int i =0,len = checkStockProducts.size();i<len;i++) {

                    int  finalNum  = 0;

                    int fastNum =  checkStockProducts.get(i).getDepotnum();

                    int lastNum = checkStockProducts.get(i).getNownum();

                    if(fastNum > lastNum){
                        //盘亏
                        setChangeType(changetype,6);
                        finalNum = fastNum - lastNum;
                    }else if(lastNum > fastNum){
                        //盘盈
                        setChangeType(changetype,0);
                        finalNum = lastNum - fastNum;
                    }

                    StockChangeProductProperty stockChangeProductProperty = new StockChangeProductProperty(0,"盘库作业",checkStock.getCheckorder(),i+1+"",checkStockProducts.get(i).getProductorder(),checkStockProducts.get(i).getProductname(),finalNum,checkStockProducts.get(i).getProductunit(),checkStockProducts.get(i).getDepotorder(),checkStockProducts.get(i).getDepotname(),checkStockProducts.get(i).getRemarks());

                    stockChangeProductProperties.add(stockChangeProductProperty);
                }


            }else if(saleReturnGoods != null){

                //存储被关联单据
                relation.setBeRelationName("销售退货单");
                relation.setBeRelationId(saleReturnGoods.getId());
                relationLock = true;
                insert();

                loadStockChangeProduct(0);

                setComboBox(7,changecurrency,0);

                setChangeType(changetype,1);

                DataSetting dataSetting = dataSettingService.findDataSettingByName(saleReturnGoods.getWarehouseIn());

                if(dataSetting != null){

                    //仓库编号
                    depotorder.setText(dataSetting.getId()+"");
                    //仓库名称
                    depotname.setText(dataSetting.getTitle());
                }

                //异动来源
                changesourse.setText("销售退货单");


                List<SaleReturnGoodsProduct> saleReturnGoodsProduct =  iSaleReturnGoodsProductService.listReturnGoodsProduct(saleReturnGoods.getId().toString());


                for (int i =0,len = saleReturnGoodsProduct.size();i<len;i++) {


                    DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(saleReturnGoodsProduct.get(i).getWarehousePosition());

                    StockChangeProductProperty stockChangeProductProperty = new StockChangeProductProperty(0,"销售退货单",saleReturnGoods.getPinbackNo(),i+1+"",saleReturnGoodsProduct.get(i).getProductNo(),saleReturnGoodsProduct.get(i).getProductName(),saleReturnGoodsProduct.get(i).getNum(),saleReturnGoodsProduct.get(i).getUnit(),depotBasic != null ? depotBasic.getIsnum() : "",depotBasic != null ? depotBasic.getDepname() : "",saleReturnGoodsProduct.get(i).getRemark());

                    stockChangeProductProperties.add(stockChangeProductProperty);

                }

            }else{
                setChangeType(changetype);
                findInquiry(1);
            }

            BaseController.clickEvent(stockChangeProductView, data ->{
                tableViewIndex = stockChangeProductView.getSelectionModel().getSelectedIndex();
//                moreProductStockClick();
            }, 2);


            peopleorder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    if(!"".equals(newValue) && newValue != null ){


                    EmployeeBasic employeeBasic =  employeeBasicService.selectEmployeeBasicByIsnum(newValue.toString());


                        peoplename.setText(employeeBasic.getEmpname());

//                        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();
//
//
//                        for(int i=0,len=employeeBasics.size();i<len;i++){
//                            peopleorder.getItems().set(i,"("+employeeBasics.get(i).getIdnum()+")"+employeeBasics.get(i).getEmpname());
//                        }
//
//                        peopleorder.getSelectionModel().select(0);
//
//
//                        prveIndex = peopleorder.getSelectionModel().getSelectedIndex();
//
//                        String str = newValue.toString();
//
//                        String empName = str.substring(str.indexOf(")")+1,str.length());
//
//                        String empOrder =  str.substring(str.indexOf("(")+1,str.indexOf(")"));
//
//
//                        peopleorder.getItems().set(prveIndex,empOrder);

//                        peoplename.setText(empName);

                    }

                }
            });


        }

    }


    /**
     * tableView1 键盘按下触发
     * @param event
     */
    public void tableView1Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {

            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){

                if(changeId != 0){
                    stockChangeService.delete(changeId);
                }

                ObservableList<StockChangeProductProperty> stockChangeProductPropertiesNews = FXCollections.observableArrayList();


                for(int i=0,len = stockChangeProductProperties.size();i<len;i++  ){
                    if(i != tableViewIndex){
                        stockChangeProductPropertiesNews .add(stockChangeProductProperties.get(i));
                    }
                }

//                inquiryProductProperties.clear();
                stockChangeProductProperties.setAll(stockChangeProductPropertiesNews);

            }

        }


        if (event.getCode() == KeyCode.INSERT) {
            //联系人空白行
            /* blankContact();*/
            blankStockChangeProduct();
        }

        if(event.getCode() == KeyCode.F4){
//            moreProductStockClick();
        }
    }


    /**
     * 根据异动类型修改库存数量
     */
    public void changePurchangeStockByChangeType(){

        long changeOrder =  (long)changeorder.getUserData();

        StockChange stockChange = stockChangeService.selectByKey(changeOrder);

       if(stockChange != null){

           List<StockChangeProduct> stockChangeProducts = stockChangeProductService.findStockChangeProduct(stockChange.getId());

           if(stockChangeProducts != null && stockChangeProducts.size()>0){
               for (StockChangeProduct stockChangeProduct:stockChangeProducts) {
                   //根据库位编号和产品编号查询库存
                   ProductStock productStock = productStockService.findProductStockByDepotAndIsnum(stockChangeProduct.getDepotorder(),stockChangeProduct.getProductorder());
                   if(productStock != null){

                       int switchFlag = 0;

                       switch (stockChange.getChangetype()){
                           case 1:
                               //盘盈
                               switchFlag = 1;
                               break;
                           case 2:
                               //销退入库
                               switchFlag = 1;
                               break;
                           case 3:
                               //借入
                               switchFlag = 1;
                               break;
                           case 4:
                               //调整减少
                               switchFlag = 2;
                               break;
                           case 5:
                               //调整增多
                               switchFlag = 1;
                               break;
                           case 6:
                               //借出归还
                               switchFlag = 1;
                               break;
                           case 7:
                               //盘亏
                               switchFlag = 2;
                               break;
                           case 8:
                               //报废
                               switchFlag = 2;
                               break;
                           case 9:
                               //借出
                               switchFlag = 2;
                               break;
                           case 10:
                               //借入归还
                               switchFlag = 2;
                               break;
                           default:
                               break;
                       }

                       if(switchFlag == 1){
                           productStock.setUsablenum(productStock.getUsablenum() + stockChangeProduct.getProductnum());
                           productStock.setStocknum(productStock.getStocknum() + stockChangeProduct.getProductnum());
                       }else if(switchFlag == 2){
                           productStock.setUsablenum(productStock.getUsablenum() - stockChangeProduct.getProductnum());
                           productStock.setStocknum(productStock.getStocknum() - stockChangeProduct.getProductnum());
                       }
                       productStockService.updateNotNull(productStock);

                   }else{
                       //产品为空
                       alert_informationDialog("产品库存不存在!");
                   }
               }
           }
       }
    }












}
