package com.company.invoice.dto;

public class Product {
    private int id;
    private String name;
    private double grossPrice;
    private double netPrice;
    private int vat;
    private String unitOfMeasure;
    private String type;

    public Product(int id, String name, double grossPrice, double netPrice, int vat, String unitOfMeasure, String type) {
        this.id = id;
        this.name = name;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
        this.vat = vat;
        this.unitOfMeasure = unitOfMeasure;
        this.type = type;
    }

    public Product() {

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

    public double getNetPrice() {
        return netPrice;
    }

    public int getDBPriceNet() {
        return (int)this.netPrice * 100;
    }

    public void setDBPriceNet(int netPrice) {
        this.netPrice = (double)netPrice / 100;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public int getDBPriceGross() {
        return (int)this.grossPrice * 100;
    }

    public void setGrossPrice(double grossPrice) {
        this.grossPrice = grossPrice;
        this.netPrice = this.grossPrice - (this.grossPrice * (double)this.vat / (100 + (double)this.vat));
    //*TODO check precision of this method to calculate net price
    }

    public void setDBPriceGross(int grossPrice) {
        this.grossPrice = (double)grossPrice / 100;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Name: " + this.name +
                "\nPrice Gross: " + this.grossPrice + " Price Net: " + this.netPrice + " VAT: " + this.vat + " UoM: " + this.unitOfMeasure;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Item)) {
            return false;
        }

        Product product = (Product) obj;

        return id == product.id && name.equals(product.name) && Double.compare(grossPrice, product.grossPrice) == 0 && Double.compare(netPrice, product.netPrice) == 0
                    && vat == product.vat && unitOfMeasure.equals(product.unitOfMeasure) && type.equals(product.type);
    }
}
