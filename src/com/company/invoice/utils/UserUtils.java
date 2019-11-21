package com.company.invoice.utils;

import com.company.invoice.dto.User;

import java.util.List;

import static com.company.invoice.dictionaries.Errors.*;

public class UserUtils {

    private DataBaseUtils dataBaseUtils;

    public UserUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void addUserToDB(User user) {
        try {
            dataBaseUtils.addUserToDB(user);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }

    public List<User> downloadUsers() {
        try {
            List<User> users = dataBaseUtils.downloadUsers();
            return users;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public User downloadUser(int userId) {
        try {
            User user = dataBaseUtils.downloadUser(userId);
            return user;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }

    public void updateUser(User user) {
        try {
            dataBaseUtils.updateUser(user);
        }
        catch(Exception e) {
            System.out.println(UPDATE_DB_ERROR + e.getMessage());
        }
    }
}
