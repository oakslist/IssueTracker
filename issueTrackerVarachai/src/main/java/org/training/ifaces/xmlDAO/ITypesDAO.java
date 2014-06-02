package org.training.ifaces.xmlDAO;

import java.util.Map;

import org.training.model.impls.DaoException;

public interface ITypesDAO {

	public Map<Integer, String> getExistTypes() throws DaoException;
	
}
