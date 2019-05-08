package com.yc.education.util;

import com.yc.education.controller.customer.CustomerBasicInfoController;
import com.yc.education.model.customer.CustomerShippingAddress;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 客户基本信息中送货地址的tableview中的下拉框
 */
public class ComboBoxCell extends TableCell<CustomerShippingAddress, String> {

    private ComboBox<String> comboBox;

    public ComboBoxCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (comboBox == null) {
            createComboBox();
        }

        setGraphic(comboBox);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();

        setText(String.valueOf(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (comboBox != null) {
                    comboBox.setValue(getString());
                }
                setGraphic(comboBox);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }

    private void createComboBox() {
        // ClassesController.getLevelChoice() is the observable list of String
        comboBox = new ComboBox<>(CustomerBasicInfoController.getLevelChoice());
        comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap()*2);
        comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(comboBox.getSelectionModel().getSelectedItem());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}