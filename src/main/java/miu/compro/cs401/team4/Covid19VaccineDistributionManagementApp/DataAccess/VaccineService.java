package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.SiteStock;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VaccineService extends RepositoryService<Vaccine> {
    @Override
    public List<Vaccine> getAll() {
        List<Vaccine> VaccineList = new ArrayList<>();

        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("query preparing");
            String query = "select id, name, supplier, amount from Vaccine";

            ResultSet resultSet = statement.executeQuery(query);
            SupplierService supplierService = new SupplierService();

            while (resultSet.next()) {
                Supplier supplier = supplierService.getById(resultSet.getInt(3));
                Vaccine vaccine = new Vaccine(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        supplier,
                        resultSet.getInt(4));

                VaccineList.add(vaccine);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return VaccineList;
    }

    @Override
    public Vaccine getById(Integer id) {
        Vaccine vaccine = null;

        try {
            System.out.println("query preparing");
            String query = "select id, name, supplier, amount from Vaccine where id=?";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

//            resultSet.next();
            SupplierService supplierService = new SupplierService();
            Supplier supplier = supplierService.getById(resultSet.getInt(3));

            vaccine = new Vaccine(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    supplier,
                    resultSet.getInt(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vaccine;
    }

    @Override
    public boolean add(Vaccine model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "insert into Vaccine( name, supplier, amount) values (?,?,?)";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getManufacturer().getId());
            preparedStatement.setInt(3, model.getAmount());

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(Vaccine model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "update Vaccine set name = ? , supplier = ?, amount =? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getManufacturer().getId());
            preparedStatement.setInt(3, model.getAmount());
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
            String query = "delete from Vaccine where id = ?";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);


            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
    
    public boolean dispatch(Integer vaccineId, Integer siteId, Integer quantity) {
        int result = 0;
        try {
        	var stockService= new SiteStockService();
        	var stock = stockService.getByOthers(vaccineId, siteId);
        	Vaccine vaccine = getById(vaccineId);
        	
        	vaccine.setAmount(vaccine.getAmount() - quantity);
        	if(update(vaccine)) {
        		if(stock == null) {
            		stock = new SiteStock(null, 
            				new VaccinationSite(siteId, null, null, null, null),
            				vaccine,
        					quantity);
            		return stockService.add(stock);
            	}
            	else {
            		stock.setStockAmount(quantity + stock.getStockAmount());
            		return stockService.update(stock);
            	}
        	}
        	
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
 
    	return false;
    }
    
    
}
