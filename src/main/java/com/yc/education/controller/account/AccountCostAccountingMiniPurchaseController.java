package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.account.AccountReceivable;
import com.yc.education.model.stock.PurchaseStock;
import com.yc.education.service.stock.PurchaseStockProductService;
import com.yc.education.service.stock.PurchaseStockService;
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
 * 成本核算--采购入库单单据
 */
@Controller
public class AccountCostAccountingMiniPurchaseController extends BaseController implements Initializable {

    @Autowired private PurchaseStockService iPurchaseStockService;      //采购入库单



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
    @FXML TableColumn storage_no;       // 入库单号
    @FXML TableColumn install_no;     // 入库仓库编号
    @FXML TableColumn warehouse_name;   // 入库仓库名称
    @FXML TableColumn supplier_name;    // 供应商名称
    @FXML TableColumn create_date;      // 制单日期



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
        List<PurchaseStock> stockList = iPurchaseStockService.listPurchaseStock(text,page, rows);
        if(stockList != null && stockList.size() >0){
            PageInfo<PurchaseStock> pageInfo = new PageInfo<>(stockList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(stockList);
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
    private void loadData(List<PurchaseStock> list){
        if(list != null){
            list.forEach(p->{
                try {
                    if(p.getCreatedate() != null){
                        p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreatedate()));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }

        final ObservableList<PurchaseStock> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        storage_no.setCellValueFactory(new PropertyValueFactory("stockorder"));
        install_no.setCellValueFactory(new PropertyValueFactory("boxnum"));
        warehouse_name.setCellValueFactory(new PropertyValueFactory("depotname"));
        supplier_name.setCellValueFactory(new PropertyValueFactory("suppliername"));
        create_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseStock>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseStock> observableValue, PurchaseStock oldItem, PurchaseStock newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    AccountCostAccountingMiniPurchaseController.orderid = newItem.getId()+"";
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
        StageManager.CONTROLLER.remove("AccountCoastAccountingPurchaseController");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 报价单
        AccountCostAccountingController coast = (AccountCostAccountingController) StageManager.CONTROLLER.get("AccountCoastAccountingPurchaseController");
        if(coast != null && orderid != null && !"".equals(orderid)){
            PurchaseStock purchaseStock = iPurchaseStockService.selectByKey(Long.valueOf(orderid));
            if(purchaseStock != null){
                // 赋值
                coast.setBasicPurchaseValue(purchaseStock);
            }
        }
        closeWin();
    }
}
