package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Candidate;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateService extends RepositoryService<Candidate> {
    @Override
    public List<Candidate> getAll() {
        List<Candidate> CandidateList = new ArrayList<>();

        try {
            Statement statement = DBManager.getInstance().getConnection().createStatement();
            System.out.println("query preparing");
            String query = "select id, firstname, lastname, age, ssn, status, dateofapplication, vaccinationsite from Candidate";

            ResultSet resultSet = statement.executeQuery(query);
            SupplierService supplierService = new SupplierService();

            while (resultSet.next()) {
                Supplier supplier = supplierService.getById(resultSet.getInt(8));
                //Integer id, String firstName, String lastName, Byte age, String ssn, String status, LocalDate dateOfApplication, VaccinationSite vaccinationSite) {

                Candidate candidate = new Candidate(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7).toLocalDate(),
                        new VaccinationSite(1,null,null, null,null));
                CandidateList.add(candidate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return CandidateList;
    }

    @Override
    public Candidate getById(Integer id) {
        Candidate candidate = null;

        try {
            System.out.println("query preparing");
            String query = "select id, firstname, lastname, age, ssn, status, dateofapplication, vaccinationsite from Candidate where id=?";
            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            SupplierService supplierService = new SupplierService();
            Supplier supplier = supplierService.getById(resultSet.getInt(resultSet.getString(3)));

            candidate = new Candidate(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getByte(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDate(7).toLocalDate(),
                    new VaccinationSite(1,null,null, null,null));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return candidate;
    }

    @Override
    public boolean add(Candidate model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "insert into Candidate(firstname, lastname, age, ssn, status, dateofapplication, vaccinationsite) values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setString(4, model.getSsn());
            preparedStatement.setString(5, model.getStatus());
            preparedStatement.setDate(6, Date.valueOf(model.getDateOfApplication()));
            preparedStatement.setInt(7, model.getVaccinationSite().getId());

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Candidate model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "update Candidate set firstname = ?, lastname = ?, age= ?, ssn= ?, status= ?, dateofapplication = ?, vaccinationsite = ? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setString(4, model.getSsn());
            preparedStatement.setString(5, model.getStatus());
            preparedStatement.setDate(6, Date.valueOf(model.getDateOfApplication()));
            preparedStatement.setInt(7, model.getVaccinationSite().getId());
            preparedStatement.setInt(8, model.getId());
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
            String query = "delete from Candidate where id = ?";

            PreparedStatement preparedStatement = DBManager.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);


            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
}
