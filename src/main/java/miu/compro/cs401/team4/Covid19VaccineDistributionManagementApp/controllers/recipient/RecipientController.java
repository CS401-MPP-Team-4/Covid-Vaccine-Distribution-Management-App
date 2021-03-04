package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.recipient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.CandidateService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RecipientService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.SupplierService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Recipient;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;


import java.net.URL;
import java.util.ResourceBundle;

public class RecipientController extends AbstractCRUDListController<Candidate> {

    static final RecipientService repositoryService = new RecipientService();
    static final CandidateService candidateService = new CandidateService();
    @FXML
    @Bind("firstName")
    TableColumn<Candidate, String> cFirstName;

    @FXML
    @Bind("lastName")
    TableColumn<Candidate, String> cLastName;

    @FXML
    @Bind("age")
    TableColumn<Candidate, Integer> cAge;


//    @FXML
//    @Bind("status")
//    TableColumn<Candidate, Integer> status;
    public void initialize(){
        TableColumn<Candidate, Void> colBtn = new TableColumn("Action");

        Callback<TableColumn<Candidate, Void>, TableCell<Candidate, Void>> cellCallback = new Callback<TableColumn<Candidate, Void>, TableCell<Candidate, Void>>() {
            @Override
            public TableCell<Candidate, Void> call(TableColumn<Candidate, Void> candidateVoidTableColumn) {
                final TableCell<Candidate, Void> cell = new TableCell<Candidate, Void>() {
                    private final Button btn = new Button("Vaccinate");

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellCallback);
        getCrudTable().getColumns().add(colBtn);

    }



    @Override
    public RepositoryService<Candidate> getRepositoryService() {
        return candidateService;
    }

    @Override
    public String getTitle() {
        return "Recipient";
    }

    @Override
    public void init(URL location, ResourceBundle resources) {


    }

    @Override
    public String getFormUrl() {
        return null;
    }
}
