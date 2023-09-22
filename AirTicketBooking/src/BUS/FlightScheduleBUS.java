package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.FlightScheduleDAO;
import DTO.FlightSchedule;

public class FlightScheduleBUS {
    private ArrayList<FlightSchedule> list_FlightSchedules;
    private FlightScheduleDAO flightScheduleDAO;
    public FlightScheduleBUS() throws ClassNotFoundException, SQLException{
        list_FlightSchedules = new ArrayList<>();
        flightScheduleDAO = new FlightScheduleDAO();
        list_FlightSchedules = flightScheduleDAO.docDB();
    }
    public ArrayList<FlightSchedule> getList_FlightSchedules() {
        return list_FlightSchedules;
    }
    public void setList_FlightSchedules(ArrayList<FlightSchedule> list_FlightSchedules) {
        this.list_FlightSchedules = list_FlightSchedules;
    }
    public FlightScheduleDAO getFlightScheduleDAO() {
        return flightScheduleDAO;
    }
    public void setFlightScheduleDAO(FlightScheduleDAO flightScheduleDAO) {
        this.flightScheduleDAO = flightScheduleDAO;
    }

    public boolean checkID(String id){
        for (FlightSchedule FlightSchedule : list_FlightSchedules) {
            if(FlightSchedule.getFlight_schedule_id().equals(id)){
                return false;
            }
        }
        return true;
    }

    public void add(FlightSchedule a) throws ClassNotFoundException, SQLException {
        list_FlightSchedules.add(a);
        FlightScheduleDAO dao = new FlightScheduleDAO();
        dao.add(a);
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (FlightSchedule n : list_FlightSchedules) {
            if (n.getFlight_schedule_id().equals(id)) {
                list_FlightSchedules.remove(n);
                FlightScheduleDAO dao = new FlightScheduleDAO();
                dao.delete(id);
                return;
            }
        }
    }

    public void set(FlightSchedule a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_FlightSchedules.size(); i++) {
            if (list_FlightSchedules.get(i).getFlight_schedule_id().equals(a.getFlight_schedule_id())) {
                list_FlightSchedules.set(i, a);
                FlightScheduleDAO dao = new FlightScheduleDAO();
                dao.set(a);
                return;
            }

        }
    }
}
