package com.company.invoice.dto;

public class Invoice {
    private int id;
    private int customerId;
    private int userId;
    private String invoiceDate; //sell date
    private String issueDate;

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

    @Override
    public String toString() {
        return "Invoice NO: " + this.id +
                "\nCustomer id: " + this.customerId + " Invoice date: " + this.invoiceDate + " Issue date: " + this.issueDate;
    }
}
