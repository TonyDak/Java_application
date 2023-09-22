package BUS;

import java.sql.SQLException;
import java.util.ArrayList;


import DAO.RolesDAO;
import DTO.Roles;
public class RolesBUS {
    private ArrayList <Roles> list_Roles;
    private RolesDAO rolesDAO;
    public RolesBUS() throws ClassNotFoundException, SQLException{
        list_Roles = new ArrayList<>();
        rolesDAO = new RolesDAO();
        list_Roles = rolesDAO.docDB();
    }
    public ArrayList<Roles> getList_Roles() {
        return list_Roles;
    }
    public void setList_Roles(ArrayList<Roles> list_Roles) {
        this.list_Roles = list_Roles;
    }
    public RolesDAO getRolesDAO() {
        return rolesDAO;
    }
    public void setRolesDAO(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }
    public boolean checkRoleID(String name){
        for (Roles roles : list_Roles) {
            if(roles.getRoles_id().equals(name)){
                return false;
            }
        }
        return true;
    }
    public String Createrole(){
        if(list_Roles == null || list_Roles.size() == 0){
            return "1";
        }
        Roles role = new Roles();
        role = list_Roles.get(list_Roles.size()-1);
        String id = role.getRoles_id();
        int number = Integer.parseInt(id); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("%d", number);
    }
    public void add(Roles a) throws ClassNotFoundException, SQLException {
        list_Roles.add(a);
        RolesDAO dao = new RolesDAO();
        dao.add(a);
    }
    public void set(Roles a) throws ClassNotFoundException, SQLException{
        for (int i = 0; i < list_Roles.size(); i++) {
            if (list_Roles.get(i).getRoles_id().equals(a.getRoles_id() ) ) {
                list_Roles.set(i, a);
                RolesDAO dao = new RolesDAO();
                dao.set(a);
                return;
            }
    }
}
    public void delete(String id) throws ClassNotFoundException, SQLException {
        for (Roles n : list_Roles) {
            if (n.getRoles_id().equals(id)) {
                list_Roles.remove(n);
                RolesDAO dao = new RolesDAO();
                dao.delete(id);
                return;
            }
        }

    }
    
   }