package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import javafx.fxml.FXML;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;

import java.io.IOException;

public class LoginController {
    @FXML
    private void goToHome() throws IOException {
        App.setRoot("master");
    }
}
