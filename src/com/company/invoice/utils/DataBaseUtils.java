package com.company.invoice.utils;

import com.company.invoice.db.DataBase;
import com.company.invoice.dto.Customer;
import com.company.invoice.dto.Invoice;
import com.company.invoice.dto.Product;
import com.company.invoice.dto.User;

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

    /**
     * Transfer Customer List
     * @return customers List
     */
    public List<Customer> downloadCustomersFromDB() {

        dataBase.open();

        List<Customer> customers = dataBase.downloadCustomers();

        dataBase.close();

        return customers;
    }

    public List<User> downloadUsersFromDB() {

        dataBase.open();

        List<User> users = dataBase.downloadUsers();

        dataBase.close();

        return users;
    }
}
