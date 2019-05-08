package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountCoastAccounting;
import com.yc.education.model.account.AccountReceivable;
import com.yc.education.service.account.IAccountCoastAccountingService;
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
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 成本核算
 */
@Controller
public class AccountCostAccountingMiniController extends BaseController implements Initializable {

    @Autowired
    IAccountCoastAccountingService iAccountCoastAccountingService;

    @FXML
    VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML
    CheckBox che_recently;
    @FXML
    TextField num;
    @FXML TextField text_query;
    @FXML
    Button client_sure;

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn storage_no;
    @FXML TableColumn install_no;
    @FXML TableColumn audit;
    @FXML TableColumn audit_date;
    @FXML TableColumn remark;



    // 订单编号
    private static String  orderid = "";


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
        List<AccountCoastAccounting> coastAccountingList = iAccountCoastAccountingService.listAccountCoastAccounting(text,page, rows);
        if(coastAccountingList != null && coastAccountingList.size() >0){
            PageInfo<AccountCoastAccounting> pageInfo = new PageInfo<>(coastAccountingList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(coastAccountingList);
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
     * 初始化加载数据
     */
    @SneakyThrows
    private void loadData(List<AccountCoastAccounting> list){
        if(list != null){
            list.forEach(p->{
                try {
                    if(p.getAuditDate() != null){
                        p.setAuditDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getAuditDate()));
                    }else{
                        p.setAudit("暂未审核");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        // 查询客户集合
        final ObservableList<AccountCoastAccounting> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        storage_no.setCellValueFactory(new PropertyValueFactory("storageInNo"));
        install_no.setCellValueFactory(new PropertyValueFactory("installNo"));
        audit.setCellValueFactory(new PropertyValueFactory("audit"));
        audit_date.setCellValueFactory(new PropertyValueFactory("auditDateStr"));
        remark.setCellValueFactory(new PropertyValueFactory("storageInRemark"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AccountCoastAccounting>() {
            @Override
            public void changed(ObservableValue<? extends AccountCoastAccounting> observableValue, AccountCoastAccounting oldItem, AccountCoastAccounting newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    AccountCostAccountingMiniController.orderid = newItem.getId()+"";
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
        StageManager.CONTROLLER.remove("AccountCostAccountingController");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 报价单
        AccountCostAccountingController accountCostAccountingController = (AccountCostAccountingController) StageManager.CONTROLLER.get("AccountCostAccountingController");
        if(accountCostAccountingController != null && orderid != null && !"".equals(orderid)){
            AccountCoastAccounting accountCoastAccounting = iAccountCoastAccountingService.selectByKey(Long.valueOf(orderid));
            if(accountCoastAccounting != null){
                accountCostAccountingController.clearValue();
                accountCostAccountingController.setBasicValue(accountCoastAccounting);
            }
        }
        closeWin();
    }
}
