package org.training.ifaces.xmlDAO;

import java.util.Map;

import org.training.model.impls.DaoException;

public interface IStatusesDAO {
	
	public Map<Integer, String> getExistStatuses() throws DaoException;

}
