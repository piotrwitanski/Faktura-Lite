package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class InvoiceModel {
    private SimpleStringProperty invoiceType = new SimpleStringProperty("");
    private SimpleStringProperty invoiceNumber = new SimpleStringProperty("");
    private SimpleStringProperty issueDate = new SimpleStringProperty("");
    private SimpleStringProperty customerName = new SimpleStringProperty("");
    private SimpleStringProperty nettoValue = new SimpleStringProperty("");
    private SimpleStringProperty bruttoValue = new SimpleStringProperty("");
    private SimpleStringProperty vatValue = new SimpleStringProperty("");
    private SimpleStringProperty currency = new SimpleStringProperty("");

    public InvoiceModel() {

    }

    public InvoiceModel(String invoiceType, String invoiceNumber, String issueDate, String customerName,
                        String nettoValue, String bruttoValue, String vatValue, String currency) {
        this.invoiceType.set(invoiceType);
        this.invoiceNumber.set(invoiceNumber);
        this.issueDate.set(issueDate);
        this.customerName.set(customerName);
        this.nettoValue.set(nettoValue);
        this.bruttoValue.set(bruttoValue);
        this.vatValue.set(vatValue);
        this.currency.set(currency);
    }

    public String getInvoiceType() {
        return invoiceType.get();
    }

    public SimpleStringProperty invoiceTypeProperty() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType.set(invoiceType);
    }

    public String getInvoiceNumber() {
        return invoiceNumber.get();
    }

    public SimpleStringProperty invoiceNumberProperty() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber.set(invoiceNumber);
    }

    public String getIssueDate() {
        return issueDate.get();
    }

    public SimpleStringProperty issueDateProperty() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate.set(issueDate);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getNettoValue() {
        return nettoValue.get();
    }

    public SimpleStringProperty nettoValueProperty() {
        return nettoValue;
    }

    public void setNettoValue(String nettoValue) {
        this.nettoValue.set(nettoValue);
    }

    public String getBruttoValue() {
        return bruttoValue.get();
    }

    public SimpleStringProperty bruttoValueProperty() {
        return bruttoValue;
    }

    public void setBruttoValue(String bruttoValue) {
        this.bruttoValue.set(bruttoValue);
    }

    public String getVatValue() {
        return vatValue.get();
    }

    public SimpleStringProperty vatValueProperty() {
        return vatValue;
    }

    public void setVatValue(String vatValue) {
        this.vatValue.set(vatValue);
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

    @Override
    public String toString() {
        return "InvoiceModel{" +
                "invoiceType=" + invoiceType +
                ", invoiceNumber=" + invoiceNumber +
                ", issueDate=" + issueDate +
                ", customerName=" + customerName +
                ", nettoValue=" + nettoValue +
                ", bruttoValue=" + bruttoValue +
                ", vatValue=" + vatValue +
                ", currency=" + currency +
                '}';
    }
}
