package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.views;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
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
	TableColumn<Vaccine, String> col_amount;

	@Override
	public void init(URL location, ResourceBundle resources) {
		System.out.println("VaccineController");
//		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
//		col_manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
//		col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
	}

	@Override
	public List<Vaccine> fetchData() {
		return Arrays.asList(new Vaccine(1, "Moderna", "Moderna", 200), new Vaccine(2, "Pfizer", "Pfizer", 100));
	}

}
