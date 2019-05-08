package com.yc.education.util;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

/**
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/9/7 14:00
 * @Version 1.0
 */
public class MyCheckBox {


    CheckBox checkbox=new CheckBox();
    public ObservableValue<CheckBox> getCheckBox()
    {
        return new  ObservableValue<CheckBox>() {
            @Override
            public void addListener(ChangeListener<? super CheckBox> listener) {

            }

            @Override
            public void removeListener(ChangeListener<? super CheckBox> listener) {

            }

            @Override
            public CheckBox getValue() {
                return checkbox;
            }

            @Override
            public void addListener(InvalidationListener listener) {

            }

            @Override
            public void removeListener(InvalidationListener listener) {

            }
        };
    }
    public Boolean isSelected()
    {
        return checkbox.isSelected();
    }

}
