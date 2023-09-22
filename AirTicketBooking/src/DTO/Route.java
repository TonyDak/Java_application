package DTO;

public class Route {
    private String route_id;
    private String origin; //diem di
    private String destination; //diem den
    public Route() {
    }
    public Route(String route_id, String origin, String destination) {
        this.route_id = route_id;
        this.origin = origin;
        this.destination = destination;
    }
    public String getRoute_id() {
        return route_id;
    }
    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
}
