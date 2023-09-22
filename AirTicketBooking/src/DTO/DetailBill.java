package DTO;

public class DetailBill {
    private String bill_id;
    private String type_id;
    private int amount;
    private double total_price;
    public DetailBill() {
    }
    public DetailBill(String bill_id, String type_id, int amount, double total_price) {
        this.bill_id = bill_id;
        this.type_id = type_id;
        this.amount = amount;
        this.total_price = total_price;
    }
    public String getBill_id() {
        return bill_id;
    }
    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }
    public String getType_id() {
        return type_id;
    }
    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public double getTotal_price() {
        return total_price;
    }
    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
    
   
}
