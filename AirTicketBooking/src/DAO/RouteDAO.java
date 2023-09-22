package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import DTO.Route;

public class RouteDAO {
    public ArrayList<Route> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM ROUTE");
        ArrayList<Route> routes = new ArrayList<>();
        while(rs.next()){
            Route route = new Route();
            route.setRoute_id(rs.getString("route_id"));
            route.setOrigin(rs.getString("origin"));
            route.setDestination(rs.getString("destination"));
            
            routes.add(route);
        }
        connection.close();
        return routes;
    }
    public void add(Route a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO ROUTE VALUES (";
        sql += "'" + a.getRoute_id() + "',";
        sql += "'" + a.getOrigin() + "',";
        sql += "'" + a.getDestination() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(Route a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE ROUTE SET " ;
        sql += "route_id='" + a.getRoute_id() + "',";
        sql += "origin='" + a.getOrigin() + "',";
        sql += "destination='" + a.getDestination() + "'";
        sql += " WHERE route_id = '" + a.getRoute_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM ROUTE WHERE route_id='" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
}
