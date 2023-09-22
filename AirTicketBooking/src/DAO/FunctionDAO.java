package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Funtions;

public class FunctionDAO {
    public ArrayList<Funtions> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM FUNTIONS");
        ArrayList<Funtions> functions = new ArrayList<>();
        while (rs.next()) {
            Funtions function = new Funtions();
            function.setFuntion_id(rs.getString("function_id"));
            function.setName(rs.getString("name"));
            function.setDescription(rs.getString("decripition"));
            functions.add(function);
        }
        connection.close();
        return functions;
    }

    public void add(Funtions func) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "insert into FUNTIONS values(";
        sql += "'" + func.getFuntion_id() + ",";
        sql += "'" + func.getName() + ",";
        sql += "'" + func.getDescription() + ")";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);

    }

    public void set(Funtions func) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "UPDATE FUNTIONS SET ";
        sql += "function_id='" + func.getFuntion_id() + "',";
        sql += "name='" + func.getName() + "',";
        sql += "decripition='" + func.getDescription() + "',";
        sql += " WHERE function_id = '" + func.getFuntion_id() + "'";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void delete(int funcID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " DELETE FROM FUNTIONS WHERE function_id='" + funcID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);

    }
}
