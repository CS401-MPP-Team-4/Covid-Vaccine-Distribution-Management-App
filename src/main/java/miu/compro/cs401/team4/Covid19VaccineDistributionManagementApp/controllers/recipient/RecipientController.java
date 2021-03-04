package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.recipient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.CandidateService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RecipientService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.RepositoryService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccineService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper.AbstractCRUDListController;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Recipient;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Staff;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.Bind;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RecipientController extends AbstractCRUDListController<Candidate> {

    static final RecipientService recipientService = new RecipientService();
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

    @FXML
    @Bind("status")
    TableColumn<Candidate, Integer> cStatus;

    @FXML
    TableView<Recipient> recipientTable;


    @Override
    public void init(URL location, ResourceBundle resources) {


        TableColumn<Candidate, String> colVaccine = new TableColumn("Vaccine Type");

        Callback<TableColumn<Candidate, String>, TableCell<Candidate, String>> cellFactory =
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Candidate, String> param) {
                        final TableCell<Vaccine, String> cell = new TableCell<>() {

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
        getCrudTable().getColumns().add(colVaccine);

        TableColumn<Candidate, Void> colBtn = new TableColumn("Action");

        Callback<TableColumn<Candidate, Void>, TableCell<Candidate, Void>> cellCallback = new Callback<TableColumn<Candidate, Void>, TableCell<Candidate, Void>>() {
            @Override
            public TableCell<Candidate, Void> call(TableColumn<Candidate, Void> candidateVoidTableColumn) {
                final TableCell<Candidate, Void> cell = new TableCell<>() {
                    private final Button btn = new Button("Vaccinate");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            //TODO call service
                            Candidate candidate = candidateService.getById(getCrudTable().getItems().get(getIndex()).getId());
                            candidate.setStatus("VACCINATED");
                            candidateService.update(candidate);

//                            ChoiceBox<Vaccine> choiceBox = getCrudTable().getItems().get(getIndex()))

                            Recipient recipient = new Recipient();
                            recipient.setTakenBy(candidate);
                            recipient.setDateOfShot(LocalDate.now());
                            recipient.setAdministeredBy(new Staff(1));
                            Vaccine vaccine = new Vaccine();
                            vaccine.setId(1);
                            recipient.setVaccine(vaccine);
                            recipientService.add(recipient);
                            getCrudTable().getItems().remove(getIndex());
                            recipientTable.getItems().removeAll();
                            recipientTable.getItems().addAll(recipientService.getAll());


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
