package com.example.task1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.time.DayOfWeek;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainUi extends AppCompatActivity {

    Button btn_answerOption0, btn_answerOption1, btn_answerOption2, btn_answerOption3;
    TextView level, levelText, score, scoreText, randomDate;
    ProgressBar progressBar;
    View screenView;
    Vibrator vibrator2;
    int secondsRemaining;

    Game g=new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        this.setTitle("GUESS THE DAY QUIZ");

        btn_answerOption0=findViewById(R.id.btn_answerOption0);
        btn_answerOption1=findViewById(R.id.btn_answerOption1);
        btn_answerOption2=findViewById(R.id.btn_answerOption2);
        btn_answerOption3=findViewById(R.id.btn_answerOption3);

        level=findViewById(R.id.level);
        levelText=findViewById(R.id.levelText);
        score=findViewById(R.id.score);
        scoreText=findViewById(R.id.scoreText);
        randomDate=findViewById(R.id.randomDate);

        progressBar=findViewById(R.id.progressBar);
        screenView=findViewById(R.id.rView);

        level.setText("1");
        levelText.setText("Round :");
        score.setText("0");
        scoreText.setText("Score :");


        vibrator2=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        nextTurn();

        if(savedInstanceState!=null){
            g.setScore(savedInstanceState.getInt("getScoreHorizontal"));
            score.setText(Integer.toString(g.getScore()));
            g.setTotalNumberOfQuestions(savedInstanceState.getInt("TotalNumberOfQuestionsHorizontal"));
            level.setText(Integer.toString(g.getTotalNumberOfQuestions()));
            g.getCurrentQuestion().setDayAnswer(DayOfWeek.valueOf(savedInstanceState.getString("getDayAnswerHorizontal")));
            secondsRemaining=savedInstanceState.getInt("secondsRemainingHorizontal");
        }

        View.OnClickListener btn_answerOptionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                vibrator2.vibrate(75);
                DayOfWeek answerSelected=DayOfWeek.valueOf(buttonClicked.getText().toString());
                g.checkAnswer(answerSelected);
                g.changeBackground(screenView);
                score.setText(Integer.toString(g.getScore()));
                if(g.isFlag()){
                    nextTurn();
                    level.setText(Integer.toString(g.getTotalNumberOfQuestions()));
                }
                else{
                    secondsRemaining=secondsRemaining-5;
                }
            }
        };

        btn_answerOption0.setOnClickListener(btn_answerOptionClickListener);
        btn_answerOption1.setOnClickListener(btn_answerOptionClickListener);
        btn_answerOption2.setOnClickListener(btn_answerOptionClickListener);
        btn_answerOption3.setOnClickListener(btn_answerOptionClickListener);

    }

    public void nextTurn(){
        timer.cancel();
        timer.start();
        secondsRemaining=20;
        g.makeNewQuestion();
        DayOfWeek[] answerArray=g.getCurrentQuestion().getOptionsArray();

        btn_answerOption0.setText(answerArray[0].toString());
        btn_answerOption1.setText(answerArray[1].toString());
        btn_answerOption2.setText(answerArray[2].toString());
        btn_answerOption3.setText(answerArray[3].toString());

        randomDate.setText(g.getCurrentQuestion().getFormattedRandomDate());
    }

    CountDownTimer timer=new CountDownTimer(20000,1000) {
        @Override
        public void onTick(long millisInFuture) {
            secondsRemaining--;
            progressBar.setProgress(secondsRemaining);
            if(secondsRemaining<=0){
                Finish();
            }
        }
        @Override
        public void onFinish() {
            Finish();
        }
    };

    public void Finish(){
        timer.cancel();
        Intent MainUiToScorePage=new Intent(MainUi.this,scorePage.class);
        MainUiToScorePage.putExtra("scoreFinal",Integer.toString(g.getScore()));
        startActivity(MainUiToScorePage);
    }
    @Override
    public void onBackPressed(){
        finish();
        timer.cancel();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("secondsRemainingHorizontal",secondsRemaining);
        outState.putInt("getScoreHorizontal",g.getScore());
        outState.putInt("TotalNumberOfQuestionsHorizontal",g.getTotalNumberOfQuestions());
        outState.putCharSequence("randomDateHorizontal",randomDate.getText());
        outState.putCharSequence("btn_answerOption0Horizontal",btn_answerOption0.getText());
        outState.putCharSequence("btn_answerOption1Horizontal",btn_answerOption1.getText());
        outState.putCharSequence("btn_answerOption2Horizontal",btn_answerOption2.getText());
        outState.putCharSequence("btn_answerOption3Horizontal",btn_answerOption3.getText());
        outState.putString("getDayAnswerHorizontal",g.getCurrentQuestion().getDayAnswer().toString());
        timer.cancel();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        randomDate.setText(savedInstanceState.getCharSequence("randomDateHorizontal"));
        btn_answerOption0.setText(savedInstanceState.getCharSequence("btn_answerOption0Horizontal"));
        btn_answerOption1.setText(savedInstanceState.getCharSequence("btn_answerOption1Horizontal"));
        btn_answerOption2.setText(savedInstanceState.getCharSequence("btn_answerOption2Horizontal"));
        btn_answerOption3.setText(savedInstanceState.getCharSequence("btn_answerOption3Horizontal"));

    }
}





