package com.company.invoice.utils;

import com.company.invoice.dto.Product;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;

public class ProductUtils {
    private DataBaseUtils dataBaseUtils;

    public ProductUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addProductToDB(Product product) {
        try{
            dataBaseUtils.addProductToDB(product);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }
}
