module miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires java.desktop;
	requires javafx.graphics;
    
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.crudhelper to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models to javafx.base;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccine to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.supplier to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.vaccinationsite to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.candidate to javafx.fxml;

    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers.staff to javafx.fxml;

    exports miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;
    exports miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;
}