package com.company.test;

import com.company.invoice.dto.Product;
import com.company.invoice.utils.InvoiceUtils;
import com.company.invoice.utils.ProductUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProductUtilsTest {

    private ProductUtils productUtils;

    @org.junit.Before
    public void setup() {
        productUtils = new ProductUtils();
    }
    @Test
    public void downloadProducts() {
        List<Product> products = productUtils.downloadProducts();
        List<Product> expectedProductsList = Arrays.asList(
                new Product(1, "USŁUGA", 100.0, 77.0, 23, "szt.", "Towar"),
                new Product(2, "DOJAZD", 100.0, 77.0, 23, "szt.", "Towar"),
                new Product(3, "USŁUGA1", 1.0, 1.0, 23, "szt.", "Towar"),
                new Product(4, "USŁUGA2", 100.0, 77.0, 23, "szt.", "Towar"),
                new Product(5, "KOMPUTER", 1000.0, 770.0, 23, "szt.", "Towar"),
                new Product(6, "KOMPUTER3", 1000.0, 770.0, 23, "szt.", "Towar"),
                new Product(7, "DRUKARKA", 1000.0, 813.0, 23, "szt.", "Towar")
        );

        assertThat(products, is(expectedProductsList));
    }
}