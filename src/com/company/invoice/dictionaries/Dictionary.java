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

}
