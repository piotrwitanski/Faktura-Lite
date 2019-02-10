package com.company.invoice.general;

import com.company.invoice.dto.*;
import com.company.invoice.tools.PDFCreator;
import com.company.invoice.tools.UserProperties;
import com.company.invoice.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import java.io.File;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/company/invoice/ui/main.fxml"));
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("Invoice Lite");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

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
        customer.setName("Peter Newone");
        customer.setCity("Warsaw");
        customer.setPostCode("94-39");
        customer.setStreet("Something");
        customer.setNIP("335-35435-23");
        customer.setHouseNumber(24);
        customer.setApartmentNumber(10);
        customer.setBankAccount("10-1000-2222-3333-4444-4444");


        CustomerUtils customerUtils = new CustomerUtils();
//        customerUtils.addCustomerToDB(customer);

        //add User to DB
        User user = new User();
        user.setName("Alan Now");
        user.setCity("Here");
        user.setPostCode("445-000");
        user.setStreet("Something");
        user.setNIP("334-243-234-343");
        user.setHouseNumber(12);
        user.setApartmentNumber(23);
        user.setBankAccount("10-2323-3333-4444-5555-4444");


        UserUtils userUtils = new UserUtils();
//        userUtils.addUserToDB(user);

        //add Product to DB
        Product product = new Product();
        product.setName("DRUKARKA");
        product.setVat(23);
        product.setGrossPrice(1000);
        product.setUnitOfMeasure("szt.");
        product.setType("Towar");
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
        invoice.setPaymentId(2);
        invoice.setInvoiceNumber("01/01/2019");
        invoice.setInvoiceType("Faktura");

        InvoiceUtils invoiceUtils = new InvoiceUtils();
//        invoiceUtils.addInvoiceToDB(invoice);

        //download max invoice number from DB
        System.out.println("Max number: " + invoiceUtils.downloadInvoiceMaxNumber());
        System.out.println("Max invoice id: " + invoiceUtils.downloadInvoiceLastId());

        //add item to DB
        Item item = new Item();
        item.setInvoiceId(2);
        item.setName("SKANER");
        item.setQuantity(1);
        item.setVat(23);
        item.setGrossPrice(2000);
        item.setUnitOfMeasure("szt.");
        item.setType("TOWAR");

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
        launch(args);
    }

}
