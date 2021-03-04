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

/**
 * abstract class of the all controllers which are implementing create and edit form of CRUD
 * 
 * @param T {@code extends Model} generic type of target Model class. Example: Supplier 
 * */
public abstract class AbstractCRUDFormController<T extends Model> extends AbstractCRUDController<T>
		implements Initializable {
	
	/**
	 * Id of currently editing Model object
	 * */
	protected Integer currentId;
	
	/**
	 * Object currently editing Model
	 * */
	protected T currentModel;

	/**
	 * References wrapping DialogWindow. Set by owner CRUD list controller when showing the form controller in a dialog window.
	 * */
	protected DialogWindow<AbstractCRUDFormController<T>> dialogWindow;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AbstractCRUDFormController");
		init(location, resources);
	}

	/**
	 * When called the controller initialized.
	 * Inherited controllers must implement that method and can place some initialization staffs such as "fill comboboxes".
	 * */
	public abstract void init(URL location, ResourceBundle resources);

	/**
	 * Must return new object of the {@code T}. When called the controller switches to Add mode.
	 * Inherited controllers must implement that method. See {@SupplierFormController}
	 * */
	public abstract T createModel();

	FormMode getMode() {
		return Optional.ofNullable(currentId).map(id -> FormMode.EDIT).orElse(FormMode.ADD);
	}

	/**
	 * Sets wrapping dialog window. CRUD list controller calls that when shows the form controller/when create, edit button clicked/ in dialog. 
	 * */
	public void setWindow(DialogWindow<AbstractCRUDFormController<T>> window) {
		dialogWindow = window;
		setWindowTitle();
	}

	/**
	 * Sets wrapping dialog window title. CRUD list controller calls that when shows the form controller in dialog. 
	 * */
	public void setWindowTitle() {
		String title = (getMode() == FormMode.ADD ? "Create " : "Edit ") + getTitle();
		if (this.dialogWindow != null) {
			this.dialogWindow.getDialogStage().setTitle(title);
		}
	}

	/**
	 * Gets current models' detail information from the database
	 * */
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

	/**
	 * Fills input controls /TextField, ChooseBox/ with model's property values. Uses {@Bind} annotation.
	 * */
	protected void fillDetails(T model) {
		Utils.setBoundedData(model, this, "");
	}

	/**
	 * Sets currentModel's property by corresponding input control/TextField, ChooseBox/ values. Uses {@Bind} annotation.
	 * */
	protected void getFormData() {
		Utils.getBoundedData(currentModel, this, null);
	}

	/**
	 * Clears all input controls and switches form to Add mode.
	 * Button "Create New" calls that method. See the button's onAction attribute.
	 * */
	@FXML
	public void create() {
		setCurrentId(null);
	}

	/**
	 * Saves/create or update/ current model to the database.
	 * Button "Save" calls that method. See the button's onAction attribute.
	 * */
	@FXML
	public void save() {
		runTask(() -> {
			getFormData();

			if (getMode() == FormMode.ADD) {
				getRepositoryService().add(currentModel);
				setCurrentId(null);
			} else {
				getRepositoryService().update(currentModel);
				getModelDetails(currentModel.getId());
			}
		}, true);
	}

	/**
	 * Closes current dialog Window.
	 * Button "Cancel" calls that method. See the button's onAction attribute.
	 * */
	@FXML
	public void cancel() {
		this.dialogWindow.closeDialog();
	}

//	public void fillData(T model) {
//		Utils.setBoundedData(model, this, "");
//	}

	
	/**
	 * Invoked when the currentId changed.
	 * */
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

	/**
	 * Sets currentId and calls {@code onCurrentIdChanged}
	 * */
	public void setCurrentId(Integer id) {
		currentId = id;
		onCurrentIdChanged();
	}

}
