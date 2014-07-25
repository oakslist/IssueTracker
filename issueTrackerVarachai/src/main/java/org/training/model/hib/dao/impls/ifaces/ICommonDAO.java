package org.training.model.hib.dao.impls.ifaces;

import java.util.List;

import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.impls.DefaultTableClass;
import org.training.model.impls.DaoException;

public interface ICommonDAO {
	
	public boolean setStatus(Status status);

	public boolean setType(Type type) throws DaoException;

	public boolean setResolution(Resolution resolution) throws DaoException;

	public boolean setPriority(Priority priority) throws DaoException;

	public boolean setProject(Project project) throws DaoException;

	public boolean isExist(DefaultTableClass arg);

	public DefaultTableClass getRowFromTableById(DefaultTableClass arg, int id);

	/* only for Status, Resolution, Type, Priority (table with *_name) */
	public Status getStatusByName(String name);

	public Status getStatusById(int statusId);

	public Type getTypeByName(String name);

	public Type getTypeById(int typeId);

	public Priority getPriorityByName(String name);

	public Priority getPriorityById(int priorityId);

	public Resolution getResolutionByName(String name);

	public Resolution getResolutionById(int resolutionId);

	public Project getProjectByName(String name);

	public Project getProjectById(int projectId);

	public Project getProjectByNameAndBuild(String name, String buildValue);

	public List<Status> getStatuses() throws DaoException;

	public List<Type> getTypes() throws DaoException;

	public List<Resolution> getResolutions() throws DaoException;

	public List<Priority> getPriorities() throws DaoException;

	public List<Project> getProjects() throws DaoException;

	public List<BuildFound> getBuildFounds() throws DaoException;

	public List<User> getUsers() throws DaoException;

	public BuildFound getBuildFound(Project project, String build)
			throws DaoException;

	public boolean updateStatus(Status status) throws DaoException;

	public boolean updateType(Type type) throws DaoException;

	public boolean updateResolution(Resolution resolution) throws DaoException;

	public boolean updatePriority(Priority priority) throws DaoException;

	public boolean updateProject(Project project) throws DaoException;

	public List<BuildFound> getBuildsByProjectId(int projectId)
			throws DaoException;

	public boolean setBuild(Project project, String buildValue)
			throws DaoException;

}
