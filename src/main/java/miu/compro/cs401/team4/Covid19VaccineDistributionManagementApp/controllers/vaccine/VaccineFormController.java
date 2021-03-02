package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDFormController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

public class VaccineFormController extends AbstractCRUDFormController<Vaccine> {

	@FXML
	ChoiceBox<Supplier> ccbSupplier;
	
	@Override
	public void init(URL location, ResourceBundle resources) {
		System.out.println("VaccineFormController");
		ccbSupplier.getItems().addAll(new Supplier(1, "Moderna", "Fair Field, Iowa", "261-458-5231"),
				new Supplier(2, "Pfizer", "Fair Field, Iowa", "261-123-4567"),
				new Supplier(3, "Pfizer", "Fair Field, Iowa", "261-123-4567"),
				new Supplier(4, "Pfizer", "Fair Field, Iowa", "261-123-4567"));

		 
	}
}
