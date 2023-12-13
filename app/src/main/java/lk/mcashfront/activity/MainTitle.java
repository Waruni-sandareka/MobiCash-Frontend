package lk.mcashfront.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import lk.mcashfront.R;

public class MainTitle extends AppCompatActivity {
    TextView MainTitles;
    Button StartButton;
    ImageView McashIMG,TreeIMG;
    Animation photoanim,textanim,btnanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainTitles = findViewById(R.id.MainTitles);
        StartButton = findViewById(R.id.StartButton);
        McashIMG = findViewById(R.id.McashIMG);
        TreeIMG = findViewById(R.id.TreeIMG);

        //load Animations
        photoanim = AnimationUtils.loadAnimation(this,R.anim.photoanim);
        textanim = AnimationUtils.loadAnimation(this,R.anim.textanim);
        btnanim = AnimationUtils.loadAnimation(this,R.anim.btnanim);

        //Passing Animations
        McashIMG.startAnimation(photoanim);
        TreeIMG.startAnimation(photoanim);
        StartButton.startAnimation(btnanim);
        MainTitles.startAnimation(textanim);

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainTitle.this,DashBoard.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });
    }
}