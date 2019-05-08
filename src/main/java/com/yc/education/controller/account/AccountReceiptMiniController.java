package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountReceipt;
import com.yc.education.model.account.AccountReceiptInfo;
import com.yc.education.model.account.AccountReceiptInfoProperty;
import com.yc.education.service.account.IAccountReceiptInfoService;
import com.yc.education.service.account.IAccountReceiptService;
import com.yc.education.service.customer.ICustomerService;
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
 * 销项发票小窗口
 */
@Controller
public class AccountReceiptMiniController extends BaseController implements Initializable {

    @Autowired ICustomerService iCustomerService;       // 客户
    @Autowired IAccountReceiptService iAccountReceiptService;       // 销项发票
    @Autowired IAccountReceiptInfoService iAccountReceiptInfoService;

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
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_name;
    @FXML TableColumn token_no;
    @FXML TableColumn status;

    // 订单id
    private static String  orderid = "";
    // 订单编号
    private static String  orderNo = "";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        int rows = pageRows(che_recently,num);
        String text = findOrder.getText();
        List<AccountReceipt> AccountReceiptList = iAccountReceiptService.listAccountReceipt(text,page, rows);
        if(AccountReceiptList != null && AccountReceiptList.size() >0){
            PageInfo<AccountReceipt> pageInfo = new PageInfo<>(AccountReceiptList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(AccountReceiptList);
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
     * 根据订单号查询
     */
    @FXML
    public void likeOrder(){
        setMenuValue(1);
    }

    /**
     * 渲染数据
     * @param list
     */
    private void generalOrder(List<AccountReceipt> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getOrderAudit() != null && p.getOrderAudit()){
                    p.setOrderAuditStr("已审核");
                }else{
                    p.setOrderAuditStr("未审核");
                }
            });
        }

        // 查询客户集合
        final ObservableList<AccountReceipt> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        token_no.setCellValueFactory(new PropertyValueFactory("tokenNo"));
        status.setCellValueFactory(new PropertyValueFactory("orderAudit"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountReceipt>() {
            @Override
            public void changed(ObservableValue<? extends AccountReceipt> observableValue, AccountReceipt oldItem, AccountReceipt newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    AccountReceiptMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getOrderNo() != null && !("".equals(newItem.getOrderNo()))){
                    AccountReceiptMiniController.orderNo = newItem.getOrderNo()+"";
                }
            }
        });

        tableView.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });
    }

    /**
     * 初始化加载数据
     */
    private void loadData(List<AccountReceipt> list){
        generalOrder(list);
    }

    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("AccountReceiptControllerMore");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 收款单
        AccountReceiptController controller = (AccountReceiptController) StageManager.CONTROLLER.get("AccountReceiptControllerMore");
        if(controller != null && orderid != null && !"".equals(orderid)){
            AccountReceipt order = iAccountReceiptService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicValue(order);
                List<AccountReceiptInfo> receiptInfoList = iAccountReceiptInfoService.listAccountReceiptInfo(order.getId().toString());
                if (receiptInfoList != null){
                    List<AccountReceiptInfoProperty> receiptInfoPropertyList = new ArrayList<>();
                    receiptInfoList.forEach(p-> receiptInfoPropertyList.add(new AccountReceiptInfoProperty(p.getId(), p.getNo(), p.getCustomer(), p.getReceiptNot(), p.getAddtime() == null ? null:new SimpleDateFormat("yyyy-MM-dd").format(p.getAddtime()), p.getPrice(), p.getDollar(), p.getOrderNo())));
                    controller.generalProductTab(receiptInfoPropertyList);
                }
                controller.setControllerUse();
            }
        }
        closeWin();
    }
}
