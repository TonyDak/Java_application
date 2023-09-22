package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.RoleFunc;
import DAO.RoleFuncDAO;

public class RoleFuncBUS {
 
    private ArrayList <RoleFunc> list_RoleFuncs;
    private RoleFuncDAO roleFuncDAO;
    public RoleFuncBUS() throws ClassNotFoundException, SQLException{
        list_RoleFuncs = new ArrayList<>();
        roleFuncDAO = new RoleFuncDAO();
        list_RoleFuncs = roleFuncDAO.docDB();
    }
    public ArrayList<RoleFunc> getList_RoleFunc() {
        return list_RoleFuncs;
    }
    public void setList_RoleFunc(ArrayList<RoleFunc> list_RoleFuncs) {
        this.list_RoleFuncs = list_RoleFuncs;
    }
    public RoleFuncDAO getRoleFuncDAO() {
        return roleFuncDAO;
    }
    public void setRoleFuncDAO(RoleFuncDAO roleFuncDAO) {
        this.roleFuncDAO= roleFuncDAO;
    }
    public boolean checkrolefunc(int roleid, int funtion_id){
        for ( RoleFunc rolefun : list_RoleFuncs) {
            if(rolefun.getRoleId().equals(Integer.toString(roleid)) && rolefun.getFunctionId().equals(Integer.toString(funtion_id))){
                return false;
            }
        }
        return true;
    }
    public void add(RoleFunc a) throws ClassNotFoundException, SQLException {
        list_RoleFuncs.add(a);
        RoleFuncDAO dao = new RoleFuncDAO();
        dao.add(a);
    }
    public void set(RoleFunc a) throws ClassNotFoundException, SQLException{
        for (int i = 0; i < list_RoleFuncs.size(); i++) {
            if (list_RoleFuncs.get(i).getRoleId().equals(a.getRoleId())) {
                list_RoleFuncs.set(i, a);
                RoleFuncDAO dao = new RoleFuncDAO();
                dao.set(a);
                return;
            }
    }
    
}
public void deleteallRole(String id) throws ClassNotFoundException, SQLException {
    for (RoleFunc n : list_RoleFuncs) {
        if (n.getRoleId().equals(id)) {
           list_RoleFuncs.remove(n);
            RoleFuncDAO dao = new RoleFuncDAO();
            dao.deleteallRole(id);
            return;
        }
    }
}
public void delete(int roleid, int funtionid) throws ClassNotFoundException, SQLException {
    for (RoleFunc n : list_RoleFuncs) {
        if (n.getRoleId().equals(Integer.toString(roleid)) && n.getFunctionId().equals(Integer.toString(funtionid))) {
           list_RoleFuncs.remove(n);
            RoleFuncDAO dao = new RoleFuncDAO();
            dao.delete(roleid,funtionid);
            return;
        }
    }
  }
  public String[] funtionname(String role_id) throws ClassNotFoundException, SQLException{
    RoleFuncDAO dao = new RoleFuncDAO();
     return dao.getFunctionsName(role_id);
    
  }
}
