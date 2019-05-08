package com.yc.education.controller.sale;

import com.yc.education.controller.BaseController;
import com.yc.education.util.AppConst;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.view.JRViewer;
import org.springframework.stereotype.Controller;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @Description 销售 - 相关报表 - 销货统计表
 * @Author BlueSky
 * @Date 2019-03-05 14:40
 */
@Controller
public class ReportSaleStatisticsController extends BaseController implements Initializable {

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

        URL url = this.getClass().getResource("/jasper/sale_statistics_customer.jasper");

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
        maps.put("saleDate", "全部");
        maps.put("business", "全部");
        maps.put("warehouseNo", "全部");
        maps.put("saleNo", "全部");
        JasperPrint jasperPrint = JasperFillManager.fillReport(str, maps, AppConst.getConnection());

        //导出HTML文件
        JasperExportManager.exportReportToHtmlFile(jasperPrint, filePath+fileName+".html");

        stage.setTitle("销货统计表");
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



    /**
     * 导出 excel
     */
    @FXML
    @SneakyThrows
    public synchronized void exportExcel(){
        String exportURL = choseURL();
        if(exportURL != null){
            //开始导出文件

            String reportName = null;
            JasperPrint jasperPrint = null;
            Map<String ,Object> map = exportCommon("/jasper/sale_statistics_customer.jasper",null);
            if(map != null){
                reportName = map.get("reportName")==null?null:map.get("reportName").toString();
                jasperPrint = map.get("jasperPrint")==null?null:(JasperPrint) map.get("jasperPrint");
            }

            // 通过Jasper对象创建指定的Excel文件导出对象
            JExcelApiExporter exporter = new JExcelApiExporter();
            // 设置导出文件的相关信息，并且导出文件在指定目录下（在这里指reportName所指的的目录）

            reportName = exportURL+"\\"+reportName+".xls";
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,reportName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.exportReport();

        }else {
            System.err.println("用户取消了路径选择");
        }
    }

    /**
     * 导出 PDF
     */
    @FXML
    @SneakyThrows
    public synchronized  void exportPDF(){
        String exportURL = choseURL();
        if(exportURL != null){
            //开始导出文件

            String reportName = null;
            JasperPrint jasperPrint = null;
            Map<String ,Object> map = exportCommon("/jasper/sale_statistics_customer.jasper",null);
            if(map != null){
                reportName = map.get("reportName")==null?null:map.get("reportName").toString();
                jasperPrint = map.get("jasperPrint")==null?null:(JasperPrint) map.get("jasperPrint");
            }

            reportName = exportURL+"\\"+reportName+".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportName);

        }else {
            System.err.println("用户取消了路径选择");
        }
    }

    /**
     * 导出 Word
     */
    @FXML
    @SneakyThrows
    public synchronized  void exportWord(){
        String exportURL = choseURL();
        if(exportURL != null){
            //开始导出文件

            String reportName = null;
            JasperPrint jasperPrint = null;
            Map<String ,Object> map = exportCommon("/jasper/sale_statistics_customer.jasper",null);
            if(map != null){
                reportName = map.get("reportName")==null?null:map.get("reportName").toString();
                jasperPrint = map.get("jasperPrint")==null?null:(JasperPrint) map.get("jasperPrint");
            }

            reportName = exportURL+"\\"+reportName+".doc";
            JRRtfExporter docReport = new JRRtfExporter();
            docReport.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,reportName);
            docReport.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            docReport.exportReport();

        }else {
            System.err.println("用户取消了路径选择");
        }
    }

    /**
     * 选择文件打印
     */
    @FXML
    public synchronized  void printView(){
        JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
        int state = fileChooser.showOpenDialog(null);
        if (state == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // 获取选择的文件
            // 构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            // 设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            // 查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            // 定位默认的打印服务
            PrintService defaultService = PrintServiceLookup
                    .lookupDefaultPrintService();
            // 显示打印对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // 创建打印作业
                    FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
//                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    
}
