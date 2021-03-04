package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Model;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.TaskWithException;

/**
 * abstract class of the all controllers which are implementing CRUD operations
 * */
public abstract class AbstractCRUDController<T extends Model> {
	public abstract RepositoryService<T> getRepositoryService();
	
	/**
	 * Inherited classes must implement that method. It used to change title of app or dialog window.
	 * */
	public abstract String getTitle();
	
	/**
	 * Runs task safely when error occurs show the error message
	 * 
	 * @param task {@code TaskWithException} task to run
	 * */
	public void runTask(TaskWithException task) {
		runTask(task, false);
	}

	/**
	 * Runs task safely when error occurs show the error message
	 * 
	 * @param task {@code TaskWithException} task to run
	 * @param showSuccess {boolean} once task if true finished shows success message
	 * */
	public void runTask(TaskWithException task, boolean showSuccess) {
		try {
			task.run();
			if (showSuccess) {
				App.showSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
			App.showError(e.getMessage());
		}
	}

}
