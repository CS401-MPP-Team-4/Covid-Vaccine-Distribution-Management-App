package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.supplier;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
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
	
	
}
