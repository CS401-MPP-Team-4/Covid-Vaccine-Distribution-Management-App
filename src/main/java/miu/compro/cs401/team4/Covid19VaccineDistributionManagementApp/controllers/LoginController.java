package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;

import java.io.IOException;

public class LoginController {
    @FXML
    public void goToHome() throws IOException {
        App.setRoot("master");
    }

    @FXML
    public Button login;

    @FXML
    public TextField username;

    @FXML
    public TextField password;

    @FXML
    public ChoiceBox roleChoiceBox;

    String[] roles = {"Admin", "Staff", "Recipient"};


    public void login(ActionEvent event) throws IOException {
        authorize();
    }


    private void authorize() {
        roleChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {

            if (observableValue.getValue().equals("Admin")) {
                //TODO change scene
            } else if (observableValue.getValue().equals("Staff")) {

            } else if (observableValue.getValue().equals("Candidate")) {

            }

        });


    }
}
