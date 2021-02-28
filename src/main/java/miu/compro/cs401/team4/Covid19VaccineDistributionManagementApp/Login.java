package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

import javafx.fxml.FXML;

import java.io.IOException;

public class Login {
    @FXML
    private void goToHome() throws IOException {
        App.setRoot("primary");
    }
}
