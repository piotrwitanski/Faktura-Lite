package com.company.invoice.ui.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class InvoiceModel {
    private SimpleStringProperty invoiceId = new SimpleStringProperty("");
    private SimpleStringProperty invoiceType = new SimpleStringProperty("");
    private SimpleStringProperty invoiceNumber = new SimpleStringProperty("");
    private SimpleStringProperty issueDate = new SimpleStringProperty("");
    private SimpleStringProperty invoiceDate = new SimpleStringProperty("");
    private SimpleStringProperty customerName = new SimpleStringProperty("");
    private SimpleStringProperty netValue = new SimpleStringProperty("");
    private SimpleStringProperty grossValue = new SimpleStringProperty("");
    private SimpleStringProperty vatValue = new SimpleStringProperty("");
    private SimpleStringProperty currency = new SimpleStringProperty("");

    public InvoiceModel() {

    }

    public InvoiceModel(String invoiceId, String invoiceType, String invoiceNumber, String issueDate, String customerName,
                        String nettoValue, String bruttoValue, String vatValue, String currency) {
        this.invoiceId.set(invoiceId);
        this.invoiceType.set(invoiceType);
        this.invoiceNumber.set(invoiceNumber);
        this.issueDate.set(issueDate);
        this.customerName.set(customerName);
        this.netValue.set(nettoValue);
        this.grossValue.set(bruttoValue);
        this.vatValue.set(vatValue);
        this.currency.set(currency);
    }

    public String getInvoiceId() {
        return invoiceId.get();
    }

    public SimpleStringProperty invoiceIdProperty() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId.set(invoiceId);
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

    public String getInvoiceDate() {
        return invoiceDate.get();
    }

    public SimpleStringProperty invoiceDateProperty() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate.set(invoiceDate);
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

    public String getNetValue() {
        return netValue.get();
    }

    public SimpleStringProperty netValueProperty() {
        return netValue;
    }

    public void setNetValue(String netValue) {
        this.netValue.set(netValue);
    }

    public String getGrossValue() {
        return grossValue.get();
    }

    public SimpleStringProperty grossValueProperty() {
        return grossValue;
    }

    public void setGrossValue(String grossValue) {
        this.grossValue.set(grossValue);
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
                ", netValue=" + netValue +
                ", grossValue=" + grossValue +
                ", vatValue=" + vatValue +
                ", currency=" + currency +
                '}';
    }
}
