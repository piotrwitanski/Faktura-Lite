package com.company.invoice.utils;

import com.company.invoice.dto.Customer;

import java.util.List;

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

    public List<Customer> downloadCustomers() {
        try {
            List<Customer> customers = dataBaseUtils.downloadCustomers();
            return customers;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }

    }

}
