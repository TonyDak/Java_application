package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Customer;

public class CustomerDAO {

    public ArrayList<Customer> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        java.sql.Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
        ArrayList<Customer> customers = new ArrayList<>();
        while(rs.next()){
            Customer customer = new Customer();
            customer.setCustomer_id(rs.getString("customer_id"));
            customer.setSurname(rs.getString("surname"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getString("phone"));
            customer.setAddress(rs.getString("address"));

            customers.add(customer);
        }
        connection.close();
        return customers;
    }
    public void add(Customer a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        java.sql.Statement stmt = connection.createStatement();
        String sql = "INSERT INTO CUSTOMER VALUES (";
        sql += "'" + a.getCustomer_id() + "','" + a.getSurname() + "'";
        sql += ",'" + a.getName() + "'";
        sql += ",'" + a.getEmail() + "','" + a.getPhone() + "'";
        sql += ",'" + a.getAddress() + "' )";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public void set(Customer a) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        java.sql.Statement stmt = connection.createStatement();
        String sql = "UPDATE CUSTOMER SET ";
        sql += "customer_id ='" + a.getCustomer_id() + "', ";
        sql += "surname = '" + a.getSurname() + "',";
        sql += "name ='" + a.getName() + "',";
        sql += "email='" + a.getEmail() + "',address='" + a.getAddress() + "',";
        sql += "phone='" + a.getPhone() + "'";
        sql += "WHERE customer_id = '" + a.getCustomer_id() + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        java.sql.Statement stmt = connection.createStatement();
        String sql = " DELETE FROM CUSTOMER WHERE customer_id = '" + id + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
}
