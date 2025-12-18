package models.data_handling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	
	private static Connection conn;
	
	//Returns connection.


	public static Connection getConnection() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/joymarket", "root", "");
			} catch (SQLException e) {
				System.out.println("Failed in connecting to database.");
				e.printStackTrace();
			}
		}
		return conn;
	}

}