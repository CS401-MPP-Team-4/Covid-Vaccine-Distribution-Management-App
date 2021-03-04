package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccinationsite;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccinationSiteService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;


public class VaccinationSiteController extends AbstractCRUDListController<VaccinationSite> {

	static final RepositoryService<VaccinationSite> repositoryService = new VaccinationSiteService();
	
	@FXML
	@Bind("name")
	TableColumn<Supplier, String> tcName;

	@FXML
	@Bind("city")
	TableColumn<Supplier, String> tcCity;
	
	@FXML
	@Bind("state")
	TableColumn<Supplier, String> tcState;

	@FXML
	@Bind("zipCode")
	TableColumn<Supplier, Integer> tcZipCode;

	@FXML
	@Bind("name")
	Label lblName;

	@FXML
	@Bind("city")
	Label lblCity;
	
	@FXML
	@Bind("state")
	Label lblState;
	
	@FXML
	@Bind("zipCode")
	Label lblZipCode;
	
	@Override
	public void init(URL location, ResourceBundle resources) {

	}
	
	@Override
	public String getTitle() {
		return "Vaccination Site";
	}
	
	@Override
	public RepositoryService<VaccinationSite> getRepositoryService() {
		return repositoryService;
	}

	@Override
	public String getFormUrl() {
		return Navigations.VACCINATION_SITE_FORM.getValue();
	}
	
}
