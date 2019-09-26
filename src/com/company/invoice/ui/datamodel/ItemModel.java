package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class ItemModel {
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty vat = new SimpleStringProperty("");
    private SimpleStringProperty netPrice = new SimpleStringProperty("");
    private SimpleStringProperty grossPrice = new SimpleStringProperty("");
    private SimpleStringProperty netValue = new SimpleStringProperty("");
    private SimpleStringProperty grossValue = new SimpleStringProperty("");
    private SimpleStringProperty unitOfMeasure = new SimpleStringProperty("");
    private SimpleStringProperty quantity = new SimpleStringProperty("");

    public ItemModel() {

    }

    public ItemModel(String id, String type, String name, String vat, String netPrice, String grossPrice, String unitOfMeasure) {
        this.id.set(id);
        this.type.set(type);
        this.name.set(name);
        this.vat.set(vat);
        this.netPrice.set(netPrice);
        this.grossPrice.set(grossPrice);
        this.unitOfMeasure.set(unitOfMeasure);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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
        setNettoValue();
    }

    public String getGrossPrice() {
        return grossPrice.get();
    }

    public SimpleStringProperty grossPriceProperty() {
        return grossPrice;
    }

    public void setGrossPrice(String grossPrice) {
        this.grossPrice.set(grossPrice);
        setGrossValue();
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

    public String getNetValue() {
        return netValue.get();
    }

    public SimpleStringProperty netValueProperty() {
        return netValue;
    }

    public void setNettoValue() {
        double nettoValue = 0;
        nettoValue = Double.parseDouble(this.quantity.get()) * Double.parseDouble(this.netPrice.get());
        this.netValue.set(Double.toString(nettoValue));
    }

    public String getGrossValue() {
        return grossValue.get();
    }

    public SimpleStringProperty grossValueProperty() {
        return grossValue;
    }

    public void setGrossValue() {
        double grossValue = 0;
        grossValue = Double.parseDouble(this.quantity.get()) * Double.parseDouble(this.grossPrice.get());
        this.grossValue.set(Double.toString(grossValue));
    }


}
