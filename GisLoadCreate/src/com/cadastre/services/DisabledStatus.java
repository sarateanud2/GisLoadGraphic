package com.cadastre.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cadastre.db.Database;

public class DisabledStatus {
	
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	public static boolean isDisabled(int id) {
		
		boolean isDesabled = false;
		try {
			connection = Database.getConnectionPG();
			String sql = "SELECT disabled FROM gisadm.disabled_status where id = 1";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				isDesabled = rs.getBoolean("disabled");
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
		
		return isDesabled;
	}
	
	public static void disableAll() {
		
		try {
			
			connection = Database.getConnectionPG();
			String sql = "update gisadm.disabled_status set disabled = true";
			stmt = connection.prepareStatement(sql);
			stmt.executeQuery();
			
		} catch(SQLException ex) {
			System.out.println(ex.getCause());
        } finally {
        	try {
				Database.closeConnections(stmt, rs, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	public static void enableAll() {
		
		try {
			
			connection = Database.getConnectionPG();
			String sql = "update gisadm.disabled_status set disabled = false";
			stmt = connection.prepareStatement(sql);
			stmt.executeQuery();
			
		} catch(SQLException ex) {
			System.out.println(ex.getCause());
        } finally {
        	try {
				Database.closeConnections(stmt, rs, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	

}
