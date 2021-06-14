package com.example.task1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class scorePage extends AppCompatActivity {

    Button btn_returnToMenu,btn_quit2;
    TextView finalScore, timeUp;
    Vibrator vibrator3;
    String scoreFinal, highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("RESULT");

        Bundle extras = getIntent().getExtras();
        scoreFinal=extras.getString("scoreFinal");

        SharedPreferences prefs = this.getSharedPreferences("highScorePrefsKey", Context.MODE_PRIVATE);
        highScore = prefs.getString("highScoreKey", "0");

        if(Integer.parseInt(scoreFinal)>=Integer.parseInt(highScore)){
            highScore=scoreFinal;
        }

        prefs = this.getSharedPreferences("highScorePrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("highScoreKey", highScore);
        editor.commit();

        btn_returnToMenu=findViewById(R.id.btn_returnToMenu);
        btn_quit2=findViewById(R.id.btn_quit2);
        finalScore=findViewById(R.id.finalScore);
        timeUp=findViewById(R.id.timeUp);

        btn_returnToMenu.setText("RETURN TO MENU");
        btn_quit2.setText("QUIT");
        finalScore.setText("SCORE : "+scoreFinal);
        timeUp.setText("TIME UP");

        vibrator3=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        View.OnClickListener btn_returnToMenuClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button returntomenubutton=(Button) v;
                vibrator3.vibrate(75);
                startActivity(new Intent(scorePage.this,MainActivity.class));
            }
        };
        btn_returnToMenu.setOnClickListener(btn_returnToMenuClickListener);

        View.OnClickListener btn_quit2ClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button quit2_button=(Button) v;
                vibrator3.vibrate(75);
                finishAffinity();
            }
        };
        btn_quit2.setOnClickListener(btn_quit2ClickListener);
    }
    public void onBackPressed() {
        finishAffinity();
    }

}