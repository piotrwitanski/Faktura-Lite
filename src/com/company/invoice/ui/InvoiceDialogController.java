package com.company.invoice.ui;

import com.company.invoice.dto.Invoice;
import com.company.invoice.dto.Item;
import com.company.invoice.ui.datamodel.ContractorModel;
import com.company.invoice.ui.datamodel.ItemModel;
import com.company.invoice.ui.datamodel.PaymentModel;
import com.company.invoice.ui.datamodel.UIData;
import com.company.invoice.utils.InvoiceUtils;
import com.company.invoice.validators.ValidateDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceDialogController {

    private LocalDate today;
    private ValidateDate validateDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private InvoiceUtils invoiceUtils;
    private ObservableList<ItemModel> itemModels;

    @FXML
    private DatePicker issueDatePicker, invoiceDatePicker, dueDatePicker;

    @FXML
    private ComboBox<String> typeComboBox, contractorComboBox, recipientComboBox, paymentComboBox;

    @FXML
    private TextField invoiceNumberTextField;

    @FXML
    private CheckBox recipientCheckBox;

    @FXML
    private Button recipientAddButton;

    @FXML
    private DialogPane invoiceDialogPane;

    @FXML
    private TableView<ItemModel> itemsTable;

    @FXML
    private Label totalNettoValueLabel, totalBruttoValueLabel;


    public InvoiceDialogController() {
        invoiceUtils = new InvoiceUtils();
        today = LocalDate.now();
        validateDate = new ValidateDate();
    }


    public void initialize() {
        UIData.getInstance().loadPaymentList();
        itemModels = FXCollections.observableArrayList();
        issueDatePicker.setValue(today);
        invoiceDatePicker.setValue(today);
        dueDatePicker.setValue(today);
        //*TODO here need to download and set invoice number from database

        invoiceNumberTextField.setText(getInvoiceNumber());
        System.out.println(dueDatePicker.getValue().format(formatter));
        recipientComboBox.setDisable(!recipientCheckBox.isSelected());
        recipientAddButton.setDisable(!recipientCheckBox.isSelected());
        setContractorsAndRecipients();
        setPayments();
    }

    public Invoice getNewInvoice() {
        Invoice newInvoice = new Invoice();
        newInvoice.setInvoiceType(typeComboBox.getSelectionModel().getSelectedItem());
        newInvoice.setInvoiceNumber(invoiceNumberTextField.getText());
        newInvoice.setIssueDate(issueDatePicker.getValue().format(formatter));
        newInvoice.setInvoiceDate(dueDatePicker.getValue().format(formatter));
        //*TODO change return object
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

    private void setPayments() {
        ObservableList<PaymentModel> paymentList = UIData.getInstance().getPaymentModels();
        ObservableList<String> nameList = FXCollections.observableArrayList();
        for (PaymentModel paymentModel : paymentList) {
            nameList.add(paymentModel.getName());
        }
        paymentComboBox.setItems(nameList);
    }

    @FXML
    public void showNewContractor() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(invoiceDialogPane.getScene().getWindow());
        dialog.setTitle("Kontrahent");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contractorDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            ContractorDialogController contractorController = fxmlLoader.getController();
            //TODO here we need to add code that takes values from ContractorDialogController to save contractor in db
            System.out.println("Contractor saved in db");
        }
    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(invoiceDialogPane.getScene().getWindow());
        dialog.setTitle("Dodawanie przedmiotów");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("serviceInvoiceDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            //*TODO add method that will save items in table view need to add temporary items list before save to db in UIData
            ServiceInvoiceDialogController serviceInvoiceController = fxmlLoader.getController();
            ItemModel newItem = serviceInvoiceController.getNewItem();
            itemModels.add(newItem);
            itemsTable.setItems(itemModels);
            calculateTotalValue();
        }
    }

    @FXML
    public void showEditItemDialog() {
        ItemModel selectedItem = itemsTable.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonego przedmiotu");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć przedmiot do edycji");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(invoiceDialogPane.getScene().getWindow());
        dialog.setTitle("Edytowanie przedmiotu");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("serviceInvoiceDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ServiceInvoiceDialogController serviceDialogController = fxmlLoader.getController();
        serviceDialogController.editItem(selectedItem);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            serviceDialogController.updateItem(selectedItem);
            calculateTotalValue();
        }
    }

    @FXML
    public void showDeleteItemDialog() {
        ItemModel selectedItem = itemsTable.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonego przedmiotu");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć przedmiot do usunięcia");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie przedmiotu");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewny, że chcesz usunąć zaznaczony przedmiot " +
                            selectedItem.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            itemModels.remove(selectedItem);
            calculateTotalValue();
        }
    }

    private String getInvoiceNumber() {
        String invoiceNumber = invoiceUtils.downloadInvoiceMaxId();
        List<String> list = Arrays.asList(invoiceNumber.split("/"));
        validateDate.setInvoiceYear(Integer.parseInt(list.get(2)));
        int day = 0;
        if(validateDate.validate()) {
            day = Integer.parseInt(list.get(0)) + 1;
        }
        else {
            day = 1;
        }
        invoiceNumber = String.format("%02d", day) + "/" + String.format("%02d", today.getMonthValue()) + "/" + today.getYear();
        return invoiceNumber;
    }

    private void calculateTotalValue() {
        double totalNettoValue = 0;
        double totalBruttoValue = 0;

        for (ItemModel itemModel : itemModels) {
            totalNettoValue += getValue(itemModel.getQuantity(), itemModel.getNettoPrice());
            totalBruttoValue += getValue(itemModel.getQuantity(), itemModel.getBruttoPrice());
        }

        totalNettoValueLabel.setText("Razem wartość netto: " + String.format("\t%.2f", totalNettoValue));
        totalBruttoValueLabel.setText("Razem wartość brutto: " + String.format("\t%.2f", totalBruttoValue));
    }

    private double getValue(String quantity, String price) {
        return (Double.parseDouble(quantity) * Double.parseDouble(price));
    }
}
