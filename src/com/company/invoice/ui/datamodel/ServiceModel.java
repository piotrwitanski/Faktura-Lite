package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class ServiceModel {
    private SimpleStringProperty type = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty vat = new SimpleStringProperty("");
    private SimpleStringProperty nettoPrice = new SimpleStringProperty("");
    private SimpleStringProperty bruttoPrice = new SimpleStringProperty("");

    public ServiceModel() {

    }

    public ServiceModel(String type, String name, String vat, String nettoPrice, String bruttoPrice) {
        this.type.set(type);
        this.name.set(name);
        this.vat.set(vat);
        this.nettoPrice.set(nettoPrice);
        this.bruttoPrice.set(bruttoPrice);
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
    }

    public String getBruttoPrice() {
        return bruttoPrice.get();
    }

    public SimpleStringProperty bruttoPriceProperty() {
        return bruttoPrice;
    }

    public void setBruttoPrice(String bruttoPrice) {
        this.bruttoPrice.set(bruttoPrice);
    }
}
