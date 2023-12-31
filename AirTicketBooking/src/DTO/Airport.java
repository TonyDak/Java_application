package DTO;

public class Airport {
    private String airport_id;
    private String name;
    private String city;
    private String country;
    public Airport() {
    }
    public Airport(String airport_id, String name, String city, String country) {
        this.airport_id = airport_id;
        this.name = name;
        this.city = city;
        this.country = country;
    }
    public String getAirport_id() {
        return airport_id;
    }
    public void setAirport_id(String airport_id) {
        this.airport_id = airport_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    
}
