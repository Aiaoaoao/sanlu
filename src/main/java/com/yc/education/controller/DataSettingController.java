package com.yc.education.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.yc.education.model.DataSetting;
import com.yc.education.model.DataSettingProperty;
import com.yc.education.model.basic.SupplierContactProperty;
import com.yc.education.service.DataSettingService;
import com.yc.education.util.EditCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName DataSettingController
 * @Description TODO  资料设定Controller
 * @Author QuZhangJing
 * @Date 2018/9/26 16:36
 * @Version 1.0
 */
@Controller
public class DataSettingController extends BaseController implements Initializable {

    @Autowired
    private DataSettingService dataSettingService;

    @FXML
    private AnchorPane radioGroup;

    private final ToggleGroup toggleGroup = new ToggleGroup();


    @FXML
    private TableView tableView1;

    @FXML
    private TableColumn sort; //序号

    @FXML
    private TableColumn title;//类型名称

    @FXML
    private TableColumn remarks;//备注

    private long contactNum=0;

    @FXML
    private Button yesBtn; //应用
    @FXML
    private Button updateBtn; //修改
    @FXML
    private Button sureBtn; //确定
    @FXML
    private Button closeBtn; //取消

    TableColumn tableColumn = new TableColumn("父级仓库");

    //TabelView之数据绑定
    private final ObservableList<DataSettingProperty> dataSettingPropertys = FXCollections.observableArrayList();


    //绑定数据
    public void bindData(){

        List<DataSetting> dataSettings = dataSettingService.findDataSetting(0L);
        List<Node> nodes = new ArrayList<>();
        if(dataSettings != null){
            Double layoutY = 20.0;
            Double prefHeight = 60.0;
            for(int i=0,len=dataSettings.size();i<len;i++){
                JFXRadioButton jfxRadioButton = new JFXRadioButton();
                jfxRadioButton.setLayoutX(20.0);
                jfxRadioButton.setLayoutY(layoutY);
                jfxRadioButton.setUserData(dataSettings.get(i).getId());
                if(i==0){
                    jfxRadioButton.setSelected(true);
                    bingChildren(dataSettings.get(i).getId());
                }
                jfxRadioButton.setText(dataSettings.get(i).getTitle());
                jfxRadioButton.setToggleGroup(toggleGroup);
                nodes.add(jfxRadioButton);
                radioGroup.setPrefHeight(prefHeight);
                layoutY += 25.0;
                prefHeight += 25.0;

            }
            radioGroup.getChildren().addAll(nodes);

        }
    }


    static ObservableList<String> levelChoice = FXCollections.observableArrayList();

    public static ObservableList<String> getLevelChoice() {
        return levelChoice;
    }

    public static void setLevelChoice(ObservableList<String> levelChoice) {
        DataSettingController.levelChoice = levelChoice;
    }



    /**
     * 绑定类型
     * @param id
     */
    public void bingChildren(long id){

        tableView1.setUserData(id);

        List<DataSetting> dataSettings = dataSettingService.findDataSetting(id);

       /* sort.setCellFactory(TextFieldTableCell.forTableColumn());*/
        title.setCellFactory(TextFieldTableCell.forTableColumn());
        remarks.setCellFactory(TextFieldTableCell.forTableColumn());

        sort.setCellValueFactory(new PropertyValueFactory("sort"));
        title.setCellValueFactory(new PropertyValueFactory("title"));
        remarks.setCellValueFactory(new PropertyValueFactory("remarks"));


        dataSettingPropertys.clear();


        List<DataSetting> dataSettingList = dataSettingService.findDataSettingByPrevId(0L,33L);

        String defaultPrev = "无";

        boolean flag = true;

        if(dataSettings.size()>0){
            for (DataSetting dataSetting:dataSettings) {
                //仓库
                if(dataSetting.getParentid() == 33 && flag){

                    flag =  !flag; //每次只允许进入一次

                    tableView1.getColumns().remove(tableColumn);

                    tableView1.getColumns().add(tableColumn);

                    tableColumn.setCellValueFactory(new PropertyValueFactory("prev"));

                    tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

                    levelChoice.clear();

                    // 地址类型
                    for (int i = 0; i < dataSettingList.size(); i++) {
                        if(i==0)levelChoice.add("无");
                        levelChoice.add(dataSettingList.get(i).getTitle());
                    }
                    tableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(levelChoice));

                }else {
                    if (tableView1.getColumns().size() > 3 && flag) tableView1.getColumns().remove(3);
                }

                if(dataSetting.getPrevid() != 0){

                    DataSetting dataSetting1 = dataSettingService.selectByKey(dataSetting.getPrevid());

                    defaultPrev = dataSetting1.getTitle();
                }

                DataSettingProperty dataSettingProperty = new DataSettingProperty(dataSetting.getId(),dataSetting.getSort().toString(),dataSetting.getTitle(),dataSetting.getRemarks(),defaultPrev);
                dataSettingPropertys.add(dataSettingProperty);
            }
        }
        tableView1.setItems(dataSettingPropertys); //tableview添加dataSettingPropertys


        tableView1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DataSettingProperty>() {
            @Override
            public void changed(ObservableValue<? extends DataSettingProperty> observableValue, DataSettingProperty oldItem, DataSettingProperty newItem) {
                if(newItem.getId() >0){
                    contactNum=newItem.getId();
                }
            }
        });


        yesBtn.setDisable(true);
        sureBtn.setDisable(true);

    }


    /**
     * 添加空白行
     */
    public void insertBlank(){
        DataSettingProperty dataSettingProperty = new DataSettingProperty();
        dataSettingPropertys.add(dataSettingProperty);
    }



    /**
     * 修改按钮
     */
    public void updateBtn(){

        insertBlank();

        yesBtn.setDisable(false);
        sureBtn.setDisable(false);
        updateBtn.setDisable(true);

        tableView1.setEditable(true);
    }


    /**
     * 确认 和 应用按钮
     */
    public void submitBtn(){

        yesBtn.setDisable(true);
        sureBtn.setDisable(true);
        updateBtn.setDisable(false);
        tableView1.setEditable(false);

            long parentid = Long.parseLong(tableView1.getUserData().toString());

            int sort =0;

            long previd = 0L;


        for (DataSettingProperty dataSettingProperty :dataSettingPropertys) {

            DataSetting dataSettingPrev =  dataSettingService.findDataSettingByName(dataSettingProperty.getPrev());

            if(dataSettingPrev != null)previd = dataSettingPrev.getId();

         if(!"".equals(dataSettingProperty.getTitle()) && dataSettingProperty.getTitle() != null){

             if(!"".equals(dataSettingProperty.getSort()) && dataSettingProperty.getSort() != null ){
                 DataSetting dataSetting = new DataSetting(Integer.parseInt(dataSettingProperty.getSort()),dataSettingProperty.getTitle(),dataSettingProperty.getRemarks(),parentid,previd);
                 sort=Integer.parseInt(dataSettingProperty.getSort());
                 dataSettingService.updateDataSetting(dataSetting);
             }else{
                 /**
                  * 权限设置 资料设定 新增 code：5_124_1
                  */
                if(getPermissions("5_124_1")){
                    DataSetting dataSetting = new DataSetting(++sort,dataSettingProperty.getTitle(),dataSettingProperty.getRemarks(),parentid,previd);
                    dataSettingProperty.setSort(sort+"");
                    dataSettingService.save(dataSetting);
                }

             }

         }

        }

    }

    /**
     * 关闭窗体
     */
    public void closeBtn(){
        Stage stage =(Stage)tableView1.getScene().getWindow();
        stage.close();
    }

    public void tableView1Key(KeyEvent event){

        if (event.getCode() == KeyCode.DELETE) {


             if(f_alert_confirmDialog(" ","是否删除!")){
                 dataSettingService.delete(contactNum);


                 ObservableList<DataSettingProperty> dataSettingProperties = FXCollections.observableArrayList();

                 for (DataSettingProperty dataSettingProperty : dataSettingPropertys){
                     if(dataSettingProperty.getId() != contactNum){
                         dataSettingProperties .add(dataSettingProperty);
                     }
                 }

                 dataSettingPropertys.clear();
                 dataSettingPropertys.setAll(dataSettingProperties);
             }

        }

        if (event.getCode() == KeyCode.INSERT) {

            //联系人空白行
            insertBlank();

        }


    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bindData();

        toggleGroup.selectedToggleProperty().addListener(    (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
            if (toggleGroup.getSelectedToggle() != null) {
                JFXRadioButton jfxRadioButton =(JFXRadioButton)toggleGroup.getSelectedToggle();
                bingChildren(Long.parseLong(jfxRadioButton.getUserData().toString()));
            }
        });

        /**
         * 权限 资料设置 修改 code: 5_125_3
         */
        if(!getPermissions("5_125_3")){
            updateBtn.setDisable(true);
        }

    }



}
