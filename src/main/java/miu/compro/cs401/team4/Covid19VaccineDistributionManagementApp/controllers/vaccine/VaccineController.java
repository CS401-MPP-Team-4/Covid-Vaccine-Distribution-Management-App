package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccineService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDFormController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.DialogWindow;


public class VaccineController extends AbstractCRUDListController<Vaccine> {
	
	static final RepositoryService<Vaccine> repositoryService = new VaccineService();
	
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
		return repositoryService;
	}
	
	@Override
	public String getTitle() {
		return "Vaccine";
	}
	
	@Override
	public String getFormUrl() {
		return Navigations.VACCINE_FORM.getValue();
	}
	
	@FXML
	public void dispatch() {
		System.out.println(this.getFormUrl());
		DialogWindow<?> window = DialogWindow.createDialog("views/vaccine/vaccine-dispatch", App.primaryStage);
		VaccineDispatchController form = (VaccineDispatchController)window.getController();
		form.setVaccineId(currentId);
		window.getDialogStage().setTitle("Dispatch");
		window.showDialog();
		refreshData();
	}
}
