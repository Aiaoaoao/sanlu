package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.*;
import com.yc.education.service.account.IAccountInputInvoiceInfoService;
import com.yc.education.service.account.IAccountInputInvoiceService;
import com.yc.education.service.account.IAccountPrepaymentInfoService;
import com.yc.education.service.account.IAccountPrepaymentService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 账款 -- 供应商查询 -- 预付账款 小窗口
 */
@Controller
public class AccountPrepaymentMiniController extends BaseController implements Initializable {

    @Autowired IAccountPrepaymentService iAccountPrepaymentService;       // 预付账款
    @Autowired IAccountPrepaymentInfoService iAccountPrepaymentInfoService;   // 预付账款明细


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML Button client_sure;
    @FXML
    CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField findOrder; // 订单号

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn order_no;
    @FXML TableColumn order_date;
    @FXML TableColumn customer_no;      //供应商编号
    @FXML TableColumn customer_name;    //供应商名称
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
        String text = findOrder.getText();
        List<AccountPrepayment> accountPrepaymentList = iAccountPrepaymentService.listAccountPrepayment(text,page, rows);
        if(accountPrepaymentList != null && accountPrepaymentList.size() >0){
            PageInfo<AccountPrepayment> pageInfo = new PageInfo<>(accountPrepaymentList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            generalOrder(accountPrepaymentList);
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
    private void generalOrder(List<AccountPrepayment> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
            });
        }

        // 查询客户集合
        final ObservableList<AccountPrepayment> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        process_people.setCellValueFactory(new PropertyValueFactory("madePeople"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountInputInvoice>() {
            @Override
            public void changed(ObservableValue<? extends AccountInputInvoice> observableValue, AccountInputInvoice oldItem, AccountInputInvoice newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    AccountPrepaymentMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getOrderNo() != null && !("".equals(newItem.getOrderNo()))){
                    AccountPrepaymentMiniController.orderNo = newItem.getOrderNo()+"";
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
        StageManager.CONTROLLER.remove("AccountPrepaymentControllerMore");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 应付账款冲账
        AccountPrepaymentController controller = (AccountPrepaymentController) StageManager.CONTROLLER.get("AccountPrepaymentControllerMore");
        if(controller != null && orderid != null && !"".equals(orderid)){
            AccountPrepayment order = iAccountPrepaymentService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicValue(order);
                controller.showTableSelfInfo();
                controller.setControllerUse();
            }
        }
        closeWin();
    }
}
