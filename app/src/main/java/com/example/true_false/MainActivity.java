package com.example.true_false;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.true_false.Data.AnswerListSyncResponse;
import com.example.true_false.Data.Prefs;
import com.example.true_false.Data.Repository;
import com.example.true_false.Modul.Question;
import com.example.true_false.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
private ActivityMainBinding binding;
private  int questionIndex =0;
private  int score =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TextView questionText = findViewById(R.id.questionTextView);
        Prefs prefs=new Prefs(this);
        questionIndex= prefs.getCurrentQuestionIndex();
        String questionIndexText=String.valueOf(prefs.getCurrentQuestionIndex());
//        binding.questionTextView.setText("Question "+questionIndexText+"/913");
        binding.highestScoreTextView.setText("Highest Score: ");

//         binding.highestScoreTextView.setText(prefs.getHighestScore());


        List<Question>  questions = new Repository().getQuestions(new AnswerListSyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questions) {
//                Log.d("question", "processFinished: " + questions.get(0).toString());

                binding.questionRenderTextView.setText(questions.get(questionIndex).getAnswer());

                binding.trueButton.setOnClickListener(view -> {
                    if (questions.get(questionIndex).getAnswerTrue()) {
                        score +=10;
                        updateQuestion(questions);
                        fadeAnimation();
                        Toast.makeText(MainActivity.this, "True!", Toast.LENGTH_SHORT).show();

                    } else {
                        score -=5;
                        updateQuestion( questions);
                        shakeAnimation();
                        Toast.makeText(MainActivity.this, "False!", Toast.LENGTH_SHORT).show();
                    }
                });

                binding.falseButton.setOnClickListener(view -> {
                    if (!questions.get(questionIndex).getAnswerTrue()) {
                        score +=10;
                        updateQuestion( questions);
                        fadeAnimation();
                        Toast.makeText(MainActivity.this, "True!", Toast.LENGTH_SHORT).show();
                    } else {
                        score -=5;
                        updateQuestion( questions);
                        shakeAnimation();
                        Toast.makeText(MainActivity.this, "False!", Toast.LENGTH_SHORT).show();
                    }
                });

                binding.nextButton.setOnClickListener(view -> updateQuestion(questions));



            }

            });
        }

//METHODS

public void updateQuestion(ArrayList<Question> questions){
    Prefs prefs=new Prefs(this);
    prefs.saveCurrentQuestionIndex(questionIndex);
    questionIndex++;
    binding.questionRenderTextView.setText(questions.get(questionIndex).getAnswer());
    binding.questionTextView.setText(new StringBuilder().append("Question ").append(questionIndex+1).append("/").append(questions.size()).toString());

    updateScore();
//    prefs.saveHighestScore(score);
    }

public void updateScore(){
    Prefs prefs=new Prefs(this);
    prefs.saveHighestScore(score);
    binding.highestScoreTextView.setText(new StringBuilder().append("Highest Score: ").append(String.valueOf(prefs.getHighestScore())).toString());
        binding.scoreTextView.setText(String.valueOf(score));
}


//ANIMATION METHODS
    public void shakeAnimation(){
    Animation shake = AnimationUtils.loadAnimation(MainActivity.this,R.anim.shameanimation);
    binding.cardView.setAnimation(shake);
    shake.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            binding.questionRenderTextView.setTextColor(Color.RED);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            binding.questionRenderTextView.setTextColor(Color.WHITE);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
    }

    public void fadeAnimation(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0f);
        alphaAnimation.setDuration(200);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        binding.questionRenderTextView.setAnimation(alphaAnimation);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionRenderTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionRenderTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

