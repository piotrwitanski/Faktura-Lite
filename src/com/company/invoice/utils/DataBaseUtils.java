package com.company.invoice.utils;

import com.company.invoice.db.DataBase;
import com.company.invoice.dto.*;

import java.util.List;

public class DataBaseUtils {

    private DataBase dataBase;

    public DataBaseUtils() {
        this.dataBase = new DataBase();
    }

    /**
     * Adding new Customer to database
     *
     * @param customer dto for database
     */
    public void addCustomerToDB(Customer customer) {

        dataBase.open();

        dataBase.addStatement(customer);

        dataBase.close();
    }

    /**
     * Adding new User to database
     *
     * @param user dto for database
     */
    public void addUserToDB(User user) {

        dataBase.open();

        dataBase.addStatement(user);

        dataBase.close();
    }

    /**
     * Adding new Product to database
     *
     * @param product dto for database
     */
    public void addProductToDB(Product product) {

        dataBase.open();

        dataBase.addStatement(product);

        dataBase.close();
    }

    public Product downloadProduct(int productId) {

        dataBase.open();

        Product product = dataBase.downloadProduct(productId);

        dataBase.close();

        return product;
    }

    public void updateProduct(Product product) {

        dataBase.open();

        dataBase.updateProduct(product);

        dataBase.close();
    }

    public void removeProduct(int productId) {

        dataBase.open();

        dataBase.removeProduct(productId);

        dataBase.close();
    }

    public int downloadProductLastId() {

        dataBase.open();

        int productId = dataBase.downloadProductLastId();

        dataBase.close();

        return  productId;
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

    public void addPaymentToDB(Payment payment) {

        dataBase.open();

        dataBase.addStatement(payment);

        dataBase.close();
    }

    /**
     * Transfer Customer List
     *
     * @return customers List
     */
    public List<Customer> downloadCustomers() {

        dataBase.open();

        List<Customer> customers = dataBase.downloadCustomers();

        dataBase.close();

        return customers;
    }

    public Customer downloadCustomer(int customerId) {

        dataBase.open();

        Customer customer = dataBase.downloadCustomer(customerId);

        dataBase.close();

        return customer;
    }

    public List<User> downloadUsers() {

        dataBase.open();

        List<User> users = dataBase.downloadUsers();

        dataBase.close();

        return users;
    }

    public User downloadUser(int userId) {

        dataBase.open();

        User user = dataBase.downloadUser(userId);

        dataBase.close();

        return user;
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

    public Invoice downloadInvoice(int invoiceId) {

        dataBase.open();

        Invoice invoice = dataBase.downloadInvoice(invoiceId);

        dataBase.close();

        return invoice;
    }

    public List<Item> downloadItems(int invoiceId) {

        dataBase.open();

        List<Item> items = dataBase.downloadItems(invoiceId);

        dataBase.close();

        return items;
    }

    public List<Payment> downloadPayments() {

        dataBase.open();

        List<Payment> payments = dataBase.downloadPayments();

        dataBase.close();

        return payments;
    }

    public Payment downloadPayment(int paymentId) {

        dataBase.open();

        Payment payment = dataBase.downloadPayment(paymentId);

        dataBase.close();

        return payment;
    }

    public String downloadInvoiceMaxNumber() {

        dataBase.open();

        String invoiceNumber = dataBase.downloadInvoiceMaxNumber();

        dataBase.close();

        return invoiceNumber;
    }

    public int downloadInvoiceLastId() {

        dataBase.open();

        int invoiceId = dataBase.downloadInvoiceLastId();

        dataBase.close();

        return invoiceId;
    }

    public void updateInvoice(Invoice invoice) {

        dataBase.open();

        dataBase.updateInvoice(invoice);

        dataBase.close();
    }

    public void removeItem(int invoiceId) {

        dataBase.open();

        dataBase.removeItem(invoiceId);

        dataBase.close();

    }

    public void removeInvoice(int invoiceId) {

        dataBase.open();

        dataBase.removeInvoice(invoiceId);

        dataBase.close();
    }

    public int downloadCustomerLastId() {

        dataBase.open();

        int customerLastId = dataBase.downloadCustomerLastID();

        dataBase.close();

        return customerLastId;
    }

    public void updateCustomer(Customer customer) {

        dataBase.open();

        dataBase.updateCustomer(customer);

        dataBase.close();
    }

    public void removeCustomer(int customerId) {

        dataBase.open();

        dataBase.removeCustomer(customerId);

        dataBase.close();
    }
}
