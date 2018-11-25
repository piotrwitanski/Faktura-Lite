package com.company.invoice.utils;

import com.company.invoice.dto.Product;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;
import static com.company.invoice.dictionaries.Errors.DOWNLOAD_DB_ERROR;

public class ProductUtils {
    private DataBaseUtils dataBaseUtils;

    public ProductUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addProductToDB(Product product) {
        try {
            dataBaseUtils.addProductToDB(product);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }

    public List<Product> downloadProducts() {
        try {
            List<Product> products = dataBaseUtils.downloadProducts();
            return products;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }
}
