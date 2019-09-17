package com.company.invoice.ui;

import com.company.invoice.dto.Customer;
import com.company.invoice.ui.datamodel.ContractorModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

import static com.company.invoice.dictionaries.Errors.DIALOG_LOAD_ERROR;

public class ContractorTabController {

    @FXML
    private BorderPane contractorBorderPane;

    @FXML
    private TableView<ContractorModel> contractorTable;

    @FXML
    public void initialize() {
//        System.out.println("Test contractor");
        UIData.getInstance().loadContractorTable();
        contractorTable.setItems(UIData.getInstance().getContractorModels());
//
//        ContractorModel contractorModel = new ContractorModel("Jan Ktos", "Tychy", "Ulica", "56-343", "344-4343-4343", "3", "20");
//        ObservableList<ContractorModel> list = FXCollections.observableArrayList();
//        list.add(contractorModel);
//        contractorTable.setItems(list);
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
            ContractorDialogController contractorController = fxmlLoader.getController();
            Customer customer = contractorController.getNewCustomer();
            UIData.getInstance().saveCustomer(customer);
            UIData.getInstance().addContractorModel(UIData.getInstance().loadNewCustomer());
            System.out.println("Contractor saved in db");
            contractorTable.setItems(UIData.getInstance().getContractorModels());
        }
    }

    @FXML
    public void showEditContractorDialog() {
        ContractorModel selectedContractor = contractorTable.getSelectionModel().getSelectedItem();
        if(selectedContractor == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonego kontrahenta");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć kontrahenta do edytowania");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(contractorBorderPane.getScene().getWindow());
        dialog.setTitle("Edytowanie kontrahenta");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contractorDialog.fxml"));
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

        ContractorDialogController contractorDialogController = fxmlLoader.getController();
        contractorDialogController.editContractor(selectedContractor);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Customer customer = contractorDialogController.updateCustomer(Integer.parseInt(selectedContractor.getId()));
            UIData.getInstance().updateCustomer(customer);
            UIData.getInstance().updateContractorModel(customer);
            contractorTable.setItems(UIData.getInstance().getContractorModels());

        }
    }

    @FXML
    public void showDeleteContractorDialog() {
        ContractorModel selectedContractor = contractorTable.getSelectionModel().getSelectedItem();
        if(selectedContractor == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonego kontrahenta");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć kontrahenta do usunięcia");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie kontrahenta");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewny, że chcesz usunąć zaznaczonego kontrahenta " + selectedContractor.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            UIData.getInstance().deleteContractor(selectedContractor);
        }
    }

}
