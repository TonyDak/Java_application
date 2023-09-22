package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ServiceDTO;

public class ServiceDAO {
    public ArrayList<ServiceDTO> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SERVICE");
        ArrayList<ServiceDTO> services = new ArrayList<>();
        while(rs.next()){
            ServiceDTO service = new ServiceDTO();
            service.setName(rs.getString("name"));
            service.setDescription(rs.getString("description"));
            service.setPrice(rs.getDouble("price"));
            service.setImage(rs.getString("image"));
            services.add(service);
        }
        connection.close();
        return services;
    }
    public void add(ServiceDTO a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO SERVICE VALUES (";
        sql += "'" + a.getName() + "',";
        sql += "'" + a.getDescription() + "',";
        sql += "'" + a.getPrice() + "',";
        sql += "'" + a.getImage() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(ServiceDTO a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE SERVICE SET " ;
        sql += "name='" + a.getName() + "',";
        sql += "description='" + a.getDescription() + "',";
        sql += "price='" + a.getPrice() + "',";
        sql += "image='" + a.getImage() + "',";
        sql += " WHERE description = '" + a.getDescription() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM SERVICE WHERE description ='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
}
