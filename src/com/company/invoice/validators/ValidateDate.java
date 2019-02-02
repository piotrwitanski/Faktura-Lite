package com.company.invoice.validators;

import java.time.LocalDate;

public class ValidateDate implements Validator {
    private int  invoiceYear;
    private LocalDate today;

    public ValidateDate() {
        today = LocalDate.now();
    }

    @Override
    public boolean validate() {
        return validate(invoiceYear);
    }

    //*TODO check current date with set date by user
    private boolean validate(int invoiceYear) {
        int year = today.getYear();
        if(invoiceYear == year) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setInvoiceYear(int invoiceYear) {
        this.invoiceYear = invoiceYear;
    }
}
