package com.cadastre.services;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.cadastre.db.Database;
import com.cadastre.objectsget.ParameterEdit;

@ManagedBean(name="paramsForEditSerivces")
@ViewScoped
public class ParamsForEditSerivces implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	

	public List<ParameterEdit> fillAllParamsForEdit(){
		List<ParameterEdit> allParameterList = new ArrayList<>();
		
		try {
			connection = Database.getConnectionPG();
			String sql = "select param_name, param_value, description from gisadm.t_param";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ParameterEdit param = new ParameterEdit();
				param.setParamNotation(rs.getString(1));
				param.setValue(rs.getString(2));
				param.setDescription(rs.getString(3));
				allParameterList.add(param);
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
		
		return allParameterList;
	}
	
	public void onRowEdit(String paramValue, String paramDescription, String paramName) {
		try {
			connection = Database.getConnectionPG();
			String updateParasSQL = "Update gisadm.t_param set param_value = ?, description = ? where param_name = ?";
			stmt = connection.prepareStatement(updateParasSQL);
			stmt.setString(1, paramValue);
			stmt.setString(2, paramDescription);
			stmt.setString(3, paramName);
			int numberRowUpdate = stmt.executeUpdate();
			System.out.println(numberRowUpdate);
			
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
	
			/*stmt.setString(1, ((ParameterEdit) event.getObject()).getValue());
			stmt.setString(2, ((ParameterEdit) event.getObject()).getDescription());
			stmt.setString(3, ((ParameterEdit) event.getObject()).getParamNotation());*/

}
