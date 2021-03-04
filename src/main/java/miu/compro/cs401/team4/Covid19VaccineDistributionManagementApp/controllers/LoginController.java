package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.App;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess.LoginService;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.Navigations;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.utils.ROLES;

import java.io.IOException;

public class LoginController {


    @FXML
    private void goToHome() throws IOException {
        App.setRoot(Navigations.MASTER.getValue());
    }

    @FXML
    public Button login;

    @FXML
    public TextField username;

    @FXML
    public TextField password;

    @FXML
    public ChoiceBox roleChoiceBox;


    public void initialize() {
        ObservableList<ROLES> choices = FXCollections.observableArrayList();
        choices.add(ROLES.ADMIN);
        choices.add(ROLES.STAFF);
        choices.add(ROLES.CANDIDATE);

        roleChoiceBox.setItems(choices);

        roleChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (observableValue.getValue().equals(ROLES.CANDIDATE)) {
                    System.out.println("works");
                }
            }
        });


    }


    public void login(ActionEvent event) throws IOException {
        if (LoginService.login(username.getText(), password.getText(), ROLES.ADMIN.getVal())) {
            Object selectedItem = roleChoiceBox.getSelectionModel().getSelectedItem();
            if (ROLES.STAFF.getVal().equals(selectedItem)) {
                App.setRoot(Navigations.MASTER.getValue());
            } else if (ROLES.ADMIN.getVal().equals(selectedItem)) {
                App.setRoot(Navigations.MASTER.getValue());
            }
        } else {
            App.showError("Incorrect username or password!");

        }
        username.setText("");
        password.setText("");
    }


}
