module miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp {
    requires javafx.controls;
    requires javafx.fxml;

    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.views to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.views.crudHelper to javafx.fxml;
    opens miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models to javafx.base;
    
    exports miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp;
}