package DTO;

public class Plane {
    private String plane_id ; // mã máy bay
    private String plane_model; // tên máy bay
    private int eco_seats, busi_seats;// số lượng ghế
    private String airplane_id;// mã hãng máy bay
    public Plane() {
    }
    
    public Plane(String plane_id, String plane_model, int eco_seats, int busi_seats, String airplane_id) {
        this.plane_id = plane_id;
        this.plane_model = plane_model;
        this.eco_seats = eco_seats;
        this.busi_seats = busi_seats;
        this.airplane_id = airplane_id;
    }

    public String getPlane_id() {
        return plane_id;
    }
    public void setPlane_id(String plane_id) {
        this.plane_id = plane_id;
    }
    public String getPlane_model() {
        return plane_model;
    }
    public void setPlane_model(String plane_model) {
        this.plane_model = plane_model;
    }
    
    public int getEco_seats() {
        return eco_seats;
    }

    public void setEco_seats(int eco_seats) {
        this.eco_seats = eco_seats;
    }

    public int getBusi_seats() {
        return busi_seats;
    }

    public void setBusi_seats(int busi_seats) {
        this.busi_seats = busi_seats;
    }

    public String getAirplane_id() {
        return airplane_id;
    }
    public void setAirplane_id(String airplane_id) {
        this.airplane_id = airplane_id;
    }
    
}
