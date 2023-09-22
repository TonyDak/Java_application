package DTO;

public class Ticket {
    private String ticket_id;
    private String flight_id;
    private String customer_id;
    private String type_id;
    private String chair_number;
    private String bill_id;

    public Ticket() {
    }
    

    public Ticket(String ticket_id, String flight_id, String customer_id, String type_id, String chair_number,
            String bill_id) {
        this.ticket_id = ticket_id;
        this.flight_id = flight_id;
        this.customer_id = customer_id;
        this.type_id = type_id;
        this.chair_number = chair_number;
        this.bill_id = bill_id;
    }


    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }


    public String getChair_number() {
        return chair_number;
    }


    public void setChair_number(String chair_number) {
        this.chair_number = chair_number;
    }
    
    
}
