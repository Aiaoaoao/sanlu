package com.yc.education.controller.check;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.check.CheckOrder;
import com.yc.education.model.check.CheckOrderEmployee;
import com.yc.education.model.check.CheckOrderEmployeeProperty;
import com.yc.education.service.check.CheckOrderEmployeeService;
import com.yc.education.service.check.CheckOrderService;
import com.yc.education.util.NumberUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
 * @ClassName CheckOrderController
 * @Description TODO 请假/加班/出差申请单
 * @Author QuZhangJing
 * @Date 2018/11/22 16:34
 * @Version 1.0
 */
@Controller
public class CheckOrderController extends BaseController implements Initializable {

    @Autowired
    private CheckOrderService checkOrderService;

    @Autowired
    private CheckOrderEmployeeService checkOrderEmployeeService; //申请人


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

    @FXML private DatePicker createdate ; //制单日期


     @FXML private TextField checkorder;//申请单号

    @FXML private ComboBox depotorder;//部门编号

    @FXML private TextField depotname; //部门名称

    @FXML private ComboBox applicantorder; //申请人编号

    @FXML private TextField applicantname; //申请人名称

    @FXML private ComboBox applytype; //申请类型

    @FXML private ComboBox applydes;//申请明细

    @FXML private DatePicker startdate; //开始时间

    @FXML private DatePicker enddate; //结束时间

    @FXML private TextArea remarks; //备注

    @FXML private TextField createpeople; //制单人

    @FXML private TextField shpeople; //审核人

    @FXML private TextField shdate; //审核时间

    @FXML private TextField updatepeople; //最后修改人

    @FXML private TextField updatedate; //最后修改日期


    @FXML private TableView checkOrderEmployee; //申请人

    @FXML private TableColumn emporder; //员工编号

    @FXML private TableColumn empname; //员工名称



    /**
     * 生成编号
     * @return
     */
    public String createIsnum(){
        String maxIsnum = checkOrderService.selectMaxIdnum();
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






    /**
     * 加载数据
     * @param checkOrder
     */
    public void  loadData(CheckOrder checkOrder){

        checkorder.setUserData(checkOrder.getId());
        checkorder.setText(checkOrder.getCheckorder());

        createdate.setValue(LocalDateTime.ofInstant(checkOrder.getCheckdate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        depotname.setText(checkOrder.getDepotname()); //部门名称

        applicantname.setText(checkOrder.getApplicantname()); //申请人

        startdate.setValue(LocalDateTime.ofInstant(checkOrder.getStartdate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        enddate.setValue(LocalDateTime.ofInstant(checkOrder.getEnddate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        remarks.setText(checkOrder.getRemarks());

         createpeople.setText(checkOrder.getCreatepeople());

         shpeople.setText(checkOrder.getShpeople());

         shdate.setText(checkOrder.getShdate());

         updatepeople.setText(checkOrder.getUpdatepeople());

         updatedate.setText(checkOrder.getUpdatedate());


        changeEditable(false);

        submitvbox.setDisable(true);

        insertvbox.setDisable(false);

        updatevbox.setDisable(false);

        deletevbox.setDisable(false);

        findEmployee(checkOrder.getId()); //申请人
    }


    /**
     * 分页查询供应商查询供应商
     */
    public void findSupplier(int pageNum){

        List<CheckOrder> checkOrders = checkOrderService.findCheckOrder(pageNum,1);

        PageInfo<CheckOrder> pageInfo = new PageInfo<>(checkOrders);

        first.setUserData(1); //首页

        prev.setUserData(pageInfo.getPrePage()); //上一页

        next.setUserData(pageInfo.getNextPage());//下一页

        last.setUserData(pageInfo.getPages());//尾页

        checkOrders.forEach(checkOrder ->loadData(checkOrder));

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

        createdate.setDisable(!status);

        checkorder.setDisable(!status);

        depotorder.setDisable(!status);

        depotname.setDisable(!status);

        applicantorder.setDisable(!status);

        applicantname.setDisable(!status);

        applytype.setDisable(!status);

        applydes.setDisable(!status);

        startdate.setDisable(!status);

        enddate.setDisable(!status);

        remarks.setDisable(!status);

        createpeople.setDisable(!status);

        shpeople.setDisable(!status);

        shdate.setDisable(!status);

        updatepeople.setDisable(!status);

        updatedate.setDisable(!status);

    }




    /**
     * 清空
     */
    public void clearValue(){

        createdate.setValue(null);

        checkorder.setUserData(NumberUtil.NULLSTRING);

        checkorder.setUserData(NumberUtil.NULLSTRING);

        depotorder.getSelectionModel().select(0);

        depotname.setText(NumberUtil.NULLSTRING);

        applicantorder.getSelectionModel().select(0);

        applicantname.setText(NumberUtil.NULLSTRING);

        applytype.getSelectionModel().select(0);

        applydes.getSelectionModel().select(0);

        startdate.setValue(null);

        enddate.setValue(null);

        remarks.setText(NumberUtil.NULLSTRING);

        createpeople.setText(NumberUtil.NULLSTRING);

        shpeople.setText(NumberUtil.NULLSTRING);

        shdate.setText(NumberUtil.NULLSTRING);

        updatepeople.setText(NumberUtil.NULLSTRING);

        updatedate.setText(NumberUtil.NULLSTRING);

    }



    /**
     * 提交
     */
    public  void submit(){

        int  submitStuts = (int)fxmlStatus.getUserData(); //1、新增 2、修改


        LocalDate orderDate =createdate.getValue();

        if(orderDate == null){
            alert_informationDialog("请填写制单日期!");//
            return;
        }


        String idnums ="";

        if(submitStuts==2){
            idnums=checkorder.getText();
        }

        CheckOrder checkOrder = new CheckOrder(
                new java.util.Date(java.sql.Date.valueOf(orderDate).getTime()),
                idnums,
                depotorder.getItems().size()==0?"":depotorder.getSelectionModel().getSelectedItem().toString(),
                depotname.getText(),
                applicantorder.getItems().size()==0?"":applicantorder.getSelectionModel().getSelectedItem().toString(),
                applicantname.getText(),
                applytype.getItems().size()==0?"":applytype.getSelectionModel().getSelectedItem().toString(),
                applydes.getItems().size()==0?"":applydes.getSelectionModel().getSelectedItem().toString(),
                new java.util.Date(java.sql.Date.valueOf(startdate.getValue()).getTime()),
                new java.util.Date(java.sql.Date.valueOf(enddate.getValue()).getTime()),
                remarks.getText(),
                createpeople.getText(),
                shpeople.getText(),
                shdate.getText(),
                updatepeople.getText(),
                updatedate.getText(),
                0
        );

        int rows =0;
        if(submitStuts==1){
            String isNums = this.createIsnum();
            checkorder.setText(isNums);
            checkOrder.setCheckorder(isNums);
            rows = checkOrderService.save(checkOrder);
        }else if(submitStuts ==2){
            checkOrder.setId((long)checkorder.getUserData());
            rows = checkOrderService.updateNotNull(checkOrder);
        }
//        checkOrderService.setIdnum(idnum.getText());
        this.loadData(checkOrder); //重新加载数据

        NumberUtil.changeStatus(fxmlStatus,0);
        submitvbox.setDisable(true);


    }





    /**
     * 删除
     */
    public void delete(){
        if(f_alert_confirmDialog(" ","是否删除!")) {
            long id = (long) checkorder.getUserData();
            int rows = checkOrderService.delete(id);
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

    }


    /**
     *  新增按钮
     */
    public void insert(){
        NumberUtil.changeStatus(fxmlStatus,NumberUtil.INSERT);//修改为新增状态
        this.changeEditable(true);
        clearValue();//清空控件

        createPeople(createpeople);//建档人、建档日期

        submitvbox.setDisable(false);
        insertvbox.setDisable(true);
        deletevbox.setDisable(true);
        updatevbox.setDisable(true);
    }


    ObservableList<CheckOrderEmployeeProperty> checkOrderEmployeeProperties = FXCollections.observableArrayList();


    public void findEmployee(long id){

        List<CheckOrderEmployee> checkOrderEmployees = checkOrderEmployeeService.findCheckOrderEmployees(id);


        emporder.setCellFactory(TextFieldTableCell.forTableColumn());
        empname.setCellFactory(TextFieldTableCell.forTableColumn());

        emporder.setCellValueFactory(new PropertyValueFactory("emporder"));
        empname.setCellValueFactory(new PropertyValueFactory("empname"));

        checkOrderEmployeeProperties.clear();

        for (CheckOrderEmployee checkOrderEmployee:checkOrderEmployees) {

            CheckOrderEmployeeProperty checkOrderEmployeeProperty = new CheckOrderEmployeeProperty(checkOrderEmployee.getId(),checkOrderEmployee.getEmployeeorder(),checkOrderEmployee.getEmployeename());

            checkOrderEmployeeProperties.add(checkOrderEmployeeProperty);

        }

        checkOrderEmployee.setItems(checkOrderEmployeeProperties);

    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("check_order.fxml");

        if(index != -1){
            bindEmployee(applicantorder,applicantname);
            bindDepartment(depotorder,depotname);

            findSupplier(1);
        }
    }




}
