package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DTO.Flight;

public class FlightDAO {
    public ArrayList<Flight> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHT");
        ArrayList<Flight> flights = new ArrayList<>();
        while(rs.next()){
            Flight flight = new Flight();
            flight.setFlight_id(rs.getString("flight_id"));
            flight.setRoute_id(rs.getString("route_id"));
            flight.setPlane_id(rs.getString("plane_id"));
            flight.setFlightSchedule_id(rs.getString("flight_schedule_id"));
            flight.setTotalSeats(rs.getInt("TotalSeats"));
            flight.setBookedECOSeats(rs.getString("bookedECOSeats"));
            flight.setBookedBUSSeats(rs.getString("bookedBUSSeats"));
            flight.setPrice_eco(rs.getString("price_eco"));
            flight.setPrice_bus(rs.getString("price_bus"));
            flights.add(flight);
        }
        connection.close();
        return flights;
    }
    public void add(Flight cb) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "insert into FLIGHT values ("
                + "'" + cb.getFlight_id() + "'"
                + ",'" + cb.getRoute_id() + "'"
                + ",'" + cb.getPlane_id() + "'"
                + ",'" + cb.getFlightSchedule_id() + "'"
                + ",'" + cb.getTotalSeats() + "'"
                + ",'" + cb.getBookedECOSeats() + "'"
                + ",'" + cb.getBookedBUSSeats() + "'"
                + ",'" + cb.getPrice_eco() + "'"
                + ",'" + cb.getPrice_bus() + "')";
        stmt.executeUpdate(qry);
        connection.close();
    }

    public void delete(String ID) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "delete from FLIGHT where flight_id = '" + ID + "'";
        stmt.executeUpdate(qry);
        connection.close();
    }

    public void set(Flight cb) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "update flight set "
        + "route_id = '" + cb.getRoute_id() + "'"
        + ",plane_id = '" + cb.getPlane_id() + "'"
        + ",flight_schedule_id = '" + cb.getFlightSchedule_id() + "'"
        + ",totalSeats = '" + cb.getTotalSeats() + "'"
        + ",bookedECOSeats = '" + cb.getBookedECOSeats() + "'"
        + ",bookedBUSSeats = '" + cb.getBookedBUSSeats() + "'"
        + ",price_eco = '" + cb.getPrice_eco() + "'"
        + ",price_bus = '" + cb.getPrice_bus() + "'"
        + " where flight_id = '" + cb.getFlight_id() + "'";
        stmt.executeUpdate(qry);
        connection.close();
    }

    public ArrayList<String> getRouteID() throws ClassNotFoundException, SQLException {
        ArrayList<String> dsID = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "select route_id, origin, destination from ROUTE";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String routeID = rs.getString("route_id");
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                dsID.add(routeID + " (" + origin + " - " + destination + ")");
            }
            rs.close();
            connection.close();;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
        }
        return dsID;
    }

    public ArrayList<String> getAirplaneID() throws ClassNotFoundException, SQLException {
        ArrayList<String> dsID = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "select plane_id, plane_model from PLANE";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String airplaneID = rs.getString("plane_id");
                String plane_model = rs.getString("plane_model");
                dsID.add(airplaneID + " (" + plane_model + ")");
            }
            rs.close();
            connection.close();;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
        }
        return dsID;
    }

    public ArrayList<String> getSeats(String ID) throws ClassNotFoundException, SQLException {
        ArrayList<String> ds = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "SELECT SUM(eco_seats + busi_seats) AS totalSeats, eco_seats, busi_seats FROM PLANE WHERE plane_id = " + "'" + ID + "'";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String totalSeats = rs.getString("totalSeats");
                ds.add(totalSeats);
                String eco_seats = rs.getString("eco_seats");
                ds.add(eco_seats);
                String busi_seats = rs.getString("busi_seats");
                ds.add(busi_seats);
            }
            rs.close();
            connection.close();;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
        }
        return ds;
    }

    public ArrayList<String> getFlightScheduleID() throws ClassNotFoundException, SQLException {
        ArrayList<String> dsID = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "select * from FLIGHTSCHEDULE";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String flightScheduleID = rs.getString("flight_schedule_id");
                String departureAirport = rs.getString("departureAirport");
                String arrivalAirport = rs.getString("arrivalAirport");
                String weekdays = rs.getString("weekdays");
                String time = rs.getString("time");
                dsID.add(flightScheduleID + " (" + departureAirport + "-" + arrivalAirport + "/" + weekdays + "/" + time + ")");
            }
            rs.close();
            connection.close();;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
        }
        return dsID;
    }
    
    public ArrayList<String> getAirportID() throws ClassNotFoundException, SQLException {
        ArrayList<String> dsID = new ArrayList<>();
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        try {
            String qry = "select airport_id, city from AIRPORT";
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String airportID = rs.getString("airport_id");
                String airportName = rs.getString("city");
                dsID.add(airportID + " ("+ airportName +")");
            }
            rs.close();
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi đọc thông tin");
        }
        return dsID;
    }
    
    public void updateAmountECO(String flightId, String seat) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "update FLIGHT set "
                + "bookedECOSeats = '" + seat + "'"
                + " where flight_id = '" + flightId + "'";
        stmt.executeUpdate(qry);
        System.out.println(qry);
    }
    
    public void updateAmountBUS(String flightId, String seat) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "update FLIGHT set "
                + "bookedBUSSeats = '" + seat + "'"
                + " where flight_id = '" + flightId + "'";
        stmt.executeUpdate(qry);
        System.out.println(qry);
    }
}
