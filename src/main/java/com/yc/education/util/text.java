package com.yc.education.util;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName text
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/10/23 16:10
 * @Version 1.0
 */
public class text {
    public static void main(String[] args) throws IOException {


        try {
            JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
            //生成. 欧洲商品条码(=European Article Number)
            //这里我们用作图书条码
            String str = "788515004012";
            BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
            saveToGIF(localBufferedImage, "EAN13.gif");
            localJBarcode.setEncoder(Code39Encoder.getInstance());
            localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
            localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
            localJBarcode.setShowCheckDigit(false);
            //xx
            str = "TMTR6R 0.2L15 BXC";
            localBufferedImage = localJBarcode.createBarcode(str);
            saveToPNG(localBufferedImage, "Code39.png");

        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }


//        String[] arr1 ={"a","b","1","c","b"};
//
//        List list1 = new ArrayList<String>();
//        list1.add("d");
//        Collections.addAll(list1,arr1);
//        System.out.println("运行结果1："+list1);
//
//        Set<String> set1 = new HashSet<String>();
//        set1.add("d");
//        Collections.addAll(set1,arr1);
//        System.out.println("运行结果2："+set1);


//        int i = 2147483647;
//
//        if(i+1<i){
//                System.err.println("哦豁");
//        }


    static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
        saveToFile(paramBufferedImage, paramString, "jpeg");
    }

    static void saveToPNG(BufferedImage paramBufferedImage, String paramString) {
        saveToFile(paramBufferedImage, paramString, "png");
    }

    static void saveToGIF(BufferedImage paramBufferedImage, String paramString) {
        saveToFile(paramBufferedImage, paramString, "gif");
    }

    static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream("d:/images/" + paramString1);
            ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);
            localFileOutputStream.close();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

}
