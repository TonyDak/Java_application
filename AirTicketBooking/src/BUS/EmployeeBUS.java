package BUS;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.EmployeeDAO;
import DTO.Employee;

public class EmployeeBUS {
    private ArrayList<Employee> list_Employees;
    private EmployeeDAO employeeDAO;
    public EmployeeBUS() throws ClassNotFoundException, SQLException {
        list_Employees = new ArrayList<>();
        employeeDAO = new EmployeeDAO();
        list_Employees = employeeDAO.docDB();
    }
    
    
    public ArrayList<Employee> getList_Employees() {
        return list_Employees;
    }


    public void setList_Employees(ArrayList<Employee> list_Employees) {
        this.list_Employees = list_Employees;
    }


    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }


    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    public Boolean chekcLogin(String a, String b) {
        for ( Employee employee : list_Employees ) {
            // kiểm tra mật khảu và tên đăng nhập
            if (employee.getUser_name().equalsIgnoreCase(a)
                    && employee.getPass().equalsIgnoreCase(b) ) {
                return true;
            }
        }
        return false;
    }
    public Employee getEmployee(String a, String b){
        for ( Employee employee : list_Employees ) {
            // kiểm tra mật khảu và tên đăng nhập
            if (employee.getUser_name().equalsIgnoreCase(a)
                    && employee.getPass().equalsIgnoreCase(b) ) {
                return employee;
            }
        }
        return null;
    }
    public boolean checkEmloyeeID(String id){
        for (Employee Employee : list_Employees) {
            if(Employee.getEmployee_id().equals(id)){
                return false;
            }
        }
        return true;
    }
    public String CreateMNV(){
        if(list_Employees == null || list_Employees.size() == 0){
            return "NV001";
        }
        Employee employee = new Employee();
        employee = list_Employees.get(list_Employees.size()-1);
        String id = employee.getEmployee_id();
        String numberPart = id.substring(2); // lấy phần số sau chuỗi "KH"
        int number = Integer.parseInt(numberPart); // chuyển phần số thành kiểu int
        number++; // tăng giá trị số lên 1 đơn vị
        return String.format("NV%03d", number);
    }
    public void add(Employee nv) throws ClassNotFoundException, SQLException{
        list_Employees.add(nv);
        EmployeeDAO nvDAO = new EmployeeDAO();
        nvDAO.add(nv);
    }
    public void delete(String ID) throws ClassNotFoundException, SQLException{
        for(Employee n : list_Employees){
           if(n.getEmployee_id().equals(ID)){
                list_Employees.remove(n);
                EmployeeDAO nvDAO = new EmployeeDAO();
                nvDAO.delete(ID);
                return;
            }
        }
    }
    
    public void set(Employee nv) throws ClassNotFoundException, SQLException {
        for (int i = 0; i < list_Employees.size(); i++) {
            if (list_Employees.get(i).getEmployee_id().equals(nv.getEmployee_id())) {
                list_Employees.set(i, nv);
                EmployeeDAO nvDAO = new EmployeeDAO();
                nvDAO.set(nv);
                return;
            }
        }
    }
}
