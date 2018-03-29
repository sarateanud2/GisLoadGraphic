package com.cadastre.objectsget;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.cadastre.db.Database;
import com.cadastre.db.QueryToGetParams;

public class Parametres {
	
	private static PreparedStatement stmt = null;
	private static ResultSet rs = null;
	private static Connection connection = null;

	private String pgdb;
    private String userpg;
    private String pwdpg;
    private String orcldb;
    private String userorcl;
    private String pwdorcl;
    private String gispath;
    private String import_tab_to_pg;
    private String terenuri_table_oracle;
    private String import_orcl_conn;
    private String cladiri_table_oracle;
    private String max_processes;
    private String temp_path;
    private String import_to_oracle;
    private String local_gispath;
    private String oracleFMWAddress;
    private String oracleFMWName;
    private String oracleFMWSchemaName;
    private String postgresFMWAddress;
    private String postgresFMWName;
    
    
    public Parametres() {
    	this.userpg = QueryToGetParams.getParamsValue("userpg");
    	this.pwdpg = QueryToGetParams.getParamsValue("pwdpg");
    	this.userorcl = QueryToGetParams.getParamsValue("userorcl");
    	this.pwdorcl = QueryToGetParams.getParamsValue("pwdorcl");
    	this.gispath = QueryToGetParams.getParamsValue("gispath");
    	this.import_tab_to_pg = QueryToGetParams.getParamsValue("import_tab_to_pg");
    	this.terenuri_table_oracle = QueryToGetParams.getParamsValue("terenuri_table_oracle");
    	this.cladiri_table_oracle = QueryToGetParams.getParamsValue("cladiri_table_oracle");
    	this.max_processes = QueryToGetParams.getParamsValue("max_processes");
    	this.temp_path = QueryToGetParams.getParamsValue("temp_path");
    	this.oracleFMWAddress = QueryToGetParams.getParamsValue("oracleFMWAddress");
    	this.oracleFMWName = QueryToGetParams.getParamsValue("oracleFMWName");
    	this.oracleFMWSchemaName = QueryToGetParams.getParamsValue("oracleFMWSchemaName");
    	this.postgresFMWAddress = QueryToGetParams.getParamsValue("postgresFMWAddress");
    	this.postgresFMWName = QueryToGetParams.getParamsValue("postgresFMWName");
    }

	public String getPgdb() {
		return pgdb;
	}

	public void setPgdb(String pgdb) {
		this.pgdb = pgdb;
	}

	public String getUserpg() {
		return userpg;
	}

	public void setUserpg(String userpg) {
		this.userpg = userpg;
	}

	public String getPwdpg() {
		return pwdpg;
	}

	public void setPwdpg(String pwdpg) {
		this.pwdpg = pwdpg;
	}

	public String getOrcldb() {
		return orcldb;
	}

	public void setOrcldb(String orcldb) {
		this.orcldb = orcldb;
	}

	public String getUserorcl() {
		return userorcl;
	}

	public void setUserorcl(String userorcl) {
		this.userorcl = userorcl;
	}

	public String getPwdorcl() {
		return pwdorcl;
	}

	public void setPwdorcl(String pwdorcl) {
		this.pwdorcl = pwdorcl;
	}

	public String getGispath() {
		return gispath;
	}

	public void setGispath(String gispath) {
		this.gispath = gispath;
	}

	public String getImport_tab_to_pg() {
		return import_tab_to_pg;
	}

	public void setImport_tab_to_pg(String import_tab_to_pg) {
		this.import_tab_to_pg = import_tab_to_pg;
	}

	public String getTerenuri_table_oracle() {
		return terenuri_table_oracle;
	}

	public void setTerenuri_table_oracle(String terenuri_table_oracle) {
		this.terenuri_table_oracle = terenuri_table_oracle;
	}

	public String getImport_orcl_conn() {
		return import_orcl_conn;
	}

	public void setImport_orcl_conn(String import_orcl_conn) {
		this.import_orcl_conn = import_orcl_conn;
	}

	public String getCladiri_table_oracle() {
		return cladiri_table_oracle;
	}

	public void setCladiri_table_oracle(String cladiri_table_oracle) {
		this.cladiri_table_oracle = cladiri_table_oracle;
	}

	public String getMax_processes() {
		return max_processes;
	}

	public void setMax_processes(String max_processes) {
		this.max_processes = max_processes;
	}

	public String getTemp_path() {
		return temp_path;
	}

	public void setTemp_path(String temp_path) {
		this.temp_path = temp_path;
	}

	public String getImport_to_oracle() {
		return import_to_oracle;
	}

	public void setImport_to_oracle(String import_to_oracle) {
		this.import_to_oracle = import_to_oracle;
	}

	public String getLocal_gispath() {
		return local_gispath;
	}

	public void setLocal_gispath(String local_gispath) {
		this.local_gispath = local_gispath;
	}

	public String getOracleFMWAddress() {
		return oracleFMWAddress;
	}

	public void setOracleFMWAddress(String oracleFMWAddress) {
		this.oracleFMWAddress = oracleFMWAddress;
	}

	public String getOracleFMWName() {
		return oracleFMWName;
	}

	public void setOracleFMWName(String oracleFMWName) {
		this.oracleFMWName = oracleFMWName;
	}

	public String getOracleFMWSchemaName() {
		return oracleFMWSchemaName;
	}

	public void setOracleFMWSchemaName(String oracleFMWSchemaName) {
		this.oracleFMWSchemaName = oracleFMWSchemaName;
	}

	public String getPostgresFMWAddress() {
		return postgresFMWAddress;
	}

	public void setPostgresFMWAddress(String postgresFMWAddress) {
		this.postgresFMWAddress = postgresFMWAddress;
	}

	public String getPostgresFMWName() {
		return postgresFMWName;
	}

	public void setPostgresFMWName(String postgresFMWName) {
		this.postgresFMWName = postgresFMWName;
	}
	
}
