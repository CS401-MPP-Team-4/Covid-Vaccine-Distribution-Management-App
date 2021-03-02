package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void login(ActionEvent event) throws  IOException{
        authorize();
    }

    private void authorize() {


    }
}
