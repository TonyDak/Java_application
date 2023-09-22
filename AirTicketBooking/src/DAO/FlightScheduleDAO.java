package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.FlightSchedule;

public class FlightScheduleDAO {
    public ArrayList<FlightSchedule> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHTSCHEDULE");
        ArrayList<FlightSchedule> flightSchedules = new ArrayList<>();
        while(rs.next()){
            FlightSchedule flightSchedule = new FlightSchedule();
            flightSchedule.setFlight_schedule_id( rs.getString("flight_schedule_id"));
            flightSchedule.setDepartureAirport(rs.getString("departureAirport"));
            flightSchedule.setArrivalAirport(rs.getString("arrivalAirport"));
            flightSchedule.setDepartureDate(rs.getString("departureDate"));
            flightSchedule.setArrivalDate(rs.getString("arrivalDate"));
            flightSchedule.setBoartime(rs.getString("boartime"));
            flightSchedules.add(flightSchedule);
        }
        connection.close();
        return flightSchedules;
    }
    public void add(FlightSchedule a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO FLIGHTSCHEDULE VALUES (";
        sql += "'" + a.getFlight_schedule_id() + "',";
        sql += "'" + a.getDepartureAirport() + "',";
        sql += "'" + a.getArrivalAirport() + "',";
        sql += "'" + a.getDepartureDate() + "',";
        sql += "'" + a.getArrivalDate() + "',";
        sql += "'" + a.getBoartime() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(FlightSchedule a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE FLIGHTSCHEDULE SET " ;
        sql += "flight_schedule_id='" + a.getFlight_schedule_id() + "',";
        sql += "departureAirport='" + a.getDepartureAirport() + "',";
        sql += "arrivalAirport='" + a.getArrivalAirport() + "',";
        sql += "departureDate='" + a.getDepartureDate() + "',";
        sql += "arrivalDate='" + a.getArrivalDate() + "',";
        sql += "boartime='" + a.getBoartime() + "'";
        sql += " WHERE flight_schedule_id = '" + a.getFlight_schedule_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM FLIGHTSCHEDULE WHERE flight_schedule_id='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
}
