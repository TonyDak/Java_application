package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PlaneDAO;
import DTO.Plane;
public class PlaneBUS {
    private ArrayList<Plane> list_Planes;
    private PlaneDAO planeDAO;
    public PlaneBUS() throws ClassNotFoundException, SQLException{
        list_Planes = new ArrayList<>();
        planeDAO = new PlaneDAO();
        list_Planes = planeDAO.docDB();
    }
    public ArrayList<Plane> getList_Planes() {
        return list_Planes;
    }
    public void setList_Planes(ArrayList<Plane> list_Planes) {
        this.list_Planes = list_Planes;
    }
    public PlaneDAO getPlaneDAO() {
        return planeDAO;
    }
    public void setPlaneDAO(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }
    public boolean checkPlaneID(String id){
        for (Plane Plane : list_Planes) {
            if(Plane.getPlane_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public void add(Plane a) throws ClassNotFoundException, SQLException {
        list_Planes.add(a);
        PlaneDAO dao = new PlaneDAO();
        dao.add(a);
    }

    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (Plane n : list_Planes) {
            if (n.getPlane_id().equals(id)) {
                list_Planes.remove(n);
                PlaneDAO dao = new PlaneDAO();
                dao.delete(id);
                return;
            }
        }
    }

    public void set(Plane a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Planes.size(); i++) {
            if (list_Planes.get(i).getPlane_id().equals(a.getPlane_id())) {
                list_Planes.set(i, a);
                PlaneDAO dao = new PlaneDAO();
                dao.set(a);
                return;
            }

        }
    }
}
