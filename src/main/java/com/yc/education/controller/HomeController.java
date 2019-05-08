package com.yc.education.controller;

import com.yc.education.application.ScreenManager;
import com.yc.education.constants.SpringFxmlLoader;
import com.yc.education.util.DragUtil;
import com.yc.education.util.StageManager;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName HomeController
 * @Description TODO home  首页
 * @Author QuZhangJing
 * @Date 2018-08-07 13:48
 * @Version 1.0
 */
@Controller
public class HomeController extends BaseController implements Initializable {

    @FXML private AnchorPane anPane;

    @FXML private Pane buttomPane;

    @FXML private MenuBar menuBarTop;

    @FXML private Pane homePane;

    @FXML private VBox homeVBox;

    @FXML private HBox centerHBox;

    @FXML private VBox btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;

    private VBox attrVbox ;

    private  String url="/fxml/sale_data.fxml"; //默认fxml

    private final String defaultColor="-fx-background-color:#EFF3F7;";  //默认按钮颜色

    private final String checkedColor=" -fx-background-color:#169252;"; //选中颜色

    private final String checkedFontColor=" -fx-text-fill:#FFF;"; //选中字体颜色

    private final String defaultFontColor=" -fx-text-fill: #8CA0B3;"; //默认字体颜色



    private static SpringFxmlLoader loader = new SpringFxmlLoader();

    private Stage stage = new Stage();



    @FXML
    private Label username;

    /**
     * 打开资料设定
     */
    public void openDataSetting(){
        showDataSetting();
    }


    /**
     * 用户密码修改 --查看 --- 1006_580_4
     */
    public void passWord(){
        if(getPermissions("1008_585_4")){
            stage.setTitle("修改密码");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/password.fxml"));
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
            stage.show();
        }
    }

    /**
     * 用户权限设置 --查看 --- 1006_580_4
     */
    public void permissions(){
        if(getPermissions("1006_580_4")){
            stage.setTitle("权限设置");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/permissions_set.fxml"));
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
            stage.show();
        }
    }



    public void taxRateSetting(){

        stage.setTitle("税率设置");
        Pane pane = new Pane();
        pane.getChildren().setAll(loader.load("/fxml/tax_rate.fxml"));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        /*stage.setResizable(false);*/
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
        stage.show();

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

    public  void checkedClass(Node node,int type){
        attrVbox = (VBox)node;

        if(type == 1){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/xsed.png');");
        }else if(type == 2){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/cged.png');");
        }else if(type == 3){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kced.png');");
        }else if(type == 4){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/zked.png');");
        }else if(type == 5){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/zled.png');");
        }else if(type == 6){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/khed.png');");
        }else if(type == 7){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/kqed.png');");
        }else if(type == 8){
            attrVbox.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/bbed.png');");
        }

        attrVbox.getChildren().get(1).setStyle(checkedFontColor);
    }


    public void btn1Click(Event event){

        Node node =(Node)event.getSource();
        Integer type=Integer.parseInt(node.getUserData().toString());

        removeClass();

        /**
         * 添加选中样式
         */
        node.setStyle(checkedColor);

        checkedClass(node,type);

        //销售
        if(type==1){ url="/fxml/sale_data.fxml"; }
        //采购
        if(type==2){url="/fxml/purchase_data.fxml";}
        //库存
        if(type==3){url="/fxml/inventory_data.fxml";}
        //账款
        if(type==4){url="/fxml/account_data.fxml";}
        //基本资料
        if(type==5){url="/fxml/basic_data.fxml";}
        //客户关系
        if(type==6){url="/fxml/customer_relation_data.fxml";}
        //考勤管理
        if(type==7){url="/fxml/check_data.fxml";}
        //统计汇总
        if(type==8){url="/fxml/baseline_data.fxml";}

        Pane pane = new Pane();

        pane.getChildren().setAll(loader.load(url));

//        node.getScene().getStylesheets().add(getClass().getResource("/css/home.css").toExternalForm());

        homePane.getChildren().setAll(pane);



    }


    public void changeWinSize(){
        System.err.println("====");

    }



    public void productStockQuery(){

        Stage stageStock = (Stage) StageManager.CONTROLLER.get("rightWinStage");
        if(!StageManager.rightWin){
            stageStock.setTitle("库存查询");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/right_stock_query.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            stage.setResizable(false);
//            stage.initStyle(StageStyle.UNDECORATED);
            DragUtil.addDragListener(stage, pane); //拖拽监听

            Stage stage = (Stage) StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stage.getScene().getWindow().getWidth()+stage.getScene().getWindow().getX()-15);
            stageStock.setY(stage.getScene().getWindow().getY());
        /*stage.initStyle(StageStyle.UNDECORATED);
        DragUtil.addDragListener(stage, pane); //拖拽监听*/
            stageStock.setResizable(false); //禁止窗体缩放
            stageStock.show();
        }else{
            stageStock.close();
        }

        StageManager.rightWin = !StageManager.rightWin;

    }


    /**
     * 待审核单据
     */
    public void stayToExamine(){
        Stage stageStock = (Stage) StageManager.CONTROLLER.get("rightWinStage");

        StageManager.rightWin = !StageManager.rightWin;

        if(StageManager.rightWin){
            stageStock.setTitle("待审核单据");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/right_stay_toexamine.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            stage.setResizable(false);
//            stage.initStyle(StageStyle.UNDECORATED);
            DragUtil.addDragListener(stage, pane); //拖拽监听

            Stage stage = (Stage) StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stage.getScene().getWindow().getWidth()+stage.getScene().getWindow().getX()-15);
            stageStock.setY(stage.getScene().getWindow().getY());
            stageStock.setResizable(false); //禁止窗体缩放
            stageStock.show();

        }else{
            stageStock.close();
        }


    }

    /**
     * 关联单据查询
     */
    public void relationOrders(){
        Stage stageStock = (Stage) StageManager.CONTROLLER.get("rightWinStage");

        StageManager.rightWin = !StageManager.rightWin;

        if(StageManager.rightWin){
            stageStock.setTitle("关联单据");
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load("/fxml/right_relation.fxml"));
            Scene scene = new Scene(pane);
            stageStock.setScene(scene);
            stage.setResizable(false);
//            stage.initStyle(StageStyle.UNDECORATED);
            DragUtil.addDragListener(stage, pane); //拖拽监听

            Stage stage = (Stage) StageManager.CONTROLLER.get("homeStage");

            stageStock.setX(stage.getScene().getWindow().getWidth()+stage.getScene().getWindow().getX()-15);
            stageStock.setY(stage.getScene().getWindow().getY());
            stageStock.setResizable(false); //禁止窗体缩放
            stageStock.show();

        }else{
            stageStock.close();
        }


    }





    //退出程序
    public void exitApplication(){
        Platform.exit();
    }


    //注销重新登录
    public void cancellation(){

         Stage stage = (Stage)homePane.getScene().getWindow();
         stage.close();

          stage = new Stage();

        ScreenManager screens = new ScreenManager();
        screens.setPrimaryStage(stage);
        screens.showStage();

    }

        /**
         * home 页面初始化
         * @param location
         * @param resources baseline_data
         */
        @Override
        public void initialize(URL location, ResourceBundle resources) {

//            anPane.setPrefWidth(Double.MAX_VALUE);


            StageManager.ORDERCONTENT.put("home",homePane);
            StageManager.CONTROLLER.put("anPane",anPane);
            StageManager.CONTROLLER.put("homeVBox",homeVBox);
            StageManager.CONTROLLER.put("centerHBox",centerHBox);

            username.setText(getAdminName());


            /**
             * 默认选中
             */
            btn1.setStyle(checkedColor);
            btn1.getChildren().get(0).setStyle(" -fx-background-image: url('/images/basic/xsed.png');");
            btn1.getChildren().get(1).setStyle(checkedFontColor);
            Pane pane = new Pane();
            pane.getChildren().setAll(loader.load(url));
            homePane.getChildren().setAll(pane);


            StageManager.CONTROLLER.put("homePaneUrl",homePane);

            StageManager.CONTROLLER.put("btn1",btn1);
            StageManager.CONTROLLER.put("btn2",btn2);
            StageManager.CONTROLLER.put("btn3",btn3);
            StageManager.CONTROLLER.put("btn4",btn4);
            StageManager.CONTROLLER.put("btn5",btn5);
            StageManager.CONTROLLER.put("btn6",btn6);
            StageManager.CONTROLLER.put("btn7",btn7);
            StageManager.CONTROLLER.put("btn8",btn8);

    }
}
