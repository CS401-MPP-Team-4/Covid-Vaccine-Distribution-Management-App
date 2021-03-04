package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.CandidateService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;

import java.io.IOException;

public class CheckCandidateController {

    static final CandidateService candidateService = new CandidateService();

    @FXML
    public TextField candidateID;

    @FXML
    public Label statusInfo;

    public void checkCandidateStatus() {
       Candidate candidate =  candidateService.getById(Integer.parseInt(candidateID.getText()));
       statusInfo.setText("You are: " + candidate.getStatus());

    }

    public void back() throws IOException {
        App.setRoot(Navigations.CANDIDATE.getValue());
    }
}
