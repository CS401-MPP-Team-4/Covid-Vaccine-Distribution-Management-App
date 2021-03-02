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
    public List<Supplier> getAll() {
        List<Supplier> supplierList = new ArrayList<>();

        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "select id, name, city, phoneNumber from Supplier";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));

                supplierList.add(supplier);
            }
//            DBManager.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return supplierList;
    }

    @Override
    public Supplier getById(Integer id) {
        return null;
    }

    @Override
    public Integer add(Supplier model) {
        int result = 0;
        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "insert into Supplier( name, city, phoneNumber) values (?,?,?)";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getAddress());
            preparedStatement.setString(3, model.getPhoneNumber());

            result = preparedStatement.executeUpdate();

            return result;
//            DBManager.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return result;
        }
    }

    @Override
    public void update(Supplier model) {

    }

    @Override
    public void delete(Integer Id) {

    }
}
