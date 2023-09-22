package BUS;
import DAO.FunctionDAO;
import DTO.Funtions;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuntionBUS {
    private ArrayList <Funtions> list_funtion;
    private FunctionDAO functionDAO;
    public FuntionBUS() throws ClassNotFoundException, SQLException{
        list_funtion = new ArrayList<>();
        functionDAO = new FunctionDAO();
        list_funtion = functionDAO.docDB();
    }
    public ArrayList<Funtions> getList_Funtions() {
        return  list_funtion;
    }
    public void setList_Futions(ArrayList<Funtions> list_funtion) {
        this.list_funtion = list_funtion;
    }
    public FunctionDAO getFunctionDAO() {
        return functionDAO;
    }
    public void setFunctionDAO(FunctionDAO functionDAO) {
        this.functionDAO = functionDAO;
    }
    public boolean checkFuntionID(String name ){
        for (Funtions funtions : list_funtion) {
            if(funtions.getName() == name){
                return false;
            }
        }
        return true;
    }
    public void add(Funtions a) throws ClassNotFoundException, SQLException {
        list_funtion.add(a);
        FunctionDAO dao = new FunctionDAO();
        dao.add(a);
    }
    public void set(Funtions a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_funtion.size(); i++) {
            if (list_funtion.get(i).getFuntion_id() == a.getFuntion_id()) {
                list_funtion.set(i, a);
                FunctionDAO dao = new FunctionDAO();
                dao.set(a);
                return;
            }

        }
    }
    public void delete(int id) throws ClassNotFoundException, SQLException {
        for (Funtions n : list_funtion) {
            if (n.getFuntion_id().equals(Integer.toString(id))) {
                list_funtion.remove(n);
                FunctionDAO dao = new FunctionDAO();
                dao.delete(id);
                return;
            }
        }
  }
  
}