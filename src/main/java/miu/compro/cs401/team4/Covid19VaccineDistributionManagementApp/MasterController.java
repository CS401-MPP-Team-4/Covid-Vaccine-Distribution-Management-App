package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MasterController {
	@FXML
	VBox mainContainer;
	
	@FXML
	private void jumptToVaccine() throws IOException {
		var abc = App.loadFXML("views/vaccine");
		mainContainer.getChildren().add(abc);
	}
	
}
