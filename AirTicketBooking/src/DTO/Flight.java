package DTO;

public class Flight {
    private String flight_id;
    private String route_id;  // route tuyến đường
    private String plane_id;  // airplane máy bay
    private String flightSchedule_id;  // schedule lịch trình
    private int totalSeats;  // total seats tổng số ghế
    private String bookedECOSeats;  // bookedECOSeats đã đặt trước
    private String bookedBUSSeats;  // bookedBUSSeats đã đặt chỗ
    private String price_eco;
    private String price_bus;
    public Flight() {
    }
    public Flight(String flight_id, String route_id, String plane_id, String flightSchedule_id, int totalSeats,
            String bookedECOSeats, String bookedBUSSeats, String price_eco, String price_bus) {
        this.flight_id = flight_id;
        this.route_id = route_id;
        this.plane_id = plane_id;
        this.flightSchedule_id = flightSchedule_id;
        this.totalSeats = totalSeats;
        this.bookedECOSeats = bookedECOSeats;
        this.bookedBUSSeats = bookedBUSSeats;
        this.price_eco = price_eco;
        this.price_bus = price_bus;
    }
    
    public String getPrice_eco() {
        return price_eco;
    }
    public void setPrice_eco(String price_eco) {
        this.price_eco = price_eco;
    }
    public String getPrice_bus() {
        return price_bus;
    }
    public void setPrice_bus(String price_bus) {
        this.price_bus = price_bus;
    }
    public String getFlight_id() {
        return flight_id;
    }
    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }
    public String getRoute_id() {
        return route_id;
    }
    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }
    public String getPlane_id() {
        return plane_id;
    }
    public void setPlane_id(String plane_id) {
        this.plane_id = plane_id;
    }
    public String getFlightSchedule_id() {
        return flightSchedule_id;
    }
    public void setFlightSchedule_id(String flightSchedule_id) {
        this.flightSchedule_id = flightSchedule_id;
    }
    public int getTotalSeats() {
        return totalSeats;
    }
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
    public String getBookedECOSeats() {
        return bookedECOSeats;
    }
    public void setBookedECOSeats(String bookedECOSeats) {
        this.bookedECOSeats = bookedECOSeats;
    }
    public String getBookedBUSSeats() {
        return bookedBUSSeats;
    }
    public void setBookedBUSSeats(String bookedBUSSeats) {
        this.bookedBUSSeats = bookedBUSSeats;
    }
    

}
