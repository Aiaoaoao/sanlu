package com.yc.education.controller.sale;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.sale.SaleReturnGoods;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.sale.ISaleReturnGoodsService;
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
 * @Description 销售 - 业务单据 - 销售退货单 小窗口
 * @Author BlueSky
 * @Date 14:14 2019/4/11
 **/
@Controller
public class SaleReturnQueryMiniController extends BaseController implements Initializable {

    @Autowired ISaleReturnGoodsService iSaleReturnGoodsService;


    @Autowired DataSettingService iDataSettingService;


    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页


    @FXML CheckBox che_recently;
    @FXML TextField num;
    @FXML TextField text_query;

    @FXML Button client_sure;

    @FXML TableView tableView;

    @FXML TableColumn id;
    @FXML TableColumn order_no;
    @FXML TableColumn order_date;
    @FXML TableColumn customer_no;
    @FXML TableColumn customer_name;
    @FXML TableColumn business_leader;
    @FXML TableColumn tax;
    @FXML TableColumn reason;
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
     * @Date 14:24 2019/4/11
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
        List<SaleReturnGoods> SaleReturnGoodsList = iSaleReturnGoodsService.listSaleReturnGoodsByPage(text,"",page, rows);
        if(SaleReturnGoodsList != null && SaleReturnGoodsList.size() >0){
            PageInfo<SaleReturnGoods> pageInfo = new PageInfo<>(SaleReturnGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(SaleReturnGoodsList);
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
    private void loadData( List<SaleReturnGoods> list){
        List<DataSetting> settings = iDataSettingService.findDataSetting(19L);
        if(list != null){
            list.forEach(p->{
                p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                if(p.getReturnReason() != null && !"".equals(p.getReturnReason()) && settings != null){
                    settings.forEach(k->{
                        if(k.getSort().equals(p.getReturnReason())){
                            p.setReasonStr(k.getTitle());
                            return;
                        }
                    });
                }
            });
        }

        // 查询客户集合
        final ObservableList<SaleReturnGoods> data = FXCollections.observableArrayList(list);
        id.setCellValueFactory(new PropertyValueFactory("id"));
        order_no.setCellValueFactory(new PropertyValueFactory("pinbackNo"));
        order_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        business_leader.setCellValueFactory(new PropertyValueFactory("businessLeaderStr"));
        tax.setCellValueFactory(new PropertyValueFactory("tax"));
        reason.setCellValueFactory(new PropertyValueFactory("returnReason"));
        remark.setCellValueFactory(new PropertyValueFactory("remark"));

        tableView.setItems(data);

        // 选择行 保存数据
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleReturnGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleReturnGoods> observableValue, SaleReturnGoods oldItem, SaleReturnGoods newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId()))){
                    SaleReturnQueryMiniController.orderid = newItem.getId()+"";
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
        StageManager.CONTROLLER.remove("SaleReturnControllerNo");
        stage.close();
    }

    //确定并关闭当前窗体
    @FXML
    public void closeSureWin(){
        // 报价单
        SaleReturnController controller = (SaleReturnController) StageManager.CONTROLLER.get("SaleReturnControllerNo");
        if(controller != null && orderid != null && !"".equals(orderid)){
            SaleReturnGoods order = iSaleReturnGoodsService.selectByKey(Long.valueOf(orderid));
            if(order != null){
                controller.setBasicVal(order);
            }
        }
        closeWin();
    }
}
