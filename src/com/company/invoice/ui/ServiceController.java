package com.company.invoice.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ServiceController {

    @FXML
    private ComboBox<String> comboBox;
    //added only for test
    public void initialize() {
        String cos = comboBox.getSelectionModel().getSelectedItem();
        System.out.println(cos);
    }
}
