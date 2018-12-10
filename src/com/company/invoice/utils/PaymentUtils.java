package com.company.invoice.utils;

import com.company.invoice.dto.Payment;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;
import static com.company.invoice.dictionaries.Errors.DOWNLOAD_DB_ERROR;

public class PaymentUtils {
    private DataBaseUtils dataBaseUtils;

    public PaymentUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addPaymentToDB(Payment payment) {
        try {
            dataBaseUtils.addPaymentToDB(payment);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }

    public List<Payment> downloadPayments() {
        try {
            List<Payment> payments = dataBaseUtils.downloadPayments();
            return payments;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public Payment downloadPayment(int paymentId) {
        try {
            Payment payment = dataBaseUtils.downloadPayment(paymentId);
            return payment;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }
}
