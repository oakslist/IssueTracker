package org.training.model.hib.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training.model.beans.hibbeans.BuildFound;
import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Project;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;
import org.training.model.beans.hibbeans.User;
import org.training.model.hib.dao.impls.ifaces.ICommonDAO;
import org.training.model.hib.ifaces.ITableDataService;
import org.training.model.impls.DaoException;

@Service(value="commonService")
public class CommonService implements ITableDataService {

	
	@Autowired
	// (required=false)
	private ICommonDAO commonDAOImpl;

	@Transactional
	public boolean setStatus(Status status) {
		return commonDAOImpl.setStatus(status);
	}
	
	@Override
	@Transactional
	public boolean setType(Type type) throws DaoException {
		return commonDAOImpl.setType(type);
	}

	@Override
	@Transactional
	public boolean setResolution(Resolution resolution) throws DaoException {
		return commonDAOImpl.setResolution(resolution);
	}

	@Override
	@Transactional
	public boolean setPriority(Priority priority) throws DaoException {
		return commonDAOImpl.setPriority(priority);
	}
	
	@Override
	@Transactional
	public boolean setProject(Project project) throws DaoException {
		return commonDAOImpl.setProject(project);
	}
	
	@Transactional
	public boolean isExist(DefaultTableClass arg) {
		return commonDAOImpl.isExist(arg);
	}
	
	@Transactional
	public DefaultTableClass getRowFromTableById(DefaultTableClass arg, int id) {
		return commonDAOImpl.getRowFromTableById(arg, id);
	}
	
	/* only for Status, Resolution, Type, Priority (table with *_name) */
	@Transactional
	public Status getStatusByName(String name) {
		return commonDAOImpl.getStatusByName(name);
	}
	
	@Override
	@Transactional
	public Status getStatusById(int statusId) {
		return commonDAOImpl.getStatusById(statusId);
	}
	
	@Transactional
	public Type getTypeByName(String name) {
		return commonDAOImpl.getTypeByName(name);
	}
	
	@Override
	@Transactional
	public Type getTypeById(int typeId) {
		return commonDAOImpl.getTypeById(typeId);
	}
	
	@Transactional
	public Priority getPriorityByName(String name) {
		return commonDAOImpl.getPriorityByName(name);
	}
	
	@Override
	@Transactional
	public Priority getPriorityById(int priorityId) {
		return commonDAOImpl.getPriorityById(priorityId);
	}
	
	@Transactional
	public Resolution getResolutionByName(String name) {
		return commonDAOImpl.getResolutionByName(name);
	}
	
	@Override
	@Transactional
	public Resolution getResolutionById(int resolutionId) {
		return commonDAOImpl.getResolutionById(resolutionId);
	}
	
	@Transactional
	public Project getProjectByName(String name) {
		return commonDAOImpl.getProjectByName(name);
	}
	
	@Override
	@Transactional
	public Project getProjectById(int projectId) {
		return commonDAOImpl.getProjectById(projectId);
	}
	
	@Transactional
	public Project getProjectByNameAndBuild(String name, String buildValue) {
		return commonDAOImpl.getProjectByNameAndBuild(name, buildValue);
	}

	@Override
	@Transactional
	public List<Status> getStatuses() throws DaoException {
		return commonDAOImpl.getStatuses();
	}

	@Override
	@Transactional
	public List<Type> getTypes() throws DaoException {
		return commonDAOImpl.getTypes();
	}

	@Override
	@Transactional
	public List<Resolution> getResolutions() throws DaoException {
		return commonDAOImpl.getResolutions();
	}

	@Override
	@Transactional
	public List<Priority> getPriorities() throws DaoException {
		return commonDAOImpl.getPriorities();
	}

	@Override
	@Transactional
	public List<Project> getProjects() throws DaoException {
		return commonDAOImpl.getProjects();
	}

	@Override
	@Transactional
	public List<BuildFound> getBuildFounds() throws DaoException {
		return commonDAOImpl.getBuildFounds();
	}

	@Override
	@Transactional
	public List<User> getUsers() throws DaoException {
		return commonDAOImpl.getUsers();
	}

	@Override
	@Transactional
	public BuildFound getBuildFound(Project project, String build)
			throws DaoException {
		return commonDAOImpl.getBuildFound(project, build);

	}

	@Override
	@Transactional
	public boolean updateStatus(Status status) throws DaoException {
		return commonDAOImpl.updateStatus(status);
	}

	@Override
	public boolean updateType(Type type) throws DaoException {
		return commonDAOImpl.updateType(type);
	}

	@Override
	public boolean updateResolution(Resolution resolution) throws DaoException {
		return commonDAOImpl.updateResolution(resolution);
	}

	@Override
	public boolean updatePriority(Priority priority) throws DaoException {
		return commonDAOImpl.updatePriority(priority);
	}

	@Override
	public boolean updateProject(Project project) throws DaoException {
		return commonDAOImpl.updateProject(project);
	}

	@Override
	@Transactional
	public List<BuildFound> getBuildsByProjectId(int projectId) throws DaoException {
		return commonDAOImpl.getBuildsByProjectId(projectId);
	}

	@Override
	@Transactional
	public boolean setBuild(Project project, String buildValue)
			throws DaoException {
		return commonDAOImpl.setBuild(project, buildValue);
	}
		
}
