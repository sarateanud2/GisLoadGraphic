package com.cadastre.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cadastre.objectsget.ParameterEdit;

public class QueryToGetParams {
	
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	public QueryToGetParams() {
		super();
	}


	public static String getParamsValue(String paramName){
			
		String parametrValue = "";
		
		try {
			connection = Database.getConnectionPG();
			String sql = "select param_value from gisadm.t_param" 
					+ " where param_name = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, paramName);
			rs = stmt.executeQuery();
			while(rs.next()) {
				parametrValue = rs.getString("param_value");
			}
		} catch(SQLException ex) {
			System.out.println(ex.getCause());
        } finally {
        	try {
				Database.closeConnections(stmt, rs, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		
		return parametrValue;
	}
	
	
	
	
	

	
}
