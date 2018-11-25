package com.company.invoice.utils;

import com.company.invoice.dto.Item;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;

public class ItemUtils {
    private DataBaseUtils dataBaseUtils;

    public ItemUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addItemToDB(Item item) {
        try {
            dataBaseUtils.addItemToDB(item);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }
}
