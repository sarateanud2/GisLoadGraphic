package com.cadastre.execution;

import com.cadastre.objectsget.Parametres;
import com.cadastre.services.DisabledStatus;

public class Upload implements Runnable{
	
	private String cadzone;

	public Upload() {
		super();
	}

	public Upload(String cadzone) {
		super();
		this.cadzone = cadzone;
	}

	public String getCadzone() {
		return cadzone;
	}

	public void setCadzone(String cadzone) {
		this.cadzone = cadzone;
	}

	@Override
	public void run() {
		
		System.out.println("Running ... " + Thread.currentThread().getName());
		String exec = executCommandForCadzoneAndSectii();
		System.out.println(exec);
		ExecutComandLine.executeCommand(exec);
		System.out.println("Finished ... " + Thread.currentThread().getName());
		
		
	}
	
	public void runRepublica(String cadZone) {
		String exec = "java -jar C:\\Users\\dsarateanu\\.m2\\repository\\md\\cadastru\\GisLoad\\1.0-SNAPSHOT\\GisLoad-1.0-SNAPSHOT.jar \"Moldova\"";
		ExecutComandLine.executeCommand(exec);
	}
	
	public String executCommandForCadzoneAndSectii() {
		Parametres parameters = new Parametres();
		StringBuilder strCommand = new StringBuilder();
		
		strCommand.append("java -jar C:\\Users\\dsarateanu\\.m2\\repository\\md\\cadastru\\GisLoad\\1.0-SNAPSHOT\\GisLoad-1.0-SNAPSHOT.jar ");
		strCommand.append("\"" + cadzone + "\" ");
		strCommand.append("\"" + parameters.getUserorcl() + "\" ");
		strCommand.append("\"" + parameters.getPwdorcl() + "\" ");
		strCommand.append("\"" + parameters.getTemp_path() + "\\\" ");
		strCommand.append("\"" + parameters.getGispath() + "\\\" ");
		strCommand.append("\"" + parameters.getUserpg() + "\" ");
		strCommand.append("\"" + parameters.getPwdpg() + "\" ");
		strCommand.append("\"" + parameters.getMax_processes() + "\" ");
		strCommand.append("\"" + parameters.getImport_tab_to_pg() + "\" ");
		strCommand.append("\"" + parameters.getCladiri_table_oracle() + "\" ");
		strCommand.append("\"" + parameters.getTerenuri_table_oracle() + "\" ");
		strCommand.append("\"" + parameters.getOracleFMWSchemaName() + "\" ");
		strCommand.append("\"" + parameters.getOracleFMWAddress() + "\" ");
		strCommand.append("\"" + parameters.getOracleFMWName() + "\" ");
		strCommand.append("\"" + parameters.getPostgresFMWAddress() + "\" ");
		strCommand.append("\"" + parameters.getPostgresFMWName() + "\"");

		
		return strCommand.toString();
	}

}
