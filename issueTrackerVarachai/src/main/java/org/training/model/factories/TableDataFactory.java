package org.training.model.factories;

import org.training.ifaces.ITableDataDAO;
import org.training.model.impls.H2TableDataImpl;

public class TableDataFactory {
	
	private static H2TableDataImpl instance;
	
	public static String classesRealPath;
	
	public static ITableDataDAO getClassFromFactory() {
		return getInstance();
	}
	
	private static H2TableDataImpl getInstance() {
		if (instance == null) {
			instance = new H2TableDataImpl();
		}
		return instance;
	}

}
