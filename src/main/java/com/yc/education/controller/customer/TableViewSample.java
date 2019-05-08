package com.yc.education.controller.customer;


import java.util.EnumSet;

import com.yc.education.service.customer.ICustomerShippingAddressService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: BlueSky
 * @Date: 2018/8/17 14:49
 */
public class TableViewSample{

    @Autowired
    ICustomerShippingAddressService iCustomerShippingAddressService;

    public static class RadioButtonCell<S,T extends Enum<T>> extends TableCell<S,T>{

        private EnumSet<T> enumeration;

        public RadioButtonCell(EnumSet<T> enumeration) {
            this.enumeration = enumeration;
        }


        @Override
        protected void updateItem(T item, boolean empty)
        {

            super.updateItem(item, empty);
            if (!empty)
            {
                // gui setup
                HBox hb = new HBox(7);
                hb.setAlignment(Pos.CENTER);
                final ToggleGroup group = new ToggleGroup();

                // create a radio button for each 'element' of the enumeration
                for (Enum<T> enumElement : enumeration) {
                    RadioButton radioButton = new RadioButton(enumElement.toString());
                    radioButton.setUserData(enumElement);
                    radioButton.setToggleGroup(group);
                    hb.getChildren().add(radioButton);
                    if (enumElement.equals(item)) {
                        radioButton.setSelected(true);
                    }
                }

                // issue events on change of the selected radio button
                group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                    @SuppressWarnings("unchecked")
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observable,
                                        Toggle oldValue, Toggle newValue) {
                        getTableView().edit(getIndex(), getTableColumn());
                        RadioButtonCell.this.commitEdit((T) newValue.getUserData());
                    }
                });
                setGraphic(hb);
            }
        }
    }

    private static void method(Long customerid){
        TableViewSample t = new TableViewSample();
        t.updateClearAddressDefault(customerid);
    }

    public void updateClearAddressDefault(Long customerid){
        iCustomerShippingAddressService.updateClearAddressDefault(customerid);
    }
}
