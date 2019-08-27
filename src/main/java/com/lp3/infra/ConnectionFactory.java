package com.lp3.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnectionMySQL() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/portaria?useSSL=false",
					                         "root", "");
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
}


