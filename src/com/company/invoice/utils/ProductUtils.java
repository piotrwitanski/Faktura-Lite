package com.company.invoice.utils;

import com.company.invoice.dto.Product;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.*;

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

    public Product downloadProduct(int productId) {
        try {
            Product product = dataBaseUtils.downloadProduct(productId);
            return product;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public void updateProduct(Product product) {
        try {
            dataBaseUtils.updateProduct(product);
        }
        catch(Exception e) {
            System.out.println(UPDATE_DB_ERROR + e.getMessage());

        }
    }

    public void removeProduct(int productId) {
        try {
            dataBaseUtils.removeProduct(productId);
        }
        catch(Exception e) {
            System.out.println(REMOVE_FROM_DB_ERROR + e.getMessage());
        }
    }

    public int downloadProductLastId() {
        try {
            int productId = dataBaseUtils.downloadProductLastId();
            return productId;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return -1;
        }
    }
}
