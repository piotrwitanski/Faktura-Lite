package com.company.invoice.ui;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

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

        System.out.println(issueDatePicker.getValue());
    }
}
