package com.company.invoice.validators;

public class ValidateDate implements Validator {
    private String date;
    //*TODO add setter

    @Override
    public boolean validate() {
        return validate(date);
    }

    //*TODO check current date with set date by user
    private boolean validate(String date) {
        return false;
    }
}
