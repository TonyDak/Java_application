package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ChairDAO;
import DTO.Chair;

public class ChairBUS {
    private ArrayList<Chair> list_Chairs;
    private ChairDAO chairDAO;
    public ChairBUS() throws ClassNotFoundException, SQLException{
        list_Chairs = new ArrayList<Chair>();
        chairDAO = new ChairDAO();
        list_Chairs = chairDAO.docDB();
    }
    public ArrayList<Chair> getList_Chairs(){
        return list_Chairs;
    }
    public void setList_Chairs(ArrayList<Chair> list_Chairs) {
        this.list_Chairs = list_Chairs;
    }
    public ChairDAO getChairDAO() {
        return chairDAO;
    }
    public void setChairDAO(ChairDAO chairDAO) {
        this.chairDAO = chairDAO;
    }


    public void add(Chair a) throws ClassNotFoundException, SQLException {
        list_Chairs.add(a);
        chairDAO.add(a);
    }
    public void delete(String ID) throws ClassNotFoundException, SQLException {
        for (Chair n : list_Chairs) {
            if (n.getFlight_id().equals(ID)) {
                list_Chairs.remove(n);
                chairDAO.delete(ID);
                return;
            }
        }
    }

    public void set_chair(String ID, String type, String chair) throws ClassNotFoundException, SQLException {
        for (Chair n : list_Chairs) {
            if (n.getFlight_id().equals(ID) && n.getType_chair().equals(type)&&n.getChair_number().equals(chair)) {
                chairDAO.set_chair(ID,type,chair);
                return;
            }
        }
    }
    public void set_chair_1(String ID, String type, String chair) throws ClassNotFoundException, SQLException {
        for (Chair n : list_Chairs) {
            if (n.getFlight_id().equals(ID) && n.getType_chair().equals(type)&&n.getChair_number().equals(chair)) {
                chairDAO.set_chair_1(ID,type,chair);
                return;
            }
        }
    }
    public void set_flight(String ID) throws ClassNotFoundException, SQLException {
        for (Chair n : list_Chairs) {
            if (n.getFlight_id().equals(ID)) {
                chairDAO.set_flight(ID);
                return;
            }
        }
    }
    public void set(Chair s) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Chairs.size(); i++) {
            if (list_Chairs.get(i).getChair_number().equals(s.getChair_number()) && list_Chairs.get(i).getFlight_id().equals(s.getFlight_id())&&list_Chairs.get(i).getType_chair().equals(s.getType_chair())) {
                list_Chairs.set(i, s);
                chairDAO.set(s);
                return;
            }
        }

    }
}
