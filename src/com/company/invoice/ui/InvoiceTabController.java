package com.company.invoice.ui;

import com.company.invoice.ui.datamodel.InvoiceModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class InvoiceTabController {

    @FXML
    private BorderPane sellBorderPane;

    @FXML
    private TableView<InvoiceModel> invoiceTable;


    public void initialize() {
//        InvoiceModel invoiceModel = new InvoiceModel("Faktura", "01/01/2019", "26-01-2019",
//                "Piotr Wit", "2000", "3000", "1000", "PLN");
//        ObservableList<InvoiceModel> list = FXCollections.observableArrayList();
//        if(list.isEmpty()) {
//            list.add(invoiceModel);
//            for (InvoiceModel model : list) {
//                System.out.println(model);
//            }
//        }
//        invoiceTable.setItems(list);
        UIData.getInstance().loadInvoiceTable();
        invoiceTable.setItems(UIData.getInstance().getInvoiceModels());
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
            System.out.println("Couldn't load the dialog");
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            InvoiceController sellController = fxmlLoader.getController();
            //*TODO here we need to add code that will get text from sellController and save it to specific class e.g. Invoice (but here we need different class for this object)
//            sellController.getNewInvoice();
            System.out.println("Invoice added to db");
        }
    }

}
