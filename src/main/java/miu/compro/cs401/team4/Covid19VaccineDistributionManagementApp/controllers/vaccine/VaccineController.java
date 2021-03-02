package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.Bind;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

import java.io.IOException;

public class VaccineController extends AbstractCRUDListController<Vaccine> {
	@FXML
	@Bind(field = "name")
	TableColumn<Vaccine, String> tcName;

	@FXML
	@Bind(field = "manufacturer")
	TableColumn<Vaccine, String> tcManufacturer;

	@FXML
	@Bind(field = "amount")
	TableColumn<Vaccine, Integer> tcAmount;

	@FXML
	@Bind(field = "name")
	Label lblName;

	@FXML
	@Bind(field = "manufacturer")
	Label lblManufacturer;

	@FXML
	@Bind(field = "amount")
	Label lblAmount;

	@Override
	public void init(URL location, ResourceBundle resources) {
		System.out.println("VaccineController");
	}

	@Override
	public List<Vaccine> fetchData() {
		return Arrays.asList(
				new Vaccine(1, "Moderna", new Supplier(1, "Moderna", "Fair Field, Iowa", "261-458-5231"), 200),
				new Vaccine(2, "Pfizer", new Supplier(1, "Pfizer", "Fair Field, Iowa", "261-123-4567"), 100));
	}

	@FXML
	public void create() {
		this.showEditDialog(null);
	}

	public void delete() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure to delete?");
		System.out.println(alert.showAndWait().get());

	}

	@FXML
	public void showEditDialog(Vaccine vaccine) {
		try {
			Parent page = App.loadFXML("views/vaccine/vaccine-form");

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(App.primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
//	        PersonEditDialogController controller = loader.getController();
//	        controller.setDialogStage(dialogStage);
//	        controller.setPerson(person);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

//	        return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
//	        return false;
		}
	}

}
