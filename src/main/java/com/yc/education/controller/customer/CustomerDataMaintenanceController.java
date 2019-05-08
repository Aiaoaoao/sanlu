package com.yc.education.controller.customer;

import com.github.pagehelper.PageInfo;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.controller.BaseController;
import com.yc.education.model.DataSetting;
import com.yc.education.model.customer.*;
import com.yc.education.service.DataSettingService;
import com.yc.education.service.customer.*;
import com.yc.education.util.AppConst;
import com.yc.education.util.DateUtils;
import com.yc.education.util.EditCell;
import com.yc.education.util.StageManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 客户资料维护
 *
 * @Author: BlueSky
 * @Date: 2018/8/15 15:09
 */
@Controller
public class CustomerDataMaintenanceController extends BaseController implements Initializable {

    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    @Autowired ICustomerDataMaintainService iCustomerDataMaintainService;
    @Autowired ICustomerDataMaintainCarService iCustomerDataMaintainCarService;
    @Autowired ICustomerDataMaintainProductionService iCustomerDataMaintainProductionService;
    @Autowired ICustomerDataMaintainQuestionService iCustomerDataMaintainQuestionService;
    @Autowired ICustomerDemandGoodsService iCustomerDemandGoodsService;
    @Autowired ICustomerService iCustomerService;
    @Autowired ICustomerDetailInfoService iCustomerDetailInfoService;
    @Autowired DataSettingService iDataSettingService;



    @FXML VBox menu_first;           // 第一页
    @FXML VBox menu_prev;            // 上一页
    @FXML VBox menu_next;            // 下一页
    @FXML VBox menu_last;            // 最后一页

    //====================================== 主要信息 ==========================================
    @FXML
    public DatePicker create_date;
    @FXML
    public TextField customer_no;
    @FXML
    public TextField customer_no_str;
    @FXML
    public TextField customer_name;
    @FXML
    public TextField create_no;
    // 客户需求商品id
    @FXML
    public TextField demandid;
    // 客户id
    @FXML
    public TextField customer_id;
    // 切换面板
    @FXML
    TabPane tabpan;

    @FXML Button btn_customer_no;
    @FXML Button btn_create_no;

    String current_pane_str = "";
    // 当前页
    private static int page = 0;
    // 页大小
    private final static int rows = 1;
    //====================================== 基本信息 ==========================================
    @FXML
    TextField leader_people;
    @FXML
    TextField contacts;
    @FXML
    TextField fax;
    @FXML
    TextField delivery_address;
    @FXML
    TextField invoice_address;
    @FXML
    TextField create_people;
    @FXML
    TextField create_people_str;
    @FXML
    TextField update_people;
    @FXML
    TextField update_people_str;
    @FXML
    TextField purchase;
    @FXML
    TextField phone;
    //====================================== 详细信息 ==========================================
    @FXML
    TextField start_year;
    @FXML
    TextField annual_turnover;
    @FXML
    TextField employee_num;
    @FXML
    TextField budget;
    @FXML
    CheckBox che_production;
    @FXML
    CheckBox che_oem;
    @FXML
    CheckBox che_general_oem;
    @FXML
    TableView car_table;
    @FXML
    TableColumn col_car_id;
    @FXML
    TableColumn col_car_lathe_type;
    @FXML
    TableColumn col_car_brand;
    @FXML
    TableColumn col_car_models;
    @FXML
    TableColumn col_car_num;
    //====================================== 生产资料 ==========================================
    @FXML
    TextArea production_type;
    @FXML
    TextArea vendor;
    @FXML
    TextArea factory;
    @FXML
    Pane production_checkbox_pan;
    @FXML
    Button btn_from_select_add;
    //====================================== 问题及意见 ==========================================
    @FXML
    TableView question_now_table;
    @FXML
    TableView question_other_table;
    @FXML
    TableColumn col_now_id;
    @FXML
    TableColumn col_now_content;
    @FXML
    TableColumn col_other_id;
    @FXML
    TableColumn col_other_content;

    //============================================== 菜单 ===============================================
    @FXML
    VBox menu_clearAll;
    @FXML
    VBox menu_commit;
    @FXML
    VBox menu_insert;
    @FXML
    VBox menu_update;
    @FXML
    VBox menu_delete;
    @FXML
    VBox menu_printing;

    String str = "";
    static List<String> stringList = new ArrayList<>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    CheckBox box;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clearControllerVal();

        // 设置某些菜单状态
        setMenu(true);

        setMenuValue(1);

        //查询所有面板中的信息
        selectAllPane();

        // 加载生产类别的复选框
        setProductionCateGoryCheckBox();
    }

    /**
     * 显示车床tableview
     */
    public void showCarBed(){
        String maintainid = create_no.getUserData()==null?"":create_no.getUserData().toString();
        car_table.setEditable(true);
        car_table.setItems(null);
        if(!"".equals(maintainid) && maintainid != null){
            List<CustomerDataMaintainCar> carlist = iCustomerDataMaintainCarService.listCustomerDataMaintainCarByMaintainId(Long.valueOf(maintainid));
            List<CustomerDataMaintainCarProperty> carPropertyList = new ArrayList<>();
            if(carlist!= null){
                carlist.forEach(p-> carPropertyList.add(new CustomerDataMaintainCarProperty(p.getId(), p.getMaintainId(), p.getLatheType(), p.getBrand(), p.getModels(), p.getNum().toString())) );
            }

            col_car_brand.setCellFactory(column -> EditCell.createStringEditCell());
            col_car_num.setCellFactory(column -> EditCell.createStringEditCell());
            col_car_lathe_type.setCellFactory(column -> EditCell.createStringEditCell());
            col_car_models.setCellFactory(column -> EditCell.createStringEditCell());

            ObservableList<CustomerDataMaintainCarProperty> data = FXCollections.observableArrayList(carPropertyList);
            col_car_id.setCellValueFactory(new PropertyValueFactory("id"));
            col_car_brand.setCellValueFactory(new PropertyValueFactory("brand"));
            col_car_num.setCellValueFactory(new PropertyValueFactory("num"));//映射
            col_car_lathe_type.setCellValueFactory(new PropertyValueFactory("latheType"));
            col_car_models.setCellValueFactory(new PropertyValueFactory<Customer,Date>("models"));

            car_table.setItems(data);
        }

    }

    /**
     * 加载生产类别的复选框
     */
    private void setProductionCateGoryCheckBox(){
        production_checkbox_pan.getChildren().clear();
        List<DataSetting> dataSettingList = iDataSettingService.findDataSetting(35L);   //生产类别

        for (int i = 0; i < dataSettingList.size(); i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setText(dataSettingList.get(i).getTitle());
            checkBox.setUserData(dataSettingList.get(i).getTitle());
            checkBox.setPrefWidth(120L);
            if (i > 0) {
                int r = i / 5;
                checkBox.setLayoutX(120L * (i % 5));
                checkBox.setLayoutY(r * 25L);
            }
            production_checkbox_pan.getChildren().add(checkBox);

            checkBox.selectedProperty().addListener
                    (new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                            if(new_val){
                                if(checkBox.getUserData() != null){
                                    stringList.add(checkBox.getUserData().toString());
                                }
                            }else {
                                stringList.remove(checkBox.getUserData().toString());
                            }
                        }
                    });
        }
    }

    /**
     * 设置菜单按钮状态
     * @param state
     */
    private void setMenu(boolean state){
        menu_clearAll.setDisable(state);
        menu_commit.setDisable(state);
        menu_insert.setDisable(false);
        menu_delete.setDisable(state);
    }

    /**
     * 获取选中的checkbox
     */
    @FXML
    private void calculateCheckbox() {
        production_type.clear();
        StringBuffer buffer = new StringBuffer();
        if(stringList != null && stringList.size() > 0){
            stringList.forEach(p->{
                buffer.append(p);
                buffer.append(",");
            });
            production_type.setText(new String(buffer));
        }
    }

    /**
     * 新增
     */
    public void add(){
        updateState();
        clearControllerVal();
        create_no.setText(new Date().getTime()+"");
        createPeople(create_people, create_people_str);
    }

    /**
     * 修改--激活控件到可编辑状态
     */
    @FXML
    private void updateState() {
        setMainControls(false);
        setBasicControls(false);
        setInfoControls(false);
        setManufactureControls(false);
        setQuestionControls(false);
        // 设置某些菜单状态
        setMenu(false);
    }

    /**
     * 修改--设置控件不可编辑
     */
    @FXML
    private void updateStateDisable() {
        setMainControls(true);
        setBasicControls(true);
        setInfoControls(true);
        setManufactureControls(true);
        setQuestionControls(true);
        // 设置某些菜单状态
        setMenu(true);
    }

    /**
     * 设置问题及意见控件状态（可编辑、不可编辑）
     * @param state
     */
    private void setQuestionControls(boolean state){
        question_now_table.setDisable(state);
        question_other_table.setDisable(state);
    }


    /**
     * 设置生产资料信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setManufactureControls(boolean state) {
        production_type.setDisable(state);
        vendor.setDisable(state);
        factory.setDisable(state);
        btn_from_select_add.setDisable(state);
    }

    /**
     * 设置详细信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setInfoControls(boolean state) {
        start_year.setDisable(state);
        annual_turnover.setDisable(state);
        employee_num.setDisable(state);
        budget.setDisable(state);
        car_table.setDisable(state);
        che_general_oem.setDisable(state);
        che_oem.setDisable(state);
        che_production.setDisable(state);
    }

    /**
     * 设置基本信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setBasicControls(boolean state) {
        leader_people.setDisable(state);
        contacts.setDisable(state);
        fax.setDisable(state);
        delivery_address.setDisable(state);
        invoice_address.setDisable(state);
        create_people.setDisable(true);
        create_people_str.setDisable(true);
        update_people.setDisable(true);
        update_people_str.setDisable(true);
        purchase.setDisable(state);
        phone.setDisable(state);
    }

    /**
     * 设置主要信息控件状态（可编辑、不可编辑）
     *
     * @param state
     */
    private void setMainControls(boolean state) {
        create_date.setDisable(state);
        customer_no.setDisable(state);
        customer_no_str.setDisable(state);
        customer_name.setDisable(state);
        create_no.setDisable(state);

        btn_create_no.setDisable(state);
        btn_customer_no.setDisable(state);
    }

    /**
     * 获取问题及意见
     */
    public void getQuestionVal() {
        question_now_table.setEditable(true);
        question_other_table.setEditable(true);
        question_now_table.setItems(null);
        question_other_table.setItems(null);

        if(create_no.getUserData() != null && !"".equals(create_no.getUserData().toString())){
            List<CustomerDataMaintainQuestion> nowlist = iCustomerDataMaintainQuestionService.listCustomerDataMaintainQuestionByType(Long.valueOf(create_no.getUserData().toString()), 1);
            List<CustomerDataMaintainQuestion> otherlist = iCustomerDataMaintainQuestionService.listCustomerDataMaintainQuestionByType(Long.valueOf(create_no.getUserData().toString()), 2);

            col_now_content.setCellFactory(column -> EditCell.createStringEditCell());
            col_other_content.setCellFactory(column -> EditCell.createStringEditCell());

            // 查询地址集合
            col_now_id.setCellValueFactory(new PropertyValueFactory<CustomerDemandGoodsInfo, Long>("id"));
            col_now_content.setCellValueFactory(new PropertyValueFactory("content"));
            List<CustomerDataMaintainQuestionProperty> questionPropertyList = new ArrayList<>();
            for (CustomerDataMaintainQuestion p : nowlist) {
                questionPropertyList.add(new CustomerDataMaintainQuestionProperty(p.getId(), p.getType().toString(), p.getContent()));
            }
            question_now_table.setItems(FXCollections.observableArrayList(questionPropertyList));

            col_other_id.setCellValueFactory(new PropertyValueFactory<CustomerDemandGoodsInfo, Long>("id"));
            col_other_content.setCellValueFactory(new PropertyValueFactory("content"));
            List<CustomerDataMaintainQuestionProperty> questionPropertyList2 = new ArrayList<>();
            for (CustomerDataMaintainQuestion p : otherlist) {
                questionPropertyList2.add(new CustomerDataMaintainQuestionProperty(p.getId(), p.getType().toString(), p.getContent()));
            }
            question_other_table.setItems(FXCollections.observableArrayList(questionPropertyList2));

        }

    }



    /**
     * 保存修改
     */
    @FXML
    private void save() {
        CustomerDataMaintain maintain = new CustomerDataMaintain();

        lastUpdatePeopel(update_people, update_people_str);
        if(create_date.getValue() != null){
            try {
                Date date = df.parse(create_date.getValue().toString());
                maintain.setCreateDate(date);
            } catch (Exception e) {
                maintain.setCreateDate(new Date());
            }
        }
        if(create_no.getUserData() != null && !"".equals(create_no.getUserData().toString())){
            maintain.setId(Long.valueOf(create_no.getUserData().toString()));
        }
        maintain.setCustomerNo(customer_no.getText());
        maintain.setCustomerNoStr(customer_no_str.getText());
        maintain.setCustomerName(customer_name.getText());
        maintain.setCreateNo(create_no.getText());
        maintain.setLeaderPeople(leader_people.getText());
        maintain.setContacts(contacts.getText());
        maintain.setFax(fax.getText());
        maintain.setDeliveryAddress(delivery_address.getText());
        maintain.setInvoiceAddress(invoice_address.getText());
        maintain.setCreatePeople(create_people.getText());
        maintain.setCreatePeopleStr(create_people_str.getText());
        maintain.setUpdatePeople(update_people.getText());
        maintain.setUpdatePeopleStr(update_people_str.getText());
        maintain.setPurchase(purchase.getText());
        maintain.setPhone(phone.getText());
        maintain.setStartYear(start_year.getText());
        maintain.setAnnualTurnover(annual_turnover.getText());
        maintain.setEmployeeNum(employee_num.getText());
        maintain.setBudget(budget.getText());
        String str = "";
        if (che_production.isSelected()) {
            str += che_production.getText() + ",";
        }
        if (che_oem.isSelected()) {
            str += che_oem.getText() + ",";
        }
        if (che_general_oem.isSelected()) {
            str += che_general_oem.getText() + ",";
        }
        maintain.setIndustry(str);
        maintain.setProductionType(production_type.getText());
        maintain.setVendor(vendor.getText());
        maintain.setFactory(factory.getText());
        if(maintain.getId() == null || maintain.getId() == 0){
            maintain.setAddtime(new Date());
            // 保存
            int rows = iCustomerDataMaintainService.save(maintain);
            create_no.setUserData(maintain.getId().toString());
            returnMsg(rows);
        }else{
            // 修改
            int rows = iCustomerDataMaintainService.updateNotNull(maintain);
            returnMsg(rows);
        }
        saveTableviewQuestion();
        saveTableviewQuestionNow();
        saveTableviewCar();
        getQuestionVal(); // 刷新现有、其它问题列表
        updateStateDisable();
    }



    /**
     * 清空详情
     */
    public void clearDetailInfoVal() {
        start_year.clear();
        annual_turnover.clear();
        employee_num.clear();
        budget.clear();
        che_production.setSelected(false);
        che_oem.setSelected(false);
        che_general_oem.setSelected(false);
    }

    /**
     * 刷新所有面板信息
     */
    private void selectAllPane(){

        //车床信息
        showCarBed();
        // 问题及意见
        getQuestionVal();

        updateStateDisable();
        //权限管理
        matchingPermissions("客户资料维护",menu_insert,menu_delete,menu_update,menu_printing,menu_clearAll);

    }



    /****************************** 主要部分 *****************************/



    /**
     * 根据id查询客户资料维护主要信息
     */
    public void getMaintainMainInfoById() {
        String idStr = create_no.getUserData()==null?"":create_no.getUserData().toString();
        if (!"".equals(idStr)) {
            CustomerDemandGoods customerDataMaintain = iCustomerDemandGoodsService.selectByKey(Long.valueOf(idStr));
            setdemandVal(customerDataMaintain);
        }
    }



    /**
     * 给翻页菜单赋值
     * @param page
     */
    private void setMenuValue(int page){
        List<CustomerDataMaintain> list = iCustomerDataMaintainService.listCustomerDataMaintain("",page, 1);
        if(list != null && list.size() >0){
            PageInfo<CustomerDataMaintain> pageInfo = new PageInfo<>(list);
            menu_first.setUserData(pageInfo.getFirstPage());
            menu_prev.setUserData(pageInfo.getPrePage());
            menu_next.setUserData(pageInfo.getNextPage());
            menu_last.setUserData(pageInfo.getLastPage());
            setBasicVal(list.get(0));
        }else {
            setBasicVal(null);
        }
    }


    /**
     * 分页
     * @param event
     */
    @FXML
    public void pages(MouseEvent event){
        Node node =(Node)event.getSource();
        if(node.getUserData() != null){
            int page =Integer.parseInt(String.valueOf(node.getUserData()));
            setMenuValue(page);
        }
    }



    /**
     * 主要信息赋值--从客户资料维护获取
     *
     * @param maintain
     */
    public void setBasicVal(CustomerDataMaintain maintain) {
        clearControllerVal();
        if (maintain != null) {
            if (maintain.getId() != null && maintain.getId() != 0L) {
                create_no.setUserData(maintain.getId().toString());
            }
            if (!"".equals(maintain.getCreateDate()) && maintain.getCreateDate() != null) {
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(maintain.getCreateDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
            }
            create_no.setText(maintain.getCreateNo());
            customer_no.setText(maintain.getCustomerNo());
            customer_no_str.setText(maintain.getCustomerNoStr());
            customer_name.setText(maintain.getCustomerName());

            // 基本信息
            contacts.setText(maintain.getContacts());
            create_people.setText(maintain.getCreatePeople());
            create_people_str.setText(maintain.getCreatePeopleStr());
            delivery_address.setText(maintain.getDeliveryAddress());
            fax.setText(maintain.getFax());
            invoice_address.setText(maintain.getInvoiceAddress());
            leader_people.setText(maintain.getLeaderPeople());
            phone.setText(maintain.getPhone());
            purchase.setText(maintain.getPurchase());
            update_people.setText(maintain.getUpdatePeople());
            update_people_str.setText(maintain.getUpdatePeopleStr());

            //详细信息
            start_year.setText(maintain.getStartYear());
            annual_turnover.setText(maintain.getAnnualTurnover());
            employee_num.setText(maintain.getEmployeeNum());
            budget.setText(maintain.getBudget());
            if (maintain.getIndustry() != null && !"".equals(maintain.getIndustry())) {
                String ids[] = maintain.getIndustry().split(",");
                for (int i = 0; i < ids.length; i++) {
                    if ("生产".equals(ids[i])) {
                        che_production.setSelected(true);
                    } else if ("专门代工".equals(ids[i])) {
                        che_oem.setSelected(true);
                    } else if ("一般代工".equals(ids[i])) {
                        che_general_oem.setSelected(true);
                    }
                }
            }
            production_type.setText(maintain.getProductionType());
            vendor.setText(maintain.getVendor());
            factory.setText(maintain.getFactory());
            selectAllPane();
        }
    }

    /**
     * 主要信息赋值--从客户需求商品获取
     *
     * @param demand
     */
    public void setdemandVal(CustomerDemandGoods demand) {
        if (demand != null) {
            if (demand.getId() != null && demand.getId() != 0L) {
                demandid.setText(demand.getId().toString());
            }
            if (!"".equals(demand.getCreateDate()) && demand.getCreateDate() != null) {
                create_date.setValue(LocalDate.parse(DateUtils.getSpecifyDate(demand.getCreateDate(), DateUtils.FORMAT_YYYY_MM_DD), formatter));
            }
            if (!"".equals(demand.getCustomerNo())) {
                customer_no.setText(demand.getCustomerNo());
            }
            if (!"".equals(demand.getCustomerNoStr())) {
                customer_no_str.setText(demand.getCustomerNoStr());
            }
            if (!"".equals(demand.getCustomerName())) {
                customer_name.setText(demand.getCustomerName());
            }
            if (!"".equals(demand.getCreateNo())) {
                create_no.setText(demand.getCreateNo());
            }
        }
    }

    /**
     * @Description 清除所有内容
     * @Author BlueSky
     * @Date 18:40 2019/4/29
     **/
    @FXML
    public void clearControllerVal() {
        create_no.setUserData(null);
        customer_id.clear();
        demandid.clear();
        clearMain();
        clearDetailInfoVal();
        clearBasic();
        clearInfo();
        clearProduction();
        clearQuestion();
    }

    /**
     * 清空主要部分值
     */
    public void clearMain() {
        create_date.setValue(LocalDateTime.now().toLocalDate());
        customer_no.clear();
        customer_no_str.clear();
        customer_name.clear();
        create_no.clear();
        create_no.setUserData(null);
    }

    /**
     * 清空基本信息的内容
     */
    public void clearBasic() {
        leader_people.clear();
        contacts.clear();
        fax.clear();
        delivery_address.clear();
        invoice_address.clear();
        create_people.clear();
        create_people_str.clear();
        update_people.clear();
        update_people_str.clear();
        purchase.clear();
        phone.clear();
    }

    /**
     * 清除详细信息
     */
    public void clearInfo() {
        start_year.clear();
        annual_turnover.clear();
        employee_num.clear();
        budget.clear();
        car_table.setItems(null);
        car_table.refresh();
    }

    /**
     * 清除生产资料信息
     */
    public void clearProduction() {
        production_type.clear();
        vendor.clear();
        factory.clear();
    }

    /**
     * 清除问题及意见内容
     */
    public void clearQuestion() {
        question_now_table.setItems(null);
        question_now_table.refresh();
        question_other_table.setItems(null);
        question_other_table.refresh();
    }




    /**
     * 车床 view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfCarTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addCarRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfCar();
        }
    }

    /**
     * 行添加 车床
     */
    public void addCarRow() {


        ObservableList<CustomerDataMaintainCarProperty> list = car_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new CustomerDataMaintainCarProperty("", "", "", ""));
        car_table.setItems(list);
    }


    /**
     * 行删除 车床
     */
    private void deleteRowOfCar(){
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            // 取得当前行的数据
            try {
                TablePosition pos = (TablePosition) car_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                CustomerDataMaintainCarProperty property = (CustomerDataMaintainCarProperty)car_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iCustomerDataMaintainCarService.delete(property.getId());
                    returnMsg(rows);
                }
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {

        }

    }


    /**
     * 保存 车床 tableview数据
     */
    private void saveTableviewCar(){
        String ids = create_no.getUserData()==null?"":create_no.getUserData().toString();
        if(ids != null && !"".equals(ids) && car_table.getItems() != null){
            int tableSize = car_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                try {
                    CustomerDataMaintainCarProperty property = null;
                    if(car_table.getItems().get(i) != null){
                        property = (CustomerDataMaintainCarProperty)car_table.getItems().get(i);
                    }
                    CustomerDataMaintainCar product = new CustomerDataMaintainCar();
                    if(property.getBrand() != null && !"".equals(property.getBrand())){
                        product.setBrand(property.getBrand());
                    }
                    if(property.getLatheType() != null && !"".equals(property.getLatheType())){
                        product.setLatheType(property.getLatheType());
                    }
                    if(property.getModels() != null && !"".equals(property.getModels())){
                        product.setModels(property.getModels());
                    }
                    if(property.getNum() != null && !"".equals(property.getNum())){
                        product.setNum(Integer.valueOf(property.getNum()));
                    }
                    product.setMaintainId(Long.valueOf(ids));
                    if(property.getId() == null || property.getId() == 0){
                        try {
                            product.setAddtime(new Date());
                            iCustomerDataMaintainCarService.save(product);
                            showCarBed();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            product.setId(property.getId());
                            iCustomerDataMaintainCarService.updateNotNull(product);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 现有加工问题 view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfQuestionNowTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addQuestionNowRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfQuestionNow();
            getQuestionVal();
        }
    }

    /**
     * 行添加 现有加工问题
     */
    public void addQuestionNowRow() {

        ObservableList<CustomerDataMaintainQuestionProperty> list = question_now_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new CustomerDataMaintainQuestionProperty(""));
        question_now_table.setItems(list);
    }


    /**
     * 行删除 现有加工问题
     */
    private void deleteRowOfQuestionNow(){
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            // 取得当前行的数据
            try {
                if(question_now_table.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) question_now_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                CustomerDataMaintainQuestionProperty property = (CustomerDataMaintainQuestionProperty)question_now_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iCustomerDataMaintainQuestionService.delete(property.getId());
                    returnMsg(rows);
                }
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }


    /**
     * 保存 现有加工问题 tableview数据
     */
    private void saveTableviewQuestionNow(){
        String ids = create_no.getUserData()==null?"":create_no.getUserData().toString();
        if(ids != null && !"".equals(ids) && question_now_table.getItems() != null){
            int tableSize = question_now_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                try {
                    CustomerDataMaintainQuestionProperty property = null;
                    if(question_now_table.getItems().get(i) != null){
                        property = (CustomerDataMaintainQuestionProperty)question_now_table.getItems().get(i);
                    }
                    CustomerDataMaintainQuestion product = new CustomerDataMaintainQuestion();
                    if(property.getContent() != null && !"".equals(property.getContent())){
                        product.setContent(property.getContent());
                    }
                    // 现有问题
                    product.setType(1);
                    product.setMaintainId(Long.valueOf(ids));
                    if(property.getId() == null || property.getId() == 0){
                        try {
                            product.setAddtime(new Date());
                            iCustomerDataMaintainQuestionService.save(product);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            product.setId(property.getId());
                            iCustomerDataMaintainQuestionService.updateNotNull(product);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 其它问题 view
     * enter 键盘按下触发
     * @param event
     */
    @FXML
    public void KeyOfQuestionTableView(KeyEvent event) {
        if (event.getCode() == KeyCode.INSERT) {
            addQuestionRow();
        }
        if (event.getCode() == KeyCode.DELETE) {
            deleteRowOfQuestion();
            getQuestionVal();
        }
    }

    /**
     * 行添加 其它问题
     */
    public void addQuestionRow() {

        ObservableList<CustomerDataMaintainQuestionProperty> list = question_other_table.getItems();

        if (list == null) {
            list = FXCollections.observableArrayList();
        }
        list.add(new CustomerDataMaintainQuestionProperty(""));
        question_other_table.setItems(list);
    }


    /**
     * 行删除 其它问题
     */
    private void deleteRowOfQuestion(){
        if (f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)) {
            // 取得当前行的数据
            try {
                if(question_other_table.getSelectionModel().getSelectedCells().size() == 0){
                    return;
                }
                TablePosition pos = (TablePosition) question_other_table.getSelectionModel().getSelectedCells().get(0);
                int index = pos.getRow();
                CustomerDataMaintainQuestionProperty property = (CustomerDataMaintainQuestionProperty)question_other_table.getItems().get(index);
                if(property.getId() != null && property.getId() != 0L){
                    int rows = iCustomerDataMaintainQuestionService.delete(property.getId());
                    returnMsg(rows);
                }
            }catch (Exception e){
                alert_informationDialog("请选择需要删除的行！");
                e.printStackTrace();
            }
        }


    }


    /**
     * 保存 其它问题 tableview数据
     */
    private void saveTableviewQuestion(){
        String ids = create_no.getUserData()==null?"":create_no.getUserData().toString();
        if(ids != null && !"".equals(ids) && question_other_table.getItems() != null){
            int tableSize = question_other_table.getItems().size();
            for (int i = 0; i < tableSize; i++) {
                try {
                    CustomerDataMaintainQuestionProperty property = null;
                    if(question_other_table.getItems().get(i) != null){
                        property = (CustomerDataMaintainQuestionProperty)question_other_table.getItems().get(i);
                    }
                    CustomerDataMaintainQuestion product = new CustomerDataMaintainQuestion();
                    if(property.getContent() != null && !"".equals(property.getContent())){
                        product.setContent(property.getContent());
                    }
                    // 现有问题
                    product.setType(2);
                    product.setMaintainId(Long.valueOf(ids));
                    if(property.getId() == null || property.getId() == 0){
                        try {
                            product.setAddtime(new Date());
                            iCustomerDataMaintainQuestionService.save(product);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            product.setId(property.getId());
                            iCustomerDataMaintainQuestionService.updateNotNull(product);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description 删除
     * @Author BlueSky
     * @Date 1:04 2019/4/29
     **/
    @FXML
    public void delete(){
        if(f_alert_confirmDialog(AppConst.ALERT_HEADER, AppConst.ALERT_DELETE)){
            if(create_no.getUserData() != null && !"".equals(create_no.getUserData().toString())){
                int rows = iCustomerDataMaintainService.delete(Long.valueOf(create_no.getUserData().toString()));
                iCustomerDataMaintainQuestionService.deleteCustomerDataMaintainQuestionByCustomerDemandId(Long.valueOf(create_no.getUserData().toString()));
                iCustomerDataMaintainCarService.deleteCustomerDataMaintainCarByMaintainId(Long.valueOf(create_no.getUserData().toString()));
                iCustomerDataMaintainProductionService.deleteCustomerDataMaintainProductionByMaintainId(Long.valueOf(create_no.getUserData().toString()));
                returnMsg(rows);
                setMenuValue(1);
            }
        }
    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //TODO  现有客户查询
     * @Date 20:22 2018/8/15
     * @Param []
     **/
    @FXML
    public void CurrentClientQuery() {
        Stage stage = new Stage();
        Pane pane = new Pane();

        //将第二个窗口保存到map中
        StageManager.STAGE.put("CustomerDataMaintenance", stage);
        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerDataMaintenanceController", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/current_client_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @return void
     * @Author BlueSky
     * @Description //建档编号（客户需求商品查询）
     * @Date 20:22 2018/8/15
     * @Param []
     **/
    @FXML
    public void CustomerDemandGoodsQuery() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        stage.setTitle("客户资料维护查询");

        //将本窗口保存到map中
        StageManager.CONTROLLER.put("CustomerDataMaintenanceMini", this);

        pane.getChildren().setAll(loader.load("/fxml/customer/customer_data_maintenance_query_mini.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

}
