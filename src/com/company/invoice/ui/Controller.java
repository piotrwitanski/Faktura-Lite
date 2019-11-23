package com.company.invoice.ui;

import com.company.invoice.dto.Customer;
import com.company.invoice.dto.User;
import com.company.invoice.ui.datamodel.InvoiceModel;
import com.company.invoice.ui.datamodel.UIData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

import static com.company.invoice.dictionaries.Dictionary.USER_ID;
import static com.company.invoice.dictionaries.Errors.DIALOG_LOAD_ERROR;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public void showEditUserDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edytowanie użytkownika");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("userDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException e) {
            System.out.println(DIALOG_LOAD_ERROR);
            e.getStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        UserDialogController userDialogController = fxmlLoader.getController();
        userDialogController.editUser(UIData.getInstance().downloadUserModel(USER_ID));

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            User user = userDialogController.updateUser(USER_ID);
            UIData.getInstance().updateUser(user);
        }
    }

    @FXML
    public void showAboutProgramDialog() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("Autor: Piotr Witański");
        alert.setContentText("FakturaLite jest uproszczonym programem przeznaczonym głównie dla osób prowadzących jednoosobową działalność gospodarczą. \n" +
                "Program posiada podstawowe funkcje umożliwiające dodanie, edytowanie i usunięcie faktur, wraz z możliwością wydrukowania faktury do PDF'a. \n" +
                "Program umożliwia również stworzenie podstawowej bazy klientów oraz usług i towarów.");
        alert.showAndWait();

    }

    @FXML
    public void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoc");
        alert.setHeaderText(null);
        alert.setContentText("Jeśli znalazłeś błąd w oprogramowaniu lub chcesz zgłosić poprawki, prosimy o wysłanie informacji na adres:\nprzykładowy.adres_email@jv.com");
        alert.showAndWait();
    }
}
