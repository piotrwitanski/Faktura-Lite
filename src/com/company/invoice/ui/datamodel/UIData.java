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

    /**
     * Method adding Invoice object to the list
     *
     * @param invoice represent object that will be added to the list.
     */
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
        invoiceModel.setNetValue(Double.toString(getNetValue(itemsList)));
        invoiceModel.setGrossValue(Double.toString(getGrossValue(itemsList)));
        invoiceModel.setVatValue(Double.toString(getGrossValue(itemsList) - getNetValue(itemsList)));
        invoiceModel.setCurrency(payment.getCurrency());
        invoiceModels.add(invoiceModel);

    }

    /**
     * Method removing Invoice object from database and from the models list
     *
     * @param invoiceModel represent object from the list that must be removed
     */
    public void deleteInvoice(InvoiceModel invoiceModel) {
        invoiceModels.remove(invoiceModel);
        itemUtils.removeItems(Integer.parseInt(invoiceModel.getInvoiceId()));
        invoiceUtils.removeInovoice(Integer.parseInt(invoiceModel.getInvoiceId()));
    }

    public Invoice loadNewInvoice() {
        return invoiceUtils.downloadInvoice(getInvoiceLastId());
    }

    public Invoice downloadInvoice(int invoiceId) {
        return invoiceUtils.downloadInvoice(invoiceId);
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
            invoiceModel.setNetValue(Double.toString(getNetValue(itemsList)));
            invoiceModel.setGrossValue(Double.toString(getGrossValue(itemsList)));
            invoiceModel.setVatValue(Double.toString(getGrossValue(itemsList) - getNetValue(itemsList)));
            invoiceModel.setCurrency(payment.getCurrency());

            invoiceModels.add(invoiceModel);
        }
    }

    public void updateInvoice(Invoice invoice) {
        invoiceUtils.updateInvoice(invoice);
    }

    public void updateInvoiceModel(Invoice invoice) {
        for(InvoiceModel invoiceModel : invoiceModels) {
            if(Integer.parseInt(invoiceModel.getInvoiceId()) == invoice.getId()) {
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
                invoiceModel.setNetValue(Double.toString(getNetValue(itemsList)));
                invoiceModel.setGrossValue(Double.toString(getGrossValue(itemsList)));
                invoiceModel.setVatValue(Double.toString(getGrossValue(itemsList) - getNetValue(itemsList)));
                invoiceModel.setCurrency(payment.getCurrency());
            }
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

    public void updateCustomer(Customer customer) {
        customerUtils.updateCustomer(customer);
    }

    public void updateContractorModel(Customer customer) {
        for(ContractorModel contractorModel : contractorModels) {
            if(Integer.parseInt(contractorModel.getId()) == customer.getId()) {
                contractorModel.setName(customer.getName());
                contractorModel.setStreet(customer.getStreet());
                contractorModel.setHouseNumber(Integer.toString(customer.getHouseNumber()));
                contractorModel.setApartmentNumber(Integer.toString(customer.getApartmentNumber()));
                contractorModel.setPostCode(customer.getPostCode());
                contractorModel.setCity(customer.getCity());
                contractorModel.setNIP(customer.getNIP());
                contractorModel.setBankAccount(customer.getBankAccount());
            }
        }
    }

    public void deleteContractor(ContractorModel contractorModel) {
        contractorModels.remove(contractorModel);
        customerUtils.removeCustomer(Integer.parseInt(contractorModel.getId()));
    }

    public Customer loadNewCustomer() {
        return customerUtils.downloadCustomer(getCustomerLastId());
    }

    public Customer downloadCustomer(int customerId) {
        return customerUtils.downloadCustomer(customerId);
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
            contractorModel.setBankAccount(customer.getBankAccount());

            contractorModels.add(contractorModel);
        }
    }

    public Product loadNewProduct() {
        return productUtils.downloadProduct(getProductLastId());
    }

    public void updateProduct(Product product) {
        productUtils.updateProduct(product);
    }

    public void updateServiceModel(Product product) {
        for(ServiceModel serviceModel : serviceModels) {
            if(Integer.parseInt(serviceModel.getId()) == product.getId()) {
                serviceModel.setType(product.getType());
                serviceModel.setName(product.getName());
                serviceModel.setVat(Integer.toString(product.getVat()));
                serviceModel.setGrossPrice(Double.toString(product.getGrossPrice()));
                serviceModel.setNetPrice(Double.toString(product.getNetPrice()));
                serviceModel.setUnitOfMeasure(product.getUnitOfMeasure());
            }
        }
    }

    public void removeService(ServiceModel serviceModel) {
        serviceModels.remove(serviceModel);
        productUtils.removeProduct(Integer.parseInt(serviceModel.getId()));
    }

    public void loadServiceTable() {
        List<Product> productList = productUtils.downloadProducts();
        for(Product product : productList) {
            ServiceModel serviceModel = new ServiceModel();

            serviceModel.setId(Integer.toString(product.getId()));
            serviceModel.setType(product.getType());
            serviceModel.setName(product.getName());
            serviceModel.setVat(Integer.toString(product.getVat()));
            serviceModel.setNetPrice(Double.toString(product.getNetPrice()));
            serviceModel.setGrossPrice(Double.toString(product.getGrossPrice()));
            serviceModel.setUnitOfMeasure(product.getUnitOfMeasure());

            serviceModels.add(serviceModel);
        }
    }

    public void addServiceModel(Product product) {
        ServiceModel serviceModel = new ServiceModel();

        serviceModel.setId(Integer.toString(product.getId()));
        serviceModel.setType(product.getType());
        serviceModel.setName(product.getName());
        serviceModel.setVat(Integer.toString(product.getVat()));
        serviceModel.setNetPrice(Double.toString(product.getNetPrice()));
        serviceModel.setGrossPrice(Double.toString(product.getGrossPrice()));
        serviceModel.setUnitOfMeasure(product.getUnitOfMeasure());

        serviceModels.add(serviceModel);
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
        UserModel userModel = new UserModel();

        userModel.setId(Integer.toString(user.getId()));
        userModel.setName(user.getName());
        userModel.setBankAccount(user.getBankAccount());

        userModels.add(userModel);
    }

    public User downloadUser(int userId) {
        return userUtils.downloadUser(userId);
    }

    public UserModel downloadUserModel(int userId) {
        UserModel userModel = new UserModel();
        User user = downloadUser(userId);

        userModel.setName(user.getName());
        userModel.setCity(user.getCity());
        userModel.setStreet(user.getStreet());
        userModel.setHouseNumber(Integer.toString(user.getHouseNumber()));
        userModel.setApartmentNumber(Integer.toString(user.getApartmentNumber()));
        userModel.setPostCode(user.getPostCode());
        userModel.setNIP(user.getNIP());
        userModel.setBankAccount(user.getBankAccount());

        return userModel;
    }

    public void updateUser(User user) {
        userUtils.updateUser(user);
    }

    public void removeAllItems(int invoiceId) {
        itemUtils.removeItems(invoiceId);
    }

    public void removeItem(int itemId) {
        itemUtils.removeItem(itemId);
    }

    public void updateItem(Item item) {
        itemUtils.updateItem(item);
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

    public void saveProduct(Product newProduct) {
        productUtils.addProductToDB(newProduct);
    }

    public List<Item> downloadItems(int invoiceId) {
        return itemUtils.downloadItems(invoiceId);
    }

    public Payment downloadPayment(int paymentId) {
        return paymentUtils.downloadPayment(paymentId);
    }

    private double getNetValue(List<Item> itemsList) {
        double totalNetValue = 0;
        for(Item item : itemsList) {
            totalNetValue += item.getQuantity() * item.getNetPrice();
        }
        return totalNetValue;
    }

    private double getGrossValue(List<Item> itemsList) {
        double totalGrossValue = 0;
        for(Item item : itemsList) {
            totalGrossValue += item.getQuantity() * item.getGrossPrice();
        }
        return totalGrossValue;
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

    public int getProductLastId() {
        return productUtils.downloadProductLastId();
    }

}
