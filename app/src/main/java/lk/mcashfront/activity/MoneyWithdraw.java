package lk.mcashfront.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import lk.mcashfront.R;
import lk.mcashfront.model.MoneyWithdrawal;
import lk.mcashfront.model.Wallet;
import lk.mcashfront.response.McashResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoneyWithdraw extends AppCompatActivity {
    private EditText edtMobileNoWithdraw,edtPinWithdraw,edtWithdrawAmount;
    private Button btnWithdraw;
    private TextView txtResponse4;
    private CardView cardViewNotifyWithdraw;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_withdraw);

        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewNotifyWithdraw.setVisibility(View.VISIBLE);
                moneyWithdraw();
            }
        });

    }
    private void initViews(){
        edtMobileNoWithdraw = findViewById(R.id.edtMobileNoWithdraw);
        edtPinWithdraw = findViewById(R.id.edtPinNoWithdraw);
        edtWithdrawAmount = findViewById(R.id.edtWithdrawAmount);
        btnWithdraw = findViewById(R.id.btnWithdraw);
        txtResponse4 = findViewById(R.id.txtResponse4);
        cardViewNotifyWithdraw = findViewById(R.id.cardViewNotifyWithdraw);

    }
    private void moneyWithdraw(){
        int mobileNo;
        if (edtMobileNoWithdraw.getText().length() == 10) {
            mobileNo = Integer.parseInt(edtMobileNoWithdraw.getText().toString().substring(1));
        } else {
            mobileNo = Integer.parseInt(edtMobileNoWithdraw.getText().toString());
        }
        MoneyWithdrawal moneyWithdrawal =  new MoneyWithdrawal(mobileNo,Integer.parseInt(edtPinWithdraw.getText().toString()),Integer.parseInt(edtWithdrawAmount.getText().toString()));
        Call<McashResponse> call = jsonPlaceHolderApi.moneyWithdraw(moneyWithdrawal);
        call.enqueue(new Callback<McashResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<McashResponse> call, Response<McashResponse> response) {
                if(!response.isSuccessful()){
                    txtResponse4.setText("code: " + response.code());
                    return;
                }
                McashResponse walletResponse = response.body();
                String content ="";
                assert walletResponse != null;
                content+= walletResponse.getMessage()+"\n";
                content+= "Mobile No: "+walletResponse.getWallet().getMobileno()+"\n";
                content+= "Pin No: "+walletResponse.getWallet().getPin()+"\n";
                content+= "Updated Balance Rs: "+walletResponse.getWallet().getAmount();
                txtResponse4.setText(content);
            }

            @Override
            public void onFailure(Call<McashResponse> call, Throwable t) {
                txtResponse4.setText(t.getMessage());
            }
        });
    }
}