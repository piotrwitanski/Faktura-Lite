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



    public double getPriceNetto() {
        return priceNetto;
    }

    public void setDBPriceNetto(int priceNetto) {
        this.priceNetto = (double)priceNetto / 100;
    }

    public int getDBPriceNetto() {
        return (int)this.priceNetto;
    }

    public double getPriceBrutto() {
        return priceBrutto;
    }

    public int getDBPriceBrutto() {
        return (int)this.priceBrutto * 100;
    }

    public void setPriceBrutto(double priceBrutto) {
        this.priceBrutto = priceBrutto;
        this.priceNetto = this.priceBrutto * (1.0 - ((double)this.vat / 100));
    }

    public void setDBPriceBrutto(int priceBrutto) {
        this.priceBrutto = (double)priceBrutto / 100;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    @Override
    public String toString() {
        return "Item Id: " + this.id + " Invoice Id: " + this.invoiceId +
                "\nName: " + this.name + " quantity: " + this.quantity +
                " price Brutto: " + this.priceBrutto + " price Netto: " + this.priceNetto + " VAT: " + this.vat;
    }
}
