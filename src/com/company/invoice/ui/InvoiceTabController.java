package com.company.invoice.ui;

import com.company.invoice.dto.*;
import com.company.invoice.tools.PDFCreator;
import com.company.invoice.ui.datamodel.InvoiceModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.company.invoice.dictionaries.Errors.DIALOG_LOAD_ERROR;

public class InvoiceTabController {

    @FXML
    private BorderPane sellBorderPane;

    @FXML
    private TableView<InvoiceModel> invoiceTable;

    private FileChooser chooser;
    private PDFCreator pdfCreator;

    public void initialize() {
        UIData.getInstance().loadInvoiceTable();
        UIData.getInstance().loadPaymentList();
        UIData.getInstance().loadBankAccountNumber();
        invoiceTable.setItems(UIData.getInstance().getInvoiceModels());
        chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        pdfCreator = new PDFCreator();
    }

    @FXML
    public void showNewInvoiceDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(sellBorderPane.getScene().getWindow());
        dialog.setTitle("Nowa faktura");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("invoiceDialog.fxml"));
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
        ButtonType buttonSaveInvoice = new ButtonType("Zapisz i drukuj");
        dialog.getDialogPane().getButtonTypes().add(buttonSaveInvoice);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            saveNewInvoice(fxmlLoader);
        }
        else if(result.isPresent() && result.get() == buttonSaveInvoice) {
            saveNewInvoice(fxmlLoader);

            File file = chooser.showSaveDialog(sellBorderPane.getScene().getWindow());
            savePDF(UIData.getInstance().getInvoiceLastId(), file.getPath());
        }
    }

    private void saveNewInvoice(FXMLLoader fxmlLoader) {
        InvoiceDialogController invoiceController = fxmlLoader.getController();
        Invoice newInvoice = invoiceController.getNewInvoice();
        UIData.getInstance().saveInvoice(newInvoice);
        int invoiceId = UIData.getInstance().getInvoiceLastId();
        List<Item> itemList = invoiceController.getInvoiceItems();
        saveItems(itemList, invoiceId);
        UIData.getInstance().addInvoiceModel(UIData.getInstance().loadNewInvoice());

        invoiceTable.setItems(UIData.getInstance().getInvoiceModels());
    }

    private void savePDF(int invoiceId, String filePath) {
        Invoice invoice = UIData.getInstance().downloadInvoice(invoiceId);
        Customer customer = UIData.getInstance().downloadCustomer(invoice.getCustomerId());
        User user = UIData.getInstance().downloadUser(invoice.getUserId());
        List<Item> items = UIData.getInstance().downloadItems(invoice.getId());
        Payment payment = UIData.getInstance().downloadPayment(invoice.getPaymentId());
        pdfCreator.createPdf(filePath, customer, invoice, items, user, payment);
    }

    @FXML
    public void showEditInvoiceDialog() {
        InvoiceModel selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();
        if(selectedInvoice == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonej faktury");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć fakturę do edytowania");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(sellBorderPane.getScene().getWindow());
        dialog.setTitle("Edytowanie faktury");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("invoiceDialog.fxml"));
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
        ButtonType buttonSaveInvoice = new ButtonType("Zapisz i drukuj");
        dialog.getDialogPane().getButtonTypes().add(buttonSaveInvoice);

        InvoiceDialogController invoiceDialogController = fxmlLoader.getController();
        invoiceDialogController.editInvoice(selectedInvoice);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            saveEditedInvoice(invoiceDialogController, selectedInvoice);
        }
        else if(result.isPresent() && result.get() == buttonSaveInvoice) {
            saveEditedInvoice(invoiceDialogController, selectedInvoice);

            File file = chooser.showSaveDialog(sellBorderPane.getScene().getWindow());
            if(file != null) {
                savePDF(Integer.parseInt(selectedInvoice.getInvoiceId()), file.getPath());
            }
        }
    }

    private void saveEditedInvoice(InvoiceDialogController invoiceDialogController, InvoiceModel selectedInvoice) {
        Invoice invoice = invoiceDialogController.updateInvoice(Integer.parseInt(selectedInvoice.getInvoiceId()));
        UIData.getInstance().updateInvoice(invoice);

        List<Item> itemList = invoiceDialogController.getInvoiceItems();
        updateItems(itemList, invoice.getId());
        UIData.getInstance().updateInvoiceModel(invoice);
        invoiceTable.setItems(UIData.getInstance().getInvoiceModels());
    }

    public void showDeleteInvoiceDialog() {
        InvoiceModel selectedInvoice = invoiceTable.getSelectionModel().getSelectedItem();
        if(selectedInvoice == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonej faktury");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć fakturę do usunięcia");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie faktury");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewny, że chcesz usunąć zaznaczoną fakturę " +
                selectedInvoice.getInvoiceType() + " " + selectedInvoice.getInvoiceNumber());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            UIData.getInstance().deleteInvoice(selectedInvoice);
        }
    }

    private void saveItems(List<Item> itemList, int invoiceId) {
        for(Item item : itemList) {
            item.setInvoiceId(invoiceId);
            UIData.getInstance().saveItem(item);
        }
    }

    private void updateItems(List<Item> newItemList, int invoiceId) {
        UIData.getInstance().removeAllItems(invoiceId);
        saveItems(newItemList, invoiceId);

    }


}
