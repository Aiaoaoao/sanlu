package com.yc.education.controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.model.Citys;
import com.yc.education.model.DataSetting;
import com.yc.education.model.DepotProperty;
import com.yc.education.model.Relation;
import com.yc.education.model.account.AccountReceipt;
import com.yc.education.model.account.AccountSaleInvoice;
import com.yc.education.model.basic.DepartmentBasic;
import com.yc.education.model.basic.DepotBasic;
import com.yc.education.model.basic.EmployeeBasic;
import com.yc.education.model.basic.ProductBasic;
import com.yc.education.model.customer.Customer;
import com.yc.education.model.customer.CustomerBasic;
import com.yc.education.model.purchase.PurchaseProduct;
import com.yc.education.service.*;
import com.yc.education.service.account.IAccountReceiptService;
import com.yc.education.service.account.IAccountSaleInvoiceService;
import com.yc.education.service.basic.DepartmentBasicService;
import com.yc.education.service.basic.DepotBasicService;
import com.yc.education.service.basic.EmployeeBasicService;
import com.yc.education.service.basic.ProductBasicService;
import com.yc.education.service.customer.ICustomerBasicService;
import com.yc.education.service.customer.ICustomerService;
import com.yc.education.service.purchase.PurchaseProductService;
import com.yc.education.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.persistence.Entity;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName BaseController
 * @Description TODO 基础控制类
 * @Author QuZhangJing
 * @Date 2018-08-15 11:04
 * @Version 1.0
 */
@Controller
public class BaseController {


    @Autowired
    private DataSettingService dataSettingService;
    @Autowired
    private EmployeeBasicService employeeBasicService;
    @Autowired
    private CitysService citysService;//省市区
    @Autowired
    ICustomerService iCustomerService;  //客户
    @Autowired
    ICustomerBasicService iCustomerBasicService;  //客户基本信息
    @Autowired
    private DepartmentBasicService departmentBasicService;
    @Autowired
    private PermissionsService permissionsService; //权限管理
    @Autowired
    private TaxRateService taxRateService;//税率
    @Autowired
    ProductBasicService iProductBasicService; //产品基本资料
    @Autowired
    private DepotBasicService depotBasicService;
    @Autowired
    private static PurchaseProductService purchaseProductService;
    @Autowired IAccountSaleInvoiceService iAccountSaleInvoiceService;   //销项发票
    @Autowired IAccountReceiptService iAccountReceiptService;   //收款单


    private Stage stage = new Stage();


    private static SpringFxmlLoader loader = new SpringFxmlLoader();


    private final String defaultColor="-fx-background-color:#EFF3F7;";  //默认按钮颜色

    private final String checkedColor=" -fx-background-color:#169252;"; //选中颜色

    private final String checkedFontColor=" -fx-text-fill:#FFF;"; //选中字体颜色

    private final String defaultFontColor=" -fx-text-fill: #8CA0B3;"; //默认字体颜色


    /**
     * 单据关联容器
     */
    Relation relation = new Relation();


    // // T指代表实体
    // private T entity;

    // 提示：子类应该在自己的构造方法中自动去实现各个表格的初始化，而不是显示调用
    public BaseController() {

    }



    /**
     * 绑定多个表格的列
     *
     * @param
     */
    public <T> void bindCellValueByTable(T entity, TableView<T> table) {
        try {
            List<TableColumn<T, ?>> columns = table.getColumns();
            bindCellValues(entity, columns);
        } catch (Exception e) {
            throw new RuntimeException("小林：绑定列值失败");
        }
    }

    /**
     * 绑定数据域
     *
     * @param colums TableColumn 可变参数
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends Entity> void bindCellValue(T entity, TableColumn<T, ?>... colums) {
        for (TableColumn column : colums) {
            bindSingleCellValues(entity, column);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> void bindCellValues(T entity, List<TableColumn<T, ?>> colums) {
        for (TableColumn column : colums) {
            bindSingleCellValues(entity, column);
        }
    }

    public <T> void bindSingleCellValues(T entity, TableColumn<T, String> column) {
        String fxId = column.getId();
        column.setCellValueFactory(new PropertyValueFactory<T, String>(fxId));
        column.setStyle("-fx-alignment: CENTER;");
        column.setSortable(false);// 禁止排序
        if (entity != null) {
            column.setCellFactory(getColorCellFactory(entity));
        }
    }


    public <T> Callback<TableColumn<T, String>, TableCell<T, String>> getColorCellFactory(
            T t) {
        return new Callback<TableColumn<T, String>, TableCell<T, String>>() {
            public TableCell<T, String> call(TableColumn<T, String> param) {
                TableCell<T, String> cell = new TableCell<T, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        this.setTextFill(null);
                        if (!isEmpty() && item != null) {
                            if (item.contains("-")) {
                                this.setTextFill(Color.RED);
                            } else {
                                this.setTextFill(Color.BLACK);
                            }
                            setText(item);
                        }
                    }
                };
                cell.setEditable(false);// 不让其可编辑
                return cell;
            }
        };
    }



    /**
     * 父类获取子类Class
     * @author linzt
     */
    public Class<?> getSubClass(){ return null;}

    /**
     * @return java.lang.String
     * @Author BlueSky
     * @Description //TODO 返回四位随机数
     * @Date 10:48 2018/8/20
     * @Param []
     **/
    protected String getRandomone() {
        //存放产生的随机数
        String sms = "";
        //生成四位数的随机数
        StringBuffer buf = new StringBuffer();
        Random rand = new Random();
        String[] str = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < 4; i++) {
            buf.append(str[rand.nextInt(10)]);
        }
        sms = buf.toString();
        System.err.println(sms);
        return sms;
    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO 弹出一个信息对话框
     * @Date 13:56 2018/8/20
     * @Param [p_header, p_message]
     **/
    public void alert_informationDialog(String p_message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("信息");
        alert.setHeaderText(null);
        alert.setContentText(p_message);
        alert.show();
    }

    public void alertProcessResult(int rows){
        if(rows>0){
            alert_informationDialog("操作成功！");
        }else{
            alert_informationDialog("操作失败");
        }
    }


    public boolean f_alert_confirmDialog(String p_header,String p_message){
//        按钮部分可以使用预设的也可以像这样自己 new 一个
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,p_message,new ButtonType("取消", ButtonBar.ButtonData.NO),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
//        设置窗口的标题
        alert.setTitle("确认");
        alert.setHeaderText(p_header);
//        设置对话框的 icon 图标，参数是主窗口的 stage
//        _alert.initOwner(d_stage);
//        showAndWait() 将在对话框消失以前不会执行之后的代码
        Optional<ButtonType> _buttonType = alert.showAndWait();
//        根据点击结果返回
        if(_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)){
            return true;
        }
        else {
            return false;
        }
    }



    /**
     * 资料查询根据type查询
     * @param type
     * @param comboBox 控件
     * @param index 选中下标
     * type   说明
     * 1	发货状态
     * 2	业务地区
     * 3	货运方式
     * 4	开户银行
     * 5	基本单位
     * 6	产品大类
     * 7	货币单位
     * 8	产品性质
     * 9	产品来源
     * 10	客户类别
     * 11	行业
     * 12	地区
     * 13	客户来源
     * 14	客户等级
     * 15	部门
     * 16	职务
     * 17	出生地
     * 18	民族
     * 19	退货原因
     * 20	收款方式
     * 21	电话
     * 22	传真
     * 23	备注及说明
     * 24	发货状态
     * 25	销退备注
     * 26	收款备注
     * 27	所属公司
     * 28	国家
     * 29   出货仓库 作废
     * 30   运输方式
     * 31   所属区域
     * 32   楼层
     * 33   楼层
     * 34   地址类型
     * 35   发票种类   setComboBox(6L,protype,--pte);
     */
    public void setComboBox(long type,ComboBox comboBox,int index){

        ObservableList<String> comboBoxList = FXCollections.observableArrayList();

        List<DataSetting> comboBoxType = dataSettingService.findDataSetting(type);

        for (DataSetting combox:comboBoxType) {
            comboBoxList.add(combox.getTitle());
        }

        comboBox.getItems().setAll(comboBoxList);

        comboBox.getSelectionModel().select(index);

    }

    public void setComboBox(long type,ComboBox comboBox,String  title){

//        ObservableList<String> comboBoxList = FXCollections.observableArrayList();

        List<DataSetting> comboBoxType = dataSettingService.findDataSetting(type);

        for (int i=0,len=comboBoxType.size();i<len;i++) {
            comboBox.getItems().add(comboBoxType.get(i).getTitle());
            if(title.equals(comboBoxType.get(i).getTitle())){
                comboBox.getSelectionModel().select(i);
            }
        }

//        comboBox.getItems().setAll(comboBoxList);



    }


    public void setComboBoxNo(long type,ComboBox comboBox,int index){

        ObservableList<String> comboBoxList = FXCollections.observableArrayList();

        List<DataSetting> comboBoxType = dataSettingService.findDataSetting(type);

        for (DataSetting combox:comboBoxType) {
            comboBoxList.add(combox.getId().toString());
        }

        comboBox.getItems().setAll(comboBoxList);

        comboBox.getSelectionModel().select(index);

    }



    /**
     *  权限 资料设定【查看】
     *  codes: 5_126_4
     */
    public void showDataSetting(){

        if(getPermissions("5_126_4")){
            stage.setTitle("资料设定");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/data_setting.fxml"));
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.setResizable(false);
            /*stage.initStyle(StageStyle.UNDECORATED);
            DragUtil.addDragListener(stage, pane); //拖拽监听*/
            stage.show();
        }

    }


    /**
     * 赋值制单人
     * @param textField
     */
    public void createPeople(TextField textField){

        textField.setText(getAdminName());

    }

    public void createPeople(TextField textField,TextField textFiel2){

        textField.setText(getAdminName());
        textFiel2.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }


    /**
     * 赋值 建档人、建档日期
     * @param textField
     * @param textFieldDate
     */
    public void createPeople(TextField textField,DatePicker textFieldDate){

        textField.setText(getAdminName());

        LocalDateTime localDateTime = LocalDateTime.now();

        textFieldDate.setValue(localDateTime.toLocalDate());

    }



    public void lastUpdatePeopel(TextField textField,TextField textFieldDate){

        textField.setText(getAdminName());

        textFieldDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

    }




    public void lastUpdatePeopel(TextField textField,DatePicker textFieldDate){

        textField.setText(getAdminName());

        LocalDateTime localDateTime = LocalDateTime.now();

        textFieldDate.setValue(localDateTime.toLocalDate());

    }

    /**
     * 返回执行结果
     * @param rows
     */
    public void returnMsg(int rows){
        if(rows > 0){
            alert_informationDialog("操作成功！");
        }else{
            alert_informationDialog("操作失败！");
        }
    }


    /**
     * 加载员工数据
     */
    public void loadEmployee(ComboBox comboBox,int index){

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

        for(int i=0,len =employeeBasics.size();i<len;i++){
            comboBox.getItems().add("("+employeeBasics.get(i).getIdnum()+")"+employeeBasics.get(i).getEmpname());
        }
        comboBox.getSelectionModel().select(index);

    }

    /**
     * 加载客户数据
     * @param comboBox 客户下拉框
     * @param textField 客户编号框
     * @param index
     */
    public void loadCustomer(ComboBox comboBox,TextField textField,int index){

        List<Customer> customerList = iCustomerService.listGeneralCustomer();

        for(int i=0,len = customerList == null?0:customerList.size() ;i<len;i++){
            comboBox.getItems().add(customerList.get(i).getName());
        }
        comboBox.getSelectionModel().select(index);

        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(!"".equals(newValue) && newValue != null ){
                    try {
                        textField.setText(customerList.get(comboBox.getSelectionModel().getSelectedIndex()).getCustomerCode());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    public  void bindDepartment(ComboBox comboBox,TextField textField){

        List<DepartmentBasic> departmentBasics = departmentBasicService.selectDepartmentBasicByParentId(1);

        for(int i=0,len=departmentBasics.size();i<len;i++){
            comboBox.getItems().add(departmentBasics.get(i).getIdnum());
        }
        comboBox.getSelectionModel().select(0);



        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if(!"".equals(newValue) && newValue != null ){

                    DepartmentBasic departmentBasic = departmentBasicService.selectDepartmentBasicByIsnum(newValue.toString());

                    textField.setText(departmentBasic.getDepname());


                }

            }
        });

    }




    public  void bindEmployee(ComboBox comboBox,TextField textField){

        List<EmployeeBasic> employeeBasics = employeeBasicService.selectEmployeeBasic();

        for(int i=0,len=employeeBasics.size();i<len;i++){
            comboBox.getItems().add(employeeBasics.get(i).getIdnum());
        }
        comboBox.getSelectionModel().select(0);



        comboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if(!"".equals(newValue) && newValue != null ){


                    EmployeeBasic employeeBasic =  employeeBasicService.selectEmployeeBasicByIsnum(newValue.toString());


                    textField.setText(employeeBasic.getEmpname());


                }

            }
        });

    }




    /**
     * 生成订单流水号 (A1809280001)
     * 格式:英文字母(A)+日期(180928)+4位流水号(0001)
     * @param maxIsnum 最大订单编号
     * @return
     */
    public String createOrderNo(String maxIsnum ){
        String currentDate = DateUtils.getSpecifyDate(new Date(),DateUtils.FORMAT_YYYY_MM_DD);
        String newDateStr=currentDate.substring(2,4)+currentDate.substring(currentDate.indexOf("-")+1,currentDate.indexOf("-")+3)+currentDate.substring(currentDate.lastIndexOf("-")+1,currentDate.lastIndexOf("-")+3);
        if(maxIsnum != null && maxIsnum.length() > 7){
            String maxAlphabet = maxIsnum.substring(0,1);
            //180928 0001
            int currenNumber = 0;
            if(maxIsnum.substring(1, 7).equals(newDateStr)){
                currenNumber = Integer.parseInt(maxIsnum.substring(maxIsnum.length()-4,maxIsnum.length()));
            }
            for (int i = 0; i< NumberUtil.ALPHABET.length; i++){
                if(currenNumber == 9999 ){
                    if( maxAlphabet.equals(NumberUtil.ALPHABET[i])  ){
                        return NumberUtil.ALPHABET[i+1]+"0001";
                    }
                }
            }
            if(currenNumber>= 0 && currenNumber < 9){
                return maxAlphabet +newDateStr+"000"+(currenNumber+1);
            }else if(currenNumber >= 9 && currenNumber< 99){
                return maxAlphabet+newDateStr +"00"+(currenNumber+1);
            }else if(currenNumber >= 99 && currenNumber< 999){
                return maxAlphabet+newDateStr +"0"+(currenNumber+1);
            }else{
                return maxAlphabet+newDateStr+(currenNumber+1);
            }
        }
        return "A"+newDateStr+"0001";
    }





  /**
   *@Description TODO  绑定省市区监听
   *@Author QuZhangJing
   *@Date 11:28  2018/11/14
   *@Version 1.0
   * @param province 省 ComboBox控件
   * @param city     市 ComboBox控件
   * @param area     区 ComboBox控件
   */
    public void bindCityListen(ComboBox province,ComboBox city,ComboBox area){

            List<Citys> provinceList = citysService.selectCitysByFatherCode("0000");

            foreachComboBox(province,provinceList);

            province.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){

                Citys citys = citysService.selectCitysByAddrName(newValue.toString(),"01");

                if(citys != null){

                    List<Citys> cityList = citysService.selectCitysByFatherCode(citys.getAddrCode());

                       foreachComboBox(city,cityList);

                         city.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue){

                            Citys citysare = citysService.selectCitysByAddrName(newValue.toString(),"02");

                            if(citysare != null){
                                List<Citys> cityList = citysService.selectCitysByFatherCode(citysare.getAddrCode());

                                foreachComboBox(area,cityList);

                                area.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue){


                                    }
                                });

                            }
                        }
                    });
                }
            }
          });
    }

    /**
     * 绑定省市区
     * @param bindParem
     * @param list
     */
    public void foreachComboBox(ComboBox bindParem,List<Citys> list){
        bindParem.getItems().clear();
        for(int i=0,len =list.size();i<len;i++){
            bindParem.getItems().add(list.get(i).getAddrName());
        }
        bindParem.getSelectionModel().select(0);
    }

    /**
     * 设置默认值
     * @param comboBox
     * @param area
     */
    public void selectedComboBox(ComboBox comboBox,String area){

            for(int i=0,len=comboBox.getItems().size();i<len;i++){

                if(comboBox.getItems().get(i).equals(area)){
                    comboBox.getSelectionModel().select(i);
                }

            }

    }


    public static <T> void clickEvent(TableView<T> table, IClickItem<T> item, int times) {

        table.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                // 双击执行操作
                if(event.getClickCount() == times ){
                    T t = row.getItem();
                    item.click(t);
                }
            });
            return row;
        });
    }


    /**
     * 将Map集合转成字符串
     * @param maps
     * @return
     */
    public String mapAssembleString(Map<String,String> maps){

            String resultKey = "",resultValue = "";

            for (Map.Entry<String,String> map:maps.entrySet()) {

                resultKey += map.getKey()+",";

                resultValue += map.getValue()+",";

            }

//           if(!"".equals(resultKey))resultKey = resultKey.substring(0,resultKey.length()-1);
//           if(!"".equals(resultValue))resultValue = resultValue.substring(0,resultValue.length()-1);

           if(!"".equals(resultKey) && !"".equals(resultValue)){

               resultKey = resultKey.substring(0,resultKey.length()-1);

               resultValue = resultValue.substring(0,resultValue.length()-1);

               return resultKey +";" + resultValue ;
           }else{
               return "";
           }

    }


    /**
     * 将直接格式字符串转化成map
     * @param string
     * @return
     */
    public Map<String,String> stringAssembleMap(String string){

        Map<String,String> result = new HashMap<>();

        String temp[] =  string.split(";");

        for(int i=0,len=temp.length-1;i<len;i++){

            String keyArray[] =temp[0].split(",");

            String valueArray[] =temp[1].split(",");

            for (int j=0,lens=keyArray.length;j<lens;j++){

                result.put(keyArray[j],valueArray[j]);

            }

        }

        return result;
    }


    //获取当前用户
    public String getAdminName(){

         EmployeeBasic employeeBasic = employeeBasicService.selectEmployeeBasicByIsnum(getUserName());

        return employeeBasic.getEmpname();
    }


    //获取当前时间
    public String getCurrentDate(){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    //取消审核
    public void cancelSh(TextField name,TextField date){
            name.setText(NumberUtil.NULLSTRING);
            date.setText(NumberUtil.NULLSTRING);
    }

    /**
     * 核算列表产品数量、金额、税款
     * @param pronum 行-数量
     * @param price 行-金额
     * @param tax 行-税款
     * @param totalnum 数量框
     * @param totaltax 金额总计
     * @param totalloan 贷款合计
     * @param totalmoney 税款合计
     */
    public void totalCost(int pronum,BigDecimal price,String tax,TextField totalnum,TextField totaltax,TextField totalloan,TextField totalmoney){

        Double rate = getTax(new Date(),2);
        BigDecimal taxFlag = rate==null?new BigDecimal(getTax(new Date(),2).toString()):new BigDecimal(rate.toString());  //销售项税率

        int countNum = 0;

        BigDecimal taxNum = new BigDecimal("0.00");

        BigDecimal totaloanNum = new BigDecimal("0.00");

        try {
            if(totaltax.getText() != null && !"".equals(totaltax.getText())){
                taxNum = BigDecimal.valueOf(Double.valueOf(totaltax.getText()));
            }
            if(totalloan.getText() != null && !"".equals(totalloan.getText())){
                totaloanNum = BigDecimal.valueOf(Double.valueOf(totalloan.getText()));
            }
            if(totalnum.getText() != null && !"".equals(totalnum.getText())){
                countNum = Integer.parseInt(totalnum.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        totalnum.setText(String.valueOf((countNum+pronum)));

        //税别类型  "外加","内含","零税","免税"
        switch (tax){
            case "外加":

                totaltax.setText(String.valueOf((price.multiply(taxFlag).add(taxNum)).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalloan.setText(String.valueOf(totaloanNum.add(price).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                break;
            case "内含":

                BigDecimal capital =  price.divide(taxFlag.add(new BigDecimal("1.00")),2,BigDecimal.ROUND_UP); //税前价格

                totalloan.setText(String.valueOf(totaloanNum.add(capital).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totaltax.setText(String.valueOf(price.subtract(capital).add(taxNum).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                break;
            default:
                totalloan.setText(String.valueOf(totaloanNum.add(price).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                break;
        }

    }

    /**
     * 核算列表产品数量、金额、税款
     * @param pronum 行-数量
     * @param price 行-金额
     * @param tax 行-税款
     * @param totalnum 数量框
     * @param totaltax 金额总计
     * @param totalloan 贷款合计
     * @param totalmoney 税款合计
     */
    public void totalCost(int pronum,BigDecimal price,int tax,TextField totalnum,TextField totaltax,TextField totalloan,TextField totalmoney){

        Double rate = getTax(new Date(),2);
        BigDecimal taxFlag = rate==null?new BigDecimal(getTax(new Date(),2).toString()):new BigDecimal(rate.toString());  //销售项税率

        int countNum = 0;

        BigDecimal taxNum = new BigDecimal("0.00");

        BigDecimal totaloanNum = new BigDecimal("0.00");

        try {
            if(totaltax.getText() != null && !"".equals(totaltax.getText())){
                taxNum = BigDecimal.valueOf(Double.valueOf(totaltax.getText()));
            }
            if(totalloan.getText() != null && !"".equals(totalloan.getText())){
                totaloanNum = BigDecimal.valueOf(Double.valueOf(totalloan.getText()));
            }
            if(totalnum.getText() != null && !"".equals(totalnum.getText())){
                countNum = Integer.parseInt(totalnum.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        totalnum.setText(String.valueOf((countNum+pronum)));

            //税别类型  "外加","内含","零税","免税"
            switch (tax){
                case 1:

                    totaltax.setText(String.valueOf((price.multiply(taxFlag).add(taxNum)).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    totalloan.setText(String.valueOf(totaloanNum.add(price).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    break;
                case 2:

                    BigDecimal capital =  price.divide(taxFlag.add(new BigDecimal("1.00")),2,BigDecimal.ROUND_HALF_DOWN); //税前价格

                    totalloan.setText(String.valueOf(totaloanNum.add(capital).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    totaltax.setText(String.valueOf(price.subtract(capital).add(taxNum).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    break;
                default:
                    totalloan.setText(String.valueOf(totaloanNum.add(price).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    totalmoney.setText(String.valueOf(new BigDecimal(Double.parseDouble(totaltax.getText())+Double.parseDouble(totalloan.getText())).setScale(2,   BigDecimal.ROUND_UP).doubleValue()));

                    break;
            }

    }


    /**
     * 获取用户编号
     * 这个是获取用户编号的
     * 上面有获取用户名的   getAdminName();
     *
     * @return
     */
    public String getUserName(){
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.getPrincipal().toString();
    }

    /**
     * 根据权限码查询用户是否有权限
     * @param codes  例如：1001_568_7
     * @return
     */
    public Boolean getPermissions(String codes){
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isPermitted(codes);
    }



    /**
     * 选择导出路径
     * @return
     */
    public String choseURL(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(new Stage());
        if(file != null){
            return file.getPath();//选择的文件夹路径
        }else {
            return null;
        }
    }

    /**
     * @Author BlueSky
     * @Description //TODO 导出公共方法
     * @param path /jasper/sale_statistics.jasper
     * @param params 传入的参数 key为报表参数id
     * @Date 10:52 2019-03-26
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @SneakyThrows
    public Map<String ,Object> exportCommon(String path,Map<String,Object> params){
        URL url = this.getClass().getResource(path);
        String str = url==null?null:url.toString().substring(url.toString().indexOf(':')+1, url.toString().length());
        String reportName = str.substring(str.lastIndexOf("/"), str.length()).split("\\.")[0];
        //传入报表源文件绝对路径，外部参数对象，DB连接，得到JasperPring对象
        JasperPrint jasperPrint = JasperFillManager.fillReport(str, params, AppConst.getConnection());
        Map<String ,Object> map = new HashMap<>();
        map.put("reportName", reportName);
        map.put("jasperPrint", jasperPrint);
        return map;
    }

    /**
     * 权限不足提示
     */
    public void alertPermissions(){
        alert_informationDialog("暂无权限，请联系管理员。");
    }

    /**
     * 单据初始化 匹配用户权限
     * @param insertCode  权限编码 (新增)
     * @param deleteCode  权限编码 (删除)
     * @param updateCode  权限编码 (修改)
     * @param insertVbox  操作控件(新增)
     * @param deleteVbox  操作控件(删除)
     * @param updateVbox  操作控件(修改)
     */
    public void matchingPermissions(String insertCode, String deleteCode, String updateCode, VBox insertVbox,VBox deleteVbox,VBox updateVbox){
        if(!getPermissions(insertCode))insertVbox.setDisable(true);
        if(!getPermissions(deleteCode)) deleteVbox.setDisable(true);
        if(!getPermissions(updateCode))updateVbox.setDisable(true);
    }

    /**
     * 单据初始化 匹配用户权限
     * @param insertCode
     * @param deleteCode
     * @param updateCode
     * @param shCode
     * @param cancelShCode
     * @param printingCode
     * @param insertVbox
     * @param deleteVbox
     * @param updateVbox
     * @param shVbox
     * @param cancelShVbox
     * @param printingVbox
     */
    public void matchingPermissions(String insertCode,
                                       String deleteCode,
                                       String updateCode,
                                       String shCode,
                                       String cancelShCode,
                                       String printingCode,
                                       VBox insertVbox,
                                       VBox deleteVbox,
                                       VBox updateVbox,
                                       VBox shVbox,
                                       VBox cancelShVbox,
                                       VBox printingVbox,
                                       VBox clearVbox
    ){
        if(!getPermissions(insertCode))insertVbox.setDisable(true);
        if(!getPermissions(deleteCode)) deleteVbox.setDisable(true);
        if(!getPermissions(updateCode)){
            updateVbox.setDisable(true);
            clearVbox.setDisable(true);
        }
        if(!getPermissions(shCode))shVbox.setDisable(true);
        if(!getPermissions(cancelShCode))cancelShVbox.setDisable(true);
        if(!getPermissions(printingCode))printingVbox.setDisable(true);
    }


    public void matchingPermissions(String title, VBox insertVbox,
                                    VBox deleteVbox,
                                    VBox updateVbox,
                                    VBox shVbox,
                                    VBox cancelShVbox,
                                    VBox printingVbox,
                                    VBox clearVbox){
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,1).getCodes())){
                insertVbox.setDisable(true);
            }else {
                insertVbox.setDisable(false);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,2).getCodes())) {
                deleteVbox.setDisable(true);
            }else {
                deleteVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,3).getCodes())){
                updateVbox.setDisable(true);
                clearVbox.setDisable(true);
            }else {
                updateVbox.setDisable(false);
                clearVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,5).getCodes())){
                shVbox.setDisable(true);
            }else {
                shVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,6).getCodes())){
                cancelShVbox.setDisable(true);
            }else {
                cancelShVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,7).getCodes())){
                printingVbox.setDisable(true);
            }else {
                printingVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void matchingPermissions(String title, VBox insertVbox,
                                    VBox deleteVbox,
                                    VBox updateVbox,
                                    VBox printingVbox,
                                    VBox clearVbox){
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,1).getCodes())){
                insertVbox.setDisable(true);
            }else {
                insertVbox.setDisable(false);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,2).getCodes())) {
                deleteVbox.setDisable(true);
            }else {
                deleteVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,3).getCodes())){
                updateVbox.setDisable(true);
                clearVbox.setDisable(true);
            }else {
                updateVbox.setDisable(false);
                clearVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,7).getCodes())){
                printingVbox.setDisable(true);
            }else {
                printingVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @Title: dynamiccolumn
     * @Description: TODO(对design进行处理，去掉不应该显示的列)
     * @param @param jdesign JasperDesign
     * @param @param params 需要显示的列
     * @return JasperDesign 返回类型
     */
    public static JasperDesign dynamiccolumn(JasperDesign jdesign, Map params) {
        /*
         * 该方法目前仅进行了简单的处理，如需更多业务，且自行添加 比如：1. 修改元素的位置 2.自动调整Title的宽度
         * 3.自行调整整个报表的宽度
         */
        Collection dynamiccolumns = (Collection) params.get("dynamiccolumn");
        if (dynamiccolumns != null) {

            JRDesignBand cHeader = (JRDesignBand) jdesign.getColumnHeader();
            JRBand cDetailBand = jdesign.getDetailSection().getBands()[0];
            JRDesignBand cDetail = null;
            if (cDetailBand != null && cDetailBand instanceof JRDesignBand) {
                cDetail = (JRDesignBand) cDetailBand;
            }
            JRElement[] es_header = cHeader.getElements();
            JRElement[] es_detail = cDetail.getElements();
            for (int i = 0; i < es_header.length; i++) {
                JRDesignElement e = (JRDesignElement) es_header[i];
                String v = "";
                if (e instanceof JRStaticText) {
                    JRStaticText text = (JRStaticText) e;
                    v = text.getText();
                }
                if (!dynamiccolumns.contains(v)) {
                    for (int j = 0; j < es_detail.length; j++) {
                        JRDesignElement ee = (JRDesignElement) es_detail[i];
//                         if (ee.getY() == e.getY()) {
                            cDetail.removeElement(ee);
//                        }
                    }
                    cHeader.removeElement(e);
                }
            }
        }
        return jdesign;
    }


    public void matchingPermissions(String title, VBox insertVbox,
                                    VBox deleteVbox,
                                    VBox updateVbox,
                                    VBox shVbox,
                                    VBox cancelShVbox,
                                    VBox printingVbox,
                                    VBox clearVbox,
                                    int shStatus){
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,1).getCodes())){
                insertVbox.setDisable(true);
            }else {
                insertVbox.setDisable(false);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,2).getCodes())) {
                deleteVbox.setDisable(true);
            }else {
                deleteVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,3).getCodes())){
                updateVbox.setDisable(true);
                clearVbox.setDisable(true);
            }else {
                updateVbox.setDisable(false);
                clearVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,5).getCodes()) && shStatus == 1){
                shVbox.setDisable(true);
                deleteVbox.setDisable(true);
                updateVbox.setDisable(true);
            }else if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,5).getCodes())){
                shVbox.setDisable(true);
            }else {
                shVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,6).getCodes()) && shStatus == 0 ){
                cancelShVbox.setDisable(true);
//                if(getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,2).getCodes())) {
//                    deleteVbox.setDisable(false);
//                }
//                if(getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,3).getCodes())){
//                    updateVbox.setDisable(false);
//                    clearVbox.setDisable(false);
//                }
            }else if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,6).getCodes())){
                cancelShVbox.setDisable(true);
            }else {
                cancelShVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            if(!getPermissions(permissionsService.selectPermissionsByTitleAndCodes(title,7).getCodes())){
                printingVbox.setDisable(true);
            }else {
                printingVbox.setDisable(false);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //绑定异动类型
    public void setChangeType(ComboBox changeType){
        changeType.getItems().clear();
        changeType.getItems().setAll("盘盈","销退入库","借入","调整减少","调整增多","借出归还","盘亏","报废","借出","借入归还");
    }

    public void setChangeType(ComboBox changeType,int index){
        changeType.getItems().clear();
        changeType.getItems().setAll("盘盈","销退入库","借入","调整减少","调整增多","借出归还","盘亏","报废","借出","借入归还");
        changeType.getSelectionModel().select(index);
    }


    //获取异动类型
    public String returnChangeType(int type){

//        "盘盈","销退入库","借入","调整减少","调整增多","借出归还","盘亏","报废","借出","借入归还"

                switch(type){
                    case 1:
                        return "盘盈";
                    case 2:
                        return "销退入库";
                    case 3:
                        return "借入";
                    case 4:
                        return "调整减少";
                    case 5:
                        return "调整增多";
                    case 6:
                        return "借出归还";
                    case 7:
                        return "盘亏";
                    case 8:
                        return "报废";
                    case  9:
                        return "借出";
                    case 10:
                        return "借入归还";
                        default:
                            return "未找到的类型";
                }


    }


    /**
     *@Description TODO 选择文件夹
     *@Author QuZhangJing
     *@Date 20:32  2019/3/21
     *@Version 1.0
     */
    public String chooserDirectory()
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择导出文件夹");
        ObservableList<Stage> stage = FXRobotHelper.getStages();

        File file = directoryChooser.showDialog(stage.get(0));
        if(file!=null)
        {
            return file.getAbsolutePath();
        }
        else
        {
            return null;
        }

    }

    /**
     * @Description TODO 表格导出
     *@Author QuZhangJing
     *@Date 20:32  2019/3/21
     *@Version 1.0
     */
    public void export(String url) throws Exception
    {


        if(!"".equals(url))
        {
            SimpleDateFormat sf = new SimpleDateFormat("YYYYMMddhhmmss");
            String now = sf.format(new Date());

            String filePath=url+"\\"+now+".xls";
            System.out.println(filePath);
            HSSFWorkbook workbook = new HSSFWorkbook();         //创建Excel文件(Workbook)
            HSSFSheet sheet = workbook.createSheet();           //创建工作表(Sheet)

            HSSFRow titleRow = sheet.createRow(0);
            String[] cellTitle = {"目录1","目录2","目录3","目录4","目录5","目录6","目录7"};

            for(int i=0;i<cellTitle.length;i++){
                titleRow.createCell(i).setCellValue(cellTitle[i]);
            }



            for(int i=0;i<6;i++){
                HSSFRow row = sheet.createRow(i+1);
                //Excel表的格式
                row.createCell(0).setCellValue(i);
                row.createCell(1).setCellValue(i+"J");
                row.createCell(2).setCellValue(i+"A");
                row.createCell(3).setCellValue(i+"V");
                row.createCell(4).setCellValue(i+"A");
                row.createCell(5).setCellValue(i+"F");
                row.createCell(6).setCellValue(i+"X");
            }

            FileOutputStream out = new FileOutputStream(filePath);
            workbook.write(out);//保存Excel文件
            out.flush();
            out.close();

            alert_informationDialog("导出成功!");

        }else
        {
            alert_informationDialog("导出失败!");

        }




    }

    /**
    * @Author BlueSky
    * @Description //TODO 利用正则表达式判断字符串是否是数字
    * @Date 10:57 2019-03-26
    * @Param [str]
    * @return boolean
    **/
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * @Author BlueSky
     * @Description //TODO 加载结算方式
     * @Date 15:17 2019-03-26
     * @Param [comboBox]
     * @return void
     **/
    public void loadSettlementMethod(ComboBox comboBox){
        comboBox.getItems().addAll("先款后货","票到当月","两月结","三月结","四月结","票到付款");
    }

    /**
     * @Author BlueSky
     * @Description //TODO 计算付款方式的付款日期
     * @Date 15:55 2019-03-26
     * @Param [date 制单日期, method]
     * @return java.util.Boolean
     **/
    public Boolean loadSettlementMethodCalcDate(Date date,String method){
        Date paymentDate = null;
        boolean bool = false;
        switch (method){
            case "先款后货":
                break;
            case "票到当月":
                paymentDate = DateUtils.getPreviousOrNextMonthsOfDate(date,1);
                if(paymentDate.getTime() >= new Date().getTime()){
                    bool = true;
                }
                break;
            case "两月结":
                paymentDate = DateUtils.getPreviousOrNextMonthsOfDate(date,2);
                if(paymentDate.getTime() > new Date().getTime()){
                    bool = true;
                }
                break;
            case "三月结":
                paymentDate = DateUtils.getPreviousOrNextMonthsOfDate(date,3);
                if(paymentDate.getTime() > new Date().getTime()){
                    bool = true;
                }
                break;
            case "四月结":
                paymentDate = DateUtils.getPreviousOrNextMonthsOfDate(date,4);
                if(paymentDate.getTime() > new Date().getTime()){
                    bool = true;
                }
                break;
            case "票到付款":
                bool = true;
                break;
        }
        return bool;
    }

    /**
     * 税别
     * @param status
     * @return
     */
    public  String returnTax(int status){
        switch (status){
            case 1: return "外加";
            case 2: return "内含";
            case 3: return "零税";
            case 4: return "免税";
            default:return "";
        }
    }


    public void setTaxCombox(ComboBox combox,String title){
        String taxs[] = {"外加","内含","零税","免税"};
        for(int i=0,len=taxs.length;i<len;i++){
            combox.getItems().add(taxs[i]);
            if(title.equals(taxs[i])){
                combox.getSelectionModel().select(i);
            }
        }
    }

    public void setTaxCombox(ComboBox combox,int index){
        String taxs[] = {"外加","内含","零税","免税"};
        for(int i=0,len=taxs.length;i<len;i++){
            combox.getItems().add(taxs[i]);
        }
        combox.getSelectionModel().select(index);
    }

    /**
     * 设置制单日期为当期日期
     * @param datePicker
     */
    public void setDatePicker(DatePicker datePicker){
        datePicker.setValue(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate());
    }

    /**
     * 设置到日期往后推num天  例如 到货期日
     * @param orderDate
     * @param arrivalDate
     * @param num 需要后推的天数
     */
    public void setArrivalDate(DatePicker orderDate,DatePicker arrivalDate,int num){
        //需要后推的日期
         Date currdate = new Date(java.sql.Date.valueOf(orderDate.getValue()).getTime());

        Calendar ca = Calendar.getInstance();
        // num为增加的天数，可以改变的
        ca.add(Calendar.DATE, num);
        currdate = ca.getTime();
        arrivalDate.setValue(LocalDateTime.ofInstant(currdate.toInstant(), ZoneId.systemDefault()).toLocalDate());


    }

    /**
     * 当前日期往后推num天
     * @param currentDate
     * @param num
     * @return
     */
    public String setPushBackDate(DatePicker currentDate,int num){
        //需要后推的日期
        Date currdate = new Date(java.sql.Date.valueOf(currentDate.getValue()).getTime());
        Calendar ca = Calendar.getInstance();
        // num为增加的天数，可以改变的
        ca.add(Calendar.DATE, num);
        currdate = ca.getTime();
        return DateToString(currdate);
    }


    /**
     * 税率查询
     * @param currentDate  制单日期
     * @param types 1、进项税率   2 销项税率
     * @return
     */
    public BigDecimal getTax(LocalDate currentDate,int types){
        return new BigDecimal(taxRateService.selectTaxRateByOrderTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(java.sql.Date.valueOf(currentDate).getTime())),types));
    }

    public Double getTaxReruenDouble(LocalDate currentDate,int types){
        return taxRateService.selectTaxRateByOrderTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date(java.sql.Date.valueOf(currentDate).getTime())),types);
    }

    public Double getTax(Date currentDate,int types){
        return taxRateService.selectTaxRateByOrderTime(new SimpleDateFormat("yyyy-MM-dd").format(currentDate),types);
    }

    /**
     * Date类型转成LocalDate
     * @param date
     * @return
     */
    public LocalDate dateToLocalDate(Date date){
        return  LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
    /**
     * localDate类型转成String
     * @param localDate
     * @return
     */
    public String LocalDateToString(LocalDate localDate){
        if(localDate == null ){
            return "";
        }else{
            return  localDate.toString();
        }
    }
    /**
     * localDate类型转成Date
     * @param localDate
     * @return
     */
    public Date LocalDateToDate(LocalDate localDate){
        return new Date(java.sql.Date.valueOf(localDate).getTime());
    }


    /**
     * String类型转Date
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public Date StringToDate(String dateStr) {
       try {
           return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
       }catch (Exception e){
           return null;
       }
    }


    public String DateToString(Date dateStr) {
        return new SimpleDateFormat("yyyy-MM-dd").format(dateStr);
    }

    /**
     * @Description 分页数量
     * @Author BlueSky
     * @Date 19:05 2019/4/15
     **/
    public int pageRows(CheckBox che_recently,TextField num){
        boolean recently = che_recently.isSelected();
        int rows = AppConst.ROWS;
        if(recently && num.getText() != null && !"".equals(num.getText())){
            rows = Integer.valueOf(num.getText());
        }
        return rows;
    }

    /**
     * @Description 检查产品最低售价 ，售价如果低于最低价则返回 null
     * @param productNo 产品编号
     * @Author BlueSky
     * @Date 16:38 2019/4/25
     **/
    public String checkProductPrice(String productNo,String price){
        try {
            ProductBasic basic = iProductBasicService.selectProductBasicByIsnum(productNo);
            if(basic != null && basic.getLowprice() > Double.valueOf(price)){
                return basic.getLowprice().toString();
            }else{
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    /**
     * @Description 根据产品编号获取产品基本信息
     * @Author BlueSky
     * @Date 20:17 2019/4/29
     **/
    public ProductBasic getProductBasic(String productNo){
        return iProductBasicService.selectProductBasicByIsnum(productNo);
    }

    public void attrImages(ImageView rightImage,Image image){
        rightImage.setImage(image);
    }


    /**
     * @Description 检查单据主要字段是否为空 返回布尔
     * @param orderNo 单号
     * @param customerNo 客户编号
     * @param customerNoStr 客户名称
     * @param businessStr 业务负责人
     * @Author BlueSky
     * @Date 12:16 2019/4/27
     **/
    public boolean checkPrimaryNull(TextField orderNo,TextField customerNo,TextField customerNoStr,TextField businessStr){
        boolean bool = false;
        if(orderNo.getText() == null || "".equals(orderNo.getText())){
            bool = true;
        }
        if(customerNo.getText() == null || "".equals(customerNo.getText())){
            bool = true;
        }
        if(customerNoStr.getText() == null || "".equals(customerNoStr.getText())){
            bool = true;
        }
        if(businessStr != null && "".equals(businessStr.getText())){
            bool = true;
        }
        if(bool){
            alert_informationDialog("单号、客户编号、客户名称、业务负责人不能为空！");
        }
        return bool;
    }


    /**
     * @Description 检查单据主要字段是否为空 返回布尔
     * @param warehouse 仓库库位
     * @param warehouseStr 仓库名称
     * @Author BlueSky
     * @Date 12:16 2019/4/27
     **/
    public boolean checkWarehouseNull(TextField warehouse,TextField warehouseStr){
        boolean bool = false;
        if(warehouse.getText() == null || "".equals(warehouse.getText())){
            bool = true;
        }
        if(warehouseStr.getText() == null || "".equals(warehouseStr.getText())){
            bool = true;
        }
        if(bool){
            alert_informationDialog("单号、客户编号、客户名称、业务负责人不能为空！");
        }
        return bool;
    }

    /**
     * @Description 获取客户最低折扣
     * @Author BlueSky
     * @Date 21:16 2019/4/27
     **/
    public Double getCustomerMinimumDiscountByCode(String code){
        Customer customer = iCustomerService.getCustomer(code);
        if(customer != null){
            CustomerBasic basic = iCustomerBasicService.getCustomerBasicByCustomerId(customer.getId());
            if(basic != null && basic.getMinimumDiscount() != null  && !"".equals(basic.getMinimumDiscount())){
                return basic.getMinimumDiscount();
            }else{
                return null;
            }
        }else {
            return null;
        }
    }

    VBox btn1 = (VBox) StageManager.CONTROLLER.get("btn1");
    VBox btn2 = (VBox) StageManager.CONTROLLER.get("btn2");
    VBox btn3 = (VBox) StageManager.CONTROLLER.get("btn3");
    VBox btn4 = (VBox) StageManager.CONTROLLER.get("btn4");
    VBox btn5 = (VBox) StageManager.CONTROLLER.get("btn5");
    VBox btn6 = (VBox) StageManager.CONTROLLER.get("btn6");
    VBox btn7 = (VBox) StageManager.CONTROLLER.get("btn7");
    VBox btn8 = (VBox) StageManager.CONTROLLER.get("btn8");

    /**
     *  * 改变主界面窗口按钮选择
     * @param currentIndex 当前所在模块下标
     * @param index   目标模块下标
     * @param seelectIndex
     */
    public void changeHomeBtn(int currentIndex,int index,int seelectIndex){


        if(index == 0){

            String keyUrl = "";
            //销售
            if(currentIndex==1){
                keyUrl = "saleTreeView";
            }
            //采购
            if(currentIndex==2){
                keyUrl = "purchanseTreeView";
            }
            //库存
            if(currentIndex==3){
                keyUrl = "stockTreeView";
            }
            //账款
            if(currentIndex==4){}
            //基本资料
            if(currentIndex==5){}
            //客户关系
            if(currentIndex==6){}
            //考勤管理
            if(currentIndex==7){}
            //统计汇总
            if(currentIndex==8){}
            TreeView treeViewPurchase = (TreeView)StageManager.CONTROLLER.get(keyUrl);
            treeViewPurchase.getSelectionModel().select(seelectIndex);
            return;
        }



        String url = "";
        //销售
        if(index==1){ url="/fxml/sale_data.fxml"; }
        //采购
        if(index==2){url="/fxml/purchase_data.fxml";}
        //库存
        if(index==3){url="/fxml/inventory_data.fxml";}
        //账款
        if(index==4){url="/fxml/account_data.fxml";}
        //基本资料
        if(index==5){url="/fxml/basic_data.fxml";}
        //客户关系
        if(index==6){url="/fxml/customer_relation_data.fxml";}
        //考勤管理
        if(index==7){url="/fxml/check_data.fxml";}
        //统计汇总
        if(index==8){url="/fxml/baseline_data.fxml";}

        Pane paneHome = new Pane();

        paneHome.getChildren().setAll(loader.load(url));

        Pane pane2 = (Pane) StageManager.CONTROLLER.get("homePaneUrl");

        pane2.getChildren().setAll(paneHome);





//销售
        if(index==1){

        }
        //采购
        if(index==2){

            TreeView treeViewPurchase = (TreeView)StageManager.CONTROLLER.get("purchanseTreeView");

            TreeView treeViewSale = new TreeView();

            if(currentIndex == 1){
                treeViewSale = (TreeView)StageManager.CONTROLLER.get("saleTreeView");
            }
//            treeViewSale.requestFocus();

            treeViewPurchase.getSelectionModel().select(seelectIndex);

            treeViewSale.setRoot(treeViewPurchase.getRoot());
        }
        //库存
        if(index==3){

            TreeView treeViewPurchase = (TreeView)StageManager.CONTROLLER.get("stockTreeView");

            TreeView treeViewSale = new TreeView();

            if(currentIndex ==1){
                treeViewSale = (TreeView)StageManager.CONTROLLER.get("saleTreeView");
            }else if(currentIndex == 2){
                treeViewSale = (TreeView)StageManager.CONTROLLER.get("purchanseTreeView");
            }
//            treeViewSale.requestFocus();

            treeViewPurchase.getSelectionModel().select(seelectIndex);

            treeViewSale.setRoot(treeViewPurchase.getRoot());

        }
        //账款
        if(index==4){}
        //基本资料
        if(index==5){}
        //客户关系
        if(index==6){}
        //考勤管理
        if(index==7){}
        //统计汇总
        if(index==8){}




        removeClass();

        if(index == 1){
            btn1.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/xsed.png');");
            btn1.getChildren().get(1).setStyle(checkedFontColor);
            btn1.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 2){
            btn2.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/cged.png');");
            btn2.getChildren().get(1).setStyle(checkedFontColor);
            btn2.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 3){
            btn3.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kced.png');");
            btn3.getChildren().get(1).setStyle(checkedFontColor);
            btn3.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 4){
            btn4.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/zked.png');");
            btn4.getChildren().get(1).setStyle(checkedFontColor);
            btn4.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 5){
            btn5.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/zled.png');");
            btn5.getChildren().get(1).setStyle(checkedFontColor);
            btn5.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 6){
            btn6.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/khed.png');");
            btn6.getChildren().get(1).setStyle(checkedFontColor);
            btn6.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 7){
            btn7.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kqed.png');");
            btn7.getChildren().get(1).setStyle(checkedFontColor);
            btn7.setStyle("-fx-background-color:#169252 !important;");
        }else if(index == 8){
            btn8.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/bbed.png');");
            btn8.getChildren().get(1).setStyle(checkedFontColor);
            btn8.setStyle("-fx-background-color:#169252 !important;");
        }




    }


    public void removeClass(){


        /**
         * 恢复默认样式
         */
        btn1.setStyle(defaultColor);
        btn2.setStyle(defaultColor);
        btn3.setStyle(defaultColor);
        btn4.setStyle(defaultColor);
        btn5.setStyle(defaultColor);
        btn6.setStyle(defaultColor);
        btn7.setStyle(defaultColor);
        btn8.setStyle(defaultColor);

        btn1.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/xs.png');");
        btn1.getChildren().get(1).setStyle(defaultFontColor);
        btn2.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/cg.png');");
        btn2.getChildren().get(1).setStyle(defaultFontColor);
        btn3.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kc.png');");
        btn3.getChildren().get(1).setStyle(defaultFontColor);
        btn4.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/zk.png');");
        btn4.getChildren().get(1).setStyle(defaultFontColor);
        btn5.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/zl.png');");
        btn5.getChildren().get(1).setStyle(defaultFontColor);
        btn6.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kh.png');");
        btn6.getChildren().get(1).setStyle(defaultFontColor);
        btn7.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kq.png');");
        btn7.getChildren().get(1).setStyle(defaultFontColor);
        btn8.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/bb.png');");
        btn8.getChildren().get(1).setStyle(defaultFontColor);
    }



    public DepotProperty  getDepot(String depotStr){

        DepotProperty result = new DepotProperty("","","");

        if(depotStr.indexOf(",") == -1){
            String depots[] = depotStr.split(",");

            for(int i=0,len=depots.length;i<len;i++){

                DepotBasic depotBasic = depotBasicService.selectDepotBasicByIsnum(depots[i]);

                if(i == 0){

                    if(depotBasic != null){
                        result = new DepotProperty(depotBasic.getIsnum(),depotBasic.getDepname(),depotBasic.getDepfloor());
                    }

                }

            }
        }

        return result;

    }

    /**
     * @Description 冲销项发票
     * @param customerNo 客户编号
     * @param money 收款金额
     * @Author BlueSky
     * @Date 21:44 2019/5/5
     **/
    public void rushSaleInvoice(Long id,String customerNo,BigDecimal money){
        // 查找客户未冲账发票单据 或者 未冲完的
        List<AccountSaleInvoice> invoiceList = iAccountSaleInvoiceService.listNotRushAccountSaleInvoice(customerNo);
        invoiceList = invoiceList==null?new ArrayList<>():invoiceList;
        for (AccountSaleInvoice p : invoiceList) {
            // 收款金额大于0
            if(money.compareTo(new BigDecimal("0")) > 0){
                // 收款金额不够本张销项发票单据的情况： (销项发票的金额 - 已收金额) > 收款金额
                if((p.getMoney().subtract(p.getReceiveMoney())).compareTo(money) > 0){
                    p.setReceiveMoney(p.getReceiveMoney().add(money));
                    money = money.subtract(money);
                }else{
                    money = money.subtract(p.getMoney().subtract(p.getReceiveMoney()));
                    p.setReceiveMoney(p.getMoney());
                }
                iAccountSaleInvoiceService.updateNotNull(p);
            }
        }
        // 修改收款单冲账剩余金额
        AccountReceipt receipt = new AccountReceipt();
        receipt.setId(id);
        receipt.setBalance(money);
        iAccountReceiptService.updateNotNull(receipt);
    }

    public static void main(String[] args) {
        List<PurchaseProduct> purchaseProducts = purchaseProductService.findPurchaseProductNew("","","","","","","","",1,18);

        for (PurchaseProduct purchaseProduct:purchaseProducts) {
            System.out.println("====="+purchaseProduct.getProorders());
        }
    }



}
