package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	static private final String jdbcUrl = "jdbc:mysql://localhost/porto2015?user=root";
	static private Connection connection = null;

	public static Connection getConnection() {

		try {
			connection = DriverManager.getConnection(jdbcUrl);
			return connection;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Cannot get connection " + jdbcUrl, e);
		}
	}

}
