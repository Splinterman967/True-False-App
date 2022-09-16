package com.example.true_false.Data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public static final String HIGHEST_SCORE = "highest score";
    private SharedPreferences sharedPreferences;

    public Prefs(Activity context){
        this.sharedPreferences=context.getPreferences(Context.MODE_PRIVATE);
    }

    public void saveHighestScore(int score){
        int currentScore=score;
        int highestScore=sharedPreferences.getInt(HIGHEST_SCORE,0);
        if (currentScore>highestScore){
            sharedPreferences.edit().putInt(HIGHEST_SCORE,currentScore).apply();
            //From now on highestScore is the current biggest score.
        }


    }
    public int getHighestScore(){
        return sharedPreferences.getInt(HIGHEST_SCORE,0);
    }
    public void saveCurrentQuestionIndex(int currentQuestionIndex){
     sharedPreferences.edit().putInt("Current Question Index",currentQuestionIndex).apply();

    }
    public int getCurrentQuestionIndex(){
        return sharedPreferences.getInt("Current Question Index",0);
    }
}
