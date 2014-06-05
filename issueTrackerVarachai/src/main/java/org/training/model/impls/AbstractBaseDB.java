package org.training.model.impls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

abstract public class AbstractBaseDB {
	
	static {
		new CreateTablesH2DB();
	}
	
	private InitialContext initContext;
	private DataSource ds;
	
	protected Connection getConnection() {
		try {
			initContext = new InitialContext();
			ds = (DataSource) initContext.lookup("java:comp/env/jdbc/H2db");
			return ds.getConnection();
			
//			Class.forName(ConstantsH2.H2_DRIVER);
//			return DriverManager.getConnection(ConstantsH2.H2_DB_PATH, 
//					ConstantsH2.H2_DB_USER, 
//					ConstantsH2.H2_BD_PASSWORD);
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
