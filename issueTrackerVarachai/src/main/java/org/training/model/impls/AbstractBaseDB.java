package org.training.model.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class AbstractBaseDB {
	
	static {
		new CreateTablesH2DB();
	}
	
	protected Connection getConnection() {
		try {
			Class.forName(ConstantsH2.H2_DRIVER);
			return DriverManager.getConnection(ConstantsH2.H2_DB_PATH, 
					ConstantsH2.H2_DB_USER, 
					ConstantsH2.H2_BD_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void closeConnection(Connection connection, Statement statement, 
			ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
            System.err.println(ConstantsH2.H2_ERROR_CLOSE_CONNECTION + e);
		}
	}
	
	protected void closeConnection(Connection connection, Statement statement1, 
			Statement statement2, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement1 != null) {
				statement1.close();
			}
			if (statement2 != null) {
				statement2.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
            System.err.println(ConstantsH2.H2_ERROR_CLOSE_CONNECTION + e);
		}
	}
}
