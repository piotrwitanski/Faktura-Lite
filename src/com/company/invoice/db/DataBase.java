package com.company.invoice.db;

import com.company.invoice.dto.Customer;
import com.company.invoice.dto.Product;
import com.company.invoice.dto.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.company.invoice.dictionaries.Dictionary.*;

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
        catch(SQLException e)
        {
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
        catch (SQLException e)
        {
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
                    COLUMN_CUSTOMER_NIP +
                    ")" +
                    "VALUES(NULL" + ", '" + customer.getName()  +
                            "', '" + customer.getCity() + "', '"  + customer.getStreet() + "', '"  +
                            customer.getPostCode() + "', " + customer.getNIP() + ")");
        }
        catch (SQLException e)
        {
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
                    user.getPostCode() + "', " + user.getNIP() + ")");
        }
        catch (SQLException e)
        {
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
                    COLUMN_PRODUCT_NAME +
                    ")" +
                    "VALUES(NULL" +  ", '" + product.getName()  + "')");
        }
        catch (SQLException e)
        {
            System.out.println("Add statement ERROR: " + e.getMessage());
        }
    }
}
