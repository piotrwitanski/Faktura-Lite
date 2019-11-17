package com.company.invoice.ui;

import com.company.invoice.ui.datamodel.InvoiceModel;
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

public class Controller {

    @FXML
    public void showAboutProgramDialog(){

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
