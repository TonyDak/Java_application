package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Airport;

public class AirportDAO {
    public ArrayList<Airport> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);
        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM AIRPORT");
        ArrayList<Airport> airports = new ArrayList<>();
        while(rs.next()){
            Airport airport = new Airport();
            airport.setAirport_id(rs.getString("airport_id"));
            airport.setName(rs.getString("name"));
            airport.setCity(rs.getString("city"));
            airport.setCountry(rs.getString("country"));
            airports.add(airport);
        }
        connection.close();
        return airports;
    }
    public void add(Airport a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO AIRPORT VALUES (";
        sql += "'" + a.getAirport_id() + "',";
        sql += "'" + a.getName() + "',";
        sql += "'" + a.getCity() + "',";
        sql += "'" + a.getCountry() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(Airport a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE AIRPORT SET " ;
        sql += "airport_id='" + a.getAirport_id() + "',";
        sql += "name='" + a.getName() + "',";
        sql += "city='" + a.getCity() + "',";
        sql += "country='" + a.getCountry() + "'";
        sql += " WHERE airport_id = '" + a.getAirport_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM AIRPORT WHERE airport_id='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
}
