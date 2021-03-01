package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	 static Connection connection = null;
	 
	 public static Connection getConnection() {
		 if(connection == null) {
			 try
			 {
				 connection = DriverManager.getConnection("jdbc:sqlite:cvdms.db");
			 }
			 catch (SQLException e) {
				throw new RuntimeException(e);
			}
		 }
		 
		 return connection;
	 }
}
