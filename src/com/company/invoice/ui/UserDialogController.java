package com.company.invoice.ui;

import com.company.invoice.dto.User;
import com.company.invoice.ui.datamodel.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UserDialogController {

    @FXML
    private TextField userNameTextField, userCityTextField, userStreetTextField, userHouseNumberTextField,
            userApartmentNumberTextField, userPostCodeTextField, userNIPTextField, userBankAccountTextField;

    public void editUser(UserModel userModel) {
        if(userModel != null) {
            userNameTextField.setText(userModel.getName());
            userCityTextField.setText(userModel.getCity());
            userStreetTextField.setText(userModel.getStreet());
            userHouseNumberTextField.setText(userModel.getHouseNumber());
            userApartmentNumberTextField.setText(userModel.getApartmentNumber());
            userPostCodeTextField.setText(userModel.getPostCode());
            userNIPTextField.setText(userModel.getNIP());
            userBankAccountTextField.setText(userModel.getBankAccount());
        }
    }

    public User updateUser(int id) {
        User user = new User();
        user.setId(id);
        user.setName(userNameTextField.getText());
        user.setCity(userCityTextField.getText());
        user.setStreet(userStreetTextField.getText());
        user.setHouseNumber(Integer.parseInt(userHouseNumberTextField.getText()));
        user.setApartmentNumber(Integer.parseInt(userApartmentNumberTextField.getText()));
        user.setPostCode(userPostCodeTextField.getText());
        user.setNIP(userNIPTextField.getText());
        user.setBankAccount(userBankAccountTextField.getText());

        return user;
    }
}
