package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Plane;


public class PlaneDAO {
    public ArrayList<Plane> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PLANE");
        ArrayList<Plane> planes = new ArrayList<>();
        while(rs.next()){
            Plane plane = new Plane();
            plane.setPlane_id(rs.getString("plane_id"));
            plane.setPlane_model(rs.getString("plane_model"));
            plane.setEco_seats(rs.getInt("eco_seats"));
            plane.setBusi_seats(rs.getInt("busi_seats"));
            plane.setAirplane_id(rs.getString("airplane_id"));

            planes.add(plane);
        }
        connection.close();
        return planes;
    }
    public void add(Plane a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO PLANE VALUES (";
        sql += "'" + a.getPlane_id() + "',";
        sql += "'" + a.getPlane_model() + "',";
        sql += "'" + a.getEco_seats() + "',";
        sql += "'" + a.getBusi_seats() + "',";
        sql += "'" + a.getAirplane_id() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(Plane a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE PLANE SET " ;
        sql += "plane_id='" + a.getPlane_id() + "',";
        sql += "plane_model='" + a.getPlane_model() + "',";
        sql += "eco_seats='" + a.getEco_seats() + "',";
        sql += "busi_seats='" + a.getBusi_seats() + "',";
        sql += "airplane_id='" + a.getAirplane_id() + "'";
        sql += " WHERE plane_id = '" + a.getPlane_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM PLANE WHERE plane_id='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }

}
