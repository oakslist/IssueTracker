package org.training.model.factories.hib;

import org.training.ifaces.hib.ITableDataDAOHib;
import org.training.model.hib.impls.CommonService;

public class TableDataFactoryHib {

	private static CommonService instance;

	public static String classesRealPath;

	public static ITableDataDAOHib getClassFromFactory() {
		return getInstance();
	}

	private static CommonService getInstance() {
		if (instance == null) {
			instance = new CommonService();
		}
		return instance;
	}

}
