package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Question;

import java.util.ArrayList;
import java.util.Random;

public class QuestioList {

    private ArrayList<Question> questions;

    QuestioList() {
        questions = new ArrayList<>();
    }

    /**
     * adds a question into the questionnaire
     * @param q Question with all fields set
     */
    public void addQuestion(Question q) {
        questions.add(q);
    }

    /**
     * Returns a random question out of the questionnaire for a random mode
     * @return
     */
    public Question getRandomQuestion() {
        Random rdm = new Random();
        int i = rdm.nextInt() % questions.size();
        return questions.get(i);
    }

    /**
     * getter for the questionnaire, if all questions have to be answered in a row
     * @return
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }
}
