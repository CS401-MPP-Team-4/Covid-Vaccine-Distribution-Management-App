module miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp {
    requires javafx.controls;
    requires javafx.fxml;

    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp to javafx.fxml;
    exports miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;
    exports miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;
}