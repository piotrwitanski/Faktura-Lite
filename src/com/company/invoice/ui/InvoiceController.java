package com.company.invoice.ui;

import com.company.invoice.dto.Invoice;
import com.company.invoice.ui.datamodel.ContractorModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvoiceController {

    @FXML
    private DatePicker issueDatePicker;

    @FXML
    private DatePicker invoiceDatePicker;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField invoiceNumberTextField;

    @FXML
    private ComboBox<String> contractorComboBox;

    @FXML
    private ComboBox<String> recipientComboBox;

    @FXML
    private CheckBox recipientCheckBox;

    @FXML
    private Button recipientAddButton;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public void initialize() {
        issueDatePicker.setValue(LocalDate.now());
        invoiceDatePicker.setValue(LocalDate.now());
        dueDatePicker.setValue(LocalDate.now());
//        System.out.println(dueDatePicker.getValue());
        //*TODO here need to download and set invoice number from database
        invoiceNumberTextField.setText("01/01/2019");
        System.out.println(dueDatePicker.getValue().format(formatter));
        recipientComboBox.setDisable(!recipientCheckBox.isSelected());
        recipientAddButton.setDisable(!recipientCheckBox.isSelected());
        setContractorsAndRecipients();
    }

    public Invoice getNewInvoice() {
        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceType(typeComboBox.getSelectionModel().getSelectedItem());
        newInvoice.setInvoiceNumber(invoiceNumberTextField.getText());
        newInvoice.setIssueDate(issueDatePicker.getValue().format(formatter));
        newInvoice.setInvoiceDate(dueDatePicker.getValue().format(formatter));

        return null;
    }

    @FXML
    private void chooseRecipient() {
//        recipientComboBox.setVisible(recipientCheckBox.isSelected());
        recipientComboBox.setDisable(!recipientCheckBox.isSelected());
        recipientAddButton.setDisable(!recipientCheckBox.isSelected());
    }

    private void setContractorsAndRecipients() {
        ObservableList<ContractorModel> modelList = UIData.getInstance().getContractorModels();
        ObservableList<String> nameList = FXCollections.observableArrayList();
        for (ContractorModel contractorModel : modelList) {
            nameList.add(contractorModel.getName() + "\t\t" + contractorModel.getNIP());
        }
        contractorComboBox.setItems(nameList);
        recipientComboBox.setItems(nameList);
    }

}
