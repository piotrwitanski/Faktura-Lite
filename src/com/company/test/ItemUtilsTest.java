package com.company.test;

import com.company.invoice.dto.Item;
import com.company.invoice.utils.DataBaseUtils;
import com.company.invoice.utils.ItemUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ItemUtilsTest {
    private ItemUtils itemUtils;

    @org.junit.Before
    public void setup() {
        itemUtils = new ItemUtils();
    }

    @Test
    public void addItemToDB() {
    }

    @Test
    public void downloadItems() {
        List<Item> itemsList = itemUtils.downloadItems(5);
        List<Item> expectedItemsList = Arrays.asList(
                new Item(8, 5, "KOMPUTER1", 1, 500.0, 385.0, 23, "szt.", "TOWAR")
        );
        assertThat(itemsList, is(expectedItemsList));
    }

}