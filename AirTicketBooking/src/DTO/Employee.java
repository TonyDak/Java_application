package DTO;

public class Employee {
    private String employee_id;
    private String user_name;
    private String pass;
    private String role_id;
    private String surname;
    private String name;
    private String gender;
    private String birth;
    private String email;
    private String phone;
    private String address;
    public Employee() {
    }
    public Employee(String employee_id, String user_name, String pass, String role_id, String surname, String name,
            String gender, String birth, String email, String phone, String address) {
        this.employee_id = employee_id;
        this.user_name = user_name;
        this.pass = pass;
        this.role_id = role_id;
        this.surname = surname;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    public String getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
    public String getUser_name() {
        return user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRole_id() {
        return role_id;
    }
    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
    
}
