package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Type;

public class TypeDAO {
    public ArrayList<Type> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TYPE");
        ArrayList<Type> types = new ArrayList<>();
        while(rs.next()){
            Type type = new Type();
            type.setType_id(rs.getString("type_id"));
            type.setType_name(rs.getString("type_name"));
            type.setType_price(rs.getFloat("type_price"));
            types.add(type);
        }
        connection.close();
        return types;
    }
    public ArrayList<Type> docDBPT() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TYPE WHERE type_id LIKE 'T-ECO'");
        ArrayList<Type> types = new ArrayList<>();
        while(rs.next()){
            Type type = new Type();
            type.setType_id(rs.getString("type_id"));
            type.setType_name(rs.getString("type_name"));
            type.setType_price(rs.getFloat("type_price"));
            types.add(type);
        }
        connection.close();
        return types;
    }
    public ArrayList<Type> docDBTG() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TYPE WHERE type_id LIKE 'T-BUS'");
        ArrayList<Type> types = new ArrayList<>();
        while(rs.next()){
            Type type = new Type();
            type.setType_id(rs.getString("type_id"));
            type.setType_name(rs.getString("type_name"));
            type.setType_price(rs.getFloat("type_price"));
            types.add(type);
        }
        connection.close();
        return types;
    }
    public void add(Type tDTO) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " INSERT INTO TYPE VALUES (";
        sql += "'" + tDTO.getType_id() + "',";
        sql += "'" + tDTO.getType_name()+ "',";
        sql += "'NULL ,";
        sql += "" + tDTO.getType_price()+ ")";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }
    public void add2(Type tDTO) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " INSERT INTO TYPE VALUES (";
        sql += "'" + tDTO.getType_id() + "',";
        sql += "'" + tDTO.getType_name()+ "',";
        sql += "" + tDTO.getType_price()+ ")";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(Type type) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE TYPE SET ";
        sql += "type_id ='" + type.getType_id()+ "', ";
        sql += "type_name ='" + type.getType_name() + "', ";
        sql += "type_price =" + type.getType_price() + " ";
        sql += "WHERE type_id ='" + type.getType_id()+ "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM TYPE WHERE type_id = '" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
}
