package org.training.ifaces.xmlDAO;

import java.util.Map;

import org.training.model.impls.DaoException;

public interface IPrioritiesDAO {
	
	public Map<Integer, String> getExistPriorities() throws DaoException;

}
