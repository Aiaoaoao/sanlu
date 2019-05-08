package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountPayable;
import com.yc.education.model.account.AccountReceivable;
import com.yc.education.service.account.IAccountPayableInfoService;
import com.yc.education.service.account.IAccountPayableService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 应付账款冲账 小窗口
 */
@Controller
public class AccountPayableMiniController extends BaseController implements Initializable {

    @Autowired IAccountPayableService iAccountPayableService;       // 应付账款冲账
    @Autowired IAccountPayableInfoService iAccountPayableInfoService;


    @FXML
    VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML
    CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField text_query;
    @FXML Button client_sure;

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn order_no;
    @FXML TableColumn order_date;
    @FXML TableColumn supplier_no;
    @FXML TableColumn supplier_name;
    @FXML TableColumn process_people;

    // 订单id
    private static String  orderid = "";
    // 订单编号
    private static String  orderNo = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }


    /**
     * @Description 模糊查询
     * @Author BlueSky
     * @Date 15:12 2019/4/11
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
        int rows = pageRows(che_recently,num);
        String text = text_query.getText();
        List<AccountPayable> payableList = iAccountPayableService.listAccountsPayable(text,page, rows);
        if(payableList != null && payableList.size() >0){
            PageInfo<AccountPayable> pageInfo = new PageInfo<>(payableList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            generalOrder(payableList);
        }else {
            tableView.setItems(null);
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
     * 渲染数据
     * @param list
     */
    private void generalOrder(List<AccountPayable> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getRushDate()));
            });
        }

        // 查询客户集合
        final ObservableList<AccountPayable> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        supplier_no.setCellValueFactory(new PropertyValueFactory("supplierNo"));
        supplier_name.setCellValueFactory(new PropertyValueFactory("supplierNoStr"));
        process_people.setCellValueFactory(new PropertyValueFactory("processPeople"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountPayable>() {
            @Override
            public void changed(ObservableValue<? extends AccountPayable> observableValue, AccountPayable oldItem, AccountPayable newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    AccountPayableMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getOrderNo() != null && !("".equals(newItem.getOrderNo()))){
                    AccountPayableMiniController.orderNo = newItem.getOrderNo()+"";
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
        StageManager.CONTROLLER.remove("AccountPayableControllerMore");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 应付账款冲账
        AccountPayableController controller = (AccountPayableController) StageManager.CONTROLLER.get("AccountPayableControllerMore");
        if(controller != null && orderid != null && !"".equals(orderid)){
            AccountPayable order = iAccountPayableService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicValue(order);
                controller.setControllerUse();
            }
        }
        closeWin();
    }
}
