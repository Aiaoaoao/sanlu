package com.yc.education.controller.stock;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.ProductStock;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.purchase.InquiryProductProperty;
import com.yc.education.model.stock.*;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.ProductStockService;
import com.yc.education.service.RelationService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.stock.CheckStockProductService;
import com.yc.education.service.stock.CheckStockService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.StageManager;
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
 * @ClassName CheckStockController
 * @Description TODO 盘库作业
 * @Author QuZhangJing
 * @Date 2018/11/9 10:07
 * @Version 1.0
 */
@Controller
public class CheckStockController extends BaseController implements Initializable {

    @Autowired
    private CheckStockService checkStockService; //盘库作业Service

    @Autowired
    private CheckStockProductService checkStockProductService; //盘库产品

    @Autowired
    private DepotBasicService depotBasicService; //库位 Service

    @Autowired
    private ProductStockService productStockService; //产品库存

    @Autowired
    private ProductBasicService productBasicService; //产品基本资料

    @Autowired
    private DataSettingService dataSettingService;
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

    @FXML private VBox printingvbox; //打印

    @FXML private VBox shyes; //审核

    @FXML private VBox shno; //取消审核


    @FXML private DatePicker checkdate; //盘点日期

    @FXML private TextField checkorder; //盘库单号

    @FXML private TextField depotorder;//盘点仓库编号

    @FXML private TextField depotname;//盘点仓库名称

    @FXML private TextField createpeople;//制单人

    @FXML private TextField shpeople;//审核人

    @FXML private TextField shdate;//审核时间

    @FXML private TextArea remarks;//备注



    //仓库库位
    @FXML private TableView depotView; //仓库库位
    @FXML private TableColumn depid; //  编号
    @FXML private TableColumn finddepotid; //  仓库编号
    @FXML private TableColumn finddepotnames; //  仓库名称




    //现有盘库作业查询
    @FXML private TableView stockChangeView; //库存异动
    @FXML private TableColumn findstockchangeid; //  编号
    @FXML private TableColumn findstockchangeorder; //  异动单号
    @FXML private TableColumn findstockchangedate; //  制单日期
    @FXML private TableColumn findstockchangedepotname; //  仓库名称
    @FXML private TableColumn findstockchangeremarks; //  备注

    //盘库作业
    @FXML private TableView checkStockProductView; //盘库作业
    @FXML private TableColumn findcheckproductorder; //  产品编号
    @FXML private TableColumn findcheckproductname; //  产品名称
    @FXML private TableColumn findcheckdepotorder; //  库位编号
    @FXML private TableColumn findcheckdepotname; //  库位名称
    @FXML private TableColumn findcheckdepotnum; //  库存数量
    @FXML private TableColumn findchecknownum; //  实盘数量
    @FXML private TableColumn findcheckproductunit; //  单位
    @FXML private TableColumn findcheckprofitandloss; //  盘盈/盘亏
    @FXML private TableColumn findcheckproductprice; //  单价
    @FXML private TableColumn findcheckremarks; //  备注



    private long changeId = 0;


    //盘库产品之TableView绑定
    private ObservableList<CheckStockProductProperty> checkStockProductProperties = FXCollections.observableArrayList();

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    private int pageSize = 10;

    private int tableViewIndex = 0;


    @FXML private VBox department_find_fast;
    @FXML private VBox department_find_prev;
    @FXML private VBox department_find_next;
    @FXML private VBox department_find_last;

    @FXML private TextField department_textField_pageSize;

    @FXML private TextField department_order_textField;


    @FXML private VBox checkStock_find_fast;
    @FXML private VBox checkStock_find_prev;
    @FXML private VBox checkStock_find_next;
    @FXML private VBox checkStock_find_last;

    @FXML private TextField checkStock_textField_pageSize;

    @FXML private TextField checkStock_order_textField;


    @FXML
    private TableView tableViewProduct;

    //产品编号
    @FXML
    private TableColumn findproductid;
    //产品名称
    @FXML
    private TableColumn findproductname;
    //库位编号
    @FXML
    private TableColumn finddepot;


    @FXML private VBox productStock_find_fast;
    @FXML private VBox productStock_find_prev;
    @FXML private VBox productStock_find_next;
    @FXML private VBox productStock_find_last;

    @FXML private TextField productStock_textField_pageSize;

    @FXML private TextField productStock_order_textField;


    /**
     * 生成询价订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @return
     */
    public String createIsnum(String currentDate){
        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
        String maxIsnum = checkStockService.selectMaxIdnum(currentDate);
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

    public void moreCheckStockDepotClick(){

        stage.setTitle("现有库位查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/check_stock_depot_find.fxml"));
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


        List<DataSetting> departmentBasics = dataSettingService.findDataSetting(33L,"".equals(department_order_textField.getText()) || department_order_textField.getText() == null ? "" : department_order_textField.getText().toString(),pageNum,pageSize);

        PageInfo<DataSetting> pageInfo = new PageInfo<>(departmentBasics);

        department_find_fast.setUserData(1); //首页

        department_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        department_find_next.setUserData(pageInfo.getNextPage());//下一页

        department_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<DataSetting> list = FXCollections.observableArrayList();


//        depid.setCellValueFactory(new PropertyValueFactory("id"));
        finddepotid.setCellValueFactory(new PropertyValueFactory("id"));
        finddepotnames.setCellValueFactory(new PropertyValueFactory("title"));

        for (DataSetting depotBasic:departmentBasics) {

            list.add(depotBasic);

        }

        depotView.setItems(list); //tableview添加list

        depotView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DataSetting>() {
            @Override
            public void changed(ObservableValue<? extends DataSetting> observableValue, DataSetting oldItem, DataSetting newItem) {
                if(newItem.getId()!= null && !("".equals(newItem.getId()))){
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

        depotorder.setText(dataSetting.getId()+""); //入库仓库编号


        depotname.setText(dataSetting.getTitle()); //入库仓库名称

//        loadProductStock(dataSetting.getId()+"");

        coseWin();
    }

    public void coseWin(){
        stage.close();
    }




    public void loadProductStock(String depot){



        checkStockProductProperties.clear();

        List<ProductStock> productStocks = productStockService.findProductStockByDepot(depot);

        DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(depot);

        for (ProductStock productStock:productStocks) {

            ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productStock.getProductorder());

            DataSetting dataSetting = dataSettingService.findDataSettingBySortAndParentId(productBasic.getBasicunit().intValue(),5);

            CheckStockProductProperty checkStockProductProperty = new CheckStockProductProperty(0,
                    productStock.getProductorder(),
                    productStock.getProductname(),
                    depotBasic.getIsnum(),
                    depotBasic.getDepname(),
                    productStock.getStocknum(),
                    0,
                    dataSetting.getTitle(),
                    0+"&",
                    productStock.getParprice(),
                    productBasic.getRemarks());

            checkStockProductProperties.add(checkStockProductProperty);

        }

        checkStockProductView.setItems(checkStockProductProperties);

    }



    public void findCheckStockSearch(){
        String pageSizes =  checkStock_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadStockChange(1);
        }
    }


    public void findCheckStockPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadStockChange(pageNum);
    }



    //盘库作业查询
    public void moreCheckStockClick(){

        stage.setTitle("盘库作业查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/check_stock_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize =  10;
        loadStockChange(1);
    }




    public void loadStockChange(int pageNum){

        List<CheckStock> checkStocks = checkStockService.findCheckStock("".equals(checkStock_order_textField.getText()) || checkStock_order_textField.getText() == null ? "" : checkStock_order_textField.getText().toString(),pageNum,pageSize);


        PageInfo<CheckStock> pageInfo = new PageInfo<>(checkStocks);

        checkStock_find_fast.setUserData(1); //首页

        checkStock_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        checkStock_find_next.setUserData(pageInfo.getNextPage());//下一页

        checkStock_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<CheckStock> list = FXCollections.observableArrayList();



//        findstockchangeid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findstockchangeorder.setCellValueFactory(new PropertyValueFactory("checkorder"));
        findstockchangedate.setCellValueFactory(new PropertyValueFactory("flgTime"));
        findstockchangedepotname.setCellValueFactory(new PropertyValueFactory("depotname"));
        findstockchangeremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        for (CheckStock checkStock:checkStocks) {

            checkStock.setFlgTime(new SimpleDateFormat("yyyy-MM-dd").format(checkStock.getCheckdate()));

            list.add(checkStock);

        }

        stockChangeView.setItems(list); //tableview添加list

        stockChangeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CheckStock>() {
            @Override
            public void changed(ObservableValue<? extends CheckStock> observableValue, CheckStock oldItem, CheckStock newItem) {
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

        CheckStock checkStock  = checkStockService.selectByKey(id);

        loadDate(checkStock);

        coseWin();

    }








    public void loadCheckStockProduct(long checkStockId){

        List<CheckStockProduct> checkStockProducts = checkStockProductService.findCheckStockProduct(checkStockId);


        findcheckproductorder.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckproductname.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckdepotorder.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckdepotname.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckdepotnum.setCellFactory(TextFieldTableCell.forTableColumn());
        findchecknownum.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckproductunit.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckprofitandloss.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckproductprice.setCellFactory(TextFieldTableCell.forTableColumn());
        findcheckremarks.setCellFactory(TextFieldTableCell.forTableColumn());

        findcheckproductorder.setCellValueFactory(new PropertyValueFactory("productorder"));
        findcheckproductname.setCellValueFactory(new PropertyValueFactory("productname"));
        findcheckdepotorder.setCellValueFactory(new PropertyValueFactory("depotorder"));
        findcheckdepotname.setCellValueFactory(new PropertyValueFactory("depotname"));
        findcheckdepotnum.setCellValueFactory(new PropertyValueFactory("depotnum"));
        findchecknownum.setCellValueFactory(new PropertyValueFactory("nownum"));
        findcheckproductunit.setCellValueFactory(new PropertyValueFactory("productunit"));
        findcheckprofitandloss.setCellValueFactory(new PropertyValueFactory("profitandloss"));
        findcheckproductprice.setCellValueFactory(new PropertyValueFactory("productprice"));
        findcheckremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        checkStockProductProperties.clear();

        if(checkStockProducts.size()>0){

            for (CheckStockProduct checkStockProduct :checkStockProducts) {
                CheckStockProductProperty checkStockProductProperty = new CheckStockProductProperty(checkStockProduct.getId(),checkStockProduct.getProductorder(),checkStockProduct.getProductname(),checkStockProduct.getDepotorder(),checkStockProduct.getDepotname(),checkStockProduct.getDepotnum(),checkStockProduct.getNownum(),checkStockProduct.getProductunit(),checkStockProduct.getProfitandloss(),checkStockProduct.getProductprice(),checkStockProduct.getRemarks());
                checkStockProductProperties.add(checkStockProductProperty);
            }
        }
        checkStockProductView.setItems(checkStockProductProperties);

//        findchecknownum.

        checkStockProductView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CheckStockProductProperty>() {
            @Override
            public void changed(ObservableValue<? extends CheckStockProductProperty> observableValue, CheckStockProductProperty oldItem, CheckStockProductProperty newItem) {
                tableViewIndex = checkStockProductView.getSelectionModel().getSelectedIndex();
                changeId=newItem.getId();
            }
        });


        findchecknownum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>(){
            @Override

            public void handle(TableColumn.CellEditEvent event) {


                String newValue =event.getNewValue().toString();
                int newNum = 0;
                if(!"".equals(newValue) && newValue != null)newNum = Integer.parseInt(newValue);

                for (int i=0,len = checkStockProductProperties.size();i<len;i++) {
                        if(tableViewIndex == i){

                                checkStockProductProperties.get(i).setNownum(newValue);

                                int depotNum = checkStockProductProperties.get(i).getDepotnum();
                                if(newNum > depotNum ){
                                    checkStockProductProperties.get(i).setProfitandloss("盘盈");
                                }else if(newNum < depotNum){
                                    checkStockProductProperties.get(i).setProfitandloss("盘亏");
                                }else{
                                    checkStockProductProperties.get(i).setProfitandloss("-");
                                }
                        }
                }



            }

        });


    }


    public void saveCheckStockProduct(long checkStockid){
        for (CheckStockProductProperty checkStockProductProperty :checkStockProductProperties) {
            if(checkStockProductProperty.getProductorder()!=null){
                if(checkStockProductProperty.getId()>0){
                    //修改
                    CheckStockProduct checkStockProduct = new CheckStockProduct(checkStockProductProperty.getId(),checkStockProductProperty.getProductorder(),checkStockProductProperty.getProductname(),checkStockProductProperty.getDepotorder(),checkStockProductProperty.getDepotname(),checkStockProductProperty.getDepotnum(),checkStockProductProperty.getNownum(),checkStockProductProperty.getProductunit(),checkStockProductProperty.getProfitandloss(),checkStockProductProperty.getProductprice(),checkStockProductProperty.getRemarks(),checkStockid);
                    checkStockProductService.updateNotNull(checkStockProduct);
                }else{
                    //新增
                    CheckStockProduct checkStockProduct = new CheckStockProduct(checkStockProductProperty.getProductorder(),checkStockProductProperty.getProductname(),checkStockProductProperty.getDepotorder(),checkStockProductProperty.getDepotname(),checkStockProductProperty.getDepotnum(),checkStockProductProperty.getNownum(),checkStockProductProperty.getProductunit(),checkStockProductProperty.getProfitandloss(),checkStockProductProperty.getProductprice(),checkStockProductProperty.getRemarks(),checkStockid);
                    checkStockProductService.save(checkStockProduct);
                }
            }
        }
    }

    public void blankCheckStockProduct(){
        CheckStockProductProperty checkStockProductProperty = new CheckStockProductProperty();
        checkStockProductProperties.add(checkStockProductProperty);
    }










    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findInquiry(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);//修改为修改状态
    }




    /**
     * 分页查询盘点订单
     */
    public void findInquiry(int pageNum){


        List<CheckStock> checkStocks = checkStockService.findCheckStock(pageNum,1);

        PageInfo<CheckStock> pageInfo = new PageInfo<>(checkStocks);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页





        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


        if(checkStocks != null){
            checkStocks.forEach(inquiryOrder ->loadDate(inquiryOrder));
        }

        if(checkStocks.size()<=0){
            loadCheckStockProduct(0);
            //权限管理
            matchingPermissions("盘库作业",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox);
        }





    }


    /**
     * 向控件加载数据
     * @param checkStock
     */
    public void loadDate(CheckStock checkStock){




        checkorder.setUserData(checkStock.getId());

        checkdate.setValue(LocalDateTime.ofInstant(checkStock.getCheckdate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        checkorder.setText(checkStock.getCheckorder());

        depotorder.setText(checkStock.getDepotorder());

        depotname.setText(checkStock.getDepotname());

        createpeople.setText(checkStock.getCreatepeople());

        shpeople.setText(checkStock.getShpeople());

        shdate.setText(checkStock.getShdate());

        remarks.setText(checkStock.getRemarks());

        int isSh = checkStock.getShstatus();

//        if(isSh == 0){
//            shyes.setDisable(false);
//            shno.setDisable(true);
//        }else{
//            shyes.setDisable(true);
//            shno.setDisable(false);
//        }

        loadCheckStockProduct(checkStock.getId());

        //权限管理
        matchingPermissions("盘库作业",insertvbox,deletevbox,updatevbox,shyes,shno,printingvbox,clearvbox,isSh);

    }






    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){
        checkdate.setDisable(!status); //盘点日期
        checkorder.setDisable(!status); //盘库单号
         depotorder.setDisable(!status);//盘点仓库编号
         depotname.setDisable(!status);//盘点仓库名称
         createpeople.setDisable(!status);//制单人
        shpeople.setDisable(!status);//审核人
        shdate.setDisable(!status);//审核时间
        remarks.setDisable(!status);//备注
        //库存异动表格
        checkStockProductView.setEditable(status);

    }



    /**
     * 清空
     */
    public void clearValue(){

        checkdate.setValue(null); //盘点日期
        checkorder.setText(NumberUtil.NULLSTRING); //盘库单号
        depotorder.setText(NumberUtil.NULLSTRING);//盘点仓库编号
        depotname.setText(NumberUtil.NULLSTRING);//盘点仓库名称
        createpeople.setText(NumberUtil.NULLSTRING);//制单人
        shpeople.setText(NumberUtil.NULLSTRING);//审核人
        shdate.setText(NumberUtil.NULLSTRING);//审核时间
        remarks.setText(NumberUtil.NULLSTRING);//备注
        checkStockProductProperties.clear();

    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) checkorder.getUserData();

            int rows = checkStockService.delete(id);
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
        blankCheckStockProduct();
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

        //联系人空白行


    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件
        blankCheckStockProduct();
        submitvbox.setDisable(false);
        updatevbox.setDisable(true);
        setDatePicker(checkdate);
        deletevbox.setDisable(true);
        insertvbox.setDisable(true);
        createPeople(createpeople);//制单人



    }


    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改

        LocalDate orderDate =checkdate.getValue();

        if(orderDate == null){
            alert_informationDialog("请填写制单日期!");
            return;
        }

    CheckStock checkStock = new CheckStock(new Date(java.sql.Date.valueOf(orderDate).getTime()),
            checkorder.getText(),
            depotorder.getText(),
            depotname.getText(),
            createpeople.getText(),
            shpeople.getText(),
            shdate.getText(),
            remarks.getText(),0);


        int rows =0;
        if(submitStuts==1){
            //产生订单编号
            String orderNum = createIsnum(orderDate.toString());
            checkorder.setText(orderNum);
            checkStock.setCheckorder(orderNum);
            rows = checkStockService.save(checkStock);
        }else if(submitStuts ==2){
            checkStock.setId((long)checkorder.getUserData());
            rows = checkStockService.updateNotNull(checkStock);
        }

        saveCheckStockProduct(checkStock.getId());

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);



        this.loadDate(checkStock); //重新加载数据


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
    }

    /**
     * 取消审核
     *
     * 需查看 单据是否被其他地方调用
     *
     */
    public void shButtonCancel(){


        long id =  (long)checkorder.getUserData();

        if(!relationService.isRelation("盘库作业",id)){
            shno.setDisable(true);
            shyes.setDisable(false);
            updateOrderStatus(0);
        }else {
            alert_informationDialog(AppConst.ALERT_OCCUPY);
        }

    }

    /**
     * 修改审核状态
     * @param status  0、未审核    1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)checkorder.getUserData();
        CheckStock checkStock = checkStockService.selectByKey(id);
        checkStock.setShstatus(status);

        if(status == 1){
            checkStock.setShpeople(getAdminName());
            checkStock.setShdate(getCurrentDate());

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            checkStock.setShpeople(NumberUtil.NULLSTRING);
            checkStock.setShdate(NumberUtil.NULLSTRING);

            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
        }

        checkStockService.updateNotNull(checkStock);
    }


    /**
     * 导出库存异动作业
     */
    public void exportStockChange(){

        CheckStock checkStock = checkStockService.selectByKey((long)checkorder.getUserData());

        if(checkStock != null){


           if(checkStock.getShstatus() == 1){


               changeHomeBtn(3,0,3);

               StageManager.checkStock = checkStock;

               StageManager.checkStockProducts = checkStockProductService.findCheckStockProduct(checkStock.getId());

               Pane pane1 = StageManager.ORDERCONTENT.get("stockPane");

               pane1.getChildren().setAll(loader.load("/fxml/stock/stock_change.fxml"));

           }else{
               alert_informationDialog(AppConst.ALERT_EXAMINE);
           }

        }else{
            alert_informationDialog("暂未单据导出!");
        }



    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("inventory_operation.fxml");

        if(index != -1) {

            findInquiry(1);


            BaseController.clickEvent(checkStockProductView, data ->{
                tableViewIndex = checkStockProductView.getSelectionModel().getSelectedIndex();
                moreProductStockClick();
            }, 2);
        }

    }





    public void findProductStockSearch(){

        String pageSizes =  productStock_textField_pageSize.getText();

        if("".equals(pageSizes) || pageSizes == null  ){
            alert_informationDialog("请输入页码数!");
        }else{
            pageSize = Integer.parseInt(pageSizes);
            loadProductStockInfo(1);
        }

    }


    public void findProductStockPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadProductStockInfo(pageNum);
    }



    public void moreProductStockClick(){

        stage.setTitle("现有库存产品查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/stock/checkstock_product_stock_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10 ;
        loadProductStockInfo(1);
    }



    public void loadProductStockInfo(int pageNum){

        List<ProductStock> productStocks = productStockService.findProductStockByDeoptParent("".equals(depotorder.getText()) || depotorder.getText() == null ? 0L : Long.parseLong(depotorder.getText().toString()) ,pageNum,pageSize);


        PageInfo<ProductStock> pageInfo = new PageInfo<>(productStocks);

        productStock_find_fast.setUserData(1); //首页

        productStock_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        productStock_find_next.setUserData(pageInfo.getNextPage());//下一页

        productStock_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<ProductStock> list = FXCollections.observableArrayList();



//        findstockchangeid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findproductid.setCellValueFactory(new PropertyValueFactory("productorder"));
        findproductname.setCellValueFactory(new PropertyValueFactory("productname"));
        finddepot.setCellValueFactory(new PropertyValueFactory("depot"));

        if(productStocks != null && productStocks.size()>0){
            for (ProductStock productStock:productStocks) {

                list.add(productStock);

            }
        }

        tableViewProduct.setItems(list); //tableview添加list

        tableViewProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductStock>() {
            @Override
            public void changed(ObservableValue<? extends ProductStock> observableValue, ProductStock oldItem, ProductStock newItem) {
                if(newItem.getId()!= null && !("".equals(newItem.getId()))){
                    tableViewProduct.setUserData(newItem.getId());
                }else{
                    tableViewProduct.setUserData(0L);
                }
            }
        });

        tableViewProduct.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeProductStockWin();
            }
        });
    }


    public void closeProductStockWin(){

        long id =  (long)tableViewProduct.getUserData();

        ProductStock productStock = productStockService.selectByKey(id);

        DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(productStock.getDepot());

        ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(productStock.getProductorder());
//        productBasic.getBasicunit()
        DataSetting dataSetting =  dataSettingService.findDataSettingBySortAndParentId(productBasic.getBasicunit().intValue(),5);

        CheckStockProductProperty checkStockProductProperty = new CheckStockProductProperty(0L,productStock.getProductorder(),productStock.getProductname(),depotBasic.getIsnum(),depotBasic.getDepname(),productStock.getStocknum(),productStock.getStocknum(),dataSetting.getTitle(),"-",productStock.getParprice(),"");

        for(int i=0,len = checkStockProductProperties.size();i<len;i++){

            if(i ==  tableViewIndex){
                try {
                    checkStockProductProperties.set(i,checkStockProductProperty);
                } catch (Exception e) {

                }
            }

        }


        coseWin();

    }



    /**
     * tableView1 键盘按下触发
     * @param event
     */
    public void tableView1Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {

            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){

                if(changeId != 0){
                    checkStockService.delete(changeId);
                }

                ObservableList<CheckStockProductProperty> checkStockProductPropertiesNews = FXCollections.observableArrayList();


                for(int i=0,len = checkStockProductProperties.size();i<len;i++  ){
                    if(i != tableViewIndex){
                        checkStockProductPropertiesNews .add(checkStockProductProperties.get(i));
                    }
                }

//                inquiryProductProperties.clear();
                checkStockProductProperties.setAll(checkStockProductPropertiesNews);

            }

        }


        if (event.getCode() == KeyCode.INSERT) {
            //联系人空白行
            /* blankContact();*/
            blankCheckStockProduct();
        }

        if(event.getCode() == KeyCode.F4){
            moreProductStockClick();
        }
    }


}
