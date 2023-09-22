package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.BillDAO;
import DTO.Bill;

public class BillBUS {
    private ArrayList<Bill> list_Bills;
    private BillDAO billDAO;
    public BillBUS() throws ClassNotFoundException, SQLException{
        list_Bills = new ArrayList<>();
        billDAO = new BillDAO();
        list_Bills = billDAO.docDB();
    }
    public ArrayList<Bill> getList_Bills() {
        return list_Bills;
    }
    public void setList_Bills(ArrayList<Bill> list_Bills) {
        this.list_Bills = list_Bills;
    }
    public BillDAO getBillDAO() {
        return billDAO;
    }
    public void setBillDAO(BillDAO billDAO) {
        this.billDAO = billDAO;
    }
    public String CreateMHD(){
        if(list_Bills == null || list_Bills.size() == 0){
            return "HD001";
        }
        Bill bill = new Bill();
        bill = list_Bills.get(list_Bills.size()-1);
        String id = bill.getBill_id();
        String numberPart = id.substring(2); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("HD%03d", number);
    }
    public void add(Bill hd) throws ClassNotFoundException, SQLException{
        list_Bills.add(hd);
        BillDAO hdDAODAO = new BillDAO();
        hdDAODAO.add(hd);
    }
    
    public void delete(String ID) throws ClassNotFoundException, SQLException{
        for(Bill n : list_Bills){
           if(n.getBill_id().equals(ID)){
                list_Bills.remove(n);
                BillDAO hdDAO = new BillDAO();
                hdDAO.delete(ID);
                return;
            }
        }
    }
    
    public void set(Bill hd) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Bills.size(); i++) {
            if (list_Bills.get(i).getBill_id().equals(hd.getBill_id())) {
                list_Bills.set(i, hd);
                BillDAO hdDAO = new BillDAO();
                hdDAO.set(hd);
                return;
            }
        }
    }

}
