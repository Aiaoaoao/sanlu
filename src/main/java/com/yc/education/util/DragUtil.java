package com.yc.education.util;

import com.yc.education.listener.DragListener;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * @ClassName DragUtil
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018-08-08 17:50
 * @Version 1.0
 */
public class DragUtil {

    public static void addDragListener(Stage stage, Node root) {
        new DragListener(stage).enableDrag(root);
    }
}
