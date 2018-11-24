package com.company.invoice.utils;

import com.company.invoice.dto.Customer;

import static com.company.invoice.dictionaries.Errors.*;

public class CustomerUtils {

    private DataBaseUtils dataBaseUtils;

    public CustomerUtils() {
        dataBaseUtils = new DataBaseUtils();
    }

    public void addCustomerToDB(Customer customer) {

        try {
            dataBaseUtils.addCustomerToDB(customer);
        }
        catch (Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }


}
