package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
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
	private void jumptToSupplier() throws IOException {
		loadChildView(Navigations.SUPPLIER);
	}
	

	@FXML
	private void jumptToVaccine() throws IOException {
		loadChildView(Navigations.VACCINE);
	}
	
	@FXML
	private void jumptToVaccinationSite() throws IOException {
		loadChildView(Navigations.VACCINATION_SITE);
	}

	@FXML
	public void jumptToRecipient(ActionEvent event) throws IOException {
		loadChildView(Navigations.RECIPIENT);
	}
	
	@FXML
	private void exitApp() throws IOException {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			loadChildView(Navigations.VACCINE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
