package com.company.invoice.utils;

import com.company.invoice.dto.Invoice;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;
import static com.company.invoice.dictionaries.Errors.DOWNLOAD_DB_ERROR;

public class InvoiceUtils {
    private DataBaseUtils dataBaseUtils;

    public InvoiceUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addInvoiceToDB(Invoice invoice) {
        try {
            dataBaseUtils.addInvoiceToDB(invoice);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }

    public List<Invoice> downloadInvoices() {
        try {
            List<Invoice> invoices = dataBaseUtils.downloadInvoices();
            return invoices;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public Invoice downloadInvoice(int invoiceId) {
        try {
            Invoice invoice = dataBaseUtils.downloadInvoice(invoiceId);
            return invoice;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public String downloadInvoiceMaxNumber() {
        try {
            String invoiceNumber = dataBaseUtils.downloadInvoiceMaxNumber();
            return invoiceNumber;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public int downloadInvoiceLastId() {
        try {
            int invoiceId = dataBaseUtils.downloadInvoiceLastId();
            return invoiceId;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return -1;
        }
    }
}
