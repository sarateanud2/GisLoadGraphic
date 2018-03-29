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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.cadastre.db.Database;
import com.cadastre.execution.Upload;

@ManagedBean
@ViewScoped
public class TeritorialModel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;
	
	private List<String> terenuriList;
	private String[] selecteTerenuri;
	private String statusMessage;
	private boolean isDisabled = DisabledStatus.isDisabled(1);
	private boolean isAcctive = true;
	private boolean isSelectedAllCadzone;
	
	public boolean isSelectedAllCadzone() {
		return isSelectedAllCadzone;
	}

	public void setSelectedAllCadzone(boolean isSelectedAllCadzone) {
		this.isSelectedAllCadzone = isSelectedAllCadzone;
	}

	public TeritorialModel() {
		super();
		getAllTeritorii();
	}

	public List<String> getTerenuriList() {
		return terenuriList;
	}
	
	public void setTerenuriList(List<String> terenuriList) {
		this.terenuriList = terenuriList;
	}
	
	public String[] getSelecteTerenuri() {
		return selecteTerenuri;
	}
	
	public void setSelecteTerenuri(String[] selecteTerenuri) {
		this.selecteTerenuri = selecteTerenuri;
	}
	
	public void addMessageAndSetAllTeritoriiSelected() {
		if(isSelectedAllCadzone) {
			this.selecteTerenuri = terenuriList.toArray(new String[0]);
		} else {
			this.selecteTerenuri = null;
		}
		
		String message = isSelectedAllCadzone ? "Sunt selectate toate" : "Deselectate toate";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
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
	
	
	private void getAllTeritorii(){
		
		terenuriList = new ArrayList<>();
		try {
			connection = Database.getConnectionPG();
			String sql = "select DISTINCT description from gisadm.v_path_cadzone" 
					+ " where description NOT IN('TIRASPOL', 'BENDER', 'RIBNITA') order by description asc";
			stmt = connection.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				terenuriList.add(rs.getString("description"));
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
	
	public List<String> getTerenuriListAll(){
		return terenuriList;
	}
	
	public List<String> getCadZoneByTerenuri(String terenuri){
		List<String> cadzoneList = new ArrayList<>();
		try {
			connection = Database.getConnectionPG();
			String sql = "select cadzone from gisadm.v_path_cadzone" 
					+ " where description = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, terenuri);
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
		
		return cadzoneList;
	}
	
	public List<String> getAllCadZonesFromAllZoneTeritoriale(){
		List<String> allCadZonesList = new ArrayList<>();
		for(String terenuri : selecteTerenuri) {
			allCadZonesList.addAll(getCadZoneByTerenuri(terenuri));
		}
		
		return allCadZonesList;
	}
	
	public void executUpload() {
		
		if(!isDisabled) {
			DisabledStatus.disableAll();
			isDisabled = true;
			statusMessage = "Upload Start";
			System.out.println("Start");
			ExecutorService executor = null;
			List<String> allCadzone = getAllCadZonesFromAllZoneTeritoriale();
			int allCadZoneSize = allCadzone.size();
			if(allCadZoneSize > 0 && allCadZoneSize < 4) {
				executor = Executors.newFixedThreadPool(allCadZoneSize);
			} else {
				executor = Executors.newFixedThreadPool(4);
			}
			
			for(String cadzone : allCadzone) {
				if(isAcctive) {
					Upload upload = new Upload(cadzone);
					executor.submit(upload);
				} else {
					break;
				}
			}
	    	
	    	try {
	        	executor.shutdown();
	        	executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
	        		        	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	
	    	System.out.println("end IN !!!!!!!!!!!!!!!!!!!!");
	    	DisabledStatus.enableAll();
	    	isDisabled = false;
	    	isAcctive = true;
	    		    	
		}
	}
	
	public void stopThreadExecutiion() {
		this.isAcctive = false;
	}
	

	

}
