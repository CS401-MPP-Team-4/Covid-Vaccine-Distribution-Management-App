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

public abstract class AbstractCRUDListController<T extends Model> extends AbstractCRUDController<T>
		implements Initializable {
	@FXML
	TableView<T> crudTable;

	@FXML
	Button btnCreate;

	@FXML
	Button btnDelete;

	@FXML
	Button btnEdit;

	protected Integer currentId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindColums();
		refreshData();
		bindEvents();
		init(location, resources);
	}

	public abstract void init(URL location, ResourceBundle resources);

	public abstract String getFormUrl();

	public List<T> fetchData() throws SQLException {
		return getRepositoryService().getAll();
	}

	public void refreshData() {
		runTask(() -> {
			ObservableList<T> list = FXCollections.observableList(fetchData());
			crudTable.setItems(list);
			if (list.size() > 0) {
				setCurrentId(list.get(0).getId());
			} else {
				setCurrentId(null);
			}
//			throw new Exception("Error Test");
		});
	}

	public T getById(Integer id) {
		return null;
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
	
	public void getModelDetails(Integer id) {
		runTask(() -> {
			T model = getRepositoryService().getById(id);
			fillDetails(model);
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

	@FXML
	public void edit() {
		DialogWindow<AbstractCRUDFormController<T>> window = DialogWindow.createDialog(this.getFormUrl(), App.primaryStage);
		AbstractCRUDFormController<T> form = window.getController();
		form.setWindow(window);
		form.setCurrentId(currentId);
		window.showDialog();
		refreshData();
	}

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

	public TableView<T> getCrudTable() {
		return crudTable;
	}

}
