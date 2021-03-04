package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate;

import javafx.event.ActionEvent;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;

import java.io.IOException;

public class CandidateController {
    public void checkStatus(ActionEvent event) throws IOException {
        App.setRoot(Navigations.CHECK_CANDIDATE_STATUS.getValue());
    }

    public void register(ActionEvent event) throws IOException {
        App.setRoot(Navigations.REGISTER_CANDIDATE.getValue());
    }

    public void back(ActionEvent event) throws IOException {
        App.setRoot(Navigations.LOGIN.getValue());
    }
}
