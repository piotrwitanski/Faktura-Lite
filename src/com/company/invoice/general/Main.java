package com.company.invoice.general;

import com.company.invoice.dto.Customer;
import com.company.invoice.dto.Product;
import com.company.invoice.dto.User;
import com.company.invoice.tools.UserProperties;
import com.company.invoice.utils.CustomerUtils;
import com.company.invoice.utils.ProductUtils;
import com.company.invoice.utils.UserUtils;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        UserProperties userProperties = new UserProperties();
        //only do this when file is empty or not exists!!!
        File file = new File("properties.txt");
        if(!file.exists() || file.length() == 0) {
            userProperties.setProperties("Cris Smith", "Warsaw", "445-829", "Something", "123456789", "654321");
            userProperties.saveProperties();
            System.out.println("Saving data to file...");
        }

        userProperties.loadProperties();

        //add Customer to DB
        Customer customer = new Customer();
        customer.setName("Peter New");
        customer.setCity("Warsaw");
        customer.setPostCode("94-39");
        customer.setStreet("Something");
        customer.setNIP(123456);

        System.out.println("Customer:\n" + customer);

        CustomerUtils customerUtils = new CustomerUtils();
//        customerUtils.addCustomerToDB(customer);

        //add User to DB
        User user = new User();
        user.setName("Cris Smith");
        user.setCity("Warsaw");
        user.setPostCode("445-829");
        user.setStreet("Something");
        user.setNIP(654321);

        System.out.println("User:\n" + user);

        UserUtils userUtils = new UserUtils();
//        userUtils.addUserToDB(user);

        //add Product to DB
        Product product = new Product();
        product.setName("USŁUGA");
        ProductUtils productUtils = new ProductUtils();
//        productUtils.addProductToDB(product);

        product.setName("DOJAZD");
//        productUtils.addProductToDB(product);

    }

}
