import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.training.model.impls.ConstantsH2;


public class CheckDB {

	/**
     * @param args
     */
    public static void main(String[] args) {
    	try {
			Class.forName(ConstantsH2.H2_DRIVER);
            Connection con = DriverManager.getConnection(ConstantsH2.H2_DB_PATH, 
					ConstantsH2.H2_DB_USER, 
					ConstantsH2.H2_BD_PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.executeUpdate("DROP TABLE IF EXISTS defects");

            //add a users table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
            		+ "userId INT AUTO_INCREMENT, "
            		+ "firstName varchar(30) NOT NULL, "
            		+ "lastName varchar(30) NOT NULL, "
            		+ "emailAddress varchar(50) UNIQUE NOT NULL, "
            		+ "role varchar(13) NOT NULL CHECK (role IN ('GUEST', 'USER', 'ADMINISTRATOR')),"
            		+ "password varchar(50) NOT NULL, "
            		+ "PRIMARY KEY (userId))");
            
          
           

            //add a defects table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS defects "
            		+ "(defectId INT AUTO_INCREMENT, "
            		+ "summary varchar(200) NOT NULL, "
            		+ "description varchar(1000) NOT NULL, "
            		+ "status varchar(11) NOT NULL CHECK (status IN ('New', 'Assigned', 'In Progress', 'Resolved', 'Closed', 'Reopened')), "
            		+ "resolution varchar(10) CHECK (resolution IN ('Fixed', 'Invalid', 'Wontfix', 'Worksforme')), "
            		+ "type varchar(10) NOT NULL CHECK (type IN ('Cosmetic', 'Bug', 'Feature', 'Performance')), "
            		+ "priority varchar(9) NOT NULL CHECK (priority IN ('Critical', 'Major', 'Important', 'Minor')), "
            		+ "project varchar(50) NOT NULL DEFAULT ('some project'), "
            		+ "buildFound varchar(50) NOT NULL DEFAULT ('some project build'), "
            		+ "assignee varchar(50), "
            		+ "createDate DATETIME NOT NULL DEFAULT (NOW() ), "
            		+ "createdBy INT NOT NULL, "
            		+ "modifyDate DATETIME NOT NULL DEFAULT (NOW()), "
            		+ "modifiedBy INT NOT NULL, "
            		+ "PRIMARY KEY (defectId), "
            		+ "FOREIGN KEY (createdBy) REFERENCES users (userId), "
            		+ "FOREIGN KEY (modifiedBy) REFERENCES users (userId))");
            
            
            stmt.executeUpdate("INSERT INTO users (firstName, lastName, emailAddress, role, password) "
            		+ "VALUES ('Siarhei', 'Varachai', 'siarhei@varachai.com', 'ADMINISTRATOR', '111')");
            stmt.executeUpdate("INSERT INTO users (firstName, lastName, emailAddress, role, password) "
            		+ "VALUES ('Siarhei2', 'Varachai2', 'siarhei@varachai.com2', 'USER', '11122')");
            
            stmt.executeUpdate("INSERT INTO defects (summary, description, status, resolution, type, priority, assignee, createdBy, modifiedBy) "
            		+ "VALUES ('summary info', 'descr info', 'Closed', 'Invalid', 'Bug', 'Critical', 'me', '1', '1')");
            stmt.executeUpdate("INSERT INTO defects (summary, description, status, resolution, type, priority, assignee, createdBy, modifiedBy) "
            		+ "VALUES ('summary info2', 'descr info2', 'Resolved', 'Fixed', 'Feature', 'Major', 'me2', '2', '1')");
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while( rs.next() )
            {
                String name = rs.getString("userId");
                System.out.print(name + " ");
                name = rs.getString("firstName");
                System.out.print(name + " ");
                name = rs.getString("lastName");
                System.out.print(name + " ");
                name = rs.getString("emailAddress");
                System.out.print(name + " ");
                name = rs.getString("role");
                System.out.print(name + " ");
                name = rs.getString("password");
                System.out.println(name + " ");
            }
            
            rs = stmt.executeQuery("SELECT * FROM defects");
            while( rs.next() )
            {
                String name = rs.getString("defectId");
                System.out.print(name + "; ");
                name = rs.getString("summary");
                System.out.print(name + "; ");
                name = rs.getString("description");
                System.out.print(name + "; ");
                name = rs.getString("status");
                System.out.print(name + "; ");
                name = rs.getString("resolution");
                System.out.print(name + "; ");
                name = rs.getString("type");
                System.out.print(name + "; ");
                name = rs.getString("priority");
                System.out.print(name + "; ");
                name = rs.getString("project");
                System.out.print(name + "; ");
                name = rs.getString("buildFound");
                System.out.print(name + "; ");
                name = rs.getString("assignee");
                System.out.print(name + "; ");
                name = rs.getString("createDate");
                System.out.print(name + "; ");
                name = rs.getString("createdBy");
                System.out.print(name + "; ");
                name = rs.getString("modifyDate");
                System.out.print(name + "; ");
                name = rs.getString("modifiedBy");
                System.out.println(name + "; ");
                
                
            }
            stmt.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	
}
