package com.yc.education.controller.sale;

import com.yc.education.controller.BaseController;
import com.yc.education.util.AppConst;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import org.springframework.stereotype.Controller;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Description 销售 - 相关报表 - 销退统计表
 * @Author BlueSky
 * @Date 19:15 2019/4/28
 **/
@Controller
public class ReportSaleReturnStatisticsController extends BaseController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 预览
     */
    @FXML
    @SneakyThrows
    public synchronized void  openWebviewWindow() {
        Stage stage = new Stage();

        URL url = this.getClass().getResource("/jasper/sale_return_statistics_customer.jasper");

        String str = url==null?null:url.toString().substring(url.toString().indexOf(':')+1, url.toString().length());
        String filePath = str.substring(0, str.lastIndexOf("/"));
        String fileName = str.substring(str.lastIndexOf("/"), str.length()).split("\\.")[0];
        //传入报表源文件绝对路径，外部参数对象，DB连接，得到JasperPring对象
        Map<String,Object> maps = new HashMap<>();
        maps.put("createPeople", "全部");
        maps.put("company", "全部");
        maps.put("customerNo", "全部");
        maps.put("customerArea", "全部");
        maps.put("creataDate", "全部");
        maps.put("date", "全部");
        maps.put("business", "全部");
        maps.put("warehouseNo", "全部");
        maps.put("orderNo", "全部");
        JasperPrint jasperPrint = JasperFillManager.fillReport(str, maps, AppConst.getConnection());

        //导出HTML文件
        JasperExportManager.exportReportToHtmlFile(jasperPrint, filePath+fileName+".html");

        stage.setTitle("销退统计表");
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load(this.getClass().getResource("/jasper"+fileName+".html").toExternalForm());

        Scene scene = new Scene(webView, 10, 10, Color.web("lightgray"));

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setScene(scene);
        stage.show();
    }
    
}
