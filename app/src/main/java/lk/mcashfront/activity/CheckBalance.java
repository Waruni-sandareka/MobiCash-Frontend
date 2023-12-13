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
import lk.mcashfront.model.Wallet;
import lk.mcashfront.response.McashResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckBalance extends AppCompatActivity {

    private Button btnCheck;
    private TextView txtAmount,txtResponse2;
    private EditText edtMobileNoCheck,edtPinNoCheck;
    private CardView cardViewNotify2;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_balance);

        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewNotify2.setVisibility(View.VISIBLE);
                checkBalance();
            }
        });
    }
        void initViews(){
            btnCheck = findViewById(R.id.btnCheck);
            txtAmount = findViewById(R.id.TxtAmount);
            txtResponse2 = findViewById(R.id.txtResponse2);
            edtMobileNoCheck = findViewById(R.id.edtMobileNoCheck);
            edtPinNoCheck = findViewById(R.id.edtPinNoCheck);
            cardViewNotify2 = findViewById(R.id.cardViewNotify2);
    }
    private void checkBalance(){
        int mobileNo;
        if (edtMobileNoCheck.getText().length() == 10) {
            mobileNo = Integer.parseInt(edtMobileNoCheck.getText().toString().substring(1));
        } else {
            mobileNo = Integer.parseInt(edtMobileNoCheck.getText().toString());
        }
        Wallet wallet = new Wallet(mobileNo,0,Integer.parseInt(edtPinNoCheck.getText().toString()));
        //Wallet wallet = new Wallet(0716269346,50,110);

        Call<McashResponse> call = jsonPlaceHolderApi.checkBalance(wallet);
        call.enqueue(new Callback<McashResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<McashResponse> call, Response<McashResponse> response) {
                if(!response.isSuccessful()){
                    txtResponse2.setText("code: " + response.code());
                    return;
                }
                McashResponse walletResponse = response.body();
                String content ="";
                assert walletResponse != null;
                content+= walletResponse.getMessage()+"\n";
                content+= "Mobile No: "+walletResponse.getWallet().getMobileno()+"\n";
                content+= "Pin No: "+walletResponse.getWallet().getPin()+"\n";
                content+= "Rs: "+walletResponse.getWallet().getAmount();
                txtResponse2.setText(content);

            }

            @Override
            public void onFailure(Call<McashResponse> call, Throwable t) {
                    txtResponse2.setText(t.getMessage());
            }
        });
    }
}

