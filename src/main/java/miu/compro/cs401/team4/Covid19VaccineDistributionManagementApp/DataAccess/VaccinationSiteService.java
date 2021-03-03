package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VaccinationSiteService extends RepositoryService<VaccinationSite> {
    @Override
    public List<VaccinationSite> getAll() {
        List<VaccinationSite> vaccinationSites = new ArrayList<>();

        try {
            Statement statement = DBManager.getInstance().getConnection().createStatement();
            System.out.println("query preparing");
            String query = "select id, name, city, state, zipCode from VaccinationSite";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                VaccinationSite VaccinationSite = new VaccinationSite(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));

                vaccinationSites.add(VaccinationSite);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vaccinationSites;
    }

    @Override
    public VaccinationSite getById(Integer id) {
        VaccinationSite VaccinationSite = null;

        try {
            System.out.println("query preparing");
            String query = "select id, name, city, state, zipCode from VaccinationSite where id=?";
            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            VaccinationSite = new VaccinationSite(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return VaccinationSite;
    }

    @Override
    public boolean add(VaccinationSite model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "insert into VaccinationSite(  name, city, state, zipCode) values (?,?,?,?)";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getCity());
            preparedStatement.setString(3, model.getState());
            preparedStatement.setString(4, model.getZipCode());

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(VaccinationSite model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "update VaccinationSite set name =?, city =?, state =?, zipCode =? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getCity());
            preparedStatement.setString(3, model.getState());
            preparedStatement.setString(4, model.getZipCode());
            preparedStatement.setInt(5, model.getId());

            result = preparedStatement.executeUpdate();

//            DBManager.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean delete(Integer Id) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "delete from VaccinationSite where id = ?";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
}
