package miu.compro.cs401.team4.Covid19VaccineDistributionManagementApp.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEmptyDB {

	/// CREATING NEW DB. DON"T USE UNLESS THERE IS NO DB FILE.
	public static void main(String[] args) {
		Connection connection = DBManager.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE Supplier(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "    [name] NVARCHAR(50) NOT NULL,\r\n"
					+ "    [address] VARCHAR(150) NULL,\r\n"
					+ "    [phoneNumber] NVARCHAR(15) NULL,\r\n"
					+ "\r\n"
					+ "    CONSTRAINT [PK_Supplier] PRIMARY KEY ([id] ASC autoincrement),\r\n"
					+ "    CONSTRAINT [UQ_Supplier_Name] UNIQUE ([name] ASC)\r\n"
					+ ");\r\n"
					+ "");
			
			statement.executeUpdate("CREATE TABLE Vaccine(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "    [name] NVARCHAR(50) NOT NULL,\r\n"
					+ "    [supplier] INT NULL,\r\n"
					+ "    \r\n"
					+ "    CONSTRAINT [PK_Vaccine] PRIMARY KEY ([id] ASC autoincrement),\r\n"
					+ "    CONSTRAINT [UQ_Vaccine_Name] UNIQUE ([name] ASC),\r\n"
					+ "    CONSTRAINT [FK_Vaccine_To_Supplier] FOREIGN KEY ([supplier]) REFERENCES [Supplier] ([id])\r\n"
					+ ");");
			
			statement.executeUpdate("CREATE TABLE VaccinationSite(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "    [name] NVARCHAR(50) NOT NULL,\r\n"
					+ "    [city] VARCHAR(150) NULL,\r\n"
					+ "    [state] VARCHAR(100) NULL,\r\n"
					+ "    [zipCode] NVARCHAR(5) NULL,\r\n"
					+ "    \r\n"
					+ "    CONSTRAINT [PK_VaccinationSite] PRIMARY KEY ([id] ASC autoincrement),\r\n"
					+ "    CONSTRAINT [UQ_VaccinationSite_Name] UNIQUE ([name] ASC)\r\n"
					+ ");\r\n"
					+ "");
			
			
			statement.executeUpdate("CREATE TABLE SiteStock(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "    [vaccinationSite] INT NULL,\r\n"
					+ "    [vaccine] INT NULL,\r\n"
					+ "    [stockAmount] INT NOT NULL DEFAULT 0,\r\n"
					+ "    \r\n"
					+ "    CONSTRAINT [PK_SiteStock] PRIMARY KEY ([id] ASC autoincrement),\r\n"
					+ "    CONSTRAINT [FK_SiteStock_To_VaccinationSite] FOREIGN KEY ([vaccinationSite]) REFERENCES [VaccinationSite] ([id]),\r\n"
					+ "    CONSTRAINT [FK_SiteStock_To_Vaccine] FOREIGN KEY ([vaccine]) REFERENCES [Vaccine] ([id])\r\n"
					+ ");");
			
			
			statement.executeUpdate("CREATE TABLE Candidate(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "    [firstName] NVARCHAR(50) NOT NULL,\r\n"
//					+ "    [middleName] NVARCHAR(50) NOT NULL,\r\n"
					+ "    [lastName] NVARCHAR(50) NOT NULL,\r\n"
					+ "    [age] INT NOT NULL,\r\n"
					+ "    [ssn] NVARCHAR(15) NOT NULL,\r\n"
					+ "    [status] NVARCHAR(25) NOT NULL,\r\n"
					+ "    [dateOfApplication] DATE NOT NULL,\r\n"
//					+ "    [person] INT NULL,\r\n"
					+ "    [vaccinationSite] INT NULL,\r\n"
					+ "\r\n"
					+ "    CONSTRAINT [PK_Candidate] PRIMARY KEY ([id] ASC autoincrement),\r\n"
					+ "    CONSTRAINT [FK_Candidate_To_VaccinationSite] FOREIGN KEY ([vaccinationSite]) REFERENCES [VaccinationSite] ([id])\r\n"
					+ ");");
			
			statement.executeUpdate("CREATE TABLE Staff(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "\r\n"
					+ "    [userName] NVARCHAR(50) NOT NULL,\r\n"
					+ "    [password] NVARCHAR(250) NOT NULL,\r\n"
					+ "    [jobDescription] NVARCHAR(25) NOT NULL,\r\n"
					+ "\r\n"
					+ "    CONSTRAINT [PK_Staff] PRIMARY KEY ([id] ASC autoincrement)\r\n"
					+ ");");

			statement.executeUpdate("CREATE TABLE Recipient(\r\n"
					+ "    [id] INTEGER ,\r\n"
					+ "    [dateOfShot] DATE NOT NULL,\r\n"
					+ "\r\n"
					+ "    [takenBy] INT NULL,\r\n"
					+ "    [vaccine] INT NULL,\r\n"
					+ "    [administeredBy] INT NULL,\r\n"
					+ "\r\n"
					+ "    CONSTRAINT [PK_Recipient] PRIMARY KEY ([id] ASC autoincrement),\r\n"
					+ "    CONSTRAINT [FK_Recipient_To_Candidate] FOREIGN KEY ([takenBy]) REFERENCES [Candidate] ([id]),\r\n"
					+ "    CONSTRAINT [FK_Recipient_To_Vaccine] FOREIGN KEY ([vaccine]) REFERENCES [Vaccine] ([id]),\r\n"
					+ "    CONSTRAINT [FK_Recipient_To_Staff] FOREIGN KEY ([administeredBy]) REFERENCES [Staff] ([id])\r\n"
					+ ");");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
