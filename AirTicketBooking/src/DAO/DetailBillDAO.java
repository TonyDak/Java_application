package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.DetailBill;

public class DetailBillDAO {
    public ArrayList<DetailBill> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DETAILBILL");
        ArrayList<DetailBill> detailBills = new ArrayList<>();
        while(rs.next()){
            DetailBill detailBill = new DetailBill();
            detailBill.setBill_id( rs.getString("bill_id"));
            detailBill.setType_id(rs.getString("type_id"));
            detailBill.setAmount(rs.getInt("amount"));
            detailBill.setTotal_price(rs.getDouble("total_price"));
            detailBills.add(detailBill);
        }
        connection.close();
        return detailBills;
    }
    public void add(DetailBill iDDTO) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " INSERT INTO DETAILBILL VALUES (";
        sql += "'" + iDDTO.getBill_id() + "',";
        sql += "'" + iDDTO.getType_id() + "',";
        sql += "" + iDDTO.getTotal_price() + ",";
        sql += "" + iDDTO.getAmount() + ")";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(DetailBill iDetailDTO) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE DETAILBILL SET ";
        sql += "bill_id ='" + iDetailDTO.getBill_id() + "', ";
        sql += "type_id ='" + iDetailDTO.getType_id() + "', ";
        sql += "amount =" + iDetailDTO.getTotal_price() + ", ";
        sql += "total_price =" + iDetailDTO.getAmount() + " ";
        sql += "WHERE bill_id ='" + iDetailDTO.getBill_id() + "'" + " AND type_id = '" + iDetailDTO.getType_id() + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public void delete2(String invoiceID, String typeID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM DETAILBILL WHERE bill_id = '" + invoiceID + "'" + " AND type_id = '" + typeID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
    public void delete(String invoiceID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM DETAILBILL WHERE bill_id = '" + invoiceID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
}
