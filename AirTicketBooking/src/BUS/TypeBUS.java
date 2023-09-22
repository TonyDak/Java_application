package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.TypeDAO;
import DTO.Type;;

public class TypeBUS {
    private ArrayList<Type> list_Types;
    private TypeDAO typeDAO;
    public TypeBUS() throws ClassNotFoundException, SQLException{
        list_Types = new ArrayList<>();
        typeDAO = new TypeDAO();
        list_Types = typeDAO.docDB();
    }
    public ArrayList<Type> getList_Types() {
        return list_Types;
    }
    public void setList_Types(ArrayList<Type> list_Types) {
        this.list_Types = list_Types;
    }
    public TypeDAO getTypeDAO() {
        return typeDAO;
    }
    public void setTypeDAO(TypeDAO typeDAO) {
        this.typeDAO = typeDAO;
    }
    public Type get(String id) {
        for (Type a : list_Types) {
            if (a.getType_id().equals(id)) {
                return a;
            }
        }
        return null;
    }
    public void add(Type a) throws ClassNotFoundException, SQLException {
        list_Types.add(a);
       typeDAO.add(a);
    }
    public void add2(Type a) throws ClassNotFoundException, SQLException {
        list_Types.add(a);
        typeDAO.add2(a);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        for (Type n : list_Types) {
            if (n.getType_id().equals(ID)) {
                list_Types.remove(n);
               typeDAO.delete(ID);
                return;
            }
        }
    }
    public String CreateType(){
        if(list_Types == null || list_Types.size() == 0){
            return "DV001";
        }
        Type Type = new Type();
        Type = list_Types.get(list_Types.size()-1);
        String id = Type.getType_id();
        String numberPart = id.substring(2); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("DV%03d", number);
    }
    
    public void set(Type s) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Types.size(); i++) {
            if (list_Types.get(i).getType_id().equals(s.getType_id())) {
                list_Types.set(i, s);
               typeDAO.set(s);
                return;
            }
        }

    }
}
