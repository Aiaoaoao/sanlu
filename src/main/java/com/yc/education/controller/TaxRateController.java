package com.yc.education.controller;

import com.yc.education.model.TaxRate;
import com.yc.education.service.TaxRateService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @ClassName TaxRateController
 * @Description TODO  税率设置
 * @Author QuZhangJing
 * @Date 2019/3/28 20:52
 * @Version 1.0
        */
@Controller
public class TaxRateController extends  BaseController implements Initializable {


    @Autowired
    private TaxRateService taxRateService;

    @FXML
    private DatePicker inStartTime;

    @FXML
    private DatePicker outStartTime;

    @FXML
    private TextField inEndTime;

    @FXML
    private TextField outEndTime;

    @FXML
    private TextField inTax;

    @FXML
    private TextField outTax;

    @FXML
    private Button inUpdateBtn;

    @FXML
    private Button outUpdateBtn;

    @FXML
    private Button inSaveBtn;

    @FXML
    private Button outSaveBtn;


    final  private boolean flag = true;


    public void disSetting(int type,boolean bool){
        switch (type){
            case 1:
                inStartTime.setDisable(bool);
                inEndTime.setDisable(bool);
                inTax.setDisable(bool);
                inUpdateBtn.setDisable(!bool);
                inSaveBtn.setDisable(bool);
                break;
            case 2:
                outStartTime.setDisable(bool);
                outEndTime.setDisable(bool);
                outTax.setDisable(bool);
                outUpdateBtn.setDisable(!bool);
                outSaveBtn.setDisable(bool);
                break;
                default:break;
        }

    }


    /**
     * 进项修改方法
     */
    public void inUpdateMethod(){

        disSetting(1,!flag);

    }


    /**
     * 进项保存方法
     */
    public void inSaveMethod(){

         Date startTime =  LocalDateToDate(inStartTime .getValue());

         String endTime =  inEndTime.getText();

         String taxs = Double.parseDouble(inTax.getText()) /100 + "";

         if(inStartTime .getValue() == null|| "".equals(endTime) || "".equals(taxs)){
                alert_informationDialog("参数不能为空!");
         }

        Date endDate = null;

         if(!"至今".equals(endTime)){
                 endDate = StringToDate(endTime);
         }

         TaxRate taxRate =  taxRateService.selectTaxRatesByStartTimeAndEndTime(startTime,endDate,1);


         if(taxRate == null){

             //查询税率表最新数据  如何结束时间为空修改为当前时间

             taxRate = taxRateService.selectTaxRatesLimit(1);

             if(taxRate.getEndtime() == null){
                 taxRate.setEndtime(new Date());
             }

             taxRateService.updateNotNull(taxRate);

             taxRate = new TaxRate();

             taxRate.setStarttime(startTime);

             taxRate.setEndtime(StringToDate(endTime));


             taxRate.setNums(Double.parseDouble(taxs));

             taxRate.setTypes(1);

             taxRate.setAddtime(new Date());

             if(taxRateService.save(taxRate) >0){
                 alert_informationDialog("保存成功!");
             }else{
                 alert_informationDialog("保存失败!");
             }

             disSetting(1,flag);

         }else {
             alert_informationDialog("请确保时间不重复!");
         }



    }



    /**
     * 销项修改方法
     */
    public void outUpdateMethod(){

        disSetting(2,!flag);

    }


    /**
     * 销项保存方法
     */
    public void outSaveMethod(){

        Date startTime =  LocalDateToDate(outStartTime .getValue());

        String endTime =  outEndTime.getText();

        String taxs =  Double.parseDouble(outTax.getText()) /100 + "";

        if(outStartTime .getValue() == null|| "".equals(endTime) || "".equals(taxs)){
            alert_informationDialog("参数不能为空!");
        }

        Date endDate = null;

        if(!"至今".equals(endTime)){
                endDate = StringToDate(endTime);
        }

        TaxRate taxRate =  taxRateService.selectTaxRatesByStartTimeAndEndTime(startTime,endDate,2);


        if(taxRate == null){

            //查询税率表最新数据  如何结束时间为空修改为当前时间

            taxRate = taxRateService.selectTaxRatesLimit(2);

            if(taxRate.getEndtime() == null){
                taxRate.setEndtime(new Date());
            }

            taxRateService.updateNotNull(taxRate);

            taxRate = new TaxRate();

            taxRate.setStarttime(startTime);

            taxRate.setEndtime(StringToDate(endTime));

            taxRate.setNums(Double.parseDouble(taxs));

            taxRate.setTypes(2);

            taxRate.setAddtime(new Date());

            if(taxRateService.save(taxRate) >0){
                alert_informationDialog("保存成功!");
            }else{
                alert_informationDialog("保存失败!");
            }

            disSetting(2,flag);

        }else {
            alert_informationDialog("请确保时间不重复!");
        }




    }



    public void loadData(){


        disSetting(1,flag);
        disSetting(2,flag);

        TaxRate taxRateIn = taxRateService.selectTaxRatesLimit(1);

        inStartTime.setValue(dateToLocalDate(taxRateIn.getStarttime()));

        if("".equals(taxRateIn.getEndtime()) || taxRateIn.getEndtime() == null){
            inEndTime.setText("至今");
        }else {
            inEndTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(taxRateIn.getEndtime()));
        }

        inTax.setText(taxRateIn.getNums() * 100 +"");

        TaxRate taxRateOut = taxRateService.selectTaxRatesLimit(2);

        outStartTime.setValue(dateToLocalDate(taxRateOut.getStarttime()));

        if("".equals(taxRateOut.getEndtime()) || taxRateOut.getEndtime() == null){
            outEndTime.setText("至今");
        }else{

            outEndTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(taxRateOut.getEndtime()));
        }

        outTax.setText((taxRateOut.getNums() * 100 )+"");


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * 初始化数据
         */
        loadData();

    }







}
