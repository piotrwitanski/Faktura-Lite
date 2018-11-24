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
        try{
            dataBaseUtils.addUserToDB(user);
        }
        catch(Exception e) {
            System.out.println(DATABASE_ERROR + e.getMessage());
        }
    }

    public List<User> downloadUsersFromDB() {
        try {
            List<User> users = dataBaseUtils.downloadUsersFromDB();
            return users;
        }
        catch(Exception e) {
            System.out.println(DOWNLOAD_DB_ERROR + e.getMessage());
            return null;
        }
    }
}
