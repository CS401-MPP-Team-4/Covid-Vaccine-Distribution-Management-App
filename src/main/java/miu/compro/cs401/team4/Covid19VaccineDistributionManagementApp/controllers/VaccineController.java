package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.views.crudHelper.AbstractCRUDController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.views.crudHelper.Bind;

public class VaccineController extends AbstractCRUDController<Vaccine> {
	@FXML
	@Bind(field = "name")
	TableColumn<Vaccine, String> col_name;
	
	@FXML
	@Bind(field = "manufacturer")
	TableColumn<Vaccine, String> col_manufacturer;
	
	@FXML
	@Bind(field = "amount")
	TableColumn<Vaccine, Integer> col_amount;
	
	@FXML
	@Bind(field = "name")
	TextField txt_name;
	
	@FXML
	@Bind(field = "manufacturer")
	TextField txt_manufacturer;

	@FXML
	@Bind(field = "amount")
	TextField txt_amount;

	@Override
	public void init(URL location, ResourceBundle resources) {
		System.out.println("VaccineController");
	}

	@Override
	public List<Vaccine> fetchData() {
		return Arrays.asList(new Vaccine(1, "Moderna", "Moderna", 200), new Vaccine(2, "Pfizer", "Pfizer", 100));
	}

}
