package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.Employee;

public class EmployeeDAO {
    //DatabaseInfo connection;
    public ArrayList<Employee> docDB() throws ClassNotFoundException, SQLException{
        Class.forName(DatabaseInfo.driverName);
        Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);
        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
        ArrayList<Employee> employees = new ArrayList<>();
        while(rs.next()){
            Employee employee = new Employee();
            employee.setEmployee_id(rs.getString("employee_id"));
            employee.setUser_name(rs.getString("user_name"));
            employee.setPass(rs.getString("pass"));
            employee.setRole_id(rs.getString("role_id"));
            employee.setSurname(rs.getString("surname"));
            employee.setName(rs.getString("name"));
            employee.setGender(rs.getString("gender"));
            employee.setBirth(rs.getString("birth"));
            employee.setEmail(rs.getString("email"));
            employee.setPhone(rs.getString("phone"));
            employee.setAddress(rs.getString("address"));

            employees.add(employee);
        }
        connection.close();
        return employees;
    }
    public void add(Employee nv) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "insert into EMPLOYEE values ("
                + "'" + nv.getEmployee_id() + "'"
                + ",'" + nv.getUser_name() + "'"
                + ",'" + nv.getPass() + "'"
                + ",'" + nv.getRole_id() + "'"
                + ",'" + nv.getSurname() + "'"
                + ",'" + nv.getName() + "'"
                + ",'" + nv.getGender() + "'"
                + ",'" + nv.getBirth()+ "'"
                + ",'" + nv.getEmail()+ "'"
                + ",'" + nv.getPhone() + "'"
                + ",'" + nv.getAddress() + "')";
        System.out.println(qry);
        stmt.executeUpdate(qry);
    }

    public void delete(String ID) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "delete from EMPLOYEE where employee_id = '" + ID + "'";
        stmt.executeUpdate(qry);
    }

    public void set(Employee nv) throws ClassNotFoundException, SQLException {
        Class.forName(DatabaseInfo.driverName);
                Connection connection = DriverManager.getConnection(DatabaseInfo.dbURL, DatabaseInfo.dbUser, DatabaseInfo.dbPass);

        System.out.println("Connected to SQL Server" + connection.getCatalog());
        Statement stmt = connection.createStatement();
        String qry = "update EMPLOYEE set "
                + "user_name = '" + nv.getUser_name() + "'"
                + ",pass = '" + nv.getPass() + "'"
                + ",role_id = '" + nv.getRole_id() + "'"
                + ",surname = '" + nv.getSurname() + "'"
                + ",name = '" + nv.getName() + "'"
                + ",gender = '" + nv.getGender() + "'"
                + ",birth = '" + nv.getBirth() + "'"
                + ",email = '" + nv.getEmail() + "'"
                + ",phone = '" + nv.getPhone() + "'"
                + " where employee_id = '" + nv.getEmployee_id() +"'";
        stmt.executeUpdate(qry);
    }
}
