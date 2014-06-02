package org.training.ifaces;

import java.util.Map;

import org.training.model.beans.Project;
import org.training.model.impls.DaoException;

public interface ITableDataDAO {
	public Map<Integer, String> getStatuses() throws DaoException;
	public Map<Integer, String> getTypes() throws DaoException;
	public Map<Integer, String> getResolutions() throws DaoException;
	public Map<Integer, String> getPriorities() throws DaoException;
	public Map<Integer, Project> getProjects() throws DaoException;
	public Map<Integer, String> getBuildFound(String projectNumber) throws DaoException;
	public Map<Integer, String> getBuildFound() throws DaoException;
	public Map<Integer, String> getAssignee() throws DaoException;

}
