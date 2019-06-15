package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Question;

import java.util.ArrayList;

public class QuestioList {

    ArrayList<Question> questions;

    QuestioList(){
        questions=new ArrayList<>();
    }

    public void addQuestion(Question q){
        questions.add(q);
    }
}
