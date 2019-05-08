package com.yc.education.controller.basic;

import com.yc.education.controller.BaseController;
import com.yc.education.model.basic.CodingProperty;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.basic.SupplierBasic;
import com.yc.education.model.basic.TransportBasic;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.customer.CustomerBasic;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.basic.SupplierBasicService;
import com.yc.education.service.basic.TransportBasicService;
import com.yc.education.service.customer.ICustomerBasicService;
import com.yc.education.service.customer.ICustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @ClassName CodingBasicController
 * @Description TODO 资料编码维护   维护范围(供应商、运输商、客户、产品)
 * @Author QuZhangJing
 * @Date 2018-08-14 13:47
 * @Version 1.0
 */
@Controller
public class CodingBasicController extends BaseController implements Initializable {

    @Autowired
    private SupplierBasicService supplierBasicService; //供应商0
    @Autowired
    private ICustomerBasicService iCustomerBasicService;//客户基本信息
    @Autowired
    private ICustomerService iCustomerService; //客户信息
    @Autowired
    private TransportBasicService transportBasicService;

    @Autowired
    private ProductBasicService productBasicService; //产品3




    @FXML private ComboBox codeType;

    @FXML private TextField isNum; //源编码

    @FXML private TextField newIsNum; //新编码



    @FXML private TableView itemsView;

    @FXML private TableColumn ck;

    @FXML private TableColumn code;

    @FXML private TableColumn newCode;

    @FXML private TableColumn name;

    @FXML private TableColumn addDate;

    @FXML private TableColumn addPeople;

    @FXML private TableColumn stopDate;

    @FXML private TableColumn remarks;


    /**
     * 编码设置-TabelView数据绑定
     */
    private ObservableList<CodingProperty> codingProperties = FXCollections.observableArrayList();




    /**
     * 搜索
     */
    public void search(){

//        int type = codeType.getSelectionModel().getSelectedIndex();

        /*System.err.println("type:"+type);*/
//        if(type==0){
//            searchSupplier(isNum.getText());
//        }else if(type==1){
//
//        }else if(type==2){
//            searchCustomer(isNum.getText());
//        }else if(type==3){
//            searchProduct(isNum.getText());
//        }

        searchSupplier(isNum.getText());
    }


    public void searchSupplier(String isNum){



        itemsView.setEditable(false);

            /*ck.setCellFactory(CheckBoxTableCell.forTableColumn(ck));*/
            code.setCellFactory(TextFieldTableCell.forTableColumn());
            newCode.setCellFactory(TextFieldTableCell.forTableColumn());
            name.setCellFactory(TextFieldTableCell.forTableColumn());
            addDate.setCellFactory(TextFieldTableCell.forTableColumn());
            addPeople.setCellFactory(TextFieldTableCell.forTableColumn());
            stopDate.setCellFactory(TextFieldTableCell.forTableColumn());
            remarks.setCellFactory(TextFieldTableCell.forTableColumn());

            ck.setCellValueFactory(new PropertyValueFactory("id"));
            code.setCellValueFactory(new PropertyValueFactory("idnum"));
             newCode.setCellValueFactory(new PropertyValueFactory("newcode"));
            name.setCellValueFactory(new PropertyValueFactory("supname"));
            addDate.setCellValueFactory(new PropertyValueFactory("adddate"));
            addPeople.setCellValueFactory(new PropertyValueFactory("addpeople"));
            stopDate.setCellValueFactory(new PropertyValueFactory("stopdes"));
            remarks.setCellValueFactory(new PropertyValueFactory("remarks"));
        codingProperties.clear();

        switch (codeType.getSelectionModel().getSelectedIndex()){
            case 0:
                SupplierBasic supplierBasic = supplierBasicService.selectSupplierBasicByIsnum(isNum);

                if(supplierBasic != null) {

                    CodingProperty codingProperty = new CodingProperty(supplierBasic.getId(),supplierBasic.getIdnum(),"",supplierBasic.getSupname(),supplierBasic.getAdddate(),supplierBasic.getAddpeople(),supplierBasic.getStopdes(),supplierBasic.getRemarks());

                    codingProperties.add(codingProperty);
                }
                break;
            case 1:
                 TransportBasic transportBasic = transportBasicService.selectTransportBasicByIsnum(isNum);
                 if(transportBasic != null){
                    CodingProperty codingProperty = new CodingProperty(transportBasic.getId(),transportBasic.getIdnum(),"",transportBasic.getSupname(),transportBasic.getAdddate(),transportBasic.getAddpeople(),transportBasic.getStopdes(),transportBasic.getRemarks());
                     codingProperties.add(codingProperty);
                }
                break;
            case 2:
                Customer customer = iCustomerService.getCustomer(isNum);
                CustomerBasic customerBasic = iCustomerBasicService.getCustomerBasicByCustomerId(customer.getId());
                if(customer != null){
                    CodingProperty codingProperty = new CodingProperty(customer.getId(),customer.getCustomerCode(),"",customer.getName(),new SimpleDateFormat("yyyy-MM-dd").format(customerBasic.getAddtime()),customerBasic.getArchivist(),customerBasic.getStopUseStr(),customerBasic.getCreditLineRemark());
                    codingProperties.add(codingProperty);
                }
                break;
            case 3:
                ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(isNum);
                if(productBasic != null) {
                    CodingProperty codingProperty = new CodingProperty(productBasic.getId(),productBasic.getIsnum(),"",productBasic.getProname(),productBasic.getAdddate(),productBasic.getAddpeople(),productBasic.getStopdes(),productBasic.getRemarks());
                    codingProperties.add(codingProperty);
                }
                break;
                default:
                    break;

        }



        itemsView.setItems(codingProperties);


    }


    /**
     * 清除
     */
    public void clear(){



    }


    //替换
    public void replace(){
        operation(1);
    }

    //提交
    public void submit(){
        operation(2);
    }



    /**
     * 替换
     */
    public void operation(int types){

       String nisnum = newIsNum.getText();

       if(nisnum != null && !"".equals(nisnum)){

           int type = codeType.getSelectionModel().getSelectedIndex();


           switch (type){

               case 0:
                   SupplierBasic supplierBasic = supplierBasicService.selectSupplierBasicByIsnum(nisnum);

                   if(supplierBasic == null){

                       codingProperties.forEach(codingProperty -> {

                           codingProperty.setNewcode(nisnum);

                           if(types == 2){
                               SupplierBasic supplierBasic1 = new SupplierBasic(codingProperty.getId(),nisnum);
                               supplierBasicService.updateNotNull(supplierBasic1);
                               alert_informationDialog("替换成功!");
                           }


                       });
                   }else {
                       alert_informationDialog("编码已存在!");
                   }
                   break;
               case 1:
                   TransportBasic transportBasic = transportBasicService.selectTransportBasicByIsnum(nisnum);

                   if(transportBasic == null){

                       codingProperties.forEach(codingProperty -> {

                           codingProperty.setNewcode(nisnum);

                           if(types == 2){
                               TransportBasic transportBasic1 = new TransportBasic(codingProperty.getId(),nisnum);
                               transportBasicService.updateNotNull(transportBasic1);
                               alert_informationDialog("替换成功!");
                           }

                       });
                   }else {
                       alert_informationDialog("编码已存在!");
                   }
                   break;
               case 2:

                   Customer customer = iCustomerService.getCustomer(nisnum);

                   if(customer == null){

                       codingProperties.forEach(codingProperty -> {

                           codingProperty.setNewcode(nisnum);

                        if(types == 2){
                            Customer customer1 = new Customer();
                            customer1.setId(codingProperty.getId());
                            customer1.setCustomerCode(nisnum);
                            iCustomerService.updateNotNull(customer1);
                            alert_informationDialog("替换成功!");
                        }

                       });
                   }else {
                       alert_informationDialog("编码已存在!");
                   }
                   break;
               case 3:
                   ProductBasic productBasic = productBasicService.selectProductBasicByIsnum(nisnum);

                   if(productBasic == null){

                       codingProperties.forEach(codingProperty -> {

                           codingProperty.setNewcode(nisnum);

                        if(types == 2){
                            ProductBasic productBasic1 = new ProductBasic(codingProperty.getId(),nisnum);
                            productBasicService.updateNotNull(productBasic1);
                            alert_informationDialog("替换成功!");
                        }

                       });
                   }else {
                       alert_informationDialog("编码已存在!");
                   }
                   break;
                   default:
                       break;

           }

       }


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String newStr = location.toString();

        int index = newStr.indexOf("coding_basic");

        if(index != -1){

            codeType.getItems().addAll(
                    "供应商","运输商","客户","产品"
            );
            codeType.getSelectionModel().select(0);

        }


    }





}
