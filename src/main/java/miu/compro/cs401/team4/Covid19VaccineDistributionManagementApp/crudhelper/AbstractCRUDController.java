package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Model;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.TaskWithException;

public abstract class AbstractCRUDController<T extends Model> {
	public abstract RepositoryService<T> getRepositoryService();
	
	public abstract String getTitle();
	
	public void runTask(TaskWithException task) {
		runTask(task, false);
	}

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
