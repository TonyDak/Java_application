package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Chair;

public class ChairDAO {
    public ArrayList<Chair> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM CHAIR");
        ArrayList<Chair> chairs = new ArrayList<>();
        while(rs.next()){
            Chair chair = new Chair();
            chair.setFlight_id(rs.getString("flight_id"));
            chair.setType_chair(rs.getString("type_chair"));
            chair.setChair_number(rs.getString("chair_number"));
            chair.setStatus(rs.getInt("status"));
            chairs.add(chair);
        }
        connection.close();
        return chairs;
    }
    public void add(Chair tDTO) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " INSERT INTO CHAIR VALUES (";
        sql += "'" + tDTO.getFlight_id() + "',";
        sql += "'" + tDTO.getType_chair()+ "',";
        sql += "" + tDTO.getChair_number()+ ",";
        sql += "" + tDTO.getStatus()+ ")";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public void set(Chair type) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE CHAIR SET ";
        sql += "flight_id ='" + type.getFlight_id()+ "', ";
        sql += "type_chair ='" + type.getType_chair() + "', ";
        sql += "chair_number =" + type.getChair_number() + ", ";
        sql += "status =" + type.getStatus() + " ";
        sql += "WHERE chair_number ='" + type.getChair_number()+ "' and flight_id ='"+type.getFlight_id()+"' and type_chair='"+ type.getType_chair()+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
    public void set_chair(String id, String type, String chair) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE CHAIR SET ";
        sql += "status = 0 ";
        sql += "WHERE chair_number ='" + chair+ "' and flight_id ='"+id+"' and type_chair='"+ type+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
    public void set_chair_1(String id, String type, String chair) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE CHAIR SET ";
        sql += "status = 1 ";
        sql += "WHERE chair_number ='" + chair+ "' and flight_id ='"+id+"' and type_chair='"+ type+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
    public void set_flight(String id) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE CHAIR SET ";
        sql += "flight_id ='" + id + "' ";
        sql += "WHERE flight_id ='"+id+"'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
    public void delete(String ID) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM CHAIR WHERE flight_id = '" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
}
