package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.customer.CustomerBusinessLeaderProperty;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 员工查询窗口
 * @Author BlueSky
 * @Date 2018-12-29 14:22
 */
@Controller
public class EmployeeMiniController extends BaseController implements Initializable {

    @Autowired
    private EmployeeBasicService iEmployeeBasicService; // 员工数据

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;    //显示最近
    @FXML CheckBox che_stop_compay; //显示暂停来往员工
    @FXML TextField number;       //最近多少笔
    @FXML TextField text;         //查询内容： 编号、姓名


    @FXML private TableView tableViewEmployee; //供应商查询
    @FXML private TableColumn empid; //id
    @FXML private TableColumn findemployeeid; //员工编号
    @FXML private TableColumn findemployeename; //员工姓名

    private static String  id = "";  // 临时存储 员工id
    private static String  num = ""; // 临时存储 员工编号
    private static String  name = "";// 临时存储 员工姓名
    int tablePosition = 0; // 弹出窗口选中的行数

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id = "";
        num = "";
        name = "";
        setMenuValue(1);
    }

    /**
     * @Description 查找
     * @Author BlueSky
     * @Date 16:04 2019/4/15
     **/
    @FXML
    public void textQuery(){
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        int rows = pageRows(che_recently,number);

        List<EmployeeBasic> list = iEmployeeBasicService.listEmployeeBasic(text.getText(),che_stop_compay.isSelected()?"":"0",page, rows);
        if(list != null && list.size() >0){
            PageInfo<EmployeeBasic> pageInfo = new PageInfo<>(list);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadMoreEmployee(list);
        }
    }


    /**
     * 分页
     * @param event
     */
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        if(node.getUserData() != null){
            int page =Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
        }
    }

    /**
     * 现有供应商查询
     */
    public void loadMoreEmployee(List<EmployeeBasic> employeeBasics){

        ObservableList<EmployeeBasic> list = FXCollections.observableArrayList();

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
                    id = newItem.getId().toString();
                }
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    num = newItem.getIdnum();
                }
                if(newItem.getEmpname() != null && !("".equals(newItem.getEmpname()))){
                    name = newItem.getEmpname();
                }
            }
        });

        tableViewEmployee.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });

    }

    @FXML
    public void closeSureWin(){
        try{
            tablePosition = (int) StageManager.CONTROLLER.get("tablePosition");
        }catch (Exception e){
            e.printStackTrace();
        }
        // 客户关系 - 客户基本资料
        CustomerBasicInfoController customerBasicInfoController = (CustomerBasicInfoController)StageManager.CONTROLLER.get("CustomerBasicInfoControllerEmployee");
        if(customerBasicInfoController != null && id != null && !"".equals(id)){
            EmployeeBasic employeeBasic =  iEmployeeBasicService.selectByKey(Long.valueOf(id));
            if(employeeBasic == null){
                colseWin();
            }
            // 把数据绑到当前选中的行中
            ObservableList<CustomerBusinessLeaderProperty> list = customerBasicInfoController.customer_business_leader_tab.getItems();
            if (list == null) {
                list = FXCollections.observableArrayList();
            }
            for (int i = 0; i < list.size(); i++) {
                if(tablePosition == i){
                    if((i+"").equals(tablePosition+"")){
                        list.get(i).setEmployeeCode(employeeBasic.getIdnum());
                        list.get(i).setName(employeeBasic.getEmpname());
                        list.get(i).setPrimaryPeople(employeeBasic.getEmpname());
                        break;
                    }
                }
            }
            customerBasicInfoController.customer_business_leader_tab.setItems(FXCollections.observableArrayList(list));
        }

        colseWin();
    }

    /**
     * 关闭此窗口
     */
    @FXML
    public void colseWin(){
        StageManager.CONTROLLER.remove("CustomerBasicInfoControllerEmployee");
        Stage stage=(Stage)tableViewEmployee.getScene().getWindow();
        stage.close();
    }
}
