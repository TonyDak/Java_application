package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.CustomerDAO;
import DTO.Customer;

public class CustomerBUS {
    private ArrayList<Customer> list_Customers;
    private CustomerDAO customerDAO;
    public CustomerBUS() throws ClassNotFoundException, SQLException{
        list_Customers = new ArrayList<>();
        customerDAO = new CustomerDAO();
        list_Customers = customerDAO.docDB();
    }
    
    public ArrayList<Customer> getList_Customers() {
        return list_Customers;
    }

    public void setList_Customers(ArrayList<Customer> list_Customers) {
        this.list_Customers = list_Customers;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public String CreateMKH(){
        if(list_Customers == null || list_Customers.size() == 0){
            return "KH001";
        }
        Customer customer = new Customer();
        customer = list_Customers.get(list_Customers.size()-1);
        String id = customer.getCustomer_id();
        String numberPart = id.substring(2); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("KH%03d", number);
    }
    public boolean checkCustomerID(String id){
        for (Customer Customer : list_Customers) {
            if(Customer.getCustomer_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public void add(Customer a) throws ClassNotFoundException, SQLException {
        list_Customers.add(a);
        CustomerDAO khdao = new CustomerDAO();
        khdao.add(a);
    }

    public void delete(String MaKhachHang) throws ClassNotFoundException, SQLException {
        for (Customer a :   list_Customers) {
            if (a.getCustomer_id().equals(MaKhachHang)) {
                list_Customers.remove(a);
                CustomerDAO khdao = new CustomerDAO();
                khdao.delete(MaKhachHang);
                return;
            }
        }
    }

    public void set(Customer a) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Customers.size(); i++) {
            if  (list_Customers.get(i).getCustomer_id().equals(a.getCustomer_id())) {
                list_Customers.set(i, a);
                CustomerDAO khdao = new CustomerDAO();
                khdao.set(a);
                return;
            }
        }

    }
}
