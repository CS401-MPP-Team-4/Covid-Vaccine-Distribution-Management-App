package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;

public class MasterController implements Initializable {
	@FXML
	VBox mainContainer;
	
	
	private void loadChildView(Navigations view) throws IOException {
		var child = App.loadFXML(view.getValue());
		mainContainer.getChildren().setAll(child);
	}
	
	@FXML
	private void jumptToVaccine() throws IOException {
		loadChildView(Navigations.VACCINE);
	}
	
	@FXML
	private void exitApp() throws IOException {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			loadChildView(Navigations.SUPPLIER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
