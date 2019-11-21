package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class UserModel {
    private SimpleStringProperty id = new SimpleStringProperty("");
    private SimpleStringProperty name = new SimpleStringProperty("");
    private SimpleStringProperty city = new SimpleStringProperty("");
    private SimpleStringProperty street = new SimpleStringProperty("");
    private SimpleStringProperty postCode = new SimpleStringProperty("");
    private SimpleStringProperty NIP = new SimpleStringProperty("");
    private SimpleStringProperty houseNumber = new SimpleStringProperty("");
    private SimpleStringProperty apartmentNumber = new SimpleStringProperty("");
    private SimpleStringProperty bankAccount = new SimpleStringProperty("");


    public UserModel() {

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

    public String getBankAccount() {
        return bankAccount.get();
    }

    public SimpleStringProperty bankAccountProperty() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount.set(bankAccount);
    }

    public String getCity() {
        return city.get();
    }

    public SimpleStringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public SimpleStringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getPostCode() {
        return postCode.get();
    }

    public SimpleStringProperty postCodeProperty() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode.set(postCode);
    }

    public String getNIP() {
        return NIP.get();
    }

    public SimpleStringProperty NIPProperty() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP.set(NIP);
    }

    public String getHouseNumber() {
        return houseNumber.get();
    }

    public SimpleStringProperty houseNumberProperty() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber.set(houseNumber);
    }

    public String getApartmentNumber() {
        return apartmentNumber.get();
    }

    public SimpleStringProperty apartmentNumberProperty() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber.set(apartmentNumber);
    }
}

