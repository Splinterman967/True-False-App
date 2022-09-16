package com.example.true_false.Modul;

import androidx.annotation.NonNull;

public class Question {
    private String answer;
    private boolean answerTrue;

    public Question(){}

    public Question(String answer, boolean answerTrue) {
        this.answer = answer;
        this.answerTrue = answerTrue;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    @NonNull
    @Override
    public String toString() {
        return "Question: "+getAnswer()+"Answer: "+getAnswerTrue();
    }
}
