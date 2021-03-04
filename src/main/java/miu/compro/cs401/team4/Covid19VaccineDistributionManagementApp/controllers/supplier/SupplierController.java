package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.supplier;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;


public class SupplierController extends AbstractCRUDListController<Supplier> {

	static final RepositoryService<Supplier> repositoryService = new SupplierService();
	
	@FXML
	@Bind("name")
	TableColumn<Supplier, String> tcName;

	@FXML
	@Bind("address")
	TableColumn<Supplier, String> tcAddress;

	@FXML
	@Bind("phoneNumber")
	TableColumn<Supplier, Integer> tcPhoneNumber;

	@FXML
	@Bind("name")
	Label lblName;

	@FXML
	@Bind("address")
	Label lblAddress;

	@FXML
	@Bind("phoneNumber")
	Label lblPhoneNumber;

	
	
	@Override
	public void init(URL location, ResourceBundle resources) {

	}
	
	@Override
	public String getTitle() {
		return "Supplier";
	}
	
	@Override
	public RepositoryService<Supplier> getRepositoryService() {
		return repositoryService;
	}

	@Override
	public String getFormUrl() {
		return Navigations.SUPPLIER_FORM.getValue();
	}

//	@Override
//	public List<Supplier> fetchData() {
//		return Arrays.asList(new Supplier(1, "Moderna", "Fair Field, Iowa", "261-458-5231"),
//				new Supplier(1, "Pfizer", "Fair Field, Iowa", "261-123-4567"));
//	}

	

	
}
