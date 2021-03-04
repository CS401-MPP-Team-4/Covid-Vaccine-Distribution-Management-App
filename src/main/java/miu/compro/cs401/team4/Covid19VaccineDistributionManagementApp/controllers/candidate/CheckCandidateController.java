package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate;

import javafx.event.ActionEvent;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;

import java.io.IOException;

public class CheckCandidateController {

    public void checkCandidateStatus(ActionEvent event) {
    }

    public void back(ActionEvent event) throws IOException {
        App.setRoot(Navigations.CANDIDATE.getValue());
    }
}
