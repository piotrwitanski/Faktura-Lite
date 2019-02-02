package com.company.invoice.ui;

import com.company.invoice.ui.datamodel.ContractorModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

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
            //TODO here we need to add code that takes values from ContractorDialogController to save contractor in db
            System.out.println("Contractor saved in db");
        }
    }

}
