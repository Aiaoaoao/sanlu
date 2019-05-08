package com.yc.education.controller.account;

import com.github.pagehelper.PageInfo;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.sale.SaleGoods;

import com.yc.education.model.sale.SaleGoodsProduct;
import com.yc.education.model.sale.SalePurchaseOrder;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.sale.ISaleGoodsProductService;
import com.yc.education.service.sale.ISaleGoodsService;
import com.yc.education.util.AppConst;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Description 账款 -> 查询 -> 销货未开票
 * @Author BlueSky
 * @Date 2019-02-22 10:18
 */
@Controller
public class QueryAccountSaleNotTicketController extends BaseController implements Initializable {

    @Autowired ISaleGoodsService iSaleGoodsService;                 //销售单
    @Autowired ISaleGoodsProductService iSaleGoodsProductService;   //销售单详情
    @Autowired DataSettingService iDataSettingService;              //基础数据

    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    @FXML TableView tableview_order;            //销售单未开发票的单据
    @FXML TableColumn col_id;
    @FXML TableColumn col_no;
    @FXML TableColumn col_sale_no;              //销售单号
    @FXML TableColumn col_create_date;          //制单日期
    @FXML TableColumn col_customer_no;          //客户编号
    @FXML TableColumn col_customer_name;        //客户简称
    @FXML TableColumn col_payment;              //结算方式
    @FXML TableColumn col_state;                //单据类型
    @FXML TableColumn col_operation;            //操作

    @FXML TableView tableview_product;          //产品明细表
    @FXML TableColumn colp_id;
    @FXML TableColumn colp_no;
    @FXML TableColumn colp_product_no;          //产品编号
    @FXML TableColumn colp_product_name;        //产品名称
    @FXML TableColumn colp_num;                 //数量
    @FXML TableColumn colp_price;               //单价
    @FXML TableColumn colp_tax;                 //税别
    @FXML TableColumn colp_tax_not_contain;     //未税小计
    @FXML TableColumn colp_tax_contain;         //含税小计


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMenuValue(1);
    }

    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<SaleGoods> SaleGoodsList = iSaleGoodsService.listSaleInvoiceNotProcess(page, AppConst.ROWS);
        if(SaleGoodsList != null && SaleGoodsList.size() >0){
            PageInfo<SaleGoods> pageInfo = new PageInfo<>(SaleGoodsList);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            loadData(SaleGoodsList);
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
     * 加载销售单未开发票的单据
     * @param list
     */
    @SneakyThrows
    private void loadData(List<SaleGoods> list){
        if(list != null){
            int rows = 1;
            for (SaleGoods p : list) {
                p.setNo(rows++);
                if(p.getCreateDate()!=null){
                    p.setCreateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(p.getCreateDate()));
                }
                p.setPaymentStr("销货单");
            }
        }

        // Button
        Callback<TableColumn<SaleGoods, String>, TableCell<SaleGoods, String>> buttonFactory
                = new Callback<TableColumn<SaleGoods, String>, TableCell<SaleGoods, String>>() {
            @Override
            public TableCell call(final TableColumn<SaleGoods, String> param) {
                final TableCell<SaleGoods, String> cell = new TableCell<SaleGoods, String>() {

                    final Button btn1 = new Button("导出");

                    @Override
                    public void updateItem(String ite, boolean empty) {
                        super.updateItem(ite, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn1.setOnAction((ActionEvent t)
                                    -> {
                                int selectdIndex = getTableRow().getIndex();
                                System.out.println(selectdIndex);
                                alert_informationDialog("导出");
                            });
                            btn1.setMaxWidth(40);
                            btn1.setTextFill(Color.WHITE);
                            setGraphic(btn1);
                            setText(null);

                        }
                    }
                };
                cell.setStyle("-fx-alignment: CENTER;");
                return cell;
            }
        };
        col_operation.setCellFactory(buttonFactory);


        ObservableList<SaleGoods> data = FXCollections.observableArrayList(list);
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_no.setCellValueFactory(new PropertyValueFactory("no"));
        col_sale_no.setCellValueFactory(new PropertyValueFactory("saleNo"));
        col_create_date.setCellValueFactory(new PropertyValueFactory("createDateStr"));
        col_customer_no.setCellValueFactory(new PropertyValueFactory("customerNo"));
        col_customer_name.setCellValueFactory(new PropertyValueFactory("customerNoStr"));
        col_payment.setCellValueFactory(new PropertyValueFactory("payment"));
        col_state.setCellValueFactory(new PropertyValueFactory("paymentStr")); // 单据类型

        tableview_order.setItems(data);

        // 选择行
        tableview_order.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SaleGoods>() {
            @Override
            public void changed(ObservableValue<? extends SaleGoods> observableValue, SaleGoods oldItem, SaleGoods newItem) {
                if(newItem.getId() != null && !("".equals(newItem.getId())) && newItem.getCreateDate() != null && !("".equals(newItem.getCreateDate()))){

                    List<SaleGoodsProduct> listProduct = iSaleGoodsProductService.listSaleGoodsProduct(newItem.getId().toString());

                    if(newItem.getTax() != null && listProduct != null){
                        int rows = 1;
                        for (SaleGoodsProduct p : listProduct) {
                            p.setNo(rows++);
                            p.setTaxStr(newItem.getTax());
                            // 数量、单价不能为空或0
                            if(p.getNum() != null && p.getNum() != 0 && p.getPrice() != null && !("0.00").equals(p.getPrice().toString())){
                                BigDecimal total =  p.getPrice().multiply(new BigDecimal(p.getNum()));
                                if("外加".equals(newItem.getTax())){
                                    p.setTaxContainStr( total.add(total.multiply(new BigDecimal(AppConst.RATE))).setScale(2).toString());
                                }else{
                                    p.setTaxContainStr(total.toString());
                                }
                                p.setTaxNotContainStr(total.setScale(2).toString());
                            }
                        }
                    }
                    // 查询客户集合
                    final ObservableList<SaleGoodsProduct> data = FXCollections.observableArrayList(listProduct);
                    colp_id.setCellValueFactory(new PropertyValueFactory("id"));
                    colp_no.setCellValueFactory(new PropertyValueFactory("no"));
                    colp_product_no.setCellValueFactory(new PropertyValueFactory("productNo"));
                    colp_product_name.setCellValueFactory(new PropertyValueFactory("productName"));
                    colp_num.setCellValueFactory(new PropertyValueFactory("num"));
                    colp_price.setCellValueFactory(new PropertyValueFactory("price"));
                    colp_tax.setCellValueFactory(new PropertyValueFactory("taxStr"));
                    colp_tax_not_contain.setCellValueFactory(new PropertyValueFactory("taxNotContainStr"));
                    colp_tax_contain.setCellValueFactory(new PropertyValueFactory("taxContainStr"));

                    tableview_product.setItems(data);
                }
            }
        });
    }


}
