package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountSaleInvoice;
import com.yc.education.model.account.AccountSaleInvoiceInfo;
import com.yc.education.model.account.AccountSaleInvoiceInfoProperty;
import com.yc.education.service.account.IAccountSaleInvoiceInfoService;
import com.yc.education.service.account.IAccountSaleInvoiceService;
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

/**
 * 销项发票小窗口
 */
@Controller
public class AccountSaleInvoiceMiniController extends BaseController implements Initializable {


    @Autowired
    IAccountSaleInvoiceService iAccountSaleInvoiceService;
    @Autowired
    IAccountSaleInvoiceInfoService iAccountSaleInvoiceInfoService;

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
    @FXML TableColumn invoice_no;
    @FXML TableColumn status;
    @FXML TableColumn cancel;

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
        List<AccountSaleInvoice> AccountSaleInvoiceList = iAccountSaleInvoiceService.listAccountSaleInvoice(text,page, rows);
        if(AccountSaleInvoiceList != null && AccountSaleInvoiceList.size() >0){
            PageInfo<AccountSaleInvoice> pageInfo = new PageInfo<>(AccountSaleInvoiceList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(AccountSaleInvoiceList);
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
    private void generalOrder(List<AccountSaleInvoice> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getOrderCancel() != null && p.getOrderCancel()){
                    p.setOrderCancelStr("已作废");
                }else{
                    p.setOrderCancelStr("正常");
                }
                if(p.getOrderAudit() != null && p.getOrderAudit()){
                    p.setOrderAuditStr("已审核");
                }else{
                    p.setOrderAuditStr("未审核");
                }
            });
        }

        // 查询客户集合
        final ObservableList<AccountSaleInvoice> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("orderNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        invoice_no.setCellValueFactory(new PropertyValueFactory("invoiceNo"));
        status.setCellValueFactory(new PropertyValueFactory("orderAuditStr"));
        cancel.setCellValueFactory(new PropertyValueFactory("orderCancelStr"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountSaleInvoice>() {
            @Override
            public void changed(ObservableValue<? extends AccountSaleInvoice> observableValue, AccountSaleInvoice oldItem, AccountSaleInvoice newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    AccountSaleInvoiceMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getOrderNo() != null && !("".equals(newItem.getOrderNo()))){
                    AccountSaleInvoiceMiniController.orderNo = newItem.getOrderNo()+"";
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
    private void loadData(List<AccountSaleInvoice> list){
        generalOrder(list);
    }

    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)client_sure.getScene().getWindow();
        StageManager.CONTROLLER.remove("saleInvoiceControllerInvoiceMore");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 销项发票
        AccountSaleInvoiceController controller = (AccountSaleInvoiceController) StageManager.CONTROLLER.get("saleInvoiceControllerInvoiceMore");
        // 导入选中的产品--销货单
        if(controller != null && orderid != null && !"".equals(orderid)){
            AccountSaleInvoice order = iAccountSaleInvoiceService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicValue(order);
                List<AccountSaleInvoiceInfo> productList = iAccountSaleInvoiceInfoService.listAccountSaleInvoiceInfo(order.getId().toString());
                ObservableList<AccountSaleInvoiceInfoProperty> importPurchaseData = FXCollections.observableArrayList();
                int rows = 1;
                for (AccountSaleInvoiceInfo k : productList) {
                    importPurchaseData.add(new AccountSaleInvoiceInfoProperty(k.getId(),k.getOrderSoruce(), k.getOrderNo(), (rows++)+"", k.getCustomerNo(), k.getCustomerStr(), k.getProductNo(), k.getProductName(), k.getInvoceName(),k.getUnit(),k.getNum(),k.getPrice(),k.getMoney(),k.getRate(),k.getRateMoney(),k.getTax(),k.getRateNot(),k.getRemark()));
                }
                if(importPurchaseData != null){
                    controller.generalProductTab(importPurchaseData);
                }
                controller.setControllerUse();
            }
        }
        closeWin();
    }
}
