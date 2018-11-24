package com.company.invoice.dto;

public class User {
    private int id;
    private String name;
    private String city;
    private String street;
    private String postCode;
    private String NIP;

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

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nAdress: " + this.postCode + " " + this.city + ", " + this.street +
                "\nNIP: " + this.NIP;
    }
}
