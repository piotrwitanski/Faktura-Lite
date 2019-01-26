package com.company.invoice.ui;

import com.company.invoice.ui.datamodel.InvoiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.text.DateFormat;
import java.time.LocalDate;

public class InvoiceController {

    @FXML
    private DatePicker issueDatePicker;

    @FXML
    private DatePicker invoiceDatePicker;

    @FXML
    private DatePicker dueDatePicker;

    public void initialize() {
        issueDatePicker.setValue(LocalDate.now());
        invoiceDatePicker.setValue(LocalDate.now());
        dueDatePicker.setValue(LocalDate.now());

    }


}
