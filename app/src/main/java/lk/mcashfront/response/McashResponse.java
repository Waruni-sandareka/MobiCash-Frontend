package lk.mcashfront.response;

import lk.mcashfront.model.MoneyTransferObject;
import lk.mcashfront.model.MoneyWithdrawal;
import lk.mcashfront.model.Wallet;

public class McashResponse {
    private String message;
    private String code;
    private Wallet wallet;
    private MoneyTransferObject moneyTransferObject;
    private MoneyWithdrawal moneyWithdrawal;

    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code=code;
    }
    public Wallet getWallet(){
        return wallet;
    }
    public void setWallet(Wallet wallet){
        this.wallet=wallet;
    }
    public MoneyTransferObject getMoneyTransferObject(){
        return moneyTransferObject;
    }

    public void setMoneyTransferObject(MoneyTransferObject moneyTransferObject) {
        this.moneyTransferObject = moneyTransferObject;
    }
    public MoneyWithdrawal getMoneyWithdrawal(){
        return moneyWithdrawal;
    }
    public void setMoneyWithdrawal(MoneyWithdrawal moneyWithdrawal){
        this.moneyWithdrawal=moneyWithdrawal;
    }
}
