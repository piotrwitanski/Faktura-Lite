package com.company.invoice.ui.datamodel;

import com.company.invoice.dto.*;
import com.company.invoice.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UIData {

    private static UIData instance = new UIData();

    private ObservableList<InvoiceModel> invoiceModels;
    private ObservableList<ContractorModel> contractorModels;
    private ObservableList<ServiceModel> serviceModels;
    private ObservableList<PaymentModel> paymentModels;
    private InvoiceUtils invoiceUtils;
    private CustomerUtils customerUtils;
    private ItemUtils itemUtils;
    private PaymentUtils paymentUtils;
    private ProductUtils productUtils;

    private UIData() {
        invoiceUtils = new InvoiceUtils();
        customerUtils = new CustomerUtils();
        itemUtils = new ItemUtils();
        paymentUtils = new PaymentUtils();
        productUtils = new ProductUtils();
        invoiceModels = FXCollections.observableArrayList();
        contractorModels = FXCollections.observableArrayList();
        serviceModels = FXCollections.observableArrayList();
        paymentModels = FXCollections.observableArrayList();
    }

    public static UIData getInstance() {
        return instance;
    }

    public ObservableList<InvoiceModel> getInvoiceModels() {
        return invoiceModels;
    }

    public ObservableList<ContractorModel> getContractorModels() {
        return contractorModels;
    }

    public ObservableList<ServiceModel> getServiceModels() {
        return serviceModels;
    }

    public ObservableList<PaymentModel> getPaymentModels() {
        return paymentModels;
    }

    public void addInvoiceModel() {

    }

    /**
     * Method download and prepare Invoice data to display it in UI
     */
    public void loadInvoiceTable() {
        List<Invoice> invoiceList = invoiceUtils.downloadInvoices();

        for (Invoice invoice : invoiceList) {
            InvoiceModel invoiceModel = new InvoiceModel();
            Customer customer = customerUtils.downloadCustomer(invoice.getCustomerId());
            List<Item> itemsList = itemUtils.downloadItems(invoice.getId());
            Payment payment = paymentUtils.downloadPayment(invoice.getPaymentId());

            invoiceModel.setInvoiceType(invoice.getInvoiceType());
            invoiceModel.setInvoiceNumber(invoice.getInvoiceNumber());
            invoiceModel.setIssueDate(invoice.getIssueDate());
            invoiceModel.setCustomerName(customer.getName());
            invoiceModel.setNetValue(Double.toString(getNettoValue(itemsList)));
            invoiceModel.setGrossValue(Double.toString(getBruttoValue(itemsList)));
            invoiceModel.setVatValue(Double.toString(getBruttoValue(itemsList) - getNettoValue(itemsList)));
            invoiceModel.setCurrency(payment.getCurrency());

            invoiceModels.add(invoiceModel);
        }
    }

    public void loadContractorTable() {
        List<Customer> customerList = customerUtils.downloadCustomers();
        for(Customer customer : customerList) {
            ContractorModel contractorModel = new ContractorModel();

            contractorModel.setId(Integer.toString(customer.getId()));
            contractorModel.setName(customer.getName());
            contractorModel.setCity(customer.getCity());
            contractorModel.setStreet(customer.getStreet());
            contractorModel.setHouseNumber(Integer.toString(customer.getHouseNumber()));
            contractorModel.setApartmentNumber(Integer.toString(customer.getApartmentNumber()));
            contractorModel.setPostCode(customer.getPostCode());
            contractorModel.setNIP(customer.getNIP());

            contractorModels.add(contractorModel);
        }
    }

    public void loadServiceTable() {
        List<Product> productList = productUtils.downloadProducts();
        for (Product product : productList) {
            ServiceModel serviceModel = new ServiceModel();

            serviceModel.setType(product.getType());
            serviceModel.setName(product.getName());
            serviceModel.setVat(Integer.toString(product.getVat()));
            serviceModel.setNetPrice(Double.toString(product.getNetPrice()));
            serviceModel.setGrossPrice(Double.toString(product.getGrossPrice()));
            serviceModel.setUnitOfMeasure(product.getUnitOfMeasure());

            serviceModels.add(serviceModel);
        }
    }

    public void loadPaymentList() {
        List<Payment> paymentList = paymentUtils.downloadPayments();
        for (Payment payment : paymentList) {
            PaymentModel paymentModel = new PaymentModel();

            paymentModel.setId(Integer.toString(payment.getId()));
            paymentModel.setName(payment.getName());
            paymentModel.setCurrency(payment.getCurrency());

            paymentModels.add(paymentModel);
        }

    }

    public void saveInvoice(Invoice newInvoice) {
        invoiceUtils.addInvoiceToDB(newInvoice);
    }

    public void saveItem(Item newItem) {
        itemUtils.addItemToDB(newItem);
    }

    private double getNettoValue(List<Item> itemsList) {
        double totalNettoValue = 0;
        for (Item item : itemsList) {
            totalNettoValue += item.getQuantity() * item.getNetPrice();
        }
        return totalNettoValue;
    }

    private double getBruttoValue(List<Item> itemsList) {
        double totalBruttoValue = 0;
        for (Item item : itemsList) {
            totalBruttoValue += item.getQuantity() * item.getGrossPrice();
        }
        return totalBruttoValue;
    }

    public String getInvoiceMaxNumber() {
        return invoiceUtils.downloadInvoiceMaxNumber();
    }

    public int getInvoiceLastId() {
        return invoiceUtils.downloadInvoiceLastId();
    }

}
