package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.FlightDAO;
import DTO.Chair;
import DTO.Flight;
import DTO.Plane;

public class FlightBUS {
    private ArrayList<Flight> list_Flights;
    private FlightDAO flightDAO;
    public FlightBUS() throws ClassNotFoundException, SQLException{
        list_Flights = new ArrayList<>();
        flightDAO = new FlightDAO();
        list_Flights = flightDAO.docDB();
    }
    public ArrayList<Flight> getList_Flights() {
        return list_Flights;
    }
    public void setList_Flights(ArrayList<Flight> list_Flights) {
        this.list_Flights = list_Flights;
    }
    public FlightDAO getFlightDAO() {
        return flightDAO;
    }
    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }
    public boolean checkID(String id){
        for (Flight Flight : list_Flights) {
            if(Flight.getFlight_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public boolean checkIDplane(String id){
        for (Flight Flight : list_Flights) {
            if(Flight.getPlane_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public boolean checkIDflightschedule(String id){
        for (Flight Flight : list_Flights) {
            if(Flight.getFlightSchedule_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public void add(Flight cb) throws ClassNotFoundException, SQLException{
        list_Flights.add(cb);
        PlaneBUS planeBUS = new PlaneBUS();
        ArrayList<Plane> list_Planes = planeBUS.getList_Planes();
        ChairBUS chairBUS = new ChairBUS();
        for (Plane plane : list_Planes) {
            if(cb.getPlane_id().equals(plane.getPlane_id())){
                for(int i = 1; i <= plane.getEco_seats(); i++){
                    Chair chair = new Chair(cb.getFlight_id(), "ECO", Integer.toString(i), 0);
                    chairBUS.add(chair);
                }
                for(int i = 1; i <= plane.getBusi_seats(); i++){
                    Chair chair = new Chair(cb.getFlight_id(), "BUS", Integer.toString(i), 0);
                    chairBUS.add(chair);
                }
            }
        }
        
        flightDAO.add(cb);
    }
    
    public void delete(String ID) throws ClassNotFoundException, SQLException{
        ChairBUS chairBUS = new ChairBUS();
        for(Flight n : list_Flights){
           if(n.getFlight_id().equals(ID)){
                list_Flights.remove(n);
                flightDAO.delete(ID);
                chairBUS.delete(ID);
                return;
            }
        }
    }
    
    public void set(Flight hd) throws ClassNotFoundException, SQLException {
        ChairBUS chairBUS = new ChairBUS();
        for (int i = 0; i < list_Flights.size(); i++) {
            if (list_Flights.get(i).getFlight_id().equals(hd.getFlight_id()) ) {
                list_Flights.set(i, hd);
                flightDAO.set(hd);
                chairBUS.set_flight(list_Flights.get(i).getFlight_id());
                return;
            }
        }
    }
}
