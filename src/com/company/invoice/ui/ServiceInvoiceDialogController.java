package com.company.invoice.ui;

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
    private TextField netPriceTextField;

    @FXML
    private TextField grossPriceTextField;

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
                    netPriceTextField.setText(newValue.getNetPrice());
                    grossPriceTextField.setText(newValue.getGrossPrice());
                    vatTextField.setText(newValue.getVat());
                    serviceModel = newValue;
                }
            }
        });

        netPriceTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue) {
                    calculateBruttoPrice();
                }
            }
        });

        grossPriceTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
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
        newItem.setGrossPrice(grossPriceTextField.getText());
        newItem.setNetPrice(netPriceTextField.getText());
        newItem.setUnitOfMeasure(serviceModel.getUnitOfMeasure());

        return newItem;
    }

    public void editItem(ItemModel itemModel) {
        netPriceTextField.setText(itemModel.getNetPrice());
        grossPriceTextField.setText(itemModel.getGrossPrice());
        vatTextField.setText(itemModel.getVat());
        quantityTextField.setText(itemModel.getQuantity());

    }

    public void updateItem(ItemModel itemModel) {
        if(serviceTable.getSelectionModel().getSelectedItem() == null) {
            itemModel.setQuantity(quantityTextField.getText());
            itemModel.setVat(vatTextField.getText());
            itemModel.setGrossPrice(grossPriceTextField.getText());
            itemModel.setNetPrice(netPriceTextField.getText());
        }
        else {
            itemModel.setType(serviceModel.getType());
            itemModel.setName(serviceModel.getName());
            itemModel.setQuantity(quantityTextField.getText());
            itemModel.setVat(vatTextField.getText());
            itemModel.setGrossPrice(grossPriceTextField.getText());
            itemModel.setNetPrice(netPriceTextField.getText());
        }
    }

    @FXML
    public void calculateBruttoPrice() {
        grossPriceTextField.setText(getBruttoPrice());
    }

    @FXML
    public void calculateNettoPrice() {
        netPriceTextField.setText(getNettoPrice());
    }

    private String getBruttoPrice() {
        double bruttoPrice = 0;
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        //*TODO how to avoid empty String exception in TextField
        bruttoPrice = (Double.parseDouble(vatTextField.getText()) / 100 + 1) * Double.parseDouble(netPriceTextField.getText());
        return Double.toString(bruttoPrice);

    }

    private String getNettoPrice() {
        double nettoPrice = 0;
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        nettoPrice = Double.parseDouble(grossPriceTextField.getText()) - (Double.parseDouble(grossPriceTextField.getText()) *
                (Double.parseDouble(vatTextField.getText())) / (100 + Double.parseDouble(vatTextField.getText())));
        return Double.toString(nettoPrice);
        //*TODO add correct formatter to net and gross price
    }
}
