package com.lp3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VeiculoDAO {

	private final Connection connection;

	public VeiculoDAO(Connection connection) {

		this.connection = connection;
	}
	
	
	
	public int procuraVeiculoPor(String placa) {
		
		try (PreparedStatement ps = this.connection.prepareStatement("select * from veiculo where placa = ?") ) {
			
			ps.setString(1, placa);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return 1;
			}	
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return 0;
	}
	
	
}
