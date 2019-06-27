package com.company.test;

import com.company.invoice.dto.Item;
import com.company.invoice.utils.DataBaseUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemUtilsTest {
    private DataBaseUtils dataBaseUtils;

    @org.junit.Before
    public void setup() {
        dataBaseUtils = new DataBaseUtils();
    }
    @Test
    public void addItemToDB() {
    }

    @Test
    public void downloadItems() {
        List<Item> itemsList = dataBaseUtils.downloadItems(5);
        List<Item> expectedItemsList = Arrays.asList(
            new Item(8, 5, "KOMPUTER1", 1, 500.0, 385.0, 23, "szt.","TOWAR")
        );
        assertThat(itemsList, is(expectedItemsList));
    }

}