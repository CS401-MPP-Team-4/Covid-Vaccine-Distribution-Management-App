package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Recipient;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Staff;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipientService extends RepositoryService<Recipient> {
    @Override
    public List<Recipient> getAll() {
        List<Recipient> RecipientList = new ArrayList<>();

        try {
            Statement statement = DBManager.getInstance().getConnection().createStatement();
            System.out.println("query preparing");
            String query = "select id, dateOfShot, takenBy, vaccine, administeredBy from Recipient";

            ResultSet resultSet = statement.executeQuery(query);
            StaffService staffService = new StaffService();
            CandidateService candidateService = new CandidateService();
            VaccineService vaccineService = new VaccineService();

            while (resultSet.next()) {
                Candidate candidate = candidateService.getById(resultSet.getInt(3));
                Vaccine vaccine = vaccineService.getById(resultSet.getInt(4));
                Staff staff = staffService.getById(resultSet.getInt(5));

                Recipient Recipient = new Recipient(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        candidate,
                        vaccine,
                        staff);

                RecipientList.add(Recipient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return RecipientList;
    }

    @Override
    public Recipient getById(Integer id) {
        Recipient Recipient = null;

        try {
            System.out.println("query preparing");
            String query = "select id, dateOfShot, takenBy, vaccine, administeredBy from Recipient where id=?";
            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            StaffService staffService = new StaffService();
            CandidateService candidateService = new CandidateService();
            VaccineService vaccineService = new VaccineService();

            Candidate candidate = candidateService.getById(resultSet.getInt(3));
            Vaccine vaccine = vaccineService.getById(resultSet.getInt(4));
            Staff staff = staffService.getById(resultSet.getInt(5));

            Recipient = new Recipient(
                    resultSet.getInt(1),
                    resultSet.getDate(2).toLocalDate(),
                    candidate,
                    vaccine,
                    staff);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Recipient;
    }

    @Override
    public boolean add(Recipient model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "insert into Recipient( dateOfShot, takenBy, vaccine, administeredBy ) values (?,?,?,?)";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(model.getDateOfShot()));
            preparedStatement.setInt(2, model.getTakenBy().getId());
            preparedStatement.setInt(3, model.getVaccine().getId());
            preparedStatement.setInt(4, model.getAdministeredBy().getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Recipient model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "update Recipient set dateOfShot =?, takenBy =?, vaccine =?, administeredBy =? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(model.getDateOfShot()));
            preparedStatement.setInt(2, model.getTakenBy().getId());
            preparedStatement.setInt(3, model.getVaccine().getId());
            preparedStatement.setInt(4, model.getAdministeredBy().getId());
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
            String query = "delete from Recipient where id = ?";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);


            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
}
