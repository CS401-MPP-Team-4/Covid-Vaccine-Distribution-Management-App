package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Model;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Utils;

public abstract class AbstractCRUDFormController<T extends Model> extends AbstractCRUDController<T> implements Initializable {
	@FXML
	TableView<T> crudTable;

	FormMode mode = FormMode.ADD;
	
	protected T currentModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AbstractCRUDFormController");
		init(location, resources);
	}
	
	public void setWindow(Stage window) {
		String title = (mode == mode.ADD? "Create ": "Edit ") + getTitle();
		window.setTitle(title);
	}
	
	public void fillData(T model) {
		Utils.setBoundedData(model, this, "");
	}
	
	public void onCurrentModelChanged(T newModel) {
		mode = newModel == null? mode.ADD: mode.EDIT;
		fillData(newModel);
		toggleButtons();
	}

	protected void toggleButtons() {
		if (mode == mode.EDIT) {
//			if (btnEdit != null)
//				btnEdit.setDisable(false);
//			if (btnDelete != null)
//				btnDelete.setDisable(false);
		} else {
//			if (btnEdit != null)
//				btnEdit.setDisable(true);
//			if (btnDelete != null)
//				btnDelete.setDisable(true);
		}
	}
	
	public void setCurrentModel(T model) {
		currentModel = model;
		onCurrentModelChanged(model);
	}

	public abstract void init(URL location, ResourceBundle resources);

}
