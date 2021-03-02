package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StaffService extends RepositoryService<Staff> {
    @Override
    public List<Staff> getAll() {
        List<Staff> StaffList = new ArrayList<>();

        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "select id, userName, password, jobDescription from Staff";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Staff Staff = new Staff(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));

                StaffList.add(Staff);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return StaffList;
    }

    @Override
    public Staff getById(Integer id) {
        Staff Staff = null;

        try {
            System.out.println("query preparing");
            String query = "select id, userName, password, jobDescription from Staff where id=?";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Staff = new Staff(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Staff;
    }

    @Override
    public boolean add(Staff model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "insert into Staff( userName, password, jobDescription) values (?,?,?)";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setString(3, model.getJobDescription());

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Staff model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "update Staff set userName =?, password =?, jobDescription =? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getUserName());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setString(3, model.getJobDescription());
            preparedStatement.setInt(4, model.getId());

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
            String query = "delete from Staff where id = ?";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);


            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
}
