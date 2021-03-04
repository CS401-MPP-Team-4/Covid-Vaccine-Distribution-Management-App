package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Model;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.DialogWindow;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Utils;

public abstract class AbstractCRUDFormController<T extends Model> extends AbstractCRUDController<T>
		implements Initializable {
	@FXML
	TableView<T> crudTable;

	protected Integer currentId;
	protected T currentModel;

	protected DialogWindow<AbstractCRUDFormController<T>> dialogWindow;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AbstractCRUDFormController");
		init(location, resources);
	}

	public abstract void init(URL location, ResourceBundle resources);

	public abstract T createModel();

	FormMode getMode() {
		return Optional.ofNullable(currentId).map(id -> FormMode.EDIT).orElse(FormMode.ADD);
	}

	public void setWindow(DialogWindow<AbstractCRUDFormController<T>> window) {
		dialogWindow = window;
		setWindowTitle();
	}

	public void setWindowTitle() {
		String title = (getMode() == FormMode.ADD ? "Create " : "Edit ") + getTitle();
		if (this.dialogWindow != null) {
			this.dialogWindow.getDialogStage().setTitle(title);
		}
	}

	public void getModelDetails(Integer id) {
		runTask(() -> {
			if (getMode() == FormMode.ADD) {
				currentModel = createModel();
			} else {
				currentModel = getRepositoryService().getById(id);
			}

			fillDetails(currentModel);
		});
	}

	protected void fillDetails(T model) {
		Utils.setBoundedData(model, this, "");
	}

	protected void getFormData() {
		Utils.getBoundedData(currentModel, this, null);
	}

	@FXML
	public void create() {
		setCurrentId(null);
	}

	@FXML
	public void save() {
		runTask(() -> {
			getFormData();
			System.out.println(((Supplier) currentModel).getName());
			if (getMode() == FormMode.ADD) {
				Integer newId = getRepositoryService().addNew(currentModel);
				setCurrentId(newId);
			} else {
				getRepositoryService().update(currentModel);
				getModelDetails(currentModel.getId());
			}
		});
	}

	@FXML
	public void cancel() {
		this.dialogWindow.closeDialog();
	}

	public void fillData(T model) {
		Utils.setBoundedData(model, this, "");
	}

	public void onCurrentIdChanged() {
		getModelDetails(currentId);
		toggleButtons();
		setWindowTitle();
	}

	protected void toggleButtons() {
		if (getMode() == FormMode.EDIT) {
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

	public void setCurrentId(Integer id) {
		currentId = id;
		onCurrentIdChanged();
	}

}
