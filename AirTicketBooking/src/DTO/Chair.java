package DTO;

public class Chair {
    private String flight_id;
    private String type_chair;
    private String chair_number;
    private int status;

    

    public Chair() {
    }
    public Chair(String flight_id, String type_chair, String chair_number, int status) {
        this.flight_id = flight_id;
        this.type_chair = type_chair;
        this.chair_number = chair_number;
        this.status = status;
    }
    public String getFlight_id() {
        return flight_id;
    }
    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }
    public String getType_chair() {
        return type_chair;
    }
    public void setType_chair(String type_chair) {
        this.type_chair = type_chair;
    }
    public String getChair_number() {
        return chair_number;
    }
    public void setChair_number(String chair_number) {
        this.chair_number = chair_number;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    

}
