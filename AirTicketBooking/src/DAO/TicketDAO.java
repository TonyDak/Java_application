package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Ticket;

public class TicketDAO {
    public ArrayList<Ticket> docDB() throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TICKET");
        ArrayList<Ticket> tickets = new ArrayList<>();
        while(rs.next()){
            Ticket ticket = new Ticket();
            ticket.setTicket_id(rs.getString("ticket_id"));
            ticket.setFlight_id(rs.getString("flight_id"));
            ticket.setCustomer_id(rs.getString("customer_id"));
            ticket.setType_id(rs.getString("type_id"));
            ticket.setChair_number(rs.getString("chair_number"));
            ticket.setBill_id(rs.getString("bill_id"));

            tickets.add(ticket);
        }
        connection.close();
        return tickets;
    }
    public void add(Ticket tkDTO) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " INSERT INTO TICKET VALUES (";
        sql += "'" + tkDTO.getTicket_id() + "',";
        sql += "'" + tkDTO.getFlight_id() + "',";
        sql += "'" + tkDTO.getCustomer_id() + "',";
        sql += "'" + tkDTO.getType_id() + "',";
        sql += "'" + tkDTO.getChair_number() + "',";
        sql += "'" + tkDTO.getBill_id() + "')";
        System.out.println(sql + "\n");
        stmt.executeUpdate(sql);
    }

    public void set(Ticket tk) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = " UPDATE TICKET SET ";
        sql += "ticket_id ='" + tk.getTicket_id() + "', ";
        sql += "flight_id='" + tk.getFlight_id() + "', ";
        sql += "customer_id ='" + tk.getCustomer_id() + "', ";
        sql += "type_id ='" + tk.getType_id() + "', ";
        sql += "chair_number ='" + tk.getChair_number() + "', ";
        sql += "bill_id ='" + tk.getBill_id() + "' ";
        sql += "WHERE ticket_id ='" + tk.getTicket_id() + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM TICKET WHERE ticket_id = '" + ID + "'";
        System.out.println(sql);
        stmt.executeUpdate(sql);
    }
}
