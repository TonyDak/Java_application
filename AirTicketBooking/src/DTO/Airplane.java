package DTO;

public class Airplane {
    private String airplane_id;// mã hãng máy bay
    private String name;// tên hãng máy bay
    private String country; //hang may bay thuoc nuoc nao
    public Airplane() {
    }
    public Airplane(String airplane_id, String name, String country) {
        this.airplane_id = airplane_id;
        this.name = name;
        this.country = country;
    }
    public String getAirplane_id() {
        return airplane_id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setAirplane_id(String airplane_id) {
        this.airplane_id = airplane_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
