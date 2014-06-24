package org.training.ifaces.hib;

import java.util.List;

import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.impls.DaoException;

public interface ITableDataDAOHib {

	public List<Status> getStatuses() throws DaoException;
	public List<Type> getTypes() throws DaoException;
	public List<Resolution> getResolutions() throws DaoException;
	public List<Priority> getPriorities() throws DaoException;
	public List<Project> getProjects() throws DaoException;
	public BuildFound getBuildFound(Project project, String build) throws DaoException;
	public List<BuildFound> getBuildFounds() throws DaoException;
	public List<User> getAssignee() throws DaoException;
	
}
