package com.company.invoice.utils;

import com.company.invoice.dto.Customer;

import java.sql.SQLOutput;
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
        catch(Exception e) {
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

    public Customer downloadCustomer(int customerId) {
        try {
            Customer customer = dataBaseUtils.downloadCustomer(customerId);
            return customer;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public int downloadCustomerLastId() {
        try {
            int customerId = dataBaseUtils.downloadCustomerLastId();
            return customerId;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return -1;
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            dataBaseUtils.updateCustomer(customer);
        }
        catch(Exception e) {
            System.out.println(UPDATE_DB_ERROR + e.getMessage());
        }
    }

    public void removeCustomer(int customerId) {
        try {
            dataBaseUtils.removeCustomer(customerId);
        }
        catch(Exception e) {
            System.out.println(REMOVE_FROM_DB_ERROR + e.getMessage());
        }
    }
}
