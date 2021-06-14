package com.example.task1;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

public class Question {

    private String formattedRandomDate;
    private DayOfWeek dayAnswer;
    private DayOfWeek dayOption;
    private DayOfWeek[] optionsArray;


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Question(){
        Random randomGenerator = new Random();
        long randomLong = (Math.abs(randomGenerator.nextLong()) % (52L * 365));
        LocalDate randomDate = LocalDate.ofEpochDay(randomLong);
        formattedRandomDate = randomDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        dayAnswer = randomDate.getDayOfWeek();

        optionsArray = new DayOfWeek[4];
        int m=randomGenerator.nextInt(4);
        optionsArray[m] = dayAnswer;

        for(int i=0;i<4;) {
            if(i==m){
                i++;
                continue;
            }
            else {
                long randomLongOption = (Math.abs(randomGenerator.nextLong()) % (52L * 365));
                LocalDate randomDateOption = LocalDate.ofEpochDay(randomLongOption);
                dayOption = randomDateOption.getDayOfWeek();
                boolean optionIsUnique = true;
                for (int j = 0; j < 4; j++) {
                    if (dayOption == optionsArray[j])
                        optionIsUnique = false;
                }
                if (optionIsUnique) {
                    optionsArray[i]=dayOption;
                    i++;
                }

            }
        }
    }




    public String getFormattedRandomDate() {
        return formattedRandomDate;
    }

    public void setFormattedRandomDate(String formattedRandomDate) {
        this.formattedRandomDate = formattedRandomDate;
    }

    public DayOfWeek getDayAnswer() {
        return dayAnswer;
    }

    public void setDayAnswer(DayOfWeek dayAnswer) {
        this.dayAnswer = dayAnswer;
    }

    public DayOfWeek getDayOption() {
        return dayOption;
    }

    public void setDayOption(DayOfWeek dayOption) {
        this.dayOption = dayOption;
    }

    public DayOfWeek[] getOptionsArray() {
        return optionsArray;
    }

    public void setOptionsArray(DayOfWeek[] optionsArray) {
        this.optionsArray = optionsArray;
    }
}
