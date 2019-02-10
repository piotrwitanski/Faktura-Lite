package com.company.invoice.ui;

import com.company.invoice.dto.Invoice;
import com.company.invoice.dto.Item;
import com.company.invoice.ui.datamodel.InvoiceModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class InvoiceTabController {

    @FXML
    private BorderPane sellBorderPane;

    @FXML
    private TableView<InvoiceModel> invoiceTable;


    public void initialize() {
        UIData.getInstance().loadInvoiceTable();
        invoiceTable.setItems(UIData.getInstance().getInvoiceModels());
    }

    //*TODO it's just for test, need to check this solution !!!!!!!!!!
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
            System.out.println("Couldn't load the dialog");
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            InvoiceDialogController invoiceController = fxmlLoader.getController();
            //*TODO here we need to add code that will get text from invoiceController and save it to specific class e.g. Invoice (but here we need different class for this object)
            Invoice newInvoice = invoiceController.getNewInvoice();
            UIData.getInstance().saveInvoice(newInvoice);
            int invoiceId = UIData.getInstance().getInvoiceLastId();
            List<Item> itemList = invoiceController.getInvoiceItems();
            saveItems(itemList, invoiceId);
            System.out.println("Invoice added to db");
            //*TODO save here as pdf, add chooser and pdf method
        }
    }

    private void saveItems(List<Item> itemList, int invoiceId) {
        for (Item item : itemList) {
            item.setInvoiceId(invoiceId);
            UIData.getInstance().saveItem(item);
        }
    }


}
