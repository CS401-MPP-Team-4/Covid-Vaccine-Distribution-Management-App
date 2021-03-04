package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccinationSiteService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccineService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

public class VaccineDispatchController implements Initializable {
	static final VaccineService vaccineService = new VaccineService();
	static final VaccinationSiteService vaccinationSiteService = new VaccinationSiteService();

	@FXML
	ChoiceBox<VaccinationSite> ccbSite;

	@FXML
	TextField txtAmount;

	Integer vaccineId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<VaccinationSite> sites;
		try {
			sites = vaccinationSiteService.getAll();
			ccbSite.getItems().addAll(sites);
		} catch (Exception e) {
			e.printStackTrace();
			App.showError(e.getMessage());
		}
	}

	public Integer getVaccineId() {
		return vaccineId;
	}

	public void setVaccineId(Integer vaccineId) {
		this.vaccineId = vaccineId;
	}

	@FXML
	public void dispatch() {
		try {
			boolean res = vaccineService.dispatch(vaccineId, ccbSite.getValue().getId(), Integer.parseInt(txtAmount.getText()));
			if(res)
				App.showSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			App.showError(e.getMessage());
		}
	}

}
