package com.cadastre.services.interfaces;

import java.util.List;

public interface TeritorialServiceInterfaces {
	List<String> getAllTeritorialOffices();
	List<String> getCadZoneByTerenuri(String terenuri);
	List<String> getAllCadZonesFromAllZoneTeritoriale(String[] selecteTerenuri);
	void executUpload(String[] selecteTerenuri);
}
