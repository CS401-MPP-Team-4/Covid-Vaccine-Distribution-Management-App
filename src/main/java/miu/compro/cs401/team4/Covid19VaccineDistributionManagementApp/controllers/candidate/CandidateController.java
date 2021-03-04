package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;

import java.io.IOException;

public class CandidateController {
    public void checkStatus() throws IOException {
        App.setRoot(Navigations.CHECK_CANDIDATE_STATUS.getValue());
    }

    public void register() throws IOException {
        App.setRoot(Navigations.REGISTER_CANDIDATE.getValue());
    }

    public void back() throws IOException {
        App.setRoot(Navigations.LOGIN.getValue());
    }
}
