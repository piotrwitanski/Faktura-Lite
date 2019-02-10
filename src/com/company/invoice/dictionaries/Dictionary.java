package com.company.invoice.dictionaries;

public class Dictionary {

    public static final String DB_NAME = "database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:db\\" + DB_NAME;

    public static final String PROPERTIES_PATH = "properties.txt";

    public static final String TABLE_CUSTOMER = "customer";
    public static final String COLUMN_CUSTOMER_ID = "id";
    public static final String COLUMN_CUSTOMER_NAME = "name";
    public static final String COLUMN_CUSTOMER_CITY = "city";
    public static final String COLUMN_CUSTOMER_STREET = "street";
    public static final String COLUMN_CUSTOMER_POST_CODE = "post_code";
    public static final String COLUMN_CUSTOMER_NIP = "NIP";
    public static final String COLUMN_CUSTOMER_HOUSE_NUMBER = "house_number";
    public static final String COLUMN_CUSTOMER_APARTMENT_NUMBER = "apartment_number";
    public static final String COLUMN_CUSTOMER_BANK_ACCOUNT = "bank_account";

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_CITY = "city";
    public static final String COLUMN_USER_STREET = "street";
    public static final String COLUMN_USER_POST_CODE = "post_code";
    public static final String COLUMN_USER_NIP = "NIP";
    public static final String COLUMN_USER_HOUSE_NUMBER = "house_number";
    public static final String COLUMN_USER_APARTMENT_NUMBER = "apartment_number";
    public static final String COLUMN_USER_BANK_ACCOUNT = "bank_account";

    public static final String TABLE_PRODUCT = "product";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_PRICE_GROSS = "price_gross";
    public static final String COLUMN_PRODUCT_PRICE_NET = "price_net";
    public static final String COLUMN_PRODUCT_VAT = "vat";
    public static final String COLUMN_PRODUCT_UNIT_OF_MEASURE = "unit_of_measure";
    public static final String COLUMN_PRODUCT_TYPE = "type";

    public static final String TABLE_INVOICE = "invoice";
    public static final String COLUMN_INVOICE_ID = "id";
    public static final String COLUMN_INVOICE_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_INVOICE_USER_ID = "user_id";
    public static final String COLUMN_INVOICE_INVOICE_DATE = "invoice_date";
    public static final String COLUMN_INVOICE_ISSUE_DATE = "issue_date";
    public static final String COLUMN_INVOICE_PAYMENT_ID = "payment_id";
    public static final String COLUMN_INVOICE_NUMBER = "invoice_number";
    public static final String COLUMN_INVOICE_TYPE = "invoice_type";

    public static final String TABLE_ITEM = "item";
    public static final String COLUMN_ITEM_ID = "id";
    public static final String COLUMN_ITEM_INVOICE_ID = "invoice_id";
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_QUANTITY = "quantity";
    public static final String COLUMN_ITEM_PRICE_GROSS = "price_gross";
    public static final String COLUMN_ITEM_PRICE_NET = "price_net";
    public static final String COLUMN_ITEM_VAT = "vat";
    public static final String COLUMN_ITEM_UNIT_OF_MEASURE = "unit_of_measure";
    public static final String COLUMN_ITEM_TYPE = "type";

    public static final String TABLE_PAYMENT = "payment";
    public static final String COLUMN_PAYMENT_ID = "id";
    public static final String COLUMN_PAYMENT_NAME = "name";
    public static final String COLUMN_PAYMENT_CURRENCY = "currency";

    //Invoice
    public static final String INVOICE = "Faktura";
    public static final String NUMBER = "L.p.";
    public static final String NAME = "Nazwa";
    public static final String QUANTITY = "Ilość";
    public static final String UNIT_OF_MEASURE = "j.m.";
    public static final String PRICE_NET = "Cena netto";
    public static final String VALUE_NET = "Wartość netto";
    public static final String VAT = "VAT [%]";
    public static final String VAT_PRICE = "Wartość VAT";
    public static final String PRICE_GROSS = "Wartość brutto";

    public static final String PERCENTAGE = "%";
    public static final String NET = "NETTO";
    public static final String GROSS = "BRUTTO";
    public static final String VAT_VALUE = "VAT";
    public static final String SELLER = "Sprzedawca";
    public static final String BUYER = "Nabywca";
    public static final String DOTS = "...............................................................................";

}
