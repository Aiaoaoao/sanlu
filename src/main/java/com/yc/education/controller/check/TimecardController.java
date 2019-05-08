package com.yc.education.controller.check;

import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.check.Timecard;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.service.check.TimecardService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName TimecardController
 * @Description TODO  原始考勤资料
 * @Author QuZhangJing
 * @Date 2019/2/18 14:11
 * @Version 1.0
 */
@Controller
public class TimecardController extends BaseController implements Initializable {

    @Autowired
    private TimecardService timecardService; //原始考勤资料

    @Autowired
    private EmployeeBasicService employeeBasicService; //员工资料


    @FXML private TextField startorder;

    @FXML private TextField endorder;

    @FXML private DatePicker starttime;

    @FXML private DatePicker endtime;

    @FXML
    private TableView timecardTableView;

    @FXML
    private TableColumn emporder;

    @FXML
    private TableColumn emptime;


    @FXML private TableView tableViewEmployee; //供应商查询
    @FXML private TableColumn empid; //id
    @FXML private TableColumn findemployeeid; //员工编号
    @FXML private TableColumn findemployeename; //员工姓名

    private Stage stage = new Stage();
    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    //查询更多员工    true为开始   false结束
    private  boolean flag = false;

    public void buttonStart(){
        flag = true;
        moreEmployeeButtonClick();
    }


    public void buttonEnd(){
        flag = false;
        moreEmployeeButtonClick();
    }

    /**
     * 点击弹出 查询员工
     */
    public void moreEmployeeButtonClick(){

        stage.setTitle("现有员工查询");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/check/employee_find.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();

        loadMoreEmployee();
    }

    public void loadMoreEmployee(){

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

        ObservableList<EmployeeBasic> list = FXCollections.observableArrayList();


        tableViewEmployee.setEditable(true);

        /*staffcode.setCellFactory((TableColumn<Object,Object> a ) -> new EditingCell<>());*/

        empid.setCellValueFactory(new PropertyValueFactory("id"));
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
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)tableViewEmployee.getUserData();
        EmployeeBasic employeeBasic =  employeeBasicService.selectByKey(id);
        if(flag){
            startorder.setText(employeeBasic.getIdnum());
        }else{
            endorder.setText(employeeBasic.getIdnum());
        }
        coseWin();
    }

    public void coseWin(){
        stage.close();
    }





    //查询考勤资料
    public void  searchCheck(){
        search(1);
    }

    //查询食堂资料
    public void searchCanteen(){
        search(2);
    }


    //查询考勤资料
    public void search(int status){

         String sorder =  startorder.getText();

        String eorder =  endorder.getText();

        String stime = "";

        if( starttime.getValue() != null){
            stime = starttime.getValue().toString();
        }

        String etime = "";
        if( endtime.getValue() != null){
            etime = endtime.getValue().toString();
        }

         List<Timecard> timecards = timecardService.findTimecardByUserOrderAndTime(status,sorder,eorder,stime,etime);

        ObservableList<Timecard> observableList = FXCollections.observableArrayList();

        emporder.setCellFactory(TextFieldTableCell.forTableColumn());
        emptime.setCellFactory(TextFieldTableCell.forTableColumn());

        emporder.setCellValueFactory(new PropertyValueFactory("employeeorder"));
        emptime.setCellValueFactory(new PropertyValueFactory("createtime"));

        if(timecards.size()>0){

           for (Timecard timecard:timecards) {

               timecard.setEmployeeorder("("+timecard.getEmployeeorder()+")"+timecard.getEmployeename());

               observableList.add(timecard);

           }

       }

        timecardTableView.setItems(observableList);

    }


    //清空
    public void clearTextField(){

        startorder.setText("");

        endorder.setText("");

        starttime.setValue(null);

        endtime.setValue(null);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
