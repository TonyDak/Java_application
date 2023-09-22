package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Bill;

public class BillDAO {
    public ArrayList<Bill> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM BILL");
        ArrayList<Bill> bills = new ArrayList<>();
        while(rs.next()){
            Bill bill = new Bill();
            bill.setBill_id( rs.getString("bill_id"));
            bill.setCustomer_id(rs.getString("customer_id"));
            bill.setEmployee_id(rs.getString("employee_id"));
            bill.setTotal_price(rs.getInt("total_price"));
            bill.setBill_date(rs.getString("bill_date"));
            bills.add(bill);
        }
        connection.close();
        return bills;
    }
    public void add(Bill hd) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "insert into BILL values ("
                + "'" + hd.getBill_id() + "'"
                + ",'" + hd.getCustomer_id() + "'"
                + ",'" + hd.getEmployee_id() + "'"
                + ",'" + hd.getTotal_price() + "'"
                + ",'" + hd.getBill_date() + "')";
        System.out.println(qry);
        stmt.executeUpdate(qry);
    }

    public void delete(String ID) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "delete from BILL where bill_id='" + ID + "'";
        stmt.executeUpdate(qry);
    }

    public void set2(Bill hd) throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "update BILL set "
                + "customer_id = '" + hd.getCustomer_id() + "'"
                + ",employee_id = '" + hd.getEmployee_id() + "'"
                + ",bill_date = '" + hd.getBill_date() + "'"
                + " where bill_id = '" + hd.getBill_id()+"'";
        stmt.executeUpdate(qry);
    }
    public void set(Bill hd) throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "update BILL set"
                    +" bill_id = '" + hd.getBill_id() + "'"
                    +",customer_id = '" + hd.getCustomer_id() + "'"
                    +",employee_id = '" + hd.getEmployee_id() + "'"
                    +",total_price = '" + hd.getTotal_price() + "'"
                    +",bill_date = '" + hd.getBill_date() + "'"
                    +" where bill_id = '" + hd.getBill_id() + "'";
        stmt.executeUpdate(qry);
        connection.close();;
    }
}
