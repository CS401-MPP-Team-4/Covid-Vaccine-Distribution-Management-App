package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierService extends RepositoryService<Supplier> {
	
    @Override
    public List<Supplier> getAll() throws SQLException {
        List<Supplier> supplierList;

        String query = "select id, name, address, phoneNumber from Supplier";
        supplierList = DBManager.getAll(query, resultSet -> new Supplier(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
        
        return supplierList;
    }

    @Override
    public Supplier getById(Integer id) throws SQLException {
        Supplier supplier = null;

        String query = "select id, name, address, phoneNumber from Supplier where id=?";
        supplier = DBManager.getById(query, resultSet-> new Supplier(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)), id).orElse(null);
        return supplier;
    }

    @Override
    public boolean add(Supplier model) {
        int result = 0;
        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "insert into Supplier( name, address, phoneNumber) values (?,?,?)";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getPhoneNumber());

            result = preparedStatement.executeUpdate();

//            DBManager.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
    
    @Override
    public Integer addNew(Supplier model) throws SQLException {
        String query = "insert into Supplier( name, address, phoneNumber) values (?,?,?)";
        Integer newId = DBManager.insert(query,  model.getName(),  model.getAddress(), model.getPhoneNumber()).orElse(null);
        return newId;
    }

    @Override
    public boolean update(Supplier model) {
        int result = 0;
        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "update Supplier set name = ? , address = ?, phoneNumber =? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getPhoneNumber());
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
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "delete from Supplier where id = ?";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);


            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
}
