package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.CandidateService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;

import java.io.IOException;

public class RegisterCandidateController {

    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public TextField age;

    @FXML
    public TextField status;

    @FXML
    public ChoiceBox vaccinationSiteChoiceBox;

    public void initialize(){

    }


    public void registerCandidate(ActionEvent event) {
        CandidateService candidateService = new CandidateService();
//        candidateService.add(new Candidate())
    }

    public void back(ActionEvent event) throws IOException {
        App.setRoot(Navigations.CANDIDATE.getValue());
    }
}
