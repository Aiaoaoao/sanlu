package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.account.AccountInputInvoiceController;
import com.yc.education.controller.account.AccountPayableController;
import com.yc.education.controller.account.AccountPrepaymentController;
import com.yc.education.controller.purchase.PurchaseInvoiceController;
import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.service.basic.SupplierBasicService;
import com.yc.education.util.AppConst;
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
 * @Description 供应商查询
 * @Author BlueSky
 * @Date 2019-01-29 16:03
 */
@Controller
public class SupplierMiniController extends BaseController implements Initializable {


    @Autowired
    private SupplierBasicService iSupplierBasicService;


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML CheckBox che_recently;    //显示最近
    @FXML CheckBox che_stop_compay; //显示暂停来往公司
    @FXML TextField num;       //最近多少笔

    //查询更多供应商
    @FXML private TableView tableView_supplier; //供应商查询
    @FXML private TableColumn supid; //id
    @FXML private TableColumn findsupplierid; //供应商编号
    @FXML private TableColumn findsuppliername; //

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        int rows = pageRows(che_recently,num);
        int types = 0;
        if(che_stop_compay.isSelected()){
            types = 1;
        }
        List<SupplierBasic> list = iSupplierBasicService.selectSupplierBasicNotSotp(types,page, rows);
        if(list != null && list.size() >0){
            PageInfo<SupplierBasic> pageInfo = new PageInfo<>(list);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadMoreSupplier(list);
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
    public void loadMoreSupplier(List<SupplierBasic> supplierBasics){


        ObservableList<SupplierBasic> list =FXCollections.observableArrayList();

        tableView_supplier.setEditable(true);

        findsupplierid.setCellValueFactory(new PropertyValueFactory("idnum"));
        findsuppliername.setCellValueFactory(new PropertyValueFactory("supdes"));

        for (SupplierBasic supplierBasics1:supplierBasics) {

            list.add(supplierBasics1);

        }

        tableView_supplier.setItems(list); //tableview添加list

        tableView_supplier.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierBasic>() {
            @Override
            public void changed(ObservableValue<? extends SupplierBasic> observableValue, SupplierBasic oldItem, SupplierBasic newItem) {
                if(newItem.getIdnum() != null && !("".equals(newItem.getIdnum()))){
                    tableView_supplier.setUserData(newItem.getId());
                }
            }
        });

        tableView_supplier.setOnMouseClicked((MouseEvent t) -> {
            if (t.getClickCount() == 2) {
                closeSureWin();
            }
        });


    }

    public void closeSureWin(){
        long id =(long)tableView_supplier.getUserData();
        SupplierBasic supplierBasic =  iSupplierBasicService.selectByKey(id);

        if(supplierBasic != null){

            // 账款 - 预付账款
            AccountPrepaymentController accountPrepaymentController = (AccountPrepaymentController)StageManager.CONTROLLER.get("AccountPrepaymentController");
            if(accountPrepaymentController != null){
                accountPrepaymentController.customer_no.setText(supplierBasic.getIdnum());
                accountPrepaymentController.customer_no_str.setText(supplierBasic.getSupname());
            }
            // 账款 - 进项发票
            AccountInputInvoiceController accountInputInvoiceController = (AccountInputInvoiceController)StageManager.CONTROLLER.get("AccountInputInvoiceControllerSupplier");
            if(accountInputInvoiceController != null){
                accountInputInvoiceController.supplier_no.setText(supplierBasic.getIdnum());
                accountInputInvoiceController.supplier_str.setText(supplierBasic.getSupname());
            }
            // 账款 - 应付账款冲账
            AccountPayableController accountPayableControllerSupplier = (AccountPayableController)StageManager.CONTROLLER.get("AccountPayableControllerSupplier");
            if(accountPayableControllerSupplier != null){
                accountPayableControllerSupplier.supplier_no.setText(supplierBasic.getIdnum());
                accountPayableControllerSupplier.supplier_no_str.setText(supplierBasic.getSupname());
            }
            // 账款 - 应付账款 Ben
            PurchaseInvoiceController purchaseInvoiceControllerSupplierBen = (PurchaseInvoiceController)StageManager.CONTROLLER.get("PurchaseInvoiceControllerSupplierBen");
            if(purchaseInvoiceControllerSupplierBen != null){
                purchaseInvoiceControllerSupplierBen.supplierOrderStart.setText(supplierBasic.getIdnum());
            }
            // 账款 - 应付账款 End
            PurchaseInvoiceController purchaseInvoiceControllerSupplierEnd = (PurchaseInvoiceController)StageManager.CONTROLLER.get("PurchaseInvoiceControllerSupplierEnd");
            if(purchaseInvoiceControllerSupplierEnd != null){
                purchaseInvoiceControllerSupplierEnd.supplierOrderEnd.setText(supplierBasic.getIdnum());
            }

        }

        closeWin();
    }

    //关闭当前窗体
    @FXML
    public void closeWin(){
        Stage stage=(Stage)tableView_supplier.getScene().getWindow();
        StageManager.CONTROLLER.remove("AccountPrepaymentController");
        StageManager.CONTROLLER.remove("AccountInputInvoiceControllerSupplier");
        StageManager.CONTROLLER.remove("AccountPayableControllerSupplier");
        StageManager.CONTROLLER.remove("PurchaseInvoiceControllerSupplierBen");
        StageManager.CONTROLLER.remove("PurchaseInvoiceControllerSupplierEnd");

        stage.close();
    }


}
