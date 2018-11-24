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

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_CITY = "city";
    public static final String COLUMN_USER_STREET = "street";
    public static final String COLUMN_USER_POST_CODE = "post_code";
    public static final String COLUMN_USER_NIP = "NIP";

    public static final String TABLE_PRODUCT = "service";
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";

    public static final String TABLE_INVOICE = "invoice";
    public static final String COLUMN_INVOICE_ID = "id";
    public static final String COLUMN_INVOICE_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_INVOICE_USER_ID = "user_id";
    public static final String COLUMN_INVOICE_INVOICE_DATE = "invoice_date";
    public static final String COLUMN_INVOICE_ISSUE_DATE = "issue_date";

    public static final String TABLE_ITEM = "item";
    public static final String COLUMN_ITEM_ID = "id";
    public static final String COLUMN_ITEM_INVOICE_ID = "invoice_id";
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_QUANTITY = "quantity";
    public static final String COLUMN_ITEM_PRICE = "price";
    public static final String COLUMN_ITEM_VAT = "vat";

}
