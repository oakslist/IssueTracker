package org.training.ifaces.xmlDAO;

import java.util.Map;

import org.training.model.impls.DaoException;

public interface IResolutionsDAO {

	public Map<Integer, String> getExistResolutions() throws DaoException;
}
