package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Recipient;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Staff;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public static boolean login(String username, String password, String role){
        try {
            System.out.println("query preparing");
            String query = "select * from Staff where userName=? and password =?";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
