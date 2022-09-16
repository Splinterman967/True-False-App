package com.example.true_false.Data;

import com.example.true_false.Modul.Question;

import java.util.ArrayList;

public interface AnswerListSyncResponse {
    void processFinished(ArrayList<Question> questions);
}
