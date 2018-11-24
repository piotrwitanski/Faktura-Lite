package com.company.invoice.utils;

import com.company.invoice.dto.Invoice;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;

public class InvoiceUtils {
    private DataBaseUtils dataBaseUtils;

    public InvoiceUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addInvoiceToDB(Invoice invoice) {
        try{
            dataBaseUtils.addInvoiceToDB(invoice);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }
}
