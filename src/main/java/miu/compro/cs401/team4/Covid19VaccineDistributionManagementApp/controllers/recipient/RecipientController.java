package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.recipient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.CandidateService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RecipientService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccineService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipientController extends AbstractCRUDListController<Candidate> {

    static final RecipientService repositoryService = new RecipientService();
    static final CandidateService candidateService = new CandidateService();
    static final VaccineService vaccineService = new VaccineService();
    @FXML
    @Bind("firstName")
    TableColumn<Candidate, String> cFirstName;

    @FXML
    @Bind("lastName")
    TableColumn<Candidate, String> cLastName;

    @FXML
    @Bind("age")
    TableColumn<Candidate, Integer> cAge;


    @Override
    public void init(URL location, ResourceBundle resources) {

        TableColumn<Candidate, Void> colBtn = new TableColumn("Action");

        Callback<TableColumn<Candidate, Void>, TableCell<Candidate, Void>> cellCallback = new Callback<TableColumn<Candidate, Void>, TableCell<Candidate, Void>>() {
            @Override
            public TableCell<Candidate, Void> call(TableColumn<Candidate, Void> candidateVoidTableColumn) {
                final TableCell<Candidate, Void> cell = new TableCell<Candidate, Void>() {
                    private final Button btn = new Button("Vaccinate");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            //TODO call service
//                            getTableView().getItems().get(getIndex());

                        });
                    }

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

        TableColumn<Vaccine, String> colVaccine = new TableColumn("Vaccine Type");

        Callback<TableColumn<Vaccine, String>, TableCell<Vaccine, String>> cellFactory =
                new Callback<TableColumn<Vaccine, String>, TableCell<Vaccine, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Vaccine, String> param) {
                        final TableCell<Vaccine, String> cell = new TableCell<Vaccine, String>() {

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    setGraphic(createBox());
                                    setText(null);
                                }
                            }

                            private ChoiceBox createBox() {
                                    ChoiceBox box = new ChoiceBox();
                                    box.getItems().addAll(vaccineService.getAll());
                                    return box;
                            }
                        };
                        return cell;
                    }
                };

        colVaccine.setCellFactory(cellFactory);
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
    public String getFormUrl() {
        return null;
    }
}
