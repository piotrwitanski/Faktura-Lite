package com.company.invoice.ui;

import com.company.invoice.dto.Product;
import com.company.invoice.ui.datamodel.ServiceModel;
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

public class ServiceTabController {

    @FXML
    private BorderPane serviceBorderPane;

    @FXML
    private TableView<ServiceModel> serviceTable;

    public void initialize() {
        UIData.getInstance().loadServiceTable();
        serviceTable.setItems(UIData.getInstance().getServiceModels());
        System.out.println("Test service");
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
            ServiceDialogController serviceController = fxmlLoader.getController();
            Product product = serviceController.getNewProduct();
            UIData.getInstance().saveProduct(product);
            UIData.getInstance().addServiceModel(UIData.getInstance().loadNewProduct());
            serviceTable.setItems(UIData.getInstance().getServiceModels());
            //TODO need to improve gross to net price method
        }
    }

    @FXML
    public void showEditServiceDialog() {
        ServiceModel selectedService = serviceTable.getSelectionModel().getSelectedItem();
        if(selectedService == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonej usługi/towaru");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć usługę/towar do edytowania");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(serviceBorderPane.getScene().getWindow());
        dialog.setTitle("Edytowanie usługi/towaru");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("serviceDialog.fxml"));
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

        ServiceDialogController serviceDialogController = fxmlLoader.getController();
        serviceDialogController.editService(selectedService);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Product product = serviceDialogController.updateProduct(Integer.parseInt(selectedService.getId()));
            UIData.getInstance().updateProduct(product);
            UIData.getInstance().updateServiceModel(product);
            serviceTable.setItems(UIData.getInstance().getServiceModels());
        }
    }

    @FXML
    public void showDeleteServiceDialog() {
        ServiceModel selectedService = serviceTable.getSelectionModel().getSelectedItem();
        if(selectedService == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak zaznaczonej usługi/towaru");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć usługę/towar do usunięcia");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie usługi/towaru");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewny, że chcesz usunąć zaznaczoną usługę/towar " + selectedService.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            UIData.getInstance().removeService(selectedService);
        }
    }
}
