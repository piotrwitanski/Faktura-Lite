package com.company.invoice.utils;

import com.company.invoice.dto.Item;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.*;

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

    public void removeItems(int invoiceId) {
        try {
            dataBaseUtils.removeItems(invoiceId);
        }
        catch(Exception e) {
            System.out.println(REMOVE_FROM_DB_ERROR + e.getMessage());
        }
    }

    public void removeItem(int itemId) {
        try {
            dataBaseUtils.removeItem(itemId);
        }
        catch(Exception e) {
            System.out.println(REMOVE_FROM_DB_ERROR + e.getMessage());
        }
    }

    public void updateItem(Item item) {
        try {
            dataBaseUtils.updateItem(item);
        }
        catch(Exception e) {
            System.out.println(UPDATE_DB_ERROR + e.getMessage());
        }
    }
}
