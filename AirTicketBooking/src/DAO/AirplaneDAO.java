package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Airplane;

public class AirplaneDAO {
    public ArrayList<Airplane> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);
        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM AIRPLANE");
        ArrayList<Airplane> airplanes = new ArrayList<>();
        while(rs.next()){
            Airplane airplane = new Airplane();
            airplane.setAirplane_id(rs.getString("airplane_id"));
            airplane.setName(rs.getString("name"));
            airplane.setCountry(rs.getString("country"));
            airplanes.add(airplane);
        }
        connection.close();
        return airplanes;
    }
    public void add(Airplane a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO AIRPLANE VALUES (";
        sql += "'" + a.getAirplane_id() + "',";
        sql += "'" + a.getName() + "',";
        sql += "'" + a.getCountry() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(Airplane a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE AIRPLANE SET " ;
        sql += "airplane_id='" + a.getAirplane_id() + "',";
        sql += "name='" + a.getName() + "',";
        sql += "country='" + a.getCountry() + "'";
        sql += " WHERE Airplane_id = '" + a.getAirplane_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM AIRPLANE WHERE airport_id='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
}
