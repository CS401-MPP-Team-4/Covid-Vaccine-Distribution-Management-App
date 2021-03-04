package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.supplier;

import java.net.URL;
import java.util.ResourceBundle;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDFormController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;

public class SupplierFormController extends AbstractCRUDFormController<Supplier> {

	static final RepositoryService<Supplier> repositoryService = new SupplierService();
	
	@Override
	public void init(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	@Override
	public RepositoryService<Supplier> getRepositoryService() {
		return repositoryService;
	}

	@Override
	public String getTitle() {
		return "Supplier";
	}

}
