package com.example.task1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_quit;
    ImageButton btn_title;
    TextView highScoreText, highScore;
    Vibrator vibrator1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("MENU");

        btn_start = findViewById(R.id.btn_start);
        btn_quit = findViewById(R.id.btn_quit);
        btn_title = findViewById(R.id.btn_title);
        highScoreText = findViewById(R.id.highScoreText);
        highScore = findViewById(R.id.highScore);

        btn_start.setText("Start");
        btn_quit.setText("Quit");
        highScoreText.setText("High Score :");

        SharedPreferences prefs = this.getSharedPreferences("highScorePrefsKey", Context.MODE_PRIVATE);
        String highScoreMainMenu = prefs.getString("highScoreKey", "0");

        highScore.setText(highScoreMainMenu);

        vibrator1 = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        View.OnClickListener btn_titleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton titlebutton = (ImageButton) v;
                Toast.makeText(MainActivity.this, "Never Gonna Give You Up", Toast.LENGTH_SHORT).show();
            }
        };
        btn_title.setOnClickListener(btn_titleClickListener);

        View.OnClickListener btn_startClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                startActivity(new Intent(MainActivity.this, MainUi.class));
                vibrator1.vibrate(75);
            }
        };
        btn_start.setOnClickListener(btn_startClickListener);

        View.OnClickListener btn_quitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button quit_button = (Button) v;
                vibrator1.vibrate(75);
                System.exit(0);
            }
        };
        btn_quit.setOnClickListener(btn_quitClickListener);
    }
}









