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

public class CreateWallet extends AppCompatActivity {

    private Button btnCreateWallet;
    private EditText edtMobileNo;
    private EditText edtPinNo;
    private EditText edtAmount;
    private CardView cardViewNotify;
    private TextView txtResponse;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);

        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        btnCreateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewNotify.setVisibility(View.VISIBLE);
                createWallet();
            }
        });

    }
    void initViews(){
        btnCreateWallet = findViewById(R.id.btnCreate);
        edtMobileNo = findViewById(R.id.edtMobileNo2);
        edtPinNo = findViewById(R.id.edtPinNo2);
        edtAmount = findViewById(R.id.TxtAmount);
        EditText edtConfirmPinNo = findViewById(R.id.TxtConfirmPin);
        cardViewNotify = findViewById(R.id.cardViewNotify);
        txtResponse = findViewById(R.id.txtResponse);
    }
    private void createWallet() {
        int mobileNo;
        if (edtMobileNo.getText().length() == 10) {
            mobileNo = Integer.parseInt(edtMobileNo.getText().toString().substring(1));
        } else {
            mobileNo = Integer.parseInt(edtMobileNo.getText().toString());
        }
        Wallet wallet = new Wallet(mobileNo,Integer.parseInt(edtAmount.getText().toString()),Integer.parseInt(edtPinNo.getText().toString()));
        //Wallet wallet = new Wallet(0716269346,50,110);
        Call<McashResponse> call = jsonPlaceHolderApi.createWallet(wallet);
        call.enqueue(new Callback<McashResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<McashResponse> call, Response<McashResponse> response) {
                if(!response.isSuccessful()){
                    txtResponse.setText("code: " + response.code());
                    return;
                }
                McashResponse walletResponse = response.body();
                String content ="";
                assert walletResponse != null;
                content+= walletResponse.getMessage()+"\n";
                content+= "Mobile No: "+walletResponse.getWallet().getMobileno()+"\n";
                content+= "Pin No: "+walletResponse.getWallet().getPin()+"\n";
                content+= "Amount Rs: "+walletResponse.getWallet().getAmount();
                txtResponse.setText(content);
            }

            @Override
            public void onFailure(Call<McashResponse> call, Throwable t) {
                    txtResponse.setText(t.getMessage());
            }
        });
    }
}