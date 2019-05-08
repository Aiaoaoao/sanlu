package com.yc.education.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

/**
 * @ClassName EditingCell
 * @Description TODO
 * @Author QuZhangJing
 * @Date 2018/8/28 17:30
 * @Version 1.0
 */
public class EditingCell<T> extends TableCell<T,String> {



    private TextField textField;


    public EditingCell() {
    }


    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createTextField();
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
    }


    @Override
    public void cancelEdit() {
        super.cancelEdit();


        setText((String) getItem());
        setGraphic(null);
    }


    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);


        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.focusedProperty().addListener((ob, old, now) -> {
            if (!now) {
                commitEdit(textField.getText());
            }
        });
    }


    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }


}
