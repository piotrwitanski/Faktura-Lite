package com.company.invoice.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private BorderPane sellBorderPane;

    @FXML
    private BorderPane contractorBorderPane;

    @FXML
    private BorderPane serviceBorderPane;

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

    @FXML
    public void showNewContractorDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(contractorBorderPane.getScene().getWindow());
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
            ContractorController contractorController = fxmlLoader.getController();
            //TODO here we need to add code that takes values from ContractorController to save contractor in db
            System.out.println("Contractor saved in db");
        }
    }

    @FXML
    public void showNewServiceDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(serviceBorderPane.getScene().getWindow());
        dialog.setTitle("Towar/Usługa");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("serviceDialog.fxml"));
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
            ServiceController serviceController = fxmlLoader.getController();
            //TODO here we need add code take values from ServiceController and sava them in db
            System.out.println("Service or ware saved in database");
        }
    }
}
