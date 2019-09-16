package com.company.invoice.ui;

import com.company.invoice.dto.Customer;
import com.company.invoice.ui.datamodel.ContractorModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContractorDialogController {

    @FXML
    private TextField contractorSymbolTextField, contractorNameTextField, contractorStreetTextField, contractorHouseNumberTextField,
                    contractorApartmentNumberTextField, contractorPostCodeTextField, contractorCityTextField, contractorNIPTextField,
                    contractorREGONTextField, contractorBankAccountTextField;


    public Customer getNewCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setName(contractorNameTextField.getText());
        newCustomer.setStreet(contractorStreetTextField.getText());
        newCustomer.setHouseNumber(Integer.parseInt(contractorHouseNumberTextField.getText()));
        newCustomer.setApartmentNumber(Integer.parseInt(contractorApartmentNumberTextField.getText()));
        newCustomer.setPostCode(contractorPostCodeTextField.getText());
        newCustomer.setCity(contractorCityTextField.getText());
        newCustomer.setNIP(contractorNIPTextField.getText());
        newCustomer.setBankAccount(contractorBankAccountTextField.getText());

        return newCustomer;
    }

    public void editContractor(ContractorModel contractorModel) {
        contractorNameTextField.setText(contractorModel.getName());
        contractorStreetTextField.setText(contractorModel.getStreet());
        contractorHouseNumberTextField.setText(contractorModel.getHouseNumber());
        contractorApartmentNumberTextField.setText(contractorModel.getApartmentNumber());
        contractorPostCodeTextField.setText(contractorModel.getPostCode());
        contractorCityTextField.setText(contractorModel.getCity());
        contractorNIPTextField.setText(contractorModel.getNIP());
        contractorBankAccountTextField.setText(contractorModel.getBankAccount());
    }

    public void updateContractor(ContractorModel contractorModel) {

    }
}
