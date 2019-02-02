package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class ItemModel {
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty vat = new SimpleStringProperty("");
    private SimpleStringProperty nettoPrice = new SimpleStringProperty("");
    private SimpleStringProperty bruttoPrice = new SimpleStringProperty("");
    private SimpleStringProperty nettoValue = new SimpleStringProperty("");
    private SimpleStringProperty bruttoValue = new SimpleStringProperty("");
    private SimpleStringProperty unitOfMeasure = new SimpleStringProperty("");
    private SimpleStringProperty quantity = new SimpleStringProperty("");

    public ItemModel() {

    }

    public ItemModel(String type, String name, String vat, String nettoPrice, String bruttoPrice, String unitOfMeasure) {
        this.type.set(type);
        this.name.set(name);
        this.vat.set(vat);
        this.nettoPrice.set(nettoPrice);
        this.bruttoPrice.set(bruttoPrice);
        this.unitOfMeasure.set(unitOfMeasure);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getVat() {
        return vat.get();
    }

    public SimpleStringProperty vatProperty() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat.set(vat);
    }

    public String getNettoPrice() {
        return nettoPrice.get();
    }

    public SimpleStringProperty nettoPriceProperty() {
        return nettoPrice;
    }

    public void setNettoPrice(String nettoPrice) {
        this.nettoPrice.set(nettoPrice);
        setNettoValue();
    }

    public String getBruttoPrice() {
        return bruttoPrice.get();
    }

    public SimpleStringProperty bruttoPriceProperty() {
        return bruttoPrice;
    }

    public void setBruttoPrice(String bruttoPrice) {
        this.bruttoPrice.set(bruttoPrice);
        setBruttoValue();
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure.get();
    }

    public SimpleStringProperty unitOfMeasureProperty() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure.set(unitOfMeasure);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getNettoValue() {
        return nettoValue.get();
    }

    public SimpleStringProperty nettoValueProperty() {
        return nettoValue;
    }

    public void setNettoValue() {
        double nettoValue = 0;
        nettoValue = Double.parseDouble(this.quantity.get()) * Double.parseDouble(this.nettoPrice.get());
        this.nettoValue.set(Double.toString(nettoValue));
    }

    public String getBruttoValue() {
        return bruttoValue.get();
    }

    public SimpleStringProperty bruttoValueProperty() {
        return bruttoValue;
    }

    public void setBruttoValue() {
        double bruttoValue = 0;
        bruttoValue = Double.parseDouble(this.quantity.get()) * Double.parseDouble(this.bruttoPrice.get());
        this.bruttoValue.set(Double.toString(bruttoValue));
    }


}
