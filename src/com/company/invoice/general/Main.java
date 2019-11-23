package com.company.invoice.general;

import com.company.invoice.dto.*;
import com.company.invoice.tools.PDFCreator;
import com.company.invoice.tools.UserProperties;
import com.company.invoice.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joda.time.DateTime;

import java.io.File;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/company/invoice/ui/main.fxml"));
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setTitle("FakturaLite");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
