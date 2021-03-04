package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;


public class VaccineController extends AbstractCRUDListController<Vaccine> {
	@FXML
	@Bind(value = "name")
	TableColumn<Vaccine, String> tcName;

	@FXML
	@Bind(value = "manufacturer")
	TableColumn<Vaccine, String> tcManufacturer;

	@FXML
	@Bind(value = "amount")
	TableColumn<Vaccine, Integer> tcAmount;

	@FXML
	@Bind(value = "name")
	Label lblName;

	@FXML
	@Bind(value = "manufacturer")
	Label lblManufacturer;

	@FXML
	@Bind(value = "amount")
	Label lblAmount;

	@Override
	public void init(URL location, ResourceBundle resources) {
		System.out.println("VaccineController");
	}

	@Override
	public RepositoryService<Vaccine> getRepositoryService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Vaccine> fetchData() {
		return Arrays.asList(
				new Vaccine(1, "Moderna", new Supplier(1, "Moderna", "Fair Field, Iowa", "261-458-5231"), 200),
				new Vaccine(2, "Pfizer", new Supplier(1, "Pfizer", "Fair Field, Iowa", "261-123-4567"), 100));
	}

	@Override
	public String getTitle() {
		return "Vaccine";
	}
	
	@Override
	public String getFormUrl() {
		return Navigations.VACCINE_FORM.getValue();
	}
}
