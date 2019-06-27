package com.company.invoice.tools;

import java.io.*;
import java.util.Properties;

import static com.company.invoice.dictionaries.Dictionary.*;

public class UserProperties {


    private String userName;
    private String city;
    private String postCode;
    private String street;
    private String phoneNumber;
    private String NIP;

    public UserProperties() {

    }

    public void saveProperties() {
        try {
            File filePath = new File(PROPERTIES_PATH);
            OutputStream out = new FileOutputStream(filePath);

            Properties properties = new Properties();
            properties.setProperty("User name", userName);
            properties.setProperty("City", city);
            properties.setProperty("Postcode", postCode);
            properties.setProperty("Street", street);
            properties.setProperty("Phone number", phoneNumber);
            properties.setProperty("NIP", NIP);

            properties.store(out, "User properties");
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loadProperties() {
        try {
            InputStream inS = new FileInputStream(PROPERTIES_PATH);
            Properties properties = new Properties();
            properties.load(inS);

            this.userName = properties.getProperty("User name");
            this.city = properties.getProperty("City");
            this.postCode = properties.getProperty("Postcode");
            this.street = properties.getProperty("Street");
            this.phoneNumber = properties.getProperty("Phone number");
            this.NIP = properties.getProperty("NIP");

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setProperties(String userName, String city, String postCode, String street, String phoneNumber, String NIP) {
        this.userName = userName;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }
}
