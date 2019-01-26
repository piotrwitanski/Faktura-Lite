package com.company.invoice.db;

import com.company.invoice.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.company.invoice.dictionaries.Dictionary.*;
import static com.company.invoice.dictionaries.Errors.SELECT_DB_ERROR;

public class DataBase {
    private Connection conn;
    private Statement statement;

    /**
     * Open connection to database
     * @return false when there is a problem to connect with database and true when connected with database
     * @constant CONNECTION_STRING a database path from Dictionary.java
     */
    public boolean open() {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }
        catch(SQLException e) {
            System.out.println("Couldn't connect to database" + e.getMessage());
            return false;
        }
    }

    /**
     * Close connection to database
     *
     */
    public void close() {
        try {
            if(conn != null)
                conn.close();
        }
        catch (SQLException e) {
            System.out.println("Couldn't close connection:" + e.getMessage());
        }
    }

    /**
     * Adding new Customer to database
     * Customer id is automatically incremented (send NULL to database)
     * @param customer transfer data to table customer from database
     */
    public void addStatement(Customer customer) {
        try(Statement statement = conn.createStatement()){

            statement.execute("INSERT INTO " + TABLE_CUSTOMER +
                    " (" + COLUMN_CUSTOMER_ID + ", " +
                    COLUMN_CUSTOMER_NAME + ", " +
                    COLUMN_CUSTOMER_CITY + ", " +
                    COLUMN_CUSTOMER_STREET + ", " +
                    COLUMN_CUSTOMER_POST_CODE + ", " +
                    COLUMN_CUSTOMER_NIP + ", " +
                    COLUMN_CUSTOMER_HOUSE_NUMBER + ", " +
                    COLUMN_CUSTOMER_APARTMENT_NUMBER + ", " +
                    COLUMN_CUSTOMER_BANK_ACCOUNT +
                    ")" +
                    "VALUES(NULL" + ", '" + customer.getName()  +
                            "', '" + customer.getCity() + "', '"  + customer.getStreet() + "', '"  +
                            customer.getPostCode() + "', '" + customer.getNIP() + "', " +
                            customer.getHouseNumber() + ", " + customer.getApartmentNumber() + ", '" +
                            customer.getBankAccount() + "')");
        }
        catch (SQLException e) {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }
    /**
     * Adding new User to database
     * Customer id is automatically incremented (send NULL to database)
     * @param user transfer data to table customer from database
     */
    public void addStatement(User user) {
        try(Statement statement = conn.createStatement()){

            statement.execute("INSERT INTO " + TABLE_USER +
                    " (" + COLUMN_USER_ID + ", " +
                    COLUMN_USER_NAME + ", " +
                    COLUMN_USER_CITY + ", " +
                    COLUMN_USER_STREET + ", " +
                    COLUMN_USER_POST_CODE + ", " +
                    COLUMN_USER_NIP +
                    ")" +
                    "VALUES(NULL" + ", '" + user.getName()  +
                    "', '" + user.getCity() + "', '"  + user.getStreet() + "', '"  +
                    user.getPostCode() + "', '" + user.getNIP() + "')");
        }
        catch (SQLException e) {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }

    /**
     * Adding new Product to database
     * @param product transfer data to table customer from database
     */
    public void addStatement(Product product) {
        try(Statement statement = conn.createStatement()){

            statement.execute("INSERT INTO " + TABLE_PRODUCT +
                    " (" + COLUMN_PRODUCT_ID + ", " +
                    COLUMN_PRODUCT_NAME + ", " +
                    COLUMN_PRODUCT_PRICE_BRUTTO + ", " +
                    COLUMN_PRODUCT_PRICE_NETTO+ ", " +
                    COLUMN_PRODUCT_VAT + ", " +
                    COLUMN_PRODUCT_UNIT_OF_MEASURE + ", " +
                    COLUMN_PRODUCT_TYPE +
                    ")" +
                    "VALUES(NULL" +  ", '" + product.getName() + "', " +
                    product.getDBPriceBrutto() + ", " + product.getDBPriceNetto() + ", " +
                    product.getVat() + ", '" + product.getUnitOfMeasure() + "', '" +
                    product.getType() +"')");
        }
        catch (SQLException e) {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }

    /**
     * Adding new Invoice to database
     * @param invoice transfer data to table invoice from database
     */
    public void addStatement(Invoice invoice) {
        try(Statement statement = conn.createStatement()){

            statement.execute("INSERT INTO " + TABLE_INVOICE +
                    " (" + COLUMN_INVOICE_ID + ", " +
                    COLUMN_INVOICE_NUMBER + ", " +
                    COLUMN_INVOICE_TYPE + ", " +
                    COLUMN_INVOICE_CUSTOMER_ID + ", " +
                    COLUMN_INVOICE_USER_ID + ", " +
                    COLUMN_INVOICE_INVOICE_DATE + ", " +
                    COLUMN_INVOICE_ISSUE_DATE + ", " +
                    COLUMN_INVOICE_PAYMENT_ID +
                    ")" +
                    "VALUES(NULL" + ", '" +
                    invoice.getInvoiceNumber() + "', '" + invoice.getInvoiceType() + "', " +
                    invoice.getCustomerId() + ", " +
                    invoice.getUserId() + ", '" + invoice.getInvoiceDate() + "', '" +
                    invoice.getIssueDate() + "', " + invoice.getPaymentId() + ")");
        }
        catch (SQLException e) {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }

    public void addStatement(Item item) {
        try(Statement statement = conn.createStatement()){

            statement.execute("INSERT INTO " + TABLE_ITEM +
                    " (" + COLUMN_ITEM_ID + ", " +
                    COLUMN_ITEM_INVOICE_ID + ", " +
                    COLUMN_ITEM_NAME + ", " +
                    COLUMN_ITEM_QUANTITY + ", " +
                    COLUMN_ITEM_PRICE_BRUTTO + ", " +
                    COLUMN_ITEM_PRICE_NETTO + ", " +
                    COLUMN_ITEM_VAT + ", " +
                    COLUMN_ITEM_UNIT_OF_MEASURE +
                    ")" +
                    "VALUES(NULL" +  ", " + item.getInvoiceId() + ", '" +
                    item.getName() + "', " + item.getQuantity() + ", " +
                    item.getDBPriceBrutto() + ", " + item.getDBPriceNetto() + ", " +
                    item.getVat() + ", '" + item.getUnitOfMeasure() + "')");
        }
        catch (SQLException e) {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }

    /**
     * Method for adding payment to database
     * @param payment
     */
    public void addStatement(Payment payment) {
        try(Statement statement = conn.createStatement()){

            statement.execute("INSERT INTO " + TABLE_PAYMENT +
                    " (" + COLUMN_PAYMENT_ID + ", " +
                    COLUMN_PAYMENT_NAME + ", " +
                    COLUMN_PAYMENT_CURRENCY +
                    ")" +
                    "VALUES(NULL" + ", '" +
                    payment.getName() + "', '" + payment.getCurrency() + "')");
        }
        catch (SQLException e) {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }

    /**
     * Downloading Customers List from database.
     * @return customerList with all db customer list or {@code null} when there is a problem with database
     */
    public List<Customer> downloadCustomers() {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_CUSTOMER)){

            List<Customer> customerList = new ArrayList<>();
            while(result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt(COLUMN_CUSTOMER_ID));
                customer.setName(result.getString(COLUMN_CUSTOMER_NAME));
                customer.setCity(result.getString(COLUMN_CUSTOMER_CITY));
                customer.setStreet(result.getString(COLUMN_CUSTOMER_STREET));
                customer.setPostCode(result.getString(COLUMN_CUSTOMER_POST_CODE));
                customer.setNIP(result.getString(COLUMN_CUSTOMER_NIP));
                customer.setHouseNumber(result.getInt(COLUMN_CUSTOMER_HOUSE_NUMBER));
                customer.setApartmentNumber(result.getInt(COLUMN_CUSTOMER_APARTMENT_NUMBER));
                customer.setBankAccount(result.getString(COLUMN_CUSTOMER_BANK_ACCOUNT));

                customerList.add(customer);
            }

            return customerList;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    /**
     * Method download one Cutomer from database for Invoice
     * @param customerId specify which customerId we want download from database
     * @return Customer
     */
    public Customer downloadCustomer(int customerId) {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_CUSTOMER +
                                                        " WHERE " + COLUMN_CUSTOMER_ID + " = " + customerId)){
            Customer customer = new Customer();

            while(result.next()) {
                customer.setId(result.getInt(COLUMN_CUSTOMER_ID));
                customer.setName(result.getString(COLUMN_CUSTOMER_NAME));
                customer.setCity(result.getString(COLUMN_CUSTOMER_CITY));
                customer.setStreet(result.getString(COLUMN_CUSTOMER_STREET));
                customer.setPostCode(result.getString(COLUMN_CUSTOMER_POST_CODE));
                customer.setNIP(result.getString(COLUMN_CUSTOMER_NIP));
                customer.setHouseNumber(result.getInt(COLUMN_CUSTOMER_HOUSE_NUMBER));
                customer.setApartmentNumber(result.getInt(COLUMN_CUSTOMER_APARTMENT_NUMBER));
                customer.setBankAccount(result.getString(COLUMN_CUSTOMER_BANK_ACCOUNT));

            }

            return customer;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    /**
     * Downloading Users List from database.
     * @return userList with all db users  or {@code null} when there is a problem with database
     */
    public List<User> downloadUsers() {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_USER)){

            List<User> userList = new ArrayList<>();
            while(result.next()) {
                User user = new User();
                user.setId(result.getInt(COLUMN_USER_ID));
                user.setName(result.getString(COLUMN_USER_NAME));
                user.setCity(result.getString(COLUMN_USER_CITY));
                user.setStreet(result.getString(COLUMN_USER_STREET));
                user.setPostCode(result.getString(COLUMN_USER_POST_CODE));
                user.setNIP(result.getString(COLUMN_USER_NIP));

                userList.add(user);
            }

            return userList;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    /**
     * Method download User from database for Invoice
     * @param userId specify which user we want to download
     * @return User
     */
    public User downloadUser(int userId) {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_USER +
                    " WHERE " + COLUMN_USER_ID + " = " + userId)){
            User user = new User();

            while(result.next()) {
                user.setId(result.getInt(COLUMN_USER_ID));
                user.setName(result.getString(COLUMN_USER_NAME));
                user.setCity(result.getString(COLUMN_USER_CITY));
                user.setStreet(result.getString(COLUMN_USER_STREET));
                user.setPostCode(result.getString(COLUMN_USER_POST_CODE));
                user.setNIP(result.getString(COLUMN_USER_NIP));
            }

            return user;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }

    }

    /**
     * Downloading Products List from database.
     * @return productList with all db products or {@code null} when there is a problem with database
     */
    public List<Product> downloadProducts() {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_PRODUCT)){

            List<Product> productList = new ArrayList<>();
            while(result.next()) {
                Product product = new Product();
                product.setId(result.getInt(COLUMN_PRODUCT_ID));
                product.setName(result.getString(COLUMN_PRODUCT_NAME));
                product.setDBPriceBrutto(result.getInt(COLUMN_PRODUCT_PRICE_BRUTTO));
                product.setDBPriceNetto(result.getInt(COLUMN_PRODUCT_PRICE_NETTO));
                product.setVat(result.getInt(COLUMN_PRODUCT_VAT));
                product.setUnitOfMeasure(result.getString(COLUMN_PRODUCT_UNIT_OF_MEASURE));
                product.setType(result.getString(COLUMN_PRODUCT_TYPE));
                productList.add(product);
            }

            return productList;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public List<Invoice> downloadInvoices() {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_INVOICE)){

            List<Invoice> invoiceList = new ArrayList<>();
            while(result.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(result.getInt(COLUMN_CUSTOMER_ID));
                invoice.setCustomerId(result.getInt(COLUMN_INVOICE_CUSTOMER_ID));
                invoice.setUserId(result.getInt(COLUMN_INVOICE_USER_ID));
                invoice.setInvoiceDate(result.getString(COLUMN_INVOICE_INVOICE_DATE));
                invoice.setIssueDate(result.getString(COLUMN_INVOICE_ISSUE_DATE));
                invoice.setPaymentId(result.getInt(COLUMN_INVOICE_PAYMENT_ID));
                invoice.setInvoiceNumber(result.getString(COLUMN_INVOICE_NUMBER));
                invoice.setInvoiceType(result.getString(COLUMN_INVOICE_TYPE));

                invoiceList.add(invoice);
            }

            return invoiceList;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public Invoice downloadInvoice(int invoiceId) {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_INVOICE +
                    " WHERE " + COLUMN_INVOICE_ID + " = " + invoiceId)){

            Invoice invoice = new Invoice();

            while(result.next()) {
                invoice.setId(result.getInt(COLUMN_INVOICE_ID));
                invoice.setCustomerId(result.getInt(COLUMN_INVOICE_CUSTOMER_ID));
                invoice.setUserId(result.getInt(COLUMN_INVOICE_USER_ID));
                invoice.setInvoiceDate(result.getString(COLUMN_INVOICE_INVOICE_DATE));
                invoice.setIssueDate(result.getString(COLUMN_INVOICE_ISSUE_DATE));
                invoice.setPaymentId(result.getInt(COLUMN_INVOICE_PAYMENT_ID));
                invoice.setInvoiceNumber(result.getString(COLUMN_INVOICE_NUMBER));
                invoice.setInvoiceType(result.getString(COLUMN_INVOICE_TYPE));
            }

            return invoice;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    /**
     * Downloading itemList from database for specific invoice number
     * @param invoiceId indicate which item should be downloaded from database
     * @return list of Items that match the invoice id or {@code null} when there is a problem with database
     */
    public List<Item> downloadItems(int invoiceId) {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_ITEM +
                                                        " WHERE " + COLUMN_ITEM_INVOICE_ID + " = " + invoiceId)){

            List<Item> itemList = new ArrayList<>();
            while(result.next()) {
                Item item = new Item();
                item.setId(result.getInt(COLUMN_ITEM_ID));
                item.setInvoiceId(result.getInt(COLUMN_ITEM_INVOICE_ID));
                item.setName(result.getString(COLUMN_ITEM_NAME));
                item.setQuantity(result.getInt(COLUMN_ITEM_QUANTITY));
                item.setDBPriceBrutto(result.getInt(COLUMN_ITEM_PRICE_BRUTTO));
                item.setDBPriceNetto(result.getInt(COLUMN_ITEM_PRICE_NETTO));
                item.setVat(result.getInt(COLUMN_ITEM_VAT));
                item.setUnitOfMeasure(result.getString(COLUMN_ITEM_UNIT_OF_MEASURE));

                itemList.add(item);
            }

            return itemList;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    /**
     * Downloading Payment List from database.
     * @return paymentList with all db payments or {@code null} when there is a problem with database
     */
    public List<Payment> downloadPayments() {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_PAYMENT)){

            List<Payment> paymentList = new ArrayList<>();
            while(result.next()) {
                Payment payment = new Payment();
                payment.setId(result.getInt(COLUMN_PAYMENT_ID));
                payment.setName(result.getString(COLUMN_PAYMENT_NAME));
                payment.setCurrency(result.getString(COLUMN_PAYMENT_CURRENCY));

                paymentList.add(payment);
            }

            return paymentList;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public Payment downloadPayment(int paymentId) {
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_PAYMENT +
                    " WHERE " + COLUMN_PAYMENT_ID + " = " + paymentId)){

            Payment payment = new Payment();

            while(result.next()) {
                payment.setId(result.getInt(COLUMN_PAYMENT_ID));
                payment.setName(result.getString(COLUMN_PAYMENT_NAME));
                payment.setCurrency(result.getString(COLUMN_PAYMENT_CURRENCY));

            }

            return payment;
        }
        catch(SQLException e) {
            System.out.println(SELECT_DB_ERROR + e.getMessage());
            return null;
        }
    }
}
