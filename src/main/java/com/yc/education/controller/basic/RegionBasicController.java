package com.yc.education.controller.basic;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.*;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.service.basic.RegionBasicService;
import com.yc.education.service.basic.RegionEmployeeService;
import com.yc.education.util.AppConst;
import com.yc.education.util.NumberUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName RegionBasicController
 * @Description TODO 业务区域设定
 * @Author QuZhangJing
 * @Date 2018-08-14 13:47
 * @Version 1.0
 */
@Controller
public class RegionBasicController extends BaseController implements Initializable {


    @Autowired
    private RegionBasicService regionBasicService;  //业务区域设定

    @Autowired
    private RegionEmployeeService regionEmployeeService; //业务区域员工

    @Autowired
    private EmployeeBasicService employeeBasicService;//员工





    @FXML private javafx.scene.control.Label fxmlStatus; //状态


    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页


    @FXML private VBox clearvbox; //清除

    @FXML private VBox submitvbox; //提交

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除


    @FXML private TextField isnum; //编号

    @FXML private TextField area;//地区


    @FXML TableView region_employee; //区域-员工

    @FXML TableColumn empid; //员工编号

    @FXML TableColumn empisnum; //员工编号

    @FXML TableColumn empname; //姓名

    @FXML TableColumn remarks; //备注


    @FXML private TableView tableViewRegion; //区域查询查询
    @FXML private TableColumn regid; //id
    @FXML private TableColumn findregionid; //区域编号
    @FXML private TableColumn findregionname; //区域名称


    @FXML private TableView tableViewEmployee; //员工TableView
    @FXML private TableColumn findempid; //id
    @FXML private TableColumn findemployeeid; //员工编号
    @FXML private TableColumn findemployeename; //员工姓名

    private long regNum=0;

    private Stage stage = new Stage();


    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    /**
     * 业务区域设定-TabelView数据绑定
     */
    private ObservableList<RegionProperty> regionPropertys = FXCollections.observableArrayList();



    @FXML private  VBox  region_find_fast;
    @FXML private  VBox  region_find_prev;
    @FXML private  VBox  region_find_next;
    @FXML private  VBox  region_find_last;

    private int pageSize;

    @FXML private TextField region_basic_textField;

    private int tableViewIndex = 0;


    @FXML private VBox employee_find_fast;
    @FXML private VBox employee_find_prev;
    @FXML private VBox employee_find_next;
    @FXML private VBox employee_find_last;

    @FXML private TextField employee_textField;

    @FXML private TextField order_textField;

    @FXML private TextField orderAndName_textField;

    public void findRegionBasicSearch(){

        String pageSizes =region_basic_textField.getText();


        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreRegion(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }


    }


    public void findRegionBasicPages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreRegion(pageNum);

    }


    public void moreRegionButtonClick(){

        stage.setTitle("现有区域查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/region_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize = 10;
        loadMoreRegion(1);
    }


    /**
     * 现有区域查询
     */
    public void loadMoreRegion(int pageNum){

        List<RegionBasic> regionBasics = regionBasicService.selectProductBasic("".equals(order_textField.getText()) || order_textField.getText() == null ? "" : order_textField.getText(),pageNum,pageSize);


        PageInfo<RegionBasic> pageInfo = new PageInfo<>(regionBasics);

        region_find_fast.setUserData(1); //首页

        region_find_prev.setUserData(pageInfo.getPrePage()); //上一页

        region_find_next.setUserData(pageInfo.getNextPage());//下一页

        region_find_last.setUserData(pageInfo.getPages());//尾页

        ObservableList<RegionBasic> list = FXCollections.observableArrayList();

        tableViewRegion.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

//        regid.setCellValueFactory(new PropertyValueFactory("id"));
        findregionid.setCellValueFactory(new PropertyValueFactory("isnum"));
        findregionname.setCellValueFactory(new PropertyValueFactory("area"));

        for (RegionBasic regionBasic:regionBasics) {
            list.add(regionBasic);
        }

        tableViewRegion.setItems(list); //tableview添加list

        tableViewRegion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegionBasic>() {
            @Override
            public void changed(ObservableValue<? extends RegionBasic> observableValue, RegionBasic oldItem, RegionBasic newItem) {
                if(newItem.getIsnum()!= null && !("".equals(newItem.getIsnum()))){
                    isnum.setUserData(newItem.getId());
                }
            }
        });


        tableViewRegion.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)isnum.getUserData();
        RegionBasic regionBasic =  regionBasicService.selectByKey(id);
         loadData(regionBasic);
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }














    /**
     * 加载数据
     * @param regionBasic
     */
    public void  loadData(RegionBasic regionBasic){

        isnum.setUserData(regionBasic.getId());
        isnum.setText(regionBasic.getIsnum());
        area.setText(regionBasic.getArea());



        loadEmp(regionBasic.getId());

        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);


    }


    public void loadEmp(long regionBasicId){
        List<RegionEmployee> regionEmployees = regionEmployeeService.selectRegionEmployeeByRegionid(regionBasicId);


        empisnum.setCellFactory(TextFieldTableCell.forTableColumn());
        empname.setCellFactory(TextFieldTableCell.forTableColumn());
        remarks.setCellFactory(TextFieldTableCell.forTableColumn());

        empid.setCellValueFactory(new PropertyValueFactory("id"));
        empisnum.setCellValueFactory(new PropertyValueFactory("empisnum"));
        empname.setCellValueFactory(new PropertyValueFactory("empname"));
        remarks.setCellValueFactory(new PropertyValueFactory("remarks"));



        regionPropertys.clear();

        if(regionEmployees.size()>0) {
            for (RegionEmployee regionEmployee : regionEmployees) {
                RegionProperty regionProperty = new RegionProperty(regionEmployee.getId(),regionEmployee.getEmpisnum(),regionEmployee.getEmpname(),regionEmployee.getRemarks());
                regionPropertys.add(regionProperty);
            }
        }
        region_employee.setItems(regionPropertys);

        region_employee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegionProperty>() {
            @Override
            public void changed(ObservableValue<? extends RegionProperty> observableValue, RegionProperty oldItem, RegionProperty newItem) {
                if(newItem.getId() >0){
                    regNum=newItem.getId();
                }else{
                    regNum=0;
                }
            }
        });
    }



    public void saveRegion(long regionid){
        for (RegionProperty regionProperty :regionPropertys) {
            if(regionProperty.getEmpisnum()!=null){
                if(regionProperty.getId()>0){
                    //修改业务区域负责人
                    RegionEmployee regionEmployee = new RegionEmployee(regionProperty.getId(),regionProperty.getEmpisnum(),regionProperty.getEmpname(),regionProperty.getRemarks());
                    regionEmployeeService.updateNotNull(regionEmployee);
                }else{
                    //新增业务区域负责人
                    RegionEmployee regionEmployee = new RegionEmployee(regionProperty.getId(),regionid,regionProperty.getEmpisnum(),regionProperty.getEmpname(),regionProperty.getRemarks());
                    regionEmployeeService.save(regionEmployee);
                }
            }
        }
    }

    /**
     * 业务区域负责人空白行
     */
    public void blankRegion(){
        RegionProperty regionProperty = new RegionProperty();
        regionPropertys.add(regionProperty);
    }



    public void findEmployeeSearch(){
        String pageSizes =  employee_textField.getText();

        if(!"".equals(pageSizes) || pageSizes != null  ){
            pageSize = Integer.parseInt(pageSizes);
            loadMoreRegionEmployee(1);
        }else{
            alert_informationDialog("请输入页码数!");
        }




    }


    public void employeePages(MouseEvent event){
        Node node =(Node)event.getSource();
        //当前页码
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));

        loadMoreRegionEmployee(pageNum);
    }



    public void moreRegionEmployeeButtonClick(){

        stage.setTitle("现有员工查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/basic/region_employee_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();
        pageSize =10;
        loadMoreRegionEmployee(1);
    }


    /**
     * 现有员工查询
     */
    public void loadMoreRegionEmployee(int pageNum){

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic("".equals(orderAndName_textField.getText()) || orderAndName_textField.getText() == null ? "" : orderAndName_textField.getText(),pageNum,pageSize);

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

        for(int i=0,len=regionPropertys.size();i<len;i++){
            RegionProperty regionPropertys1 = regionPropertys.get(i);
            if(i == tableViewIndex){
                regionPropertys1.setEmpisnum(employeeBasic.getIdnum());
                regionPropertys1.setEmpname(employeeBasic.getEmpname());
                regionPropertys1.setRemarks(employeeBasic.getRemarks());
            }
        }

        coseWin();
    }





    /**
     * region_employee 键盘按下触发
     * @param event
     */
    public void region_employeeKey(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {
            if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
                regionEmployeeService.delete(regNum);
                ObservableList<RegionProperty> regionPropertiesNew = FXCollections.observableArrayList();

                for (RegionProperty regionProperty : regionPropertys) {
                    if (regionProperty.getId() != regNum) {
                        regionPropertiesNew.add(regionProperty);
                    }
                }
                regionPropertys.clear();
                regionPropertys.setAll(regionPropertiesNew);
            }
        }
        if (event.getCode() == KeyCode.INSERT) {
            //空白行
            blankRegion();
        }

        if(event.getCode() == KeyCode.F4){
            moreRegionEmployeeButtonClick();
        }

    }




    /**
     * 分页查询供应商查询供应商
     */
    public void findSupplier(int pageNum){

        List<RegionBasic> regionBasics = regionBasicService.selectProductBasic(pageNum,1);

        PageInfo<RegionBasic> pageInfo = new PageInfo<>(regionBasics);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        regionBasics.forEach(companyBasic1 ->loadData(companyBasic1));

        if(regionBasics.size() == 0 || regionBasics == null ){
            loadEmp(0);

            changeEditable(false);

            submitvbox.setDisable(true);

            insertvbox.setDisable(false);

            updatevbox.setDisable(false);

            deletevbox.setDisable(false);
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

        isnum.setDisable(!status);
        area.setDisable(!status);
        region_employee.setEditable(status);
    }




    /**
     * 清空
     */
    public void clearValue(){

        isnum.setUserData(NumberUtil.NULLSTRING);
        isnum.setText(NumberUtil.NULLSTRING);
        area.setText(NumberUtil.NULLSTRING);

        regionPropertys.clear();


    }



    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改


        if(isnum.getText()=="" || "".equals(isnum.getText()) ){
            alert_informationDialog("请手动填写编号!");
            return;
        }


        Object[] values = {
                0L,
                isnum.getText(),
                area.getText(),
        };

        RegionBasic regionBasic =(RegionBasic)NumberUtil.arrayToObject(values,RegionBasic.class);

        int rows =0;
        if(submitStuts==1){
            rows = regionBasicService.save(regionBasic);
        }else if(submitStuts ==2){
            regionBasic.setId((long)isnum.getUserData());
            rows = regionBasicService.updateNotNull(regionBasic);
        }

        saveRegion(regionBasic.getId());

        this.loadData(regionBasic); //重新加载数据
        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);


    }





    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            long id = (long) isnum.getUserData();
            int rows = regionBasicService.delete(id);
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
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);
        blankRegion();
    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件
        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);
        blankRegion();
    }


    /**
     * 回车查询
     * @param event
     */
    public void isNumKey(KeyEvent event){

        if(event.getCode()== KeyCode.ENTER){

            String value = isnum.getText();

            if(!"".equals(value)){

                RegionBasic regionBasic= regionBasicService.selectProductBasicByIsnum(value);

                if(regionBasic != null){
                    this.loadData(regionBasic);
                }

            }

        }

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("region_basic");

        if(index != -1){
            findSupplier(1);

            BaseController.clickEvent(region_employee, data ->{
                tableViewIndex = region_employee.getSelectionModel().getSelectedIndex();
                moreRegionEmployeeButtonClick();
            }, 2);

        }


    }



}
