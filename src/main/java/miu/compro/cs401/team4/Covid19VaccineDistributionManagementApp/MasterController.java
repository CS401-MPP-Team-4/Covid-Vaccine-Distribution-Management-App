package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MasterController {
	@FXML
	AnchorPane mainContainer;
	
	
	private void loadChildView(ChildView view) throws IOException {
		var child = App.loadFXML(view.getValue());
		mainContainer.getChildren().setAll(child);
	}
	
	@FXML
	private void jumptToVaccine() throws IOException {
		loadChildView(ChildView.VACCINE);
	}
	
	@FXML
	private void exitApp() throws IOException {
		Platform.exit();
	}
	
}
