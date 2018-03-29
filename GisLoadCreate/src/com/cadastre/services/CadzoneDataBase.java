package com.cadastre.services;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.cadastre.db.Database;
import com.cadastre.execution.ExecutComandLine;
import com.cadastre.execution.Upload;



@ManagedBean(eager=true)
@ViewScoped
public class CadzoneDataBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	private List<String> cadzoneList;
	private String[] selectedcadzone;
	private String statusMessage;
	private boolean isDisabled = DisabledStatus.isDisabled(1);
	private boolean isAcctive = true;
	
	public CadzoneDataBase() {
		getAllCadZone();
	}

	private void getAllCadZone(){
		
		cadzoneList = new ArrayList<>();
		try {
			connection = Database.getConnectionPG();
			String sql = "select cadzone from gisadm.v_path_cadzone" 
					+ " where description NOT IN('TIRASPOL', 'BENDER', 'RIBNITA') order by cadzone asc";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				cadzoneList.add(rs.getString("cadzone"));
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
		
	}
	
	public List<String> getCadZoneList(){
		return cadzoneList;
	}

	public String[] getSelectedcadzone() {
		return selectedcadzone;
	}

	public void setSelectedcadzone(String[] selectedcadzone) {
		this.selectedcadzone = selectedcadzone;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public boolean isAcctive() {
		return isAcctive;
	}

	public void setAcctive(boolean isAcctive) {
		this.isAcctive = isAcctive;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public void executUpload() {
		
		if(!isDisabled) {
			DisabledStatus.disableAll();
			isDisabled = true;
			isAcctive = true;
			
			statusMessage = "Upload Start";
			System.out.println("start");
			ExecutorService executor = null;
			if(selectedcadzone.length > 0 && selectedcadzone.length < 4) {
				executor = Executors.newFixedThreadPool(selectedcadzone.length);
			} else {
				executor = Executors.newFixedThreadPool(4);
			}
			for(String cadzone : selectedcadzone) {
//				System.out.println(cadzone);
				if(isAcctive) {
					Upload upload = new Upload(cadzone);
					executor.execute(upload);
				} else {
					break;
				}
			}

	        	executor.shutdown();
	            while (!executor.isTerminated()) {
	            }

	    	 
	    	System.out.println("end IN !!!!!!!!!!!!!!!!!!!!");
	    	DisabledStatus.enableAll();
	    	isDisabled = false;
	    	isAcctive = true;	    	
		}
		
	}
	
	public void cancelThreadExecution() {
		this.isAcctive = false;
	}
	
}
