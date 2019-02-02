package com.company.invoice.ui;

import com.company.invoice.dto.Item;
import com.company.invoice.ui.datamodel.ItemModel;
import com.company.invoice.ui.datamodel.ServiceModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.util.Locale;


public class ServiceInvoiceDialogController {

    @FXML
    private TableView<ServiceModel> serviceTable;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField nettoPriceTextField;

    @FXML
    private TextField bruttoPriceTextField;

    @FXML
    private TextField vatTextField;

    private ServiceModel serviceModel;


    public void initialize() {
        serviceTable.setItems(UIData.getInstance().getServiceModels());
        serviceModel = new ServiceModel();

        serviceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ServiceModel>() {
            @Override
            public void changed(ObservableValue<? extends ServiceModel> observable, ServiceModel oldValue, ServiceModel newValue) {
                if(newValue != null) {
                    nettoPriceTextField.setText(newValue.getNettoPrice());
                    bruttoPriceTextField.setText(newValue.getBruttoPrice());
                    vatTextField.setText(newValue.getVat());
                    System.out.println(newValue.unitOfMeasureProperty());
                    serviceModel = newValue;
                }
            }
        });

        nettoPriceTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    calculateBruttoPrice();
                }
            }
        });

        bruttoPriceTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    calculateNettoPrice();
                }
            }
        });

        vatTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    calculateBruttoPrice();
                }
            }
        });
    }

    public ItemModel getNewItem() {
        ItemModel newItem = new ItemModel();
        newItem.setType(serviceModel.getType());
        newItem.setName(serviceModel.getName());
        newItem.setQuantity(quantityTextField.getText());
        newItem.setVat(vatTextField.getText());
        newItem.setBruttoPrice(bruttoPriceTextField.getText());
        newItem.setNettoPrice(nettoPriceTextField.getText());

        return newItem;
    }

    public void editItem(ItemModel itemModel) {
        nettoPriceTextField.setText(itemModel.getNettoPrice());
        bruttoPriceTextField.setText(itemModel.getBruttoPrice());
        vatTextField.setText(itemModel.getVat());
        quantityTextField.setText(itemModel.getQuantity());

    }

    public void updateItem(ItemModel itemModel) {
        if(serviceTable.getSelectionModel().getSelectedItem() == null) {
            itemModel.setQuantity(quantityTextField.getText());
            itemModel.setVat(vatTextField.getText());
            itemModel.setBruttoPrice(bruttoPriceTextField.getText());
            itemModel.setNettoPrice(nettoPriceTextField.getText());
        }
        else {
            itemModel.setType(serviceModel.getType());
            itemModel.setName(serviceModel.getName());
            itemModel.setQuantity(quantityTextField.getText());
            itemModel.setVat(vatTextField.getText());
            itemModel.setBruttoPrice(bruttoPriceTextField.getText());
            itemModel.setNettoPrice(nettoPriceTextField.getText());
        }
    }

    @FXML
    public void calculateBruttoPrice() {
        bruttoPriceTextField.setText(getBruttoPrice());
    }

    @FXML
    public void calculateNettoPrice() {
        nettoPriceTextField.setText(getNettoPrice());
    }

    private String getBruttoPrice() {
        double bruttoPrice = 0;
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        //*TODO how to avoid empty String exception in TextField
        bruttoPrice = (Double.parseDouble(vatTextField.getText()) / 100 + 1) * Double.parseDouble(nettoPriceTextField.getText());
        return Double.toString(bruttoPrice);

    }

    private String getNettoPrice() {
        double nettoPrice = 0;
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        nettoPrice = Double.parseDouble(bruttoPriceTextField.getText()) - (Double.parseDouble(bruttoPriceTextField.getText()) *
                    (Double.parseDouble(vatTextField.getText())) / (100 + Double.parseDouble(vatTextField.getText())));
        return Double.toString(nettoPrice);
        //*TODO add correct formatter to netto and brutto price
    }
}
