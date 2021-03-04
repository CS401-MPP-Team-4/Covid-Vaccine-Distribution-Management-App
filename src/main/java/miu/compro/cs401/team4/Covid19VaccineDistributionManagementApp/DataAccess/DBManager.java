package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBManager {

	INSTANCE;

	// instance vars, constructor
	private final Connection connection;

	DBManager()
	{
		// Initialize the connection
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:cvdms.db");
		} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	// Static getter
	public static DBManager getInstance()
	{
		return INSTANCE;
	}

	public Connection getConnection()
	{
		return connection;
	}
}
