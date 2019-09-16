package com.company.invoice.dto;

public class User {
    private int id;
    private String name;
    private String city;
    private String street;
    private String postCode;
    private String NIP;
    private int houseNumber;
    private int apartmentNumber;
    private String bankAccount;

    public User(int id, String name, String city, String street, String postCode, String NIP, int houseNumber, int apartmentNumber, String bankAccount) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.NIP = NIP;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.bankAccount = bankAccount;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nAdress: " + this.postCode + " " + this.city + ", " + this.street +
                "\nNIP: " + this.NIP +
                "\nBank Account: " + this.bankAccount;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Item)) {
            return false;
        }

        User user = (User) obj;

        return id == user.id && name.equals(user.name) && city.equals(user.city) && street.equals(user.street) && postCode.equals(user.postCode)
                    && NIP.equals(user.NIP) && houseNumber == user.houseNumber && apartmentNumber == user.apartmentNumber && bankAccount.equals(user.bankAccount);
    }
}
