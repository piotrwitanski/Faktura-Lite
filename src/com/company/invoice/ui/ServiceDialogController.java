package com.company.invoice.ui;

import com.company.invoice.dto.Product;
import com.company.invoice.ui.datamodel.ServiceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ServiceDialogController {

    @FXML
    private ComboBox<String> typeComboBox, unitComboBox;

    @FXML
    private TextField nameTextField, symbolTextField, vatTextField, netPriceTextField, grossTextField;

    public void initialize() {
    }

    public Product getNewProduct() {
        Product product = new Product();
        product.setType(typeComboBox.getSelectionModel().getSelectedItem());
        product.setName(nameTextField.getText());
        product.setVat(Integer.parseInt(vatTextField.getText()));
        product.setGrossPrice(Double.parseDouble(grossTextField.getText()));
        product.setUnitOfMeasure(unitComboBox.getSelectionModel().getSelectedItem());

        return product;
    }

    public Product updateProduct(int id) {
        Product product = new Product();

        product.setId(id);
        product.setName(nameTextField.getText());
        product.setType(typeComboBox.getSelectionModel().getSelectedItem());
        product.setVat(Integer.parseInt(vatTextField.getText()));
        product.setGrossPrice(Double.parseDouble(grossTextField.getText()));
        product.setUnitOfMeasure(unitComboBox.getSelectionModel().getSelectedItem());

        return product;
    }

    public void editService(ServiceModel serviceModel) {
        typeComboBox.getSelectionModel().select(serviceModel.getType());
        nameTextField.setText(serviceModel.getName());
        vatTextField.setText(serviceModel.getVat());
        netPriceTextField.setText(serviceModel.getNetPrice());
        grossTextField.setText(serviceModel.getGrossPrice());
        unitComboBox.getSelectionModel().select(serviceModel.getUnitOfMeasure());
    }
}
