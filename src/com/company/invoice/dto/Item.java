package com.company.invoice.dto;

public class Item {
    private int id;
    private int invoiceId;
    private String name;
    private int quantity;
    private double priceBrutto;
    private double priceNetto;
    private int vat;

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

    public double getPriceBrutto() {
        return priceBrutto;
    }

    public double getPriceNetto() {
        return priceNetto;
    }

    public int getDBPriceNetto() {
        return (int)this.priceNetto;
    }

    public int getDBPriceBrutto() {
        return (int)this.priceBrutto * 100;
    }

    public void setPriceBrutto(double priceBrutto) {
        this.priceBrutto = priceBrutto;
        this.priceNetto = this.priceBrutto * (1.0 - ((double)this.vat / 100));
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
}
