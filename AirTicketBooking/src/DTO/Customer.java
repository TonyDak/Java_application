package DTO;

import javax.swing.JTextField;

public class Customer {
    private String customer_id;
    private String surname;
    private String name;
    private String email;
    private String phone;
    private String address;
    
    public Customer() {
    }
    
    public Customer(String customer_id, String surname, String name, String email, String phone, String address) {
        this.customer_id = customer_id;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Customer(JTextField customerIdTextField, JTextField surnameTextField, JTextField nameTextField,
            JTextField emailTextField, JTextField phoneTextField, JTextField addressTextField) {
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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
}