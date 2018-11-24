package com.company.invoice.utils;

import com.company.invoice.dto.User;

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
            System.out.println(DATABASE_ERROR);
        }
    }
}
