package com.company.invoice.general;

import com.company.invoice.dto.*;
import com.company.invoice.tools.PDFCreator;
import com.company.invoice.tools.UserProperties;
import com.company.invoice.utils.*;
import org.joda.time.DateTime;

import java.io.File;
import java.util.*;

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
        customer.setName("Adam New");
        customer.setCity("Warsaw");
        customer.setPostCode("94-39");
        customer.setStreet("Something");
        customer.setNIP("335-35435-23");


        CustomerUtils customerUtils = new CustomerUtils();
//        customerUtils.addCustomerToDB(customer);

        //add User to DB
        User user = new User();
        user.setName("Adam Smith");
        user.setCity("Warsaw");
        user.setPostCode("445-829");
        user.setStreet("Something");
        user.setNIP("334-243-234-343");


        UserUtils userUtils = new UserUtils();
//        userUtils.addUserToDB(user);

        //add Product to DB
        Product product = new Product();
        product.setName("KOMPUTER");
        product.setVat(23);
        product.setPriceBrutto(1000);
        product.setUnitOfMeasure("szt.");
        ProductUtils productUtils = new ProductUtils();
//        productUtils.addProductToDB(product);



        //download Customers from DB
        List<Customer> customers = customerUtils.downloadCustomers();
        for(Customer customerDB : customers) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println(customerDB);
            System.out.println("--------------------------------------------------------------------");

        }

        //download Users from DB
        List<User> users = userUtils.downloadUsers();
        for(User userDB : users) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println(userDB);
            System.out.println("--------------------------------------------------------------------");
        }
        //get today date
        //*TODO date should have separate class?
        Date today = Calendar.getInstance().getTime();
        DateTime dtToday = new DateTime(today);
        DateTime dtInvoice = dtToday.plusDays(14);
        String todayDate = dtToday.toString("dd-MM-yyyy");
        String invoiceDate = dtInvoice.toString("dd-MM-yyyy");

        List<String> list = Arrays.asList(todayDate.split("-"));

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println(todayDate);
        System.out.println(invoiceDate);
        //add Invoice to DB
        Invoice invoice = new Invoice();
        invoice.setCustomerId(customers.get(0).getId());
        invoice.setUserId(users.get(0).getId());
        invoice.setIssueDate(todayDate);
        invoice.setInvoiceDate(invoiceDate);

        InvoiceUtils invoiceUtils = new InvoiceUtils();
//        invoiceUtils.addInvoiceToDB(invoice);

        //add item to DB
        Item item = new Item();
        item.setInvoiceId(2);
        item.setName("KOMPUTER");
        item.setQuantity(6);
        item.setVat(23);
        item.setPriceBrutto(5000);
        item.setUnitOfMeasure("szt.");

        ItemUtils itemUtils = new ItemUtils();
//        itemUtils.addItemToDB(item);

        //download Products from DB
        List<Product> products = productUtils.downloadProducts();
        for(Product productDB : products) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println(productDB);
            System.out.println("--------------------------------------------------------------------");
        }

        //download Invoices from DB
        List<Invoice> invoices = invoiceUtils.downloadInvoices();
        for(Invoice invoiceDB : invoices) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println(invoiceDB);
            System.out.println("--------------------------------------------------------------------");
        }

        //download Items form DB
        List<Item> items = itemUtils.downloadItems(1);
        for(Item itemDB : items) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println(itemDB);
            System.out.println("--------------------------------------------------------------------");
        }

        List<Item> items1 = itemUtils.downloadItems(2);
        for(Item itemDB : items1) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println(itemDB);
            System.out.println("--------------------------------------------------------------------");
        }

        //add Payment to DB
        Payment payment = new Payment();
        payment.setName("PRZELEW");
        payment.setCurrency("PLN");

        PaymentUtils paymentUtils = new PaymentUtils();
//        paymentUtils.addPaymentToDB(payment);
        

        //create pdf
        PDFCreator pdfCreator = new PDFCreator();
        invoice = invoiceUtils.downloadInvoice(2);
        customer = customerUtils.downloadCustomer(invoice.getCustomerId());
        user = userUtils.downloadUser(invoice.getUserId());
        items = itemUtils.downloadItems(invoice.getId());
        payment = paymentUtils.downloadPayment(invoice.getPaymentId());
        pdfCreator.createPdf("Invoice.pdf", customer, invoice, items, user, payment);

    }

}
