package com.adviserratinguiautomation.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Assertions;

import com.adviserratinguiautomation.customexceptions.ResourceCustomException;
import com.adviserratinguiautomation.resourceRead.ResourceRead;

public class PostGreDBValidation {

	public static String executeQuery(String sqlQuery, String schema) throws ResourceCustomException, Exception {
		Connection connection = null;
		Statement stmt;
		String resultValue = "", Url = "", username = "", password = "";
		ResultSet rs;

		if (System.getProperty("Environment").equalsIgnoreCase("UAT")) {

			if (schema.equalsIgnoreCase("sentrysvc")) {
				Url = "jdbc:postgresql://35.244.119.2/ecx-sentry-svc";
				username = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBUserNameSentrySvcSchema");
				password = new ResourceRead().getResourceValueFromXML()
						.getProperty("PostGreDBPasswordUATSentrySvcSchema");
			} else if (schema.equalsIgnoreCase("nitro")) {
				Url = "jdbc:postgresql://35.244.119.2/nitro";
				username = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBUserNameNitroSchema");
				password = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBPasswordNitroSchema");
			}
		} else if (System.getProperty("Environment").equalsIgnoreCase("Dev")) {

			if (schema.equalsIgnoreCase("sentrysvc")) {
				Url = "jdbc:postgresql://35.197.169.59/ecx-sentry-svc";
				username = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBUserNameSentrySvcSchema");
				password = new ResourceRead().getResourceValueFromXML()
						.getProperty("PostGreDBPasswordDevSentrySvcSchema");
			} else if (schema.equalsIgnoreCase("nitro")) {
				Url = "jdbc:postgresql://35.197.169.59/nitro";
				username = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBUserNameNitroSchema");
				password = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBPasswordNitroSchema");
			}
		}

		try {

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(Url, username, password);

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			// rs.first();

			try {
				while (rs.next()) {
					resultValue = rs.getString(1).toString();
					return resultValue;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connection.close();
			}
		} catch (SQLException sqlEx) {
			Assertions.fail("SQL Exception:" + sqlEx.getStackTrace());
		}

		return resultValue;
	}

	public static String updateQuery(String updateQuery) throws ResourceCustomException, Exception {
		Connection connection = null;
		PreparedStatement stmt;
		String resultValue = "", Url = "", username, password;
		ResultSet rs;

		if (System.getProperty("Environment").equalsIgnoreCase("UAT")) {
			Url = "jdbc:postgresql://35.244.119.2/nitro";
		} else if (System.getProperty("Environment").equalsIgnoreCase("Dev")) {
			Url = "jdbc:postgresql://35.197.169.59/nitro";
		}

		username = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBUserNameNitroSchema");
		password = new ResourceRead().getResourceValueFromXML().getProperty("PostGreDBPasswordNitroSchema");

		try {

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(Url, username, password);

			stmt = connection.prepareStatement(updateQuery);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		return resultValue;
	}
}
