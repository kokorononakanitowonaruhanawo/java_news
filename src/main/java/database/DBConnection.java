package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import settings.DBSettings;

public class DBConnection {


	private Connection connection = null;
	
	public DBConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DBSettings.DRIVER_NAME);
		
		this.connection = DriverManager.getConnection(DBSettings.JDBC_URL, DBSettings.DB_USER, DBSettings.DB_PASS);
			
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
