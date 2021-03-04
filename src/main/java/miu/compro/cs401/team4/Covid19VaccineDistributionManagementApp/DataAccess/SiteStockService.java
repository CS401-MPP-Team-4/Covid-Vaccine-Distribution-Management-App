package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Supplier;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.SiteStock;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.VaccinationSite;
import miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.models.Vaccine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SiteStockService extends RepositoryService<SiteStock> {
    @Override
    public List<SiteStock> getAll() {
        List<SiteStock> SiteStockList = new ArrayList<>();

        try {
            Statement statement = DBManager.getConnection().createStatement();
            System.out.println("SiteStock: query preparing");
            String query = "select id, vaccinationSite, vaccine, stockAmount from SiteStock";

            ResultSet resultSet = statement.executeQuery(query);
            VaccinationSiteService vaccinationSiteService = new VaccinationSiteService();
            VaccineService vaccineService = new VaccineService();

            while (resultSet.next()) {
                Vaccine vaccine = vaccineService.getById(resultSet.getInt(2));
                VaccinationSite vaccinationSite = vaccinationSiteService.getById(resultSet.getInt(3));
                SiteStock SiteStock = new SiteStock(
                        resultSet.getInt(1),
                       vaccinationSite,
                        vaccine,
                        resultSet.getInt(4));

                SiteStockList.add(SiteStock);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return SiteStockList;
    }

    @Override
    public SiteStock getById(Integer id) {
        SiteStock siteStock = null;

        try {
            System.out.println("site stock: query preparing");
            String query = "select id, vaccinationSite, vaccine, stockAmount from SiteStock where id = ?";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            VaccinationSiteService vaccinationSiteService = new VaccinationSiteService();
            VaccineService vaccineService = new VaccineService();

            Vaccine vaccine = vaccineService.getById(resultSet.getInt(2));
            VaccinationSite vaccinationSite = vaccinationSiteService.getById(resultSet.getInt(3));

            siteStock = new SiteStock(
                    resultSet.getInt(1),
                    vaccinationSite,
                    vaccine,
                    resultSet.getInt(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return siteStock;
    }

    @Override
    public boolean add(SiteStock model) {
        int result = 0;
        try {
            System.out.println("Site Stock: query preparing");
            String query = "insert into SiteStock(vaccinationSite, vaccine, stockAmount) values (?,?,?)";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, model.getSite().getId());
            preparedStatement.setInt(2, model.getVaccine().getId());
            preparedStatement.setInt(3, model.getStockAmount());

            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }

    @Override
    public boolean update(SiteStock model) {
        int result = 0;
        try {
            System.out.println("query preparing");
            String query = "update SiteStock set vaccinationSite = ? , vaccine = ?, stockAmount =? where id = ?;";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, model.getSite().getId());
            preparedStatement.setInt(2, model.getVaccine().getId());
            preparedStatement.setInt(3, model.getStockAmount());
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
            System.out.println("site stock: query preparing");
            String query = "delete from SiteStock where id = ?";

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, Id);


            result = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result > 0;
    }
    
    
    public SiteStock getByOthers(Integer vaccineId, Integer siteId) {
        SiteStock siteStock = null;

        try {
            System.out.println("site stock: query preparing");
            String query = "select id, vaccinationSite, vaccine, stockAmount from SiteStock where vaccinationSite = ? and vaccine = ?";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, vaccineId);
            preparedStatement.setInt(2, siteId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.isClosed())
            	return null;
            
            VaccinationSiteService vaccinationSiteService = new VaccinationSiteService();
            VaccineService vaccineService = new VaccineService();

            Vaccine vaccine = vaccineService.getById(resultSet.getInt(2));
            VaccinationSite vaccinationSite = vaccinationSiteService.getById(resultSet.getInt(3));

            siteStock = new SiteStock(
                    resultSet.getInt(1),
                    vaccinationSite,
                    vaccine,
                    resultSet.getInt(4));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return siteStock;
    }
    
    public List<SiteStock> getBySiteId(Integer siteId) {
    	List<SiteStock> siteStockList = new ArrayList<>();

        try {
            System.out.println("site stock: query preparing");
            String query = "select id, vaccinationSite, vaccine, stockAmount from SiteStock where vaccinationSite = ?";
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, siteId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            VaccinationSiteService vaccinationSiteService = new VaccinationSiteService();
            VaccineService vaccineService = new VaccineService();

            while (resultSet.next()) {
                Vaccine vaccine = vaccineService.getById(resultSet.getInt(2));
                VaccinationSite vaccinationSite = vaccinationSiteService.getById(resultSet.getInt(3));
                SiteStock SiteStock = new SiteStock(
                        resultSet.getInt(1),
                       vaccinationSite,
                        vaccine,
                        resultSet.getInt(4));

                siteStockList.add(SiteStock);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return siteStockList;
    }
}
