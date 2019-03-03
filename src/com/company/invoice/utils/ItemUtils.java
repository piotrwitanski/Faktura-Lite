package com.company.invoice.utils;

import com.company.invoice.dto.Item;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.DATABASE_ERROR;
import static com.company.invoice.dictionaries.Errors.DOWNLOAD_DB_ERROR;
import static com.company.invoice.dictionaries.Errors.REMOVE_FROM_DB_ERROR;

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

    public List<Item> downloadItems(int invoiceId) {
        try {
            List<Item> items = dataBaseUtils.downloadItems(invoiceId);
            return items;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public void removeItem(int invoiceId) {
        try {
            dataBaseUtils.removeItem(invoiceId);
        }
        catch(Exception e) {
            System.out.println(REMOVE_FROM_DB_ERROR + e.getMessage());
        }
    }
}
