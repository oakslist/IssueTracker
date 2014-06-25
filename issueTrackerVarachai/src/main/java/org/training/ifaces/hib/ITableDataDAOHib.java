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
	public List<User> getUsers() throws DaoException;
	
	public boolean setStatus(Status status) throws DaoException;
	public boolean setType(Type type) throws DaoException;
	public boolean setResolution(Resolution resolution) throws DaoException;
	public boolean setPriority(Priority priority) throws DaoException;
	public boolean setProject(Project project) throws DaoException;
	
	public Status getStatusById(int statusId) throws DaoException;
	public Type getTypeById(int typeId) throws DaoException;
	public Resolution getResolutionById(int resolutionId) throws DaoException;
	public Priority getPriorityById(int priorityId) throws DaoException;
	public Project getProjectById(int projectsId) throws DaoException;
	
	public boolean updateStatus(Status status) throws DaoException;
	public boolean updateType(Type type) throws DaoException;
	public boolean updateResolution(Resolution resolution) throws DaoException;
	public boolean updatePriority(Priority priority) throws DaoException;
	public boolean updateProject(Project project) throws DaoException;
		
}
