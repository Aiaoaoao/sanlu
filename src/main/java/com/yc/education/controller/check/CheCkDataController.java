package com.yc.education.controller.check;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.DepartmentBasic;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.check.*;
import com.yc.education.service.basic.DepartmentBasicService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.check.CheckDataEmployeeService;
import com.yc.education.service.check.CheckDataLeaveearlyService;
import com.yc.education.service.check.CheckDataService;
import com.yc.education.util.NumberUtil;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName CheCkDataController
 * @Description TODO 考勤资料结转
 * @Author QuZhangJing
 * @Date 2019/2/20 16:35
 * @Version 1.0
 */
@Controller
public class CheCkDataController extends BaseController implements Initializable {

    @Autowired
    private CheckDataService checkDataService; //考勤资料结转
    @Autowired
    private DepartmentBasicService departmentBasicService;

    @Autowired
    private CheckDataEmployeeService checkDataEmployeeService;//考勤资料结转 -- 考勤资料人员
    @Autowired
    private CheckDataLeaveearlyService checkDataLeaveearlyService;//考勤资料结转 -- 考勤资料 早退人员


    @FXML Label fxmlStatus; //窗体状态

    @FXML private VBox first; //第一页

    @FXML private VBox prev; //上一页

    @FXML private VBox next; //下一页

    @FXML private VBox last; //最后一页

    @FXML private VBox submitvbox; //提交

    @FXML private VBox clearvbox; //清除

    @FXML private VBox insertvbox; //新增

    @FXML private VBox updatevbox; //修改

    @FXML private VBox deletevbox; //删除

    @FXML private VBox shyes; //审核

    @FXML private VBox shno; //取消审核

    @FXML
    private TextField order; //考勤编号

    @FXML
    private ComboBox depot; //考勤部门

    @FXML
    private DatePicker starttime; //考勤开始

    @FXML
    private DatePicker endtime; //考勤结束

    @FXML
    private TextArea remarks; //备注

    @FXML
    private TextField createpeople; //建档人

    @FXML
    private TextField createdate; //建档日期

    @FXML
    private TextField shpeople; //审核人

    @FXML
   private TextField shdate; //审核日期

    @FXML
    private TextField updatepeople; //最后修改时间

    @FXML
    private TextField updatedate; //最后修改日期

    @FXML private TableView checkDataEmployeeTableView;

    @FXML private TableColumn tabjobnumber; //工号

    @FXML private TableColumn tabworkname; //姓名

    @FXML private TableColumn tabworkday;//工作日

    @FXML private TableColumn tabcheckday;//出勤天数

    @FXML private TableColumn tabnormalovertime;//平时加班天数

    @FXML private TableColumn tabweekendovertime;//周末加班天数

    @FXML private TableColumn tabfestivalovertime;//节日加班天数

    @FXML private TableColumn taboutworkday;//出差天数

    @FXML private TableColumn tabthingvacationday;//事假天数

    @FXML private TableColumn tabillnessvacationday;//带薪假天数

    @FXML private TableColumn tabpaidvactionday;//旷工天数

    @FXML private TableColumn tababsenteeismday;//迟到次数

    @FXML private TableColumn tablatetime;//迟到分钟

    @FXML private TableColumn tablateminute;//早退次数

    @FXML private TableColumn tableaveearlytime;//早退分钟

    @FXML private TableColumn tabremarks;//备注


    @FXML private TableView checkDataLeaveearlyTabelView;

    @FXML private TableColumn jobnumber; //工号

    @FXML private TableColumn workname; //姓名

    @FXML private TableColumn addtime; //日期

    @FXML private TableColumn leaveearlytime; //早退分钟

    @FXML private TableColumn leaveearlyminute; //迟到分钟



    @FXML private TableView checkDataFindTableView;

    @FXML private TableColumn findorder; //考勤编号

    @FXML private TableColumn finddepot; //考勤部门

    @FXML private TableColumn findstarttime; //考勤开始时间

    @FXML private TableColumn findendtime; //考勤结束时间



    //考勤资料转结 --- 员工考勤信息
    ObservableList<CheckDataEmployeeProperty> checkDataEmployeeProperties = FXCollections.observableArrayList();
    //考勤资料转结 --- 员工早退信息
    ObservableList<CheckDataLeaveearlyProperty> checkDataLeaveearlyProperties = FXCollections.observableArrayList();

//    public String createIsnum(){
//        String maxIsnum = employeeBasicService.selectMaxIdnum();
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

    private Stage stage = new Stage();

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    /**
     * 点击弹出 查询员工
     */
    public void moreEmployeeButtonClick(){

        stage.setTitle("现有考勤资料");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/check/check_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();

        loadMoreEmployee();
    }

    public void loadMoreEmployee(){

        List<CheckData> checkDatas = checkDataService.findCheckData();

        ObservableList<CheckData> list = FXCollections.observableArrayList();


        checkDataFindTableView.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

        findorder.setCellValueFactory(new PropertyValueFactory("orders"));
        finddepot.setCellValueFactory(new PropertyValueFactory("depot"));
//        findstarttime.setCellValueFactory(new PropertyValueFactory("empname"));
//        findendtime.setCellValueFactory(new PropertyValueFactory("empname"));

        for (CheckData checkData:checkDatas) {

            list.add(checkData);

        }

        checkDataFindTableView.setItems(list); //tableview添加list

        checkDataFindTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmployeeBasic>() {
            @Override
            public void changed(ObservableValue<? extends EmployeeBasic> observableValue, EmployeeBasic oldItem, EmployeeBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    checkDataFindTableView.setUserData(newItem.getId());
                }
            }
        });


        checkDataFindTableView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)checkDataFindTableView.getUserData();
        CheckData checkData =  checkDataService.selectByKey(id);

        order.setText(checkData.getOrders());

        coseWin();
    }

    public void coseWin(){
        stage.close();
    }








    /**
     * 加载数据
     * @param checkData
     */
    public void  loadData(CheckData checkData){

        order.setUserData(checkData.getId());

        order.setText(checkData.getOrders());

        /**
         * 部门设置
         */
        loadDepartment(checkData.getDepot());

        starttime.setValue(LocalDateTime.ofInstant(checkData.getStarttime().toInstant(), ZoneId.systemDefault()).toLocalDate());

        endtime.setValue(LocalDateTime.ofInstant(checkData.getEndtime().toInstant(), ZoneId.systemDefault()).toLocalDate());

        remarks.setText(checkData.getRemarks());

        createpeople.setText(checkData.getCreatepeople());

        createdate.setText(checkData.getCreatedate());

        shpeople.setText(checkData.getShpeople());

        shdate.setText(checkData.getShdate());

        updatepeople.setText(checkData.getUpdatepeople());

        updatedate.setText(checkData.getUpdatedate());


        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

        if(checkData.getShstatus() == 1){

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);
            shno.setDisable(false);
            shyes.setDisable(true);

        }else{
            //未审核可以修改和删除
            updatevbox.setDisable(false);
            deletevbox.setDisable(false);
            shno.setDisable(true);
            shyes.setDisable(false);
        }

        loadEmployee(checkData.getId());

        loadLeaveearly(checkData.getId());

    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findCheckDate(int pageNum){

        List<CheckData> checkDatas = checkDataService.findCheckData(pageNum,1);

        PageInfo<CheckData> pageInfo = new PageInfo<>(checkDatas);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        checkDatas.forEach(checkData ->loadData(checkData));

    }



    /**
     * 上下首尾跳页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        int pageNum =Integer.parseInt(String.valueOf(node.getUserData()));
        findCheckDate(pageNum);
        NumberUtil.changeStatus(fxmlStatus,0);
    }




    /**
     * 不可编辑
     * @param status
     */
    public void changeEditable(boolean status){

        order.setDisable(!status);

        depot.setDisable(!status);

        starttime.setDisable(!status);

        endtime.setDisable(!status);

        remarks.setDisable(!status);

        createpeople.setDisable(!status);

        createdate.setDisable(!status);

        shpeople.setDisable(!status);

        shdate.setDisable(!status);

        updatepeople.setDisable(!status);

        updatedate.setDisable(!status);

    }




    /**
     * 清空
     */
    public void clearValue(){

        starttime.setValue(null);

        endtime.setValue(null);

        order.setUserData(NumberUtil.NULLSTRING);

        depot.getSelectionModel().select(0);

        remarks.setText(NumberUtil.NULLSTRING);

        remarks.setText(NumberUtil.NULLSTRING);

        createpeople.setText(NumberUtil.NULLSTRING);

        createdate.setText(NumberUtil.NULLSTRING);

        shpeople.setText(NumberUtil.NULLSTRING);

        shdate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);

        checkDataEmployeeProperties.clear();

        checkDataLeaveearlyProperties.clear();

    }



    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改


        LocalDate orderDate =starttime.getValue();

        LocalDate endDate =endtime.getValue();

        if(orderDate == null){
            alert_informationDialog("请填写考勤开始日期!");//
            return;
        }

        if(endDate == null){
            alert_informationDialog("请填写考勤结束日期!");//
            return;
        }

        if(order.getText() == null || "".equals(order.getText())){
            alert_informationDialog("请填写考勤编号!");//
            return;
        }

//        String idnums ="";
//
//        if(submitStuts==2){
//            idnums=order.getText();
//        }

        CheckData checkData = new CheckData(order.getText(),
                depot.getSelectionModel().getSelectedItem()==null?"":depot.getSelectionModel().getSelectedItem().toString(),
                new java.util.Date(Date.valueOf(starttime.getValue()).getTime()),
                new java.util.Date(Date.valueOf(endtime.getValue()).getTime()),
                remarks.getText(),
                createpeople.getText(),
                createdate.getText(),
                shpeople.getText(),
                shdate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
                0
        );


        int rows =0;
        if(submitStuts==1){
//            String isNums = this.createIsnum();
//            order.setText(isNums);
//            checkData.setOrder(isNums);
            rows = checkDataService.save(checkData);
        }else if(submitStuts ==2){
            checkData.setId((long)order.getUserData());
            rows = checkDataService.updateNotNull(checkData);
        }
//      checkOrderService.setIdnum(idnum.getText());
        this.loadData(checkData); //重新加载数据

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);


    }





    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(" ","是否删除!")) {
            long id = (long) order.getUserData();
            int rows = checkDataService.delete(id);
            if (rows > 0) {
                findCheckDate((int) prev.getUserData()); //初始化第一条数据
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

    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件

        createPeople(createpeople,createdate);//建档人、建档日期

        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);
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
        shno.setDisable(true);
        shyes.setDisable(false);
        cancelSh(shpeople,shdate);
        updateOrderStatus(0);

    }

    /**
     * 修改审核状态
     * @param status  0、未审核  1、审核通过
     */
    public void updateOrderStatus(int status){
        long id =  (long)order.getUserData();
        CheckData checkData = checkDataService.selectByKey(id);
        checkData.setShstatus(status);

        if(status == 1){

            checkData.setShpeople(getAdminName());
            checkData.setShdate(getCurrentDate());

            //已审核不得修改和删除
            updatevbox.setDisable(true);
            deletevbox.setDisable(true);

        }else{
            checkData.setShpeople(NumberUtil.NULLSTRING);
            checkData.setShdate(NumberUtil.NULLSTRING);

            //未审核可以修改和删除
            updatevbox.setDisable(false);

            deletevbox.setDisable(false);
        }

        checkDataService.updateNotNull(checkData);
    }


    public void loadEmployee(long checkdataid){

        List<CheckDataEmployee> checkDataEmployees = checkDataEmployeeService.findCheckDataEmployee(checkdataid);

        tabjobnumber.setCellFactory(TextFieldTableCell.forTableColumn());
        tabworkname.setCellFactory(TextFieldTableCell.forTableColumn());
        tabworkday.setCellFactory(TextFieldTableCell.forTableColumn());
        tabcheckday.setCellFactory(TextFieldTableCell.forTableColumn());
        tabnormalovertime.setCellFactory(TextFieldTableCell.forTableColumn());
        tabweekendovertime.setCellFactory(TextFieldTableCell.forTableColumn());
        tabfestivalovertime.setCellFactory(TextFieldTableCell.forTableColumn());
        taboutworkday.setCellFactory(TextFieldTableCell.forTableColumn());
        tabthingvacationday.setCellFactory(TextFieldTableCell.forTableColumn());
        tabillnessvacationday.setCellFactory(TextFieldTableCell.forTableColumn());
        tabpaidvactionday.setCellFactory(TextFieldTableCell.forTableColumn());
        tababsenteeismday.setCellFactory(TextFieldTableCell.forTableColumn());
        tablatetime.setCellFactory(TextFieldTableCell.forTableColumn());
        tablateminute.setCellFactory(TextFieldTableCell.forTableColumn());
        tableaveearlytime.setCellFactory(TextFieldTableCell.forTableColumn());
        tabremarks.setCellFactory(TextFieldTableCell.forTableColumn());


        tabjobnumber.setCellValueFactory(new PropertyValueFactory("jobnumber"));
        tabworkname.setCellValueFactory(new PropertyValueFactory("workname"));
        tabworkday.setCellValueFactory(new PropertyValueFactory("workday"));
        tabcheckday.setCellValueFactory(new PropertyValueFactory("checkday"));
        tabnormalovertime.setCellValueFactory(new PropertyValueFactory("normalovertime"));
        tabweekendovertime.setCellValueFactory(new PropertyValueFactory("weekendovertime"));
        tabfestivalovertime.setCellValueFactory(new PropertyValueFactory("festivalovertime"));
        taboutworkday.setCellValueFactory(new PropertyValueFactory("outworkday"));
        tabthingvacationday.setCellValueFactory(new PropertyValueFactory("thingvacationday"));
        tabillnessvacationday.setCellValueFactory(new PropertyValueFactory("illnessvacationday"));
        tabpaidvactionday.setCellValueFactory(new PropertyValueFactory("paidvactionday"));
        tababsenteeismday.setCellValueFactory(new PropertyValueFactory("absenteeismday"));
        tablatetime.setCellValueFactory(new PropertyValueFactory("latetime"));
        tablateminute.setCellValueFactory(new PropertyValueFactory("lateminute"));
        tableaveearlytime.setCellValueFactory(new PropertyValueFactory("leaveearlytime"));
        tabremarks.setCellValueFactory(new PropertyValueFactory("remarks"));

        checkDataEmployeeProperties.clear();

        if(checkDataEmployees.size()>0){

            for (CheckDataEmployee checkDataEmployee:checkDataEmployees) {

                CheckDataEmployeeProperty checkDataEmployeeProperty = new CheckDataEmployeeProperty(
                        checkDataEmployee.getId(),
                        checkDataEmployee.getJobnumber(),
                        checkDataEmployee.getWorkname(),
                        checkDataEmployee.getWorkday(),
                        checkDataEmployee.getCheckday(),
                        checkDataEmployee.getNormalovertime(),
                        checkDataEmployee.getWeekendovertime(),
                        checkDataEmployee.getFestivalovertime(),
                        checkDataEmployee.getOutworkday(),
                        checkDataEmployee.getThingvacationday(),
                        checkDataEmployee.getIllnessvacationday(),
                        checkDataEmployee.getPaidvacationday(),
                        checkDataEmployee.getAbsenteeismday(),
                        checkDataEmployee.getLatetime(),
                        checkDataEmployee.getLateminute(),
                        checkDataEmployee.getLeaveearlytime(),
                        checkDataEmployee.getLeaveearlyminute(),
                        checkDataEmployee.getRemarks());
                checkDataEmployeeProperties.add(checkDataEmployeeProperty);
            }

            checkDataEmployeeTableView.setItems(checkDataEmployeeProperties);

        }


    }

    public void loadLeaveearly(long checkdataid){


        List<CheckDataLeaveearly> checkDataLeaveearlies = checkDataLeaveearlyService.findCheckDataLeaveearly(checkdataid);

        jobnumber.setCellFactory(TextFieldTableCell.forTableColumn());

        workname.setCellFactory(TextFieldTableCell.forTableColumn());

        addtime.setCellFactory(TextFieldTableCell.forTableColumn());

        leaveearlytime.setCellFactory(TextFieldTableCell.forTableColumn());

        leaveearlyminute.setCellFactory(TextFieldTableCell.forTableColumn());

        jobnumber.setCellValueFactory(new PropertyValueFactory("jobnumber"));

        workname.setCellValueFactory(new PropertyValueFactory("workname"));

        addtime.setCellValueFactory(new PropertyValueFactory("addtime"));

        leaveearlytime.setCellValueFactory(new PropertyValueFactory("leaveearlytime"));

        leaveearlyminute.setCellValueFactory(new PropertyValueFactory("leaveearlyminute"));


        checkDataLeaveearlyProperties.clear();

        if(checkDataLeaveearlies.size() > 0){

            for (CheckDataLeaveearly checkDataLeaveearly:checkDataLeaveearlies) {

                CheckDataLeaveearlyProperty checkDataLeaveearlyProperty = new CheckDataLeaveearlyProperty(checkDataLeaveearly.getId(),checkDataLeaveearly.getJobnumber(),checkDataLeaveearly.getWorkname(),checkDataLeaveearly.getAddtime(),checkDataLeaveearly.getLeaveearlytime(),checkDataLeaveearly.getLeaveearlyminute());

                checkDataLeaveearlyProperties.add(checkDataLeaveearlyProperty);
            }

        }

        checkDataLeaveearlyTabelView.setItems(checkDataLeaveearlyProperties);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("data_check");

        if(index != -1){
            //添加部门信息
            loadDepartment("");


            findCheckDate(1);//加载数据

           // NumberUtil.changeStatus(fxmlStatus,0); //查看

        }

    }


     public void loadDepartment(String department){

         //查询部门全部信息
         List<DepartmentBasic> departmentBasics = departmentBasicService.selectDepartmentBasicByParentId(1);

         ObservableList<String> str = FXCollections.observableArrayList();

//         depot.getItems().clear();

         if(departmentBasics.size() > 0 ){
             for (DepartmentBasic departmentBasic:departmentBasics) {
                 str.add(departmentBasic.getDepname());
             }
         }


         for (int  i=0,len=departmentBasics.size();i<len;i++){

             if(department.equals(departmentBasics.get(i).getDepname())){
                 depot.getSelectionModel().select(i);
                 break;
             }else{
                 depot.getSelectionModel().select(0);
             }

         }

     }


}
