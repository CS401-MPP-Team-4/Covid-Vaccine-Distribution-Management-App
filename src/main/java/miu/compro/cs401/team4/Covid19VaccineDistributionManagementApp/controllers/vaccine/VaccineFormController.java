package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccineService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDFormController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;

public class VaccineFormController extends AbstractCRUDFormController<Vaccine> {

	static final RepositoryService<Vaccine> repositoryService = new VaccineService();
	static final RepositoryService<Supplier> supplierService = new SupplierService();
	
	@FXML
	@Bind("name")
	TextField txtName;
	
	
	@FXML
	@Bind("amount")
	TextField txtAmount;
	
	@FXML
	@Bind("manufacturer")
	ChoiceBox<Supplier> ccbSupplier;

	@Override
	public void init(URL location, ResourceBundle resources) {
		runTask(()-> {
			List<Supplier> suppliers = supplierService.getAll();
			ccbSupplier.getItems().addAll(suppliers);
		});
	}

	@Override
	public Vaccine createModel() {
		return new Vaccine();
	}

	@Override
	public RepositoryService<Vaccine> getRepositoryService() {
		return repositoryService;
	}

	@Override
	public String getTitle() {
		return "Vaccine";
	}
	
}
