package com.company.invoice.dto;

public class Invoice {
    private int id;
    private int customerId;
    private int userId;
    private String invoiceDate; //sell date
    private String issueDate;
    private int paymentId;
    private String invoiceType;
    private String invoiceNumber;

    public Invoice(int id, int customerId, int userId, String invoiceDate, String issueDate, int paymentId, String invoiceType, String invoiceNumber) {
        this.id = id;
        this.customerId = customerId;
        this.userId = userId;
        this.invoiceDate = invoiceDate;
        this.issueDate = issueDate;
        this.paymentId = paymentId;
        this.invoiceType = invoiceType;
        this.invoiceNumber = invoiceNumber;
    }

    public Invoice() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "Invoice NO: " + this.id +
                "\nCustomer id: " + this.customerId + " Invoice date: " + this.invoiceDate + " Issue date: " + this.issueDate + " Payment id: " + this.paymentId;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Item)) {
            return false;
        }

        Invoice invoice = (Invoice) obj;

        return id == invoice.id && customerId == invoice.customerId && userId == invoice.userId && invoiceDate.equals(invoice.invoiceDate) && issueDate.equals(invoice.issueDate)
                    && paymentId == invoice.paymentId && invoiceType.equals(invoice.invoiceType) && invoiceNumber.equals(invoice.invoiceNumber);
    }
}
