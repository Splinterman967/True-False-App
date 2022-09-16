package com.example.true_false.Data;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.true_false.Modul.Question;
import com.example.true_false.controllersingleton.request;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    String questionUrl = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<Question> questions = new ArrayList<>();
    int counter = 0;

    public List<Question> getQuestions(final AnswerListSyncResponse callback) {

        JsonArrayRequest questionRequest = new JsonArrayRequest(Request.Method.GET, questionUrl, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
//                    Question question1=new Question(String answer,boolean answerTrue);
                    Question question = new Question();
                    question.setAnswer((String) response.getJSONArray(i).get(0));
                    question.setAnswerTrue((Boolean) response.getJSONArray(i).get(1));
                    questions.add(question);
                    Log.d("question", "getQuestions: " + questions.get(i).getAnswer());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (null != callback) callback.processFinished(questions);

        }, error -> {
            Log.d("questionRequest", "Failed: ");
        });

        request.getInstance().addToRequestQueue(questionRequest);

        return null;

    }

    public int showQuestion() {
        for (int i = 0; i < questions.size(); i++) {
            String s = questions.get(1).getAnswer();
        }


        return 0;
    }


}
