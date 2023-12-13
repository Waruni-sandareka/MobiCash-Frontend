package lk.mcashfront.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import lk.mcashfront.R;

public class DashBoard extends AppCompatActivity {
    TextView DashTitle;
    ImageView McashDashIV,TreeDashIV,walletIV,withdrawIV,balanceIV,transferIV;
    Button Walletbtn,Withdrawbtn,Balancebtn,Transferbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        DashTitle = findViewById(R.id.DashTitle);
        McashDashIV = findViewById(R.id.McashDashIV);
        TreeDashIV = findViewById(R.id.TreeDashIV);
        walletIV = findViewById(R.id.walletIV);
        withdrawIV = findViewById(R.id.withdrawIV);
        balanceIV = findViewById(R.id.balanceIV);
        transferIV = findViewById(R.id.transferIV);
        Walletbtn = findViewById(R.id.Walletbtn);
        Withdrawbtn = findViewById(R.id.Withdrawbtn);
        Balancebtn = findViewById(R.id.Balancebtn);
        Transferbtn = findViewById(R.id.Transferbtn);

        Walletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w = new Intent(DashBoard.this,CreateWallet.class);
                startActivity(w);
            }
        });
        Withdrawbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withdraw = new Intent(DashBoard.this,MoneyWithdraw.class);
                startActivity(withdraw);
            }
        });
        Balancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(DashBoard.this,CheckBalance.class);
                startActivity(b);
            }
        });
        Transferbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(DashBoard.this,MoneyTransfer.class);
                startActivity(t);
            }
        });
    }
}