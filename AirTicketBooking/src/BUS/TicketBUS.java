package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TicketDAO;
import DTO.Ticket;

public class TicketBUS {
    private ArrayList<Ticket> list_Tickets;
    private TicketDAO ticketDAO;
    public TicketBUS() throws ClassNotFoundException, SQLException{
        list_Tickets = new ArrayList<>();
        ticketDAO = new TicketDAO();
        list_Tickets = ticketDAO.docDB();   
    }
    public ArrayList<Ticket> getList_Tickets() {
        return list_Tickets;
    }
    public void setList_Tickets(ArrayList<Ticket> list_Tickets) {
        this.list_Tickets = list_Tickets;
    }
    public TicketDAO getTicketDAO() {
        return ticketDAO;
    }
    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public String CreateTickerID(String a){
        if(list_Tickets == null || list_Tickets.size() == 0){
            return "TK"+a+"001";
        }
        Ticket Ticket = new Ticket();
        Ticket = list_Tickets.get(list_Tickets.size()-1);
        String id = Ticket.getTicket_id();
        String numberPart = id.substring(7); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("TK"+a+"%03d", number);
    }
    public void add(Ticket a) throws ClassNotFoundException, SQLException {
        list_Tickets.add(a);
        ticketDAO.add(a);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        for (Ticket n : list_Tickets) {
            if (n.getTicket_id().equals(ID)) {
                list_Tickets.remove(n);
                ticketDAO.delete(ID);
                return;
            }
        }
    }

    public void set(Ticket s) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Tickets.size(); i++) {
            if (list_Tickets.get(i).getTicket_id().equals(s.getTicket_id())) {
                list_Tickets.set(i, s);
                ticketDAO.set(s);
                return;
            }
        }

    }
}
