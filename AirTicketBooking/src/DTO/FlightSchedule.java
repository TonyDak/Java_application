package DTO;

public class FlightSchedule {
    private String flight_schedule_id;
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String arrivalDate;
    private String boartime;
    public FlightSchedule() {
    }
    
    public FlightSchedule(String flight_schedule_id, String departureAirport, String arrivalAirport,
            String departureDate, String arrivalDate, String boartime) {
        this.flight_schedule_id = flight_schedule_id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.boartime = boartime;
    }
    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getFlight_schedule_id() {
        return flight_schedule_id;
    }
    public void setFlight_schedule_id(String flight_schedule_id) {
        this.flight_schedule_id = flight_schedule_id;
    }
    public String getDepartureAirport() {
        return departureAirport;
    }
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }



    public String getBoartime() {
        return boartime;
    }



    public void setBoartime(String boartime) {
        this.boartime = boartime;
    }
    
}
