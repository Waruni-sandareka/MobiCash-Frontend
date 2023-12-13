package lk.mcashfront.activity;

import lk.mcashfront.model.MoneyTransferObject;
import lk.mcashfront.model.MoneyWithdrawal;
import lk.mcashfront.model.Wallet;
import lk.mcashfront.response.McashResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @POST("createWallet")
    Call<McashResponse>createWallet(@Body Wallet post);
    @POST("checkBalance")
    Call<McashResponse>checkBalance(@Body Wallet post);
    @POST("moneyTransfer")
    Call<McashResponse>moneyTransfer(@Body MoneyTransferObject post);
    @POST("moneyWithdrawal")
    Call<McashResponse>moneyWithdraw(@Body MoneyWithdrawal post);
}
