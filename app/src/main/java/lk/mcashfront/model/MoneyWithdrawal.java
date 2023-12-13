package lk.mcashfront.model;

public class MoneyWithdrawal {
    private Integer mobileno;
    private int pin;
    private double withdrawamount;

    public MoneyWithdrawal(int mobileno,int pin,double withdrawamount){
        this.mobileno = mobileno;
        this.pin = pin;
        this.withdrawamount = withdrawamount;
    }
    public Integer getMobileno(){
        return mobileno;
    }
    public void setMobileno(Integer mobileno){
        this.mobileno=mobileno;
    }
    public int getPin(){
        return pin;
    }
    public void setPin(int pin){
        this.pin=pin;
    }
    public double getWithdrawamount(){
        return withdrawamount;
    }
    public void setWithdrawamount(double amount){
        this.withdrawamount=withdrawamount;
    }
}
