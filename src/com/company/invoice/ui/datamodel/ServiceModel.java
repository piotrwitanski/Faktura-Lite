package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class ServiceModel {
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty vat = new SimpleStringProperty("");
    private SimpleStringProperty netPrice = new SimpleStringProperty("");
    private SimpleStringProperty grossPrice = new SimpleStringProperty("");
    private SimpleStringProperty unitOfMeasure = new SimpleStringProperty("");

    public ServiceModel() {

    }

    public ServiceModel(String type, String name, String vat, String netPrice, String grossPrice, String unitOfMeasure) {
        this.type.set(type);
        this.name.set(name);
        this.vat.set(vat);
        this.netPrice.set(netPrice);
        this.grossPrice.set(grossPrice);
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

    public String getNetPrice() {
        return netPrice.get();
    }

    public SimpleStringProperty netPriceProperty() {
        return netPrice;
    }

    public void setNetPrice(String netPrice) {
        this.netPrice.set(netPrice);
    }

    public String getGrossPrice() {
        return grossPrice.get();
    }

    public SimpleStringProperty grossPriceProperty() {
        return grossPrice;
    }

    public void setGrossPrice(String grossPrice) {
        this.grossPrice.set(grossPrice);
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
}
