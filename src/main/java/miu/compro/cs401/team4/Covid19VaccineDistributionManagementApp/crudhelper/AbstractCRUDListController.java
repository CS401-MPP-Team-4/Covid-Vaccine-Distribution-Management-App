package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Model;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.DialogWindow;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Utils;

/**
 * abstract class of the all controllers which are implementing list of CRUD
 * 
 * @param T {@code extends Model} generic type of target Model class. Example: Supplier 
 * */
public abstract class AbstractCRUDListController<T extends Model> extends AbstractCRUDController<T>
		implements Initializable {
	
	/**
	 * Returns reference of main TableView. tx:id of TableView must be equal "crudTable" in the FXML file
	 * */
	@FXML
	TableView<T> crudTable;

	/**
	 * Reference of main Create button. tx:id of Button must be equal "btnCreate" in the FXML file
	 * */
	@FXML
	Button btnCreate;

	/**
	 * Reference of main Delete button. tx:id of Button must be equal "btnDelete" in the FXML file
	 * */
	@FXML
	Button btnDelete;

	/**
	 * Reference of main Edit button. tx:id of Button must be equal "btnEdit" in the FXML file
	 * */
	@FXML
	Button btnEdit;

	/**
	 * id of currently selected model
	 * */
	protected Integer currentId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindColums();
		refreshData();
		bindEvents();
		App.setUppAppSubTitle(getTitle());
		init(location, resources);
	}
	
	/**
	 * When called the controller initialized.
	 * Inherited controllers must implement that method and can place some initialization staffs such as "fill comboboxes".
	 * */
	public abstract void init(URL location, ResourceBundle resources);

	/**
	 * Inherited controllers must implement that method and should return URL of form view. See {@code Supplier}
	 * */
	public abstract String getFormUrl();

	/**
	 * Refreshes table data from the database
	 * */
	public void refreshData() {
		runTask(() -> {
			List<T> data = getRepositoryService().getAll();
			ObservableList<T> list = FXCollections.observableList(data);
			crudTable.setItems(list);
			if (list.size() > 0) {
				setCurrentId(list.get(0).getId());
			} else {
				setCurrentId(null);
			}
//			throw new Exception("Error Test");
		});
	}

	private void bindColums() {
		Class<?> cls = this.getClass();

		Stream.of(cls.getDeclaredFields()).filter(f -> f.getType().isAssignableFrom(TableColumn.class)).forEach(f -> {
			if (f.isAnnotationPresent(Bind.class)) {
				Bind annotation = (Bind) f.getAnnotation(Bind.class);
				try {
					f.setAccessible(true);
					TableColumn<?, ?> col = (TableColumn<?, ?>) f.get(this);
					col.setCellValueFactory(new PropertyValueFactory<>(annotation.value()));
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
	/**
	 * Fetches currenty selected model's detail information then fills detail section with it.
	 * */
	public void getModelDetails(Integer id) {
		runTask(() -> {
			if(id != null) {				
				T model = getRepositoryService().getById(id);
				fillDetails(model);
			}
			else {				
				fillDetails(null);
			}
		});
	}

	protected void fillDetails(T model) {
		Utils.setBoundedData(model, this, "...");
	}

	private void bindEvents() {
		getCrudTable().getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if(newValue != null)
						setCurrentId(newValue.getId());	
				});
	}

	public void onCurrentIdChanged(Integer id) {
		getModelDetails(id);
		toggleButtons();
	}

	private void setCurrentId(Integer id) {
		currentId = id;
		onCurrentIdChanged(id);
	}

	/**
	 * Shows form view with Add mode.
	 * Button "Create" calls that method. See the button's onAction attribute.
	 * */
	@FXML
	public void create() {
		System.out.println(this.getFormUrl());
		DialogWindow<AbstractCRUDFormController<T>> window = DialogWindow.createDialog(this.getFormUrl(), App.primaryStage);
		AbstractCRUDFormController<T> form = window.getController();
		form.setWindow(window);
		form.setCurrentId(null);
		window.showDialog();
		refreshData();
	}

	/**
	 * Shows form view with Edit mode.
	 * Button "Edit" calls that method. See the button's onAction attribute.
	 * */
	@FXML
	public void edit() {
		DialogWindow<AbstractCRUDFormController<T>> window = DialogWindow.createDialog(this.getFormUrl(), App.primaryStage);
		AbstractCRUDFormController<T> form = window.getController();
		form.setWindow(window);
		form.setCurrentId(currentId);
		window.showDialog();
		refreshData();
	}

	/**
	 * Deletes currently selected model from the database.
	 * Button "Delete" calls that method. See the button's onAction attribute.
	 * */
	@FXML
	public void delete() {
		if (App.showConfirm("Are you sure to delete?").get() == ButtonType.OK) {
			runTask(() -> {
				getRepositoryService().delete(currentId);
			}, true);

			refreshData();
		}
	}

	protected void toggleButtons() {
		if (currentId != null) {
			if (btnEdit != null)
				btnEdit.setDisable(false);
			if (btnDelete != null)
				btnDelete.setDisable(false);
		} else {
			if (btnEdit != null)
				btnEdit.setDisable(true);
			if (btnDelete != null)
				btnDelete.setDisable(true);
		}
	}

	/**
	 * Returns reference of main TableView. Can override when tx:id of TableView not equals to "crudTable"
	 * */
	public TableView<T> getCrudTable() {
		return crudTable;
	}

}
