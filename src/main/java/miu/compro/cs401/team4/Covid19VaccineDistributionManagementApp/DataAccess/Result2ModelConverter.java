package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface Result2ModelConverter<T> {
	T convert(ResultSet resultSet) throws SQLException;
}
