package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.controller.account.QueryAccountSaleCostController;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.sale.SaleGoods;
import com.yc.education.model.sale.SaleQuotation;
import com.yc.education.service.customer.ICustomerBasicService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.sale.ISaleGoodsService;
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
public class SaleGoodsQueryMiniController extends BaseController implements Initializable {

    @Autowired ISaleGoodsService iSaleGoodsService; // 销货单
    @Autowired ICustomerService iCustomerService; // 客户
    @Autowired ICustomerBasicService iCustomerBasicService; // 客户基本信息


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML Button client_sure;

    @FXML TableView tableView;

    @FXML CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField queryText;  // 查询内容

    @FXML TableColumn id;
    @FXML TableColumn order_no;
    @FXML TableColumn order_date;
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_name;
    @FXML TableColumn contact;
    @FXML TableColumn business_leader;
    @FXML TableColumn tax;
    @FXML TableColumn phone;
    @FXML TableColumn fax;
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
     * @Description 模糊查询
     * @Author BlueSky
     * @Date 12:00 2019/4/11
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
        String text = queryText.getText();
        List<SaleGoods> SaleGoodsList = iSaleGoodsService.listSaleGoodsByPage(text==null?"":text,"",page, rows);
        if(SaleGoodsList != null && SaleGoodsList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(SaleGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(SaleGoodsList);
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
    private void loadData(List<SaleGoods> list){
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getOrderAudit() != null && p.getOrderAudit()){
                    p.setAuditStatus("已审核");
                }else{
                    p.setAuditStatus("未审核");
                }
            });
        }

        // 查询客户集合
        final ObservableList<SaleGoods> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        contact.setCellValueFactory(new PropertyValueFactory("contact"));
        business_leader.setCellValueFactory(new PropertyValueFactory("businessLeader"));
        tax.setCellValueFactory(new PropertyValueFactory("tax"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        fax.setCellValueFactory(new PropertyValueFactory("fax"));
        status.setCellValueFactory(new PropertyValueFactory("auditStatus"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleGoods> observableValue, SaleGoods oldItem, SaleGoods newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    SaleGoodsQueryMiniController.orderid = newItem.getId()+"";
                }
                if(newItem.getSaleNo() != null && !("".equals(newItem.getSaleNo()))){
                    SaleGoodsQueryMiniController.orderNo = newItem.getSaleNo()+"";
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
        StageManager.CONTROLLER.remove("SaleGoodsControllerNo");
        StageManager.CONTROLLER.remove("SaleDeliveryTrackControllerMiniBen");
        StageManager.CONTROLLER.remove("SaleDeliveryTrackControllerMiniEnd");
        StageManager.CONTROLLER.remove("AccountSaleCostControllerSaleGoods");
        StageManager.CONTROLLER.remove("AccountSaleCostControllerSaleGoodsEnd");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 销货单
        SaleGoodsController controller = (SaleGoodsController) StageManager.CONTROLLER.get("SaleGoodsControllerNo");
        if(controller != null && orderid != null && !"".equals(orderid)){
            SaleGoods order = iSaleGoodsService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicVal(order);
                Customer customer = iCustomerService.getCustomer(order.getCustomerNo());
                if(customer != null){
                    controller.receivable.setText(customer.getUseableMoney()==null?"0.00":customer.getUseableMoney().toString());
                }
            }
        }
        // 销售发货跟踪
        SaleDeliveryTrackController saleDeliveryTrackControllerMiniBen = (SaleDeliveryTrackController) StageManager.CONTROLLER.get("SaleDeliveryTrackControllerMiniBen");
        if(saleDeliveryTrackControllerMiniBen != null && orderNo != null && !"".equals(orderNo)){
            saleDeliveryTrackControllerMiniBen.sale_no.setText(orderNo);
        }
        // 销售发货跟踪
        SaleDeliveryTrackController saleDeliveryTrackControllerMiniEnd = (SaleDeliveryTrackController) StageManager.CONTROLLER.get("SaleDeliveryTrackControllerMiniEnd");
        if(saleDeliveryTrackControllerMiniEnd != null && orderNo != null && !"".equals(orderNo)){
            saleDeliveryTrackControllerMiniEnd.sale_no_end.setText(orderNo);
        }
        // 账款 -- 销项成本明细
        QueryAccountSaleCostController queryAccountSaleCostControllerSaleGoods = (QueryAccountSaleCostController) StageManager.CONTROLLER.get("AccountSaleCostControllerSaleGoods");
        if(queryAccountSaleCostControllerSaleGoods != null && orderNo != null && !"".equals(orderNo)){
            queryAccountSaleCostControllerSaleGoods.sale_no.setText(orderNo);
        }
        // 账款 -- 销项成本明细结束
        QueryAccountSaleCostController queryAccountSaleCostControllerSaleGoodsEnd = (QueryAccountSaleCostController) StageManager.CONTROLLER.get("AccountSaleCostControllerSaleGoodsEnd");
        if(queryAccountSaleCostControllerSaleGoodsEnd != null && orderNo != null && !"".equals(orderNo)){
            queryAccountSaleCostControllerSaleGoodsEnd.sale_no_end.setText(orderNo);
        }


        closeWin();
    }
}
