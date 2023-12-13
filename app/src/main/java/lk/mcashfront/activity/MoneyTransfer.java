package lk.mcashfront.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import lk.mcashfront.R;
import lk.mcashfront.model.MoneyTransferObject;
import lk.mcashfront.model.Wallet;
import lk.mcashfront.response.McashResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoneyTransfer extends AppCompatActivity {
    private ImageView McashLogo3,TreeLogo3;
    private EditText edtFromNo,edtToNo,edtPinNo3,edtAmount2;
    private Button btnTransfer;
    private CardView cardViewTransfer;
    private TextView txtResponseTransfer;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);

        initViews();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.101:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewTransfer.setVisibility(View.VISIBLE);
                moneyTransfer();
            }
        });
    }
    void initViews(){
        btnTransfer = findViewById(R.id.btnTransfer);
        edtFromNo = findViewById(R.id.edtFromNo);
        edtToNo = findViewById(R.id.edtToNo);
        edtAmount2 = findViewById(R.id.edtAmount2);
        edtPinNo3 = findViewById(R.id.edtPinNo3);
        cardViewTransfer = findViewById(R.id.cardViewTransfer);
        txtResponseTransfer = findViewById(R.id.txtResponseTransfer);
    }
    private void moneyTransfer() {
        int fromNo, toNo;
        if ((edtFromNo.getText().length() == 10) && (edtToNo.getText().length() == 10)) {
            fromNo = Integer.parseInt(edtFromNo.getText().toString().substring(1));
            toNo = Integer.parseInt(edtToNo.getText().toString().substring(1));
        } else {
            fromNo = Integer.parseInt(edtFromNo.getText().toString());
            toNo = Integer.parseInt(edtToNo.getText().toString());
        }
        MoneyTransferObject moneyTransferObject = new MoneyTransferObject(fromNo, toNo, Integer.parseInt(edtPinNo3.getText().toString()),Integer.parseInt(edtAmount2.getText().toString()));
        //Wallet wallet = new Wallet(0716269346,0716269348,50,110);
        Call<McashResponse> call = jsonPlaceHolderApi.moneyTransfer(moneyTransferObject);
        call.enqueue(new Callback<McashResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<McashResponse> call, Response<McashResponse> response) {
                if (!response.isSuccessful()) {
                    txtResponseTransfer.setText("code: " + response.code());
                    return;
                }
                McashResponse walletResponse = response.body();
                String content = "";
                assert walletResponse != null;
                content += walletResponse.getMessage() + "\n";
                content += "From Mobile No: " + walletResponse.getWallet().getMobileno() + "\n";
                content += "To Mobile No: " + walletResponse.getWallet().getMobileno() + "\n";
                content += "Pin No: " + walletResponse.getWallet().getPin() + "\n";
                content += "Transfer Amount Rs: " + walletResponse.getWallet().getAmount();
                txtResponseTransfer.setText(content);
            }

            @Override
            public void onFailure(Call<McashResponse> call, Throwable t) {
                txtResponseTransfer.setText(t.getMessage());
            }
        });
    }
}
