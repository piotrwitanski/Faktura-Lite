package com.company.invoice.ui.datamodel;

import com.company.invoice.dto.*;
import com.company.invoice.utils.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import static com.company.invoice.dictionaries.Dictionary.USER_ID;

public class UIData {

    private static UIData instance = new UIData();

    private ObservableList<InvoiceModel> invoiceModels;
    private ObservableList<ContractorModel> contractorModels;
    private ObservableList<ServiceModel> serviceModels;
    private ObservableList<PaymentModel> paymentModels;
    private ObservableList<UserModel> userModels;
    private InvoiceUtils invoiceUtils;
    private CustomerUtils customerUtils;
    private ItemUtils itemUtils;
    private PaymentUtils paymentUtils;
    private ProductUtils productUtils;
    private UserUtils userUtils;

    private UIData() {
        invoiceUtils = new InvoiceUtils();
        customerUtils = new CustomerUtils();
        itemUtils = new ItemUtils();
        paymentUtils = new PaymentUtils();
        productUtils = new ProductUtils();
        userUtils = new UserUtils();
        invoiceModels = FXCollections.observableArrayList();
        contractorModels = FXCollections.observableArrayList();
        serviceModels = FXCollections.observableArrayList();
        paymentModels = FXCollections.observableArrayList();
        userModels = FXCollections.observableArrayList();
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

    public ObservableList<UserModel> getUserModels() {
        return userModels;
    }

    public void addInvoiceModel(Invoice invoice) {
        InvoiceModel invoiceModel = new InvoiceModel();
        Customer customer = customerUtils.downloadCustomer(invoice.getCustomerId());
        List<Item> itemsList = itemUtils.downloadItems(invoice.getId());
        Payment payment = paymentUtils.downloadPayment(invoice.getPaymentId());

        invoiceModel.setInvoiceId(Integer.toString(invoice.getId()));
        invoiceModel.setInvoiceType(invoice.getInvoiceType());
        invoiceModel.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceModel.setIssueDate(invoice.getIssueDate());
        invoiceModel.setPaymentId(Integer.toString(invoice.getPaymentId()));
        invoiceModel.setInvoiceDate(invoice.getInvoiceDate());
        invoiceModel.setCustomerName(customer.getName());
        invoiceModel.setNetValue(Double.toString(getNettoValue(itemsList)));
        invoiceModel.setGrossValue(Double.toString(getBruttoValue(itemsList)));
        invoiceModel.setVatValue(Double.toString(getBruttoValue(itemsList) - getNettoValue(itemsList)));
        invoiceModel.setCurrency(payment.getCurrency());
        invoiceModels.add(invoiceModel);

    }

    public void deleteInvoice(InvoiceModel invoiceModel) {
        invoiceModels.remove(invoiceModel);
        itemUtils.removeItem(Integer.parseInt(invoiceModel.getInvoiceId()));
        invoiceUtils.removeInovoice(Integer.parseInt(invoiceModel.getInvoiceId()));
    }

    public Invoice loadNewInvoice() {
        return invoiceUtils.downloadInvoice(getInvoiceLastId());
    }

    /**
     * Method download and prepare Invoice data to display it in UI
     */
    public void loadInvoiceTable() {
        List<Invoice> invoiceList = invoiceUtils.downloadInvoices();
        invoiceModels.removeAll(invoiceModels);
        for(Invoice invoice : invoiceList) {
            InvoiceModel invoiceModel = new InvoiceModel();
            Customer customer = customerUtils.downloadCustomer(invoice.getCustomerId());
            List<Item> itemsList = itemUtils.downloadItems(invoice.getId());
            Payment payment = paymentUtils.downloadPayment(invoice.getPaymentId());

            invoiceModel.setInvoiceId(Integer.toString(invoice.getId()));
            invoiceModel.setInvoiceType(invoice.getInvoiceType());
            invoiceModel.setInvoiceNumber(invoice.getInvoiceNumber());
            invoiceModel.setIssueDate(invoice.getIssueDate());
            invoiceModel.setPaymentId(Integer.toString(invoice.getPaymentId()));
            invoiceModel.setInvoiceDate(invoice.getInvoiceDate());
            invoiceModel.setCustomerName(customer.getName());
            invoiceModel.setNetValue(Double.toString(getNettoValue(itemsList)));
            invoiceModel.setGrossValue(Double.toString(getBruttoValue(itemsList)));
            invoiceModel.setVatValue(Double.toString(getBruttoValue(itemsList) - getNettoValue(itemsList)));
            invoiceModel.setCurrency(payment.getCurrency());

            invoiceModels.add(invoiceModel);
        }
    }

    public void addContractorModel(Customer customer) {
        ContractorModel contractorModel = new ContractorModel();

        contractorModel.setId(Integer.toString(customer.getId()));
        contractorModel.setName(customer.getName());
        contractorModel.setStreet(customer.getStreet());
        contractorModel.setHouseNumber(Integer.toString(customer.getHouseNumber()));
        contractorModel.setApartmentNumber(Integer.toString(customer.getApartmentNumber()));
        contractorModel.setPostCode(customer.getPostCode());
        contractorModel.setCity(customer.getCity());
        contractorModel.setNIP(customer.getNIP());
        contractorModel.setBankAccount(customer.getBankAccount());

        contractorModels.add(contractorModel);

    }

    public Customer loadNewCustomer() {
        return customerUtils.downloadCustomer(getCustomerLastId());
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
        for(Product product : productList) {
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
        for(Payment payment : paymentList) {
            PaymentModel paymentModel = new PaymentModel();

            paymentModel.setId(Integer.toString(payment.getId()));
            paymentModel.setName(payment.getName());
            paymentModel.setCurrency(payment.getCurrency());

            paymentModels.add(paymentModel);
        }

    }

    public void loadBankAccountNumber() {
        User user = userUtils.downloadUser(USER_ID);
        //*TODO just for now we assume that we will have only one user (id 1). Not sure if in future need to add more users
        UserModel userModel = new UserModel();

        userModel.setId(Integer.toString(user.getId()));
        userModel.setName(user.getName());
        userModel.setBankAccount(user.getBankAccount());

        userModels.add(userModel);
    }

    public void saveInvoice(Invoice newInvoice) {
        invoiceUtils.addInvoiceToDB(newInvoice);
    }

    public void saveItem(Item newItem) {
        itemUtils.addItemToDB(newItem);
    }

    public void saveCustomer(Customer newCustomer) {
        customerUtils.addCustomerToDB(newCustomer);
    }

    public List<Item> downloadItems(int invoiceId) {
        return itemUtils.downloadItems(invoiceId);
    }

    public Payment downloadPayment(int paymentId) {
        return paymentUtils.downloadPayment(paymentId);
    }

    private double getNettoValue(List<Item> itemsList) {
        double totalNettoValue = 0;
        for(Item item : itemsList) {
            totalNettoValue += item.getQuantity() * item.getNetPrice();
        }
        return totalNettoValue;
    }

    private double getBruttoValue(List<Item> itemsList) {
        double totalBruttoValue = 0;
        for(Item item : itemsList) {
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

    public int getCustomerLastId() {
        return customerUtils.downloadCustomerLastId();
    }

}
