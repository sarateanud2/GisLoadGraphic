package com.cadastre.execution;

import com.cadastre.objectsget.Parametres;
import com.cadastre.services.DisabledStatus;

public class Upload {
	
	public Upload() {
		super();
	}


	public void executUploadProgramm(String[] cadzoneArgs) {

		String exec = executCommandForCadzoneAndSectii(cadzoneArgs);
		System.out.println(exec);
		ExecutComandLine.executeCommand(exec);
		
	}
	
	public void runRepublica(String cadZoneArgs) {
		String exec = "java -jar C:\\Users\\dsarateanu\\.m2\\repository\\md\\cadastru\\GisLoad\\1.0-SNAPSHOT\\GisLoad-1.0-SNAPSHOT.jar \"Moldova\"";
		ExecutComandLine.executeCommand(exec);
	}
	
	private String executCommandForCadzoneAndSectii(String[] cadzoneArgs) {
		Parametres parameters = new Parametres();
		StringBuilder strCommand = new StringBuilder();
		String cadzoneArgsString = transformArgsInString(cadzoneArgs);
		
		strCommand.append("java -jar C:\\Users\\dsarateanu\\.m2\\repository\\md\\cadastru\\GisLoad\\1.0-SNAPSHOT\\GisLoad-1.0-SNAPSHOT.jar ");
		strCommand.append("\"" + cadzoneArgsString + "\" ");
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
	
	public String transformArgsInString(String[] argArray) {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < argArray.length; i++) {
			if(i != argArray.length-1) {
				str.append(argArray[i] + ",");
			} else {
				str.append(argArray[i]);
			}
		}
		return str.toString();
	}

}
