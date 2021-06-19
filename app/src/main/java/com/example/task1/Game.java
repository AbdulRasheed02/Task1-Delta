package com.example.task1;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import androidx.annotation.RequiresApi;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Question> questions;
    private int totalNumberOfQuestions;
    private int score;
    private Question currentQuestion;
    private boolean flag;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Game() {
        totalNumberOfQuestions = 0;
        score = 0;
        currentQuestion = new Question();
        questions = new ArrayList<Question>();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void makeNewQuestion () {
        currentQuestion = new Question();
        totalNumberOfQuestions++;
        questions.add(currentQuestion);
    }

    public void checkAnswer (DayOfWeek submittedAnswer){
        if (currentQuestion.getDayAnswer() == submittedAnswer) {
                score = score + 10;
                flag=true;
        }
        else {
                score = score - 5;
                flag=false;
        }
    }


    public void changeBackground(View changeBackground){
        if(flag) {
            changeBackground.setBackgroundColor(Color.parseColor("#dcfcf7"));
            Handler handler=new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeBackground.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            },500);
        }

        else{
            changeBackground.setBackgroundColor(Color.parseColor("#fac8c9"));
            Handler handler=new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeBackground.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
            },500);
        }
    }

        public List<Question> getQuestions () {
            return questions;
        }

        public void setQuestions (List < Question > questions) {
            this.questions = questions;
        }

        public int getTotalNumberOfQuestions () {
            return totalNumberOfQuestions;
        }

        public void setTotalNumberOfQuestions ( int totalNumberOfQuestions){
            this.totalNumberOfQuestions = totalNumberOfQuestions;
        }

        public int getScore () {
            return score;
        }

        public void setScore ( int score){
            this.score = score;
        }

        public Question getCurrentQuestion () {
            return currentQuestion;
        }

        public void setCurrentQuestion (Question currentQuestion){
            this.currentQuestion = currentQuestion;
        }
        public boolean isFlag() {
        return flag;
        }

        public void setFlag(boolean flag) {
        this.flag = flag;
        }


}
