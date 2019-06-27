package com.company.test;

import com.company.invoice.dto.Customer;
import com.company.invoice.utils.CustomerUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerUtilsTest {

    private CustomerUtils customerUtils;

    @org.junit.Before
    public void setup() {
        customerUtils = new CustomerUtils();
    }

    @org.junit.Test
    public void addCustomerToDB() {
    }

    @org.junit.Test
    public void downloadCustomers() {
        List<Customer> customers = customerUtils.downloadCustomers();

        assertEquals("Peter New", customers.get(0).getName());
        assertEquals("Adam New", customers.get(1).getName());
        assertEquals("Adam New", customers.get(2).getName());
        assertEquals("Peter Newone", customers.get(3).getName());
    }

    @org.junit.Test
    public void downloadCustomer() {
        Customer customer = customerUtils.downloadCustomer(1);

        assertEquals("Peter New", customer.getName());
        assertEquals("Warsaw", customer.getCity());
    }
}