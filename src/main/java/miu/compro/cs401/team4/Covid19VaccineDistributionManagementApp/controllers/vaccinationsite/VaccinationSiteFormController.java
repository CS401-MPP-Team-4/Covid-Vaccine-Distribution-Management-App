package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccinationsite;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccinationSiteService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDFormController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;

public class VaccinationSiteFormController extends AbstractCRUDFormController<VaccinationSite> {

	static final RepositoryService<VaccinationSite> repositoryService = new VaccinationSiteService();
	
	@FXML
	@Bind("name")
	TextField txtName;
	
	@FXML
	@Bind("city")
	TextField txtCity;
	
	@FXML
	@Bind("state")
	TextField txtState;
	
	@FXML
	@Bind("zipCode")
	TextField txtZipCode;
	
	@Override
	public void init(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	@Override
	public RepositoryService<VaccinationSite> getRepositoryService() {
		return repositoryService;
	}

	@Override
	public String getTitle() {
		return "Vaccination Site";
	}

	@Override
	public VaccinationSite createModel() {
		return new VaccinationSite();
	}

}
