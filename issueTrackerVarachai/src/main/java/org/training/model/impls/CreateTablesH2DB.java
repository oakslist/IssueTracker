package org.training.model.impls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.training.ifaces.xmlDAO.IPrioritiesDAO;
import org.training.ifaces.xmlDAO.IResolutionsDAO;
import org.training.ifaces.xmlDAO.IStatusesDAO;
import org.training.ifaces.xmlDAO.ITypesDAO;
import org.training.model.beans.nums.UserRoleEnum;
import org.training.model.factories.PriorityFactory;
import org.training.model.factories.ResolutionFactory;
import org.training.model.factories.StatusFactory;
import org.training.model.factories.TypeFactory;

public class CreateTablesH2DB extends AbstractBaseDB {

	//create all tables and default data in database
	static {
		try {
			//read all constant parameters in table
			Map<Integer, String> issueTypes = new HashMap<Integer, String>();
			Map<Integer, String> issueStatuses = new HashMap<Integer, String>();
			Map<Integer, String> issueResolutions = new HashMap<Integer, String>();
			Map<Integer, String> issuePriorities = new HashMap<Integer, String>();
			
			ITypesDAO typesDAO = TypeFactory.getClassFromFactory();
			issueTypes = typesDAO.getExistTypes();
			IStatusesDAO statusesDAO = StatusFactory.getClassFromFactory();
			issueStatuses = statusesDAO.getExistStatuses();
			
			//create all tables
			IResolutionsDAO resolutionsDAO = ResolutionFactory.getClassFromFactory();
			issueResolutions = resolutionsDAO.getExistResolutions();
			IPrioritiesDAO prioritiesDAO = PriorityFactory.getClassFromFactory();
			issuePriorities = prioritiesDAO.getExistPriorities();
			
			Class.forName(ConstantsH2.H2_DRIVER);
            Connection connection = DriverManager.getConnection(ConstantsH2.H2_DB_PATH, 
					ConstantsH2.H2_DB_USER, 
					ConstantsH2.H2_BD_PASSWORD);
            
            PreparedStatement stmt = null;
            Statement st = connection.createStatement();
            
            st.executeUpdate("DROP TABLE IF EXISTS users");
            st.executeUpdate("DROP TABLE IF EXISTS roles");
            st.executeUpdate("DROP TABLE IF EXISTS issues");
            st.executeUpdate("DROP TABLE IF EXISTS statuses");
            st.executeUpdate("DROP TABLE IF EXISTS resolutions");
            st.executeUpdate("DROP TABLE IF EXISTS types");
            st.executeUpdate("DROP TABLE IF EXISTS priorities");
            st.executeUpdate("DROP TABLE IF EXISTS projects");
            st.executeUpdate("DROP TABLE IF EXISTS builds");
            
            
            //add a roles table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS roles ("
            		+ "roleId INT AUTO_INCREMENT, "
            		+ "roleName varchar(15) NOT NULL, "
            		+ "PRIMARY KEY (roleId))");
            
            //set all role values
            stmt = connection.prepareStatement(ConstantsH2.INSERT_INTO_ROLES);
            UserRoleEnum[] userRoles;
            userRoles = UserRoleEnum.values();
            for (UserRoleEnum role : userRoles) {
            	stmt.setString(1, role.toString());
            	stmt.executeUpdate();
            }
            
            //add a users table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
            		+ "userId INT AUTO_INCREMENT, "
            		+ "firstName varchar(30) NOT NULL, "
            		+ "lastName varchar(30) NOT NULL, "
            		+ "emailAddress varchar(50) UNIQUE NOT NULL, "
            		+ "roleId INT NOT NULL, "
            		+ "password varchar(50) NOT NULL, "
            		+ "PRIMARY KEY (userId), "
            		+ "FOREIGN KEY (roleId) REFERENCES roles (roleId))");
            
            //add a statuses table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS statuses ("
            		+ "statusId INT AUTO_INCREMENT, "
            		+ "statusName varchar(15) NOT NULL, "
            		+ "PRIMARY KEY (statusId))");
            
            //set all status values
            stmt = connection.prepareStatement(ConstantsH2.INSERT_INTO_STATUSES);
            setTableData(stmt, issueStatuses);
            

            //add a types table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS types ("
            		+ "typeId INT AUTO_INCREMENT, "
            		+ "typeName varchar(15) NOT NULL, "
            		+ "PRIMARY KEY (typeId))");
            
            //set all type values
            stmt = connection.prepareStatement(ConstantsH2.INSERT_INTO_TYPES);
            setTableData(stmt, issueTypes);
            
            //add a resolutions table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS resolutions ("
            		+ "resolutionId INT AUTO_INCREMENT, "
            		+ "resolutionName varchar(15) NOT NULL, "
            		+ "PRIMARY KEY (resolutionId))");
            
            //set all resolution values
            stmt = connection.prepareStatement(ConstantsH2.INSERT_INTO_RESOLUTIONS);
            setTableData(stmt, issueResolutions);
                       
            //add a priorities table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS priorities ("
            		+ "priorityId INT AUTO_INCREMENT, "
            		+ "priorityName varchar(15) NOT NULL, "
            		+ "PRIMARY KEY (priorityId))");
            
            //set all priorities values
            stmt = connection.prepareStatement(ConstantsH2.INSERT_INTO_PRIORITIES);
            setTableData(stmt, issuePriorities);
            
            //add a builds table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS builds ("
            		+ "buildId INT AUTO_INCREMENT, "
            		+ "buildNumber varchar(15) NOT NULL DEFAULT ('1'), "
            		+ "PRIMARY KEY (buildId))");
            
            //add a projects table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS projects ("
            		+ "projectId INT AUTO_INCREMENT, "
            		+ "name varchar(30) NOT NULL, "
            		+ "description varchar(100) NOT NULL, "
            		+ "buildId INT NOT NULL, "
            		+ "managerId INT NOT NULL, "
            		+ "PRIMARY KEY (projectId), "
            		+ "FOREIGN KEY (managerId) REFERENCES users (userId), "
            		+ "FOREIGN KEY (buildId) REFERENCES builds (buildId))");
                      
            
            //add a defects table
            st.executeUpdate("CREATE TABLE IF NOT EXISTS issues "
            		+ "(issueId INT AUTO_INCREMENT, "
            		+ "summary varchar(200) NOT NULL, "
            		+ "description varchar(1000) NOT NULL, "
            		+ "statusId INT NOT NULL, "
            		+ "resolutionId INT, "
            		+ "typeId INT NOT NULL, "
            		+ "priorityId INT NOT NULL, "
            		+ "projectId INT NOT NULL, "
            		+ "buildId INT NOT NULL, "
            		+ "assignee varchar(50), "
            		+ "createDate DATETIME NOT NULL DEFAULT (NOW()), "
            		+ "createdBy INT NOT NULL, "
            		+ "modifyDate DATETIME, "
            		+ "modifiedBy INT, "
            		+ "PRIMARY KEY (issueId), "
            		+ "FOREIGN KEY (createdBy) REFERENCES users (userId), "
            		+ "FOREIGN KEY (modifiedBy) REFERENCES users (userId), "
            		+ "FOREIGN KEY (statusId) REFERENCES statuses (statusId), "
            		+ "FOREIGN KEY (resolutionId) REFERENCES resolutions (resolutionId), "
            		+ "FOREIGN KEY (typeId) REFERENCES types (typeId), "
            		+ "FOREIGN KEY (buildId) REFERENCES builds (buildId), "
            		+ "FOREIGN KEY (projectId) REFERENCES projects (projectId), "
            		+ "FOREIGN KEY (priorityId) REFERENCES priorities (priorityId))");
            
            //some default data in tables
            st.executeUpdate("INSERT INTO users (firstName, lastName, emailAddress, roleId, password) "
            		+ "VALUES ('SiarheiUser', 'Varachai', 'user', '2', 'user')");
            st.executeUpdate("INSERT INTO users (firstName, lastName, emailAddress, roleId, password) "
            		+ "VALUES ('SiarheiAdmin', 'Varachai', 'admin', '1', 'admin')");
            st.executeUpdate("INSERT INTO users (firstName, lastName, emailAddress, roleId, password) "
            		+ "VALUES ('SiarheiGuest', 'Varachai', 'guest', '3', 'guest')");            
            
            st.executeUpdate("INSERT INTO builds (buildNumber) VALUES ('1')");
            st.executeUpdate("INSERT INTO builds (buildNumber) VALUES ('2')");
            st.executeUpdate("INSERT INTO builds (buildNumber) VALUES ('3')");
            
            st.executeUpdate("INSERT INTO projects (name, description, buildId, managerId) "
            		+ "VALUES ('MyProject1', 'Some descriptions', '1', '2')");
            st.executeUpdate("INSERT INTO projects (name, description, buildId, managerId) "
            		+ "VALUES ('MyProject1', 'Some descriptions2', '2', '2')");
            st.executeUpdate("INSERT INTO projects (name, description, buildId, managerId) "
            		+ "VALUES ('MyProject2', 'Some more descriptions', '2', '3')");            
            
            st.executeUpdate("INSERT INTO issues (summary, description, statusId, "
            		+ "resolutionId, typeId, priorityId, projectId, "
            		+ "buildId, assignee, createdBy, modifiedBy, modifyDate) "
            		+ "VALUES ('summary info', 'descr info', '1', '2', '3', '3', '1', '1', 'me', '1', '2', '2014-06-04')");
            st.executeUpdate("INSERT INTO issues (summary, description, statusId, "
            		+ "resolutionId, typeId, priorityId, projectId, "
            		+ "buildId, assignee, createdBy) "
            		+ "VALUES ('summary info2', 'descr info2', '2', '3', '3', '4', '2', '2', 'me2', '1')");
            st.executeUpdate("INSERT INTO issues (summary, description, statusId, "
            		+ "resolutionId, typeId, priorityId, projectId, "
            		+ "buildId, assignee, createdBy) "
            		+ "VALUES ('summary info3', 'descr info3', '3', '2', '1', '2', '2', '1', 'me3', '2')");
            
            st.close();
            stmt.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	private static void setTableData(PreparedStatement stmt, Map<Integer, 
			String> issueMap) throws SQLException {
        if (!issueMap.isEmpty()) {
        	for (int key = 1; key <= issueMap.size(); key++) {
        		stmt.setString(1, issueMap.get(key));
        		stmt.executeUpdate();
        	}
        }
	}
	
	
}
