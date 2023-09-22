package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Airplane;
import DAO.AirplaneDAO;

public class AirplaneBUS {
    private ArrayList<Airplane> list_Airplanes;
    private AirplaneDAO airplaneDAO;
    public AirplaneBUS() throws ClassNotFoundException, SQLException {
        list_Airplanes = new ArrayList<>();
        airplaneDAO = new AirplaneDAO();
        list_Airplanes = airplaneDAO.docDB();
    }
    public ArrayList<Airplane> getList_Airplanes() {
        return list_Airplanes;
    }


    public void setList_Airplanes(ArrayList<Airplane> list_Airplanes) {
        this.list_Airplanes = list_Airplanes;
    }


    public AirplaneDAO getAirplaneDAO() {
        return airplaneDAO;
    }


    public void setAirplaneDAO(AirplaneDAO airplaneDAO) {
        this.airplaneDAO = airplaneDAO;
    }

    public void add(Airplane a) throws ClassNotFoundException, SQLException {
        list_Airplanes.add(a);
        AirplaneDAO dao = new AirplaneDAO();
        dao.add(a);
    }
    public boolean checkAirplaneID(String id){
        for (Airplane Airplane : list_Airplanes) {
            if(Airplane.getAirplane_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (Airplane n : list_Airplanes) {
            if (n.getAirplane_id().equals(id)) {
                list_Airplanes.remove(n);
                AirplaneDAO dao = new AirplaneDAO();
                dao.delete(id);
                return;
            }
        }
    }

    public void set(Airplane a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Airplanes.size(); i++) {
            if (list_Airplanes.get(i).getAirplane_id().equals(a.getAirplane_id())) {
                list_Airplanes.set(i, a);
                AirplaneDAO dao = new AirplaneDAO();
                dao.set(a);
                return;
            }

        }
    }
}
