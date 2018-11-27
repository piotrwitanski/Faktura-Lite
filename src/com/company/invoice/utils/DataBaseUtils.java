package com.company.invoice.utils;

import com.company.invoice.db.DataBase;
import com.company.invoice.dto.*;

import java.util.ArrayList;
import java.util.List;

public class DataBaseUtils {

    private DataBase dataBase;

    public DataBaseUtils() {
        this.dataBase = new DataBase();
    }

    /**
     * Adding new Customer to database
     * @param customer dto for database
     */
    public void addCustomerToDB(Customer customer) {

        dataBase.open();

        dataBase.addStatement(customer);

        dataBase.close();
    }

    /**
     * Adding new User to database
     * @param user dto for database
     */
    public void addUserToDB(User user) {

        dataBase.open();

        dataBase.addStatement(user);

        dataBase.close();
    }

    /**
     * Adding new Product to database
     * @param product dto for database
     */
    public void addProductToDB(Product product) {

        dataBase.open();

        dataBase.addStatement(product);

        dataBase.close();
    }


    public void addInvoiceToDB(Invoice invoice) {

        dataBase.open();

        dataBase.addStatement(invoice);

        dataBase.close();
    }

    public void addItemToDB(Item item) {

        dataBase.open();

        dataBase.addStatement(item);

        dataBase.close();
    }

    /**
     * Transfer Customer List
     * @return customers List
     */
    public List<Customer> downloadCustomers() {

        dataBase.open();

        List<Customer> customers = dataBase.downloadCustomers();

        dataBase.close();

        return customers;
    }

    public List<User> downloadUsers() {

        dataBase.open();

        List<User> users = dataBase.downloadUsers();

        dataBase.close();

        return users;
    }

    public List<Product> downloadProducts() {

        dataBase.open();

        List<Product> products = dataBase.downloadProducts();

        dataBase.close();

        return products;
    }

    public List<Invoice> downloadInvoices() {

        dataBase.open();

        List<Invoice> invoices = dataBase.downloadInvoices();

        dataBase.close();

        return invoices;
    }

    public List<Item> downloadItems(int invoiceId) {

        dataBase.open();

        List<Item> items = dataBase.downloadItems(invoiceId);

        dataBase.close();

        return items;
    }
}
