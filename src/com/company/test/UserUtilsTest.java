package com.company.test;

import com.company.invoice.dto.User;
import com.company.invoice.utils.DataBaseUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserUtilsTest {

    private DataBaseUtils dataBaseUtils;

    @org.junit.Before
    public void setup() {
        dataBaseUtils = new DataBaseUtils();
    }

    @Test
    public void downloadUsers() {
        List<User> userList = dataBaseUtils.downloadUsers();
        List<User> expectedUserList = Arrays.asList(
                new User(1, "Cris Smith", "Warsaw", "Something", "445-829", "654321", 10, 18, "10-2323-3333-4444-5555-4444"),
                new User(2, "Adam Smith", "Warsaw", "Something", "445-829", "334-243-234-343", 12, 10, "10-2323-3333-2222-3333"),
                new User(3, "Alan Now", "Here", "Something", "445-000", "334-243-234-343", 12, 23, "10-2323-3333-4444-5555-4444"),
                new User(4, "Alan Now", "Here", "Something", "445-000", "334-243-234-343", 12, 23, "10-2323-3333-4444-5555-4444"),
                new User(5, "Alan Now", "Here", "Something", "445-000", "334-243-234-343", 12, 23, "10-2323-3333-4444-5555-4444")
        );
        assertThat(userList, is(expectedUserList));
    }

    @Test
    public void downloadUser() {
        User user = dataBaseUtils.downloadUser(1);

//        User expectedUser = new User(1, "Cris Smith", "Warsaw", "Something", "445-829", "654321", 10, 18, "10-2323-3333-4444-5555-4444");
//        User expectedUser1 = new User(1, "Cris Smith", "Warsaw", "Something", "445-829", "654321", 10, 18, "10-2323-3333-4444-5555-4444");

        assertEquals(1, user.getId());
        assertEquals("Cris Smith", user.getName());
        assertEquals("654321", user.getNIP());
    }
}