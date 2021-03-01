package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("vaccine");
    }
}
