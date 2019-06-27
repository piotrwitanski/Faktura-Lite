package com.company.test;

import com.company.invoice.dto.Invoice;
import com.company.invoice.utils.DataBaseUtils;
import com.company.invoice.utils.InvoiceUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InvoiceUtilsTest {

    private InvoiceUtils invoiceUtils;
    private DataBaseUtils dataBaseUtils;

    @org.junit.Before
    public void setup() {
        invoiceUtils = new InvoiceUtils();
        dataBaseUtils = new DataBaseUtils();
    }
    @Test
    public void addInvoiceToDB() {
    }

    @Test
    public void downloadInvoices() {
        List<Invoice> invoices = dataBaseUtils.downloadInvoices();

        List<Invoice> expectedInvoices = Arrays.asList(
                new Invoice(1, 1, 1, "09-12-2018", "25-11-2018", 1, "Faktura", "01/05/2018"),
                new Invoice(2, 2, 1, "09-12-2018", "25-11-2018", 2, "Faktura", "02/07/2018"),
                new Invoice(3, 1, 1, "09-12-2018", "25-11-2018", 1, "Faktura", "12/09/2018"),
                new Invoice(5, 1, 1, "09-02-2019", "26-01-2019", 2, "Faktura", "10/01/2018")
        );


    }

    @Test
    public void downloadInvoice() {
        Invoice invoice = invoiceUtils.downloadInvoice(1);

        assertEquals("01/05/2018", invoice.getInvoiceNumber());
    }

    @Test
    public void downloadInvoiceMaxNumber() {
        String invoiceNumber = invoiceUtils.downloadInvoiceMaxNumber();

        assertEquals("10/01/2018", invoiceNumber);
    }

    @Test
    public void downloadInvoiceLastId() {
        int invoiceId = invoiceUtils.downloadInvoiceLastId();

        assertEquals(5, invoiceId);
        //*TODO this method do not download last id from db but last existing id
    }


}