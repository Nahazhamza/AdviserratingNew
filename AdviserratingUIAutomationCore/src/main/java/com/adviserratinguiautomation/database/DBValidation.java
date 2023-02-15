package com.adviserratinguiautomation.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

public class DBValidation {

	private static String dbUsername;
	private static String dbPassword;
	private static String dbServer;
	private static String dbDatabase;
	final static Logger log = Logger.getLogger(DBValidation.class);

	/**
	 * This is the base class for the selenium framework web driver
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ResourceCustomException
	 */

	public static String executeSQLQuery(String sqlQuery) throws InstantiationException, Exception {

		log.info("Entered executeSQLQuery method");

		Connection connection;
		String resultValue = "";
		ResultSet rs;

		dbServer = new ResourceRead().getResourceValueFromXML().getProperty("DBServer");
		dbUsername = new ResourceRead().getResourceValueFromXML().getProperty("DBUserName");
		dbPassword = new ResourceRead().getResourceValueFromXML().getProperty("DBPassword");
		dbDatabase = new ResourceRead().getResourceValueFromXML().getProperty("DBDatabase");

		String connectionUrl = "jdbc:sqlserver://" + dbServer + ":1433;" + "database=" + dbDatabase + ";" + "user="
				+ dbUsername + ";" + "password=" + dbPassword + ";" + "encrypt=true;" + "trustServerCertificate=true;"
				+ "loginTimeout=60;";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		} catch (ClassNotFoundException e) {

		}

		try {
			connection = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);

			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlQuery);

			try {

				while (rs.next()) {
					resultValue = rs.getString(1).toString();

				}
			} catch (SQLException e) {

			} catch (NullPointerException err) {
			} finally {
				connection.close();
			}
		} catch (SQLException sqlEx) {

		}

		log.info("Exited executeSQLQuery method");

		return resultValue;
	}

	/**
	 * @throws IOException
	 * @throws ResourceCustomException
	 * @throws ClassNotFoundException  This is the base class for the selenium
	 *                                 framework web driver
	 * @throws SQLException
	 * @throws
	 */

	public static int executeSQLProc(String appType, String searchType, String searchValue, String compCode)
			throws InstantiationException, IllegalAccessException, SQLException, ClassNotFoundException,
			ResourceCustomException, IOException {

		log.info("Entered SQL Proc Execution");

		Connection conn = null;
		String resultValue = "";
		ResultSet rs;
		int rowCount = 0;
		SQLServerCallableStatement ps = null;
		SQLServerDataTable stable = new SQLServerDataTable();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		dbServer = new ResourceRead().getResourceValueFromXML().getProperty("DBServer");
		dbUsername = new ResourceRead().getResourceValueFromXML().getProperty("DBUserName");
		dbPassword = new ResourceRead().getResourceValueFromXML().getProperty("DBPassword");
		dbDatabase = new ResourceRead().getResourceValueFromXML().getProperty("DBDatabase");

		String connectionUrl = "jdbc:sqlserver://" + dbServer + ":1433;" + "database=" + dbDatabase + ";" + "user="
				+ dbUsername + ";" + "password=" + dbPassword + ";" + "encrypt=true;" + "trustServerCertificate=true;"
				+ "loginTimeout=60;";
		stable.addColumnMetadata("CountryCode", java.sql.Types.VARCHAR);
		stable.addColumnMetadata("CompanyCode", java.sql.Types.VARCHAR);
		stable.addColumnMetadata("TypeId", java.sql.Types.INTEGER);
		stable.addColumnMetadata("Value", java.sql.Types.VARCHAR);

		try {

			conn = DriverManager.getConnection(connectionUrl);

			// Application Type
			if (appType.equalsIgnoreCase("FleetPartnersAU")) {
				ps = (SQLServerCallableStatement) conn
						.prepareCall("{call FLEETPARTNERSAU.spGetVehiclesNex(?,?,?,?,?,?,?,?,?,?,?,?,?)})");
				// Output Parameter
				ps.registerOutParameter(13, java.sql.Types.INTEGER);
				ps.setString(1, "FleetPartnersAU");
				ps.setNull(11, java.sql.Types.NVARCHAR); // NZCompanyCodes
				ps.setString(10, compCode); // Company Code 259837
			} else if (appType.equalsIgnoreCase("FleetPartnersNZ")) {

				ps = (SQLServerCallableStatement) conn
						.prepareCall("{call  FLEETPARTNERSNZ.spGetVehiclesNex(?,?,?,?,?,?,?,?,?,?,?,?,?)})");
				// Output Parameter
				ps.registerOutParameter(13, java.sql.Types.INTEGER);
				ps.setString(1, "FleetPartnersNZ");
				ps.setNull(10, java.sql.Types.NVARCHAR); // AuCompanyCodes
				ps.setString(11, compCode); // Company Code 259837
			} else if (appType.equalsIgnoreCase("FleetPlusAU")) {
				ps = (SQLServerCallableStatement) conn
						.prepareCall("{call FLEETPLUSAU.spGetVehiclesNex(?,?,?,?,?,?,?,?,?,?,?,?,?)})");
				// Output Parameter
				ps.registerOutParameter(13, java.sql.Types.INTEGER);
				ps.setString(1, "FleetPlusAU");
				ps.setNull(11, java.sql.Types.NVARCHAR); // NZCompanyCodes
				ps.setString(10, compCode); // Company Code 259837
			} else if (appType.equalsIgnoreCase("FleetPlusNZ")) {
				ps = (SQLServerCallableStatement) conn
						.prepareCall("{call  FLEETPLUSNZ.spGetVehiclesNex(?,?,?,?,?,?,?,?,?,?,?,?,?)})");
				// Output Parameter
				ps.registerOutParameter(13, java.sql.Types.INTEGER);
				ps.setString(1, "FleetPlusNZ");
				ps.setNull(10, java.sql.Types.NVARCHAR); // AuCompanyCodes
				ps.setString(11, compCode); // Company Code 259837
			}

			// Search Type
			if (searchType.equalsIgnoreCase("Driver")) {

				ps.setString(9, "Driver");
			} else if (searchType.equalsIgnoreCase("Registration")) {
				ps.setString(9, "Registration");
			} else if (searchType.equalsIgnoreCase("LeaseID")) {
				ps.setString(9, "Lease ID");
			} else if (searchType.equalsIgnoreCase("Vehicle Type")) {
				ps.setString(9, "Vehicle Type");
			}

			// Parameters
			ps.setNull(2, java.sql.Types.NVARCHAR); // CompanyCode
			ps.setNull(3, java.sql.Types.NVARCHAR); // CountryCode
			ps.setStructured(4, "dbo.FilterRestrictions", stable); // Restrictions Table Parameter
			ps.setInt(5, 0); // Offset
			ps.setInt(6, 2147483647); // Limit
			ps.setNull(7, java.sql.Types.INTEGER); // LeaseID
			ps.setString(8, searchValue); // "Mazda"
			ps.setString(12, "Company.Name,Vehicle.Make,Vehicle.Model");
			// Set timeout of 5 minutes
			ps.setQueryTimeout(300);

			// Procedure execution
			ps.execute();

			rowCount = ps.getInt(13);

		} catch (SQLException ex) {
			Assertions.fail("SQL Exception:" + ex.getStackTrace());
		} finally {
			// Close the connection
			conn.close();
		}

		log.info("Exited SQL Proc Execution");
		// Return the value
		return rowCount;

	}

	public static void updateSQLQuery(String sqlQuery) throws Exception {
		Connection connection = null;
		String dbServer, dbUsername, dbPassword, dbDatabase;

		dbServer = new ResourceRead().getResourceValueFromXML().getProperty("DBServer");
		dbUsername = new ResourceRead().getResourceValueFromXML().getProperty("DBUserName");
		dbPassword = new ResourceRead().getResourceValueFromXML().getProperty("DBPassword");
		dbDatabase = new ResourceRead().getResourceValueFromXML().getProperty("DBDatabase");

		String connectionUrl = "jdbc:sqlserver://" + dbServer + ":1433;" + "database=" + dbDatabase + ";" + "user="
				+ dbUsername + ";" + "password=" + dbPassword + ";" + "encrypt=true;" + "trustServerCertificate=true;"
				+ "loginTimeout=60;";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(connectionUrl, dbUsername, dbPassword);

			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sqlQuery);

		} catch (SQLException sqlEx) {
			Assertions.fail("SQL Exception:" + sqlEx.getStackTrace());
		} finally {
			connection.close();
		}
	}
}
