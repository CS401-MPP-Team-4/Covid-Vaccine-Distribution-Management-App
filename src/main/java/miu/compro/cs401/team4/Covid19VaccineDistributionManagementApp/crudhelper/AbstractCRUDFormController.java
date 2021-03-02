package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public abstract class AbstractCRUDFormController<T> implements Initializable {
	@FXML
	TableView<T> crudTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AbstractCRUDFormController");


		init(location, resources);
	}

	public abstract void init(URL location, ResourceBundle resources);

}
