package com.company.invoice.dto;

public class Item {
    private int id;
    private int invoiceId;
    private String name;
    private int quantity;
    private double grossPrice;
    private double netPrice;
    private int vat;
    private String unitOfMeasure;
    private String type;

    public Item(int id, int invoiceId, String name, int quantity, double grossPrice, double netPrice, int vat, String unitOfMeasure, String type) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.name = name;
        this.quantity = quantity;
        this.grossPrice = grossPrice;
        this.netPrice = netPrice;
        this.vat = vat;
        this.unitOfMeasure = unitOfMeasure;
        this.type = type;
    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public double getNetPrice() {
        return netPrice;
    }

    public void setDBPriceNet(int priceNet) {
        this.netPrice = (double)priceNet / 100;
    }

    public int getDBPriceNet() {
        return (int)this.netPrice * 100;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public int getDBPriceGross() {
        return (int)this.grossPrice * 100;
    }

    public void setGrossPrice(double grossPrice) {
        this.grossPrice = grossPrice;
        this.netPrice = this.grossPrice * (1.0 - ((double)this.vat / 100));
    }

    public void setDBPriceGross(int priceGross) {
        this.grossPrice = (double)priceGross / 100;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item Id: " + this.id + " Invoice Id: " + this.invoiceId +
                "\nName: " + this.name + " quantity: " + this.quantity +
                " price Gross: " + this.grossPrice + " price Net: " + this.netPrice + " VAT: " + this.vat + " UoM: " + this.unitOfMeasure;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Item)) {
            return false;
        }

        Item item = (Item) obj;

        return id == item.id && invoiceId == item.invoiceId && name.equals(item.name) && quantity == item.quantity && Double.compare(grossPrice, item.grossPrice) == 0
                    && Double.compare(netPrice, item.netPrice) == 0 && vat == item.vat && unitOfMeasure.equals(item.unitOfMeasure) && type.equals(item.type);
    }

}
