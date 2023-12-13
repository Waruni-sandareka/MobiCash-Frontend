package lk.mcashfront.model;

public class Wallet {
    private Integer mobileno;
    private double amount;
    private int pin;

    public Wallet(Integer mobileno,double amount,int pin){
        this.mobileno=mobileno;
        this.amount=amount;
        this.pin=pin;
    }
    public Integer getMobileno(){

        return mobileno;
    }
    public void setMobileno(Integer mobileno){

        this.mobileno = mobileno;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public int getPin() {
        return pin;
    }
    public void setPin(int pin){
        this.pin=pin;
    }
}
