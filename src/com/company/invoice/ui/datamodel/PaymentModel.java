package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class PaymentModel {
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty currency = new SimpleStringProperty("");

    public PaymentModel() {

    }

    public PaymentModel(String id, String name, String currency) {
        this.id.set(id);
        this.name.set(name);
        this.currency.set(currency);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCurrency() {
        return currency.get();
    }

    public SimpleStringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }
}
