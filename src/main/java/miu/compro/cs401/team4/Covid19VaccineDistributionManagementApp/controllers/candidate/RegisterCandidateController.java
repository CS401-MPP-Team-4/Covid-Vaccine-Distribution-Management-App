package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.CandidateService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.VaccinationSiteService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterCandidateController {

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public TextField age;

    @FXML
    public TextField ssn;

    @FXML
    public ChoiceBox<VaccinationSite> vaccinationSiteChoiceBox;

    public void initialize() {

        ObservableList<VaccinationSite> vaccinationSites = FXCollections.observableArrayList();
        vaccinationSites.addAll(new VaccinationSiteService().getAll());
        vaccinationSiteChoiceBox.setItems(vaccinationSites);
    }


    public void registerCandidate() {
        CandidateService candidateService = new CandidateService();
        VaccinationSite vacSite = vaccinationSiteChoiceBox.getSelectionModel().getSelectedItem();
        candidateService.add(new Candidate(0, firstName.getText(), lastName.getText(), Byte.parseByte(age.getText()),  ssn.getText(),"NOT VACCINATED", LocalDate.now(), vacSite));
        
        Integer lastId = candidateService.getAll()
        	.stream()
        	.mapToInt(Candidate::getId)
        	.max()
        	.orElse(0);
        
        clearFields();
        App.showSuccess("Registered Successfully!",  "Your ID is: " + lastId);
    }

    public void back() throws IOException {
        App.setRoot(Navigations.CANDIDATE.getValue());
    }

    public void clearFields() {
        firstName.setText("");
        lastName.setText("");
        age.setText("");
        ssn.setText("");
    }
}
