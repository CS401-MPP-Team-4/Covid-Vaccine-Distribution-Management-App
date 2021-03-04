package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.staff;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.StaffService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Staff;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffController extends AbstractCRUDListController<Staff> {
    static final RepositoryService<Staff> repositoryService = new StaffService();

    @FXML
    @Bind("userName")
    TableColumn<Staff, String> tcUsername;

    @FXML
    @Bind("password")
    TableColumn<Staff, String> tcPassword;

    @FXML
    @Bind("jobDescription")
    TableColumn<Staff, Integer> tcJobDescription;

    @FXML
    @Bind("userName")
    Label lblUsername;

    @FXML
    @Bind("password")
    Label lblPassword;

    @FXML
    @Bind("jobDescription")
    Label lblJobDescription;



    @Override
    public void init(URL location, ResourceBundle resources) {

    }

    @Override
    public String getTitle() {
        return "Staff";
    }

    @Override
    public RepositoryService<Staff> getRepositoryService() {
        return repositoryService;
    }

    @Override
    public String getFormUrl() {
        return Navigations.STAFF_FORM.getValue();
    }
}
