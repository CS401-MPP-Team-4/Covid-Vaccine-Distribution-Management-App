package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.staff;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.StaffService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDFormController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Staff;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffFormController extends AbstractCRUDFormController<Staff> {

    static final RepositoryService<Staff> repositoryService = new StaffService();

    @FXML
    @Bind("userName")
    TextField txtUsername;

    @FXML
    @Bind("password")
    TextField txtPassword;

    @FXML
    @Bind("jobDescription")
    TextField txtJobDescription;

    @Override
    public void init(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
    }

    @Override
    public RepositoryService<Staff> getRepositoryService() {
        return repositoryService;
    }

    @Override
    public String getTitle() {
        return "Staff";
    }

    @Override
    public Staff createModel() {
        return new Staff();
    }
}
