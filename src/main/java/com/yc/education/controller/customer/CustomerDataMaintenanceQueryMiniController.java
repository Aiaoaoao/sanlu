package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.customer.CustomerDataMaintain;
import com.yc.education.service.customer.ICustomerDataMaintainService;
import com.yc.education.util.AppConst;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class CustomerDataMaintenanceQueryMiniController extends BaseController implements Initializable {


    @Autowired
    ICustomerDataMaintainService iCustomerDataMaintainService;


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField textQuery;

    @FXML Button client_sure;

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn order_no;
    @FXML TableColumn create_date;
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_name;



    // 订单编号
    private static String  orderid = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }


    /**
     * @Description 模糊查询
     * @Author BlueSky
     * @Date 11:17 2019/4/11
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
//        int rows = pageRows(che_recently,num);
        String text = textQuery.getText();
        List<CustomerDataMaintain> list = iCustomerDataMaintainService.listCustomerDataMaintain(text,page, AppConst.ROWS);
        if(list != null && list.size() >0){
            PageInfo<CustomerDataMaintain> pageInfo = new PageInfo<>(list);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(list);
        }else {
            tableView.setItems(null);
        }
    }


    /**
     * 分页
     * @param event
     */
    @FXML
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        if(node.getUserData() != null){
            int page =Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
        }
    }


    /**
     * 初始化加载数据
     */
    private void loadData(List<CustomerDataMaintain> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
            });
        }

        // 查询客户集合
        final ObservableList<CustomerDataMaintain> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("createNo"));
        create_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));//映射
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerName"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerDataMaintain>() {
            @Override
            public void changed(ObservableValue<? extends CustomerDataMaintain> observableValue, CustomerDataMaintain oldItem, CustomerDataMaintain newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    CustomerDataMaintenanceQueryMiniController.orderid = newItem.getId()+"";
                }
            }
        });

        tableView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }

    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("CustomerDataMaintenanceMini");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 客户资料你维护
        CustomerDataMaintenanceController controller = (CustomerDataMaintenanceController) StageManager.CONTROLLER.get("CustomerDataMaintenanceMini");
        if(controller != null){
            CustomerDataMaintain order = iCustomerDataMaintainService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicVal(order);
            }
        }
        closeWin();
    }


}
