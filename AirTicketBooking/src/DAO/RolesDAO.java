package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.Roles;
public class RolesDAO {
    public ArrayList<Roles> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ROLES");
        ArrayList<Roles> roles = new ArrayList<>();
        while(rs.next()){
            Roles role = new Roles();
            role.setRoles_id(rs.getString("role_id"));
            role.setName(rs.getString("name"));
            role.setDescription(rs.getString("description"));
            
            roles.add(role);
        }
        connection.close();
        return roles;
    }
    public void add(Roles a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO ROLES VALUES (";
        sql += "'" + a.getRoles_id() + "',";
        sql += "'" + a.getName()+ "',";
        sql += "'" + a.getDescription() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public void set(Roles a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE ROLES SET " ;
        sql += "role_id='" + a.getRoles_id() + "',";
        sql += "name='" + a.getName() + "',";
        sql += "description='" + a.getDescription() + "'";
        sql += " WHERE role_id = '" + a.getRoles_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM ROLES WHERE role_id='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
    
    public String nameroles(int id) throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " SELECT name FROM ROLES WHERE role_id='" + id + "'";
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(sql);
        if(rs.next())
        { return rs.getString("name");

        }
        return "" ;
    }


    
}
