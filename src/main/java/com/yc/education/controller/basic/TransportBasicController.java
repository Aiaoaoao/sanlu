package com.yc.education.controller.basic;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.customer.TableViewSample;
import com.yc.education.model.basic.*;
import com.yc.education.model.purchase.InquiryOrder;
import com.yc.education.model.purchase.InquiryProduct;
import com.yc.education.model.purchase.PurchaseOrders;
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.model.stock.PurchaseStockProduct;
import com.yc.education.service.basic.*;
import com.yc.education.service.purchase.*;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.service.stock.PurchaseStockService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import com.yc.education.util.PathUtil;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
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
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName TransportBasicController
 * @Description TODO  运输商基本资料
 * @Author QuZhangJing
 * @Date 2018/12/27 9:59
 * @Version 1.0
 */
@Controller
public class TransportBasicController extends BaseController implements Initializable {

    @Autowired
    private TransportBasicService transportBasicService;
    @Autowired
    private TransportContactService transportContactService;
    @Autowired
    private TransportPurchaseService transportPurchaseService;
    @Autowired
    private EmployeeBasicService employeeBasicService;//员工Service
    @Autowired
    private InquiryOrderService inquiryOrderService; //询价单
    @Autowired
    private PurchaseOrdersService purchaseOrdersService;//采购订单
    @Autowired
    private ForceOrderService forceOrderService;//强制结案
    @Autowired
    private PurchaseStockService purchaseStockService; //采购入库
    @Autowired
    private InquiryProductService inquiryProductService;
    @Autowired
    private PurchaseProductService purchaseProductService;
    @Autowired
    private PurchaseStockProductService purchaseStockProductService;




    /**
     *  联系人TabelView绑定数据
     */
    private ObservableList<SupplierContactProperty> supplierContactProperties = FXCollections.observableArrayList();
    /**
     *  采购负责人TabelView绑定数据
     */
    private ObservableList<SupplierPurchaserProperty> supplierPurchaserProperties = FXCollections.observableArrayList();


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



    @FXML private TextField isNum;//编号
    @FXML private TextField supdes; //供应商简称
    @FXML private TextField supname; //供应商名称
    @FXML private TextField regadd; //注册地址


    @FXML private ComboBox country; //国家
    @FXML private TextField area; //地区
    @FXML private TextField postalcode; //邮政编码
    @FXML private ComboBox phonetype; //电话类型
    @FXML private TextField phone; //电话
    @FXML private ComboBox faxtype; //传真类型
    @FXML private TextField fax; //传真
    @FXML private TextField remarks; //备注
    @FXML private TextField addpeople; //建档人
    @FXML private TextField adddate; //建档日期
    @FXML private TextField updatepeople; //最后修改人
    @FXML private TextField updatedate; //最后修改日期
    @FXML private CheckBox isstop; //是否暂停使用 默认0 1、暂停使用
    @FXML private TextField stopdes; //暂停说明



    @FXML private TableView tableView1; //联系人
    @FXML private TableColumn contactId;  //编号
    @FXML private TableColumn mainContact;  //主要联系人
    @FXML private TableColumn username; //姓名
    @FXML private TableColumn department; //部门
    @FXML private TableColumn position; //职务
    @FXML private TableColumn phones; //电话
    @FXML private TableColumn faxs; //传真
    @FXML private TableColumn iphone; //移动电话
    @FXML private TableColumn email; //E-mail
    @FXML private TableColumn remarkss; //备注




    @FXML private TableView tableView2; //采购负责人
    @FXML private TableColumn staffid; //员工编码
    @FXML private TableColumn staffcode; //员工编码
    @FXML private TableColumn staffname; //姓名
    @FXML private TableColumn keycontent; //主要负责人
    @FXML private TableColumn staffremarks; //备注


    @FXML private Button moreSupplierButton; //点击弹出 查询供应商


    @FXML private TableView tableView3; //供应商查询
    @FXML private TableColumn supid; //id
    @FXML private TableColumn findsupplierid; //供应商编号
    @FXML private TableColumn findsuppliername; //供应商简称

    @FXML private TableView tableViewEmployee; //员工TableView
    @FXML private TableColumn empid; //id
    @FXML private TableColumn findemployeeid; //员工编号
    @FXML private TableColumn findemployeename; //员工姓名

    private long contactNum=0;

    private long purchaserNum=0;

    //电话下拉
    private Map<String,String> phoneArray = new HashMap<>();
    //传真下拉
    private Map<String,String> faxArray = new HashMap<>();

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    @FXML
    private TableView tableTransactionBill;
    @FXML
    private TableColumn billcreatedata; //制单日期
    @FXML
    private TableColumn billtype; //单据类型
    @FXML
    private TableColumn billnum; //单号

    @FXML
    private TableColumn seeorder; //参考单号
    @FXML
    private TableColumn billemp; //业务负责
    @FXML
    private TableColumn totalprice; //金额总计

    @FXML
    private DatePicker billStartDate;
    @FXML
    private DatePicker billEndDate;

    @FXML
    private CheckBox xjChecked;
    @FXML
    private CheckBox cgChecked;
    @FXML
    private CheckBox cgrkChecked;


    ObservableList<SupplierBillInfo> supplierBillInfos = FXCollections.observableArrayList();

    @FXML private VBox transport_find_fast;
    @FXML private VBox transport_find_prev;
    @FXML private VBox transport_find_next;
    @FXML private VBox transport_find_last;

    @FXML private TextField transport_pageSize;

    private int transportPageSize = 10;

    private int tableViewIndex = 0;


    @FXML private VBox employee_find_fast;
    @FXML private VBox employee_find_prev;
    @FXML private VBox employee_find_next;
    @FXML private VBox employee_find_last;

    @FXML private TextField employee_pagesize;

    @FXML private CheckBox transportCheckBox;

    @FXML private TextField order_textField;

    /**
     * 生成供应商基本资料编号
     * @return
     */
    public String createIsnum(){

        String maxIsnum = transportBasicService.selectMaxIdnum();

        if(maxIsnum != null){

            String maxAlphabet = maxIsnum.substring(0,1);

            int currenNumber = Integer.parseInt(maxIsnum.substring(1,maxIsnum.length()));

            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){

                if(currenNumber == NumberUtil.MAXNUMBER){
                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
                        return NumberUtil.ALPHABET[i+1]+"001";
                    }
                }
            }

            if(currenNumber>0 && currenNumber < 9){
                return maxAlphabet +"00"+(currenNumber+1);
            }else if(currenNumber >= 9 && currenNumber< 99){
                return maxAlphabet +"0"+(currenNumber+1);
            }else{
                return maxAlphabet +(currenNumber+1);
            }
        }
        return "A001";
    }



    public void findTransportSearch(){
        String pageSizes =  transport_pageSize.getText();

        if(!"".equals(pageSizes) || pageSizes != null  ){
            transportPageSize = Integer.parseInt(pageSizes);
            loadMoreSupplier(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }




    }

    public void transportPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreSupplier(pageNum);
    }


    /**
     * 点击弹出 查询供应商
     */
    public void moreSupplierButtonClick(){

        stage.setTitle("现有运输商查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/transport_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        transportPageSize = 10;
        loadMoreSupplier(1);
    }


    /**
     * 现有供应商查询
     */
    public void loadMoreSupplier(int pageNum){

        List<TransportBasic> transportBasics = transportBasicService.selectTransportBasicNotSotp(transportCheckBox.isSelected() ? 1 : 0 ,pageNum,transportPageSize);

        PageInfo<TransportBasic> pageInfo = new PageInfo<>(transportBasics);

        transport_find_fast.setUserData(1); //首页

        transport_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        transport_find_next.setUserData(pageInfo.getNextPage());//下一页

        transport_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<TransportBasic> list =FXCollections.observableArrayList();


        tableView3.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        supid.setCellValueFactory(new PropertyValueFactory("id"));
        findsupplierid.setCellValueFactory(new PropertyValueFactory("idnum"));
        findsuppliername.setCellValueFactory(new PropertyValueFactory("supdes"));

        for (TransportBasic transportBasic:transportBasics) {

            if(transportBasic.getIsstop() == 1){
                transportBasic.setSupdes(transportBasic.getSupdes()+"(停用)");
            }

            list.add(transportBasic);

        }

        tableView3.setItems(list); //tableview添加list

        tableView3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransportBasic>() {
            @Override
            public void changed(ObservableValue<? extends TransportBasic> observableValue, TransportBasic oldItem, TransportBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    isNum.setUserData(newItem.getId());
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
        long id =(long)isNum.getUserData();
        TransportBasic transportBasic =  transportBasicService.selectByKey(id);
        loadDate(transportBasic);
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
        findSupplier(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);//修改为修改状态
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findSupplier(int pageNum){

        List<TransportBasic> transportBasics = transportBasicService.selectTransportBasic(pageNum,1);

        PageInfo<TransportBasic> pageInfo = new PageInfo<>(transportBasics);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        transportBasics.forEach(supplierBasic ->loadDate(supplierBasic));


        if(transportBasics == null  || transportBasics.size() == 0){

            isNum.setUserData(0L);

            contact();//加载联系人

            purchaser();//加载采购负责人
        }

    }


    /**
     * 向控件加载数据
     * @param supplierBasic
     */
    public void loadDate(TransportBasic supplierBasic){


        isNum.setUserData(supplierBasic.getId()); //供应商自增id 用户 删 改

        isNum.setText(supplierBasic.getIdnum());


        if(supplierBasic.getIsstop() == 1){
            supdes.setText(supplierBasic.getSupdes()+"(停用)");
        }else{
            supdes.setText(supplierBasic.getSupdes());
        }


        supname.setText(supplierBasic.getSupname());


        regadd.setText(supplierBasic.getRegadd());


        int  cout = supplierBasic.getCountry(); //国家

        country.getSelectionModel().select(--cout);


        area.setText(supplierBasic.getArea());


        postalcode.setText(supplierBasic.getPostalcode());


        int phty =supplierBasic.getPhonetype();

        phonetype.getSelectionModel().select(--phty);

        //将供应商电话放入map中存放
        phoneArray =  stringAssembleMap(supplierBasic.getPhone());

        phone.setText(phoneArray.get("电话1"));


        int faxint = supplierBasic.getFaxtype();

        faxtype.getSelectionModel().select(--faxint);

        faxArray =  stringAssembleMap(supplierBasic.getFax());

        fax.setText(faxArray.get("传真1"));


        remarks.setText(supplierBasic.getRemarks());


        addpeople.setText(supplierBasic.getAddpeople());


        /*
         * Date 类型转换 LocalDate
         * Instant instant = date.toInstant();
         *  ZoneId zone = ZoneId.systemDefault();
         *  LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
         *  LocalDate localDate = localDateTime.toLocalDate();
         */

        adddate.setText(supplierBasic.getAdddate());

        updatepeople.setText(supplierBasic.getUpdatepeople());

        updatedate.setText(supplierBasic.getUpdatedate());

        if(supplierBasic.getIsstop()==1){
            isstop.setSelected(true);
        }else{
            isstop.setSelected(false);
        }


        stopdes.setText(supplierBasic.getStopdes());

        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

        contact();//加载联系人

        purchaser();//加载采购负责人

    }



    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){
        supdes.setDisable(!status);
        supname.setDisable(!status);
        regadd.setDisable(!status);
        country.setDisable(!status);
        area.setDisable(!status);
        postalcode.setDisable(!status);
        phonetype.setDisable(!status);
        phone.setDisable(!status);
        faxtype.setDisable(!status);
        fax.setDisable(!status);
        remarks.setDisable(!status);
        addpeople.setDisable(!status);
        adddate.setDisable(!status);
        updatepeople.setDisable(!status);
        updatedate.setDisable(!status);
        isstop.setDisable(!status);
        stopdes.setDisable(!status);

        tableView1.setDisable(!status);
        tableView2.setDisable(!status);
        tableView1.setEditable(status);
        tableView2.setEditable(status);

    }



    /**
     * 清空
     */
    public void clearValue(){


        isNum.setText(NumberUtil.NULLSTRING);

        supdes.setText(NumberUtil.NULLSTRING);

        supname.setText(NumberUtil.NULLSTRING);

        regadd.setText(NumberUtil.NULLSTRING);

        country.getSelectionModel().select(0);

        area.setText(NumberUtil.NULLSTRING);

        postalcode.setText(NumberUtil.NULLSTRING);

        phonetype.getSelectionModel().select(0);

        phone.setText(NumberUtil.NULLSTRING);

        faxtype.getSelectionModel().select(0);

        fax.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        addpeople.setText(NumberUtil.NULLSTRING);

        LocalDateTime localDateTime = LocalDateTime.now();

        adddate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);

        isstop.setSelected(false);

        stopdes.setText(NumberUtil.NULLSTRING);

        supplierContactProperties.clear();

        supplierPurchaserProperties.clear();

    }

    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) isNum.getUserData();
            int rows = transportBasicService.delete(id);
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
        this.changeEditable(true);


        lastUpdatePeopel(updatepeople,updatedate); //最后修改人 和最后修改日期

        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);

        //联系人空白行
        blankContact();
        blankPurchaser();


    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件

        createPeople(addpeople,adddate);//建档人、建档日期

        submitvbox.setDisable(false);
        updatevbox.setDisable(true);
        deletevbox.setDisable(true);
        insertvbox.setDisable(true);

        phoneArray =  new HashMap<>();
        faxArray =  new HashMap<>();

        //联系人空白行
        blankContact();
        blankPurchaser();

    }


    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改


        /*    SupplierBasic supplierBasic = new SupplierBasic();*/

        String idnumss=isNum.getText();
        if(submitStuts==2){
            idnumss=isNum.getText();
        }

        int istopval=0;
        if(isstop.isSelected()){
            istopval=1;
        }else{
            istopval=0;
        }

        if(supdes.getText()=="" ||"".equals(supdes.getText()) ){
            alert_informationDialog("请填写供应商简称!");
            return;
        }

        if(supname.getText()=="" ||"".equals(supname.getText())){
            alert_informationDialog("请填写供应商名称!");
            return;
        }
        if(regadd.getText()=="" ||"".equals(regadd.getText()) ){
            alert_informationDialog("请填写注册地址!");
            return;
        }


        String newsupdes = supdes.getText();

        if(newsupdes.indexOf("(停用)") != -1){
            newsupdes = newsupdes.substring(0,newsupdes.indexOf("("));
        }

        TransportBasic transportBasic = new TransportBasic(
                0L,
                idnumss,
                newsupdes,
                supname.getText(),
                regadd.getText(),
                country.getSelectionModel().getSelectedIndex()+1,
                area.getText(),
                postalcode.getText(),
                phonetype.getSelectionModel().getSelectedIndex()+1,
                mapAssembleString(phoneArray),
                faxtype.getSelectionModel().getSelectedIndex()+1,
                mapAssembleString(faxArray),
                remarks.getText(),
                addpeople.getText(),
                adddate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
                istopval,
                stopdes.getText(),
                0
        );

        int rows =0;
        if(submitStuts==1){
            String isNums = this.createIsnum();
            isNum.setText(isNums);
            transportBasic.setIdnum(isNums);
            rows = transportBasicService.save(transportBasic);
        }else if(submitStuts ==2){
            transportBasic.setId((long)isNum.getUserData());
            rows = transportBasicService.updateNotNull(transportBasic);
        }
        transportBasic.setIdnum(isNum.getText());

        saveContact(transportBasic.getId());//联系人
        savePurchaser(transportBasic.getId()); //采购负责人

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);

        this.loadDate(transportBasic); //重新加载数据

    }







    /**
     * tableView1 键盘按下触发
     * @param event
     */
    public void tableView1Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {
            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
                transportContactService.delete(contactNum);


                ObservableList<SupplierContactProperty> supplierContactPropertiesNew = FXCollections.observableArrayList();

                for (SupplierContactProperty supplierContactProperty : supplierContactProperties) {
                    if (supplierContactProperty.getId() != contactNum) {
                        supplierContactPropertiesNew.add(supplierContactProperty);
                    }
                }

                supplierContactProperties.clear();
                supplierContactProperties.setAll(supplierContactPropertiesNew);
            }
            /*loadDate(supplierBasic);*/

        }

        if (event.getCode() == KeyCode.INSERT) {

            //联系人空白行
            blankContact();

        }




    }



    /**
     * tableView2 键盘按下触发
     * @param event
     */
    public void tableView2Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {
            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
                transportPurchaseService.delete(purchaserNum);

                long supid = (long) isNum.getUserData();


                ObservableList<SupplierPurchaserProperty> supplierPurchaserPropertiesNew = FXCollections.observableArrayList();

                for (SupplierPurchaserProperty supplierPurchaserProperty : supplierPurchaserProperties) {
                    if (supplierPurchaserProperty.getId() != purchaserNum) {
                        supplierPurchaserPropertiesNew.add(supplierPurchaserProperty);
                    }
                }
                supplierPurchaserProperties.clear();
                supplierPurchaserProperties.setAll(supplierPurchaserPropertiesNew);
            }
        }
        if (event.getCode() == KeyCode.INSERT) {
            //联系人空白行
            blankPurchaser();
        }

        if(event.getCode() == KeyCode.F4){
            moreSupplierEmployeeButtonClick();
        }

    }


    /**
     * 回车查询
     * @param event
     */
    public void isNumKey(KeyEvent event){


        if(event.getCode()==KeyCode.ENTER){

            String value = isNum.getText();

            if(!"".equals(value)){

                TransportBasic transportBasic = transportBasicService.selectTransportBasicByIsnum(value);

                if(transportBasic != null){
                    this.loadDate(transportBasic);
                }

            }

        }

    }

    public void findEmployeeSearch(){

        String pageSizes =employee_pagesize.getText();


        if(!"".equals(pageSizes) || pageSizes != null  ){
            transportPageSize = Integer.parseInt(pageSizes);
            loadMoreSupplierEmployee(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }


    }


    public void employeePages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreSupplierEmployee(pageNum);

    }

    public void moreSupplierEmployeeButtonClick(){

        stage.setTitle("现有员工查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/transport_employee_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        transportPageSize = 10;
        loadMoreSupplierEmployee(1);
    }


    /**
     * 现有员工查询
     */
    public void loadMoreSupplierEmployee(int pageNum){

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasicByIdnum("".equals(order_textField.getText()) || order_textField.getText() == null ? "" :order_textField.getText(),pageNum,transportPageSize);

        PageInfo<EmployeeBasic> pageInfo = new PageInfo<>(employeeBasics);

        employee_find_fast.setUserData(1); //首页

        employee_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        employee_find_next.setUserData(pageInfo.getNextPage());//下一页

        employee_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<EmployeeBasic> list =FXCollections.observableArrayList();


        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/
//        empid.setCellValueFactory(new PropertyValueFactory("id"));
        findemployeeid.setCellValueFactory(new PropertyValueFactory("idnum"));
        findemployeename.setCellValueFactory(new PropertyValueFactory("empname"));

        for (EmployeeBasic employeeBasic:employeeBasics) {

            list.add(employeeBasic);

        }

        tableViewEmployee.setItems(list); //tableview添加list

        tableViewEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmployeeBasic>() {
            @Override
            public void changed(ObservableValue<? extends EmployeeBasic> observableValue, EmployeeBasic oldItem, EmployeeBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    tableViewEmployee.setUserData(newItem.getId());
                }
            }
        });


        tableViewEmployee.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closePurchaseWin();
            }
        });


    }

    public void closePurchaseWin(){
        long id =(long)tableViewEmployee.getUserData();
        EmployeeBasic employeeBasic =  employeeBasicService.selectByKey(id);

        for(int i=0,len=supplierPurchaserProperties.size();i<len;i++){

            if(i==tableViewIndex){
                SupplierPurchaserProperty supplierPurchaserProperty =supplierPurchaserProperties.get(i);
                supplierPurchaserProperty.setStaffcode(employeeBasic.getIdnum());
                supplierPurchaserProperty.setStaffname(employeeBasic.getEmpname());
                supplierPurchaserProperty.setStaffremarks(employeeBasic.getRemarks());
            }
        }

        coseWin();
    }


    public static enum Participation {
        设为默认;
    }

    /**
     * 选项卡---采购负责人 TableView
     *
     * TableColumn staffcode; //员工编码
     * TableColumn staffname; //姓名
     * TableColumn keycontent; //主要负责人
     * TableColumn staffremarks; //备注
     */
    public void purchaser(){


        /*     ObservableList<SupplierPurchaser> list = tableView2.getItems();*/


        List<TransportPurchaser> transportPurchasers =transportPurchaseService.selectTransportPurchaseBySupplierid((long)isNum.getUserData());



        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

        keycontent.setCellFactory((param) -> new TableViewSample.RadioButtonCell<SupplierPurchaserProperty, SupplierBasicController.Participation>(EnumSet.allOf(SupplierBasicController.Participation.class)));
        staffcode.setCellFactory(TextFieldTableCell.forTableColumn());
        staffname.setCellFactory(TextFieldTableCell.forTableColumn());
        staffremarks.setCellFactory(TextFieldTableCell.forTableColumn());


//        staffid.setCellValueFactory(new PropertyValueFactory("id"));
        staffcode.setCellValueFactory(new PropertyValueFactory("staffcode"));
        staffname.setCellValueFactory(new PropertyValueFactory("staffname"));
//            keycontent.setCellValueFactory(new PropertyValueFactory("keycontent"));
        keycontent.setCellValueFactory(new PropertyValueFactory<SupplierPurchaserProperty,SupplierBasicController.Participation>("participation"));
        staffremarks.setCellValueFactory(new PropertyValueFactory("staffremarks"));

        supplierPurchaserProperties.clear();

        if(transportPurchasers.size()>0){
            for (TransportPurchaser supplierPurchaser:transportPurchasers) {
                SupplierPurchaserProperty supplierPurchaserProperty;
                if(supplierPurchaser.getKeycontent() == 1){
                    supplierPurchaserProperty = new SupplierPurchaserProperty(supplierPurchaser.getId(),supplierPurchaser.getStaffcode(),supplierPurchaser.getStaffname(),supplierPurchaser.getKeycontent()+"",supplierPurchaser.getStaffremarks(), SupplierBasicController.Participation.设为默认,true);
                }else {
                    supplierPurchaserProperty = new SupplierPurchaserProperty(supplierPurchaser.getId(),supplierPurchaser.getStaffcode(),supplierPurchaser.getStaffname(),supplierPurchaser.getKeycontent()+"",supplierPurchaser.getStaffremarks(),false);
                }

                supplierPurchaserProperties.add(supplierPurchaserProperty);

            }
        }
        tableView2.setItems(supplierPurchaserProperties); //tableview添加list

        tableView2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierPurchaserProperty>() {
            @Override
            public void changed(ObservableValue<? extends SupplierPurchaserProperty> observableValue, SupplierPurchaserProperty oldItem, SupplierPurchaserProperty newItem) {
                if(newItem.getId() >0){
                    purchaserNum=newItem.getId();
                }else{
                    purchaserNum=0;
                }
            }



        });

        keycontent.setOnEditCommit(evt -> {


            int row = tableView2.getEditingCell().getRow();

            SupplierPurchaserProperty selected = (SupplierPurchaserProperty) tableView2.getItems().get(row);

            for (SupplierPurchaserProperty supplierPurchaserProperty:supplierPurchaserProperties) {

                supplierPurchaserProperty.setParticipation(null);

                supplierPurchaserProperty.setIsbool(false);

                if(supplierPurchaserProperty.getId() == selected.getId() ){

                    supplierPurchaserProperty.setParticipation(SupplierBasicController.Participation.设为默认);

                    supplierPurchaserProperty.setIsbool(!supplierPurchaserProperty.isIsbool());

                }
            }

        });





    }





    /**
     * 选项卡---联系人 TableView
     */
    public void contact(){



        List<TransportContact> transportContacts = transportContactService.selectTransportContactBySupplierid((long)isNum.getUserData());



        /* contactId.setCellFactory(TextFieldTableCell.forTableColumn());*/
        mainContact.setCellFactory((param) -> new TableViewSample.RadioButtonCell<SupplierContactProperty, SupplierBasicController.Participation>(EnumSet.allOf(SupplierBasicController.Participation.class)));
        username.setCellFactory(TextFieldTableCell.forTableColumn());
        department.setCellFactory(TextFieldTableCell.forTableColumn());
        position.setCellFactory(TextFieldTableCell.forTableColumn());
        phones.setCellFactory(TextFieldTableCell.forTableColumn());
        faxs.setCellFactory(TextFieldTableCell.forTableColumn());
        iphone.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setCellFactory(TextFieldTableCell.forTableColumn());

        remarkss.setCellFactory(TextFieldTableCell.forTableColumn());



//        contactId.setCellValueFactory(new PropertyValueFactory("id"));
//        mainContact.setCellValueFactory(new PropertyValueFactory("keycontact"));
//        new PropertyValueFactory<ShippingAddressProperty,Participation>("participation")
        mainContact.setCellValueFactory(new PropertyValueFactory<SupplierContactProperty,SupplierBasicController.Participation>("participation"));
        username.setCellValueFactory(new PropertyValueFactory("uname"));
        department.setCellValueFactory(new PropertyValueFactory("udepartment"));
        position.setCellValueFactory(new PropertyValueFactory("ujob"));
        phones.setCellValueFactory(new PropertyValueFactory("uphone"));
        faxs.setCellValueFactory(new PropertyValueFactory("ufax"));
        iphone.setCellValueFactory(new PropertyValueFactory("umobile"));
        email.setCellValueFactory(new PropertyValueFactory("uemail"));
        remarkss.setCellValueFactory(new PropertyValueFactory("uremarks"));

        supplierContactProperties.clear();


        if(transportContacts.size()>0){
            for (TransportContact supplierContact1:transportContacts) {

                SupplierContactProperty supplierContactProperty ;
                if(supplierContact1.getKeycontact() == 1){
                    supplierContactProperty = new SupplierContactProperty(supplierContact1.getId(),supplierContact1.getKeycontact().toString(),supplierContact1.getUname(),supplierContact1.getUdepartment(),supplierContact1.getUjob(),supplierContact1.getUphone(), supplierContact1.getUfax(),supplierContact1.getUmobile(),supplierContact1.getUemail(),supplierContact1.getUremarks(), SupplierBasicController.Participation.设为默认,true);
                }else{
                    supplierContactProperty = new SupplierContactProperty(supplierContact1.getId(),supplierContact1.getKeycontact().toString(),supplierContact1.getUname(),supplierContact1.getUdepartment(),supplierContact1.getUjob(),supplierContact1.getUphone(), supplierContact1.getUfax(),supplierContact1.getUmobile(),supplierContact1.getUemail(),supplierContact1.getUremarks(),false);
                }
                supplierContactProperties.add(supplierContactProperty);
            }
        }

        tableView1.setItems(supplierContactProperties); //tableview添加list

        tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierContactProperty>() {
            @Override
            public void changed(ObservableValue<? extends SupplierContactProperty> observableValue, SupplierContactProperty oldItem, SupplierContactProperty newItem) {
                if(newItem.getId() >0){
                    contactNum=newItem.getId();
                }
            }
        });


        mainContact.setOnEditCommit(evt -> {


            int row = tableView1.getEditingCell().getRow();

            SupplierContactProperty selected = (SupplierContactProperty) tableView1.getItems().get(row);

            for (SupplierContactProperty supplierContactProperty:supplierContactProperties) {

                supplierContactProperty.setParticipation(null);

                supplierContactProperty.setIsbool(false);

                if(supplierContactProperty.getId() == selected.getId() ){

                    supplierContactProperty.setParticipation(SupplierBasicController.Participation.设为默认);

                    supplierContactProperty.setIsbool(!supplierContactProperty.isIsbool());

                }
            }

        });


    }

    /**
     * 选项卡---联系人 TableView 新增修改
     */
    public void saveContact(long supplierid){
        for (SupplierContactProperty supplierContactProperty :supplierContactProperties) {
            if(supplierContactProperty.getUname()!=null){

                int keycontack = 0;

                if(supplierContactProperty.isIsbool()){
                    keycontack=1;
                }

                if(supplierContactProperty.getId()>0){
                    //修改联系人
                    TransportContact supplierContact = new TransportContact(supplierContactProperty.getId(),keycontack,supplierContactProperty.getUname(),supplierContactProperty.getUdepartment(),supplierContactProperty.getUjob(),supplierContactProperty.getUphone(),supplierContactProperty.getUfax(),supplierContactProperty.getUmobile(),supplierContactProperty.getUemail(),supplierContactProperty.getUremarks());
                    transportContactService.updateNotNull(supplierContact);
                }else{
                    //新增联系人
                    TransportContact supplierContact = new TransportContact(supplierContactProperty.getId(),keycontack,supplierContactProperty.getUname(),supplierContactProperty.getUdepartment(),supplierContactProperty.getUjob(),supplierContactProperty.getUphone(),supplierContactProperty.getUfax(),supplierContactProperty.getUmobile(),supplierContactProperty.getUemail(),supplierContactProperty.getUremarks(),0,supplierid);
                    transportContactService.save(supplierContact);
                }
            }
        }
    }

    /**
     * 选项卡---采购负责人TabelView 新增修改
     * @param supplierid
     */
    public void savePurchaser(long supplierid){
        for (SupplierPurchaserProperty supplierPurchaserProperty :supplierPurchaserProperties) {
            if(supplierPurchaserProperty.getStaffcode()!=null){

                int keycontack = 0;

                if(supplierPurchaserProperty.isIsbool()){
                    keycontack=1;
                }

                if(supplierPurchaserProperty.getId()>0){
                    //修改采购负责人
                    TransportPurchaser supplierPurchaser = new TransportPurchaser(supplierPurchaserProperty.getId(),supplierPurchaserProperty.getStaffcode(),supplierPurchaserProperty.getStaffname(),keycontack,supplierPurchaserProperty.getStaffremarks());
                    transportPurchaseService.updateNotNull(supplierPurchaser);
                }else{
                    //新增采购负责人
                    TransportPurchaser supplierPurchaser = new TransportPurchaser(supplierPurchaserProperty.getId(),1L,supplierPurchaserProperty.getStaffcode(),supplierPurchaserProperty.getStaffname(),keycontack,supplierPurchaserProperty.getStaffremarks(),0,supplierid);
                    transportPurchaseService.save(supplierPurchaser);
                }
            }
        }
    }


    //联系人空白行
    public void blankContact(){
        SupplierContactProperty supplierContactProperty = new SupplierContactProperty();
        supplierContactProperties.add(supplierContactProperty);
    }

    //采购负责人空白行
    public void blankPurchaser(){
        SupplierPurchaserProperty supplierPurchaserProperty = new SupplierPurchaserProperty();
        supplierPurchaserProperties.add(supplierPurchaserProperty);
    }





    //交易单据
    public void seachTransactionBillInfo(){

        String billStartTime = billStartDate.getValue()==null?"":billStartDate.getValue().toString();

        String billEndtime = billEndDate.getValue()==null?"":billEndDate.getValue().toString();

        String supplierNum =  isNum.getText();

        supplierBillInfos.clear();

        //强制结案订单
//        List<ForceOrder> forceOrders = forceOrderService.findForceOrderBySupplier(supplierNum,"","");


        billcreatedata.setCellValueFactory(new PropertyValueFactory("createdate"));
        billtype.setCellValueFactory(new PropertyValueFactory("types"));
        billnum.setCellValueFactory(new PropertyValueFactory("order"));

        if(xjChecked.isSelected() ||  !xjChecked.isSelected() && !cgChecked.isSelected() && !cgrkChecked.isSelected() ){

            //询价单
            List<InquiryOrder> inquiryOrders = inquiryOrderService.findInquiryOrderBySupplier(supplierNum,billStartTime,billEndtime);

            for (InquiryOrder inquiryOrder:inquiryOrders) {

                SupplierBillInfo supplierBillInfo = new SupplierBillInfo(inquiryOrder.getCreatedate(),"询价单",inquiryOrder.getOrders(),"",inquiryOrder.getPurpeople(),0.00);

                supplierBillInfos.add(supplierBillInfo);
            }

        }


        if(cgChecked.isSelected() ||  !xjChecked.isSelected() && !cgChecked.isSelected() && !cgrkChecked.isSelected()  ){


            //采购订单
            List<PurchaseOrders> purchaseOrders =  purchaseOrdersService.findPurchaseOrdersBySupplier(supplierNum,billStartTime,billEndtime);
            for (PurchaseOrders purchaseOrder:purchaseOrders) {

                SupplierBillInfo supplierBillInfo = new SupplierBillInfo(purchaseOrder.getCreatedate(),"采购订单",purchaseOrder.getOrders(),purchaseOrder.getSeeorders(),purchaseOrder.getPurpeople(),0.00);

                supplierBillInfos.add(supplierBillInfo);

            }
        }
        if(cgrkChecked.isSelected() ||  !xjChecked.isSelected() && !cgChecked.isSelected() && !cgrkChecked.isSelected() ){
            //采购入库单
            List<PurchaseStock> purchaseStocks = purchaseStockService.findPurchaseStockBySupplier(supplierNum,billStartTime,billEndtime);
            for (PurchaseStock purchaseStock:purchaseStocks) {


                SupplierBillInfo supplierBillInfo = new SupplierBillInfo(purchaseStock.getCreatedate(),"采购入库",purchaseStock.getStockorder(),"",purchaseStock.getInspectname(),0.00);

                supplierBillInfos.add(supplierBillInfo);
            }

        }

        tableTransactionBill.setItems(supplierBillInfos);



    }


    @FXML
    private TableView tableTransactionProduct;
    @FXML
    private TableColumn productdate; //制单日期
    @FXML
    private TableColumn producttypes;//单据类型
    @FXML
    private TableColumn productorder;//单号
    @FXML
    private TableColumn producctid;//产品编号
    @FXML
    private TableColumn productnames;//产品名称
    @FXML
    private TableColumn prductnums;//产品数量
    @FXML
    private TableColumn productprices;//单价
    @FXML
    private TableColumn productRemarks;//备注

    @FXML
    private DatePicker proStartTime;
    @FXML
    private DatePicker proEndTime;

    @FXML
    private CheckBox xjcheckedpro;
    @FXML
    private CheckBox cgcheckedpro;
    @FXML
    private CheckBox cgrkcheckedpro;

    ObservableList<SupplierProductInfo> supplierProductInfos = FXCollections.observableArrayList();

    //交易产品
    public void seachTransactionProductInfo(){

        //供应商编号
        String supplierNum =  isNum.getText();

        String proStartTimes = proStartTime.getValue()==null?"":proStartTime.getValue().toString();

        String proEndTimes = proEndTime.getValue()==null?"":proEndTime.getValue().toString();

        supplierProductInfos.clear();


        productdate.setCellValueFactory(new PropertyValueFactory("productdate"));
        producttypes.setCellValueFactory(new PropertyValueFactory("producttypes"));
        productorder.setCellValueFactory(new PropertyValueFactory("productorder"));
        producctid.setCellValueFactory(new PropertyValueFactory("producctid"));
        productnames.setCellValueFactory(new PropertyValueFactory("productnames"));
        prductnums.setCellValueFactory(new PropertyValueFactory("prductnums"));
        productprices.setCellValueFactory(new PropertyValueFactory("productprices"));
        productRemarks.setCellValueFactory(new PropertyValueFactory("productRemarks"));


        if(xjcheckedpro.isSelected() || !xjcheckedpro.isSelected() && !cgcheckedpro.isSelected() && !cgrkcheckedpro.isSelected() ){

            List<InquiryOrder> inquiryOrders = inquiryOrderService.findInquiryOrderBySupplier(supplierNum,proStartTimes,proEndTimes);


            for (InquiryOrder inquiryOrder:inquiryOrders) {


                List<InquiryProduct> inquiryProducts = inquiryProductService.findInquiryProductByInquiryid(inquiryOrder.getId());

                for (InquiryProduct inquiryProduct:inquiryProducts) {

                    SupplierProductInfo supplierProductInfo = new SupplierProductInfo(inquiryOrder.getCreatedate(),"询价单",inquiryOrder.getOrders(),inquiryProduct.getProisnum(),inquiryProduct.getProname(),inquiryProduct.getPronum().toString(),inquiryProduct.getProprice().toString(),inquiryProduct.getProremark());

                    supplierProductInfos.add(supplierProductInfo);

                }

            }

        }



        if(cgcheckedpro.isSelected() || !xjcheckedpro.isSelected() && !cgcheckedpro.isSelected() && !cgrkcheckedpro.isSelected()){


            List<PurchaseOrders> purchaseOrders =  purchaseOrdersService.findPurchaseOrdersBySupplier(supplierNum,proStartTimes,proEndTimes);


            for (PurchaseOrders purchaseOrders1:purchaseOrders) {

                List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProduct(purchaseOrders1.getId());

                for (PurchaseProduct purchaseProduct:purchaseProducts) {

                    SupplierProductInfo supplierProductInfo = new SupplierProductInfo(purchaseOrders1.getCreatedate(),"采购订单",purchaseOrders1.getOrders(),purchaseProduct.getProorders(),purchaseProduct.getProname(),purchaseProduct.getNum().toString(),purchaseProduct.getPrice().toString(),purchaseProduct.getRemarks());

                    supplierProductInfos.add(supplierProductInfo);

                }


            }



        }



        if(cgrkcheckedpro.isSelected() || !xjcheckedpro.isSelected() && !cgcheckedpro.isSelected() && !cgrkcheckedpro.isSelected()){

            List<PurchaseStock> purchaseStocks = purchaseStockService.findPurchaseStockBySupplier(supplierNum,proStartTimes,proEndTimes);

            for (PurchaseStock purchaseStock :purchaseStocks) {

                List<PurchaseStockProduct> purchaseStockProducts = purchaseStockProductService.findStockProductBypurchaseStockId(purchaseStock.getId());


                for (PurchaseStockProduct purchaseStockProduct:purchaseStockProducts) {

                    SupplierProductInfo supplierProductInfo = new SupplierProductInfo(purchaseStock.getCreatedate(),"采购入库",purchaseStock.getStockorder(),purchaseStockProduct.getPronum(),purchaseStockProduct.getProname(),purchaseStockProduct.getStocknum().toString(),"".toString(),purchaseStockProduct.getRemarks());

                    supplierProductInfos.add(supplierProductInfo);

                }


            }


        }






        tableTransactionProduct.setItems(supplierProductInfos);


    }











    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //电话监听
        phonetype.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {


                if(!"新增".equals(newValue)){



                    if(phone.getText() != null && !"".equals(phone.getText())){

                        phoneArray.put(oldValue.toString(),phone.getText());

                    }else{
                        if(phoneArray.get(oldValue) != null && !"".equals(phoneArray.get(oldValue))){
                            phoneArray.remove(oldValue);
                        }
                    }



                    for (Map.Entry<String,String> map:phoneArray.entrySet()) {

                        String mapKey = map.getKey();
                        String mapValue = map.getValue();


                        if(mapKey.equals(newValue)){
                            phone.setText(mapValue);
                            break;
                        }else{
                            phone.setText("");
                        }

                    }

                }else {
//                   //新增

                    ObservableList<String> observableList =  phonetype.getItems();

                    int flagPhone = phonetype.getItems().size();

                    for(int i=0,len=observableList.size();i<len;i++){
                        if(observableList.get(i).equals("新增")){
                            observableList.set(i,"电话"+flagPhone);
                        }
                    }

                    observableList.add("新增");


                    phonetype.setItems(observableList);


                }

            }
        });




        //传真
        faxtype.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {


                if(!"新增".equals(newValue)){



                    if(fax.getText() != null && !"".equals(fax.getText())){

                        faxArray.put(oldValue.toString(),fax.getText());

                    }else{
                        if(faxArray.get(oldValue) != null && !"".equals(faxArray.get(oldValue))){
                            faxArray.remove(oldValue);
                        }
                    }

                    for (Map.Entry<String,String> map:faxArray.entrySet()) {

                        String mapKey = map.getKey();
                        String mapValue = map.getValue();


                        if(mapKey.equals(newValue)){
                            fax.setText(mapValue);
                            break;
                        }else{
                            fax.setText("");
                        }

                    }

                }else {
//                   //新增

                    ObservableList<String> observableList =  faxtype.getItems();

                    int flagPhone = faxtype.getItems().size();

                    for(int i=0,len=observableList.size();i<len;i++){
                        if(observableList.get(i).equals("新增")){
                            observableList.set(i,"传真"+flagPhone);
                        }
                    }

                    observableList.add("新增");


                    faxtype.setItems(observableList);


                }

            }
        });


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

        int index = newStr.indexOf("transport_basic.fxml");

        if(index != -1){

            findSupplier(0); //初始化第一条数据

            BaseController.clickEvent(tableView2, data ->{
                tableViewIndex = tableView2.getSelectionModel().getSelectedIndex();
                moreSupplierEmployeeButtonClick();
            }, 2);

            NumberUtil.changeStatus(fxmlStatus,0); //查看

            setComboBox(28L,country,0);//国家

            setComboBox(21,phonetype,0); //电话

            setComboBox(22,faxtype,0);//传真

            phonetype.getItems().add("新增");

            faxtype.getItems().add("新增");

        }


        /*  contact();*/

    }




    @FXML
    private ImageView rightImage;

    /**
     * 右侧查询Panne
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
            stageStock.setTitle("运输商基本资料");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/basic/loadMoreTransport.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            Stage stageHome = (Stage)StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stageHome.getScene().getWindow().getWidth()+stageHome.getScene().getWindow().getX()-15);
            stageStock.setY(stageHome.getScene().getWindow().getY());

            loadSupplierRight(false);
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


    public void loadSupplierRight(boolean isStop){

        rightCheckBox.selectedProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                boolean isbool =(boolean)newValue;

                loadSupplierRight(isbool);

            }
        });

        List<TransportBasic> transportBasics =  isStop ? transportBasicService.selectTransportBasicNotSotp(0) : transportBasicService.selectTransportBasic();

        ObservableList<TransportBasic> transportBasicsList = FXCollections.observableArrayList();


        rightSupplierSort.setCellValueFactory(new PropertyValueFactory("sort"));
        rightSupplierOrder.setCellValueFactory(new PropertyValueFactory("idnum"));
        rightSupplierDes.setCellValueFactory(new PropertyValueFactory("supdes"));

        if(transportBasics != null && transportBasics.size() > 0){

            for (int i=0,len = transportBasics.size();i < len;i++) {
                transportBasics.get(i).setSort(i+1);
                if(transportBasics.get(i).getIsstop() == 1){
                    transportBasics.get(i).setSupdes(transportBasics.get(i).getSupdes()+"(停用)");
                }
                transportBasicsList.add(transportBasics.get(i));
            }
        }

        rightSupplierTableView.setItems(transportBasicsList);


        rightSupplierTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransportBasic>() {
            @Override
            public void changed(ObservableValue<? extends TransportBasic> observableValue, TransportBasic oldItem, TransportBasic newItem) {
//               alert_informationDialog("=="+newItem.getId());

                TransportBasic transportBasic = transportBasicService.selectByKey(newItem.getId());

                loadDate(transportBasic);

            }
        });

    }

}
