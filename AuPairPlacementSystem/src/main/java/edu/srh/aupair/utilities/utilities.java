package edu.srh.aupair.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class utilities {

	public static void main(String[] args) {
		
		try {
			getConnectionString();
		} catch (SQLException e) {
						e.printStackTrace();
		}

	}

	public static Connection getConnectionString() throws SQLException 
	{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AU_PAIR_MANAGEMENT?useSSL=false", "root",
			"oot56");
				
		return conn;
	}
	
	
}
