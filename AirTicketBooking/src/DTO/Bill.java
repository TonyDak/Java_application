package DTO;

public class Bill {
    private String bill_id;
    private String customer_id;
    private String employee_id;
    private int total_price;
    private String bill_date;
    
    public Bill() {
    }
    public Bill(String bill_id, String customer_id, String employee_id, int total_price, String bill_date) {
        this.bill_id = bill_id;
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.total_price = total_price;
        this.bill_date = bill_date;
    }
    
    public String getBill_id() {
        return bill_id;
    }
    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
    public int getTotal_price() {
        return total_price;
    }
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
    public String getBill_date() {
        return bill_date;
    }
    public void setBill_date(String bill_date) {
        this.bill_date = bill_date;
    }
    public String getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
    
}
