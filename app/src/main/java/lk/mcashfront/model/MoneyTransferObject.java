package lk.mcashfront.model;

public class MoneyTransferObject {
    private Integer fromnumber;
    private Integer tonumber;
    private int pin;
    private double amount;

    public MoneyTransferObject(Integer fromnumber,Integer tonumber,int pin,double amount){
        this.fromnumber=fromnumber;
        this.tonumber=tonumber;
        this.pin=pin;
        this.amount=amount;
    }
    public Integer getFromnumber(){
        return fromnumber;
    }
    public void setFromnumber(Integer fromnumber){
        this.fromnumber=fromnumber;
    }
    public Integer getTonumber(){
        return tonumber;
    }
    public void setTonumber(Integer tonumber){
        this.tonumber=tonumber;
    }
    public int getPin(){
        return pin;
    }
    public void setPin(int pin){
        this.pin=pin;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }

}
