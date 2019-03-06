package com.company.invoice.ui;

import com.company.invoice.dto.Invoice;
import com.company.invoice.dto.Item;
import com.company.invoice.ui.datamodel.*;
import com.company.invoice.utils.InvoiceUtils;
import com.company.invoice.validators.ValidateDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.company.invoice.dictionaries.Errors.DIALOG_LOAD_ERROR;

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
    private Label totalNetValueLabel, totalGrossValueLabel;


    public InvoiceDialogController() {
        invoiceUtils = new InvoiceUtils();
        today = LocalDate.now();
        validateDate = new ValidateDate();
    }


    public void initialize() {
        itemModels = FXCollections.observableArrayList();
        issueDatePicker.setValue(today);
        invoiceDatePicker.setValue(today);
        dueDatePicker.setValue(today);

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
        newInvoice.setCustomerId(getContractorId());
        //*TODO just for now we use user id 1 but we need add some method that will choose user from the list after log in
        newInvoice.setUserId(1);
        newInvoice.setPaymentId(getPaymentId());
        return newInvoice;
    }

    public List<Item> getInvoiceItems() {
        List<Item> itemList = new ArrayList<>();
        for (ItemModel itemModel : itemModels) {
            Item item = new Item();

            item.setType(itemModel.getType());
            item.setName(itemModel.getName());
            item.setQuantity(Integer.parseInt(itemModel.getQuantity()));
            item.setVat(Integer.parseInt(itemModel.getVat()));
            item.setGrossPrice(Double.parseDouble(itemModel.getGrossPrice()));
            item.setUnitOfMeasure(itemModel.getUnitOfMeasure());

            itemList.add(item);
        }

        return itemList;
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
        //*TODO somehow every time when we open new window payment list is doubled
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
            System.out.println(DIALOG_LOAD_ERROR);
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
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
            System.out.println(DIALOG_LOAD_ERROR);
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
        String invoiceNumber = UIData.getInstance().getInvoiceMaxNumber();
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
            totalNettoValue += getValue(itemModel.getQuantity(), itemModel.getNetPrice());
            totalBruttoValue += getValue(itemModel.getQuantity(), itemModel.getGrossPrice());
        }

        totalNetValueLabel.setText("Razem wartość netto: " + String.format("\t%.2f", totalNettoValue));
        totalGrossValueLabel.setText("Razem wartość brutto: " + String.format("\t%.2f", totalBruttoValue));
    }

    private double getValue(String quantity, String price) {
        return (Double.parseDouble(quantity) * Double.parseDouble(price));
    }

    private int getContractorId() {
        ObservableList<ContractorModel> modelList = UIData.getInstance().getContractorModels();
        int index = contractorComboBox.getSelectionModel().getSelectedIndex();

        return Integer.parseInt(modelList.get(index).getId());
    }

    private int getPaymentId() {
        ObservableList<PaymentModel> paymentList = UIData.getInstance().getPaymentModels();
        int index = paymentComboBox.getSelectionModel().getSelectedIndex();

        return Integer.parseInt(paymentList.get(index).getId());
    }

    public void editInvoice(InvoiceModel invoiceModel) {
        typeComboBox.getSelectionModel().select(invoiceModel.getInvoiceType());
        invoiceNumberTextField.setText(invoiceModel.getInvoiceNumber());
        issueDatePicker.setValue(LocalDate.parse(invoiceModel.getIssueDate(), formatter));
        invoiceDatePicker.setValue(LocalDate.parse(invoiceModel.getInvoiceDate(), formatter));
        contractorComboBox.getSelectionModel().select(invoiceModel.getCustomerName());
        setInvoiceItems(Integer.parseInt(invoiceModel.getInvoiceId()));
        itemsTable.setItems(itemModels);
    }

    private void setInvoiceItems(int invoiceId) {
        List<Item> items = UIData.getInstance().downloadItems(invoiceId);
        for (Item item : items) {
            itemModels.add(setItemModel(item));
        }
    }

    private ItemModel setItemModel(Item item) {
        //*TODO need to set net value and price
        ItemModel itemModel = new ItemModel();
        itemModel.setType(item.getType());
        itemModel.setName(item.getName());
        itemModel.setQuantity(Integer.toString(item.getQuantity()));
        itemModel.setVat(Integer.toString(item.getVat()));
        itemModel.setGrossPrice(Double.toString(item.getGrossPrice()));
        itemModel.setUnitOfMeasure(item.getUnitOfMeasure());
        return itemModel;
    }
}
