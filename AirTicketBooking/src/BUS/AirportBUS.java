package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AirportDAO;
import DTO.Airport;

public class AirportBUS {
    private ArrayList<Airport> list_Airports;
    private AirportDAO airportDAO;
    public AirportBUS() throws ClassNotFoundException, SQLException {
        list_Airports = new ArrayList<>();
        airportDAO = new AirportDAO();
        list_Airports = airportDAO.docDB();
    }
    public ArrayList<Airport> getList_Airports() {
        return list_Airports;
    }

    public void setList_Airports(ArrayList<Airport> list_Airports) {
        this.list_Airports = list_Airports;
    }

    public AirportDAO getAirportDAO() {
        return airportDAO;
    }

    public void setAirportDAO(AirportDAO airportDAO) {
        this.airportDAO = airportDAO;
    }
    public boolean checkID(String id){
        for (Airport Airport : list_Airports) {
            if(Airport.getAirport_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public String[] listCity() throws ClassNotFoundException, SQLException{
        list_Airports = airportDAO.docDB();
        String[] listCity = new String[list_Airports.size()];
        int i = 0;
        for (Airport airport : list_Airports) {
            listCity[i++] = airport.getAirport_id()+"-"+ airport.getCity();
        }
        return listCity;
    }

    public void add(Airport a) throws ClassNotFoundException, SQLException {
        list_Airports.add(a);
        AirportDAO dao = new AirportDAO();
        dao.add(a);
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (Airport n : list_Airports) {
            if (n.getAirport_id().equals(id)) {
                list_Airports.remove(n);
                AirportDAO dao = new AirportDAO();
                dao.delete(id);
                return;
            }
        }
    }

    public void set(Airport a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Airports.size(); i++) {
            if (list_Airports.get(i).getAirport_id().equals(a.getAirport_id())) {
                list_Airports.set(i, a);
                AirportDAO dao = new AirportDAO();
                dao.set(a);
                return;
            }

        }
    }
}
